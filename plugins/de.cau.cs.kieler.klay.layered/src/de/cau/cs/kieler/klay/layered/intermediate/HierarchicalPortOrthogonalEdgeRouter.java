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
import de.cau.cs.kieler.klay.layered.Util;
import de.cau.cs.kieler.klay.layered.graph.Insets;
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
         * constraints are at FIXED_RATIO.
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
        
        // Assign the restored dummies to the graph's last layer
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
    
    private void setNorthSouthDummyCoordinates(final LayeredGraph layeredGraph,
            final Set<LNode> northSouthDummies) {
        
        PortConstraints constraints = layeredGraph.getProperty(LayoutOptions.PORT_CONSTRAINTS);
        KVector graphSize = layeredGraph.getSize();
        Insets.Double graphInsets = layeredGraph.getInsets();
        float borderSpacing = layeredGraph.getProperty(Properties.BORDER_SPACING);
        double nodeWidth = graphSize.x + graphInsets.left + graphInsets.right + 2 * borderSpacing;
        
        for (LNode dummy : northSouthDummies) {
            // Set x coordinate
            switch (constraints) {
            case FREE:
            case FIXED_SIDE:
            case FIXED_ORDER:
                calculateNorthSouthDummyPositions(dummy);
                break;
            
            case FIXED_RATIO:
                applyNorthSouthDummyRatio(dummy, nodeWidth);
                Util.borderToContentAreaCoordinates(dummy, layeredGraph, true, false);
                break;
            
            case FIXED_POS:
                applyNorthSouthDummyPosition(dummy);
                Util.borderToContentAreaCoordinates(dummy, layeredGraph, true, false);
                break;
            }
        }
        
        // If we have a fixed order, we calculated the position using a heuristic. This
        // may have broken the ordering, so we need to check that
        // TODO: Implement.
    }
    
    private void calculateNorthSouthDummyPositions(final LNode dummy) {
        
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
        Insets.Double insets = graph.getInsets();
        float borderSpacing = graph.getProperty(Properties.BORDER_SPACING);
        KVector offset = graph.getOffset();
        double graphHeight = graph.getSize().y + insets.top + insets.bottom + (2 * borderSpacing);
        
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
                if (constraints == PortConstraints.FIXED_RATIO) {
                    double ratio = node.getProperty(Properties.EXT_PORT_RATIO_OR_POSITION);
                    nodePosition.y = graphHeight * ratio;
                    Util.borderToContentAreaCoordinates(node, graph, false, true);
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
