package de.cau.cs.kieler.kex.ui.wizards.importing;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.dialogs.WizardResourceImportPage;

import de.cau.cs.kieler.kex.controller.ExampleManager;
import de.cau.cs.kieler.kex.model.Example;

public class ImportExamplePage extends WizardResourceImportPage {

	private Text exampleTitle;

	private Text exampleVersion;

	private Text exampleDescription;

	private Tree exampleTree;

	private Text exampleContact;

	private final String dummyText = "                                                                  ";

	protected List<Example> checkedExamples;

	private static final String EXAMPLE_DATA_KEY = "example";

	private boolean fastStart = false;

	protected ImportExamplePage(String name, IStructuredSelection selection) {
		super(name, selection);
		setTitle(name);
		setDescription("Enter example attributes and destination location.");
		this.checkedExamples = new ArrayList<Example>();
	}

	// TODO Probleme mit der Hintergrund farbe unter ubuntu, k�nnten mit dem
	// set font der elemente geloest werden.

	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout());
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
		setControl(composite);
		createTopGroup(composite);
		createMiddleComponent(composite);
		createBottomComponent(composite);

	}

	private void createTopGroup(Composite composite) {
		Group topGroup = new Group(composite, SWT.NONE);
		topGroup.setLayout(new GridLayout());
		topGroup.setLayoutData(new GridData(GridData.FILL_BOTH));
		topGroup.setText("Set Destination");
		super.createControl(topGroup);

	}

	@Override
	protected void createOptionsGroup(Composite parent) {
		// no options
	}

	private void createMiddleComponent(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		composite.setLayout(layout);
		createTreeElement(composite);
		createExampleGroup(composite);

	}

	private void createBottomComponent(Composite parent) {
		Label descriptionLabel = new Label(parent, SWT.NONE);
		descriptionLabel.setText("Description:");

		this.exampleDescription = new Text(parent, SWT.MULTI | SWT.V_SCROLL
				| SWT.H_SCROLL | SWT.BORDER);
		this.exampleDescription.setEditable(false);
		this.exampleDescription.setText("\n\n\n\n");
		this.exampleDescription.setLayoutData(new GridData(GridData.FILL_BOTH));
	}

	private void createTreeElement(Composite composite) {
		exampleTree = new Tree(composite, SWT.BORDER | SWT.CHECK | SWT.V_SCROLL
				| SWT.H_SCROLL);
		exampleTree.setLayoutData(new GridData(GridData.FILL_BOTH));
		exampleTree.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				updateElements(e.item);
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				updateElements(e.item);
			}
		});
		exampleTree.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				// fill per hand checked elements list
				if (event.detail == SWT.CHECK) {
					TreeItem item = (TreeItem) event.item;
					Object data = item.getData("example");
					// if category is choosen.
					if (data != null) {
						updateCheckedExamples((Example) data);
					} else {
						updateCategoryItems(item);
					}
				}
			}

		});

		initTree(exampleTree);
	}

	private void updateCategoryItems(TreeItem item) {
		boolean checked = item.getChecked();
		boolean isAdded = false;
		for (TreeItem itemElement : item.getItems()) {
			isAdded = false;
			Example example = (Example) itemElement.getData("example");
			for (int i = 0; i < checkedExamples.size(); i++) {
				if (example.equals(checkedExamples.get(i))) {
					isAdded = true;
				}
			}
			if (checked && !isAdded)
				checkedExamples.add(example);
			if (!checked && isAdded)
				checkedExamples.remove(example);
			itemElement.setChecked(checked);
		}
	}

	/**
	 * Updates checked elements list, if contains element, remove it otherwise
	 * add it.
	 * 
	 * @param example
	 */
	private void updateCheckedExamples(Example example) {
		int removeCount = -1;
		for (int i = 0; i < checkedExamples.size(); i++) {
			if (example.equals(checkedExamples.get(i))) {
				removeCount = i;
				break;
			}
		}
		if (removeCount == -1) {
			checkedExamples.add(example);

		} else {
			checkedExamples.remove(removeCount);

		}
	}

	private void initTree(Tree tree) {
		List<String> categories = ExampleManager.get().getCategories();
		for (int i = 0; i < categories.size(); i++) {
			TreeItem iItem = new TreeItem(tree, SWT.CHECK);
			iItem.setText(categories.get(i));
			addExamplesToItem(categories.get(i), iItem);
		}
	}

	private void addExamplesToItem(String category, TreeItem tItem) {
		for (Example example : ExampleManager.get().getExamples().values()) {
			if (example.contains(category)) {
				TreeItem item = new TreeItem(tItem, SWT.NONE);
				item.setText(example.getTitle());
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
		new Label(exampleGroup, SWT.NONE).setText("Title:");
		Text text = new Text(exampleGroup, SWT.NONE);
		text.setText(this.dummyText);
		this.setExampleTitle(text);
		this.getExampleTitle().setEditable(false);
		this.getExampleTitle().setLayoutData(
				new GridData(GridData.FILL_HORIZONTAL));
		createEmptyLine(exampleGroup, columnCount);
		new Label(exampleGroup, SWT.NONE).setText("Version:");
		this.exampleVersion = new Text(exampleGroup, SWT.NONE);
		this.exampleVersion.setEditable(false);
		this.exampleVersion
				.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		createEmptyLine(exampleGroup, columnCount);
		new Label(exampleGroup, SWT.NONE).setText("Contact:");
		this.exampleContact = new Text(exampleGroup, SWT.NONE);
		this.exampleContact.setEditable(false);
		this.exampleContact
				.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		createEmptyLine(exampleGroup, columnCount);
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
				getExampleTitle().setText(selectedExample.getTitle());
				getExampleDescription().setText(
						selectedExample.getDescription());
				String contact = selectedExample.getContact();
				if (contact != null)
					getExampleContact().setText(contact);
				getExampleVersion().setText(
						selectedExample.getVersion().toString());
			}
		}
	}

	public List<Example> getCheckedExamples() {
		return this.checkedExamples;
	}

	public Text getExampleDescription() {
		return exampleDescription;
	}

	public Text getExampleVersion() {
		return exampleVersion;
	}

	public void setExampleContact(Text exampleContact) {
		this.exampleContact = exampleContact;
	}

	public Text getExampleContact() {
		return exampleContact;
	}

	public void setExampleTitle(Text exampleTitle) {
		this.exampleTitle = exampleTitle;
	}

	public Text getExampleTitle() {
		return exampleTitle;
	}

	@Override
	protected void createSourceGroup(Composite parent) {
		// no sourceGroup
	}

	@Override
	protected ITreeContentProvider getFileProvider() {
		return null;
	}

	@Override
	protected ITreeContentProvider getFolderProvider() {
		return null;
	}

	public IPath getContainerPath() {
		return super.getContainerFullPath();
	}

	public boolean isFastStart() {
		boolean result = super.getContainerFullPath() == null
				&& checkedExamples.size() == 0;
		this.fastStart = result;
		return result;
	}
}
