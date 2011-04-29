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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.kex.controller.ExampleElement;
import de.cau.cs.kieler.kex.controller.ExampleManager;
import de.cau.cs.kieler.kex.ui.KEXUIPlugin;

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
        setWindowTitle(Messages.getString("exportWizardTitle"));
        setNeedsProgressMonitor(true);
        examplePage = new ExampleAttributesPage(Messages.getString("attributePageTitle"), selection);
        resourcePage = new ExampleResourcesPage(Messages.getString("resourcePageTitle"), selection);
        exportPage = new ExampleExportPage(Messages.getString("exportPageTitle"), selection);
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
            result.put(ExampleElement.CATEGORY, exportPage.getCheckedCategory());
            // TODO temporarily works wrong, it is now even longer a String, it is a Category
            result.put(ExampleElement.CREATE_CATEGORIES, exportPage.getCreatableCategories());

            resourcePage.buildResourceStructure();
            result.put(ExampleElement.RESOURCES, resourcePage.getExportedResources());
            ExampleManager.get().export(result);
        } catch (RuntimeException e) {
            IStatus status = new Status(IStatus.WARNING, KEXUIPlugin.PLUGIN_ID,
                    Messages.getString("exportErrorMsg"), e);
            StatusManager.getManager().handle(status, StatusManager.SHOW);
            return false;
        }
        return true;

    }

}
