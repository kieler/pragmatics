/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klodd.orthogonal.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.slimgraph.KSlimGraph;
import de.cau.cs.kieler.core.slimgraph.KGraphSection;
import de.cau.cs.kieler.core.slimgraph.KSlimNode;

/**
 * Algorithm that determines the biconnected components of a graph with a DFS.
 * 
 * @author msp
 */
public class BiconnectedComponents extends AbstractAlgorithm {

    /** list of biconnected components calculated by the algorithm. */
    private List<KGraphSection> components = new LinkedList<KGraphSection>();
    /** next DFS number to assign. */
    private int nextDfsnum = 0;
    /** list of lowest point numbers. */
    private int[] lowpt;
    /** list of parent nodes. */
    private KSlimNode[] parent;
    /** stack with unfinished nodes. */
    private Stack<KSlimNode> unfinished = new Stack<KSlimNode>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        super.reset();
        components.clear();
        unfinished.clear();
        nextDfsnum = 0;
    }

    /**
     * Calculates the biconnected components of the given graph. The input graph
     * is considered as undirected.
     * 
     * @param graph graph to be processed
     * @return list of biconnected components
     */
    public List<KGraphSection> findComponents(final KSlimGraph graph) {
        // initialize DFS variables
        int graphSize = graph.getNodes().size();
        lowpt = new int[graphSize];
        parent = new KSlimNode[graphSize];
        for (KSlimNode node : graph.getNodes()) {
            node.setRank(-1);
        }

        // perform DFS on all nodes of the graph
        for (KSlimNode node : graph.getNodes()) {
            if (node.getRank() < 0) {
                dfsVisit(node);
            }
        }

        return components;
    }

    /**
     * Performs a DFS starting at the given node.
     * 
     * @param node node to visit
     */
    private void dfsVisit(final KSlimNode node) {
        node.setRank(nextDfsnum++);
        lowpt[node.getId()] = node.getRank();
        unfinished.push(node);
        for (KSlimNode.IncEntry edgeEntry : node.getIncidence()) {
            KSlimNode endpoint = edgeEntry.endpoint();
            if (endpoint.getRank() < 0) {
                parent[endpoint.getId()] = node;
                dfsVisit(endpoint);
                lowpt[node.getId()] = Math.min(lowpt[node.getId()], lowpt[endpoint.getId()]);
            } else {
                lowpt[node.getId()] = Math.min(lowpt[node.getId()], endpoint.getRank());
            }
        }
        if (node.getRank() >= 2 && lowpt[node.getId()] == parent[node.getId()].getRank()) {
            KGraphSection graphSection = new KGraphSection();
            KSlimNode sectionNode;
            do {
                sectionNode = unfinished.pop();
                graphSection.getNodes().add(sectionNode);
            } while (sectionNode != node);
            graphSection.getNodes().add(parent[node.getId()]);
            graphSection.sortNodes();
            components.add(graphSection);
        }
    }

}
