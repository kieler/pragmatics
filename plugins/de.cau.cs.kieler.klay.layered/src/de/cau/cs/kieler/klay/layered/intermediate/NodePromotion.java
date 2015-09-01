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

import java.util.Iterator;
import java.util.List;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
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
     * Contains the number of the layer for each node. For a node with the ID i layer[i] holds
     * the index of the layer to which it is currently assigned.
     */
    private int[] layers;

    /** Precalculated difference between count of incoming and outgoing edges for each node. */
    private int[] degreeDiff;

    /**
     * {@inheritDoc}
     */
    public void process(final LGraph layeredGraph, final IKielerProgressMonitor progressMonitor) {

        progressMonitor.begin("Node promotion heuristic", 1);

        precalculateAndSetInformation(layeredGraph);

        int promotions; // Indicator if there have been promotions applied in a run.
        boolean promotionFlag;
        int promoteUntil = layeredGraph.getProperty(Properties.NODE_PROMOTION_BOUNDARY).intValue();
        int iterationCounter = 0; // Counts the number of iterations over all nodes.
        int[] layeringBackup = layers.clone();

        // Set maximal number of iterations till stopping the node promotion.
        // It depends on the number of nodes in the graph. 0 causes the algorithm to run until no
        // more promotions are made. Other values lie between 1% to 70% of the number of nodes in
        // the graph. Values greater than 70% would lead to the same results like using no boundary.
        if (promoteUntil != 0) {
            promoteUntil = (int) Math.ceil(layers.length * promoteUntil / 100.0);
        }

        do {
            promotions = 0;
            // Start promotion for all nodes with incoming edges.
            for (LNode node : nodesWithIncomingEdges) {
                if (promoteNode(node) < 0) {
                    promotions++;
                    layeringBackup = layers.clone();
                } else {
                    layers = layeringBackup.clone();
                }
            }
            iterationCounter++;
            promotionFlag = promotions != 0; // Abortion criterium of Nikolov et al..
            if (promotionFlag && promoteUntil != 0) {
                // Abortion criterium if a lower boundary for the number of iterations is set.
                promotionFlag = iterationCounter < promoteUntil;
            }
        } while (promotionFlag);

        setNewLayering(layeredGraph);

        progressMonitor.done();
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
        for (Layer layer : layeredGraph.getLayers()) {
            for (LNode node : layer.getNodes()) {
                layers[node.id] = node.getLayer().id;
                int inDegree = Iterables.size(node.getIncomingEdges());
                degreeDiff[node.id] = Iterables.size(node.getOutgoingEdges()) - inDegree;
                if (inDegree > 0) {
                    nodesWithIncomingEdges.add(node);
                }
                nodes.add(node);
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
     * @return The estimated difference of dummy nodes after applying the node promotion. A negative
     *         value indicates a reduction of dummy nodes.
     */
    private int promoteNode(final LNode node) {

        int dummydiff = 0;
        // Calculate index of the layer for the promoted node.
        int nodeNewLayerPos = layers[node.id] + 1;

        // Set new position of the node in the layering by promoting preceding nodes in the above
        // neighboring layer recursively and calculating the difference of dummy nodes.
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
