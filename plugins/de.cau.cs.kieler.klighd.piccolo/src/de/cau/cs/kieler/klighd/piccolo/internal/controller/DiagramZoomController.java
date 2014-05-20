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
package de.cau.cs.kieler.klighd.piccolo.internal.controller;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.statushandlers.StatusManager;

import com.google.common.collect.Iterables;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.klighd.ZoomStyle;
import de.cau.cs.kieler.klighd.piccolo.KlighdPiccoloPlugin;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KNodeTopNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdMainCamera;
import edu.umd.cs.piccolo.util.PAffineTransform;
import edu.umd.cs.piccolo.util.PBounds;
import edu.umd.cs.piccolo.util.PDimension;

/**
 * A dedicated controller encapsulating all the zoom related stuff.
 * 
 * @author chsch
 */
public class DiagramZoomController {

    /** the main camera that determines the actually drawn picture. */
    private final KlighdMainCamera canvasCamera;

    private KNodeTopNode topNode;
    private KNode focusNode = null;


    /**
     * Constructor.
     * 
     * @param theTopNode
     *            the employed {@link KNodeTopNode}
     * @param theCanvasCamera
     *            the employed {@link KlighdMainCamera}
     */
    public DiagramZoomController(final KNodeTopNode theTopNode, final KlighdMainCamera theCanvasCamera) {
        this.canvasCamera = theCanvasCamera;
        this.topNode = theTopNode;
    }

    /**
     * Sets the node to be focused during next 'zoom to focus' run.
     * 
     * @param node
     *            the node to be focused during next 'zoom to focus' run
     */
    public void setFocusNode(final KNode node) {
        this.focusNode = node;
    }

    /**
     * Performs a zooming depending on the specified style.
     * 
     * @param style
     *            the desired style
     * @param duration
     *            time to animate
     */
    public void zoom(final ZoomStyle style, final int duration) {
        switch (style) {
        case ZOOM_TO_ACTUAL_SIZE:
            zoomToActualSize(duration);
            break;

        case ZOOM_TO_FIT:
            zoomToFit(duration);
            break;

        case ZOOM_TO_FOCUS:
            KNode focus = focusNode != null ? focusNode : topNode.getGraphElement();
            zoomToFocus(focus, duration);
            break;

        default:
            // nothing
        }
    }

    /**
     * 
     * 
     * @param duration
     *            time to animate in ms
     */
    private void zoomToActualSize(final int duration) {
        final KNode displayedKNode = this.canvasCamera.getDisplayedINode().getGraphElement(); 
        final KShapeLayout nodeLayout = displayedKNode.getData(KShapeLayout.class);

        if (nodeLayout == null) {
            final String msg = "KLighD DiagramController: "
                    + "Failed to apply 'zoom to actual size' as the displayed node's layout data are "
                    + "unavailable. This is most likely due to a failed incremental update before.";
            StatusManager.getManager().handle(
                    new Status(IStatus.ERROR, KlighdPiccoloPlugin.PLUGIN_ID, msg),
                    StatusManager.LOG);
            return;
        }
        
        final PBounds newBounds = toPBoundsIncludingPortsAndLabels(displayedKNode);

        this.canvasCamera.animateViewToTransform(
                PAffineTransform.getTranslateInstance(-newBounds.x, -newBounds.y), duration);
    }
    
    /**
     * @param duration
     *            time to animate in ms
     */
    private void zoomToFit(final int duration) {
        final KNode displayedKNode = this.canvasCamera.getDisplayedINode().getGraphElement(); 
        final KShapeLayout nodeLayout = displayedKNode.getData(KShapeLayout.class);

        if (nodeLayout == null) {
            final String msg = "KLighD DiagramController: "
                    + "Failed to apply 'zoom to fit' as the displayed node's layout data are "
                    + "unavailable. This is most likely due to a failed incremental update before.";
            StatusManager.getManager().handle(
                    new Status(IStatus.ERROR, KlighdPiccoloPlugin.PLUGIN_ID, msg),
                    StatusManager.LOG);
            return;
        }

        final PBounds newBounds = toPBoundsIncludingPortsAndLabels(displayedKNode);

        if (this.canvasCamera.getBoundsReference().isEmpty()) {
            // this case occurs while initializing the DiagramEditorPart
            //  since the whole diagram building and layout is performed within 'createPartControl()'
            // at that time, the (widget) layout of the KlighdCanvas has not been performed and,
            //  thus, the root pnode's bounds are empty
            // this setting will be replaced by 'setBounds()' in KlighdCanvas (inherited)  
            this.canvasCamera.setBounds(newBounds);
        } else {
            this.canvasCamera.animateViewToCenterBounds(newBounds, true, duration);
        }
    }
    
