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

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkGraphElement;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.util.ElkGraphUtil;

import de.cau.cs.kieler.grana.AnalysisContext;
import de.cau.cs.kieler.grana.AnalysisOptions;
import de.cau.cs.kieler.grana.IAnalysis;

/**
 * Analysis for approximate undirected cycle count. If the number is zero, the undirected graph is
 * acyclic, otherwise it contains cycles.
 *
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow 2012-07-10 msp
 */
public class UndirectedCycleAnalysis implements IAnalysis {
    
    /** the identifier of the undirected cycles analysis. */
    public static final String ID = "de.cau.cs.kieler.grana.undirectedCycles";

    /** map of DFS marks determined by the analysis. */
    private Map<ElkGraphElement, Integer> dfsMark = new HashMap<ElkGraphElement, Integer>();
    
    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final ElkNode parentNode, final AnalysisContext context,
            final IElkProgressMonitor progressMonitor) {
        progressMonitor.begin("Approximate undirected cycle count", 1);
        
        boolean hierarchy = parentNode.getProperty(AnalysisOptions.ANALYZE_HIERARCHY);
        
        int cycleCount = 0;
        List<ElkNode> nodeQueue = new LinkedList<ElkNode>();
        nodeQueue.addAll(parentNode.getChildren());
        while (!nodeQueue.isEmpty()) {
            ElkNode node = nodeQueue.remove(0);
            
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
    private int dfs(final ElkNode node, final boolean hierarchy) {
        dfsMark.put(node, 1);
        int backEdgeCount = 0;
        for (ElkEdge edge : ElkGraphUtil.allIncidentEdges(node)) {
            ElkNode source = ElkGraphUtil.getSourceNode(edge);
            ElkNode target = ElkGraphUtil.getTargetNode(edge);
            if (!dfsMark.containsKey(edge)
                    && (hierarchy || source.getParent() == target.getParent())) {
                dfsMark.put(edge, 1);
                ElkNode endpoint = target == node ? source : target;
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
