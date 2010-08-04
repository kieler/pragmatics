package de.cau.cs.kieler.kex.ui.wizards.exporting;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.kex.controller.ExampleElement;
import de.cau.cs.kieler.kex.controller.ExampleManager;

public class ExportExampleWizard extends Wizard implements IWizard {

	private ExamplePage examplePage;

	@Override
	public void addPages() {
		examplePage = new ExamplePage("examplePage");
		addPage(examplePage);
	}

	@Override
	public boolean performFinish() {

		// TODO exampleResource mit einbinden...
		Map<ExampleElement, Object> result = new HashMap<ExampleElement, Object>();
		result.put(ExampleElement.PROJECTID, examplePage.getProjectId());
		result.put(ExampleElement.DEST_LOCATION, examplePage.getLocation());
		result.put(ExampleElement.ID, examplePage.getId());
		result.put(ExampleElement.NAME, examplePage.getExampleName());
		// result.put(ExampleElement.DESCRIPTION,
		// examplePage.getExampleDescription());
		result.put(ExampleElement.VERSION, examplePage.getVersion());
		result.put(ExampleElement.CONTACT, examplePage.getContact());

		try {
			ExampleManager.get().exportExample(result);
		} catch (KielerException e) {
			MessageDialog.open(MessageDialog.INFORMATION, getShell(),
					"Error while exporting example.", e.getMessage(), SWT.NONE);
			return false;
		}
		return true;
	}

}
