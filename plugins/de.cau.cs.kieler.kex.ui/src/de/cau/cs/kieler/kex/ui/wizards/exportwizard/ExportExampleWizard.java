package de.cau.cs.kieler.kex.ui.wizards.exportwizard;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.Wizard;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.kex.controller.ExampleElement;
import de.cau.cs.kieler.kex.controller.ExampleManager;

public class ExportExampleWizard extends Wizard implements IWizard {

    private ExamplePage examplePage;

    @Override
    public void addPages() {
        examplePage = new ExamplePage("examplePage");

    }

    @Override
    public boolean performFinish() {

        // TODO exampleResource mit einbinden...

        String projectId = examplePage.getProjectId();
        String location = examplePage.getLocation();

        Map<ExampleElement, Object> result = new HashMap<ExampleElement, Object>();
        String exampleId = examplePage.getId().toString();
        result.put(ExampleElement.ID, exampleId);
        String exampleName = examplePage.getName().toString();
        result.put(ExampleElement.NAME, exampleName);
        String exampleDescription = examplePage.getDescription().toString();
        result.put(ExampleElement.DESCRIPTION, exampleDescription);
        String exampleVersion = examplePage.getVersion().toString();
        result.put(ExampleElement.VERSION, exampleVersion);
        result.put(ExampleElement.PROJECTID, examplePage.getProjectId());
        result.put(ExampleElement.LOCATION, examplePage.getLocation());

        try {
            ExampleManager.get().exportExample(result);
        } catch (KielerException e) {
            MessageDialog.openError(getShell(), "Error while exporting example.", e.getMessage());
            return false;
        }
        return true;
    }

}
