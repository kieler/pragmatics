/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kaom.importer.ptolemy.wizards;

import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

import de.cau.cs.kieler.kaom.importer.ptolemy.Messages;
import de.cau.cs.kieler.kaom.importer.ptolemy.utils.Utils;


/**
 * First page of the Import Diagrams Wizard.
 * 
 * @author cds
 * @kieler.rating yellow 2010-03-14
 *      reviewed by haf, msp, pkl
 */
public class ImportDiagramsOptionsPage extends WizardPage {
    
    // CONSTANTS
    /**
     * The wizard page name.
     */
    private static final String PAGE_NAME = "importDiagramsOptionsPage"; //$NON-NLS-1$
    
    /**
     * Key of the import source setting.
     */
    private static final String SETT_SOURCE_FILESYSTEM =
        PAGE_NAME + ".source.filesystem"; //$NON-NLS-1$
    
    /**
     * Key of the initialize KAOD files setting.
     */
    private static final String SETT_OPT_INIT_KAOD =
        PAGE_NAME + ".options.initKaodFiles"; //$NON-NLS-1$
    
    /**
     * Key of the overwrite setting.
     */
    private static final String SETT_OPT_OVERWRITE =
        PAGE_NAME + ".options.overwrite"; //$NON-NLS-1$
    
    /**
     * Key of the advanced annotations handling option.
     */
    private static final String SETT_ANN_ADV_ANNOTATIONS =
        PAGE_NAME + ".options.advancedAnnotations"; //$NON-NLS-1$
    
    /**
     * Key of the annotations attachment heuristics override.
     */
    private static final String SETT_ANN_HEURISTICS_OVERRIDE =
        PAGE_NAME + ".options.annotationHeuristicsOverride"; //$NON-NLS-1$
    
    
    // WIDGETS
    private Composite container;
    private Group sourceGroup;
    private Button sourceFileSystemButton;
    private Button sourceWorkspaceButton;
    private Group optGroup;
    private Button optInitializeDiagramFilesButton;
    private Button optOverwriteButton;
    private Group annGroup;
    private Button annAdvancedAnnotationsButton;
    private Label annHeuristicsOverrideLabel;
    private Button annHeuristicsOverrideOffButton;
    private Button annHeuristicsOverrideOnButton;
    
    
    /**
     * Constructs a new instance.
     */
    public ImportDiagramsOptionsPage() {
        super(PAGE_NAME);
        
        this.setTitle(Messages.ImportDiagramsOptionsPage_title);
        this.setMessage(Messages.ImportDiagramsOptionsPage_message);
        
        // This page is always complete
        this.setPageComplete(true);
    }
    
    
    /**
     * Checks whether the user wants to import diagrams from the file system or from
     * the workspace.
     * 
     * @return {@code true} if the user wants to import diagrams from the file system.
     */
    public boolean isFileSystemSource() {
        return sourceFileSystemButton.getSelection();
    }
    
    /**
     * Checks whether the user wants to have KAOD files created after the import.
     * 
     * @return {@code true} if KAOD files should be created.
     */
    public boolean isInitializeDiagramFiles() {
        return optInitializeDiagramFilesButton.getSelection();
    }
    
    /**
     * Checks whether the user wants existing files to be overwritten without
     * warning.
     * 
     * @return {@code true} if existing files should be overwritten without warning.
     */
    public boolean isOverwriteWithoutWarning() {
        return optOverwriteButton.getSelection();
    }
    
    /**
     * Checks whether the user wants advanced annotation handling to be turned on or not.
     * 
     * @return {@code true} if advanced annotation handling is to be turned on,
     *         {@code false} otherwise.
     */
    public boolean isAdvancedAnnotationsHandling() {
        return annAdvancedAnnotationsButton.getSelection();
    }
    
