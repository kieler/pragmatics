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

import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.properties.InternalProperties;
import de.cau.cs.kieler.klay.layered.properties.NodeType;

/**
 * This class is an abstract superclass for six different variants of how to decide, whether two
 * neighboring nodes should be switched: <br>
 * The {@link CounterSwitchDecider} classes use {@link AllCrossingsCounter} to count the amount of
 * crossings that would result from a switch. <br>
 * The {@link CrossingMatrixSwitchDecider} classes calculate the crossing matrix for a pair of fixed
 * and free layers beforehand. Entries i,j in a crossing matrix show the amount of crossings between
 * incident edges to nodes i and j when node i is above node j. <br>
 * The {@link OnDemandCrossingMatrixSwitchDecider} classes calculate the entries in the crossing
 * matrix only when needed. <br>
 * These last two categories both mainly use {@link BetweenLayerEdgeTwoNodeCrossingsCounter} which
 * calculates two entries i,j and j,i in the crossing matrix.
 * 
 * All three categories exist in two variants: <br>
 * - OneSided – The traditional risky way: The decider checks if a switch would reduce crossings on
 * the given side of the layer whose nodes are to be switched. <br>
 * - TwoSided – The faithless way: The decider checks if a switch would reduce crossings on both
 * sides of the layer whose nodes are to be switched.
 * 
 * @author alan
 */
abstract class SwitchDecider {
    private final LNode[][] graph;
    private final int freeLayerIndex;
    private final LNode[] freeLayer;

    /**
     * Use factory method to create correct switchDecider, not this constructor.
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
            throw new IndexOutOfBoundsException();
        }
        this.freeLayerIndex = freeLayerIndex;
        freeLayer = graph[freeLayerIndex];
        this.graph = graph;
    }

    /**
     * Calculate if switch reduces crossings.
     * 
     * @param upperNodeIndex
     *            the index of the upper node assuming left-right alignment.
     * @param lowerNodeIndex
     *            the index of thelower node assuming left-right alignment.
     * @return whether switching can help.
     */
    public abstract boolean doesSwitchReduceCrossings(final int upperNodeIndex,
            final int lowerNodeIndex);

    protected void switchNodes(final int upperNodeIndex, final int lowerNodeIndex) {
        LNode upperNode = graph[freeLayerIndex][upperNodeIndex];
        graph[freeLayerIndex][upperNodeIndex] = graph[freeLayerIndex][lowerNodeIndex];
        graph[freeLayerIndex][lowerNodeIndex] = upperNode;
    }

    public abstract void notifyOfSwitch(final LNode upperNode, final LNode lowerNode);

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
    protected boolean constraintsPreventSwitch(final int nodeIndex, final int lowerNodeIndex) {
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
        // If upperNode and lowerNode are part of a layout unit not only containing themselves,
        // then the layout units must be equal for a switch to be allowed.
        LNode upperLayoutUnit = upperNode.getProperty(InternalProperties.IN_LAYER_LAYOUT_UNIT);
        LNode lowerLayoutUnit = lowerNode.getProperty(InternalProperties.IN_LAYER_LAYOUT_UNIT);
        boolean nodesHaveLayoutUnits =
                partOfMultiNodeLayoutUnit(upperNode, upperLayoutUnit)
                        || partOfMultiNodeLayoutUnit(lowerNode, lowerLayoutUnit);

        NodeType firstNodeType = upperNode.getProperty(InternalProperties.NODE_TYPE);
        NodeType secondNodeType = lowerNode.getProperty(InternalProperties.NODE_TYPE);
        boolean neitherNodeIsLongEdgeDummy =
                firstNodeType != NodeType.LONG_EDGE && secondNodeType != NodeType.LONG_EDGE;

        boolean areInDifferentLayoutUnits = upperLayoutUnit != lowerLayoutUnit;

        return nodesHaveLayoutUnits && neitherNodeIsLongEdgeDummy && areInDifferentLayoutUnits;
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

    /**
     * Get Layer as LNode array for given index.
     * 
     * @param layerIndex
     *            index of the layer.
     * @return Layer as LNode array for given index.
     */
    protected LNode[] getLayerForIndex(final int layerIndex) {
        return graph[layerIndex];
    }

    /**
     * Get the graph.
     * 
     * @return the node matrix
     */
    protected LNode[][] getGraph() {
        return graph;
    }

    /**
     * Get the free layer index.
     * 
     * @return the index of the free layer.
     */
    protected int getFreeLayerIndex() {
        return freeLayerIndex;
    }

    protected boolean freeLayerIsNotFirstLayer() {
        return getFreeLayerIndex() != 0;
    }

    protected boolean freeLayerIsNotLastLayer() {
        return getFreeLayerIndex() != getGraph().length - 1;
    }

    protected LNode[] getFreeLayer() {
        return freeLayer;
    }
}
