package de.cau.cs.kieler.kex.ui.wizards.importing.overviewWizard;

import java.util.List;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import de.cau.cs.kieler.kex.controller.ExampleManager;
import de.cau.cs.kieler.kex.model.Example;

public class OverviewPage extends WizardPage {

	private Button importExample;
	private Button updateExample;
	private Button deleteExample;
	private Table addedExamples;

	private static final int tableColumnWidth = 200;

	public OverviewPage(String pageName) {
		super(pageName);
		setTitle("Examples Overview");
		setDescription("Have a look at your imported examples!");
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.CHECK);
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		composite.setLayout(layout);
		setControl(composite);
		// TODO set table borders fix
		addedExamples = new Table(composite, SWT.VIRTUAL | SWT.BORDER);
		addedExamples.setHeaderVisible(true);
		addedExamples.setLayoutData(new GridData(GridData.GRAB_VERTICAL));
		TableColumn tc1 = new TableColumn(addedExamples, SWT.CENTER);
		TableColumn tc2 = new TableColumn(addedExamples, SWT.CENTER);
		tc1.setText("Project");
		tc2.setText("Example");
		tc1.setWidth(tableColumnWidth);
		tc2.setWidth(tableColumnWidth);
		List<Example> examples = ExampleManager.get().getImportedExamples();
		for (int i = 0; i < 100; i++) {
			for (Example example : examples) {
				TableItem item = new TableItem(addedExamples, SWT.NONE);
				item.setText(new String[] { "Project" + (i + 1),
						example.getName() });
			}
		}
		//
		// addedExamples.addListener(SWT.SetData, new Listener() {
		// public void handleEvent(Event event) {
		// // TODO zwischen view und controller so wenig bruecken wie
		// // moeglich schlagen, d.h. am besten greift nur eine klasse
		// // auf die methoden des controllers zu. (Store oder Wizard)
		//
		// List<Example> examples = ExampleManager.get()
		// .getImportedExamples();
		// for (Example example : examples) {
		// TableItem item = (TableItem) event.item;
		// item.setText(new String[] { "A", example.getName() });
		// }
		// }
		// });
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
