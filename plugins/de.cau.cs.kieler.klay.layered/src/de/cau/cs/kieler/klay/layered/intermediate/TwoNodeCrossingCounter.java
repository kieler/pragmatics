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

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

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
public class TwoNodeCrossingCounter {

    private int crossingsForOrderUpperLower;

    private int crossingsForOrderLowerUpper;

    private final boolean fixedLayerEastOfFreeLayer;

    private final LNode lowerNode;

    private final int[] nodeDegrees;

    private final int[] nodePositions;

    private final LNode upperNode;

    private final HashMap<LPort, Integer> portIndices;

    /**
     * Create {@link TwoNodeCrossingCounter}. Naming assumes a left-right layer ordering.
     *
     * @param upperNode
     *            The upper node assuming left-right layer ordering.
     * @param lowerNode
     *            The lower node assuming left-right layer ordering.
     * @param fixedLayerEastOfFreeLayer
     *            Order of layers in question.
     * @param nodeDegrees
     *            Amount of incident edges to node in the given order.
     * @param nodePositions
     *            Position of nodes ordered by node.id
     */
    public TwoNodeCrossingCounter(final LNode upperNode, final LNode lowerNode,
            final boolean fixedLayerEastOfFreeLayer, final int[] nodeDegrees,
            final int[] nodePositions, final HashMap<LPort, Integer> portIndices) {
        crossingsForOrderUpperLower = 0;
        crossingsForOrderLowerUpper = 0;
        this.upperNode = upperNode;
        this.lowerNode = lowerNode;
        this.fixedLayerEastOfFreeLayer = fixedLayerEastOfFreeLayer;
        this.nodeDegrees = nodeDegrees;
        this.nodePositions = nodePositions;
        this.portIndices = portIndices;
    }

