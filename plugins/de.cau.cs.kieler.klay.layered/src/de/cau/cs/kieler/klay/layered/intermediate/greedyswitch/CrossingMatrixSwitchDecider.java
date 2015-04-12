/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.intermediate.greedyswitch;

import java.util.Map;

import com.google.common.collect.Maps;

import de.cau.cs.kieler.klay.layered.graph.LNode;

/**
 * A crossing matrix saves the amount of crossings caused by between layer edges incident to two
 * nodes: Entry i,j shows the amount of crossings of these edges when i is above j and entry j,i
 * shows the amount of crossings of these edges when j is above i.<br>
 * 
 * @author alan
 *
 */
abstract class CrossingMatrixSwitchDecider extends SwitchDecider {

    private final InLayerEdgeTwoNodeCrossingCounter inLayerCounter;
    private final NorthSouthEdgeNeighbouringNodeCrossingsCounter northSouthCounter;
    private final BetweenLayerEdgeTwoNodeCrossingsCounter inBetweenLayerCrossingCounter;
    /** To avoid interdependency errors between classes, the .id field of nodes is not used. */
    private final Map<LNode, Integer> nodeIds;

    public CrossingMatrixSwitchDecider(final int freeLayerIndex, final LNode[][] graph) {
        super(freeLayerIndex, graph);
        inBetweenLayerCrossingCounter =
                new BetweenLayerEdgeTwoNodeCrossingsCounter(graph, freeLayerIndex);
        inLayerCounter = new InLayerEdgeTwoNodeCrossingCounter(super.getFreeLayer());
        northSouthCounter = new NorthSouthEdgeNeighbouringNodeCrossingsCounter(super.getFreeLayer());
        nodeIds = Maps.newHashMap();
        initializeNodePositions(graph);
    }

    private void initializeNodePositions(final LNode[][] graph) {
        for (LNode[] layer : graph) {
            int id = 0;
            for (LNode node : layer) {
                nodeIds.put(node, id++);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean doesSwitchReduceCrossings(final int upperNodeIndex, final int lowerNodeIndex) {
        if (super.constraintsPreventSwitch(upperNodeIndex, lowerNodeIndex)) {
            return false;
        }

        LNode upperNode = super.getFreeLayer()[upperNodeIndex];
        LNode lowerNode = super.getFreeLayer()[lowerNodeIndex];

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

    @Override
    public void notifyOfSwitch(final LNode upperNode, final LNode lowerNode) {
        inLayerCounter.notifyOfSwitch(upperNode, lowerNode);
    }

    protected abstract int getCrossingMatrixEntry(final LNode upperNode, final LNode lowerNode);

    protected BetweenLayerEdgeTwoNodeCrossingsCounter getTwoLayerCrossCounter() {
        return inBetweenLayerCrossingCounter;
    }

    protected int idOf(final LNode node) {
        return nodeIds.get(node);
    }

}
