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
import java.util.LinkedList;
import java.util.List;

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

    private int upperLowerCrossings;
    private int lowerUpperCrossings;
    private LNode lowerNode;
    private LNode upperNode;
    private List<LEdge> upperNodeInLayerEdges;
    private final LNode[] layer;
    private List<LEdge> lowerNodeInLayerEdges;
    private int[] nodePositions;

    public TwoNodeInLayerEdgeCrossingCounter(final LNode[] nodeOrder) {
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

    public void countCrossings(final LNode upper, final LNode lower) {
        upperNode = upper;
        lowerNode = lower;
        upperNodeInLayerEdges = collectInLayerEdges(upper);
        lowerNodeInLayerEdges = collectInLayerEdges(lower);
        fetchCrossingsFromLower();
        fetchCrossingsFromUpper();
        // secondVersionCounter(upper, lower);
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

    private boolean edgeIsConnectedToNode(final LEdge edge, final LNode node) {
        return edge.getSource().getNode() == node || edge.getTarget().getNode() == node;
    }

    private boolean isSelfLoop(final LEdge edge) {
        return edge.getTarget().getNode() == edge.getSource().getNode();
    }

    public void nodesSwitchedWithIndexes(final int firstNodeIndex, final int secondNodeIndex) {
        upperLowerCrossings = 0;
        lowerUpperCrossings = 0;
        int i = nodePositions[firstNodeIndex];
        nodePositions[firstNodeIndex] = nodePositions[secondNodeIndex];
        nodePositions[secondNodeIndex] = i;
    }

    private void fetchCrossingsFromUpper() {
        for (LEdge edge : upperNodeInLayerEdges) {
            // we count in-layer edge crossings from edges which connect nodes when considering the
            // upper node
            if (edgeConnectsUpperNodeAndLowerNode(edge)) {
                // neighboring nodes can only cause crossings with connected nodes if port order is
                // fixed
                if (portOrderIsFixedFor(upperNode)) {
                    LPort port = getEdgePortForNode(upperNode, edge);
                    upperLowerCrossings += getRemainingPortsBelowForNode(port, upperNode);
                    lowerUpperCrossings += getRemainingPortsAboveForNode(port, upperNode);
                }
                if (portOrderIsFixedFor(lowerNode)) {
                    LPort port = getEdgePortForNode(lowerNode, edge);
                    upperLowerCrossings += getRemainingPortsAboveForNode(port, lowerNode);
                    lowerUpperCrossings += getRemainingPortsBelowForNode(port, lowerNode);
                }
            } else {
                PortSide edgeSide = getSideOfEdge(edge);
                if (edgeFromNodePassesFromAbove(edge, upperNode, lowerNode)) {
                    upperLowerCrossings += getAmountOfOneNodesEdgesOnSide(lowerNode, edgeSide);
                    getFixedPortOrderCrossingsForDownwardEdge(edge, upperNode);
                } else {
                    lowerUpperCrossings += getAmountOfOneNodesEdgesOnSide(lowerNode, edgeSide);
                    getFixedPortOrderCrossingsForUpwardEdge(edge, upperNode);
                }
            }
        }
    }

    private void getFixedPortOrderCrossingsForUpwardEdge(final LEdge edge, final LNode node) {
        if (portOrderIsFixedFor(node)) {
            LPort port = getEdgePortForNode(node, edge);
            upperLowerCrossings += getRemainingPortsAboveForNode(port, node);
            lowerUpperCrossings += getRemainingPortsBelowForNode(port, node);
        }
    }

    private void getFixedPortOrderCrossingsForDownwardEdge(final LEdge edge, final LNode node) {
        if (portOrderIsFixedFor(node)) {
            LPort port = getEdgePortForNode(node, edge);
            upperLowerCrossings += getRemainingPortsBelowForNode(port, node);
            lowerUpperCrossings += getRemainingPortsAboveForNode(port, node);
        }
    }

    // TODO-alan possible problem with clockwise order of ports
    private int getRemainingPortsAboveForNode(final LPort port, final LNode node) {
        int amountOfRemainingPorts = 0;
        Iterator<LPort> iterator = getPortIterator(node, port.getSide());
        while (iterator.hasNext()) {
            LPort otherPort = iterator.next();
            if (otherPort == port) {
                break;
            }
            amountOfRemainingPorts++;
        }
        return amountOfRemainingPorts;
    }

    private void fetchCrossingsFromLower() {
        for (LEdge edge : lowerNodeInLayerEdges) {
            if (!edgeConnectsUpperNodeAndLowerNode(edge)) { // only for above TODO-alan
                PortSide edgeSide = getSideOfEdge(edge);
                if (edgeFromNodePassesFromAbove(edge, lowerNode, upperNode)) {
                    lowerUpperCrossings += getAmountOfOneNodesEdgesOnSide(upperNode, edgeSide);
                    getFixedPortOrderCrossingsForUpwardEdge(edge, upperNode);
                } else {
                    upperLowerCrossings += getAmountOfOneNodesEdgesOnSide(upperNode, edgeSide);
                    getFixedPortOrderCrossingsForDownwardEdge(edge, upperNode);
                }
            }
        }
    }

    private int getRemainingPortsBelowForNode(final LPort port, final LNode node) {
        int amountOfRemainingPorts = 0;
        boolean belowGivenPort = false;
        Iterator<LPort> iterator = getPortIterator(node, port.getSide());
        while (iterator.hasNext()) {
            LPort otherPort = iterator.next();
            if (belowGivenPort) {
                amountOfRemainingPorts++;
            }
            if (otherPort == port) {
                belowGivenPort |= true;
            }
        }
        return amountOfRemainingPorts;
    }

    private LPort getEdgePortForNode(final LNode node, final LEdge edge) {
        if (edge.getTarget().getNode() == node) {
            return edge.getTarget();
        } else {
            return edge.getSource();
        }
    }

    /**
     * If this method is called with fromNode == lowerNode and passNode == upperNode, then we are
     * considering the switched situation and lowerNode is ABOVE upperNode in the layer.
     * 
     * @param edge
     *            The in-layer edge currently being considered.
     * @param fromNode
     *            The edges target or source.
     * @param passNode
     *            The node being possibly being passed by this edge.
     * @return true if edge from fromNode passes passNode
     */
    private boolean edgeFromNodePassesFromAbove(final LEdge edge, final LNode fromNode,
            final LNode passNode) {
        LNode edgeEndNode = getEdgeEndNodeFromNode(edge, fromNode);
        return positionOf(edgeEndNode) > positionOf(passNode);
    }

    private LNode getEdgeEndNodeFromNode(final LEdge edge, final LNode node) {
        boolean nodeIsSource = edge.getSource().getNode() == node;
        LPort edgeEndPort = nodeIsSource ? edge.getTarget() : edge.getSource();
        return edgeEndPort.getNode();
    }

    private int positionOf(final LNode node) {
        return nodePositions[node.id];
    }

    private List<LEdge> collectInLayerEdges(final LNode node) {
        List<LEdge> inLayerEdges = getInLayerEdgesForSide(node, PortSide.EAST);
        inLayerEdges.addAll(getInLayerEdgesForSide(node, PortSide.WEST));
        return inLayerEdges;
    }

    private List<LEdge> getInLayerEdgesForSide(final LNode node, final PortSide side) {
        Iterator<LPort> iterator = getPortIterator(node, side);
        List<LEdge> inLayerEdges = new LinkedList<LEdge>();
        while (iterator.hasNext()) {
            LPort port = iterator.next();
            if (port.getSide() != side) {
                continue;
            }
            for (LEdge edge : port.getConnectedEdges()) {
                if (isInLayer(edge)) {
                    if (!edgeConnectsUpperNodeAndLowerNode(edge) || portOrderIsFixedFor(lowerNode)
                            || portOrderIsFixedFor(upperNode)) {
                        inLayerEdges.add(edge);
                    }
                }
            }
        }
        return inLayerEdges;
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

    private PortSide getSideOfEdge(final LEdge edge) {
        assert edge.getTarget().getSide() == edge.getSource().getSide(); // TODO-alan ask
        PortSide edgeSide = edge.getTarget().getSide();
        return edgeSide;
    }

    private int getAmountOfOneNodesEdgesOnSide(final LNode node, final PortSide side) {
        int numberOfEdges = 0;
        Iterator<LPort> iterator = getPortIterator(node, side);
        while (iterator.hasNext()) {
            iterator.next();
            numberOfEdges++;
        }
        return numberOfEdges;
    }
}
