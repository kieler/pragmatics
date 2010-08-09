package de.cau.cs.kieler.kex.ui.wizards.exporting;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.Wizard;

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

		return false;
		// TODO exampleResource mit einbinden...
		// Map<ExampleElement, Object> result = new HashMap<ExampleElement,
		// Object>();
		// // FIXME so wie ich momentan vorgehe, ist die projectId überflüssig
		// // result.put(ExampleElement.PROJECTID, examplePage.getProjectId());
		// // result.put(ExampleElement.DEST_LOCATION,
		// examplePage.getLocation());
		// result.put(ExampleElement.ID, examplePage.getId());
		// result.put(ExampleElement.NAME, examplePage.getExampleName());
		// result.put(ExampleElement.DESCRIPTION,
		// examplePage.getExampleDescription());
		// result.put(ExampleElement.VERSION, examplePage.getVersion());
		// result.put(ExampleElement.CONTACT, examplePage.getContact());
		//
		// // just a test
		// // TODO plattform unabhï¿½ngiger pfadbau in externe methode
		// auslagern,
		// // wenn nicht schon der richte pfad vom ui runtergereicht wird.
		// // just a test
		// // StringBuffer destLocation = new StringBuffer();
		// // // windows test
		// // destLocation.append("E:").append(File.separatorChar);
		// // destLocation.append("bachelorarbeit").append(File.separatorChar);
		// // destLocation.append("3_6 Workspace").append(File.separatorChar);
		// // destLocation.append("de.cau.cs.kieler.core.kex.models");
		//
		// // linux test
		// // destLocation
		// //
		// .append("/home/pkl/kieler-workspace/de.cau.cs.kieler.core.kex.models");
		//
		// try {
		// ExampleManager.get().exportExample(result);
		// } catch (KielerException e) {
		// MessageDialog.open(MessageDialog.INFORMATION, getShell(),
		// "Error while exporting example.", e.getMessage(), SWT.NONE);
		// return false;
		// }
		// return true;
	}

}
