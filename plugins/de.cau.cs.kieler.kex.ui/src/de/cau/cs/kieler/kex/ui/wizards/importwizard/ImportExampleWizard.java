package de.cau.cs.kieler.kex.ui.wizards.importwizard;

import java.util.List;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IWorkbench;

import de.cau.cs.kieler.kex.controller.ExampleManager;

public class ImportExampleWizard extends Wizard implements IWizard {

	private OverviewPage overviewPage;
	private ChooseExamplePage chooseExamplePage;

	public ImportExampleWizard() {
		super();
		setNeedsProgressMonitor(true);
		setWindowTitle("Kieler Example Management");
		// show();
	}

	@Override
	public boolean performFinish() {
//		ExampleManager.get().
		
		return true;
	}

	
	
	@Override
	public void addPages() {
		overviewPage = new OverviewPage("Overview");
		addPage(overviewPage);
		chooseExamplePage = new ChooseExamplePage("Choose Example");
		addPage(chooseExamplePage);
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


}
