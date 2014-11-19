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

import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.p3order.NodeGroup;
import de.cau.cs.kieler.klay.layered.properties.InternalProperties;

/**
 * @author alan
 *
 */
public class GreedySwitchOnDemandCrossingMatrixProcessor extends AbstractGreedySwitchProcessor {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void switchInLayer(final boolean forward, final int layerIndex) {
        int freeLayerIndex = forward ? layerIndex + 1 : layerIndex - 1;
        NodeGroup[] fixedLayer = getCurSweep()[layerIndex];
        NodeGroup[] freeLayer = getCurSweep()[freeLayerIndex];
     // set all ids to indices first
        setIds(freeLayer);
        setIds(fixedLayer);
        switchInLayerWithOnDemandCrossingMatrix(freeLayerIndex, forward);
    }

    /**
     * @param freeLayerIndex
     * @param forward
     */
    private void switchInLayerWithOnDemandCrossingMatrix(final int freeLayerIndex,
            final boolean forward) {
        NodeGroup[] freeLayer = getCurSweep()[freeLayerIndex];
        int[][] crossingMatrix = new int[freeLayer.length][freeLayer.length];
        boolean[][] isCrossingMatrixEntryFilled = new boolean[freeLayer.length][freeLayer.length];
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


                if (!isCrossingMatrixEntryFilled[currentNode.id][nextNode.id]) {
                    List<LEdge> iEdges = getEdges(forward, freeLayer, i);
                    List<LEdge> jEdges = getEdges(forward, freeLayer, i + 1);
                    IncidentEdgeCrossCounter crossCounter =
                            new IncidentEdgeCrossCounter(iEdges, jEdges, forward);
                    crossCounter.calculateCrossingNumber();
                    crossingMatrix[currentNode.id][nextNode.id] = crossCounter.getCrossingsForOrderIJ();
                    crossingMatrix[nextNode.id][currentNode.id] = crossCounter.getCrossingsForOrderJI();
                    isCrossingMatrixEntryFilled[currentNode.id][nextNode.id] = true;
                }
                boolean switchReducesCrossings =
                        crossingMatrix[currentNode.id][nextNode.id] > crossingMatrix[nextNode.id][currentNode.id];

                if (noSuccessorConstraint && switchReducesCrossings) {
                    exchangeNodes(i, i + 1, freeLayer);
                    stop = false;
                }
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

}
