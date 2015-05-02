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

import java.util.HashMap;

import com.google.common.collect.Maps;

import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.intermediate.greedyswitch.SwitchDecider.CrossingCountSide;
import de.cau.cs.kieler.klay.layered.properties.GreedySwitchType;

/**
 * This class manages the crossing matrix and fills it on demand. It needs to be reinitialized for
 * each free layer.
 * 
 * @author alan
 *
 */
class CrossingMatrixFiller {
    /** To avoid interdependency errors between classes, the .id field of nodes is not used. */
    private final HashMap<LNode, Integer> nodeIds;
    private final boolean[][] isCrossingMatrixFilled;
    private final int[][] crossingMatrix;
    private final BetweenLayerEdgeTwoNodeCrossingsCounter inBetweenLayerCrossingCounter;
    private final CrossingCountSide direction;
    private final boolean oneSided;

    /**
     * Constructs class which manages the crossing matrix.
     * 
     * @param greedyType
     */
    public CrossingMatrixFiller(final GreedySwitchType greedyType, final LNode[][] graph,
            final int freeLayerIndex, final CrossingCountSide direction) {
        this.direction = direction;
        oneSided = greedyType.isOneSided();

        LNode[] freeLayer = graph[freeLayerIndex];
        isCrossingMatrixFilled = new boolean[freeLayer.length][freeLayer.length];
        crossingMatrix = new int[freeLayer.length][freeLayer.length];

        inBetweenLayerCrossingCounter =
                new BetweenLayerEdgeTwoNodeCrossingsCounter(graph, freeLayerIndex);

        nodeIds = Maps.newHashMap();
        initializeNodePositions(freeLayer);
    }

    public int getCrossingMatrixEntry(final LNode upperNode, final LNode lowerNode) {
        if (!isCrossingMatrixFilled[idOf(upperNode)][idOf(lowerNode)]) {
            fillCrossingMatrix(upperNode, lowerNode);
            isCrossingMatrixFilled[idOf(upperNode)][idOf(lowerNode)] = true;
            isCrossingMatrixFilled[idOf(lowerNode)][idOf(upperNode)] = true;
        }
        return crossingMatrix[idOf(upperNode)][idOf(lowerNode)];
    }

    private void fillCrossingMatrix(final LNode upperNode, final LNode lowerNode) {
        if (oneSided) {
            switch (direction) {
            case EAST:
                inBetweenLayerCrossingCounter.countEasternEdgeCrossings(upperNode, lowerNode);
                break;
            case WEST:
                inBetweenLayerCrossingCounter.countWesternEdgeCrossings(upperNode, lowerNode);
            }
        } else {
            inBetweenLayerCrossingCounter.countBothSideCrossings(upperNode, lowerNode);
        }
        crossingMatrix[idOf(upperNode)][idOf(lowerNode)] =
                inBetweenLayerCrossingCounter.getUpperLowerCrossings();
        crossingMatrix[idOf(lowerNode)][idOf(upperNode)] =
                inBetweenLayerCrossingCounter.getLowerUpperCrossings();
    }

    private void initializeNodePositions(final LNode[] layer) {
        int id = 0;
        for (LNode node : layer) {
            nodeIds.put(node, id++);
        }
    }

    private int idOf(final LNode node) {
        return nodeIds.get(node);
    }

}
