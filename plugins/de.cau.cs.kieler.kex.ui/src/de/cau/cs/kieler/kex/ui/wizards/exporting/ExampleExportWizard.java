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

    // TODO validate auf wizardpages verteilen
    // private static final int EXAMPLE_TITLE_MIN = 4;
    // private static final int AUTHOR_MIN = 3;
    // private static final int DESCRIPTION_MIN = 10;
    // private static final int CONTACT_MIN = 5;
    // /**
    // * Method for validating given map elements. This contains minimumlengths- and
    // duplicate-checks.
    // *
    // * @param map
    // * , Map of {@link ExampleElement} and an arbitrary {@link Object}.
    // * @param collectors
    // * , {@link ExampleCollector}s
    // */
    // @SuppressWarnings("unchecked")
    // public static void validate(final Map<ExampleElement, Object> map,
    // final ExampleCollector... collectors) {
    // checkAttributes(map);
    //
    // Object sourceType = map.get(ExampleElement.SOURCETYPE);
    // if (!(sourceType instanceof SourceType)) {
    // throw new RuntimeException("No source type has been defined.");
    // }
    //
    // String destLocation = (String) map.get(ExampleElement.DEST_LOCATION);
    // validateField(destLocation, 2, "Destination Location");
    //
    // // List<String> categories = (List<String>) map.get(ExampleElement.CATEGORY);
    // // validateElement(categories, 1, "Categories");
    //
    // List<ExportResource> exportedResources = (List<ExportResource>) map
    // .get(ExampleElement.RESOURCES);
    // validateElement(exportedResources, 1, "Exported Resources");
    //
    //
    // }
    //
    // private static void checkAttributes(final Map<ExampleElement, Object> map) {
    // String exampleTitle = (String) map.get(ExampleElement.TITLE);
    // validateField(exampleTitle, EXAMPLE_TITLE_MIN, "Example Title");
    //
    // String author = (String) map.get(ExampleElement.AUTHOR);
    // // min. uni abbreviations like pkl
    // validateField(author, AUTHOR_MIN, "Author");
    //
    // String exampleDescription = (String) map.get(ExampleElement.DESCRIPTION);
    // validateField(exampleDescription, DESCRIPTION_MIN, "Example Description");
    //
    // String exampleContact = (String) map.get(ExampleElement.CONTACT);
    // // min 5 chars a@b.c
    // validateField(exampleContact, CONTACT_MIN, "Example Contact");
    // }
    //
    // private static void validateElement(final List<?> list, final int minLength,
    // final String listName) {
    // if (list == null || list.size() < minLength) {
    // StringBuilder errorMsg = new StringBuilder();
    // errorMsg.append("No ").append(listName).append(" has been selected.\n")
    // .append("Please choose at least ").append(String.valueOf(minLength));
    // throw new RuntimeException(errorMsg.toString());
    // }
    // }
    //
    // private static void validateField(final String checkable, final int minLength,
    // final String checkableName) {
    // if (checkable == null || checkable.length() < minLength) {
    // StringBuilder errorMsg = new StringBuilder();
    // errorMsg.append("The field ").append(checkableName)
    // .append(" has to be set with at least ").append(String.valueOf(minLength))
    // .append(" characters.");
    // throw new RuntimeException(errorMsg.toString());
    // }
    // }

}