    /**
     * 
     * @param focus
     *            the desired focus bounds
     * @param duration
     *            duration of the animation
     */
    private void zoomToFocus(final KNode focus, final int duration) {
        final KNode displayedKNode = this.canvasCamera.getDisplayedINode().getGraphElement(); 
        final PBounds newBounds = toPBoundsIncludingPortsAndLabels(focus);

        // we need the bounds in view coordinates (absolute), hence for
        // a knode add the translations of all parent nodes

        if (focus != displayedKNode) {
            KNode parent = focus.getParent();
            while (parent != null && parent != displayedKNode.getParent()) {
                KShapeLayout parentLayout = parent.getData(KShapeLayout.class);
                newBounds.moveBy(parentLayout.getXpos(), parentLayout.getYpos());
                parent = parent.getParent();
            }
        }

        zoomToFocus(newBounds, duration);
    }

    /**
     * 
     * @param focus
     *            the desired focus bounds
     * @param duration
     *            duration of the animation
     */
    private void zoomToFocus(final PBounds focus, final int duration) {
        final PBounds viewBounds = canvasCamera.getViewBounds();

        // check if we need to scale the view in order for the view to
        // contain the whole focus
        boolean scale = viewBounds.getWidth() < focus.getWidth()
                || viewBounds.getHeight() < focus.getHeight();

        // fetch bounds of the whole diagram
        final KNode displayedKNode = this.canvasCamera.getDisplayedINode().getGraphElement(); 
        final KShapeLayout nodeLayout = displayedKNode.getData(KShapeLayout.class);

        if (nodeLayout == null) {
            final String msg = "KLighD DiagramController: "
                    + "Failed to apply 'zoom to focus' as the displayed node's layout data are "
                    + "unavailable. This is most likely due to a failed incremental update before.";
            StatusManager.getManager().handle(
                    new Status(IStatus.ERROR, KlighdPiccoloPlugin.PLUGIN_ID, msg),
                    StatusManager.LOG);
            return;
        }

        final PBounds newBounds = toPBoundsIncludingPortsAndLabels(displayedKNode);
        
        boolean fullyContains = viewBounds.getWidth() > newBounds.getWidth()
                && viewBounds.getHeight() > newBounds.getHeight();

        // if the viewport can fully accommodate the diagram, we perform zoom to fit 
        if (fullyContains) {
            canvasCamera.animateViewToCenterBounds(newBounds, true, duration);
        } else {
            canvasCamera.animateViewToCenterBounds(focus, scale, duration);
        }
    }
    
    /**
     * Sets the zoom level to {@code newZoomLevel}. A value below 1 results in smaller elements than
     * in the original diagram, a value greater than 1 in a bigger elements than in the original.
     * 
     * The method tries retain the center point, i.e., to center over the currently centered point,
     * however, it is assured that at least some parts of the underlying diagram are visible.
     * 
     * @param newZoomLevel
     *            the new zoom level
     * @param duration
     *            time to animate
     */
    public void zoomToLevel(final float newZoomLevel, final int duration) {
        final KNode displayedKNode = this.canvasCamera.getDisplayedINode().getGraphElement(); 
        final KShapeLayout topNodeLayout = displayedKNode.getData(KShapeLayout.class);

        if (topNodeLayout == null) {
            final String msg = "KLighD DiagramController: "
                    + "Failed to apply 'zoom to level' as the displayed node's layout data are "
                    + "unavailable. This is most likely due to a failed incremental update before.";
            StatusManager.getManager().handle(
                    new Status(IStatus.ERROR, KlighdPiccoloPlugin.PLUGIN_ID, msg),
                    StatusManager.LOG);
            return;
        }

        final PBounds nodeBounds = toPBounds(displayedKNode);

        // it would be possible to use PCamera#scaleViewAboutPoint(scale, x, y), 
        // however this method does not allow for animation
        
        // calculate the bound as they would be if scaled by the new factor
        final PBounds origBounds = canvasCamera.getViewBounds();
        final double oldZoomLevel = canvasCamera.getViewTransformReference().getScale();
        
        final PBounds newBounds = new PBounds(origBounds.x, origBounds.y,
                origBounds.width * oldZoomLevel / newZoomLevel,
                origBounds.height * oldZoomLevel / newZoomLevel);

        // add the necessary translation
        final double normalizedWidth = origBounds.width * oldZoomLevel;
        final double normalizedHeight = origBounds.height * oldZoomLevel;
        final double transX = (origBounds.width - normalizedWidth / newZoomLevel) / 2f;
        final double transY = (origBounds.height - normalizedHeight / newZoomLevel) / 2f;
        
        newBounds.moveBy(transX, transY);

        // make sure at least some of the diagram is visible after zooming to scale 1
        final PDimension dim = newBounds.deltaRequiredToContain(nodeBounds);
        newBounds.moveBy(dim.width, dim.height);

        // perform the animation
        canvasCamera.animateViewToCenterBounds(newBounds, true, duration);
    }


