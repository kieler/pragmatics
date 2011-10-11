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
package de.cau.cs.kieler.klay.layered.intermediate;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.math.KVectorChain;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.graph.LInsets;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.p5edges.OrthogonalRoutingGenerator;
import de.cau.cs.kieler.klay.layered.properties.NodeType;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * TODO: Document.
 * 
 * <dl>
 *   <dt>Precondition:</dt><dd>A layered graph, with edge routing finished for edges not incident
 *     to external ports; long edge dummies are not yet joined.</dd>
 *   <dt>Postcondition:</dt><dd>All external port dummy nodes left map onto an actual external port;
 *     the coordinates of external port dummy nodes specify the coordinates of their respective
 *     external port; all external port dummy nodes have a size of (0, 0); edges connected to
 *     external ports have their bend points set.</dd>
 *   <dt>Slots:</dt><dd>After phase 5.</dd>
 *   <dt>Same-slot dependencies:</dt><dd>None.</dd>
 * </dl>
 * 
 * @see HierarchicalPortConstraintProcessor
 * @see HierarchicalPortDummySizeProcessor
 * @see HierarchicalPortPositionProcessor
 * @see OrthogonalRoutingGenerator
 * @author cds
 */
public class HierarchicalPortOrthogonalEdgeRouter extends AbstractAlgorithm implements ILayoutProcessor {
    
