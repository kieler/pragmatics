package de.cau.cs.kieler.klay.layered.intermediate.greedyswitch;

import de.cau.cs.kieler.klay.layered.graph.LNode;

public class CrossingMatrixTwoSidedSwitchDecider extends CompleteCrossingMatrixSwitchDecider {

    protected CrossingMatrixTwoSidedSwitchDecider(final int freeLayerIndex, final LNode[][] graph) {
        super(freeLayerIndex, graph);
        calculateCrossingMatrixEntries();
    }

    private void calculateCrossingMatrixEntries() {
        if (super.freeLayerIsNotLastLayer()) {
            super.calculateOneSidedCrossingMatrix(CrossingCountSide.EAST);
        }
        if (super.freeLayerIsNotFirstLayer()) {
            super.calculateOneSidedCrossingMatrix(CrossingCountSide.WEST);
        }
    }
}
