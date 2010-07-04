package de.cau.cs.kieler.kex.ui.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import de.cau.cs.kieler.kex.ui.wizards.importing.overviewWizard.OverviewWizard;

public class OverviewAction implements IWorkbenchWindowActionDelegate {

	private IWorkbenchWindow workbenchWindow;

	public void run(IAction action) {

		if (workbenchWindow == null) {
			// action has been disposed
			return;
		}
		ISelection selection = workbenchWindow.getSelectionService()
				.getSelection();
		OverviewWizard wizard = new OverviewWizard();
		wizard.init(
				workbenchWindow.getWorkbench(),
				(selection instanceof IStructuredSelection) ? (IStructuredSelection) selection
						: StructuredSelection.EMPTY);
		Shell parent = workbenchWindow.getShell();
		WizardDialog dialog = new WizardDialog(parent, wizard);
		dialog.create();
		dialog.open();
	}

	/**
	 * Selection in the workbench has been changed. We can change the state of
	 * the 'real' action here if we want, but this can only happen after the
	 * delegate has been created.
	 * 
	 * @see IWorkbenchWindowActionDelegate#selectionChanged
	 */
	public void selectionChanged(IAction action, ISelection selection) {
	}

	/**
	 * We can use this method to dispose of any system resources we previously
	 * allocated.
	 * 
	 * @see IWorkbenchWindowActionDelegate#dispose
	 */
	public void dispose() {
		if (workbenchWindow == null) {
			// action has already been disposed
			return;
		}
		workbenchWindow = null;
	}

	/**
	 * We will cache window object in order to be able to provide parent shell
	 * for the message dialog.
	 * 
	 * @see IWorkbenchWindowActionDelegate#init
	 */
	public void init(IWorkbenchWindow window) {
		this.workbenchWindow = window;
	}
}
