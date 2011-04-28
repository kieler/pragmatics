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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.Properties;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;

/**
 * Processes constraints imposed on external node dummies. There are constraints when the
 * graph's port constraints are at least at {@code FIXED_ORDER}.
 * 
 * <p>Eastern and western ports cannot be ordered arbitrarily by the crossing minimizer if
 * the port order is fixed. Thus, this processor inserts appropriate in-layer successor
 * constraints to restrict the node ordering.</p>
 * 
 * <p>Northern and southern external ports can sadly not be processed in the usual way with
 * port constraints at least at {@code FIXED_ORDER}. Instead, we need to replace them by new
 * external port dummies. For each node connected to a northern or southern external port
 * dummy, we need to place a new dummy in the next layer, rerouting the edges appropriately.
 * The original dummies are removed, to be reinserted later by
 * {@link ExternalPortOrthogonalEdgeRouter}.</p>
 * 
 * <dl>
 *   <dt>Precondition:</dt><dd>A layered graph; long edge dummies have not yet been inserted;
 *     layer constraints have not yet been applied.</dd>
 *   <dt>Postcondition:</dt><dd>External port dummies for northern and southern ports are
 *     replaced by multiple dummies if the port constraints are at least {@code FIXED_ORDER}.</dd>
 *   <dt>Slots:</dt><dd>Before phase 3.</dd>
 *   <dt>Same-slot dependencies:</dt><dd>None.</dd>
 * </dl>
 * 
 * @see ExternalPortDummySizeProcessor
 * @see ExternalPortOrthogonalEdgeRouter
 * @author cds
 */
public class ExternalPortConstraintProcessor extends AbstractAlgorithm implements ILayoutProcessor {
    
    /**
     * Comparator to compare nodes by their position values in ascending order. Nodes
     * that are not external port dummies are sorted to the bottom of a list.
     * 
     * @author cds
     */
    private static class NodeComparator implements Comparator<LNode> {
        /**
         * {@inheritDoc}
         */
        public int compare(final LNode node1, final LNode node2) {
            Properties.NodeType nodeType1 = node1.getProperty(Properties.NODE_TYPE);
            double nodePos1 = node1.getProperty(Properties.EXT_PORT_RATIO_OR_POSITION);
            Properties.NodeType nodeType2 = node2.getProperty(Properties.NODE_TYPE);
            double nodePos2 = node2.getProperty(Properties.EXT_PORT_RATIO_OR_POSITION);
            
            if (nodeType2 != Properties.NodeType.EXTERNAL_PORT) {
                return -1;
            } else if (nodeType1 != Properties.NodeType.EXTERNAL_PORT) {
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
        getMonitor().begin("External port constraint processing", 1);
        
        // If the port constraints are not at least FIXED_ORDER, there's nothing to do
        if (layeredGraph.getProperty(LayoutOptions.PORT_CONSTRAINTS).isOrderFixed()) {
            processEasternAndWesternPortDummies(layeredGraph);
            processNorthernAndSouthernPortDummies(layeredGraph);
        }
        
        getMonitor().done();
    }


    ///////////////////////////////////////////////////////////////////////////////
    // East / West External Port Dummies
    
    /**
     * Process eastern and southern external port dummies.
     * 
     * @param layeredGraph the layered graph
     */
    private void processEasternAndWesternPortDummies(final LayeredGraph layeredGraph) {
        List<Layer> layers = layeredGraph.getLayers();
        
        // This affects the first and last layer
        processEasternAndWesternPortDummies(layers.get(0));
        processEasternAndWesternPortDummies(layers.get(layers.size() - 1));
    }
    
    /**
     * Process the eastern and western external port dummies present in the given layer.
     * 
     * @param layer the layer.
     */
    private void processEasternAndWesternPortDummies(final Layer layer) {
        // Put the nodes into an array
        LNode[] nodes = layer.getNodes().toArray(new LNode[0]);
        
        // Sort the array; external port dummies are at the top, sorted by position or ratio
        // in ascending order
        Arrays.sort(nodes, new NodeComparator());
        
        // Insert in-layer successor constraints where appropriate
        LNode lastExternalDummy = null;
        
        for (LNode node : nodes) {
            if (node.getProperty(Properties.NODE_TYPE) != Properties.NodeType.EXTERNAL_PORT) {
                // No external port dummy nodes any more
                break;
            }
            
            if (lastExternalDummy != null) {
                lastExternalDummy.setProperty(Properties.IN_LAYER_SUCCESSOR_CONSTRAINT, node);
            }
            
            lastExternalDummy = node;
        }
    }


    ///////////////////////////////////////////////////////////////////////////////
    // North / South External Port Dummies
    
    /**
     * Process northern and southern external port dummies.
     * 
     * @param layeredGraph the layered graph.
     */
    private void processNorthernAndSouthernPortDummies(final LayeredGraph layeredGraph) {
        Map<Object, LNode> nextLayerNodesToAdd = new HashMap<Object, LNode>();
        Set<LNode> externalNodeDummiesToRemove = new HashSet<LNode>();
        
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
            
            // Iterate through the layer's ndoes, looking for normal nodes connected to
            // northern / southern external port dummies
            for (LNode currentNode : currentLayer.getNodes()) {
                // Iterate through the node's outgoing edges
                for (LEdge edge : currentNode.getOutgoingEdges()) {
                    LNode targetNode = edge.getTarget().getNode();
                    
                    // Check if it's a northern / southern dummy node
                    if (!isNorthernSouthernDummy(targetNode)) {
                        continue;
                    }
                    externalNodeDummiesToRemove.add(targetNode);
                    
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
        
        // See if there are nodes that have to be added to a new last layer (this actually shouldn't
        // happen, but as the old saying I just invented goes: when in doubt, opt for the safe side!)
        if (!nextLayerNodesToAdd.isEmpty()) {
            Layer newLastLayer = new Layer(layeredGraph);
            layeredGraph.getLayers().add(newLastLayer);

            for (LNode dummy : nextLayerNodesToAdd.values()) {
                dummy.setLayer(newLastLayer);
            }
        }
        
        // Remove external node dummies
        for (LNode dummy : externalNodeDummiesToRemove) {
            dummy.setLayer(null);
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
        Properties.NodeType nodeType = node.getProperty(Properties.NODE_TYPE);
        
        if (nodeType == Properties.NodeType.EXTERNAL_PORT) {
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
        LNode newDummy = new LNode(originalDummy.getName());
        newDummy.copyProperties(originalDummy);
        newDummy.setProperty(Properties.ORIGIN, originalDummy);
        newDummy.setProperty(LayoutOptions.PORT_CONSTRAINTS, PortConstraints.FIXED_POS);
        
        LPort inputPort = new LPort();
        inputPort.setNode(newDummy);
        inputPort.setSide(PortSide.WEST);
        
        return newDummy;
    }
    
}
