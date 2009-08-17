package de.cau.cs.kieler.ksbase.ui;

import java.net.URI;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.internal.core.SourceType;
import org.eclipse.jdt.ui.IJavaElementSearchConstants;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ControlEditor;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.dialogs.SelectionDialog;

import de.cau.cs.kieler.ksbase.KSBasEPlugin;
import de.cau.cs.kieler.ksbase.transformations.EditorTransformationSettings;
import de.cau.cs.kieler.ksbase.transformations.Transformation;
import de.cau.cs.kieler.ksbase.transformations.TransformationManager;

@SuppressWarnings("restriction")
public class KSBasEPreferencePage extends PreferencePage implements
        IWorkbenchPreferencePage {

    private class CheckBoxDialog extends Dialog {

        protected Object result;
        Button btMenu, btContext, btToolbar, btBalloon;

        public CheckBoxDialog(Shell parent, int style) {
            super(parent, style);
        }

        public Object open(boolean[] initalSelections) {
            if (initalSelections == null | initalSelections.length != 4)
                return null;

            Shell parent = getParent();
            final Shell shell = new Shell(parent, SWT.DIALOG_TRIM
                    | SWT.APPLICATION_MODAL);
            shell.setText("Select visibility");

            Composite pane = new Composite(shell, SWT.NONE);
            pane.setLayout(new GridLayout(4, false));

            new Label(pane, SWT.NONE).setText("Show in Menu");
            btMenu = new Button(pane, SWT.CHECK);
            btMenu.setSelection(initalSelections[0]);

            new Label(pane, SWT.NONE).setText("Show in Context");
            btContext = new Button(pane, SWT.CHECK);
            btContext.setSelection(initalSelections[1]);

            new Label(pane, SWT.NONE).setText("Show in Toolbar");
            btToolbar = new Button(pane, SWT.CHECK);
            btToolbar.setSelection(initalSelections[2]);

            new Label(pane, SWT.NONE).setText("Show in Balloon");
            btBalloon = new Button(pane, SWT.CHECK);
            btBalloon.setSelection(initalSelections[3]);
            new Label(pane, SWT.NONE);

            Button btCancel = new Button(pane, SWT.NONE);
            btCancel.setText("Cancel");
            btCancel.addSelectionListener(new SelectionListener() {

                public void widgetDefaultSelected(SelectionEvent e) {
                    // TODO Auto-generated method stub

                }

                public void widgetSelected(SelectionEvent e) {
                    result = null;
                    shell.dispose();
                }

            });
            Button btOK = new Button(pane, SWT.NONE);
            btOK.setText("OK");
            btOK.addSelectionListener(new SelectionListener() {

                public void widgetDefaultSelected(SelectionEvent e) {
                }

                public void widgetSelected(SelectionEvent e) {
                    result = new boolean[] { btMenu.getSelection(),
                            btContext.getSelection(), btToolbar.getSelection(),
                            btBalloon.getSelection() };
                    shell.dispose();
                }

            });
            shell.setLayout(new GridLayout(1, false));
            shell.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
            // pane.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
            shell.layout();
            shell.pack();
            shell.open();

            Display display = parent.getDisplay();

            while (!shell.isDisposed()) {
                if (!display.readAndDispatch())
                    display.sleep();
            }

            return result;
        }
    }

    private class XtendEditorDialog extends Dialog {

        protected Object result;
        protected Text textEditor;

        public XtendEditorDialog(Shell parent, int style) {
            super(parent, style);
            result = false;
        }

        public XtendEditorDialog(Shell parent) {
            super(parent, 0);
        }

        public Object open(String initialText) {
            Shell parent = getParent();
            final Shell shell = new Shell(parent, SWT.DIALOG_TRIM
                    | SWT.APPLICATION_MODAL);
            shell.setText("Modify Transformations");

            Composite pane = new Composite(shell, SWT.NONE);
            GridLayout layout = new GridLayout(1, false);
            GridData layoutData = new GridData();

            textEditor = new Text(pane, SWT.MULTI);
            textEditor.setText(initialText);
            textEditor.setSize(500, 400);
            textEditor.setTextLimit(Text.LIMIT);
            textEditor.setBounds(0, 0, 500, 400);
            layoutData.horizontalAlignment = SWT.FILL;
            layoutData.verticalAlignment = SWT.FILL;
            layoutData.grabExcessHorizontalSpace = true;
            layoutData.grabExcessVerticalSpace = true;
            textEditor.setLayoutData(layoutData);

            Composite buttonPane = new Composite(shell, SWT.NONE);
            GridLayout buttonLayout = new GridLayout(2, true);
            Button btCancel = new Button(buttonPane, SWT.NONE);
            btCancel.setText("Cancel");
            btCancel.addSelectionListener(new SelectionListener() {

                public void widgetDefaultSelected(SelectionEvent e) {
                    // TODO Auto-generated method stub

                }

                public void widgetSelected(SelectionEvent e) {
                    result = null;
                    shell.dispose();
                }

            });
            Button btOK = new Button(buttonPane, SWT.NONE);
            btOK.setText("OK");
            btOK.addSelectionListener(new SelectionListener() {

                public void widgetDefaultSelected(SelectionEvent e) {
                }

                public void widgetSelected(SelectionEvent e) {
                    result = textEditor.getText();
                    shell.dispose();
                }

            });
            buttonPane.setLayout(buttonLayout);
            shell.setLayout(new GridLayout(1, false));
            shell.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

            pane.setLayout(layout);
            pane.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
            // shell.layout();
            // shell.pack();
            shell.setSize(500, 500);
            shell.open();

            Display display = parent.getDisplay();

            while (!shell.isDisposed()) {
                if (!display.readAndDispatch())
                    display.sleep();
            }
            return result;

        }

    }

    protected Text sfMetaModel, sfMenu, sfMenuLoc, sfToolbarLoc;
    protected Combo cbEditors;
    protected Button bfShowMenu, bfShowToolbar, bfShowPopup, bfShowBalloon;
    FileFieldEditor dfDefaultIcon, btTableRemove, btTableAdd;
    protected EditorTransformationSettings activeEditor;
    protected Table table;
    Composite tableComp, browserContainer, btComp;

    public KSBasEPreferencePage() {
        setDescription("Preferences for the KIELER Structure Based Editing Features.");
    }

    @Override
    protected Control createContents(Composite parent) {
        Composite container = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout(2, false);

        Label editorLabel = new Label(container, SWT.NONE);
        editorLabel.setText("Editor :");
        cbEditors = new Combo(container, SWT.DROP_DOWN | SWT.READ_ONLY);
        cbEditors.addSelectionListener(new SelectionListener() {

            public void widgetDefaultSelected(SelectionEvent e) {
            }

            public void widgetSelected(SelectionEvent e) {
                EditorTransformationSettings editor = TransformationManager
                        .getEditorByName(((Combo) e.getSource()).getText());
                activeEditor = editor;
                sfMetaModel.setText(editor.getModelURI());
                sfMenu.setText(editor.getMenuName());
                sfMenuLoc.setText(editor.getMenuLocation());
                sfToolbarLoc.setText(editor.getToolbarLocation());
                bfShowMenu.setSelection(editor.isShownInMenu());
                bfShowPopup.setSelection(editor.isShownInContext());
                bfShowToolbar.setSelection(editor.isShownIToolbar());
                bfShowBalloon.setSelection(editor.isShownInBalloon());
                dfDefaultIcon.setStringValue(editor.getDefaultIconURI()
                        .toString());

                table.removeAll();
                for (Transformation t : editor.getTransformations()) {
                    TableItem tItem = new TableItem(table, SWT.NONE);
                    String showIn = "";
                    if (t.getVisiblity() == 15)
                        showIn = "All";
                    else {
                        if (t.isShownInMenu())
                            showIn = "Menu";
                        if (t.isShownInContext()) {
                            if (showIn.length() > 0)
                                showIn += "|";
                            showIn += "Popup";
                        }
                        if (t.isShownIToolbar()) {
                            if (showIn.length() > 0)
                                showIn += "|";
                            showIn += "Toolbar";
                        }
                        if (t.isShownInBalloon()) {
                            if (showIn.length() > 0)
                                showIn += "|";
                            showIn += "Balloon";
                        }
                    }
                    tItem.setText(new String[] { t.getName(),
                            t.getTransformation(), showIn,
                            t.getPartConfgList(),
                            String.valueOf(t.getNumSelections()),
                            t.getIconString() });
                }

                sfMetaModel.setEnabled(true);
                sfMenu.setEnabled(true);
                sfMenuLoc.setEnabled(true);
                sfToolbarLoc.setEnabled(true);
                browserContainer.setEnabled(true);
                tableComp.setEnabled(true);
                btComp.setEnabled(true);
                bfShowMenu.setEnabled(true);
                bfShowToolbar.setEnabled(true);
                bfShowPopup.setEnabled(true);
                bfShowBalloon.setEnabled(true);
            }

        });
        new Label(container, SWT.NONE);

        GridData gData = new GridData(SWT.FILL, SWT.BEGINNING, true, true);
        cbEditors.setLayoutData(gData);

        Composite editContainer = new Composite(container, SWT.NONE);
        editContainer.setLayout(new RowLayout());
        Button btAddEditor = new Button(editContainer, SWT.RIGHT);
        btAddEditor.setText("Add");
        btAddEditor.addSelectionListener(new SelectionListener() {

            public void widgetDefaultSelected(SelectionEvent e) {
            }

            public void widgetSelected(SelectionEvent e) {
                try {
                    SelectionDialog dlg;
                    dlg = JavaUI
                            .createTypeDialog(
                                    getShell(),
                                    new ProgressMonitorDialog(getShell()),
                                    SearchEngine.createWorkspaceScope(),
                                    IJavaElementSearchConstants.CONSIDER_CLASSES_AND_INTERFACES,
                                    false);

                    if (dlg.open() == SelectionDialog.OK
                            && dlg.getResult().length > 0) {
                        String res = ((SourceType) dlg.getResult()[0])
                                .getFullyQualifiedName();
                        EditorTransformationSettings editor = TransformationManager
                                .addEditor(res);
                        cbEditors.add(editor.getEditor());
                        cbEditors.select(cbEditors.indexOf(editor.getEditor()));
                        cbEditors.notifyListeners(SWT.Selection, null);
                    }
                } catch (JavaModelException exc) {
                    exc.printStackTrace();
                }
            }

        });
        Button btModifyEditor = new Button(editContainer, SWT.RIGHT);
        btModifyEditor.setText("Edit");
        btModifyEditor.addSelectionListener(new SelectionListener() {

            public void widgetDefaultSelected(SelectionEvent e) {
                // TODO Auto-generated method stub

            }

            public void widgetSelected(SelectionEvent e) {
                if (cbEditors.getText().length() > 0) {
                    try {
                        SelectionDialog dlg;
                        dlg = JavaUI
                                .createTypeDialog(
                                        getShell(),
                                        new ProgressMonitorDialog(getShell()),
                                        SearchEngine.createWorkspaceScope(),
                                        IJavaElementSearchConstants.CONSIDER_CLASSES_AND_INTERFACES,
                                        false);
                        List<String> selected = new LinkedList<String>();
                        selected.add(cbEditors.getText());
                        dlg.setInitialElementSelections(selected);
                        if (dlg.open() == SelectionDialog.OK
                                && dlg.getResult().length > 0) {
                            String res = ((SourceType) dlg.getResult()[0])
                                    .getFullyQualifiedName();
                            TransformationManager.getEditorByName(
                                    cbEditors.getText()).setEditor(res);
                            cbEditors.notifyListeners(SWT.Selection, null);
                        }
                    } catch (JavaModelException exc) {
                        exc.printStackTrace();
                    }
                }
            }

        });

        new Label(container, SWT.NONE).setText("Editors Model URI");
        sfMetaModel = new Text(container, SWT.SINGLE | SWT.BORDER);
        sfMetaModel.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true,
                true));
        sfMetaModel.setEnabled(false);

        new Label(container, SWT.NONE).setText("Menu Name");
        sfMenu = new Text(container, SWT.SINGLE | SWT.BORDER);
        sfMenu.setTextLimit(50);
        sfMenu.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, true));
        sfMenu.setEnabled(false);

        new Label(container, SWT.NONE).setText("Menu location");
        sfMenuLoc = new Text(container, SWT.SINGLE | SWT.BORDER);
        sfMenuLoc.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true,
                true));
        sfMenuLoc.setEnabled(false);

        new Label(container, SWT.NONE).setText("Toolbar location");
        sfToolbarLoc = new Text(container, SWT.SINGLE | SWT.BORDER);
        sfToolbarLoc.setTextLimit(Text.LIMIT);
        sfToolbarLoc.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true,
                true));
        sfToolbarLoc.setEnabled(false);

        browserContainer = new Composite(parent, SWT.NONE);
        browserContainer.setLayout(new GridLayout(3, false));

        dfDefaultIcon = new FileFieldEditor("dfDefaultIcon", "Default icon",
                browserContainer);
        dfDefaultIcon.setFileExtensions(new String[] { "*.png", "*.ico" });

        browserContainer.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING,
                true, true));
        browserContainer.setEnabled(false);

        bfShowMenu = new Button(container, SWT.CHECK);
        bfShowMenu.setText("Show Menu");
        bfShowMenu.setSelection(true);
        bfShowMenu.setEnabled(false);

        bfShowToolbar = new Button(container, SWT.CHECK);
        bfShowToolbar.setText("Show Toolbar");
        bfShowToolbar.setSelection(true);
        bfShowToolbar.setEnabled(false);

        bfShowPopup = new Button(container, SWT.CHECK);
        bfShowPopup.setText("Show Popup");
        bfShowPopup.setSelection(true);
        bfShowPopup.setEnabled(false);

        bfShowBalloon = new Button(container, SWT.CHECK);
        bfShowBalloon.setText("Show Balloon");
        bfShowBalloon.setSelection(true);
        bfShowBalloon.setEnabled(false);

        container.setLayout(layout);
        container.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, false,
                false));

        tableComp = new Composite(parent, SWT.NONE);
        tableComp.setLayout(new GridLayout(1, false));
        Label tableLabel = new Label(tableComp, SWT.NONE);
        tableLabel.setText("Configure Transformations");

        table = new Table(tableComp, SWT.BORDER | SWT.SINGLE);

        table.setHeaderVisible(true);
        table.setLinesVisible(true);

        TableColumn[] titleCols = new TableColumn[5];
        titleCols[0] = new TableColumn(table, SWT.NONE);
        titleCols[0].setText("Name");
        titleCols[0].setToolTipText("Name of menu entry");
        titleCols[0].setWidth(150);
        titleCols[1] = new TableColumn(table, SWT.NONE);
        titleCols[1].setText("Transformation");
        titleCols[1].setToolTipText("Name of the xtend method");
        titleCols[1].setWidth(150);
        titleCols[2] = new TableColumn(table, SWT.NONE);
        titleCols[2].setText("visibleIn");
        titleCols[2].setToolTipText("Visibility of entry");
        titleCols[2].setWidth(75);
        titleCols[3] = new TableColumn(table, SWT.NONE);
        titleCols[3].setText("SelectedElements");
        titleCols[3]
                .setToolTipText("Diagram element types for which the transformation is defined");
        titleCols[3].setWidth(150);
        titleCols[4] = new TableColumn(table, SWT.NONE);
        titleCols[4].setText("Selections");
        titleCols[4].setToolTipText("Number of selected Elements");
        titleCols[4].setWidth(60);
        titleCols[4] = new TableColumn(table, SWT.NONE);
        titleCols[4].setText("Icon");
        titleCols[4].setWidth(50);

        // Cursor to edit Table cells
        final TableCursor cursor = new TableCursor(table, SWT.NONE);
        final ControlEditor editor = new ControlEditor(cursor);
        editor.grabHorizontal = true;
        editor.grabVertical = true;

        cursor.addSelectionListener(new SelectionListener() {
            public void widgetDefaultSelected(SelectionEvent e) {
                table.setSelection(new TableItem[] { cursor.getRow() });
            }

            public void widgetSelected(SelectionEvent e) {

                TableItem row = cursor.getRow();
                int col = cursor.getColumn();
                Transformation transformation = activeEditor
                .getTransformations().get(table.indexOf(row));
                
                if (col == 0 || col == 4) { //Text editor
                    final Text text = new Text(cursor, SWT.NONE);
                    text.addKeyListener(new KeyAdapter() {

                        @Override
                        public void keyPressed(KeyEvent e) {
                            if (e.character == SWT.CR) {
                                TableItem innerRow = cursor.getRow();
                                int col = cursor.getColumn();
                                if ( col == 4) { //Number of selections has to be an int-value
                                    try {
                                        Integer.parseInt(text.getText());
                                        innerRow.setText(col, text.getText());
                                    }
                                    catch (NumberFormatException exec) {
                                        //ignore invalid input
                                    }
                                }
                                else {
                                    innerRow.setText(col, text.getText());
                                }
                                text.dispose();
                            }

                        }
                    });
                    text.addFocusListener(new FocusAdapter() {
                        public void focusLost(FocusEvent e) {
                            text.dispose();
                        }
                    });
                    text.setText(row.getText(col));
                    editor.setEditor(text);
                    text.setFocus();
                } else if (col == 1) { // Transformation
                    XtendEditorDialog dlg = new XtendEditorDialog(getShell());
                    Object result = dlg.open(row.getText(col));
                    if (result != null) {
                        row.setText(col, (String) result);
                    }
                } else if (col == 2) { // VisibleIn
                    CheckBoxDialog dlg = new CheckBoxDialog(getShell(),
                            SWT.NONE);
                    row.getText(col);
                    Object result = dlg.open(new boolean[] { transformation.isShownInMenu(),
                            transformation.isShownInContext(), transformation.isShownIToolbar(),
                            transformation.isShownInBalloon() });
                    if (result != null) {
                        boolean[] bRes = (boolean[]) result;
                        int visible = 0;
                        if (bRes[0])
                            visible += KSBasEPlugin.SHOW_MENU;
                        if (bRes[1])
                            visible += KSBasEPlugin.SHOW_CONTEXT;
                        if (bRes[2])
                            visible += KSBasEPlugin.SHOW_TOOLBAR;
                        if (bRes[3])
                            visible += KSBasEPlugin.SHOW_BALLOON;

                        transformation.setVisibility(visible);
                        cbEditors.notifyListeners(SWT.Selection, null);
                    }
                } else if (col == 3) { // Editors
                    try {
                        SelectionDialog openPartsEditor;
                        openPartsEditor = JavaUI
                                .createTypeDialog(
                                        getShell(),
                                        new ProgressMonitorDialog(getShell()),
                                        SearchEngine.createWorkspaceScope(),
                                        IJavaElementSearchConstants.CONSIDER_CLASSES_AND_INTERFACES,
                                        true);
                        List<String> selected = new LinkedList<String>();
                        
                        if ( transformation.getPartConfig() != null) {
                        for (String editor : transformation.getPartConfig())
                            selected.add(editor);
                        }

                        openPartsEditor.setInitialElementSelections(selected);
                        if (openPartsEditor.open() == SelectionDialog.OK
                                && openPartsEditor.getResult().length > 0) {
                            Object[] openPartsResult = openPartsEditor
                                    .getResult();
                            String[] res = new String[openPartsResult.length];
                            for (int i = 0; i < res.length; ++i) {
                                res[i] = ((SourceType) openPartsResult[i])
                                        .getFullyQualifiedName();
                            }
                            transformation.setPartConfig(res);
                            cbEditors.notifyListeners(SWT.Selection, null);
                        }
                    } catch (JavaModelException ex) {
                        ex.printStackTrace();
                    }
                }
                else { //Icon
                    FileDialog dlg = new FileDialog(getShell(),SWT.OPEN);
                    dlg.setFilterExtensions(new String[] {"*.png", "*.ico" });
                    String fileName = dlg.open();
                    if ( fileName != null) {
                        transformation.setIconURI(URI.create(fileName));
                        cbEditors.notifyListeners(SWT.Selection, null);
                    }
                }
            }
        });
        table.setLayoutData(new GridData(SWT.FILL, SWT.TOP | SWT.FILL, true,
                true));
        tableComp.setLayoutData(new GridData(SWT.FILL, SWT.TOP | SWT.FILL,
                true, true));
        tableComp.setEnabled(false);

        btComp = new Composite(parent, SWT.NONE);
        btComp.setLayout(new GridLayout(2, false));
        btComp.setEnabled(false);

        Button btTableAdd = new Button(btComp, SWT.NONE);
        btTableAdd.setText("Add Transformation");
        btTableAdd.addSelectionListener(new SelectionListener() {

            public void widgetDefaultSelected(SelectionEvent e) {
                // TODO Auto-generated method stub

            }

            public void widgetSelected(SelectionEvent e) {
                // TODO Auto-generated method stub
                XtendEditorDialog dlg = new XtendEditorDialog(getShell());
                Object result = dlg.open("");
                if (result != null) {
                    activeEditor.addTransformation(new Transformation(
                            "new Transformation", (String) result));
                    cbEditors.notifyListeners(SWT.Selection, null);
                }
            }

        });

        Button btTableRemove = new Button(btComp, SWT.NONE);
        btTableRemove.setText("Remove Transformation");
        btTableRemove.addSelectionListener(new SelectionListener() {

            public void widgetDefaultSelected(SelectionEvent e) {
                // TODO Auto-generated method stub

            }

            public void widgetSelected(SelectionEvent e) {
                if (table.getSelectionIndex() > -1) {
                    activeEditor
                            .removeTransformation(table.getSelectionIndex());
                    cbEditors.notifyListeners(SWT.Selection, null);
                }
            }

        });

        if (TransformationManager.getEditors() != null) {
            for (EditorTransformationSettings s : TransformationManager
                    .getEditors()) {
                cbEditors.add(s.getEditor());
            }
            cbEditors.select(0);
            cbEditors.notifyListeners(SWT.Selection, null);
        }
        return null;
    }

    public void init(IWorkbench workbench) {
        setPreferenceStore(KSBasEPlugin.getDefault().getPreferenceStore());
    }

    @Override
    public boolean performOk() {
        if (activeEditor != null) {
            activeEditor.setModelURI(sfMetaModel.getText());
            activeEditor.setMenuName(sfMenu.getText());
            activeEditor.setMenuLocation(sfMenuLoc.getText());
            activeEditor.setToolbarLocation(sfToolbarLoc.getText());
            int flags = 0;
            if (bfShowMenu.getSelection())
                flags |= KSBasEPlugin.SHOW_MENU;
            if (bfShowPopup.getSelection())
                flags |= KSBasEPlugin.SHOW_CONTEXT;
            if (bfShowToolbar.getSelection())
                flags |= KSBasEPlugin.SHOW_TOOLBAR;
            if (bfShowBalloon.getSelection())
                flags |= KSBasEPlugin.SHOW_BALLOON;

            activeEditor.setVisibilityFlags(flags);
            activeEditor.setDefaultIconURI(URI.create(dfDefaultIcon
                    .getStringValue()));
            TransformationManager.storeTransformations();
        }
        return super.performOk();
    }
}
