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

import de.cau.cs.kieler.klay.layered.graph.LNode;

/**
 * The {@link CounterSwitchDecider}s decide on whether to switch neighboring nodes by counting all
 * current crossings between the free layer and one or two adjacent fixed layers, then switching the
 * nodes and recounting.
 * 
 * @author alan
 */
abstract class CounterSwitchDecider extends SwitchDecider {

    private final BetweenLayerEdgeAllCrossingsCounter betweenLayerCounter;
    private int numberOfCrossingsInCurrentLayer = 0;
    private InLayerEdgeAllCrossingsCounter inLayerEdgeAllCrossingCounter;
    private NorthSouthEdgeAllCrossingsCounter northSouthPortAllCrossingCounter;

    /**
     * Constructs CounterSwitchDecider.
     * 
     * @param freeLayerIndex
     *            the free layer index.
     * @param graph
     *            the graph as LNode[][]
     * @throws SwitchDeciderException
     *             on faulty input.
     */
    public CounterSwitchDecider(final int freeLayerIndex, final LNode[][] graph) {
        super(freeLayerIndex, graph);
        betweenLayerCounter = new BetweenLayerStraightEdgeAllCrossingsCounter(super.getGraph());
    }

    @Override
    public boolean doesSwitchReduceCrossings(final int upperNodeIndex, final int lowerNodeIndex) {

        if (super.constraintsPreventSwitch(upperNodeIndex, lowerNodeIndex)) {
            return false;
        }

        numberOfCrossingsInCurrentLayer = calculateCrossings();

        if (numberOfCrossingsInCurrentLayer == 0) {
            return false;
        }
        LNode upperNode = super.getFreeLayer()[upperNodeIndex];
        LNode lowerNode = super.getFreeLayer()[upperNodeIndex];
        notifyOfSwitch(upperNode, lowerNode);
        switchNodes(upperNodeIndex, lowerNodeIndex);

        int newNumberOfCrossings = calculateCrossings();

        switchNodes(upperNodeIndex, lowerNodeIndex);
        notifyOfSwitch(upperNode, upperNode);

        boolean switchReducesCrossings = newNumberOfCrossings < numberOfCrossingsInCurrentLayer;
        if (switchReducesCrossings) {
            numberOfCrossingsInCurrentLayer = newNumberOfCrossings;
        }

        return switchReducesCrossings;
    }

    @Override
    public void notifyOfSwitch(final LNode upperNode, final LNode lowerNode) {
        inLayerEdgeAllCrossingCounter.notifyOfSwitch(upperNode, lowerNode);
        northSouthPortAllCrossingCounter.notifyNodeSwitch(upperNode, lowerNode);
    }

    /**
     * Calculate number of crossings to be considered.
     * 
     * @return Number of crossings.
     */
    abstract int calculateCrossings();

    protected BetweenLayerEdgeAllCrossingsCounter getBetweenLayerCounter() {
        return betweenLayerCounter;
    }

    protected InLayerEdgeAllCrossingsCounter getInLayerCounterFor(final LNode[] layer) {
        inLayerEdgeAllCrossingCounter = new InLayerEdgeAllCrossingsCounter(layer);
        return inLayerEdgeAllCrossingCounter;
    }

    protected NorthSouthEdgeAllCrossingsCounter getNorthSoutPortCounterFor(final LNode[] layer) {
        northSouthPortAllCrossingCounter = new NorthSouthEdgeAllCrossingsCounter(layer);
        return northSouthPortAllCrossingCounter;
    }

}
