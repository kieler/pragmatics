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
package de.cau.cs.kieler.klay.layered.p2layers;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.klay.layered.ILayoutPhase;
import de.cau.cs.kieler.klay.layered.IntermediateProcessingConfiguration;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.intermediate.IntermediateProcessorStrategy;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * StretchWidth algorithm described in In Search for Efficient Heuristics for Minimum-Width Graph
 * Layering with Consideration of Dummy Nodes written by Nikolas S. Nikolov, Alexandre Tarassov, and
 * Jürgen Branke
 * 
 * The criterion for going to the next layer was adapted to
 * 
 * "widthUp > maxWidth * upperLayerInfluence"
 * 
 * where upperLayerInfluence is set between 0 and 1 1 will result in the {@link LongestPathLayerer}
 * 0 will result in a layerer very similar to the one-node-layerer.
 * 
 * 
 * Precondition: the graph has no cycles, but might contain self-loops</dd> Postcondition: all nodes
 * have been assigned a layer such that edges connect only nodes from layers with increasing indices
 * 
 * 
 * @author mad
 */
public class StretchWidthLayerer implements ILayoutPhase {
    // indicates the width of the currently built layer
    private int widthCurrent = 0;
    // estimated width of the next layer
    private int widthUp = 0;
    // indicates the width of the layering
    // Will be increased dynamically increased if the layering fails for lower values
    // The Pseudo codes says initialize with 0 but I think you can start with 1 or maybe
    // higher/average
    // out-degree
    private int maxWidth = 0;
    // The graph the layering is done for
    private LGraph currentGraph;
    // out-degree which determines the bounding with widthUp and maxWidth
    private double upperLayerInfluence;
    // sorted list of layerless nodes
    private List<LNode> sortedLayerlessNodes;
    // Set of nodes placed in the current layer
    private Set<LNode> alreadyPlacedNodes = Sets.newHashSet();
    // Set of nodes in all layers except the current
    private Set<LNode> alreadyPlacedInOtherLayers = Sets.newHashSet();
    // list of layerless nodes to be used in one layering approach, will be sorted after
    // initialization
    private List<LNode> tempLayerlessNodes;
    // List of sets of successors for every node
    // can be accessed with successors.get(node.id)
    private List<Set<LNode>> successors;
    // array of out-degrees for every node
    // access with outDegree[node.id]
    private int[] outDegree;
    // array of in-degrees for every node
    // access with inDegree[node.id]
    private int[] inDegree;
    // selected node to be placed
    private LNode selectedNode;
    // indicator if the original Algorithm or the adaption should be used
    private Boolean originalAlg = false;

    /**
     * {@inheritDoc}
     */

    public IntermediateProcessingConfiguration getIntermediateProcessingConfiguration(
            final LGraph graph) {
        return IntermediateProcessingConfiguration
                .createEmpty()
                .addBeforePhase1(
                        IntermediateProcessorStrategy.EDGE_AND_LAYER_CONSTRAINT_EDGE_REVERSER)
                .addBeforePhase3(IntermediateProcessorStrategy.LAYER_CONSTRAINT_PROCESSOR);
    }

    /**
     * {@inheritDoc}
     */
    public void process(final LGraph layeredGraph, final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("StretchWidth", 1);

        // set graph
        currentGraph = layeredGraph;
        // reset variables
        widthCurrent = 0;
        widthUp = 0;
        maxWidth = 1;
        // Layer currently worked on
        Layer currentLayer = new Layer(currentGraph);
        // Should the algorithm go up to next the layer
        currentGraph.getLayers().add(currentLayer);
        // works between 0 and 1 (1 = longest path / 0 = one node per layer except for layers with
        // nodes without incoming edges)
        // 0= widthUp > maxWidth * 0 -> only false for the first node and once every remaining node
        // has no incoming edges
        upperLayerInfluence = currentGraph.getProperty(Properties.UPPER_LAYER_SCALE).doubleValue();
        // -1 is used to indicate that the original algorithm should be used
        if (upperLayerInfluence == -1) {
            originalAlg = true;
            upperLayerInfluence = getAverageOutDegree();

        }

        // sort the nodes at beginning, since the rank will not change
        // since it uses the node.id field it has to be computed first
        computeSortedNodes();
        // compute a list with the successors of every node
        // can't be swapped with the computation of the out- and in-degrees
        computeSuccessors();
        // compute two arrays of out- and in-degree for every node
        computeDegrees();
        // copy the sorted layerless nodes so we don't overwrite it for the reset case
        tempLayerlessNodes = Lists.newArrayList(sortedLayerlessNodes);
        // variable to compute the difference of u and z

        while (!tempLayerlessNodes.isEmpty()) {
            // select a node to be placed
            selectedNode = selectNode();
            // The pseudo-code computes u\z but since u /alreadyPlacedNodes will be cleared here,
            // it's enough to check if alreadyPlacedNodes is empty
            // if it is empty it would indicate an empty layer
            if (selectedNode == null || (conditionGoUp() && !alreadyPlacedNodes.isEmpty())) {
                // go to the next layer //
                currentLayer = new Layer(currentGraph);
                currentGraph.getLayers().add(currentLayer);
                // union of z and u in z
                alreadyPlacedInOtherLayers.addAll(alreadyPlacedNodes);
                alreadyPlacedNodes.clear();
                // change width
                widthCurrent = widthUp;
                widthUp = 0;
            } else {
                if (conditionGoUp()) {
                    // reset layering //
                    // clear the placed layers
                    currentGraph.getLayers().clear();
                    // create the new first layer;
                    currentLayer = new Layer(currentGraph);
                    currentGraph.getLayers().add(currentLayer);
                    // reset variables
                    widthCurrent = 0;
                    widthUp = 0;
                    alreadyPlacedNodes.clear();
                    alreadyPlacedInOtherLayers.clear();
                    // increase maxWidth
                    maxWidth++;
                    // reset layerless nodes
                    tempLayerlessNodes = Lists.newArrayList(sortedLayerlessNodes);

                } else {
                    // add node to current layer //
                    selectedNode.setLayer(currentLayer);
                    tempLayerlessNodes.remove(selectedNode);
                    alreadyPlacedNodes.add(selectedNode);
                    // compute new widthCurrent and widthUp
                    widthCurrent = widthCurrent - outDegree[selectedNode.id] + 1;
                    widthUp = widthUp + inDegree[selectedNode.id];
                }
            }
        }

        // layering done, delete original layerlessNodes
        layeredGraph.getLayerlessNodes().clear();
        // Algorithm is Bottom-Up -> reverse Layers
        Collections.reverse(layeredGraph.getLayers());

        progressMonitor.done();
    }

