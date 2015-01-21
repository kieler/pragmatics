package de.cau.cs.kieler.klay.layered.intermediate.greedyswitch;

import de.cau.cs.kieler.klay.layered.graph.LNode;

class CrossingMatrixOneSidedSwitchDecider extends CrossingMatrixSwitchDecider {

    private final SweepDirection direction;

    CrossingMatrixOneSidedSwitchDecider(final int freeLayerIndex, final LNode[][] graph,
            final SweepDirection direction) {
        super(freeLayerIndex, graph);
        this.direction = direction;
    }

    @Override
    void calculateCrossingMatrixEntries(final int upperNodeIndex, final int lowerNodeIndex) {
        switch (direction) {
        case FORWARD:
            if (freeLayerIsNotFirstLayer()) {
                super.addWesternCrossingsToMatrix();
            }
            break;
        case BACKWARD:
            if (freeLayerIsNotLastLayer()) {
                super.addEasternCrossingsToMatrix();
            }
        }
    }

}
