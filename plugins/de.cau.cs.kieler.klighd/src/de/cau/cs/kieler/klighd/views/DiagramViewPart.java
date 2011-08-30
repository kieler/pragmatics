/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.views;

import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.core.DiagramEditingDomainFactory;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.DropTargetListener;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ResourceTransfer;
import org.eclipse.ui.part.ViewPart;

import de.cau.cs.kieler.kiml.ui.diagram.DiagramLayoutEngine;
import de.cau.cs.kieler.klighd.LightDiagramServices;
import de.cau.cs.kieler.klighd.ViewContext;

/**
 * A view which is able to display models in light-weight diagrams.
 * 
 * @author mri
 */
public class DiagramViewPart extends ViewPart {

    /** the default name for this view. */
    public static final String DEFAULT_NAME = "Light Diagram";

    /** the viewer for this view part. */
    private ContextViewer viewer;

    /**
     * {@inheritDoc}
     */
    @Override
    public void createPartControl(final Composite parent) {
        addLayoutButton();
        // create a context viewer
        viewer = new ContextViewer(parent, getViewSite().getSecondaryId());
        // install a drop handler for the view
        installDropHandler(parent);
        viewer.setModel("No model selected.");
        // register the context viewer as selection provider on the workbench
        getSite().setSelectionProvider(viewer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFocus() {
        viewer.getControl().setFocus();
    }

    /**
     * Returns the context viewer represented by this view part.
     * 
     * @return the context viewer
     */
    public ContextViewer getViewer() {
        return viewer;
    }

    /**
     * Sets a new name for the view.
     * 
     * @param name
     *            the name
     */
    public void setName(final String name) {
        setPartName(name);
    }

    /**
     * Installs a handler for dropping resources on the view.
     */
    private void installDropHandler(final Composite parent) {
        DropTarget target = new DropTarget(parent, DND.DROP_MOVE | DND.DROP_DEFAULT);
        final ResourceTransfer resourceTransfer = ResourceTransfer.getInstance();
        target.setTransfer(new Transfer[] { resourceTransfer });
        target.addDropListener(new DropTargetListener() {

            public void drop(final DropTargetEvent event) {
                if (resourceTransfer.isSupportedType(event.currentDataType)
                        && event.data instanceof IResource[]) {
                    IResource[] resources = (IResource[]) event.data;
                    for (IResource resource : resources) {
                        if (resource instanceof IFile) {
                            IFile file = (IFile) resource;
                            Object model = loadModel(file);
                            if (model != null) {
                                ViewContext viewContext =
                                        LightDiagramServices.getInstance().getValidViewContext(
                                                model);
                                if (viewContext != null) {
                                    viewer.setModel(viewContext);
                                } else {
                                    viewer.setModel("Could not find a viewer for the resource.");
                                }
                            } else {
                                viewer.setModel("The file does not contain an EMF resource.");
                            }
                            break;
                        }
                    }
                }
            }

            public void dropAccept(final DropTargetEvent event) {
                // do nothing
            }

            public void dragOperationChanged(final DropTargetEvent event) {
                // do nothing
            }

            public void dragEnter(final DropTargetEvent event) {
                // do nothing
            }

            public void dragOver(final DropTargetEvent event) {
                // do nothing
            }

            public void dragLeave(final DropTargetEvent event) {
                // do nothing
            }
        });
    }

    /**
     * Loads an EMF model from the given file.
     * 
     * @param file
     *            the file
     * @return the model
     */
    private Object loadModel(final IFile file) {
        // TransactionalEditingDomain.Factory factory = TransactionalEditingDomain.Factory.INSTANCE;
        TransactionalEditingDomain transactionalEditingDomain =
                DiagramEditingDomainFactory.INSTANCE.createEditingDomain();
        // factory.createEditingDomain();
        ResourceSet resourceSet = transactionalEditingDomain.getResourceSet();
        Resource resource =
                resourceSet.createResource(URI.createPlatformResourceURI(file.getFullPath()
                        .toOSString(), true));
        try {
            resource.load(null);
        } catch (IOException e) {
            return null;
        }
        if (resource.getContents().size() > 0) {
            return resource.getContents().get(0);
        } else {
            return null;
        }
    }

    // TODO just for testing purposes
    private void addLayoutButton() {
        IToolBarManager mgr = getViewSite().getActionBars().getToolBarManager();
        final DiagramViewPart view = this;
        Action layout = new Action("Layout") {
            public void run() {
                try {
                    DiagramLayoutEngine layoutEngine = DiagramLayoutEngine.INSTANCE;
                    layoutEngine.layout(view, null, true, false, false, false, null);
                } catch (UnsupportedOperationException e) {
                    // do nothing (empty view)
                }
            }
        };
        mgr.add(layout);
    }

}
