/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.ui.parts;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.ITreeViewerListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeExpansionEvent;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.part.ViewPart;

import com.google.common.collect.Iterables;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.klighd.IViewChangeListener;
import de.cau.cs.kieler.klighd.KlighdTreeSelection;
import de.cau.cs.kieler.klighd.LightDiagramServices;
import de.cau.cs.kieler.klighd.ViewChangeType;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.util.Iterables2;
import de.cau.cs.kieler.klighd.util.ModelingUtil;

/**
      <view
            allowMultiple="false"
            category="de.cau.cs.kieler.klighd"
            class="de.cau.cs.kieler.klighd.ui.parts.KlighdTreeView"
            id="de.cau.cs.kieler.klighd.ui.treeView"
            name="KLighD Tree View"
            restorable="true">
      </view>
 * @author chsch
 */
public class KlighdTreeView extends ViewPart implements ISelectionListener {

    private TreeViewer viewer;

    private ContentProvider contentProvider;
    private ILabelProvider labelProvider;
    
    private IViewChangeListener viewChangeListener;

    /**
     * {@inheritDoc}
     */
    @Override
    public void createPartControl(final Composite parent) {        
        parent.setLayout(new FillLayout());

        viewChangeListener = new IViewChangeListener() {

            public void viewChanged(final ViewChange change) {
                if (viewer.isBusy()) {
                    return;
                }
                if (change.getType() == ViewChangeType.COLLAPSE) {
                    final KNode collapsedNode = (KNode) change.getAffectedElement();
                    if (collapsedNode != null) {
                        viewer.collapseToLevel(collapsedNode, 1);
                    }
                }
                if (change.getType() == ViewChangeType.EXPAND) {
                    final KNode expandedNode = (KNode) change.getAffectedElement();
                    if (expandedNode != null) {
                        viewer.expandToLevel(expandedNode, 1);
                    }
                }
            }
        };
                
        contentProvider = new ContentProvider();
        labelProvider = new LabelProvider();

        viewer = new TreeViewer(parent);
        viewer.setContentProvider(contentProvider);
        viewer.setLabelProvider(labelProvider);
        viewer.setAutoExpandLevel(2);
        viewer.addTreeListener(new ITreeViewerListener() {
            
            public void treeExpanded(final TreeExpansionEvent event) {
                final Object element = event.getElement();
                if (element instanceof KNode) {
                    // be careful: don't call 
                    //  viewContext.getViewer().expand(element);!!
                    // the KNode will wrongly be considered the semantic element
                    viewContext.getViewer().expand((KNode) element);
                    LightDiagramServices.layoutDiagram(viewContext);
                }
            }
            
            public void treeCollapsed(final TreeExpansionEvent event) {
                final Object element = event.getElement();
                if (element instanceof KNode) {
                    if (viewContext.getViewer().getClip() != element) {
                        // be careful: don't call 
                        //  viewContext.getViewer().collapse(element);!!
                        // the KNode will wrongly be considered the semantic element
                        viewContext.getViewer().collapse((KNode) element);
                        LightDiagramServices.layoutDiagram(viewContext);
                    }
                }
            }
        });
        
        viewer.addSelectionChangedListener(new ISelectionChangedListener() {
            
            public void selectionChanged(final SelectionChangedEvent event) {
                if (!viewer.getControl().isFocusControl()) {
                    return;
                }
                final Object element = ((TreeSelection) event.getSelection()).getFirstElement();
                if (element instanceof KNode) {
                    // be careful: don't call 
                    //  viewContext.getViewer().collapse(element);!!
                    // the KNode will wrongly be considered the semantic element
                    
                    if (!((KNode) element).getChildren().isEmpty()) {
                        final KNode clip = viewContext.getViewer().getClip();
                        
                        if (!viewer.getExpandedState(clip)) {
                            viewContext.getViewer().collapse(clip);
                        }
                        
                        viewContext.getViewer().expand((KNode) element);
                        for (EObject k : Iterables2.toIterable(ModelingUtil
                                .eAllContainers((KNode) element))) {
                            viewContext.getViewer().expand((KNode) k);
                        }

                        viewContext.getViewer().clip((KNode) element);
                        LightDiagramServices.layoutDiagram(viewContext, true);
                    }
                }
            }
        });
        
        final IWorkbenchWindow workbenchWindow = getSite().getWorkbenchWindow();
        // be careful to unregister this listener in the 'dispose()'
        //  otherwise exceptions will occur if the view has been closed (and maybe re-opened)
        workbenchWindow.getSelectionService().addSelectionListener(this);

        // get the current selection and trigger an update
        final IWorkbenchPage activePage = workbenchWindow.getActivePage();
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
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setFocus() {
        viewer.getControl().setFocus();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose() {
        getSite().getWorkbenchWindow().getSelectionService().removeSelectionListener(this);
        contentProvider.dispose();
    }
    
    private ViewContext viewContext = null;
    
    /**
     * {@inheritDoc}
     */
    public void selectionChanged(final IWorkbenchPart part, final ISelection selection) {
        if (selection instanceof KlighdTreeSelection) {
            final ViewContext context = ((KlighdTreeSelection) selection).getViewContext();
            if (context != viewContext) {
                if (viewContext != null) {
                    viewContext.getViewer().removeViewChangedEventListener(viewChangeListener);
                }
                viewContext = context; 
                viewContext.getViewer().addViewChangedListener(viewChangeListener,
                        ViewChangeType.COLLAPSE, ViewChangeType.EXPAND);
                viewer.setInput(viewContext);
            }
            viewer.setSelection(selection);
        }
    }

    /**
     * 
     * @author chsch
     *
     */
    private static class ContentProvider implements ITreeContentProvider {

        /**
         * {@inheritDoc}
         */
        public void dispose() {
        }

        /**
         * {@inheritDoc}
         */
        public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
        }

        /**
         * {@inheritDoc}
         */
        public Object[] getElements(final Object inputElement) {
            if (inputElement instanceof KNode) {
                return new Object[] { inputElement };
                
            } else if (inputElement instanceof KlighdTreeSelection) {
                return ((KlighdTreeSelection) inputElement).getPaths();
                
            } else if (inputElement instanceof ViewContext) {
                return new Object[] { ((ViewContext) inputElement).getViewModel() };

            } else {
                return null;
            }
        }

        /**
         * {@inheritDoc}
         */
        public Object[] getChildren(final Object parentElement) {
            if (parentElement instanceof KNode) {
                return Iterables.toArray(((KNode) parentElement).getChildren(), KNode.class);
            } else {
                return null;
            }
        }

        /**
         * {@inheritDoc}
         */
        public Object getParent(final Object element) {
            if (element instanceof KNode) {
                return ((KNode) element).getParent();
            } else {
                return null;
            }
        }

        /**
         * {@inheritDoc}
         */
        public boolean hasChildren(final Object element) {
            if (element instanceof KNode) {
                return !((KNode) element).getChildren().isEmpty();
            } else {
                return false;
            }
        }
    }
    
    /**
     * @author chsch
     */
    private class LabelProvider extends org.eclipse.jface.viewers.LabelProvider {

        /**
         * {@inheritDoc}
         */
        @Override
        public String getText(final Object element) {
            if (element instanceof KNode) {
//                Object source = KlighdTreeView.this.viewContext.getSourceElement((EObject) element);
//                if (source != null) {
//                    return source.toString();
//                } else {
                    return super.getText(element);
//                }
            } else {
                return super.getText(element);
            }
        }
    }
}
