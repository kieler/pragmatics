/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2015 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.internal.events;

import java.awt.geom.Dimension2D;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;

import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.klighd.KlighdConstants;
import de.cau.cs.kieler.klighd.KlighdPreferences;
import de.cau.cs.kieler.klighd.LightDiagramServices;
import de.cau.cs.kieler.klighd.piccolo.KlighdSWTGraphics;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KDisposingLayer;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KLabelNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KNodeNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdStyledText;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.PAlignmentNode;
import de.cau.cs.kieler.klighd.piccolo.internal.util.KlighdPaintContext;
import de.cau.cs.kieler.klighd.piccolo.internal.util.NodeUtil;
import de.cau.cs.kieler.klighd.piccolo.viewer.PiccoloViewer;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.event.PDragSequenceEventHandler;
import edu.umd.cs.piccolo.event.PInputEvent;
import edu.umd.cs.piccolo.util.PAffineTransform;
import edu.umd.cs.piccolo.util.PPaintContext;

/**
 *
 * @author chsch
 * @author mkr
 */
public class KlighdInteractiveDragEventHandler extends PDragSequenceEventHandler {

    private static final int HALF_OPACITY = KlighdConstants.ALPHA_FULL_OPAQUE / 2;

    private final PiccoloViewer pViewer;

    private boolean enabled;

    // The node picked by clicking on node/label
    private PNode pickedNode;
    
    // The KNode representing the current KNodeNode
    private KNode knode;

    // The PNode representing the current position of the dragged KNode 
    private PNode node;
    
    /**
     * Constructor.
     *
     * @param pViewer
     *            the {@link PiccoloViewer} this handler is attached to
     */
    public KlighdInteractiveDragEventHandler(final PiccoloViewer pViewer) {
        this.pViewer = pViewer;
        this.enabled = KlighdPreferences.isInteractiveDragEnabled();

        // Update variable enabled, if preference is changed
        KlighdPreferences.registerPrefChangeListener(pViewer.getControl(), 
                new IPropertyChangeListener() {

                    public void propertyChange(final PropertyChangeEvent event) {
                        final String prop = event.getProperty();

                        if (KlighdPreferences.INTERACTIVE_DRAG_ENABLED.equals(prop)) {
                            enabled = KlighdPreferences.isInteractiveDragEnabled();
                        }
                    }
        });
    }

    /**
     * Dragging nodes should only start if shift is pressed and a node selected.
     * Needed to distinguish from other drag-events.
     * {@inheritDoc}
     */
    @Override
    protected boolean shouldStartDragInteraction(final PInputEvent event) {
        PNode object = event.getPickedNode();
        pickedNode = null;
        
        // Extracts the KNodeNode, if selected element is type of
        // KlighdStyledText.
        if (object instanceof KNodeNode) {
            pickedNode = object;
        } else if (object instanceof KlighdStyledText) {
            PAlignmentNode pAligNode = (PAlignmentNode) object.getParent();
            KLabelNode labelNode = (KLabelNode) pAligNode.getParent() ;
            KDisposingLayer disposingLayer = (KDisposingLayer) labelNode.getParent();
            pickedNode = (PNode) disposingLayer.getParent();
        }        
        
        return event.isShiftDown() && enabled
                && pickedNode != null
                && super.shouldStartDragInteraction(event);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void startDrag(final PInputEvent event) {

        super.startDrag(event);

        final KNodeNode nodeNode = (KNodeNode) pickedNode;
        knode = nodeNode.getGraphElement();
        final Rectangle2D bounds = nodeNode.getFullBounds();

        final PAffineTransform invertedNodeNodeTransform =
                new PAffineTransform(NodeUtil.invert(nodeNode.getTransform()));
        
        invertedNodeNodeTransform.transform(bounds, bounds);
        // Create a new, transparent PNode to show current position. The node to be
        // dragged is still not moved.         
        node = new PNode() {

            private static final long serialVersionUID = -3737436221817065320L;

            @Override
            public void fullPaint(final PPaintContext paintContext) {
                                
                //  Push the transform reference of PNode to stack
                //  to get the reference of the new node.  
                paintContext.pushTransform(getTransformReference(false));
                
                // Set the new PNodes graphic to the KNodes one
                // and add transparency.
                // Save the old transparency value to restore value later.
                final KlighdPaintContext pc = (KlighdPaintContext) paintContext;
                final KlighdSWTGraphics graphics = pc.getKlighdGraphics();
                final int alpha = graphics.getAlpha();

                graphics.setAlpha(HALF_OPACITY);

                // Push the invertedNodeNodeTransform on Stack to get the
                // current transform of the KNodeNode and paint this and 
                // its context.
                paintContext.pushTransform(invertedNodeNodeTransform);
                nodeNode.fullPaint(paintContext);

                paintContext.popTransform(invertedNodeNodeTransform);

                // Restore old transparency value:
                graphics.setAlpha(alpha);
                paintContext.popTransform(getTransformReference(false));
            };
        };
        
        //Set the shown PNodes bounds at new position.
        //Set the transform to the ones of the KNodeNode. 
        node.setBounds(bounds);
        node.setTransform(event.getPath().getPathTransformTo(pickedNode));

        // Adds the PNode to the top camera, so it will appear while dragging
        // at layer. 
        event.getTopCamera().addChild(node);
        event.setHandled(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void drag(final PInputEvent event) {
        super.drag(event);

        // Get the delta between the old and new mouse position. Translate it
        // to the new coordinates of the shown PNode.
        final Dimension2D d = event.getDelta();
        node.translate(d.getWidth(), d.getHeight());

        event.setHandled(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void endDrag(final PInputEvent event) {
        super.endDrag(event);


        // Remove the PNode representing the new KNodes position from camera. 
        node.removeFromParent();

        KShapeLayout ksl = knode.getData(KShapeLayout.class);
        ksl.setPos((float) node.getTransform().getTranslateX(), 
                (float) node.getTransform().getTranslateY());

        pViewer.getControl().getDisplay().asyncExec(new Runnable() {
            public void run() {
                LightDiagramServices.layoutDiagram(pViewer.getViewContext());
            }
        });
        knode = null;
        node = null;
        event.setHandled(true);
    }
}
