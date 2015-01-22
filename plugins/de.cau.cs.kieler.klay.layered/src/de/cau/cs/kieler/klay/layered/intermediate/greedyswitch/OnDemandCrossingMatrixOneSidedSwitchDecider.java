/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.intermediate.greedyswitch;

import de.cau.cs.kieler.klay.layered.graph.LNode;

/**
 * @author alan
 *
 */
class OnDemandCrossingMatrixOneSidedSwitchDecider extends OnDemandCrossingMatrixSwitchDecider {

    private final CrossingCountSide direction;

    /**
     * @param freeLayerIndex
     * @param graph
     */
    public OnDemandCrossingMatrixOneSidedSwitchDecider(final int freeLayerIndex,
            final LNode[][] graph, final CrossingCountSide direction) {
        super(freeLayerIndex, graph);
        this.direction = direction;
    }

    @Override
    void fillCrossingMatrix(final LNode upperNode, final LNode lowerNode) {
        switch (direction) {
        case EAST:
            super.getTwoLayerCrossCounter().countEasternEdgeCrossings(upperNode, lowerNode);
            break;
        case WEST:
            super.getTwoLayerCrossCounter().countWesternEdgeCrossings(upperNode, lowerNode);
        }
        super.getCrossingMatrix()[upperNode.id][lowerNode.id] +=
                super.getTwoLayerCrossCounter().getUpperLowerCrossings();
        super.getCrossingMatrix()[lowerNode.id][upperNode.id] +=
                super.getTwoLayerCrossCounter().getLowerUpperCrossings();
    }

}
