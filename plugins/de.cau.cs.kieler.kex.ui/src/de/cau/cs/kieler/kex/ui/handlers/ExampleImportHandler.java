package de.cau.cs.kieler.kex.ui.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import de.cau.cs.kieler.kex.ui.wizards.importing.importWizard.ExampleImportWizard;

public class ExampleImportHandler extends AbstractHandler {

	public Object execute(ExecutionEvent event) throws ExecutionException {

		IWorkbenchWindow workbenchWindow = HandlerUtil
				.getActiveWorkbenchWindowChecked(event);
		if (workbenchWindow == null) {
			// action has been disposed
			return null;
		}
		ISelection selection = workbenchWindow.getSelectionService()
				.getSelection();
		ExampleImportWizard wizard = new ExampleImportWizard(
				(selection instanceof IStructuredSelection) ? (IStructuredSelection) selection
						: null);
		Shell parent = workbenchWindow.getShell();
		WizardDialog dialog = new WizardDialog(parent, wizard);
		dialog.create();
		dialog.open();
		return null;
	}

}
