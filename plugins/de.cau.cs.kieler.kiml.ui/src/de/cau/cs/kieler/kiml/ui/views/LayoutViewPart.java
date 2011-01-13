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
package de.cau.cs.kieler.kiml.ui.views;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.events.MenuAdapter;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.views.properties.IPropertySheetEntry;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySourceProvider;
import org.eclipse.ui.views.properties.PropertySheetEntry;
import org.eclipse.ui.views.properties.PropertySheetPage;

import de.cau.cs.kieler.core.ui.IGraphicalFrameworkBridge;
import de.cau.cs.kieler.core.util.Maybe;
import de.cau.cs.kieler.kiml.ILayoutConfig;
import de.cau.cs.kieler.kiml.LayoutProviderData;
import de.cau.cs.kieler.kiml.LayoutServices;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.ui.IEditorChangeListener;
import de.cau.cs.kieler.kiml.ui.KimlUiPlugin;
import de.cau.cs.kieler.kiml.ui.Messages;
import de.cau.cs.kieler.kiml.ui.layout.DiagramLayoutManager;
import de.cau.cs.kieler.kiml.ui.layout.EclipseLayoutConfig;
import de.cau.cs.kieler.kiml.ui.layout.EclipseLayoutServices;

/**
 * A view that displays layout options for selected objects.
 *
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @author msp
 */
public class LayoutViewPart extends ViewPart implements IEditorChangeListener {

    /** the view identifier. */
    public static final String VIEW_ID = "de.cau.cs.kieler.kiml.views.layout";
    /** preference identifier for enabling categories. */
    public static final String PREF_CATEGORIES = "view.categories";
    /** preference identifier for enabling advanced options. */
    public static final String PREF_ADVANCED = "view.advanced";
    
    /** default layout provider array, which is empty. */
    private static final LayoutProviderData[] DEFAULT_PROVIDER_DATA = new LayoutProviderData[0];
    
    /** the form container for the property sheet page. */
    private ScrolledForm form;
    /** the page that is displayed in this view part. */
    private PropertySheetPage page;
    /** the part listener that tracks the active editor part. */
    private IPartListener partListener;
    /** the currently tracked diagram editor. */
    private IEditorPart currentEditor;
    /** the currently used diagram layout manager. */
    private DiagramLayoutManager currentManager;
    /** the currently examined edit part. */
    private EditPart currentEditPart;
    /** the layout provider data for the currently displayed options. */
    private LayoutProviderData[] currentProviderData = DEFAULT_PROVIDER_DATA;
    
    /**
     * Finds the active layout view, if it exists.
     * 
     * @return the active layout view, or {@code null} if there is none
     */
    public static LayoutViewPart findView() {
        if (Display.getCurrent() == null) {
            final Maybe<LayoutViewPart> part = new Maybe<LayoutViewPart>();
            Display.getDefault().syncExec(new Runnable() {
                public void run() {
                    part.set(findViewUI());
                }
            });
            return part.get();
        } else {
            return findViewUI();
        }
    }
    
    /**
     * Finds the active layout view, if it exists. This method works only if called
     * from the user interface thread.
     * 
     * @return the active layout view, or {@code null} if there is none
     */
    private static LayoutViewPart findViewUI() {
        IWorkbenchWindow activeWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        if (activeWindow != null) {
            IWorkbenchPage activePage = activeWindow.getActivePage();
            if (activePage != null) {
                return (LayoutViewPart) activePage.findView(VIEW_ID);
            }
        }
        return null;
    }
    
