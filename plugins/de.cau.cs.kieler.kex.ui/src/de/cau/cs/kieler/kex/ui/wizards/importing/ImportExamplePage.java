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
package de.cau.cs.kieler.kex.ui.wizards.importing;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.TreeColumnLayout;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.dialogs.WizardResourceImportPage;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.kex.controller.ExampleManager;
import de.cau.cs.kieler.kex.model.Example;

/**
 * This class represents the import page of importwizard. It contains a tree which shows the
 * importable examples. Secondly there is a description and a preview picture field. The
 * {@link WizardResourceImportPage} is extended because of getting a Workspace Directory Chooser.
 * 
 * @author pkl
 * 
 */
public class ImportExamplePage extends WizardPage {

    private static final int IMAGE_MAX_WIDTH = 800;
    private static final int IMAGE_MAX_HEIGHT = 600;

    private static final int IMAGE_PRE_WIDTH = 208;
    private static final int IMAGE_PRE_HEIGHT = 117;

    private static final int DESC_HEIGHT_HINT = 100;
    private static final int DESC_MIN_HEIGHT = 80;

    private static final int EXTREE_WIDTH = 190;
    private static final int EXTREE_HEIGHT = 300;

    private static final int OFFSET = 5;
    private static final int PREVIEW_OFFSET = 20;

    private static final int IMG_PADDINGS_WIDTH = 40;
    private static final int IMG_PADDINGS_HEIGHT = 120;

    private Text exampleDescription;

    private Tree exampleTree;

    private Label imageLabel;

    private Example selectedExample;

    private Composite previewComp;

    private Label previewDesc;

    /**
     * The constructor will be called with following parameters.
     * 
     * @param name
     *            , the name of page, works as page title, too.
     * @param selection
     *            , the selected resource will be set as default as import location.
     */

    public ImportExamplePage(final String name, final IStructuredSelection selection) {
        super(name);
        setDescription("Choose examples to import.");
    }

    public void createControl(Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 2;
        composite.setLayout(gridLayout);
        composite.setLayoutData(new GridData(GridData.FILL_BOTH));
        setControl(composite);
        Control exampleTreeControl = createLeft(composite);
        // createRight(composite, exampleTreeControl);
        getShell().setMinimumSize(540, 600);
    }

    private Control createLeft(Composite parent) {
        return createTreeComposite(parent);
    }

    private void createRight(Composite parent, Control control) {
        Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(new GridLayout());
        composite.setLayoutData(new GridData(SWT.NONE));
        createPreviewComp(composite, control);
        createDescriptionComp(composite, control);
    }

    private void createDescriptionComp(Composite composite, Control control) {

        Label descriptionLabel = new Label(composite, SWT.NONE);
        descriptionLabel.setText("Example Description");

        this.exampleDescription = new Text(composite, SWT.NONE | SWT.MULTI | SWT.V_SCROLL
                | SWT.BORDER);
        GridData descData = new GridData(GridData.FILL_HORIZONTAL);
        descData.heightHint = DESC_HEIGHT_HINT;
        descData.minimumHeight = DESC_MIN_HEIGHT;
        this.exampleDescription.setLayoutData(descData);

    }

    /**
     * creates the composite of the tree with importable examples.
     * 
     * @param parent
     * @return Control, the created composite.
     */
    private Control createTreeComposite(final Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 3;
        composite.setLayout(new GridLayout());
        composite.setLayoutData(new GridData(GridData.FILL_VERTICAL));

        Composite treeComposite = new Composite(composite, SWT.NONE);
        TreeColumnLayout treeColumnLayout = new TreeColumnLayout();
        treeComposite.setLayout(treeColumnLayout);

        TreeViewer treeViewer = new TreeViewer(treeComposite, SWT.BORDER | SWT.FULL_SELECTION);
        // createTree(treeComposite);
        // new TableViewer(tableComposite, SWT.BORDER | SWT.FULL_SELECTION);

        Tree tree = treeViewer.getTree();

        List<String> categories = ExampleManager.get().getCategories();
        if (categories.size() == 0) {
            MessageDialog.openError(getShell(), "Could not start example import wizard",
                    "There are no examples to import. Please check installed features!");
        }

        for (int i = 0; i < categories.size(); i++) {
            TreeItem iItem = new TreeItem(tree, SWT.CHECK);
            iItem.setText(categories.get(i));
            addExamplesToItem(categories.get(i), iItem);
        }

        // Spaltenk�pfe und Zeilenbegrenzungen sichtbar machen
        tree.setHeaderVisible(true);
        tree.setLinesVisible(true);

        // treeViewer = new TreeViewer(parent);
        // treeViewer.setContentProvider(new MovingBoxContentProvider());
        // treeViewer.setLabelProvider(new MovingBoxLabelProvider());
        // treeViewer.setInput(getInitalInput());
        // treeViewer.expandAll();

        // ArrayContentProvider kann verwendet werden, da Input-Objekt eine Java Collection ist
        treeViewer.setContentProvider(ArrayContentProvider.getInstance());

        // F�r jede Spalte ein TableViewerColumn erzeugen
        TreeViewerColumn viewerNameColumn = new TreeViewerColumn(treeViewer, SWT.NONE);
        viewerNameColumn.getColumn().setText("Examples");
        viewerNameColumn.getColumn().setWidth(140);

        // LabelProvider f�r jede Spalte setzen
        viewerNameColumn.setLabelProvider(new CellLabelProvider() {
            @Override
            public void update(ViewerCell cell) {
                cell.setText(((Example) cell.getElement()).getTitle());
            }
        });

        treeColumnLayout.setColumnData(viewerNameColumn.getColumn(), new ColumnWeightData(50, 20,
                true));
        // List<SomeObject> als Input-Objekt setzen
        // treeViewer.setInput(ExampleManager.get().getExamples().values());

        return composite;
    }

