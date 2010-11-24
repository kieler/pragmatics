/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kaom.importer.ptolemy.wizards;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.model.BaseWorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.ui.model.WorkbenchViewerComparator;

import de.cau.cs.kieler.kaom.importer.ptolemy.PtolemyImporterConstants;


/**
 * Source file page in the {@link ImportDiagramWizard}. Allows the user to select the
 * file to import from.
 * 
 * @author cds
 */
public class ImportDiagramSourceFilePage extends WizardPage {

    /**
     * Sorts workspace objects, distinguishing between container resources and
     * normal resources. I.e., folders are shown before files.
     * 
     * @author cds
     */
    private static class ContainerVsResourcesWorkbenchViewerComparator
        extends WorkbenchViewerComparator {
        
        // CONSTANTS
        /** Container resources. (folders, mainly) */
        private static final int CAT_CONTAINERS = 0;
        /** Normal, non-container resources. */
        private static final int CAT_NORMAL_RESOURCES = 1;
        
        
        /**
         * {@inheritDoc}
         */
        @Override
        public int category(final Object element) {
            if (element instanceof IContainer) {
                return CAT_CONTAINERS;
            } else {
                return CAT_NORMAL_RESOURCES;
            }
        }
    }
    
    
    /**
     * Lets only containers and xml or moml files pass.
     * 
     * @author cds
     */
    private static class PtolemyFileFilter extends ViewerFilter {
        
        /**
         * {@inheritDoc}
         */
        @Override
        public boolean select(final Viewer viewer, final Object parentElement,
                final Object element) {
            
            if (element instanceof IContainer) {
                return true;
            } else if (element instanceof IFile) {
                // Check if the file's extension matches one of the Ptolemy file extensions
                String extension = ((IFile) element).getFileExtension();
                if (extension == null) {
                    return false;
                }
                
                for (String ptolemyExt : PtolemyImporterConstants.PTOLEMY_FILE_EXTENSIONS) {
                    if (ptolemyExt.equalsIgnoreCase(extension)) {
                        return true;
                    }
                }
                
                return false;
            } else {
                return false;
            }
        }
    }
    
    
    // LOCALIZATION
    private static final String LOC_PAGE_TITLE = "Source File";
    private static final String LOC_PAGE_DESCRIPTION =
        "Select the Ptolemy2 file to import.";
    private static final String LOC_FILEDIALOG_TITLE = "Open Ptolemy2 Diagram";
    private static final String LOC_ERR_NO_PTOLEMY_FILE_SELECTED =
        "Select a Ptolemy2 file. (file extension .moml)";
    private static final String LOC_ERR_FILE_DOESNT_EXIST =
        "Enter an existing file name.";
    private static final String LOC_ERR_FILE_UNREADABLE =
        "The given file cannot be read.";
    
    // DIALOG SETTINGS KEYS
    private static final String SETT_FROM_WORKSPACE = "importDiagramWizard.source.workspace";
    private static final String SETT_FILE_NAME_HISTORY = "importDiagramWizard.fileName.history";
    
    // MISCELLANEOUS CONSTANTS
    /** The number of history items remembered in the combo box. */
    private static final int HISTORY_ITEMS = 10;
    
    // GUI CONTROLS
    private Composite container;
    private Button fileSystemButton;
    private Combo fileNameCombo;
    private Button fileNameBrowseButton;
    private Button workspaceButton;
    private Tree workspaceTree;
    private TreeViewer workspaceTreeViewer;
    
    private FileDialog fileDialog;
    
    // MISCELLANEOUS
    /** The selection the wizard was called on. */
    private IStructuredSelection selection;
    
    
    
