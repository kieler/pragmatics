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
 * Removes dummy nodes due to edge splitting. (dummy nodes that have the node
 * type {@link de.cau.cs.kieler.klay.layered.Properties.NodeType#LONG_EDGE})
 * 
 * <p>
 * It is assumed that the edges connected to such dummy nodes were created by
 * the {@link EdgeSplitter}, which implies that such a dummy node has exactly
 * one incoming and one outgoing edge, and that these edges were created from
 * the same source edge. It should thus make no difference which of the edges
 * is dropped.
 * 
 * <dl>
 *   <dt>Precondition:</dt><dd>a layered graph; dummy nodes with the
 *     {@link de.cau.cs.kieler.klay.layered.Properties.NodeType#LONG_EDGE} node
 *     type have exactly one incoming and one outgoing edge, both resulting from
 *     splitting a single "unproper" edge.</dd>
 *   <dt>Postcondition:</dt><dd>the graph is layered; there are no dummy nodes
 *     of type {@link de.cau.cs.kieler.klay.layered.Properties.NodeType#LONG_EDGE};
 *     the graph may not be properly layered.</dd>
 * </dl>
 *
 * @author cds
 */
public class EdgeJoiner extends AbstractAlgorithm implements ILayoutProcessor {

    /**
     * {@inheritDoc}
     */
    public void process(final LayeredGraph layeredGraph) {
        getMonitor().begin("Edge joining", 1);
        
        // Iterate through the layers
        for (Layer layer : layeredGraph.getLayers()) {
            // Get a list iterator for the layer's nodes (since we might be
            // removing dummy nodes from it)
            ListIterator<LNode> layerIterator = layer.getNodes().listIterator();
            
            while (layerIterator.hasNext()) {
                LNode node = layerIterator.next();
                
                // Check if it's a dummy edge we're looking for
                if (node.getProperty(Properties.NODE_TYPE).equals(Properties.NodeType.LONG_EDGE)) {
                    // Get the input and output port (of which we assume to be
                    // exactly one)
                    LPort inputPort = node.getPorts(PortType.INPUT).iterator().next();
                    LPort outputPort = node.getPorts(PortType.OUTPUT).iterator().next();
                    
                    // Get the two edges
                    LEdge survivingEdge = inputPort.getEdges().get(0);
                    LEdge droppedEdge = outputPort.getEdges().get(0);
                    
                    // Do some edgy stuff
                    survivingEdge.setTarget(droppedEdge.getTarget());
                    droppedEdge.getTarget().getEdges().remove(droppedEdge);
                    
                    // Remove the node
                    layerIterator.remove();
                }
            }
        }
        
        getMonitor().done();
    }

}
