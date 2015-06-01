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

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klay.layered.ILayoutPhase;
import de.cau.cs.kieler.klay.layered.IntermediateProcessingConfiguration;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * @author marc
 *
 */
public class StretchWidth implements ILayoutPhase {
    // indicates the width of the currently build layer
    private int widthCurrent = 0;
    // estimated width of the next layer
    private int widthUp = 0;
    // indicates the width of the layering
    // will be changed dynamically increased if the layering fails for lower values
    // pseudo codes says initialize with 0 but i think you can start with 1 or mb higher/average
    // out-degree
    private int maxWidth = 0;
    // the Graph the layering is done for
    private LGraph currentGraph;
    // average out-degree
    private int avOut;
    // sorted list of layerless nodes
    private List<LNode> sortedLayerlessNodes;
    // Set of nodes placed in the current layer
    private HashSet<LNode> u = new HashSet<LNode>();
    // Set of nodes in all layers except the current
    private HashSet<LNode> z = new HashSet<LNode>();
    // list of (later sorted) layerless nodes to be used in one layering approach
    private List<LNode> tempLayerlessNodes;

    /**
     * {@inheritDoc}
     */

    public IntermediateProcessingConfiguration getIntermediateProcessingConfiguration(
            final LGraph graph) {
        return IntermediateProcessingConfiguration.createEmpty();
    }

    /**
     * {@inheritDoc}
     */
    public void process(final LGraph layeredGraph, final IKielerProgressMonitor progressMonitor) {
        /*
         * pseudo code Requires DAG G=(V,E) U={} Z={} currentLayer = 1; widthCurrent=0;widthUp=0;
         * maxWidth=0;
         */
        // while U!=V do
        // Select node v€V\U with N⁺_g(v) subset of Z and ConditionSelect
        // if no node has been selected or (ConditionGoUp and U\Z != {}) then
        // move to the upper layer
        // currentLayer = currentLayer+1;
        // Z=Union(Z,U);
        // widthCurrent=widthUp;
        // widthUp=0;
        // else if ConditionGoUp then
        // /*reset layering */
        // currentLayer=1;
        // widthCurrent=0;
        // widthUp=0; U={} Z={}
        // maxWidth=maxWidth+1;
        // else
        // /*add node to current layer*/
        // Assign v to the layer with a number currentLayer
        // U=Union(U,{v})
        // widthCurrent=withCurrent-d⁺(v)+1;
        // widthUp=widthUp+d⁻(v)
        // end if
        // end if
        // end while

        progressMonitor.begin("StretchWidth", 1);
        // set graph
        currentGraph = layeredGraph;
        
        maxWidth = currentGraph.getProperty(Properties.MAX_WIDTH_START);
        System.out.println(maxWidth);
        // Layer currently worked on
        Layer currentLayer = new Layer(currentGraph);
        currentGraph.getLayers().add(currentLayer);
        // compute average out-degree
        computeAverageDegree();
        // sort the nodes at beginning, since the rank will not change
        computeSortedNodes();
        // copy the sorted layerless nodes so we don't overwrite it for the reset case
        tempLayerlessNodes = Lists.newArrayList(sortedLayerlessNodes);
        LNode selectedNode;
        // variable to compute the difference of u and z
        HashSet<LNode> diff;

        while (!tempLayerlessNodes.isEmpty()) {
            // select a node to be placed
            selectedNode = selectNode();
            // compute u\z
            diff = new HashSet<LNode>(u);
            diff.removeAll(z);
            if (selectedNode == null || (conditionGoUp() && !diff.isEmpty())) {
                /* go to the next layer */
                currentLayer = new Layer(currentGraph);
                currentGraph.getLayers().add(currentLayer);
                // union of z and u in z
                z.addAll(u);
                // change width
                widthCurrent = widthUp;
                widthUp = 0;
            } else {
                if (conditionGoUp()) {
                    /* reset layering */
                    // clear the placed layers
                    currentGraph.getLayers().clear();
                    // create the new first layer;
                    currentLayer = new Layer(currentGraph);
                    currentGraph.getLayers().add(currentLayer);

                    // reset variables
                    widthCurrent = 0;
                    widthUp = 0;
                    u.clear();
                    z.clear();
                    // increase maxWidth
                    maxWidth++;
                    // reset layerless nodes
                    tempLayerlessNodes = Lists.newArrayList(sortedLayerlessNodes);

                } else {
                    /* add node to current layer */
                    // add node to layer
                    selectedNode.setLayer(currentLayer);
                    // remove node from the
                    tempLayerlessNodes.remove(selectedNode);
                    // add node to u
                    u.add(selectedNode);
                    // compute new widthCurrent and widthUp
                    widthCurrent = widthCurrent - getOutDegree(selectedNode) + 1;
                    widthUp = widthUp + getInDegree(selectedNode);

                }
            }

        }

        // layering done, delete original layerlessNodes
        layeredGraph.getLayerlessNodes().clear();
        //
        // Algorithm is Bottom-Up -> reverse Layers
        java.util.Collections.reverse(layeredGraph.getLayers());
        progressMonitor.done();
    }

