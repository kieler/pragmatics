package de.cau.cs.kieler.kex.ui.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import de.cau.cs.kieler.kex.ui.wizards.editing.ExampleEditWizard;
import de.cau.cs.kieler.kex.ui.wizards.exporting.ExampleExportWizard;
import de.cau.cs.kieler.kex.ui.wizards.importing.ExampleImportWizard;

public class ExampleHandler extends AbstractHandler {

	private final WizardType wizardType;

	public ExampleHandler(WizardType wizardType) {
		super();
		this.wizardType = wizardType;

	}

	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow workbenchWindow = HandlerUtil
				.getActiveWorkbenchWindowChecked(event);
		if (workbenchWindow == null) {
			// action has been disposed
			return null;
		}
		ISelection selection = workbenchWindow.getSelectionService()
				.getSelection();
		IStructuredSelection structuredSelection = (selection instanceof IStructuredSelection) ? (IStructuredSelection) selection
				: StructuredSelection.EMPTY;
		IWizard wizard = null;
		if (wizardType == WizardType.IMPORT) {
			wizard = new ExampleImportWizard(structuredSelection);
		} else if (wizardType == WizardType.EXPORT) {
			wizard = new ExampleExportWizard(structuredSelection);
		} else if (wizardType == WizardType.UPDATE) {
			wizard = new ExampleEditWizard(structuredSelection);
		} else
			return null;
		Shell parent = workbenchWindow.getShell();
		WizardDialog dialog = new WizardDialog(parent, wizard);
		dialog.create();
		dialog.open();
		return null;
	}

}
