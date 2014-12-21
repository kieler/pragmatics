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

import java.util.Arrays;

import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.p3order.NodeGroup;

/**
 * @author alan
 *
 */
public class GreedySwitchOnDemandCrossingMatrixProcessor extends AbstractGreedySwitchProcessor {

    private boolean[][] isCrossingMatrixEntryFilled;
    private int[][] crossingMatrix;

    /**
     * @return the crossingMatrix
     */
    int[][] getCrossingMatrix() {
        return crossingMatrix;
    }

    public int getAmountOfCrossings(final NodeGroup[][] currentOrder) {
        CrossingCounter allCrossingsCounter = new CrossingCounter(super.getLayeredGraph());
        int result = allCrossingsCounter.countAllCrossingsInGraph(currentOrder);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    boolean checkIfSwitchReducesCrossings(final int currentNodeIndex, final int nextNodeIndex,
            final boolean forward, final NodeGroup[] fixedLayer, final NodeGroup[] freeLayer,
            final boolean isFirstRun, int freeLayerIndex) {
        int freeLayerSize = freeLayer.length;
        LNode currNode = freeLayer[currentNodeIndex].getNode();
        LNode nextNode = freeLayer[nextNodeIndex].getNode();

        if (isFirstRun) {
            crossingMatrix = new int[freeLayerSize][freeLayerSize];
            isCrossingMatrixEntryFilled = new boolean[freeLayerSize][freeLayerSize];
        }
        if (!isCrossingMatrixEntryFilled[currNode.id][nextNode.id]) {
            // northSouthPortAdjust(currNode, nextNode);

            int[] nodeDegrees =
                    forward ? super.getWestNodeDegrees()[freeLayerIndex] : super
                            .getEastNodeDegrees()[freeLayerIndex];
            IncidentEdgeCrossCounter incidentEdgeCrossCounter =
                    new IncidentEdgeCrossCounter(currNode, nextNode, forward, nodeDegrees,
                            super.getNodePositions()[freeLayerIndex]);
            incidentEdgeCrossCounter.calculateCrossingNumber();

            crossingMatrix[currNode.id][nextNode.id] =
                    incidentEdgeCrossCounter.getCrossingsForOrderIJ();
            crossingMatrix[nextNode.id][currNode.id] =
                    incidentEdgeCrossCounter.getCrossingsForOrderJI();

            isCrossingMatrixEntryFilled[currNode.id][nextNode.id] = true;

        }

        System.out.println("Crossing Matrix freeLayer " + currNode.getLayer().getIndex() + " i = "
                + currNode.id + ", j = " + nextNode.id);
        System.out.println("Direction = " + (forward ? "forward" : "backward"));
        for (int i = 0; i < crossingMatrix.length; i++) {
            System.out.println(Arrays.toString(crossingMatrix[i]));
        }
        return crossingMatrix[currNode.id][nextNode.id] > crossingMatrix[nextNode.id][currNode.id];
    }

}
