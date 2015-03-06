/*
a * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
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
package de.cau.cs.kieler.klay.layered.test.intermediate;

import static org.junit.Assert.assertEquals; // SUPPRESS CHECKSTYLE AvoidStarImport

import java.util.Set;

import org.junit.Test;

import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.KlayLayered;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.intermediate.LabelAndNodeSizeProcessor;
import de.cau.cs.kieler.klay.layered.properties.GraphProperties;
import de.cau.cs.kieler.klay.layered.properties.InternalProperties;

/**
 * Tests whether the port offset is correctly applied to ports of all sides on nodes with all port
 * constraints. The test graph always consists of a node with one port on each side whose position is
 * somewhat away from that side and whose offset is set to zero. The result should always be that all
 * ports touch the border of the node.
 * 
 * @author cds
 */
public class LabelAndNodeSizeProcessorOffsetTest {
    
    /**
     * Runs the test.
     */
    @Test
    public void testPortPositionsWithOffsetZero() {
        LGraph graph = generateGraph();
        
        KlayLayered algorithm = new KlayLayered();
        KlayLayered.TestExecutionState state = algorithm.prepareLayoutTest(graph);
        algorithm.runLayoutTestUntil(LabelAndNodeSizeProcessor.class, state);
        
        for (LGraph resultGraph : state.getGraphs()) {
            doTestPortPositions(resultGraph);
        }
    }
    
    
    /**
     * Tests if the ports of the given graph really touch the border of their node.
     * 
     * @param graph the graph to test.
     */
    private void doTestPortPositions(final LGraph graph) {
        for (Layer layer : graph) {
            for (LNode node : layer) {
                for (LPort port : node.getPorts()) {
                    switch (port.getSide()) {
                    case NORTH:
                        assertEquals(
                                generateErrorMessage(port),
                                -port.getSize().y,
                                port.getPosition().y,
                                0.0);
                        break;
                        
                    case SOUTH:
                        assertEquals(
                                generateErrorMessage(port),
                                node.getSize().y,
                                port.getPosition().y,
                                0.0);
                        break;
                        
                    case EAST:
                        assertEquals(
                                generateErrorMessage(port),
                                node.getSize().x,
                                port.getPosition().x,
                                0.0);
                        break;
                        
                    case WEST:
                        assertEquals(
                                generateErrorMessage(port),
                                -port.getSize().x,
                                port.getPosition().x,
                                0.0);
                        break;
                    }
                }
            }
        }
    }
    
    /**
     * Creates a new layered graph containing a node for each port constraints configuration and four
     * ports each.
     * 
     * @return the layered graph.
     */
    private LGraph generateGraph() {
        LGraph graph = new LGraph();
        
        Set<GraphProperties> graphProperties = graph.getProperty(InternalProperties.GRAPH_PROPERTIES);
        graphProperties.add(GraphProperties.NON_FREE_PORTS);
        graphProperties.add(GraphProperties.NORTH_SOUTH_PORTS);
        
        generateNode(graph, PortConstraints.FREE);
        generateNode(graph, PortConstraints.FIXED_SIDE);
        generateNode(graph, PortConstraints.FIXED_ORDER);
        generateNode(graph, PortConstraints.FIXED_RATIO);
        generateNode(graph, PortConstraints.FIXED_POS);
        
        return graph;
        
    }

    /**
     * Generates a node for the given graph with one port on each side and with its port constraints
     * set to the given value.
     * 
     * @param graph the graph to add the node to.
     * @param portConstraints the node's port constraints.
     */
    private void generateNode(final LGraph graph, final PortConstraints portConstraints) {
        // We need to set coordinates and don't want to add constants for them all
        // CHECKSTYLEOFF MagicNumber
        
        // Create the node
        LNode node = new LNode(graph);
        graph.getLayerlessNodes().add(node);
        node.setProperty(LayoutOptions.PORT_CONSTRAINTS, portConstraints);
        node.getSize().x = 100;
        node.getSize().y = 100;
        
        // Create ports
        LPort nPort = new LPort();
        nPort.setNode(node);
        nPort.setProperty(LayoutOptions.OFFSET, 0.0F);
        nPort.setSide(PortSide.NORTH);
        nPort.getSize().x = 5;
        nPort.getSize().y = 5;
        nPort.getPosition().x = 50;
        nPort.getPosition().y = -300;

        LPort sPort = new LPort();
        sPort.setNode(node);
        sPort.setProperty(LayoutOptions.OFFSET, 0.0F);
        sPort.setSide(PortSide.SOUTH);
        sPort.getSize().x = 5;
        sPort.getSize().y = 5;
        sPort.getPosition().x = 50;
        sPort.getPosition().y = 300;

        LPort ePort = new LPort();
        ePort.setNode(node);
        ePort.setProperty(LayoutOptions.OFFSET, 0.0F);
        ePort.setSide(PortSide.EAST);
        ePort.getSize().x = 5;
        ePort.getSize().y = 5;
        ePort.getPosition().x = 300;
        ePort.getPosition().y = 50;

        LPort wPort = new LPort();
        wPort.setNode(node);
        wPort.setProperty(LayoutOptions.OFFSET, 0.0F);
        wPort.setSide(PortSide.EAST);
        wPort.getSize().x = 5;
        wPort.getSize().y = 5;
        wPort.getPosition().x = -300;
        wPort.getPosition().y = 50;

        // CHECKSTYLEON MagicNumber
    }
    
    /**
     * Generates a meaningful error message in case the given port doesn't touch its parent node's
     * border.
     * 
     * @param port the port.
     * @return a meaningful error message.
     */
    private String generateErrorMessage(final LPort port) {
        return "Port (" + port.getSide() + ") does not touch border of node with port constraints "
                + port.getNode().getProperty(LayoutOptions.PORT_CONSTRAINTS);
    }
    
}
