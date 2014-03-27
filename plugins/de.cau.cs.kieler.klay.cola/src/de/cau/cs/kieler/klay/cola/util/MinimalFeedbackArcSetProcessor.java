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
package de.cau.cs.kieler.klay.cola.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.google.common.collect.Sets;

import de.cau.cs.kieler.klay.cola.graph.CEdge;
import de.cau.cs.kieler.klay.cola.graph.CNode;

/**
 * @author msp
 * @author uru
 */
public class MinimalFeedbackArcSetProcessor {

    /** indegree values for the nodes. */
    private int[] indeg;
    /** outdegree values for the nodes. */
    private int[] outdeg;
    /** mark for the nodes, inducing an ordering of the nodes. */
    private int[] mark;
    /** list of source nodes. */
    private final LinkedList<CNode> sources = new LinkedList<CNode>();
    /** list of sink nodes. */
    private final LinkedList<CNode> sinks = new LinkedList<CNode>();
    

    public List<CNode> scc;
    
    /**
     * {@inheritDoc}
     */
    public Set<CEdge> process(final List<CNode> nodes) {
        
        if (nodes.size() == 1) {
            return Sets.newHashSet();
        }
        
        scc = nodes;
        
        Set<CEdge> mfas = Sets.newHashSet(); 
        //Collection<CNode> nodes = layeredGraph.getChildren();

        // initialize values for the algorithm (sum of priorities of incoming edges and outgoing
        // edges per node, and the ordering calculated for each node)
        int unprocessedNodeCount = nodes.size();
        indeg = new int[unprocessedNodeCount];
        outdeg = new int[unprocessedNodeCount];
        mark = new int[unprocessedNodeCount];
        
        int index = 0;
        for (CNode node : nodes) {
            // the node id is used as index for the indeg, outdeg, and mark arrays
            node.id = index;

            // the node's list of inc/out edges contains every port's edges as well
            //for (CPort port : node.getPorts()) {
                // calculate the sum of edge priorities
                for (CEdge edge : node.getIncomingEdges()) {
                    // ignore self-loops
                    if (edge.getSource().equals(node)) {
                        continue;
                    }
                    
                    if (!scc.contains(edge.getSource())) {
                        continue;
                    }
                    
                    //int priority = edge.getProperty(Properties.PRIORITY);
                    // TODO
                    int priority = 0;
                    indeg[index] += priority > 0 ? priority + 1 : 1;
                }
                
                for (CEdge edge : node.getOutgoingEdges()) {
                    // ignore self-loops
                    if (edge.getTarget().equals(node)) {
                        continue;
                    }
                    
                    if (!scc.contains(edge.getTarget())) {
                        continue;
                    }
                    
                    //int priority = edge.getProperty(Properties.PRIORITY);
                    // TODO
                    int priority = 0;
                    outdeg[index] += priority > 0 ? priority + 1 : 1;
                }
            //}
            
            // collect sources and sinks
            if (outdeg[index] == 0) {
                sinks.add(node);
            } else if (indeg[index] == 0) {
                sources.add(node);
            }
            index++;
        }
        
        
        System.out.println("sources: " + sources);
        System.out.println("sinks: " + sinks);
        // next rank values used for sinks and sources (from right and from left)
        int nextRight = -1, nextLeft = 1;

        // assign marks to all nodes
        List<CNode> maxNodes = new ArrayList<CNode>();
        //Random random = layeredGraph.getProperty(InternalProperties.RANDOM); 
        // TODO new
        Random random = new Random(1);
        
        while (unprocessedNodeCount > 0) {
            // sinks are put to the right --> assign negative rank, which is later shifted to positive
            while (!sinks.isEmpty()) {
                CNode sink = sinks.removeFirst();
                mark[sink.id] = nextRight--;
                updateNeighbors(sink);
                unprocessedNodeCount--;
            }
            
            // sources are put to the left --> assign positive rank
            while (!sources.isEmpty()) {
                CNode source = sources.removeFirst();
                mark[source.id] = nextLeft++;
                updateNeighbors(source);
                unprocessedNodeCount--;
            }
            
            // while there are unprocessed nodes left that are neither sinks nor sources...
            if (unprocessedNodeCount > 0) {
                int maxOutflow = Integer.MIN_VALUE;
                
                // find the set of unprocessed node (=> mark == 0), with the largest out flow
                for (CNode node : nodes) {
                    if (mark[node.id] == 0) {
                        int outflow = outdeg[node.id] - indeg[node.id];
                        if (outflow >= maxOutflow) {
                            if (outflow > maxOutflow) {
                                maxNodes.clear();
                                maxOutflow = outflow;
                            }
                            maxNodes.add(node);
                        }
                    }
                }
                assert maxOutflow > Integer.MIN_VALUE;
                
                // randomly select a node from the ones with maximal outflow and put it left
                int rand = random.nextInt(maxNodes.size());
                System.out.println("random select " + rand);
                CNode maxNode = maxNodes.get(rand);
                mark[maxNode.id] = nextLeft++;
                updateNeighbors(maxNode);
                unprocessedNodeCount--;
            }
        }

        // shift negative ranks to positive; this applies to sinks of the graph
        int shiftBase = nodes.size() + 1;
        for (index = 0; index < nodes.size(); index++) {
            if (mark[index] < 0) {
                mark[index] += shiftBase;
            }
        }

        // reverse edges that point left
        for (CNode node : nodes) {
//            CPort[] ports = node.getPorts().toArray(new CPort[node.getPorts().size()]);
//            for (CPort port : ports) {
//                CEdge[] outgoingEdges = port.getOutgoingEdges().toArray(
//                        new CEdge[port.getOutgoingEdges().size()]);
                
                // look at the node's outgoing edges
                for (CEdge edge : node.getOutgoingEdges()) {
                    int targetIx = edge.getTarget().id;
                    if (mark[node.id] > mark[targetIx]) {
                        mfas.add(edge);
                        //edge.reverse(layeredGraph, true);
                        //layeredGraph.setProperty(InternalProperties.CYCLIC, true);
                    }
                }                
//            }
        }

        dispose();
        
        return mfas;
    }
    
