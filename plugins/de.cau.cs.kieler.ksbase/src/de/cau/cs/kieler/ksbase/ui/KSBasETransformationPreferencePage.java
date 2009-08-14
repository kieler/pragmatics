package de.cau.cs.kieler.ksbase.ui;

import javax.naming.InitialContext;

import org.eclipse.internal.xtend.xtend.XtendFile;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.preference.PreferenceStore;
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
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import de.cau.cs.kieler.ksbase.KSBasEPlugin;

public class KSBasETransformationPreferencePage extends PreferencePage
        implements IWorkbenchPreferencePage {

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



    protected Table table;
    
    @Override
    protected Control createContents(final Composite parent) {

        Composite container = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout(1, false);

        Label tableLabel = new Label(container, SWT.NONE);
        tableLabel.setText("Configure Transformations");

         table = new Table(container, SWT.BORDER | SWT.SINGLE);

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

        // Read from Editor.Props.Transformations
        // I will need some providers here to parse from Transformation.class
        // Restrict transformation command string display length
        TableItem[] func1 = new TableItem[5];
        func1[0] = new TableItem(table, SWT.NONE);
        func1[0].setText(new String[] { "add connected state",
                "Void addConnected...", "ALL", "StateEditPart, State2EditPart",
                "1", "" });
        func1[1] = new TableItem(table, SWT.NONE);
        func1[1].setText(new String[] { "flip transition",
                "Void flipTransition...", "Menu",
                "TransitionPart, Transition2EditPart", "1", "" });
        func1[2] = new TableItem(table, SWT.NONE);
        func1[2].setText(new String[] { "add transition between states",
                "Void addTransition...", "Context, Menu",
                "StateEditPart,State2EditPart", "2", "" });

        // Cursor to edit Table cells
        final TableCursor cursor = new TableCursor(table, SWT.NONE);
        final ControlEditor editor = new ControlEditor(cursor);
        editor.grabHorizontal = true;
        editor.grabVertical = true;
        // FileDialog for selecting icon file

        cursor.addSelectionListener(new SelectionListener() {

            public void widgetDefaultSelected(SelectionEvent e) {
                table.setSelection(new TableItem[] { cursor.getRow() });
            }

            public void widgetSelected(SelectionEvent e) {

                TableItem row = cursor.getRow();
                int col = cursor.getColumn();
                if (col == 0 || col == 4) {
                    final Text text = new Text(cursor, SWT.NONE);
                    text.addKeyListener(new KeyAdapter() {

                        @Override
                        public void keyPressed(KeyEvent e) {
                            if (e.character == SWT.CR) {
                                TableItem innerRow = cursor.getRow();
                                int col = cursor.getColumn();
                                innerRow.setText(col, text.getText());
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
                } else if (col == 1) {
                    XtendEditorDialog dlg = new XtendEditorDialog(parent
                            .getShell());
                    Object result = dlg.open(row.getText(col));
                    if (result != null) {
                        row.setText(col, (String) result);
                    }
                }
            }

        });
        table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
        table.pack();

        Button btEdit = new Button(container, SWT.NONE);
        btEdit.setText("Add Transformation");
        btEdit.addSelectionListener(new SelectionListener() {

            public void widgetDefaultSelected(SelectionEvent e) {
                // TODO Auto-generated method stub

            }

            public void widgetSelected(SelectionEvent e) {
                // TODO Auto-generated method stub
                XtendEditorDialog dlg = new XtendEditorDialog(parent.getShell());
                dlg.open("");
            }

        });
        // -> show Editor

        container.setLayout(layout);
        container.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, false,
                false));
        // TODO: Validate on OK => No empty fields !
        return null;
    }

    public void init(IWorkbench workbench) {
        setPreferenceStore(KSBasEPlugin.getDefault().getPreferenceStore());
    }
    
    @Override
    public boolean performOk() {
        
        return super.performOk();
    }
}
/*
 * Jimmy Shi wrote:

    Hi,
    I want to store some plug-in specific data into the workspace, I think the .metadata/.plugins/.myplugin folder is a nice place to store the data. How can I do it? 


If you want to use a org.eclipse.core.runtime.Preferences object to store simple name/value pairs, just call getPluginPreferences() and savePluginPreferences() on your Plugin class. See the Javadoc of Preferences to see what it can do.
If you want to store files, you can call getStateLocation() to get an IPath that represents that plugin's metadata state location, and then do various things with that (like create/read directories and/or files).
 */
/*
//Opens the ext file in the default editor:
IWorkbenchPage page= PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
URI fileLoc = URI.create("/home/mim/runtime-New_configuration/aabb/feature.ext");
IFileStore fileStore = EFS.getLocalFileSystem().getStore(fileLoc);
try {
	IDE.openEditorOnFileStore(page, fileStore);
} catch (PartInitException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
}
*/

