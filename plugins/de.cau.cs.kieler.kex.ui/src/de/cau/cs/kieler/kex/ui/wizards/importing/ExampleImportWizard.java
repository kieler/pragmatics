package de.cau.cs.kieler.kex.ui.wizards.importing;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.Wizard;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.kex.controller.ExampleManager;

public class ExampleImportWizard extends Wizard implements IWizard {

	private ImportExamplePage importExamplePage;

	private final IStructuredSelection selection;

	// TODO schweinerein sollen die wizard �berleben, also sicher machen auf
	// nullpr�fungen, fieses hin und her wechseln der pages and so on...
	public ExampleImportWizard(IStructuredSelection selection) {
		super();
		this.selection = selection;
		setNeedsProgressMonitor(true);
		setWindowTitle("Kieler Example Import");
		try {
			ExampleManager.get().load(false);
		} catch (KielerException e) {
			MessageDialog.openError(this.getShell(), "Could not load example.",
					e.getLocalizedMessage());
		}
	}

	@Override
	public void addPages() {
		importExamplePage = new ImportExamplePage("importExamplePage",
				selection);
		addPage(importExamplePage);
	}

	public IStructuredSelection getSelection() {
		return selection;
	}

	@Override
	public boolean performFinish() {

		try {
			ExampleManager.get().importExamples(
					importExamplePage.getContainerPath(),
					importExamplePage.getCheckedExamples());
			// importExamplePage.getRootResource();
		} catch (KielerException e) {
			// Messagebox ausgabe
			return false;
		}

		// refresh workspace project
		IContainer element = ResourcesPlugin.getWorkspace().getRoot()
				.getProject(importExamplePage.getContainerPath().segment(0));
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