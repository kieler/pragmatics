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

import java.util.List;
import java.util.TreeMap;

import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.p3order.NodeGroup;

/**
 * This processor attempts to improve crossing number by using a simple greedy switch heuristic.
 * TODOAlan: Extend, Dependencies on In-Layer Constraint Processor?
 * 
 * @author alan
 * 
 */
public class GreedySwitchCrossingMatrixProcessor extends AbstractGreedySwitchProcessor implements
        ILayoutProcessor {

    private boolean crossingMatrixConstructed;
    private int[][] crossingMatrix;
    private int amountOfCrossings;

    /**
     * {@inheritDoc}
     */
    @Override
    boolean checkIfSwitchReducesCrossings(final int currentNodeIndex, final int nextNodeIndex,
            final boolean forward, final NodeGroup[] fixedLayer, final NodeGroup[] freeLayer, boolean firstRun) {
        if (!crossingMatrixConstructed) {
            crossingMatrix = calculateCrossingMatrix(freeLayer, fixedLayer, forward);
            crossingMatrixConstructed = true;
        }
        if (nextNodeIndex == freeLayer.length - 1) {
            crossingMatrixConstructed = false;
        }
        return crossingMatrix[currentNodeIndex][nextNodeIndex] > crossingMatrix[nextNodeIndex][currentNodeIndex];
    }

    private int[][] calculateCrossingMatrix(final NodeGroup[] freeLayer,
            final NodeGroup[] fixedLayer, final boolean forwardSweep) {
        amountOfCrossings = 0;
        int matrixSize = freeLayer.length;
        crossingMatrix = new int[matrixSize][matrixSize];
        for (int i = 0; i < matrixSize; i++) {
            for (int j = i + 1; j < matrixSize; j++) {

                TreeMap<Integer, LEdge> iEdges = getEdges(forwardSweep, freeLayer[i].getNode());
                TreeMap<Integer, LEdge> jEdges = getEdges(forwardSweep, freeLayer[j].getNode());

                if (iEdges.isEmpty() || jEdges.isEmpty()) {
                    continue;
                }
                IncidentEdgeCrossCounter crossCounter =
                        new IncidentEdgeCrossCounter(iEdges, jEdges, forwardSweep);
                crossCounter.calculateCrossingNumber();
                crossingMatrix[i][j] = crossCounter.getCrossingsForOrderIJ();
                crossingMatrix[j][i] = crossCounter.getCrossingsForOrderJI();
                amountOfCrossings += crossingMatrix[i][j];
            }
        }
        return crossingMatrix;
    }

    private TreeMap<Integer, LEdge> getEdges(final boolean forwardSweep, final LNode node) {
        List<LPort> ports = node.getPorts();
        TreeMap<Integer, LEdge> edges = new TreeMap<Integer, LEdge>();
        for (LPort port : ports) {
            for (LEdge edge : forwardSweep ? port.getIncomingEdges() : port.getOutgoingEdges()) {
                boolean edgeIsNotSelfLoop =
                        edge.getSource().getNode() != edge.getTarget().getNode();
                if (edgeIsNotSelfLoop) {
                    int indexInOtherLayer = forwardSweep ? edge.getSource().getNode().id : edge.getTarget().getNode().id;
                    edges.put(indexInOtherLayer, edge);
                }
            }
        }
        return edges;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    int getAmountOfCrossings(NodeGroup[][] currentOrder) {
        return amountOfCrossings;
    }
}
