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

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;

import de.cau.cs.kieler.kaom.importer.ptolemy.PtolemyImporterConstants;


/**
 * Target file page in the {@link ImportDiagramWizard}. Allows the user to specify where
 * to save the imported diagram.
 * 
 * @author cds
 */
public class ImportDiagramTargetFilePage extends WizardNewFileCreationPage {
    
    // LOCALIZATION
    private static final String LOC_PAGE_TITLE = "Target File";
    private static final String LOC_PAGE_DESCRIPTION =
        "Choose where to save the imported file.";
    
    
    /**
     * Constructs a new file wizard page.
     * 
     * @param selection the selection the wizard was called on.
     */
    public ImportDiagramTargetFilePage(final IStructuredSelection selection) {
        super("importDiagramWizard.importDiagramNewFilePage", selection);
        this.setTitle(LOC_PAGE_TITLE);
        this.setDescription(LOC_PAGE_DESCRIPTION);
        this.setFileExtension(PtolemyImporterConstants.TARGET_FILE_EXTENSION);
    }

}
