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

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.kiml.options.PortType;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.Properties;
import de.cau.cs.kieler.klay.layered.Properties.NodeType;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;

/**
 * Inserts dummy nodes to cope with odd port sides.
 * 
 * <p>
 * The problem is with edges coming from the left of a node being connected to a
 * port that's on its right side, or the other way around. Let a node of that kind
 * be in layer {@code i}. This processor now takes the offending edge and connects
 * it to a new dummy node, also in layer {@code i}. That dummy node is then connected
 * to another new dummy node, in layer {@code i + 1}. Finally, a new edge connects
 * the offending port with the dummy node in layer {@code i + 1}.
 * 
 * <p>
 * The dummy nodes are decorated with a {@link de.cau.cs.kieler.klay.layered.Properties#NODE_TYPE}
 * property, its value being set to
 * {@link de.cau.cs.kieler.klay.layered.Properties.NodeType#ODD_PORT_SIDE}.
 * 
 * <dl>
 *   <dt>Precondition:</dt><dd>a layered graph.</dd>
 *   <dt>Postcondition:</dt><dd>dummy nodes have been inserted for edges connected to
 *     ports on odd sides.</dd>
 * </dl>
 * 
 * @author cds
 */
public class OddPortSideProcessor extends AbstractAlgorithm implements ILayoutProcessor {

    /**
     * {@inheritDoc}
     */
    public void process(final LayeredGraph layeredGraph) {
        getMonitor().begin("Odd port side processing", 1);
        
        // Retrieve the layers in the graph
        List<Layer> layers = layeredGraph.getLayers();
        
        // We may need a new first and last layer
        Layer prefixLayer = new Layer(layeredGraph);
        Layer postfixLayer = new Layer(layeredGraph);
        
        // Iterate through the layers and for each layer create a list of dummy nodes
        // that were created, but not yet assigned to the layer (to avoid concurrent
        // modification exceptions)
        ListIterator<Layer> layerIterator = layers.listIterator();
        Layer currentLayer = prefixLayer;
        List<LNode> previousLayerUnassignedNodes = new LinkedList<LNode>();
        
        while (layerIterator.hasNext()) {
            // Find the current, previous and next layer. If this layer is the last one,
            // use the postfix layer as the next layer
            Layer previousLayer = currentLayer;
            currentLayer = layerIterator.next();
            Layer nextLayer = postfixLayer;
            if (layerIterator.hasNext()) {
                nextLayer = layerIterator.next();
                layerIterator.previous();
            }
            
            // If the last layer had unassigned nodes, assign them now and clear the list
            for (LNode node : previousLayerUnassignedNodes) {
                node.setLayer(previousLayer);
            }
            previousLayerUnassignedNodes.clear();
            
            // Iterate through the layer's nodes
            for (LNode node : currentLayer.getNodes()) {
                // Skip dummy nodes
                if (!node.getProperty(Properties.NODE_TYPE).equals(NodeType.NORMAL)) {
                    continue;
                }
                
                // Look for input ports on the right side
                for (LPort port : node.getPorts(PortType.INPUT, PortSide.EAST)) {
                    // For every edge connected to this port, insert dummy nodes (do this using
                    // a copy of the current list of edges, since the edges are modified when
                    // dummy nodes are created)
                    List<LEdge> edges = port.getEdges();
                    LEdge[] edgeArray = edges.toArray(new LEdge[edges.size()]);
                    
                    for (LEdge edge : edgeArray) {
                        createEastPortSideDummies(port, edge, previousLayerUnassignedNodes,
                                nextLayer);
                    }
                }
                
                // Look for ports on the left side connected to edges going to higher layers
                for (LPort port : node.getPorts(PortType.OUTPUT, PortSide.WEST)) {
                    // For every edge connected to this port, insert dummy nodes (do this using
                    // a copy of the current list of edges, since the edges are modified when
                    // dummy nodes are created)
                    List<LEdge> edges = port.getEdges();
                    LEdge[] edgeArray = edges.toArray(new LEdge[edges.size()]);
                    
                    for (LEdge edge : edgeArray) {
                        createWestPortSideDummies(port, edge, previousLayerUnassignedNodes,
                                previousLayer);
                    }
                }
            }
        }
        
        // Add the prefix and postfix layers to the graph, if necessary
        if (!prefixLayer.getNodes().isEmpty()) {
            layers.add(0, prefixLayer);
        }
        
        if (!postfixLayer.getNodes().isEmpty()) {
            layers.add(postfixLayer);
        }
        
        
        getMonitor().done();
    }

