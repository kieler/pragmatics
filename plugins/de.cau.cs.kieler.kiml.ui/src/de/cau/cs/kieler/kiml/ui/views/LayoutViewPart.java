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

import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.part.ViewPart;
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

    /** the page that is displayed in this view part */
    private PropertySheetPage page;
    /** the part listener that tracks the active editor part */
    private IPartListener partListener;
    /** the currently tracked diagram editor */
    private DiagramEditor currentEditor;
    
    /* (non-Javadoc)
     * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
     */
    public void createPartControl(Composite parent) {
        FormToolkit toolkit = new FormToolkit(parent.getDisplay());
        ScrolledForm form = toolkit.createScrolledForm(parent);
        form.setText("Layout");
        form.setExpandHorizontal(true);
        form.setExpandVertical(true);
        Composite content = form.getBody();
        content.setLayout(new FillLayout());
        
        page = new PropertySheetPage();
        page.setRootEntry(new PropertySheetEntry());
        page.createControl(content);
        page.setPropertySourceProvider(new IPropertySourceProvider() {
            public IPropertySource getPropertySource(Object object) {
                if (object instanceof IGraphicalEditPart)
                    return new GmfLayoutPropertySource((IGraphicalEditPart)object);
                else
                    return null;
            }
        });
        IWorkbenchWindow workbenchWindow = getSite().getWorkbenchWindow();
        setInput(workbenchWindow.getActivePage().getActivePart());
        
        
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
            currentEditor.getDiagramGraphicalViewer().addSelectionChangedListener(this);
        }        
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
     */
    public void selectionChanged(SelectionChangedEvent event) {
        if (currentEditor != null) {
            page.selectionChanged(currentEditor, event.getSelection());
        }
    }

}
