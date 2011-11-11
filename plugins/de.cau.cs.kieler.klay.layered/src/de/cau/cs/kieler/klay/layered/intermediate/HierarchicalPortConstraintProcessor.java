/*
looks  * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.kiml.options.Alignment;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.properties.NodeType;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * Processes constraints imposed on hierarchical node dummies.
 * 
 * <p>Eastern and western ports cannot be ordered arbitrarily by the crossing minimizer if
 * the port order is fixed. Thus, this processor inserts appropriate in-layer successor
 * constraints to restrict the node ordering.</p>
 * 
 * <p>Northern and southern external ports can sadly not be processed in the usual way with
 * port constraints at least at {@code FIXED_ORDER}. Instead, we need to replace them by new
 * external port dummies. For each node connected to a northern or southern hierarchical port
 * dummy, we need to place a new dummy in the next layer, rerouting the edges appropriately.
 * The original dummies are removed, to be reinserted later by
 * {@link HierarchicalPortOrthogonalEdgeRouter}. For simplification, this is also done in all
 * other port constraint cases. This saves us the trouble of having to differentiate between
 * the different port constraints later on.</p>
 * 
 * <dl>
 *   <dt>Precondition:</dt><dd>A layered graph; long edge dummies have not yet been inserted;
 *     layer constraints have not yet been applied.</dd>
 *   <dt>Postcondition:</dt><dd>Hierarchical port dummies for northern and southern ports are
 *     replaced by multiple dummies if the port constraints are at least {@code FIXED_ORDER}.</dd>
 *   <dt>Slots:</dt><dd>Before phase 3.</dd>
 *   <dt>Same-slot dependencies:</dt><dd>{@link LayerConstraintProcessor}</dd>
 * </dl>
 * 
 * @see HierarchicalPortDummySizeProcessor
 * @see HierarchicalPortOrthogonalEdgeRouter
 * @see HierarchicalPortPositionProcessor
 * @author cds
 */
public class HierarchicalPortConstraintProcessor extends AbstractAlgorithm implements ILayoutProcessor {
    
    /**
     * Comparator to compare nodes by their position values in ascending order. Nodes
     * that are not hierarchical port dummies are sorted to the bottom of a list.
     * 
     * @author cds
     */
    private static class NodeComparator implements Comparator<LNode> {
        /**
         * {@inheritDoc}
         */
        public int compare(final LNode node1, final LNode node2) {
            NodeType nodeType1 = node1.getProperty(Properties.NODE_TYPE);
            double nodePos1 = node1.getProperty(Properties.EXT_PORT_RATIO_OR_POSITION);
            NodeType nodeType2 = node2.getProperty(Properties.NODE_TYPE);
            double nodePos2 = node2.getProperty(Properties.EXT_PORT_RATIO_OR_POSITION);
            
            if (nodeType2 != NodeType.EXTERNAL_PORT) {
                return -1;
            } else if (nodeType1 != NodeType.EXTERNAL_PORT) {
                return 1;
            } else {
                if (nodePos1 == nodePos2) {
                    return 0;
                } else {
                    return nodePos1 < nodePos2 ? -1 : 1;
                }
            }
        }
    }
    

    /**
     * {@inheritDoc}
     */
    public void process(final LayeredGraph layeredGraph) {
        getMonitor().begin("Hierarchical port constraint processing", 1);
        
        processEasternAndWesternPortDummies(layeredGraph);
        processNorthernAndSouthernPortDummies(layeredGraph);
        
        getMonitor().done();
    }


    ///////////////////////////////////////////////////////////////////////////////
    // East / West Hierarchical Port Dummies
    
    /**
     * Process eastern and western hierarchical port dummies.
     * 
     * @param layeredGraph the layered graph
     */
    private void processEasternAndWesternPortDummies(final LayeredGraph layeredGraph) {
        // If the port constraints are not at least FIXED_ORDER, there's nothing to be done here
        if (!layeredGraph.getProperty(LayoutOptions.PORT_CONSTRAINTS).isOrderFixed()) {
            return;
        }
        
        List<Layer> layers = layeredGraph.getLayers();
        
        // This affects the first and last layer
        processEasternAndWesternPortDummies(layers.get(0));
        processEasternAndWesternPortDummies(layers.get(layers.size() - 1));
    }
    
    /**
     * Process the eastern and western hierarchical port dummies present in the given layer.
     * 
     * @param layer the layer.
     */
    private void processEasternAndWesternPortDummies(final Layer layer) {
        // Put the nodes into an array
        LNode[] nodes = layer.getNodes().toArray(new LNode[0]);
        
        // Sort the array; hierarchical port dummies are at the top, sorted by
        // position or ratio in ascending order
        Arrays.sort(nodes, new NodeComparator());
        
        // Insert in-layer successor constraints where appropriate
        LNode lastHierarchicalDummy = null;
        
        for (LNode node : nodes) {
            if (node.getProperty(Properties.NODE_TYPE) != NodeType.EXTERNAL_PORT) {
                // No hierarchical port dummy nodes any more
                break;
            }
            
            // Only process dummies created for eastern or western external ports
            PortSide externalPortSide = node.getProperty(Properties.EXT_PORT_SIDE);
            if (externalPortSide != PortSide.WEST && externalPortSide != PortSide.EAST) {
                continue;
            }
            
            if (lastHierarchicalDummy != null) {
                lastHierarchicalDummy.setProperty(Properties.IN_LAYER_SUCCESSOR_CONSTRAINT, node);
            }
            
            lastHierarchicalDummy = node;
        }
    }


    ///////////////////////////////////////////////////////////////////////////////
    // North / South Hierarchical Port Dummies
    
