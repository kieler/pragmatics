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

import java.util.HashMap;
import java.util.HashSet;
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
 * Processes northern and southern external port dummies if the graph's port constraints are
 * at least {@code FIXED_ORDER}.
 * 
 * <p>At {@code FIXED_ORDER} upwards, northern and southern external ports can sadly not be
 * processed in the usual way. Instead, we need to replace them by new external port dummies.
 * For each node connected to a northern or southern external port dummy, we need to place a
 * new dummy in the next layer, rerouting the edges appropriately. The original dummies are
 * removed, to be reinserted later by {@link ExternalPortOrthogonalEdgeRouter}.</p>
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
 * @see ExternalPortOrthogonalEdgeRouter
 * @author cds
 */
public class ConstrainedExternalPortProcessor extends AbstractAlgorithm implements ILayoutProcessor {

    /**
     * {@inheritDoc}
     */
    public void process(final LayeredGraph layeredGraph) {
        getMonitor().begin("Constrained external port processing", 1);
        
        // If the port constraints are not at least FIXED_ORDER, there's nothing to do
        if (layeredGraph.getProperty(LayoutOptions.PORT_CONSTRAINTS).isOrderFixed()) {
            processNorthernAndSouthernPortDummies(layeredGraph);
        }
        
        getMonitor().done();
    }
    
    /**
     * Processes northern and southern external port dummies.
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
        
        // See if there are nodes that have to be added to a new last layer
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