    /**
     * Constructs a new source file wizard page.
     * 
     * @param theSelection the selection the wizard was called on.
     */
    protected ImportDiagramSourceFilePage(final IStructuredSelection theSelection) {
        super("importDiagramWizard.importDiagramSourceFilePage");
        
        this.setTitle(LOC_PAGE_TITLE);
        this.setDescription(LOC_PAGE_DESCRIPTION);
        
        this.setPageComplete(false);
        
        this.selection = theSelection;
    }
    
    
    /**
     * Returns whether the user wants to import an external file or a workspace-local
     * file. If the former is true, use {@link getExternalFile()} to get the actual
     * file. Otherwise, use {@link getWorkspaceFile()}.
     * 
     * @return {@code true} if the user wants to import an external file, {@code false}
     *         otherwise.
     */
    public boolean isExternalFileSelected() {
        return fileSystemButton.getSelection();
    }
    
    /**
     * Returns the chosen external file, if any.
     * 
     * @return the chosen external file or {@code null} if the user didn't actually
     *         select an external file.
     */
    public File getExternalFile() {
        if (fileSystemButton.getSelection() && isPageComplete()) {
            return new File(fileNameCombo.getText());
        } else {
            return null;
        }
    }
    
    /**
     * Returns the chosen workspace-internal file, if any.
     * 
     * @return the chosen workspace-internal file or {@code null} if the user didn't
     *         actually select a workspace-internal file.
     */
    public IFile getWorkspaceFile() {
        if (workspaceButton.getSelection() && isPageComplete()) {
            IStructuredSelection treeSelection =
                (IStructuredSelection) workspaceTreeViewer.getSelection();
            
            if (treeSelection.isEmpty()) {
                return null;
            } else {
                Object element = treeSelection.getFirstElement();
                
                if (element instanceof IFile) {
                    return (IFile) element;
                } else {
                    return null;
                }
            }
        } else {
            return null;
        }
    }
    
    
    /**
     * {@inheritDoc}
     */
    public void createControl(final Composite parent) {
        // This method uses magic numbers for layout purposes, so keep Checkstyle from
        // checking for those
        // CHECKSTYLEOFF MagicNumber
        
        GridData gd;
        GridLayout gl = new GridLayout(2, false);
        
        // Container composite
        container = new Composite(parent, SWT.NULL);
        container.setLayout(gl);
        this.setControl(container);
        
        // File System Button
        fileSystemButton = new Button(container, SWT.RADIO);
        fileSystemButton.setText("From file system:");
        fileSystemButton.setToolTipText("Import a file from a source outside of the workspace.");
        
        gd = new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false);
        gd.horizontalSpan = 2;
        fileSystemButton.setLayoutData(gd);
        
        // File Name Combo
        fileNameCombo = new Combo(container, SWT.DROP_DOWN | SWT.BORDER);
        fileNameCombo.setToolTipText(
                "Enter a file name or choose one of the previously selected file names.");
        
        gd = new GridData(SWT.FILL, SWT.BEGINNING, true, false);
        gd.horizontalIndent = 20;
        fileNameCombo.setLayoutData(gd);
        
        // File Name Browse Button
        fileNameBrowseButton = new Button(container, SWT.DEFAULT);
        fileNameBrowseButton.setText("Browse...");
        fileNameBrowseButton.setToolTipText("Browse the file system...");
        
