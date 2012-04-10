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
package de.cau.cs.kieler.kiml.export.wizards;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;

/**
 * A wizard for Exporting graphs from workspace.
 * 
 * @author wah
 */
public class ExportGraphWizard extends Wizard implements IExportWizard {

    /** the page from which to select the workspace source files and the target folder. */
    private ExportGraphWorkspaceSourcesPage workspaceSourcesPage;
    /** the selection the import wizard was called on. */
    private IStructuredSelection selection;

    /**
     * Constructs a graph Export wizard.
     */
    public ExportGraphWizard() {
        super();

    }

    /**
     * {@inheritDoc}
     */
    public void addPages() {
        workspaceSourcesPage = new ExportGraphWorkspaceSourcesPage(selection);
        addPage(workspaceSourcesPage);
    }

    /**
     * {@inheritDoc}
     */
    public IWizardPage getStartingPage() {
        // determine the starting page
        return workspaceSourcesPage;
    }

    /**
     * {@inheritDoc}
     */
    public void init(IWorkbench workbench, IStructuredSelection selection) {
        setWindowTitle(Messages.ExportGraphWizard_title);
        // TODO KEGImporterPlugin
        // setDialogSettings(KEGImporterPlugin.getDefault().getDialogSettings());
        this.selection = selection;
        setNeedsProgressMonitor(true);

    }

    public boolean performFinish() {
        return true;
    }

}
