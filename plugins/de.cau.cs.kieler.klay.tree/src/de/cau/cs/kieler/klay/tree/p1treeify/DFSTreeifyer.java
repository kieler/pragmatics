/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.tree.p1treeify;

import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.klay.tree.ILayoutPhase;
import de.cau.cs.kieler.klay.tree.IntermediateProcessingConfiguration;
import de.cau.cs.kieler.klay.tree.graph.TEdge;
import de.cau.cs.kieler.klay.tree.graph.TGraph;
import de.cau.cs.kieler.klay.tree.graph.TNode;
import de.cau.cs.kieler.klay.tree.intermediate.LayoutProcessorStrategy;
import de.cau.cs.kieler.klay.tree.properties.Properties;

/**
 * This phase should create a tree out of a graph that is hardly a tree.
 * 
 * It should for example look for cycles in a tree and remove that cycles. This cycle breaking is
 * done through eliminating the edge that destroys the tree property and putting that edge into a
 * list. So this phase builds up a list with removed edges, so that the KLay Tree algorithm can
 * operate on the newly constructed graph which is now a tree. The previously removed edges could be
 * reinserted during a post-processing.
 * 
 * @author sor
 * @author sgu
 * @author msp
 */
public class DFSTreeifyer implements ILayoutPhase {

    /** intermediate processing configuration. */
    private static final IntermediateProcessingConfiguration INTERMEDIATE_PROCESSING_CONFIGURATION = 
            new IntermediateProcessingConfiguration(IntermediateProcessingConfiguration.AFTER_PHASE_4,
                    LayoutProcessorStrategy.DETREEIFYING_PROC);

    /**
     * {@inheritDoc}
     */
    public void process(final TGraph tGraph, final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("DFS Treeifying phase", 1);

        init(tGraph);
        collectEdges(tGraph);

        eliminated = null;
        visited = null;
        progressMonitor.done();
    }

    /**
     * {@inheritDoc}
     */
    public IntermediateProcessingConfiguration getIntermediateProcessingConfiguration(
            final TGraph tGraph) {
        return INTERMEDIATE_PROCESSING_CONFIGURATION;
    }

    /** marks the nodes that have been visited during DFS. */
    private int[] visited;

    /** a list with the edges that can be removed. */
    private List<TEdge> eliminated;

    /**
     * Initializes all previously defined structures of a TGraph with useful values.
     * 
     * @param tGraph
     */
    private void init(final TGraph tGraph) {

        int size = tGraph.getNodes().size();
        eliminated = new LinkedList<TEdge>();
        visited = new int[size];

        /** for using id property initialize ids first */
        int id = 0;
        for (TNode node : tGraph.getNodes()) {
            node.id = id++;
        }
    }

    /**
     * Create a list with the edges to remove. These edges should be all edges that destroy the
     * tree-property. 
     * 
     * @param TGraph
     *            where to collect the edges
     */
    private void collectEdges(final TGraph tGraph) {

        // start DFS on every node in graph
        for (TNode tNode : tGraph.getNodes()) {
            // if the node has not been visited yet
            if (visited[tNode.id] == 0) {
                // call DFS on that node
                dfs(tNode);
                // if we come back to that node again, set the node as root
                visited[tNode.id] = 2;
            }
        }
        // remove the found edges out of graph structure
        for (TEdge tEdge : eliminated) {
            tEdge.getSource().getOutgoingEdges().remove(tEdge);
            tEdge.getTarget().getIncomingEdges().remove(tEdge);
        }
        // set the list of collected edges as a graph property
        tGraph.setProperty(Properties.REMOVABLE_EDGES, eliminated);
    }

    /**
     * This method performs a DFS on a given graph till all nodes of the graph have been visited.
     * 
     * @param node
     *            to start DFS
     */
    private void dfs(final TNode tNode) {
        // dfs starts on a node and marks that node as visited
        visited[tNode.id] = 1;

        // go to all child nodes of start node
        for (TEdge tEdge : tNode.getOutgoingEdges()) {
            TNode target = tEdge.getTarget();
            // if a child has been visited
            if (visited[target.id] == 1) {
                // put that edge to the list that contains the edges to remove
                eliminated.add(tEdge);
            } else {
                // if a previous root can be visited from another node unmark the root property
                if (visited[target.id] == 2) {
                    visited[target.id] = 1;
                } else {
                    // recurse
                    dfs(target);
                }
            }
        }
    }
}
