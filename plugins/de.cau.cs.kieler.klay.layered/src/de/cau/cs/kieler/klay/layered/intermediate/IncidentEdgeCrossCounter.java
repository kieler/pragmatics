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
import java.util.ListIterator;
import java.util.TreeMap;

import de.cau.cs.kieler.kiml.options.LayoutOptions;
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

    private TreeMap<Integer, LEdge> iEdges;

    private TreeMap<Integer, LEdge> jEdges;

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

    private boolean forwardSweep;

    /**
     * @param iEdges
     * @param jEdges
     * @param forwardSweep
     */
    public IncidentEdgeCrossCounter(final TreeMap<Integer, LEdge> iEdges,
            final TreeMap<Integer, LEdge> jEdges, final boolean forwardSweep) {
        crossingsForOrderIJ = 0;
        crossingsForOrderJI = 0;
        this.iEdges = iEdges;
        this.jEdges = jEdges;
        this.forwardSweep = forwardSweep;
    }

    /**
     * Calculates the number of crossings for incident edges to node i and j. The crossing amounts
     * can be received with getCrossingForOrderJI and getCrossingForOrderIJ.
     */
    public void calculateCrossingNumber() {
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
                if (forwardSweep) {
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
    }

    private LPort getNextPortAndRemoveEdge(final TreeMap<Integer, LEdge> edges) {
        LPort iNeighbourPort = null;
        LEdge edge;
        while (!edges.isEmpty()) {
            edge = edges.remove(edges.firstKey());
            if (edge.getSource().getNode() != edge.getTarget().getNode()) {
                iNeighbourPort = forwardSweep ? edge.getSource() : edge.getTarget();
                break;
            }
        }
        return iNeighbourPort;
    }

}
