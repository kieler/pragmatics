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
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.kiml.options.PortSide;
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
                    swapNodes(node, longEdge.get(middle));
                }
            }
        }
        
    }
    
    private void swapNodes(final LNode one, final LNode other) {
//        Layer oneLayer = one.getLayer();
//        List<LEdge> oneIncoming = one.getPorts().get(0).getIncomingEdges();
//        List<LEdge> oneOutgoing = one.getPorts().get(0).getOutgoingEdges();
//        
//     // Get the next layer
//        Layer nextLayer = layerIter.next();
//        
//        // Create dummy node
//        LNode dummyNode = new LNode();
//        dummyNode.setProperty(Properties.ORIGIN, edge);
//        dummyNode.setProperty(Properties.NODE_TYPE, NodeType.LONG_EDGE);
//        dummyNode.setProperty(LayoutOptions.PORT_CONSTRAINTS,
//                PortConstraints.FIXED_POS);
//        dummyNode.setLayer(nextLayer);
//        
//        // Create dummy input and output ports
//        LPort dummyInput = new LPort();
//        dummyInput.setSide(PortSide.WEST);
//        dummyInput.setNode(dummyNode);
//        
//        LPort dummyOutput = new LPort();
//        dummyOutput.setSide(PortSide.EAST);
//        dummyOutput.setNode(dummyNode);
//        
//        edge.setTarget(dummyInput);
//        
//        // Create a dummy edge
//        LEdge dummyEdge = new LEdge();
//        dummyEdge.copyProperties(edge);
//        dummyEdge.setSource(dummyOutput);
//        dummyEdge.setTarget(targetPort);
//        
//        setDummyProperties(dummyNode, edge, dummyEdge);
//        
//        // Reset the layer pointer
//        layerIter.previous();
    }
}
