package de.cau.cs.kieler.kex.ui.wizards.exporting;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TreeEditor;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
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

	private Tree exampleTree;

	private final int TWO_COLUMNS = 2;
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
		middleGroup.setText("Add Example Resources");
		middleGroup.setLayout(middleLayout);
		createCheckedTree(middleGroup);
		// createTreeElement(middleGroup);

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
	}

	// TODO grï¿½ï¿½e fix machen und mit scrollbar ausstatten, gilt auch fï¿½r
	// import
	// mechanismus.
	private void createTreeElement(Composite composite) {
		exampleTree = new Tree(composite, SWT.BORDER);
		exampleTree.setLayoutData(new GridData(GridData.FILL_BOTH));
		exampleTree.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// updateElements(e.item);
			}

		});
		exampleTree
				.setToolTipText("Use context menu to edit example resources.");
		// Create the editor and set its attributes
		final TreeEditor editor = new TreeEditor(exampleTree);
		editor.horizontalAlignment = SWT.LEFT;
		editor.grabHorizontal = true;

		// Add a key listener to the tree that listens for F2.
		// If F2 is pressed, we do the editing
		exampleTree.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent event) {
				// Make sure one and only one item is selected when F2 is
				// pressed
				if (event.keyCode == SWT.F2
						&& exampleTree.getSelectionCount() == 1) {
					// Determine the item to edit
					final TreeItem item = exampleTree.getSelection()[0];

					// Create a text field to do the editing
					final Text text = new Text(exampleTree, SWT.NONE);
					text.setText(item.getText());
					text.selectAll();
					text.setFocus();

					// If the text field loses focus, set its text into the tree
					// and end the editing session
					text.addFocusListener(new FocusAdapter() {
						@Override
						public void focusLost(FocusEvent event) {
							item.setText(text.getText());
							text.dispose();
						}
					});

					// If they hit Enter, set the text into the tree and end the
					// editing
					// session. If they hit Escape, ignore the text and end the
					// editing
					// session
					text.addKeyListener(new KeyAdapter() {
						@Override
						public void keyPressed(KeyEvent event) {
							switch (event.keyCode) {
							case SWT.CR:
								// Enter hit--set the text into the tree and
								// drop through
								item.setText(text.getText());
							case SWT.ESC:
								// End editing session
								text.dispose();
								break;
							}
						}
					});

					// Set the text field into the editor
					editor.setEditor(text, item);
				}
			}
		});

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
		// TODO muss der user entscheiden wohin das gehen soll, außerdem muss
		// der name der enumeration geändert werden
		return ExportType.EXTENSIONPOINT;
	}
}
