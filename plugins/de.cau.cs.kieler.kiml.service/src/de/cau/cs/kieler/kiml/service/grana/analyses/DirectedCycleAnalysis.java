/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.service.grana.analyses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.google.common.collect.Iterables;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.service.grana.AnalysisOptions;
import de.cau.cs.kieler.kiml.service.grana.IAnalysis;

/**
 * Analysis for approximate directed cycle count, that is the number of back edges determined by a
 * heuristic that tries to minimize back edges. This gives a hint on how many edges need to be reversed
 * or removed to obtain a directed acyclic graph.
 *
 * @author msp
 * @kieler.rating 2012-07-10 proposed yellow msp
 */
public class DirectedCycleAnalysis implements IAnalysis {
    
    /** the identifier for the cycle analysis. */
    public static final String ID = "de.cau.cs.kieler.kiml.grana.directedCycles";

    /** indegree values for the nodes. */
    private int[] indeg;
    /** outdegree values for the nodes. */
    private int[] outdeg;
    /** mark for the nodes. */
    private int[] mark;
    /** node id map. */
    private Map<KNode, Integer> idmap = new HashMap<KNode, Integer>();
    /** list of source nodes. */
    private final LinkedList<KNode> sources = new LinkedList<KNode>();
    /** list of sink nodes. */
    private final LinkedList<KNode> sinks = new LinkedList<KNode>();
    
    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final KNode parentNode, final Map<String, Object> results,
            final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Approximate directed cycle count", 1);
        
        boolean hierarchy = parentNode.getData(KShapeLayout.class).getProperty(
                AnalysisOptions.ANALYZE_HIERARCHY);
        int count;
        
        if (hierarchy) {
            List<KNode> nodes = new LinkedList<KNode>();
            LinkedList<KNode> nodeQueue = new LinkedList<KNode>();
            nodeQueue.addAll(parentNode.getChildren());
            while (!nodeQueue.isEmpty()) {
                KNode node = nodeQueue.removeFirst();
                nodes.add(node);
                nodeQueue.addAll(node.getChildren());
            }
            count = process(nodes, true);
        } else {
            count = process(parentNode.getChildren(), false);
        }

        progressMonitor.done();
        return count;
    }
    
    /**
     * Count the number of back edges in the given graph.
     * 
     * @param nodes list of nodes to process
     * @param hierarchy whether hierarchy should be processed
     * @return the number of back edges
     */
    private int process(final List<KNode> nodes, final boolean hierarchy) {
        // initialize values for the algorithm (sum of priorities of incoming edges and outgoing
        // edges per node, and the "layer" calculated for each node)
        int unprocessedNodes = nodes.size();
        indeg = new int[unprocessedNodes];
        outdeg = new int[unprocessedNodes];
        mark = new int[unprocessedNodes];
        
        int index = 0;
        for (KNode node : nodes) {
            idmap.put(node, index);
            for (KEdge edge : node.getIncomingEdges()) {
                // ignore self-loops and hierarchy edges
                if (edge.getSource() != node && (hierarchy
                        || edge.getSource().getParent() == node.getParent())) {
                    indeg[index]++;
                }
            }
            
            for (KEdge edge : node.getOutgoingEdges()) {
                // ignore self-loops and hierarchy edges
                if (edge.getTarget() != node && (hierarchy
                        || edge.getTarget().getParent() == node.getParent())) {
                    outdeg[index]++;
                }
            }
            
            // collect sources and sinks
            if (outdeg[index] == 0) {
                sinks.add(node);
            } else if (indeg[index] == 0) {
                sources.add(node);
            }
            index++;
        }
        
        // next rank values used for sinks and sources (from right and from left)
        int nextRight = -1, nextLeft = 1;
        
        // create random number generator
        Random random = new Random(1);

        // assign marks to all nodes
        List<KNode> maxNodes = new ArrayList<KNode>();
        
        while (unprocessedNodes > 0) {
            // while we have sinks left...
            while (!sinks.isEmpty()) {
                KNode sink = sinks.removeFirst();
                mark[idmap.get(sink)] = nextRight--;
                updateNeighbors(sink);
                unprocessedNodes--;
            }
            
            // while we have sources left...
            while (!sources.isEmpty()) {
                KNode source = sources.removeFirst();
                mark[idmap.get(source)] = nextLeft++;
                updateNeighbors(source);
                unprocessedNodes--;
            }
            
            // while there are unprocessed nodes left that are neither sinks nor sources...
            if (unprocessedNodes > 0) {
                int maxOutflow = Integer.MIN_VALUE;
                
                // find the set of unprocessed node (=> mark == 0), with the largest out flow
                for (KNode node : nodes) {
                    int id = idmap.get(node);
                    if (mark[id] == 0) {
                        int outflow = outdeg[id] - indeg[id];
                        if (outflow >= maxOutflow) {
                            if (outflow > maxOutflow) {
                                maxNodes.clear();
                                maxOutflow = outflow;
                            }
                            maxNodes.add(node);
                        }
                    }
                }
                
                // randomly select a node from the ones with maximal outflow
                KNode maxNode = maxNodes.get(random.nextInt(maxNodes.size()));
                mark[idmap.get(maxNode)] = nextLeft++;
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

        // count edges that point left
        int backEdgeCount = 0;
        for (KNode node : nodes) {
            int sourceIx = idmap.get(node);
            for (KEdge edge : node.getOutgoingEdges()) {
                if (edge.getTarget() != node && idmap.containsKey(edge.getTarget())) {
                    int targetIx = idmap.get(edge.getTarget());
                    if (mark[sourceIx] > mark[targetIx]) {
                        backEdgeCount++;
                    }
                }
            }
        }

        dispose();
        return backEdgeCount;
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
        idmap.clear();
    }

    /**
     * Updates indegree and outdegree values of the neighbors of the given node,
     * simulating its removal from the graph. the sources and sinks lists are
     * also updated.
     * 
     * @param node node for which neighbors are updated
     */
    private void updateNeighbors(final KNode node) {
        for (KEdge edge : Iterables.concat(node.getOutgoingEdges(), node.getIncomingEdges())) {
            KNode endpoint = edge.getSource() == node ? edge.getTarget() : edge.getSource();
            
            // exclude self-loops
            if (endpoint == node || !idmap.containsKey(endpoint)) {
                continue;
            }
            
            int index = idmap.get(endpoint);
            if (mark[index] == 0) {
                if (edge.getTarget() == endpoint) {
                    indeg[index]--;
                    if (indeg[index] <= 0 && outdeg[index] > 0) {
                        sources.add(endpoint);
                    }
                } else {
                    outdeg[index]--;
                    if (outdeg[index] <= 0 && indeg[index] > 0) {
                        sinks.add(endpoint);
                    }
                }
            }
        }
    }

}
