package de.cau.cs.kieler.kex.ui.wizards.importwizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class ChooseExamplePage extends WizardPage {

	private Text street;
	private Text city;
	private Text state;

	public ChooseExamplePage(String pageName) {
		super(pageName);
		setTitle("Address Information");
		setDescription("Please enter your address information");
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		composite.setLayout(layout);
		setControl(composite);
		new Label(composite, SWT.NONE).setText("Street");
		setStreet(new Text(composite, SWT.NONE));
		new Label(composite, SWT.NONE).setText("City");
		setCity(new Text(composite, SWT.NONE));
		new Label(composite, SWT.NONE).setText("State");
		setState(new Text(composite, SWT.NONE));
	}

	public void setStreet(Text street) {
		this.street = street;
	}

	public Text getStreet() {
		return street;
	}

	public void setCity(Text city) {
		this.city = city;
	}

	public Text getCity() {
		return city;
	}

	public void setState(Text state) {
		this.state = state;
	}

	public Text getState() {
		return state;
	}

}
