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

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.Properties;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.p2layers.LayerConstraint;

/**
 * Makes sure nodes with layer constraints have only incoming or only outgoing edges,
 * as appropriate. This is done even before cycle breaking because the result may
 * already break some cycles. This processor is required for
 * {@link LayerConstraintApplicationProcessor} to work correctly.
 * 
 * <dl>
 *   <dt>Precondition:</dt><dd>an unlayered graph.</dd>
 *   <dt>Postcondition:</dt><dd>nodes with layer constraints have only incoming or
 *     only outgoing edges, as appropriate.</dd>
 *   <dt>Slots:</dt><dd>Before phase 1.</dd>
 *   <dt>Same-slot dependencies:</dt><dd>None.</dd>
 * </dl>
 * 
 * @see LayerConstraintApplicationProcessor
 * @author cds
 */
public class LayerConstraintEdgeReverser extends AbstractAlgorithm implements ILayoutProcessor {

    /**
     * {@inheritDoc}
     */
    public void process(final LayeredGraph layeredGraph) {
        getMonitor().begin("Layer constraint edge reversal", 1);
        
        // Iterate through the list of nodes
        for (LNode node : layeredGraph.getLayerlessNodes()) {
            LayerConstraint constraint = node.getProperty(Properties.LAYER_CONSTRAINT);
            
            // Check if there is a layer constraint
            switch (constraint) {
            case FIRST:
                reverseEdges(node, true);
                break;
            
            case FIRST_SEPARATE:
                reverseEdges(node, true);
                break;
            
            case LAST:
                reverseEdges(node, false);
                break;
            
            case LAST_SEPARATE:
                reverseEdges(node, false);
                break;
            }
        }
        
        getMonitor().done();
    }
    
    /**
     * Places the node in the given layer, taking care to reverse the necessary edges.
     * 
     * @param node the node to place in the layer.
     * @param first {@code true} if the node is only allowed to have outgoing edges,
     *              {@code false} if it is only allowed to have incoming edges.
     */
    private void reverseEdges(final LNode node, final boolean first) {
        // Iterate through the node's edges and reverse them, if necessary
        for (LPort port : node.getPorts()) {
            // In the first layer, incoming edges are not OK
            if (first && !port.getIncomingEdges().isEmpty()) {
                LEdge[] edges = port.getIncomingEdges().toArray(new LEdge[0]);

                for (LEdge edge : edges) {
                    edge.reverse();
                }
            }
            
            // In the last layer, outgoing edges are not OK
            if (!first && !port.getOutgoingEdges().isEmpty()) {
                LEdge[] edges = port.getOutgoingEdges().toArray(new LEdge[0]);

                for (LEdge edge : edges) {
                    edge.reverse();
                }
            }
        }
    }

}
