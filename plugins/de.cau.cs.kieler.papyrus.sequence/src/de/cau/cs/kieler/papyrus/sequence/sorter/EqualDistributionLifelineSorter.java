/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.papyrus.sequence.sorter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.google.common.collect.HashBiMap;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.properties.Properties;
import de.cau.cs.kieler.papyrus.PapyrusProperties;
import de.cau.cs.kieler.papyrus.SequenceArea;
import de.cau.cs.kieler.papyrus.sequence.ILifelineSorter;
import de.cau.cs.kieler.papyrus.sequence.MessageType;
import de.cau.cs.kieler.papyrus.sequence.SequenceDiagramProperties;
import de.cau.cs.kieler.papyrus.sequence.graph.SGraph;
import de.cau.cs.kieler.papyrus.sequence.graph.SLifeline;
import de.cau.cs.kieler.papyrus.sequence.graph.SMessage;

/**
 * Lifeline sorting algorithm that is inspired by the heuristic solution to the linear arrangement
 * problem as proposed by McAllister in
 * "A new heuristic algorithm for the Linear Arrangement problem".
 * 
 * @author grh
 * @kieler.design proposed grh
 * @kieler.rating proposed yellow grh
 */
public class EqualDistributionLifelineSorter implements ILifelineSorter {

    /**
     * A list of these nodes is a very basic implementation of a simple graph. Every node has a map
     * with adjacent nodes and the weight of the connecting edge. Every node corresponds to a
     * lifeline in the sequence diagram. The degree of an edge represents the number of messages
     * connecting the corresponding lifelines.
     */
    private static class EDLSNode {
        /**
         * A map that contains every adjacent node and the weight of the corresponding edge. Edges
         * are stored in both of their connected nodes.
         */
        private HashMap<EDLSNode, Integer> edges;
        /**
         * For an unplaced node, the weighted sum of edges to nodes that are already placed. At
         * first, this value is 0 since there are no nodes placed so far.
         */
        private int tl = 0;
        /** Indicates, if the node was already placed. */
        private boolean placed = false;

        /** Constructor. */
        public EDLSNode() {
            edges = new HashMap<EqualDistributionLifelineSorter.EDLSNode, Integer>();
        }

        /**
         * @return the tl value
         */
        public int getTl() {
            return tl;
        }

        /**
         * @param tl
         *            the new tl value
         */
        public void setTl(final int tl) {
            this.tl = tl;
        }

        /**
         * Increment the sum of the edge-weights for neighbored nodes. This is necessary, if this
         * node was placed in the last step.
         */
        public void incrementNeighborsTL() {
            for (EDLSNode node : edges.keySet()) {
                // If a connected node is node placed yet, its TL-value has to be incremented by the
                // connecting edge's weight
                if (!node.isPlaced()) {
                    node.setTl(node.getTl() + edges.get(node));
                }
            }
        }

        /**
         * @return the placed value
         */
        public boolean isPlaced() {
            return placed;
        }

        /**
         * @param placed
         *            the new placed value
         */
        public void setPlaced(final boolean placed) {
            this.placed = placed;
        }

        /**
         * Get the weighted degree of a node. The weighted degree is the sum of all edge-weights of
         * connected edges.
         * 
         * @return the weighted degree for the node
         */
        public int getWeightedDegree() {
            int ret = 0;
            for (int value : edges.values()) {
                ret += value;
            }
            return ret;
        }
    }

    /** Option that indicates, if the starting node is searched by layering attributes. */
    private boolean layerBased = true;
    /**
     * Option that indicates, if areas are considered by the sorter. If so, edges corresponding to
     * messages in an area are given higher weight.
     */
    private boolean considerAreas = false;

    /** List of nodes that are already placed by the algorithm. */
    private List<EDLSNode> placedNodes;

    /** The map of lifeline <-> node correspondences. */
    private HashBiMap<SLifeline, EDLSNode> correspondences;

    /**
     * Constructor with parameter for the area grouping option.
     * 
     * @param groupAreas
     *            if messages that are contained in areas should be given increased priority in the
     *            message length minimization process.
     */
    public EqualDistributionLifelineSorter(final boolean groupAreas) {
        this.considerAreas = groupAreas;
    }

