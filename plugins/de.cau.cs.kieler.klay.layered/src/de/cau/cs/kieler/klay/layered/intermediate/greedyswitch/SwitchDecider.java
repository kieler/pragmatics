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

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.properties.InternalProperties;
import de.cau.cs.kieler.klay.layered.properties.NodeType;

/**
 * 
 * This class decides whether two neighboring nodes should be switched. There are two variants:
 * <ul>
 * <li>OneSided – The traditional risky way: The decider checks if a switch would reduce crossings
 * on the given side of the layer whose nodes are to be switched.
 * <li>TwoSided – The faithless way: The decider checks if a switch would reduce crossings on both
 * sides of the layer whose nodes are to be switched.
 * </ul>
 * 
 * @author alan
 */
abstract class SwitchDecider {
    private final LNode[] freeLayer;
    private final boolean[][] isCrossingMatrixFilled;
    private final int[][] crossingMatrix;
    private final InLayerEdgeTwoNodeCrossingCounter inLayerCounter;
    private final NorthSouthEdgeNeighbouringNodeCrossingsCounter northSouthCounter;
    private final BetweenLayerEdgeTwoNodeCrossingsCounter inBetweenLayerCrossingCounter;
    /** To avoid interdependency errors between classes, the .id field of nodes is not used. */
    private final Map<LNode, Integer> nodeIds;

    /**
     * Creates SwitchDecider.
     * 
     * @param freeLayerIndex
     *            The freeLayer to switch in.
     * @param graph
     *            The graph as LNode[][]
     * @throws SwitchDeciderException
     *             on faulty input
     */
    SwitchDecider(final int freeLayerIndex, final LNode[][] graph) {
        if (freeLayerIndex >= graph.length) {
            throw new IndexOutOfBoundsException(
                    "Greedy SwitchDecider: Free layer layer not in graph.");
        }
        freeLayer = graph[freeLayerIndex];

        isCrossingMatrixFilled =
                new boolean[freeLayer.length][freeLayer.length];
        crossingMatrix = new int[freeLayer.length][freeLayer.length];

        inBetweenLayerCrossingCounter =
                new BetweenLayerEdgeTwoNodeCrossingsCounter(graph, freeLayerIndex);
        inLayerCounter = new InLayerEdgeTwoNodeCrossingCounter(freeLayer);
        northSouthCounter = new NorthSouthEdgeNeighbouringNodeCrossingsCounter(freeLayer);

        nodeIds = Maps.newHashMap();
        initializeNodePositions(graph);
    }

    public final void notifyOfSwitch(final LNode upperNode, final LNode lowerNode) {
        inLayerCounter.notifyOfSwitch(upperNode, lowerNode);
    }

    /**
     * {@inheritDoc}
     */
    public final boolean doesSwitchReduceCrossings(final int upperNodeIndex,
            final int lowerNodeIndex) {
        if (constraintsPreventSwitch(upperNodeIndex, lowerNodeIndex)) {
            return false;
        }

        LNode upperNode = freeLayer[upperNodeIndex];
        LNode lowerNode = freeLayer[lowerNodeIndex];

        inLayerCounter.countCrossingsBetweenNodes(upperNode, lowerNode);
        northSouthCounter.countCrossings(upperNode, lowerNode);

        int upperLowerCrossings =
                getCrossingMatrixEntry(upperNode, lowerNode)
                        + inLayerCounter.getUpperLowerCrossings()
                        + northSouthCounter.getUpperLowerCrossings();
        int lowerUpperCrossings =
                getCrossingMatrixEntry(lowerNode, upperNode)
                        + inLayerCounter.getLowerUpperCrossings()
                        + northSouthCounter.getLowerUpperCrossings();

        return upperLowerCrossings > lowerUpperCrossings;
    }

    private int getCrossingMatrixEntry(final LNode upperNode, final LNode lowerNode) {
        if (!isCrossingMatrixFilled[idOf(upperNode)][idOf(lowerNode)]) {
            fillCrossingMatrix(upperNode, lowerNode);
            isCrossingMatrixFilled[idOf(upperNode)][idOf(lowerNode)] = true;
            isCrossingMatrixFilled[idOf(lowerNode)][idOf(upperNode)] = true;
        }
        return crossingMatrix[idOf(upperNode)][idOf(lowerNode)];
    }

    protected abstract void fillCrossingMatrix(LNode upperNode, LNode lowerNode);

    protected final void setCrossingMatrixEntriesFromCounter(final LNode upperNode,
            final LNode lowerNode) {
        crossingMatrix[idOf(upperNode)][idOf(lowerNode)] =
                inBetweenLayerCrossingCounter.getUpperLowerCrossings();
        crossingMatrix[idOf(lowerNode)][idOf(upperNode)] =
                inBetweenLayerCrossingCounter.getLowerUpperCrossings();
    }

