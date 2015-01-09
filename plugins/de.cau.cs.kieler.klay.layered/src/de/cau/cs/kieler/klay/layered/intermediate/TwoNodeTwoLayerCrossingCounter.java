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
package de.cau.cs.kieler.klay.layered.intermediate;

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
public class TwoNodeTwoLayerCrossingCounter implements TwoNodeCrossingCounter {
    private int crossingsForOrderUpperLower;
    private int crossingsForOrderLowerUpper;
    private NodeData upper;
    private NodeData lower;
    private boolean fixedLayerEastOfFreeLayer;
    private final LNode upperNode;
    private final LNode lowerNode;

    /**
     * Create {@link TwoNodeTwoLayerCrossingCounter}. Naming assumes a left-right layer ordering.
     *
     * @param upperNode
     *            The upper node assuming left-right layer ordering.
     * @param lowerNode
     *            The lower node assuming left-right layer ordering.
     */
    public TwoNodeTwoLayerCrossingCounter(final LNode upperNode, final LNode lowerNode) {
        this.upperNode = upperNode;
        this.lowerNode = lowerNode;

    }

    /**
     * Calculates the number of crossings for incident edges coming from the east to node i and j.
     * The crossing amounts can be received with getCrossingForOrderUpperLower and
     * getCrossingForOrderLowerUpper for the order node -> lower or lower -> node respectively.
     */
    public void countWesternEdgeCrossings() {
        fixedLayerEastOfFreeLayer = true;
        upper = new NodeData(upperNode);
        lower = new NodeData(lowerNode);
        countCrossings();
    }

    /**
     * Calculates the number of crossings for incident edges coming from the west to node i and j.
     * The crossing amounts can be received with getCrossingForOrderUpperLower and
     * getCrossingForOrderLowerUpper for the order node -> lower or lower -> node respectively.
     */
    public void countEasternEdgeCrossings() {
        fixedLayerEastOfFreeLayer = false;
        upper = new NodeData(upperNode);
        lower = new NodeData(lowerNode);
        countCrossings();
    }

    /**
     * Calculates the number of crossings for incident edges coming from the both sides to node i
     * and j. The crossing amounts can be received with getCrossingForOrderUpperLower and
     * getCrossingForOrderLowerUpper for the order node -> lower or lower -> node respectively.
     */
    public void countBothSideCrossings() {
        countWesternEdgeCrossings();
        countEasternEdgeCrossings();
    }

    private void countCrossings() {
        if (areSameNodes()) {
            return;
        }

        if (notOnlyHasSelfLoops()) {
            boolean edgesRemain = true;
            while (edgesRemain) {
                if (nextEdgeCausesCrossingsToOrder(upper, lower)) {
                    crossingsForOrderUpperLower += upper.getAmountOfEdges();
                    edgesRemain = fetchNextEdge(lower);
                } else if (nextEdgeCausesCrossingsToOrder(lower, upper)) {
                    crossingsForOrderLowerUpper += lower.getAmountOfEdges();
                    edgesRemain = fetchNextEdge(upper);
                } else if (nextEdgeCausesCrossingsToBothOrders()) {
                    crossingsForOrderUpperLower += upper.getAmountOfEdgesWithoutCurrentNode();
                    crossingsForOrderLowerUpper += lower.getAmountOfEdgesWithoutCurrentNode();
                    edgesRemain = fetchNextEdge(upper) && fetchNextEdge(lower);
                } else {
                    edgesRemain = false;
                }
            }
        }
    }

    private boolean areSameNodes() {
        return upper.getNode() == lower.getNode();
    }

    private boolean notOnlyHasSelfLoops() {
        return upper.getNeighbourPort() != null && lower.getNeighbourPort() != null;
    }

    private boolean nextEdgeCausesCrossingsToOrder(final NodeData above, final NodeData below) {
        LNode neighbourToUpperNode = above.getNeighbourPort().getNode();
        LNode neighbourToLowerNode = below.getNeighbourPort().getNode();
        boolean causesCrossingsToOrderUpperLower =
                neighbourToUpperNode.id > neighbourToLowerNode.id;

        if (neighbourToUpperNode.id == neighbourToLowerNode.id
                && neighbourToUpperNode.getProperty(LayoutOptions.PORT_CONSTRAINTS).isOrderFixed()) {
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

    private boolean fetchNextEdge(final NodeData nodeData) {
        boolean crossingsRemain;
        nodeData.decrementEdgeCount();

        crossingsRemain = nodeData.getAmountOfEdges() != 0;
        if (crossingsRemain) {
            nodeData.setNextPortAndRemoveEdge();
        }
        return crossingsRemain;
    }

    private boolean nextEdgeCausesCrossingsToBothOrders() {
        LNode neighbourToLowerNode = lower.getNeighbourPort().getNode();
        LNode neighbourToUpperNode = upper.getNeighbourPort().getNode();
        boolean causesCrossingsToBoth =
                neighbourToUpperNode.id == neighbourToLowerNode.id
                        && !neighbourToUpperNode.getProperty(LayoutOptions.PORT_CONSTRAINTS)
                                .isOrderFixed();
        return causesCrossingsToBoth;
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
    private class NodeData {
        private final LNode node;
        private final TreeMap<Integer, List<LEdge>> edges;
        private int amountOfEdges;
        private LPort neighbourPort;

        public NodeData(final LNode node) {
            this.node = node;
            edges = fetchEdges();
            amountOfEdges = fetchAmountOfEdges();
            neighbourPort = fetchNextPort();
        }

        private TreeMap<Integer, List<LEdge>> fetchEdges() {
            TreeMap<Integer, List<LEdge>> resultEdges = new TreeMap<Integer, List<LEdge>>();
            List<LPort> ports = node.getPorts();
            for (LPort port : ports) {
                for (LEdge edge : fixedLayerEastOfFreeLayer ? port.getIncomingEdges() : port
                        .getOutgoingEdges()) {
                    boolean edgeIsNotSelfLoop =
                            edge.getSource().getNode() != edge.getTarget().getNode();
                    boolean edgeIsNotInLayerEdge =
                            edge.getSource().getNode().getLayer() != edge.getTarget().getNode()
                                    .getLayer();

                    if (edgeIsNotSelfLoop && edgeIsNotInLayerEdge) {
                        int index =
                                fixedLayerEastOfFreeLayer ? edge.getSource().getNode().id : edge
                                        .getTarget().getNode().id;
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
                            fixedLayerEastOfFreeLayer ? edgesWithSameNodes.get(0).getSource()
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
