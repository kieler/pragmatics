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
package de.cau.cs.kieler.keg.importer.wizards;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;

/**
 * The wizard page from which to select the KEG file the graph is imported into.
 * 
 * @author mri
 */
public class ImportGraphNewFilePage extends WizardNewFileCreationPage {

    /** the page title. */
    private static final String TITLE = "KEG File";
    /** the message that is displayed while the input is correct. */
    private static final String MESSAGE_OK =
            "Create a new KEG file to import into.";

    /** the extension for the file to create. */
    private static final String NEW_FILE_EXTENSION = "keg";

    /** the import page. */
    private ImportGraphWizardPage importPage;

    /**
     * Constructs the new file wizard page.
     * 
     * @param selection
     *            the selection the wizard is called on
     * @param theImportPage
     *            the import page
     */
    public ImportGraphNewFilePage(final IStructuredSelection selection,
            final ImportGraphWizardPage theImportPage) {
        super("importGraphNewFileWizardPage", selection);
        importPage = theImportPage;
        setTitle(TITLE);
        setDescription(MESSAGE_OK);
        setFileExtension(NEW_FILE_EXTENSION);
        setAllowExistingResources(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setVisible(final boolean visible) {
        if (visible) {
            IPath path = new Path(importPage.getImportFile());
            setFileName(path.removeFileExtension()
                    .addFileExtension(NEW_FILE_EXTENSION).lastSegment());
        }
        super.setVisible(visible);
    }
}
