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
import org.eclipse.swt.graphics.RGB;

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
import de.cau.cs.kieler.klay.layered.properties.InternalProperties;
import de.cau.cs.kieler.klay.layered.properties.Properties;
import de.cau.cs.kieler.klighd.KlighdConstants;
import de.cau.cs.kieler.klighd.KlighdPreferences;
import de.cau.cs.kieler.klighd.LightDiagramServices;
import de.cau.cs.kieler.klighd.piccolo.KlighdSWTGraphics;
import de.cau.cs.kieler.klighd.piccolo.internal.controller.DiagramController;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KEdgeNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KLabelNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KNodeNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdAlignmentNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdDisposingLayer;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdPath;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdStyledText;
import de.cau.cs.kieler.klighd.piccolo.internal.util.KlighdPaintContext;
import de.cau.cs.kieler.klighd.piccolo.internal.util.NodeUtil;
import de.cau.cs.kieler.klighd.piccolo.viewer.PiccoloViewer;
import edu.umd.cs.piccolo.PCamera;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.event.PDragSequenceEventHandler;
import edu.umd.cs.piccolo.event.PInputEvent;
import edu.umd.cs.piccolo.util.PAffineTransform;
import edu.umd.cs.piccolo.util.PPaintContext;
import edu.umd.cs.piccolo.util.PPickPath;

/**
 *
 * @author chsch
 * @author mkr
 */
public class KlighdInteractiveDragEventHandler extends PDragSequenceEventHandler {
    
    // Constant for half opacity
    private static final int HALF_OPACITY = KlighdConstants.ALPHA_FULL_OPAQUE / 2;

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
    
    // Representing the incoming edges of the current dragged KNode
    private PNode pathNodeIncoming;

    // Representing the outgoing edges of the current dragged KNode
    private PNode pathNodeOutgoing;
    
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
        
