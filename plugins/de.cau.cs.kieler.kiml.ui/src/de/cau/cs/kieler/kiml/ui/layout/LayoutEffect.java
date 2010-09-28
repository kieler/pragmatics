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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.ui.IEditorPart;

import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
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
     * {@inheritDoc}
     */
    public void execute() {
        final DiagramLayoutManager manager = EclipseLayoutServices.getInstance().getManager(
                diagramEditor, editPart);
        if (manager != null) {
            MonitoredOperation.runInUI(new Runnable() {
                // first phase: build the layout graph
                public void run() {
                    manager.buildLayoutGraph(diagramEditor, editPart, true);
                }
            }, true);
            final IStatus status = manager.layout(new BasicProgressMonitor(0), true);

            // determine pre- or post-layout zoom
            ILayoutInspector inspector = manager.getInspector(manager.getCurrentEditPart());
            final ZoomManager zoomManager = inspector.getZoomManager();
            KNode current = manager.getLayoutGraph();
            while (current.eContainer() instanceof KNode) {
                current = (KNode) current.eContainer();
            }
            KShapeLayout layout = current.getData(KShapeLayout.class);
            Dimension available = zoomManager.getViewport().getClientArea().getSize();
            Dimension desired = new Dimension((int) layout.getWidth(), (int) layout.getHeight());
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
                    manager.applyAnimatedLayout(true, false, nodeCount);
                    if (scale > oldScale) {
                        zoomManager.setViewLocation(new Point(0, 0));
                        zoomManager.setZoom(scale);
                        zoomManager.setViewLocation(new Point(0, 0));
                    }
                }
            }, false);

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
     * Find the first common ancestor of two EditParts. Modelled after
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

    /**
     * Find the first common ancestor of two EditParts. Modelled after
     * GmfDiagramLayoutManager.addDummyEdgesForInterlevelConnections().
     * 
     * @param a
     * @param b
     * @return
     */
    private EObject commonAncestorOrSelf(final EObject a, final EObject b) {
        if (a == b) {
            return a;
        }
        EObject aParent = a;
        EObject bParent = b;
        List<EObject> aAncestors = new ArrayList<EObject>();
        List<EObject> bAncestors = new ArrayList<EObject>();
        aAncestors.add(a);
        bAncestors.add(b);
        do {
            if (aParent != null) {
                aParent = aParent.eContainer();
            }
            if (bParent != null) {
                bParent = bParent.eContainer();
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
