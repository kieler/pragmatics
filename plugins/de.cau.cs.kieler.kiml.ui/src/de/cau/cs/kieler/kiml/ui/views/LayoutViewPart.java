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

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.events.MenuAdapter;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.views.properties.IPropertySheetEntry;
import org.eclipse.ui.views.properties.PropertySheetEntry;
import org.eclipse.ui.views.properties.PropertySheetPage;

import de.cau.cs.kieler.core.util.Maybe;
import de.cau.cs.kieler.kiml.LayoutAlgorithmData;
import de.cau.cs.kieler.kiml.LayoutContext;
import de.cau.cs.kieler.kiml.config.DefaultLayoutConfig;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.ui.KimlUiPlugin;
import de.cau.cs.kieler.kiml.ui.Messages;
import de.cau.cs.kieler.kiml.ui.diagram.DiagramLayoutManager;
import de.cau.cs.kieler.kiml.ui.service.EclipseLayoutConfig;
import de.cau.cs.kieler.kiml.ui.service.EclipseLayoutInfoService;

/**
 * A view that displays layout options for selected objects.
 *
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @author msp
 */
public class LayoutViewPart extends ViewPart implements ISelectionListener {

    /** the view identifier. */
    public static final String VIEW_ID = "de.cau.cs.kieler.kiml.views.layout";
    /** preference identifier for enabling categories. */
    public static final String PREF_CATEGORIES = "view.categories";
    /** preference identifier for enabling advanced options. */
    public static final String PREF_ADVANCED = "view.advanced";
    /** preference identifier for the title font. */
    private static final String TITLE_FONT = "de.cau.cs.kieler.kiml.ui.views.LayoutViewPart.TITLE_FONT";
    
    /** the form toolkit used to create forms. */
    private FormToolkit toolkit;
    /** the form container for the property sheet page. */
    private Form form;
    /** the page that is displayed in this view part. */
    private PropertySheetPage page;
    /** the property source provider that keeps track of created property sources. */
    private LayoutPropertySourceProvider propSourceProvider = new LayoutPropertySourceProvider();
    
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
        // GUI code needs magic numbers
        // CHECKSTYLEOFF MagicNumber
        
        toolkit = new FormToolkit(parent.getDisplay());
        form = toolkit.createForm(parent);
        
        // Set the form's heading
        // (see org.eclipse.ui.internal.views.properties.tabbed.view.TabbedPropertyTitle)
        toolkit.decorateFormHeading(form);
        form.setText("");

        if (!JFaceResources.getFontRegistry().hasValueFor(TITLE_FONT)) {
            FontData[] fontData = JFaceResources.getFontRegistry().getBold(
                    JFaceResources.DEFAULT_FONT).getFontData();
            /* title font is 2pt larger than that used in the tabs. */  
            fontData[0].setHeight(fontData[0].getHeight() + 2);
            JFaceResources.getFontRegistry().put(TITLE_FONT, fontData);
        }
        form.setFont(JFaceResources.getFont(TITLE_FONT));
        
        // Content
        Composite content = form.getBody();
        FormLayout contentLayout = new FormLayout();
        contentLayout.marginWidth = MARGIN_WIDTH;
        content.setLayout(contentLayout);
        
        // Property Sheet Page
        page = new PropertySheetPage();
        page.setRootEntry(new PropertySheetEntry());
        page.createControl(content);
        FormData formData = new FormData();
        formData.left = new FormAttachment(FORM_LEFT, 0);
        formData.right = new FormAttachment(FORM_RIGHT, 0);
        formData.top = new FormAttachment(FORM_TOP, 5);
        formData.bottom = new FormAttachment(FORM_BOTTOM, 0);
        page.getControl().setLayoutData(formData);
        page.setPropertySourceProvider(propSourceProvider);
        IPreferenceStore preferenceStore = KimlUiPlugin.getDefault().getPreferenceStore();
        
        // add actions to the toolbar, view menu, and context menu
        IActionBars actionBars = getViewSite().getActionBars();
        page.setActionBars(actionBars);
        addPopupActions(page.getControl().getMenu());
        IMenuManager menuManager = actionBars.getMenuManager();
        menuManager.add(new RemoveOptionsAction(this, Messages.getString("kiml.ui.30")));
        IToolBarManager toolBarManager = actionBars.getToolBarManager();
        toolBarManager.add(new SelectionInfoAction(this, Messages.getString("kiml.ui.37")));
        
        // CHECKSTYLEON MagicNumber
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
        
