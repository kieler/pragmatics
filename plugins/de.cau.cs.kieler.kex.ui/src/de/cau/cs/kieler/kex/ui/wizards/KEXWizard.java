package de.cau.cs.kieler.kex.ui.wizards;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IWorkbench;

import de.cau.cs.kieler.kex.controller.ExampleManager;

public class KEXWizard extends Wizard implements IWizard {

	// private SampleNewWizardPage page1;
	// private SampleNewWizardPage page2;
	// private SampleNewWizardPage page3;
	
//	private ISelection selection;


	public KEXWizard() {
		super();
		setNeedsProgressMonitor(true);
		show();
	}

	@Override
	public boolean performFinish() {
		return false;
	}

	@Override
	public void addPages() {
//		page1 = new SampleNewWizardPage(selection);
//		addPage(page1);
//		page2 = new SampleNewWizardPage(selection);
//		addPage(page2);
//		page3 = new SampleNewWizardPage(selection);
//		addPage(page3);
	}

	public void show() {
		// manager.reload();

		List<String> examplesAsStrings = ExampleManager.get().getExamplesAsStrings();
		if (examplesAsStrings.size() > 0) {
			System.out.println("generated examples:");
			for ( String exampleAsString : examplesAsStrings)
			{
				System.out.println(exampleAsString);
			}
		}

		List<String> categeories = ExampleManager.get().getCategories();
		if(categeories.size() > 0){
			System.out.println("generated categories:");
			for (String categoryAsString : categeories) {
				System.out.println(categoryAsString);
			}
		}
		// TODO: Errors ueber den Statusmanager verteilen
	}
	
	
	public void init(IWorkbench workbench, IStructuredSelection selection) {
//		this.selection = selection;
	}

}
