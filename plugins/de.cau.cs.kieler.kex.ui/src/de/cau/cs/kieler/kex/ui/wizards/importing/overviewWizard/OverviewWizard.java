package de.cau.cs.kieler.kex.ui.wizards.importing.overviewWizard;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IWorkbench;

public class OverviewWizard extends Wizard implements IWizard {

	private OverviewPage overviewPage;

	private IStructuredSelection currentSelection;

	private final boolean containsPreAndNextButtons;

	public OverviewWizard() {
		super();
		setNeedsProgressMonitor(true);
		setWindowTitle("Kieler Example Overview");
		containsPreAndNextButtons = false;
	}

	@Override
	public boolean performFinish() {
		return true;
	}

	@Override
	public boolean needsPreviousAndNextButtons() {
		return containsPreAndNextButtons;
	}

	@Override
	public void addPages() {
		overviewPage = new OverviewPage("overviewPage");
		addPage(overviewPage);
	}

	public IStructuredSelection getSelection() {
		return this.currentSelection;
	}

	public void init(IWorkbench workbench, IStructuredSelection currentSelection) {
		this.currentSelection = currentSelection;
		setNeedsProgressMonitor(true);
	}

}