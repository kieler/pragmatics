package de.cau.cs.kieler.kex.ui.wizards.exporting;

import java.util.HashMap;
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
		result.put(ExampleElement.RESOURCES, exRePage.getExampleResources());
		result.put(ExampleElement.ID, examplePage.getId());
		result.put(ExampleElement.NAME, examplePage.getExampleName());
		result.put(ExampleElement.DESCRIPTION,
				examplePage.getExampleDescription());
		result.put(ExampleElement.VERSION, examplePage.getVersion());
		result.put(ExampleElement.CONTACT, examplePage.getContact());

		// just a test
		// TODO plattform unabh�ngiger pfadbau in externe methode
		// auslagern,
		// wenn nicht schon der richte pfad vom ui runtergereicht wird.
		// just a test
		// StringBuffer destLocation = new StringBuffer();
		// // windows test
		// destLocation.append("E:").append(File.separatorChar);
		// destLocation.append("bachelorarbeit").append(File.separatorChar);
		// destLocation.append("3_6 Workspace").append(File.separatorChar);
		// destLocation.append("de.cau.cs.kieler.core.kex.models");

		// linux test
		// destLocation
		//
		// .append("/home/pkl/kieler-workspace/de.cau.cs.kieler.core.kex.models");

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
