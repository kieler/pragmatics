package de.cau.cs.kieler.klay.layered.intermediate.greedyswitch;

import de.cau.cs.kieler.klay.layered.graph.LNode;

class CounterTwoSidedSwitchDecider extends CounterSwitchDecider {

    public CounterTwoSidedSwitchDecider(final int freeLayerIndex, final LNode[][] graph) {
        super(freeLayerIndex, graph);
    }

    @Override
    int calculateCrossings() {
        int crossings = 0;

        if (freeLayerIsNotFirstLayer()) {
            crossings += getWesternCrossings();
        }
        if (freeLayerIsNotLastLayer()) {
            crossings += getEasternCrossings();
        }

        crossings += getInLayerCrossings();

        return crossings;
    }

    private int getInLayerCrossings() {
        int crossings =
                super.getCrossingCounter().countInLayerEdgeCrossingsWithOrder(super.getFreeLayer());
        return crossings
                + super.getCrossingCounter().countNorthSouthPortCrossings(super.getFreeLayer());
    }

    private int getEasternCrossings() {
        LNode[] fixedLayer = getLayerForIndex(getFreeLayerIndex() + 1);
        return super.getCrossingCounter().countCrossingsBetweenLayersInOrder(super.getFreeLayer(),
                fixedLayer);
    }

    private int getWesternCrossings() {
        LNode[] fixedLayer = getLayerForIndex(getFreeLayerIndex() - 1);
        return super.getCrossingCounter().countCrossingsBetweenLayersInOrder(fixedLayer,
                super.getFreeLayer());
    }

    private boolean freeLayerIsNotLastLayer() {
        return super.getFreeLayerIndex() != super.getGraph().length - 1;
    }

    private boolean freeLayerIsNotFirstLayer() {
        return getFreeLayerIndex() != 0;
    }

}