    /**
     * Check if in layer {@link InternalProperties.IN_LAYER_SUCCESSOR_CONSTRAINTS} or
     * {@link InternalProperties.IN_LAYER_LAYOUT_UNIT} constraints prevent a possible switch or if
     * the nodes are a normal node and a north south port dummy.
     * 
     * @param nodeIndex
     *            the index of the upper node, assuming a left-right order.
     * @param lowerNodeIndex
     *            the index of the lower node, assuming a left-right order.
     * @return true if constraints should prevent switching.
     */
    private boolean constraintsPreventSwitch(final int nodeIndex, final int lowerNodeIndex) {
        LNode upperNode = freeLayer[nodeIndex];
        LNode lowerNode = freeLayer[lowerNodeIndex];

        return haveSuccessorConstraints(upperNode, lowerNode)
                || haveLayoutUnitConstraints(upperNode, lowerNode)
                || areNormalAndNorthSouthPortDummy(upperNode, lowerNode);
    }

    private boolean haveSuccessorConstraints(final LNode upperNode, final LNode lowerNode) {
        List<LNode> constraints =
                upperNode.getProperty(InternalProperties.IN_LAYER_SUCCESSOR_CONSTRAINTS);
        boolean hasSuccessorConstraint =
                constraints != null && constraints.size() != 0 && constraints.contains(lowerNode);
        return hasSuccessorConstraint;
    }

    private boolean haveLayoutUnitConstraints(final LNode upperNode, final LNode lowerNode) {
        NodeType firstNodeType = upperNode.getProperty(InternalProperties.NODE_TYPE);
        NodeType secondNodeType = lowerNode.getProperty(InternalProperties.NODE_TYPE);
        boolean neitherNodeIsLongEdgeDummy =
                firstNodeType != NodeType.LONG_EDGE && secondNodeType != NodeType.LONG_EDGE;

        // If upperNode and lowerNode are part of a layout unit not only containing themselves,
        // then the layout units must be equal for a switch to be allowed.
        LNode upperLayoutUnit = upperNode.getProperty(InternalProperties.IN_LAYER_LAYOUT_UNIT);
        LNode lowerLayoutUnit = lowerNode.getProperty(InternalProperties.IN_LAYER_LAYOUT_UNIT);
        boolean nodesHaveLayoutUnits =
                partOfMultiNodeLayoutUnit(upperNode, upperLayoutUnit)
                        || partOfMultiNodeLayoutUnit(lowerNode, lowerLayoutUnit);
        boolean areInDifferentLayoutUnits = upperLayoutUnit != lowerLayoutUnit;

        boolean upperNodeHasNorthernEdges = hasEdgesOnSide(upperNode, PortSide.NORTH);
        boolean lowerNodeHasSouthernEdges = hasEdgesOnSide(lowerNode, PortSide.SOUTH);

        boolean hasLayoutUnitConstraint =
                nodesHaveLayoutUnits && areInDifferentLayoutUnits || upperNodeHasNorthernEdges
                        || lowerNodeHasSouthernEdges;

        return neitherNodeIsLongEdgeDummy && hasLayoutUnitConstraint;
    }

    private boolean hasEdgesOnSide(final LNode node, final PortSide side) {
        Iterable<LPort> ports = node.getPorts(side);
        for (LPort port : ports) {
            if (!port.getProperty(InternalProperties.CONNECTED_NORTH_SOUTH_PORT_DUMMIES).isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private boolean partOfMultiNodeLayoutUnit(final LNode node, final LNode layoutUnit) {
        return layoutUnit != null && layoutUnit != node;
    }

    private boolean areNormalAndNorthSouthPortDummy(final LNode upperNode, final LNode lowerNode) {
        return isNorthSouthPortNode(upperNode) && isNormalNode(lowerNode)
                || isNorthSouthPortNode(lowerNode) && isNormalNode(upperNode);
    }

    private boolean isNormalNode(final LNode node) {
        return node.getProperty(InternalProperties.NODE_TYPE) == NodeType.NORMAL;
    }

    private boolean isNorthSouthPortNode(final LNode node) {
        return node.getProperty(InternalProperties.NODE_TYPE) == NodeType.NORTH_SOUTH_PORT;
    }

    protected final BetweenLayerEdgeTwoNodeCrossingsCounter getTwoLayerCrossCounter() {
        return inBetweenLayerCrossingCounter;
    }

    private void initializeNodePositions(final LNode[][] g) {
        for (LNode[] layer : g) {
            int id = 0;
            for (LNode node : layer) {
                nodeIds.put(node, id++);
            }
        }
    }

    private int idOf(final LNode node) {
        return nodeIds.get(node);
    }
}
