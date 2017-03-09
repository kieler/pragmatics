/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.grana.analyses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.util.ElkGraphUtil;

import de.cau.cs.kieler.grana.AnalysisContext;
import de.cau.cs.kieler.grana.AnalysisOptions;
import de.cau.cs.kieler.grana.IAnalysis;

/**
 * Analysis for approximate directed cycle count, that is the number of back edges determined by a
 * heuristic that tries to minimize back edges. This gives a hint on how many edges need to be reversed
 * or removed to obtain a directed acyclic graph.
 *
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow 2012-07-10 msp
 */
public class DirectedCycleApproximationAnalysis implements IAnalysis {
    
    /** the identifier for the cycle analysis. */
    public static final String ID = "de.cau.cs.kieler.grana.directedCyclesApprox";

    /** indegree values for the nodes. */
    private int[] indeg;
    /** outdegree values for the nodes. */
    private int[] outdeg;
    /** mark for the nodes. */
    private int[] mark;
    /** node id map. */
    private Map<ElkNode, Integer> idmap = new HashMap<ElkNode, Integer>();
    /** list of source nodes. */
    private final LinkedList<ElkNode> sources = new LinkedList<ElkNode>();
    /** list of sink nodes. */
    private final LinkedList<ElkNode> sinks = new LinkedList<ElkNode>();
    
    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final ElkNode parentNode, final AnalysisContext context,
            final IElkProgressMonitor progressMonitor) {
        progressMonitor.begin("Approximate directed cycle count", 1);
        
        boolean hierarchy = parentNode.getProperty(AnalysisOptions.ANALYZE_HIERARCHY);
        int count;
        
        if (hierarchy) {
            List<ElkNode> nodes = new LinkedList<ElkNode>();
            LinkedList<ElkNode> nodeQueue = new LinkedList<ElkNode>();
            nodeQueue.addAll(parentNode.getChildren());
            while (!nodeQueue.isEmpty()) {
                ElkNode node = nodeQueue.removeFirst();
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
    private int process(final List<ElkNode> nodes, final boolean hierarchy) {
        // initialize values for the algorithm (sum of priorities of incoming edges and outgoing
        // edges per node, and the "layer" calculated for each node)
        int unprocessedNodes = nodes.size();
        indeg = new int[unprocessedNodes];
        outdeg = new int[unprocessedNodes];
        mark = new int[unprocessedNodes];
        
        int index = 0;
        for (ElkNode node : nodes) {
            idmap.put(node, index);
            for (ElkEdge edge : ElkGraphUtil.allIncomingEdges(node)) {
                ElkNode source = ElkGraphUtil.getSourceNode(edge);
                // ignore self-loops and hierarchy edges
                if (source != node && (hierarchy || source.getParent() == node.getParent())) {
                    indeg[index]++;
                }
            }
            
            for (ElkEdge edge : ElkGraphUtil.allOutgoingEdges(node)) {
                ElkNode target = ElkGraphUtil.getTargetNode(edge);
                // ignore self-loops and hierarchy edges
                if (target != node && (hierarchy || target.getParent() == node.getParent())) {
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
        List<ElkNode> maxNodes = new ArrayList<ElkNode>();
        
        while (unprocessedNodes > 0) {
            // while we have sinks left...
            while (!sinks.isEmpty()) {
                ElkNode sink = sinks.removeFirst();
                mark[idmap.get(sink)] = nextRight--;
                updateNeighbors(sink);
                unprocessedNodes--;
            }
            
            // while we have sources left...
            while (!sources.isEmpty()) {
                ElkNode source = sources.removeFirst();
                mark[idmap.get(source)] = nextLeft++;
                updateNeighbors(source);
                unprocessedNodes--;
            }
            
            // while there are unprocessed nodes left that are neither sinks nor sources...
            if (unprocessedNodes > 0) {
                int maxOutflow = Integer.MIN_VALUE;
                
                // find the set of unprocessed node (=> mark == 0), with the largest out flow
                for (ElkNode node : nodes) {
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
                ElkNode maxNode = maxNodes.get(random.nextInt(maxNodes.size()));
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
        for (ElkNode node : nodes) {
            int sourceIx = idmap.get(node);
            for (ElkEdge edge : ElkGraphUtil.allOutgoingEdges(node)) {
                ElkNode target = ElkGraphUtil.getTargetNode(edge);
                if (target != node && idmap.containsKey(target)) {
                    int targetIx = idmap.get(target);
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
    private void updateNeighbors(final ElkNode node) {
        for (ElkEdge edge : ElkGraphUtil.allIncidentEdges(node)) {
            ElkNode source = ElkGraphUtil.getSourceNode(edge);
            ElkNode target = ElkGraphUtil.getTargetNode(edge);
            ElkNode endpoint = source == node ? target : source;
            
            // exclude self-loops
            if (endpoint.equals(node) || !idmap.containsKey(endpoint)) {
                continue;
            }
            
            int index = idmap.get(endpoint);
            if (mark[index] == 0) {
                if (target == endpoint) {
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
