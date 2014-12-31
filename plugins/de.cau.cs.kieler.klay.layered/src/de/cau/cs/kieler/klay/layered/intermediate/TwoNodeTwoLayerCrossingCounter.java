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
public class TwoNodeTwoLayerCrossingCounter {

    private int crossingsForOrderUpperLower;

    private int crossingsForOrderLowerUpper;

    private final boolean fixedLayerEastOfFreeLayer;

    private final LNode lowerNode;

    private final int[] nodeDegrees;

    private final int[] nodePositions;

    private final LNode upperNode;

    private final HashMap<LPort, Integer> portIndices;

    /**
     * Create {@link TwoNodeTwoLayerCrossingCounter}. Naming assumes a left-right layer ordering.
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
     * @param portIndices
     *            Each port must be numbered in clockwise order for each node. TODOALAN this is
     *            crap.
     */
    public TwoNodeTwoLayerCrossingCounter(final LNode upperNode, final LNode lowerNode,
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
     * can be received with getCrossingForOrderUpperLower and getCrossingForOrderLowerUpper for the
     * order upperNode -> lowerNode or lowerNode -> upperNode respectively.
     */
    public void calculateCrossingNumber() {

        if (upperNode == lowerNode) {
            return;
        }

        calculateInLayerEdges();

        calculateBetweenLayerEdges();

    }

    private void calculateBetweenLayerEdges() {
        TreeMap<Integer, List<LEdge>> upperEdges = new TreeMap<Integer, List<LEdge>>();
        int amountOfUpperEdges = getEdges(fixedLayerEastOfFreeLayer, upperNode, upperEdges);
        TreeMap<Integer, List<LEdge>> lowerEdges = new TreeMap<Integer, List<LEdge>>();
        int amountOfLowerEdges = getEdges(fixedLayerEastOfFreeLayer, lowerNode, lowerEdges);

        LPort upperNeighbourPort = getNextPort(upperEdges);
        LPort lowerNeighbourPort = getNextPort(lowerEdges);

        if (upperNeighbourPort == null || lowerNeighbourPort == null) { // only self loops
            return;
        }

        LNode neighbourToUpperNode = upperNeighbourPort.getNode();
        LNode neighbourToLowerNode = lowerNeighbourPort.getNode();

        boolean stillWorking = true;
        while (stillWorking) {
            stillWorking = false;

            if (checkIfCausesCrossingsToUpperLower(upperNeighbourPort, lowerNeighbourPort)) {
                crossingsForOrderUpperLower += amountOfUpperEdges;
                if (!lowerEdges.isEmpty()) {
                    stillWorking = true;
                    lowerNeighbourPort = getNextPortAndRemoveEdge(lowerEdges);
                    boolean onlySelfLoopsLeft = lowerNeighbourPort == null;
                    if (onlySelfLoopsLeft) {
                        break;
                    }
                    neighbourToLowerNode = lowerNeighbourPort.getNode();
                    amountOfLowerEdges--;
                }
            } else if (checkIfCausesCrossingsToLowerUpper(upperNeighbourPort, lowerNeighbourPort)) {
                crossingsForOrderLowerUpper += amountOfLowerEdges;
                if (!upperEdges.isEmpty()) {
                    stillWorking = true;
                    upperNeighbourPort = getNextPortAndRemoveEdge(upperEdges);
                    boolean onlySelfLoopsLeft = lowerNeighbourPort == null;
                    if (onlySelfLoopsLeft) {
                        break;
                    }
                    neighbourToUpperNode = upperNeighbourPort.getNode();
                    amountOfUpperEdges--;
                }
            } else if (checkIfCausesCrossingsToBoth(neighbourToUpperNode, neighbourToLowerNode)) {
                crossingsForOrderUpperLower +=
                        amountOfUpperEdges - upperEdges.get(upperEdges.firstKey()).size();
                crossingsForOrderLowerUpper +=
                        amountOfLowerEdges - lowerEdges.get(lowerEdges.firstKey()).size();
                upperNeighbourPort = getNextPortAndRemoveEdge(upperEdges);
                lowerNeighbourPort = getNextPortAndRemoveEdge(lowerEdges);
                amountOfUpperEdges--;
                amountOfLowerEdges--;
                boolean onlySelfLoopsLeft =
                        lowerNeighbourPort == null || upperNeighbourPort == null;
                if (onlySelfLoopsLeft) {
                    break;
                }
                neighbourToLowerNode = lowerNeighbourPort.getNode();
                neighbourToUpperNode = upperNeighbourPort.getNode();

                if (!lowerEdges.isEmpty() && !upperEdges.isEmpty()) {
                    stillWorking = true;
                }
            }
        }
    }

    private boolean checkIfCausesCrossingsToBoth(final LNode neighbourToUpperNode,
            final LNode neighbourToLowerNode) {
        boolean causesCrossingsToBoth =
                neighbourToUpperNode.id == neighbourToLowerNode.id
                        && !neighbourToUpperNode.getProperty(LayoutOptions.PORT_CONSTRAINTS)
                                .isOrderFixed();
        return causesCrossingsToBoth;
    }

    private boolean checkIfCausesCrossingsToUpperLower(final LPort upperNeighbourPort,
            final LPort lowerNeighbourPort) {
        LNode neighbourToUpperNode = upperNeighbourPort.getNode();
        LNode neighbourToLowerNode = lowerNeighbourPort.getNode();
        boolean causesCrossingsToOrderUpperLower =
                neighbourToUpperNode.id > neighbourToLowerNode.id;

        if (neighbourToUpperNode.id == neighbourToLowerNode.id
                && neighbourToUpperNode.getProperty(LayoutOptions.PORT_CONSTRAINTS).isOrderFixed()) {
            if (fixedLayerEastOfFreeLayer) {
                causesCrossingsToOrderUpperLower |= upperNeighbourPort.id > lowerNeighbourPort.id;

            } else {
                causesCrossingsToOrderUpperLower |= upperNeighbourPort.id < lowerNeighbourPort.id;

            }
        }
        return causesCrossingsToOrderUpperLower;
    }

    private boolean checkIfCausesCrossingsToLowerUpper(final LPort upperNeighbourPort,
            final LPort lowerNeighbourPort) {
        LNode neighbourToUpperNode = upperNeighbourPort.getNode();
        LNode neighbourToLowerNode = lowerNeighbourPort.getNode();
        boolean causesCrossingsToOrderLowerUpper =
                neighbourToUpperNode.id < neighbourToLowerNode.id;
        if (neighbourToUpperNode.id == neighbourToLowerNode.id
                && neighbourToUpperNode.getProperty(LayoutOptions.PORT_CONSTRAINTS).isOrderFixed()) {
            if (fixedLayerEastOfFreeLayer) {
                causesCrossingsToOrderLowerUpper |= upperNeighbourPort.id < lowerNeighbourPort.id;
            } else {
                causesCrossingsToOrderLowerUpper |= upperNeighbourPort.id > lowerNeighbourPort.id;
            }
        }
        return causesCrossingsToOrderLowerUpper;
    }

    /**
     * 
     */
    private void calculateInLayerEdges() {
        TwoNodeInLayerEdgeCrossingCounter inLayerEdgeCounter =
                new TwoNodeInLayerEdgeCrossingCounter(nodePositions, nodeDegrees,
                        fixedLayerEastOfFreeLayer, portIndices);
        inLayerEdgeCounter.countInLayerEdgeCrossings(upperNode, lowerNode);
        crossingsForOrderLowerUpper += inLayerEdgeCounter.getCrossingsForOrderLowerUpper();
        crossingsForOrderUpperLower += inLayerEdgeCounter.getCrossingsForOrderUpperLower();
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

    private int getEdges(final boolean forwardSweep, final LNode node,
            final TreeMap<Integer, List<LEdge>> edges) {
        int amountOfEdges = 0;
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
                    if (edges.containsKey(index)) {
                        edges.get(index).add(edge);
                    } else {
                        edges.put(index, Lists.newArrayList(edge));
                    }
                    amountOfEdges++;
                }
            }
        }
        return amountOfEdges;
    }

    /**
     * @param jEdges
     * @return
     */
    private LPort getNextPort(final TreeMap<Integer, List<LEdge>> edges) {
        LPort neighbourPort = null;
        while (!edges.isEmpty()) {
            List<LEdge> edgesWithSameNodes = edges.firstEntry().getValue();
            if (edgesWithSameNodes.get(0).getSource().getNode() == edgesWithSameNodes.get(0)
                    .getTarget().getNode()) {
                edges.remove(edges.firstKey());
            } else {
                neighbourPort =
                        fixedLayerEastOfFreeLayer ? edgesWithSameNodes.get(0).getSource()
                                : edgesWithSameNodes.get(0).getTarget();
                break;
            }
        }
        return neighbourPort;
    }

    private LPort getNextPortAndRemoveEdge(final TreeMap<Integer, List<LEdge>> edges) {
        List<LEdge> firstEdgesWithSameNodes = edges.get(edges.firstKey());
        if (firstEdgesWithSameNodes.size() == 1) {
            edges.remove(edges.firstKey());
        } else {
            edges.get(edges.firstKey()).remove(0);
        }
        return getNextPort(edges);
    }

}
