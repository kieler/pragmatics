package de.cau.cs.kieler.klay.layered.intermediate.greedyswitch;

import de.cau.cs.kieler.klay.layered.graph.LNode;

abstract class OnDemandCrossingMatrixSwitchDecider extends CrossingMatrixSwitchDecider {
    private final boolean[][] isCrossingMatrixFilled;
    private final int[][] crossingMatrix;

    public OnDemandCrossingMatrixSwitchDecider(final int freeLayerIndex, final LNode[][] graph) {
        super(freeLayerIndex, graph);
        isCrossingMatrixFilled =
                new boolean[super.getFreeLayer().length][super.getFreeLayer().length];
        crossingMatrix = new int[super.getFreeLayer().length][super.getFreeLayer().length];
    }

    @Override
    int getCrossingMatrixEntry(final LNode upperNode, final LNode lowerNode) {
        if (!isCrossingMatrixFilled[upperNode.id][lowerNode.id]) {
            fillCrossingMatrix(upperNode, lowerNode);
            isCrossingMatrixFilled[upperNode.id][lowerNode.id] = true;
        }
        return crossingMatrix[upperNode.id][lowerNode.id];
    }

    abstract void fillCrossingMatrix(LNode upperNode, LNode lowerNode);

    protected void getCrossingMatrixEntriesFromCounter(final LNode upperNode, final LNode lowerNode) {
        crossingMatrix[upperNode.id][lowerNode.id] +=
                super.getTwoLayerCrossCounter().getUpperLowerCrossings();
        crossingMatrix[lowerNode.id][upperNode.id] +=
                super.getTwoLayerCrossCounter().getLowerUpperCrossings();
    }

}
