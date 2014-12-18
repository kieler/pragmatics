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
import de.cau.cs.kieler.klay.layered.p3order.NodeGroup;

/**
 * This processor attempts to improve crossing number by using a simple greedy switch heuristic.
 * TODOAlan: Extend, Dependencies on In-Layer Constraint Processor?
 * 
 * @author alan
 * 
 */
public class GreedySwitchCrossingMatrixProcessor extends AbstractGreedySwitchProcessor implements
        ILayoutProcessor {

    private boolean crossingMatrixConstructed;
    private int[][] crossingMatrix;
    private int amountOfCrossings;
    private int freeLayerIndex;

    /**
     * {@inheritDoc}
     */
    @Override
    boolean checkIfSwitchReducesCrossings(final int currentNodeIndex, final int nextNodeIndex,
            final boolean forward, final NodeGroup[] fixedLayer, final NodeGroup[] freeLayer,
            boolean firstRun, int freeLayerIndex) {
        this.freeLayerIndex = freeLayerIndex;
        if (!crossingMatrixConstructed) {
            crossingMatrix = calculateCrossingMatrix(freeLayer, fixedLayer, forward);
            crossingMatrixConstructed = true;
        }
        if (nextNodeIndex == freeLayer.length - 1) {
            crossingMatrixConstructed = false;
        }
        return crossingMatrix[currentNodeIndex][nextNodeIndex] > crossingMatrix[nextNodeIndex][currentNodeIndex];
    }

    private int[][] calculateCrossingMatrix(final NodeGroup[] freeLayer,
            final NodeGroup[] fixedLayer, final boolean isForwardSweep) {
        amountOfCrossings = 0;
        int matrixSize = freeLayer.length;
        crossingMatrix = new int[matrixSize][matrixSize];
        int[] nodeDegrees =
                isForwardSweep ? super.getWestNodeDegrees()[freeLayerIndex] : super
                        .getEastNodeDegrees()[freeLayerIndex];
        for (int i = 0; i < matrixSize; i++) {
            for (int j = i + 1; j < matrixSize; j++) {
                IncidentEdgeCrossCounter crossCounter =
                        new IncidentEdgeCrossCounter(freeLayer[i].getNode(),
                                freeLayer[j].getNode(), isForwardSweep, nodeDegrees);
                crossCounter.calculateCrossingNumber();
                crossingMatrix[i][j] = crossCounter.getCrossingsForOrderIJ();
                crossingMatrix[j][i] = crossCounter.getCrossingsForOrderJI();
                amountOfCrossings += crossingMatrix[i][j];
            }
        }
        return crossingMatrix;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    int getAmountOfCrossings(NodeGroup[][] currentOrder) {
        return amountOfCrossings;
    }
}
