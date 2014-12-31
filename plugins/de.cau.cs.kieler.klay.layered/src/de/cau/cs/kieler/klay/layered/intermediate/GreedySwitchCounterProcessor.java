/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.intermediate;

import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;

/**
 * This Processor uses greedy switch and recounts number of crossings in layer for every feasable
 * switch.
 *
 * @author alan
 *
 */
public class GreedySwitchCounterProcessor extends AbstractGreedySwitchProcessor {

    public GreedySwitchCounterProcessor(final boolean considerAllCrossings) {
        super(considerAllCrossings);
    }

    private int amountOfCrossings = 0;

    /**
     * TODOALAN DEPRECATED REPAIR AND TEST! {@inheritDoc}
     */
    @Override
    protected boolean switchReducesCrossings(final LNode[][] currentGraph,
            final int freeLayerIndex, final int fixedLayerIndex, final int currentNodeIndex,
            final int nextNodeIndex, final boolean calculateOnBothSides) {
        LNode[] freeLayer = currentGraph[freeLayerIndex];
        LNode[] fixedLayer = currentGraph[fixedLayerIndex];
        CrossingCounter crossingCounter = new CrossingCounter(freeLayer[0].getGraph());

        amountOfCrossings =
                calculateCrossings(currentGraph, freeLayerIndex, fixedLayerIndex,
                        calculateOnBothSides, freeLayer, fixedLayer, crossingCounter);

        if (amountOfCrossings == 0) {
            return false;
        }

        exchangeNodes(currentNodeIndex, nextNodeIndex, freeLayer, freeLayerIndex);

        int newAmountOfCrossings =
                calculateCrossings(currentGraph, freeLayerIndex, fixedLayerIndex,
                        calculateOnBothSides, freeLayer, fixedLayer, crossingCounter);
        exchangeNodes(nextNodeIndex, currentNodeIndex, freeLayer, freeLayerIndex);

        boolean switchReducesCrossings = newAmountOfCrossings < amountOfCrossings;
        if (switchReducesCrossings) {
            amountOfCrossings = newAmountOfCrossings;
        }
        return switchReducesCrossings;
    }

    private int calculateCrossings(final LNode[][] currentGraph, final int freeLayerIndex,
            final int fixedLayerIndex, final boolean calculateOnBothSides, final LNode[] freeLayer,
            final LNode[] fixedLayer, final CrossingCounter crossingCounter) {
        int crossings = 0;
        if (calculateOnBothSides) {
            if (freeLayerIndex == 0 && currentGraph.length > 1) {
                LNode[] fixedLayerEastOfFreeLayer = currentGraph[freeLayerIndex + 1];
                crossings =
                        crossingCounter.countCrossingsBetweenLayers(fixedLayerEastOfFreeLayer,
                                freeLayer, false);
            } else if (freeLayerIndex == currentGraph.length - 1) {
                crossings =
                        crossingCounter.countCrossingsBetweenLayers(fixedLayer, freeLayer, true);
            } else {
                LNode[] fixedLayerEastOfFreeLayer = currentGraph[freeLayerIndex + 1];
                crossings =
                        crossingCounter.countCrossingsBetweenLayers(fixedLayer, freeLayer, true);
                crossings +=
                        crossingCounter.countCrossingsBetweenLayers(fixedLayerEastOfFreeLayer,
                                freeLayer, false);
            }
        } else {
            boolean fixedLayerIsEastOfFreeLayer = fixedLayerIndex < freeLayerIndex;
            crossings =
                    crossingCounter.countCrossingsBetweenLayers(fixedLayer, freeLayer,
                            fixedLayerIsEastOfFreeLayer);
        }
        return crossings;
    }

    /**
     * {@inheritDoc} // TODOALAN think about this. THIS DOESNT WORK!!!
     */
    @Override
    protected int getAmountOfCrossings(final LNode[][] currentOrder, final LGraph layeredGraph) {
        return amountOfCrossings;
    }
}