    /**
     * Creates the necessary dummy nodes for an input port on the east side of a node.
     * 
     * @param eastwardPort the offending port.
     * @param edge the edge connected to the port.
     * @param layerNodeList list of unassigned nodes belonging to the layer of the node the
     *                      port belongs to. The new dummy node is added to this list and
     *                      must be assigned to the layer later.
     * @param nextLayer the next layer.
     */
    private void createEastPortSideDummies(final LPort eastwardPort, final LEdge edge,
            final List<LNode> layerNodeList, final Layer nextLayer) {
        
        // Dummy node in the same layer
        LNode sameLayerDummy = new LNode();
        sameLayerDummy.setProperty(Properties.ORIGIN, edge);
        sameLayerDummy.setProperty(Properties.NODE_TYPE,
                Properties.NodeType.ODD_PORT_SIDE);
        sameLayerDummy.setProperty(LayoutOptions.PORT_CONSTRAINTS, PortConstraints.FIXED_POS);
        layerNodeList.add(sameLayerDummy);
        
        LPort sameLayerDummyInput = new LPort(PortType.INPUT);
        sameLayerDummyInput.setNode(sameLayerDummy);
        sameLayerDummyInput.setSide(PortSide.WEST);
        
        LPort sameLayerDummyOutput = new LPort(PortType.OUTPUT);
        sameLayerDummyOutput.setNode(sameLayerDummy);
        sameLayerDummyOutput.setSide(PortSide.EAST);
        
        // Dummy node in the next layer
        LNode nextLayerDummy = new LNode();
        nextLayerDummy.setProperty(Properties.ORIGIN, edge);
        nextLayerDummy.setProperty(Properties.NODE_TYPE,
                Properties.NodeType.ODD_PORT_SIDE);
        sameLayerDummy.setProperty(LayoutOptions.PORT_CONSTRAINTS, PortConstraints.FIXED_POS);
        nextLayerDummy.setLayer(nextLayer);
        
        LPort nextLayerDummyInput = new LPort(PortType.INPUT);
        nextLayerDummyInput.setNode(nextLayerDummy);
        nextLayerDummyInput.setSide(PortSide.WEST);
        
        // Reroute the original edge
        edge.setTarget(sameLayerDummyInput);
        
        // Create two new edges to connect the two dummies
        LEdge dummyEdge = new LEdge();
        dummyEdge.copyProperties(edge);
        dummyEdge.setSource(sameLayerDummyOutput);
        dummyEdge.setTarget(nextLayerDummyInput);
        
        dummyEdge = new LEdge();
        dummyEdge.copyProperties(edge);
        dummyEdge.setSource(eastwardPort);
        dummyEdge.setTarget(nextLayerDummyInput);
        
        // Reconfigure the port
        eastwardPort.setType(PortType.OUTPUT);
        eastwardPort.setProperty(Properties.REVERSED, true);
    }

    /**
     * Creates the necessary dummy nodes for an output port on the west side of a node.
     * 
     * @param westwardPort the offending port.
     * @param edge the edge connected to the port.
     * @param layerNodeList list of unassigned nodes belonging to the layer of the node the
     *                      port belongs to. The new dummy node is added to this list and
     *                      must be assigned to the layer later.
     * @param previousLayer the previous layer.
     */
    private void createWestPortSideDummies(final LPort westwardPort, final LEdge edge,
            final List<LNode> layerNodeList, final Layer previousLayer) {
        
        // Dummy node in the same layer
        LNode sameLayerDummy = new LNode();
        sameLayerDummy.setProperty(Properties.ORIGIN, edge);
        sameLayerDummy.setProperty(Properties.NODE_TYPE,
                Properties.NodeType.ODD_PORT_SIDE);
        sameLayerDummy.setProperty(LayoutOptions.PORT_CONSTRAINTS, PortConstraints.FIXED_POS);
        layerNodeList.add(sameLayerDummy);
        
        LPort sameLayerDummyInput = new LPort(PortType.INPUT);
        sameLayerDummyInput.setNode(sameLayerDummy);
        sameLayerDummyInput.setSide(PortSide.WEST);
        
        LPort sameLayerDummyOutput = new LPort(PortType.OUTPUT);
        sameLayerDummyOutput.setNode(sameLayerDummy);
        sameLayerDummyInput.setSide(PortSide.EAST);
        
        // Dummy node in the previous layer
        LNode previousLayerDummy = new LNode();
        previousLayerDummy.setProperty(Properties.ORIGIN, edge);
        previousLayerDummy.setProperty(Properties.NODE_TYPE,
                Properties.NodeType.ODD_PORT_SIDE);
        sameLayerDummy.setProperty(LayoutOptions.PORT_CONSTRAINTS, PortConstraints.FIXED_POS);
        previousLayerDummy.setLayer(previousLayer);
        
        LPort previousLayerDummyOutput = new LPort(PortType.OUTPUT);
        previousLayerDummyOutput.setNode(previousLayerDummy);
        previousLayerDummyOutput.setSide(PortSide.EAST);
        
        // Reroute the original edge
        edge.setSource(sameLayerDummyOutput);
        
        // Create two new edges to connect the two dummies
        LEdge dummyEdge = new LEdge();
        dummyEdge.copyProperties(edge);
        dummyEdge.setSource(previousLayerDummyOutput);
        dummyEdge.setTarget(westwardPort);
        
        dummyEdge = new LEdge();
        dummyEdge.copyProperties(edge);
        dummyEdge.setSource(previousLayerDummyOutput);
        dummyEdge.setTarget(sameLayerDummyInput);
        
        // Reconfigure the port
        westwardPort.setType(PortType.INPUT);
        westwardPort.setProperty(Properties.REVERSED, true);
    }

}
