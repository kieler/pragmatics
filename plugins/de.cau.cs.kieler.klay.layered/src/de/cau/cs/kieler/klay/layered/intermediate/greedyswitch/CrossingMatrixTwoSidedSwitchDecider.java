package de.cau.cs.kieler.klay.layered.intermediate.greedyswitch;

import de.cau.cs.kieler.klay.layered.graph.LNode;

class CrossingMatrixTwoSidedSwitchDecider extends CrossingMatrixSwitchDecider {

    private final int[][] crossingMatrix;

    public CrossingMatrixTwoSidedSwitchDecider(final int freeLayerIndex, final LNode[][] graph) {
        super(freeLayerIndex, graph);
        crossingMatrix = new int[super.getFreeLayer().length][super.getFreeLayer().length];
        calculateCrossingMatrixEntries();
    }

    private void calculateCrossingMatrixEntries() {
        int matrixSize = super.getFreeLayer().length;
        for (int i = 0; i < matrixSize; i++) {
            for (int j = i + 1; j < matrixSize; j++) {
                LNode upperNode = super.getFreeLayer()[i];
                LNode lowerNode = super.getFreeLayer()[j];
                getTwoLayerCrossCounter().countBothSideCrossings(upperNode, lowerNode);
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
