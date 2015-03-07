/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 204 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v0.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.intermediate.greedyswitch;

import java.util.Map;
import java.util.Set;

import com.google.common.collect.BoundType;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.collect.SortedMultiset;
import com.google.common.collect.TreeMultiset;

import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.intermediate.greedyswitch.PortIterable.PortOrder;

/**
 * Abstract superclass for In-layer edge crossing counter to reduce code duplication.
 * 
 * @author alan
 *
 */
abstract class InLayerEdgeCrossingCounter {
    /** The amount of inLayerEdges incident to each node from the east */
    private final Map<LNode, Integer> eastNodeCardinalities;
    /** The amount of inLayerEdges incident to each node from the west */
    private final Map<LNode, Integer> westNodeCardinalities;
    /** If a node's port order is not fixed, each port of that node has the same position. */
    private final Map<LPort, Integer> portPositions;
    private final LNode[] nodeOrder;
    /** We store port.ids in mutlisets, as nodes without fixed order have the same port.id. */
    protected final SortedMultiset<Integer> inLayerPorts;
    protected final Set<LEdge> inLayerEdges;

    protected InLayerEdgeCrossingCounter(final LNode[] nodeOrder) {
        eastNodeCardinalities = Maps.newHashMap();
        westNodeCardinalities = Maps.newHashMap();
        portPositions = Maps.newHashMap();
        inLayerEdges = Sets.newHashSet();
        inLayerPorts = TreeMultiset.create();
        this.nodeOrder = nodeOrder;
        initializeLayer(nodeOrder);
    }

    private void initializeLayer(final LNode[] layer) {
        int eastPortId = 0;
        int westPortId = 0;
        for (LNode node : layer) {
            eastPortId =
                    setPortIdsAndNodeCardinality(eastPortId, node, PortSide.EAST,
                            eastNodeCardinalities);
            westPortId =
                    setPortIdsAndNodeCardinality(westPortId, node, PortSide.WEST,
                            westNodeCardinalities);
        }
    }

    private int setPortIdsAndNodeCardinality(final int portId, final LNode node,
            final PortSide portSide, final Map<LNode, Integer> cardinalities) {
        int currentPortId = portId;
        int cardinality = 0;
        boolean hasPorts = false;
        PortIterable ports = new PortIterable(node, portSide, PortOrder.TOPDOWN_LEFTRIGHT);
        for (LPort port : ports) {
            hasPorts = true;
            portPositions.put(port, currentPortId);
            // Ports whose order on the node is not set have the same id.
            if (portOrderIsFixedFor(node)) {
                cardinality++;
                currentPortId++;
            }
        }
        if (!portOrderIsFixedFor(node) && hasPorts) {
            cardinality++;
            currentPortId++;
        }
        cardinalities.put(node, cardinality);
        return currentPortId;
    }

    /**
     * This method should be used as soon as neighboring nodes have been switched. Use the first
     * parameter to pass which node was the upper node before a switch and the second to pass the
     * lower node. We assume a left-right layout.
     * 
     * @param wasUpperNode
     *            The node which was the upper node before switching.
     * @param wasLowerNode
     *            The node which was the lower node before switching.
     */
    public void notifyNodeSwitch(final LNode wasUpperNode, final LNode wasLowerNode) {
        updatePortIds(wasUpperNode, wasLowerNode, PortSide.EAST, eastNodeCardinalities);
        updatePortIds(wasUpperNode, wasLowerNode, PortSide.WEST, westNodeCardinalities);
    }

    private void updatePortIds(final LNode firstNode, final LNode secondNode,
            final PortSide portSide, final Map<LNode, Integer> cardinalities) {
        PortIterable ports = new PortIterable(firstNode, portSide, PortOrder.TOPDOWN_LEFTRIGHT);
        for (LPort port : ports) {
            portPositions.put(port, positionOf(port) + cardinalities.get(secondNode));
        }
        ports = new PortIterable(secondNode, portSide, PortOrder.TOPDOWN_LEFTRIGHT);
        for (LPort port : ports) {
            portPositions.put(port, positionOf(port) - cardinalities.get(firstNode));
        }
    }

    private boolean portOrderIsFixedFor(final LNode node) {
        return node.getProperty(LayoutOptions.PORT_CONSTRAINTS).isOrderFixed();
    }

    /**
     * This is the main algorithm for both for counting all in-layer crossings and for counting
     * in-layer crossings between two nodes. TODO-alan explain with ASCII Drawing
     * 
     * @param edge
     * @param port
     * @return
     */
    protected int countCrossingsOn(final LEdge edge, final LPort port) {
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

    private int amountOfPortsInbetweenEndsOf(final LEdge edge, final SortedMultiset<Integer> set) {
        int lowerBound = Math.min(positionOf(edge.getTarget()), positionOf(edge.getSource()));
        int upperBound = Math.max(positionOf(edge.getTarget()), positionOf(edge.getSource()));
        return set.subMultiset(lowerBound, BoundType.OPEN, upperBound, BoundType.OPEN).size();
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

    protected LNode[] getNodeOrder() {
        return nodeOrder;
    }

    protected LPort otherEndOf(final LEdge edge, final LPort fromPort) {
        return fromPort == edge.getSource() ? edge.getTarget() : edge.getSource();
    }

    protected boolean isInLayer(final LEdge edge) {
        Layer sourceLayer = edge.getSource().getNode().getLayer();
        Layer targetLayer = edge.getTarget().getNode().getLayer();
        return sourceLayer == targetLayer;
    }

    protected int positionOf(final LPort port) {
        return portPositions.get(port);
    }
}
