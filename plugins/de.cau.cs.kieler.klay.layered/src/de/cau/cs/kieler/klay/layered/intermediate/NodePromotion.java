/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.intermediate;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * An approach that is implementing the node-promotion heuristic of Nikola S. Nikolov and Alexandre
 * Tarassov.
 * 
 * The algorithm is described in
 * <ul>
 * <li>Nikola S. Nikolov, Alexandre Tarassov, and Jürgen Branke. 2005. In search for efficient
 * heuristics for minimum-width graph layering with consideration of dummy nodes. J. Exp.
 * Algorithmics 10, Article 2.7 (December 2005). DOI=10.1145/1064546.1180618
 * http://doi.acm.org/10.1145/1064546.1180618.</li>
 * </ul>
 * 
 * The goal of the node promotion is to achieve a layering with less dummy nodes. For this purpose
 * the original graph nodes are promoted recursively and the promotion is applied, if and only if
 * this reduces the determined count of dummy nodes. A promotion takes a node and puts it on a
 * higher layer, if there is a predecessor on that layer this node will be promoted as well and so
 * on. After that the effect of the promotion is evaluated, if the number of dummy nodes decreases
 * the promotion is applied.
 * 
 * <ul>
 * <li>{@link Properties#NODE_PROMOTION}: Enables or disables the node promotion.</li>
 * <li>{@link Properties#NODE_PROMOTION_BOUNDARY}: Sets an upper bound for the iterations of the
 * node promotion. Is calculated by a percentage of the number of all nodes. The algorithm stops if
 * this boundary is reached, even if it's still possible to promote nodes.</li>
 * </ul>
 * 
 * @author amf
 */
public class NodePromotion implements ILayoutProcessor {

    /** Holds all nodes of the graph that have incoming edges. */
    private List<LNode> nodesWithIncomingEdges;

    /** Stores all nodes of the graph. */
    private List<LNode> nodes;

    /**
     * Holds for each layer the current number of nodes inside it, where the index in the list
     * stands for the index of the layer.
     */
    private List<Integer> currentWidth;

    /**
     * Contains the number of the layer for each node. For a node with the ID i layer[i] holds the
     * index of the layer to which it is currently assigned.
     */
    private int[] layers;

    /** Precalculated difference between count of incoming and outgoing edges for each node. TODO */
    private int[][] degreeDiff;

    /** Stores the maximum width of the graph before processing. */
    private int maxWidth;

    /** Holds current number of dummy nodes in the graph. */
    private int dummyNodeCount;

    /** TODO . */
    private int maxHeight;

    /** TODO . */
    private boolean widthConsideration;

    /**
     * {@inheritDoc}
     */
    public void process(final LGraph layeredGraph, final IKielerProgressMonitor progressMonitor) {

        progressMonitor.begin("Node promotion heuristic", 1);

        precalculateAndSetInformation(layeredGraph);


        System.out.println("Maximum width: " + maxWidth);
        
        promotionFactory(layeredGraph);            
        
        if (widthConsideration) {
            // && if new max width < maxWidth
            // promotion factory with controlled promotion
        }

        setNewLayering(layeredGraph);

        progressMonitor.done();
    }

    /**
     * 
     */
    private void promotionFactory(LGraph layeredGraph) {
        // TODO Auto-generated method stub
        int promotions; // Indicator if there have been promotions applied in a run.
        boolean promotionFlag;
        int promoteUntil = layeredGraph.getProperty(Properties.NODE_PROMOTION_BOUNDARY).intValue();
        widthConsideration =
                layeredGraph.getProperty(Properties.NODE_PROMOTION_WIDTH_CONSIDERATION)
                        .booleanValue();
//        widthConsideration = true;
        int iterationCounter = 0; // Counts the number of iterations over all nodes.
        int[] layeringBackup = layers.clone();
        int dummyBackup = dummyNodeCount;
        int heightBackup = maxHeight;
        List<Integer> currentWidthBackup = currentWidth;

        // Set maximal number of iterations till stopping the node promotion.
        // It depends on the number of nodes in the graph. 0 causes the algorithm to run until no
        // more promotions are made. Other values lie between 1% to 70% of the number of nodes in
        // the graph. Values greater than 70% would lead to the same results as using no boundary.
        if (promoteUntil != 0) {
            promoteUntil = (int) Math.ceil(layers.length * promoteUntil / 100.0);
        }
        do {
            promotions = 0;
            // Start promotion for all nodes with incoming edges.
            for (LNode node : nodesWithIncomingEdges) {
                Pair<Integer, Boolean> promotionPair = promoteNode(node);
                if (promotionPair.getFirst() < 0 && promotionPair.getSecond()) {
                    promotions++;
                    layeringBackup = layers.clone();
                    dummyBackup = dummyNodeCount;
                    heightBackup = maxHeight;
                    currentWidthBackup = Lists.newArrayList(currentWidth);
                } else {
                    layers = layeringBackup.clone();
                    dummyNodeCount = dummyBackup;
                    currentWidth = Lists.newArrayList(currentWidthBackup);
                    maxHeight = heightBackup;
                }
            }
            iterationCounter++;
            promotionFlag = promotions != 0; // Abortion criterium of Nikolov et al..
            if (promotionFlag && promoteUntil != 0) {
                // Abortion criterium if a lower boundary for the number of iterations is set.
                promotionFlag = iterationCounter < promoteUntil;
            }
        } while (promotionFlag);
        
    }

    /**
     * Helper method for calculating needed information for the heuristic. Sets IDs for layers and
     * nodes to grant an easier access. Also calculates the difference for each node between its
     * incoming and outgoing edges.
     * 
     * @param layeredGraph
     *            The layered graph which is used as source of all needed information.
     */
    private void precalculateAndSetInformation(final LGraph layeredGraph) {

        // Set IDs for all layers and nodes.
        // Layer IDs are reversed for easier handling in the heuristic.
        // Determine maximum width of all layers and fill list of the width of all current layers
        // with information at corresponding position in the list.
        maxHeight = layeredGraph.getLayers().size();
        int layerID = maxHeight - 1;
        int nodeID = 0;
        maxWidth = 0;
        currentWidth = Lists.newArrayList(new Integer[maxHeight]);
        System.out.println("Current width: " + currentWidth);
        for (Layer layer : layeredGraph.getLayers()) {
            layer.id = layerID;
            for (LNode node : layer.getNodes()) {
                node.id = nodeID;
                nodeID++;
            }
            layerID--;
        }

        layers = new int[nodeID];
        degreeDiff = new int[nodeID][3];
        nodes = Lists.newArrayList();
        nodesWithIncomingEdges = Lists.newArrayList();
        int baggage = 0;

        // Calculate difference and determine all nodes with incoming edges.
        for (Layer layer : layeredGraph) {
            layerID = layer.id;
            System.out.println("Layer: " + layerID);
            int incoming = 0;
            int outcoming = 0;
            int layerSize = layer.getNodes().size();
            for (LNode node : layer.getNodes()) {
                nodeID = node.id;
                layers[nodeID] = node.getLayer().id;
                int inDegree = Iterables.size(node.getIncomingEdges());
                int outDegree = Iterables.size(node.getOutgoingEdges());
                degreeDiff[nodeID][0] = outDegree - inDegree;
                degreeDiff[nodeID][1] = inDegree;
                degreeDiff[nodeID][2] = outDegree;
                incoming += inDegree;
                outcoming += outDegree;
                if (inDegree > 0) {
                    nodesWithIncomingEdges.add(node);
                }
                nodes.add(node);
            }
            baggage -= incoming;
            int nodesNdummies = layerSize + baggage;
            currentWidth.set(layerID, nodesNdummies);
            maxWidth = Math.max(maxWidth, nodesNdummies);
            baggage += outcoming;
            System.out.println("Layer     : " + layerID + "\nLayersize : " + layerSize
                    + "\nDummynodes: " + baggage);
        }

        // Calculate number of dummy nodes in the graph.
        dummyNodeCount = 0;
        for (LNode node : nodes) {
            for (LEdge edge : node.getOutgoingEdges()) {
                int layerDistance = node.getLayer().id - edge.getTarget().getNode().getLayer().id;
                if (layerDistance > 1) {
                    dummyNodeCount += layerDistance - 1;
                }
            }
        }
    }

    /**
     * Node-promotion heuristic of the paper. Works on an array of integers which represents the
     * nodes and their position within the layers to avoid difficulties while creating and deleting
     * new layers over the course of the discarded promotions.
     * 
     * @param node
     *            Node that shall be promoted.
     * @return TODO The estimated difference of dummy nodes after applying the node promotion. A
     *         negative value indicates a reduction of dummy nodes.
     */
    private Pair<Integer, Boolean> promoteNode(final LNode node) {
        boolean maxWidthNotExceeded = true;
        int dummydiff = 0;
        int nodeLayerPos = layers[node.id];
        currentWidth.set(nodeLayerPos, currentWidth.get(nodeLayerPos) - 1
                + degreeDiff[nodeLayerPos][2]); // TODO.
        // Calculate index of the layer for the promoted node.
        nodeLayerPos++;
        if (nodeLayerPos >= maxHeight) {
            maxHeight++;
            currentWidth.add(1);
        } else {
            currentWidth.set(nodeLayerPos, currentWidth.get(nodeLayerPos) + 1
                    - degreeDiff[nodeLayerPos][1]); // TODO murks?
        }
        if (currentWidth.get(nodeLayerPos) > maxWidth && widthConsideration) {
            maxWidthNotExceeded = false;
        }

        // Set new position of the node in the layering by promoting preceding nodes in the above
        // neighboring layer recursively and calculating the difference of dummy nodes.
        for (LEdge edge : node.getIncomingEdges()) {
            LNode masterNode = edge.getSource().getNode();
            if (layers[masterNode.id] == nodeLayerPos) {
                Pair<Integer, Boolean> promotion = promoteNode(masterNode);
                dummydiff = dummydiff + promotion.getFirst();
                maxWidthNotExceeded = maxWidthNotExceeded && promotion.getSecond();
            }
        }

        layers[node.id] = nodeLayerPos;
        dummydiff = dummydiff + degreeDiff[node.id][0];

        return new Pair<Integer, Boolean>(dummydiff, maxWidthNotExceeded);
    }

    /**
     * Helper method for setting the calculated and potentially improved layering after the
     * node-promotion heuristic is finished.
     * 
     * @param layeredGraph
     *            The graph to which the calculated new layering is applied.
     */
    private void setNewLayering(final LGraph layeredGraph) {

        // Determine required number of layers.
        int maxLayerCnt = layers[0];
        for (int in : layers) {
            maxLayerCnt = Math.max(maxLayerCnt, in);
        }

        // The laLaLayer, born in the far away laLaLand, traveled here to be of special use for
        // our layering. It is added to the layeredGraph with reversed IDs so they fit to the IDs
        // stored in the nodes but can also be properly assigned compliant with the layering used in
        // KLay Layered.
        List<Layer> layList = Lists.newArrayList();
        for (int i = 0; i <= maxLayerCnt; i++) {
            Layer laLaLayer = new Layer(layeredGraph);
            laLaLayer.id = maxLayerCnt - i;
            layList.add(laLaLayer);
        }

        // Assign all nodes to the beforehand created (laLa)layers.
        for (LNode node : nodes) {
            node.setLayer(layList.get(maxLayerCnt - layers[node.id]));
        }

        // One Loop to exterminate all deceiving layers that don't contain any nodes!
        Iterator<Layer> layerIt = layList.iterator();
        while (layerIt.hasNext()) {
            Layer possiblyEvilLayer = layerIt.next();
            if (possiblyEvilLayer.getNodes().isEmpty()) {
                layerIt.remove();
            }
        }
        layeredGraph.getLayers().clear();
        layeredGraph.getLayers().addAll(layList);
    }
}