    /**
     * creates the tree object and fills it with categories and examples.
     * 
     * @param composite
     * @param topControl
     * @return exampleTree
     */
    private Tree createTree(final Composite composite) {
        exampleTree = new Tree(composite, SWT.BORDER | SWT.CHECK | SWT.V_SCROLL | SWT.H_SCROLL
                | SWT.FULL_SELECTION);
        exampleTree.addSelectionListener(new SelectionListener() {
            public void widgetSelected(final SelectionEvent e) {
                updateElements(e);
            }

            public void widgetDefaultSelected(final SelectionEvent e) {
                updateElements(e);
            }
        });

        List<String> categories = ExampleManager.get().getCategories();
        if (categories.size() == 0) {
            MessageDialog.openError(getShell(), "Could not start example import wizard",
                    "There are no examples to import. Please check installed features!");
        }
        for (int i = 0; i < categories.size(); i++) {
            TreeItem iItem = new TreeItem(exampleTree, SWT.CHECK);
            iItem.setText(categories.get(i));
            addExamplesToItem(categories.get(i), iItem);
        }
        return exampleTree;
    }

    /**
     * Creates new tree items for a category tree item.
     * 
     * @param category
     *            , category id
     * @param tItem
     *            , category tree item
     */
    private void addExamplesToItem(final String category, final TreeItem tItem) {
        for (Example example : ExampleManager.get().getExamples().values()) {
            if (example.contains(category)) {
                TreeItem item = new TreeItem(tItem, SWT.CHECK);
                item.setText(example.getTitle());
                item.setData(example);
            }
        }
    }

