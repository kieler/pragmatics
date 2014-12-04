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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.p3order.NodeGroup;
import de.cau.cs.kieler.klay.layered.properties.InternalProperties;
import de.cau.cs.kieler.klay.layered.properties.NodeType;

/**
 * @author alan
 *
 */
public class GreedySwitchOnDemandCrossingMatrixProcessor extends AbstractGreedySwitchProcessor {

    private boolean[][] isCrossingMatrixEntryFilled;
    private int[][] crossingMatrix;
    HashMap<LPort, Integer> portIndices = new HashMap<LPort, Integer>();
    private int inLayerCrossingsIJ, inLayerCrossingsJI;

    public int getAmountOfCrossings(final NodeGroup[][] currentOrder) { // Doesn't work!!! TODOALAN
        CrossingCounter allCrossingsCounter = new CrossingCounter(super.getLayeredGraph());
        int result = allCrossingsCounter.countAllCrossingsInGraph(currentOrder);
        System.out.println("Current amount of crossings in graph = " + result);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    boolean checkIfSwitchReducesCrossings(final int currentNodeIndex, final int nextNodeIndex,
            final boolean forward, final NodeGroup[] fixedLayer, final NodeGroup[] freeLayer,
            final boolean isFirstRun) {

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

//        countInLayerEdgeCrossings(isFirstRun, freeLayer, forward, currNode, nextNode);

        crossingMatrix[currNode.id][nextNode.id] += inLayerCrossingsIJ;
        crossingMatrix[nextNode.id][currNode.id] += inLayerCrossingsJI;

        // // TODOALAN delme
        // boolean decisionChangedByInLayerCrossCounter =
        // crossingMatrix[currNode.id][nextNode.id] <= crossingMatrix[nextNode.id][currNode.id]
        // && crossingsIJ > crossingsJI;
        // if (decisionChangedByInLayerCrossCounter) {
        // // breakpointdummy;
        // int i = 0;
        // }
        // // delme
        System.out.println("Crossing Matrix freeLayer " + currNode.getLayer().getIndex() + " i = "
                + currNode.id + ", j = " + nextNode.id);
        System.out.println("Direction = " + (forward ? "forward" : "backward"));
        for (int i = 0; i < crossingMatrix.length; i++) {
            System.out.println(Arrays.toString(crossingMatrix[i]));
        }
        return crossingMatrix[currNode.id][nextNode.id] > crossingMatrix[nextNode.id][currNode.id];
    }

    private void setPortIndices(NodeGroup[] layer, boolean forward) {
        int currentNumber = 0;
        PortSide portSide = forward ? PortSide.WEST : PortSide.EAST;
        // Assign numbers to ports, top-down
        for (int nodeIndex = 0; nodeIndex < layer.length; nodeIndex++) {
            LNode node = layer[nodeIndex].getNode();
            // At the moment the port switcher is not implemented after this processor
            // if (node.getProperty(LayoutOptions.PORT_CONSTRAINTS).isOrderFixed()) {
            // for (LPort port : node.getPorts(portSide)) {
            // if (port.getDegree() > 0) {
            // currentNumber += port.getDegree();
            // portIndices.put(port, currentNumber);
            // }
            // }
            // } else {
            // Find the number of edges incident to ports
            for (LPort easternPort : node.getPorts(portSide)) {
                currentNumber += easternPort.getDegree();
            }

            // Assign the number to all ports
            for (LPort easternPort : node.getPorts(portSide)) {
                if (easternPort.getDegree() > 0) {
                    portIndices.put(easternPort, currentNumber);
                }
            }
            // }
        }
    }

    private void countInLayerEdgeCrossings(boolean isFirstRun, NodeGroup[] freeLayer,
            boolean forward, LNode currNode, LNode nextNode) {
        boolean currNodeHasInLayerEdges = false;
        LinkedList<LEdge> currNodeInLayerEdges = new LinkedList<LEdge>();
        for (LPort port : currNode.getPorts(forward ? PortSide.WEST : PortSide.EAST)) {
            if (!port.getConnectedEdges().iterator().hasNext()) {
                break;
            }
            // TODOALAN change for mutliple edges into port
            LEdge edge = port.getConnectedEdges().iterator().next();

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
        if (currNodeHasInLayerEdges) {
            if (isFirstRun) {
                setPortIndices(freeLayer, forward);
            }
            PortSide portSide = forward ? PortSide.WEST : PortSide.EAST;
            for (LEdge edge : currNodeInLayerEdges) {
                LPort otherPort = null;
                LPort thisPort = null;
                if (edge.getSource().getNode() == currNode) {
                    otherPort = edge.getTarget();
                    thisPort = edge.getSource();
                } else {
                    otherPort = edge.getSource();
                    thisPort = edge.getTarget();
                }

                if (nextNode.getPorts(portSide) != null
                        && nextNode.getPorts(portSide).iterator().hasNext()
                        && portIndices.containsKey(nextNode.getPorts(portSide).iterator().next())) {
                    // F** iterator TODOALAN Bug: Can lead to endless loop
                    if (portIndices.get(otherPort) > portIndices.get(nextNode.getPorts(portSide)
                            .iterator().next())) {
                        for (LPort port : nextNode.getPorts(portSide)) {
                            inLayerCrossingsIJ += port.getDegree();
                        }
                        // nextNode.getPorts(portSide).iterator().
                    }
                }
                assert portIndices != null;
                assert otherPort != null;
                assert thisPort != null;
                if (portIndices.containsKey(otherPort) && portIndices.containsKey(thisPort)) {
                    if (portIndices.get(otherPort) < portIndices.get(thisPort)) {
                        boolean countRestOfPorts = false;
                        for (LPort port : currNode.getPorts(portSide)) {
                            if (port == thisPort) {
                                countRestOfPorts = true;
                            }
                            if (countRestOfPorts) {
                                inLayerCrossingsJI += port.getDegree();
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * @param currNode
     * @param nextNode
     */
    private void northSouthPortAdjust(LNode currNode, LNode nextNode) {
        if (currNode.getProperty(InternalProperties.NODE_TYPE) == NodeType.NORTH_SOUTH_PORT
                && nextNode.getProperty(InternalProperties.NODE_TYPE) == NodeType.NORTH_SOUTH_PORT) {
            CrossingCounter crossingCounter = new CrossingCounter(currNode.getGraph());
            crossingMatrix[currNode.id][nextNode.id] +=
                    crossingCounter.crossBetweenTwoNorthSouthNodes(currNode, nextNode);
            crossingMatrix[nextNode.id][currNode.id] +=
                    crossingCounter.crossBetweenTwoNorthSouthNodes(nextNode, currNode);
        }
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
    protected void exchangeNodes(final int indexOne, final int indexTwo, final NodeGroup[] layer) {
        // Edit port Indices Map for in-layer edge crossings
        // We simply edit for both sides, as we don't know which direction we are going into yet
        // TODOALAN
        // make more efficient or at least reduce code amount TODOALAN
        int nodeOneDegreeEast = 0, nodeOneDegreeWest = 0;
        int nodeTwoDegreeEast = 0, nodeTwoDegreeWest = 0;

        LNode nodeOne = layer[indexOne].getNode();
        Iterator<LPort> nodeOneEastIterator = nodeOne.getPorts(PortSide.EAST).iterator();
        Iterator<LPort> nodeOneWestIterator = nodeOne.getPorts(PortSide.WEST).iterator();
        LNode nodeTwo = layer[indexTwo].getNode();
        Iterator<LPort> nodeTwoEastIterator = nodeTwo.getPorts(PortSide.EAST).iterator();
        Iterator<LPort> nodeTwoWestIterator = nodeTwo.getPorts(PortSide.WEST).iterator();
        while (nodeOneEastIterator.hasNext()) {
            nodeOneEastIterator.next();
            nodeOneDegreeEast++;
        }
        while (nodeOneWestIterator.hasNext()) {
            nodeOneWestIterator.next();
            nodeOneDegreeWest++;
        }
        while (nodeTwoEastIterator.hasNext()) {
            nodeTwoEastIterator.next();
            nodeTwoDegreeEast++;
        }
        while (nodeTwoWestIterator.hasNext()) {
            nodeTwoWestIterator.next();
            nodeTwoDegreeWest++;
        }

        for (LPort port : nodeOne.getPorts(PortSide.EAST)) {
            if (portIndices.containsKey(port)) {
                int iCurrent = portIndices.get(port);
                portIndices.put(port, iCurrent + nodeOneDegreeEast);
            }
        }
        for (LPort port : nodeOne.getPorts(PortSide.WEST)) {
            if (portIndices.containsKey(port)) {
                int iCurrent = portIndices.get(port);
                portIndices.put(port, iCurrent + nodeOneDegreeWest);
            }
        }
        for (LPort port : nodeTwo.getPorts(PortSide.EAST)) {
            if (portIndices.containsKey(port)) {
                int iCurrent = portIndices.get(port);
                portIndices.put(port, iCurrent + nodeTwoDegreeEast);
            }
        }
        for (LPort port : nodeTwo.getPorts(PortSide.WEST)) {
            if (portIndices.containsKey(port)) {
                int iCurrent = portIndices.get(port);
                portIndices.put(port, iCurrent + nodeTwoDegreeWest);
            }
        }
        super.exchangeNodes(indexOne, indexTwo, layer);
    }
}
