package de.cau.cs.kieler.kex.ui.wizards.edit;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class ChooseExamplePage extends WizardPage {

	protected ChooseExamplePage(String pageName) {
		super(pageName);
		// TODO Auto-generated constructor stub
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout());
		new Button(composite, SWT.NONE);
		setControl(composite);
	}
}
