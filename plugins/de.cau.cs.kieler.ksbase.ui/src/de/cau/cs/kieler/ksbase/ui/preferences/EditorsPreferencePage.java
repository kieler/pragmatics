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

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.LinkedList;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
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
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.dialogs.ResourceSelectionDialog;
import org.eclipse.ui.model.BaseWorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;

import de.cau.cs.kieler.ksbase.core.EditorTransformationSettings;
import de.cau.cs.kieler.ksbase.core.Transformation;
import de.cau.cs.kieler.ksbase.core.TransformationManager;
import de.cau.cs.kieler.ksbase.ui.KSBasEUIPlugin;

/**
 * The KSBasE transformation preference page.
 * 
 * The preference page is used to modify existing extensions only! Due to technical restrictions, it
 * is not possible to add new settings here. If you'd like to create features for a new editor
 * please use the 'ksbase.configuration' extension point provided by this project
 * 
 * @author mim
 * 
 */
public class EditorsPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {

    private static final int GRIDSIZE_BROWSER_CONTAINER = 3;

    /**
     * The classes which have to be implemented/extended by a class to be used as an editor.
     **/
    static final String[] DIAGRAM_EDITORS = new String[] {"org.eclipse.gmf.runtime."
            + "diagram.ui.resources.editor.parts.DiagramDocumentEditor" }; //$NON-NLS-1$
    /** The list of classes that provide ecore packages. **/
    static final String[] DIAGRAM_PACKAGES = new String[] {"org.eclipse.emf.ecore.EPackage" };
    /** Text boxes. **/
    private Text sfMetaModel;
    /** Combo boxes. **/
    private Combo cbEditors;
    /** Buttons. **/
    private Button btModelPackage, btEditorAdd, btEditorDel, btLoadXtend;
    /** The currently selected editor. **/
    private static EditorTransformationSettings activeEditor;
    /** The treeViewer used to display the menus. **/
    private TreeViewer menuTreeViewer;
    /** Composites used to layout the preference page. **/
    private Composite browserContainer;
    /** List of transformations for this editor. **/
    private Table transformationTable;
    /**
     * The transformation manager instance used in this class so we don't have to get the instance
     * every time.
     **/
    private TransformationManager manager;

    /**
     * Default constructor. Sets the preference page title.
     */
    public EditorsPreferencePage() {
        setTitle(Messages.kSBasEEPreferencePageTitle);
        manager = TransformationManager.INSTANCE;
    }

