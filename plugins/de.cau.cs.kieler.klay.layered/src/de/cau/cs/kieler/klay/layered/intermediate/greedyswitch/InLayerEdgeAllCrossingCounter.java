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

import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.SortedMultiset;
import com.google.common.collect.TreeMultiset;

import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;

/**
 * Counts exact number of all crossings caused by in-layer edges.
 * 
 * @author alan
 *
 */
public class InLayerEdgeAllCrossingCounter extends InLayerEdgeCrossingCounter {
    /** We store port.ids in mutlisets, as nodes without fixed order have the same port.id. */
    private final SortedMultiset<Integer> downwardUpperNodeEdgePorts;
    private final Set<LEdge> inLayerEdges;

    /**
     * Create in-layer edge crossingCounter with current order of nodes.
     * 
     * @param nodeOrder
     *            current node order.
     */
    public InLayerEdgeAllCrossingCounter(final LNode[] nodeOrder) {
        super(nodeOrder);
        inLayerEdges = new HashSet<LEdge>();
        downwardUpperNodeEdgePorts = TreeMultiset.create();
    }

    /**
     * Counts all in-layer crossings.
     * 
     * @return in-layer crossings
     */
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
            PortIterable ports = new PortIterable(node, portSide);
            for (LPort port : ports) {
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
            } else if (!edge.isSelfLoop()) {
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