    /**
     * {@inheritDoc}
     */
    public List<SLifeline> sortLifelines(final SGraph sgraph, final LGraph lgraph,
            final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Equal distribution lifeline sorting", 1);

        // Create the simple graph representation that this algorithm works with.
        createEDLSNodes(sgraph);

        // Initialize list of nodes that are already placed. Nodes will be inserted one by one here.
        placedNodes = new LinkedList<EDLSNode>();

        // Calculate the starting node in a first step
        EDLSNode first;
        if (layerBased) {
            first = layerBasedFirstNode(sgraph, lgraph);
        } else {
            first = degreeBasedFirstNode(sgraph, lgraph);
        }
        placedNodes.add(first);
        // Update the TL-values for connected nodes
        first.incrementNeighborsTL();

        // Calculate following nodes one after another
        for (int i = 2; i <= sgraph.getLifelines().size(); i++) {
            EDLSNode next = calculateNextNode(sgraph);
            placedNodes.add(next);
            // Update the TL-value for connected nodes
            next.incrementNeighborsTL();
        }

        // Get the corresponding lifelines
        int i = 0;
        List<SLifeline> lifelines = new LinkedList<SLifeline>();
        for (EDLSNode node : placedNodes) {
            SLifeline lifeline = correspondences.inverse().get(node);
            lifelines.add(lifeline);
            lifeline.setHorizontalSlot(i);
            i++;
        }

        // Free memory
        placedNodes = null;
        correspondences = null;
        
        progressMonitor.done();

        // Return the list of lifelines in the calculated order
        return lifelines;
    }

    /**
     * Set the layer-based option.
     * 
     * @param layerBased
     *            the new value for the layer-based option
     */
    public void setLayerBased(final boolean layerBased) {
        this.layerBased = layerBased;
    }

    /**
     * Set the consider-areas option.
     * 
     * @param considerAreas
     *            the new value for the consider-areas option
     */
    public void setConsiderAreas(final boolean considerAreas) {
        this.considerAreas = considerAreas;
    }

    /**
     * Create the lightweight graph implementation out of the SGraph.
     * 
     * @param graph
     *            the sequence graph
     */
    private void createEDLSNodes(final SGraph graph) {
        // List of lifelines
        List<SLifeline> lifelines = graph.getLifelines();

        // Initialize correspondences list
        correspondences = HashBiMap.create(lifelines.size());

        // Create nodes
        for (SLifeline lifeline : lifelines) {
            EDLSNode node = new EDLSNode();
            correspondences.put(lifeline, node);
        }

        // If the considerAreas option is set, increase the weight of every edge whose message is
        // contained in an area.
        // Prepare this by filling a map of (message <-> number of its areas) pairs.
        HashMap<SMessage, Integer> areaMessages = new HashMap<SMessage, Integer>();
        if (considerAreas) {
            List<SequenceArea> areas = graph.getProperty(PapyrusProperties.AREAS);
            if (areas != null) {
                for (SequenceArea area : areas) {
                    for (Object messageObject : area.getMessages()) {
                        SMessage message = (SMessage) messageObject;
                        Integer messageEntry = areaMessages.get(message);
                        if (messageEntry == null) {
                            areaMessages.put(message, 1);
                        } else {
                            areaMessages.put(message, areaMessages.get(messageEntry) + 1);
                        }
                    }
                }
            }
        }

        // Insert edges
        for (SLifeline lifeline : lifelines) {
            // Get the corresponding node
            EDLSNode node = correspondences.get(lifeline);

            // Update or create entry in the edges map for every message
            for (SMessage message : lifeline.getOutgoingMessages()) {
                int increaseValue = 1;
                if (considerAreas) {
                    if (areaMessages.containsKey(message)) {
                        increaseValue += areaMessages.get(message);
                    }
                }

                SLifeline target = message.getTarget();
                EDLSNode oppositeNode = correspondences.get(target);
                if (oppositeNode != null) {
                    if (node.edges.containsKey(oppositeNode)) {
                        // Increment edge-weight by one to represent this message
                        node.edges.put(oppositeNode, node.edges.get(oppositeNode) + increaseValue);
                        // Increment opposite's map too
                        oppositeNode.edges.put(node, oppositeNode.edges.get(node) + increaseValue);
                    } else {
                        // Insert edge
                        node.edges.put(oppositeNode, increaseValue);
                        // Insert edge at opposite node too
                        oppositeNode.edges.put(node, increaseValue);
                    }
                }

                // Give a "penalty" to the TL-value of the node if there are messages leading to the
                // surrounding interaction. This is necessary, because these messages point to the
                // right border of the diagram and are not considered in the normal algorithm.
                MessageType messageType = message
                        .getProperty(SequenceDiagramProperties.MESSAGE_TYPE);
                if (oppositeNode == null && messageType != MessageType.LOST) {
                    node.setTl(node.getTl() - 1);
                }
            }

            // Give an "advantage" to the TL-value of the node if there are messages coming from the
            // surrounding interaction. This is necessary, because these messages come from the left
            // border of the diagram and are not considered in the normal algorithm.
            for (SMessage message : lifeline.getIncomingMessages()) {
                SLifeline source = message.getSource();
                EDLSNode oppositeNode = correspondences.get(source);
                MessageType messageType = message
                        .getProperty(SequenceDiagramProperties.MESSAGE_TYPE);
                if (oppositeNode == null && messageType != MessageType.FOUND) {
                    node.setTl(node.getTl() + 1);
                }
            }
        }
    }

