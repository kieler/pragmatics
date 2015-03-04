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
import de.cau.cs.kieler.klay.layered.intermediate.greedyswitch.PortIterable.PortOrder;

/**
 * Counts exact number of all crossings caused by in-layer edges.
 * 
 * @author alan
 *
 */
public class InLayerEdgeAllCrossingCounter extends InLayerEdgeCrossingCounter {
    /** We store port.ids in mutlisets, as nodes without fixed order have the same port.id. */
    private final SortedMultiset<Integer> inLayerPorts;
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
        inLayerPorts = TreeMultiset.create();
    }

    /**
     * Counts all in-layer crossings.
     * 
     * @return in-layer crossings
     */
    public int countAllCrossings() {
        int crossings = 0;
        crossings = iterateEdgesTopDownAndCountCrossingsOnSide(PortSide.WEST);
        crossings += iterateEdgesTopDownAndCountCrossingsOnSide(PortSide.EAST);
        return crossings;
    }

    private int iterateEdgesTopDownAndCountCrossingsOnSide(final PortSide portSide) {
        int crossings = 0;
        for (LNode node : getNodeOrder()) {
            PortIterable ports = new PortIterable(node, portSide, PortOrder.TOPDOWN_LEFTRIGHT);
            for (LPort port : ports) {
                for (LEdge edge : port.getConnectedEdges()) {
                    if (!edge.isSelfLoop()) {
                        crossings += countCrossingsOn(edge, port);
                    }
                }
            }
        }

        return crossings;
    }

    private int countCrossingsOn(final LEdge edge, final LPort port) {
        int crossings = 0;
        if (isInLayer(edge)) {
            if (notVisited(edge)) {
                add(edge);
            } else {
                remove(edge);
                crossings += amountOfPortsInbetweenEndsOf(edge, inLayerPorts);
            }
        } else { // is in-between layer edge
            crossings += amountOfOpenEdgesMinusThoseWithFreePortOrderOnSameNodeAs(port);
        }
        return crossings;
    }

    private boolean notVisited(final LEdge edge) {
        return !inLayerEdges.contains(edge);
    }

    private int amountOfOpenEdgesMinusThoseWithFreePortOrderOnSameNodeAs(final LPort port) {
        return inLayerEdges.size() - inLayerPorts.count(positionOf(port));
    }

    private void remove(final LEdge edge) {
        inLayerPorts.remove(positionOf(edge.getSource()));
        inLayerPorts.remove(positionOf(edge.getTarget()));
        inLayerEdges.remove(edge);
    }

    private void add(final LEdge edge) {
        inLayerEdges.add(edge);
        inLayerPorts.add(positionOf(edge.getSource()));
        inLayerPorts.add(positionOf(edge.getTarget()));
    }
}
