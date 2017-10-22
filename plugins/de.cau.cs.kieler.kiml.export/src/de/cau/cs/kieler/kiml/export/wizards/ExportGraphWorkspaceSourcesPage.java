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
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

import de.cau.cs.kieler.formats.GraphFormatData;
import de.cau.cs.kieler.formats.GraphFormatsService;

/**
 * The Export graph wizard page which lets the user select the source files from the workspace and
 * the target folder.
 * 
 * @author wah
 * @kieler.ignore (excluded from review process)
 */
public class ExportGraphWorkspaceSourcesPage extends WorkspaceResourcesPage {

    // CONSTANTS
    /**
     * The wizard page name.
     */
    private static final String PAGE_NAME = "exportDiagramsWorkspaceSourcesPage"; //$NON-NLS-1$

    private static final String PREFERENCE_EXPORTER = ".exporter"; //$NON-NLS-1$
    private static final String PREFERENCE_SEPARATE_HIERARCHY_LEVELS =
            ".separate_hierarchy_levels"; //$NON-NLS-1$
    private static final String PREFERENCE_FILTER_EDGELESS_LEVELS =
            ".filterEdgelessLevels"; //$NON-NLS-1$
    private static final String PREFERENCE_FILTER_SELF_LOOPS =
            ".filterSelfLoops"; //$NON-NLS-1$

    // UI widgets
    private Combo fileFormatCombo;
    private Button separateHierarchyLevelsCheckbox;
    private Button filterEdgelessLevelsCheckbox;
    private Button filterSelfLoopsCheckbox;
    
    /** available graph format data. */
    private GraphFormatData[] graphFormatData;

    /**
     * Constructs a new instance.
     * 
     * @param selection
     *            the selection the wizard was called on.
     */
    public ExportGraphWorkspaceSourcesPage(final IStructuredSelection selection) {
        super(PAGE_NAME, true, null, selection, true, true);
        this.setTitle(Messages.ExportGraphWizard_title);
        this.setDescription(Messages.ExportGraphWizard_Exporting_workspace_task);
        this.setMessage(Messages.ExportGraphWizard_Exporting_workspace_task);
    }
    
    // UI Constants
    private static final int DEFAULT_TARGET_GROUP_COLUMNS = 3;
    private static final int DEFAULT_TARGET_GROUP_MARGIN_TOP = 20;
    private static final int DEFAULT_HORIZONTAL_INDENT = 20;

    /**
     * {@inheritDoc}
     */
    @Override
    protected Composite createOptionsGroup(final Composite parent) {
        GridData layoutData;
        
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

        // fill the Combo with all possible export formats
        Collection<GraphFormatData> formatDataCollection = GraphFormatsService.getInstance()
                .getFormatData();
        graphFormatData = new GraphFormatData[formatDataCollection.size()];
        String[] formatNames = new String[formatDataCollection.size()];
        int i = 0;
        for (GraphFormatData gfd : formatDataCollection) {
            graphFormatData[i] = gfd;
            formatNames[i] = gfd.getName();
            i++;
        }
        if (formatNames.length > 0) {
            fileFormatCombo.setItems(formatNames);
            fileFormatCombo.select(0);
        } else {
            fileFormatCombo.setEnabled(false);
        }
        
        // Options group
        Group optionsGroup = new Group(parent, SWT.NULL);
        optionsGroup.setText(Messages.exportDialog_optionsGroup_caption);
        optionsGroup.setLayout(new GridLayout());
        
        layoutData = new GridData(SWT.FILL, SWT.CENTER, true, false);
        layoutData.horizontalSpan = 2;
        optionsGroup.setLayoutData(layoutData);
        
        // Hierarchy export
        separateHierarchyLevelsCheckbox = new Button(optionsGroup, SWT.CHECK);
        separateHierarchyLevelsCheckbox.setText(
                Messages.ExportDialog_separateHierarchyLevels_caption);
        separateHierarchyLevelsCheckbox.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                updateEnablement();
            }

