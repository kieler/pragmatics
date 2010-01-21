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
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.events.MenuAdapter;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbench;
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

import de.cau.cs.kieler.kiml.layout.LayoutServices;
import de.cau.cs.kieler.kiml.layout.options.LayoutOptions;
import de.cau.cs.kieler.kiml.ui.Messages;

/**
 * A view that displays layout options for selected objects.
 *
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @author msp
 */
public class LayoutViewPart extends ViewPart implements ISelectionChangedListener {

    /** the view identifier. */
    public static final String VIEW_ID = "de.cau.cs.kieler.kiml.views.layout";
    
    /** the form container for the property sheet page. */
    private ScrolledForm form;
    /** the page that is displayed in this view part. */
    private PropertySheetPage page;
    /** the part listener that tracks the active editor part. */
    private IPartListener partListener;
    /** the currently tracked diagram editor. */
    private DiagramEditor currentEditor;
    /** the current selection. */
    private IStructuredSelection currentSelection;
    
    /**
     * Finds an active layout view and refreshes it.
     */
    public static void refreshLayoutView() {
        final IWorkbench workbench = PlatformUI.getWorkbench();
        workbench.getDisplay().asyncExec(new Runnable() {
            public void run() {
                IWorkbenchWindow activeWindow = workbench.getActiveWorkbenchWindow();
                if (activeWindow != null) {
                    IWorkbenchPage activePage = activeWindow.getActivePage();
                    if (activePage != null) {
                        LayoutViewPart layoutViewPart = (LayoutViewPart)
                                activePage.findView(VIEW_ID);
                        if (layoutViewPart != null) {
                            layoutViewPart.page.refresh();
                        }
                    }
                }
            }
        });
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
        page.setPropertySourceProvider(new IPropertySourceProvider() {
            public IPropertySource getPropertySource(final Object object) {
                if (object instanceof IGraphicalEditPart) {
                    return new GmfLayoutPropertySource((IGraphicalEditPart) object);
                } else {
                    return null;
                }
            }
        });
        page.setActionBars(getViewSite().getActionBars());
        addActions(page.getControl().getMenu());
        
        IWorkbenchWindow workbenchWindow = getSite().getWorkbenchWindow();
        IWorkbenchPage activePage = workbenchWindow.getActivePage();
        if (activePage != null) {
            IWorkbenchPart activePart = activePage.getActivePart();
            if (activePart != null) {
                setInput(activePart);
            }
        }
        
        partListener = new IPartListener() {
            public void partActivated(final IWorkbenchPart part) {
                setInput(part);
            }
            public void partDeactivated(final IWorkbenchPart part) {
                if (part == currentEditor) {
                    currentEditor.getDiagramGraphicalViewer()
                            .removeSelectionChangedListener(LayoutViewPart.this);
                    currentEditor = null;
                }
            }
            public void partBroughtToTop(final IWorkbenchPart part) {
            }
            public void partClosed(final IWorkbenchPart part) {
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
        super.dispose();
        getSite().getWorkbenchWindow().getPartService().removePartListener(partListener);
        if (currentEditor != null) {
            currentEditor.getDiagramGraphicalViewer().removeSelectionChangedListener(this);
            currentEditor = null;
        }
    }
    
    /**
     * Sets the input for the property sheet page.
     * 
     * @param part the active workbench part
     */
    private void setInput(final IWorkbenchPart part) {
        if (part instanceof DiagramEditor) {
            if (currentEditor != null) {
                currentEditor.getDiagramGraphicalViewer().removeSelectionChangedListener(this);
            }
            currentEditor = (DiagramEditor) part;
            ISelection selection = currentEditor.getDiagramGraphicalViewer().getSelection();
            page.selectionChanged(currentEditor, selection);
            if (selection instanceof IStructuredSelection) {
                currentSelection = (IStructuredSelection) selection;
                setPartText(currentSelection);
            }
            currentEditor.getDiagramGraphicalViewer().addSelectionChangedListener(this);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void selectionChanged(final SelectionChangedEvent event) {
        if (currentEditor != null) {
            ISelection selection = event.getSelection();
            page.selectionChanged(currentEditor, selection);
            if (selection instanceof IStructuredSelection) {
                currentSelection = (IStructuredSelection) selection;
                setPartText(currentSelection);
            }
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
    private void addActions(final Menu menu) {
        final String applyOptionString = Messages.getString("kiml.ui.10"); //$NON-NLS-1$
        final IAction applyOptionAction = new ApplyOptionAction(this, applyOptionString);
        final String setDefaultString = Messages.getString("kiml.ui.16"); //$NON-NLS-1$
        final IAction diagramTypeDefaultAction = new DiagramTypeDefaultAction(this, setDefaultString);
        final IAction editPartDefaultAction = new EditPartDefaultAction(this, setDefaultString);
        // dirty hack to add actions to an existing menu without having the menu manager
        menu.addMenuListener(new MenuAdapter() {
            public void menuShown(final MenuEvent event) {
                MenuItem applyOptionItem = null, diagramTypeDefaultItem = null,
                        editPartDefaultItem = null;
                for (MenuItem item : menu.getItems()) {
                    if (item.getData() instanceof IContributionItem) {
                        String itemId = ((IContributionItem) item.getData()).getId();
                        if (ApplyOptionAction.ACTION_ID.equals(itemId)) {
                            applyOptionItem = item;
                        } else if (DiagramTypeDefaultAction.ACTION_ID.equals(itemId)) {
                            diagramTypeDefaultItem = item;
                        } else if (EditPartDefaultAction.ACTION_ID.equals(itemId)) {
                            editPartDefaultItem = item;
                        }
                    }
                }
                // add the "apply to whole diagram" action
                if (applyOptionItem == null) {
                    ContributionItem contributionItem = new ActionContributionItem(
                            applyOptionAction);
                    contributionItem.setId(ApplyOptionAction.ACTION_ID);
                    contributionItem.fill(menu, -1);
                }
                // add the "set as default for diagram type" action
                LayoutServices layoutServices = LayoutServices.getInstance();
                String diagramType = getSelectedDiagramType();
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
                            diagramTypeDefaultAction.setText(setDefaultString + " " + diagramTypeName);
                            ContributionItem contributionItem = new ActionContributionItem(
                                    diagramTypeDefaultAction);
                            contributionItem.setId(DiagramTypeDefaultAction.ACTION_ID);
                            contributionItem.fill(menu, -1);
                        } else {
                            diagramTypeDefaultItem.setEnabled(true);
                            diagramTypeDefaultItem.setText(setDefaultString + " " + diagramTypeName);
                        }
                    }
                }
                // add the "set as default for edit part" action
                EditPart editPart = getSelectedEditPart();
                if (editPart == null) {
                    if (editPartDefaultItem != null) {
                        editPartDefaultItem.setEnabled(false);
                    }
                } else {
                    String editPartName = getReadableName(editPart, true);
                    if (editPartDefaultItem == null) {
                        editPartDefaultAction.setText(setDefaultString + " " + editPartName);
                        ContributionItem contributionItem = new ActionContributionItem(
                                editPartDefaultAction);
                        contributionItem.setId(EditPartDefaultAction.ACTION_ID);
                        contributionItem.fill(menu, -1);
                    } else {
                        editPartDefaultItem.setEnabled(true);
                        editPartDefaultItem.setText(setDefaultString + " " + editPartName);
                    }
                }
            }
        });
    }
    
    /** suffix of edit part class names. */
    private static final String EDIT_PART_SUFFIX = "EditPart";
    
    /**
     * Builds a readable name for the given edit part.
     * 
     * @param editPart an edit part
     * @param plural if true, the plural form is created
     * @return a readable name for the edit part
     */
    private String getReadableName(final EditPart editPart, final boolean plural) {
        String className = null;
        if (editPart instanceof IGraphicalEditPart) {
            EObject model = ((IGraphicalEditPart) editPart).getNotationView().getElement();
            className = model.eClass().getName();
        } else {
            className = editPart.getClass().getSimpleName();
            if (className.endsWith(EDIT_PART_SUFFIX)) {
                className = className.substring(0, className.length() - EDIT_PART_SUFFIX.length());
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < className.length(); i++) {
            char c = className.charAt(i);
            if (Character.isUpperCase(c) && i > 0) {
                stringBuilder.append(' ');
            }
            if (!Character.isDigit(c)) {
                stringBuilder.append(c);
            }
        }
        if (plural && !className.endsWith("s")) {
            stringBuilder.append('s');
        }
        return stringBuilder.toString();
    }
    
    /**
     * Returns the diagram type identifier for the current selection.
     * 
     * @return the identifier of the diagram type for the currently selected object,
     *     or {@code null} if there is no such diagram type
     */
    public String getSelectedDiagramType() {
        if (currentSelection != null) {
            Object object = currentSelection.getFirstElement();
            if (object != null) {
                String diagramType = (String) LayoutServices.getInstance().getBindingOption(
                        object.getClass().getName(), LayoutOptions.DIAGRAM_TYPE);
                return diagramType;
            }
        }
        return null;
    }

    /**
     * Returns the first edit part in the current selection for which options are shown.
     * 
     * @return the selected edit part, or {@code null} if there is none
     */
    public EditPart getSelectedEditPart() {
        if (currentSelection != null) {
            Object object = currentSelection.getFirstElement();
            if (object instanceof IGraphicalEditPart) {
                return GmfLayoutPropertySource.getShownEditPart((IGraphicalEditPart) object);
            }
        }
        return null;
    }
    
    /**
     * Sets a text line for the view part.
     * 
     * @param selection the current selection
     */
    private void setPartText(final IStructuredSelection selection) {
        Object firstElement = selection.getFirstElement();
        if (firstElement instanceof IGraphicalEditPart) {
            IGraphicalEditPart editPart = GmfLayoutPropertySource.getShownEditPart(
                    (IGraphicalEditPart) firstElement);
            if (editPart != null) {
                StringBuilder textBuffer = new StringBuilder();
                textBuffer.append(getReadableName(editPart, false));
                Object model = editPart.getNotationView().getElement();
                String name = getProperty(model, "Name");
                if (name == null) {
                    name = getProperty(model, "Label");
                }
                if (name == null) {
                    name = getProperty(model, "Id");
                }
                if (name != null) {
                    textBuffer.append(" '" + name + "'");
                }
                form.setText(textBuffer.toString());
            } else {
                form.setText("");
            }
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
