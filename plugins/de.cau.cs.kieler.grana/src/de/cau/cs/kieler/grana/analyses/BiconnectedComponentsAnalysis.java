/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.grana.analyses;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.util.ElkGraphUtil;

import com.google.common.collect.Iterables;

import de.cau.cs.kieler.grana.AnalysisContext;
import de.cau.cs.kieler.grana.AnalysisOptions;
import de.cau.cs.kieler.grana.IAnalysis;

/**
 * A graph analysis that finds the number of biconnected components in a graph. Returns
 * a single-component result of type integer.
 */
public class BiconnectedComponentsAnalysis implements IAnalysis {
    
    /** next DFS number to assign. */
    private int nextDfsnum;
    /** map of DFS numbers. */
    private Map<ElkNode, Integer> dfsMap = new HashMap<ElkNode, Integer>();
    /** lowest point numbers. */
    private int[] lowpt;
    /** parent node numbers. */
    private int[] parent;

    @Override
    public Object doAnalysis(final ElkNode parentNode, final AnalysisContext context,
            final IElkProgressMonitor progressMonitor) {
        
        progressMonitor.begin("Biconnected Components Analysis", 1);
        
        boolean hierarchy = parentNode.getProperty(AnalysisOptions.ANALYZE_HIERARCHY);
        int count = findComponents(parentNode, hierarchy);
        
        dfsMap.clear();
        lowpt = null;
        parent = null;
        
        progressMonitor.done();
        return count;
    }

    /**
     * Calculates the biconnected components of the given graph. The input graph
     * is considered as undirected.
     * 
     * @param graph graph to be processed
     * @param hierarchy whether the whole hierarchy shall be processed
     * @return number of biconnected components
     */
    public int findComponents(final ElkNode graph, final boolean hierarchy) {
        nextDfsnum = 1;
        int graphSize = graph.getChildren().size();
        lowpt = new int[graphSize + 1];
        parent = new int[graphSize + 1];
        int count = 0;
        for (ElkNode node : graph.getChildren()) {
            if (dfsMap.get(node) == null) {
                count += dfsVisit(node);
            }
        }
        
        // find components in the nested subgraphs
        if (hierarchy) {
            for (ElkNode node : graph.getChildren()) {
                if (!node.getChildren().isEmpty()) {
                    count += findComponents(node, true);
                }
            }
        }
        return count;
    }
    
    /**
     * Performs a DFS starting at the given node.
     * 
     * @param node node to visit
     * @param the number of biconnected components that were found
     */
    private int dfsVisit(final ElkNode node) {
        int dfsNum = nextDfsnum++;
        dfsMap.put(node, dfsNum);
        lowpt[dfsNum] = dfsNum;
        int count = 0;
        
        // follow the outgoing and incoming edges
        for (ElkEdge edge : Iterables.concat(node.getOutgoingEdges(), node.getIncomingEdges())) {
            ElkNode sourceNode = ElkGraphUtil.connectableShapeToNode(edge.getSources().get(0));
            ElkNode targetNode = ElkGraphUtil.connectableShapeToNode(edge.getTargets().get(0));
            ElkNode endpoint = sourceNode == node ? targetNode : sourceNode;
            
            if (endpoint.getParent() == node.getParent()) {
                Integer endpointNum = dfsMap.get(endpoint);
                if (endpointNum == null) {
                    endpointNum = nextDfsnum;
                    parent[endpointNum] = dfsNum;
                    count += dfsVisit(endpoint);
                    lowpt[dfsNum] = Math.min(lowpt[dfsNum], lowpt[endpointNum]);
                } else {
                    lowpt[dfsNum] = Math.min(lowpt[dfsNum], endpointNum);
                }
            }
        }
        
        // analyze the result
        if (dfsNum >= 2 && lowpt[dfsNum] == parent[dfsNum]) {
            count++;
        }
        return count;
    }

}