    /**
     * Process northern and southern hierarchical port dummies.
     * 
     * @param layeredGraph the layered graph.
     */
    private void processNorthernAndSouthernPortDummies(final LayeredGraph layeredGraph) {
        // If the port constraints are not at least FIXED_SIDE, there's nothing to do here
        PortConstraints portConstraints = layeredGraph.getProperty(LayoutOptions.PORT_CONSTRAINTS);
        if (!portConstraints.isSideFixed()) {
            return;
        }
        
        // If the port order is not fixed, we don't need to insert a hierarchical port dummy
        // after every node that's connected to a hierarchical port. Instead, we just replace
        // the original dummy
        boolean onlyReplaceOriginalDummy = !portConstraints.isOrderFixed();
        
        Map<Object, LNode> nextLayerNodesToAdd = new HashMap<Object, LNode>();
        Set<LNode> externalNodeDummies = new HashSet<LNode>();
        
        // Iterate through each layer
        ListIterator<Layer> layerIterator = layeredGraph.getLayers().listIterator();
        
        while (layerIterator.hasNext()) {
            Layer currentLayer = layerIterator.next();
            
            // See if any nodes must be added to this layer
            if (!nextLayerNodesToAdd.isEmpty()) {
                for (LNode dummy : nextLayerNodesToAdd.values()) {
                    dummy.setLayer(currentLayer);
                }
                
                nextLayerNodesToAdd.clear();
            }
            
            // Iterate through the layer's nodes, looking for normal nodes connected to
            // northern / southern hierarchical port dummies
            for (LNode currentNode : currentLayer) {
                // Iterate through the node's outgoing edges
                for (LEdge edge : currentNode.getOutgoingEdges()) {
                    LNode targetNode = edge.getTarget().getNode();
                    
                    // Check if it's a northern / southern dummy node
                    if (!isNorthernSouthernDummy(targetNode)) {
                        continue;
                    }
                    externalNodeDummies.add(targetNode);
                    
                    // If only the original dummy should be replaced by a new one, we'll
                    // do that below, after the for loop
                    if (!onlyReplaceOriginalDummy) {
                        // See if a dummy has already been created for the next layer
                        LNode nextLayerDummy = nextLayerNodesToAdd.get(
                                targetNode.getProperty(Properties.ORIGIN));
                        
                        if (nextLayerDummy == null) {
                            // No. Create one.
                            nextLayerDummy = createDummy(targetNode);
                            nextLayerNodesToAdd.put(
                                    targetNode.getProperty(Properties.ORIGIN), nextLayerDummy);
                        }
                        
                        // Reroute the edge
                        edge.setTarget(nextLayerDummy.getPorts().get(0));
                    }
                }
            }
        }
        
        // See if there are nodes that have to be added to a new last layer (this actually shouldn't
        // happen, but as the old saying I just invented goes: when in doubt, opt for the safe side!)
        if (!nextLayerNodesToAdd.isEmpty()) {
            Layer newLastLayer = new Layer(layeredGraph);
            layeredGraph.getLayers().add(newLastLayer);

            for (LNode dummy : nextLayerNodesToAdd.values()) {
                dummy.setLayer(newLastLayer);
            }
        }
        
        // Iterate through the hierarchical port dummies
        for (LNode originalDummy : externalNodeDummies) {
            if (onlyReplaceOriginalDummy) {
                // Create a new dummy node and replace the original dummy with it. This simplifies
                // handling them when routing hierarchical port edges.
                LNode newDummy = createDummy(originalDummy);
                newDummy.setLayer(originalDummy.getLayer());
                
                // Reroute all incident edges; this assumes that the original dummy only has
                // one port, with only incoming edges
                LPort originalPort = originalDummy.getPorts().get(0);
                LPort newPort = newDummy.getPorts().get(0);
                
                LEdge[] edges = originalPort.getIncomingEdges().toArray(new LEdge[0]);
                for (LEdge edge : edges) {
                    edge.setTarget(newPort);
                }
            }
            
            // Remove the original dummy; new dummy nodes have already been created for it
            originalDummy.setLayer(null);
        }
    }
    
    /**
     * Checks if the node represents a northern or southern external port.
     * 
     * @param node the node to check.
     * @return {@code true} if the node represents a northern or southern external port,
     *         {@code false} otherwise.
     */
    private boolean isNorthernSouthernDummy(final LNode node) {
        NodeType nodeType = node.getProperty(Properties.NODE_TYPE);
        
        if (nodeType == NodeType.EXTERNAL_PORT) {
            PortSide portSide = node.getProperty(Properties.EXT_PORT_SIDE);
            
            return portSide == PortSide.NORTH || portSide == PortSide.SOUTH;
        }
        
        return false;
    }
    
    /**
     * Creates a dummy for the given original dummy. The dummy's {@code ORIGIN} property
     * will point to the original dummy. This way, the original dummy can later be restored,
     * and the newly created dummy can be told apart from the original.
     * 
     * @param originalDummy the original dummy.
     * @return the newly created dummy node.
     */
    private LNode createDummy(final LNode originalDummy) {
        LNode newDummy = new LNode();
        newDummy.copyProperties(originalDummy);
        newDummy.setProperty(Properties.EXT_PORT_REPLACED_DUMMY, originalDummy);
        newDummy.setProperty(LayoutOptions.PORT_CONSTRAINTS, PortConstraints.FIXED_POS);
        newDummy.setProperty(LayoutOptions.ALIGNMENT, Alignment.CENTER);
        
        LPort inputPort = new LPort();
        inputPort.setNode(newDummy);
        inputPort.setSide(PortSide.WEST);
        
        return newDummy;
    }
    
}
