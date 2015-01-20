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
package de.cau.cs.kieler.klay.layered.intermediate;

import java.util.Deque;
import java.util.Iterator;

import com.google.common.collect.Iterators;

import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;

/**
 * Counts the worst case amount of in-layer Crossings between two nodes. Nodes with multiple in-
 * layer edges in both directions may cause too many crossings. TODO-alan only neighbouring !!???
 * 
 * @author alan
 *
 */
public class TwoNodeInLayerEdgeCrossingCounter implements TwoNodeCrossingCounter {
    private final DoublyLinkedHashSet<LEdge> edges;
    private final LNode[] layer;
    private int upperLowerCrossings;
    private int lowerUpperCrossings;
    private LNode lowerNode;
    private LNode upperNode;
    private int[] nodePositions;

    public TwoNodeInLayerEdgeCrossingCounter(final LNode[] nodeOrder) {
        edges = new DoublyLinkedHashSet<LEdge>();
        layer = nodeOrder;
        initializeLayer();
    }

    private void initializeLayer() {
        nodePositions = new int[layer.length];
        int id = -1;
        for (int i = 0; i < layer.length; i++) {
            boolean idsAreAscending = id == layer[i].id - 1;
            assert idsAreAscending : "node.id's must be numbered in ascending order";
            id = layer[i].id;
            nodePositions[i] = id;
        }
    }

    public int getUpperLowerCrossings() {
        return upperLowerCrossings;
    }

    public int getLowerUpperCrossings() {
        return lowerUpperCrossings;
    }

    public void nodesSwitched(final LNode firstNode, final LNode secondNode) {
        int temp = nodePositions[firstNode.id];
        nodePositions[firstNode.id] = nodePositions[secondNode.id];
        nodePositions[secondNode.id] = temp;
    }

    public void countCrossings(final LNode upper, final LNode lower) {
        upperLowerCrossings = countForOrder(upper, lower);
        nodesSwitched(upper, lower);
        lowerUpperCrossings = countForOrder(lower, upper);
        nodesSwitched(upper, lower);
    }

    /**
     * Comment heavily. TODO-alan
     * 
     * @param upper
     * @param lower
     * @param edges
     * @return
     */
    private int countForOrder(final LNode upper, final LNode lower) {
        upperNode = upper;
        lowerNode = lower;

        int crossings = countOnSide(PortSide.WEST);
        crossings += countOnSide(PortSide.EAST);
        return crossings;
    }

    private int countOnSide(final PortSide portSide) {
        edges.clear();
        int upperInBetweenLayerEdges = 0;
        int crossings = 0;

        Iterator<LPort> iterator = getPortIterator(upperNode, portSide);
        while (iterator.hasNext()) {
            LPort port = iterator.next();
            for (LEdge edge : port.getConnectedEdges()) {

                if (ignoreUpperNodeEdge(edge)) {
                    continue;
                }

                if (isInLayer(edge)) {
                    if (edges.contains(edge)) {
                        crossings += edges.removeAndGetAmountOfEntriesAfter(edge);
                    } else {
                        edges.add(edge);
                    }
                } else {
                    if (portOrderIsFixedFor(upperNode)) {
                        crossings += edges.size();
                    }
                    upperInBetweenLayerEdges++;
                }
            }
        }

        iterator = getPortIterator(lowerNode, portSide);
        while (iterator.hasNext()) {
            LPort port = iterator.next();
            for (LEdge edge : port.getConnectedEdges()) {

                if (ignoreLowerNodeEdge(edge)) {
                    continue;
                }

                if (isInLayer(edge)) {
                    if (edges.contains(edge)) {
                        crossings += edges.removeAndGetAmountOfEntriesAfter(edge);
                    } else if (directionUpward(edge, lowerNode)) {
                        crossings += upperInBetweenLayerEdges;
                    } else if (portOrderIsFixedFor(lowerNode)) {
                        edges.add(edge);
                    }
                } else {
                    crossings += edges.size();
                }
            }
        }

        return crossings;
    }

    /**
     * comment. TODO-alan
     * 
     * @param edge
     * @return
     */
    private boolean ignoreUpperNodeEdge(final LEdge edge) {
        return ignoreEdge(edge) || isInLayer(edge) && directionUpward(edge, upperNode)
                && !portOrderIsFixedFor(upperNode);
    }

    private boolean ignoreLowerNodeEdge(final LEdge edge) {
        return ignoreEdge(edge) || isInLayer(edge) && !directionUpward(edge, lowerNode)
                && !portOrderIsFixedFor(lowerNode);
    }

    private boolean ignoreEdge(final LEdge edge) {
        return isSelfLoop(edge) || !portOrderIsFixedFor(upperNode)
                && !portOrderIsFixedFor(lowerNode) && edgeConnectsUpperNodeAndLowerNode(edge);
    }

    /**
     * For in-layer edges, checks if the edge goes from node upwards.
     * 
     * @param edge
     * @param node
     * @return
     */
    private boolean directionUpward(final LEdge edge, final LNode node) {
        boolean isSource = edge.getSource().getNode() == node;
        LNode otherNode = isSource ? edge.getTarget().getNode() : edge.getSource().getNode();
        return positionOf(otherNode) < positionOf(node);
    }

    private boolean isSelfLoop(final LEdge edge) {
        return edge.getTarget().getNode() == edge.getSource().getNode();
    }

    private int positionOf(final LNode node) {
        return nodePositions[node.id];
    }

    private Iterator<LPort> getPortIterator(final LNode node, final PortSide side) {
        Iterator<LPort> iterator;
        if (side == PortSide.WEST) {
            Deque<LPort> ports = (Deque<LPort>) node.getPorts(); // TODO-alan
            iterator = ports.descendingIterator();
            iterator = Iterators.filter(iterator, LPort.WEST_PREDICATE);
        } else {
            iterator = node.getPorts().iterator();
            iterator = Iterators.filter(iterator, LPort.EAST_PREDICATE);
        }
        return iterator;
    }

    private boolean edgeConnectsUpperNodeAndLowerNode(final LEdge edge) {
        LNode targetNode = edge.getTarget().getNode();
        LNode sourceNode = edge.getSource().getNode();
        boolean goesFromUpperToLower = targetNode == upperNode && sourceNode == lowerNode;
        boolean goesFromLowerToUpper = targetNode == lowerNode && sourceNode == upperNode;
        return goesFromUpperToLower || goesFromLowerToUpper;
    }

    private boolean portOrderIsFixedFor(final LNode node) {
        return node.getProperty(LayoutOptions.PORT_CONSTRAINTS).isOrderFixed();
    }

    private boolean isInLayer(final LEdge edge) {
        Layer currentLayer = layer[0].getLayer();
        Layer sourceLayer = edge.getSource().getNode().getLayer();
        Layer targetLayer = edge.getTarget().getNode().getLayer();
        boolean isInLayerEdge = sourceLayer == currentLayer && targetLayer == currentLayer;
        return isInLayerEdge;
    }
}
