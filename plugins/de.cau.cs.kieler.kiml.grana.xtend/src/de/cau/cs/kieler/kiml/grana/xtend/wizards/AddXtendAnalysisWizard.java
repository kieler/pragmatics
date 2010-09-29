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
package de.cau.cs.kieler.kiml.grana.xtend.wizards;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.wizard.Wizard;

import de.cau.cs.kieler.kiml.grana.xtend.XtendAnalysesManager;
import de.cau.cs.kieler.kiml.grana.xtend.XtendAnalysis;

/**
 * The wizard for adding xtend analyses.
 * 
 * @author mri
 */
public class AddXtendAnalysisWizard extends Wizard {

    /** the general page. */
    private AddXtendAnalysisWizardGeneralPage generalPage;
    /** the extension page. */
    private AddXtendAnalysisWizardExtensionPage extensionPage;
    /** the dependencies page. */
    private AddXtendAnalysisWizardDependenciesPage dependenciesPage;

    /** the source file (null by default). */
    private IFile file = null;

    /**
     * The default constructor.
     */
    public AddXtendAnalysisWizard() {
        super();
        setWindowTitle("Add Xtend Analysis");
    }

    /**
     * The constructor initialized with a source file.
     * 
     * @param theFile
     *            the source xtend file
     */
    public AddXtendAnalysisWizard(final IFile theFile) {
        super();
        file = theFile;
        setWindowTitle("Add Xtend Analysis");
    }

    /**
     * {@inheritDoc}
     */
    public void addPages() {
        generalPage = new AddXtendAnalysisWizardGeneralPage(file);
        extensionPage = new AddXtendAnalysisWizardExtensionPage(generalPage);
        dependenciesPage = new AddXtendAnalysisWizardDependenciesPage();
        addPage(generalPage);
        addPage(extensionPage);
        addPage(dependenciesPage);
    }

    /**
     * {@inheritDoc}
     */
    public boolean performFinish() {
        String name = generalPage.getAnalysisName();
        String fileName = generalPage.getFileName();
        String extension = extensionPage.getExtension();
        List<String> dependencies = dependenciesPage.getDependencies();
        XtendAnalysis analysis =
                new XtendAnalysis(name, fileName, extension, dependencies);
        XtendAnalysesManager.getInstance().addXtendAnalysis(analysis);
        return true;
    }

}
