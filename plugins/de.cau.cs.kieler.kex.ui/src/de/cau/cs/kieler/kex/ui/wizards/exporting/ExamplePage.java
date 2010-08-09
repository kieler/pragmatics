package de.cau.cs.kieler.kex.ui.wizards.exporting;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class ExamplePage extends WizardPage {

	private Text id;

	private Text exampleName;

	private Text version;

	private Text exampleDescription;

	private Text contact;

	public ExamplePage(String pageName) {
		super(pageName);
		setTitle("Example Export");
		setDescription("Please enter example attributes");

	}

	public void createControl(Composite parent) {

		Composite composite = new Composite(parent, SWT.BORDER);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		composite.setLayout(layout);
		setControl(composite);
		composite.setLayoutData(new GridData(GridData.FILL_VERTICAL));
		new Label(composite, SWT.NONE).setText("Id:");
		Text idText = new Text(composite, SWT.BORDER);
		idText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		setId(idText);
		new Label(composite, SWT.NONE).setText("Name:");
		Text nameText = new Text(composite, SWT.BORDER);
		nameText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		setExampleName(nameText);

		new Label(composite, SWT.NONE).setText("Version:");
		Text versionText = new Text(composite, SWT.BORDER);
		versionText.setText("1.0");
		versionText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		setVersion(versionText);

		new Label(composite, SWT.NONE).setText("Contact:");
		Text contactText = new Text(composite, SWT.BORDER);
		contactText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		setContact(contactText);

		new Label(composite, SWT.NONE).setText("Description:");
		Text descText = new Text(composite, SWT.BORDER | SWT.MULTI
				| SWT.V_SCROLL | SWT.H_SCROLL);
		descText.setLayoutData(new GridData(GridData.FILL_BOTH));
		setExampleDescription(descText);
	}

	public void setId(Text id) {
		this.id = id;
	}

	public String getId() {
		return id.getText();
	}

	public void setVersion(Text version) {
		this.version = version;
	}

	public String getVersion() {
		return version.getText();
	}

	public void setExampleName(Text exampleName) {
		this.exampleName = exampleName;
	}

	public String getExampleName() {
		return this.exampleName.getText();
	}

	public void setExampleDescription(Text exampleDescription) {
		this.exampleDescription = exampleDescription;
	}

	public String getExampleDescription() {
		return exampleDescription.getText();
	}

	public void setContact(Text contact) {
		this.contact = contact;
	}

	public String getContact() {
		return contact.getText();
	}
}