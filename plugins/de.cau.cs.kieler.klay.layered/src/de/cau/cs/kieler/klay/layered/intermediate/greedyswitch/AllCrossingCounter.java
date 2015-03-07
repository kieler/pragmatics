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
 * Counts the amount of crossings between two given layers or all layers in a graph. Counts
 * crossings between layers, worst-case crossings caused by in-layer edges and crossings caused by
 * north-south ports.
 *
 * @author alan
 *
 */
public class AllCrossingCounter {

    private final LNode[][] layeredGraph;
    private int portCount;
    private InLayerEdgeAllCrossingCounter inLayerEdgeCrossingsCounter;
    private InBetweenLayerEdgeAllCrossingCounter inbetweenLayerCounter;
    private NorthSouthPortAllCrossingCounter northSouthPortCrossingCounter;

    /**
     * Constructs and initializes a cross counter. Initialization iterates through all ports.
     * 
     * @param layeredGraph
     *            The layered graph
     */
    public AllCrossingCounter(final LNode[][] layeredGraph) {
        this.layeredGraph = layeredGraph;
    }

    /**
     * Counts all crossings in a graph.
     * 
     * @return the number of crossings for the node order passed to the constructor.
     */
    public int countAllCrossingsInGraph() {
        return countAllCrossingsInGraphWithOrder(layeredGraph);
    }

    /**
     * Counts all crossings in a graph in the originalOrder.
     *
     * @param currentOrder
     *            The current order of the nodes.
     * @return the amount of crossings
     */
    public int countAllCrossingsInGraphWithOrder(final LNode[][] currentOrder) {
        int totalCrossings = 0;
        for (int layerIndex = 0; layerIndex < currentOrder.length; layerIndex++) {
            LNode[] easternLayer = currentOrder[layerIndex];
            if (layerIndex < currentOrder.length - 1) {
                LNode[] westernLayer = currentOrder[layerIndex + 1];
                totalCrossings += countBetweenLayerCrossingsInOrder(easternLayer, westernLayer);
            }
            totalCrossings += countNorthSouthPortCrossings(easternLayer);
            totalCrossings += countInLayerEdgeCrossingsWithOrder(easternLayer);
        }
        return totalCrossings;
    }

    private int countBetweenLayerCrossingsInOrder(final LNode[] easternLayer,
            final LNode[] westernLayer) {
        if (isALayerEmpty(easternLayer, westernLayer)) {
            return 0;
        }
        inbetweenLayerCounter = new InBetweenLayerEdgeAllCrossingCounter(layeredGraph);
        return inbetweenLayerCounter.countCrossings(easternLayer, westernLayer);
    }

    private boolean isALayerEmpty(final LNode[] easternLayer, final LNode[] westernLayer) {
        return easternLayer == null || easternLayer.length == 0 || westernLayer == null
                || westernLayer.length == 0;
    }

    private int countInLayerEdgeCrossingsWithOrder(final LNode[] layer) {
        inLayerEdgeCrossingsCounter = new InLayerEdgeAllCrossingCounter(layer);
        return inLayerEdgeCrossingsCounter.countCrossings();
    }

    private int countNorthSouthPortCrossings(final LNode[] layer) {
        northSouthPortCrossingCounter = new NorthSouthPortAllCrossingCounter(layer);
        return northSouthPortCrossingCounter.countCrossings();
    }
}