        // get the current selection and trigger an update
        IWorkbenchWindow workbenchWindow = getSite().getWorkbenchWindow();
        IWorkbenchPage activePage = workbenchWindow.getActivePage();
        if (activePage != null) {
            final IWorkbenchPart activePart = activePage.getActivePart();
            final ISelection selection = workbenchWindow.getSelectionService().getSelection();
            if (activePart != null && selection != null) {
                workbenchWindow.getWorkbench().getDisplay().asyncExec(new Runnable() {
                    public void run() {
                        selectionChanged(activePart, selection);
                    }
                });
            }
        }
        workbenchWindow.getSelectionService().addSelectionListener(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFocus() {
        page.setFocus();
    }
    
    /**
     * Returns the control that is handled by this view part.
     * 
     * @return the control
     */
    public Control getControl() {
        return page.getControl();
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
        getSite().getWorkbenchWindow().getSelectionService().removeSelectionListener(this);
        propSourceProvider.resetContext(null);
        toolkit.dispose();
        super.dispose();
    }
    
    /**
     * Refreshes the layout view asynchronously.
     */
    public void refresh() {
        Display.getDefault().asyncExec(new Runnable() {
            public void run() {
                propSourceProvider.resetContext();
                page.refresh();
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    public void selectionChanged(final IWorkbenchPart part, final ISelection selection) {
        DiagramLayoutManager<?> manager = EclipseLayoutInfoService.getInstance().getManager(part, null);
        if (manager != null) {
            propSourceProvider.resetContext(part);
            page.selectionChanged(part, selection);
            setPartText();
        }
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
                if (!propSourceProvider.hasContent()) {
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
                LayoutContext context = propSourceProvider.getContext();
                String diagramType = (String) EclipseLayoutConfig.getOption(
                        context.getProperty(LayoutContext.DIAGRAM_PART),
                        context.getProperty(LayoutContext.DOMAIN_MODEL),
                        LayoutOptions.DIAGRAM_TYPE);
                if (diagramType == null) {
                    if (diagramTypeDefaultItem != null) {
                        diagramTypeDefaultItem.setEnabled(false);
                    }
                } else {
                    String diagramTypeName = EclipseLayoutInfoService.getInstance()
                            .getDiagramTypeName(diagramType);
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
        if (!propSourceProvider.hasContent()) {
            return "";
        }
        
        LayoutContext context = propSourceProvider.getContext();
        EObject model = context.getProperty(LayoutContext.DOMAIN_MODEL);
        Object diagramPart = context.getProperty(LayoutContext.DIAGRAM_PART);
        String clazzName = model == null ? null : model.eClass().getInstanceTypeName();
        if (clazzName == null) {
            if (plural || diagramPart == null) {
                return null;
            }
            clazzName = diagramPart.getClass().getName();
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
    public Object getCurrentEditPart() {
        if (propSourceProvider.hasContent()) {
            return propSourceProvider.getContext().getProperty(LayoutContext.DIAGRAM_PART);
        }
        return null;
    }

    /**
     * Returns the currently active workbench part that is tracked by the layout view.
     * 
     * @return the current workbench part, or {@code null} if there is none
     */
    public IWorkbenchPart getCurrentPart() {
        return propSourceProvider.getWorkbenchPart();
    }

    /**
     * Returns the current layout algorithm data.
     * 
     * @return the current layout algorithm data
     */
    public LayoutAlgorithmData[] getCurrentLayouterData() {
        // SUPPRESS CHECKSTYLE NEXT MagicNumber
        HashSet<LayoutAlgorithmData> data = new HashSet<LayoutAlgorithmData>(4);
        LayoutContext context = propSourceProvider.getContext();
        LayoutAlgorithmData lad = context.getProperty(DefaultLayoutConfig.CONTENT_ALGO);
        if (lad != null) {
            data.add(lad);
        }
        lad = context.getProperty(DefaultLayoutConfig.CONTAINER_ALGO);
        if (lad != null) {
            data.add(lad);
        }
        return data.toArray(new LayoutAlgorithmData[data.size()]);
    }

    /**
     * Sets a text line for the view part.
     */
    private void setPartText() {
        if (propSourceProvider.hasContent()) {
            StringBuilder textBuffer = new StringBuilder();
            String name = getReadableName(true, false);
            if (name != null) {
                textBuffer.append(name);
            }
            EObject model = propSourceProvider.getContext().getProperty(LayoutContext.DOMAIN_MODEL);
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
