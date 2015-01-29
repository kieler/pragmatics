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
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import com.google.common.collect.BoundType;
import com.google.common.collect.Iterators;
import com.google.common.collect.SortedMultiset;
import com.google.common.collect.TreeMultiset;

import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;

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
class InLayerEdgeNeighboringNodeCrossingCounter {
    /** We store port.ids in mutlisets, as nodes without fixed order have the same port.id. */
    private final SortedMultiset<Integer> inLayerEdgePorts;
    /** We store port.ids in mutlisets, as nodes without fixed order have the same port.id. */
    private final SortedMultiset<Integer> betweenLayerEdgePorts;
    private final Set<LEdge> inLayerEdges;
    private int upperLowerCrossings;
    private int lowerUpperCrossings;
    private LNode lowerNode;
    private LNode upperNode;
    /** The amount of inLayerEdges incident to each node from the east accessed by node.id. */
    private int[] eastNodeCardinalities;
    /** The amount of inLayerEdges incident to each node from the west accessed by node.id. */
    private int[] westNodeCardinalities;

    /**
     * Creates counter for crossings between in-layer inLayerEdges of neighboring nodes.
     * 
     * @param nodeOrder
     */
    public InLayerEdgeNeighboringNodeCrossingCounter(final LNode[] nodeOrder) {
        inLayerEdgePorts = TreeMultiset.create();
        betweenLayerEdgePorts = TreeMultiset.create();
        inLayerEdges = new HashSet<LEdge>();
        initializeLayer(nodeOrder);
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
    public void countCrossings(final LNode upper, final LNode lower) {
        upperNode = upper;
        lowerNode = lower;
        upperLowerCrossings = countUpperLower();
        lowerUpperCrossings = countLowerUpper();
    }

    private int countUpperLower() {
        int crossings = countOnSide(PortSide.WEST);
        crossings += countOnSide(PortSide.EAST);
        return crossings;
    }

    private int countLowerUpper() {
        switchUpperLower();

        int crossings = countUpperLower();

        switchUpperLower();

        return crossings;
    }

    private void switchUpperLower() {
        notifyNodeSwitch(upperNode, lowerNode);
        LNode temp = upperNode;
        upperNode = lowerNode;
        lowerNode = temp;
    }

    private int countOnSide(final PortSide portSide) {
        inLayerEdgePorts.clear();
        betweenLayerEdgePorts.clear();
        inLayerEdges.clear();
        int crossings = processUpperNode(portSide);
        crossings += processLowerNode(portSide);
        return crossings;
    }

    private int processUpperNode(final PortSide portSide) {
        int crossings = 0;

        for (LPort port : portsOrderedTopToBottom(upperNode, portSide)) {
            for (LEdge edge : port.getConnectedEdges()) {

                if (isInBetweenLayerEdge(edge)) {
                    if (portOrderIsFixedFor(upperNode)) {
                        crossings += amountOfOpenInLayerEdges(port);
                    }
                    betweenLayerEdgePorts.add(port.id);

                } else if (isNotSelfLoop(edge)) {
                    if (isVisited(edge)) {
                        close(edge);
                    } else {
                        open(edge);
                        crossings += countOpenPortsInBetweenEndsOf(edge);
                    }
                }

            }
        }
        return crossings;
    }

    private int processLowerNode(final PortSide portSide) {
        int crossings = 0;
        for (LPort port : portsOrderedTopToBottom(lowerNode, portSide)) {
            for (LEdge edge : port.getConnectedEdges()) {

                if (isInBetweenLayerEdge(edge)) {
                    crossings += amountOfOpenInLayerEdges(port);
                    if (portOrderIsFixedFor(lowerNode)) {
                        betweenLayerEdgePorts.add(port.id);
                    }

                } else if (isNotSelfLoop(edge)) {
                    if (isVisited(edge)) {
                        close(edge);
                    } else {
                        if (portOrderIsFixedFor(lowerNode)) {
                            open(edge);
                        }
                        crossings += countOpenPortsInBetweenEndsOf(edge);
                    }
                }

            }
        }
        return crossings;
    }

    private boolean isVisited(final LEdge edge) {
        return inLayerEdges.contains(edge);
    }

    private boolean isInBetweenLayerEdge(final LEdge edge) {
        Layer sourceLayer = edge.getSource().getNode().getLayer();
        Layer targetLayer = edge.getTarget().getNode().getLayer();
        return sourceLayer != targetLayer;
    }

    private boolean isNotSelfLoop(final LEdge edge) {
        return edge.getTarget().getNode() != edge.getSource().getNode();
    }

    private int countOpenPortsInBetweenEndsOf(final LEdge edge) {
        int crossings = amountOfInLayerPortsInBetween(edge.getSource(), edge.getTarget());
        crossings += amountOfBetweenLayerPortsInbetween(edge.getSource(), edge.getTarget());
        return crossings;
    }

    private void open(final LEdge edge) {
        inLayerEdgePorts.add(edge.getSource().id);
        inLayerEdgePorts.add(edge.getTarget().id);
        inLayerEdges.add(edge);
    }

    private void close(final LEdge edge) {
        inLayerEdgePorts.remove(edge.getSource().id);
        inLayerEdgePorts.remove(edge.getTarget().id);
        inLayerEdges.remove(edge);
    }

    private boolean portOrderIsFixedFor(final LNode node) {
        return node.getProperty(LayoutOptions.PORT_CONSTRAINTS).isOrderFixed();
    }

    private int amountOfBetweenLayerPortsInbetween(final LPort port, final LPort endOfEdgePort) {
        int lowerBound = Math.min(port.id, endOfEdgePort.id);
        int upperBound = Math.max(port.id, endOfEdgePort.id);
        return betweenLayerEdgePorts.subMultiset(lowerBound, BoundType.OPEN, upperBound,
                BoundType.OPEN).size();
    }

    private int amountOfInLayerPortsInBetween(final LPort port, final LPort endOfEdgePort) {
        int lowerBound = Math.min(port.id, endOfEdgePort.id);
        int upperBound = Math.max(port.id, endOfEdgePort.id);
        return inLayerEdgePorts.subMultiset(lowerBound, BoundType.OPEN, upperBound, BoundType.OPEN)
                .size();
    }

    private int amountOfOpenInLayerEdges(final LPort port) {
        return inLayerEdgePorts.tailMultiset(port.id, BoundType.OPEN).size();
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
        for (LPort port : portsOrderedTopToBottom(firstNode, portSide)) {
            port.id += nodeCardinalities[secondNode.id];
        }
        for (LPort port : portsOrderedTopToBottom(secondNode, portSide)) {
            port.id -= nodeCardinalities[firstNode.id];
        }
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
        for (LPort port : portsOrderedTopToBottom(node, portSide)) {
            hasPorts = true;
            port.id = currentPortId;
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
     * Creates an Iterable which iterates over all ports on the given side of the layer from top to
     * bottom.
     * 
     * @param node
     * @param side
     * @return
     */
    private Iterable<LPort> portsOrderedTopToBottom(final LNode node, final PortSide side) {
        return new Iterable<LPort>() {
            public Iterator<LPort> iterator() {
                if (side == PortSide.WEST) {
                    final List<LPort> ports = node.getPorts();
                    Iterator<LPort> iterable = new Iterator<LPort>() {
                        private final ListIterator<LPort> listIterator = ports.listIterator(ports
                                .size());

                        public boolean hasNext() {
                            return listIterator.hasPrevious();
                        }

                        public LPort next() {
                            return listIterator.previous();
                        }
                    };
                    return Iterators.filter(iterable, LPort.WEST_PREDICATE);
                } else {
                    Iterator<LPort> iterable = node.getPorts().iterator();
                    return Iterators.filter(iterable, LPort.EAST_PREDICATE);
                }
            }
        };
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
