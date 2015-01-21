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

    private final int[][] crossingMatrix;
    private final TwoNodeInLayerEdgeCrossingCounter inLayerCounter;

    /**
     * @param freeLayerIndex
     * @param graph
     */
    public CrossingMatrixSwitchDecider(final int freeLayerIndex, final LNode[][] graph) {
        super(freeLayerIndex, graph);
        crossingMatrix = new int[super.getFreeLayerLength()][super.getFreeLayerLength()];
        initializeNodeIds(graph);
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

    private void calculateOneSidedCrossingMatrix(final boolean isFixedEastOfFree) {
        int matrixSize = super.getFreeLayerLength();
        for (int i = 0; i < matrixSize; i++) {
            for (int j = i + 1; j < matrixSize; j++) {
                TwoNodeTwoLayerCrossingCounter crossCounter =
                        new TwoNodeTwoLayerCrossingCounter(super.getGraph(),
                                super.getFreeLayerIndex());
                LNode upperNode = super.getFreeLayerNode(i);
                LNode lowerNode = super.getFreeLayerNode(j);
                if (isFixedEastOfFree) {
                    crossCounter.countWesternEdgeCrossings(upperNode, lowerNode);
                } else {
                    crossCounter.countEasternEdgeCrossings(upperNode, lowerNode);
                }
                crossingMatrix[i][j] += crossCounter.getUpperLowerCrossings();
                crossingMatrix[j][i] += crossCounter.getLowerUpperCrossings();
            }
        }
    }

    void addWesternCrossingsToMatrix() {
        calculateOneSidedCrossingMatrix(true);
    }

    void addEasternCrossingsToMatrix() {
        calculateOneSidedCrossingMatrix(false);
    }

    TwoNodeInLayerEdgeCrossingCounter getInLayerCounter() {
        return inLayerCounter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean doesSwitchReduceCrossings(final int upperNodeIndex, final int lowerNodeIndex) {

        calculateCrossingMatrixEntries(upperNodeIndex, lowerNodeIndex);

        getInLayerCounter().countCrossings(upperNodeIndex, lowerNodeIndex);
        int upperLowerCrossings =
                crossingMatrix[upperNodeIndex][lowerNodeIndex]
                        + getInLayerCounter().getUpperLowerCrossings();
        int lowerUpperCrossings =
                crossingMatrix[lowerNodeIndex][upperNodeIndex]
                        + getInLayerCounter().getLowerUpperCrossings();

        return upperLowerCrossings > lowerUpperCrossings;
    }

    abstract void calculateCrossingMatrixEntries(final int upperNodeIndex, final int lowerNodeIndex);
}
