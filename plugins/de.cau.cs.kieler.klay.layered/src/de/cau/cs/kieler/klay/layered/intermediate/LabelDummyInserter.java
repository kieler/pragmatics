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
import de.cau.cs.kieler.kiml.options.EdgeLabelPlacement;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LLabel;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.properties.NodeType;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * 
 * @author jjc
 */
public class LabelDummyInserter extends AbstractAlgorithm implements ILayoutProcessor {

    /**
     * {@inheritDoc}
     */
    public void process(final LGraph layeredGraph) {
        getMonitor().begin("Label dummy insertions", 1);
        List<LNode> newDummyNodes = new LinkedList<LNode>();

        for (LNode node : layeredGraph.getLayerlessNodes()) {
            for (LPort port : node.getPorts()) {
                for (LEdge edge : port.getOutgoingEdges()) {
                    for (LLabel label : edge.getLabels()) {
                        if (label.getProperty(LayoutOptions.EDGE_LABEL_PLACEMENT)
                                == EdgeLabelPlacement.CENTER) {
                            LPort targetPort = edge.getTarget();
                            
                            // Create dummy node
                            LNode dummyNode = new LNode();
                            dummyNode.setProperty(Properties.ORIGIN, label);
                            dummyNode.setProperty(Properties.NODE_TYPE, NodeType.LABEL);
                            dummyNode.setProperty(LayoutOptions.PORT_CONSTRAINTS,
                                    PortConstraints.FIXED_POS);
                            dummyNode.setProperty(Properties.LONG_EDGE_SOURCE, edge.getSource());
                            dummyNode.setProperty(Properties.LONG_EDGE_TARGET, edge.getTarget());
                            
                            // Set values of dummy node
                            dummyNode.getSize().x = label.getSize().x;                           
                            dummyNode.getSize().y = label.getSize().y;
                            
                            // Create dummy ports
                            LPort dummyInput = new LPort();
                            dummyInput.setSide(PortSide.WEST);
                            dummyInput.setNode(dummyNode);
                            
                            LPort dummyOutput = new LPort();
                            dummyOutput.setSide(PortSide.EAST);
                            dummyOutput.setNode(dummyNode);
                            
                            edge.setTarget(dummyInput);
                            
                            // Create dummy edge
                            LEdge dummyEdge = new LEdge();
                            dummyEdge.copyProperties(edge);
                            dummyEdge.setSource(dummyOutput);
                            dummyEdge.setTarget(targetPort);
                            
                            // Remember created dummies
                            newDummyNodes.add(dummyNode);
                        }
                    }
                }
            }
        }

        // Add created dummies to graph
        layeredGraph.getLayerlessNodes().addAll(newDummyNodes);
        
        getMonitor().done();
    }

}
