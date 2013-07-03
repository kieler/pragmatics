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
 * @author sor
 * @author sgu
 * 
 */
public class Treeing implements ILayoutPhase {

    /** intermediate processing configuration. */
    // first phase gets no intermediateProcessingConfiguration?!
    private static final IntermediateProcessingConfiguration INTERMEDIATE_PROCESSING_CONFIGURATION = 
            new IntermediateProcessingConfiguration(null, null);

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
     * tree-property. For now these are all edges that conclude circles, so the treeing will
     * work for graphs that contain one cycle.
     * 
     * @param TGraph
     *            where to collect the edges
     */
    private void collectEdges(TGraph tGraph) {

        // start DFS on every node in graph
        for (TNode tNode : tGraph.getNodes() ) {
            if (tNode.getInComingEdges().size() > 1) {
                for (int i = 1; i < tNode.getInComingEdges().size()-1; i++) {
                    eliminated.add(tNode.getInComingEdges().get(i));
                    tNode.getInComingEdges().get(i).setSource(null);
                    tNode.getInComingEdges().get(i).setTarget(null);
                }
            }
            if (tNode.getOutgoingEdges().size() > 1) {
                for (int i = 1; i < tNode.getOutgoingEdges().size()-1; i++) {
                    eliminated.add(tNode.getOutgoingEdges().get(i));
                    tNode.getOutgoingEdges().get(i).setSource(null);
                    tNode.getOutgoingEdges().get(i).setTarget(null);
                }
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
                }
                else {
                    for (TEdge e : tNode.getOutgoingEdges()) {
                        if (!eliminated.contains(e)) {
                            eliminated.add(e);
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
                TNode node = (TNode)iterator.next();
                if (node != null) {
                    dfs(node, tGraph);
                }
            }
        }
    }
}
