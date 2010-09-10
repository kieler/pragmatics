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
package de.cau.cs.kieler.kivi.examples;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.render.editparts.RenderedDiagramRootEditPart;
import org.eclipse.ui.part.EditorPart;

import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.ui.util.MonitoredOperation;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.ui.layout.DiagramLayoutManager;
import de.cau.cs.kieler.kiml.ui.layout.EclipseLayoutServices;
import de.cau.cs.kieler.kivi.core.impl.AbstractEffect;

/**
 * Performs automatic layout on an editor for a given selection.
 * 
 * @author mmu
 * 
 */
public class LayoutEffect extends AbstractEffect {

    private EditorPart editorPart;

    private EditPart editPart;

    /**
     * Create a new layout effect for the given editor and object.
     * 
     * @param editor
     *            the editor containing the diagram to layout
     * @param eP
     *            the edit part to layout
     */
    public LayoutEffect(final EditorPart editor, final EditPart eP) {
        editorPart = editor;
        editPart = eP;
    }

    @Override
    public void execute() {
        final DiagramLayoutManager manager = EclipseLayoutServices.getInstance().getManager(
                editorPart, editPart);
        if (manager != null) {
            MonitoredOperation.runInUI(new Runnable() {
                // first phase: build the layout graph
                public void run() {
                    manager.buildLayoutGraph(editorPart, editPart, true);
                }
            }, true);
            final IStatus status = manager.layout(new BasicProgressMonitor(0), true);
            
            // check whether to zoom before animation
            if (editorPart instanceof DiagramEditor) {
                DiagramEditPart diagramEditPart = ((DiagramEditor) editorPart).getDiagramEditPart();
                final ZoomManager zoomManager = ((RenderedDiagramRootEditPart) diagramEditPart
                        .getRoot()).getZoomManager();
                KNode current = manager.getLayoutGraph();
                while (current.eContainer() != null && current.eContainer() instanceof KNode) {
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

        // EclipseLayoutServices.getInstance().layout(editorPart, editPart, true, false, true);
    }

}
