/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kiml.export.wizards;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import de.cau.cs.kieler.core.ui.wizards.WorkspaceResourcesPage;
import de.cau.cs.kieler.kiml.service.formats.GraphFormatData;
import de.cau.cs.kieler.kiml.ui.service.EclipseTransformationService;

/**
 * The Export graph wizard page which lets the user select the source files from the workspace and
 * the target folder.
 * 
 * @author wah
 */
public class ExportGraphWorkspaceSourcesPage extends WorkspaceResourcesPage {

    // CONSTANTS
    /**
     * The wizard page name.
     */
    private static final String PAGE_NAME = "exportDiagramsWorkspaceSourcesPage"; //$NON-NLS-1$

    private Combo fileFormatCombo;

    /** the preference key for the selected exporter. */
    private static final String PREFERENCE_EXPORTER = "exportDialog.exporter"; //$NON-NLS-1$

    /**
     * The number of columns used to lay out the default target groups.
     */
    private static final int DEFAULT_TARGET_GROUP_COLUMNS = 3;
    /**
     * The number of columns used to lay out the default target groups.
     */
    private static final int DEFAULT_TARGET_GROUP_MARGIN_TOP = 20;
    /**
     * The graph files extension able to be converted.
     */
    private static final String[] GRAPH_FILE_EXTENSIONS = { "kegdi", "kaod", "kids" };

    /**
     * Constructs a new instance.
     * 
     * @param selection
     *            the selection the wizard was called on.
     */
    public ExportGraphWorkspaceSourcesPage(final IStructuredSelection selection) {
        super(PAGE_NAME, true, GRAPH_FILE_EXTENSIONS, selection);
        this.setTitle(Messages.ExportGraphWizard_title);
        this.setDescription(Messages.ExportGraphWizard_Exporting_workspace_task);
        this.setMessage(Messages.ExportGraphWizard_Exporting_workspace_task);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Composite createOptionsGroup(final Composite parent) {
        // Composite
        Composite targetGroup = new Composite(parent, SWT.NULL);
        GridLayout gl = new GridLayout(DEFAULT_TARGET_GROUP_COLUMNS, false);
        gl.marginHeight = DEFAULT_TARGET_GROUP_MARGIN_TOP;
        targetGroup.setLayout(gl);
        // Label
        Label label = new Label(targetGroup, SWT.NULL);
        label.setText(Messages.ExportDialog_file_format_caption);
        label.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false));
        // Combo
        fileFormatCombo = new Combo(targetGroup, SWT.DROP_DOWN | SWT.READ_ONLY);
        fileFormatCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        //fill the Combo with all possible export formats
        String[] formatNames = getGraphFileExtensions();
        if (formatNames.length > 0) {
            fileFormatCombo.setItems(formatNames);
            fileFormatCombo.select(0);
        } else {
            fileFormatCombo.setEnabled(false);
        }

        return targetGroup;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getBrowseMessage() {
        return Messages.WorkspaceResourcesPage_containerSelectionDialog_message;
    }

    /**
     * Returns the selected source files to export. This method may take a while to complete and
     * shows its progress using a progress monitor.
     * 
     * @param monitor
     *            progress monitor.
     * @return list of selected source files.
     */
    public List<IPath> getSourceFiles(final IProgressMonitor monitor) {
        List<IResource> selectedResources = this.getResources(monitor);
        List<IPath> files = new ArrayList<IPath>();

        for (IResource resource : selectedResources) {
            if (resource instanceof IFile) {
                IFile iFile = (IFile) resource;
                // files.add(iFile.getLocation().toFile());
                files.add(iFile.getFullPath());
            }
        }

        return files;
    }

    /**
     * returns the Graph files Possible extensions.
     * 
     * @return array of Graph Extensions
     */

    private static String[] getGraphFileExtensions() {
        Collection<GraphFormatData> formatData = EclipseTransformationService.getInstance()
                .getFormatData();
        String[] formatNames = new String[formatData.size()];
        if (formatNames.length > 0) {
            int i = 0;
            for (GraphFormatData gfd : formatData) {
                formatNames[i++] = gfd.getName();
            }
            return formatNames;
        }
        return null;
    }

    /**
     * Returns the target directory selected from the user.
     * 
     * @return target directory
     */
    public IPath getTargetWorksapceDirectory() {

        return getTargetContainerPath();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean doValidate() {
        if (!super.doValidate()) {
            return false;
        }
        // check if selected extension
        if (!(getTargetFormat().length() > 0)) {
            return false;
        }
        // return false if no files in grayedTreeItems are selected
        if (!(getSourceFiles(null).size() > 0)) {
            return false;
        }
        // return false if no valid target project
        if (!isValidTargetProject()) {
            super.setErrorMessage(Messages.WorkspaceResourcesPage_errors_NoValidDestinationSelected);
            return false;
        }
        return true;
    }

    /**
     * function to check if target directory is in an existing project in the current workspace.
     * 
     * @return true if valid target project and false otherwise
     */
    private boolean isValidTargetProject() {
        IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
        // get the selected target path
        IPath targetPath = getTargetContainerPath();
        // get the project name
        String parentnode = (!targetPath.isEmpty() ? targetPath.segment(0) : "");
        // compare all projects with the selected project name and return true when
        // the project already exists
        if (workspaceRoot.getProjects().length > 0) {
            for (IProject project : workspaceRoot.getProjects()) {
                if (parentnode.equals(project.getName())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 
     * @return the selected target format
     */
    public String getTargetFormat() {
        return fileFormatCombo.getItem(fileFormatCombo.getSelectionIndex()).toLowerCase();
    }

    /**
     * actions to do when closing the window.
     * 
     * 
     */
    public void close() {
        // save settings to preference store
        // preferenceStore.setValue(PREFERENCE_EXPORTER, getTargetFormat());
    }

    @Override
    public void saveDialogSettings() {
        super.saveDialogSettings();

        // save the last export format in dialogSettings
        IDialogSettings dialogSettings = getDialogSettings();
        if (dialogSettings == null) {
            // The dialog settings have not been set on the wizard
            return;
        }
        dialogSettings.put(getName() + PREFERENCE_EXPORTER, getTargetFormat());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void restoreDialogSettings() {
        super.restoreDialogSettings();
        
        // get the last export format from dialogSettings
        IDialogSettings dialogSettings = getDialogSettings();
        if (dialogSettings == null) {
            // The dialog settings have not been set on the wizard
            return;
        }
        //set the target format combo selection to the restored format 
        String targetFormatName = dialogSettings.get(getName() + PREFERENCE_EXPORTER);
        if (fileFormatCombo.getItemCount() > 0) {
            for (int i = 0; i < fileFormatCombo.getItemCount(); i++) {
                if (fileFormatCombo.getItem(i).toLowerCase().equals(targetFormatName)) {
                    fileFormatCombo.select(i);
                }
            }
        }
    }
}
