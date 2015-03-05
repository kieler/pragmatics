package de.cau.cs.kieler.klay.layered.intermediate.greedyswitch;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.intermediate.greedyswitch.PortIterable.PortOrder;

/**
 * TODO-alan: Consider renaming to two node, and losing the upper node lowernode business.
 * 
 * @author alan
 *
 */
class InLayerEdgeNeighboringNodeCrossingCounter extends InLayerEdgeCrossingCounter {

    private final List<ComparableEdgeAndPort> relevantEdgesAndPorts;
    private int upperLowerCrossings;
    private int lowerUpperCrossings;
    private LNode upperNode;
    private LNode lowerNode;

    public InLayerEdgeNeighboringNodeCrossingCounter(final LNode[] nodeOrder) {
        super(nodeOrder);
        relevantEdgesAndPorts = Lists.newArrayList();
    }

    public void countCrossingsBetweenNeighbouringNodes(final LNode upperNode, final LNode lowerNode) {
        this.upperNode = upperNode;
        this.lowerNode = lowerNode;

        upperLowerCrossings = countCrossingsOnSide(PortSide.EAST);
        upperLowerCrossings += countCrossingsOnSide(PortSide.WEST);

        notifyNodeSwitch(upperNode, lowerNode);

        lowerUpperCrossings = countCrossingsOnSide(PortSide.EAST);
        lowerUpperCrossings += countCrossingsOnSide(PortSide.WEST);

        notifyNodeSwitch(lowerNode, upperNode);
    }

    private int countCrossingsOnSide(final PortSide side) {
        relevantEdgesAndPorts.clear();

        addEdgesAndPortsConnectedToNodesAndSort(side);

        return iterateThroughRelevantEdgesAndPortsAndCountCrossings();
    }

    private void addEdgesAndPortsConnectedToNodesAndSort(final PortSide side) {
        iterateThroughEdgesAndCollectThem(upperNode, side);
        iterateThroughEdgesAndCollectThem(lowerNode, side);
        Collections.sort(relevantEdgesAndPorts);
    }

    private void iterateThroughEdgesAndCollectThem(final LNode node, final PortSide side) {
        Iterable<LPort> ports = new PortIterable(node, side, PortOrder.TOPDOWN_LEFTRIGHT);
        for (LPort port : ports) {
            for (LEdge edge : port.getConnectedEdges()) {
                if (!edge.isSelfLoop()) {
                    addThisEndOrBothEndsOfEdge(node, port, edge);
                }
            }
        }
    }

    private int iterateThroughRelevantEdgesAndPortsAndCountCrossings() {
        int crossings = 0;
        for (ComparableEdgeAndPort eP : relevantEdgesAndPorts) {
            crossings += super.countCrossingsOn(eP.edge, eP.port);
        }
        return crossings;
    }

    private void addThisEndOrBothEndsOfEdge(final LNode node, final LPort port, final LEdge edge) {
        relevantEdgesAndPorts.add(new ComparableEdgeAndPort(port, edge, positionOf(port)));

        if (isInLayer(edge) && notConnectedToOtherNode(edge, node)) {
            LPort otherEnd = otherEndOf(edge, port);
            relevantEdgesAndPorts.add(new ComparableEdgeAndPort(otherEnd, edge,
                    positionOf(otherEnd)));
        }
    }

    private boolean notConnectedToOtherNode(final LEdge edge, final LNode node) {
        return node.equals(upperNode) ? !edge.getTarget().getNode().equals(lowerNode)
                && !edge.getSource().getNode().equals(lowerNode) : !edge.getTarget().getNode()
                .equals(upperNode)
                && !edge.getSource().getNode().equals(upperNode);
    }

    /**
     * This private class collects a port and a connected edge and can be by portPosition.
     * 
     * @author alan
     *
     */
    private static class ComparableEdgeAndPort implements Comparable<ComparableEdgeAndPort> {
        public final LPort port;
        public final LEdge edge;
        public final int portPosition;

        public ComparableEdgeAndPort(final LPort port, final LEdge edge, final int portPosition) {
            this.port = port;
            this.edge = edge;
            this.portPosition = portPosition;
        }

        public int compareTo(final ComparableEdgeAndPort o) {
            return Integer.compare(portPosition, o.portPosition);
        }

        @Override
        public String toString() {
            return "ComparableEdgeAndPort [port=" + port + ", edge=" + edge + ", portPosition="
                    + portPosition + "]";
        }

    }

    public int getUpperLowerCrossings() {
        return upperLowerCrossings;
    }

    public int getLowerUpperCrossings() {
        return lowerUpperCrossings;
    }

}
