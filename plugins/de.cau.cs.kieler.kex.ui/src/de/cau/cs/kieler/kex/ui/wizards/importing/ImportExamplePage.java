package de.cau.cs.kieler.kex.ui.wizards.importing;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
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
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.dialogs.WizardResourceImportPage;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.kex.controller.ExampleManager;
import de.cau.cs.kieler.kex.model.Example;

public class ImportExamplePage extends WizardResourceImportPage {

    private static final int IMAGE_MAX_WIDTH = 800;
    private static final int IMAGE_MAX_HEIGHT = 600;

    private static final int IMAGE_PRE_WIDTH = 208;
    private static final int IMAGE_PRE_HEIGHT = 117;

    private Browser exampleDescription;

    private Tree exampleTree;

    private Label imageLabel;

    private Example selectedExample;

    private Composite previewComp;

    private Label previewDesc;

    protected ImportExamplePage(String name, IStructuredSelection selection) {
        super(name, selection);
        setTitle(name);
        super.setDescription("Choose examples to import and set destination location.");
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

        this.exampleDescription = new Browser(parent, SWT.NONE | SWT.BORDER);
        GridData descData = new GridData(GridData.FILL_HORIZONTAL);
        descData.heightHint = 100;
        descData.minimumHeight = 80;
        this.exampleDescription.setLayoutData(descData);
        // Font initialFont = descriptionLabel.getFont();
        // FontData[] fontData = initialFont.getFontData();
        // for (int i = 0; i < fontData.length; i++) {
        // fontData[i].setHeight(24);
        // }
        // FontData[] fontData = this.exampleDescription.getFont().getFontData();
        // for (FontData fd : fontData) {
        //
        // int lfHeight = fd.data.lfHeight;
        // System.err.println(lfHeight);
        // }
        // this.exampleDescription.setFont(new Font(parent.getDisplay(), fontData));
        // FontData[] fontData2 = this.exampleDescription.getFont().getFontData();
        // for (FontData fd : fontData) {
        // fd.setHeight(25);
        // }
        // this.exampleDescription = new StyledText(parent, SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL
        // | SWT.BORDER);
        // this.exampleDescription.setEditable(false);
        // GridData descData = new GridData(GridData.FILL_HORIZONTAL);
        // descData.heightHint = 80;
        // descData.minimumHeight = 80;
        // this.exampleDescription.setLayoutData(descData);

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
        exampleTree = new Tree(composite, SWT.BORDER | SWT.CHECK | SWT.V_SCROLL | SWT.H_SCROLL);
        FormData formData = new FormData(190, 100);
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
        prevData.left = new FormAttachment(controlComp, 20);
        previewComp.setLayoutData(prevData);
        previewDesc = new Label(previewComp, SWT.NONE);
        previewDesc.setText("Example Preview");
        FormData formData = new FormData();
        previewDesc.setLayoutData(formData);
        imageLabel = new Label(previewComp, SWT.BORDER);
        imageLabel.setImage(initPreviewImage());
        // 16 : 9
        FormData formData2 = new FormData(IMAGE_PRE_WIDTH, IMAGE_PRE_HEIGHT);
        formData2.top = new FormAttachment(previewDesc, 5);
        imageLabel.setLayoutData(formData2);
        imageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseDown(MouseEvent e) {
                super.mouseDown(e);
                if (selectedExample == null) {
                    return;
                }
                Dialog dialog = new Dialog(imageLabel.getShell()) {

                    private Rectangle bounds;

                    @Override
                    protected Control createDialogArea(Composite parent) {
                        Composite composite = (Composite) super.createDialogArea(parent);
                        Composite innerComp = new Composite(composite, SWT.CENTER | SWT.BORDER);
                        innerComp.setLayout(new GridLayout());
                        Label imageLabel = new Label(innerComp, SWT.BORDER | SWT.V_SCROLL
                                | SWT.H_SCROLL);
                        imageLabel.setLayoutData(new GridData(GridData.CENTER));
                        Image image = loadImage(IMAGE_MAX_WIDTH, IMAGE_MAX_HEIGHT);
                        bounds = image.getBounds();
                        imageLabel.setImage(image);
                        return composite;
                    }

                    @Override
                    protected Point getInitialSize() {
                        // imagesize + paddings
                        return new Point(bounds.width + 40, bounds.height + 120);
                    }

                    @Override
                    protected void configureShell(Shell newShell) {
                        super.configureShell(newShell);
                        newShell.setText("Preview Picture");
                    }

                };
                dialog.open();
            }
        });
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
            Image previewPic = null;
            if (data instanceof Example) {
                selectedExample = (Example) data;
                updateDescriptionLabel(selectedExample);
                previewPic = loadImage(IMAGE_PRE_WIDTH, IMAGE_PRE_HEIGHT);
            } else {
                getExampleDescription().setText("");
                selectedExample = null;
                previewPic = initPreviewImage();
                imageLabel.setImage(initPreviewImage());

            }
            updateImageLabel(previewPic);
        } else {
            getExampleDescription().setText("");
            updateImageLabel(initPreviewImage());
        }
    }

    private void updateImageLabel(Image image) {
        FormData formData = new FormData(image.getImageData().width, image.getImageData().height);
        formData.top = new FormAttachment(previewDesc, 5);
        imageLabel.setLayoutData(formData);
        imageLabel.setImage(image);
        imageLabel.pack();
    }

    private Image loadImage(double image_width, double image_height) {
        final String previewPicPath = selectedExample.getOverviewPic();
        if (previewPicPath != null && previewPicPath.length() > 1) {
            try {
                ImageData imgData = new ImageData(ExampleManager.get().loadOverviewPic(
                        selectedExample));

                double tempSize = Math.max(imgData.width / image_width, imgData.height
                        / image_height);
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

    private void updateDescriptionLabel(Example selectedExample) {
        StringBuilder sb = new StringBuilder();

        String htmlDesc = selectedExample.getDescription().replaceAll("\n", "<br>");
        sb.append("<font face=\"Tahoma, sans-serif\" size=\"-1\">");
        sb.append("Title: ").append(selectedExample.getTitle()).append("<br>").append("Author:")
                .append(selectedExample.getAuthor()).append("<br>").append("Contact: ")
                .append(selectedExample.getContact()).append("<br>").append("<br>")
                .append(htmlDesc);
        sb.append("</font>");
        getExampleDescription().setText(sb.toString());
    }

    // TODO falls ein image nicht richtig geladen wird wegen format fehler oder
    // so muss auf jeden fall eine meldung kommen... am besten schon beim export
    // darauf reagieren.

    private Image noPreviewPic(Display display) {
        return new Image(display, ExampleManager.get().loadStandardPic());
    }

    private Image initPreviewImage() {
        Image preview = new Image(previewComp.getDisplay(), IMAGE_PRE_WIDTH, IMAGE_PRE_HEIGHT);
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

    public Browser getExampleDescription() {
        return exampleDescription;
    }

    public IPath getContainerPath() {
        return super.getContainerFullPath();
    }

    public boolean isQuickStart() {
        return super.getContainerFullPath() == null && getCheckedExamples().size() == 0;
    }
}
