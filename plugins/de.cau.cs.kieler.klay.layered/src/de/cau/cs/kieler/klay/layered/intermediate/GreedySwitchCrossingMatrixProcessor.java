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

import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.graph.LNode;

/**
 * This processor attempts to improve crossing number by using a simple greedy switch heuristic.
 * TODOAlan: Extend, Dependencies on In-Layer Constraint Processor?
 *
 * @author alan
 *
 */
public final class GreedySwitchCrossingMatrixProcessor extends AbstractGreedySwitchProcessor
        implements ILayoutProcessor {

    private GreedySwitchCrossingMatrixProcessor(final boolean considerAllCrossings) {
        super(considerAllCrossings);
    }

    /**
     * This greedy switcher will calculate the complete crossing matrix for the free layer and
     * consider only one neighboring layer for each layer whose node order should be switched.
     *
     * @return careless {@link GreedySwitchCrossingMatrixProcessor}
     */
    public static GreedySwitchCrossingMatrixProcessor createConsideringOneSidedCrossings() {
        return new GreedySwitchCrossingMatrixProcessor(false);
    }

    /**
     * This greedy switcher will calculate the complete crossing matrix for the free layer and will
     * consider both neighboring layers for each layer whose node order should be switched.
     * 
     * @return careful {@link GreedySwitchCrossingMatrixProcessor}
     */
    public static GreedySwitchCrossingMatrixProcessor createConsideringAllCrossings() {
        return new GreedySwitchCrossingMatrixProcessor(true);
    }

    private int[][] crossingMatrix;

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean checkIfSwitchReducesCrossings(final LNode[][] currentGraph,
            final int freeLayerIndex, final int fixedLayerIndex, final int upperNodeIndex,
            final int lowerNodeIndex) {
        final LNode[] freeLayer = currentGraph[freeLayerIndex];
        if (crossingMatrix == null) {
            crossingMatrix = new int[freeLayer.length][freeLayer.length];
        }

        if (super.isConsiderAllCrossings()) {
            calculateCrossingsOnBothSides(currentGraph, freeLayerIndex, freeLayer);
        } else {
            calculateCrossingsFromOneSide(freeLayerIndex, fixedLayerIndex, freeLayer);
        }

        final int[][] resultMatr = resetCrossingMatrixIfEndOfLayer(lowerNodeIndex, freeLayer);

        final boolean causesCrossings =
                resultMatr[upperNodeIndex][lowerNodeIndex] > resultMatr[lowerNodeIndex][upperNodeIndex];
        return causesCrossings;
    }

    private int[][] resetCrossingMatrixIfEndOfLayer(final int lowerNodeIndex,
            final LNode[] freeLayer) {
        final int[][] resultMatr = crossingMatrix;
        final boolean lowerNodeIsLastInLayer = lowerNodeIndex == freeLayer.length - 1;
        if (lowerNodeIsLastInLayer) {
            crossingMatrix = null;
        }
        return resultMatr;
    }

    private void calculateCrossingsFromOneSide(final int freeLayerIndex, final int fixedLayerIndex,
            final LNode[] freeLayer) {
        final boolean isFixedLayerEastOfFreeLayer = fixedLayerIndex < freeLayerIndex;
        calculateCrossingMatrix(freeLayer, isFixedLayerEastOfFreeLayer, freeLayerIndex);
    }

    private void calculateCrossingsOnBothSides(final LNode[][] currentGraph,
            final int freeLayerIndex, final LNode[] freeLayer) {
        final boolean isFreeLayerFirstLayer = freeLayerIndex == 0;
        final boolean isFreeLayerLastLayer = freeLayerIndex == currentGraph.length - 1;
        if (isFreeLayerFirstLayer) {
            calculateCrossingMatrix(freeLayer, false, freeLayerIndex);
        } else if (isFreeLayerLastLayer) {
            calculateCrossingMatrix(freeLayer, true, freeLayerIndex);
        } else {
            calculateCrossingMatrix(freeLayer, false, freeLayerIndex);
            calculateCrossingMatrix(freeLayer, true, freeLayerIndex);
        }
    }

    private void calculateCrossingMatrix(final LNode[] freeLayer, final boolean isFixedEastOfFree,
            final int freeLayerIndex) {
        final int matrixSize = freeLayer.length;
        final int[] nodeDegrees =
                isFixedEastOfFree ? super.getWestNodeDegrees()[freeLayerIndex] : super
                        .getEastNodeDegrees()[freeLayerIndex];
        for (int i = 0; i < matrixSize; i++) {
            for (int j = i + 1; j < matrixSize; j++) {
                final TwoNodeTwoLayerCrossingCounter crossCounter =
                        new TwoNodeTwoLayerCrossingCounter(freeLayer[i], freeLayer[j],
                                isFixedEastOfFree, nodeDegrees,
                                super.getNodePositions()[freeLayerIndex], super.getPortIndices());
                crossCounter.countCrossings();
                crossingMatrix[i][j] += crossCounter.getCrossingsForOrderUpperLower();
                crossingMatrix[j][i] += crossCounter.getCrossingsForOrderLowerUpper();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int getAmountOfCrossings(final LNode[][] currentOrder) {
        final CrossingCounter allCrossingsCounter = new CrossingCounter(super.getGraph());
        final int result = allCrossingsCounter.countAllCrossingsInGraphWithOrder(currentOrder);
        return result;
    }
}
