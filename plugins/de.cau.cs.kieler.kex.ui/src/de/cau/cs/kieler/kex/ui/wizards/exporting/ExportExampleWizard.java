package de.cau.cs.kieler.kex.ui.wizards.exporting;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.kex.controller.ExampleElement;
import de.cau.cs.kieler.kex.controller.ExampleManager;
import de.cau.cs.kieler.kex.model.ExampleResource;

public class ExportExampleWizard extends Wizard implements IWizard {

	private ExamplePage examplePage;
	private ExampleResourcePage exRePage;

	private final IStructuredSelection selection;

	public ExportExampleWizard(IStructuredSelection selection) {
		super();
		this.selection = selection;
		setWindowTitle("Kieler Example Import");
	}

	@Override
	public void addPages() {
		examplePage = new ExamplePage("examplePage");
		exRePage = new ExampleResourcePage("projectImportPage");
		addPage(examplePage);
		addPage(exRePage);
	}

	@Override
	public boolean performFinish() {

		Map<ExampleElement, Object> result = new HashMap<ExampleElement, Object>();
		result.put(ExampleElement.DEST_LOCATION, exRePage.getDestLocation());
		// TODO test ausbauen und echtes implementieren.
		result.put(ExampleElement.RESOURCES, testExampleResources());

		result.put(ExampleElement.ID, examplePage.getId());
		result.put(ExampleElement.NAME, examplePage.getExampleName());
		result.put(ExampleElement.DESCRIPTION,
				examplePage.getExampleDescription());
		result.put(ExampleElement.VERSION, examplePage.getVersion());
		result.put(ExampleElement.CONTACT, examplePage.getContact());
		result.put(ExampleElement.IMPORTTYPE, exRePage.getImportType());
		try {
			ExampleManager.get().exportExample(result);
		} catch (KielerException e) {
			MessageDialog.open(MessageDialog.INFORMATION, getShell(),
					"Error while exporting example.", e.getMessage(), SWT.NONE);
			return false;
		}
		return true;
	}

	private List<ExampleResource> testExampleResources() {
		List<ExampleResource> result = new ArrayList<ExampleResource>();
		try {
			ExampleResource resource1 = new ExampleResource();
			resource1.setCategory("de.cau.cs.kieler.dataflow");
			resource1.setHeadResource(true);
			// TODO check, sollte schon direkt auch die unterliegenden Elemente
			// hinzufügen.
			resource1
					.addResource(new URL(
							"http",
							"localhost",
							"E:\\bachelorarbeit\\runtime-New_configuration\\testPro\\examples\\Flight\\SyncTest"));

			// TODO schauen, ob resource alternativ auch ein path in form eines
			// Strings sein kann...
			resource1
					.addResource(new URL(
							"http",
							"localhost",
							"E:\\bachelorarbeit\\runtime-New_configuration\\testPro\\examples\\Flight\\SyncTest\\folderFile.kids"));
			resource1
					.addResource(new URL(
							"http",
							"localhost",
							"E:\\bachelorarbeit\\runtime-New_configuration\\testPro\\examples\\Flight\\SyncTest\\folderFile.kixs"));
			result.add(resource1);

			ExampleResource resource2 = new ExampleResource();
			resource2.setCategory("de.cau.cs.kieler.syncchart");
			resource2.setHeadResource(true);
			// TODO check, sollte schon direkt auch die unterliegenden Elemente
			// hinzufügen.
			resource2
					.addResource(new URL("http", "localhost",
							"E:\\bachelorarbeit\\runtime-New_configuration\\testPro\\test.file"));
			result.add(resource2);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