    /**
     * Calculates the number of crossings for incident edges to node i and j. The crossing amounts
     * can be received with getCrossingForOrderJI and getCrossingForOrderIJ. TODOALAN change names:
     * IJ,nEdges
     */
    public void calculateCrossingNumber() {

        if (upperNode == lowerNode) {
            return;
        }

        TreeMap<Integer, EdgeAndCardinality> iEdges =
                new TreeMap<Integer, TwoNodeCrossingCounter.EdgeAndCardinality>();
        int nIEdges = getEdges(fixedLayerEastOfFreeLayer, upperNode, iEdges);
        TreeMap<Integer, EdgeAndCardinality> jEdges =
                new TreeMap<Integer, TwoNodeCrossingCounter.EdgeAndCardinality>();

        int nJEdges = getEdges(fixedLayerEastOfFreeLayer, lowerNode, jEdges);

        LPort iNeighbourPort = null, jNeighbourPort = null;
        LNode neighbourToINode = null, neighbourToJNode = null;
        boolean stillWorking = true;
        while (stillWorking) {
            stillWorking = false;
            if (iNeighbourPort == null || jNeighbourPort == null) {

                iNeighbourPort = getNextPort(iEdges);
                jNeighbourPort = getNextPort(jEdges);

                if (iNeighbourPort == null || jNeighbourPort == null) { // only self loops
                    break;
                }

                neighbourToINode = iNeighbourPort.getNode();
                neighbourToJNode = jNeighbourPort.getNode();
            }

            boolean causesCrossingsToOrderIJ = neighbourToINode.id > neighbourToJNode.id;
            boolean causesCrossingsToOrderJI = neighbourToINode.id < neighbourToJNode.id;
            if (neighbourToINode.id == neighbourToJNode.id
                    && neighbourToINode.getProperty(LayoutOptions.PORT_CONSTRAINTS).isOrderFixed()) {
                if (fixedLayerEastOfFreeLayer) {
                    causesCrossingsToOrderIJ |= iNeighbourPort.id > jNeighbourPort.id;
                    causesCrossingsToOrderJI |= iNeighbourPort.id < jNeighbourPort.id;
                } else {
                    causesCrossingsToOrderIJ |= iNeighbourPort.id < jNeighbourPort.id;
                    causesCrossingsToOrderJI |= iNeighbourPort.id > jNeighbourPort.id;
                }
            }

            boolean causesCrossingsToBoth =
                    neighbourToINode.id == neighbourToJNode.id
                            && !neighbourToINode.getProperty(LayoutOptions.PORT_CONSTRAINTS)
                                    .isOrderFixed();

            if (causesCrossingsToOrderIJ) {
                crossingsForOrderUpperLower += nIEdges;
                if (!jEdges.isEmpty()) {
                    stillWorking = true;
                    jNeighbourPort = getNextPortAndRemoveEdge(jEdges);
                    if (jNeighbourPort == null) { // only self loops left
                        break;
                    }
                    neighbourToJNode = jNeighbourPort.getNode();
                    nJEdges--;
                }
            } else if (causesCrossingsToOrderJI) {
                crossingsForOrderLowerUpper += nJEdges;
                if (!iEdges.isEmpty()) {
                    stillWorking = true;
                    iNeighbourPort = getNextPortAndRemoveEdge(iEdges);
                    if (iNeighbourPort == null) { // only self loops left
                        break;
                    }
                    neighbourToINode = iNeighbourPort.getNode();
                    nIEdges--;
                }
            } else if (causesCrossingsToBoth) {
                // TODOALAN rename
                crossingsForOrderUpperLower += getCrossingsForSameNode(iEdges, nIEdges);
                crossingsForOrderLowerUpper += getCrossingsForSameNode(jEdges, nJEdges);
                iNeighbourPort = getNextPortAndRemoveEdge(iEdges);
                jNeighbourPort = getNextPortAndRemoveEdge(jEdges);
                nIEdges--;
                nJEdges--;
                if (jNeighbourPort == null || iNeighbourPort == null) {
                    // only self loops left
                    break;
                }
                neighbourToJNode = jNeighbourPort.getNode();
                neighbourToINode = iNeighbourPort.getNode();

                if (!jEdges.isEmpty() && !iEdges.isEmpty()) {
                    stillWorking = true;
                }
            }
        }

        countInLayerEdgeCrossings(upperNode, lowerNode);
    }

    private boolean collectInLayerEdges(final LNode currNode,
            final LinkedList<LEdge> currNodeInLayerEdges, final PortSide portSide) {
        boolean currNodeHasInLayerEdges = false;
        for (LPort port : currNode.getPorts(portSide)) {
            for (LEdge edge : port.getConnectedEdges()) {
                boolean inLayerIncomingEdge =
                        edge.getSource().getNode() != currNode
                                && edge.getSource().getNode().getLayer() == currNode.getLayer();
                boolean inLayerOutgoingEdge =
                        edge.getTarget().getNode() != currNode
                                && edge.getTarget().getNode().getLayer() == currNode.getLayer();
                boolean currEdgeHasInLayerEdges = inLayerIncomingEdge || inLayerOutgoingEdge;

                if (currEdgeHasInLayerEdges) {
                    currNodeInLayerEdges.add(edge);
                    currNodeHasInLayerEdges |= currEdgeHasInLayerEdges;
                }
            }
        }
        return currNodeHasInLayerEdges;
    }

