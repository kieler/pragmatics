/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.ui.layout;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.ui.KielerProgressMonitor;
import de.cau.cs.kieler.core.ui.util.MonitoredOperation;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.core.kivi.AbstractEffect;
import de.cau.cs.kieler.core.kivi.IEffect;
import de.cau.cs.kieler.core.kivi.UndoEffect;
import de.cau.cs.kieler.core.model.util.ModelingUtil;

/**
 * Performs automatic layout on an diagramEditor for a given selection.
 * 
 * @author mmu
 */
public class LayoutEffect extends AbstractEffect {

    private IEditorPart diagramEditor;

    private EditPart editPart;

    private boolean zoom = true;

    private boolean progressMonitor = false;

    /**
     * Create a new layout effect for the given DiagramEditor and EObject.
     * 
     * @param editor
     *            the DiagramEditor containing the diagram to layout
     * @param object
     *            the EObject to layout
     */
    public LayoutEffect(final IEditorPart editor, final EObject object) {
        diagramEditor = editor;
        editPart = ModelingUtil.getEditPart(editor, object);
    }

    /**
     * Create a new layout effect for the given DiagramEditor and EObject.
     * 
     * @param editor
     *            the DiagramEditor containing the diagram to layout
     * @param object
     *            the EObject to layout
     * @param zoomToFit
     *            true if zoom to fit shall be performed
     * @param progressBar
     *            true if a progress bar shall be displayed
     */
    public LayoutEffect(final IEditorPart editor, final EObject object, final boolean zoomToFit,
            final boolean progressBar) {
        this(editor, object);
        zoom = zoomToFit;
        progressMonitor = progressBar;

    }

