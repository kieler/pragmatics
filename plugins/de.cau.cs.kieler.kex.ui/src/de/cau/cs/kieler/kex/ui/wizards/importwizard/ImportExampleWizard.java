package de.cau.cs.kieler.kex.ui.wizards.importwizard;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;

import de.cau.cs.kieler.kex.controller.ExampleManager;

public class ImportExampleWizard extends Wizard implements IWizard {

	private OverviewPage overviewPage;
	private ChooseExamplePage chooseExamplePage;
	// private ImportExamplePage importExamplePage;

	private final IStructuredSelection selection;

	private boolean containsPreAndNextButtons;

	public ImportExampleWizard(IStructuredSelection selection) {
		super();
		this.selection = selection;
		setNeedsProgressMonitor(true);
		setWindowTitle("Kieler Example Management");
		containsPreAndNextButtons = false;

		// show();
	}

	@Override
	public boolean performFinish() {
		// ExampleManager.get().

		return true;
	}

	@Override
	public boolean needsPreviousAndNextButtons() {
		return containsPreAndNextButtons;
	}

	@Override
	public void addPages() {
		overviewPage = new OverviewPage("overviewPage");
		addPage(overviewPage);
		chooseExamplePage = new ChooseExamplePage("chooseExamplePage");
		addPage(chooseExamplePage);
		// importExamplePage = new ImportExamplePage("importExamplePage",
		// selection);
		// addPage(importExamplePage);
	}

	public void show() {
		// manager.reload();
		List<String> examplesAsStrings = ExampleManager.get()
				.getExamplesAsStrings();
		if (examplesAsStrings.size() > 0) {
			System.out.println("generated examples:");
			for (String exampleAsString : examplesAsStrings) {
				System.out.println(exampleAsString);
			}
		}

		List<String> categeories = ExampleManager.get().getCategories();
		if (categeories.size() > 0) {
			System.out.println("generated categories:");
			for (String categoryAsString : categeories) {
				System.out.println(categoryAsString);
			}
		}
		// TODO: Errors ueber den Statusmanager verteilen
	}

	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		containsPreAndNextButtons = page.getName().equals("overviewPage");
		return super.getNextPage(page);
	}

}
