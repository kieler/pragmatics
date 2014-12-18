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
import java.util.TreeMap;

import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;

/**
 * @author alan
 *
 */
public class IncidentEdgeCrossCounter {

    private int crossingsForOrderIJ;

    private int crossingsForOrderJI;

    private LNode currNode;

    private LNode nextNode;
    
    private int [] nodeDegrees;

    /**
     * @return the crossingsForOrderIJ
     */
    public int getCrossingsForOrderIJ() {
        return crossingsForOrderIJ;
    }

    /**
     * @return the crossingsForOrderJI
     */
    public int getCrossingsForOrderJI() {
        return crossingsForOrderJI;
    }

    private boolean isForwardSweep;

    /**
     * @param currNode
     * @param nextNode
     * @param forwardSweep
     */
    public IncidentEdgeCrossCounter(final LNode currNode,
            final LNode nextNode, final boolean forwardSweep, int [] nodeDegrees) {
        crossingsForOrderIJ = 0;
        crossingsForOrderJI = 0;
        this.currNode = currNode;
        this.nextNode = nextNode;
        this.isForwardSweep = forwardSweep;
        this.nodeDegrees = nodeDegrees;
    }

    /**
     * Calculates the number of crossings for incident edges to node i and j. The crossing amounts
     * can be received with getCrossingForOrderJI and getCrossingForOrderIJ.
     */
    public void calculateCrossingNumber() {
        TreeMap<Integer, LEdge> iEdges = getEdges(isForwardSweep, currNode);
        TreeMap<Integer, LEdge> jEdges = getEdges(isForwardSweep, nextNode);
        System.out.println("\nIEdges = " + iEdges);
        System.out.println("JEdges = " + jEdges);
        int remainingIEdges = iEdges.size();
        int remainingJEdges = jEdges.size();
        LPort iNeighbourPort = null, jNeighbourPort = null;
        LNode neighbourToINode = null, neighbourToJNode = null;
        boolean stillWorking = true;
        while (stillWorking) {
            stillWorking = false;
            if (iNeighbourPort == null || jNeighbourPort == null) {

                iNeighbourPort = getNextPortAndRemoveEdge(iEdges);
                jNeighbourPort = getNextPortAndRemoveEdge(jEdges);

                if (iNeighbourPort == null || jNeighbourPort == null) { // only self loops
                    break;
                }

                neighbourToINode = iNeighbourPort.getNode();
                neighbourToJNode = jNeighbourPort.getNode();
            }

            boolean causesCrossingsToOrderIJ = neighbourToINode.id > neighbourToJNode.id;
            boolean causesCrossingsToOrderJI = neighbourToINode.id < neighbourToJNode.id;
            if (neighbourToINode.id == neighbourToJNode.id
                    && neighbourToINode.getProperty(LayoutOptions.PORT_CONSTRAINTS).isOrderFixed()) {
                if (isForwardSweep) {
                    causesCrossingsToOrderIJ |= iNeighbourPort.id > jNeighbourPort.id;
                    causesCrossingsToOrderJI |= iNeighbourPort.id < jNeighbourPort.id;
                } else {
                    causesCrossingsToOrderIJ |= iNeighbourPort.id < jNeighbourPort.id;
                    causesCrossingsToOrderJI |= iNeighbourPort.id > jNeighbourPort.id;
                }
            }

            boolean causesCrossingsToBoth =
                    (neighbourToINode.id == neighbourToJNode.id)
                            && !neighbourToINode.getProperty(LayoutOptions.PORT_CONSTRAINTS)
                                    .isOrderFixed();

            if (causesCrossingsToOrderIJ) {
                crossingsForOrderIJ += remainingIEdges;
                if (!jEdges.isEmpty()) {
                    stillWorking = true;
                    jNeighbourPort = getNextPortAndRemoveEdge(jEdges);
                    if (jNeighbourPort == null) { // only self loops left
                        break;
                    }
                    neighbourToJNode = jNeighbourPort.getNode();
                    remainingJEdges--;
                }
            } else if (causesCrossingsToOrderJI) {
                crossingsForOrderJI += remainingJEdges;
                if (!iEdges.isEmpty()) {
                    stillWorking = true;
                    iNeighbourPort = getNextPortAndRemoveEdge(iEdges);
                    if (iNeighbourPort == null) { // only self loops left
                        break;
                    }
                    neighbourToINode = iNeighbourPort.getNode();
                    remainingIEdges--;
                }
            } else if(causesCrossingsToBoth){
                remainingJEdges--;
                remainingIEdges--;
                crossingsForOrderIJ += remainingIEdges;
                crossingsForOrderJI += remainingJEdges;
                if (!jEdges.isEmpty()) {
                    stillWorking = true;
                    jNeighbourPort = getNextPortAndRemoveEdge(jEdges);
                    neighbourToJNode = jNeighbourPort.getNode();
                }
                if (!iEdges.isEmpty()) {
                    stillWorking = true;
                    iNeighbourPort = getNextPortAndRemoveEdge(iEdges);
                    neighbourToINode = iNeighbourPort.getNode();
                }
                if (jNeighbourPort == null || iNeighbourPort == null) {
                    // only self loops left
                    break;
                }
            }
        }
        
        countInLayerEdgeCrossings(currNode, nextNode);
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

    private LPort getNextPortAndRemoveEdge(final TreeMap<Integer, LEdge> edges) {
        LPort iNeighbourPort = null;
        LEdge edge;
        while (!edges.isEmpty()) {
            edge = edges.remove(edges.firstKey());
            if (edge.getSource().getNode() != edge.getTarget().getNode()) {
                iNeighbourPort = isForwardSweep ? edge.getSource() : edge.getTarget();
                break;
            }
        }
        return iNeighbourPort;
    }
    
    /**
     * TODOALAN explain this: if (otherNodeId > thisNodeId){ inLayerCrossingsIJ +=
     * nodeDegrees[otherNodeId]; } else if (otherNodeId < thisNodeId){ inLayerCrossingsJI +=
     * nodeDegrees[otherNodeId]; }
     * 
     * @param isFirstRun
     * @param freeLayer
     * @param isForwardSweep
     * @param currNode
     * @param nextNode
     * @param freeLayerIndex
     */
    private void countInLayerEdgeCrossings(final LNode currNode, final LNode nextNode) {
        LinkedList<LEdge> currNodeInLayerEdges = new LinkedList<LEdge>();
        PortSide portSide = isForwardSweep ? PortSide.WEST : PortSide.EAST;
        boolean currNodeHasInLayerEdges =
                collectInLayerEdges(currNode, currNodeInLayerEdges, portSide);

        if (currNodeHasInLayerEdges) {
            for (LEdge edge : currNodeInLayerEdges) {
                int nextNodeId = nextNode.id;
                boolean currNodeIsSource = edge.getSource().getNode() == currNode;
                int edgeTargetNodeId =
                        currNodeIsSource ? edge.getTarget().getNode().id : edge.getSource()
                                .getNode().id;

                if (edgeTargetNodeId > nextNodeId) {
                    crossingsForOrderIJ += nodeDegrees[edgeTargetNodeId];
                } else if (edgeTargetNodeId < nextNodeId) {
                    crossingsForOrderJI += nodeDegrees[edgeTargetNodeId];
                }
            }
        }
    }

    /**
     * TODOALAN
     * 
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
    
    

}
