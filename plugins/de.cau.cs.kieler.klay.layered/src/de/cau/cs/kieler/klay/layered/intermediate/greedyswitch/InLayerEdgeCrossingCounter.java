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

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.BoundType;
import com.google.common.collect.SortedMultiset;

import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;

/**
 * Abstract superclass for In-layer edge crossing counter to reduce code duplication.
 * 
 * @author alan
 *
 */
abstract class InLayerEdgeCrossingCounter {
    /** The amount of inLayerEdges incident to each node from the east accessed by node.id. */
    private int[] eastNodeCardinalities;
    /** The amount of inLayerEdges incident to each node from the west accessed by node.id. */
    private int[] westNodeCardinalities;
    private final LNode[] nodeOrder;
    private final Map<LPort, Integer> portPositions;

    /**
     * Creates counter for crossings between in-layer inLayerEdges of neighboring nodes.
     * 
     * @param nodeOrder
     */
    public InLayerEdgeCrossingCounter(final LNode[] nodeOrder) {
        portPositions = new HashMap<LPort, Integer>();
        this.nodeOrder = nodeOrder;
        initializeLayer(nodeOrder);
    }

    /**
     * All node ids are numbered ascendingly by their position in the layer. These ids must stay
     * constant for each node. Eastern and western port ids are numbered ascendingly from top to
     * bottom. If a node's port order is not fixed, each port of that node gets the same id. The
     * cardinality of each node is the amount of inLayerEdges incident from the east for
     * 
     * {@link #eastNodeCardinalities} or from the west for {@link #westNodeCardinalities}.
     * 
     * @param layer
     */
    private void initializeLayer(final LNode[] layer) {
        int eastPortId = 0;
        int westPortId = 0;
        eastNodeCardinalities = new int[layer.length];
        westNodeCardinalities = new int[layer.length];
        int nodeId = 0;
        for (LNode node : layer) {
            node.id = nodeId++;
            eastPortId =
                    setPortIdsAndNodeCardinality(eastPortId, node, PortSide.EAST,
                            eastNodeCardinalities);
            westPortId =
                    setPortIdsAndNodeCardinality(westPortId, node, PortSide.WEST,
                            westNodeCardinalities);

        }
    }

    private int setPortIdsAndNodeCardinality(final int portId, final LNode node,
            final PortSide portSide, final int[] nodeCardinality) {
        int currentPortId = portId;
        int cardinality = 0;
        boolean hasPorts = false;
        PortIterable ports = new PortIterable(node, portSide);
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
        nodeCardinality[node.id] = cardinality;
        return currentPortId;
    }

    /**
     * This method should be used as soon as neighbouring nodes have been switched. Use the first
     * parameter to pass which node was the upper node before a switch and the secodn to pass the
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
            final PortSide portSide, final int[] nodeCardinalities) {
        PortIterable ports = new PortIterable(firstNode, portSide);
        for (LPort port : ports) {
            portPositions.put(port, positionOf(port) + nodeCardinalities[secondNode.id]);
        }
        ports = new PortIterable(secondNode, portSide);
        for (LPort port : ports) {
            portPositions.put(port, positionOf(port) - nodeCardinalities[firstNode.id]);
        }
    }

    protected LNode[] getNodeOrder() {
        return nodeOrder;
    }

    protected int amountOfPortsBefore(final Integer portId, final SortedMultiset<Integer> set) {
        return set.headMultiset(portId, BoundType.OPEN).size();
    }

    protected int amountOfPortsAfter(final int portId, final SortedMultiset<Integer> set) {
        return set.tailMultiset(portId, BoundType.OPEN).size();
    }

    protected LPort endOfEdgePort(final LEdge edge, final LPort port) {
        return port == edge.getSource() ? edge.getTarget() : edge.getSource();
    }

    protected boolean isInBetweenLayerEdge(final LEdge edge) {
        Layer sourceLayer = edge.getSource().getNode().getLayer();
        Layer targetLayer = edge.getTarget().getNode().getLayer();
        return sourceLayer != targetLayer;
    }

    protected boolean isUpward(final LEdge edge, final LPort port) {
        return edge.getTarget() == port ? positionOf(edge.getSource()) < positionOf(edge
                .getTarget()) : positionOf(edge.getSource()) > positionOf(edge.getTarget());
    }

    protected boolean portOrderIsFixedFor(final LNode node) {
        return node.getProperty(LayoutOptions.PORT_CONSTRAINTS).isOrderFixed();
    }

    protected int amountOfPortsInbetweenEndsOf(final LEdge edge, final SortedMultiset<Integer> set) {
        int lowerBound = Math.min(positionOf(edge.getTarget()), positionOf(edge.getSource()));
        int upperBound = Math.max(positionOf(edge.getTarget()), positionOf(edge.getSource()));
        return set.subMultiset(lowerBound, BoundType.OPEN, upperBound, BoundType.OPEN).size();
    }

    protected int positionOf(final LPort port) {
        return portPositions.get(port);
    }
}
