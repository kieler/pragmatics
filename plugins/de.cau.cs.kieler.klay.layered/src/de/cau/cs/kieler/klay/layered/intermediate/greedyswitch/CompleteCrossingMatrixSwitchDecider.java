package de.cau.cs.kieler.klay.layered.intermediate.greedyswitch;

import de.cau.cs.kieler.klay.layered.graph.LNode;

abstract class CompleteCrossingMatrixSwitchDecider extends CrossingMatrixSwitchDecider {

    protected final int[][] crossingMatrix;

    public CompleteCrossingMatrixSwitchDecider(final int freeLayerIndex, final LNode[][] graph) {
        super(freeLayerIndex, graph);
        crossingMatrix = new int[super.getFreeLayer().length][super.getFreeLayer().length];
    }

    @Override
    int getCrossingMatrixEntry(final int upperNodeIndex, final int lowerNodeIndex) {
        return crossingMatrix[upperNodeIndex][lowerNodeIndex];
    }

    void calculateOneSidedCrossingMatrix(final CrossingCountSide direction) {
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

}