    /**
     * Converts <code>node</code>'s layout data into {@link PBounds}, respects an attached
     * {@link LayoutOptions#SCALE_FACTOR}.
     * 
     * @param node
     *            the node
     * @return the corresponding {@link PBounds}
     */
    public static PBounds toPBounds(final KNode node) {
        final KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
        final float scale = nodeLayout.getProperty(LayoutOptions.SCALE_FACTOR); 
        return new PBounds(nodeLayout.getXpos(), nodeLayout.getYpos(),
                nodeLayout.getWidth() * scale, nodeLayout.getHeight() * scale);
    }
    
    /**
     * Converts <code>node</code>'s layout data into {@link PBounds} s.t. <code>node</code>'s ports
     * and labels are included, respects an attached {@link LayoutOptions#SCALE_FACTOR}.
     * 
     * @param node
     *            the node
     * @return the corresponding {@link PBounds}
     */
    public static PBounds toPBoundsIncludingPortsAndLabels(final KNode node) {
        return includePortAndLabelBounds(toPBounds(node), node);
    }    

    /**
     * This method checks for ports and labels of the given <code>node</code> and increases the
     * given <code>nodeBounds</code> accordingly.<br>
     * 
     * 
     * @param nodeBounds
     *            the bounds of the {@link de.cau.cs.kieler.klighd.piccolo.internal.nodes.INode
     *            INode} representing {@link KNode} <code>node</code>
     * @param node
     *            the {@link KNode} to be evaluated for ports and labels
     * @return the updated <code>nodeBounds</code> for convenience
     */
    private static PBounds includePortAndLabelBounds(final PBounds nodeBounds, final KNode node) {
        double maxX = nodeBounds.getWidth();
        double maxY = nodeBounds.getHeight();
        final float scale = node.getData(KShapeLayout.class).getProperty(LayoutOptions.SCALE_FACTOR); 
        
        // these min values are <= 0 at all times!
        double minX = 0;
        double minY = 0;

        boolean includedElement = false;
        
        for (KGraphElement element : Iterables.concat(node.getPorts(), node.getLabels())) {
            final KShapeLayout pL = element.getData(KShapeLayout.class);
            float val;

            val = pL.getXpos() * scale; 
            if (val < minX) {
                minX = val;
            }

            val = pL.getYpos() * scale; 
            if (val  < minY) {
                minY = val;
            }

            val = pL.getXpos() * scale + pL.getWidth() * scale;
            if (val > maxX) {
                maxX = val;
            }

            val = pL.getYpos() * scale + pL.getHeight() * scale;
            if (val > maxY) {
                maxY = val;
            }

            includedElement = true;
        }
        
        if (includedElement) {
            nodeBounds.setRect(nodeBounds.getX() + minX, nodeBounds.getY() + minY,
                    maxX - minX, maxY - minY);
        } else {
            final KInsets insets = node.getData(KShapeLayout.class).getInsets();
            nodeBounds.setRect(nodeBounds.getX() + insets.getLeft() * scale,
                    nodeBounds.getY() + insets.getTop() * scale,
                    maxX - insets.getLeft() - insets.getRight() * scale,
                    maxY - insets.getTop() - insets.getBottom() * scale);
        }

        return nodeBounds;
    }
}