    /**
     * TODOALAN explain this: if (otherNodeId > thisNodeId){ inLayerCrossingsIJ +=
     * nodeDegrees[otherNodeId]; } else if (otherNodeId < thisNodeId){ inLayerCrossingsJI +=
     * nodeDegrees[otherNodeId]; }.
     */
    private void countInLayerEdgeCrossings(final LNode currNode, final LNode nextNode) {
        if (currNode == nextNode) {
            return;
        }

        LinkedList<LEdge> currNodeInLayerEdges = new LinkedList<LEdge>();
        LinkedList<LEdge> nextNodeInLayerEdges = new LinkedList<LEdge>();
        PortSide portSide = fixedLayerEastOfFreeLayer ? PortSide.WEST : PortSide.EAST;
        boolean currNodeHasInLayerEdges =
                collectInLayerEdges(currNode, currNodeInLayerEdges, portSide);
        boolean nextNodeHasInLayerEdges =
                collectInLayerEdges(nextNode, nextNodeInLayerEdges, portSide);
        // change to return list and check on size TODOALAN
        if (currNodeHasInLayerEdges) {
            for (LEdge edge : currNodeInLayerEdges) {
                boolean currNodeIsSource = edge.getSource().getNode() == currNode;
                LPort edgeEndPort = currNodeIsSource ? edge.getTarget() : edge.getSource();
                int edgeEndNodeId = edgeEndPort.getNode().id;
                if (nodePositions[edgeEndNodeId] > nodePositions[nextNode.id]) {
                    crossingsForOrderUpperLower += nodeDegrees[nextNode.id];
                } else if (nodePositions[edgeEndNodeId] < nodePositions[currNode.id]) {
                    crossingsForOrderLowerUpper += nodeDegrees[nextNode.id];
                } else if (nodePositions[edgeEndNodeId] == nodePositions[nextNode.id]
                        && edgeEndPort.getProperty(LayoutOptions.PORT_CONSTRAINTS).isOrderFixed()) {
                    // Western ports (accessed by forward sweep) are numbered bottom up.
                    // nodeDegrees is the degree of the node on the side we are currently working
                    // on.
                    if (fixedLayerEastOfFreeLayer) {
                        crossingsForOrderUpperLower +=
                                nodeDegrees[edgeEndNodeId] - portIndices.get(edgeEndPort);
                        crossingsForOrderLowerUpper += portIndices.get(edgeEndPort);
                    } else {
                        crossingsForOrderUpperLower += portIndices.get(edgeEndPort);
                        crossingsForOrderLowerUpper +=
                                nodeDegrees[edgeEndNodeId] - portIndices.get(edgeEndPort);
                    }
                }
            }
        }
        if (nextNodeHasInLayerEdges) { // TODOALAN this seems very similar
            for (LEdge edge : nextNodeInLayerEdges) {
                boolean edgeAlredyCounted =
                        edge.getSource().getNode() == currNode
                                || edge.getTarget().getNode() == currNode;
                if (edgeAlredyCounted) {
                    continue;
                }
                boolean nextNodeIsSource = edge.getSource().getNode() == nextNode;
                LPort edgeEndPort = nextNodeIsSource ? edge.getTarget() : edge.getSource();
                int edgeEndNodeId = edgeEndPort.getNode().id;

                if (nodePositions[edgeEndNodeId] < nodePositions[currNode.id]) {
                    crossingsForOrderUpperLower += nodeDegrees[currNode.id];
                } else if (nodePositions[edgeEndNodeId] > nodePositions[currNode.id]) {
                    crossingsForOrderLowerUpper += nodeDegrees[currNode.id];
                } else if (nodePositions[edgeEndNodeId] == nodePositions[currNode.id]
                        && edgeEndPort.getProperty(LayoutOptions.PORT_CONSTRAINTS).isOrderFixed()) {
                    // Western ports (accessed by forward sweep) are numbered bottom up.
                    // nodeDegrees is the degree of the node on the side we are currently working
                    // on.
                    if (fixedLayerEastOfFreeLayer) {
                        crossingsForOrderUpperLower +=
                                nodeDegrees[edgeEndNodeId] - portIndices.get(edgeEndPort);
                        crossingsForOrderLowerUpper += portIndices.get(edgeEndPort);
                    } else {
                        crossingsForOrderUpperLower += portIndices.get(edgeEndPort);
                        crossingsForOrderLowerUpper +=
                                nodeDegrees[edgeEndNodeId] - portIndices.get(edgeEndPort);
                    }
                }
            }
        }
    }

