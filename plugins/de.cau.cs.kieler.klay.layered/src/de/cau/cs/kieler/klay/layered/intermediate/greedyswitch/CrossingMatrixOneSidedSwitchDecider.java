package de.cau.cs.kieler.klay.layered.intermediate.greedyswitch;

import de.cau.cs.kieler.klay.layered.graph.LNode;

class CrossingMatrixOneSidedSwitchDecider extends CompleteCrossingMatrixSwitchDecider {

    private final CrossingCountSide direction;

    CrossingMatrixOneSidedSwitchDecider(final int freeLayerIndex, final LNode[][] graph,
            final CrossingCountSide direction) {
        super(freeLayerIndex, graph);
        this.direction = direction;

        calculateCrossingMatrix();
    }

    private void calculateCrossingMatrix() {
        switch (direction) {
        case WEST:
            if (freeLayerIsNotFirstLayer()) {
                super.calculateOneSidedCrossingMatrix(direction);
            }
            break;
        case EAST:
            if (freeLayerIsNotLastLayer()) {
                super.calculateOneSidedCrossingMatrix(direction);
            }
        }
    }

    @Override
    int getCrossingMatrixEntry(final int upperNodeIndex, final int lowerNodeIndex) {
        return crossingMatrix[upperNodeIndex][lowerNodeIndex];
    }

}
