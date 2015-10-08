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

import java.util.Iterator;
import java.util.List;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;

/**
 * Approach of implementing the node-promotion heuristic of Nikola S. Nikolov and Alexandre Tarassov.
 * Goal is to achieve a layering with less dummy nodes. For this purpose the original graph nodes
 * are promoted recursively and the promotion is applied, if and only if this reduces the determined
 * count of dummy nodes.
 * 
 * @author amf
 *
 */
public class NodePromotion implements ILayoutProcessor {

    /* Holds all nodes of the graph that have incoming edges. */
    private List<LNode> nodesWithIncomingEdges;

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

        progressMonitor.begin("Node promotion heuristic", 1);

        precalculateAndSetInformation(layeredGraph);

        int promotions;

        int[] layeringBackUp = layers.clone();

        do {
            promotions = 0;
            // Start promotion for all nodes with incoming edges.
            for (LNode node : nodesWithIncomingEdges) {
                if (promoteNode(node) < 0) {
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
     * Helper method for calculating needed information for the heuristic. 
     * Sets ID's for layers and nodes to grant an easier access. 
     * Also calculates the difference for each node between its incoming and outgoing edges.
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

        layers = new int[nodeID];
        degreeDiff = new int[nodeID];
        nodes = Lists.newArrayList();
        nodesWithIncomingEdges = Lists.newArrayList();

        // Calculate difference and determine all nodes with incoming edges.
        int inDegree;
        for (Layer layer : layeredGraph.getLayers()) {
            for (LNode node : layer.getNodes()) {
                layers[node.id] = node.getLayer().id;
                inDegree = countEdges(node.getIncomingEdges());
                degreeDiff[node.id] = countEdges(node.getOutgoingEdges()) - inDegree;
                if (inDegree > 0) {
                    nodesWithIncomingEdges.add(node);
                }
                nodes.add(node);
            }
        }

    }

    /**
     * Helper method for setting the calculated and potentially improved layering after the
     * node-promotion-heuristic is finished.
     * 
     * @param layeredGraph
     */
    private void setNewLayering(final LGraph layeredGraph) {

        // Determine required amount of layers.
        int maxLayerCnt = layers[0];
        for (int in : layers) {
            if (in > maxLayerCnt) {
                maxLayerCnt = in;
            }
        }

        List<Layer> layList = Lists.newArrayList();
        for (int i = 0; i <= maxLayerCnt; i++) {
            Layer laLaLayer = new Layer(layeredGraph);
            laLaLayer.id = maxLayerCnt - i;
            layList.add(laLaLayer);
        }

        for (LNode node : nodes) {
            node.setLayer(layList.get(maxLayerCnt - layers[node.id]));
        }

        Iterator<Layer> variable = layList.iterator();
        while (variable.hasNext()) {
            Layer irgendwas = variable.next();
            if (irgendwas.getNodes().isEmpty()) {
                variable.remove();
            }
        }
        layeredGraph.getLayers().clear();
        layeredGraph.getLayers().addAll(layList);

        
    }

    /**
     * Node-promotion heuristic of the paper. Works on an array of integers which represents the
     * nodes and their position on the layers to avoid difficulties while creating and deleting new
     * layers over the course of the discarded promotions.
     * 
     * @param node
     *            that shall be promoted.
     * @return
     */
    private int promoteNode(final LNode node) {

        int dummydiff = 0;
        // Calculate layernumber for promoted node.
        int nodeNewLayerPos = layers[node.id] + 1;

        // Set new layering of the node by promoting preceding nodes in the above neighboring layer
        // recursively and calculating the difference of dummy nodes.
        for (LEdge edge : node.getIncomingEdges()) {
            LNode masterNode = edge.getSource().getNode();
            if (layers[masterNode.id] == nodeNewLayerPos) {
                dummydiff = dummydiff + promoteNode(masterNode);
            }
        }

        layers[node.id] = nodeNewLayerPos;
        dummydiff = dummydiff + degreeDiff[node.id];

        return dummydiff;
    }

    /**
     * Method for determining the size of a given Iterable of LEdges.
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
