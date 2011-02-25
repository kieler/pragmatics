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

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

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
        Map<Layer, List<LNode>> unassignedNodes = new HashMap<Layer, List<LNode>>();
        
        while (layerIterator.hasNext()) {
            // Find the current, previous and next layer. If this layer is the first or last one,
            // use the prefix or postfix layer as the previous and next layer, respectively.
            Layer previousLayer = currentLayer;
            currentLayer = layerIterator.next();
            Layer nextLayer = null;
            if (layerIterator.hasNext()) {
                nextLayer = layerIterator.next();
                layerIterator.previous();
            } else {
                nextLayer = postfixLayer;
            }
            
            // Create an empty list of unassigned nodes
            List<LNode> currentLayerUnassignedNodes = new LinkedList<LNode>();
            unassignedNodes.put(currentLayer, currentLayerUnassignedNodes);
            
            // Iterate through the layer's nodes (we cannot optimize the nodes.size() call
            // away since the list of nodes is modified whenever dummy nodes are inserted
            // anywhere)
            for (LNode node : currentLayer.getNodes()) {
                // Look for ports on the right side connected to edges originating from lower layers
                for (LPort port : node.getPorts(PortType.INPUT, PortSide.EAST)) {
                    // For every edge connected to this port, insert dummy nodes (do this using
                    // a copy of the current list of edges, since the edges are modified when
                    // dummy nodes are created)
                    List<LEdge> edges = port.getEdges();
                    LEdge[] edgeArray = edges.toArray(new LEdge[edges.size()]);
                    
                    for (LEdge edge : edgeArray) {
                        createEastPortSideDummies(port, edge, currentLayerUnassignedNodes,
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
                        createWestPortSideDummies(port, edge, currentLayerUnassignedNodes,
                                previousLayer);
                    }
                }
            }
        }
        
        // Assign the unasssigned nodes
        assignUnassignedNodes(unassignedNodes);
        
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
        layerNodeList.add(sameLayerDummy);
        
        LPort sameLayerDummyInput = new LPort(PortType.INPUT);
        sameLayerDummyInput.setNode(sameLayerDummy);
        
        LPort sameLayerDummyOutput = new LPort(PortType.OUTPUT);
        sameLayerDummyOutput.setNode(sameLayerDummy);
        
        // Dummy node in the next layer
        LNode nextLayerDummy = new LNode();
        nextLayerDummy.setProperty(Properties.ORIGIN, edge);
        nextLayerDummy.setProperty(Properties.NODE_TYPE,
                Properties.NodeType.ODD_PORT_SIDE);
        nextLayerDummy.setLayer(nextLayer);
        
        LPort nextLayerDummyInput = new LPort(PortType.INPUT);
        nextLayerDummyInput.setNode(nextLayerDummy);
        
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
        layerNodeList.add(sameLayerDummy);
        
        LPort sameLayerDummyInput = new LPort(PortType.INPUT);
        sameLayerDummyInput.setNode(sameLayerDummy);
        
        LPort sameLayerDummyOutput = new LPort(PortType.OUTPUT);
        sameLayerDummyOutput.setNode(sameLayerDummy);
        
        // Dummy node in the previous layer
        LNode previousLayerDummy = new LNode();
        previousLayerDummy.setProperty(Properties.ORIGIN, edge);
        previousLayerDummy.setProperty(Properties.NODE_TYPE,
                Properties.NodeType.ODD_PORT_SIDE);
        previousLayerDummy.setLayer(previousLayer);
        
        LPort previousLayerDummyOutput = new LPort(PortType.OUTPUT);
        previousLayerDummyOutput.setNode(previousLayerDummy);
        
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
    }
    
    /**
     * Goes through the map of layer and assigns any unassigned nodes.
     * 
     * @param unassignedNodes the map mapping layers to unassigned nodes.
     */
    private void assignUnassignedNodes(final Map<Layer, List<LNode>> unassignedNodes) {
        for (Map.Entry<Layer, List<LNode>> entry : unassignedNodes.entrySet()) {
            Layer layer = entry.getKey();
            for (LNode node : entry.getValue()) {
                node.setLayer(layer);
            }
        }
    }

}
