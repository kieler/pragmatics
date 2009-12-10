/**
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
 * 
 *****************************************************************************/
package de.cau.cs.kieler.ksbase.ui.preferences;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Locale;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
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
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.dialogs.PreferencesUtil;
import org.eclipse.ui.dialogs.ResourceSelectionDialog;

import de.cau.cs.kieler.ksbase.core.EditorTransformationSettings;
import de.cau.cs.kieler.ksbase.core.Transformation;
import de.cau.cs.kieler.ksbase.ui.KSBasEUIPlugin;

/**
 * The post-transformation preference page.
 * Contains a list of existing effects which can be 
 * executed after a transformation has been executed.
 * The effects can be ordered with a simply priority scheme.
 * 
 * @author Michael Matzen - mim AT informatik.uni-kiel.de
 *
 */
public class TransformationPreferencePage extends PreferencePage
        implements IWorkbenchPreferencePage {

    private static final int BUTTON_CONTAINER_GRIDSIZE = 4;
    /** The table used to display the existing transformation. **/
    private  Table table;
    /** Composites used to layout the preference page. **/
    private Composite tableComp, btComp;
    /**
     * The currently selected editor (retrieved from the
     * {@link EditorsPreferencePage}).
     **/
    private  EditorTransformationSettings activeEditor;
    /** The 'Browse Xtend File' Button. **/
    private Button btBrowseXtend;

    /** Number constants. **/
    private static final int TABLE_COL_NORMAL = 75;
    private static final int TABLE_COL_MIN = 50;
    private static final int TABLE_COL_LARGE = 150;
    private static final int TABLE_COL_TRANSFORMATION = 2;
    private static final int TABLE_COLS = 5;
    private static final int TABLE_COL_NAME = 1;
    private static final int TABLE_COL_ID = 0;
    private static final int TABLE_COL_ICON = 3;
    private static final int TABLE_COL_KEYBOARD = 4;
    private static final int KEYCHAR_MAX = 122;
    private static final int KEYCHAR_MIN = 97;

    /**
     * Constructor for the preference page.
     */
    public TransformationPreferencePage() {
        // ...not working for sub pages ....
        // super("On this page you can set the transformations for the selected Editor");
    }

    /**
     * Creates the controls of this preference page.
     * 
     * @param parent
     *            The parent composite
     * @return The control created
     */
    @Override
    protected Control createContents(final Composite parent) {
        activeEditor = EditorsPreferencePage.getActiveEditor();

        if (activeEditor == null) {
            // Insert a link to the editor preference page and do not display
            // anything more.
            Link l = new Link(parent, SWT.NONE);
            l.setText("No valid editor has been selected,"
                    + " please return to the <A>editor settings page</A>");
            l.addSelectionListener(new SelectionListener() {

                public void widgetSelected(final SelectionEvent e) {
                    PreferencesUtil.createPreferenceDialogOn(
                            getShell(), "de.cau.cs.kieler.ksbase.EditorPreferencePage", null, null);
                }

                public void widgetDefaultSelected(final SelectionEvent e) {
                }
            });
            noDefaultAndApplyButton();
            return null;
        } else {
            new Label(parent, SWT.NONE).setText(Messages.kSBasETPreferencePageTitle);
        }
        tableComp = new Composite(parent, SWT.NONE);
        tableComp.setLayout(new GridLayout(1, false));
        Label tableLabel = new Label(tableComp, SWT.NONE);
        tableLabel.setText(Messages.kSBasEPreferencePageTableTitle);

        table = new Table(tableComp, SWT.BORDER | SWT.SINGLE);

        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        // Fill table with transformations
        table.removeAll();
        if (activeEditor.getTransformations() != null) {
            for (Transformation t : activeEditor.getTransformations()) {
                TableItem tItem = new TableItem(table, SWT.NONE);

                tItem.setText(new String[] {
                        t.getTransformationId(), t.getName(), t.getExtension(),
                        t.getIcon(), t.getKeyboardShortcut() });

            }
        }

        table.setEnabled(true);

        TableColumn[] titleCols = new TableColumn[TABLE_COLS];

        titleCols[TABLE_COL_ID] = new TableColumn(table, SWT.NONE);
        titleCols[TABLE_COL_ID].setText(Messages.kSBasEPreferencePageTableColId);
        titleCols[TABLE_COL_ID].setToolTipText(Messages.kSBasEPreferencePageTableColIdToolTip);
        titleCols[TABLE_COL_ID].setWidth(TABLE_COL_LARGE);

        titleCols[TABLE_COL_NAME] = new TableColumn(table, SWT.NONE);
        titleCols[TABLE_COL_NAME].setText(Messages.kSBasEPreferencePageTableColName);
        titleCols[TABLE_COL_NAME].setToolTipText(Messages.kSBasEPreferencePageTableColNameToolTip);
        titleCols[TABLE_COL_NAME].setWidth(TABLE_COL_LARGE);

        titleCols[TABLE_COL_TRANSFORMATION] = new TableColumn(table, SWT.NONE);
        titleCols[TABLE_COL_TRANSFORMATION]
                .setText(Messages.kSBasEPreferencePageTableColTransformation);
        titleCols[TABLE_COL_TRANSFORMATION]
                .setToolTipText(Messages.kSBasEPreferencePageTableColTransformationToolTip);
        titleCols[TABLE_COL_TRANSFORMATION].setWidth(TABLE_COL_LARGE);

        titleCols[TABLE_COL_ICON] = new TableColumn(table, SWT.NONE);
        titleCols[TABLE_COL_ICON].setText(Messages.kSBasEPreferencePageTableColIcon);
        titleCols[TABLE_COL_ICON].setWidth(TABLE_COL_MIN);

        titleCols[TABLE_COL_KEYBOARD] = new TableColumn(table, SWT.NONE);
        titleCols[TABLE_COL_KEYBOARD].setText(Messages.kSBasEPreferencePageTableColShortcut);
        titleCols[TABLE_COL_KEYBOARD].setWidth(TABLE_COL_NORMAL);
        titleCols[TABLE_COL_KEYBOARD]
                .setToolTipText(Messages.kSBasEPreferencePageTableColShortcutToolTip);

        // Cursor to edit Table cells
        final TableCursor cursor = new TableCursor(table, SWT.NONE);
        final ControlEditor editor = new ControlEditor(cursor);
        editor.grabHorizontal = true;
        editor.grabVertical = true;

        cursor.addSelectionListener(new SelectionListener() {
            public void widgetDefaultSelected(final SelectionEvent e) {
                table.setSelection(new TableItem[] {cursor.getRow() });
            }

            /**
             * Handles the event of selecting a table entry
             */
            public void widgetSelected(final SelectionEvent e) {
                assert (activeEditor != null);
                TableItem row = cursor.getRow();
                int col = cursor.getColumn();
                Collection<Transformation> transformations = activeEditor.getTransformations();
                final Transformation transformation =
                        activeEditor.getTransformations().toArray(new Transformation[transformations.size()])[table.indexOf(row)];
                if (col == TABLE_COL_ID || col == TABLE_COL_NAME) {
                    // Name and Id can be edited directly
                    final Text text = new Text(cursor, SWT.NONE);
                    text.addKeyListener(new KeyAdapter() {

                        public void keyPressed(final KeyEvent e) {
                            if (e.character == SWT.CR) {
                                int col = cursor.getColumn();
                                if (col == TABLE_COL_ID) {
                                    // TODO: Check for duplicate Id's
                                    transformation.setTransformationId(text.getText());
                                } else if (col == TABLE_COL_NAME) {
                                    transformation.setName(text.getText());
                                }
                                text.dispose();
                                // TODO:
                                // cbEditors.notifyListeners(SWT.Selection,
                                // null);
                            }

                        }
                    });
                    text.addFocusListener(new FocusAdapter() {
                        public void focusLost(final FocusEvent e) {
                            int col = cursor.getColumn();
                            if (col == 0) {
                                transformation.setTransformationId(text.getText());
                            } else if (col == 1) {
                                transformation.setName(text.getText());
                            }
                            text.dispose();
                        }
                    });
                    text.setText(row.getText(col));
                    editor.setEditor(text);
                    text.setFocus();
                } else if (col == TABLE_COL_ICON) { // Icon
                    FileDialog dlg = new FileDialog(getShell(), SWT.OPEN);
                    dlg.setFilterExtensions(new String[] {
                            Messages.kSBasEPreferencePageIconExtensionPNG,
                            Messages.kSBasEPreferencePageIconExtensionICO });
                    String fileName = dlg.open();
                    if (fileName != null) {
                        transformation.setIcon(fileName);
                        // TODO: cbEditors.notifyListeners(SWT.Selection, null);
                    }
                } else if (col == TABLE_COL_KEYBOARD) { // Keyboard shortcut
                    // We are using two text fields here
                    // one for storing the actual shortcut text including
                    // modifiers ('text') and one
                    // for storing the raw pressed keys ('keys') without
                    // modifiers
                    final Text text = new Text(cursor, SWT.NONE);
                    // We can't use a string here 'cause we need a final
                    // variable which is defined
                    // in the enclosing type and we really don't need another
                    // class variable

                    // FIXME: Adapt behavior of key binding pref page
                    final Text keys = new Text(cursor, SWT.NONE);
                    text.addKeyListener(new KeyAdapter() {

                        public void keyPressed(final KeyEvent e) {
                            if (e.keyCode == SWT.DEL || e.keyCode == SWT.BS) {
                                text.setText(""); //$NON-NLS-1$
                                keys.setText(""); //$NON-NLS-1$
                            } else {
                                if (e.keyCode >= KEYCHAR_MIN && e.keyCode <= KEYCHAR_MAX) { // only
                                    // allow
                                    // characters
                                    String ex = ""; //$NON-NLS-1$
                                    if (ex.length() > 0) {
                                        ex += "\u002B"; //$NON-NLS-1$
                                    }
                                    if ((e.stateMask & SWT.CTRL) != 0) {
                                        ex += Messages.kSBasEPreferencePageShortcutCTRL;
                                    }
                                    if ((e.stateMask & SWT.ALT) != 0) {
                                        ex += Messages.kSBasEPreferencePageShortcutALT;
                                    }
                                    if ((e.stateMask & SWT.SHIFT) != 0) {
                                        ex += Messages.kSBasEPreferencePageShortcutSHIFT;
                                    }
                                    if (keys.getText().length() > 0) {
                                        ex += keys.getText();
                                    }

                                    keys.append(String.valueOf((char) e.keyCode) + " "); //$NON-NLS-1$

                                    ex += (char) e.keyCode;
                                    text.setText(ex.toUpperCase(Locale.getDefault()));
                                }
                            }
                        }
                    });
                    text.addFocusListener(new FocusAdapter() {
                        public void focusLost(final FocusEvent e) {

                            if (text.getText().length() > 0) {
                                transformation.setKeyboardShortcut(text.getText());
                                // TODO:
                                // cbEditors.notifyListeners(SWT.Selection,
                                // null);
                            }
                            text.dispose();
                            keys.dispose();
                        }
                    });
                    text.setText(row.getText(col));
                    editor.setEditor(text);
                    text.setFocus();
                }
            }
        });

        table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        tableComp.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        tableComp.setEnabled(false);

        btComp = new Composite(parent, SWT.NONE);
        btComp.setLayout(new GridLayout(BUTTON_CONTAINER_GRIDSIZE, false));

        btBrowseXtend = new Button(btComp, SWT.NONE);
        btBrowseXtend.setText("Load Xtend File");
        btBrowseXtend.setEnabled(false);
        btBrowseXtend.addSelectionListener(new SelectionListener() {

            public void widgetDefaultSelected(final SelectionEvent e) {
            }

            /**
             * Handles the 'browse xtend file' event Shows a simple FileDialog
             * in which a file can be selected
             */
            public void widgetSelected(final SelectionEvent e) {
                assert (activeEditor != null);
                MessageBox box = new MessageBox(getShell(), SWT.YES | SWT.NO);
                box.setMessage("Loading an xtend file may reset the "
                        + "existing transformations.\r\n Do you want continue?");
                box.setText(Messages.kSBasEPreferencePageEditTransformationsTitle);
                if (box.open() == SWT.YES) {
                    ResourceSelectionDialog rsd =
                            new ResourceSelectionDialog(
                                    getShell(), ResourcesPlugin.getWorkspace().getRoot(),
                                    Messages.kSBasEPreferencePageXtendFileDialogTitle);
                    rsd.setBlockOnOpen(true);

                    if (rsd.open() == ResourceSelectionDialog.OK) {
                        Object[] results = rsd.getResult();
                        if (results != null && results.length == 1 && results[0] instanceof IFile) {

                            IFile file = (IFile) results[0];

                            if (file == null) {
                                box = new MessageBox(getShell(), SWT.OK);
                                box.setMessage(Messages.kSBasEPreferencePageXtendFileInvalidFile);
                                box.open();
                                return;
                            } else {
                                StringBuffer contentBuffer = new StringBuffer();
                                try {
                                    InputStream in = file.getContents();
                                    while (in.available() > 0) {
                                        contentBuffer.append((char) in.read());

                                    }
                                    if (contentBuffer != null) {
                                        activeEditor.setExtFile(contentBuffer.toString());
                                        //TODO: Enable parsing:
                                        activeEditor.parseTransformations(true,null);
                                    }
                                } catch (IOException exce) {
                                    KSBasEUIPlugin.getDefault().logError("Can't read Xtend file.");
                                } catch (CoreException exce) {
                                    KSBasEUIPlugin.getDefault().logError("Can't read Xtend file.");
                                }
                                // TODO: readEditors();
                            }
                        } else {
                            box = new MessageBox(getShell(), SWT.OK);
                            box.setMessage("Invalid selection please select a single "
                                    + "file that contains the transformations");
                            box.setText(Messages.kSBasEPreferencePageXtendFileInvalidFile);
                            box.open();
                            return;
                        }
                    }
                }
            }

        });

        btComp.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

        return null;
    }

    /**
     * Initializes the preference page.
     * @param workbench The workbench object to use.
     */
    public void init(final IWorkbench workbench) {
    }

}
