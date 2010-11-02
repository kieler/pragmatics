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
package de.cau.cs.kieler.graphs.wizards;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;

/**
 * The wizard page from which to select the graphs file the graph is imported
 * into.
 * 
 * @author mri
 */
public class ImportGraphNewFilePage extends WizardNewFileCreationPage {
    
    /** the message that is displayed while the input is correct. */
    private static final String MESSAGE_OK =
        "Create a new Graphs file to import into.";
    
    /** the extension for the file to create. */
    private static final String NEW_FILE_EXTENSION = "graph";
    
    /**
     * Constructs the new file wizard page.
     * 
     * @param selection
     *            the selection the wizard is called on
     */
    public ImportGraphNewFilePage(IStructuredSelection selection) {
        super("importGraphNewFileWizardPage", selection);
        setTitle("Graphs File");
        setDescription(MESSAGE_OK);
        setFileExtension(NEW_FILE_EXTENSION);
    }
}
