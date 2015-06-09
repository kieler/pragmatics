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

import java.awt.geom.AffineTransform;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeListener;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.LineAttributes;

import com.google.common.collect.ImmutableList;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.config.ILayoutConfig;
import de.cau.cs.kieler.kiml.config.VolatileLayoutConfig;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.klay.layered.p1cycles.CycleBreakingStrategy;
import de.cau.cs.kieler.klay.layered.p2layers.LayeringStrategy;
import de.cau.cs.kieler.klay.layered.p3order.CrossingMinimizationStrategy;
import de.cau.cs.kieler.klay.layered.properties.InternalProperties;
import de.cau.cs.kieler.klay.layered.properties.Properties;
import de.cau.cs.kieler.klighd.KlighdConstants;
import de.cau.cs.kieler.klighd.KlighdPreferences;
import de.cau.cs.kieler.klighd.LightDiagramServices;
import de.cau.cs.kieler.klighd.internal.util.KlighdInternalProperties;
import de.cau.cs.kieler.klighd.piccolo.KlighdSWTGraphics;
import de.cau.cs.kieler.klighd.piccolo.internal.controller.DiagramController;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KEdgeNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KLabelNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KNodeNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KNodeTopNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdAlignmentNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdDisposingLayer;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdPath;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdStyledText;
import de.cau.cs.kieler.klighd.piccolo.internal.util.KlighdPaintContext;
import de.cau.cs.kieler.klighd.piccolo.internal.util.NodeUtil;
import de.cau.cs.kieler.klighd.piccolo.viewer.PiccoloViewer;
import de.cau.cs.kieler.klighd.util.RenderingContextData;
import edu.umd.cs.piccolo.PCamera;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.event.PDragSequenceEventHandler;
import edu.umd.cs.piccolo.event.PInputEvent;
import edu.umd.cs.piccolo.util.PAffineTransform;
import edu.umd.cs.piccolo.util.PPaintContext;
import edu.umd.cs.piccolo.util.PPickPath;

/**
 * The KlighdInteractiveDragEventHandeler enables the possibility to configure the layout
 * with drag and drop actions. Always a valid drag and drop action was performed, the
 * new layout will be calculated by the interactive layout algorithms.
 *
 * @author chsch
 * @author mkr
 */
public class KlighdInteractiveDragEventHandler extends PDragSequenceEventHandler {

    private final PiccoloViewer pViewer;
    
    private final DiagramController diagController;
    
    // True if interactive drag is enabled, false else
    private boolean enabled;

    // The node picked by clicking on node/label
    private PNode pickedNode;
    
    // The KNode representing the current KNodeNode
    private KNode knode;

    // The PNode representing the current position of the dragged KNode 
    private PNode node;
    
    private KNode clipNode;
    private PNode clipNodeNode;
    
    // Representing the incoming edges of the current dragged KNode
    private PNode pNodeIncomingEdges;

    // Representing the outgoing edges of the current dragged KNode
    private PNode pNodeOutgoingEdges;

    // Highlight the dragged node and the layer where to drag
    private PNode dragArea;
    
    // Represent warning if position isn't valid
    private PNode warning;
    
    // Highlighting of the current layer
    private PNode layer;
    
    // Layer position used by the layout algorithm to calculate layout
    private List<Float> layerPos;
    
    // Event listener to scale the transparent, dragged node.
    private PropertyChangeListener cameraViewListener;

    private boolean associatedNodeInNextLayer;

    private boolean outOfParentBounds;