    /**
     * Creates the editor setting part of the page.
     * 
     * @param parent
     *            The parent composite object
     */
    private void createEditorContent(final Composite parent) {
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
                EditorTransformationSettings editor = manager.getUserDefinedEditorById(((Combo) e
                        .getSource()).getText());
                EditorsPreferencePage.setActiveEditor(editor);
                if (activeEditor != null) { // Load editor settings
                    sfMetaModel.setText(editor.getModelPackageClass());
                    // Fill menu tree viewer
                    if (menuTreeViewer != null && menuTreeViewer.getContentProvider() != null
                            && activeEditor.getMenuContributions() != null
                            && activeEditor.getMenuContributions().size() > 0) {
                        menuTreeViewer.setInput(activeEditor.getMenuContributions());
                    }
                    transformationTable.removeAll();
                    for (Transformation t : activeEditor.getTransformations()) {
                        TableItem item = new TableItem(transformationTable, SWT.NONE);
                        item.setText(new String[] {"", t.getName(), t.getExtension() });
                        item.setChecked(t.isVisible());
                    }
                    transformationTable.redraw();
                    for (TableColumn col : transformationTable.getColumns()) {
                        col.pack();
                    }

                    // enable controls
                    sfMetaModel.setEnabled(true);
                    browserContainer.setEnabled(true);
                    btEditorDel.setEnabled(true);
                    cbEditors.setEnabled(true);
                    btLoadXtend.setEnabled(true);
                } else {
                    // clear texts
                    cbEditors.setText("");
                    cbEditors.clearSelection();
                    cbEditors.removeAll();
                    sfMetaModel.setText("");

                    // Disable controls
                    sfMetaModel.setEnabled(false);
                    browserContainer.setEnabled(false);
                    btEditorDel.setEnabled(false);
                    btLoadXtend.setEnabled(false);
                }
            }

        });

        new Label(container, SWT.NONE);
        Composite buttonContainer = new Composite(container, SWT.NONE);

        btEditorAdd = new Button(buttonContainer, SWT.NONE);
        btEditorAdd.setText("Add Editor");
        btEditorAdd.addSelectionListener(new SelectionListener() {

            public void widgetSelected(final SelectionEvent arg0) {
                IConfigurationElement[] elements = Platform.getExtensionRegistry()
                        .getConfigurationElementsFor("org.eclipse.ui.editors");

                LinkedList<String> gmfEditors = new LinkedList<String>();
                for (IConfigurationElement element : elements) {
                    gmfEditors.add(element.getAttribute("class"));
                }
                ElementListSelectionDialog dlg = new ElementListSelectionDialog(getShell(),
                        new LabelProvider());
                dlg.setTitle("Select editor");
                dlg.setMessage("Select a diagram editor");
                dlg.setElements(gmfEditors.toArray());
                dlg.setAllowDuplicates(false);
                dlg.setMatchEmptyString(true);
                dlg.setMultipleSelection(false);
                dlg.setEmptyListMessage("No editor found, please check your workspace settings.");

                if (dlg.open() == ElementListSelectionDialog.OK) {
                    EditorTransformationSettings editor = new EditorTransformationSettings(dlg
                            .getFirstResult().toString());
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
                manager.removeEditor(activeEditor.getEditorId());
                readEditors();
            }

            public void widgetDefaultSelected(final SelectionEvent arg0) {
            }
        });
        buttonContainer.setLayout(new GridLayout(2, false));
        buttonContainer.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, false, false));

        GridData gData = new GridData(SWT.FILL, SWT.BEGINNING, true, false);
        cbEditors.setLayoutData(gData);
        browserContainer = new Composite(parent, SWT.NONE);
        browserContainer.setLayout(new GridLayout(GRIDSIZE_BROWSER_CONTAINER, false));
        new Label(container, SWT.NONE).setText(Messages.kSBasEPreferencePageModelPackage);
        sfMetaModel = new Text(browserContainer, SWT.SINGLE | SWT.BORDER | SWT.READ_ONLY);
        sfMetaModel.setEnabled(false);
        btModelPackage = new Button(browserContainer, SWT.NONE);
        btModelPackage.setText(Messages.kSBasEPreferencePageButtonBrowse);
        btModelPackage.addSelectionListener(new SelectionListener() {

            public void widgetDefaultSelected(final SelectionEvent e) {
            }

            /**
             * Handles the 'browse model package' event. Shows a simple FileDialog in which a file
             * can be selected
             */
            public void widgetSelected(final SelectionEvent e) {
                assert (activeEditor != null);
                IConfigurationElement[] elements = Platform.getExtensionRegistry()
                        .getConfigurationElementsFor("org.eclipse.emf.ecore.generated_package");
                ArrayList<String> editors = new ArrayList<String>();

                for (int i = 0; i < elements.length; ++i) {

                    if (elements[i] != null && elements[i].getAttribute("class") != null) {
                        editors.add(elements[i].getAttribute("class"));
                    }
                }

                ElementListSelectionDialog dlg = new ElementListSelectionDialog(getShell(),
                        new LabelProvider());
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
                    if (manager.getUserDefinedEditorById(modelPackage) == null) {
                        activeEditor.setModelPackageClass(modelPackage);
                        cbEditors.notifyListeners(SWT.Selection, null);
                    }
                }
            }

        });
        sfMetaModel.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));

        browserContainer.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
        browserContainer.setEnabled(false);

        container.setLayout(layout);
        container.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, false, false));

    }

    /**
     * Creates the transformation part.
     */
    private void createTransformationContent(final Composite parent) {
        Composite tContainer = new Composite(parent, SWT.NONE);
        tContainer.setLayout(new GridLayout(1, true));
        new Label(tContainer, SWT.NONE);
        new Label(tContainer, SWT.NONE)
                .setText("Add operations to the editor by loading an Xtend file");

        transformationTable = new Table(tContainer, SWT.SINGLE | SWT.CHECK | SWT.BORDER);
        transformationTable.setLinesVisible(true);
        transformationTable.setHeaderVisible(true);

        TableColumn isVis = new TableColumn(transformationTable, SWT.CHECK);
        isVis.setText("Show");
        TableColumn tName = new TableColumn(transformationTable, SWT.NONE);
        tName.setText("Name");
        TableColumn tExt = new TableColumn(transformationTable, SWT.NONE);
        tExt.setText("Extension");

        // Cursor to edit Table cells
        final TableCursor cursor = new TableCursor(transformationTable, SWT.NONE);
        final ControlEditor editor = new ControlEditor(cursor);
        editor.grabHorizontal = true;
        editor.grabVertical = true;
        transformationTable.addSelectionListener(new SelectionListener() {

            public void widgetSelected(final SelectionEvent e) {
                if (e.item instanceof TableItem) {
                    TableItem item = (TableItem) e.item;
                    Transformation t = activeEditor.getTransformationByName(item.getText(2));
                    t.setVisible(item.getChecked());
                }
            }

            public void widgetDefaultSelected(final SelectionEvent e) {
                // TODO Auto-generated method stub

            }
        });

        cursor.addSelectionListener(new SelectionListener() {

            public void widgetSelected(final SelectionEvent e) {
                TableItem item = cursor.getRow();
                int col = cursor.getColumn();
                final Transformation t = activeEditor.getTransformationByName(item.getText(2));
                if (col == 0) {
                    // Check
                    Boolean stat = item.getChecked();
                    if (stat != null) {
                        t.setVisible(stat);
                    }
                } else if (col == 1) {
                    // Name

                    final Text text = new Text(cursor, SWT.NONE);
                    text.addKeyListener(new KeyAdapter() {

                        public void keyPressed(final KeyEvent e) {
                            if (e.character == SWT.CR) {
                                if (text.getText().length() > 0) {
                                    t.setName(text.getText());
                                }
                                text.dispose();
                                cbEditors.notifyListeners(SWT.Selection, null);
                            }

                        }
                    });

                    text.addFocusListener(new FocusAdapter() {
                        public void focusLost(final FocusEvent e) {
                            if (text.getText().length() > 0) {
                                t.setName(text.getText());
                            }
                            text.dispose();
                            cbEditors.notifyListeners(SWT.Selection, null);
                        }
                    });

                    text.setText(item.getText(col));
                    editor.setEditor(text);
                    text.setFocus();
                }
            }

            public void widgetDefaultSelected(final SelectionEvent e) {
                System.out.println("default sel");
            }
        });

        btLoadXtend = new Button(parent, SWT.PUSH);
        btLoadXtend.setText("Load Xtend file");
        btLoadXtend.addSelectionListener(new SelectionListener() {

            public void widgetSelected(final SelectionEvent e) {
                MessageBox box = new MessageBox(getShell(), SWT.YES | SWT.NO);
                box.setMessage("Loading an xtend file may reset the "
                        + "existing transformations.\r\n Do you want continue?");
                box.setText(Messages.kSBasEPreferencePageEditTransformationsTitle);
                if (box.open() == SWT.YES) {
                    ElementTreeSelectionDialog rsd = new ElementTreeSelectionDialog(getShell(),
                            new WorkbenchLabelProvider(), new BaseWorkbenchContentProvider());

                    rsd.setTitle("Xtend Selection");
                    rsd.setBlockOnOpen(true);
                    rsd.setInput(ResourcesPlugin.getWorkspace().getRoot());
                    rsd.addFilter(new ViewerFilter() {

                        @Override
                        public boolean select(final Viewer viewer, final Object parentElement,
                                final Object element) {
                            return (element instanceof IFile) ? ((IFile) element)
                                    .getFileExtension().equals("ext") : true;
                        }
                    });

                    int result = rsd.open();
                    if (result != ResourceSelectionDialog.CANCEL
                            && rsd.getFirstResult() instanceof IFile) {

                        IFile file = (IFile) rsd.getFirstResult();

                        if (file == null) {
                            box = new MessageBox(getShell(), SWT.OK);
                            box.setMessage(Messages.kSBasEPreferencePageXtendFileInvalidFile);
                            box.open();
                            return;
                        } else {
                            try {

                                BufferedInputStream bis = new BufferedInputStream(file
                                        .getContents());
                                StringBuffer sbuf = new StringBuffer();
                                while (bis.available() > 0) {
                                    sbuf.append((char) bis.read());
                                }
                                activeEditor.setExtFile(sbuf.toString());
                                activeEditor.parseTransformations(true, file.getLocationURI()
                                        .toURL());
                            } catch (MalformedURLException e1) {
                                KSBasEUIPlugin.getDefault().logError(
                                        "Could not find the Xtend file: Malformed URL");
                            } catch (CoreException e1) {
                                KSBasEUIPlugin.getDefault().logError("Could not read XtendFile");
                            } catch (IOException e1) {
                                KSBasEUIPlugin.getDefault().logError("Could not read XtendFile");
                            }
                            cbEditors.notifyListeners(SWT.Selection, null);
                        }
                    }
                }
            }

            public void widgetDefaultSelected(final SelectionEvent e) {
            }
        });
        btLoadXtend.setEnabled(false);
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
        // Since the title wont be display for setTitle or super(text) we are
        // simply inserting a label
        new Label(parent, SWT.NONE).setText(Messages.kSBasEEPreferencePageTitle);

        createEditorContent(parent);
        createTransformationContent(parent);
        readEditors();
        return null;
    }

    /**
     * Reads the existing editors from the manager and inserts them to the editor list.
     */
    private void readEditors() {
        if (manager.getEditors() != null) {
            for (EditorTransformationSettings s : manager.getUserDefinedEditors()) {
                cbEditors.add(s.getEditorId());
            }
            if (cbEditors.getItemCount() > 0) {
                cbEditors.select(0);
            }
            cbEditors.notifyListeners(SWT.Selection, null);
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
        }
        super.performApply();
    }

    /**
     * Performs an 'OK' command. i.e. stores the settings for the currently selected editor.
     * 
     * @return False if an error occurred while storing the settings.
     */
    @Override
    public boolean performOk() {
        manager.storeUserDefinedTransformations();
        return super.performOk();
    }

    /**
     * Gets the active editor. Called by the {@link TransformationPreferencePage}
     * 
     * @return The active editor, may return null if no editor available or selected
     */
    protected static EditorTransformationSettings getActiveEditor() {
        return activeEditor;
    }

    /**
     * Sets the new active editor. The new editor ''may be null'' this is used for some checks, so
     * the missing null-check is intended.
     * 
     * @param editor
     *            The new editor
     */
    protected static void setActiveEditor(final EditorTransformationSettings editor) {
        activeEditor = editor;
    }
}
