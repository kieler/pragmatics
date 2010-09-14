package de.cau.cs.kieler.kex.ui.wizards.exporting;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.kex.controller.ExampleElement;
import de.cau.cs.kieler.kex.controller.ExampleManager;

public class ExampleExportWizard extends Wizard implements IExportWizard {

    private ExampleAttributesPage examplePage;
    private ExampleResourcesPage resourcePage;
    private ExampleExportPage exportPage;

    public ExampleExportWizard() {
        super();
    }

    public void init(IWorkbench workbench, IStructuredSelection selection) {
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
            result.put(ExampleElement.OVERVIEW_PIC, exportPage.getOverviewPic().getText());

            result.put(ExampleElement.SOURCETYPE, exportPage.getSourceType());
            result.put(ExampleElement.DEST_LOCATION, exportPage.getDestLocation());
            result.put(ExampleElement.CATEGORIES, exportPage.getCheckedCategories());
            result.put(ExampleElement.CREATE_CATEGORIES, exportPage.getCreatableCategories());

            resourcePage.buildResourceStructure();
            result.put(ExampleElement.RESOURCES, resourcePage.getExportedResources());

            ExampleManager.get().export(result);
        } catch (KielerException e) {
            MessageDialog.open(MessageDialog.ERROR, getShell(), "Error while exporting example.",
                    e.getMessage(), SWT.NONE);
            return false;
        }
        return true;

    }

}
