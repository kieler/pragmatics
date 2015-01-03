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

import java.util.LinkedList;
import java.util.Map;

import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;

/**
 * Counts the worst case amount of in-layer Crossings between two nodes. Nodes with multiple in-
 * layer edges in both directions may cause too many crossings.
 *
 * @author alan
 *
 */
public class TwoNodeInLayerEdgeCrossingCounter {
    private int crossingsForOrderUpperLower;
    private int crossingsForOrderLowerUpper;
    private final int[] nodePositions;
    private final int[] nodeDegrees;
    private final boolean fixedLayerEastOfFreeLayer;
    private final Map<LPort, Integer> portIndices;

    /**
     * Constructs Counter Class for In-LayerEdge Crossing Counters.
     *
     * @param nodePositions
     *            Current Positions sorted by node.id
     * @param nodeDegrees
     *            Amount of edges into each node on currently viewed side.
     * @param fixedLayerEastOfFreeLayer
     *            True if we are sweeping forward
     * @param map
     *            Map of Ports to Indices which number the ports on each side.
     */
    public TwoNodeInLayerEdgeCrossingCounter(final int[] nodePositions, final int[] nodeDegrees,
            final boolean fixedLayerEastOfFreeLayer, final Map<LPort, Integer> map) {
        this.nodePositions = nodePositions;
        this.nodeDegrees = nodeDegrees;
        this.fixedLayerEastOfFreeLayer = fixedLayerEastOfFreeLayer;
        portIndices = map;
    }

    /**
     * Counts the worst case amount of in-layer Crossings between two nodes. Nodes with multiple in-
     * layer edges in both directions may cause too many crossings.
     *
     * @param upperNode
     *            the upper node assuming left-right order.
     * @param lowerNode
     *            the lower node assuming left-right order.
     */
    public void countCrossings(final LNode upperNode, final LNode lowerNode) {
        if (upperNode == lowerNode) {
            return;
        }

        PortSide portSide = fixedLayerEastOfFreeLayer ? PortSide.WEST : PortSide.EAST;

        LinkedList<LEdge> upperNodeInLayerEdges = collectInLayerEdges(upperNode, portSide);
        if (!upperNodeInLayerEdges.isEmpty()) {
            fetchCrossingsFromUpper(upperNode, lowerNode, upperNodeInLayerEdges);
        }

        LinkedList<LEdge> lowerNodeInLayerEdges = collectInLayerEdges(lowerNode, portSide);
        if (!lowerNodeInLayerEdges.isEmpty()) {
            fetchCrossingsFromLower(upperNode, lowerNode, lowerNodeInLayerEdges);
        }
    }

    private void fetchCrossingsFromLower(final LNode upperNode, final LNode lowerNode,
            final LinkedList<LEdge> lowerNodeInLayerEdges) {
        for (final LEdge edge : lowerNodeInLayerEdges) {

            boolean edgeAlredyCounted =
                    edge.getSource().getNode() == upperNode
                            || edge.getTarget().getNode() == upperNode;
            if (edgeAlredyCounted) {
                continue;
            }

            boolean lowerNodeIsSource = edge.getSource().getNode() == lowerNode;
            LPort edgeEndPort = lowerNodeIsSource ? edge.getTarget() : edge.getSource();
            int edgeEndNodeId = edgeEndPort.getNode().id;

            if (nodePositions[edgeEndNodeId] < nodePositions[upperNode.id]) {
                crossingsForOrderUpperLower += nodeDegrees[upperNode.id];
            } else if (nodePositions[edgeEndNodeId] > nodePositions[upperNode.id]) {
                crossingsForOrderLowerUpper += nodeDegrees[upperNode.id];
            } else if (nodePositions[edgeEndNodeId] == nodePositions[upperNode.id]
                    && edgeEndPort.getProperty(LayoutOptions.PORT_CONSTRAINTS).isOrderFixed()) {
                countInLayerEdgeCrossingsWithFixedPortsOfTargetNode(edgeEndPort, edgeEndNodeId);
            }
        }
    }

    private void fetchCrossingsFromUpper(final LNode upperNode, final LNode lowerNode,
            final LinkedList<LEdge> upperNodeInLayerEdges) {
        for (LEdge edge : upperNodeInLayerEdges) {
            boolean upperNodeIsSource = edge.getSource().getNode() == upperNode;
            LPort edgeEndPort = upperNodeIsSource ? edge.getTarget() : edge.getSource();
            int edgeEndNodeId = edgeEndPort.getNode().id;
            if (nodePositions[edgeEndNodeId] > nodePositions[lowerNode.id]) {
                crossingsForOrderUpperLower += nodeDegrees[lowerNode.id];
            } else if (nodePositions[edgeEndNodeId] < nodePositions[upperNode.id]) {
                crossingsForOrderLowerUpper += nodeDegrees[lowerNode.id];
            } else if (nodePositions[edgeEndNodeId] == nodePositions[lowerNode.id]
                    && edgeEndPort.getProperty(LayoutOptions.PORT_CONSTRAINTS).isOrderFixed()) {
                countInLayerEdgeCrossingsWithFixedPortsOfTargetNode(edgeEndPort, edgeEndNodeId);
            }
        }
    }

    private LinkedList<LEdge> collectInLayerEdges(final LNode node, final PortSide portSide) {
        final LinkedList<LEdge> inLayerEdges = new LinkedList<LEdge>();
        for (LPort port : node.getPorts(portSide)) {
            for (LEdge edge : port.getConnectedEdges()) {
                boolean inLayerIncomingEdge =
                        edge.getSource().getNode() != node
                                && edge.getSource().getNode().getLayer() == node.getLayer();
                boolean inLayerOutgoingEdge =
                        edge.getTarget().getNode() != node
                                && edge.getTarget().getNode().getLayer() == node.getLayer();
                boolean currEdgeHasInLayerEdges = inLayerIncomingEdge || inLayerOutgoingEdge;

                if (currEdgeHasInLayerEdges) {
                    inLayerEdges.add(edge);
                }
            }
        }
        return inLayerEdges;
    }

    /**
     * Western ports (accessed by forward sweep) are numbered bottom-up and eastern Ports (on the
     * way back are numbered top-down. nodeDegrees keeps the degrees of the nodes on the side we are
     * currently working on.
     */
    private void countInLayerEdgeCrossingsWithFixedPortsOfTargetNode(final LPort edgeEndPort,
            final int edgeEndNodeId) {
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
