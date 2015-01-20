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

import java.util.List;

import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.properties.InternalProperties;

/**
 * TODO-alan This class is an abstract superclass for six different variants of the greedy switch
 * algorithm: {@link GreedySwitchCounterProcessor} uses {@link CrossingCounterType} to count the
 * amount of crossings that would result from a switch. {@link GreedySwitchCrossingMatrixProcessor}
 * calculates the crossing matrix for a pair of fixed and free layers beforehand. Entries i,j in a
 * crossing matrix show the amount of crossings between incident edges to nodes i and j when node i
 * is above node j. {@link GreedySwitchOnDemandCrossingMatrixProcessor} calculates the entries in
 * the crossing matrix only when needed. The last two both use
 * {@link TwoNodeTwoLayerCrossingCounter} which calculates two entries i,j and j,i in the crossing
 * matrix. All variants can
 * 
 * @author alan
 */
public abstract class SwitchDecider {
    private final LNode[][] graph;
    private final int freeLayerIndex;

    /**
     * SweepDirection enum for one sided switchers.
     * 
     * @author alan
     *
     */
    public static enum SweepDirection {
        /** Sweep across graph from left to right. */
        FORWARD,
        /** Sweep across graph from right to left. */
        BACKWARD
    }

    /**
     * Use factory method to create correct switchDecider, not this constructor. TODO-alan actually
     * class access should be restricted, however tests are not in same package at the moment.
     * 
     * @param freeLayerIndex
     *            The freeLayer to switch in.
     * @param graph
     *            The graph as LNode[][]
     * @throws SwitchDeciderException
     *             on faulty input
     */
    protected SwitchDecider(final int freeLayerIndex, final LNode[][] graph)
            throws SwitchDeciderException {
        if (graph.length - 1 < freeLayerIndex) {
            throw new SwitchDeciderException(freeLayerIndex + " is out of graph bounds for graph.");
        }
        this.freeLayerIndex = freeLayerIndex;
        this.graph = graph;
    }

    /**
     * Calculate if switch reduces crossings.
     * 
     * @param upperNodeIndex
     *            the upper node assuming left-right alignment.
     * @param lowerNodeIndex
     *            the lower node assuming left-right alignment.
     * @return whether switching can help.
     */
    public abstract boolean doesSwitchReduceCrossings(final int upperNodeIndex,
            final int lowerNodeIndex);

    /**
     * Check if in layer {@link InternalProperties.IN_LAYER_SUCCESSOR_CONSTRAINTS} or
     * {@link InternalProperties.IN_LAYER_LAYOUT_UNIT} constraints prevent a possible switch.
     * 
     * @param freeLayer
     *            the layer currently being switched in.
     * @param upperNodeIndex
     *            the index of the upper node, assuming a left-right order.
     * @param lowerNodeIndex
     *            the index of the lower node, assuming a left-right order.
     * @return whether true if constraints should prevent switching.
     */
    protected boolean constraintsPreventSwitch(final LNode[] freeLayer, final int upperNodeIndex,
            final int lowerNodeIndex) {
        LNode upperNode = freeLayer[upperNodeIndex];
        LNode lowerNode = freeLayer[lowerNodeIndex];

        List<LNode> constraints =
                upperNode.getProperty(InternalProperties.IN_LAYER_SUCCESSOR_CONSTRAINTS);
        boolean hasSuccessorConstraint =
                constraints != null && constraints.size() != 0 && constraints.contains(lowerNode);

        // If upperNode and lowerNode are part of a layout unit, then the layout units must
        // be equal for a switch to be allowed.
        LNode upperLayoutUnit = upperNode.getProperty(InternalProperties.IN_LAYER_LAYOUT_UNIT);
        LNode lowerLayoutUnit = lowerNode.getProperty(InternalProperties.IN_LAYER_LAYOUT_UNIT);
        if (upperLayoutUnit != null && lowerLayoutUnit != null) { // Are they null if not set?
                                                                  // TODO-alan
            hasSuccessorConstraint |= upperLayoutUnit != lowerLayoutUnit; // ????
        }
        return hasSuccessorConstraint;
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
}
