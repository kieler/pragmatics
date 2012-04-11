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

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import de.cau.cs.kieler.core.ui.wizards.WorkspaceResourcesPage;
import de.cau.cs.kieler.kiml.export.ExportPlugin;
import de.cau.cs.kieler.kiml.service.TransformationService;
import de.cau.cs.kieler.kiml.service.formats.GraphFormatData;

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

    /** the preference store. */
    private IPreferenceStore preferenceStore = null;

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
     * Constructs a new instance.
     * 
     * @param selection
     *            the selection the wizard was called on.
     */
    public ExportGraphWorkspaceSourcesPage(final IStructuredSelection selection) {
        super(PAGE_NAME, true, getGraphFileExtensions(), selection);
        this.setTitle(Messages.ExportGraphWizard_title);
        this.setDescription(Messages.ExportGraphWizard_Exporting_workspace_task);
        this.setMessage(Messages.ExportGraphWizard_Exporting_workspace_task);
        preferenceStore = ExportPlugin.getDefault().getPreferenceStore();
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
        String[] formatNames = getGraphFileExtensions();
        if (formatNames.length > 0) {
            fileFormatCombo.setItems(formatNames);
            // get last exporter from preference store
            String lastFormatName = preferenceStore.getString(PREFERENCE_EXPORTER);
            if (lastFormatName.length() > 0) {
                fileFormatCombo.setText(lastFormatName);
            } else {
                fileFormatCombo.setText(formatNames[0]);
            }
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
    public List<File> getSourceFiles(final IProgressMonitor monitor) {
        List<IResource> selectedResources = this.getResources(monitor);
        List<File> files = new ArrayList<File>();

        for (IResource resource : selectedResources) {
            if (resource instanceof IFile) {
                IFile iFile = (IFile) resource;
                files.add(iFile.getLocation().toFile());
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
        Collection<GraphFormatData> formatData = TransformationService.getInstance()
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
     *  actions to do when closing the window.
     * 
     * 
     */
    public void close() {
        // save settings to preference store
        String formatName = fileFormatCombo.getItem(fileFormatCombo.getSelectionIndex());
        preferenceStore.setValue(PREFERENCE_EXPORTER, formatName);
    }
    
}
