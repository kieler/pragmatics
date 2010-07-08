package de.cau.cs.kieler.kex.ui.wizards.exportwizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class ExamplePage extends WizardPage {

	private String projectId;

	private String location;

	private Text id;
	// TODO Textarea waere besser geeignet.
	private Text exampleName;

	private Text version;

	private StyledText exampleDescription;

	private Text contact;

	// TODO localprojects ueber den store laden... falls es keinen mechanismus
	// gibt
	public ExamplePage(String pageName) {
		super(pageName);
		setTitle("Example Export");
		setDescription("Please enter example attributes");

	}

	public void createControl(Composite parent) {

		// TODO projectId und location noch zusammenbauen
		// sch�nen mechanismus �berlegen

		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		composite.setLayout(layout);
		setControl(composite);

		new Label(composite, SWT.NONE).setText("ID");
		setId(new Text(composite, SWT.NONE));

		new Label(composite, SWT.NONE).setText("Name");
		setExampleName(new Text(composite, SWT.NONE));

		new Label(composite, SWT.NONE).setText("Version");
		Text text = new Text(composite, SWT.NONE);
		text.setText("1.0.0");
		setVersion(text);

		// new Label(composite, SWT.NONE).setText("Description");
		// setExampleDescription(new StyledText(parent, SWT.BORDER | SWT.MULTI
		// | SWT.V_SCROLL | SWT.H_SCROLL));
		// exampleDescription.setLayoutData(new GridData(GridData.FILL_BOTH
		// | GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL));
		// exampleDescription.addKeyListener(new KeyAdapter() {
		// @Override
		// public void keyPressed(KeyEvent e) {
		// super.keyPressed(e);
		//
		// }
		// });

		new Label(composite, SWT.NONE).setText("Contact");
		setContact(new Text(composite, SWT.NONE));

		// newResourcePage = new ProjectImportPage("projectImportPage");

		// Control control = newResourcePage.createControl(parent);
		// getWorkbench().getHelpSystem().setHelp(control,
		// IWorkbenchHelpContextIds.NEW_WIZARD_SELECTION_WIZARD_PAGE);
		// setControl(control);

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

	public void setExampleDescription(StyledText exampleDescription) {
		this.exampleDescription = exampleDescription;
	}

	public String getExampleDescription() {
		return exampleDescription.getText();
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLocation() {
		return location;
	}

	public void setContact(Text contact) {
		this.contact = contact;
	}

	public String getContact() {
		return contact.getText();
	}
}