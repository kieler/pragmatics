/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.intermediate;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.kiml.options.PortType;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.Properties;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;

/**
 * Inserts dummy nodes to cope with north and south ports.
 * 
 * <p>
 * TODO Add a proper description here of what's going on.
 * 
 * <dl>
 *   <dt>Precondition:</dt><dd>a layered graph, with an established node order in each
 *     layer.</dd>
 *   <dt>Postcondition:</dt><dd>dummy nodes have been inserted for edges connected to
 *     ports on north and south sides.</dd>
 * </dl>
 * 
 * @see NorthSouthPortPostprocessor
 * @author cds
 */
public class NorthSouthPortPreprocessor extends AbstractAlgorithm implements ILayoutProcessor {

    /**
     * {@inheritDoc}
     */
    public void process(final LayeredGraph layeredGraph) {
        getMonitor().begin("Odd port side processing", 1);
        
        // Iterate through the layers
        for (Layer layer : layeredGraph.getLayers()) {
            // The pointer indicates the index of the current node while northern ports are
            // processed, and the index of the most recently inserted dummy while south ports
            // are processed
            int pointer = -1;
            
            // Iterate through the nodes (use an array to avoid concurrent modification
            // exceptions)
            LNode[] nodeArray = layer.getNodes().toArray(new LNode[0]);
            for (LNode node : nodeArray) {
                pointer++;
                
                // We only care about non-dummy nodes
                if (!node.getProperty(Properties.NODE_TYPE).equals(Properties.NodeType.NORMAL)) {
                    continue;
                }
                
                // Iterate over the northern ports
                int northPointer = pointer;
                for (LPort northPort : node.getPorts(PortSide.NORTH)) {
                    LNode dummy = createDummyNode(northPort);
                    dummy.setLayer(northPointer, layer);
                    pointer++;
                }
                
                // Iterate over the southern ports
                for (LPort southPort : node.getPorts(PortSide.SOUTH)) {
                    LNode dummy = createDummyNode(southPort);
                    dummy.setLayer(++pointer, layer);
                }
            }
        }
    }
    
    /**
     * Creates a dummy node for the given port. All the edges incident with the given port
     * are rerouted to the new dummy node, leaving the port edgeless for the moment. This
     * method takes into account that a port may have both incoming and outgoing edges,
     * distributing them to an input and an output port of the new dummy node.
     * 
     * @param port the port.
     * @return dummy node for the port.
     */
    private LNode createDummyNode(final LPort port) {
        LNode dummy = new LNode();
        dummy.setProperty(Properties.ORIGIN, port);
        dummy.setProperty(Properties.NODE_TYPE,
                Properties.NodeType.NORTH_SOUTH_PORT);
        dummy.setProperty(LayoutOptions.PORT_CONSTRAINTS, PortConstraints.FIXED_POS);
        
        LPort dummyInputPort = new LPort();
        dummyInputPort.setType(PortType.INPUT);
        dummyInputPort.setSide(PortSide.WEST);
        dummyInputPort.setNode(dummy);
        
        LPort dummyOutputPort = new LPort();
        dummyOutputPort.setType(PortType.OUTPUT);
        dummyOutputPort.setSide(PortSide.EAST);
        dummyOutputPort.setNode(dummy);
        
        // Reroute the edges
        LEdge[] edgeArray = port.getEdges().toArray(new LEdge[0]);
        for (LEdge edge : edgeArray) {
            if (edge.getSource() == port) {
                edge.setSource(dummyOutputPort);
            } else {
                edge.setTarget(dummyInputPort);
            }
        }
        
        return dummy;
    }
}
