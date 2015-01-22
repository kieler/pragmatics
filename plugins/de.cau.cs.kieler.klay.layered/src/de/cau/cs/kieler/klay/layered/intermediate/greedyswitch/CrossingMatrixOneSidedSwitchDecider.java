package de.cau.cs.kieler.klay.layered.intermediate.greedyswitch;

import de.cau.cs.kieler.klay.layered.graph.LNode;

class CrossingMatrixOneSidedSwitchDecider extends CompleteCrossingMatrixSwitchDecider {

    private final CrossingCountSide side;

    CrossingMatrixOneSidedSwitchDecider(final int freeLayerIndex, final LNode[][] graph,
            final CrossingCountSide side) {
        super(freeLayerIndex, graph);
        this.side = side;

        calculateCrossingMatrix();
    }

    private void calculateCrossingMatrix() {
        switch (side) {
        case WEST:
            if (freeLayerIsNotFirstLayer()) {
                super.calculateOneSidedCrossingMatrix(side);
            }
            break;
        case EAST:
            if (freeLayerIsNotLastLayer()) {
                super.calculateOneSidedCrossingMatrix(side);
            }
        }
    }
}
