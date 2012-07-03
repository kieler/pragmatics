/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
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

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.properties.NodeType;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * 
 * @author jjc
 */
public class LabelDummySwitcher extends AbstractAlgorithm implements ILayoutProcessor {

    /**
     * {@inheritDoc}
     */
    public void process(final LayeredGraph layeredGraph) {
        // Mark all label nodes which can be swapped to the middle of a long edge
        List<Pair<LNode, LNode>> nodesToSwap = new LinkedList<Pair<LNode, LNode>>();
        for (Layer layer : layeredGraph) {
            for (LNode node : layer.getNodes()) {
                if (node.getProperty(Properties.NODE_TYPE) == NodeType.LABEL) {
                    LNode target = node.getPorts().get(0).getOutgoingEdges()
                            .get(0).getTarget().getNode();
                    List<LNode> longEdge = new LinkedList<LNode>();
                    while (target.getProperty(Properties.NODE_TYPE) == NodeType.LONG_EDGE) {
                        longEdge.add(target);
                        target = target.getPorts().get(0).getOutgoingEdges()
                                .get(0).getTarget().getNode();
                    }
                    int middle = longEdge.size() / 2;
                    if (longEdge.size() > 0) {
                        nodesToSwap.add(new Pair<LNode, LNode>(node, longEdge.get(middle)));
                    }
                }
            }
        }
        
        // Execute the swapping
        for (Pair<LNode, LNode> swapPair : nodesToSwap) {
            swapNodes(swapPair.getFirst(), swapPair.getSecond());
        }
    }
    
    private void swapNodes(final LNode one, final LNode other) {
        // Detect incoming and outgoing ports of the nodes
        // Since they are dummy nodes, they can simply be found by looking where
        // there are incoming or outgoing edges
        LPort oneIncomingPort = null;
        LPort oneOutgoingPort = null;
        LPort otherIncomingPort = null;
        LPort otherOutgoingPort = null;
        for (LPort port : one.getPorts()) {
            if (port.getIncomingEdges().size() > 0) {
                oneIncomingPort = port;
            } else if (port.getOutgoingEdges().size() > 0) {
                oneOutgoingPort = port;
            }
        }
        for (LPort port : other.getPorts()) {
            if (port.getIncomingEdges().size() > 0) {
                otherIncomingPort = port;
            } else if (port.getOutgoingEdges().size() > 0) {
                otherOutgoingPort = port;
            }
        }
        
        // Store information about first node
        Layer oneLayer = one.getLayer();
        int inLayerPosition = one.getIndex();
        List<LEdge> oneIncomingEdges = oneIncomingPort.getIncomingEdges();
        List<LEdge> oneOutgoingEdges = oneOutgoingPort.getOutgoingEdges();
        
        // Set values of first node to values from second node
        one.setLayer(other.getIndex(), other.getLayer());
        for (LEdge edge : otherIncomingPort.getIncomingEdges()) {
            edge.setTarget(oneIncomingPort);
        }
        for (LEdge edge : otherOutgoingPort.getOutgoingEdges()) {
            edge.setSource(oneOutgoingPort);
        }
        
        // Set values of first node to values from second node
        other.setLayer(inLayerPosition, oneLayer);
        for (LEdge edge : oneIncomingEdges) {
            edge.setTarget(otherIncomingPort);
        }
        for (LEdge edge : oneOutgoingEdges) {
            edge.setSource(otherOutgoingPort);
        }
    }
}
