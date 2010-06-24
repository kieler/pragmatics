package de.cau.cs.kieler.kex.ui.wizards.importwizard;

import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

public class OverviewPage extends WizardPage{

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
          new Label(composite,SWT.NONE).setText("First Name");
          setFirstNameText(new Text(composite,SWT.NONE));
          new Label(composite,SWT.NONE).setText("Last Name");
          setSecondNameText(new Text(composite,SWT.NONE));
          new Label(composite,SWT.CENTER).setText("added Examples");
          
          
          addedExamples = new Table(composite, SWT.CHECK);

          // Although this table column is not needed, and can cause resize problems,
          // it can't be removed since this would be a breaking change against R1.0.
          // See bug 6643 for more details.
          new TableColumn(addedExamples, SWT.NONE);
          TableLayout tablelayout = new TableLayout();
          tablelayout.addColumnData(new ColumnWeightData(100));
          addedExamples.setLayout(tablelayout);

    
          
//          Table table = new Table(composite, SWT.NONE);	
//          TableColumn tc1 = new TableColumn(table, SWT.CENTER);
//          TableColumn tc2 = new TableColumn(table, SWT.CENTER);
//          TableColumn tc3 = new TableColumn(table, SWT.CENTER);
//          tc1.set("First Name");
//          tc2.setText("Last Name");
//          tc3.setText("Address");
//          tc1.setWidth(70);
//          tc2.setWidth(70);
//          tc3.setWidth(80);
          addedExamples.setHeaderVisible(true);

//          TableItem item1 = new TableItem(t, SWT.NONE);
//          item1.setText(new String[] { "A", "B", "Address 1" });
//          TableItem item2 = new TableItem(t, SWT.NONE);
//          item2.setText(new String[] { "C", "D", "Address 2" });
//          TableItem item3 = new TableItem(t, SWT.NONE);
//          item3.setText(new String[] { "E", "F", "Address 3" });
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
