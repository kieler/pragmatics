/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.intermediate.greedyswitch;

import java.util.List;
import java.util.TreeMap;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;

/**
 * Calculates the number of crossings for incident edges to node i and j. Prerequisites: Node.id's
 * set to original order
 *
 * @author alan
 *
 */
class InBetweenLayerEdgeTwoNodeCrossingCounter {
    private int crossingsForOrderUpperLower;
    private int crossingsForOrderLowerUpper;
    private NodeData upper;
    private NodeData lower;
    private boolean fixedLayerEastOfFreeLayer;
    private final LNode[][] currentNodeOrder;
    private final int freeLayerIndex;

    /**
     * Create {@link InBetweenLayerEdgeTwoNodeCrossingCounter}. Naming assumes a left-right layer
     * ordering.
     * 
     * @param currentNodeOrder
     *            Currently considered node ordering.
     * @param freeLayerIndex
     *            Index of free layer.
     */
    public InBetweenLayerEdgeTwoNodeCrossingCounter(final LNode[][] currentNodeOrder,
            final int freeLayerIndex) {
        this.currentNodeOrder = currentNodeOrder;
        this.freeLayerIndex = freeLayerIndex;
        initializeNodeAndPortIdsForNeighbouringLayers();
        // TODO-alan ids could be changed before counting. At least put into comment
    }

    /**
     * Calculates the number of crossings for incident edges coming from the east to node i and j.
     * The crossing amounts can be received with getCrossingForOrderUpperLower and
     * getCrossingForOrderLowerUpper for the order node -> lower or lower -> node respectively.
     * 
     * @param upperNode
     *            Upper node assuming left-right layout.
     * @param lowerNode
     *            Lower node assuming left-right layout
     */
    public void countWesternEdgeCrossings(final LNode upperNode, final LNode lowerNode) {
        resetCrossingCount();
        addWesternCrossings(upperNode, lowerNode);
    }

    private void addWesternCrossings(final LNode upperNode, final LNode lowerNode) {
        fixedLayerEastOfFreeLayer = true;
        upper = new NodeData(upperNode, fixedLayerEastOfFreeLayer);
        lower = new NodeData(lowerNode, fixedLayerEastOfFreeLayer);
        countCrossings();
    }

    /**
     * Calculates the number of crossings for incident edges coming from the west to node i and j.
     * The crossing amounts can be received with getCrossingForOrderUpperLower and
     * getCrossingForOrderLowerUpper for the order node -> lower or lower -> node respectively.
     * 
     * @param upperNode
     *            Upper node assuming left-right layout.
     * @param lowerNode
     *            Lower node assuming left-right layout
     */
    public void countEasternEdgeCrossings(final LNode upperNode, final LNode lowerNode) {
        resetCrossingCount();
        addEasternCrossings(upperNode, lowerNode);
    }

    private void addEasternCrossings(final LNode upperNode, final LNode lowerNode) {
        fixedLayerEastOfFreeLayer = false;
        upper = new NodeData(upperNode, fixedLayerEastOfFreeLayer);
        lower = new NodeData(lowerNode, fixedLayerEastOfFreeLayer);
        countCrossings();
    }

    /**
     * Calculates the number of crossings for incident edges coming from the both sides to node i
     * and j. The crossing amounts can be received with getCrossingForOrderUpperLower and
     * getCrossingForOrderLowerUpper for the order node -> lower or lower -> node respectively.#
     * 
     * @param upperNode
     *            Upper node assuming left-right layout.
     * @param lowerNode
     *            Lower node assuming left-right layout
     */
    public void countBothSideCrossings(final LNode upperNode, final LNode lowerNode) {
        resetCrossingCount();
        addWesternCrossings(upperNode, lowerNode);
        addEasternCrossings(upperNode, lowerNode);
    }

    private void resetCrossingCount() {
        crossingsForOrderLowerUpper = 0;
        crossingsForOrderUpperLower = 0;
    }

    private void initializeNodeAndPortIdsForNeighbouringLayers() {
        if (freeLayerIsNotFirstLayer()) {
            setIdsForLayer(freeLayerIndex - 1);
        }
        if (freeLayerIsNotLastLayer()) {
            setIdsForLayer(freeLayerIndex + 1);
        }
    }

    private boolean freeLayerIsNotFirstLayer() {
        return freeLayerIndex > 0;
    }

    private boolean freeLayerIsNotLastLayer() {
        return freeLayerIndex < currentNodeOrder.length - 1;
    }

    /**
     * This prevents coupling to set node.ids for a given node order. Guaranteeing to keep node.ids
     * in the order for every layer would make this unnecessary, however to due design
     * considerations we won't do this.
     * 
     * @param layerIndex
     */
    private void setIdsForLayer(final int layerIndex) {
        for (int i = 0; i < currentNodeOrder[layerIndex].length; i++) {
            LNode node = currentNodeOrder[layerIndex][i];
            node.id = i;
            int portId = 0;
            for (LPort port : node.getPorts()) {
                port.id = portId;
                portId++;
            }
        }
    }

    private void countCrossings() {
        if (areSameNodes()) {
            return;
        }

        if (nodesHaveNeighbours()) {
            boolean edgesRemain = true;
            while (edgesRemain) {
                if (nextEdgeCausesCrossingsToOrder(upper, lower)) {
                    crossingsForOrderUpperLower += upper.getAmountOfEdges();
                    edgesRemain = setToNextEdge(lower);
                } else if (nextEdgeCausesCrossingsToOrder(lower, upper)) {
                    crossingsForOrderLowerUpper += lower.getAmountOfEdges();
                    edgesRemain = setToNextEdge(upper);
                } else {
                    crossingsForOrderUpperLower += upper.getAmountOfEdgesWithoutCurrentNode();
                    crossingsForOrderLowerUpper += lower.getAmountOfEdgesWithoutCurrentNode();
                    edgesRemain = setToNextEdge(upper) && setToNextEdge(lower);
                }
            }
        }
    }

