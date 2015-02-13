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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortSide;
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
    private final LNode[][] currentNodeOrder;
    private final int freeLayerIndex;
    private final Map<LNode, Integer> nodePositions;
    private final Map<LPort, Integer> portPositions;

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
        nodePositions = new HashMap<LNode, Integer>();
        portPositions = new HashMap<LPort, Integer>();
        this.currentNodeOrder = currentNodeOrder;
        this.freeLayerIndex = freeLayerIndex;
        initializeNodeAndPortIdsForNeighbouringLayers();
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

    private void addEasternCrossings(final LNode upperNode, final LNode lowerNode) {
        upper = new NodeData(upperNode, PortSide.EAST);
        lower = new NodeData(lowerNode, PortSide.EAST);
        countCrossings();
    }

    private void addWesternCrossings(final LNode upperNode, final LNode lowerNode) {
        upper = new NodeData(upperNode, PortSide.WEST);
        lower = new NodeData(lowerNode, PortSide.WEST);
        countCrossings();
    }

    private void resetCrossingCount() {
        crossingsForOrderLowerUpper = 0;
        crossingsForOrderUpperLower = 0;
    }

    private void initializeNodeAndPortIdsForNeighbouringLayers() {
        if (freeLayerIsNotFirstLayer()) {
            setPortAndNodePositionsForLayer(freeLayerIndex - 1, PortSide.EAST);
        }
        if (freeLayerIsNotLastLayer()) {
            setPortAndNodePositionsForLayer(freeLayerIndex + 1, PortSide.WEST);
        }
    }

    private boolean freeLayerIsNotFirstLayer() {
        return freeLayerIndex > 0;
    }

    private boolean freeLayerIsNotLastLayer() {
        return freeLayerIndex < currentNodeOrder.length - 1;
    }

    private void setPortAndNodePositionsForLayer(final int layerIndex, final PortSide portSide) {
        for (int i = 0; i < currentNodeOrder[layerIndex].length; i++) {
            LNode node = currentNodeOrder[layerIndex][i];
            nodePositions.put(node, i);
            int portId = 0;
            PortIterable ports = new PortIterable(node, portSide);
            for (LPort port : ports) {
                portPositions.put(port, portId);
                portId++;
            }
        }
    }

    /**
     * The main algorithm.
     */
    private void countCrossings() {
        if (areSameNodes()) {
            return;
        }

        if (nodesHaveNeighbours()) {
            boolean edgesRemain = true;
            while (edgesRemain) {
                if (nextEdgeCausesCrossingsToOrder(upper, lower)) {
                    crossingsForOrderUpperLower += upper.getAmountOfEdges();
                    edgesRemain = removeFirstEdgeFrom(lower);
                } else if (nextEdgeCausesCrossingsToOrder(lower, upper)) {
                    crossingsForOrderLowerUpper += lower.getAmountOfEdges();
                    edgesRemain = removeFirstEdgeFrom(upper);
                } else {
                    crossingsForOrderUpperLower += upper.getAmountOfEdgesWithoutCurrentNode();
                    crossingsForOrderLowerUpper += lower.getAmountOfEdgesWithoutCurrentNode();
                    edgesRemain = removeFirstEdgeFrom(upper) && removeFirstEdgeFrom(lower);
                }
            }
        }
    }

    private boolean nextEdgeCausesCrossingsToOrder(final NodeData above, final NodeData below) {
        LNode neighbourToUpperNode = above.getNeighbourPort().getNode();
        LNode neighbourToLowerNode = below.getNeighbourPort().getNode();

        boolean higherEdgeToLowerNode =
                positionOf(neighbourToUpperNode) > positionOf(neighbourToLowerNode);

        boolean bothEdgesToSameNodeWithFixedPortOrder =
                positionOf(neighbourToUpperNode) == positionOf(neighbourToLowerNode)
                        && portOrderIsFixed(neighbourToUpperNode);
        boolean higherEdgeToLowerPort =
                positionOf(above.getNeighbourPort()) > positionOf(below.getNeighbourPort());

        return higherEdgeToLowerNode || bothEdgesToSameNodeWithFixedPortOrder
                && higherEdgeToLowerPort;
    }

    private boolean areSameNodes() {
        return upper.getNode() == lower.getNode();
    }

    private boolean nodesHaveNeighbours() {
        return upper.getNeighbourPort() != null && lower.getNeighbourPort() != null;
    }

    private int positionOf(final LPort port) {
        return portPositions.get(port);
    }

    private int positionOf(final LNode node) {
        return nodePositions.get(node);
    }

    private boolean portOrderIsFixed(final LNode neighbourToUpperNode) {
        return neighbourToUpperNode.getProperty(LayoutOptions.PORT_CONSTRAINTS).isOrderFixed();
    }

    private boolean removeFirstEdgeFrom(final NodeData nodeData) {
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
    private class NodeData {
        private final LNode node;
        private final TreeMap<Integer, List<LEdge>> edges;
        private int amountOfEdges;
        private LPort neighbourPort;
        private final PortSide side;

        public NodeData(final LNode node, final PortSide side) {
            this.node = node;
            this.side = side;
            edges = fetchEdges();
            amountOfEdges = fetchAmountOfEdges();
            neighbourPort = fetchNextPort();
        }

        private TreeMap<Integer, List<LEdge>> fetchEdges() {
            TreeMap<Integer, List<LEdge>> resultEdges = new TreeMap<Integer, List<LEdge>>();
            PortIterable ports = new PortIterable(node, side);
            for (LPort port : ports) {
                List<LEdge> lEdges =
                        side == PortSide.WEST ? port.getIncomingEdges() : port.getOutgoingEdges();
                for (LEdge edge : lEdges) {
                    if (!edge.isSelfLoop() && notInLayer(edge)) {
                        int endNodePosition = positionOf(endNodeOf(edge, side));
                        if (resultEdges.containsKey(endNodePosition)) {
                            resultEdges.get(endNodePosition).add(edge);
                        } else {
                            resultEdges.put(endNodePosition, Lists.newArrayList(edge));
                        }
                    }
                }
            }
            return resultEdges;
        }

        private boolean notInLayer(final LEdge edge) {
            return edge.getSource().getNode().getLayer() != edge.getTarget().getNode().getLayer();
        }

        private LNode endNodeOf(final LEdge edge, final PortSide portSide) {
            return portSide == PortSide.WEST ? edge.getSource().getNode() : edge.getTarget()
                    .getNode();
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
                            side == PortSide.WEST ? edgesWithSameNodes.get(0).getSource()
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
