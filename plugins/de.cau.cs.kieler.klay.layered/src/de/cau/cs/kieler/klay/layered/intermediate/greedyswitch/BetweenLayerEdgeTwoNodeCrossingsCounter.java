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

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LNode.PortOrder;
import de.cau.cs.kieler.klay.layered.graph.LPort;

/**
 * Calculates the number of crossings for incident edges to node i and j. Prerequisites: Node.id's
 * set to original order
 *
 * @author alan
 *
 */
class BetweenLayerEdgeTwoNodeCrossingsCounter {
    private int upperLowerCrossings;
    private int lowerUpperCrossings;
    private AdjacencyList upperAdjacencies;
    private AdjacencyList lowerAdjacencies;
    private final LNode[][] currentNodeOrder;
    private final int freeLayerIndex;
    private final Map<LPort, Integer> portPositions;
    private final Map<LNode, AdjacencyList> easternAdjacencies;
    private final Map<LNode, AdjacencyList> westernAdjacencies;

    /**
     * Create {@link BetweenLayerEdgeTwoNodeCrossingsCounter}. Naming assumes a left-right layer
     * ordering.
     * 
     * @param currentNodeOrder
     *            Currently considered node ordering.
     * @param freeLayerIndex
     *            Index of free layer.
     */
    public BetweenLayerEdgeTwoNodeCrossingsCounter(final LNode[][] currentNodeOrder,
            final int freeLayerIndex) {
        portPositions = Maps.newHashMap();
        easternAdjacencies = Maps.newHashMap();
        westernAdjacencies = Maps.newHashMap();
        this.currentNodeOrder = currentNodeOrder;
        this.freeLayerIndex = freeLayerIndex;
        setPortPositionsForNeighbouringLayers();
    }

    private void setPortPositionsForNeighbouringLayers() {
        if (freeLayerIsNotFirstLayer()) {
            setPortPositionsForLayer(freeLayerIndex - 1, PortSide.EAST);
        }
        if (freeLayerIsNotLastLayer()) {
            setPortPositionsForLayer(freeLayerIndex + 1, PortSide.WEST);
        }
    }

    private boolean freeLayerIsNotFirstLayer() {
        return freeLayerIndex > 0;
    }

    private boolean freeLayerIsNotLastLayer() {
        return freeLayerIndex < currentNodeOrder.length - 1;
    }

