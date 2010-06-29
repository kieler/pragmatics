package de.cau.cs.kieler.kex.ui.wizards.exportwizard;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.internal.wizards.newresource.ResourceMessages;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.kex.controller.ExampleElement;
import de.cau.cs.kieler.kex.controller.ExampleManager;

public class ExportExampleWizard extends Wizard implements IWizard {

	private ExamplePage examplePage;

	private final IProject[] localProjects;

	private IWorkbench workbench;

	private IStructuredSelection currentSelection;

	public ExportExampleWizard() {
		localProjects = ExampleManager.get().getLocalProjects();

	}

	@Override
	public void addPages() {
		examplePage = new ExamplePage("examplePage", localProjects);
		addPage(examplePage);
	}

	@Override
	public boolean performFinish() {

		// TODO exampleResource mit einbinden...
		Map<ExampleElement, Object> result = new HashMap<ExampleElement, Object>();
		result.put(ExampleElement.PROJECTID, examplePage.getProjectId());
		result.put(ExampleElement.LOCATION, examplePage.getLocation());
		result.put(ExampleElement.ID, examplePage.getId());
		result.put(ExampleElement.NAME, examplePage.getExampleName());
		// result.put(ExampleElement.DESCRIPTION,
		// examplePage.getExampleDescription());
		result.put(ExampleElement.VERSION, examplePage.getVersion());
		result.put(ExampleElement.CONTACT, examplePage.getContact());

		try {
			ExampleManager.get().exportExample(result);
		} catch (KielerException e) {
			MessageDialog.openError(getShell(),
					"Error while exporting example.", e.getMessage());
			return false;
		}
		return true;
	}

	public void init(IWorkbench workbench, IStructuredSelection currentSelection) {
		this.workbench = workbench;
		this.currentSelection = currentSelection;
		setWindowTitle(ResourceMessages.NewFolder_title);
		setNeedsProgressMonitor(true);
	}

}
