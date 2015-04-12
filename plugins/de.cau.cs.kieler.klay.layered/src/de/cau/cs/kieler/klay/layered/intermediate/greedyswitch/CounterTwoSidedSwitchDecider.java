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

/**
 * Counter SwitchDecider which takes both adjacent layers of the free layer int account. See
 * {@link CounterSwitchDecider}.
 * 
 * @author alan
 *
 */
class CounterTwoSidedSwitchDecider extends CounterSwitchDecider {

    public CounterTwoSidedSwitchDecider(final int freeLayerIndex, final LNode[][] graph) {
        super(freeLayerIndex, graph);
    }

    @Override
    protected int calculateCrossings() {
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
        int crossings = getInLayerCounterFor(getFreeLayer()).countCrossings();
        crossings += getNorthSouthPortCounterFor(getFreeLayer()).countCrossings();
        return crossings;
    }

    private int getEasternCrossings() {
        LNode[] fixedLayer = getLayerForIndex(getFreeLayerIndex() + 1);
        return getBetweenLayerCounter().countCrossings(getFreeLayer(), fixedLayer);
    }

    private int getWesternCrossings() {
        LNode[] fixedLayer = getLayerForIndex(getFreeLayerIndex() - 1);
        return getBetweenLayerCounter().countCrossings(fixedLayer, getFreeLayer());
    }

}