    /**
     * Calculate the first node to be set. Standard way in the linear arrangement problem is to
     * choose the one with the fewest connected edges.
     * 
     * @param sgraph
     *            the sequence graph
     * @param lgraph
     *            the layered graph
     * @return the node that should be placed in first position
     */
    private EDLSNode degreeBasedFirstNode(final SGraph sgraph, final LGraph lgraph) {
        // Search the node with the lowest weighted degree
        int minDegree = Integer.MAX_VALUE;
        EDLSNode candidate = null;
        for (EDLSNode node : correspondences.values()) {
            if (node.getWeightedDegree() < minDegree) {
                minDegree = node.getWeightedDegree();
                candidate = node;
            }
        }
        candidate.setPlaced(true);
        return candidate;
    }

    /**
     * Calculate the first node to be set. Sequence diagram specific algorithm that chooses the
     * lifeline with the highest outgoing message.
     * 
     * @param sgraph
     *            the sequence graph
     * @param lgraph
     *            the layered graph
     * @return the node that should be placed in first location
     */
    private EDLSNode layerBasedFirstNode(final SGraph sgraph, final LGraph lgraph) {
        List<Layer> layers = lgraph.getLayers();
        Layer firstLayer = layers.get(0);
        List<LNode> nodes = firstLayer.getNodes();
        if (nodes.size() > 1) {
            // If there is more than one message in the first layer, return the one with the lowest
            // weighted node degree
            EDLSNode candidate = null;
            int bestDegree = Integer.MAX_VALUE;
            for (LNode node : nodes) {
                SMessage message = (SMessage) node.getProperty(Properties.ORIGIN);
                SLifeline sourceLifeline = message.getSource();
                EDLSNode cand = correspondences.get(sourceLifeline);
                if (cand.getWeightedDegree() < bestDegree) {
                    bestDegree = cand.getWeightedDegree();
                    candidate = cand;
                }
            }
            candidate.setPlaced(true);
            return candidate;
        } else {
            // If there is just one message in the first layer, return the node corresponding to its
            // source lifeline
            SMessage message = (SMessage) nodes.get(0).getProperty(Properties.ORIGIN);
            SLifeline sourceLifeline = message.getSource();
            EDLSNode candidate = correspondences.get(sourceLifeline);
            if (candidate == null) {
                // Found messages have no source lifeline. Therefore their target is the first lifeline 
                candidate = correspondences.get(message.getTarget());
            }
            candidate.setPlaced(true);
            return candidate;
        }
    }

    /**
     * Calculate the next node to be set.
     * 
     * @param sgraph
     *            the sequence graph
     * @return the node that should be placed in the next position
     */
    private EDLSNode calculateNextNode(final SGraph sgraph) {
        int minSF = Integer.MAX_VALUE;
        EDLSNode candidate = null;
        for (EDLSNode node : correspondences.values()) {
            if (!node.isPlaced()) {
                // Calculate the selection factor as proposed by McAllister
                int sf = node.getWeightedDegree() - 2 * node.getTl();
                // If the selection factor is smaller than the ones of other nodes, this is the next
                // node
                if (sf < minSF) {
                    minSF = sf;
                    candidate = node;
                } else if (sf == minSF) {
                    // If the factor is equal, choose the one that is more connected to aready
                    // placed nodes than the other
                    if (candidate.getTl() < node.getTl()) {
                        candidate = node;
                    }
                }
            }
        }
        candidate.setPlaced(true);
        return candidate;
    }
}
