package de.cau.cs.kieler.kex.ui.wizards.importing.importWizard;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.Wizard;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.kex.controller.ExampleManager;

public class ImportWizard extends Wizard implements IWizard {

	private ChooseExamplePage chooseExamplePage;
	private ImportExamplePage importExamplePage;

	private final IStructuredSelection selection;

	public ImportWizard(IStructuredSelection selection) {
		super();

		// TODO nullpointer exception falls selection null!

		this.selection = selection;
		setNeedsProgressMonitor(true);
		setWindowTitle("Kieler Example Import");
		ExampleManager.get().load();
	}

	@Override
	public boolean performFinish() {
		try {
			ExampleManager.get().importExamples(
					importExamplePage.getContainerPath(),
					chooseExamplePage.getSelectedExamples());
		} catch (KielerException e) {
			// Messagebox ausgabe
			return false;
		}
		// TODO refresh local workspace
		// it would be best if only folder will refreshed in which the example
		// has to be added.
		return true;
	}

	@Override
	public void addPages() {
		chooseExamplePage = new ChooseExamplePage("chooseExamplePage");
		addPage(chooseExamplePage);
		importExamplePage = new ImportExamplePage("importExamplePage",
				selection);
		addPage(importExamplePage);
	}

	public IStructuredSelection getSelection() {
		return selection;
	}
}