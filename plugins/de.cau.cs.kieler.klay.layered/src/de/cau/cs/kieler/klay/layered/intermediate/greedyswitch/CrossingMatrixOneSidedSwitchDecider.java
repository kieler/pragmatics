package de.cau.cs.kieler.klay.layered.intermediate.greedyswitch;

import de.cau.cs.kieler.klay.layered.graph.LNode;

class CrossingMatrixOneSidedSwitchDecider extends SwitchDecider {

    private int[][] crossingMatrix;
    private final SweepDirection direction;
    private final TwoNodeInLayerEdgeCrossingCounter inLayerCrossingCounter;

    protected CrossingMatrixOneSidedSwitchDecider(final int freeLayerIndex, final LNode[][] graph,
            final SweepDirection direction) {
        super(freeLayerIndex, graph);
        initialize(graph);
        this.direction = direction;
        LNode[] freeLayer = super.getLayerForIndex(super.getFreeLayerIndex());
        inLayerCrossingCounter = new TwoNodeInLayerEdgeCrossingCounter(freeLayer);
    }

    private void initialize(final LNode[][] graph) {
        for (int i = 0; i < graph.length; i++) {
            int id = 0;
            for (int j = 0; j < graph[i].length; j++) {
                graph[i][j].id = id++;
            }
        }
    }

    /**
     * TODO-alan change to node and node {@inheritDoc}
     */
    @Override
    public boolean doesSwitchReduceCrossings(final int upperNodeIndex, final int lowerNodeIndex) {
        if (crossingMatrix == null) {
            crossingMatrix = new int[getFreeLayerLength()][getFreeLayerLength()];
        }

        switch (direction) {
        case FORWARD:
            if (freeLayerIsNotFirstLayer()) {
                int fixedLayerIndex = getFreeLayerIndex() - 1;
                calculateCrossingsFromOneSide(getFreeLayerIndex(), fixedLayerIndex);
            }
            break;
        case BACKWARD:
            if (freeLayerIsNotLastLayer()) {
                int fixedLayerIndex = getFreeLayerIndex() + 1;
                calculateCrossingsFromOneSide(getFreeLayerIndex(), fixedLayerIndex);
            }
        }

        inLayerCrossingCounter.countCrossings(upperNodeIndex, lowerNodeIndex);
        int upperLowerCrossings =
                crossingMatrix[upperNodeIndex][lowerNodeIndex]
                        + inLayerCrossingCounter.getUpperLowerCrossings();
        int lowerUpperCrossings =
                crossingMatrix[lowerNodeIndex][upperNodeIndex]
                        + inLayerCrossingCounter.getLowerUpperCrossings();

        return upperLowerCrossings > lowerUpperCrossings;
    }

    private boolean freeLayerIsNotFirstLayer() {
        return getFreeLayerIndex() != 0;
    }

    private boolean freeLayerIsNotLastLayer() {
        return super.getFreeLayerIndex() != super.getGraph().length - 1;
    }

    private int getFreeLayerLength() {
        return super.getGraph()[getFreeLayerIndex()].length;
    }

    private void calculateCrossingsFromOneSide(final int freeLayerIndex, final int fixedLayerIndex) {
        boolean isFixedLayerEastOfFreeLayer = fixedLayerIndex < freeLayerIndex;
        LNode[] freeLayer = getLayerForIndex(getFreeLayerIndex());
        calculateCrossingMatrix(freeLayer, isFixedLayerEastOfFreeLayer, freeLayerIndex);
    }

    private void calculateCrossingMatrix(final LNode[] freeLayer, final boolean isFixedEastOfFree,
            final int freeLayerIndex) {
        int matrixSize = freeLayer.length;
        for (int i = 0; i < matrixSize; i++) {
            for (int j = i + 1; j < matrixSize; j++) {
                TwoNodeTwoLayerCrossingCounter crossCounter =
                        new TwoNodeTwoLayerCrossingCounter(super.getGraph(),
                                super.getFreeLayerIndex());
                LNode upperNode = freeLayer[i];
                LNode lowerNode = freeLayer[j];
                if (isFixedEastOfFree) {
                    crossCounter.countWesternEdgeCrossings(upperNode, lowerNode);
                } else {
                    crossCounter.countEasternEdgeCrossings(upperNode, lowerNode);
                }
                crossingMatrix[i][j] += crossCounter.getUpperLowerCrossings();
                crossingMatrix[j][i] += crossCounter.getLowerUpperCrossings();
            }
        }
    }
}
