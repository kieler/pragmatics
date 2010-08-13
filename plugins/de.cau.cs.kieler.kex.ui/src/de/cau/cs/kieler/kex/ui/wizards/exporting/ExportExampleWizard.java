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

		result.put(ExampleElement.ID, examplePage.getId());
		result.put(ExampleElement.NAME, examplePage.getExampleName());
		result.put(ExampleElement.DESCRIPTION,
				examplePage.getExampleDescription());
		result.put(ExampleElement.VERSION, examplePage.getVersion());
		result.put(ExampleElement.CONTACT, examplePage.getContact());
		result.put(ExampleElement.EXPORTTYPE, exRePage.getImportType());

		result.put(ExampleElement.DEST_LOCATION, exRePage.getDestLocation());
		result.put(ExampleElement.CATEGORIES, exRePage.getCategories());

		result.put(ExampleElement.RESOURCES, rePage.getExportedResources());
		result.put(ExampleElement.HEAD_RESOURCE, rePage.getHeadResource());

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
