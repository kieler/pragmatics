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
class OnDemandCrossingMatrixTwoSidedSwitchDecider extends OnDemandCrossingMatrixSwitchDecider {

    /**
     * @param freeLayerIndex
     * @param graph
     */
    public OnDemandCrossingMatrixTwoSidedSwitchDecider(final int freeLayerIndex,
            final LNode[][] graph) {
        super(freeLayerIndex, graph);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    void fillCrossingMatrix(final LNode upperNode, final LNode lowerNode) {
        super.getTwoLayerCrossCounter().countBothSideCrossings(upperNode, lowerNode);
        super.setCrossingMatrixEntriesFromCounter(upperNode, lowerNode);
    }

}
