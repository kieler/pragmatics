package de.cau.cs.kieler.kex.ui.wizards.exporting;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class ExampleAttributesPage extends WizardPage {

	private Text exampleTitle;

	private Text author;

	private Text contact;

	private Text exampleDescription;

	public ExampleAttributesPage(String pageName, IStructuredSelection selection) {
		super(pageName);
		setTitle(pageName);
		setDescription("Please enter example attributes.");

	}

	/**
	 * creates the control composite and adds attributes field for it.
	 * 
	 * @param parent
	 *            , parent composite
	 */
	public void createControl(Composite parent) {

		Composite composite = new Composite(parent, SWT.BORDER);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		composite.setLayout(layout);
		setControl(composite);
		addAttributeFields(composite);
	}

	private void addAttributeFields(Composite composite) {
		composite.setLayoutData(new GridData(GridData.FILL_VERTICAL));
		new Label(composite, SWT.NONE).setText("Title:");
		exampleTitle = new Text(composite, SWT.BORDER);
		exampleTitle.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		new Label(composite, SWT.NONE).setText("Author:");
		author = new Text(composite, SWT.BORDER);
		author.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		new Label(composite, SWT.NONE).setText("Contact:");
		contact = new Text(composite, SWT.BORDER);
		contact.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		new Label(composite, SWT.NONE).setText("Description:");
		exampleDescription = new Text(composite, SWT.BORDER | SWT.MULTI
				| SWT.V_SCROLL | SWT.H_SCROLL);
		exampleDescription.setLayoutData(new GridData(GridData.FILL_BOTH));

	}

	public void setExampleTitle(Text exampleTitle) {
		this.exampleTitle = exampleTitle;
	}

	public String getExampleTitle() {
		return exampleTitle.getText();
	}

	public void setAuthor(Text author) {
		this.author = author;
	}

	public String getAuthor() {
		return this.author.getText();
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