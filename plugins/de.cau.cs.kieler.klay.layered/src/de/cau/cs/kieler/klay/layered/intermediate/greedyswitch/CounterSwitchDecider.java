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
 * See {@link SwitchDecider} for details. TODO-alan
 * 
 * @author alan
 */
abstract class CounterSwitchDecider extends SwitchDecider {

    private final LNode[] freeLayer;
    private final CrossingCounter crossingCounter;
    private int amountOfCrossingsInCurrentLayer = 0;

    /**
     * Constructs CounterSwitchDecider. TODO-alan
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
        freeLayer = getLayerForIndex(freeLayerIndex);
        crossingCounter = new CrossingCounter(super.getGraph());
    }

    @Override
    public boolean doesSwitchReduceCrossings(final int upperNodeIndex, final int lowerNodeIndex) {

        if (super.constraintsPreventSwitch(upperNodeIndex, lowerNodeIndex)) {
            return false;
        }

        amountOfCrossingsInCurrentLayer = calculateCrossings();

        if (amountOfCrossingsInCurrentLayer == 0) {
            return false;
        }

        exchangeNodes(upperNodeIndex, lowerNodeIndex);

        int newAmountOfCrossings = calculateCrossings();

        exchangeNodes(lowerNodeIndex, upperNodeIndex);

        boolean switchReducesCrossings = newAmountOfCrossings < amountOfCrossingsInCurrentLayer;
        if (switchReducesCrossings) {
            amountOfCrossingsInCurrentLayer = newAmountOfCrossings;
        }

        return switchReducesCrossings;
    }

    private void exchangeNodes(final int upperNodeIndex, final int lowerNodeIndex) {
        LNode temp = freeLayer[upperNodeIndex];
        freeLayer[upperNodeIndex] = freeLayer[lowerNodeIndex];
        freeLayer[lowerNodeIndex] = temp; // TODO-alan check if this works
    }

    /**
     * Calculate amount of crossings to be considered.
     * 
     * @return Amount of crossings.
     */
    abstract int calculateCrossings();

    /**
     * Returns Crossing Counter.
     * 
     * @return the crossing counter
     */
    CrossingCounter getCrossingCounter() {
        return crossingCounter;
    }

}
