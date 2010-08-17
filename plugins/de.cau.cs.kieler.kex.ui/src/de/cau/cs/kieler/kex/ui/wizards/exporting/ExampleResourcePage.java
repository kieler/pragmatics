package de.cau.cs.kieler.kex.ui.wizards.exporting;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import de.cau.cs.kieler.kex.controller.ExampleManager;
import de.cau.cs.kieler.kex.model.ExportType;

public class ExampleResourcePage extends WizardPage {

	private Text destPath;

	private final int THREE_COLUMNS = 3;
	private List<URL> resources;

	private Tree categoryTree;

	protected ExampleResourcePage(String pageName) {
		super(pageName);
		setTitle("Destination Choice");
		setDescription("Set destination for exported Example and determine Example Resources.");
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout());
		setControl(composite);

		createTopGroup(composite);
		createMiddleGroup(composite);
		createBottomGroup(composite);
	}

	private void createTopGroup(final Composite composite) {

		Group topGroup = new Group(composite, SWT.NONE);
		GridLayout topLayout = new GridLayout();
		topLayout.numColumns = this.THREE_COLUMNS;
		topGroup.setLayout(topLayout);
		topGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		topGroup.setText("Set Example Destination");
		Label destLabel = new Label(topGroup, SWT.NONE);
		destLabel.setText("To directory:");
		this.destPath = new Text(topGroup, SWT.BORDER);
		this.destPath.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		Button addDestPath = new Button(topGroup, SWT.NONE);
		addDestPath.setText("Add...");

		addDestPath.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				DirectoryDialog dirDiag = new DirectoryDialog(composite
						.getShell());

				dirDiag.setText("Destination directory choice");
				dirDiag.setMessage("Select a directory in java project with existing plugin.xml.");
				String dir = dirDiag.open();
				// TODO ueberlegen, ob hier direkt eine pruefung eingebaut
				// werden kann.
				if (dir != null) {
					destPath.setText(dir);
				}
			}
		});
	}

	private void createMiddleGroup(Composite composite) {
		Group middleGroup = new Group(composite, SWT.NONE);
		GridLayout middleLayout = new GridLayout();
		middleGroup.setLayoutData(new GridData(GridData.FILL_BOTH));
		middleLayout.numColumns = 2;
		middleGroup.setText("Add Example Category");
		middleGroup.setToolTipText("There could be more selected than one.");
		middleGroup.setLayout(middleLayout);
		createCheckedTree(middleGroup);

	}

	private void createBottomGroup(Composite composite) {
		// TODO think about: exampleFolderButton kann ich glaube ich nicht
		// machen, nachdenken was damit passiert bis zum import
		Group bottomGroup = new Group(composite, SWT.NONE);
		GridLayout bottomLayout = new GridLayout();
		bottomLayout.numColumns = 1;
		bottomGroup.setText("Options");
		bottomGroup.setLayout(bottomLayout);
		bottomGroup.setLayoutData(new GridData(GridData.FILL_BOTH));
		Button exampleFolderButton = new Button(bottomGroup, SWT.CHECK);
		exampleFolderButton.setText("create folder with example name");
		exampleFolderButton.setSelection(true);
		Button copyHiddenFilesButton = new Button(bottomGroup, SWT.CHECK);
		copyHiddenFilesButton.setText("copy hidden files");

	}

	void createCheckedTree(Composite parent) {
		this.categoryTree = new Tree(parent, SWT.CHECK | SWT.BORDER);
		GridData data = new GridData(GridData.FILL_BOTH);
		categoryTree.setLayoutData(data);
		fillTree(categoryTree);

	}

	/**
	 * Helper method to fill a tree with data
	 * 
	 * @param tree
	 *            the tree to fill
	 */
	private void fillTree(Tree tree) {
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

	public List<String> getCategories() {
		List<String> result = new ArrayList<String>();
		for (TreeItem item : this.categoryTree.getSelection()) {
			result.add(item.getText());
		}
		return result;
	}

	public String getDestLocation() {
		return this.destPath.getText();
	}

	public List<URL> getExampleResources() {
		return this.resources;
	}

	public ExportType getExportType() {
		// TODO muss der user entscheiden wohin das gehen soll, auﬂerdem muss
		// der name der enumeration ge‰ndert werden
		return ExportType.EXTENSIONPOINT;
	}

	@Override
	public boolean isPageComplete() {
		return true;
	};
}
