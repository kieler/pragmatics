package de.cau.cs.kieler.kex.ui.wizards.importing;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.kex.controller.ExampleManager;

public class ExampleImportWizard extends Wizard implements IImportWizard {

	private ImportExamplePage mainPage;

	public ExampleImportWizard() {
		super();
	}

	public void init(IWorkbench workbench, IStructuredSelection selection) {
		setWindowTitle("Kieler Example Import");
		setNeedsProgressMonitor(true);
		try {
			ExampleManager.get().load(false);
		} catch (KielerException e) {
			MessageDialog.openError(this.getShell(), "Could not load example.",
					e.getLocalizedMessage());
		}
		mainPage = new ImportExamplePage("Import Example", selection);
	}

	@Override
	public void addPages() {
		super.addPages();
		addPage(mainPage);
	}

	@Override
	public boolean canFinish() {
		if (mainPage.isQuickStart())
			return true;
		return super.canFinish();
	}

	@Override
	public boolean performFinish() {

		try {
			ExampleManager.get().importExamples(mainPage.getContainerPath(),
					mainPage.getCheckedExamples());
		} catch (KielerException e) {
			// Messagebox ausgabe
			return false;
		}

		// refresh workspace project
		IContainer element = ResourcesPlugin.getWorkspace().getRoot()
				.getProject(mainPage.getContainerPath().segment(0));
		try {
			if (element != null) {
				element.refreshLocal(IContainer.DEPTH_INFINITE, null);
			}
		} catch (CoreException e1) {
			// do nothing
		}
		// open head file
		// IDE.openEditor(getWorkbenchPage(), element, true);

		return true;
	}

}