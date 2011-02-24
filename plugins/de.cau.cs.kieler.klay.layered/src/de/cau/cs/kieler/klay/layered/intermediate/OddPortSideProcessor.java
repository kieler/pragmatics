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

import java.util.List;

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
 *   <dt>Postcondition:</dt><dd>dummy nodes have been insertes for edges connected to
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
        int layerCount = layers.size();
        
        // We may need a new first and last layer
        Layer prefixLayer = new Layer(layeredGraph);
        Layer postfixLayer = new Layer(layeredGraph);
        
        // Iterate through the layers
        for (int layerIndex = 0; layerIndex < layerCount; layerIndex++) {
            // Find the current, previous and next layer. If this layer is the first or last one,
            // use the prefix or postfix layer as the previous and next layer, respectively.
            Layer layer = layers.get(layerIndex);
            Layer previousLayer = (layerIndex > 0)
                ? layers.get(layerIndex - 1)
                : prefixLayer;
            Layer nextLayer = (layerIndex < layerCount - 1)
                ? layers.get(layerIndex + 1)
                : postfixLayer;
            
            // Iterate through the layer's nodes (we cannot optimize the nodes.size() call
            // away since the list of nodes is modified whenever dummy nodes are inserted
            // anywhere)
            List<LNode> nodes = layer.getNodes();
            for (int nodeIndex = 0; nodeIndex < nodes.size(); nodeIndex++) {
                LNode node = nodes.get(nodeIndex);
                
                // Look for ports on the right side connected to edges originating from lower layers
                for (LPort port : node.getPorts(PortType.INPUT, PortSide.EAST)) {
                    // For every edge connected to this port, insert dummy nodes (do this using
                    // a copy of the current list of edges, since the edges are modified when
                    // dummy nodes are created)
                    List<LEdge> edges = port.getEdges();
                    LEdge[] edgeArray = edges.toArray(new LEdge[edges.size()]);
                    
                    for (LEdge edge : edgeArray) {
                        createEastPortSideDummies(port, edge, layer, nextLayer);
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
                        createWestPortSideDummies(port, edge, layer, previousLayer);
                    }
                }
            }
        }
        
        // Add the prefix and postfix layers to the graph, if necessary
        if (!prefixLayer.getNodes().isEmpty()) {
            layeredGraph.getLayers().add(0, prefixLayer);
        }
        
        if (!postfixLayer.getNodes().isEmpty()) {
            layeredGraph.getLayers().add(postfixLayer);
        }
        
        
        getMonitor().done();
    }

    /**
     * Creates the necessary dummy nodes for an input port on the east side of a node.
     * 
     * @param eastwardPort the offending port.
     * @param edge the edge connected to the port.
     * @param layer the layer of the node the port belongs to.
     * @param nextLayer the next layer.
     */
    private void createEastPortSideDummies(final LPort eastwardPort, final LEdge edge,
            final Layer layer, final Layer nextLayer) {
        
        // Dummy node in the same layer
        LNode sameLayerDummy = new LNode();
        sameLayerDummy.setProperty(Properties.ORIGIN, edge);
        sameLayerDummy.setProperty(Properties.NODE_TYPE,
                Properties.NodeType.ODD_PORT_SIDE);
        sameLayerDummy.setLayer(layer);
        
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
     * @param layer the layer of the node the port belongs to.
     * @param previousLayer the previous layer.
     */
    private void createWestPortSideDummies(final LPort westwardPort, final LEdge edge,
            final Layer layer, final Layer previousLayer) {
        
        // Dummy node in the same layer
        LNode sameLayerDummy = new LNode();
        sameLayerDummy.setProperty(Properties.ORIGIN, edge);
        sameLayerDummy.setProperty(Properties.NODE_TYPE,
                Properties.NodeType.ODD_PORT_SIDE);
        sameLayerDummy.setLayer(layer);
        
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

}
