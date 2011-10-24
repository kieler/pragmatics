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
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.TypedEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.dialogs.WizardResourceImportPage;

import de.cau.cs.kieler.kex.controller.ExampleManager;
import de.cau.cs.kieler.kex.model.Category;
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

    private static final int THREE_COLUMNS = 3;
    private static final int VERTICAL_INDENT = 10;
    private static final int MARGIN = 10;

    private static final int PAGE_MIN_WIDTH = 540;
    private static final int PAGE_MIN_HEIGHT = 600;
    private static final String NO_PARENT = "No Parent";

    // preferred height
    private static final int HEIGHT_HINT = 160;

    private Tree categoryTree;
    private Category checkedCategory = null;
    private final List<Category> creatableCategories = new ArrayList<Category>();

    private static final String WORKSPACE_DIR = ResourcesPlugin.getWorkspace().getRoot()
            .getLocation().toOSString();

    private Button revertTreeButton;

    /**
     * contstructor for {@link ExampleExportPage}.
     * 
     * @param name
     *            , String
     * @param selection
     *            , String
     */
    protected ExampleExportPage(final String name, final IStructuredSelection selection) {
        super(name, selection);
        setTitle(name);
        setDescription("Set destination and preview picture "
                + "for exported example and determine example categories.");
    }

    @Override
    public void createControl(final Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(new GridLayout(THREE_COLUMNS, false));
        setControl(composite);
        createTopGroup(composite);
        createMiddleGroup(composite);
        createBottomGroup(composite);
        getShell().setMinimumSize(PAGE_MIN_WIDTH, PAGE_MIN_HEIGHT);
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
        Label destLabel = new Label(composite, SWT.NONE);
        destLabel.setText("Destination Directory:");
        this.destPath = new Text(composite, SWT.BORDER);
        this.destPath.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        Button addDestPath = new Button(composite, SWT.NONE);
        addDestPath.setText("Browse...");
        addDestPath.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent event) {
                DirectoryDialog dirDiag = new DirectoryDialog(composite.getShell());
                dirDiag.setText("Choose destination directory");
                dirDiag.setMessage("Select a directory in a java plugin project.");
                String choosenPath = destPath.getText();
                dirDiag.setFilterPath(choosenPath == null || choosenPath.length() < 2 ? WORKSPACE_DIR
                        : choosenPath);
                String dir = dirDiag.open();
                if (dir != null) {
                    destPath.setText(dir);
                }
            }
        });

    }

    private void createMiddleGroup(final Composite composite) {
        Label label = new Label(composite, SWT.NONE);
        label.setText("Set Picture:");
        this.previewPic = new Text(composite, SWT.BORDER);
        previewPic.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        Button browse = new Button(composite, SWT.NONE);
        browse.setText("Browse...");
        browse.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                super.widgetSelected(e);
                String choosenPath = previewPic.getText();
                final FileDialog picDialog = new FileDialog(composite.getShell());
                picDialog.setFilterPath(WORKSPACE_DIR);
                String[] extensions = { "*.png;*.jpg;*.gif" };
                IPath path = new Path(choosenPath);
                picDialog.setFilterExtensions(extensions);
                if (choosenPath == null || choosenPath.length() < 2) {
                    picDialog.setFilterPath(WORKSPACE_DIR);
                } else {
                    String portableString = path.removeLastSegments(1).toPortableString();
                    picDialog.setFilterPath(choosenPath == null || choosenPath.length() < 2
                            ? WORKSPACE_DIR
                            : portableString);
                }
                String pic = picDialog.open();
                if (pic != null) {
                    getPreviewPic().setText(pic);
                }
            }

        });

    }

    private void createButtonComposite(final Group middleGroup) {
        Composite buttonCompo = new Composite(middleGroup, SWT.NONE);
        GridLayout buttonCompoLayout = new GridLayout(2, true);
        buttonCompoLayout.marginHeight = 0;
        buttonCompoLayout.marginWidth = 0;
        buttonCompo.setLayout(buttonCompoLayout);
        buttonCompo.setLayoutData(new GridData(SWT.END, SWT.BEGINNING, false, false));
        
        Button addCategory = new Button(buttonCompo, SWT.NONE);
        addCategory.setToolTipText("Create a new Example Category");
        addCategory.setText("New...");
        addCategory.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent event) {

                Dialog diag = new Dialog(getShell()) {

                    private Text titleText;
                    private Text idText;
                    private Text iconPath;
                    private Text descText;
                    private Combo parentCatCombo;

                    private static final int DESCRIPTION_MIN = 10;
                    private static final int ID_MIN = 3;
                    private static final int TITLE_MIN = 3;

                    private static final int DIALOG_WIDTH = 540;
                    private static final int DIALOG_HEIGHT = 300;

                    private static final int CAT_DESC_HEIGHT = 100;
                    private static final int CAT_DESC_MINHEIGHT = 80;

                    @Override
                    protected Control createDialogArea(final Composite parent) {
                        // Control createDialogArea = super.createDialogArea(parent);
                        Composite composite = new Composite(parent, SWT.NONE);
                        GridLayout layout = new GridLayout();
                        layout.numColumns = 2;

                        composite.setLayout(layout);
                        composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

                        Label idLab = new Label(composite, SWT.NONE);
                        idLab.setText("Id: ");
                        idLab.setToolTipText("");

                        idText = new Text(composite, SWT.BORDER);
                        idText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
                        doSingleCheck(idText, ID_MIN);
                        idText.addModifyListener(new TextBoxValidator(idText, Messages.getString(
                                "titleToShort", ID_MIN)) {
                            @Override
                            public boolean check(final TypedEvent e) {
                                return doCheck((Text) e.getSource(), ID_MIN);
                            }
                        });

                        Label titleLab = new Label(composite, SWT.NONE);
                        titleLab.setText("Title: ");
                        titleLab.setToolTipText("");

                        titleText = new Text(composite, SWT.BORDER);
                        titleText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
                        doSingleCheck(titleText, TITLE_MIN);
                        titleText.addModifyListener(new TextBoxValidator(titleText, Messages
                                .getString("titleToShort", TITLE_MIN)) {
                            @Override
                            public boolean check(final TypedEvent e) {
                                return doCheck((Text) e.getSource(), TITLE_MIN);
                            }
                        });

                        // TODO browse field.
                        Label iconLab = new Label(composite, SWT.NONE);
                        iconLab.setText("Icon: ");
                        iconLab.setToolTipText("");
                        iconPath = new Text(composite, SWT.BORDER);
                        iconPath.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

                        Label parentCatLab = new Label(composite, SWT.NONE);
                        parentCatLab.setText("Parent Category: ");
                        parentCatCombo = new Combo(composite, SWT.BORDER);
                        parentCatCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
                        parentCatCombo.add(NO_PARENT);
                        parentCatCombo.select(0);
                        // FIXME save performance, if loading categories only one time.
                        List<Category> categories = ExampleManager.get().getCategories();
                        for (Category cat : categories) {
                            parentCatCombo.add(cat.getId());
                            parentCatCombo.getText();
                        }
                        Label descLab = new Label(composite, SWT.NONE);
                        descLab.setText("Description: ");

                        descText = new Text(composite, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL
                                | SWT.H_SCROLL);
                        GridData descData = new GridData(GridData.FILL_HORIZONTAL);
                        descData.heightHint = CAT_DESC_HEIGHT;
                        descData.minimumHeight = CAT_DESC_MINHEIGHT;
                        descText.setLayoutData(descData);
                        doSingleCheck(descText, DESCRIPTION_MIN);
                        descText.addModifyListener(new TextBoxValidator(descText, Messages
                                .getString("titleToShort", DESCRIPTION_MIN)) {
                            @Override
                            public boolean check(final TypedEvent e) {
                                return doCheck((Text) e.getSource(), DESCRIPTION_MIN);
                            }
                        });

                        return parent;
                    }

                    @Override
                    protected void okPressed() {
                        addNewCategory(idText.getText(), titleText.getText(), descText.getText(),
                                iconPath.getText(), parentCatCombo.getText());
                        super.okPressed();
                    }

                    @Override
                    protected void configureShell(final Shell newShell) {
                        super.configureShell(newShell);
                        newShell.setMinimumSize(DIALOG_WIDTH, DIALOG_HEIGHT);
                        newShell.setText("Category creation");
                    }

                    private boolean doSingleCheck(final Text field, final int minLength) {
                        boolean decorate = field.getText().length() < minLength;
                        field.setData(TextBoxValidator.WANTS_COMPLETE, !decorate);
                        return decorate;
                    }

                    private boolean doCheck(final Text field, final int minLength) {
                        boolean decorate = doSingleCheck(field, minLength);
                        triggerDialogComplete();
                        return decorate;
                    }

                    private void triggerDialogComplete() {
                        getButton(IDialogConstants.OK_ID).setEnabled(
                                (Boolean) idText.getData(TextBoxValidator.WANTS_COMPLETE)
                                        && (Boolean) titleText
                                                .getData(TextBoxValidator.WANTS_COMPLETE)
                                        && (Boolean) descText
                                                .getData(TextBoxValidator.WANTS_COMPLETE));

                    }

                };
                diag.open();
            }
        });

        revertTreeButton = new Button(buttonCompo, SWT.NONE);
        revertTreeButton.setText("Revert");
        revertTreeButton.setEnabled(false);
        revertTreeButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent e) {
                categoryTree.removeAll();
                creatableCategories.clear();
                fillTree(categoryTree);
                revertTreeButton.setEnabled(false);
                super.widgetSelected(e);
            }
        });
    }

    private void createBottomGroup(final Composite composite) {
        Group bottomGroup = new Group(composite, SWT.NONE);
        
        GridData bottomLayoutData = new GridData(SWT.FILL, SWT.FILL, true, true);
        bottomLayoutData.horizontalSpan = THREE_COLUMNS;
        bottomLayoutData.verticalIndent = VERTICAL_INDENT;
        bottomGroup.setLayoutData(bottomLayoutData);

        GridLayout bottomLayout = new GridLayout();
        bottomLayout.marginHeight = MARGIN;
        bottomLayout.marginWidth = MARGIN;
        bottomLayout.numColumns = 1;
        bottomGroup.setLayout(bottomLayout);
        
        bottomGroup.setText("Example Categories");
        bottomGroup.setToolTipText("Please select a category.");
        
        createCheckedTree(bottomGroup);
        createButtonComposite(bottomGroup);
    }

    private void createCheckedTree(final Composite parent) {
        this.categoryTree = new Tree(parent, SWT.CHECK | SWT.BORDER);
        GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
        data.heightHint = HEIGHT_HINT;
        categoryTree.setLayoutData(data);

        // categoryTree.setExpanded(true);
        categoryTree.addListener(SWT.Selection, new Listener() {
            public void handleEvent(final Event event) {
                if (event.detail == SWT.CHECK) {
                    TreeItem item = ((TreeItem) event.item);
                    if (item.getChecked()) {
                        // TODO check all categories
                        for (TreeItem catElem : categoryTree.getItems()) {
                            if (catElem.getItemCount() > 0) {
                                checkDuplicateRec(catElem, item);
                            }
                            if (catElem.getChecked() && !catElem.equals(item)) {
                                catElem.setChecked(false);
                            }
                        }
                        checkedCategory = (Category) item.getData();
                    }
                }
            }

            private void checkDuplicateRec(final TreeItem catElem, final TreeItem selected) {
                for (TreeItem item : catElem.getItems()) {
                    if (item.getItemCount() > 0) {
                        checkDuplicateRec(item, selected);
                    }
                    if (item.getChecked() && !item.equals(selected)) {
                        item.setChecked(false);
                    }
                }
            }
        });

        categoryTree.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent e) {
                revertTreeButton.setEnabled(checkedCategory != null
                        || creatableCategories.size() > 0);
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
    public void fillTree(final Tree tree) {
        tree.setRedraw(true);
        List<Category> categories = ExampleManager.get().getCategories();
        List<Category> notPlacedCategories = new ArrayList<Category>();
        for (Category category : categories) {
            if (category.getParentId() == null) {
                TreeItem item = new TreeItem(tree, SWT.NONE);
                item.setText(category.getTitle());
                item.setData(category);
            } else {
                notPlacedCategories.add(category);
            }
        }

        for (Category cat : creatableCategories) {
            if (cat.getParentId() == null) {
                TreeItem item = new TreeItem(tree, SWT.NONE);
                item.setText(cat.getTitle());
                item.setData(cat);
            } else {
                notPlacedCategories.add(cat);
            }
        }

        // enable drawing
        tree.setRedraw(true);
        // subcategories.
        if (notPlacedCategories.size() > 0) {
            addCategory(Arrays.asList(tree.getItems()), tree.getItemCount(), notPlacedCategories,
                    categories);
        }
    }

    private void addCategory(final List<TreeItem> items, final int itemCount,
            final List<Category> placeAbleCategories, final List<Category> allCategories) {
        
        int newItemCount = itemCount;
        
        // TODO not really worksome and test and tree has to be sprayed at default.
        List<Category> removable = new ArrayList<Category>();
        List<TreeItem> newItems = new ArrayList<TreeItem>(items);
        for (Category placeable : placeAbleCategories) {

            for (TreeItem item : items) {
                Category cat = (Category) item.getData();
                if (placeable.getParentId().equals(cat.getId())) {
                    TreeItem treeItem = new TreeItem(item, SWT.NONE);
                    treeItem.setText(placeable.getTitle());
                    treeItem.setData(placeable);
                    newItems.add(treeItem);
                    newItemCount++;
                    removable.add(placeable);
                    break;
                }
            }
        }
        if (newItemCount < allCategories.size()) {
            placeAbleCategories.removeAll(removable);
            addCategory(newItems, newItemCount, placeAbleCategories, allCategories);
        }
    }

    /**
     * Getter for checked category.
     * 
     * @return {@link Category}
     */
    public Category getCheckedCategory() {
        return checkedCategory;
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

    private void addNewCategory(final String id, final String title, final String desc,
            final String iconPath, final String parentId) {
        creatableCategories.add(new Category(id, title, desc, iconPath,
                parentId.equals(NO_PARENT) ? null : parentId));
        updateCategoryTree();
    }

    private void updateCategoryTree() {
        // FIXME looses all dependencies and checked elements before.
        categoryTree.removeAll();
        fillTree(categoryTree);

    }

    /**
     * Getter for categories that have to be created.
     * 
     * @return {@link List} of {@link String}s
     */
    public List<Category> getCreatableCategories() {
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
