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
public class IncidentEdgeCrossCounter { // TODOALAN rename: twonode.. pairwise...

    private int crossingsForOrderIJ;

    private int crossingsForOrderJI;

    private LNode currNode;

    private LNode nextNode;

    private int[] nodeDegrees;

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

    private int[] nodePositions;

    /**
     * @param currNode
     * @param nextNode
     * @param forwardSweep
     */
    public IncidentEdgeCrossCounter(final LNode currNode, final LNode nextNode,
            final boolean forwardSweep, int[] nodeDegrees, int[] nodePositions) {
        crossingsForOrderIJ = 0;
        crossingsForOrderJI = 0;
        this.currNode = currNode;
        this.nextNode = nextNode;
        this.isForwardSweep = forwardSweep;
        this.nodeDegrees = nodeDegrees;
        this.nodePositions = nodePositions;
    }

    /**
     * Calculates the number of crossings for incident edges to node i and j. The crossing amounts
     * can be received with getCrossingForOrderJI and getCrossingForOrderIJ.
     */
    public void calculateCrossingNumber() {
        
        if(currNode == nextNode){
            return;
        }
        
        TreeMap<Double, LEdge> iEdges = getEdges(isForwardSweep, currNode);
        TreeMap<Double, LEdge> jEdges = getEdges(isForwardSweep, nextNode);
        System.out.println("\nIEdges = " + iEdges);
        System.out.println("JEdges = " + jEdges);

        LPort iNeighbourPort = null, jNeighbourPort = null;
        LNode neighbourToINode = null, neighbourToJNode = null;
        boolean stillWorking = true;
        while (stillWorking) {
            stillWorking = false;
            if (iNeighbourPort == null || jNeighbourPort == null) {

                iNeighbourPort = getNextPort(iEdges);
                jNeighbourPort = getNextPort(jEdges);

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
                crossingsForOrderIJ += iEdges.size();
                if (!jEdges.isEmpty()) {
                    stillWorking = true;
                    jNeighbourPort = getNextPortAndRemoveEdge(jEdges);
                    if (jNeighbourPort == null) { // only self loops left
                        break;
                    }
                    neighbourToJNode = jNeighbourPort.getNode();
                }
            } else if (causesCrossingsToOrderJI) {
                crossingsForOrderJI += jEdges.size();
                if (!iEdges.isEmpty()) {
                    stillWorking = true;
                    iNeighbourPort = getNextPortAndRemoveEdge(iEdges);
                    if (iNeighbourPort == null) { // only self loops left
                        break;
                    }
                    neighbourToINode = iNeighbourPort.getNode();
                }
            } else if(causesCrossingsToBoth){
                iNeighbourPort = getNextPortAndRemoveEdgeSameNode(iEdges);
                jNeighbourPort = getNextPortAndRemoveEdgeSameNode(jEdges);
                crossingsForOrderIJ += iEdges.size();
                crossingsForOrderJI += jEdges.size();
                if (jNeighbourPort == null || iNeighbourPort == null) {
                    // only self loops left
                    break;
                }
                neighbourToJNode = jNeighbourPort.getNode();
                neighbourToINode = iNeighbourPort.getNode();
                
                if (!jEdges.isEmpty() && !iEdges.isEmpty()) {
                    stillWorking = true;
                }
            }
        }
        
        countInLayerEdgeCrossings(currNode, nextNode);
    }
    
    /**
     * @param jEdges
     * @return
     */
    private LPort getNextPort(TreeMap<Double, LEdge> edges) {
        LPort neighbourPort = null;
        LEdge edge;
        while (!edges.isEmpty()) {
            edge = edges.get(edges.firstKey());
            if (edge.getSource().getNode() == edge.getTarget().getNode()) {
                edges.remove(edges.firstKey());
            } else {
                neighbourPort = isForwardSweep ? edge.getSource() : edge.getTarget();
                break;
            }
        }
        return neighbourPort;
    }

    private LPort getNextPortAndRemoveEdgeSameNode(TreeMap<Double, LEdge> edges) {
        LPort port = getNextPortAndRemoveEdge(edges);
        if(!edges.isEmpty()){
            boolean edgesToSameNodeRemain = edges.firstKey() % 1 > 0;
            while (!edges.isEmpty() &&edgesToSameNodeRemain) {
                edges.remove(edges.firstKey());
            }
        }
        return port;
    }

    private TreeMap<Double, LEdge> getEdges(final boolean forwardSweep, final LNode node) {
        List<LPort> ports = node.getPorts();
        TreeMap<Double, LEdge> edges = new TreeMap<Double, LEdge>();
        for (LPort port : ports) {
            for (LEdge edge : forwardSweep ? port.getIncomingEdges() : port.getOutgoingEdges()) {
                boolean edgeIsNotSelfLoop =
                        edge.getSource().getNode() != edge.getTarget().getNode();
                boolean edgeIsNotInLayerEdge =
                        edge.getSource().getNode().getLayer() != edge.getTarget().getNode()
                                .getLayer();
             
                
                if (edgeIsNotSelfLoop && edgeIsNotInLayerEdge) {
                    double index =
                            forwardSweep ? edge.getSource().getNode().id : edge.getTarget()
                                    .getNode().id;
                    if (edges.containsKey(index)){
                        index += 0.000001; //TODOALAN number 2.1 2.2 ... 2.11 etc. WTF potential!!
                    }
                    edges.put(index, edge);
                }
            }
        }
        return edges;
    }

    private LPort getNextPortAndRemoveEdge(final TreeMap<Double, LEdge> edges) {
        edges.remove(edges.firstKey());
        return getNextPort(edges);
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
                boolean currNodeIsSource = edge.getSource().getNode() == currNode;
                LPort edgeEndPort = currNodeIsSource ? edge.getTarget() : edge.getSource();

                if (nodePositions[edgeEndPort.getNode().id] > nodePositions[nextNode.id]) {
                    crossingsForOrderIJ += nodeDegrees[nextNode.id];
                } else if (nodePositions[edgeEndPort.getNode().id] < nodePositions[nextNode.id]) {
                    crossingsForOrderJI += nodeDegrees[edgeEndPort.getNode().id];
                } else if (nodePositions[edgeEndPort.getNode().id] == nodePositions[nextNode.id]
                        && edgeEndPort.getProperty(LayoutOptions.PORT_CONSTRAINTS).isOrderFixed()) {
                    // Western ports (accessed by forward sweep) are numbered bottom up.
                    // nodeDegrees is the degree of the node on the side we are currently working
                    // on.
                    if (isForwardSweep) {
                        crossingsForOrderIJ +=
                                nodeDegrees[edgeEndPort.getNode().id] - edgeEndPort.id;
                        crossingsForOrderJI += edgeEndPort.id;
                    } else {
                        crossingsForOrderIJ += edgeEndPort.id;
                        crossingsForOrderJI +=
                                nodeDegrees[edgeEndPort.getNode().id] - edgeEndPort.id;
                    }
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
