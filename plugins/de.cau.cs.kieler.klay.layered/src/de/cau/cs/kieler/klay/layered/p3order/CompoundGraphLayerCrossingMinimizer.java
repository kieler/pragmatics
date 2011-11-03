/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.p3order;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.google.common.collect.Multimap;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.klay.layered.graph.LGraphElement;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.intermediate.SubgraphOrderingProcessor;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * Implements the actual crossing minimization step for a given free layer. In a flat graph, an
 * ICrossingMinimizationHeuristic is directly used to compute the node ordering. For compound graphs
 * it computes orderings for each compound node seperately, working its way from the innermost
 * compound node to the outermost, using calculated node orders as atomic sets of nodes for the next
 * calculations. This approach is inspired by Michael Forster: "Applying Crossing Reduction
 * Strategies to Layered Compound Graphs", University of Passau.
 * 
 * @author ima
 * @author cds
 * @author msp
 */

public class CompoundGraphLayerCrossingMinimizer {

    private IPortDistributor portDistributor;

    private Random random;

    private boolean isCompound;

    private float[] portPos;

    private Map<LNode, NodeGroup>[] singleNodeNodeGroups;

    private Multimap<LNode, LNode> layoutUnits;

    private ICrossingMinimizationHeuristic minimizationHeuristic;

    /**
     * Constructs a CompoundGraphLayerCrossingMinimizer with the given IPortDistributor, Random,
     * isCompound, portPos array, single-node-nodeGroup map and layoutUnits.
     * 
     * @param selPortDistributor
     *            the IPortDistributor chosen by the crossing minimizer.
     * @param graphRandom
     *            the random number generator.
     * @param handleHierarchy
     *            whether the graph to be laid out is a compound one.
     * @param portRanks
     *            the array of port ranks.
     * @param oneNodeNodeGroups
     *            map of single node vertices for each layer.
     * @param layoutUnit
     *            map associating layoutUnits with their members.
     */
    protected CompoundGraphLayerCrossingMinimizer(final IPortDistributor selPortDistributor,
            final Random graphRandom, final boolean handleHierarchy, final float[] portRanks,
            final Map<LNode, NodeGroup>[] oneNodeNodeGroups, final Multimap<LNode, LNode> layoutUnit) {
        portDistributor = selPortDistributor;
        random = graphRandom;
        isCompound = handleHierarchy;
        portPos = portRanks;
        singleNodeNodeGroups = oneNodeNodeGroups;
        layoutUnits = layoutUnit;
    }

