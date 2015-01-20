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
package de.cau.cs.kieler.klay.layered.intermediate;

import de.cau.cs.kieler.klay.layered.graph.LNode;

/**
 * See {@link SwitchDecider} for details. TODO-alan
 * 
 * @author alan
 */
public class CounterOneSidedSwitchDecider extends CounterSwitchDecider {
    private final SweepDirection direction;

    /**
     * Constructs CounterOneSidedSwitchDecider. TODO-alan
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
            final SweepDirection direction) throws SwitchDeciderException {
        super(freeLayerIndex, graph);
        this.direction = direction;
    }

    @Override
    protected int calculateCrossings() {
        int crossings = 0;
        switch (direction) {
        case FORWARD:
            if (freeLayerIsNotFirstLayer()) {
                LNode[] fixedLayer = getLayerForIndex(getFreeLayerIndex() - 1);
                crossings =
                        getCrossingCounter().countCrossingsBetweenLayersInOrder(fixedLayer,
                                super.getFreeLayer());
            }
            break;
        case BACKWARD:
            if (freeLayerIsNotLastLayer()) {
                LNode[] fixedLayer = getLayerForIndex(getFreeLayerIndex() + 1);
                crossings =
                        getCrossingCounter().countCrossingsBetweenLayersInOrder(
                                super.getFreeLayer(), fixedLayer);
            }
        }
        crossings += getCrossingCounter().countInLayerEdgeCrossingsWithOrder(super.getFreeLayer());
        crossings += getCrossingCounter().countNorthSouthPortCrossings(getFreeLayer());
        return crossings;
    }

    private boolean freeLayerIsNotLastLayer() {
        return super.getFreeLayerIndex() != super.getGraph().length - 1;
    }

    private boolean freeLayerIsNotFirstLayer() {
        return getFreeLayerIndex() != 0;
    }

}