    /**
     * Checks the effects of the hypothetical
     * placement of the selected node and if the algorithm should
     *  rather go up, then placing the node
     *     
     * @return true, if the algorithm should go to the next layer
     */
    private Boolean conditionGoUp() {
        return ((widthCurrent - outDegree[selectedNode.id] + 1) > maxWidth 
                || ((widthUp + inDegree[selectedNode.id]) > (maxWidth * upperLayerInfluence)));        
        }    


    /**
     * Selects a node from the sorted list of layerless nodes. The selection is done according to
     * the rank of the node and only if all of its successors are already in z (the set of layered
     * nodes already placed in the other layers).
     * 
     * @return node to be placed in the current layer
     */
    private LNode selectNode() {
        for (LNode node : tempLayerlessNodes) {
            // If all successors of node are in the alreadyPlacedInOtherLayers set, choose this
            // node, since the list is sorted by rank
            if (alreadyPlacedInOtherLayers.containsAll(successors.get(node.id))) {
                return node;
            }

        }
        // If no candidate was found return null
        return null;
    }

    /**
     * Sorts a list of nodes after its rank. The rank is defined as max(d⁺(v),max(d⁺(u):(u,v)∈ E)).
     * // since the id-field is reused later on, this function should be executed first
     */
    private void computeSortedNodes() {
        List<LNode> unsortedNodes = currentGraph.getLayerlessNodes();
        sortedLayerlessNodes = Lists.newArrayList(unsortedNodes);
        // the id-field is reused later on, be careful
        for (LNode node : unsortedNodes) {
            node.id = getRank(node);
        }
        // sorting the list by the node-id /rank
        Collections.sort(sortedLayerlessNodes, new Comparator<LNode>() {
            public int compare(final LNode o1, final LNode o2) {
                // descending sort
                if (o1.id < o2.id) {
                    return 1;
                } else if (o1.id > o2.id) {
                    return -1;
                }
                return 0;
            }
        });

    }

    /**
     * Computes the rank of a node. The rank is defined as max(d⁺(v),max(d⁺(u):(u,v)∈ E)).
     * 
     * @param node
     *            node to compute the rank for
     * @return rank of the node
     */
    private Integer getRank(final LNode node) {
        int max = getOutDegree(node);
        int temp;
        LNode pre;

        // compute max of predecessors out-degree and out-degree of the current node
        for (LEdge preEdge : node.getIncomingEdges()) {
            pre = preEdge.getSource().getNode();
            temp = getOutDegree(pre);
            max = Math.max(max, temp);
        }

        return max;
    }

    /**
     * Computes the successors of every node and saves them in a list of sets of nodes. This list is
     * used in selectNode. Needs to be executed after computeSortedNodes
     */
    private void computeSuccessors() {
        int i = 0;
        successors = Lists.newArrayList();
        Set<LNode> currSucc = Sets.newHashSet();

        for (LNode node : sortedLayerlessNodes) {
            node.id = i;
            for (LEdge edge : node.getOutgoingEdges()) {
                currSucc.add(edge.getTarget().getNode());
            }
            // remove current node from accSucc to deal with self-loops
            currSucc.remove(node);
            successors.add(Sets.newHashSet(currSucc));
            currSucc.clear();
            i++;

        }

    }

    /**
     * Computes the in- and out-degree of every node. the values are used to determine widthUp and
     * widthCurrent. Needs to be executed after computeSuccessors()
     */
    private void computeDegrees() {
        inDegree = new int[sortedLayerlessNodes.size()];
        outDegree = new int[sortedLayerlessNodes.size()];

        for (LNode node : sortedLayerlessNodes) {
            inDegree[node.id] = getInDegree(node);
            outDegree[node.id] = getOutDegree(node);
        }

    }

    /**
     * Computes the out-degree of a node.
     * 
     * @param node
     *            node to compute the out-degree for
     * @return out-degree of the node
     */
    private Integer getOutDegree(final LNode node) {
        int i = 0;
        for (@SuppressWarnings("unused")
        LEdge edge : node.getOutgoingEdges()) {
            i++;
        }
        return i;
    }

    /**
     * Computes the in-degree of a node.
     * 
     * @param node
     *            node to compute the in-degree for
     * @return in-degree of the node
     */
    private Integer getInDegree(final LNode node) {
        int i = 0;
        for (@SuppressWarnings("unused")
        LEdge edge : node.getIncomingEdges()) {
            i++;
        }
        return i;
    }

    /**
     * Computes the Average out-degree of the graph should be computed before changing the
     * layerlessNodes list.
     * 
     * @return average outdegree of the Graph
     */
    private float getAverageOutDegree() {
        float allOut = 0;
        for (LNode node : currentGraph.getLayerlessNodes()) {
            allOut += getOutDegree(node);

        }

        return allOut / currentGraph.getLayerlessNodes().size();
    }
}
