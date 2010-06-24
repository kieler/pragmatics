package de.cau.cs.kieler.kex.ui.wizards.importwizard;

import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Shell;

import de.cau.cs.kieler.kex.ui.KielerExampleStore;
import de.cau.cs.kieler.kex.ui.SharedExampleStore;

public class ExampleDialog extends WizardDialog implements SelectionListener{

	private KielerExampleStore kielerStore;
	private SharedExampleStore sharedStore;

	public ExampleDialog(Shell parentShell, IWizard newWizard) {
		super(parentShell, newWizard);
		kielerStore = new KielerExampleStore();
		kielerStore.load();
		sharedStore = new SharedExampleStore();
		sharedStore.load();
	}

	

	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void widgetSelected(SelectionEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
}
