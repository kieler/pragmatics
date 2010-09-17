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
package de.cau.cs.kieler.kiml.gmf;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.render.editparts.RenderedDiagramRootEditPart;

import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.ui.util.MonitoredOperation;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.ui.layout.DiagramLayoutManager;
import de.cau.cs.kieler.kiml.ui.layout.EclipseLayoutServices;
import de.cau.cs.kieler.core.kivi.AbstractEffect;
import de.cau.cs.kieler.core.model.util.ModelingUtil;

/**
 * Performs automatic layout on an diagramEditor for a given selection.
 * 
 * @author mmu
 * 
 */
public class LayoutEffect extends AbstractEffect {

    private DiagramEditor diagramEditor;

    private EditPart editPart;

    /**
     * Create a new layout effect for the given DiagramEditor and EObject.
     * 
     * @param editor
     *            the DiagramEditor containing the diagram to layout
     * @param object
     *            the EObject to layout
     */
    public LayoutEffect(final DiagramEditor editor, final EObject object) {
        diagramEditor = editor;
        editPart = ModelingUtil.getEditPart(editor.getDiagramEditPart(), object);
        if (editPart == null) {
            // layout the entire diagram if no editpart found
            editPart = editor.getDiagramEditPart();
        }
    }

    @Override
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
            final ZoomManager zoomManager = ((RenderedDiagramRootEditPart) diagramEditor
                    .getDiagramEditPart().getRoot()).getZoomManager();
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
            final boolean zoomPre = scale < zoomManager.getZoom();

            MonitoredOperation.runInUI(new Runnable() {
                // third phase: apply layout with animation
                public void run() {
                    if (zoomPre) {
                        zoomManager.setViewLocation(new Point(0, 0));
                        zoomManager.setZoom(scale);
                        zoomManager.setViewLocation(new Point(0, 0));
                    }
                    int nodeCount = status == null ? 0 : status.getCode();
                    manager.applyAnimatedLayout(true, false, nodeCount);
                    if (!zoomPre) {
                        zoomManager.setViewLocation(new Point(0, 0));
                        zoomManager.setZoom(scale);
                        zoomManager.setViewLocation(new Point(0, 0));
                    }
                }
            }, false);

        }
    }

}
