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
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.DropTargetListener;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.part.ResourceTransfer;
import org.eclipse.ui.part.ViewPart;

import de.cau.cs.kieler.klighd.IViewerProvider;
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

    /** the currently active viewer. */
    private Viewer currentViewer = null;
    /** the current control. */
    private Control currentControl = null;
    /** the view composite. */
    private Composite parentComposite = null;

    /**
     * {@inheritDoc}
     */
    @Override
    public void createPartControl(final Composite parent) {
        parentComposite = parent;
        // install a drop handler for the view
        installDropHandler();
        showMessage("No model selected.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFocus() {
        if (currentViewer != null) {
            currentViewer.getControl().setFocus();
        }
    }

    /**
     * Tries to find a viewer to show the given model. If a fitting viewer is found it is shown in
     * the view.
     * 
     * @param model
     *            the model
     * @return <code>true</code> if the view could be initialized properly, <code>false</code> if no
     *         viewer is available.
     */
    public Boolean setInputModel(final Object model) {
        ViewContext viewContext = LightDiagramServices.getInstance().getValidViewContext(model);
        if (viewContext != null) {
            setViewer(viewContext.getViewerProvider());
            currentViewer.setInput(viewContext.getModel());
            return true;
        } else {
            showMessage("No viewer registered for the model.");
            return false;
        }
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
     * Shows a message in the view.
     * 
     * @param message
     *            the message
     */
    private void showMessage(final String message) {
        if (currentControl != null) {
            currentControl.dispose();
            currentViewer = null;
            currentControl = null;
        }
        // add a canvas for displaying the message
        Canvas canvas = new Canvas(parentComposite, SWT.NONE);
        currentControl = canvas;
        canvas.addPaintListener(new PaintListener() {
            public void paintControl(final PaintEvent e) {
                e.gc.drawString(message, 0, 0, true);
            }
        });
        parentComposite.layout(true);
    }

    /**
     * Sets the viewer provided by the viewer provider as active viewer for this view.
     * 
     * @param viewerProvider
     *            the viewer provider
     */
    private void setViewer(final IViewerProvider viewerProvider) {
        if (currentControl != null) {
            currentControl.dispose();
            currentViewer = null;
            currentControl = null;
        }
        // add the viewer from the viewer provider
        currentViewer = viewerProvider.createViewer(parentComposite);
        currentControl = currentViewer.getControl();
        parentComposite.layout(true);
    }

    /**
     * Installs a handler for dropping resources on the view.
     */
    private void installDropHandler() {
        DropTarget target = new DropTarget(parentComposite, DND.DROP_MOVE | DND.DROP_DEFAULT);
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
                                setInputModel(model);
                            } else {
                                showMessage("The file does not contain an EMF resource.");
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
        TransactionalEditingDomain transactionalEditingDomain =
                TransactionalEditingDomain.Factory.INSTANCE.createEditingDomain();
        // DiagramEditingDomainFactory.INSTANCE.createEditingDomain();
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

}