    /**
     * Checks if the layering should go up to the next layer.
     * 
     * @return
     */
    private Boolean conditionGoUp() {
        return widthCurrent > maxWidth || (widthUp > maxWidth * avOut);
    }

    /**
     * Selects a node from the sorted list of layerless nodes. The selection is done according to
     * the rank of the node and only if all of its successors are already in z (the set of layered
     * nodes after layers earlier than the current layer).
     * 
     * @return node to be placed in the current layer
     */
    private LNode selectNode() {
        HashSet<LNode> accSucc = Sets.newHashSet();
        for (LNode node : tempLayerlessNodes) {
            for (LEdge edge : node.getOutgoingEdges()) {
                accSucc.add(edge.getTarget().getNode());
            }

            // remove current node from accSucc to deal with self-loops
            accSucc.remove(node);
            // if all successors of node are n z, choose this node, since the list is sorted by rank
            if (z.containsAll(accSucc)) {
                return node;
            }
            accSucc.clear();

        }
        // if no candidate was found return null
        return null;
    }

    /**
     * sorts a list of nodes after its rank. the rank is defined as max(d⁺(v),max(d⁺(u):(u,v)∈ E))
     * 
     * 
     */
    private void computeSortedNodes() {
        List<LNode> unsortedNodes = currentGraph.getLayerlessNodes();
        List<Pair<LNode, Integer>> nodesWithRank = Lists.newArrayList();
        sortedLayerlessNodes = Lists.newArrayList();
        // didn't find something useful to Sort a Map by its value, so I used the Pair-construct
        for (LNode node : unsortedNodes) {
            nodesWithRank.add(new Pair<LNode, Integer>(node, getRank(node)));
        }
        // sorting the Pair after its value
        java.util.Collections.sort(nodesWithRank, new Comparator<Pair<LNode, Integer>>() {
            // TODO maybe implement the comparator in the Pair-class
            public int compare(final Pair<LNode, Integer> o1, final Pair<LNode, Integer> o2) {
                // descending sort
                if ((Integer) o1.getSecond() < (Integer) o2.getSecond()) {
                    return 1;
                } else if ((Integer) o1.getSecond() > (Integer) o2.getSecond()) {
                    return -1;
                }
                return 0;
            }
        });
        // only the nodes are needed now
        for (Pair<LNode, Integer> pair : nodesWithRank) {
            sortedLayerlessNodes.add(pair.getFirst());
        }

    }

    /**
     * Computes the rank of a node.
     * 
     * @param node
     *            node to compute the rank for
     * @return rank of the node
     */
    private Integer getRank(final LNode node) {
        int max = getOutDegree(node);
        int temp;
        LNode pre;

        // compute max of predecessors out-degree and
        for (LEdge preEdge : node.getIncomingEdges()) {
            pre = preEdge.getSource().getNode();
            temp = getOutDegree(pre);
            max = Math.max(max, temp);
        }

        return max;
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
        for (@SuppressWarnings("unused") LEdge edge : node.getOutgoingEdges()) {
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
        for (@SuppressWarnings("unused") LEdge edge : node.getIncomingEdges()) {
            i++;
        }
        return i;
    }

    /**
     * computes the average out-degree of the graph.
     */
    private void computeAverageDegree() {
        int nodes = 0;
        int edges = 0;
        for (LNode node : currentGraph.getLayerlessNodes()) {
            nodes++;
            edges += getOutDegree(node);

        }
        avOut = edges / nodes;

    }

}
