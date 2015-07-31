/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.core.ui.wizards;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.widgets.Composite;

import de.cau.cs.kieler.core.ui.Messages;
import de.cau.cs.kieler.core.ui.util.ComboHistoryHandler;


/**
 * A wizard page that allows the user to choose files to import from the workspace.
 * Optionally, a target group may allow the user to choose a target folder in the
 * workspace to import to.
 * 
 * <p>The settings made on this page can be saved if the wizard has an instance of
 * {@code IDialogSettings} set on it. If it has not, settings are neither saved nor
 * loaded.</p>
 * 
 * @author cds
 * @kieler.design 2010-03-14
 *      reviewed by haf, msp, pkl
 * @kieler.rating yellow 2010-03-14
 *      reviewed by haf, msp, pkl
 */
public class WorkspaceResourcesPage extends ResourceTreeAndListPage {
    
    // CONSTANTS
    /**
     * Key for the target directory history setting.
     */
    private static final String SETT_TARGET_DIR_HISTORY = ".targetDir.history"; //$NON-NLS-1$
    
    /**
     * Size of the history of combo boxes.
     */
    private static final int HISTORY_SIZE = 15;
    
    // VARIABLES
    /**
     * Whether the target group should be shown.
     */
    private boolean showTargetGroup = false;
    
    /**
     * Array of allowed file name extensions. Lower case with preceding dot.
     */
    private String[] extensions = null;
    
    /**
     * Whether the source group should be initialized from the selection fed into the page.
     * If {@code true}, all items we can find in the selection are pre-selected in the
     * source group.
     */
    private boolean initializeSourceGroupFromSelection = true;
    
    /**
     * Whether the target combo folder should be initialized from the selection fed into the
     * page. If {@code true}, the first folder we find in the selection is used as the default
     * target folder.
     */
    private boolean initializeTargetGroupFromSelection = false;
    
    /**
     * The selection on which the wizard was invoked.
     */
    private IStructuredSelection selection = null;
    
    // UI WIDGETS
    private ComboHistoryHandler targetComboHistoryManager;
    
    
    /**
     * Constructs a new instance with the given name. Initially, all items in the given
     * selection are pre-selected in the source group. The target folder is empty or
     * restored from the last usage of the page.
     * 
     * @param pageName name of the page. Used as part of the IDs the dialog
     *                 settings are saved as.
     * @param showTargetGroup if {@code true}, a target group is created
     *                        where the user can select a folder in the
     *                        workspace to import to.
     * @param fileExtensions array of allowed file name extensions without
     *                       preceding dot. May be {@code null}.
     * @param selection the selection on which the wizard was invoked.
     */
    public WorkspaceResourcesPage(final String pageName, final boolean showTargetGroup,
            final String[] fileExtensions, final IStructuredSelection selection) {
        
        this(pageName, showTargetGroup, fileExtensions, selection, true, false);
    }
    
