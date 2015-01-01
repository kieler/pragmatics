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
import java.util.Map;
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

    private final LNode upperNode;

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
     * @param map
     *            Each port must be numbered in clockwise order for each node. TODOALAN this is
     *            crap.
     */
    public TwoNodeTwoLayerCrossingCounter(final LNode upperNode, final LNode lowerNode,
            final boolean fixedLayerEastOfFreeLayer, final int[] nodeDegrees,
            final int[] nodePositions, final Map<LPort, Integer> map) {
        crossingsForOrderUpperLower = 0;
        crossingsForOrderLowerUpper = 0;
        this.upperNode = upperNode;
        this.lowerNode = lowerNode;
        this.fixedLayerEastOfFreeLayer = fixedLayerEastOfFreeLayer;
    }

    /**
     * Calculates the number of crossings for incident edges to node i and j. The crossing amounts
     * can be received with getCrossingForOrderUpperLower and getCrossingForOrderLowerUpper for the
     * order upperNode -> lowerNode or lowerNode -> upperNode respectively.
     */
    public void countCrossings() {

        if (upperNode == lowerNode) {
            return;
        }

        // -- Initialize --
        final TreeMap<Integer, List<LEdge>> upperEdges = new TreeMap<Integer, List<LEdge>>();
        int amountOfUpperEdges = fetchEdges(upperNode, upperEdges);
        LPort upperNeighbourPort = fetchNextPort(upperEdges);

        final TreeMap<Integer, List<LEdge>> lowerEdges = new TreeMap<Integer, List<LEdge>>();
        int amountOfLowerEdges = fetchEdges(lowerNode, lowerEdges);
        LPort lowerNeighbourPort = fetchNextPort(lowerEdges);

        if (upperNeighbourPort == null || lowerNeighbourPort == null) { // only self loops
            return;
        }

        // -- calculate
        boolean crossingsRemain;
        do {
            crossingsRemain = false;
            if (checkIfCausesCrossingsToUpperLower(upperNeighbourPort, lowerNeighbourPort)) {
                crossingsForOrderUpperLower += amountOfUpperEdges;
                if (!lowerEdges.isEmpty()) {
                    crossingsRemain = true;
                    lowerNeighbourPort = fetchNextPortAndRemoveEdge(lowerEdges);
                    final boolean onlySelfLoopsLeft = lowerNeighbourPort == null;
                    if (onlySelfLoopsLeft) {
                        break;
                    }
                    amountOfLowerEdges--;
                }
            } else if (checkIfCausesCrossingsToLowerUpper(upperNeighbourPort, lowerNeighbourPort)) {
                crossingsForOrderLowerUpper += amountOfLowerEdges;
                if (!upperEdges.isEmpty()) {
                    crossingsRemain = true;
                    upperNeighbourPort = fetchNextPortAndRemoveEdge(upperEdges);
                    final boolean onlySelfLoopsLeft = upperNeighbourPort == null;
                    if (onlySelfLoopsLeft) {
                        break;
                    }
                    amountOfUpperEdges--;
                }
            } else if (checkIfCausesCrossingsToBoth(lowerNeighbourPort, upperNeighbourPort)) {
                crossingsForOrderUpperLower +=
                        amountOfUpperEdges - upperEdges.get(upperEdges.firstKey()).size();
                crossingsForOrderLowerUpper +=
                        amountOfLowerEdges - lowerEdges.get(lowerEdges.firstKey()).size();
                upperNeighbourPort = fetchNextPortAndRemoveEdge(upperEdges);
                lowerNeighbourPort = fetchNextPortAndRemoveEdge(lowerEdges);
                amountOfUpperEdges--;
                amountOfLowerEdges--;
                final boolean onlySelfLoopsLeft =
                        lowerNeighbourPort == null || upperNeighbourPort == null;
                if (onlySelfLoopsLeft) {
                    break;
                }

                if (!lowerEdges.isEmpty() && !upperEdges.isEmpty()) {
                    crossingsRemain = true;
                }
            }
        } while (crossingsRemain);
    }

    private boolean checkIfCausesCrossingsToBoth(final LPort lowerNeighbourPort,
            final LPort upperNeighbourPort) {
        final LNode neighbourToLowerNode = lowerNeighbourPort.getNode();
        final LNode neighbourToUpperNode = upperNeighbourPort.getNode();
        final boolean causesCrossingsToBoth =
                neighbourToUpperNode.id == neighbourToLowerNode.id
                        && !neighbourToUpperNode.getProperty(LayoutOptions.PORT_CONSTRAINTS)
                                .isOrderFixed();
        return causesCrossingsToBoth;
    }

    private boolean checkIfCausesCrossingsToUpperLower(final LPort upperNeighbourPort,
            final LPort lowerNeighbourPort) {
        final LNode neighbourToUpperNode = upperNeighbourPort.getNode();
        final LNode neighbourToLowerNode = lowerNeighbourPort.getNode();
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
        final LNode neighbourToUpperNode = upperNeighbourPort.getNode();
        final LNode neighbourToLowerNode = lowerNeighbourPort.getNode();
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

    private int fetchEdges(final LNode node, final TreeMap<Integer, List<LEdge>> edges) {
        int amountOfEdges = 0;
        final List<LPort> ports = node.getPorts();
        for (final LPort port : ports) {
            for (final LEdge edge : fixedLayerEastOfFreeLayer ? port.getIncomingEdges() : port
                    .getOutgoingEdges()) {
                final boolean edgeIsNotSelfLoop =
                        edge.getSource().getNode() != edge.getTarget().getNode();
                final boolean edgeIsNotInLayerEdge =
                        edge.getSource().getNode().getLayer() != edge.getTarget().getNode()
                                .getLayer();

                if (edgeIsNotSelfLoop && edgeIsNotInLayerEdge) {
                    final int index =
                            fixedLayerEastOfFreeLayer ? edge.getSource().getNode().id : edge
                                    .getTarget().getNode().id;
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
    private LPort fetchNextPort(final TreeMap<Integer, List<LEdge>> edges) {
        LPort neighbourPort = null;
        while (!edges.isEmpty()) {
            final List<LEdge> edgesWithSameNodes = edges.firstEntry().getValue();
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

    private LPort fetchNextPortAndRemoveEdge(final TreeMap<Integer, List<LEdge>> edges) {
        final List<LEdge> firstEdgesWithSameNodes = edges.get(edges.firstKey());
        if (firstEdgesWithSameNodes.size() == 1) {
            edges.remove(edges.firstKey());
        } else {
            edges.get(edges.firstKey()).remove(0);
        }
        return fetchNextPort(edges);
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

}
