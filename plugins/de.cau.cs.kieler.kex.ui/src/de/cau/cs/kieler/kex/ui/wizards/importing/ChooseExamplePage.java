package de.cau.cs.kieler.kex.ui.wizards.importing;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;

import de.cau.cs.kieler.kex.controller.ExampleManager;
import de.cau.cs.kieler.kex.model.Example;

public class ChooseExamplePage extends WizardPage {

	private Text exampleId;

	private Text exampleName;

	private Text exampleVersion;

	private Text exampleDescription;

	private Tree exampleTree;

	private static final String EXAMPLE_DATA_KEY = "example";

	public ChooseExamplePage(String pageName) {
		super(pageName);
		setTitle("Import Example");
		setDescription("Please choose an example.");
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		composite.setLayout(layout);
		setControl(composite);
		createTreeElement(composite);
		createExampleGroup(composite);
	}

	private void createTreeElement(Composite composite) {
		exampleTree = new Tree(composite, SWT.BORDER);
		exampleTree.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_FILL
				| GridData.GRAB_VERTICAL));
		exampleTree.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				updateElements(e.item);
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				updateElements(e.item);
			}
		});
		initTree(exampleTree);
	}

	private void initTree(Tree tree) {
		List<String> categories = ExampleManager.get().getCategories();
		for (int i = 0; i < categories.size(); i++) {
			TreeItem iItem = new TreeItem(tree, SWT.NONE);
			iItem.setText(categories.get(i));
			addExamplesToItem(categories.get(i), iItem);
		}
	}

	// TODO max performance rausholen... vielleicht schon ein tree im controller
	// speichern... oder die map so nicht bestehen lassen...
	private void addExamplesToItem(String category, TreeItem tItem) {
		for (Example example : ExampleManager.get().getExamples().values()) {
			if (example.contains(category)) {
				TreeItem item = new TreeItem(tItem, SWT.NONE);
				item.setText(example.getName());
				item.setData("example", example);
			}
		}
	}

	private void createExampleGroup(Composite composite) {
		int columnCount = 2;
		Group exampleGroup = new Group(composite, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = columnCount;
		exampleGroup.setLayout(layout);
		exampleGroup.setLayoutData(new GridData(GridData.FILL_BOTH));
		exampleGroup.setText("Selected Example");
		exampleGroup.setFont(composite.getFont());
		exampleGroup.setToolTipText("Selected Example");
		createEmptyLine(exampleGroup, columnCount);
		new Label(exampleGroup, SWT.NONE).setText("Id:");
		this.exampleId = new Text(exampleGroup, SWT.NONE);
		this.exampleId.setEditable(false);
		this.exampleId.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		createEmptyLine(exampleGroup, columnCount);
		new Label(exampleGroup, SWT.NONE).setText("Name:");
		this.exampleName = new Text(exampleGroup, SWT.NONE);
		this.exampleName.setEditable(false);
		this.exampleName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		createEmptyLine(exampleGroup, columnCount);
		new Label(exampleGroup, SWT.NONE).setText("Version:");
		this.exampleVersion = new Text(exampleGroup, SWT.NONE);
		this.exampleVersion.setEditable(false);
		this.exampleVersion
				.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		createEmptyLine(exampleGroup, columnCount);
		new Label(exampleGroup, SWT.None).setText("Description:");
		this.exampleDescription = new Text(exampleGroup, SWT.NONE);
		this.exampleDescription.setEditable(false);
		this.exampleDescription.setLayoutData(new GridData(
				GridData.FILL_HORIZONTAL));
	}

	private void createEmptyLine(Composite composite, int columnCount) {
		for (int i = 0; i < columnCount; i++)
			new Label(composite, SWT.NONE);
	}

	private void updateElements(Widget widget) {
		TreeItem selected = (TreeItem) widget;
		if (selected.getParentItem() != null) {
			Object data = selected.getData(EXAMPLE_DATA_KEY);
			if (data instanceof Example) {
				Example selectedExample = (Example) data;
				getExampleId().setText(selectedExample.getId());
				getExampleName().setText(selectedExample.getName());
				getExampleDescription().setText(
						selectedExample.getDescription());
				getExampleVersion().setText(
						selectedExample.getVersion().toString());
			}
		}
	}

	public void setExampleId(Text exampleId) {
		this.exampleId = exampleId;
	}

	public Text getExampleId() {
		return exampleId;
	}

	public void setExampleDescription(Text exampleDescription) {
		this.exampleDescription = exampleDescription;
	}

	public Text getExampleDescription() {
		return exampleDescription;
	}

	public void setExampleVersion(Text version) {
		this.exampleVersion = version;
	}

	public Text getExampleVersion() {
		return exampleVersion;
	}

	public void setExampleName(Text exampleName) {
		this.exampleName = exampleName;
	}

	public Text getExampleName() {
		return exampleName;
	}

	public List<Example> getSelectedExamples() {
		List<Example> result = new ArrayList<Example>();
		for (TreeItem item : exampleTree.getSelection()) {
			result.add((Example) item.getData("example"));
		}
		return result;
	}
}
