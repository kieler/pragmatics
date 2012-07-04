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
package de.cau.cs.kieler.klay.layered.p1cycles;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.klay.layered.ILayoutPhase;
import de.cau.cs.kieler.klay.layered.IntermediateLayoutProcessor;
import de.cau.cs.kieler.klay.layered.IntermediateProcessingStrategy;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * Cycle breaker implementation that uses a greedy algorithm. Inspired by Section 9.4 of
 * <ul>
 *   <li>Giuseppe di Battista, Peter Eades, Roberto Tamassia, Ioannis G. Tollis,
 *     <i>Graph Drawing: Algorithms for the Visualization of Graphs</i>,
 *     Prentice Hall, New Jersey, 1999
 * </ul>
 * 
 * <p>This cycle breaker doesn't support layer constraints out of the box. If layer
 * constraints should be observed,
 * {@link de.cau.cs.kieler.klay.layered.intermediate.EdgeAndLayerConstraintEdgeReverser} and
 * {@link de.cau.cs.kieler.klay.layered.intermediate.LayerConstraintProcessor} should
 * be used.</p>
 * 
 * <dl>
 *   <dt>Precondition:</dt><dd>none</dd>
 *   <dt>Postcondition:</dt><dd>the graph has no cycles, but possibly
 *     new nodes and edges</dd>
 * </dl>
 * 
 * @see de.cau.cs.kieler.klay.layered.intermediate.EdgeAndLayerConstraintEdgeReverser
 * @see de.cau.cs.kieler.klay.layered.intermediate.LayerConstraintProcessor
 * @author msp
 */
public class GreedyCycleBreaker extends AbstractAlgorithm implements ILayoutPhase {
    
    /** intermediate processing strategy. */
    private static final IntermediateProcessingStrategy INTERMEDIATE_PROCESSING_STRATEGY =
        new IntermediateProcessingStrategy(
                IntermediateProcessingStrategy.AFTER_PHASE_5,
                EnumSet.of(IntermediateLayoutProcessor.REVERSED_EDGE_RESTORER));

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
    
    /**
     * {@inheritDoc}
     */
    public IntermediateProcessingStrategy getIntermediateProcessingStrategy(final LayeredGraph graph) {
        return INTERMEDIATE_PROCESSING_STRATEGY;
    }

    /**
     * {@inheritDoc}
     */
    public void process(final LayeredGraph layeredGraph) {
        getMonitor().begin("Greedy cycle removal", 1);
        
        Collection<LNode> nodes = layeredGraph.getLayerlessNodes();

        // initialize values for the algorithm (sum of priorities of incoming edges and outgoing
        // edges per node, and the "layer" calculated for each node)
        int unprocessedNodes = nodes.size();
        indeg = new int[unprocessedNodes];
        outdeg = new int[unprocessedNodes];
        mark = new int[unprocessedNodes];
        
        // iterate over all nodes, ...
        int index = 0;
        for (LNode node : nodes) {
            node.id = index;
            for (LPort port : node.getPorts()) {
                // calculate the sum of edge priorities
                for (LEdge edge : port.getIncomingEdges()) {
                    // ignore self-loops
                    if (edge.getSource().getNode() == node) {
                        continue;
                    }
                    
                    int priority = edge.getProperty(Properties.PRIORITY);
                    indeg[index] += priority > 0 ? priority + 1 : 1;
                }
                
                for (LEdge edge : port.getOutgoingEdges()) {
                    // ignore self-loops
                    if (edge.getTarget().getNode() == node) {
                        continue;
                    }
                    
                    int priority = edge.getProperty(Properties.PRIORITY);
                    outdeg[index] += priority > 0 ? priority + 1 : 1;
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

        // assign marks to all nodes
        List<LNode> maxNodes = new ArrayList<LNode>();
        Random random = layeredGraph.getProperty(Properties.RANDOM);
        
        while (unprocessedNodes > 0) {
            // while we have sinks left...
            while (!sinks.isEmpty()) {
                LNode sink = sinks.removeFirst();
                mark[sink.id] = nextRight--;
                updateNeighbors(sink);
                unprocessedNodes--;
            }
            
            // while we have sources left...
            while (!sources.isEmpty()) {
                LNode source = sources.removeFirst();
                mark[source.id] = nextLeft++;
                updateNeighbors(source);
                unprocessedNodes--;
            }
            
            // while there are unprocessed nodes left that are neither sinks nor sources...
            if (unprocessedNodes > 0) {
                int maxOutflow = Integer.MIN_VALUE;
                
                // find the set of unprocessed node (=> mark == 0), with the largest out flow
                for (LNode node : nodes) {
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
                
                // randomly select a node from the ones with maximal outflow
                LNode maxNode = maxNodes.get(random.nextInt(maxNodes.size()));
                mark[maxNode.id] = nextLeft++;
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
        for (LNode node : nodes) {
            for (LPort port : node.getPorts()) {
                LEdge[] outgoingEdges = port.getOutgoingEdges().toArray(new LEdge[0]);
                
                // look at the node's outgoing edges
                for (LEdge edge : outgoingEdges) {
                    int targetIx = edge.getTarget().getNode().id;
                    if (mark[node.id] > mark[targetIx]) {
                        // In theory, this could create new collector ports, leading to a concurrent
                        // modification exception due to the iteration over the list of ports.
                        // However, this will not happen here, because edges are only reversed for
                        // nodes that are part of a cycle and thus already have both an input and
                        // an output collector port.
                        edge.reverse(true);
                        layeredGraph.setProperty(Properties.CYCLIC, true);
                    }
                }                
            }
        }

        dispose();
        getMonitor().done();
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
    private void updateNeighbors(final LNode node) {
        for (LPort port : node.getPorts()) {
            for (LEdge edge : port.getConnectedEdges()) {
                LPort connectedPort = edge.getSource() == port ? edge.getTarget() : edge.getSource();
                LNode endpoint = connectedPort.getNode();
                
                // exclude self-loops
                if (node == endpoint) {
                    continue;
                }
                
                int priority = edge.getProperty(Properties.PRIORITY);
                if (priority < 0) {
                    priority = 0;
                }
                int index = endpoint.id;
                if (mark[index] == 0) {
                    if (edge.getTarget() == connectedPort) {
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
        }
    }
    
}
