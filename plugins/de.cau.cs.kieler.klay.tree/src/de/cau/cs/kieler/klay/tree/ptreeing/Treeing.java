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
package de.cau.cs.kieler.klay.tree.ptreeing;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.klay.tree.ILayoutPhase;
import de.cau.cs.kieler.klay.tree.IntermediateProcessingConfiguration;
import de.cau.cs.kieler.klay.tree.graph.TEdge;
import de.cau.cs.kieler.klay.tree.graph.TGraph;
import de.cau.cs.kieler.klay.tree.graph.TNode;
import de.cau.cs.kieler.klay.tree.intermediate.LayoutProcessorStrategy;
import de.cau.cs.kieler.klay.tree.properties.Properties;

/**
 * This phase should create a tree, if a graph is hardly a tree.
 * 
 * It should for example look for cycles in a tree and remove that cycles. This cycle breaking is
 * done through eliminating the edge that destroys the tree property and putting that edge into a
 * list. So this phase builds up a list with removed edges, so that the KLay Tree algorithm can
 * operate on the newly constructed graph which is now a tree. The previously removed edges could be
 * reinserted during a post-processing.
 * 
 * @author sor
 * @author sgu
 */
public class Treeing implements ILayoutPhase {

    /** intermediate processing configuration. */
    // first phase gets no intermediateProcessingConfiguration?!
    private static final IntermediateProcessingConfiguration INTERMEDIATE_PROCESSING_CONFIGURATION = 
            new IntermediateProcessingConfiguration(IntermediateProcessingConfiguration.AFTER_PHASE_4,LayoutProcessorStrategy.DETREEIFYING_PROC);

    /**
     * {@inheritDoc}
     */
    public void process(TGraph theGraph, IKielerProgressMonitor progressMonitor) {

        progressMonitor.begin("Treeing phase", 1);

        this.tGraph = theGraph;
        init(tGraph);
        collectEdges(tGraph);

        progressMonitor.done();
    }

    /**
     * {@inheritDoc}
     */
    public IntermediateProcessingConfiguration getIntermediateProcessingConfiguration(TGraph tGraph) {

        return INTERMEDIATE_PROCESSING_CONFIGURATION;
    }

    private int[] visited;

    private List<TEdge> eliminated;

    private TGraph tGraph;

    /**
     * TODO comment
     * 
     * @param tGraph
     */
    private void init(TGraph tGraph) {

        int size = tGraph.getNodes().size();
        eliminated = new LinkedList<TEdge>();
        visited = new int[size];

        // initialize Map with 0 at every node value
        int id = 0;
        for (TNode node : tGraph.getNodes()) {
            node.id = id++;
        }
    }

    /**
     * Create a list with the edges to remove. These edges should be all edges that destroy the
     * tree-property. For now these are all edges that conclude circles, so the treeing will work
     * for graphs that contain one cycle.
     * 
     * @param TGraph
     *            where to collect the edges
     */
    private void collectEdges(TGraph tGraph) {

        // start DFS on every node in graph
        for (TNode tNode : tGraph.getNodes()) {
            List<TEdge> tmp;
            // delete multiple incoming/ outgoing edges except one first
            // if (tNode.getInComingEdges().size() > 1) {
            // tmp = tNode.getInComingEdges().subList(0, tNode.getInComingEdges().size() - 1);
            // for (Iterator<TEdge> tmpIt = tmp.iterator(); tmpIt.hasNext();) {
            // TEdge tEdge = (TEdge) tmpIt.next();
            // tmpIt.remove();
            // tEdge.setSource(null);
            // tEdge.setTarget(null);
            // }
            // eliminated.addAll(tmp);
            // }
            //
            // if (tNode.getOutgoingEdges().size() > 1) {
            // tmp = tNode.getOutgoingEdges().subList(0, tNode.getOutgoingEdges().size() - 1);
            // for (Iterator<TEdge> tmpIt = tmp.iterator(); tmpIt.hasNext();) {
            // TEdge tEdge = (TEdge) tmpIt.next();
            // tmpIt.remove();
            // tEdge.setSource(null);
            // tEdge.setTarget(null);
            // }
            // eliminated.addAll(tmp);
            // }
            // initial condition: all nodes have checked value of 0, which defines 'unvisited'
            if (visited[tNode.id] == 0) {
                // if a unvisited node gets visited, mark this node with a 1

                // look what nodes can be found from that node on
                dfs(tNode);
                visited[tNode.id]=2;
            }
//            // if a node has already been visited
//            if (checkedMap.get(tNode) == 1 && visited[tNode.id] != null) {
//                // mark that node as visited twice
//                checkedMap.put(tNode, 2);
//                if (tNode.getInComingEdges().size() > 0) {
//                    // and add all incoming edges of that node to a list
//                    for (TEdge e : tNode.getInComingEdges()) {
//                        if (!eliminated.contains(e)) {
//                            eliminated.add(e);
//                            System.out.println("Added: " + e);
//                            e.setSource(null);
//                            e.setTarget(null);
//
//                            System.out.println("Number of elements in List: " + eliminated.size());
//                        }
//                    }
//                } else {
//                    for (Iterator<TEdge> it = tNode.getOutgoingEdges().iterator(); it.hasNext();) {
//                        TEdge e = (TEdge) it.next();
//                        if (!eliminated.contains(e)) {
//                            eliminated.add(e);
//                            it.remove();
//                            System.out.println("Added: " + e);
//                            e.setSource(null);
//                            e.setTarget(null);
//                        }
//                    }
//                }
//                // recurse to handle all nodes in the graph
//                dfs(tNode);
//            }
        }
        for (TEdge tEdge : eliminated) {
            tEdge.getSource().getOutgoingEdges().remove(tEdge);
            tEdge.getTarget().getInComingEdges().remove(tEdge);
        }
        // set the list of collected edges as a graph property
        tGraph.setProperty(Properties.REMOVABLE_EDGES, eliminated);
    }

    /**
     * This method performs a DFS on a given graph till all nodes of the graph have been visited
     * 
     * @param node
     *            to start DFS
     * @param graph
     *            to perform DFS in
     */
    private void dfs(TNode tNode) {
        visited[tNode.id] = 1;

        for (TEdge tEdge : tNode.getOutgoingEdges()) {
            TNode target = tEdge.getTarget();
            if (visited[target.id] == 1) {
                eliminated.add(tEdge);
            } else {
                if (visited[target.id] == 2) {
                    visited[target.id] = 1;
                } else {
                    dfs(target);
                }
            }
        }
    }

}
