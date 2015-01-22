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
abstract class CrossingMatrixSwitchDecider extends SwitchDecider {

    private final TwoNodeInLayerEdgeCrossingCounter inLayerCounter;
    private final TwoNodeTwoLayerCrossingCounter inBetweenLayerCrossingCounter;

    /**
     * @param freeLayerIndex
     * @param graph
     */
    public CrossingMatrixSwitchDecider(final int freeLayerIndex, final LNode[][] graph) {
        super(freeLayerIndex, graph);
        initializeNodeIds(graph);
        inBetweenLayerCrossingCounter = new TwoNodeTwoLayerCrossingCounter(graph, freeLayerIndex);
        LNode[] freeLayer = super.getLayerForIndex(super.getFreeLayerIndex()); // TODO-alan
        inLayerCounter = new TwoNodeInLayerEdgeCrossingCounter(freeLayer);
    }

    private void initializeNodeIds(final LNode[][] graph) {
        for (int i = 0; i < graph.length; i++) {
            int id = 0;
            for (int j = 0; j < graph[i].length; j++) {
                graph[i][j].id = id++;
            }
        }
    }

    TwoNodeInLayerEdgeCrossingCounter getInLayerCounter() {
        return inLayerCounter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean doesSwitchReduceCrossings(final int upperNodeIndex, final int lowerNodeIndex) {

        getInLayerCounter().countCrossings(upperNodeIndex, lowerNodeIndex);
        int upperLowerCrossings =
                getCrossingMatrixEntry(upperNodeIndex, lowerNodeIndex)
                        + getInLayerCounter().getUpperLowerCrossings();
        int lowerUpperCrossings =
                getCrossingMatrixEntry(upperNodeIndex, lowerNodeIndex)
                        + getInLayerCounter().getLowerUpperCrossings();

        return upperLowerCrossings > lowerUpperCrossings;
    }

    abstract int getCrossingMatrixEntry(final int upperNodeIndex, final int lowerNodeIndex);

    TwoNodeTwoLayerCrossingCounter getTwoLayerCrossCounter() {
        return inBetweenLayerCrossingCounter;
    }

}