    /**
     * Release all created resources so the GC can reap them.
     */
    private void dispose() {
        this.indeg = null;
        this.outdeg = null;
        this.mark = null;
        sources.clear();
        sinks.clear();
    }

    /**
     * Updates indegree and outdegree values of the neighbors of the given node,
     * simulating its removal from the graph. the sources and sinks lists are
     * also updated.
     * 
     * @param node node for which neighbors are updated
     */
    private void updateNeighbors(final CNode node) {
        //for (CPort port : node.getPorts()) {
            for (CEdge edge : node.getConnectedEdges()) {
//                CPort connectedPort = edge.getSource() == port ? edge.getTarget() : edge.getSource();
//                CNode endpoint = connectedPort.getNode();
                CNode endpoint = edge.getSource().equals(node) ? edge.getTarget() : edge.getSource();
                
                // exclude self-loops
                if (node == endpoint) {
                    continue;
                }
                
                // exclude nodes that are not part of this scc
                if (!scc.contains(endpoint)) {
                    continue;
                }
                
                //int priority = edge.getProperty(Properties.PRIORITY);
                // TODO
                int priority = 0;
                if (priority < 0) {
                    priority = 0;
                }
                int index = endpoint.id;
                if (mark[index] == 0) {
                    //if (edge.getTarget() == connectedPort) {
                    // TODO
                    if (edge.getTarget() == endpoint) {
                        indeg[index] -= priority + 1;
                        if (indeg[index] <= 0 && outdeg[index] > 0) {
                            sources.add(endpoint);
                        }
                    } else {
                        outdeg[index] -= priority + 1;
                        if (outdeg[index] <= 0 && indeg[index] > 0) {
                            sinks.add(endpoint);
                        }
                    }
                }
            }
//        }
    }
    
}