    /**
     * Constructor.
     *
     * @param pViewer
     *            the {@link PiccoloViewer} this handler is attached to
     * @param diagController
     *            the {@link DiagramController} used to layout diagram
     */
    public KlighdInteractiveDragEventHandler(
            final PiccoloViewer pViewer, final DiagramController diagController) {
        this.pViewer = pViewer;
        this.diagController = diagController;
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
        final PNode object = event.getPickedNode();
        pickedNode = null;        
        clipNodeNode = null;

        if (object instanceof KNodeNode) {
            pickedNode = object;
        } else if (object instanceof KlighdStyledText) {
            // Extracts the KNodeNode, if selected element is type of
            // KlighdStyledText.
            final KlighdAlignmentNode pAligNode = (KlighdAlignmentNode) object.getParent();
            
            if (!(pAligNode.getParent() instanceof KlighdPath)) {
                final KLabelNode labelNode = (KLabelNode) pAligNode.getParent();
                final KlighdDisposingLayer disposingLayer = (KlighdDisposingLayer) labelNode.getParent();
                pickedNode = disposingLayer.getParent();
            }
        }
        
        return event.isShiftDown() && enabled
                && !pViewer.isMagnificationLensVisible()
                && pickedNode != null
                && super.shouldStartDragInteraction(event);
    }

    /**
     * Concatenate all transforms to ensure right graphic position
     * of dragged node.
     */
    private final AffineTransform transform = new AffineTransform();

