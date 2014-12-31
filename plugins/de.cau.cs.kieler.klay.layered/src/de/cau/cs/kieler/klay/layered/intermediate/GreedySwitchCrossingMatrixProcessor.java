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
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;

/**
 * This processor attempts to improve crossing number by using a simple greedy switch heuristic.
 * TODOAlan: Extend, Dependencies on In-Layer Constraint Processor?
 *
 * @author alan
 *
 */
public class GreedySwitchCrossingMatrixProcessor extends AbstractGreedySwitchProcessor implements
        ILayoutProcessor {

    public GreedySwitchCrossingMatrixProcessor(final boolean considerAllCrossings) {
        super(considerAllCrossings);
    }

    private int[][] crossingMatrix;

    // private int amountOfCrossings; TODOALAN
    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean switchReducesCrossings(final LNode[][] currentGraph,
            final int freeLayerIndex, final int fixedLayerIndex, final int currentNodeIndex,
            final int nextNodeIndex, final boolean calculateOnBothSides) {
        LNode[] freeLayer = currentGraph[freeLayerIndex];
        LNode[] fixedLayer = currentGraph[fixedLayerIndex];
        if (crossingMatrix == null) {
            crossingMatrix = new int[freeLayer.length][freeLayer.length];
        }

        if (calculateOnBothSides) {
            boolean isFreeLayerFirstLayer = freeLayerIndex == 0;
            boolean isFreeLayerLastLayer = freeLayerIndex == currentGraph.length - 1;
            if (isFreeLayerFirstLayer) {
                calculateCrossingMatrix(freeLayer, fixedLayer, false, freeLayerIndex);
            } else if (isFreeLayerLastLayer) {
                calculateCrossingMatrix(freeLayer, fixedLayer, true, freeLayerIndex);
            } else {
                calculateCrossingMatrix(freeLayer, fixedLayer, false, freeLayerIndex);
                calculateCrossingMatrix(freeLayer, fixedLayer, true, freeLayerIndex);
            }
        } else {
            boolean isFixedLayerEastOfFreeLayer = fixedLayerIndex < freeLayerIndex;
            calculateCrossingMatrix(freeLayer, fixedLayer, isFixedLayerEastOfFreeLayer,
                    freeLayerIndex);
        }

        int[][] crossingMatrixReturn = crossingMatrix;
        boolean lowerNodeIsLastInLayer = nextNodeIndex == freeLayer.length - 1;
        if (lowerNodeIsLastInLayer) {
            crossingMatrix = null;
        }

        boolean shouldSwitch =
                crossingMatrixReturn[currentNodeIndex][nextNodeIndex] > crossingMatrixReturn[nextNodeIndex][currentNodeIndex];
        return shouldSwitch;
    }

    private void calculateCrossingMatrix(final LNode[] freeLayer, final LNode[] fixedLayer,
            final boolean isFixedEastOfFree, final int freeLayerIndex) {
        // amountOfCrossings = 0; TODOALAN
        int matrixSize = freeLayer.length;
        int[] nodeDegrees =
                isFixedEastOfFree ? super.getWestNodeDegrees()[freeLayerIndex] : super
                        .getEastNodeDegrees()[freeLayerIndex];
        for (int i = 0; i < matrixSize; i++) {
            for (int j = i + 1; j < matrixSize; j++) {
                TwoNodeCrossingCounter crossCounter =
                        new TwoNodeCrossingCounter(freeLayer[i], freeLayer[j], isFixedEastOfFree,
                                nodeDegrees, super.getNodePositions()[freeLayerIndex],
                                super.getPortIndices());
                crossCounter.calculateCrossingNumber();
                crossingMatrix[i][j] += crossCounter.getCrossingsForOrderUpperLower();
                crossingMatrix[j][i] += crossCounter.getCrossingsForOrderLowerUpper();
                // amountOfCrossings += crossingMatrix[i][j]; TODOALAN
            }
        }
    }

    /**
     * {@inheritDoc} // TODOALAN uncool.
     */
    @Override
    protected int getAmountOfCrossings(final LNode[][] currentOrder, final LGraph layeredGraph) {
        CrossingCounter allCrossingsCounter = new CrossingCounter(layeredGraph);
        int result = allCrossingsCounter.countAllCrossingsInGraph(currentOrder);
        return result;
    }
}
