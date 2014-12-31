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

import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;

/**
 * @author alan
 *
 */
public class GreedySwitchOnDemandCrossingMatrixProcessor extends AbstractGreedySwitchProcessor {

    public GreedySwitchOnDemandCrossingMatrixProcessor(final boolean considerAllCrossings) {
        super(considerAllCrossings);
    }

    private int freeLayerIndex;
    private int[][] crossingMatrix;
    private boolean[][] isCrossingMatrixFilled;

    /**
     * {@inheritDoc}
     */
    @Override
    public int getAmountOfCrossings(final LNode[][] currentOrder, final LGraph layeredGraph) {
        CrossingCounter allCrossingsCounter = new CrossingCounter(layeredGraph);
        int result = allCrossingsCounter.countAllCrossingsInGraph(currentOrder);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean switchReducesCrossings(final LNode[][] currentGraph,
            final int freeLayerIndexIn, final int fixedLayerIndex, final int upperNodeIndex,
            final int lowerNodeIndex, final boolean calculateOnBothSides) {

        freeLayerIndex = freeLayerIndexIn;
        LNode[] freeLayer = currentGraph[freeLayerIndexIn];
        LNode upperNode = freeLayer[upperNodeIndex];
        LNode lowerNode = freeLayer[lowerNodeIndex];
        boolean fixedLayerEastOfFreeLayer = fixedLayerIndex < freeLayerIndexIn;

        if (crossingMatrix == null) {
            crossingMatrix = new int[freeLayer.length][freeLayer.length];
            isCrossingMatrixFilled = new boolean[freeLayer.length][freeLayer.length];
        }

        if (!isCrossingMatrixFilled[upperNode.id][lowerNode.id]) {
            if (calculateOnBothSides) {
                boolean isFreeLayerFirst = freeLayerIndex == 0;
                boolean isFreeLayerLast = freeLayerIndex == currentGraph.length - 1;
                if (isFreeLayerFirst) {
                    fillCrossingMatrix(upperNode, lowerNode, true, isCrossingMatrixFilled);
                } else if (isFreeLayerLast) {
                    fillCrossingMatrix(upperNode, lowerNode, false, isCrossingMatrixFilled);
                } else {
                    fillCrossingMatrix(upperNode, lowerNode, true, isCrossingMatrixFilled);
                    fillCrossingMatrix(upperNode, lowerNode, false, isCrossingMatrixFilled);
                }
            } else {
                fillCrossingMatrix(upperNode, lowerNode, fixedLayerEastOfFreeLayer,
                        isCrossingMatrixFilled);
            }
        }

        int[][] resultMatrix = crossingMatrix;
        boolean reachedEndOfLayer = lowerNodeIndex == freeLayer.length - 1;
        if (reachedEndOfLayer) {
            crossingMatrix = null;
        }

        return resultMatrix[upperNode.id][lowerNode.id] > resultMatrix[lowerNode.id][upperNode.id];

    }

    private void fillCrossingMatrix(final LNode upperNode, final LNode lowerNode,
            final boolean fixedLayerEastOfFreeLayer, final boolean[][] isCrossingMatrixEntryFilled) {
        int[] nodeDegrees =
                fixedLayerEastOfFreeLayer ? super.getWestNodeDegrees()[freeLayerIndex] : super
                        .getEastNodeDegrees()[freeLayerIndex];
        TwoNodeCrossingCounter incidentEdgeCrossCounter =
                new TwoNodeCrossingCounter(upperNode, lowerNode, fixedLayerEastOfFreeLayer,
                        nodeDegrees, super.getNodePositions()[freeLayerIndex]);
        incidentEdgeCrossCounter.calculateCrossingNumber();

        crossingMatrix[upperNode.id][lowerNode.id] +=
                incidentEdgeCrossCounter.getCrossingsForOrderIJ();
        crossingMatrix[lowerNode.id][upperNode.id] +=
                incidentEdgeCrossCounter.getCrossingsForOrderJI();
        isCrossingMatrixEntryFilled[upperNode.id][lowerNode.id] = true;
        isCrossingMatrixEntryFilled[lowerNode.id][upperNode.id] = true;
    }

}
