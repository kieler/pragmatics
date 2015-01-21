package de.cau.cs.kieler.klay.layered.intermediate.greedyswitch;

import de.cau.cs.kieler.klay.layered.graph.LNode;

public class CrossingMatrixTwoSidedSwitchDecider extends CrossingMatrixSwitchDecider {

    protected CrossingMatrixTwoSidedSwitchDecider(final int freeLayerIndex, final LNode[][] graph) {
        super(freeLayerIndex, graph);
    }

    @Override
    void calculateCrossingMatrixEntries(final int upperNodeIndex, final int lowerNodeIndex) {
        boolean isFreeLayerFirstLayer = super.getFreeLayerIndex() == 0;
        boolean isFreeLayerLastLayer = super.getFreeLayerIndex() == super.getGraph().length - 1;
        if (isFreeLayerFirstLayer) {
            super.addEasternCrossingsToMatrix();
        } else if (isFreeLayerLastLayer) {
            super.addWesternCrossingsToMatrix();
        } else {
            super.addEasternCrossingsToMatrix();
            super.addWesternCrossingsToMatrix();
        }
    }

}
