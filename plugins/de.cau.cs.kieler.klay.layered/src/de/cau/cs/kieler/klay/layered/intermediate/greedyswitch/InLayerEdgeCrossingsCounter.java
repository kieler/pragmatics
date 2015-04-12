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
import de.cau.cs.kieler.klay.layered.graph.LNode.PortOrder;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;

/**
 * Abstract superclass for In-layer edge crossing counter to reduce code duplication.
 * 
 * @author alan
 *
 */
abstract class InLayerEdgeCrossingsCounter {
    /** The number of inLayerEdges incident to each node from the east. */
    private final Map<LNode, Integer> eastNodeCardinalities;
    /** The number of inLayerEdges incident to each node from the west. */
    private final Map<LNode, Integer> westNodeCardinalities;
    private final Map<LPort, Integer> portPositions;
    private final LNode[] nodeOrder;
    /** We store port-positions in mutlisets, as nodes without fixed order have the same port.id. */
    private final SortedMultiset<Integer> inLayerPorts;
    private final Set<LEdge> inLayerEdges;

    protected InLayerEdgeCrossingsCounter(final LNode[] nodeOrder) {
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
            final PortSide side, final Map<LNode, Integer> cardinalities) {
        int currentPortId = portId;
        int cardinality = 0;
        boolean hasPorts = false;
        Iterable<LPort> ports = node.getPorts(side, PortOrder.NORTHSOUTH_EASTWEST);
        for (LPort port : ports) {
            hasPorts = true;
            portPositions.put(port, currentPortId);
            // Ports whose order on the node is not set have the same id.
            if (portOrderIsFixedFor(node) || port.getDegree() > 1) {
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
     * former lower node. We assume a left-right layout.
     * 
     * @param wasUpperNode
     *            The node which was the upper node before switching.
     * @param wasLowerNode
     *            The node which was the lower node before switching.
     */
    public void notifyOfSwitch(final LNode wasUpperNode, final LNode wasLowerNode) {
        updatePortIds(wasUpperNode, wasLowerNode, PortSide.EAST, eastNodeCardinalities);
        updatePortIds(wasUpperNode, wasLowerNode, PortSide.WEST, westNodeCardinalities);
    }

    private void updatePortIds(final LNode firstNode, final LNode secondNode, final PortSide side,
            final Map<LNode, Integer> cardinalities) {
        Iterable<LPort> ports = firstNode.getPorts(side, PortOrder.NORTHSOUTH_EASTWEST);
        for (LPort port : ports) {
            portPositions.put(port, positionOf(port) + cardinalities.get(secondNode));
        }
        ports = secondNode.getPorts(side, PortOrder.NORTHSOUTH_EASTWEST);
        for (LPort port : ports) {
            portPositions.put(port, positionOf(port) - cardinalities.get(firstNode));
        }
    }

    private boolean portOrderIsFixedFor(final LNode node) {
        return node.getProperty(LayoutOptions.PORT_CONSTRAINTS).isOrderFixed();
    }

    /**
     * This is the main method called for one edge and port both for counting all in-layer crossings
     * and for counting in-layer crossings between two nodes. <br>
     * The algorithm works the following way: We step through each port: If the connected edge is a
     * between-layer edge, we add the position of the port on this layer to the sorted list of
     * ports. Each time we meet an edge which has been visited, we count all port position in
     * between the end and start position of this edge and delete it from the list.<br>
     * Note that on nodes with free port order, all ports have the same port position.
     * 
     */
    protected int countCrossingsOn(final LEdge edge, final LPort port) {
        int crossings = 0;
        if (isInLayer(edge)) {
            if (notVisited(edge)) {
                add(edge);
            } else {
                remove(edge);
                crossings += numberOfPortsInbetweenEndsOf(edge, inLayerPorts);
            }
        } else { // is in-between layer edge
            int portsOnNodeWithFreePortOrder = inLayerPorts.count(positionOf(port));
            crossings += inLayerEdges.size() - portsOnNodeWithFreePortOrder;
        }
        return crossings;
    }

    private boolean notVisited(final LEdge edge) {
        return !inLayerEdges.contains(edge);
    }

    private int numberOfPortsInbetweenEndsOf(final LEdge edge, final SortedMultiset<Integer> set) {
        int lowerBound = Math.min(positionOf(edge.getTarget()), positionOf(edge.getSource()));
        int upperBound = Math.max(positionOf(edge.getTarget()), positionOf(edge.getSource()));
        return set.subMultiset(lowerBound, BoundType.OPEN, upperBound, BoundType.OPEN).size();
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