    /**
     * Uses an ICrossingMinimizationHeuristic to compute a node order for a given layer. If the
     * graph to be laid out is a compound graph, it prepares slices of work for the
     * ICrossingMinimizationHeuristic: Each compound node is ordered separately, from the innermost
     * one to the outermost. After ordering, the nodes of a compound node are treated as an atomic
     * entity with fixed node order, represented by a NodeGroup for the next calculations.
     * 
     * @param layer
     *            the nodes of the layer, whose order is to be determined.
     * @param layerIndex
     *            the index of that layer.
     * @param forward
     *            whether the fixed layer is located after the free layer.
     * @param preOrdered
     *            whether the nodes have been ordered in a previous run.
     * @param randomize
     *            whether the layer's node order should just be randomized.
     * @param layeredGraph
     *            The layered graph that is to be laid out.
     * @return the total number of edges going either in or out of the given layer.
     */
    protected int compoundMinimizeCrossings(final LNode[] layer, final int layerIndex,
            final boolean forward, final boolean preOrdered, final boolean randomize,
            final LayeredGraph layeredGraph) {

        // chose a crossing minimization heuristic
        minimizationHeuristic = new BarycenterHeuristic(portDistributor, random);

        // get the map associating LGraph-elements and the original KGraph-Elements
        HashMap<KGraphElement, LGraphElement> elemMap = layeredGraph
                .getProperty(Properties.ELEMENT_MAP);

        // prepare an LNode as key representing the layeredGraph in HashMaps
        LNode graphKey = new LNode();
        graphKey.setProperty(Properties.ORIGIN, layeredGraph);

        int totalEdges = 0;

        // Ignore empty free layers
        if (layer.length == 0) {
            return 0;
        }

        if (!isCompound) {
            /*
             * Prepare list of single-node NodeGroups for the minimization Heuristic.
             */
            List<NodeGroup> layerNodeGroups = new LinkedList<NodeGroup>();
            for (LNode node : layer) {
                layerNodeGroups.add(singleNodeNodeGroups[layerIndex].get(node));
            }

            totalEdges = minimizationHeuristic.minimizeCrossings(layerNodeGroups, layoutUnits,
                    layerIndex, preOrdered, randomize, forward, portPos, singleNodeNodeGroups);
        } else {
            // sort the layer's nodes according to their related compound nodes. Find out the
            // maximal depth on the run.
            HashMap<LNode, LinkedList<NodeGroup>> compoundNodesMap 
                = new HashMap<LNode, LinkedList<NodeGroup>>();
            // Remember the order of processing for the compound nodes. This list will contain the
            // same nodes as the keySet of the compoundNodesMap. However, as order matters, the
            // latter can not be used for iteration.
            LinkedList<LNode> compoundNodesMapKeys = new LinkedList<LNode>();

            int maximalDepth = 0;

            for (LNode node : layer) {
                // The correlation node/compoundNode is the same as in the SubGraphOrderingProcessor
                LNode key = SubgraphOrderingProcessor.getRelatedCompoundNode(node, layeredGraph);
                // If node is contained by the layeredGraph directly, getRelatedCompoundNode has
                // returned null. Use the graphkey in this case.
                if (key == null) {
                    key = graphKey;
                }
                LinkedList<NodeGroup> relatedList;
                if (compoundNodesMap.containsKey(key)) {
                    relatedList = compoundNodesMap.get(key);
                } else {
                    relatedList = new LinkedList<NodeGroup>();
                    compoundNodesMap.put(key, relatedList);
                    compoundNodesMapKeys.add(key);
                }
                NodeGroup thisNodesGroup = singleNodeNodeGroups[layerIndex].get(node);
                relatedList.add(thisNodesGroup);
                int keydepth = key.getProperty(Properties.DEPTH);
                if (keydepth > maximalDepth) {
                    maximalDepth = keydepth;
                }
            }
            // Sort the relevant compound nodes into lists sorted by depth. Index 0 means list of
            // nodes with depth 0.
            LinkedList<LinkedList<LNode>> compoundNodesPerDepthLevel 
                = new LinkedList<LinkedList<LNode>>();
            for (int i = 0; i <= maximalDepth; i++) {
                LinkedList<LNode> depthList = new LinkedList<LNode>();
                compoundNodesPerDepthLevel.add(depthList);
            }
            for (LNode compoundNode : compoundNodesMapKeys) {
                compoundNodesPerDepthLevel.get(compoundNode.getProperty(Properties.DEPTH)).add(
                        compoundNode);
            }

            while (!compoundNodesPerDepthLevel.isEmpty()) {
                // Handle the compound nodes beginning from the highest depth level up to the
                // lowest.
                LinkedList<LNode> actualList = compoundNodesPerDepthLevel.removeLast();
                // Process the compound nodes of the actual depth level.
                for (LNode keyNode : actualList) {
                    LinkedList<NodeGroup> compoundContent = compoundNodesMap.get(keyNode);
                    // Calculate the nodeOrder for this compound node.
                    totalEdges += minimizationHeuristic.minimizeCrossings(compoundContent,
                            layoutUnits, layerIndex, preOrdered, randomize, forward, portPos,
                            singleNodeNodeGroups);
                    // Is outermost level reached? If not, represent the compound node as one entity
                    // for the higher levels. Update compoundNodesMap and
                    // compoundNodesPerDepthLevel.
                    if (keyNode != graphKey) {
                        // Create a NodeGroup comprising all Nodes of this compound node, preserving
                        // the
                        // order
                        NodeGroup aggregatedNodeGroup;
                        if (compoundContent.size() == 1) {
                            aggregatedNodeGroup = compoundContent.getFirst();
                        } else {
                            aggregatedNodeGroup = new NodeGroup(compoundContent.removeFirst(),
                                    compoundContent.removeLast(), random);
                            while (!compoundContent.isEmpty()) {
                                aggregatedNodeGroup = new NodeGroup(aggregatedNodeGroup,
                                        compoundContent.removeFirst(), random);
                            }
                        }
                        // Store the new nodeGroup representing the compound node in the
                        // compoundNodesMap with the parent of the compoundNode as a key.
                        KNode keyNodeParent = keyNode.getProperty(Properties.PARENT);
                        LGraphElement parentRepresentative = elemMap.get(keyNodeParent);
                        LNode parentKey;
                        if (parentRepresentative instanceof LayeredGraph) {
                            parentKey = graphKey;
                        } else {
                            assert (parentRepresentative instanceof LNode);
                            parentKey = (LNode) parentRepresentative;
                        }
                        LinkedList<NodeGroup> parentContents;
                        if (compoundNodesMap.containsKey(parentKey)) {
                            parentContents = compoundNodesMap.get(parentKey);
                        } else {
                            parentContents = new LinkedList<NodeGroup>();
                            compoundNodesMap.put(parentKey, parentContents);
                            compoundNodesMapKeys.add(parentKey);
                        }
                        parentContents.add(aggregatedNodeGroup);

                        // Store the parent of the compoundNode in the
                        // compoundNodesPerDepthLevel-list
                        // if not already present
                        LinkedList<LNode> parentList = compoundNodesPerDepthLevel.get(parentKey
                                .getProperty(Properties.DEPTH));
                        if (!parentList.contains(parentKey)) {
                            parentList.add(parentKey);
                        }
                    } else {
                        applyNodeGroupOrderingToNodeArray(compoundContent, layer);
                    }
                }
            }
        }

        return totalEdges;
    }

    /**
     * Apply the node order as determined by the sorted list of vertices to the free layer array.
     * 
     * @param nodeGroups
     *            sorted array of vertices.
     * @param freeLayer
     *            array of nodes to apply the ordering to.
     */
    private void applyNodeGroupOrderingToNodeArray(final List<NodeGroup> nodeGroups,
            final LNode[] freeLayer) {
        int index = 0;

        for (NodeGroup nodeGroup : nodeGroups) {
            for (LNode node : nodeGroup.getNodes()) {
                freeLayer[index++] = node;
            }
        }
    }
}
