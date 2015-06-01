/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.intermediate;

import java.util.List;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;

/**
 * Approch of implementing the node-promotion heuristic of Nikola S. Nikolov and Alexandre Tarassov.
 * Goal is to achieve a layering with less dummy nodes. For this purpose the original graph nodes
 * are promoted recursively and the promotion is applied, if and only if this reduces the determined
 * count of dummy nodes.
 * 
 * @author amf
 *
 */
public class NodePromotion implements ILayoutProcessor {

    /* Holds all nodes of the graph that have incoming edges. */
    private List<LNode> nodesWithInEdges;

    /* Stores all nodes of the graph. */
    private List<LNode> nodes;

    /* Contains the layernumber for each node. */
    private int[] layers;

    /* Precalculated difference between count of incoming and outgoing edges for each node. */
    private int[] degreeDiff;

    /**
     * {@inheritDoc}
     */
    public void process(final LGraph layeredGraph, final IKielerProgressMonitor progressMonitor) {

        progressMonitor.begin("Node-promotion heuristic", 1);

        precalculateAndSetInformation(layeredGraph);

        int promotions;
        //
        int[] layeringBackUp = layers.clone();

        // determineNodesWithInEdges(layeredGraph);
        do {
            promotions = 0;
            // Start promotion for all nodes with incoming edges.
            for (LNode nodeWithE : nodesWithInEdges) {
                if (promoteNode(nodeWithE, layeredGraph) < 0) {
                    promotions++;
                    layeringBackUp = layers.clone();
                } else {
                    layers = layeringBackUp.clone();
                }
            }
        } while (promotions != 0);

        setNewLayering(layeredGraph);

        progressMonitor.done();

    }

    /**
     * @param layeredGraph
     */
    private void setNewLayering(LGraph layeredGraph) {

        int max = layers[0];
        for (int in : layers) {
            if (in > max) {
                max = in;
            }
        }

        List<Layer> layLay = Lists.newArrayList();
        for (int i = 0; i <= max; i++) {
            Layer laLaLayer = new Layer(layeredGraph);
            laLaLayer.id = max - i;
            layLay.add(laLaLayer);
        }

        for (LNode node : nodes) {
            node.setLayer(layLay.get(max - layers[node.id]));
        }

        layeredGraph.getLayers().clear();
        layeredGraph.getLayers().addAll(layLay);

    }

    /**
     * Helper method for doing stuff.
     * 
     * @param layeredGraph
     */
    private void precalculateAndSetInformation(final LGraph layeredGraph) {

        // Set IDs for all layers and nodes.
        // Layer IDs are reversed for easier handling in the heuristic.
        int layerID = layeredGraph.getLayers().size() - 1;
        int nodeID = 0;
        for (Layer layer : layeredGraph.getLayers()) {
            layer.id = layerID;
            layerID--;
            for (LNode node : layer.getNodes()) {
                node.id = nodeID;
                nodeID++;
            }
        }

        // fill layers-array with position information of nodes
        layers = new int[nodeID];
        degreeDiff = new int[nodeID];
        nodes = Lists.newArrayList();
        nodesWithInEdges = Lists.newArrayList();

        int inDegree;
        for (Layer layer : layeredGraph.getLayers()) {
            for (LNode node : layer.getNodes()) {
                layers[node.id] = node.getLayer().id;
                inDegree = countEdges(node.getIncomingEdges());
                degreeDiff[node.id] = countEdges(node.getOutgoingEdges()) - inDegree;
                if (inDegree > 0) {
                    nodesWithInEdges.add(node);
                }
                nodes.add(node);
            }
        }

    }

    /**
     * Node-promotion heuristic for doing stuff.
     * 
     * @param node
     * @return
     */
    private int promoteNode(final LNode node, final LGraph layeredGraph) {

        int dummydiff = 0;
        // Calculate layernumber for promoted node.
        int nodeNewLayerPos = layers[node.id] + 1;

        for (LEdge edge : node.getIncomingEdges()) {
            LNode masterNode = edge.getSource().getNode();
            if (layers[masterNode.id] == nodeNewLayerPos) {
                dummydiff = dummydiff + promoteNode(masterNode, layeredGraph);
            }
        }

        layers[node.id] = nodeNewLayerPos;
        dummydiff = dummydiff + degreeDiff[node.id];

        return dummydiff;
    }

    /**
     * Method for determining the size of a given Iterable.
     * 
     * @param incomingEdges
     * @return count of edges
     */
    private int countEdges(final Iterable<LEdge> edges) {
        int count = 0;
        for (LEdge edge : edges) {
            count++;
        }
        return count;
    }

}
