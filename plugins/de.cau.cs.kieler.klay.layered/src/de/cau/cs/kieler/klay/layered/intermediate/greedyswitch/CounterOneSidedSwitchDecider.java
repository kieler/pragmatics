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
 * Works just like {@link SwitchDecider}, but only counts the crossing on one side of the free
 * layer.
 * 
 * @author alan
 */
class CounterOneSidedSwitchDecider extends CounterSwitchDecider {
    private final CrossingCountSide direction;

    /**
     * Constructs CounterOneSidedSwitchDecider.
     * 
     * @param freeLayerIndex
     *            the free layer index.
     * @param graph
     *            the graph as LNode[][]
     * @param direction
     *            the direction we are sweeping in.
     * @throws SwitchDeciderException
     *             on faulty input
     */
    public CounterOneSidedSwitchDecider(final int freeLayerIndex, final LNode[][] graph,
            final CrossingCountSide direction) {
        super(freeLayerIndex, graph);
        this.direction = direction;
    }

    @Override
    protected int calculateCrossings() {
        int crossings = 0;
        switch (direction) {
        case WEST:
            if (freeLayerIsNotFirstLayer()) {
                LNode[] fixedLayer = getLayerForIndex(getFreeLayerIndex() - 1);
                crossings = getBetweenLayerCounter().countCrossings(fixedLayer, getFreeLayer());
            }
            break;
        case EAST:
            if (freeLayerIsNotLastLayer()) {
                LNode[] fixedLayer = getLayerForIndex(getFreeLayerIndex() + 1);
                crossings = getBetweenLayerCounter().countCrossings(getFreeLayer(), fixedLayer);
            }
        }
        crossings += getInLayerCounterFor(getFreeLayer()).countCrossings();
        crossings += getNorthSoutPortCounterFor(getFreeLayer()).countCrossings();
        return crossings;
    }

}
