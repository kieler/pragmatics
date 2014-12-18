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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.p3order.NodeGroup;

/**
 * @author alan
 *
 */
public class GreedySwitchOnDemandCrossingMatrixProcessor extends AbstractGreedySwitchProcessor {

    private boolean[][] isCrossingMatrixEntryFilled;
    private int[][] crossingMatrix;
    private HashMap<LPort, Integer> portIndices = new HashMap<LPort, Integer>();
    private int inLayerCrossingsIJ, inLayerCrossingsJI;
    private boolean isForwardSweep;
    private int[] nodeDegrees; // amount of ports per port.id

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
            final boolean isFirstRun) {
        this.isForwardSweep = forward;
        int freeLayerSize = freeLayer.length;
        LNode currNode = freeLayer[currentNodeIndex].getNode();
        LNode nextNode = freeLayer[nextNodeIndex].getNode();

        if (isFirstRun) {
            crossingMatrix = new int[freeLayerSize][freeLayerSize];
            isCrossingMatrixEntryFilled = new boolean[freeLayerSize][freeLayerSize];
        }
        if (!isCrossingMatrixEntryFilled[currNode.id][nextNode.id]) {
            // northSouthPortAdjust(currNode, nextNode);
            TreeMap<Integer, LEdge> iEdges = getEdges(forward, currNode);
            TreeMap<Integer, LEdge> jEdges = getEdges(forward, nextNode);

            System.out.println("\nIEdges = " + iEdges);
            System.out.println("JEdges = " + jEdges);
            IncidentEdgeCrossCounter incidentEdgeCrossCounter =
                    new IncidentEdgeCrossCounter(iEdges, jEdges, forward);
            incidentEdgeCrossCounter.calculateCrossingNumber();
            crossingMatrix[currNode.id][nextNode.id] =
                    incidentEdgeCrossCounter.getCrossingsForOrderIJ();
            crossingMatrix[nextNode.id][currNode.id] =
                    incidentEdgeCrossCounter.getCrossingsForOrderJI();
            isCrossingMatrixEntryFilled[currNode.id][nextNode.id] = true;

        }

        countInLayerEdgeCrossings(isFirstRun, freeLayer, forward, currNode, nextNode);

        crossingMatrix[currNode.id][nextNode.id] += inLayerCrossingsIJ;
        crossingMatrix[nextNode.id][currNode.id] += inLayerCrossingsJI;

        System.out.println("Crossing Matrix freeLayer " + currNode.getLayer().getIndex() + " i = "
                + currNode.id + ", j = " + nextNode.id);
        System.out.println("Direction = " + (forward ? "forward" : "backward"));
        for (int i = 0; i < crossingMatrix.length; i++) {
            System.out.println(Arrays.toString(crossingMatrix[i]));
        }
        return crossingMatrix[currNode.id][nextNode.id] > crossingMatrix[nextNode.id][currNode.id];
    }

    private void setPortIndices(NodeGroup[] layer, boolean isForwardSweep) {
        nodeDegrees = new int[layer.length];
        int portIndex = 0;
        PortSide portSide = isForwardSweep ? PortSide.WEST : PortSide.EAST;
        for (int nodeIndex = 0; nodeIndex < layer.length; nodeIndex++) {
            LNode node = layer[nodeIndex].getNode();
            int nodeDegree = 0;
            for (LPort port : node.getPorts(portSide)) {
                if (port.getDegree() > 0) {
                    portIndex += port.getDegree();
                    nodeDegree += port.getDegree();
                    portIndices.put(port, portIndex);
                }
            }

            nodeDegrees[node.id] = nodeDegree; // TEST TodoAlan
        }
    }

    /**
     * TODOALAN
     * 
     * @param isFirstRun
     * @param freeLayer
     * @param forward
     * @param currNode
     * @param nextNode
     */
    private void countInLayerEdgeCrossings(final boolean isFirstRun, final NodeGroup[] freeLayer,
            final boolean forward, final LNode currNode, final LNode nextNode) {
        inLayerCrossingsIJ = 0;
        inLayerCrossingsJI = 0;
        LinkedList<LEdge> currNodeInLayerEdges = new LinkedList<LEdge>();
        PortSide portSide = forward ? PortSide.WEST : PortSide.EAST;
        boolean currNodeHasInLayerEdges =
                collectInLayerEdges(currNode, currNodeInLayerEdges, portSide); // CHECK!

        if (isFirstRun) {
            setPortIndices(freeLayer, forward);
        }

        if (currNodeHasInLayerEdges) {
            for (LEdge edge : currNodeInLayerEdges) {
                boolean currNodeIsSource = edge.getSource().getNode() == currNode;
                LPort thisPort = currNodeIsSource ? edge.getSource() : edge.getTarget();
                LPort otherPort = currNodeIsSource ? edge.getTarget() : edge.getSource();

                if (portIndices.get(otherPort) > portIndices.get(thisPort)) {
                    assert portIndices.containsKey(thisPort) && portIndices.containsKey(otherPort);
                    int thisPortIndex = portIndices.get(thisPort);
                    int otherPortIndex = portIndices.get(otherPort);
                    inLayerCrossingsIJ +=
                            Math.abs(portIndices.get(thisPort) - portIndices.get(otherPort)) - 1;
                    updatePortIndices(currNode, nextNode); // switch indices
                    thisPortIndex = portIndices.get(thisPort);
                    otherPortIndex = portIndices.get(otherPort);
                    inLayerCrossingsJI +=
                            Math.abs(portIndices.get(thisPort) - portIndices.get(otherPort)) - 1;
                    updatePortIndices(nextNode, currNode); // switch back
                    assert inLayerCrossingsIJ >= 0 && inLayerCrossingsJI >= 0;
                }
            }
        }
    }

    /**
     * @param currNode
     * @param currNodeInLayerEdges
     * @param portSide
     * @return
     */
    private boolean collectInLayerEdges(final LNode currNode,
            final LinkedList<LEdge> currNodeInLayerEdges, final PortSide portSide) {
        boolean currNodeHasInLayerEdges = false;
        for (LPort port : currNode.getPorts(portSide)) {
            for (LEdge edge : port.getConnectedEdges()) {
                boolean inLayerIncomingEdge =
                        edge.getSource().getNode() != currNode
                                && edge.getSource().getNode().getLayer() == currNode.getLayer();
                boolean inLayerOutgoingEdge =
                        edge.getTarget().getNode() != currNode
                                && edge.getTarget().getNode().getLayer() == currNode.getLayer();
                boolean currEdgeHasInLayerEdges = inLayerIncomingEdge || inLayerOutgoingEdge;

                if (currEdgeHasInLayerEdges) {
                    currNodeInLayerEdges.add(edge);
                    currNodeHasInLayerEdges |= currEdgeHasInLayerEdges;
                }
            }
        }
        return currNodeHasInLayerEdges;
    }

    private TreeMap<Integer, LEdge> getEdges(final boolean forwardSweep, final LNode node) {
        List<LPort> ports = node.getPorts();
        TreeMap<Integer, LEdge> edges = new TreeMap<Integer, LEdge>();
        for (LPort port : ports) {
            for (LEdge edge : forwardSweep ? port.getIncomingEdges() : port.getOutgoingEdges()) {
                boolean edgeIsNotSelfLoop =
                        edge.getSource().getNode() != edge.getTarget().getNode();
                boolean edgeIsNotInLayerEdge =
                        edge.getSource().getNode().getLayer() != edge.getTarget().getNode()
                                .getLayer();
                if (edgeIsNotSelfLoop && edgeIsNotInLayerEdge) {
                    int indexInOtherLayer =
                            forwardSweep ? edge.getSource().getNode().id : edge.getTarget()
                                    .getNode().id;
                    edges.put(indexInOtherLayer, edge);
                }
            }
        }
        return edges;
    }

    /**
     * Switches nodes with index indexOne and indexTwo in layer layer.
     * 
     * @param indexOne
     *            The first nodes index
     * @param indexTwo
     *            The second nodes index
     * @param layer
     *            The layer as NodeGroup array
     */
    @Override
    protected void exchangeNodes(final int indexOne, final int indexTwo, final NodeGroup[] layer) {
        updatePortIndices(layer[indexOne].getNode(), layer[indexTwo].getNode());
        super.exchangeNodes(indexOne, indexTwo, layer);
    }

    /**
     * NodeOne has been switched with (is now below of) nodeTwo.
     * 
     * @param indexOne
     * @param indexTwo
     * @param layer
     */
    private void updatePortIndices(LNode nodeOne, LNode nodeTwo) {
        PortSide portSide = isForwardSweep ? PortSide.WEST : PortSide.EAST;

        for (LPort port : nodeOne.getPorts(portSide)) {
            if (portIndices.containsKey(port)) {
                int iCurrent = portIndices.get(port);
                portIndices.put(port, iCurrent + nodeDegrees[nodeTwo.id]);
            }
        }

        for (LPort port : nodeTwo.getPorts(portSide)) {
            if (portIndices.containsKey(port)) {
                int iCurrent = portIndices.get(port);
                portIndices.put(port, iCurrent - nodeDegrees[nodeOne.id]);
            }
        }
    }
}
