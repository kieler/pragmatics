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

import java.util.List;
import java.util.ListIterator;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.kiml.options.PortType;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.Properties;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;

/**
 * TODO Document
 * 
 * <dl>
 *   <dt>Precondition:</dt><dd>a layered graph.</dd>
 *   <dt>Postcondition:</dt><dd>long edge dummy nodes belonging to the same hyperedge and
 *     being directly next to each other are merged.</dd>
 * </dl>
 *
 * @author cds
 */
public class HyperedgeDummyJoiner extends AbstractAlgorithm implements ILayoutProcessor {

    /**
     * {@inheritDoc}
     */
    public void process(final LayeredGraph layeredGraph) {
        getMonitor().begin("Edge splitting", 1);
        
        // Iterate through the layers
        ListIterator<Layer> layerIter = layeredGraph.getLayers().listIterator();
        while (layerIter.hasNext()) {
            Layer layer = layerIter.next();
            List<LNode> nodes = layer.getNodes();
            
            // If there are no nodes anyway, just move on to the next layer
            if (nodes.isEmpty()) {
                continue;
            }
            
            LNode currentNode = null;
            Properties.NodeType currentNodeType = null;
            LNode lastNode = null;
            Properties.NodeType lastNodeType = null;
            
            // Iterate through the remaining nodes
            for (int nodeIndex = 0; nodeIndex < nodes.size(); nodeIndex++) {
                // Get the next node
                currentNode = nodes.get(nodeIndex);
                currentNodeType = currentNode.getProperty(Properties.NODE_TYPE);
                
                // We're only interested if the current and last nodes are long edge dummies
                if (currentNodeType == Properties.NodeType.LONG_EDGE
                        && lastNodeType == Properties.NodeType.LONG_EDGE) {
                    
                    // Get long edge source and target ports
                    LPort currentNodeSource = currentNode.getProperty(Properties.LONG_EDGE_SOURCE);
                    LPort lastNodeSource = lastNode.getProperty(Properties.LONG_EDGE_SOURCE);
                    LPort currentNodeTarget = currentNode.getProperty(Properties.LONG_EDGE_TARGET);
                    LPort lastNodeTarget = lastNode.getProperty(Properties.LONG_EDGE_TARGET);
                    
                    // If the source or the target are identical, merge the current node
                    // into the last
                    if (currentNodeSource == lastNodeSource || currentNodeTarget == lastNodeTarget) {
                        mergeNodes(lastNode, currentNode, currentNodeSource == lastNodeSource,
                                currentNodeTarget == lastNodeTarget
                        );
                        
                        // Remove the current node and make the last node the current node
                        nodes.remove(nodeIndex);
                        nodeIndex--;
                        currentNode = lastNode;
                        currentNodeType = lastNodeType;
                    }
                }
                
                // Remember this node for the next iteration
                lastNode = currentNode;
                lastNodeType = currentNodeType;
            }
        }
        
        getMonitor().done();
    }
    
    /**
     * Merges the merge source node into the merge target node. All edges that were previously
     * connected to the merge source's ports are rerouted to the merge target. The merge target's
     * long edge source and target ports can be set to null.
     * 
     * @param mergeTarget the merge target node.
     * @param mergeSource the merge source node.
     * @param keepSourcePort if {@code true}, the long edge source property is set to {@code null}.
     * @param keepTargetPort if {@code true}, the long edge target property is set to {@code null}.
     */
    private void mergeNodes(final LNode mergeTarget, final LNode mergeSource,
            final boolean keepSourcePort, final boolean keepTargetPort) {
        
        LPort mergeTargetInputPort = mergeTarget.getPorts(PortType.INPUT).iterator().next();
        LPort mergeTargetOutputPort = mergeTarget.getPorts(PortType.OUTPUT).iterator().next();
        
        for (LPort port : mergeSource.getPorts()) {
            if (port.getType() == PortType.INPUT) {
                // Use an array of edges to avoid concurrent modification exceptions
                LEdge[] edgeArray = port.getEdges().toArray(new LEdge[0]);
                
                for (LEdge edge : edgeArray) {
                    edge.setTarget(mergeTargetInputPort);
                }
            } else {
                // Use an array of edges to avoid concurrent modification exceptions
                LEdge[] edgeArray = port.getEdges().toArray(new LEdge[0]);
                
                for (LEdge edge : edgeArray) {
                    edge.setSource(mergeTargetOutputPort);
                }
            }
        }
        
        // Possibly reset source and target ports
        if (!keepSourcePort) {
            mergeTarget.setProperty(Properties.LONG_EDGE_SOURCE, null);
        }
        
        if (!keepTargetPort) {
            mergeTarget.setProperty(Properties.LONG_EDGE_TARGET, null);
        }
    }

}
