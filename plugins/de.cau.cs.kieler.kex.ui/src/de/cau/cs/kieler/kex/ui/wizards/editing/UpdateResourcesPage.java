package de.cau.cs.kieler.kex.ui.wizards.editing;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class UpdateResourcesPage extends WizardPage {

	protected UpdateResourcesPage(String pageName) {
		super(pageName);
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout());
		new Button(composite, SWT.NONE);
		setControl(composite);
	}

}
