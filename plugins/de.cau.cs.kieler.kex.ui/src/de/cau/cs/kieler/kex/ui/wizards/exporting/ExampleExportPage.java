/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
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

/**
 * This class is a wizard page for the export wizard.
 * 
 * @author pkl
 * 
 */
public class ExampleExportPage extends WizardResourceImportPage {

	private Text destPath;

	private Text previewPic;

	private Button createExampleFolder;

	private static final int THREE_COLUMNS = 3;
	private static final int CATEGORY_MINLENGTH = 4;

	private Tree categoryTree;
	private final List<String> checkedCategories;
	private final List<String> creatableCategories;

	private static final String WORKSPACE_DIR = ResourcesPlugin.getWorkspace()
			.getRoot().getLocation().toOSString();

	/**
	 * contstructor for {@link ExampleExportPage}.
	 * 
	 * @param name
	 *            , String
	 * @param selection
	 *            , String
	 */
	protected ExampleExportPage(final String name,
			final IStructuredSelection selection) {
		super(name, selection);
		setTitle(name);
		setDescription("Set destination and preview picture "
				+ "for exported example and determine example cateories.");
		checkedCategories = new ArrayList<String>();
		creatableCategories = new ArrayList<String>();
	}

	@Override
	public void createControl(final Composite parent) {
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
	protected void createSourceGroup(final Composite parent) {
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
	protected void createOptionsGroup(final Composite parent) {
		// no options
	}

	private void createTopGroup(final Composite composite) {

		Group topGroup = new Group(composite, SWT.NONE);
		GridLayout topLayout = new GridLayout();
		topLayout.numColumns = THREE_COLUMNS;
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
			public void widgetSelected(final SelectionEvent event) {
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
		bottomLayout.numColumns = THREE_COLUMNS;
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
		this.previewPic = new Text(bottomGroup, SWT.BORDER);
		previewPic.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		Button browse = new Button(bottomGroup, SWT.NONE);
		browse.setText("Browse...");
		browse.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(final SelectionEvent e) {
				super.widgetSelected(e);
				String pic = picDialog.open();
				if (pic != null) {
					getPreviewPic().setText(pic);
				}
			}

		});

	}

	private void createButtonComposite(final Group middleGroup) {
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
			public void widgetSelected(final SelectionEvent event) {
				IInputValidator validator = new IInputValidator() {
					public String isValid(final String newText) {
						if (newText.length() < CATEGORY_MINLENGTH) {
							return "Category name has to "
									+ "have at least 4 characters.";
						}
						for (TreeItem item : categoryTree.getItems()) {
							if (newText.equals(item.getText())) {
								return "Category exists already! "
										+ "Please enter another name.";
							}
						}
						return null;

					}
				};
				InputDialog dialog = new InputDialog(getShell(),
						"Create New Category", "Please enter a new category.",
						"", validator);
				dialog.open();
				String value = dialog.getValue();
				if (value != null && value.length() >= CATEGORY_MINLENGTH) {
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
			public void widgetSelected(final SelectionEvent e) {
				categoryTree.removeAll();
				fillTree(categoryTree);
				creatableCategories.clear();
				super.widgetSelected(e);
			}
		});

	}

	private void createBottomGroup(final Composite composite) {
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

	private void createCheckedTree(final Composite parent) {
		this.categoryTree = new Tree(parent, SWT.CHECK | SWT.BORDER);
		GridData data = new GridData(GridData.FILL_BOTH);
		categoryTree.setLayoutData(data);
		categoryTree.addListener(SWT.Selection, new Listener() {
			public void handleEvent(final Event event) {
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
					} else {
						checkedCategories.remove(removeCount);
					}
				}
			}

		});
		fillTree(categoryTree);
	}

	/**
	 * Helper method to fill a tree with data.
	 * 
	 * @param tree
	 *            the tree to fill
	 */
	public static void fillTree(final Tree tree) {
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

	/**
	 * Getter for checked categories.
	 * 
	 * @return {@link List} of {@link String}s
	 */
	public List<String> getCheckedCategories() {
		return checkedCategories;
	}

	/**
	 * Getter for destination location.
	 * 
	 * @return String
	 */
	public String getDestLocation() {
		return this.destPath.getText();
	}

	/**
	 * Getter for sourctype.
	 * 
	 * @return {@link SourceType}
	 */
	public SourceType getSourceType() {
		// TODO for extending KEX with database,
		// there have to define a second type
		// and the user has to choose which type it is.
		return SourceType.KIELER;
	}

	/**
	 * Getter for categories that have to be created.
	 * 
	 * @return {@link List} of {@link String}s
	 */
	public List<String> getCreatableCategories() {
		return creatableCategories;
	}

	/**
	 * Getter for preview picture.
	 * 
	 * @return {@link Text}
	 */
	public Text getPreviewPic() {
		return this.previewPic;
	}
}
