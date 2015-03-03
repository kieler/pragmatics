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
import de.cau.cs.kieler.klay.layered.properties.GreedySwitchType;

/**
 * TODO-alan.
 * 
 * @author alan
 *
 */
class SwitchDeciderFactory {

    private final GreedySwitchType greedyType;

    public SwitchDeciderFactory(final GreedySwitchType greedyType) {
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
        case TWO_SIDED_ON_DEMAND_CROSSING_MATRIX:
            return new OnDemandCrossingMatrixTwoSidedSwitchDecider(freeLayerIndex, currentNodeOrder);
        case OFF:
            throw new UnsupportedOperationException("You are trying to make a SwitchDecider with "
                    + this.getClass() + " although Greedy Switching is turned off");
        }
        throw new UnsupportedOperationException("Unsupported greedy switching type");
    }

}
