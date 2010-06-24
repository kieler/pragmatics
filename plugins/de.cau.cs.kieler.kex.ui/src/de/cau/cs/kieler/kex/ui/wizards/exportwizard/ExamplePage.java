package de.cau.cs.kieler.kex.ui.wizards.exportwizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class ExamplePage extends WizardPage{

	private Text id;
	//TODO Textarea waere besser geeignet.
	private Text exampleName;
	
	private Text exampleDescription;
	
	private Text version;

	public ExamplePage(String pageName) {
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
		
		new Label(composite, SWT.NONE).setText("ID");
		setId(new Text(composite, SWT.NONE));
		
		new Label(composite, SWT.NONE).setText("Name");
		setExampleName(new Text(composite, SWT.NONE));
		
		Text text = new Text(composite, SWT.NONE);
		text.setText("1.0.0");
		setVersion(text);
		
		new Label(composite, SWT.NONE).setText("description");
		setExampleDescription(new Text(composite, SWT.NONE));
		
	}

	public void setId(Text id) {
		this.id = id;
	}

	public Text getId() {
		return id;
	}
	
	public void setVersion(Text version) {
		this.version = version;
	}

	public Text getVersion() {
		return version;
	}

	public void setExampleName(Text exampleName) {
		this.exampleName = exampleName;
	}
	
	public Text getExampleName() {
		return this.exampleName;
	}

	public void setExampleDescription(Text exampleDescription) {
		this.exampleDescription = exampleDescription;
	}

	public Text getExampleDescription() {
		return exampleDescription;
	}



}
