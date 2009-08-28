/******************************************************************************
 * KIELER - Kiel Integrated Environment for Layout for the Eclipse RCP
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

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.progress.IProgressService;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySourceProvider;
import org.eclipse.ui.views.properties.PropertySheetEntry;
import org.eclipse.ui.views.properties.PropertySheetPage;

/**
 * A view that displays layout options for selected objects.
 *
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class LayoutViewPart extends ViewPart implements ISelectionChangedListener {

    /** the view identifier */
    public static final String VIEW_ID = "de.cau.cs.kieler.kiml.views.layout";
    
    /** the form container for the property sheet page */
    private ScrolledForm form;
    /** the page that is displayed in this view part */
    private PropertySheetPage page;
    /** the part listener that tracks the active editor part */
    private IPartListener partListener;
    /** the currently tracked diagram editor */
    private DiagramEditor currentEditor;
    
    /**
     * Finds an active layout view and refreshes it.
     */
    public static void refreshLayoutView() {
        try {
            IProgressService progressService = PlatformUI.getWorkbench().getProgressService();
            progressService.runInUI(progressService, new IRunnableWithProgress() {
                public void run(IProgressMonitor monitor) throws InvocationTargetException,
                        InterruptedException {
                    IWorkbenchWindow activeWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
                    if (activeWindow != null) {
                        IWorkbenchPage activePage = activeWindow.getActivePage();
                        if (activePage != null) {
                            LayoutViewPart layoutViewPart = (LayoutViewPart)activePage.findView(VIEW_ID);
                            if (layoutViewPart != null)
                                layoutViewPart.page.refresh();
                        }
                    }
                }
            }, null);
        }
        catch (Exception exception) {}
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
     */
    public void createPartControl(Composite parent) {
        FormToolkit toolkit = new FormToolkit(parent.getDisplay());
        form = toolkit.createScrolledForm(parent);
        form.setText("");
        Composite content = form.getBody();
        FormLayout contentLayout = new FormLayout();
        contentLayout.marginWidth = 4;
        content.setLayout(contentLayout);
        
        page = new PropertySheetPage();
        page.setRootEntry(new PropertySheetEntry());
        page.createControl(content);
        FormData formData = new FormData();
        formData.left = new FormAttachment(0, 0);
        formData.right = new FormAttachment(100, 0);
        formData.top = new FormAttachment(0, 0);
        formData.bottom = new FormAttachment(100, 0);
        page.getControl().setLayoutData(formData);
        page.setPropertySourceProvider(new IPropertySourceProvider() {
            public IPropertySource getPropertySource(Object object) {
                if (object instanceof IGraphicalEditPart)
                    return new GmfLayoutPropertySource((IGraphicalEditPart)object);
                else
                    return null;
            }
        });
        IActionBars actionBars = getViewSite().getActionBars();
        page.makeContributions(actionBars.getMenuManager(), actionBars
                .getToolBarManager(), actionBars.getStatusLineManager());
        
        IWorkbenchWindow workbenchWindow = getSite().getWorkbenchWindow();
        IWorkbenchPage activePage = workbenchWindow.getActivePage();
        if (activePage != null) {
            IWorkbenchPart activePart = activePage.getActivePart();
            if (activePart != null)
                setInput(activePart);
        }
        
        partListener = new IPartListener() {
            public void partActivated(IWorkbenchPart part) {
                setInput(part);
            }
            public void partDeactivated(IWorkbenchPart part) {
                if (part == currentEditor) {
                    currentEditor.getDiagramGraphicalViewer().removeSelectionChangedListener(LayoutViewPart.this);
                    currentEditor = null;
                }
            }
            public void partBroughtToTop(IWorkbenchPart part) {}
            public void partClosed(IWorkbenchPart part) {}
            public void partOpened(IWorkbenchPart part) {}
        };
        workbenchWindow.getPartService().addPartListener(partListener);
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
     */
    public void setFocus() {
        page.setFocus();
    }
    
    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.part.WorkbenchPart#dispose()
     */
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
    private void setInput(IWorkbenchPart part) {
        if (part instanceof DiagramEditor) {
            if (currentEditor != null)
                currentEditor.getDiagramGraphicalViewer().removeSelectionChangedListener(this);
            currentEditor = (DiagramEditor)part;
            ISelection selection = currentEditor.getDiagramGraphicalViewer().getSelection();
            page.selectionChanged(currentEditor, selection);
            setPartText(selection);
            currentEditor.getDiagramGraphicalViewer().addSelectionChangedListener(this);
        }        
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
     */
    public void selectionChanged(SelectionChangedEvent event) {
        if (currentEditor != null) {
            page.selectionChanged(currentEditor, event.getSelection());
            setPartText(event.getSelection());
        }
    }
    
    /**
     * Sets a text line for the view part.
     * 
     * @param selection the current selection
     */
    private void setPartText(ISelection selection) {
        if (selection instanceof IStructuredSelection && !selection.isEmpty()) {
            Object firstElement = ((IStructuredSelection)selection).getFirstElement();
            if (firstElement instanceof EditPart) {
                Object model = ((EditPart)firstElement).getModel();
                if (model instanceof View)
                    model = ((View)model).getElement();
                StringBuffer textBuffer = new StringBuffer();
                if (model instanceof EObject)
                    textBuffer.append(((EObject)model).eClass().getName());
                else
                    textBuffer.append(model.getClass().getSimpleName());
                String name = getProperty(model, "Name");
                if (name == null)
                    name = getProperty(model, "Label");
                if (name == null)
                    name = getProperty(model, "Id");
                if (name != null)
                    textBuffer.append(" '" + name + "'");
                form.setText(textBuffer.toString());
            }
        }
    }
    
    /**
     * Gets a property of the given object by invoking its getter method.
     * 
     * @param object the object from which the property shall be fetched
     * @param property the name of a property, starting with a capital
     * @return the named property, or {@code null} if there is no such property
     */
    private static String getProperty(Object object, String property) {
        try {
            return (String)object.getClass().getMethod("get" + property).invoke(object);
        }
        catch (Exception exception) {
            return null;
        }
    }

}
