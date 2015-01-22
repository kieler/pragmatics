/*
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
package de.cau.cs.kieler.klay.layered.intermediate.greedyswitch;

import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.intermediate.greedyswitch.SwitchDecider.CrossingCountSide;
import de.cau.cs.kieler.klay.layered.properties.GreedyType;

class SwitchDeciderFactory {

    private final GreedyType greedyType;

    public SwitchDeciderFactory(final GreedyType greedyType) {
        this.greedyType = greedyType;
    }

    /**
     * 
     * @param freeLayerIndex
     * @param currentNodeOrder
     * @param direction
     *            if applicable
     * @return
     */
    public SwitchDecider getNewSwitchDecider(final int freeLayerIndex,
            final LNode[][] currentNodeOrder, final CrossingCountSide direction) {
        switch (greedyType) {
        case ONE_SIDED_COUNTER:
            return new CounterOneSidedSwitchDecider(freeLayerIndex, currentNodeOrder, direction);
        case TWO_SIDED_COUNTER:
            return new CounterTwoSidedSwitchDecider(freeLayerIndex, currentNodeOrder);
        case ONE_SIDED_CROSSING_MATRIX:
            return new CrossingMatrixOneSidedSwitchDecider(freeLayerIndex, currentNodeOrder,
                    direction);
        case TWO_SIDED_CROSSING_MATRIX:
            return new CrossingMatrixTwoSidedSwitchDecider(freeLayerIndex, currentNodeOrder);
        case ONE_SIDED_ON_DEMAND_CROSSING_MATRIX:
            return new OnDemandCrossingMatrixOneSidedSwitchDecider(freeLayerIndex,
                    currentNodeOrder, direction);
        default:
            break;
        }
        throw new UnsupportedOperationException("Not implemented yet");
    }

}