    /**
     * Creates the preview picture component.
     * 
     * @param composite
     * @param controlComp
     */
    private void createPreviewComp(final Composite composite, final Control controlComp) {

        previewComp = new Composite(composite, SWT.NONE);
        previewComp.setLayout(new FormLayout());
        FormData prevData = new FormData();
        prevData.left = new FormAttachment(controlComp, PREVIEW_OFFSET);
        previewComp.setLayoutData(prevData);
        previewDesc = new Label(previewComp, SWT.NONE);
        previewDesc.setText("Example Preview");
        FormData formData = new FormData();
        previewDesc.setLayoutData(formData);
        imageLabel = new Label(previewComp, SWT.BORDER);
        imageLabel.setImage(initPreviewImage());
        // 16 : 9
        FormData formData2 = new FormData(IMAGE_PRE_WIDTH, IMAGE_PRE_HEIGHT);
        formData2.top = new FormAttachment(previewDesc, OFFSET);
        imageLabel.setLayoutData(formData2);
        imageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseDown(final MouseEvent e) {
                super.mouseDown(e);
                if (selectedExample == null) {
                    return;
                }
                Dialog dialog = new Dialog(imageLabel.getShell()) {

                    private Rectangle bounds;

                    @Override
                    protected void createButtonsForButtonBar(final Composite parent) {
                        super.createButton(parent, IDialogConstants.OK_ID,
                                IDialogConstants.OK_LABEL, true);
                    }

                    @Override
                    protected Control createDialogArea(final Composite parent) {
                        Composite composite = (Composite) super.createDialogArea(parent);
                        Composite innerComp = new Composite(composite, SWT.CENTER | SWT.BORDER);
                        innerComp.setLayout(new GridLayout());
                        Label imgLabel = new Label(innerComp, SWT.BORDER | SWT.V_SCROLL
                                | SWT.H_SCROLL);
                        imgLabel.setLayoutData(new GridData(GridData.CENTER));
                        Image image = loadImage(IMAGE_MAX_WIDTH, IMAGE_MAX_HEIGHT);
                        bounds = image.getBounds();
                        imgLabel.setImage(image);
                        return composite;
                    }

                    @Override
                    protected Point getInitialSize() {
                        // imagesize + paddings
                        return new Point(bounds.width + IMG_PADDINGS_WIDTH, bounds.height
                                + IMG_PADDINGS_HEIGHT);
                    }

                    @Override
                    protected void configureShell(final Shell newShell) {
                        super.configureShell(newShell);
                        newShell.setText("Preview Picture");
                    }

                };
                dialog.open();
            }
        });
        imageLabel.addMouseTrackListener(new MouseTrackAdapter() {
            @Override
            public void mouseHover(final MouseEvent e) {
                imageLabel.setCursor(new Cursor(imageLabel.getDisplay(), SWT.CURSOR_HAND));
            }
        });
    }

    /**
     * updates description and preview pictures of an example.
     * 
     * @param e
     */
    private void updateElements(final SelectionEvent e) {
        if (!(e.item instanceof TreeItem)) {
            getExampleDescription().setText("");
            imageLabel.setImage(initPreviewImage());
            return;
        }

        TreeItem selected = (TreeItem) e.item;
        // parent is != null if selected is a example
        if (selected.getParentItem() != null) {
            // selected is a example
            Object data = selected.getData();
            Image previewPic = null;
            if (data instanceof Example) {
                selectedExample = (Example) data;
                updateDescriptionLabel(selectedExample);
                previewPic = loadImage(IMAGE_PRE_WIDTH, IMAGE_PRE_HEIGHT);
                updateCategory(e);
            } else {
                getExampleDescription().setText("");
                selectedExample = null;
                previewPic = initPreviewImage();
                imageLabel.setImage(initPreviewImage());

            }
            updateImageLabel(previewPic);
        } else {
            // selected is a category
            getExampleDescription().setText("");
            updateImageLabel(initPreviewImage());
            if (e.detail == SWT.CHECK) {
                boolean check = selected.getChecked();
                TreeItem[] items = selected.getItems();
                for (TreeItem treeItem : items) {
                    treeItem.setChecked(check);
                }
            }
        }
    }

    private void updateCategory(final SelectionEvent e) {
        if (e.detail != SWT.CHECK) {
            return;
        }
        TreeItem parentItem = ((TreeItem) e.item).getParentItem();
        TreeItem[] items = parentItem.getItems();
        boolean selectedAll = true;
        for (TreeItem treeItem : items) {
            if (!treeItem.getChecked()) {
                selectedAll = false;
                break;
            }
        }
        parentItem.setChecked(selectedAll);
    }

    /**
     * Updates the label of preview picture.
     * 
     * @param image
     *            , {@link Image}
     */
    private void updateImageLabel(final Image image) {
        FormData formData = new FormData(image.getImageData().width, image.getImageData().height);
        formData.top = new FormAttachment(previewDesc, OFFSET);
        imageLabel.setLayoutData(formData);
        imageLabel.setImage(image);
        imageLabel.pack();
    }

    /**
     * loads preview image. The parameters define the max width and heigt of an image. The loaded
     * image will scaled to the parameter values, while keeping the imageformat.
     * 
     * @param image_width
     * @param image_height
     * @return
     */
    private Image loadImage(final double imageWidth, final double imageHeight) {
        final String previewPicPath = selectedExample.getPreviewPic();
        if (previewPicPath != null && previewPicPath.length() > 1) {
            try {
                ImageData imgData = new ImageData(ExampleManager.get().loadOverviewPic(
                        selectedExample));

                double tempSize = Math
                        .max(imgData.width / imageWidth, imgData.height / imageHeight);
                imgData = imgData.scaledTo((int) (imgData.width / tempSize),
                        (int) (imgData.height / tempSize));
                return new Image(previewComp.getDisplay(), imgData);

            } catch (final KielerException e) {
                e.printStackTrace();
                return noPreviewPic(previewComp.getDisplay());
            }
        }
        return noPreviewPic(previewComp.getDisplay());

    }

    private void updateDescriptionLabel(final Example example) {
        StringBuilder sb = new StringBuilder();
        sb.append("Title: ").append(example.getTitle()).append("\n").append("Author: ")
                .append(example.getAuthor()).append("\n").append("Contact: ")
                .append(example.getContact()).append("\n").append("\n")
                .append(example.getDescription());
        getExampleDescription().setText(sb.toString());
    }

    // TODO falls ein image nicht richtig geladen wird wegen format fehler oder
    // so muss auf jeden fall eine meldung kommen... am besten schon beim export
    // darauf reagieren.

    /**
     * loads "no preview" picture.
     * 
     * @return "no preview" picture
     */
    private Image noPreviewPic(final Display display) {
        return new Image(display, ExampleManager.get().loadStandardPic());
    }

    /**
     * initializes the preview image.
     * 
     * @return
     */
    private Image initPreviewImage() {
        Image preview = new Image(previewComp.getDisplay(), IMAGE_PRE_WIDTH, IMAGE_PRE_HEIGHT);
        GC gc = new GC(preview);
        gc.setBackground(previewComp.getBackground());
        gc.fillRectangle(preview.getBounds());
        return preview;
    }

    /**
     * filters checked examples from example tree.
     * 
     * @return list of examples.
     */
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

    /**
     * getter for example description field. It is a browser.
     * 
     * @return Text
     */
    public Text getExampleDescription() {
        return exampleDescription;
    }

}
