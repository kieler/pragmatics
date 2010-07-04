package de.cau.cs.kieler.kex.ui.wizards.importing.importWizard;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.Wizard;

import de.cau.cs.kieler.kex.controller.ExampleManager;

public class ImportWizard extends Wizard implements IWizard {

	private ChooseExamplePage chooseExamplePage;
	private ImportExamplePage importExamplePage;

	private final IStructuredSelection selection;

	private final boolean containsPreAndNextButtons;

	public ImportWizard(IStructuredSelection selection) {
		super();
		this.selection = selection;
		setNeedsProgressMonitor(true);
		setWindowTitle("Kieler Example Import");
		containsPreAndNextButtons = false;
		ExampleManager.get().load();
	}

	@Override
	public boolean performFinish() {
		// try{
		ExampleManager.get().importExamples(
				importExamplePage.getSelectedProject(),
				chooseExamplePage.getSelectedExamples());
		return true;
		// }
		// catch(){
		// return false;
		// }
	}

	@Override
	public boolean needsPreviousAndNextButtons() {
		return containsPreAndNextButtons;
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