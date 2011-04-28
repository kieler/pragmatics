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

/**
 * Makes sure nodes with edge or layer constraints have only incoming or only outgoing edges,
 * as appropriate. This is done even before cycle breaking because the result may
 * already break some cycles. This processor is required for
 * {@link LayerConstraintProcessor} to work correctly. If edge constraints are in conflict
 * with layer constraints, the latter take precedence.
 * 
 * <dl>
 *   <dt>Precondition:</dt><dd>an unlayered graph.</dd>
 *   <dt>Postcondition:</dt><dd>nodes with layer constraints have only incoming or
 *     only outgoing edges, as appropriate.</dd>
 *   <dt>Slots:</dt><dd>Before phase 1.</dd>
 *   <dt>Same-slot dependencies:</dt><dd>None.</dd>
 * </dl>
 * 
 * @see LayerConstraintProcessor
 * @author cds
 */
public class EdgeAndLayerConstraintEdgeReverser extends AbstractAlgorithm implements ILayoutProcessor {

    /**
     * {@inheritDoc}
     */
    public void process(final LayeredGraph layeredGraph) {
        getMonitor().begin("Edge and layer constraint edge reversal", 1);
        
        // Iterate through the list of nodes
        for (LNode node : layeredGraph.getLayerlessNodes()) {
            // Check if there is a layer constraint
            Properties.LayerConstraint layerConstraint = node.getProperty(Properties.LAYER_CONSTRAINT);
            
            switch (layerConstraint) {
            case FIRST:
            case FIRST_SEPARATE:
                node.setProperty(Properties.EDGE_CONSTRAINT, Properties.EdgeConstraint.OUTGOING_ONLY);
                break;
            
            case LAST:
            case LAST_SEPARATE:
                node.setProperty(Properties.EDGE_CONSTRAINT, Properties.EdgeConstraint.INCOMING_ONLY);
                break;
            }
            
            // Check if there is an edge constraint
            Properties.EdgeConstraint edgeConstraint = node.getProperty(Properties.EDGE_CONSTRAINT);
            
            if (edgeConstraint == Properties.EdgeConstraint.INCOMING_ONLY) {
                reverseEdges(node, true);
            } else if (edgeConstraint == Properties.EdgeConstraint.OUTGOING_ONLY) {
                reverseEdges(node, false);
            }
        }
        
        getMonitor().done();
    }
    
    /**
     * Reverses edges as appropriate.
     * 
     * @param node the node to place in the layer.
     * @param onlyIncoming {@code true} if the node is only allowed to have incoming edges,
     *                     {@code false} if it is only allowed to have outgoing edges.
     */
    private void reverseEdges(final LNode node, final boolean onlyIncoming) {
        // Iterate through the node's edges and reverse them, if necessary
        for (LPort port : node.getPorts()) {
            // Only incoming edges
            if (onlyIncoming && !port.getOutgoingEdges().isEmpty()) {
                LEdge[] edges = port.getOutgoingEdges().toArray(new LEdge[0]);

                for (LEdge edge : edges) {
                    edge.reverse();
                }
            }
            
            // Only outgoing edges
            if (!onlyIncoming && !port.getIncomingEdges().isEmpty()) {
                LEdge[] edges = port.getIncomingEdges().toArray(new LEdge[0]);

                for (LEdge edge : edges) {
                    edge.reverse();
                }
            }
        }
    }

}
