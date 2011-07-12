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
package de.cau.cs.kieler.kiml.ui.diagram;

import org.eclipse.draw2d.Animation;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.ZoomManager;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.model.GraphicalFrameworkService;
import de.cau.cs.kieler.core.model.IGraphicalFrameworkBridge;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;

/**
 * An abstract diagram layout manager for GEF-based implementations.
 *
 * @param <T> the type of diagram part that is handled by this diagram layout manager
 * @author msp
 */
public abstract class GefDiagramLayoutManager<T> extends DiagramLayoutManager<T> {

    /**
     * {@inheritDoc}
     */
    @Override
    public void applyLayout(final LayoutMapping<T> mapping, final boolean zoomToFit,
            final int animationTime) {
        Object layoutGraphObj = mapping.getParentElement();
        if (zoomToFit && layoutGraphObj instanceof EditPart) {
            // determine pre- or post-layout zoom
            IGraphicalFrameworkBridge bridge = GraphicalFrameworkService.getInstance()
                    .getBridge(layoutGraphObj);
            final ZoomManager zoomManager = bridge.getZoomManager((EditPart) layoutGraphObj);
            KNode parentNode = mapping.getLayoutGraph();
            KShapeLayout parentLayout = parentNode.getData(KShapeLayout.class);
            Dimension available = zoomManager.getViewport().getClientArea().getSize();
            float desiredWidth = parentLayout.getWidth();
            double scaleX = Math.min(available.width / desiredWidth, zoomManager.getMaxZoom());
            float desiredHeight = parentLayout.getHeight();
            double scaleY = Math.min(available.height / desiredHeight, zoomManager.getMaxZoom());
            final double scale = Math.min(scaleX, scaleY);
            final double oldScale = zoomManager.getZoom();

            if (scale < oldScale) {
                zoomManager.setViewLocation(new Point(0, 0));
                zoomManager.setZoom(scale);
                zoomManager.setViewLocation(new Point(0, 0));
            }
            applyLayout(mapping, animationTime);
            if (scale > oldScale) {
                zoomManager.setViewLocation(new Point(0, 0));
                zoomManager.setZoom(scale);
                zoomManager.setViewLocation(new Point(0, 0));
            }
        } else {
            applyLayout(mapping, animationTime);
        }
    }
    
    /**
     * Apply the computed layout to the original diagram.
     * 
     * @param mapping a layout mapping that was created by this layout manager
     * @param animationTime the animation time in milliseconds, or 0 for no animation
     */
    private void applyLayout(final LayoutMapping<T> mapping, final int animationTime) {
        // transfer layout to the diagram
        transferLayout(mapping);
        if (animationTime > 0) {
            // apply the layout with animation
            Animation.markBegin();
            applyLayout(mapping);
            Animation.run(animationTime);
        } else {
            // apply the layout without animation
            applyLayout(mapping);
        }
    }

    /**
     * Transfer all layout data from the last created KGraph instance to the
     * original diagram. The diagram is not modified yet, but all required
     * preparations are performed.
     * 
     * @param mapping a layout mapping that was created by this layout manager
     */
    protected abstract void transferLayout(LayoutMapping<T> mapping);
    
    /**
     * Apply the transferred layout to the original diagram. This final step
     * is where the actual change to the diagram is done.
     * 
     * @param mapping a layout mapping that was created by this layout manager
     */
    protected abstract void applyLayout(LayoutMapping<T> mapping);

}
