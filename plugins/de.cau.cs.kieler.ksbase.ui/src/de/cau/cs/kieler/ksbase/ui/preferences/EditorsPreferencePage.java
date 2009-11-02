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

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

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
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.contexts.IContextService;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.eclipse.ui.internal.contexts.ContextService;

import de.cau.cs.kieler.ksbase.core.EditorTransformationSettings;
import de.cau.cs.kieler.ksbase.core.KSBasEMenuContribution;
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
public class EditorsPreferencePage extends PreferencePage
        implements IWorkbenchPreferencePage {

    private static final int MENU_BUTTON_GRID_LAYOUT = 3;
    private static final int BROWSER_CONTAINER_GRIDLAYOUT = 3;

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
    protected Button btContext, btModelPackage, btEditorAdd, btEditorDel;
    /** The file editor used to select an icon from the project folder. **/
    FileFieldEditor dfDefaultIcon;
    /** The currently selected editor. **/
    protected static EditorTransformationSettings activeEditor;
    /** The treeViewer used to display the menus. **/
    protected TreeViewer menuTreeViewer;
    /** Composites used to layout the preference page. **/
    protected Composite browserContainer;
    /**
     * The transformation manager instance used in this class so we don't have
     * to get the instance every time.
     **/
    private TransformationManager manager;

    /**
     * Default constructor. Sets the preference page title.
     */
    public EditorsPreferencePage() {
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
        // simply inserting a label
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
                EditorsPreferencePage.setActiveEditor(editor);
                if (activeEditor != null) { // Load editor settings
                    sfMetaModel.setText(editor.getModelPackageClass());
                    sfContext.setText(editor.getContext());
                    dfDefaultIcon.setStringValue(editor.getDefaultIcon());
                    
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
                    btContext.setEnabled(true);
                    cbEditors.setEnabled(true);
                    //enable transformation page!
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
                manager.removeEditor(activeEditor.getEditor());
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
        browserContainer.setLayout(new GridLayout(BROWSER_CONTAINER_GRIDLAYOUT, true));

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

        
        Composite menuComposite = new Composite(parent, SWT.NONE);
        menuComposite.setLayout(new GridLayout(2, true));
        // Treeview used for display the menu structure
        menuTreeViewer = new TreeViewer(menuComposite);
        menuTreeViewer.setContentProvider(new MenuTreeViewContentProvider());
        menuTreeViewer.setLabelProvider(new MenuTreeViewLabelProvider());
        menuComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        Composite menuButtonComposite = new Composite(parent, SWT.NONE);
        menuButtonComposite.setLayout(new GridLayout(MENU_BUTTON_GRID_LAYOUT, true));
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
    
    /**
     * Gets the active editor.
     * Called by the {@link TransformationPreferencePage}
     * @return The active editor, may return null if no editor available or selected
     */
    protected static EditorTransformationSettings getActiveEditor() {
        return activeEditor;
    }
    
    /**
     * Sets the currently active editor.
     * @param editor The new active editor
     */
    public static void setActiveEditor(final EditorTransformationSettings editor) {
        activeEditor = editor;
    }
}
