package de.cau.cs.kieler.kex.ui.actions;

import java.util.Properties;

import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.intro.impl.IntroPlugin;
import org.eclipse.ui.intro.IIntroSite;
import org.eclipse.ui.intro.config.IIntroAction;

import de.cau.cs.kieler.kex.ui.wizards.importing.ExampleImportWizard;

public class ImportWizardAction implements IIntroAction {

	/**
	 * closes the Introscreen and starts the ExampleImportWizard.
	 */
	@SuppressWarnings("restriction")
	public void run(IIntroSite site, Properties params) {
		IntroPlugin.closeIntro();
		IWorkbench wb = PlatformUI.getWorkbench();
		IWorkbenchWindow win = wb.getActiveWorkbenchWindow();
		ExampleImportWizard wizard = new ExampleImportWizard();
		wizard.init(wb, StructuredSelection.EMPTY);
		WizardDialog dialog = new WizardDialog(win.getShell(), wizard);
		dialog.create();
		dialog.open();
	}
}