    /**
     * {@inheritDoc}
     * 
     * TODO avoid some copypasta.
     */
    public void execute() {
        final DiagramLayoutManager manager = EclipseLayoutServices.getInstance().getManager(
                diagramEditor, editPart);
        if (manager != null) {
            if (zoom) {
                if (progressMonitor) {
                    final MonitoredOperation monitoredOperation = new MonitoredOperation() {
                        // first phase: build the layout graph
                        @Override
                        protected void preUIexec() {
                            manager.buildLayoutGraph(diagramEditor, editPart, true);
                        }

                        // second phase: execute layout algorithms
                        @Override
                        protected IStatus execute(final IProgressMonitor monitor) {
                            return manager.layout(new KielerProgressMonitor(monitor,
                                    DiagramLayoutManager.MAX_PROGRESS_LEVELS), true);
                        }

                        // third phase: apply layout with animation
                        @Override
                        protected void postUIexec(final IStatus status) {
                            if (status.getSeverity() == IStatus.OK) {
                                // determine pre- or post-layout zoom
                                ILayoutInspector inspector = manager.getInspector(manager
                                        .getCurrentEditPart());
                                final ZoomManager zoomManager = inspector.getZoomManager();
                                KNode current = manager.getLayoutGraph();
                                while (current.eContainer() instanceof KNode) {
                                    current = (KNode) current.eContainer();
                                }
                                KShapeLayout layout = current.getData(KShapeLayout.class);
                                Dimension available = zoomManager.getViewport().getClientArea()
                                        .getSize();
                                Dimension desired = new Dimension((int) layout.getWidth(),
                                        (int) layout.getHeight());
                                double scaleX = Math.min(available.width / (double) desired.width,
                                        zoomManager.getMaxZoom());
                                double scaleY = Math.min(
                                        available.height / (double) desired.height,
                                        zoomManager.getMaxZoom());
                                final double scale = Math.min(scaleX, scaleY);
                                final double oldScale = zoomManager.getZoom();

                                if (scale < oldScale) {
                                    zoomManager.setViewLocation(new Point(0, 0));
                                    zoomManager.setZoom(scale);
                                    zoomManager.setViewLocation(new Point(0, 0));
                                }
                                int nodeCount = status == null ? 0 : status.getCode();
                                manager.applyAnimatedLayout(true, true, nodeCount);
                                if (scale > oldScale) {
                                    zoomManager.setViewLocation(new Point(0, 0));
                                    zoomManager.setZoom(scale);
                                    zoomManager.setViewLocation(new Point(0, 0));
                                }
                            }
                        }
                    };
                    monitoredOperation.runMonitored();
                } else {
                    MonitoredOperation.runInUI(new Runnable() {
                        // first phase: build the layout graph
                        public void run() {
                            manager.buildLayoutGraph(diagramEditor, editPart, true);
                        }
                    }, true);
                    final IStatus status = manager.layout(new BasicProgressMonitor(0), true);

                    if (status.getSeverity() == IStatus.OK) {
                        // determine pre- or post-layout zoom
                        ILayoutInspector inspector = manager.getInspector(manager
                                .getCurrentEditPart());
                        final ZoomManager zoomManager = inspector.getZoomManager();
                        KNode current = manager.getLayoutGraph();
                        while (current.eContainer() instanceof KNode) {
                            current = (KNode) current.eContainer();
                        }
                        KShapeLayout layout = current.getData(KShapeLayout.class);
                        Dimension available = zoomManager.getViewport().getClientArea().getSize();
                        Dimension desired = new Dimension((int) layout.getWidth(),
                                (int) layout.getHeight());
                        double scaleX = Math.min(available.width / (double) desired.width,
                                zoomManager.getMaxZoom());
                        double scaleY = Math.min(available.height / (double) desired.height,
                                zoomManager.getMaxZoom());
                        final double scale = Math.min(scaleX, scaleY);
                        final double oldScale = zoomManager.getZoom();

                        MonitoredOperation.runInUI(new Runnable() {
                            // third phase: apply layout with animation
                            public void run() {
                                if (scale < oldScale) {
                                    zoomManager.setViewLocation(new Point(0, 0));
                                    zoomManager.setZoom(scale);
                                    zoomManager.setViewLocation(new Point(0, 0));
                                }
                                int nodeCount = status == null ? 0 : status.getCode();
                                manager.applyAnimatedLayout(true, true, nodeCount);
                                if (scale > oldScale) {
                                    zoomManager.setViewLocation(new Point(0, 0));
                                    zoomManager.setZoom(scale);
                                    zoomManager.setViewLocation(new Point(0, 0));
                                }
                            }
                        }, false);
                    } else {
                        int handlingStyle;
                        switch (status.getSeverity()) {
                        case IStatus.ERROR:
                            handlingStyle = StatusManager.SHOW | StatusManager.LOG;
                            break;
                        default:
                            handlingStyle = StatusManager.LOG;
                        }
                        StatusManager.getManager().handle(status, handlingStyle);
                    }
                }
            } else {
                manager.layout(diagramEditor, editPart, true, progressMonitor, true, true);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMergeable() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IEffect merge(final IEffect otherEffect) {
        if (otherEffect instanceof LayoutEffect) {
            LayoutEffect other = (LayoutEffect) otherEffect;
            if (diagramEditor == other.diagramEditor) {
                editPart = commonAncestorOrSelf(editPart, other.editPart);
                zoom |= other.zoom;
                progressMonitor |= progressMonitor;
                return this;
            }
        }
        if (otherEffect instanceof UndoEffect) {
            if (((UndoEffect) otherEffect).getEffect() instanceof LayoutEffect) {
                return this;
            }
        }
        return null;
    }

    /**
     * Find the first common ancestor of two EditParts. Modeled after
     * GmfDiagramLayoutManager.addDummyEdgesForInterlevelConnections().
     * 
     * @param a
     * @param b
     * @return
     */
    private EditPart commonAncestorOrSelf(final EditPart a, final EditPart b) {
        if (a == b) {
            return a;
        }
        EditPart aParent = a;
        EditPart bParent = b;
        List<EditPart> aAncestors = new ArrayList<EditPart>();
        List<EditPart> bAncestors = new ArrayList<EditPart>();
        aAncestors.add(a);
        bAncestors.add(b);
        do {
            if (aParent != null) {
                aParent = aParent.getParent();
            }
            if (bParent != null) {
                bParent = bParent.getParent();
            }
            if (aParent != null) {
                aAncestors.add(aParent);
            }
            if (bParent != null) {
                bAncestors.add(bParent);
            }
            if (aAncestors.contains(bParent)) {
                return bParent;
            }
            if (bAncestors.contains(aParent)) {
                return aParent;
            }
        } while (!(aParent == null && bParent == null));
        return null;
    }
}
