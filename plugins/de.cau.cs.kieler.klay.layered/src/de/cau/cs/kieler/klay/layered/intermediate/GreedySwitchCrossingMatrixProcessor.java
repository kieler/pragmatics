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

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.p3order.NodeGroup;
import de.cau.cs.kieler.klay.layered.properties.InternalProperties;

/**
 * This processor attempts to improve crossing number by using a simple greedy switch heuristic.
 * TODOAlan: Extend, Dependencies on In-Layer Constraint Processor?
 * 
 * @author alan
 * 
 */
public class GreedySwitchCrossingMatrixProcessor extends AbstractGreedySwitchProcessor implements
        ILayoutProcessor {

    @Override
    protected void switchInLayer(final boolean forward, final int layerIndex) {
        NodeGroup[] fixedLayer = getCurSweep()[layerIndex];
        int freeLayerIndex = forward ? layerIndex + 1 : layerIndex - 1;
        NodeGroup[] freeLayer = getCurSweep()[freeLayerIndex];
        int[][] crossingMatrix = calculateCrossingMatrix(freeLayer, fixedLayer, forward);
        switchInLayerWithCrossingMatrix(crossingMatrix, freeLayerIndex);
    }

    private int[][] calculateCrossingMatrix(final NodeGroup[] freeLayer,
            final NodeGroup[] fixedLayer, final boolean forwardSweep) {
        // set all ids to indices first
        setIds(freeLayer);
        setIds(fixedLayer);

        int matrixSize = freeLayer.length;
        int[][] crossingMatrix = new int[matrixSize][matrixSize];
        for (int i = 0; i < matrixSize; i++) {
            for (int j = i + 1; j < matrixSize; j++) {

                List<LEdge> iEdges = getEdges(forwardSweep, freeLayer, i);
                List<LEdge> jEdges = getEdges(forwardSweep, freeLayer, j);

                if (iEdges.isEmpty() || jEdges.isEmpty()) {
                    continue;
                }
                IncidentEdgeCrossCounter crossCounter = new IncidentEdgeCrossCounter(iEdges, jEdges, forwardSweep);
                crossCounter.calculateCrossingNumber();
                crossingMatrix[i][j] = crossCounter.getCrossingsForOrderIJ();
                crossingMatrix[j][i] = crossCounter.getCrossingsForOrderJI();
            }
        }
        return crossingMatrix;
    }
    
    private void setIds(final NodeGroup[] freeLayer) {
        int id = 0;
        for (NodeGroup nodeGroup : freeLayer) {
            nodeGroup.getNode().id = id;
            id++;

            int portId = 0;
            for (LPort lPort : nodeGroup.getNode().getPorts()) {
                lPort.id = portId;
                portId++;
            }
        }
    }

    private List<LEdge> getEdges(final boolean forwardSweep, final NodeGroup[] freeLayer,
            final int index) {
        List<LPort> ports = freeLayer[index].getNode().getPorts();
        List<LEdge> edges = new LinkedList<LEdge>();
        ListIterator<LPort> portIterator =
                forwardSweep ? ports.listIterator(ports.size()) : ports.listIterator();
        while (forwardSweep ? portIterator.hasPrevious() : portIterator.hasNext()) {
            LPort lPort = forwardSweep ? portIterator.previous() : portIterator.next();
            if (forwardSweep) {
                edges.addAll(lPort.getIncomingEdges());
            } else {
                edges.addAll(lPort.getOutgoingEdges());
            }
        }
        return edges;
    }

    private boolean switchInLayerWithCrossingMatrix(final int[][] crossingMatrix,
            final int freeLayerIndex) {
        NodeGroup[] freeLayer = getCurSweep()[freeLayerIndex];

        boolean crossingNumberHasImproved = true;
        boolean stop = false;
        while (!stop) {
            stop = true;
            for (int i = 0; i < freeLayer.length - 1; i++) {

                LNode currentNode = freeLayer[i].getNode();
                LNode nextNode = freeLayer[i + 1].getNode();

                List<LNode> constraints =
                        currentNode.getProperty(InternalProperties.IN_LAYER_SUCCESSOR_CONSTRAINTS);
                boolean noSuccessorConstraint =
                        constraints == null || constraints.size() == 0
                                || !constraints.get(0).equals(nextNode);
                boolean switchReducesCrossings =
                        crossingMatrix[currentNode.id][nextNode.id] > crossingMatrix[nextNode.id][currentNode.id];

                if (noSuccessorConstraint && switchReducesCrossings) {
                    exchangeNodes(i, i + 1, freeLayer);
                    crossingNumberHasImproved = true;
                    stop = false;
                }
            }
        }

        return crossingNumberHasImproved;
    }
}
