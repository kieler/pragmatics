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
 * TODO-alan.
 * 
 * @author alan
 *
 */
class CrossingMatrixOneSidedSwitchDecider extends CrossingMatrixSwitchDecider {

    private final CrossingCountSide side;
    private final int[][] crossingMatrix;

    CrossingMatrixOneSidedSwitchDecider(final int freeLayerIndex, final LNode[][] graph,
            final CrossingCountSide side) {
        super(freeLayerIndex, graph);
        this.side = side;
        crossingMatrix = new int[super.getFreeLayer().length][super.getFreeLayer().length];
        calculateCrossingMatrix();
    }

    private void calculateCrossingMatrix() {
        switch (side) {
        case WEST:
            if (freeLayerIsNotFirstLayer()) {
                calculateOneSidedCrossingMatrix(side);
            }
            break;
        case EAST:
            if (freeLayerIsNotLastLayer()) {
                calculateOneSidedCrossingMatrix(side);
            }
        }
    }

    private void calculateOneSidedCrossingMatrix(final CrossingCountSide direction) {
        int matrixSize = super.getFreeLayer().length;
        for (int i = 0; i < matrixSize; i++) {
            for (int j = i + 1; j < matrixSize; j++) {
                LNode upperNode = super.getFreeLayer()[i];
                LNode lowerNode = super.getFreeLayer()[j];
                if (direction == CrossingCountSide.WEST) {
                    getTwoLayerCrossCounter().countWesternEdgeCrossings(upperNode, lowerNode);
                } else {
                    getTwoLayerCrossCounter().countEasternEdgeCrossings(upperNode, lowerNode);
                }
                crossingMatrix[i][j] += getTwoLayerCrossCounter().getUpperLowerCrossings();
                crossingMatrix[j][i] += getTwoLayerCrossCounter().getLowerUpperCrossings();
            }
        }
    }

    @Override
    int getCrossingMatrixEntry(final LNode upperNode, final LNode lowerNode) {
        return crossingMatrix[upperNode.id][lowerNode.id];
    }
}
