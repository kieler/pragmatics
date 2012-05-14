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
package de.cau.cs.kieler.kiml.graphiti;

import org.eclipse.draw2d.Animation;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.graphiti.ui.internal.parts.IPictogramElementEditPart;
import org.eclipse.graphiti.ui.internal.util.gef.ScalableRootEditPartAnimated;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.ui.diagram.IDiagramLayoutManager;
import de.cau.cs.kieler.kiml.ui.diagram.LayoutMapping;

/**
 * An abstract diagram layout manager for GEF-based implementations.
 * This variant is tuned for Graphiti diagram editors.
 *
 * @param <T> the type of diagram part that is handled by this diagram layout manager
 * @author msp
 */
@SuppressWarnings("restriction")
public abstract class GefDiagramLayoutManager<T> implements IDiagramLayoutManager<T> {

    /** the animation time used for layout. */
    public static final IProperty<Integer> ANIMATION_TIME = new Property<Integer>(
            "gef.animationTime", 0);
    
    /**
     * {@inheritDoc}
     */
    public void applyLayout(final LayoutMapping<T> mapping, final boolean zoomToFit,
            final int animationTime) {
        mapping.setProperty(ANIMATION_TIME, animationTime);
        Object layoutGraphObj = mapping.getParentElement();
        if (zoomToFit && layoutGraphObj instanceof EditPart) {
            // determine pre- or post-layout zoom
            GraphicalViewer viewer = ((IPictogramElementEditPart) layoutGraphObj)
                    .getConfigurationProvider().getDiagramEditor().getGraphicalViewer();
            ZoomManager zoomManager = ((ScalableRootEditPartAnimated) viewer.getRootEditPart())
                    .getZoomManager();
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
    
    /**
     * {@inheritDoc}
     */
    public void undoLayout(final LayoutMapping<T> mapping) {
        int animationTime = mapping.getProperty(ANIMATION_TIME);
        if (animationTime > 0) {
            // undo the layout with animation
            Animation.markBegin();
            performUndo(mapping);
            Animation.run(animationTime);
        } else {
            // undo the layout without animation
            performUndo(mapping);
        }
    }
    
    /**
     * Perform undo in the original diagram (optional operation).
     *
     * @param mapping a layout mapping that was created by this layout manager
     */
    protected void performUndo(final LayoutMapping<T> mapping) {
        throw new UnsupportedOperationException("Undo is not supported by this layout manager.");
    }

}
