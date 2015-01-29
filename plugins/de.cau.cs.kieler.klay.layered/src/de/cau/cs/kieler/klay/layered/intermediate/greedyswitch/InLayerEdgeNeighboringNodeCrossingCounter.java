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

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NavigableSet;
import java.util.TreeSet;

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
 * Counts the of in-layer Crossings between two neighboring nodes. Nodes with multiple in- layer
 * edges in both directions may cause too many crossings. 
 * 
 * @author alan
 *
 */
class InLayerEdgeNeighboringNodeCrossingCounter {
    private final SortedMultiset<Integer> inLayerEdgePorts;
    private final NavigableSet<Integer> betweenLayerEdgePorts;
    private int upperLowerCrossings;
    private int lowerUpperCrossings;
    private LNode lowerNode;
    private LNode upperNode;
    private int[] eastNodeCardinalities;
    private int[] westNodeCardinalities;

    public InLayerEdgeNeighboringNodeCrossingCounter(final LNode[] nodeOrder) {
        inLayerEdgePorts = TreeMultiset.create();
        betweenLayerEdgePorts = new TreeSet<Integer>();
        initializeLayer(nodeOrder);
    }

    private void initializeLayer(final LNode[] layer) {
        int eastPortId = 0;
        int westPortId = 0;
        eastNodeCardinalities = new int[layer.length];
        westNodeCardinalities = new int[layer.length];
        int nodeId = 0;
        for (LNode element : layer) {
            LNode node = element;
            node.id = nodeId++;
            eastPortId =
                    setPortIdsAndNodeCardinality(eastPortId, node, PortSide.EAST,
                            eastNodeCardinalities);
            westPortId =
                    setPortIdsAndNodeCardinality(westPortId, node, PortSide.WEST,
                            westNodeCardinalities);

        }
    }

    private int setPortIdsAndNodeCardinality(int portId, final LNode node, final PortSide portSide,
            final int[] nodeCardinality) {
        int cardinality = 0;
        for (LPort port : getPortIterable(node, portSide)) {
            port.id = portId;
            // Ports whose order on the node is not set get the same id.
            if (portOrderIsFixedFor(node)) {
                cardinality++;
                portId++;
            }
        }
        if (!portOrderIsFixedFor(node)) {
            cardinality++;
            portId++;
        }
        nodeCardinality[node.id] = cardinality;
        return portId;
    }

    public void countCrossings(final LNode upper, final LNode lower) {
        upperNode = upper;
        lowerNode = lower;
        upperLowerCrossings = countUpperLower();
        lowerUpperCrossings = countLowerUpper();
    }

    private int countUpperLower() {
        int crossings = countOnSideInOrder(PortSide.WEST, upperNode, lowerNode);
        crossings += countOnSideInOrder(PortSide.EAST, upperNode, lowerNode);
        return crossings;
    }

    private int countLowerUpper() {
        notifyNodeSwitch(upperNode, lowerNode); // pretend to have switched
        int crossings = countOnSideInOrder(PortSide.WEST, lowerNode, upperNode);
        crossings += countOnSideInOrder(PortSide.EAST, lowerNode, upperNode);
        notifyNodeSwitch(lowerNode, upperNode); // switch back
        return crossings;
    }

    private int countOnSideInOrder(final PortSide portSide, final LNode first, final LNode second) {
        inLayerEdgePorts.clear();
        betweenLayerEdgePorts.clear();
        int crossings = processNode(portSide, first);
        crossings += processNode(portSide, second);
        return crossings;
    }

    private int processNode(final PortSide portSide, final LNode node) {
        int crossings = 0;
        for (LPort port : getPortIterable(node, portSide)) {
            for (LEdge edge : port.getConnectedEdges()) {
                if (isRelevantInLayerEdge(edge)) {
                    LPort endOfEdgePort = getOtherEndOf(edge, port);
                    if (inLayerEdgePorts.contains(port.id)
                            && inLayerEdgePorts.contains(endOfEdgePort.id)) {
                        inLayerEdgePorts.remove(port.id);
                        inLayerEdgePorts.remove(endOfEdgePort.id);
                    } else {
                        inLayerEdgePorts.add(port.id);
                        inLayerEdgePorts.add(endOfEdgePort.id);
                        crossings += amountOfInLayerPortsInBetween(port, endOfEdgePort);
                        crossings += amountOfBetweenLayerPortsInbetween(port, endOfEdgePort);
                    }
                } else {
                    if (portOrderIsFixedFor(node)) {
                        crossings += amountOfPortsInSetAfter(port);
                    } else {
                        crossings +=
                                amountOfPortsInSetAfter(port) - inLayerEdgePorts.count(port.id);
                    }
                    betweenLayerEdgePorts.add(port.id);
                }

            }
        }
        return crossings;
    }

