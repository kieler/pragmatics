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
import java.util.NavigableMap;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.intermediate.greedyswitch.PortIterable.PortOrder;

/**
 * Calculates the number of crossings for incident edges to node i and j. Prerequisites: Node.id's
 * set to original order
 *
 * @author alan
 *
 */
class InBetweenLayerEdgeTwoNodeCrossingCounter {
    private int upperLowerCrossings;
    private int lowerUpperCrossings;
    private AdjacencyList upperAdjacencies;
    private AdjacencyList lowerAdjacencies;
    private final LNode[][] currentNodeOrder;
    private final int freeLayerIndex;
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
        portPositions = new HashMap<LPort, Integer>();
        this.currentNodeOrder = currentNodeOrder;
        this.freeLayerIndex = freeLayerIndex;
        initializeNodeAndPortIdsForNeighbouringLayers();
    }

    /**
     * Calculates the number of crossings for incident edges coming from the west to node i and j.
     * The crossing amounts can be received with getCrossingForOrderUpperLower and
     * getCrossingForOrderLowerUpper for the order upperNode -> lowerNode or lowerNode -> upperNode
     * respectively.
     * 
     * @param upperNode
     *            Upper node assuming left-right layout.
     * @param lowerNode
     *            Lower node assuming left-right layout
     */
    public void countEasternEdgeCrossings(final LNode upperNode, final LNode lowerNode) {
        resetCrossingCount();
        if (upperNode.equals(lowerNode)) {
            return;
        }
        addEasternCrossings(upperNode, lowerNode);
    }

    /**
     * Calculates the number of crossings for incident edges coming from the east to node i and j.
     * The crossing amounts can be received with getCrossingForOrderUpperLower and
     * getCrossingForOrderLowerUpper for the order upperNode -> lowerNode or lowerNode -> upperNode
     * respectively.
     * 
     * @param upperNode
     *            Upper node assuming left-right layout.
     * @param lowerNode
     *            Lower node assuming left-right layout
     */
    public void countWesternEdgeCrossings(final LNode upperNode, final LNode lowerNode) {
        resetCrossingCount();
        if (upperNode.equals(lowerNode)) {
            return;
        }
        addWesternCrossings(upperNode, lowerNode);
    }

    /**
     * Calculates the number of crossings for incident edges coming from the both sides to node i
     * and j. The crossing amounts can be received with getCrossingForOrderUpperLower and
     * getCrossingForOrderLowerUpper for the order upperNode -> lowerNode or lowerNode -> upperNode
     * respectively.
     * 
     * @param upperNode
     *            Upper node assuming left-right layout.
     * @param lowerNode
     *            Lower node assuming left-right layout
     */
    public void countBothSideCrossings(final LNode upperNode, final LNode lowerNode) {
        resetCrossingCount();
        if (upperNode.equals(lowerNode)) {
            return;
        }
        addWesternCrossings(upperNode, lowerNode);
        addEasternCrossings(upperNode, lowerNode);
    }

    private void resetCrossingCount() {
        lowerUpperCrossings = 0;
        upperLowerCrossings = 0;
    }

    private void addEasternCrossings(final LNode upperNode, final LNode lowerNode) {
        upperAdjacencies = new AdjacencyList(upperNode, PortSide.EAST);
        lowerAdjacencies = new AdjacencyList(lowerNode, PortSide.EAST);
        if (upperAdjacencies.size() == 0 || lowerAdjacencies.size() == 0) {
            return;
        }
        countCrossingsByMergingAdjacencyLists();
    }

    private void addWesternCrossings(final LNode upperNode, final LNode lowerNode) {
        upperAdjacencies = new AdjacencyList(upperNode, PortSide.WEST);
        lowerAdjacencies = new AdjacencyList(lowerNode, PortSide.WEST);
        if (upperAdjacencies.size() == 0 || lowerAdjacencies.size() == 0) {
            return;
        }
        countCrossingsByMergingAdjacencyLists();
    }

    /**
     * <pre>
     * The main algorithm. 
     * Adjacency Lists are lists of ports connected to a node. If a connected node has no fixed port
     * ordering all ports have the same position value.
     * By merging adjacency lists, both the amount of between-layer crossings for the order upper
     * - lower and for the opposite order can be found.
     * Consider:
     * A   p0 
     *  \\/
     *  /\\
     * B   p1
     * The adjacency list La for A is: p1, p1
     * The adjacency list Lb for B is: p0
     * Since p1 is below p0, edge (B, p0) is crossed by all edges from A and can p0 can be removed.
     * </pre>
     */
    private void countCrossingsByMergingAdjacencyLists() {
        while (!upperAdjacencies.isEmpty() && !lowerAdjacencies.isEmpty()) {
            if (isBelow(upperAdjacencies.next(), lowerAdjacencies.next())) {
                upperLowerCrossings += upperAdjacencies.size();
                lowerAdjacencies.removeFirst();
            } else if (isBelow(lowerAdjacencies.next(), upperAdjacencies.next())) {
                lowerUpperCrossings += lowerAdjacencies.size();
                upperAdjacencies.removeFirst();
            } else {
                upperLowerCrossings += upperAdjacencies.countAdjacenciesBelowNodeOfFirstPort();
                lowerUpperCrossings += lowerAdjacencies.countAdjacenciesBelowNodeOfFirstPort();
                upperAdjacencies.removeFirst();
                lowerAdjacencies.removeFirst();
            }
        }
    }

    /**
     * True if firstPort is below secondPort.
     * 
     */
    private boolean isBelow(final LPort firstPort, final LPort secondPort) {
        return portPositions.get(firstPort) > portPositions.get(secondPort);
    }

    /**
     * Keeps and manipulates all data for each node currently being examined.
     * 
     * @author alan
     *
     */
    private class AdjacencyList {
        private final LNode node;
        private NavigableMap<Integer, List<LPort>> adjacencyList;
        private int size;
        private final PortSide side;

        public AdjacencyList(final LNode node, final PortSide side) {
            this.node = node;
            this.side = side;
            fillList();
        }

        private void fillList() {
            adjacencyList = Maps.newTreeMap();
            PortIterable ports = new PortIterable(node, side, PortOrder.TOPDOWN_LEFTRIGHT);
            for (LPort port : ports) {
                List<LEdge> edges = getEdgesConnectedTo(port);
                for (LEdge edge : edges) {
                    if (!edge.isSelfLoop() && isNotInLayer(edge)) {
                        addAdjacencyOf(edge);
                        size++;
                    }
                }
            }
        }

        private List<LEdge> getEdgesConnectedTo(final LPort port) {
            return side == PortSide.WEST ? port.getIncomingEdges() : port.getOutgoingEdges();
        }

        private void addAdjacencyOf(final LEdge edge) {
            LPort adjacentPort = adjacentPortOf(edge, side);
            int adjacentPortPosition = portPositions.get(adjacentPort);
            if (!adjacencyList.isEmpty() && adjacencyList.lastKey() == adjacentPortPosition) {
                adjacencyList.lastEntry().getValue().add(adjacentPort);
            } else {
                adjacencyList.put(adjacentPortPosition, Lists.newArrayList(adjacentPort));
            }
        }

        private LPort adjacentPortOf(final LEdge edge, final PortSide portSide) {
            return portSide == PortSide.WEST ? edge.getSource() : edge.getTarget();
        }

        private boolean isNotInLayer(final LEdge edge) {
            return edge.getSource().getNode().getLayer() != edge.getTarget().getNode().getLayer();
        }

        public int countAdjacenciesBelowNodeOfFirstPort() {
            return size - adjacencyList.firstEntry().getValue().size();
        }

        public void removeFirst() {
            if (size == 0) {
                return;
            }

            List<LPort> nextPortsOnSameNode = adjacencyList.firstEntry().getValue();
            if (nextPortsOnSameNode.size() == 1) {
                adjacencyList.pollFirstEntry();
            } else {
                List<LPort> ports = adjacencyList.firstEntry().getValue();
                ports.remove(ports.size() - 1);
            }

            size--;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public LPort next() {
            if (isEmpty()) {
                return null;
            } else {
                return adjacencyList.firstEntry().getValue().get(0);
            }
        }

        public int size() {
            return size;
        }

        @Override
        public String toString() {
            return "AdjacencyList [node=" + node + ", adjacencies= " + adjacencyList + "]";
        }
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
        int portId = 0;
        for (LNode node : currentNodeOrder[layerIndex]) {
            PortIterable ports = new PortIterable(node, portSide, PortOrder.TOPDOWN_LEFTRIGHT);
            for (LPort port : ports) {
                portPositions.put(port, portId);
                if (portOrderIsFixed(node)) {
                    portId++;
                }
            }
            if (!portOrderIsFixed(node)) {
                portId++;
            }
        }
    }

    private boolean portOrderIsFixed(final LNode neighbourToUpperNode) {
        return neighbourToUpperNode.getProperty(LayoutOptions.PORT_CONSTRAINTS).isOrderFixed();
    }

    /**
     * @return the upperLowerCrossings
     */
    public int getUpperLowerCrossings() {
        return upperLowerCrossings;
    }

    /**
     * @return the lowerUpperCrossings
     */
    public int getLowerUpperCrossings() {
        return lowerUpperCrossings;
    }
}