        gd = new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false);
        fileNameBrowseButton.setLayoutData(gd);
        
        // Workspace Button
        workspaceButton = new Button(container, SWT.RADIO);
        workspaceButton.setText("From workspace:");
        workspaceButton.setToolTipText("Import a file from inside the workspace.");
        
        gd = new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false);
        gd.horizontalSpan = 2;
        workspaceButton.setLayoutData(gd);
        
        // Workspace Tree
        workspaceTree = new Tree(container, SWT.SINGLE | SWT.BORDER);
        workspaceTree.setToolTipText("Select a file from the current workspace.");
        
        gd = new GridData(SWT.FILL, SWT.FILL, true, true);
        gd.horizontalIndent = 20;
        gd.horizontalSpan = 2;
        workspaceTree.setLayoutData(gd);
        
        // Workspace Tree Viewer
        workspaceTreeViewer = new TreeViewer(workspaceTree);
        workspaceTreeViewer.setContentProvider(new BaseWorkbenchContentProvider());
        workspaceTreeViewer.setLabelProvider(new WorkbenchLabelProvider());
        workspaceTreeViewer.setComparator(new ContainerVsResourcesWorkbenchViewerComparator());
        workspaceTreeViewer.setFilters(new ViewerFilter[] {new PtolemyFileFilter()});
        workspaceTreeViewer.setInput(ResourcesPlugin.getWorkspace().getRoot());
        
        // File Dialog
        fileDialog = new FileDialog(parent.getShell(), SWT.OPEN);
        fileDialog.setText(LOC_FILEDIALOG_TITLE);
        
        // Register event handlers
        fileSystemButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent e) {
                updateControlEnablement();
                updatePageCompleteState();
            }
        });
        fileNameCombo.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                updatePageCompleteState();
            }
        });
        fileNameBrowseButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent e) {
                doOpenFileDialog();
            }
        });
        workspaceButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent e) {
                updateControlEnablement();
                updatePageCompleteState();
            }
        });
        workspaceTreeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
            public void selectionChanged(final SelectionChangedEvent event) {
                updatePageCompleteState();
            }
        });
        
        // Restore the lastly used values
        restoreSettings();
        updateControlEnablement();
        setPreselectedResource();
        
        // CHECKSTYLEON MagicNumber
    }
    
    /**
     * Enables or disables controls according to the dialog's current state.
     */
    private void updateControlEnablement() {
        boolean importFromWorkspace = workspaceButton.getSelection();
        
        fileNameCombo.setEnabled(!importFromWorkspace);
        fileNameBrowseButton.setEnabled(!importFromWorkspace);
        
        workspaceTree.setEnabled(importFromWorkspace);
    }
    
    /**
     * Updates the page's current complete state.
     * 
     * The page is considered complete if one of the following conditions holds:
     * - the user chose to import an external file and the given file name refers to
     *   a valid, existing, readable Ptolemy diagram file.
     * - the user chose to import a workspace-internal file and has selected a
     *   Ptolemy diagram file.
     * 
     * Note: For resources, this method does not check if the resource is locally
     * available and accessible. This might cause problems with linked resources
     * or resources only available in repositories. For the moment, I consider that
     * pretty much unimportant, but it's still something to bear in mind.
     */
    private void updatePageCompleteState() {
        boolean importFromWorkspace = workspaceButton.getSelection();
        String errorMessage = null;
        
        if (importFromWorkspace) {
            // Check if the selected resource is a Ptolemy2 diagram file
            boolean fileTypeMatches = false;
            IStructuredSelection treeSelection =
                (IStructuredSelection) workspaceTreeViewer.getSelection();
            
            if (!treeSelection.isEmpty()) {
                Object element = treeSelection.getFirstElement();
                
                if (element instanceof IFile) {
                    IFile file = (IFile) element;
                    String extension = file.getFileExtension();
                    
                    for (String ptolemyExt : PtolemyImporterConstants.PTOLEMY_FILE_EXTENSIONS) {
                        if (ptolemyExt.equalsIgnoreCase(extension)) {
                            fileTypeMatches = true;
                            break;
                        }
                    }
                }
            }
            
            if (!fileTypeMatches) {
                // No valid file has been selected
                errorMessage = LOC_ERR_NO_PTOLEMY_FILE_SELECTED;
            }
        } else {
            // Check if the file name refers to an existing file
            File file = new File(fileNameCombo.getText());
            
            if (!file.exists() || !file.isFile()) {
                errorMessage = LOC_ERR_FILE_DOESNT_EXIST;
            } else {
                if (!file.canRead()) {
                    errorMessage = LOC_ERR_FILE_UNREADABLE;
                } else {
                    // Get the file's file extension
                    boolean fileTypeMatches = false;
                    String fileName = file.getName();
                    int separatorLocation = fileName.lastIndexOf('.');
                    if (separatorLocation >= 0) {
                        String extension = fileName.substring(separatorLocation + 1);
                        
                        for (String ptolemyExt : PtolemyImporterConstants.PTOLEMY_FILE_EXTENSIONS) {
                            if (ptolemyExt.equalsIgnoreCase(extension)) {
                                fileTypeMatches = true;
                                break;
                            }
                        }
                    } else {
                        fileTypeMatches = false;
                    }
                    
                    if (!fileTypeMatches) {
                        errorMessage = LOC_ERR_NO_PTOLEMY_FILE_SELECTED;
                    }
                }
            }
        }
        
        // Apply complete status and set error message
        this.setErrorMessage(errorMessage);
        this.setPageComplete(errorMessage == null);
    }
    
    /**
     * Opens the Open file file dialog.
     */
    private void doOpenFileDialog() {
        // If the user has entered a file name, try to preselect it in the dialog
        String currentFileString = fileNameCombo.getText();
        if (currentFileString != null && !currentFileString.isEmpty()) {
            File file = new File(currentFileString);
            fileDialog.setFilterPath(
                    file.getParent() == null ? file.getAbsolutePath() : file.getParent());
            fileDialog.setFileName(file.getName());
        }
        
        // Open the dialog
        String result = fileDialog.open();
        if (result != null) {
            fileNameCombo.setText(result);
        }
    }

    /**
     * Tries to apply the selection the wizard was called on. This only selects
     * the first element of the selection.
     */
    private void setPreselectedResource() {
        if (selection != null && !selection.isEmpty()) {
            Object resource = selection.getFirstElement();
            
            // Make the resource visible and select it
            workspaceTreeViewer.expandToLevel(resource, 0);
            workspaceTreeViewer.setSelection(new StructuredSelection(resource));
            
            // If the selection is a Ptolemy file, preselect the
            // Import From Workspace button
            if (resource instanceof IFile) {
                String extension = ((IFile) resource).getFileExtension();
                
                for (String ptolemyExt : PtolemyImporterConstants.PTOLEMY_FILE_EXTENSIONS) {
                    if (ptolemyExt.equalsIgnoreCase(extension)) {
                        fileSystemButton.setSelection(false);
                        workspaceButton.setSelection(true);
                        updateControlEnablement();
                        updatePageCompleteState();
                        break;
                    }
                }
            }
        }
    }
    
    /**
     * Restores the settings that were used the last time.
     */
    private void restoreSettings() {
        // The dialog settings of the page are used for persistence
        IDialogSettings settings = this.getDialogSettings();
        if (settings == null) {
            return;
        }
        
        // Whether to import from workspace or from file system
        boolean importFromWorkspace = settings.getBoolean(SETT_FROM_WORKSPACE);
        if (importFromWorkspace) {
            workspaceButton.setSelection(true);
        } else {
            fileSystemButton.setSelection(true);
        }
        
        // Restore the file name history
        String[] historyItems = settings.getArray(SETT_FILE_NAME_HISTORY);
        if (historyItems != null) {
            fileNameCombo.setItems(historyItems);
        }
    }
    
    /**
     * Saves the current settings.
     */
    public void storeSettings() {
        // The dialog settings of the page are used for persistence
        IDialogSettings settings = this.getDialogSettings();
        if (settings == null) {
            return;
        }
        
        // Whether to import from workspace or from file system
        boolean importFromWorkspace = workspaceButton.getSelection();
        settings.put(SETT_FROM_WORKSPACE, importFromWorkspace);
        
        // The file name history
        // If an external file should be imported, the current text must be either
        // moved to the top of the list (if it's already in there) or added to the
        // list (if it isn't).
        if (!importFromWorkspace) {
            String[] items = fileNameCombo.getItems();
            List<String> fileList = new ArrayList<String>(HISTORY_ITEMS + 1);
            
            // Add the current entry
            String fileName = fileNameCombo.getText();
            fileList.add(fileName);
            
            // Iterate through the array, adding each item that doesn't equal the
            // current file name
            for (String item : items) {
                if (!item.equals(fileName)) {
                    fileList.add(item);
                }
            }
            
            // The file list is limited to a certain number of items
            if (fileList.size() > HISTORY_ITEMS) {
                fileList.remove(fileList.size() - 1);
            }
            
            settings.put(SETT_FILE_NAME_HISTORY, fileList.toArray(new String[HISTORY_ITEMS]));
        }
    }
}
