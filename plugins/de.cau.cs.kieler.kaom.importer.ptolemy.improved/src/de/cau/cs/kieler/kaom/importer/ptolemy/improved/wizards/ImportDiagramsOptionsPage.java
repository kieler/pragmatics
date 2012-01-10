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

package de.cau.cs.kieler.kaom.importer.ptolemy.improved.wizards;

import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

import de.cau.cs.kieler.kaom.importer.ptolemy.improved.Messages;
import de.cau.cs.kieler.kaom.importer.ptolemy.improved.utils.Utils;


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
     * Key of the advanced annotations handling option.
     */
    private static final String SETT_OPT_ADV_ANNOTATIONS =
        PAGE_NAME + ".options.advancedAnnotations"; //$NON-NLS-1$
    
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
    
    // WIDGETS
    private Composite container;
    private Group sourceGroup;
    private Button sourceFileSystemButton;
    private Button sourceWorkspaceButton;
    private Group optGroup;
    private Button optAdvancedAnnotationsButton;
    private Button optInitializeDiagramFilesButton;
    private Button optOverwriteButton;
    
    
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
     * Checks whether the user wants advanced annotation handling to be turned on or not.
     * 
     * @return {@code true} if advanced annotation handling is to be turned on,
     *         {@code false} otherwise.
     */
    public boolean isAdvancedAnnotationsHandling() {
        return optAdvancedAnnotationsButton.getSelection();
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
        
        // Opt Advanced Annotations Button
        optAdvancedAnnotationsButton = new Button(optGroup, SWT.CHECK);
        optAdvancedAnnotationsButton.setText(
                Messages.ImportDiagramsOptionsPage_advancedAnnotationsButton_text);
        optAdvancedAnnotationsButton.setToolTipText(
                Messages.ImportDiagramsOptionsPage_advancedAnnotationsButton_toolTip);
        
        optAdvancedAnnotationsButton.setLayoutData(
                new GridData(SWT.FILL, SWT.BEGINNING, true, false));
        
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
        
        // Initialize controls and validate
        initializeControls();
        
        // CHECKSTYLEON MagicNumber
    }
    
    /**
     * Initializes the controls from the wizard's dialog settings.
     */
    private void initializeControls() {
        IDialogSettings settings = this.getDialogSettings();
        
        sourceFileSystemButton.setSelection(Utils.getSettingBoolean(
                settings, SETT_SOURCE_FILESYSTEM, true));
        sourceWorkspaceButton.setSelection(!sourceFileSystemButton.getSelection());
        
        optAdvancedAnnotationsButton.setSelection(Utils.getSettingBoolean(
                settings, SETT_OPT_ADV_ANNOTATIONS, true));
        optInitializeDiagramFilesButton.setSelection(Utils.getSettingBoolean(
                settings, SETT_OPT_INIT_KAOD, true));
        optOverwriteButton.setSelection(Utils.getSettingBoolean(
                settings, SETT_OPT_OVERWRITE, false));
    }
    
    /**
     * Saves the settings to be restored next time the wizard opens.
     */
    public void saveDialogSettings() {
        IDialogSettings settings = this.getDialogSettings();
        
        settings.put(SETT_SOURCE_FILESYSTEM, sourceFileSystemButton.getSelection());
        settings.put(SETT_OPT_ADV_ANNOTATIONS, optAdvancedAnnotationsButton.getSelection());
        settings.put(SETT_OPT_INIT_KAOD, optInitializeDiagramFilesButton.getSelection());
        settings.put(SETT_OPT_OVERWRITE, optOverwriteButton.getSelection());
    }
}
