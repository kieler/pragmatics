package de.cau.cs.kieler.kex.ui.wizards.importwizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class OverviewPage extends WizardPage {

	private Button importExample;
	private Button updateExample;
	private Button deleteExample;
	private Table addedExamples;

	public OverviewPage(String pageName) {
		super(pageName);
		setTitle("Examples Overview");
		setDescription("Have a look at your added examples!");
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.CHECK);
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		composite.setLayout(layout);
		setControl(composite);

		addedExamples = new Table(composite, SWT.VIRTUAL | SWT.BORDER);
		addedExamples.setItemCount(10);
		addedExamples.setHeaderVisible(true);
		TableColumn tc1 = new TableColumn(addedExamples, SWT.CENTER);
		TableColumn tc2 = new TableColumn(addedExamples, SWT.CENTER);
		tc1.setText("Project");
		tc2.setText("Example");
		tc1.setWidth(200);
		tc2.setWidth(200);
		addedExamples.addListener(SWT.SetData, new Listener() {
			public void handleEvent(Event event) {
				// TODO zwischen view und controller so wenig bruecken wie
				// moeglich schlagen, d.h. am besten greift nur eine klasse
				// auf die methoden des controllers zu. (Store oder Wizard)

				TableItem item = (TableItem) event.item;
				item.setText(new String[] { "A", "B" });
			}
		});
		Composite buttonArea = new Composite(composite, SWT.NONE | SWT.CHECK);
		GridLayout bALayout = new GridLayout();
		bALayout.numColumns = 3;
		buttonArea.setLayout(bALayout);

		// TODO zu dem Wizard koennte man auch eine rechts neben der table
		// eine kurze beschreibung des selektierten beispiels angeben;
		// drueber nachdenken

		importExample = new Button(buttonArea, SWT.PUSH);
		importExample.setText("Import Example ");
		updateExample = new Button(buttonArea, SWT.PUSH);
		updateExample.setText("Update Example");
		updateExample.setGrayed(true);
		deleteExample = new Button(buttonArea, SWT.PUSH);
		deleteExample.setText("Delete Example ");
		updateExample.setGrayed(true);

	}
}
