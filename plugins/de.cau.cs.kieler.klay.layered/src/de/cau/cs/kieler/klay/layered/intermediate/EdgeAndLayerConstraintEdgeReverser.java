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

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.kiml.options.PortType;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.properties.EdgeConstraint;
import de.cau.cs.kieler.klay.layered.properties.LayerConstraint;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * Makes sure nodes with edge or layer constraints have only incoming or only outgoing edges,
 * as appropriate. This is done even before cycle breaking because the result may
 * already break some cycles. This processor is required for
 * {@link LayerConstraintProcessor} to work correctly. If edge constraints are in conflict
 * with layer constraints, the latter take precedence. Furthermore, this processor handles
 * nodes with fixed port sides for which all ports are reversed, i.e. input ports are on the
 * right and output ports are on the left. All incident edges are reversed in such cases.
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
            LayerConstraint layerConstraint = node.getProperty(Properties.LAYER_CONSTRAINT);
            
            switch (layerConstraint) {
            case FIRST:
            case FIRST_SEPARATE:
                node.setProperty(Properties.EDGE_CONSTRAINT, EdgeConstraint.OUTGOING_ONLY);
                break;
            
            case LAST:
            case LAST_SEPARATE:
                node.setProperty(Properties.EDGE_CONSTRAINT, EdgeConstraint.INCOMING_ONLY);
                break;
            }
            
            // Check if there is an edge constraint
            EdgeConstraint edgeConstraint = node.getProperty(Properties.EDGE_CONSTRAINT);
            
            if (edgeConstraint == EdgeConstraint.INCOMING_ONLY) {
                reverseEdges(node, PortType.OUTPUT);
            } else if (edgeConstraint == EdgeConstraint.OUTGOING_ONLY) {
                reverseEdges(node, PortType.INPUT);
            } else if (node.getProperty(LayoutOptions.PORT_CONSTRAINTS).isSideFixed()) {
                // Check whether the ports are reversed, in which case all edges are reversed
                boolean allPortsReversed = true;
                for (LPort port : node.getPorts()) {
                    if (!(port.getSide() == PortSide.EAST && port.getNetFlow() > 0
                            || port.getSide() == PortSide.WEST && port.getNetFlow() < 0)) {
                        allPortsReversed = false;
                        break;
                    }
                }
                if (allPortsReversed) {
                    reverseEdges(node, PortType.UNDEFINED);
                }
            }
        }
        
        getMonitor().done();
    }
    
    /**
     * Reverses edges as appropriate.
     * 
     * @param node the node to place in the layer.
     * @param type type of edges that are reversed
     */
    private void reverseEdges(final LNode node, final PortType type) {
        // Iterate through the node's edges and reverse them, if necessary
        for (LPort port : node.getPorts()) {
            // Only incoming edges
            List<LEdge> outgoing = port.getOutgoingEdges();
            if (type != PortType.INPUT && !outgoing.isEmpty()) {
                LEdge[] edges = outgoing.toArray(new LEdge[outgoing.size()]);

                for (LEdge edge : edges) {
                    edge.reverse();
                }
            }
            
            // Only outgoing edges
            List<LEdge> incoming = port.getIncomingEdges();
            if (type != PortType.OUTPUT && !incoming.isEmpty()) {
                LEdge[] edges = incoming.toArray(new LEdge[incoming.size()]);

                for (LEdge edge : edges) {
                    edge.reverse();
                }
            }
        }
    }

}
