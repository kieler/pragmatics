/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
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

import de.cau.cs.kieler.klay.layered.graph.LNode;

/**
 * Uses greedy switch to reduce crossing number by recounting the number of crossings in layer for
 * every feasible switch.
 *
 * @author alan
 *
 */
public final class GreedySwitchCounterProcessor extends AbstractGreedySwitchProcessor {

    private GreedySwitchCounterProcessor(final boolean considerAllCrossings) {
        super(considerAllCrossings);
    }

    /**
     * This greedy switcher will count all crossings to the free layer for each possible switch and
     * consider only one neighboring layer for each layer whose node order should be switched.
     * 
     * @return careless {@link GreedySwitchCounterProcessor}
     */
    public static GreedySwitchCounterProcessor createConsideringOneSidedCrossings() {
        return new GreedySwitchCounterProcessor(false);
    }

    /**
     * This greedy switcher will count all crossings to the free layer for each possible switch and
     * will consider both neighboring layers for each layer whose node order should be switched. @
     * 
     * @return careful {@link GreedySwitchCounterProcessor}
     */
    public static GreedySwitchCounterProcessor createConsideringAllCrossings() {
        return new GreedySwitchCounterProcessor(true);
    }

    private int amountOfCrossingsInCurrentLayer = 0;
    private boolean crossingsInLayerHaveBeenCalculated = false;
    private CrossingCounter crossingCounter;

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean checkIfSwitchReducesCrossings(final LNode[][] currentOrder,
            final int freeLayerIndex, final int fixedLayerIndex, final int upperNodeIndex,
            final int lowerNodeIndex) {
        final LNode[] freeLayer = currentOrder[freeLayerIndex];

        initializeCrossingCounter(upperNodeIndex, lowerNodeIndex, freeLayer);

        final LNode[] fixedLayer = currentOrder[fixedLayerIndex];
        amountOfCrossingsInCurrentLayer =
                calculateCurrentCrossings(currentOrder, freeLayerIndex, fixedLayerIndex, freeLayer,
                        fixedLayer);

        if (amountOfCrossingsInCurrentLayer == 0) {
            return false;
        }

        exchangeNodes(upperNodeIndex, lowerNodeIndex, freeLayer, freeLayerIndex);

        final int newAmountOfCrossings =
                calculateCrossings(currentOrder, freeLayerIndex, fixedLayerIndex,
                        super.isConsiderAllCrossings(), freeLayer, fixedLayer);

        exchangeNodes(lowerNodeIndex, upperNodeIndex, freeLayer, freeLayerIndex);

        final boolean switchReducesCrossings =
                newAmountOfCrossings < amountOfCrossingsInCurrentLayer;
        if (switchReducesCrossings) {
            amountOfCrossingsInCurrentLayer = newAmountOfCrossings;
        }

        return switchReducesCrossings;
    }

    private int calculateCurrentCrossings(final LNode[][] currentOrder, final int freeLayerIndex,
            final int fixedLayerIndex, final LNode[] freeLayer, final LNode[] fixedLayer) {
        if (!crossingsInLayerHaveBeenCalculated) {
            amountOfCrossingsInCurrentLayer =
                    calculateCrossings(currentOrder, freeLayerIndex, fixedLayerIndex,
                            super.isConsiderAllCrossings(), freeLayer, fixedLayer);
            crossingsInLayerHaveBeenCalculated = true;
        }
        return amountOfCrossingsInCurrentLayer;
    }

    private void initializeCrossingCounter(final int upperNodeIndex, final int lowerNodeIndex,
            final LNode[] freeLayer) {
        final boolean isNewFreeLayer = upperNodeIndex == 0 && lowerNodeIndex == 1;
        if (isNewFreeLayer) {
            crossingsInLayerHaveBeenCalculated = false;
            crossingCounter = new CrossingCounter(freeLayer[0].getGraph());
        }
    }

    private int calculateCrossings(final LNode[][] currentGraph, final int freeLayerIndex,
            final int fixedLayerIndex, final boolean calculateOnBothSides, final LNode[] freeLayer,
            final LNode[] fixedLayer) {
        int crossings = 0;
        if (calculateOnBothSides) {
            crossings =
                    calculateCrossingsOnBothSides(currentGraph, freeLayerIndex, freeLayer,
                            fixedLayer);
        } else {
            crossings =
                    calculateCrossingsFromOneSide(freeLayerIndex, fixedLayerIndex, freeLayer,
                            fixedLayer);
        }
        return crossings;
    }

    private int calculateCrossingsFromOneSide(final int freeLayerIndex, final int fixedLayerIndex,
            final LNode[] freeLayer, final LNode[] fixedLayer) {
        int crossings;
        final boolean fixedLayerIsEastOfFreeLayer = fixedLayerIndex < freeLayerIndex;
        crossings =
                crossingCounter.countCrossingsBetweenLayers(fixedLayer, freeLayer,
                        fixedLayerIsEastOfFreeLayer);
        return crossings;
    }

    private int calculateCrossingsOnBothSides(final LNode[][] currentGraph,
            final int freeLayerIndex, final LNode[] freeLayer, final LNode[] fixedLayer) {
        int crossings;
        if (freeLayerIndex == 0 && currentGraph.length > 1) {
            final LNode[] fixedLayerEastOfFreeLayer = currentGraph[freeLayerIndex + 1];
            crossings =
                    crossingCounter.countCrossingsBetweenLayers(fixedLayerEastOfFreeLayer,
                            freeLayer, false);
        } else if (freeLayerIndex == currentGraph.length - 1) {
            crossings = crossingCounter.countCrossingsBetweenLayers(fixedLayer, freeLayer, true);
        } else {
            final LNode[] fixedLayerEastOfFreeLayer = currentGraph[freeLayerIndex + 1];
            crossings = crossingCounter.countCrossingsBetweenLayers(fixedLayer, freeLayer, true);
            crossings +=
                    crossingCounter.countCrossingsBetweenLayers(fixedLayerEastOfFreeLayer,
                            freeLayer, false);
        }
        return crossings;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int getAmountOfCrossings(final LNode[][] currentOrder) {
        crossingCounter = new CrossingCounter(super.getGraph());
        return crossingCounter.countAllCrossingsInGraphWithOrder(currentOrder);
    }
}