    /**
     * Checks whether the user wants the annotation attachmant heuristic disabled if explicit
     * attachments are found.
     * 
     * @return {@code true} if the heuristics are turned off once explicit attachments are found,
     *         {@code false} otherwise.
     */
    public boolean isHeuristicsOverrideEnabled() {
        return annHeuristicsOverrideOnButton.getSelection();
    }
    
    
    /**
     * {@inheritDoc}
     */
    @Override
    public IWizardPage getNextPage() {
        ImportDiagramsWizard wizard = (ImportDiagramsWizard) getWizard();
        
        if (isFileSystemSource()) {
            return wizard.getFileSystemSourcesPage();
        } else {
            return wizard.getWorkspaceSourcesPage();
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
        GridLayout gl = new GridLayout(1, false);
        
        
        // Container Composite
        container = new Composite(parent, SWT.NULL);
        container.setLayout(gl);
        this.setControl(container);
        
        // Source Group
        sourceGroup = new Group(container, SWT.NULL);
        sourceGroup.setText(Messages.ImportDiagramsOptionsPage_sourceGroup_text);
        
        gl = new GridLayout(1, false);
        gl.marginHeight = 10;
        gl.marginWidth = 10;
        sourceGroup.setLayout(gl);
        
        sourceGroup.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
        
        // Source File System Button
        sourceFileSystemButton = new Button(sourceGroup, SWT.RADIO);
        sourceFileSystemButton.setText(
                Messages.ImportDiagramsOptionsPage_fileSystemButton_text);
        sourceFileSystemButton.setToolTipText(
                Messages.ImportDiagramsOptionsPage_fileSystemButton_toolTip);
        
        sourceFileSystemButton.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
        
        // Source Workspace Button
        sourceWorkspaceButton = new Button(sourceGroup, SWT.RADIO);
        sourceWorkspaceButton.setText(
                Messages.ImportDiagramsOptionsPage_workspaceButton_text);
        sourceWorkspaceButton.setToolTipText(
                Messages.ImportDiagramsOptionsPage_workspaceButton_toolTip);
        
        sourceWorkspaceButton.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
        
        // Opt Group
        optGroup = new Group(container, SWT.NULL);
        optGroup.setText(Messages.ImportDiagramsOptionsPage_optionsGroup_text);
        
        gl = new GridLayout(1, false);
        gl.marginHeight = 10;
        gl.marginWidth = 10;
        optGroup.setLayout(gl);

        gd = new GridData(SWT.FILL, SWT.BEGINNING, true, false);
        gd.verticalIndent = 10;
        optGroup.setLayoutData(gd);
        
        // Opt Initialize Diagram Files Button
        optInitializeDiagramFilesButton = new Button(optGroup, SWT.CHECK);
        optInitializeDiagramFilesButton.setText(
                Messages.ImportDiagramsOptionsPage_initializeDiagramFilesButton_text);
        optInitializeDiagramFilesButton.setToolTipText(
                Messages.ImportDiagramsOptionsPage_initializeDiagramFilesButton_toolTip);
        
        optInitializeDiagramFilesButton.setLayoutData(
                new GridData(SWT.FILL, SWT.BEGINNING, true, false));
        
        // Opt Overwrite Button
        optOverwriteButton = new Button(optGroup, SWT.CHECK);
        optOverwriteButton.setText(
                Messages.ImportDiagramsOptionsPage_overwriteButton_text);
        optOverwriteButton.setToolTipText(
                Messages.ImportDiagramsOptionsPage_overwriteButton_toolTip);
        
        optOverwriteButton.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
        
        // Annotations Group
        annGroup = new Group(container, SWT.NULL);
        annGroup.setText(Messages.ImportDiagramsOptionsPage_annotationsGroup_text);
        
        gl = new GridLayout(1, false);
        gl.marginHeight = 10;
        gl.marginWidth = 10;
        annGroup.setLayout(gl);

        gd = new GridData(SWT.FILL, SWT.BEGINNING, true, false);
        gd.verticalIndent = 10;
        annGroup.setLayoutData(gd);
        
        // Ann Advanced Annotations Button
        annAdvancedAnnotationsButton = new Button(annGroup, SWT.CHECK);
        annAdvancedAnnotationsButton.setText(
                Messages.ImportDiagramsOptionsPage_advancedAnnotationsButton_text);
        annAdvancedAnnotationsButton.setToolTipText(
                Messages.ImportDiagramsOptionsPage_advancedAnnotationsButton_toolTip);
        
        annAdvancedAnnotationsButton.setLayoutData(
                new GridData(SWT.FILL, SWT.BEGINNING, true, false));
        
        // Explanatory Label
        annHeuristicsOverrideLabel = new Label(annGroup, SWT.NULL);
        annHeuristicsOverrideLabel.setText(
                Messages.ImportDiagramsOptionsPage_annotationsHeuristicsOverrideLabel_text);
        
        gd = new GridData(SWT.FILL, SWT.BEGINNING, true, false);
        gd.verticalIndent = 10;
        annHeuristicsOverrideLabel.setLayoutData(gd);
        
        // Heuristics Override Off Button
        annHeuristicsOverrideOffButton = new Button(annGroup, SWT.RADIO);
        annHeuristicsOverrideOffButton.setText(
                Messages.ImportDiagramsOptionsPage_annotationsHeuristicsOverrideOffButton_text);
        annHeuristicsOverrideOffButton.setToolTipText(
                Messages.ImportDiagramsOptionsPage_annotationsHeuristicsOverrideOffButton_toolTip);

        gd = new GridData(SWT.FILL, SWT.BEGINNING, true, false);
        gd.horizontalIndent = 10;
        annHeuristicsOverrideOffButton.setLayoutData(gd);
        
        // Heuristics Override On Button
        annHeuristicsOverrideOnButton = new Button(annGroup, SWT.RADIO);
        annHeuristicsOverrideOnButton.setText(
                Messages.ImportDiagramsOptionsPage_annotationsHeuristicsOverrideOnButton_text);
        annHeuristicsOverrideOnButton.setToolTipText(
                Messages.ImportDiagramsOptionsPage_annotationsHeuristicsOverrideOnButton_toolTip);

        gd = new GridData(SWT.FILL, SWT.BEGINNING, true, false);
        gd.horizontalIndent = 10;
        annHeuristicsOverrideOnButton.setLayoutData(gd);
        
        // Initialize controls and update enablements
        initializeControls();
        updateControlEnablement();
        
        // Register event listeners
        annAdvancedAnnotationsButton.addSelectionListener(new SelectionAdapter() {
            /**
             * {@inheritDoc}
             */
            @Override
            public void widgetSelected(final SelectionEvent e) {
                updateControlEnablement();
            }
        });
        
        // CHECKSTYLEON MagicNumber
    }
    
    /**
     * Enables or disables controls depending on the selection of other controls.
     */
    private void updateControlEnablement() {
        boolean heuristicsEnabled = annAdvancedAnnotationsButton.getSelection();
        
        annHeuristicsOverrideLabel.setEnabled(heuristicsEnabled);
        annHeuristicsOverrideOffButton.setEnabled(heuristicsEnabled);
        annHeuristicsOverrideOnButton.setEnabled(heuristicsEnabled);
    }
    
    /**
     * Initializes the controls from the wizard's dialog settings.
     */
    private void initializeControls() {
        IDialogSettings settings = this.getDialogSettings();
        
        sourceFileSystemButton.setSelection(Utils.getSettingBoolean(
                settings, SETT_SOURCE_FILESYSTEM, true));
        sourceWorkspaceButton.setSelection(!sourceFileSystemButton.getSelection());
        
        optInitializeDiagramFilesButton.setSelection(Utils.getSettingBoolean(
                settings, SETT_OPT_INIT_KAOD, true));
        optOverwriteButton.setSelection(Utils.getSettingBoolean(
                settings, SETT_OPT_OVERWRITE, false));
        
        annAdvancedAnnotationsButton.setSelection(Utils.getSettingBoolean(
                settings, SETT_ANN_ADV_ANNOTATIONS, true));
        annHeuristicsOverrideOnButton.setSelection(Utils.getSettingBoolean(
                settings, SETT_ANN_HEURISTICS_OVERRIDE, false));
        annHeuristicsOverrideOffButton.setSelection(!annHeuristicsOverrideOnButton.getSelection());
    }
    
    /**
     * Saves the settings to be restored next time the wizard opens.
     */
    public void saveDialogSettings() {
        IDialogSettings settings = this.getDialogSettings();
        
        settings.put(SETT_SOURCE_FILESYSTEM, sourceFileSystemButton.getSelection());
        settings.put(SETT_OPT_INIT_KAOD, optInitializeDiagramFilesButton.getSelection());
        settings.put(SETT_OPT_OVERWRITE, optOverwriteButton.getSelection());
        settings.put(SETT_ANN_ADV_ANNOTATIONS, annAdvancedAnnotationsButton.getSelection());
        settings.put(SETT_ANN_HEURISTICS_OVERRIDE, annHeuristicsOverrideOnButton.getSelection());
    }
}
