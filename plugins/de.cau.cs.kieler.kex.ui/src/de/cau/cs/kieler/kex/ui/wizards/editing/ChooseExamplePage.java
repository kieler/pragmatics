package de.cau.cs.kieler.kex.ui.wizards.editing;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import de.cau.cs.kieler.kex.model.Example;

public class ChooseExamplePage extends WizardPage {

	private Text exampleTitle;

	private Text exampleVersion;

	private Text exampleDescription;

	private Tree exampleTree;

	private Text exampleContact;

	private final String dummyText = "                                                                  ";

	private static final String EXAMPLE_DATA_KEY = "example";

	protected ChooseExamplePage(String pageName) {
		super(pageName);
		this.setDescription("Choose a example to update or delete.");
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout());
		// createMiddleComponent(composite);
		setControl(composite);
	}

	// private void createMiddleComponent(Composite parent) {
	// Composite composite = new Composite(parent, SWT.NONE);
	// GridLayout layout = new GridLayout();
	// layout.numColumns = 2;
	// composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	// composite.setLayout(layout);
	// createTreeElement(composite);
	// createExampleGroup(composite);
	// }

	// private void createButtonComposite(Composite composite) {
	// Button updateButton = new Button(composite, SWT.NONE);
	// updateButton.setText("Update");
	// Button deleteButton = new Button(composite, SWT.NONE);
	// deleteButton.setText("Delete");
	// }

	// private void createTreeElement(Composite composite) {
	// // Composite treeComp = new Composite(composite, SWT.NONE);
	// // GridLayout layout = new GridLayout();
	// // layout.numColumns = 2;
	//
	// exampleTree = new Tree(composite, SWT.BORDER | SWT.V_SCROLL
	// | SWT.H_SCROLL);
	// exampleTree.setLayoutData(new GridData(GridData.FILL_BOTH));
	// exampleTree.addSelectionListener(new SelectionListener() {
	// public void widgetSelected(SelectionEvent e) {
	// updateElements(e.item);
	// }
	//
	// public void widgetDefaultSelected(SelectionEvent e) {
	// updateElements(e.item);
	// }
	// });
	// initTree(exampleTree);
	// // createButtonComposite(treeComp);
	// }
	//
	// private void initTree(Tree tree) {
	// List<String> categories = ExampleManager.get().getCategories();
	// for (int i = 0; i < categories.size(); i++) {
	// TreeItem iItem = new TreeItem(tree, SWT.NONE);
	// iItem.setText(categories.get(i));
	// addExamplesToItem(categories.get(i), iItem);
	// }
	// }

	// private void addExamplesToItem(String category, TreeItem tItem) {
	// for (Example example : ExampleManager.get().getExamples().values()) {
	// if (example.contains(category)) {
	// TreeItem item = new TreeItem(tItem, SWT.NONE);
	// item.setText(example.getName());
	// item.setData("example", example);
	// }
	// }
	// }

	// private void createExampleGroup(Composite composite) {
	// int columnCount = 2;
	// Group exampleGroup = new Group(composite, SWT.NONE);
	// GridLayout layout = new GridLayout();
	// layout.numColumns = columnCount;
	// exampleGroup.setLayout(layout);
	// exampleGroup.setLayoutData(new GridData(GridData.FILL_BOTH));
	// exampleGroup.setText("Selected Example");
	// exampleGroup.setFont(composite.getFont());
	// exampleGroup.setToolTipText("Selected Example");
	// createEmptyLine(exampleGroup, columnCount);
	// new Label(exampleGroup, SWT.NONE).setText("Title:");
	// Text text = new Text(exampleGroup, SWT.NONE);
	// text.setText(this.dummyText);
	// // text.setTabs(16);
	// this.exampleTitle = text;
	// this.exampleTitle.setEditable(false);
	// this.exampleTitle.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	// createEmptyLine(exampleGroup, columnCount);
	// new Label(exampleGroup, SWT.NONE).setText("Version:");
	// this.exampleVersion = new Text(exampleGroup, SWT.NONE);
	// this.exampleVersion.setEditable(false);
	// this.exampleVersion
	// .setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	// createEmptyLine(exampleGroup, columnCount);
	// new Label(exampleGroup, SWT.NONE).setText("Contact:");
	// this.exampleContact = new Text(exampleGroup, SWT.NONE);
	// this.exampleContact.setEditable(false);
	// this.exampleContact
	// .setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	// createEmptyLine(exampleGroup, columnCount);
	// Label descriptionLabel = new Label(exampleGroup, SWT.NONE);
	// descriptionLabel.setText("Description:");
	// this.exampleDescription = new Text(exampleGroup, SWT.MULTI
	// | SWT.V_SCROLL | SWT.H_SCROLL | SWT.BORDER);
	// this.exampleDescription.setEditable(false);
	// this.exampleDescription.setText("\n\n\n\n");
	// this.exampleDescription.setLayoutData(new GridData(GridData.FILL_BOTH));
	//
	// }

	// private void createEmptyLine(Composite composite, int columnCount) {
	// for (int i = 0; i < columnCount; i++)
	// new Label(composite, SWT.NONE);
	// }

	// private void updateElements(Widget widget) {
	// TreeItem selected = (TreeItem) widget;
	// if (selected.getParentItem() != null) {
	// Object data = selected.getData(EXAMPLE_DATA_KEY);
	// if (data instanceof Example) {
	// Example selectedExample = (Example) data;
	// this.exampleTitle.setText(selectedExample.getId());
	// this.exampleDescription.setText(selectedExample
	// .getDescription());
	// String contact = selectedExample.getContact();
	// if (contact != null)
	// this.exampleContact.setText(contact);
	// this.exampleVersion.setText(selectedExample.getVersion()
	// .toString());
	// }
	// }
	// }

	public List<Example> getSelectedExamples() {
		List<Example> result = new ArrayList<Example>();
		for (TreeItem item : exampleTree.getSelection()) {
			Object data = item.getData("example");
			// if category is choosen.
			if (data != null) {
				result.add((Example) data);
			} else {
				for (TreeItem itemElement : item.getItems()) {
					result.add((Example) itemElement.getData("example"));
				}
			}
		}
		return result;
	}

}