    /**
     * <pre>
     * An edge is not relevant when it is either:
     *  - a self-loop
     *  - an edge
     * </pre>
     * 
     * @param edge
     * @return
     */
    private boolean isRelevantInLayerEdge(final LEdge edge) {
        Layer sourceLayer = edge.getSource().getNode().getLayer();
        Layer targetLayer = edge.getTarget().getNode().getLayer();
        boolean isInLayerEdge = sourceLayer == targetLayer;
        boolean upperToLowerNodeEdgeWithoutFixedOrder =
                !portOrderIsFixedFor(upperNode) && !portOrderIsFixedFor(lowerNode)
                        && edgeConnectsNodes(edge, upperNode, lowerNode);
        boolean isRelevantEdge = !isSelfLoop(edge) && !upperToLowerNodeEdgeWithoutFixedOrder;
        return isInLayerEdge && isRelevantEdge;
    }

    private boolean isSelfLoop(final LEdge edge) {
        return edge.getTarget().getNode() == edge.getSource().getNode();
    }

    private boolean portOrderIsFixedFor(final LNode node) {
        return node.getProperty(LayoutOptions.PORT_CONSTRAINTS).isOrderFixed();
    }

    private boolean edgeConnectsNodes(final LEdge edge, final LNode upper, final LNode lower) {
        LNode targetNode = edge.getTarget().getNode();
        LNode sourceNode = edge.getSource().getNode();
        boolean goesFromUpperToLower = targetNode == upper && sourceNode == lower;
        boolean goesFromLowerToUpper = targetNode == lower && sourceNode == upper;
        return goesFromUpperToLower || goesFromLowerToUpper;
    }

    private LPort getOtherEndOf(final LEdge edge, final LPort port) {
        return edge.getSource() == port ? edge.getTarget() : edge.getSource();
    }

    private int amountOfBetweenLayerPortsInbetween(final LPort port, final LPort endOfEdgePort) {
        final boolean portIsBeforeEndPort = port.id < endOfEdgePort.id;
        int lowerBound = portIsBeforeEndPort ? port.id : endOfEdgePort.id;
        int upperBound = portIsBeforeEndPort ? endOfEdgePort.id : port.id;
        return betweenLayerEdgePorts.subSet(lowerBound, false, upperBound, false).size();
    }

    private int amountOfInLayerPortsInBetween(final LPort port, final LPort endOfEdgePort) {
        final boolean portIsBeforeEndPort = port.id < endOfEdgePort.id;
        int lowerBound = portIsBeforeEndPort ? port.id : endOfEdgePort.id;
        int upperBound = portIsBeforeEndPort ? endOfEdgePort.id : port.id;
        return inLayerEdgePorts.subMultiset(lowerBound, BoundType.OPEN, upperBound, BoundType.OPEN)
                .size();
    }

    private int amountOfPortsInSetAfter(final LPort port) {
        return inLayerEdgePorts.tailMultiset(port.id, BoundType.OPEN).size();
    }

    private Iterable<LPort> getPortIterable(final LNode node, final PortSide side) {
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

    public void notifyNodeSwitch(final LNode wasUpperNode, final LNode wasLowerNode) {
        updatePortIds(wasUpperNode, wasLowerNode, PortSide.EAST, eastNodeCardinalities);
        updatePortIds(wasUpperNode, wasLowerNode, PortSide.WEST, westNodeCardinalities);
    }

    private void updatePortIds(final LNode firstNode, final LNode secondNode,
            final PortSide portSide, final int[] nodeCardinalities) {
        for (LPort port : getPortIterable(firstNode, portSide)) {
            port.id += nodeCardinalities[secondNode.id];
        }
        for (LPort port : getPortIterable(secondNode, portSide)) {
            port.id -= nodeCardinalities[firstNode.id];
        }
    }

    public int getUpperLowerCrossings() {
        return upperLowerCrossings;
    }

    public int getLowerUpperCrossings() {
        return lowerUpperCrossings;
    }
}
