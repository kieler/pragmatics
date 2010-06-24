package de.cau.cs.kieler.kex.ui.wizards.importwizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class OverviewPage extends WizardPage{

	private Text firstNameText;
    private Text secondNameText;
	
	
	public OverviewPage(String pageName) {
		super(pageName);
        setTitle("Personal Information");
        setDescription("Please enter your personal information");
	}

	public void createControl(Composite parent) {
		 Composite composite = new Composite(parent, SWT.NONE);
         GridLayout layout = new GridLayout();
          layout.numColumns = 2;
          composite.setLayout(layout);
          setControl(composite);
          new Label(composite,SWT.NONE).setText("First Name");
          setFirstNameText(new Text(composite,SWT.NONE));
          new Label(composite,SWT.NONE).setText("Last Name");
          setSecondNameText(new Text(composite,SWT.NONE));
	}

	public void setFirstNameText(Text firstNameText) {
		this.firstNameText = firstNameText;
	}

	public Text getFirstNameText() {
		return firstNameText;
	}

	public void setSecondNameText(Text secondNameText) {
		this.secondNameText = secondNameText;
	}

	public Text getSecondNameText() {
		return secondNameText;
	}

}
