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
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.options.PortType;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.Properties;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;

/**
 * Removes dummy nodes created by {@link NorthSouthPortPreprocessor} and routes the
 * edges properly.
 * 
 * <dl>
 *   <dt>Precondition:</dt><dd>a layered graph; nodes are placed; edges are routed; port positions
 *     are fixed.</dd>
 *   <dt>Postcondition:</dt><dd>north south port dummy nodes are removed, their edges
 *     properly reconnected and routed.</dd>
 *   <dt>Slots:</dt><dd>After phase 5.</dd>
 *   <dt>Same-slot dependencies:</dt><dd>None.</dd>
 * </dl>
 * 
 * @see NorthSouthPortPreprocessor
 * @author cds
 */
public class NorthSouthPortPostprocessor extends AbstractAlgorithm implements ILayoutProcessor {

    /**
     * {@inheritDoc}
     */
    public void process(final LayeredGraph layeredGraph) {
        getMonitor().begin("Odd port side processing", 1);
        
        // Iterate through the layers
        for (Layer layer : layeredGraph.getLayers()) {
            // Iterate through the nodes (use an array to avoid concurrent modification
            // exceptions)
            LNode[] nodeArray = layer.getNodes().toArray(new LNode[0]);
            for (LNode node : nodeArray) {
                // We only care for North/South Port dummy nodes
                if (node.getProperty(Properties.NODE_TYPE) != Properties.NodeType.NORTH_SOUTH_PORT) {
                    continue;
                }
                
                // Iterate through the ports
                for (LPort port : node.getPorts()) {
                    if (port.getType() == PortType.INPUT) {
                        processInputPort(port);
                    } else if (port.getType() == PortType.OUTPUT) {
                        processOutputPort(port);
                    }
                }
                
                // Remove the node
                node.setLayer(null);
            }
        }
        
        getMonitor().done();
    }
    
    /**
     * Reroutes the edges connected to the given input port back to the port it was
     * created for.
     * 
     * @param inputPort the input port whose edges to restore.
     */
    private void processInputPort(final LPort inputPort) {
        // Retrieve the port the dummy node was created from
        LPort originPort = (LPort) inputPort.getProperty(Properties.ORIGIN);
        
        // Calculate the bend point
        KVector bendPoint = inputPort.getNode().getPosition();
        bendPoint.x = originPort.getNode().getPosition().x + originPort.getPosition().x;
        
        // Reroute the edges, inserting a new bend point at the position of
        // the dummy node
        LEdge[] edgeArray = inputPort.getEdges().toArray(new LEdge[0]);
        for (LEdge inEdge : edgeArray) {
            inEdge.setTarget(originPort);
            inEdge.getBendPoints().add(bendPoint.x, bendPoint.y);
        }
    }
    
    /**
     * Reroutes the edges connected to the given output port back to the port it was
     * created for.
     * 
     * @param outputPort the output port whose edges to restore.
     */
    private void processOutputPort(final LPort outputPort) {
        // Retrieve the port the dummy node was created from
        LPort originPort = (LPort) outputPort.getProperty(Properties.ORIGIN);
        
        // Calculate the bend point
        KVector bendPoint = outputPort.getNode().getPosition();
        bendPoint.x = originPort.getNode().getPosition().x + originPort.getPosition().x;
        
        // Reroute the edges, inserting a new bend point at the position of
        // the dummy node
        LEdge[] edgeArray = outputPort.getEdges().toArray(new LEdge[0]);
        for (LEdge outEdge : edgeArray) {
            outEdge.setSource(originPort);
            outEdge.getBendPoints().addFirst(bendPoint.x, bendPoint.y);
        }
    }
}
