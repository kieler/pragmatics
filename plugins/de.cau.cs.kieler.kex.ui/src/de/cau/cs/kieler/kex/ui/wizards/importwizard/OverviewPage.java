package de.cau.cs.kieler.kex.ui.wizards.importwizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

public class OverviewPage extends WizardPage {

	private Text firstNameText;
	private Text secondNameText;
	private Table addedExamples;

	public OverviewPage(String pageName) {
		super(pageName);
		setTitle("Examples Overview");
		setDescription("Have a look at your added examples!");
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		composite.setLayout(layout);
		setControl(composite);
		addedExamples = new Table(composite, SWT.VIRTUAL | SWT.BORDER);
		addedExamples.setItemCount(10);
		addedExamples.setHeaderVisible(true);
		TableColumn tc1 = new TableColumn(addedExamples, SWT.CENTER);
		TableColumn tc2 = new TableColumn(addedExamples, SWT.CENTER);
		TableColumn tc3 = new TableColumn(addedExamples, SWT.CENTER);
		tc1.setText("Project");
		tc2.setText("Example");
		tc1.setWidth(200);
		tc2.setWidth(200);
		addedExamples.addListener(SWT.SetData, new Listener() {
			public void handleEvent(Event event) {
				TableItem item = (TableItem) event.item;
				item.setText(new String[] { "A", "B" });
			}
		});
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