    private void setPortPositionsForLayer(final int layerIndex, final PortSide side) {
        int portId = 0;
        for (LNode node : currentNodeOrder[layerIndex]) {
            Iterable<LPort> ports = node.getPorts(side, PortOrder.NORTHSOUTH_EASTWEST);
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
     * Calculates the number of crossings for incident edges coming from the west to node i and j.
     * The crossing numbers can be received with getCrossingForOrderUpperLower and
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
     * The crossing numbers can be received with getCrossingForOrderUpperLower and
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
     * TODO-alan check for usage of the word amount Calculates the number of crossings for incident
     * edges coming from the both sides to node i and j. The crossing numbers can be received with
     * getCrossingForOrderUpperLower and getCrossingForOrderLowerUpper for the order upperNode ->
     * lowerNode or lowerNode -> upperNode respectively.
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
        upperLowerCrossings = 0;
        lowerUpperCrossings = 0;
    }

    private void addEasternCrossings(final LNode upperNode, final LNode lowerNode) {
        upperAdjacencies = getAdjacencyFor(upperNode, PortSide.EAST, easternAdjacencies);
        lowerAdjacencies = getAdjacencyFor(lowerNode, PortSide.EAST, easternAdjacencies);
        if (upperAdjacencies.size() == 0 || lowerAdjacencies.size() == 0) {
            return;
        }
        countCrossingsByMergingAdjacencyLists();
    }

    private AdjacencyList getAdjacencyFor(final LNode node, final PortSide side,
            final Map<LNode, AdjacencyList> adjacencies) {
        if (adjacencies.isEmpty()) {
            for (LNode n : currentNodeOrder[freeLayerIndex]) {
                adjacencies.put(n, new AdjacencyList(n, side));
            }
        }
        AdjacencyList aL = adjacencies.get(node);
        aL.reset();
        return aL;

    }

    private void addWesternCrossings(final LNode upperNode, final LNode lowerNode) {
        upperAdjacencies = getAdjacencyFor(upperNode, PortSide.WEST, westernAdjacencies);
        lowerAdjacencies = getAdjacencyFor(lowerNode, PortSide.WEST, westernAdjacencies);
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
            if (isBelow(upperAdjacencies.first(), lowerAdjacencies.first())) {
                upperLowerCrossings += upperAdjacencies.size();
                lowerAdjacencies.removeFirst();
            } else if (isBelow(lowerAdjacencies.first(), upperAdjacencies.first())) {
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

    private boolean isBelow(final int firstPort, final int secondPort) {
        return firstPort > secondPort;
    }

    /**
     * The adjacency list of a node holds the position of connected ports in a neighboring layer on
     * the given side. Since we want to save it for further use, the remove operation does not
     * actually delete the entries in the adjacency list. Instead we use currentIndex, currentSize
     * and currentAmount (in the inner class) to show the current state of the list. Use reset() to
     * reset to the original state.
     * 
     * @author alan
     *
     */
    private class AdjacencyList {
        private final LNode node;
        private final List<Adjacency> adjacencyList;
        private final PortSide side;
        private int size;
        private int currentSize;
        private int currentIndex;

        public AdjacencyList(final LNode node, final PortSide side) {
            this.node = node;
            this.side = side;
            adjacencyList = Lists.newArrayList();
            getAdjacenciesSortedByPosition();
        }

        private void getAdjacenciesSortedByPosition() {
            iterateTroughEdgesCollectingAdjacencies();

            Collections.sort(adjacencyList);
        }

        private void iterateTroughEdgesCollectingAdjacencies() {
            Iterable<LPort> ports = node.getPorts(side, PortOrder.NORTHSOUTH_EASTWEST);
            for (LPort port : ports) {
                List<LEdge> edges = getEdgesConnectedTo(port);
                for (LEdge edge : edges) {
                    if (!edge.isSelfLoop() && isNotInLayer(edge)) {
                        addAdjacencyOf(edge);
                        size++;
                        currentSize++;
                    }
                }
            }
        }

        private List<LEdge> getEdgesConnectedTo(final LPort port) {
            return side == PortSide.WEST ? port.getIncomingEdges() : port.getOutgoingEdges();
        }

        private boolean isNotInLayer(final LEdge edge) {
            return edge.getSource().getNode().getLayer() != edge.getTarget().getNode().getLayer();
        }

        private void addAdjacencyOf(final LEdge edge) {
            LPort adjacentPort = adjacentPortOf(edge, side);
            int adjacentPortPosition = portPositions.get(adjacentPort);
            int lastIndex = adjacencyList.size() - 1;
            if (!adjacencyList.isEmpty()
                    && adjacencyList.get(lastIndex).position == adjacentPortPosition) {
                adjacencyList.get(lastIndex).amount++;
                adjacencyList.get(lastIndex).currentAmount++;
            } else {
                adjacencyList.add(new Adjacency(adjacentPortPosition, adjacentPort));
            }
        }

        private LPort adjacentPortOf(final LEdge edge, final PortSide side) {
            return side == PortSide.WEST ? edge.getSource() : edge.getTarget();
        }

        public void reset() {
            currentIndex = 0;
            currentSize = size;
            if (!isEmpty()) {
                currentAdjacency().reset();
            }
        }

        public int countAdjacenciesBelowNodeOfFirstPort() {
            return currentSize - currentAdjacency().currentAmount;
        }

        public void removeFirst() {
            if (isEmpty()) {
                return;
            }
            Adjacency currentEntry = currentAdjacency();
            if (currentEntry.currentAmount == 1) {
                incrementCurrentIndex();
            } else {
                currentEntry.currentAmount--;
            }

            currentSize--;
        }

        private void incrementCurrentIndex() {
            currentIndex++;
            // reset Adjacency
            if (currentIndex < adjacencyList.size()) {
                currentAdjacency().reset();
            }
        }

        public boolean isEmpty() {
            return currentSize == 0;
        }

        public int first() {
            return currentAdjacency().position;
        }

        public int size() {
            return currentSize;
        }

        private Adjacency currentAdjacency() {
            return adjacencyList.get(currentIndex);
        }

        @Override
        public String toString() {
            return "AdjacencyList [node=" + node + ", adjacencies= " + adjacencyList + "]";
        }

        private class Adjacency implements Comparable<Adjacency> {
            public final int position;
            public int amount;
            public int currentAmount;

            public Adjacency(final int adjacentPortPosition, final LPort port) {
                position = adjacentPortPosition;
                amount = 1;
                currentAmount = 1;
            }

            public void reset() {
                currentAmount = amount;
            }

            public int compareTo(final Adjacency o) {
                return Integer.compare(position, o.position);
            }

            @Override
            public String toString() {
                return "Adjacency [position=" + position + ", amount=" + amount
                        + ", currentAmount=" + currentAmount + "]";
            }
        }
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
