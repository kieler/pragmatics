package de.cau.cs.kieler.kex.ui.wizards.importing;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.dialogs.WizardResourceImportPage;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.kex.controller.ExampleManager;
import de.cau.cs.kieler.kex.model.Example;

public class ImportExamplePage extends WizardResourceImportPage {

	private StyledText exampleDescription;

	private Tree exampleTree;

	private Label imageLabel;

	private Composite previewComp;

	protected ImportExamplePage(String name, IStructuredSelection selection) {
		super(name, selection);
		setTitle(name);
		setDescription("Enter example attributes and destination location.");
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

	@Override
	protected void createOptionsGroup(Composite parent) {
		// no options
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

	private void createTopGroup(Composite composite) {
		Group topGroup = new Group(composite, SWT.NONE);
		topGroup.setLayout(new GridLayout());
		topGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		topGroup.setText("Set Destination");
		super.createControl(topGroup);

	}

	private void createMiddleComponent(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new FormLayout());
		Control createTreeComposite = createTreeComposite(composite);
		createPreviewComp(composite, createTreeComposite);
		composite.getShell().redraw();

	}

	private void createBottomComponent(Composite parent) {
		Label descriptionLabel = new Label(parent, SWT.NONE);
		descriptionLabel.setText("Example Description");

		this.exampleDescription = new StyledText(parent, SWT.MULTI
				| SWT.V_SCROLL | SWT.H_SCROLL | SWT.BORDER);
		this.exampleDescription.setEditable(false);
		this.exampleDescription.setText("\n\n\n\n\n");
		this.exampleDescription.setLayoutData(new GridData(
				GridData.FILL_HORIZONTAL));
	}

	private Control createTreeComposite(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new FormLayout());
		FormData formData = new FormData();
		composite.setLayoutData(formData);
		Label treeDesc = new Label(composite, SWT.NONE);
		FormData labelData = new FormData();
		treeDesc.setLayoutData(labelData);
		treeDesc.setText("Choose Examples");
		createTree(composite, treeDesc);
		return composite;
	}

	private Control createTree(Composite composite, Control topControl) {
		exampleTree = new Tree(composite, SWT.BORDER | SWT.CHECK | SWT.V_SCROLL
				| SWT.H_SCROLL);
		FormData formData = new FormData(250, 110);
		formData.top = new FormAttachment(topControl, 5);
		exampleTree.setLayoutData(formData);
		exampleTree.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				updateElements(e);
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				updateElements(e);
			}
		});

		List<String> categories = ExampleManager.get().getCategories();
		for (int i = 0; i < categories.size(); i++) {
			TreeItem iItem = new TreeItem(exampleTree, SWT.CHECK);
			iItem.setText(categories.get(i));
			addExamplesToItem(categories.get(i), iItem);
		}
		return exampleTree;
	}

	private void addExamplesToItem(String category, TreeItem tItem) {
		for (Example example : ExampleManager.get().getExamples().values()) {
			if (example.contains(category)) {
				TreeItem item = new TreeItem(tItem, SWT.NONE);
				item.setText(example.getTitle());
				item.setData(example);
			}
		}
	}

	private void createPreviewComp(Composite composite, Control controlComp) {

		previewComp = new Composite(composite, SWT.NONE);
		previewComp.setLayout(new FormLayout());
		FormData prevData = new FormData();
		prevData.left = new FormAttachment(controlComp, 5);
		previewComp.setLayoutData(prevData);
		Label previewDesc = new Label(previewComp, SWT.NONE);
		previewDesc.setText("Example Preview");
		FormData formData = new FormData();
		previewDesc.setLayoutData(formData);
		imageLabel = new Label(previewComp, SWT.BORDER);
		imageLabel.setImage(initPreviewImage());
		FormData formData2 = new FormData(160, 90);
		formData2.top = new FormAttachment(previewDesc, 5);
		imageLabel.setLayoutData(formData2);
	}

	private void updateElements(SelectionEvent e) {
		if (!(e.item instanceof TreeItem)) {
			getExampleDescription().setText("");
			imageLabel.setImage(initPreviewImage());
			return;
		}

		TreeItem selected = (TreeItem) e.item;
		if (selected.getParentItem() != null) {
			Object data = selected.getData();
			Image preview = null;
			if (data instanceof Example) {
				Example selectedExample = (Example) data;

				updateDescriptionLabel(selectedExample);

				preview = loadImage(selectedExample);
			} else {
				getExampleDescription().setText("");
				imageLabel.setImage(initPreviewImage());
			}
			imageLabel.setImage(preview);
		} else {
			getExampleDescription().setText("");
			imageLabel.setImage(initPreviewImage());
		}
	}

	private Image loadImage(Example selectedExample) {
		final String previewPicPath = selectedExample.getPreviewPicPath();
		if (previewPicPath != null) {
			try {
				final InputStream inputStream = ExampleManager.get()
						.loadPreviewPic(selectedExample);
				return new Image(previewComp.getDisplay(), inputStream);
			} catch (final KielerException e) {
				// TODO Message Dialog error
				return noPreviewPic(previewComp.getDisplay());
			}
		}
		return noPreviewPic(previewComp.getDisplay());

	}

	private void updateDescriptionLabel(Example selectedExample) {
		StringBuilder sb = new StringBuilder();
		sb.append("Title:       ").append(selectedExample.getTitle()).append(
				"\n").append("Author:   ").append(selectedExample.getAuthor())
				.append("\n").append("Contact: ").append(
						selectedExample.getContact()).append("\n").append("\n")
				.append(selectedExample.getDescription());
		getExampleDescription().setText(sb.toString());
	}

	// TODO falls ein image nicht richtig geladen wird wegen format fehler oder
	// so muss auf jeden fall eine meldung kommen... am besten schon beim export
	// darauf reagieren.

	private Image noPreviewPic(Display display) {
		return new Image(display, ExampleManager.get().loadStandardPic());
	}

	private Image initPreviewImage() {
		Image preview = new Image(previewComp.getDisplay(), 160, 90);
		GC gc = new GC(preview);
		gc.setBackground(previewComp.getBackground());
		gc.fillRectangle(preview.getBounds());
		return preview;
	}

	public List<Example> getCheckedExamples() {
		List<Example> result = new ArrayList<Example>();
		for (TreeItem item : exampleTree.getItems()) {
			TreeItem[] categoryItems = item.getItems();
			for (TreeItem categoryItem : categoryItems) {
				if (categoryItem.getChecked() && categoryItem.getData() != null) {
					result.add((Example) categoryItem.getData());
				}
			}
		}
		return result;
	}

	public StyledText getExampleDescription() {
		return exampleDescription;
	}

	public IPath getContainerPath() {
		return super.getContainerFullPath();
	}

	public boolean isQuickStart() {
		return super.getContainerFullPath() == null
				&& getCheckedExamples().size() == 0;
	}
}
