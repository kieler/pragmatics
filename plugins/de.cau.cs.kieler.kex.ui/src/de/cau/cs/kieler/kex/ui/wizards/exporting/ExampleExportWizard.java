/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kex.ui.wizards.exporting;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;

import de.cau.cs.kieler.kex.controller.ExampleElement;
import de.cau.cs.kieler.kex.controller.ExampleManager;

/**
 * This is a wizard for the example export of kex. It contains of three wizardpages.
 * 
 * @author pkl
 * 
 */
public class ExampleExportWizard extends Wizard implements IExportWizard {

    private ExampleAttributesPage examplePage;
    private ExampleResourcesPage resourcePage;
    private ExampleExportPage exportPage;

    /**
     * Constructor for {@link ExampleExportWizard}.
     */
    public ExampleExportWizard() {
        super();
    }

    /**
     * Creates three wizard pages and sets the window title.
     * 
     * @param workbench
     *            , {@link IWorkbench}
     * @param selection
     *            , {@link IStructuredSelection}, if no selection should be set, the parameter has
     *            to {@code StructuredSelection.EMPTY}.
     */
    public void init(final IWorkbench workbench, final IStructuredSelection selection) {
        setWindowTitle("Kieler Example Export");
        setNeedsProgressMonitor(true);

        examplePage = new ExampleAttributesPage("Example Export", selection);
        resourcePage = new ExampleResourcesPage("Example Resources", selection);
        exportPage = new ExampleExportPage("Destination Choice", selection);
    }

    @Override
    public void addPages() {
        super.addPages();
        addPage(examplePage);
        addPage(resourcePage);
        addPage(exportPage);
    }

    @Override
    public boolean performFinish() {
        try {

            Map<ExampleElement, Object> result = new HashMap<ExampleElement, Object>();

            result.put(ExampleElement.TITLE, examplePage.getExampleTitle());
            result.put(ExampleElement.AUTHOR, examplePage.getAuthor());
            result.put(ExampleElement.DESCRIPTION, examplePage.getExampleDescription());
            result.put(ExampleElement.CONTACT, examplePage.getContact());
            result.put(ExampleElement.OVERVIEW_PIC, exportPage.getPreviewPic().getText());

            result.put(ExampleElement.SOURCETYPE, exportPage.getSourceType());
            result.put(ExampleElement.DEST_LOCATION, exportPage.getDestLocation());
            // FIXME category auf eine runterbrechen, denke dran bei example mit catIds arbeiten!!!
            result.put(ExampleElement.CATEGORY, exportPage.getCheckedCategories().get(0));
            result.put(ExampleElement.CREATE_CATEGORIES, exportPage.getCreatableCategories());

            resourcePage.buildResourceStructure();
            result.put(ExampleElement.RESOURCES, resourcePage.getExportedResources());
            ExampleManager.get().export(result);
        } catch (RuntimeException e) {
            MessageDialog.open(MessageDialog.ERROR, getShell(), "Error while exporting example.",
                    e.getMessage(), SWT.NONE);
            return false;
        }
        return true;

    }

}