    /**
     * @return the crossingsForOrderUpperLower
     */
    public int getCrossingsForOrderUpperLower() {
        return crossingsForOrderUpperLower;
    }

    /**
     * @return the crossingsForOrderLowerUpper
     */
    public int getCrossingsForOrderLowerUpper() {
        return crossingsForOrderLowerUpper;
    }

    private int getCrossingsForSameNode(final TreeMap<Integer, EdgeAndCardinality> edges,
            final int nEdges) {
        return nEdges - edges.get(edges.firstKey()).getCardinality();
    }

    private int getEdges(final boolean forwardSweep, final LNode node,
            final TreeMap<Integer, EdgeAndCardinality> edges) {
        int nEdges = 0;
        List<LPort> ports = node.getPorts();
        for (LPort port : ports) {
            for (LEdge edge : forwardSweep ? port.getIncomingEdges() : port.getOutgoingEdges()) {
                boolean edgeIsNotSelfLoop =
                        edge.getSource().getNode() != edge.getTarget().getNode();
                boolean edgeIsNotInLayerEdge =
                        edge.getSource().getNode().getLayer() != edge.getTarget().getNode()
                                .getLayer();

                if (edgeIsNotSelfLoop && edgeIsNotInLayerEdge) {
                    int index =
                            forwardSweep ? edge.getSource().getNode().id : edge.getTarget()
                                    .getNode().id;
                    int cardinality = 1;
                    if (edges.containsKey(index)) {
                        cardinality += edges.get(index).getCardinality();
                    }
                    edges.put(index, new EdgeAndCardinality(cardinality, edge));
                    nEdges++;
                }
            }
        }
        return nEdges;
    }

    /**
     * @param jEdges
     * @return
     */
    private LPort getNextPort(final TreeMap<Integer, EdgeAndCardinality> edges) {
        LPort neighbourPort = null;
        EdgeAndCardinality edgeAndCardinality;
        while (!edges.isEmpty()) {
            edgeAndCardinality = edges.get(edges.firstKey());
            if (edgeAndCardinality.getEdge().getSource().getNode() == edgeAndCardinality.getEdge()
                    .getTarget().getNode()) {
                edges.remove(edges.firstKey());
            } else {
                neighbourPort =
                        fixedLayerEastOfFreeLayer ? edgeAndCardinality.getEdge().getSource()
                                : edgeAndCardinality.getEdge().getTarget();
                break;
            }
        }
        return neighbourPort;
    }

    private LPort getNextPortAndRemoveEdge(final TreeMap<Integer, EdgeAndCardinality> edges) {
        EdgeAndCardinality firstEdge = edges.get(edges.firstKey());
        if (firstEdge.getCardinality() == 1) {
            edges.remove(edges.firstKey());
        } else {
            edges.get(edges.firstKey()).setCardinality(
                    edges.get(edges.firstKey()).getCardinality() - 1);
        }
        return getNextPort(edges);
    }

    /**
     * Hidden inner class collecting the edge with the number of similar edges.
     *
     * @author alan
     *
     */
    private static class EdgeAndCardinality { // TODOALAN consider using Multimaps
        private int cardinality;

        private LEdge edge;

        public EdgeAndCardinality(final int cardinality, final LEdge edge) {
            setCardinality(cardinality);
            setEdge(edge);
        }

        /**
         * @return the cardinality
         */
        public int getCardinality() {
            return cardinality;
        }

        /**
         * @return the edge
         */
        public LEdge getEdge() {
            return edge;
        }

        /**
         * @param cardinality
         *            the cardinality to set
         */
        public void setCardinality(final int cardinality) {
            this.cardinality = cardinality;
        }

        /**
         * @param edge
         *            the edge to set
         */
        public void setEdge(final LEdge edge) {
            this.edge = edge;
        }

        @Override
        public String toString() {
            return "Edge [cardinality=" + getCardinality() + ", " + getEdge() + "]";
        }
    }

}
