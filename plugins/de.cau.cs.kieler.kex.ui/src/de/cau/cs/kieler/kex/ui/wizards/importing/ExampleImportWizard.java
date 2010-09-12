package de.cau.cs.kieler.kex.ui.wizards.importing;

import java.util.List;

import org.eclipse.core.filesystem.URIUtil;
import org.eclipse.core.internal.resources.Resource;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorRegistry;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

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

	@SuppressWarnings("restriction")
	@Override
	public boolean performFinish() {
		List<String> directOpens = null;
		try {
			directOpens = ExampleManager.get().importExamples(
					mainPage.getContainerPath(), mainPage.getCheckedExamples(),
					mainPage.isQuickStart());
		} catch (KielerException e) {
			// Messagebox ausgabe
			return false;
		}

		// refresh workspace project
		// TODO wird nicht bei quickstart gehen.
		IContainer element = ResourcesPlugin.getWorkspace().getRoot()
				.getProject(mainPage.getContainerPath().segment(0));
		try {
			if (element != null) {
				element.refreshLocal(IContainer.DEPTH_INFINITE, null);
			}
		} catch (CoreException e1) {
			// do nothing
		}

		// open direct opens
		if (directOpens != null) {
			IWorkbenchWindow win = PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow();
			IWorkbenchPage page = win.getActivePage();
			for (String path : directOpens) {
				IFile[] files = ResourcesPlugin.getWorkspace().getRoot()
						.findFilesForLocationURI(URIUtil.toURI(path),
								Resource.FILE);
				if (files.length == 1) {
					IEditorDescriptor defaultEditor = PlatformUI.getWorkbench()
							.getEditorRegistry().getDefaultEditor(
									files[0].getName());
					if (defaultEditor != null) {

					} else {
						defaultEditor = PlatformUI
								.getWorkbench()
								.getEditorRegistry()
								.findEditor(
										IEditorRegistry.SYSTEM_EXTERNAL_EDITOR_ID);
					}
					try {
						page.openEditor(new FileEditorInput(files[0]),
								defaultEditor.getId());
					} catch (PartInitException e) {
						MessageDialog.openError(getShell(), "Opening Editor", e
								.getLocalizedMessage());
					}
				}
			}
		}
		return true;
	}
}