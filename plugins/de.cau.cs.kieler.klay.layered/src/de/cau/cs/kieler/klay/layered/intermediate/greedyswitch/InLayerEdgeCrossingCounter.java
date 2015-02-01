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

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import com.google.common.collect.BoundType;
import com.google.common.collect.Iterators;
import com.google.common.collect.Multiset;
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
class InLayerEdgeCrossingCounter {
    /** We store port.ids in mutlisets, as nodes without fixed order have the same port.id. */
    private final SortedMultiset<Integer> downwardUpperNodeEdgePorts;
    /** We store port.ids in mutlisets, as nodes without fixed order have the same port.id. */
    private final SortedMultiset<Integer> betweenLayerEdgePorts;
    private final SortedMultiset<Integer> upwardUpperNodeEdgePorts;
    private final Set<LEdge> inLayerEdges;
    private int upperLowerCrossings;
    private int lowerUpperCrossings;
    private LNode lowerNode;
    private LNode upperNode;
    /** The amount of inLayerEdges incident to each node from the east accessed by node.id. */
    private int[] eastNodeCardinalities;
    /** The amount of inLayerEdges incident to each node from the west accessed by node.id. */
    private int[] westNodeCardinalities;
    private final LNode[] nodeOrder;
    private final Multiset<Integer> downwardLowerNodeEdgePorts;

    /**
     * Creates counter for crossings between in-layer inLayerEdges of neighboring nodes.
     * 
     * @param nodeOrder
     */
    public InLayerEdgeCrossingCounter(final LNode[] nodeOrder) {
        this.nodeOrder = nodeOrder;
        downwardUpperNodeEdgePorts = TreeMultiset.create();
        betweenLayerEdgePorts = TreeMultiset.create();
        upwardUpperNodeEdgePorts = TreeMultiset.create();
        downwardLowerNodeEdgePorts = TreeMultiset.create();
        inLayerEdges = new HashSet<LEdge>();
        initializeLayer(nodeOrder);
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
        for (LNode node : nodeOrder) {
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
                crossings += inLayerEdges.size() - downwardUpperNodeEdgePorts.count(port.id);
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
        downwardUpperNodeEdgePorts.remove(edge.getSource().id);
        downwardUpperNodeEdgePorts.remove(edge.getTarget().id);
        inLayerEdges.remove(edge);
    }

    private void openEdge(final LEdge edge) {
        inLayerEdges.add(edge);
        downwardUpperNodeEdgePorts.add(edge.getSource().id);
        downwardUpperNodeEdgePorts.add(edge.getTarget().id);
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
        betweenLayerEdgePorts.clear();
        upwardUpperNodeEdgePorts.clear();
        downwardUpperNodeEdgePorts.clear();
        downwardLowerNodeEdgePorts.clear();
        addEdgesFromUpperNode(portSide);
        int crossings = countCrossingsToLowerNode(portSide);
        return crossings;
    }

    private void addEdgesFromUpperNode(final PortSide portSide) {
        for (LPort port : portsOrderedTopToBottom(upperNode, portSide)) {
            for (LEdge edge : port.getConnectedEdges()) {
                if (isInBetweenLayerEdge(edge)) {
                    betweenLayerEdgePorts.add(port.id);
                } else if (isNotSelfLoop(edge)) {
                    addInLayerEdge(port, edge);
                }
            }
        }
    }

    private void addInLayerEdge(final LPort port, final LEdge edge) {
        if (isUpward(edge, port)) {
            upwardUpperNodeEdgePorts.add(endOfEdgePort(edge, port).id);
        } else {
            downwardUpperNodeEdgePorts.add(endOfEdgePort(edge, port).id);
        }
        inLayerEdges.add(edge);
    }

    // TODO-alan Comment with ASCII-Drawing. This is super confusing.
    private int countCrossingsToLowerNode(final PortSide portSide) {
        int crossings = 0;
        for (LPort port : portsOrderedTopToBottom(lowerNode, portSide)) {
            for (LEdge edge : port.getConnectedEdges()) {

                if (isInBetweenLayerEdge(edge)) {
                    crossings += amountOfPortsAfter(port.id, downwardUpperNodeEdgePorts);
                } else if (isNotSelfLoop(edge)) {
                    if (downwardUpperNodeEdgePorts.contains(port.id)) {
                        crossings += countVisitedEdgeCrossings(port, edge);
                        downwardUpperNodeEdgePorts.remove(port.id);
                    } else {
                        LPort endOfEdgePort = endOfEdgePort(edge, port);
                        if (isUpward(edge, port)) {
                            crossings += countUpwardEdgeCrossings(endOfEdgePort);
                        } else {
                            downwardLowerNodeEdgePorts.add(endOfEdgePort.id);
                        }
                    }
                }

            }
        }
        // count crossings below lower Node
        for (Integer id : downwardLowerNodeEdgePorts) {
            crossings += amountOfPortsBefore(id, downwardUpperNodeEdgePorts);
        }
        return crossings;
    }

    private int countUpwardEdgeCrossings(final LPort endOfEdgePort) {
        int crossings = amountOfPortsBefore(endOfEdgePort.id, upwardUpperNodeEdgePorts);
        crossings += amountOfPortsAfter(endOfEdgePort.id, betweenLayerEdgePorts);
        crossings += downwardUpperNodeEdgePorts.size();
        return crossings;
    }

    private int countVisitedEdgeCrossings(final LPort port, final LEdge edge) {
        // Edges which are removed from the set go from the upperNode to the lowerNode.
        // Between-layer edges have only been added for the upper node, so this edge crosses
        // all between layer edges between its end ports.
        int crossings = amountOfPortsInbetweenEndsOf(edge, betweenLayerEdgePorts);

        // if the port order is fixed, this edge will also have crossed any edges which originate
        // from the lower node and go downward.
        if (portOrderIsFixedFor(lowerNode)) {
            crossings += downwardLowerNodeEdgePorts.size();
        }
        return crossings;
    }

    private int amountOfPortsBefore(final Integer portId, final SortedMultiset<Integer> set) {
        return set.headMultiset(portId, BoundType.OPEN).size();
    }

    private int amountOfPortsAfter(final int portId, final SortedMultiset<Integer> set) {
        return set.tailMultiset(portId, BoundType.OPEN).size();
    }

    private LPort endOfEdgePort(final LEdge edge, final LPort port) {
        return port == edge.getSource() ? edge.getTarget() : edge.getSource();
    }

    private boolean isInBetweenLayerEdge(final LEdge edge) {
        Layer sourceLayer = edge.getSource().getNode().getLayer();
        Layer targetLayer = edge.getTarget().getNode().getLayer();
        return sourceLayer != targetLayer;
    }

    private boolean isNotSelfLoop(final LEdge edge) {
        return edge.getTarget().getNode() != edge.getSource().getNode();
    }

    private boolean isUpward(final LEdge edge, final LPort port) {
        return edge.getTarget() == port ? edge.getSource().id < edge.getTarget().id : edge
                .getSource().id > edge.getTarget().id;
    }

    private boolean portOrderIsFixedFor(final LNode node) {
        return node.getProperty(LayoutOptions.PORT_CONSTRAINTS).isOrderFixed();
    }

    private int amountOfPortsInbetweenEndsOf(final LEdge edge, final SortedMultiset<Integer> set) {
        int lowerBound = Math.min(edge.getTarget().id, edge.getSource().id);
        int upperBound = Math.max(edge.getTarget().id, edge.getSource().id);
        return set.subMultiset(lowerBound, BoundType.OPEN, upperBound, BoundType.OPEN).size();
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
