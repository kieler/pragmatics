package de.cau.cs.kieler.kex.ui.wizards.editing;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.Wizard;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.kex.controller.ExampleManager;

public class ExampleEditWizard extends Wizard implements IWizard {

	private ChooseExamplePage chooseExamplePage;
	private UpdateAttributesPage updateAttributePage;
	private UpdateResourcesPage updateResourcesPage;

	private final IStructuredSelection selection;

	public ExampleEditWizard(IStructuredSelection selection) {
		super();
		this.selection = selection;
		setWindowTitle("Kieler Example Edit");
		try {
			ExampleManager.get().load(true);
		} catch (KielerException e) {
			MessageDialog.openError(this.getShell(), "Could not load example.",
					e.getLocalizedMessage());
		}
	}

	@Override
	public void addPages() {
		chooseExamplePage = new ChooseExamplePage("chooseExamplePage");
		addPage(chooseExamplePage);
		updateAttributePage = new UpdateAttributesPage("updateAttributePage");
		addPage(updateAttributePage);
		updateResourcesPage = new UpdateResourcesPage("updateResourcesPage");
		addPage(updateResourcesPage);
	}

	public IStructuredSelection getSelection() {
		return selection;
	}

	@Override
	public boolean performFinish() {
		// try {
		// ExampleManager.get().updateExample(
		// importExamplePage.getContainerPath(),
		// chooseExamplePage.getSelectedExamples());
		// } catch (KielerException e) {
		// Messagebox ausgabe
		// return false;
		// }
		// TODO refresh local workspace
		// it would be best if only folder will refreshed in which the example
		// has to be added.
		return true;
	}

}