            @Override
            public void widgetDefaultSelected(final SelectionEvent e) {
                updateEnablement();
            }
            
        });
        
        // Filtering options
        filterEdgelessLevelsCheckbox = new Button(optionsGroup, SWT.CHECK);
        filterEdgelessLevelsCheckbox.setText(Messages.exportDialog_filterEdgelessGraphs_caption);
        
        layoutData = new GridData(SWT.FILL, SWT.CENTER, true, false);
        layoutData.horizontalIndent = DEFAULT_HORIZONTAL_INDENT;
        filterEdgelessLevelsCheckbox.setLayoutData(layoutData);
        
        filterSelfLoopsCheckbox = new Button(optionsGroup, SWT.CHECK);
        filterSelfLoopsCheckbox.setText(Messages.exportDialog_filterSelfLoops_caption);
        
        layoutData = new GridData(SWT.FILL, SWT.CENTER, true, false);
        layoutData.horizontalIndent = DEFAULT_HORIZONTAL_INDENT;
        filterSelfLoopsCheckbox.setLayoutData(layoutData);

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
    public List<IFile> getSourceFiles(final IProgressMonitor monitor) {
        List<IResource> selectedResources = this.getResources(monitor);
        List<IFile> files = new ArrayList<>();

        for (IResource resource : selectedResources) {
            if (resource instanceof IFile) {
                files.add((IFile) resource);
            }
        }

        return files;
    }

    /**
     * Returns the target directory selected from the user.
     * 
     * @return target directory
     */
    public IPath getTargetWorkspaceDirectory() {
        return getTargetContainerPath();
    }
    
    /**
     * @return whether or not each level of hierarchy in the processed graphs should be exported in
     * a separate file.
     */
    public boolean getSeparateHierarchyLevels() {
        return separateHierarchyLevelsCheckbox.getSelection();
    }
    
    /**
     * @return whether or not graphs without edges should be included in the export.
     */
    public boolean getFilterEdgelessLevels() {
        return filterEdgelessLevelsCheckbox.getSelection();
    }
    
    /**
     * @return whether or not self loops should be removed from graphs.
     */
    public boolean getFilterSelfLoops() {
        return filterSelfLoopsCheckbox.getSelection();
    }
    
    /**
     * Enables or disables widgets based on the selection in others.
     */
    private void updateEnablement() {
        filterEdgelessLevelsCheckbox.setEnabled(separateHierarchyLevelsCheckbox.getSelection());
        filterSelfLoopsCheckbox.setEnabled(separateHierarchyLevelsCheckbox.getSelection());
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
        if (getTargetFormat() == null) {
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
        String parentnode = (!targetPath.isEmpty() ? targetPath.segment(0) : ""); //$NON-NLS-1$
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
     * Return the selected target format.
     * 
     * @return the selected target format
     */
    public GraphFormatData getTargetFormat() {
        int index = fileFormatCombo.getSelectionIndex();
        if (index >= graphFormatData.length) {
            return null;
        }
        return graphFormatData[index];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveDialogSettings() {
        super.saveDialogSettings();

        // save the last export format in dialogSettings
        IDialogSettings dialogSettings = getDialogSettings();
        if (dialogSettings == null) {
            // The dialog settings have not been set on the wizard
            return;
        }
        dialogSettings.put(getName() + PREFERENCE_EXPORTER, getTargetFormat().getId());
        dialogSettings.put(getName() + PREFERENCE_SEPARATE_HIERARCHY_LEVELS,
                separateHierarchyLevelsCheckbox.getSelection());
        dialogSettings.put(getName() + PREFERENCE_FILTER_EDGELESS_LEVELS,
                filterEdgelessLevelsCheckbox.getSelection());
        dialogSettings.put(getName() + PREFERENCE_FILTER_SELF_LOOPS,
                        filterSelfLoopsCheckbox.getSelection());
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
        // set the target format combo selection to the restored format
        String targetFormat = dialogSettings.get(getName() + PREFERENCE_EXPORTER);
        for (int i = 0; i < graphFormatData.length; i++) {
            if (graphFormatData[i].getId().equals(targetFormat)) {
                fileFormatCombo.select(i);
            }
        }
        
        separateHierarchyLevelsCheckbox.setSelection(dialogSettings.getBoolean(
                getName() + PREFERENCE_SEPARATE_HIERARCHY_LEVELS));
        filterEdgelessLevelsCheckbox.setSelection(dialogSettings.getBoolean(
                getName() + PREFERENCE_FILTER_EDGELESS_LEVELS));
        filterSelfLoopsCheckbox.setSelection(dialogSettings.getBoolean(
                getName() + PREFERENCE_FILTER_SELF_LOOPS));
        
        updateEnablement();
    }
    
}
