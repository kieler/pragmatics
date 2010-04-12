/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.kiml.layout.options.PortType;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.modules.ICycleBreaker;

/**
 * Cycle breaker implementation that uses a greedy algorithm. Inspired by Section 9.4 of
 * <ul>
 *   <li>Giuseppe di Battista, Peter Eades, Roberto Tamassia, Ioannis G. Tollis,
 *     <i>Graph Drawing: Algorithms for the Visualization of Graphs</i>,
 *     Prentice Hall, New Jersey, 1999
 * </ul> 
 *
 * @author msp
 */
public class GreedyCycleBreaker extends AbstractAlgorithm implements ICycleBreaker {

    /** indegree values for the nodes. */
    private int[] indeg;
    /** outdegree values for the nodes. */
    private int[] outdeg;
    /** mark for the nodes. */
    private int[] mark;
    /** list of source nodes. */
    private final LinkedList<LNode> sources = new LinkedList<LNode>();
    /** list of sink nodes. */
    private final LinkedList<LNode> sinks = new LinkedList<LNode>();
    /** map of nodes to their indices for degrees and mark. */
    private Map<LNode, Integer> indexMap;

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        super.reset();
        sources.clear();
        sinks.clear();
    }
    
    /** load factor for hash map. */
    private static final float HASH_LOAD = 0.9f;
    
    /**
     * {@inheritDoc}
     */
    public void breakCycles(final Collection<LNode> nodes) {
        getMonitor().begin("Greedy cycle removal", 1);

        // initialize values for the algorithm
        int unprocessedNodes = nodes.size();
        indexMap = new HashMap<LNode, Integer>((int) (unprocessedNodes / HASH_LOAD) + 1, HASH_LOAD);
        indeg = new int[unprocessedNodes];
        outdeg = new int[unprocessedNodes];
        mark = new int[unprocessedNodes];
        int nextRight = -1, nextLeft = 1, index = 0;
        for (LNode node : nodes) {
            indexMap.put(node, index);
            for (LPort port : node.getPorts()) {
                if (port.getType() == PortType.OUTPUT) {
                    outdeg[index] += port.getEdges().size();
                } else {
                    indeg[index] += port.getEdges().size();
                }
            }
            if (outdeg[index] == 0) {
                sinks.add(node);
            } else if (indeg[index] == 0) {
                sources.add(node);
            }
            index++;
        }

        // assign marks to all nodes
        while (unprocessedNodes > 0) {
            while (!sinks.isEmpty()) {
                LNode sink = sinks.removeFirst();
                int i = indexMap.get(sink);
                mark[i] = nextRight--;
                updateNeighbors(sink);
                unprocessedNodes--;
            }
            while (!sources.isEmpty()) {
                LNode source = sources.removeFirst();
                int i = indexMap.get(source);
                mark[i] = nextLeft++;
                updateNeighbors(source);
                unprocessedNodes--;
            }
            if (unprocessedNodes != 0) {
                int maxOutflow = Integer.MIN_VALUE;
                LNode maxNode = null;
                int maxIndex = 0;
                index = 0;
                for (LNode node : nodes) {
                    if (mark[index] == 0) {
                        int outflow = outdeg[index] - indeg[index];
                        if (outflow > maxOutflow) {
                            maxOutflow = outflow;
                            maxNode = node;
                            maxIndex = index;
                        }
                    }
                    index++;
                }
                mark[maxIndex] = nextLeft++;
                updateNeighbors(maxNode);
                unprocessedNodes--;
            }
        }

        // shift negative ranks
        int shiftBase = nodes.size() + 1;
        for (index = 0; index < nodes.size(); index++) {
            if (mark[index] < 0) {
                mark[index] += shiftBase;
            }
        }

        // reverse edges that point left
        index = 0;
        for (LNode node : nodes) {
            for (LPort port : node.getPorts(PortType.OUTPUT)) {
                for (LEdge edge : port.getEdges()) {
                    int targetIx = indexMap.get(edge.getTarget().getOwner());
                    if (mark[index] > mark[targetIx]) {
                        LPort source = edge.getSource();
                        LPort target = edge.getTarget();
                        edge.setSource(target);
                        edge.setTarget(source);
                    }
                }                
            }
            index++;
        }

        getMonitor().done();
    }

    /**
     * Updates indegree and outdegree values of the neighbors of the given node,
     * simulating its removal from the graph. the sources and sinks lists are
     * also updated.
     * 
     * @param node node for which neighbors are updated
     */
    private void updateNeighbors(final LNode node) {
        for (LPort port : node.getPorts()) {
            for (LPort connectedPort : port.getConnectedPorts()) {
                LNode endpoint = connectedPort.getOwner();
                int index = indexMap.get(endpoint);
                if (mark[index] == 0) {
                    if (port.getType() == PortType.OUTPUT) {
                        indeg[index]--;
                        if (indeg[index] == 0 && outdeg[index] != 0) {
                            sources.add(endpoint);
                        }
                    } else {
                        outdeg[index]--;
                        if (outdeg[index] == 0 && indeg[index] != 0) {
                            sinks.add(endpoint);
                        }
                    }
                }
            }
        }
    }

}
