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
import de.cau.cs.kieler.klay.tree.properties.Properties;

/**
 * This phase should create a tree, if a graph is hardly a tree.
 * 
 * It should for example look for cycles in a tree and remove that cycles. This cycle breaking is done 
 * through eliminating the edge that destroys the tree property and putting that edge into a list.
 * So this phase builds up a list with removed edges, so that the KLay Tree algorithm can operate on the
 * newly constructed graph which is now a tree. The previously removed edges could be reinserted during
 * a post-processing.
 * 
 * @author sor
 * @author sgu
 */
public class Treeing implements ILayoutPhase {

    /** intermediate processing configuration. */
    // first phase gets no intermediateProcessingConfiguration?!
    private static final IntermediateProcessingConfiguration INTERMEDIATE_PROCESSING_CONFIGURATION = new IntermediateProcessingConfiguration(
            null, null);

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

    private Map<TNode, Integer> checkedMap = new HashMap<TNode, Integer>();

    private TNode[] visited;

    private List<TEdge> eliminated;

    private TGraph tGraph;

    private void init(TGraph tGraph) {

        int size = tGraph.getNodes().size();
        eliminated = new LinkedList<TEdge>();
        visited = new TNode[size];

        // initialize Map with 0 at every node value
        for (int i = 0; i < size; i++) {
            checkedMap.put(tGraph.getNodes().get(i), 0);
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
            if (tNode.getInComingEdges().size() > 1) {
                tmp = tNode.getInComingEdges().subList(0, tNode.getInComingEdges().size() - 1);
                for (Iterator<TEdge> tmpIt = tmp.iterator(); tmpIt.hasNext();) {
                    TEdge tEdge = (TEdge) tmpIt.next();
                    tmpIt.remove();
                    tEdge.setSource(null);
                    tEdge.setTarget(null);   
                }
                eliminated.addAll(tmp);
            }

            if (tNode.getOutgoingEdges().size() > 1) {
                tmp = tNode.getOutgoingEdges().subList(0, tNode.getOutgoingEdges().size() - 1);
                for (Iterator<TEdge> tmpIt = tmp.iterator(); tmpIt.hasNext();) {
                    TEdge tEdge = (TEdge) tmpIt.next();
                    tmpIt.remove();
                    tEdge.setSource(null);
                    tEdge.setTarget(null);   
                }
                eliminated.addAll(tmp);
            }
            // initial condition: all nodes have checked value of 0, which defines 'unvisited'
            if (checkedMap.get(tNode) == 0 && visited[tNode.id] == null) {
                // if a unvisited node gets visited, mark this node with a 1
                checkedMap.put(tNode, 1);
                // look what nodes can be found from that node on
                dfs(tNode, tGraph);
            }
            // if a node has already been visited
            if (checkedMap.get(tNode) == 1 && visited[tNode.id] != null) {
                // mark that node as visited twice
                checkedMap.put(tNode, 2);
                if (tNode.getInComingEdges().size() > 0) {
                    // and add all incoming edges of that node to a list
                    for (TEdge e : tNode.getInComingEdges()) {
                        if (!eliminated.contains(e)) {
                            eliminated.add(e);
                            System.out.println("Added: " + e);
                            e.setSource(null);
                            e.setTarget(null);

                            System.out.println("Number of elements in List: " + eliminated.size());
                        }
                    }
                } else {
                    for (Iterator<TEdge> it = tNode.getOutgoingEdges().iterator(); it.hasNext();) {
                        TEdge e = (TEdge) it.next();
                        if (!eliminated.contains(e)) {
                            eliminated.add(e);
                            it.remove();
                            System.out.println("Added: " + e);
                            e.setSource(null);
                            e.setTarget(null);
                        }
                    }
                }
                // recurse to handle all nodes in the graph
                dfs(tNode, tGraph);
            }
            // set the list of collected edges as a graph property
            tGraph.setProperty(Properties.REMOVABLE_EDGES, eliminated);
        }
    }

    /**
     * This method performs a DFS on a given graph till all nodes of the graph have been visited
     * 
     * @param node
     *            to start DFS
     * @param graph
     *            to perform DFS in
     */
    private void dfs(TNode tNode, TGraph tGraph) {

        if (visited[tNode.id] == null) {
            visited[tNode.id] = tNode;

            Iterator<TNode> iterator = tNode.getChildren().iterator();
            while (iterator.hasNext()) {
                TNode node = (TNode) iterator.next();
                if (node != null) {
                    dfs(node, tGraph);
                }
            }
        }
    }
}