    /**
     * Property source provider for the layout view part.
     */
    private class PropertySourceProvider implements IPropertySourceProvider {
        /** {@inheritDoc} */
        public IPropertySource getPropertySource(final Object object) {
            if (object instanceof EditPart) {
                ILayoutConfig layoutConfig = currentManager.getLayoutConfig((EditPart) object);
                EditingDomain editingDomain = currentManager.getBridge().getEditingDomain(object);
                if (layoutConfig != null && editingDomain instanceof TransactionalEditingDomain) {
                    currentEditPart = currentManager.getBridge().getEditPart(object);
                    LayoutProviderData contentProvider = layoutConfig.getContentLayouterData();
                    LayoutProviderData containerProvider = layoutConfig.getContainerLayouterData();
                    if (contentProvider == null && containerProvider == null) {
                        currentProviderData = DEFAULT_PROVIDER_DATA;
                    } else if (contentProvider == null) {
                        currentProviderData = new LayoutProviderData[] { containerProvider };
                    } else if (containerProvider == null) {
                        currentProviderData = new LayoutProviderData[] { contentProvider };
                    } else {
                        currentProviderData = new LayoutProviderData[] {
                                contentProvider, containerProvider };
                    }
                    return new LayoutPropertySource(layoutConfig,
                            (TransactionalEditingDomain) editingDomain);
                }
            }
            return null;
        }
    }
    
