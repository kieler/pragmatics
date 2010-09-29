package de.cau.cs.kieler.kex.ui.wizards.exporting;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.dialogs.WizardResourceImportPage;

import de.cau.cs.kieler.kex.controller.ExampleManager;
import de.cau.cs.kieler.kex.model.SourceType;

public class ExampleExportPage extends WizardResourceImportPage {

	private Text destPath;

	private Text overviewPic;

	private Button createExampleFolder;

	private final int THREE_COLUMNS = 3;

	private Tree categoryTree;
	private final List<String> checkedCategories;
	private final List<String> creatableCategories;

	private final String WORKSPACE_DIR = ResourcesPlugin.getWorkspace()
			.getRoot().getLocation().toOSString();

	protected ExampleExportPage(String name, IStructuredSelection selection) {
		super(name, selection);
		setTitle(name);
		setDescription("Set destination and preview picture for exported example and determine example cateories.");
		checkedCategories = new ArrayList<String>();
		creatableCategories = new ArrayList<String>();
	}

	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout());
		setControl(composite);

		createTopGroup(composite);
		createMiddleGroup(composite);
		createBottomGroup(composite);
	}

	@Override
	public boolean isPageComplete() {
		return true;
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

	@Override
	protected void createOptionsGroup(Composite parent) {
		// no options
	}

	private void createTopGroup(final Composite composite) {

		Group topGroup = new Group(composite, SWT.NONE);
		GridLayout topLayout = new GridLayout();
		topLayout.numColumns = this.THREE_COLUMNS;
		topGroup.setLayout(topLayout);
		topGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		topGroup.setText("Set Example Destination");
		Label destLabel = new Label(topGroup, SWT.NONE);
		destLabel.setText("To Directory:");
		this.destPath = new Text(topGroup, SWT.BORDER);
		this.destPath.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		Button addDestPath = new Button(topGroup, SWT.NONE);
		addDestPath.setText("Browse...");

		addDestPath.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				DirectoryDialog dirDiag = new DirectoryDialog(composite
						.getShell());

				dirDiag.setText("Choose destination directory");
				dirDiag.setMessage("Select a directory in a java plugin project.");
				dirDiag.setFilterPath(WORKSPACE_DIR);
				String dir = dirDiag.open();
				if (dir != null) {
					destPath.setText(dir);
				}
			}
		});
	}

	private void createMiddleGroup(final Composite composite) {
		Group bottomGroup = new Group(composite, SWT.NONE);
		GridLayout bottomLayout = new GridLayout();
		bottomLayout.numColumns = 3;
		bottomGroup.setText("Set Preview Picture");
		bottomGroup
				.setToolTipText("Enter a picture like a screenshot of example diagram.");
		bottomGroup.setLayout(bottomLayout);
		bottomGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		final FileDialog picDialog = new FileDialog(bottomGroup.getShell());
		// ResourcesPlugin.getWorkspace().getRoot(), "Search a picture!"
		picDialog.setFilterPath(WORKSPACE_DIR);
		String[] extensions = { "*.png", "*.jpg", "*.gif", "*.PNG", "*.bmp" };
		picDialog.setFilterExtensions(extensions);
		Label label = new Label(bottomGroup, SWT.NONE);
		label.setText("Set Picture:");
		this.overviewPic = new Text(bottomGroup, SWT.BORDER);
		overviewPic.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		Button browse = new Button(bottomGroup, SWT.NONE);
		browse.setText("Browse...");
		browse.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				String pic = picDialog.open();
				if (pic != null) {
					getOverviewPic().setText(pic);
				}
			}

		});

	}

	private void createButtonComposite(Group middleGroup) {
		Composite buttonCompo = new Composite(middleGroup, SWT.NONE);
		GridLayout buttonCompoLayout = new GridLayout();
		buttonCompoLayout.numColumns = THREE_COLUMNS;
		buttonCompo.setLayout(buttonCompoLayout);
		buttonCompo.setLayoutData(new GridData(GridData.FILL_BOTH));
		Button addCategory = new Button(buttonCompo, SWT.NONE);
		addCategory.setText("New...");
		// addCategory.setToolTipText("Creates a new Category");
		// FIXME sch�ner noch mit dem tree editing mechanismus.
		addCategory.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent event) {
				IInputValidator validator = new IInputValidator() {
					public String isValid(String newText) {
						if (newText.length() < 4) {
							return "Category name has to have at least 4 characters.";
						}
						for (TreeItem item : categoryTree.getItems()) {
							if (newText.equals(item.getText()))
								return "Category exists already! Please enter another name.";
						}
						return null;

					}
				};
				InputDialog dialog = new InputDialog(getShell(),
						"Create New Category", "Please enter a new category.",
						"", validator);
				dialog.open();
				String value = dialog.getValue();
				if (value != null && value.length() > 3) {
					TreeItem item = new TreeItem(categoryTree, SWT.NONE);
					item.setText(value);
					creatableCategories.add(value);
				}
			}
		});

		Button revertTree = new Button(buttonCompo, SWT.NONE);
		revertTree.setText("Revert");
		revertTree.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				categoryTree.removeAll();
				fillTree(categoryTree);
				creatableCategories.clear();
				super.widgetSelected(e);
			}
		});

	}

	private void createBottomGroup(Composite composite) {
		Group middleGroup = new Group(composite, SWT.NONE);
		GridLayout middleLayout = new GridLayout();
		middleGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		middleLayout.numColumns = 1;
		middleGroup.setText("Add Example Categories");
		middleGroup.setToolTipText("Please select one or more cateogies.");
		middleGroup.setLayout(middleLayout);
		createCheckedTree(middleGroup);
		createButtonComposite(middleGroup);

	}

	private void createCheckedTree(Composite parent) {
		this.categoryTree = new Tree(parent, SWT.CHECK | SWT.BORDER);
		GridData data = new GridData(GridData.FILL_BOTH);
		categoryTree.setLayoutData(data);
		categoryTree.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				// fill per hand checked elements list
				if (event.detail == SWT.CHECK) {
					int removeCount = -1;
					String category = ((TreeItem) event.item).getText();
					for (int i = 0; i < checkedCategories.size(); i++) {
						if (category.equals(checkedCategories.get(i))) {
							removeCount = i;
							break;
						}
					}

					if (removeCount == -1) {
						checkedCategories.add(category);
					} else
						checkedCategories.remove(removeCount);
				}
			}

		});
		fillTree(categoryTree);
	}

	/**
	 * Helper method to fill a tree with data
	 * 
	 * @param tree
	 *            the tree to fill
	 */
	public static void fillTree(Tree tree) {
		// disable drawing to avoid flicker
		tree.setRedraw(false);
		List<String> categories = ExampleManager.get().getCategories();
		for (String category : categories) {
			TreeItem item = new TreeItem(tree, SWT.NONE);
			item.setText(category);
		}
		// enable drawing
		tree.setRedraw(true);
	}

	public List<String> getCheckedCategories() {
		return checkedCategories;
	}

	public String getDestLocation() {
		return this.destPath.getText();
	}

	public SourceType getSourceType() {
		// TODO for extending KEX with database,
		// there have to define a second type
		// and the user has to choose which type it is.
		return SourceType.KIELER;
	}

	public List<String> getCreatableCategories() {
		return creatableCategories;
	}

	public boolean createExampleFolder() {
		return this.createExampleFolder.getSelection();
	}

	public Text getOverviewPic() {
		return this.overviewPic;
	}
}
