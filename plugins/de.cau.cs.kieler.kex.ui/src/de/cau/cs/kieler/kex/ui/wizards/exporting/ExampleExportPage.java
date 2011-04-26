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
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
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
    private static final int CATEGORY_MINLENGTH = 4;

    private static final int PAGE_MIN_WIDTH = 540;
    private static final int PAGE_MIN_HEIGHT = 600;

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
        composite.setLayout(new GridLayout());
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
        Group bottomGroup = new Group(composite, SWT.NONE);
        GridLayout bottomLayout = new GridLayout();
        bottomLayout.numColumns = THREE_COLUMNS;
        bottomGroup.setText("Set Preview Picture");
        bottomGroup.setToolTipText("Enter a picture like a screenshot of example diagram.");
        bottomGroup.setLayout(bottomLayout);
        bottomGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
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
                    picDialog.setFilterPath(choosenPath == null || choosenPath.length() < 2 ? WORKSPACE_DIR
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
        GridLayout buttonCompoLayout = new GridLayout();
        buttonCompoLayout.numColumns = THREE_COLUMNS;
        buttonCompo.setLayout(buttonCompoLayout);
        buttonCompo.setLayoutData(new GridData(GridData.FILL_BOTH));
        Button addCategory = new Button(buttonCompo, SWT.NONE);
        addCategory.setToolTipText("Create a new Example Category");
        addCategory.setText("New...");
        addCategory.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent event) {
                IInputValidator validator = new IInputValidator() {
                    public String isValid(final String newText) {
                        if (newText.length() < CATEGORY_MINLENGTH) {
                            return "Category name has to " + "have at least 4 characters.";
                        }
                        for (TreeItem item : categoryTree.getItems()) {
                            if (newText.equals(item.getText())) {
                                return "Category exists already! " + "Please enter another name.";
                            }
                        }

                        return null;

                    }
                };

                Dialog diag = new Dialog(getShell()) {

                    private Text titleText;
                    private Text idText;
                    private Text iconPath;
                    private Text descText;
                    private Combo parentCatCombo;

                    @Override
                    protected Control createDialogArea(final Composite parent) {
                        // Control createDialogArea = super.createDialogArea(parent);
                        Composite composite = new Composite(parent, SWT.BORDER);
                        GridLayout layout = new GridLayout();
                        layout.numColumns = 2;
                        composite.setLayout(layout);
                        composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
                        composite.setSize(PAGE_MIN_WIDTH, PAGE_MIN_HEIGHT);
                        // TODO meaningful tips for the fields.
                        Label idLab = new Label(composite, SWT.NONE);
                        idLab.setText("Id: ");
                        idLab.setToolTipText("");

                        idText = new Text(composite, SWT.BORDER);
                        idText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
                        idText.addModifyListener(new ModifyListener() {

                            public void modifyText(final ModifyEvent e) {
                                // TODO validate
                                // id should be substringed with points at breaks.
                                // all signs should be small
                                // and min. number is 4.

                            }
                        });
                        Label titleLab = new Label(composite, SWT.NONE);
                        titleLab.setText("Title: ");
                        titleLab.setToolTipText("");

                        titleText = new Text(composite, SWT.BORDER);
                        titleText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

                        // maybe listener for validation.

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
                        parentCatCombo.add("No Parent");
                        parentCatCombo.select(0);
                        // FIXME save performance, if loading categories only one time.
                        List<Category> categories = ExampleManager.get().getCategories();
                        for (Category cat : categories) {
                            parentCatCombo.add(cat.getId());
                            parentCatCombo.getText();
                        }

                        // final FileDialog picDialog = new FileDialog(bottomGroup.getShell());
                        // // ResourcesPlugin.getWorkspace().getRoot(), "Search a picture!"
                        // picDialog.setFilterPath(WORKSPACE_DIR);
                        // String[] extensions = { "*.png;*.jpg;*.gif" };
                        // picDialog.setFilterExtensions(extensions);
                        // Label label = new Label(bottomGroup, SWT.NONE);
                        // label.setText("Set Picture:");
                        // this.previewPic = new Text(bottomGroup, SWT.BORDER);
                        // previewPic.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
                        // Button browse = new Button(bottomGroup, SWT.NONE);
                        // browse.setText("Browse...");
                        // browse.addSelectionListener(new SelectionAdapter() {
                        //
                        // @Override
                        // public void widgetSelected(final SelectionEvent e) {
                        // super.widgetSelected(e);
                        // String pic = picDialog.open();
                        // if (pic != null) {
                        // getPreviewPic().setText(pic);
                        // }
                        // }

                        // });

                        Label descLab = new Label(composite, SWT.NONE);
                        descLab.setText("Description: ");
                        descText = new Text(composite, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL
                                | SWT.H_SCROLL);
                        return parent;
                    }

                    @Override
                    protected void okPressed() {
                        addNewCategory(idText.getText(), titleText.getText(), descText.getText(),
                                iconPath.getText(), parentCatCombo.getText());
                        super.okPressed();
                    }

                };
                diag.open();
                // InputDialog dialog = new InputDialog(getShell(), "Create New Category",
                // "Please enter a new Category.", "", validator) {
                // };
                // String value = dialog.getValue();
                // if (value != null && value.length() >= CATEGORY_MINLENGTH) {
                // TreeItem item = new TreeItem(categoryTree, SWT.NONE);
                // item.setText(value);
                // creatableCategories.add(value);
                // revertTree.setEnabled(checkedCategories.size() > 0
                // || creatableCategories.size() > 0);
                // }
            }
        });

        revertTreeButton = new Button(buttonCompo, SWT.NONE);
        revertTreeButton.setText("Revert");
        revertTreeButton.setEnabled(false);
        revertTreeButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent e) {
                categoryTree.removeAll();
                fillTree(categoryTree);
                creatableCategories.clear();
                revertTreeButton.setEnabled(false);
                super.widgetSelected(e);
            }
        });
    }

    private void createBottomGroup(final Composite composite) {
        Group middleGroup = new Group(composite, SWT.NONE);
        GridLayout middleLayout = new GridLayout();
        middleGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        middleLayout.numColumns = 1;
        middleGroup.setText("Add a category");
        middleGroup.setToolTipText("Please select a category.");
        middleGroup.setLayout(middleLayout);
        createCheckedTree(middleGroup);
        createButtonComposite(middleGroup);

    }

    private void createCheckedTree(final Composite parent) {
        this.categoryTree = new Tree(parent, SWT.CHECK | SWT.BORDER);
        GridData data = new GridData(GridData.FILL_BOTH);
        data.heightHint = HEIGHT_HINT;
        categoryTree.setLayoutData(data);

        // categoryTree.setExpanded(true);
        categoryTree.addListener(SWT.Selection, new Listener() {
            public void handleEvent(final Event event) {
                if (event.detail == SWT.CHECK) {
                    TreeItem item = ((TreeItem) event.item);
                    Category choosenCategory = (Category) item.getData();
                    checkedCategory = checkedCategory == choosenCategory ? null : choosenCategory;
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
        // disable drawing to avoid flicker
        tree.setRedraw(false);
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
        // enable drawing
        tree.setRedraw(true);
        // subcategories.
        if (notPlacedCategories.size() > 0) {
            addCategory(Arrays.asList(tree.getItems()), tree.getItemCount(), notPlacedCategories,
                    categories);
        }
    }

    private void addCategory(final List<TreeItem> items, int itemCount,
            final List<Category> placeAbleCategories, final List<Category> allCategories) {
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
                    itemCount++;
                    removable.add(placeable);
                    break;
                }
            }
        }
        if (itemCount < allCategories.size()) {
            placeAbleCategories.removeAll(removable);
            addCategory(newItems, itemCount, placeAbleCategories, allCategories);
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
        creatableCategories.add(new Category(id, title, desc, iconPath, parentId));
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