    /**
     * {@inheritDoc}
     */
    public void process(final LayeredGraph layeredGraph) {
        getMonitor().begin("Orthogonally routing hierarchical port edges", 1);
        
        /* Step 1
         * Restore any north / south port dummies removed by the HierarchicalPortConstraintProcessor
         * and connect them to the dummies created in their stead.
         */
        Set<LNode> northSouthDummies = restoreNorthSouthDummies(layeredGraph);
        
        /* Step 2
         * Calculate coordinates for the north / south port dummies. Coordinates for the
         * east / west port dummies have already been calculated prior to this processor's
         * execution. The coordinates are relative to the node's content area, just like
         * normal node coordinates. (the content area is the node size minues insets minus
         * border spacing minus offset)
         */
        setNorthSouthDummyCoordinates(layeredGraph, northSouthDummies);
        
        /* Step 3
         * Orthogonal edge routing.
         */
        routeEdges(layeredGraph, northSouthDummies);
        
        /* Step 4
         * Removal of the temporarily created north / south port dummies.
         */
        removeTemporaryNorthSouthDummies(layeredGraph);
        
        /* Step 5
         * Finally, the coordinates of east / west hierarchical port dummies have to be corrected
         * and set. The x coordinate must be set, and if north / south port routing resulted
         * in a change of offset or graph size, the y coordinates have to be adjusted if port
         * constraints are at FIXED_RATIO or FIXED_POS. The graph's width may also have to be
         * adjusted.
         */
        fixCoordinates(layeredGraph);
        
        getMonitor().done();
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////
    // Restoring North / South External Port Dummies
    
    /**
     * Iterates through all layers, restoring hierarchical port dummy nodes along the way. The
     * restored nodes are connected to the temporary dummy nodes created for them. The
     * restored nodes are added to the last layer. (which layer they are added to doesn't
     * make any difference)
     * 
     * @param layeredGraph the layered graph.
     * @return the list of restored external port dummies.
     */
    private Set<LNode> restoreNorthSouthDummies(final LayeredGraph layeredGraph) {
        Set<LNode> restoredDummies = new HashSet<LNode>();
        Layer lastLayer = null;
        
        // Iterate through all nodes, looking for hierarchical port dummies whose origin is
        // another hierarchical port dummy
        for (Layer layer : layeredGraph.getLayers()) {
            for (LNode node : layer.getNodes()) {
                if (node.getProperty(Properties.NODE_TYPE) != NodeType.EXTERNAL_PORT) {
                    // Not a hierarchical port dummy - we're not interested. Move along,
                    // please, there's nothing to see here.
                    continue;
                }
                
                if (node.getProperty(Properties.ORIGIN) instanceof LNode) {
                    LNode origin = (LNode) node.getProperty(Properties.ORIGIN);
                    
                    // The origin should be a hierarchical port dummy. Better check that
                    if (origin.getProperty(Properties.NODE_TYPE) != NodeType.EXTERNAL_PORT) {
                        continue;
                    }
                    
                    // Restore the origin and connect the node to it
                    restoreDummy(origin, restoredDummies);
                    connectNodeToDummy(node, origin);
                }
            }
            
            lastLayer = layer;
        }
        
        // Assign the restored dummies to the graph's last layer (this has nothing to do anymore
        // with where they'll be placed; we just need some layer to assign them to, and we simply
        // choose the last one)
        for (LNode dummy : restoredDummies) {
            dummy.setLayer(lastLayer);
        }
        
        return restoredDummies;
    }
    
    /**
     * Restores the given dummy, if it's not already restored. If the dummy has already been
     * restored, it will be contained in the given set. If not, it is restored by setting its
     * port's side and putting it in the set.
     * 
     * @param dummy the dummy node to restore.
     * @param restoredDummies set of dummy nodes already restored.
     */
    private void restoreDummy(final LNode dummy, final Set<LNode> restoredDummies) {
        if (restoredDummies.contains(dummy)) {
            return;
        } else {
            // Depending on the hierarchical port's side, we set the dummy's port's port side
            // to be able to route properly (northern dummies must have a southern port)
            PortSide portSide = dummy.getProperty(Properties.EXT_PORT_SIDE);
            LPort dummyPort = dummy.getPorts().get(0);
            
            if (portSide == PortSide.NORTH) {
                dummyPort.setSide(PortSide.SOUTH);
            } else if (portSide == PortSide.SOUTH) {
                dummyPort.setSide(PortSide.NORTH);
            }
            
            restoredDummies.add(dummy);
        }
    }
    
    /**
     * Adds a port to the given node and connects that to the given dummy node.
     * 
     * @param node the node to connect to the dummy.
     * @param dummy the external port dummy to connect the node to.
     */
    private void connectNodeToDummy(final LNode node, final LNode dummy) {
        // First, add a port to the node. The port side depends on the node's hierarchical port side
        LPort outPort = new LPort();
        outPort.setNode(node);
        
        PortSide extPortSide = node.getProperty(Properties.EXT_PORT_SIDE);
        outPort.setSide(extPortSide);
        
        // Find the dummy node's port
        LPort inPort = dummy.getPorts().get(0);
        
        // Connect the two nodes
        LEdge edge = new LEdge();
        edge.setSource(outPort);
        edge.setTarget(inPort);
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////
    // Setting North / South Hierarchical Port Dummy Coordinates
    
    /**
     * Set coordinates for northern and southern external port dummy nodes.
     * 
     * @param layeredGraph the layered graph.
     * @param northSouthDummies set of dummy nodes whose position to set.
     */
    private void setNorthSouthDummyCoordinates(final LayeredGraph layeredGraph,
            final Set<LNode> northSouthDummies) {
        
        PortConstraints constraints = layeredGraph.getProperty(LayoutOptions.PORT_CONSTRAINTS);
        KVector graphSize = layeredGraph.getSize();
        LInsets.Double graphInsets = layeredGraph.getInsets();
        float borderSpacing = layeredGraph.getProperty(Properties.BORDER_SPACING);
        double graphWidth = graphSize.x + graphInsets.left + graphInsets.right + 2 * borderSpacing;
        double northY = 0 - graphInsets.top - borderSpacing - layeredGraph.getOffset().y;
        double southY = graphSize.y + graphInsets.top + graphInsets.bottom + 2 * borderSpacing
                + layeredGraph.getOffset().y;
        
        // Lists of northern and southern external port dummies
        List<LNode> northernDummies = new LinkedList<LNode>();
        List<LNode> southernDummies = new LinkedList<LNode>();
        
        for (LNode dummy : northSouthDummies) {
            // Set x coordinate
            switch (constraints) {
            case FREE:
            case FIXED_SIDE:
                calculateNorthSouthDummyPositions(dummy);
                break;
                
            case FIXED_ORDER:
                calculateNorthSouthDummyPositions(dummy);
                break;
                
            case FIXED_RATIO:
                applyNorthSouthDummyRatio(dummy, graphWidth);
                dummy.borderToContentAreaCoordinates(true, false);
                break;
            
            case FIXED_POS:
                applyNorthSouthDummyPosition(dummy);
                dummy.borderToContentAreaCoordinates(true, false);
                break;
            }
            
            // Set y coordinats and add the dummy to its respective list
            switch (dummy.getProperty(Properties.EXT_PORT_SIDE)) {
            case NORTH:
                dummy.getPosition().y = northY;
                northernDummies.add((dummy));
                break;
            
            case SOUTH:
                dummy.getPosition().y = southY;
                southernDummies.add(dummy);
                break;
            }
        }
        
        // Check for correct ordering of nodes in the FIXED_ORDER case and for dummy nodes that
        // have been put on top of one another
        switch (constraints) {
        case FREE:
        case FIXED_SIDE:
            ensureUniquePositions(northernDummies, layeredGraph);
            ensureUniquePositions(southernDummies, layeredGraph);
            break;
            
        case FIXED_ORDER:
            restoreProperOrder(northernDummies, layeredGraph);
            restoreProperOrder(southernDummies, layeredGraph);
            break;
        }
    }

    /**
     * Calculates the positions of northern and southern dummy nodes. The position is based
     * on the nodes the dummy nodes are connected to.
     * 
     * @param dummy the northern or southern external port dummy node to calculate the position for. 
     */
    private void calculateNorthSouthDummyPositions(final LNode dummy) {
        // We use a simple algorithm that simply adds the horizontal positions of all
        // connected ports and divides the sum by the number of connected ports
        
        // First, get the dummy's port (it has only one)
        LPort dummyInPort = dummy.getPorts().get(0);
        
        // Now, iterate over all connected ports, adding their horizontal position
        double posSum = 0.0;
        
        for (LPort connectedPort : dummyInPort.getConnectedPorts()) {
            posSum += connectedPort.getPosition().x + connectedPort.getNode().getPosition().x;
        }
        
        // Assign the dummy's x coordinate
        dummy.getPosition().x = posSum / dummyInPort.getDegree();
    }
    
    /**
     * Sets the dummy's x coordinate to respect the ratio defined for its original port.
     * 
     * @param dummy the dummy.
     * @param width the graph width.
     */
    private void applyNorthSouthDummyRatio(final LNode dummy, final double width) {
        dummy.getPosition().x = width * dummy.getProperty(Properties.EXT_PORT_RATIO_OR_POSITION);
        
    }
    
    /**
     * Sets the dummy's x coordinate to its original port's x coordinate.
     * 
     * @param dummy the dummy.
     */
    private void applyNorthSouthDummyPosition(final LNode dummy) {
        dummy.getPosition().x = dummy.getProperty(Properties.EXT_PORT_RATIO_OR_POSITION);
    }
    
    /**
     * Ensures that no two dummy nodes in the given list are assigned the same x coordinate. This
     * method must only be called if port constraints are set to {@code FREE} or {@code FIXED_SIDE}
     * as it may not preserve the original order of dummy nodes.
     * 
     * @param dummies list of dummy nodes.
     * @param graph the layered graph.
     */
    private void ensureUniquePositions(final List<LNode> dummies, final LayeredGraph graph) {
        if (dummies.isEmpty()) {
            return;
        }
        
        // Turn the list into an array of dummy nodes and sort that by their x coordinate
        LNode[] dummyArray = dummies.toArray(new LNode[dummies.size()]);
        
        Arrays.sort(dummyArray, new Comparator<LNode>() {
            public int compare(final LNode a, final LNode b) {
                double diff = a.getPosition().x - b.getPosition().x;
                
                // We cannot simply return diff cast to an int: if diff == 0.4, the returned value
                // would be 0, which is wrong
                if (diff < 0.0) {
                    return -1;
                } else if (diff > 0.0) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        
        assignAscendingCoordinates(dummyArray, graph);
    }

    /**
     * Checks if the automatically calculated node coordinates violate their fixed order and fixes
     * the coordinates. Calling this method only makes sense if port constraints are set to
     * {@code FIXED_ORDER}.
     * 
     * @param dummies list of dummy nodes.
     * @param graph the layered graph.
     */
    private void restoreProperOrder(final List<LNode> dummies, final LayeredGraph graph) {
        if (dummies.isEmpty()) {
            return;
        }
        
        // Turn the list into an array of dummy nodes and sort that by their original x coordinate
        LNode[] dummyArray = dummies.toArray(new LNode[dummies.size()]);
        
        Arrays.sort(dummyArray, new Comparator<LNode>() {
            public int compare(final LNode a, final LNode b) {
                double diff = a.getProperty(Properties.EXT_PORT_RATIO_OR_POSITION)
                        - b.getProperty(Properties.EXT_PORT_RATIO_OR_POSITION);
                
                // We cannot simply return diff cast to an int: if diff == 0.4, the returned value
                // would be 0, which is wrong
                if (diff < 0.0) {
                    return -1;
                } else if (diff > 0.0) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        
        assignAscendingCoordinates(dummyArray, graph);
    }
    
    /**
     * Iterates over the given array of dummy nodes, making sure that their x coordinates
     * are strictly ascending. Dummy nodes whose coordinates are in violation of this rule
     * are moved to the right. Once this method is finished, the coordinates of the dummy
     * nodes reflect their order in the array.
     * 
     * @param dummies array of dummy nodes.
     * @param graph the layered graph.
     */
    private void assignAscendingCoordinates(final LNode[] dummies, final LayeredGraph graph) {
        // Find the edge distance
        float edgeSpacing = graph.getProperty(Properties.OBJ_SPACING)
                * graph.getProperty(Properties.EDGE_SPACING_FACTOR);
        
        // Now, iterate over the array, remembering the last assigned position. If we find a
        // position that is less than or equal to the last position, assign a new position of
        // "lastPosition + edgeSpacing"
        double lastCoordinate = dummies[0].getPosition().x;
        for (int index = 1; index < dummies.length; index++) {
            KVector currentPosition = dummies[index].getPosition();
            
            if (currentPosition.x <= lastCoordinate) {
                currentPosition.x = lastCoordinate + edgeSpacing;
            }
            
            lastCoordinate = currentPosition.x;
        }
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////
    // Edge Routing
    
    /**
     * Routes nothern and southern hierarchical port edges and ajusts the graph's height and
     * offsets accordingly.
     * 
     * @param layeredGraph the layered graph.
     * @param northSouthDummies the collection of restored northern and southern port dummies.
     */
    private void routeEdges(final LayeredGraph layeredGraph, final Iterable<LNode> northSouthDummies) {
        // Prepare south and target layers for northern and southern routing
        Set<LNode> northernSourceLayer = new HashSet<LNode>();
        Set<LNode> northernTargetLayer = new HashSet<LNode>();
        Set<LNode> southernSourceLayer = new HashSet<LNode>();
        Set<LNode> southernTargetLayer = new HashSet<LNode>();
        
        // Find some routing parameters
        double nodeSpacing = layeredGraph.getProperty(Properties.OBJ_SPACING);
        double edgeSpacing = nodeSpacing * layeredGraph.getProperty(Properties.EDGE_SPACING_FACTOR);
        boolean debug = layeredGraph.getProperty(LayoutOptions.DEBUG_MODE);
        
        // Assemble the northern and southern hierarchical port dummies and the nodes they are
        // connected to
        for (LNode hierarchicalPortDummy : northSouthDummies) {
            PortSide portSide = hierarchicalPortDummy.getProperty(Properties.EXT_PORT_SIDE);
            
            if (portSide == PortSide.NORTH) {
                northernTargetLayer.add(hierarchicalPortDummy);
                
                for (LEdge edge : hierarchicalPortDummy.getIncomingEdges()) {
                    northernSourceLayer.add(edge.getSource().getNode());
                }
            } else if (portSide == PortSide.SOUTH) {
                southernTargetLayer.add(hierarchicalPortDummy);
                
                for (LEdge edge : hierarchicalPortDummy.getIncomingEdges()) {
                    southernSourceLayer.add(edge.getSource().getNode());
                }
            }
        }
        
        // Northern routing
        if (!northernSourceLayer.isEmpty()) {
            // Route the edges using a south-to-north orthogonal edge router
            OrthogonalRoutingGenerator routingGenerator = new OrthogonalRoutingGenerator(
                    new OrthogonalRoutingGenerator.SouthToNorthRoutingStrategy(),
                    edgeSpacing,
                    debug ? "extnorth" : null);
            
            int slots = routingGenerator.routeEdges(
                    layeredGraph,
                    northernSourceLayer,
                    0,
                    northernTargetLayer,
                    -nodeSpacing);
            
            // If anything was routed, adjust the graph's offset and height
            if (slots > 0) {
                layeredGraph.getOffset().y = nodeSpacing + (slots - 1) * edgeSpacing;
                layeredGraph.getSize().y += layeredGraph.getOffset().y;
            }
        }
        
        // Southern routing
        if (!southernSourceLayer.isEmpty()) {
            // Route the edges using a north-to-south orthogonal edge router
            OrthogonalRoutingGenerator routingGenerator = new OrthogonalRoutingGenerator(
                    new OrthogonalRoutingGenerator.NorthToSouthRoutingStrategy(),
                    edgeSpacing,
                    debug ? "extsouth" : null);
            
            int slots = routingGenerator.routeEdges(
                    layeredGraph,
                    southernSourceLayer,
                    0,
                    southernTargetLayer,
                    layeredGraph.getSize().y + nodeSpacing);
            
            // Adjust graph height.
            if (slots > 0) {
                layeredGraph.getSize().y += nodeSpacing + (slots - 1) * edgeSpacing;
            }
        }
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////
    // Temporary North / South Hierarchical Port Dummy Removal
    
    /**
     * Removes the temporary hierarchical port dummies, reconnecting their incoming
     * edges to the original dummies and setting the appropriate bend points.
     * 
     * @param layeredGraph the layered graph.
     */
    private void removeTemporaryNorthSouthDummies(final LayeredGraph layeredGraph) {
        List<LNode> nodesToRemove = new LinkedList<LNode>();
        
        // Iterate through all layers
        for (Layer layer : layeredGraph.getLayers()) {
            for (LNode node : layer.getNodes()) {
                if (node.getProperty(Properties.NODE_TYPE) != NodeType.EXTERNAL_PORT) {
                    // We're only looking for hierarchical port dummies
                    continue;
                }
                
                if (!(node.getProperty(Properties.ORIGIN) instanceof LNode)) {
                    // We're only looking for temporary north / south dummies
                    continue;
                }
                
                // There must be a port where all edges come in, and a port with an edge connecting
                // node and origin
                LPort nodeInPort = node.getPorts().get(0);
                LPort nodeOutPort = node.getPorts().get(1);
                
                LEdge nodeToOriginEdge = nodeOutPort.getOutgoingEdges().get(0);
                
                // Retrieve the edge connecting node and origin, and get its list of bend points
                KVectorChain bendPoints = new KVectorChain();
                
                KVector firstBendPoint = new KVector(nodeInPort.getPosition());
                firstBendPoint.add(node.getPosition());
                bendPoints.add(firstBendPoint);
                
                bendPoints.addAll(nodeToOriginEdge.getBendPoints());
                
                // Retrieve the original hierarchical port dummy
                LNode origin = (LNode) node.getProperty(Properties.ORIGIN);
                LPort originPort = origin.getPorts().get(0);
                
                // Reroute all the input port's edges
                LEdge[] edges = nodeInPort.getIncomingEdges().toArray(new LEdge[0]);
                
                for (LEdge edge : edges) {
                    edge.setTarget(originPort);
                    edge.getBendPoints().addAll(bendPoints);
                }
                
                // Remove connection between node and original hierarchical port dummy
                nodeToOriginEdge.setSource(null);
                nodeToOriginEdge.setTarget(null);
                
                // Remember the temporary node for removal
                nodesToRemove.add(node);
            }
        }
        
        // Remove nodes
        for (LNode node : nodesToRemove) {
            node.setLayer(null);
        }
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////
    // Setting East / West Hierarchical Port Dummy Coordinates
    
    /**
     * Fixes all hierarchical port dummy coordinates. For east / west external port dummies, this
     * means setting the x coordinate appropriately, and, in case of {@code FIXED_RATIO},
     * checking that the ratio is respected. For north / south hierarchical port dummies, this
     * means setting the y coordinate appropriately.
     * 
     * @param layeredGraph the layered graph.
     */
    private void fixCoordinates(final LayeredGraph layeredGraph) {
        PortConstraints constraints = layeredGraph.getProperty(LayoutOptions.PORT_CONSTRAINTS);
        
        // East port dummies are in the first layer; all other dummies are in the last layer
        List<Layer> layers = layeredGraph.getLayers();
        fixCoordinates(
                layers.get(0),
                constraints,
                layeredGraph);
        fixCoordinates(
                layers.get(layers.size() - 1),
                constraints,
                layeredGraph);
    }
    
    /**
     * Fixes the coordinates of the nodes in the given layer.
     * 
     * @param layer the layer.
     * @param constraints external port constraints.
     * @param graph the graph.
     */
    private void fixCoordinates(final Layer layer, final PortConstraints constraints,
            final LayeredGraph graph) {
        
        // Get some geometric values from the graph
        LInsets.Double insets = graph.getInsets();
        float borderSpacing = graph.getProperty(Properties.BORDER_SPACING);
        KVector offset = graph.getOffset();
        KVector graphActualSize = graph.getActualSize();
        
        for (LNode node : layer.getNodes()) {
            if (node.getProperty(Properties.NODE_TYPE) != NodeType.EXTERNAL_PORT) {
                // We're only looking for hierarchical port dummies
                continue;
            }
            
            PortSide extPortSide = node.getProperty(Properties.EXT_PORT_SIDE);
            KVector nodePosition = node.getPosition();
            
            // Set x coordinate
            switch (extPortSide) {
            case EAST:
                nodePosition.x = graph.getSize().x + borderSpacing + insets.right - offset.x;
                break;
            
            case WEST:
                nodePosition.x = -offset.x - borderSpacing - insets.left;
                break;
            }
            
            // Set y coordinate
            switch (extPortSide) {
            case EAST:
            case WEST:
                if (constraints.isRatioFixed()) {
                    double ratio = node.getProperty(Properties.EXT_PORT_RATIO_OR_POSITION);
                    nodePosition.y = graphActualSize.y * ratio;
                    node.borderToContentAreaCoordinates(false, true);
//                } else if (constraints.isPosFixed()) {
//                    nodePosition.y = node.getProperty(Properties.EXT_PORT_RATIO_OR_POSITION);
//                    node.borderToContentAreaCoordinates(false, true);
                }
                break;
            
            case NORTH:
                nodePosition.y = -offset.y - borderSpacing - insets.top;
                break;
            
            case SOUTH:
                nodePosition.y = graph.getSize().y + borderSpacing + insets.bottom - offset.y;
                break;
            }
        }
    }
    
}
