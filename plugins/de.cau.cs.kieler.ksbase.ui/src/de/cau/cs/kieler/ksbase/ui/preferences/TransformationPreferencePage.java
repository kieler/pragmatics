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
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Locale;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ControlEditor;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.contexts.IContextService;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.eclipse.ui.dialogs.ResourceSelectionDialog;
import org.eclipse.ui.internal.contexts.ContextService;

import de.cau.cs.kieler.ksbase.core.EditorTransformationSettings;
import de.cau.cs.kieler.ksbase.core.KSBasEMenuContribution;
import de.cau.cs.kieler.ksbase.core.Transformation;
import de.cau.cs.kieler.ksbase.core.TransformationManager;
import de.cau.cs.kieler.ksbase.ui.menus.DynamicMenuContributions;

/**
 * The KSBasE transformation preference page.
 * 
 * The preference page is used to modify existing extensions only! Due to
 * technical restrictions, it is not possible to add new settings here. If you'd
 * like to create features for a new editor please use the
 * 'ksbase.configuration' extension point provided by this project
 * 
 * @author Michael Matzen
 */
@SuppressWarnings("restriction")
public class TransformationPreferencePage extends PreferencePage
        implements IWorkbenchPreferencePage {

    /**
     * A content provider for the MenuTreeView used in this page.
     * 
     * @author Michael Matzen
     * 
     */
    private static final class MenuTreeViewContentProvider implements ITreeContentProvider {

        /**
         * Returns all the children elements for the given object.
         * 
         * @param parentElement
         *            The object to evaluate
         */
        public Object[] getChildren(final Object parentElement) {
            if (parentElement instanceof KSBasEMenuContribution) {
                KSBasEMenuContribution parent = (KSBasEMenuContribution) parentElement;
                Object[] children =
                        new Object[parent.getCommands().size() + parent.getMenus().size()];
                System.arraycopy(
                        parent.getMenus().toArray(new Object[parent.getMenus().size()]), 0,
                        children, 0, parent.getMenus().size());
                System.arraycopy(parent.getCommands().toArray(
                        new Object[parent.getCommands().size()]), 0, children, parent
                        .getMenus().size(), parent.getCommands().size());
                return children;
            }
            return new Object[0];
        }

        /**
         * Returns the parent of the given object.
         * 
         * @param element
         *            The object to evaluate
         */
        public Object getParent(final Object element) {
            // do we need this ? hope not :P
            return null;
        }

        /**
         * Returns true if the given element has children. Thats only the case
         * if it is a {@link KSBasEMenuContribution}
         * 
         * @param parentElement
         *            The object to evaluate
         */
        public boolean hasChildren(final Object element) {
            if (element instanceof KSBasEMenuContribution) {
                return true;
            }
            return false;
        }

        /**
         * Returns all elements of the given input element.
         * 
         * @param inputElement
         *            The object to evaluate
         */
        public Object[] getElements(final Object inputElement) {
            if (inputElement instanceof String) {
                return new Object[0];
            } else if (inputElement instanceof KSBasEMenuContribution) {
                return getChildren(inputElement);
            } else if (inputElement instanceof LinkedList<?>) {
                LinkedList<?> elementList = (LinkedList<?>) inputElement;

                return elementList.toArray(new Object[elementList.size()]);
            }
            return new Object[0];
        }

        /**
         * Diposes the content provider.
         */
        public void dispose() {
        }

        /**
         * Called when the input changed.
         */
        public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
            /*
             * if (oldInput != null) {
             * 
             * } if (newInput != null) {
             * 
             * }
             */
        }

    }

    /**
     * A label provider for the MenuTreeView used in this page.
     * 
     * @author Michael Matzen
     * 
     */
    private static final class MenuTreeViewLabelProvider implements ILabelProvider {

        /**
         * Returns the image of the given element. Since we dont want images,
         * returns 'null'.
         * 
         * @param element
         *            The object to evaluate
         */
        public Image getImage(final Object element) {
            return null;
        }

        /**
         * Returns the text of the given element.
         * 
         * @param element
         *            The object to evaluate
         */
        public String getText(final Object element) {
            if (element instanceof KSBasEMenuContribution) {
                return ((KSBasEMenuContribution) element).getData();
            } else if (element instanceof String) {
                return (String) element;
            }
            return null;
        }

        /**
         * Adds a label provider listener.
         * 
         * @param element
         *            The object to evaluate
         */
        public void addListener(final ILabelProviderListener listener) {

        }

        /**
         * Disposes the label provider.
         */
        public void dispose() {

        }

        /**
         * Checks if the given property is a property which is used to display
         * the label of the given element.
         * 
         * @param element
         *            The object to evaluate
         * @param property
         *            The property to check
         */
        public boolean isLabelProperty(final Object element, final String property) {
            if (element instanceof KSBasEMenuContribution
                    && property.equals("data") || element instanceof String) {
                return true;
            }
            return false;
        }

        /**
         * Removes a listener from the queue.
         * 
         * @param listener
         *            the listener to remove
         */
        public void removeListener(final ILabelProviderListener listener) {
        }

    }

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
     * The classes which have to be implemented/extended by a class to be used
     * as an editor.
     **/
    protected static final String[] DIAGRAM_EDITORS =
            new String[] {"org.eclipse.gmf.runtime."
                    + "diagram.ui.resources.editor.parts.DiagramDocumentEditor" }; //$NON-NLS-1$
    /** The list of classes that provide ecore packages. **/
    protected static final String[] DIAGRAM_PACKAGES =
            new String[] {"org.eclipse.emf." + "ecore.EPackage" }; //$NON-NLS-1$
    /** The diagram document editor defining the diagram to use. **/
    protected DiagramDocumentEditor diagram;
    /** Text boxes. **/
    protected Text sfMetaModel, sfContext;
    /** Combo boxes. **/
    protected Combo cbEditors;
    /** Buttons. **/
    protected Button btContext, btBrowseXtend, btModelPackage, btEditorAdd, btEditorDel;
    /** The file editor used to select an icon from the project folder. **/
    FileFieldEditor dfDefaultIcon;
    /** The currently selected editor. **/
    protected EditorTransformationSettings activeEditor;
    /** The table used to display the existing transformation. **/
    protected Table table;
    /** The treeViewer used to display the menus. **/
    protected TreeViewer menuTreeViewer;
    /** Composites used to layout the preference page. **/
    Composite tableComp, browserContainer, btComp;
    /**
     * The transformation manager instance used in this class so we don't have
     * to get the instance every time.
     **/
    private TransformationManager manager;

    /**
     * Default constructor. Sets the preference page title.
     */
    public TransformationPreferencePage() {
        // Strangely, this does not set the title of the pref page
        super(Messages.kSBasETPreferencePageTitle);
        manager = TransformationManager.INSTANCE;
    }

    /**
     * Creates the contents of the preference page.
     * 
     * @param parent
     *            The parent of this preference page.
     * @return The created controls.
     */
    @Override
    protected Control createContents(final Composite parent) {
        // Since the title wont be display for setTitle oder super() we are
        // simply
        // inserting a label
        new Label(parent, SWT.NONE).setText(Messages.kSBasETPreferencePageTitle);

        Composite container = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout(2, false);

        Label editorLabel = new Label(container, SWT.NONE);
        editorLabel.setText(Messages.kSBasEPreferencePageEditorSelectionTitle);
        cbEditors = new Combo(container, SWT.DROP_DOWN | SWT.READ_ONLY);
        cbEditors.addSelectionListener(new SelectionListener() {

            public void widgetDefaultSelected(final SelectionEvent e) {
            }

            /**
             * Handles the selection of an editor from the combo box.
             */
            public void widgetSelected(final SelectionEvent e) {
                EditorTransformationSettings editor =
                        manager.getEditorByName(((Combo) e.getSource()).getText());
                activeEditor = editor;
                if (activeEditor != null) { // Load editor settings
                    sfMetaModel.setText(editor.getModelPackageClass());
                    sfContext.setText(editor.getContext());
                    dfDefaultIcon.setStringValue(editor.getDefaultIcon());
                    // Fill table with transformations
                    table.removeAll();
                    for (Transformation t : editor.getTransformations()) {
                        TableItem tItem = new TableItem(table, SWT.NONE);

                        tItem.setText(new String[] {
                                t.getTransformationId(), t.getName(), t.getTransformationName(),
                                t.getIcon(), t.getKeyboardShortcut() });

                    }
                    // Fill menu tree viewer
                    if (menuTreeViewer != null
                            && menuTreeViewer.getContentProvider() != null
                            && activeEditor.getMenuContributions() != null
                            && activeEditor.getMenuContributions().size() > 0) {
                        menuTreeViewer.setInput(activeEditor.getMenuContributions());
                    }

                    // enable controls
                    sfMetaModel.setEnabled(true);
                    browserContainer.setEnabled(true);
                    tableComp.setEnabled(true);
                    btBrowseXtend.setEnabled(true);
                    btContext.setEnabled(true);
                    table.setEnabled(true);
                    cbEditors.setEnabled(true);

                }
            }

        });

        new Label(container, SWT.NONE);
        Composite buttonContainer = new Composite(container, SWT.NONE);

        btEditorAdd = new Button(buttonContainer, SWT.NONE);
        btEditorAdd.setText("Add Editor");
        btEditorAdd.addSelectionListener(new SelectionListener() {

            public void widgetSelected(final SelectionEvent arg0) {
                IConfigurationElement[] elements =
                        Platform.getExtensionRegistry().getConfigurationElementsFor(
                                "org.eclipse.ui.editors");

                LinkedList<String> gmfEditors = new LinkedList<String>();
                for (IConfigurationElement element : elements) {
                    gmfEditors.add(element.getAttribute("class"));
                }
                ElementListSelectionDialog dlg =
                        new ElementListSelectionDialog(getShell(), new LabelProvider());
                dlg.setTitle("Select editor");
                dlg.setMessage("Select a diagram editor");
                dlg.setElements(gmfEditors.toArray());
                dlg.setAllowDuplicates(false);
                dlg.setMatchEmptyString(true);
                dlg.setMultipleSelection(false);
                dlg.setEmptyListMessage("No editor found, please check your workspace settings.");

                if (dlg.open() == ElementListSelectionDialog.OK) {
                    EditorTransformationSettings editor =
                            new EditorTransformationSettings(dlg.getFirstResult().toString());
                    manager.addEditor(editor);
                    readEditors();
                }

            }

            public void widgetDefaultSelected(final SelectionEvent arg0) {
            }
        });

        btEditorDel = new Button(buttonContainer, SWT.NONE);
        btEditorDel.setText("Delete Editor");
        btEditorDel.addSelectionListener(new SelectionListener() {

            public void widgetSelected(final SelectionEvent arg0) {
                // TODO Auto-generated method stub

            }

            public void widgetDefaultSelected(final SelectionEvent arg0) {
            }
        });
        buttonContainer.setLayout(new GridLayout(2, false));
        buttonContainer.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, false, false));

        GridData gData = new GridData(SWT.FILL, SWT.BEGINNING, true, false);
        cbEditors.setLayoutData(gData);

        browserContainer = new Composite(parent, SWT.NONE);
        browserContainer.setLayout(new GridLayout(3, true));

        new Label(browserContainer, SWT.NONE).setText(Messages.kSBasEPreferencePageModelPackage);
        sfMetaModel = new Text(browserContainer, SWT.SINGLE | SWT.BORDER | SWT.READ_ONLY);
        sfMetaModel.setEnabled(false);
        btModelPackage = new Button(browserContainer, SWT.NONE);
        btModelPackage.setText(Messages.kSBasEPreferencePageButtonBrowse);
        btModelPackage.addSelectionListener(new SelectionListener() {

            public void widgetDefaultSelected(final SelectionEvent e) {
            }

            /**
             * Handles the 'browse model package' event. Shows a simple
             * FileDialog in which a file can be selected
             */
            public void widgetSelected(final SelectionEvent e) {
                assert (activeEditor != null);
                IConfigurationElement[] elements =
                        Platform.getExtensionRegistry().getConfigurationElementsFor(
                                "org.eclipse.emf.ecore.generated_package");
                ArrayList<String> editors = new ArrayList<String>();

                for (int i = 0; i < elements.length; ++i) {

                    if (elements[i] != null && elements[i].getAttribute("class") != null) {
                        editors.add(elements[i].getAttribute("class"));
                    }
                }

                ElementListSelectionDialog dlg =
                        new ElementListSelectionDialog(getShell(), new LabelProvider());
                dlg.setTitle("Select Package");
                dlg.setMessage("Select a Package");
                dlg.setElements(editors.toArray());
                dlg.setAllowDuplicates(false);
                dlg.setMatchEmptyString(true);
                dlg.setMultipleSelection(false);
                dlg.setEmptyListMessage("No Package found, please check your workspace settings.");

                if (dlg.open() == ElementListSelectionDialog.OK) {

                    String modelPackage = ((String) dlg.getFirstResult()).split("@")[0];
                    // only add a diagram once !
                    if (manager.getUserDefinedEditorByName(modelPackage) == null) {
                        activeEditor.setModelPackageClass(modelPackage);
                        cbEditors.notifyListeners(SWT.Selection, null);
                    }
                }
            }

        });
        sfMetaModel.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));

        new Label(browserContainer, SWT.NONE).setText("Diagram Context:");
        sfContext = new Text(browserContainer, SWT.SINGLE | SWT.BORDER | SWT.READ_ONLY);
        btContext = new Button(browserContainer, SWT.NONE);
        btContext.setText(Messages.kSBasEPreferencePageButtonBrowse);
        btContext.addSelectionListener(new SelectionListener() {

            public void widgetSelected(final SelectionEvent e) {
                assert (activeEditor != null);
                Collection<?> definedContexts =
                        ((ContextService) PlatformUI.getWorkbench().getService(
                                IContextService.class)).getDefinedContextIds();
                ElementListSelectionDialog dlg =
                        new ElementListSelectionDialog(getShell(), new LabelProvider());
                dlg.setTitle("Select diagram context");
                dlg.setMessage("Select a context");
                dlg.setElements(definedContexts.toArray());
                dlg.setAllowDuplicates(false);
                dlg.setMatchEmptyString(true);
                dlg.setMultipleSelection(false);
                dlg.setEmptyListMessage("No context found, please check your workspace settings");

                if (dlg.open() == ElementListSelectionDialog.OK) {
                    activeEditor.setContext((String) dlg.getFirstResult());
                    cbEditors.notifyListeners(SWT.Selection, null);
                }
            }

            public void widgetDefaultSelected(final SelectionEvent e) {
            }

        });
        sfContext.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));

        dfDefaultIcon =
                new FileFieldEditor(
                        Messages.kSBasEPreferencePageDefaultIconName,
                        Messages.kSBasEPreferencePageDefaultIcon, browserContainer);
        dfDefaultIcon.setFileExtensions(new String[] {
                Messages.kSBasEPreferencePageIconExtensionPNG,
                Messages.kSBasEPreferencePageIconExtensionICO });

        browserContainer.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
        browserContainer.setEnabled(false);

        container.setLayout(layout);
        container.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, false, false));

        tableComp = new Composite(parent, SWT.NONE);
        tableComp.setLayout(new GridLayout(1, false));
        Label tableLabel = new Label(tableComp, SWT.NONE);
        tableLabel.setText(Messages.kSBasEPreferencePageTableTitle);

        table = new Table(tableComp, SWT.BORDER | SWT.SINGLE);

        table.setHeaderVisible(true);
        table.setLinesVisible(true);

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
                final Transformation transformation =
                        activeEditor.getTransformations().get(table.indexOf(row));
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
                                cbEditors.notifyListeners(SWT.Selection, null);
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
                        cbEditors.notifyListeners(SWT.Selection, null);
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
                                cbEditors.notifyListeners(SWT.Selection, null);
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
        btComp.setLayout(new GridLayout(4, false));

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
                                        activeEditor.setExtFile(contentBuffer.toString(), true);
                                    }
                                } catch (IOException exce) {
                                    System.err.println("KSBasE configuration exception:"
                                            + " Can't read Xtend file");
                                } catch (CoreException exce) {
                                    System.err.println("Invalid file.");
                                    exce.printStackTrace();
                                }
                                readEditors();
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

        Composite menuComposite = new Composite(parent, SWT.NONE);
        menuComposite.setLayout(new GridLayout(2, true));
        // Treeview used for display the menu structure
        menuTreeViewer = new TreeViewer(menuComposite);
        menuTreeViewer.setContentProvider(new MenuTreeViewContentProvider());
        menuTreeViewer.setLabelProvider(new MenuTreeViewLabelProvider());
        menuComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        Composite menuButtonComposite = new Composite(parent, SWT.NONE);
        menuButtonComposite.setLayout(new GridLayout(3, true));
        menuButtonComposite.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, true));
        readEditors();
        return null;
    }

    /**
     * Reads the existing editors from the manager and inserts them to the
     * editor list.
     */
    private void readEditors() {
        if (manager.getEditors() != null) {
            for (EditorTransformationSettings s : manager.getUserDefinedEditors()) {
                cbEditors.add(s.getEditor());
            }
            if (cbEditors.getItemCount() > 0) {
                cbEditors.select(0);
                cbEditors.notifyListeners(SWT.Selection, null);
            }
        }
    }

    /**
     * Initializes the preference page.
     * 
     * @param workbench
     *            The workbench for this preference page
     */
    public final void init(final IWorkbench workbench) {
        // nothing to do here because we are loading the settings when
        // activating KSBasE-UI.
    }

    @Override
    protected void performApply() {
        if (activeEditor != null) {
            activeEditor.setModelPackageClass(sfMetaModel.getText());
            activeEditor.setContext(sfContext.getText());
            activeEditor.setDefaultIcon(dfDefaultIcon.getStringValue());
        }
        super.performApply();
    }

    /**
     * Performs an 'OK' command. i.e. stores the settings for the currently
     * selected editor.
     * 
     * @return False if an error occurred while storing the settings.
     */
    @Override
    public boolean performOk() {
        manager.storeUserDefinedTransformations();
        DynamicMenuContributions.INSTANCE.createMenuForEditors(manager.getUserDefinedEditors());

        return super.performOk();
    }
}