    private boolean areSameNodes() {
        return upper.getNode() == lower.getNode();
    }

    private boolean nodesHaveNeighbours() {
        return upper.getNeighbourPort() != null && lower.getNeighbourPort() != null;
    }

    private boolean nextEdgeCausesCrossingsToOrder(final NodeData above, final NodeData below) {
        LNode neighbourToUpperNode = above.getNeighbourPort().getNode();
        LNode neighbourToLowerNode = below.getNeighbourPort().getNode();
        boolean causesCrossingsToOrderUpperLower =
                neighbourToUpperNode.id > neighbourToLowerNode.id;

        if (neighbourToUpperNode.id == neighbourToLowerNode.id
                && portOrderIsFixed(neighbourToUpperNode)) {
            if (fixedLayerEastOfFreeLayer) {
                causesCrossingsToOrderUpperLower |=
                        above.getNeighbourPort().id > below.getNeighbourPort().id;
            } else {
                causesCrossingsToOrderUpperLower |=
                        above.getNeighbourPort().id < below.getNeighbourPort().id;
            }
        }
        return causesCrossingsToOrderUpperLower;
    }

    private boolean portOrderIsFixed(final LNode neighbourToUpperNode) {
        return neighbourToUpperNode.getProperty(LayoutOptions.PORT_CONSTRAINTS).isOrderFixed();
    }

    private boolean setToNextEdge(final NodeData nodeData) {
        boolean crossingsRemain;
        nodeData.decrementEdgeCount();

        crossingsRemain = nodeData.getAmountOfEdges() != 0;
        if (crossingsRemain) {
            nodeData.setNextPortAndRemoveEdge();
        }
        return crossingsRemain;
    }

    /**
     * @return the crossingsForOrderUpperLower
     */
    public int getUpperLowerCrossings() {
        return crossingsForOrderUpperLower;
    }

    /**
     * @return the crossingsForOrderLowerUpper
     */
    public int getLowerUpperCrossings() {
        return crossingsForOrderLowerUpper;
    }

    /**
     * Keeps and manipulates all data for each node currently being examined.
     * 
     * @author alan
     *
     */
    private static class NodeData {
        private final LNode node;
        private final TreeMap<Integer, List<LEdge>> edges;
        private int amountOfEdges;
        private LPort neighbourPort;
        private final boolean initWesternEdges;

        public NodeData(final LNode node, final boolean fixedLayerEastOfFreeLayer) {
            this.node = node;
            initWesternEdges = fixedLayerEastOfFreeLayer;
            edges = fetchEdges();
            amountOfEdges = fetchAmountOfEdges();
            neighbourPort = fetchNextPort();
        }

        private TreeMap<Integer, List<LEdge>> fetchEdges() {
            TreeMap<Integer, List<LEdge>> resultEdges = new TreeMap<Integer, List<LEdge>>();
            List<LPort> ports = node.getPorts();
            for (LPort port : ports) {
                for (LEdge edge : initWesternEdges ? port.getIncomingEdges() : port
                        .getOutgoingEdges()) {
                    boolean edgeIsNotInLayerEdge =
                            edge.getSource().getNode().getLayer() != edge.getTarget().getNode()
                                    .getLayer();

                    if (!edge.isSelfLoop() && edgeIsNotInLayerEdge) {
                        int index =
                                initWesternEdges ? edge.getSource().getNode().id : edge.getTarget()
                                        .getNode().id;
                        if (resultEdges.containsKey(index)) {
                            resultEdges.get(index).add(edge);
                        } else {
                            resultEdges.put(index, Lists.newArrayList(edge));
                        }
                    }
                }
            }
            return resultEdges;
        }

        private int fetchAmountOfEdges() {
            int amount = 0;
            for (List<LEdge> edge : edges.values()) {
                amount += edge.size();
            }
            return amount;
        }

        private LPort fetchNextPort() {
            LPort resultNeighbourPort = null;
            while (!edges.isEmpty()) {
                List<LEdge> edgesWithSameNodes = edges.firstEntry().getValue();
                if (edgesWithSameNodes.get(0).getSource().getNode() == edgesWithSameNodes.get(0)
                        .getTarget().getNode()) {
                    edges.remove(edges.firstKey());
                } else {
                    resultNeighbourPort =
                            initWesternEdges ? edgesWithSameNodes.get(0).getSource()
                                    : edgesWithSameNodes.get(0).getTarget();
                    break;
                }
            }
            return resultNeighbourPort;
        }

        public int getAmountOfEdgesWithoutCurrentNode() {
            return amountOfEdges - edges.get(edges.firstKey()).size();
        }

        public void decrementEdgeCount() {
            amountOfEdges--;
        }

        public void setNextPortAndRemoveEdge() {
            List<LEdge> firstEdgesWithSameNodes = edges.get(edges.firstKey());
            if (firstEdgesWithSameNodes.size() == 1) {
                edges.remove(edges.firstKey());
            } else {
                edges.get(edges.firstKey()).remove(0);
            }
            neighbourPort = fetchNextPort();
        }

        public LNode getNode() {
            return node;
        }

        public LPort getNeighbourPort() {
            return neighbourPort;
        }

        public int getAmountOfEdges() {
            return amountOfEdges;
        }

        @Override
        public String toString() {
            return "NodeData [node=" + node + "]";
        }
    }
}
