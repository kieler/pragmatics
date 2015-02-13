package de.cau.cs.kieler.klay.layered.intermediate.greedyswitch;

import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.SortedMultiset;
import com.google.common.collect.TreeMultiset;

import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;

public class InLayerEdgeAllCrossingCounter extends InLayerEdgeCrossingCounter {
    /** We store port.ids in mutlisets, as nodes without fixed order have the same port.id. */
    private final SortedMultiset<Integer> downwardUpperNodeEdgePorts;
    protected final Set<LEdge> inLayerEdges;

    public InLayerEdgeAllCrossingCounter(final LNode[] nodeOrder) {
        super(nodeOrder);
        inLayerEdges = new HashSet<LEdge>();
        downwardUpperNodeEdgePorts = TreeMultiset.create();
    }

    public int countAllCrossings() {
        int crossings = 0;
        crossings = countAllCrossingsOnSide(PortSide.WEST);
        crossings += countAllCrossingsOnSide(PortSide.EAST);
        return crossings;
    }

    private int countAllCrossingsOnSide(final PortSide portSide) {
        int crossings = 0;
        downwardUpperNodeEdgePorts.clear();
        inLayerEdges.clear();
        for (LNode node : getNodeOrder()) {
            for (LPort port : portsOrderedTopToBottom(node, portSide)) {
                crossings += countCrossingsOnPort(port);
            }
        }
        return crossings;
    }

    private int countCrossingsOnPort(final LPort port) {
        int crossings = 0;
        for (LEdge edge : port.getConnectedEdges()) {
            if (isInBetweenLayerEdge(edge)) {
                crossings +=
                        inLayerEdges.size()
                                - downwardUpperNodeEdgePorts.count(super.positionOf(port));
            } else if (isNotSelfLoop(edge)) {
                if (inLayerEdges.contains(edge)) {
                    closeEdge(edge);
                    crossings += amountOfPortsInbetweenEndsOf(edge, downwardUpperNodeEdgePorts);
                } else {
                    openEdge(edge);
                }
            }
        }
        return crossings;
    }

    private void closeEdge(final LEdge edge) {
        downwardUpperNodeEdgePorts.remove(super.positionOf(edge.getSource()));
        downwardUpperNodeEdgePorts.remove(super.positionOf(edge.getTarget()));
        inLayerEdges.remove(edge);
    }

    private void openEdge(final LEdge edge) {
        inLayerEdges.add(edge);
        downwardUpperNodeEdgePorts.add(super.positionOf(edge.getSource()));
        downwardUpperNodeEdgePorts.add(super.positionOf(edge.getTarget()));
    }

}
