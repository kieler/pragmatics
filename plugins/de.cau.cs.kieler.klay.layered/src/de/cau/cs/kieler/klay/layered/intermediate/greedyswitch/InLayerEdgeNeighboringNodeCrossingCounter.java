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

import com.google.common.collect.SortedMultiset;
import com.google.common.collect.TreeMultiset;

import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.intermediate.greedyswitch.PortIterable.PortOrder;

/**
 * Counts crossings with in-layer inLayerEdges considering two neighboring nodes. Should the order
 * of the nodes change, the counter must be notified using {link
 * {@link #notifyNodeSwitch(LNode, LNode)}. This counter initializes the node.id's and port.id's
 * when initialized. This means that should they be changed after initialization, it won't
 * necessarily work anymore.
 * 
 * @author alan
 *
 */
public class InLayerEdgeNeighboringNodeCrossingCounter extends InLayerEdgeCrossingCounter {
    /** We store port.ids in mutlisets, as nodes without fixed order have the same port.id. */
    private final SortedMultiset<Integer> betweenLayerEdgePorts;
    private final SortedMultiset<Integer> upwardEdgePorts;
    private final SortedMultiset<Integer> downwardEdgePorts;
    private int upperLowerCrossings;
    private int lowerUpperCrossings;
    private LNode lowerNode;
    private LNode upperNode;

    /**
     * Create a counter to count crossings with neighboring nodes.
     * 
     * @param nodeOrder
     *            the current order of nodes in the layer.
     */
    public InLayerEdgeNeighboringNodeCrossingCounter(final LNode[] nodeOrder) {
        super(nodeOrder);
        betweenLayerEdgePorts = TreeMultiset.create();
        upwardEdgePorts = TreeMultiset.create();
        downwardEdgePorts = TreeMultiset.create();
    }

    /**
     * Counts crossings with in-layer inLayerEdges considering two neighboring nodes. The main
     * algorithm can be found in {@link #processNode(PortSide, LNode)}.
     * 
     * @param upper
     *            The upper node assuming a left-right layout.
     * @param lower
     *            The lower node assuming a left-right layout.
     */
    public void countCrossingsBetweenNeighbouringNodes(final LNode upper, final LNode lower) {
        upperNode = upper;
        lowerNode = lower;
        upperLowerCrossings = countUpperLowerNeighbouringCrossings();
        lowerUpperCrossings = countLowerUpperNeighbouringCrossings();
    }

    private int countUpperLowerNeighbouringCrossings() {
        int crossings = countNeighbouringCrossingsOnSide(PortSide.WEST);
        crossings += countNeighbouringCrossingsOnSide(PortSide.EAST);
        return crossings;
    }

    private int countLowerUpperNeighbouringCrossings() {
        switchUpperLower();

        int crossings = countUpperLowerNeighbouringCrossings();

        switchUpperLower();

        return crossings;
    }

    private void switchUpperLower() {
        notifyNodeSwitch(upperNode, lowerNode);
        LNode temp = upperNode;
        upperNode = lowerNode;
        lowerNode = temp;
    }

    private int countNeighbouringCrossingsOnSide(final PortSide portSide) {
        clearSets();
        iterateUpperNodeEdgesAndAddPorts(portSide);
        int crossings = iterateLowerNodeEdgesAndCountCrossings(portSide);
        return crossings;
    }

    private void iterateUpperNodeEdgesAndAddPorts(final PortSide portSide) {
        PortIterable ports = new PortIterable(upperNode, portSide, PortOrder.TOPDOWN_LEFTRIGHT);
        for (LPort port : ports) {
            for (LEdge edge : port.getConnectedEdges()) {
                if (!edge.isSelfLoop()) {
                    addUpperNodeEdgePorts(port, edge);
                }
            }
        }
    }

    private void addUpperNodeEdgePorts(final LPort port, final LEdge edge) {
        if (isInLayer(edge)) {
            if (isUpward(edge, port)) {
                upwardEdgePorts.add(positionOf(otherEndOf(edge, port)));
            } else {
                downwardEdgePorts.add(positionOf(otherEndOf(edge, port)));
            }
        } else { // is in between layer edge
            betweenLayerEdgePorts.add(positionOf(port));
        }
    }

    // TODO-alan Comment with ASCII-Drawing. This is super confusing.
    private int iterateLowerNodeEdgesAndCountCrossings(final PortSide portSide) {
        int crossings = 0;
        PortIterable ports = new PortIterable(lowerNode, portSide, PortOrder.TOPDOWN_LEFTRIGHT);
        for (LPort port : ports) {
            for (LEdge edge : port.getConnectedEdges()) {
                if (!edge.isSelfLoop()) {
                    crossings += countCrossingsToUpperEdges(port, edge);
                }
            }
        }
        return crossings;
    }

    private int countCrossingsToUpperEdges(final LPort port, final LEdge edge) {
        int crossings = 0;
        if (isInLayer(edge)) {
            if (downwardEdgePorts.contains(positionOf(port))) {
                crossings += amountOfPortsInbetweenEndsOf(edge, betweenLayerEdgePorts);
                downwardEdgePorts.remove(positionOf(port));
            } else {
                int endOfEdgePosition = positionOf(otherEndOf(edge, port));
                if (isUpward(edge, port)) {
                    crossings += amountOfPortsBefore(endOfEdgePosition, upwardEdgePorts);
                    crossings += betweenLayerEdgePorts.size();
                    crossings += downwardEdgePorts.size();
                } else {
                    crossings += amountOfPortsBefore(endOfEdgePosition, downwardEdgePorts);
                }
            }
        } else { // is in between layer edge
            crossings += amountOfPortsAfter(positionOf(port), downwardEdgePorts);
        }
        return crossings;
    }

    private void clearSets() {
        betweenLayerEdgePorts.clear();
        upwardEdgePorts.clear();
        downwardEdgePorts.clear();
    }

    /**
     * Returns the amount of in-layer crossings assuming the order upperNode-lowerNode in a
     * left-right layout.
     * 
     * @return The amount of in-layer crossings in current order.
     */
    public int getUpperLowerCrossings() {
        return upperLowerCrossings;
    }

    /**
     * Returns the amount of in-layer crossings assuming the order upperNode-lowerNode in a
     * left-right layout.
     * 
     * @return The amount of in-layer crossings in switched order.
     */
    public int getLowerUpperCrossings() {
        return lowerUpperCrossings;
    }
}
