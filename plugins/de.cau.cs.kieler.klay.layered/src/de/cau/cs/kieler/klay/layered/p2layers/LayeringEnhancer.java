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
package de.cau.cs.kieler.klay.layered.p2layers;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.kiml.options.PortType;
import de.cau.cs.kieler.klay.layered.Properties;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;

/**
 * An experimental enhancing algorithm for layering.
 *
 * @author msp
 */
public class LayeringEnhancer extends AbstractAlgorithm {

    /** the list of edges that were created by the enhancer. */
    private List<LEdge> newEdges;
    
    /**
     * Perform pre-processing before a standard layering method is applied.
     * 
     * @param nodes the nodes of the graph
     */
    public void preProcess(final Collection<LNode> nodes) {
        newEdges = new LinkedList<LEdge>();
        
        // set node identifiers
        int curid = 0;
        for (LNode node : nodes) {
            node.id = curid++;
        }
        // calculate target reachability
        boolean[][] reach = calcReachability(nodes);
        
        LNode[] nodeArray = nodes.toArray(new LNode[nodes.size()]);
        for (int i = 0; i < nodeArray.length; i++) {
            for (int j = i + 1; j < nodeArray.length; j++) {
                if (reach[i][j]) {
                    preProcess(nodeArray[j], nodeArray[i], reach, nodeArray.length);
                } else {
                    preProcess(nodeArray[i], nodeArray[j], reach, nodeArray.length);
                }
            }
        }
    }
    
    /** minimal number of candidates that are needed to add new edges. */
    private static final int MIN_CAND = 3;
    
    /**
     * Perform pre-processing for the given node pair. The second node should not be reachable
     * from the first one.
     * 
     * @param node1 the first node
     * @param node2 the second node
     * @param reach the reachability matrix
     * @param n the total number of nodes
     */
    private void preProcess(final LNode node1, final LNode node2,
            final boolean[][] reach, final int n) {
        LinkedList<LNode> bfsQueue = new LinkedList<LNode>();
        bfsQueue.offer(node1);
        // calculate the subgraph that is reachable from the first node
        int[] mark = new int[n];
        while (!bfsQueue.isEmpty()) {
            for (LPort port : bfsQueue.poll().getPorts(PortType.OUTPUT)) {
                for (LEdge edge : port.getEdges()) {
                    LNode target = edge.getTarget().getNode();
                    if (mark[target.id] == 0) {
                        mark[target.id] = 1;
                        bfsQueue.offer(target);
                    }
                }
            }
        }
        
        // determine which nodes from the first subgraph are also reachable from the second node
        List<LNode> candidates = new LinkedList<LNode>();
        bfsQueue.offer(node2);
        while (!bfsQueue.isEmpty()) {
            for (LPort port : bfsQueue.poll().getPorts(PortType.OUTPUT)) {
                for (LEdge edge : port.getEdges()) {
                    LNode target = edge.getTarget().getNode();
                    if (target != node1) {
                        if (mark[target.id] == 0) {
                            mark[target.id] = 2;
                            bfsQueue.offer(target);
                        } else if (mark[target.id] == 1) {
                            mark[target.id] = 2;
                            candidates.add(target);
                        }
                    }
                }
            }
        }
        
        if (candidates.size() < MIN_CAND) {
            return;
        }
        
        // find a topological sort for the reachability of the candidates
        List<LNode> candSources = new LinkedList<LNode>();
        for (LNode cand : candidates) {
            // the mark is now interpreted as the indegree for topological sort
            mark[cand.id] = 0;
            for (LNode cand2 : candidates) {
                if (reach[cand2.id][cand.id]) {
                    mark[cand.id]++;
                }
            }
            if (mark[cand.id] == 0) {
                candSources.add(cand);
            }
        }
        LNode[] candSort = new LNode[candidates.size()];
        int next = 0;
        while (!candSources.isEmpty()) {
            LNode candSource = candSources.remove(0);
            candSort[next++] = candSource;
            boolean[] candReach = reach[candSource.id];
            for (LNode cand2 : candidates) {
                if (candReach[cand2.id]) {
                    mark[cand2.id]--;
                    if (mark[cand2.id] <= 0) {
                        candSources.add(cand2);
                    }
                }
            }
        }
        
        // add edges between consecutive non-reachable candidates
        for (int i = 0; i < candSort.length - 1; i++) {
            LNode source = candSort[i];
            LNode target = candSort[i + 1];
            if (!reach[source.id][target.id]) {
                LPort sourcePort = new LPort(PortType.OUTPUT);
                sourcePort.setNode(source);
                LPort targetPort = new LPort(PortType.INPUT);
                targetPort.setNode(target);
                LEdge newEdge = new LEdge();
                newEdge.setSource(sourcePort);
                newEdge.setTarget(targetPort);
                // give the new edge a negative priority to express zero cost for the layering
                newEdge.setProperty(Properties.PRIORITY, -1);
                newEdges.add(newEdge);
                // update reachability
                boolean[] sourceReach = reach[source.id];
                addVector(reach[target.id], sourceReach);
                reach[source.id][target.id] = true;
                for (int j = 0; j < n; j++) {
                    if (reach[j][source.id]) {
                        addVector(sourceReach, reach[j]);
                    }
                }
            }
        }
    }
    
    /**
     * Perform post-processing after a standard layering was applied.
     */
    public void postProcess() {
        for (LEdge edge : newEdges) {
           LPort sourcePort = edge.getSource();
           sourcePort.getNode().getPorts().remove(sourcePort);
           LPort targetPort = edge.getTarget();
           targetPort.getNode().getPorts().remove(targetPort);
        }
    }
    
    /**
     * Calculate the reachability matrix for an acyclic graph.
     * 
     * @param nodes the nodes of the graph, which must not contain cycles
     * @return a two-dimensional array {@code m} where {@code m[i][j]} is true iff the node
     *     with id {@code j} is reachable from the node with id {@code i} 
     */
    public static boolean[][] calcReachability(final Collection<LNode> nodes) {
        int n = nodes.size();
        boolean[][] reach = new boolean[n][n];
        
        // find sinks of the graph
        List<LNode> sinks = new LinkedList<LNode>();
        int[] outdeg = new int[n];
        for (LNode node : nodes) {
            for (LPort port : node.getPorts(PortType.OUTPUT)) {
                outdeg[node.id] += port.getEdges().size();
            }
            if (outdeg[node.id] == 0) {
                sinks.add(node);
            }
        }
        
        while (!sinks.isEmpty()) {
            LNode sink = sinks.remove(0);
            for (LPort port1 : sink.getPorts(PortType.INPUT)) {
                for (LPort port2 : port1.getConnectedPorts()) {
                    LNode source = port2.getNode();
                    outdeg[source.id]--;
                    if (outdeg[source.id] <= 0) {
                        sinks.add(source);
                    }
                    // set reachability
                    addVector(reach[sink.id], reach[source.id]);
                    reach[source.id][sink.id] = true;
                }
            }
        }
        
        return reach;
    }
    
    /**
     * Add two boolean vectors.
     * 
     * @param src the first operand
     * @param dst the second operand and destination vector
     */
    private static void addVector(final boolean[] src, final boolean[] dst) {
        for (int i = 0; i < dst.length; i++) {
            dst[i] |= src[i];
        }        
    }
    
}
