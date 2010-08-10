package de.cau.cs.kieler.kex.ui.wizards.exporting;

import java.util.Arrays;
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
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import de.cau.cs.kieler.kex.controller.ExampleManager;
import de.cau.cs.kieler.kex.model.ExampleResource;

public class ExampleResourcePage extends WizardPage {

	private Text destPath;

	private Button addDestPath;

	private Tree exampleTree;

	private final int COLUMNCOUNT = 2;

	private List<ExampleResource> exampleResources;

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
		topLayout.numColumns = this.COLUMNCOUNT;
		topGroup.setLayout(topLayout);
		topGroup.setLayoutData(new GridData(GridData.FILL_BOTH));
		topGroup.setText("Set Example Destination");
		this.destPath = new Text(topGroup, SWT.BORDER);
		this.destPath.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		this.addDestPath = new Button(topGroup, SWT.NONE);
		this.addDestPath.setText("Add...");

		this.addDestPath.addSelectionListener(new SelectionAdapter() {
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
		createTreeElement(middleGroup);
		createExReComposite(middleGroup);
		createTreeButtons(middleGroup);

	}

	private void createTreeButtons(Group middleGroup) {
		Composite buttonComposite = new Composite(middleGroup, SWT.NONE);
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 5;
		buttonComposite.setLayout(gridLayout);

		Button newExRe = new Button(buttonComposite, SWT.NONE);
		newExRe.setText("New");
		this.addDestPath.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// add new exampleResource to tree
			}
		});
		newExRe.setToolTipText("Add new Example Resource");
		Button deleteExRe = new Button(buttonComposite, SWT.NONE);
		deleteExRe.setText("Del");
		deleteExRe.setToolTipText("Delete Example Resource");
		// TODO selections hier mit einbauen, damit die buttons nur nicht grayed
		// sind wenn auch sinnvoll.
		deleteExRe.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// delete selected element(s) of tree.
			}
		});
		Label label = new Label(buttonComposite, SWT.NONE);
		label.setText(" | ");
		Button addResource = new Button(buttonComposite, SWT.NONE);
		addResource.setText("Add");
		addResource.setToolTipText("Add resources");
		addResource.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// WorkspaceResourceDialog workspaceResourceDialog = new
				// WorkspaceResourceDialog(middleGroup.getShell(),
				// labelProvider, contentProvider)
				// Achtung soll ein emf widget sein, daher noch zu den
				// dependences hinzuzufuegen
				// aber muss auch swt widget geben.
				// siehe export mechanism of java project,
				// macht dann evt. den delete button ueberfluessig.
			}

		});
		Button delResource = new Button(buttonComposite, SWT.NONE);
		delResource.setText("Del");
		delResource.setToolTipText("Delete resources");
	}

	private void createBottomGroup(Composite composite) {
		// TODO think about: exampleFolderButton kann ich glaube ich nicht
		// machen, nachdenken was damit passiert bis zum import
		Group bottomGroup = new Group(composite, SWT.NONE);
		GridLayout bottomLayout = new GridLayout();
		bottomLayout.numColumns = 1;
		bottomGroup.setText("Additional Options");
		bottomGroup.setLayout(bottomLayout);
		bottomGroup.setLayoutData(new GridData(GridData.FILL_BOTH));
		Button exampleFolderButton = new Button(bottomGroup, SWT.CHECK);
		exampleFolderButton.setText("create folder with example name");
		Button hiddenFilesButton = new Button(bottomGroup, SWT.CHECK);
		// TODO hidden files ber�cksichtigen, sowohl beim import(?) als auch
		// beim export; gedanken dr�ber machen.
		hiddenFilesButton.setText("Copy Hidden Files");
	}

	// TODO gr��e fix machen und mit scrollbar ausstatten, gilt auch f�r import
	// mechanismus.
	private void createTreeElement(Composite composite) {
		exampleTree = new Tree(composite, SWT.BORDER);
		exampleTree.setLayoutData(new GridData(GridData.FILL_BOTH));
		exampleTree.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				// updateElements(e.item);
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				// updateElements(e.item);
			}
		});
		exampleTree
				.setToolTipText("Use context menu to edit example resources.");
		initTree(exampleTree);
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

	private void initTree(Tree tree) {
		for (int i = 0; i < 3; i++) {
			TreeItem iItem = new TreeItem(tree, 0);
			iItem.setText("ExRe");
		}
	}

	// TODO sch�neren namen geben.
	private void createExReComposite(Composite composite) {
		Composite exReComposite = new Composite(composite, SWT.BORDER);
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		exReComposite.setLayout(gridLayout);
		exReComposite.setToolTipText("Set Example Resource attributes.");

		Composite comboComposite = new Composite(exReComposite, SWT.NONE);
		GridLayout comboLayout = new GridLayout();
		comboLayout.numColumns = this.COLUMNCOUNT;
		comboComposite.setLayout(comboLayout);
		new Label(comboComposite, SWT.None).setText("Category:");
		final Combo categoryCombo = new Combo(comboComposite, SWT.READ_ONLY);
		List<String> categories = ExampleManager.get().getCategories();
		String items[] = new String[categories.size()];
		for (int i = 0; i < categories.size(); i++) {
			items[i] = categories.get(i);
		}
		Arrays.sort(items);

		categoryCombo.setItems(items);
		Composite buttonComposite = new Composite(exReComposite, SWT.NONE);
		Button headResourceButton = new Button(buttonComposite, SWT.CHECK);
		buttonComposite.setLayout(new GridLayout());
		buttonComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
		headResourceButton.setText("is head resource");
		headResourceButton.setToolTipText("Explaining of that attribute");
	}

	public String getDestLocation() {
		return this.destPath.getText();
	}

	public List<ExampleResource> getExampleResources() {
		return this.exampleResources;
	}

}