    /**
     * Constructs a new instance with the given name. Initially, all items in the given
     * selection are pre-selected in the source group. The target folder is empty or
     * restored from the last usage of the page.
     * 
     * @param pageName name of the page. Used as part of the IDs the dialog
     *                 settings are saved as.
     * @param showTargetGroup if {@code true}, a target group is created
     *                        where the user can select a folder in the
     *                        workspace to import to.
     * @param fileExtensions array of allowed file name extensions without
     *                       preceding dot. May be {@code null}.
     * @param selection the selection on which the wizard was invoked.
     * @param initSourceFromSelection {@code true} if the given selection should be used
     *                                to pre-select elements on the page.
     * @param initTargetFromSelection {@code true} if the given selection should be used
     *                                to pre-select a target folder.
     */
    public WorkspaceResourcesPage(final String pageName, final boolean showTargetGroup,
            final String[] fileExtensions, final IStructuredSelection selection,
            final boolean initSourceFromSelection, final boolean initTargetFromSelection) {
        
        super(pageName);
        
        setTitle(Messages.WorkspaceResourcesPage_title);
        setMessage(Messages.WorkspaceResourcesPage_message);
        
        this.showTargetGroup = showTargetGroup;
        this.selection = selection;
        this.initializeSourceGroupFromSelection = initSourceFromSelection;
        this.initializeTargetGroupFromSelection = initTargetFromSelection;
        
        // Save extensions
        if (fileExtensions != null) {
            extensions = new String[fileExtensions.length];
            
            for (int i = 0; i < fileExtensions.length; i++) {
                extensions[i] = fileExtensions[i].toLowerCase(); //$NON-NLS-1$
            }
        }
        
        // Initially, this page is incomplete
        setPageComplete(false);
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////
    // Result Extraction
    
    /**
     * Returns the selected files. This is a potentially long-running operation.
     * 
     * @param monitor progress monitor. May be {@code null}.
     * @return the list of selected files.
     */
    public List<IResource> getResources(final IProgressMonitor monitor) {
        Collection<Object> selectedElements = getSelectedElements(monitor);
        List<IResource> files = new ArrayList<IResource>(selectedElements.size());
        
        // Extract the files from all selection elements
        for (Object o : selectedElements) {
            files.add((IResource) o);
        }
        
        return files;
    }
    
    /**
     * Returns the target container path entered by the user. The container might
     * not exist yet.
     * 
     * @return the container to import to.
     */
    public IPath getTargetContainerPath() {
        String s = getTargetGroupCombo().getText();
        IPath path = new Path(s);
        
        if (s.length() > 0) {
            path = path.makeAbsolute();
        }
        
        return path;
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////
    // Selection Stuff
    
    /**
     * Returns the selection the wizard was invoked on.
     * 
     * @return selection the wizard was invoked on.
     */
    protected IStructuredSelection getWorkspaceSelection() {
        return selection;
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////
    // UI Stuff
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void initializeControls() {
        setResourceTreeInput(ResourcesPlugin.getWorkspace().getRoot());
        
        // Initialize source group from selection
        if (initializeSourceGroupFromSelection) {
            Iterator<?> iterator = selection.iterator();
            
            while (iterator.hasNext()) {
                Object o = iterator.next();
                
                // We can only select files
                if (o instanceof IFile) {
                    selectAndRevealElement(o, true);
                } else {
                    selectAndRevealElement(o, false);
                }
            }
        }
        
        // Initialize target group from selection
        if (initializeTargetGroupFromSelection) {
            Iterator<?> iterator = selection.iterator();
            
            while (iterator.hasNext()) {
                Object o = iterator.next();
                
                // We can only select file containers
                if (o instanceof IFolder || o instanceof IProject) {
                    targetComboHistoryManager.recordAndDisplay(
                            ((IResource) o).getFullPath().makeRelative().toString());
                } else if (o instanceof IFile) {
                    targetComboHistoryManager.recordAndDisplay(
                            ((IResource) o).getParent().getFullPath().makeRelative().toString());
                }
            }
        }
        
        // If resources were selected, the page needs to be validated
        if (!selection.isEmpty()) {
            validate();
        }
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////
    // Resources Group
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected ViewerFilter[] getResourceTreeFilters() {
        return new ViewerFilter[] {new ViewerFilter() {
            @Override
            public boolean select(final Viewer viewer, final Object parentElement,
                    final Object element) {
                
                return element instanceof IContainer;
            }
        } };
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected ViewerFilter[] getResourceListFilters() {
        if (extensions != null) {
            return new ViewerFilter[] {new ViewerFilter() {
                @Override
                public boolean select(final Viewer viewer, final Object parentElement,
                        final Object element) {
                    
                    if (element instanceof IFile) {
                        IFile file = (IFile) element;
                        String fileExtension = file.getFileExtension();
                        if (fileExtension == null) {
                            fileExtension = "";
                        }
                        
                        // Iterate through the list of allowed file filters
                        for (String ext : extensions) {
                            if (fileExtension.equalsIgnoreCase(ext)) {
                                return true;
                            }
                        }
                    }
                    
                    return false;
                }
            } };
        } else {
            return new ViewerFilter[] {new ViewerFilter() {
                @Override
                public boolean select(final Viewer viewer, final Object parentElement,
                        final Object element) {
                    
                    return element instanceof IFile;
                }
            } };
        }
    }
    

    ///////////////////////////////////////////////////////////////////////////////
    // Source Group

    /**
     * {@inheritDoc}
     */
    @Override
    protected Composite createSourceGroup(final Composite parent) {
        // We don't need a source group
        return null;
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////
    // Target Group

    /**
     * {@inheritDoc}
     */
    @Override
    protected Composite createTargetGroup(final Composite parent) {
        if (!showTargetGroup) {
            return null;
        }
        
        // The default target group is fine for us
        Composite targetGroup = createDefaultTargetGroup(parent,
                Messages.WorkspaceResourcesPage_targetGroup_text,
                Messages.WorkspaceResourcesPage_targetGroup_browse);
        
        targetComboHistoryManager = new ComboHistoryHandler(getTargetGroupCombo(), HISTORY_SIZE);
        
        getTargetGroupCombo().addFocusListener(new FocusAdapter() {
            /**
             * {@inheritDoc}
             */
            @Override
            public void focusLost(final FocusEvent e) {
                // Manage history
                targetComboHistoryManager.recordAndDisplay(getTargetGroupCombo().getText());
            }
        });
        
        return targetGroup;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doBrowseTarget() {
        // Find the current container, if any
        IPath currentPath = getTargetContainerPath();
        IContainer currentContainer = null;
        
        if (currentPath != null) {
            IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
            IResource resource = workspaceRoot.findMember(currentPath);
            
            if (resource instanceof IContainer) {
                currentContainer = (IContainer) resource;
            }
        }
        
        // Find a new container
        TargetSelectionDialog dialog = new TargetSelectionDialog(getShell());
        dialog.setTitle(Messages.WorkspaceResourcesPage_containerSelectionDialog_title);
        dialog.setMessage(getBrowseMessage());
        if (currentContainer != null) {
            dialog.setInitialSelections(new Object[] {currentContainer});
        }
        
        if (dialog.open() == Dialog.OK) {
            Object[] result = dialog.getResult();
            
            if (result != null && result.length == 1) {
                IPath newPath = (IPath) result[0];
                targetComboHistoryManager.recordAndDisplay(newPath.makeRelative().toString());
            }
        }
    }
    
    /**
     * Returns the message displayed in the target browse dialog. May be overriden
     * by subclasses.
     * 
     * @return message to be displayed in the target browse dialog.
     */
    protected String getBrowseMessage() {
        return Messages.WorkspaceResourcesPage_containerSelectionDialog_message;
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////
    // Options Group

    /**
     * {@inheritDoc}
     */
    @Override
    protected Composite createOptionsGroup(final Composite parent) {
        // No options group
        return null;
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////
    // Validation

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean doValidate() {
        super.doValidate();
        
        // The user has to select at least something
        if (!isAnythingSelected()) {
            setErrorMessage(Messages.WorkspaceResourcesPage_errors_noFileSelected);
            return false;
        }
        
        // The user may have to select a target
        if (showTargetGroup) {
            if (getTargetGroupCombo().getText().trim().length() == 0) {
                setErrorMessage(Messages.WorkspaceResourcesPage_errors_noTargetSelected);
                return false;
            }
        }
        
        return true;
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////
    // Dialog Settings

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveDialogSettings() {
        IDialogSettings dialogSettings = getDialogSettings();
        if (dialogSettings == null) {
            // The dialog settings have not been set on the wizard
            return;
        }
        
        if (showTargetGroup) {
            targetComboHistoryManager.saveHistory(dialogSettings,
                    getName() + SETT_TARGET_DIR_HISTORY);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void restoreDialogSettings() {
        IDialogSettings dialogSettings = getDialogSettings();
        if (dialogSettings == null) {
            // The dialog settings have not been set on the wizard
            return;
        }
        
        if (showTargetGroup) {
            targetComboHistoryManager.restoreHistory(dialogSettings,
                    getName() + SETT_TARGET_DIR_HISTORY);
        }
    }
}
