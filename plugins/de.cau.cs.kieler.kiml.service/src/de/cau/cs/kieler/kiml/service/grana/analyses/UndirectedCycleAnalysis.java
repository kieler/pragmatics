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

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Iterables;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.service.grana.AnalysisOptions;
import de.cau.cs.kieler.kiml.service.grana.IAnalysis;

/**
 * Analysis for approximate undirected cycle count. If the number is zero, the undirected graph is
 * acyclic, otherwise it contains cycles.
 *
 * @author msp
 */
public class UndirectedCycleAnalysis implements IAnalysis {
    
    /** the identifier of the undirected cycles analysis. */
    public static final String ID = "de.cau.cs.kieler.kiml.grana.undirectedCycles";

    /** map of DFS marks determined by the analysis. */
    private Map<KGraphElement, Integer> dfsMark = new HashMap<KGraphElement, Integer>();
    
    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final KNode parentNode, final Map<String, Object> results,
            final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Approximate undirected cycle count", 1);
        
        boolean hierarchy = parentNode.getData(KShapeLayout.class).getProperty(
                AnalysisOptions.ANALYZE_HIERARCHY);
        
        int cycleCount = 0;
        List<KNode> nodeQueue = new LinkedList<KNode>();
        nodeQueue.addAll(parentNode.getChildren());
        while (!nodeQueue.isEmpty()) {
            KNode node = nodeQueue.remove(0);
            
            if (!dfsMark.containsKey(node)) {
                cycleCount += dfs(node, hierarchy);
            }
            
            if (hierarchy) {
                nodeQueue.addAll(node.getChildren());
            }
        }
        
        dfsMark.clear();
        progressMonitor.done();
        return cycleCount;
    }
    
    /**
     * Perform a DFS on the given node.
     * 
     * @param node the starting node
     * @param parentEdge the edge by which the node was reached, or null
     * @param hierarchy whether hierarchy edges shall be considered
     * @return the number of undirected cycles that were found
     */
    private int dfs(final KNode node, final boolean hierarchy) {
        dfsMark.put(node, 1);
        int backEdgeCount = 0;
        for (KEdge edge : Iterables.concat(node.getOutgoingEdges(), node.getIncomingEdges())) {
            if (!dfsMark.containsKey(edge)
                    && (hierarchy || edge.getSource().getParent() == edge.getTarget().getParent())) {
                dfsMark.put(edge, 1);
                KNode endpoint = edge.getTarget() == node ? edge.getSource() : edge.getTarget();
                Integer mark = dfsMark.get(endpoint);
                if (mark == null) {
                    // the endpoint has not been visited yet
                    backEdgeCount += dfs(endpoint, hierarchy);
                } else {
                    // the endpoint has already been visited - cycle found
                    backEdgeCount++;
                }
            }
        }
        return backEdgeCount;
    }

}