    /**
     * The standard distance to ensure fitting drag graphics
     */
    private static final float STD_DISTANCE = 6f;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void startDrag(final PInputEvent event) {
        
        super.startDrag(event);

        final KNodeNode nodeNode = (KNodeNode) pickedNode;
        knode = nodeNode.getViewModelElement();
        layerPos = knode.getParent().getData(
                KShapeLayout.class).getProperty(InternalProperties.LAYER_POSITION);
        final Rectangle2D bounds = nodeNode.getFullBounds();
        
        clipNode = diagController.getClip();
        clipNodeNode = (PNode) diagController.getRepresentation(clipNode);
        
        final PAffineTransform invertedNodeNodeTransform =
                new PAffineTransform(NodeUtil.invert(nodeNode.getTransform()));
        
        invertedNodeNodeTransform.transform(bounds, bounds);
        
        // Create a new, transparent PNode to show current position. The node to be
        // dragged is still not moved.
        node = new PNode() {

            private static final long serialVersionUID = -3737436221817065320L;

            @Override
            public void fullPaint(final PPaintContext paintContext) {

                // Push the transform reference of PNode to stack
                // to get the reference of the new node.
                paintContext.pushTransform(getTransformReference(false));

                // Set the new PNodes graphic to the KNodes one
                // and add transparency.
                // Save the old transparency value to restore value later.
                final KlighdPaintContext pc = (KlighdPaintContext) paintContext;
                final KlighdSWTGraphics graphics = pc.getKlighdGraphics();
                final int alpha = graphics.getAlpha();

                graphics.setAlpha(KlighdConstants.ALPHA_HALF_OPAQUE);

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
        
        // Creates new PNode containing several KlighdPath to show
        // the incoming and outgoing KEdges while dragging.
        pNodeIncomingEdges = new PNode();
        pNodeOutgoingEdges = new PNode();

        // Add the incoming and outgoing edges to the PNodes
        final List<KEdge> incoming = knode.getIncomingEdges();
        final List<KEdge> outgoing = knode.getOutgoingEdges();
        edgesToPNode(incoming, pNodeIncomingEdges);
        edgesToPNode(outgoing, pNodeOutgoingEdges);
        
        // PNode containing the information to highlight selected area
        dragArea = new PNode();

        // PickedNode attributes to calculate position of the selection highlighting
        float xCoordinate = (float) pickedNode.getTransform().getTranslateX();
        float yCoordinate = (float) pickedNode.getTransform().getTranslateY();
        final float fullWidth = (float) pickedNode.getFullBounds().getWidth();
        final float fullHeight = (float) pickedNode.getFullBounds().getHeight();
        final float width = (float) pickedNode.getBounds().getWidth();
        final float height = (float) pickedNode.getBounds().getHeight();
        
        // Calculate the x, y-Coordinates of the upper left corner of the box
        xCoordinate = xCoordinate - (fullWidth / 2) + (width / 2);
        yCoordinate = yCoordinate - (fullHeight / 2) + (height / 2);

        final LineAttributes dottedLine = new LineAttributes(
                1, SWT.CAP_FLAT, SWT.JOIN_MITER, SWT.LINE_DASH, null, 0, 10);

        // Calculate the highlighting area around the dragged node
        final KlighdPath pathRectangle = new KlighdPath();

        pathRectangle.setPathToRectangle(
                xCoordinate - STD_DISTANCE, yCoordinate - STD_DISTANCE,
                fullWidth + (2 * STD_DISTANCE), fullHeight + (2 * STD_DISTANCE));
        pathRectangle.setPaintAlpha(KlighdConstants.ALPHA_QUARTER_OPAQUE);
        pathRectangle.setLineAttributes(dottedLine);
        pathRectangle.setPaint(KlighdConstants.BLUE);

        transformByAllParents(pickedNode.getParent(), pathRectangle);

        dragArea.addChild(pathRectangle);

        // Calculate the layer borders shown until dragging ends
        // (if constant 100 is changed, change parts below)
        final Iterator<Float> layerIt = layerPos.iterator();
        while (layerIt.hasNext()) {
            final float pos = layerIt.next();
            final KlighdPath path = new KlighdPath();
            
            path.setPathToPolyline(new Point2D[]{
                    new Point2D.Float(pos, -100),
                    new Point2D.Float(pos, 
                            (float) pickedNode.getParent().getParent().getFullBounds().getHeight() + 100)
            });
            
            path.setLineAttributes(dottedLine);
            path.setStrokeColor(KlighdConstants.BLUE);

            transformByAllParents(pickedNode.getParent(), path);
            
            dragArea.addChild(path);
        }

        // Initialize transform with identity
        transform.setToIdentity();
        final PPickPath pickpath = event.getPath();
        
        //Set the shown PNodes bounds at new position.
        //Set the transform to the ones of the KNodeNode. 
        node.setBounds(bounds);

        layer = new PNode();
        warning = new PNode(); 
        warning.setBounds(bounds);
        
        
        // Add PropertyChangeListener to recognize mouse wheel events
        // used to scale the diagram. Scale will be used for the new
        // edges and the dragged PNode.
        cameraViewListener = new PropertyChangeListener() {
            
            public void propertyChange(final java.beans.PropertyChangeEvent evt) {
                // Set the nodes transform to the current transform of the picked
                // node.
                node.setTransform(pickpath.getPathTransformTo(pickedNode));
                node.transformBy(transform);
                
                warning.setTransform(pickpath.getPathTransformTo(pickedNode));
                warning.transformBy(transform);
                
                // Set the edges transform to the current Transform of the
                // view.
                final PAffineTransform parentViewGlobalTransform =
                        pickpath.getTopCamera().getViewTransformReference();
                pNodeOutgoingEdges.setTransform(parentViewGlobalTransform);
                pNodeIncomingEdges.setTransform(parentViewGlobalTransform);

                dragArea.setTransform(parentViewGlobalTransform);
                layer.setTransform(parentViewGlobalTransform);                
            }
        };
        
        // Calls the listener one time.
        cameraViewListener.propertyChange(null);
       
       final PCamera topCamera = event.getTopCamera();
       
       // Add PropertyChangeListener to the top camera.
       topCamera.addPropertyChangeListener(
               PCamera.PROPERTY_VIEW_TRANSFORM, cameraViewListener);
        
        // Adds the PNode to the top camera, so it will appear while dragging
        // at layer. Also all other necessary nodes are added.
        topCamera.addChild(dragArea);
        topCamera.addChild(layer);
        topCamera.addChild(pNodeIncomingEdges);
        topCamera.addChild(pNodeOutgoingEdges);
        topCamera.addChild(node);
        topCamera.addChild(warning);
        event.setHandled(true);
    }
    
    // Parent node of the dragged node, could be null if there is no parent
    private PNode parent;
    
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    protected void drag(final PInputEvent event) {
        super.drag(event);
        
        warning.removeAllChildren();

        KlighdPath leftToRight = new KlighdPath();
        KlighdPath rightToLeft = new KlighdPath();

        // Get the delta between the old and new mouse position. Translate it
        // to the new coordinates of the shown PNode.
        final Dimension2D d = event.getDelta();
        
        // Create an AffineTransform to get the current transform for the PNode     
        final AffineTransform deltaTransform =
                AffineTransform.getTranslateInstance(d.getWidth(),  d.getHeight());
       
        // Concatenate all transforms, so the current position of the PNode isn´t lost
        // after new scale while dragging
        transform.concatenate(deltaTransform);
        
        node.transformBy(deltaTransform);
        warning.transformBy(deltaTransform);
        
        // Translate coordinates relative to the parent of the picked node
     
        KVector posRelativeToParent = KimlUtil.toRelative(
                new KVector(event.getPosition().getX(), event.getPosition().getY()), knode.getParent());

        // Check if node out of parent bounds.
        // Get possible parent KNodeNode, null if parent doesnt exists
        parent = pickedNode.getParent();
        
        while (parent != null) {
            if (parent instanceof KNodeNode) {
                break;
            }
            
            parent = parent.getParent();
        }
        
        
        // Highlighting Layer
        KlighdPath highlightedLayer = new KlighdPath();
        layer.removeAllChildren();
        
        float choosenLayer = 0;
        int index = 0;
        for (float pos : layerPos) {
            if (posRelativeToParent.x < pos) {
                break;
            }
            
            choosenLayer = pos;
            index++;
        }
        
        // check if picked node is related to node next layer
        associatedNodeInNextLayer = false;
        

            
        Iterator<KEdge> checkIncoming = knode.getIncomingEdges().iterator();
        associatedNodeInNextLayer = isNodeNextLayer(checkIncoming, index, choosenLayer, true);
        
        Iterator<KEdge> checkOutgoing = knode.getOutgoingEdges().iterator();
        associatedNodeInNextLayer = associatedNodeInNextLayer
                ? true : isNodeNextLayer(checkOutgoing, index, choosenLayer, false);
        
        outOfParentBounds = false;
        
        // Check position of the current node if parent exists.
        if (parent != null) {

        // constants 60 and 100 can be found at "Layerborders" and the "warning" part - also change this
            if (posRelativeToParent.x < layerPos.get(0) - 60 
                    || posRelativeToParent.y < -100
                    || layerPos.get(layerPos.size() - 1) + 60 < posRelativeToParent.x
                    || (float) parent.getParent().getFullBounds().getHeight() + 100 < posRelativeToParent.y
                    || associatedNodeInNextLayer) {
                outOfParentBounds = true;      
                associatedNodeInNextLayer = true;
            }
        }
        
        // red cross if action is not allowed!
        if (associatedNodeInNextLayer
                || outOfParentBounds) {            
            leftToRight.setPathToPolyline(new Point2D[]{
                    new Point2D.Float(
                            (float) pickedNode.getTransform().getTranslateX(), 
                            (float) pickedNode.getTransform().getTranslateY()),
                            new Point2D.Float(
                                    (float) pickedNode.getTransform().getTranslateX()
                                    + (float) pickedNode.getBounds().getWidth(),
                                    (float) pickedNode.getTransform().getTranslateY()
                                    + (float) pickedNode.getBounds().getHeight())        
            });
            
            leftToRight.setStrokeColor(KlighdConstants.RED);
            leftToRight.setLineWidth(
                    (10f / 80f) * (float) pickedNode.getBounds().getHeight());
            leftToRight.transformBy(pickedNode.getInverseTransform());
            
            rightToLeft.setPathToPolyline(new Point2D[]{
                    new Point2D.Float(
                            (float) pickedNode.getTransform().getTranslateX()
                            + (float) pickedNode.getBounds().getWidth(),
                            (float) pickedNode.getTransform().getTranslateY()),
                            new Point2D.Float(
                                    (float) pickedNode.getTransform().getTranslateX(),
                                    (float) pickedNode.getTransform().getTranslateY()
                                    + (float) pickedNode.getBounds().getHeight())
            });
            
            rightToLeft.setStrokeColor(KlighdConstants.RED);
            rightToLeft.setLineWidth(
                    (10f / 80f) * (float) pickedNode.getBounds().getHeight());
            rightToLeft.transformBy(pickedNode.getInverseTransform());
            
            warning.addChild(leftToRight);
            warning.addChild(rightToLeft);
        }
        
        // Highlight normal layers (if constant -100 is changed, change part above)
        if (0 < index && index < layerPos.size()){
        highlightedLayer.setPathToRectangle(
                choosenLayer, 
                -100, 
                layerPos.get(index) - choosenLayer, 
                (float) pickedNode.getParent().getParent().getFullBounds().getHeight() + 200f);
        highlightedLayer.setLineWidth(0);
        
        if (associatedNodeInNextLayer
                || outOfParentBounds) {
            highlightedLayer.setPaint(KlighdConstants.RED);
        } else {
            highlightedLayer.setPaint(KlighdConstants.GREY);
        }
        
        highlightedLayer.setPaintAlpha(KlighdConstants.ALPHA_QUARTER_OPAQUE);
        }
        
        // Highlighted area before first or last layer, 
        // first layer will shadered cause of special constraint
        if (posRelativeToParent.x < layerPos.get(0)
                || posRelativeToParent.x > layerPos.get(layerPos.size() - 1)) {
            
            index = posRelativeToParent.x < layerPos.get(0) ? 0 : layerPos.size() - 1;
            int rectWidth = posRelativeToParent.x < layerPos.get(0) ? 60 : 0;         
            
            highlightedLayer.setPathToRectangle(
                    layerPos.get(index) - rectWidth,
                    -100, 
                    60, 
                    (float) pickedNode.getParent().getParent().getFullBounds().getHeight() + 200f);
            highlightedLayer.setLineWidth(0);
            if (outOfParentBounds) {
                highlightedLayer.setPaint(KlighdConstants.RED);
            } else {
                highlightedLayer.setPaint(KlighdConstants.GREY);
            }
            highlightedLayer.setPaintAlpha(KlighdConstants.ALPHA_QUARTER_OPAQUE);

            // shading of the highlighted area before first layer
            float width = rectWidth;
            float height = (float) pickedNode.getParent().getParent().getFullBounds().getHeight() + 200f;
            
            float curWidth = 0;
            float curHeightLeft = height;
            float curHeightRight = height;

            KlighdPath shading;
            
            while (curWidth + 10 <= width) {
                curWidth += 10;
                curHeightLeft += 10;
                shading = new KlighdPath();
                shading.setPathToPolyline(new Point2D[]{
                        new Point2D.Float(curWidth - width, height - height - 100),
                        new Point2D.Float(0 - width, curHeightLeft - height - 100)
                });

                shading.setStrokeColor(KlighdConstants.GREY);
                shading.setPaintAlpha(KlighdConstants.ALPHA_QUARTER_OPAQUE);
                transformByAllParents(pickedNode.getParent(), shading);
                layer.addChild(shading);
            }
            
            while (curHeightLeft + 10 <= 2 * height) {
                curHeightLeft += 10;
                curHeightRight += 10;
                shading = new KlighdPath();
                shading.setPathToPolyline(new Point2D[]{
                        new Point2D.Float(curWidth - width, curHeightRight - height - 100),
                        new Point2D.Float(0 - width, curHeightLeft - height - 100)
                });

                shading.setStrokeColor(KlighdConstants.GREY);
                shading.setPaintAlpha(KlighdConstants.ALPHA_QUARTER_OPAQUE);
                NodeUtil.localToParent(shading, clipNodeNode);
                transformByAllParents(pickedNode.getParent(), shading);
                layer.addChild(shading);
            }
            
            curWidth = curHeightLeft - 2 * height;
            
            while (curWidth + 10 <= width) {
                curWidth += 10;
                curHeightRight += 10;
                shading = new KlighdPath();
                shading.setPathToPolyline(new Point2D[]{
                        new Point2D.Float(curWidth - width, height - 100),
                        new Point2D.Float(width - width, curHeightRight - height - 100)
                }); 
                
                shading.setStrokeColor(KlighdConstants.GREY);
                shading.setPaintAlpha(KlighdConstants.ALPHA_QUARTER_OPAQUE);
                NodeUtil.localToParent(shading, clipNodeNode);
                transformByAllParents(pickedNode.getParent(), shading);
                layer.addChild(shading);
            }
        }
        
        transformByAllParents(pickedNode.getParent(), highlightedLayer);
        
        layer.addChild(highlightedLayer);

        // Transform the y-coordinate of the incoming edges to get their new position.
        for (final KlighdPath path : (List<KlighdPath>) pNodeIncomingEdges.getChildrenReference()) {
            final Point2D[] shapePoints = path.getShapePoints();
            deltaTransform.transform(shapePoints[1], shapePoints[1]);
            path.setPathToPolyline(shapePoints);   
        }

        // Transform the x-coordinate of the outgoing edges to get their new position.
        for (final KlighdPath path : (List<KlighdPath>) pNodeOutgoingEdges.getChildrenReference()) {
            final Point2D[] shapePoints = path.getShapePoints();
            deltaTransform.transform(shapePoints[0], shapePoints[0]);
            path.setPathToPolyline(shapePoints);
        }
        
        event.setHandled(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void endDrag(final PInputEvent event) {
        super.endDrag(event);
        
        // Set the center of the dragged node to the current mouse location
        float height = (float) pickedNode.getFullBounds().getHeight() * 0.5f;
        float width = (float) pickedNode.getFullBounds().getWidth() * 0.5f;

        KVector posRelativeToParent = KimlUtil.toRelative(
                new KVector(event.getPosition().getX() - width, event.getPosition().getY() - height),
                knode.getParent());

        // Remove the PNode representing the new KNodes position from camera. 
        event.getTopCamera().removePropertyChangeListener(
                PCamera.PROPERTY_VIEW_TRANSFORM, cameraViewListener);
        
        layer.removeFromParent();
        node.removeFromParent();
        pNodeIncomingEdges.removeFromParent();
        pNodeOutgoingEdges.removeFromParent();
        dragArea.removeFromParent();
        warning.removeFromParent();
        
        final KShapeLayout ksl = knode.getData(KShapeLayout.class);
        if (associatedNodeInNextLayer || outOfParentBounds) {
            ksl.setPos((float) pickedNode.getTransform().getTranslateX(), 
                    (float) pickedNode.getTransform().getTranslateY());
        } else {
            ksl.setPos((float) posRelativeToParent.x, 
                    (float) posRelativeToParent.y);
        }
        
        // trigger new layout only in bounds/valid layer
        if (!associatedNodeInNextLayer
                || !outOfParentBounds) {
            pViewer.getControl().getDisplay().asyncExec(new Runnable() {
                public void run() {
                        LightDiagramServices.layoutDiagram(pViewer.getViewContext(), config);
                }
            });
            knode.getData(RenderingContextData.class).setProperty(
                    KlighdInternalProperties.DRAGGED, true);
        }

        knode = null;
        node = null;
        pickedNode = null;
        clipNodeNode = null;
        event.setHandled(true);
    }
    
    /**
     * Extract all edges that are connected to the picked node,
     * so they could be represented while dragging.
     *
     * @param edges incoming or outgoing edges of the knode
     * @param rep the {@link PNode} to represent the edges
     */
    private void edgesToPNode(final List<KEdge> edges, final PNode rep) {
        Iterator<KEdge> it = edges.iterator();
        while (it.hasNext()) {
            final KEdge edge = it.next();

            // Because edges are maybe children of two nodes
            // they would appear twice, so we need to check
            // if the edges are children of the current dragged node.
            // If true, than continue the loop with the next edge.
            if (edge.getSource() == null
                    || edge.getSource().getParent() == knode
                    || edge.getTarget() == null
                    || edge.getTarget().getParent() == knode) {
                continue;
            }

            // Get the current representation of the edges to get the
            // start and end point
            final KEdgeNode e = (KEdgeNode) diagController.getRepresentation(edge);
            final KlighdPath path = new KlighdPath();

            // Create new polyline with the first and the last coordinate saved
            // in the bend points.
            path.setPathToPolyline(new Point2D[]{
                    (Point2D) e.getBendPoints()[0].clone(),
                    (Point2D) e.getBendPoints()[e.getBendPoints().length - 1].clone()});

            // Get all parents transforms, so edges are drawn at the right position.
            // Especially useful when they are related to more than one parent.
            transformByAllParents(e, path);

            path.setStrokeColor(KlighdConstants.BLACK);
            path.setStrokeAlpha(KlighdConstants.ALPHA_HALF_OPAQUE);
            rep.addChild(path);
        }
    }

    /**
     * Check if the underlying layer is valid to drop the node. This means there is no
     * other node, that´s directly connected to the dragged node in this layer.
     *
     * @param it
     *          iterator over all edges connected to the node
     * @param i
     *          calculated index of the current underlying layers
     *          x-position (end of the layer)
     * @param choosenLayer
     *          the x-coordinate where the underlying layer starts
     * @param inOrOut
     *          if true the incoming edges are checked, otherwise the outgoing ones
     * @return true if current position is invalid, else false.
     */
    private boolean isNodeNextLayer(final Iterator<KEdge> it,
            final int i, final float choosenLayer, final boolean inOrOut) {
        int index = i;
        while (it.hasNext()) {
            final KEdge edge = it.next();
            // Check the source and target node of the edge - if node is not existing, the
            // or the parent of the node is our dragged node, then continue with algorithm.
            // Needs to be checked, because in hierarchies are also nodes connected to the
            // dragged node, but this is not influencing the layer the node can be dropped.
            if ((inOrOut && (edge.getSource() == null || edge.getSource().getParent() == knode))
                 || (!inOrOut && (edge.getTarget() == null || edge.getTarget().getParent() == knode))) {
                continue;
            }


            KNode kn = inOrOut ? edge.getSource() : edge.getTarget();
            float incXPos = 0;
            if (kn != null) {
                KNodeNode knn = (KNodeNode) diagController.getRepresentation(kn);
                incXPos = (float) knn.getTransform().getTranslateX();
            }

            // Index correction for the last layer added.
            if (index == layerPos.size()) {
                index--;
            }

            // Check if the node of the current edge to check is in layer bounds.
            if (incXPos > choosenLayer
                    && incXPos < layerPos.get(index)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Get all parent transforms, so edges are drawn at the right position.
     * Especially useful when they are related to more than one parent.
     * 
     * @param nodeParent node to get parents from
     * @param path path to transform
     */
    private void transformByAllParents(final PNode nodeParent, final KlighdPath path) {
        // Get all parents transforms, so edges are drawn at the right position.
        // Especially useful when they are related to more than one parent.
        if (clipNodeNode instanceof KNodeTopNode) {
            PNode p = nodeParent;
            while (p.getParent() != null) {
                path.transformBy(p.getTransformReference(true));
                p = p.getParent();
            }
        } else {
            path.transformBy(NodeUtil.localToParent(pickedNode.getParent(), clipNodeNode.getParent()));
        }
    }
    
    /**
     * Set values of diagram to interactive mode.
     * 
     * @author uru
     * @author mkr
     *
     */
    private static class InteractiveLayoutConfig extends VolatileLayoutConfig {
        /**
         * 
         */
        public InteractiveLayoutConfig() {
           setValue(Properties.CYCLE_BREAKING, CycleBreakingStrategy.INTERACTIVE);
           setValue(Properties.NODE_LAYERING, LayeringStrategy.INTERACTIVE);
           setValue(Properties.CROSS_MIN, CrossingMinimizationStrategy.INTERACTIVE);
        }
    }

    private List<ILayoutConfig> config = ImmutableList.<ILayoutConfig>of(new InteractiveLayoutConfig());
}
