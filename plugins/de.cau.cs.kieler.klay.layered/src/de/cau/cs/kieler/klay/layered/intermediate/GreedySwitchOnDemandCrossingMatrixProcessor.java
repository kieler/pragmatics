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

import de.cau.cs.kieler.klay.layered.graph.LNode;

/**
 * @author alan
 *
 */
public class GreedySwitchOnDemandCrossingMatrixProcessor extends AbstractGreedySwitchProcessor {

    private int[][] crossingMatrix;
    private boolean[][] isCrossingMatrixFilled;

    /**
     * Calls super constructor.
     *
     * @param considerAllCrossings
     *            true when greedy switch should consider all neighboring layers of the layer whose
     *            node order should be switched.
     */
    public GreedySwitchOnDemandCrossingMatrixProcessor(final boolean considerAllCrossings) {
        super(considerAllCrossings);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getAmountOfCrossings(final LNode[][] currentOrder) {
        final CrossingCounter allCrossingsCounter = new CrossingCounter(super.getGraph());
        final int result = allCrossingsCounter.countAllCrossingsInGraphWithOrder(currentOrder);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean checkIfSwitchReducesCrossings(final LNode[][] currentGraph,
            final int freeLayerIndex, final int fixedLayerIndex, final int upperNodeIndex,
            final int lowerNodeIndex) {

        final LNode[] freeLayer = currentGraph[freeLayerIndex];
        if (crossingMatrix == null) {
            initializeMatrices(freeLayer);
        }

        final LNode upperNode = freeLayer[upperNodeIndex];
        final LNode lowerNode = freeLayer[lowerNodeIndex];
        if (!isCrossingMatrixFilled[upperNode.id][lowerNode.id]) {
            fillCrossingMatrix(currentGraph, freeLayerIndex, fixedLayerIndex, upperNode, lowerNode);
        }

        final int[][] resultMatrix = checkForCrossingMatrixReset(lowerNodeIndex, freeLayer);

        return resultMatrix[upperNode.id][lowerNode.id] > resultMatrix[lowerNode.id][upperNode.id];

    }

    private void initializeMatrices(final LNode[] freeLayer) {
        crossingMatrix = new int[freeLayer.length][freeLayer.length];
        isCrossingMatrixFilled = new boolean[freeLayer.length][freeLayer.length];
    }

    private int[][] checkForCrossingMatrixReset(final int lowerNodeIndex, final LNode[] freeLayer) {
        final int[][] resultMatrix = crossingMatrix;
        final boolean reachedEndOfLayer = lowerNodeIndex == freeLayer.length - 1;
        if (reachedEndOfLayer) {
            crossingMatrix = null;
        }
        return resultMatrix;
    }

    private void fillCrossingMatrix(final LNode[][] currentGraph, final int freeLayerIndex,
            final int fixedLayerIndex, final LNode upperNode, final LNode lowerNode) {
        if (super.isConsiderAllCrossings()) {
            calculateCrossingsOnBothSides(currentGraph, freeLayerIndex, upperNode, lowerNode);
        } else {
            calculateCrossingsOnOneSide(freeLayerIndex, fixedLayerIndex, upperNode, lowerNode);
        }
    }

    private void calculateCrossingsOnOneSide(final int freeLayerIndex, final int fixedLayerIndex,
            final LNode upperNode, final LNode lowerNode) {
        final boolean fixedLayerEastOfFreeLayer = fixedLayerIndex < freeLayerIndex;
        calculateOneSidedCrossingMatrixEntry(upperNode, lowerNode, fixedLayerEastOfFreeLayer,
                freeLayerIndex);
    }

    private void calculateCrossingsOnBothSides(final LNode[][] currentGraph,
            final int freeLayerIndex, final LNode upperNode, final LNode lowerNode) {
        final boolean isFreeLayerFirst = freeLayerIndex == 0;
        final boolean isFreeLayerLast = freeLayerIndex == currentGraph.length - 1;
        if (isFreeLayerFirst) {
            calculateOneSidedCrossingMatrixEntry(upperNode, lowerNode, true, freeLayerIndex);
        } else if (isFreeLayerLast) {
            calculateOneSidedCrossingMatrixEntry(upperNode, lowerNode, false, freeLayerIndex);
        } else {
            calculateOneSidedCrossingMatrixEntry(upperNode, lowerNode, true, freeLayerIndex);
            calculateOneSidedCrossingMatrixEntry(upperNode, lowerNode, false, freeLayerIndex);
        }
    }

    private void calculateOneSidedCrossingMatrixEntry(final LNode upperNode, final LNode lowerNode,
            final boolean fixedLayerEastOfFreeLayer, final int freeLayerIndex) {
        final int[] nodeDegrees =
                fixedLayerEastOfFreeLayer ? super.getWestNodeDegrees()[freeLayerIndex] : super
                        .getEastNodeDegrees()[freeLayerIndex];

        calculateBetweenLayerCrossings(upperNode, lowerNode, fixedLayerEastOfFreeLayer,
                freeLayerIndex, nodeDegrees);

        calculateInLayerCrossings(upperNode, lowerNode, fixedLayerEastOfFreeLayer, freeLayerIndex,
                nodeDegrees);

        isCrossingMatrixFilled[upperNode.id][lowerNode.id] = true;
        isCrossingMatrixFilled[lowerNode.id][upperNode.id] = true;
    }

    private void calculateInLayerCrossings(final LNode upperNode, final LNode lowerNode,
            final boolean fixedLayerEastOfFreeLayer, final int freeLayerIndex,
            final int[] nodeDegrees) {
        final TwoNodeInLayerEdgeCrossingCounter inLayerEdgeCrossCounter =
                new TwoNodeInLayerEdgeCrossingCounter(getNodePositions()[freeLayerIndex],
                        nodeDegrees, fixedLayerEastOfFreeLayer, getPortIndices());
        inLayerEdgeCrossCounter.countCrossings(upperNode, lowerNode);
        crossingMatrix[upperNode.id][lowerNode.id] +=
                inLayerEdgeCrossCounter.getCrossingsForOrderUpperLower();
        crossingMatrix[lowerNode.id][upperNode.id] +=
                inLayerEdgeCrossCounter.getCrossingsForOrderLowerUpper();
    }

    private void calculateBetweenLayerCrossings(final LNode upperNode, final LNode lowerNode,
            final boolean fixedLayerEastOfFreeLayer, final int freeLayerIndex,
            final int[] nodeDegrees) {
        final TwoNodeTwoLayerCrossingCounter incidentEdgeCrossCounter =
                new TwoNodeTwoLayerCrossingCounter(upperNode, lowerNode, fixedLayerEastOfFreeLayer,
                        nodeDegrees, getNodePositions()[freeLayerIndex], getPortIndices());
        incidentEdgeCrossCounter.countCrossings();
        crossingMatrix[upperNode.id][lowerNode.id] +=
                incidentEdgeCrossCounter.getCrossingsForOrderUpperLower();
        crossingMatrix[lowerNode.id][upperNode.id] +=
                incidentEdgeCrossCounter.getCrossingsForOrderLowerUpper();
    }

}