        // Extracts the KNodeNode, if selected element is type of
        // KlighdStyledText.
        if (object instanceof KNodeNode) {
            pickedNode = object;
        } else if (object instanceof KlighdStyledText) {
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
    
    // concatenate all transforms to ensure right graphic position
    // of dragged node
    private final AffineTransform transform = new AffineTransform();

    /**
     * {@inheritDoc}
     */
    @Override
    protected void startDrag(final PInputEvent event) {
        
        super.startDrag(event);

        final KNodeNode nodeNode = (KNodeNode) pickedNode;
        knode = nodeNode.getViewModelElement();
        layerPos = knode.getParent().getData(KShapeLayout.class).getProperty(InternalProperties.LAYER_POSITION);
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

                // Push the transform reference of PNode to stack
                // to get the reference of the new node.
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
        
        // Creates new PNode containing several KlighdPath to show
        // the incoming and outgoing KEdges while dragging.
        pathNodeIncoming = new PNode();
        pathNodeOutgoing = new PNode();

        final List<KEdge> incoming = knode.getIncomingEdges();
        final Iterator<KEdge> incomingIt = incoming.iterator();
        while (incomingIt.hasNext()) {
            final KEdge edge = incomingIt.next();
            
            // Because edges are maybe children of two nodes
            // they would disappear twice, so we need to check
            // if the edges are children of the current dragged node.
            // If true, than continue the loop with the next edge.
            if (edge.getSource() == null || edge.getSource().getParent() == knode) {
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
            
            path.setStrokeColor(new RGB(255, 0, 0));
            path.setStrokeAlpha(HALF_OPACITY);
            pathNodeIncoming.addChild(path);
        }
        
        // Same algorithm as above, but used for outgoing edges
        final List<KEdge> outgoing = knode.getOutgoingEdges();
        final Iterator<KEdge> outgoingIt = outgoing.iterator();
        while (outgoingIt.hasNext()) {
            final KEdge edge = outgoingIt.next();
            if (edge.getTarget() == null || edge.getTarget().getParent() == knode) {
                continue;
            }

            final KEdgeNode e = (KEdgeNode) diagController.getRepresentation(edge);
            final KlighdPath path = new KlighdPath();
            
            path.setPathToPolyline(new Point2D[]{
                    (Point2D) e.getBendPoints()[0].clone(), 
                    (Point2D) e.getBendPoints()[e.getBendPoints().length - 1].clone()});
            
            transformByAllParents(e, path);
            
            path.setStrokeColor(new RGB(0, 255, 0));
            path.setStrokeAlpha(HALF_OPACITY);
            pathNodeOutgoing.addChild(path);
        }
        
//        final List<KEdge> outgoing = knode.getOutgoingEdges();
//        final Iterator<KEdge> outgoingIt = outgoing.iterator();
//        while (outgoingIt.hasNext()) {
//            final KEdge edge = outgoingIt.next();
//            if (edge.getTarget().getParent() == knode) {
//                continue;
//            }
//            final KEdgeLayout edgeLayout = (KEdgeLayout) edge.getData().get(0);
//            final KPoint source = edgeLayout.getSourcePoint();
//            final KPoint target = edgeLayout.getTargetPoint();
//            
//            final KlighdPath path = new KlighdPath();
//            path.setPathToPolyline(new Point2D[]{
//                    new Point2D.Float(source.getX(), source.getY()), 
//                    new Point2D.Float(target.getX(), target.getY())});
//            
//            path.setStrokeColor(new RGB(0, 255, 0));
//            path.setTransparency(HALF_OPACITY/100);
//            pathNodeOutgoing.addChild(path);
//        }

        // PNode containing the information to highlight selected area
        dragArea = new PNode();

        // PickedNode attributes to calculate position of blue box (highlighting selected)
        float xCoordinate = (float) pickedNode.getTransform().getTranslateX();
        float yCoordinate = (float) pickedNode.getTransform().getTranslateY();
        final float fullWidth = (float) pickedNode.getFullBounds().getWidth();
        final float fullHeight = (float) pickedNode.getFullBounds().getHeight();
        final float width = (float) pickedNode.getBounds().getWidth();
        final float height = (float) pickedNode.getBounds().getHeight();
        
        xCoordinate = xCoordinate - (fullWidth / 2) + (width / 2);
        yCoordinate = yCoordinate - (fullHeight / 2) + (height / 2);

        
        final float stdDistance = 6f;
        final LineAttributes dottedLine = new LineAttributes(
                1, SWT.CAP_FLAT, SWT.JOIN_MITER, SWT.LINE_DASH, null, 0, 10);

//        
//        // LOWER BORDER //
//        final KlighdPath pathLowerBorder = new KlighdPath();
//        
//        pathLowerBorder.setPathToPolyline(new Point2D[]{
//                new Point2D.Float(xCoordinate - 1000, yCoordinate + fullHeight + stdDistance),
//                new Point2D.Float(xCoordinate + 1000, yCoordinate + fullHeight + stdDistance)
//        });
//        pathLowerBorder.setStrokeColor(new RGB(0, 0, 255));
//        pathLowerBorder.setLineAttributes(dottedLine);
//        
//        PNode pLower = pickedNode.getParent();
//        while (pLower.getParent() != null) {                
//            pathLowerBorder.transformBy(pLower.getTransformReference(true));
//            pLower = pLower.getParent();
//        } 
        
        // LAYER BORDERS!
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
            path.setStrokeColor(new RGB(0, 0, 255));
            
            transformByAllParents(pickedNode.getParent(), path);
            
            dragArea.addChild(path);
        }
        
        // RECTANGLE //
        final KlighdPath pathRectangle = new KlighdPath();
        
        pathRectangle.setPathToRectangle(
                xCoordinate - stdDistance, yCoordinate - stdDistance, 
                fullWidth + (2*stdDistance), fullHeight + (2*stdDistance));
        pathRectangle.setTransparency(0.5f);
        pathRectangle.setLineAttributes(dottedLine);
        pathRectangle.setPaint(new RGB(0, 0, 255));
        
        transformByAllParents(pickedNode.getParent(), pathRectangle);
                
        dragArea.addChild(pathRectangle);
        
        // Initialize transform with identity
        transform.setToIdentity();
        final PPickPath pickpath = event.getPath();
        
        //Set the shown PNodes bounds at new position.
        //Set the transform to the ones of the KNodeNode. 
        node.setBounds(bounds);

        layer = new PNode();
        warning = new PNode();      
        
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
                pathNodeOutgoing.setTransform(parentViewGlobalTransform);
                pathNodeIncoming.setTransform(parentViewGlobalTransform);
                
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
        topCamera.addChild(node);
        topCamera.addChild(pathNodeIncoming);
        topCamera.addChild(pathNodeOutgoing);
        topCamera.addChild(dragArea);
        topCamera.addChild(warning);
        topCamera.addChild(layer);
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
        
        KlighdPath upperLeftToLowerRightKlighdPath = new KlighdPath();
        upperLeftToLowerRightKlighdPath.removeFromParent();
        upperLeftToLowerRightKlighdPath.setStrokeColor(new RGB(255, 0, 0));

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
        
        // Translate coordinates relative to the parent of the picked node
     
        KVector test = KimlUtil.toRelative(
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
        
        // Check position of the current node if parent exists.
        if(parent != null) {
        
        double parentX = parent.getTransform().getTranslateX();
        double parentY = parent.getTransform().getTranslateY();
        double parentWidth = parent.getBounds().getWidth();
        double parentHeight = parent.getBounds().getHeight();

            if (event.getPosition().getX() < parentX 
                    || event.getPosition().getY() < parentY
                    || parentX + parentWidth < event.getPosition().getX()
                    || parentY + parentHeight < event.getPosition().getY()) {
                System.out.println("Out of Bounds!! ----------- " + event.getPosition()+ " ------ rel: " + test);
                System.out.println(knode.getData().toArray().toString());
                System.out.println(parentHeight + "    " + parentWidth);
                
                upperLeftToLowerRightKlighdPath.setPathToPolyline(new Point2D[]{
                        new Point2D.Float(
                                (float) pickedNode.getTransform().getTranslateX(), 
                                (float) pickedNode.getTransform().getTranslateY()),
                        new Point2D.Float(
                                (float) pickedNode.getTransform().getTranslateX()
                                + (float) pickedNode.getFullBounds().getWidth(),
                                (float) pickedNode.getFullBounds().getWidth()
                                + (float) pickedNode.getFullBounds().getHeight()
                                + (float) pickedNode.getTransform().getTranslateX())        
                });
                
                node.addChild(upperLeftToLowerRightKlighdPath);
            }
        }
        
        // Highlighting Layer
        KlighdPath highlightedLayer = new KlighdPath();
        layer.removeAllChildren();
        
        float choosenLayer = 0;
        int index = 0;
        for (float pos : layerPos) {
            if (test.x < pos) {
                break;
            }
            
            choosenLayer = pos;
            index++;
        }
        
        if (index < layerPos.size()){
        highlightedLayer.setPathToRectangle(
                choosenLayer, -100, 
                layerPos.get(index) - choosenLayer, (float) pickedNode.getParent().getParent().getFullBounds().getHeight() + 200f);
        highlightedLayer.setLineWidth(0);
        highlightedLayer.setPaint(new RGB(128, 128, 128));
        highlightedLayer.setTransparency(0.25f);
        }
        
        transformByAllParents(pickedNode.getParent(), highlightedLayer);
        
        layer.addChild(highlightedLayer);

        // Transform the y-coordinate of the incoming edges to get their new position.
        for (final KlighdPath path : (List<KlighdPath>) pathNodeIncoming.getChildrenReference()) {
            final Point2D[] shapePoints = path.getShapePoints();
            deltaTransform.transform(shapePoints[1], shapePoints[1]);
            path.setPathToPolyline(shapePoints);
           
        }

        // Transform the x-coordinate of the outgoing edges to get their new position.
        for (final KlighdPath path : (List<KlighdPath>) pathNodeOutgoing.getChildrenReference()) {
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

        KVector posRelativeToParent = KimlUtil.toRelative(
                new KVector(event.getPosition().getX(), event.getPosition().getY()), knode.getParent());

        // Remove the PNode representing the new KNodes position from camera. 
        event.getTopCamera().removePropertyChangeListener(
                PCamera.PROPERTY_VIEW_TRANSFORM, cameraViewListener);
        layer.removeFromParent();
        node.removeFromParent();
        pathNodeIncoming.removeFromParent();
        pathNodeOutgoing.removeFromParent();
        dragArea.removeFromParent();
        warning.removeFromParent();
        
        final KShapeLayout ksl = knode.getData(KShapeLayout.class);
//        ksl.setPos((float) node.getTransform().getTranslateX(), 
//                (float) node.getTransform().getTranslateY());
        
        ksl.setPos((float) posRelativeToParent.x, 
                (float) posRelativeToParent.y);
        
        // trigger new layout only in bounds
//        boolean inBounds = true;
//
//        if (parent != null) {
//
//            double parentX = parent.getTransform().getTranslateX();
//            double parentY = parent.getTransform().getTranslateY();
//            double parentWidth = parent.getBounds().getWidth();
//            double parentHeight = parent.getBounds().getHeight();
//
//            if (event.getPosition().getX() < parentX || event.getPosition().getY() < parentY
//                    || parentX + parentWidth < event.getPosition().getX()
//                    || parentY + parentHeight < event.getPosition().getY()) {
//                inBounds = false;
//            }
//        }
//
//        if (inBounds) {
            pViewer.getControl().getDisplay().asyncExec(new Runnable() {
                public void run() {
                        LightDiagramServices.layoutDiagram(pViewer.getViewContext(), config);
                }
            });
       

        knode = null;
        node = null;
        pickedNode = null;
        event.setHandled(true);
    }
    
    /**
     * Get all parent transforms, so edges are drawn at the right position.
     * Especially useful when they are related to more than one parent.
     * 
     * @param node node to get parents from
     * @param path path to transform
     */
    private void transformByAllParents(final PNode node, final KlighdPath path) {
        // Get all parents transforms, so edges are drawn at the right position.
        // Especially useful when they are related to more than one parent.
        PNode p = node;
        while (p.getParent() != null) {                
            path.transformBy(p.getTransformReference(true));
            p = p.getParent();
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
        }
    }

    private List<ILayoutConfig> config = ImmutableList.<ILayoutConfig>of(new InteractiveLayoutConfig());
}
