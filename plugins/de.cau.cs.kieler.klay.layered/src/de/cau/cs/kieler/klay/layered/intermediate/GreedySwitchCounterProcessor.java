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

import de.cau.cs.kieler.klay.layered.p3order.NodeGroup;

/**
 * This Processor uses greedy switch and recounts number of crossings in layer for every feasable
 * switch.
 * 
 * @author alan
 *
 */
public class GreedySwitchCounterProcessor extends AbstractGreedySwitchProcessor {

    private int amountOfCrossings = 0;

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean checkIfSwitchReducesCrossings(final int currentNodeIndex,
            final int nextNodeIndex, final boolean forward, final NodeGroup[] fixedLayer,
            final NodeGroup[] freeLayer, boolean firstRun, int freeLayerIndex) {
        CrossingCounter crossingCounter = new CrossingCounter(freeLayer[0].getNode().getGraph());
        amountOfCrossings =
                crossingCounter.countCrossingsBetweenLayers(fixedLayer, freeLayer, forward);
        if (amountOfCrossings == 0) {
            return false;
        }
        exchangeNodes(currentNodeIndex, nextNodeIndex, freeLayer, freeLayerIndex);
        int newAmountOfCrossings =
                crossingCounter.countCrossingsBetweenLayers(fixedLayer, freeLayer, forward);
        exchangeNodes(nextNodeIndex, currentNodeIndex, freeLayer, freeLayerIndex); // always switch back TODOALAN
        boolean switchReducesCrossings = newAmountOfCrossings < amountOfCrossings;
        if (switchReducesCrossings) {
            amountOfCrossings = newAmountOfCrossings;
        }
        return switchReducesCrossings;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    int getAmountOfCrossings(final NodeGroup[][] currentOrder) { // TODOALAN think about this.
        return amountOfCrossings;
    }
}
