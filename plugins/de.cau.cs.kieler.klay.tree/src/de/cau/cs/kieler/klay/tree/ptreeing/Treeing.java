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

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
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
    private static final IntermediateProcessingConfiguration INTERMEDIATE_PROCESSING_CONFIGURATION = new IntermediateProcessingConfiguration(
            null, null);

    /**
     * {@inheritDoc}
     */
    public void process(TGraph tGraph, IKielerProgressMonitor progressMonitor) {

        progressMonitor.begin("Treeing phase", 1);

        this.tGraph = tGraph;
        //TODO uncomment
        //collectEdges(tGraph);

        progressMonitor.done();
    }

    /**
     * {@inheritDoc}
     */
    public IntermediateProcessingConfiguration getIntermediateProcessingConfiguration(TGraph tGraph) {

        return INTERMEDIATE_PROCESSING_CONFIGURATION;
    }

    private int[] checked;

    private boolean[] visited;

    List<TEdge> eliminated;

    private TGraph tGraph;

    private void init(TGraph tGraph) {

        int size = tGraph.getNodes().size();
        eliminated = new LinkedList<TEdge>();
        checked = new int[size];
        visited = new boolean[size];
        Arrays.fill(visited, false);
        Arrays.fill(checked, 0);
    }

    /**
     * Create a list with the edges to remove. These edges should be all edges that destroy the
     * tree-property.
     * 
     * @param TGraph
     *            where to collect the edges
     */
    private void collectEdges(TGraph tGraph) {

        init(tGraph);
        // start DFS on every node in graph
        for (TNode tNode : tGraph.getNodes()) {
            // initial condition: all nodes have checked value of 0, which defines 'unvisited'
            if (checked[tNode.id] == 0) {
                checked[tNode.id] = 1;
                dfs(tNode, tGraph);
            }
            // if a node has already been visited
            if (checked[tNode.id] == 1) {
                checked[tNode.id] = 2;

                // add all incoming edges of that node to a list
                for (TEdge e : tNode.getInComingEdges()) {
                    eliminated.add(e);
                    // and set source and target of the edge to null
                    e.setSource(null);
                    e.setTarget(null);
                }
                dfs(tNode, tGraph);
            }
            // set the list containing bad edges as graph property
            tGraph.setProperty(Properties.REMOVABLE_EDGES, eliminated);
        }
        for (int i = 0; i < eliminated.size(); i++) {
            System.out.println("ELIMINATED!");
            System.out.println(eliminated.get(i));
        }
    }

    /**
     * This method performs a DFS on a given graph till all nodes of the graph have been visited
     * 
     * @param node
     *            to start DFS
     * @param graph
     *            to start DFS
     */
    private void dfs(TNode tNode, TGraph tGraph) {

        if (visited[tNode.id] == false) {
            visited[tNode.id] = true;
            for (TEdge e : tGraph.getEdges()) {
                TNode node = e.getTarget();
                dfs(node, tGraph);
            }
        }
    }
}
