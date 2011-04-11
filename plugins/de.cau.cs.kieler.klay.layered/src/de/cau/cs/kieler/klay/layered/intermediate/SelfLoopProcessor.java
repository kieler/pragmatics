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

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
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
 * This processor handles certain kinds of self loops. There are several kinds of self-loops
 * one can imagine:
 * <ul>
 *   <li>Both ports on the same side. This case does not require special processing. It must
 *     only be especially handled in upcoming phases.</li>
 *   <li>One of the ports on the eastern or western side, the other port on the northern or
 *     southern side. This is handled well enough with north / south dummies.</li>
 *   <li>One of the ports on the eastern side, the other on the western side. In this case,
 *     we need some special processing as the edge needs some vertical space in the layer to
 *     be routed. Thus, we insert a long edge dummy and also make sure that the edges go from
 *     the western into the eastern port, possibly reversing the edge.</li>
 * </ul>
 * 
 * <dl>
 *   <dt>Precondition:</dt><dd>a layered graph.</dd>
 *   <dt>Postcondition:</dt><dd>long edge dummies were inserted for special kinds of self
 *     loops.</dd>
 *   <dt>Slots:</dt><dd>Before phase 3.</dd>
 *   <dt>Same-slot dependencies:</dt><dd>None.</dd>
 * </dl>
 *
 * @author cds
 */
public class SelfLoopProcessor extends AbstractAlgorithm implements ILayoutProcessor {

    /**
     * {@inheritDoc}
     */
    public void process(final LayeredGraph layeredGraph) {
        getMonitor().begin("Self-loop processing", 1);
        
        // Iterate through all nodes
        List<LNode> createdDummies = new LinkedList<LNode>();
        
        for (Layer layer : layeredGraph.getLayers()) {
            createdDummies.clear();
            
            for (LNode node : layer.getNodes()) {
                // Iterate through all ports, looking for east-west self-loops
                for (LPort port : node.getPorts()) {
                    for (LEdge edge : port.getEdges()) {
                        // If it's not a self-loop, we're not interested
                        if (edge.getSource().getNode() != edge.getTarget().getNode()) {
                            continue;
                        }
                        
                        LPort sourcePort = edge.getSource();
                        LPort targetPort = edge.getTarget();
                        
                        // Check if the source and target port are east ant west, opposing each other
                        if (!isInterestingCase(sourcePort.getSide(), targetPort.getSide())) {
                            continue;
                        }
                        
                        createdDummies.add(handleInterestingCase(edge, sourcePort, targetPort));
                    }
                }
            }
            
            // Add the dummies, if any
            for (LNode dummy : createdDummies) {
                dummy.setLayer(layer);
            }
        }
        
        getMonitor().done();
    }
    
    /**
     * Checks if the two port sides are east and west.
     * 
     * @param sourceSide the source port's side.
     * @param targetSide the target port's side.
     * @return {@code true} if one of the sides is east and the other west, {@code false} otherwise.
     */
    private boolean isInterestingCase(final PortSide sourceSide, final PortSide targetSide) {
        return (sourceSide == PortSide.EAST && targetSide == PortSide.WEST)
            || (sourceSide == PortSide.WEST && targetSide == PortSide.EAST);
    }
    
    /**
     * Creates a dummy for the self-loop edge connecting the two given ports. The dummy is not
     * added to the layer yet.
     * 
     * @param edge the self-looping edge.
     * @param sourcePort the source port.
     * @param targetPort the target port.
     * @return the dummy node created.
     */
    private LNode handleInterestingCase(final LEdge edge, final LPort sourcePort,
            final LPort targetPort) {
        
        // If the edge goes from west to east, it must be reversed
        
        
        return null;
    }
    
}
