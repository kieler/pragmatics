package de.cau.cs.kieler.klay.layered.intermediate;

import java.util.HashMap;
import java.util.LinkedList;

import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;

public class TwoNodeInLayerEdgeCrossingCounter {
    private int crossingsForOrderUpperLower;

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

    private int crossingsForOrderLowerUpper;
    private int[] nodePositions;
    private int[] nodeDegrees;
    private boolean fixedLayerEastOfFreeLayer;
    private HashMap<LPort, Integer> portIndices;

    /**
     * Constructs Counter Class for In-LayerEdge Crossing Counters.
     * 
     * @param nodePositions
     * @param nodeDegrees
     * @param fixedLayerEastOfFreeLayer
     * @param portIndices
     */
    public TwoNodeInLayerEdgeCrossingCounter(final int[] nodePositions, final int[] nodeDegrees,
            final boolean fixedLayerEastOfFreeLayer, final HashMap<LPort, Integer> portIndices) {
        this.nodePositions = nodePositions;
        this.nodeDegrees = nodeDegrees;
        this.fixedLayerEastOfFreeLayer = fixedLayerEastOfFreeLayer;
        this.portIndices = portIndices;
    }

    public void countInLayerEdgeCrossings(final LNode upperNode, final LNode lowerNode) {
        if (upperNode == lowerNode) {
            return;
        }

        PortSide portSide = fixedLayerEastOfFreeLayer ? PortSide.WEST : PortSide.EAST;
        LinkedList<LEdge> upperNodeInLayerEdges = collectInLayerEdges(upperNode, portSide);
        LinkedList<LEdge> lowerNodeInLayerEdges = collectInLayerEdges(lowerNode, portSide);

        if (!upperNodeInLayerEdges.isEmpty()) {
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

        if (!lowerNodeInLayerEdges.isEmpty()) {
            for (LEdge edge : lowerNodeInLayerEdges) {

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
    }

    private LinkedList<LEdge> collectInLayerEdges(final LNode node, final PortSide portSide) {
        LinkedList<LEdge> inLayerEdges = new LinkedList<LEdge>();
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
}