    /** margin width for the form layout. */
    private static final int MARGIN_WIDTH = 4;
    /** position for left attachment. */
    private static final int FORM_LEFT = 0;
    /** position for right attachment. */
    private static final int FORM_RIGHT = 100;
    /** position for top attachment. */
    private static final int FORM_TOP = 0;
    /** position for bottom attachment. */
    private static final int FORM_BOTTOM = 100;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void createPartControl(final Composite parent) {
        FormToolkit toolkit = new FormToolkit(parent.getDisplay());
        form = toolkit.createScrolledForm(parent);
        form.setText("");
        Composite content = form.getBody();
        FormLayout contentLayout = new FormLayout();
        contentLayout.marginWidth = MARGIN_WIDTH;
        content.setLayout(contentLayout);
        
        page = new PropertySheetPage();
        page.setRootEntry(new PropertySheetEntry());
        page.createControl(content);
        FormData formData = new FormData();
        formData.left = new FormAttachment(FORM_LEFT, 0);
        formData.right = new FormAttachment(FORM_RIGHT, 0);
        formData.top = new FormAttachment(FORM_TOP, 0);
        formData.bottom = new FormAttachment(FORM_BOTTOM, 0);
        page.getControl().setLayoutData(formData);
        page.setPropertySourceProvider(new PropertySourceProvider());
        IPreferenceStore preferenceStore = KimlUiPlugin.getDefault().getPreferenceStore();
        
        // add actions to the toolbar, view menu, and context menu
        IActionBars actionBars = getViewSite().getActionBars();
        page.setActionBars(actionBars);
        addPopupActions(page.getControl().getMenu());
        IMenuManager menuManager = actionBars.getMenuManager();
        menuManager.add(new RemoveOptionsAction(this, Messages.getString("kiml.ui.30")));
        IToolBarManager toolBarManager = actionBars.getToolBarManager();
        toolBarManager.add(new SelectionInfoAction(this, Messages.getString("kiml.ui.37")));
        // set the stored value of the categories button
        ActionContributionItem categoriesItem = (ActionContributionItem) actionBars
                .getToolBarManager().find("categories");
        if (categoriesItem != null) {
            categoriesItem.getAction().setChecked(preferenceStore.getBoolean(PREF_CATEGORIES));
            categoriesItem.getAction().run();
        }
        // set the stored value of the advanced button
        ActionContributionItem advancedItem = (ActionContributionItem) actionBars
                .getToolBarManager().find("filter");
        if (advancedItem != null) {
            advancedItem.getAction().setChecked(preferenceStore.getBoolean(PREF_ADVANCED));
            advancedItem.getAction().run();
        }
        
        IWorkbenchWindow workbenchWindow = getSite().getWorkbenchWindow();
        IWorkbenchPage activePage = workbenchWindow.getActivePage();
        if (activePage != null) {
            final IWorkbenchPart activePart = activePage.getActivePart();
            if (activePart != null) {
                workbenchWindow.getWorkbench().getDisplay().asyncExec(new Runnable() {
                    public void run() {
                        setInput(activePart);
                    }
                });
            }
        }
        
        partListener = new IPartListener() {
            public void partActivated(final IWorkbenchPart part) {
                setInput(part);
            }
            public void partDeactivated(final IWorkbenchPart part) {
            }
            public void partBroughtToTop(final IWorkbenchPart part) {
            }
            public void partClosed(final IWorkbenchPart part) {
                if (part == currentEditor) {
                    if (currentManager != null) {
                        currentManager.removeChangeListener(LayoutViewPart.this);
                        currentManager = null;
                    }
                    currentEditor = null;
                    currentEditPart = null;
                    currentProviderData = DEFAULT_PROVIDER_DATA;
                }
            }
            public void partOpened(final IWorkbenchPart part) {
            }
        };
        workbenchWindow.getPartService().addPartListener(partListener);  
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFocus() {
        page.setFocus();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose() {
        // store the current status of the categories button
        ActionContributionItem categoriesItem = (ActionContributionItem) getViewSite()
                .getActionBars().getToolBarManager().find("categories");
        if (categoriesItem != null) {
            KimlUiPlugin.getDefault().getPreferenceStore().setValue(PREF_CATEGORIES,
                    categoriesItem.getAction().isChecked());
        }
        // store the current status of the advanced button
        ActionContributionItem advancedItem = (ActionContributionItem) getViewSite()
                .getActionBars().getToolBarManager().find("filter");
        if (advancedItem != null) {
            KimlUiPlugin.getDefault().getPreferenceStore().setValue(PREF_ADVANCED,
                    advancedItem.getAction().isChecked());
        }
        // dispose the view part
        super.dispose();
        getSite().getWorkbenchWindow().getPartService().removePartListener(partListener);
        if (currentManager != null) {
            currentManager.removeChangeListener(LayoutViewPart.this);
            currentManager = null;
        }
        currentEditor = null;
        currentEditPart = null;
    }
    
    /**
     * Refreshes the layout view asynchronously.
     */
    public void refresh() {
        Display.getDefault().asyncExec(new Runnable() {
            public void run() {
                page.refresh();
            }
        });
    }
    
    /**
     * Sets the input for the property sheet page.
     * 
     * @param part the active workbench part
     */
    private void setInput(final IWorkbenchPart part) {
        if (part instanceof IEditorPart) {
            IEditorPart editorPart = (IEditorPart) part;
            DiagramLayoutManager manager = EclipseLayoutServices.getInstance()
                    .getManager(editorPart, null);
            if (manager != null) {
                if (currentManager != null) {
                    currentManager.removeChangeListener(this);
                }
                currentEditor = editorPart;
                currentManager = manager;
                editorChanged();
                manager.addChangeListener(editorPart, this);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void selectionChanged(final SelectionChangedEvent event) {
        if (currentEditor != null) {
            ISelection selection = event.getSelection();
            if (selection instanceof IStructuredSelection) {
                page.selectionChanged(currentEditor, selection);
                setPartText();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void editorChanged() {
        if (currentManager != null) {
            ISelection selection = currentManager.getBridge().getSelection(currentEditor);
            if (selection instanceof IStructuredSelection) {
                page.selectionChanged(currentEditor, selection);
                setPartText();
            }
        }
    }
    
    /**
     * Returns the currently used diagram layout manager.
     * 
     * @return the current layout manager
     */
    public DiagramLayoutManager getCurrentManager() {
        return currentManager;
    }
    
    /**
     * Returns the current selection of the layout view.
     * 
     * @return the selected property sheet entries
     */
    public List<IPropertySheetEntry> getSelection() {
        List<IPropertySheetEntry> entries = new LinkedList<IPropertySheetEntry>();
        TreeItem[] treeItems = ((Tree) page.getControl()).getSelection();
        for (TreeItem item : treeItems) {
            Object data = item.getData();
            if (data instanceof IPropertySheetEntry) {
                entries.add((IPropertySheetEntry) data);
            } else {
                // a category was selected, apply options for all children
                for (TreeItem childItem : item.getItems()) {
                    data = childItem.getData();
                    if (data instanceof IPropertySheetEntry) {
                        entries.add((IPropertySheetEntry) data);
                    }
                }
            }
        }
        return entries;
    }
    
    /**
     * Adds the specific view actions to the given menu.
     * 
     * @param menu context menu to enrich with actions
     */
    private void addPopupActions(final Menu menu) {
        final String applyOptionString = Messages.getString("kiml.ui.10"); //$NON-NLS-1$
        final IAction applyOptionAction = new DiagramDefaultAction(this, applyOptionString);
        final String setDefaultString = Messages.getString("kiml.ui.16"); //$NON-NLS-1$
        final String setAllDefaultString = Messages.getString("kiml.ui.34"); //$NON-NLS-1$
        final IAction editPartDefaultAction = new EditPartDefaultAction(this, setDefaultString, false);
        final IAction modelDefaultAction = new EditPartDefaultAction(this, setAllDefaultString, true);
        final IAction diagramTypeDefaultAction = new DiagramTypeDefaultAction(this, setAllDefaultString);
        // dirty hack to add actions to an existing menu without having the menu manager
        menu.addMenuListener(new MenuAdapter() {
            public void menuShown(final MenuEvent event) {
                MenuItem applyOptionItem = null, diagramTypeDefaultItem = null,
                        editPartDefaultItem = null, modelDefaultItem = null;
                for (MenuItem item : menu.getItems()) {
                    if (item.getData() instanceof IContributionItem) {
                        String itemId = ((IContributionItem) item.getData()).getId();
                        if (DiagramDefaultAction.ACTION_ID.equals(itemId)) {
                            applyOptionItem = item;
                        } else if (EditPartDefaultAction.EDIT_PART_ACTION_ID.equals(itemId)) {
                            editPartDefaultItem = item;
                        } else if (EditPartDefaultAction.MODEL_ACTION_ID.equals(itemId)) {
                            modelDefaultItem = item;
                        } else if (DiagramTypeDefaultAction.ACTION_ID.equals(itemId)) {
                            diagramTypeDefaultItem = item;
                        }
                    }
                }
                // add the "apply to whole diagram" action
                if (applyOptionItem == null) {
                    ContributionItem contributionItem = new ActionContributionItem(
                            applyOptionAction);
                    contributionItem.setId(DiagramDefaultAction.ACTION_ID);
                    contributionItem.fill(menu, -1);
                }
                if (currentManager == null) {
                    if (editPartDefaultItem != null) {
                        editPartDefaultItem.setEnabled(false);
                    }
                    if (modelDefaultItem != null) {
                        modelDefaultItem.setEnabled(false);
                    }
                } else {
                    // add the "set as default for edit part" action
                    String editPartName = getReadableName(false, true);
                    if (editPartName != null) {
                        if (editPartDefaultItem == null) {
                            editPartDefaultAction.setText(setDefaultString + " " + editPartName);
                            ContributionItem contributionItem = new ActionContributionItem(
                                    editPartDefaultAction);
                            contributionItem.setId(EditPartDefaultAction.EDIT_PART_ACTION_ID);
                            contributionItem.fill(menu, -1);
                        } else {
                            editPartDefaultItem.setEnabled(true);
                            editPartDefaultItem.setText(setDefaultString + " " + editPartName);
                        }
                        // add the "set as default for model element" action
                        String modelName = getReadableName(true, true);
                        if (modelDefaultItem == null) {
                            modelDefaultAction.setText(setAllDefaultString + " " + modelName);
                            ContributionItem contributionItem = new ActionContributionItem(
                                    modelDefaultAction);
                            contributionItem.setId(EditPartDefaultAction.MODEL_ACTION_ID);
                            contributionItem.fill(menu, -1);
                        } else {
                            modelDefaultItem.setEnabled(true);
                            modelDefaultItem.setText(setAllDefaultString + " " + modelName);
                        }
                    }
                }
                // add the "set as default for diagram type" action
                LayoutServices layoutServices = LayoutServices.getInstance();
                IGraphicalFrameworkBridge editingProvider = currentManager.getBridge();
                String diagramType = (String) EclipseLayoutConfig.getOption(
                        currentEditPart, editingProvider.getElement(currentEditPart),
                        LayoutOptions.DIAGRAM_TYPE_ID);
                if (diagramType == null) {
                    if (diagramTypeDefaultItem != null) {
                        diagramTypeDefaultItem.setEnabled(false);
                    }
                } else {
                    String diagramTypeName = layoutServices.getDiagramTypeName(diagramType);
                    if (diagramTypeName != null) {
                        if (!diagramTypeName.endsWith("s")) {
                            diagramTypeName += "s";
                        }
                        if (diagramTypeDefaultItem == null) {
                            diagramTypeDefaultAction.setText(setAllDefaultString
                                    + " " + diagramTypeName);
                            ContributionItem contributionItem = new ActionContributionItem(
                                    diagramTypeDefaultAction);
                            contributionItem.setId(DiagramTypeDefaultAction.ACTION_ID);
                            contributionItem.fill(menu, -1);
                        } else {
                            diagramTypeDefaultItem.setEnabled(true);
                            diagramTypeDefaultItem.setText(setAllDefaultString + " " + diagramTypeName);
                        }
                    }
                }
            }
        });
    }
    
    /**
     * Builds a readable name for the current focus object.
     * 
     * @param plural if true, the plural form is created
     * @return a readable name for the edit part, or {@code null} if the focus object
     *     cannot be handled in this context
     */
    private String getReadableName(final boolean forDomainModel, final boolean plural) {
        if (currentManager == null) {
            return "";
        }
        
        EObject model = currentManager.getBridge().getElement(currentEditPart);
        String clazzName = model == null ? null : model.eClass().getInstanceTypeName();
        if (clazzName == null) {
            if (plural || currentEditPart == null) {
                return null;
            }
            clazzName = currentEditPart.getClass().getName();
            if (clazzName.endsWith("EditPart")) {
                clazzName = clazzName.substring(0, clazzName.length() - "EditPart".length());
            }
        }
        int lastDotIndex = clazzName.lastIndexOf('.');
        if (lastDotIndex >= 0) {
            clazzName = clazzName.substring(lastDotIndex + 1);
        }
        StringBuilder stringBuilder = new StringBuilder();
        int length = clazzName.length();
        if (clazzName.endsWith("Impl")) {
            length -= "Impl".length();
        }
        for (int i = 0; i < length; i++) {
            char c = clazzName.charAt(i);
            if (i > 0 && Character.isUpperCase(c)
                    && !Character.isUpperCase(clazzName.charAt(i - 1))) {
                stringBuilder.append(' ');
            }
            if (!Character.isDigit(c)) {
                stringBuilder.append(c);
            }
        }
        if (plural && !clazzName.endsWith("s")) {
            stringBuilder.append('s');
        }
        if (!forDomainModel) {
            stringBuilder.append(" " + Messages.getString("kiml.ui.33"));
        }
        return stringBuilder.toString();
    }

    /**
     * Returns the first edit part in the current selection for which options are shown.
     * 
     * @return the selected edit part, or {@code null} if there is none
     */
    public EditPart getCurrentEditPart() {
        return currentEditPart;
    }

    /**
     * Returns the currently active editor that is tracked by the layout view.
     * 
     * @return the currently tracked editor, or {@code null} if there is none
     */
    public IEditorPart getCurrentEditor() {
        // FIXME what about nested editors?
        return currentEditor;
    }

    /**
     * Returns the current layout provider data.
     * 
     * @return the current layout provider data
     */
    public LayoutProviderData[] getCurrentProviderData() {
        return currentProviderData;
    }

    /**
     * Sets a text line for the view part.
     */
    private void setPartText() {
        if (currentManager != null) {
            StringBuilder textBuffer = new StringBuilder();
            String name = getReadableName(true, false);
            if (name != null) {
                textBuffer.append(name);
            }
            EObject model = currentManager.getBridge().getElement(currentEditPart);
            if (model != null) {
                String modelName = getProperty(model, "Name");
                if (modelName == null) {
                    modelName = getProperty(model, "Label");
                }
                if (modelName == null) {
                    modelName = getProperty(model, "Id");
                }
                if (modelName != null) {
                    textBuffer.append(" '" + modelName + "'");
                }
            }
            form.setText(textBuffer.toString());
        } else {
            form.setText("");
        }
    }
    
    /**
     * Gets a property of the given object by invoking its getter method.
     * 
     * @param object the object from which the property shall be fetched
     * @param property the name of a property, starting with a capital
     * @return the named property, or {@code null} if there is no such property
     */
    private static String getProperty(final Object object, final String property) {
        try {
            return (String) object.getClass().getMethod("get" + property).invoke(object);
        } catch (Exception exception) {
            return null;
        }
    }

}
