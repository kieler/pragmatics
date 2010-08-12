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

public class ExportExampleWizard extends Wizard implements IWizard {

	private ExamplePage examplePage;
	private ExampleResourcePage exRePage;
	private ResourcePage rePage;

	private final IStructuredSelection selection;

	public ExportExampleWizard(IStructuredSelection selection) {
		super();
		this.selection = selection;
		setWindowTitle("Kieler Example Export");
	}

	@Override
	public void addPages() {
		examplePage = new ExamplePage("examplePage");
		exRePage = new ExampleResourcePage("projectImportPage");
		rePage = new ResourcePage("resourcePage");
		addPage(examplePage);
		addPage(exRePage);
		addPage(rePage);
	}

	@Override
	public boolean performFinish() {

		Map<ExampleElement, Object> result = new HashMap<ExampleElement, Object>();
		result.put(ExampleElement.DEST_LOCATION, exRePage.getDestLocation());
		// TODO test ausbauen und echtes implementieren.
		result.put(ExampleElement.RESOURCES, rePage.getExportedFiles());

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

	private List<URL> testExampleResources() {
		List<URL> result = new ArrayList<URL>();
		try {
			// linux test
			// // TODO check, sollte schon direkt auch die unterliegenden
			// Elemente
			// // hinzuf�gen.
			// resource1.addResource(new URL("http", "localhost",
			// "/home/pkl/runtime-New_configuration/testpro/SyncTest"));
			//
			// // TODO schauen, ob resource alternativ auch ein path in form
			// eines
			// // Strings sein kann...
			// resource1
			// .addResource(new URL("http", "localhost",
			// "/home/pkl/runtime-New_configuration/testpro/SyncTest/folderFile.kids"));
			// resource1
			// .addResource(new URL("http", "localhost",
			// "/home/pkl/runtime-New_configuration/testpro/SyncTest/folderFile.kixs"));
			// result.add(resource1);

			result.add(new URL("http", "localhost", "/testPro/myExample/"));
			result.add(new URL("http", "localhost", "/testPro/myExample/ladida"));
			result.add(new URL("http", "localhost",
					"/testPro/myExample/test.file"));

			// ExampleResource resource2 = new ExampleResource();
			// resource2.setCategory("de.cau.cs.kieler.syncchart");
			// resource2.setHeadResource(true);
			// // TODO check, sollte schon direkt auch die unterliegenden
			// Elemente
			// // hinzuf�gen.
			// resource2
			// .addResource(new URL("http", "localhost",
			// "E:\\bachelorarbeit\\runtime-New_configuration\\testPro\\test.file"));
			// result.add(resource2);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
