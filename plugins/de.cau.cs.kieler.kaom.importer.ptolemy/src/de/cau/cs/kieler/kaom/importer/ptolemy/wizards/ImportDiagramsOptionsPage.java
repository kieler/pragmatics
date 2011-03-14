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

import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
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
    
    // WIDGETS
    private Composite container;
    private Label sourceLabel;
    private Button sourceFileSystemButton;
    private Button sourceWorkspaceButton;
    private Label optLabel;
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
        GridLayout gl = new GridLayout(3, false);
        
        // Container Composite
        container = new Composite(parent, SWT.NULL);
        container.setLayout(gl);
        this.setControl(container);
        
        // Source Label
        sourceLabel = new Label(container, SWT.NULL);
        sourceLabel.setText(Messages.ImportDiagramsOptionsPage_sourceLabel_text);

        gd = new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false);
        gd.horizontalSpan = 3;
        sourceLabel.setLayoutData(gd);
        
        // Source File System Button
        sourceFileSystemButton = new Button(container, SWT.RADIO);
        sourceFileSystemButton.setText(
                Messages.ImportDiagramsOptionsPage_fileSystemButton_text);
        sourceFileSystemButton.setToolTipText(
                Messages.ImportDiagramsOptionsPage_fileSystemButton_toolTip);
        
        gd = new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false);
        gd.horizontalIndent = 15;
        gd.horizontalSpan = 3;
        sourceFileSystemButton.setLayoutData(gd);
        
        // Source Workspace Button
        sourceWorkspaceButton = new Button(container, SWT.RADIO);
        sourceWorkspaceButton.setText(
                Messages.ImportDiagramsOptionsPage_workspaceButton_text);
        sourceWorkspaceButton.setToolTipText(
                Messages.ImportDiagramsOptionsPage_workspaceButton_toolTip);
        
        gd = new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false);
        gd.horizontalIndent = 15;
        gd.horizontalSpan = 2;
        sourceWorkspaceButton.setLayoutData(gd);
        
        // Vertical Space
        Utils.insertVerticalSpace(container, 10, 3);
        
        // Opt Label
        optLabel = new Label(container, SWT.NULL);
        optLabel.setText(
                Messages.ImportDiagramsOptionsPage_optionsLabel_text);

        gd = new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false);
        gd.horizontalSpan = 3;
        optLabel.setLayoutData(gd);
        
        // Opt Initialize Diagram Files Button
        optInitializeDiagramFilesButton = new Button(container, SWT.CHECK);
        optInitializeDiagramFilesButton.setText(
                Messages.ImportDiagramsOptionsPage_initializeDiagramFilesButton_text);
        optInitializeDiagramFilesButton.setToolTipText(
                Messages.ImportDiagramsOptionsPage_initializeDiagramFilesButton_toolTip);
        
        gd = new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false);
        gd.horizontalIndent = 15;
        gd.horizontalSpan = 3;
        optInitializeDiagramFilesButton.setLayoutData(gd);
        
        // Opt Overwrite Button
        optOverwriteButton = new Button(container, SWT.CHECK);
        optOverwriteButton.setText(
                Messages.ImportDiagramsOptionsPage_overwriteButton_text);
        optOverwriteButton.setToolTipText(
                Messages.ImportDiagramsOptionsPage_overwriteButton_toolTip);
        
        gd = new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false);
        gd.horizontalIndent = 15;
        gd.horizontalSpan = 3;
        optOverwriteButton.setLayoutData(gd);
        
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
        settings.put(SETT_OPT_INIT_KAOD, optInitializeDiagramFilesButton.getSelection());
        settings.put(SETT_OPT_OVERWRITE, optOverwriteButton.getSelection());
    }
}
