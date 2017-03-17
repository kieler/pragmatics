/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
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

import de.cau.cs.kieler.grana.AnalysisContext;
import de.cau.cs.kieler.grana.AnalysisOptions;
import de.cau.cs.kieler.grana.IAnalysis;

/**
 * A graph analysis that finds the number of connected components in a graph. Returns
 * a single-component result of type integer. 
 * 
 * <b>Warning:</b> 
 * The content of hierarchical nodes is considered as a separate connected component. 
 * 
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow 2012-07-10 msp
 */
public class ConnectedComponentsAnalysis implements IAnalysis {

    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final ElkNode parentNode, final AnalysisContext context,
            final IElkProgressMonitor progressMonitor) {
        progressMonitor.begin("Connected Components Analysis", 1);
        
        boolean hierarchy = parentNode.getProperty(AnalysisOptions.ANALYZE_HIERARCHY);
        
        int count = countComponents(parentNode, hierarchy);
        
        visitedMap.clear();
        progressMonitor.done();
        return count;
    }
    
    /**
     * Count the number of components in the given node.
     * 
     * @param parent a parent node
     * @param hierarchy whether hierarchy shall be processed recursively
     * @return the number of components that are children of the node
     */
    private int countComponents(final ElkNode parent, final boolean hierarchy) {
        int count = 0;
        for (ElkNode child : parent.getChildren()) {
            count += dfs(child);
            if (hierarchy) {
                count += countComponents(child, true);
            }
        }
        return count;
    }
    
    /** map of nodes to visited states. */
    private Map<ElkNode, Boolean> visitedMap = new HashMap<ElkNode, Boolean>();
    
    /**
     * Perform a DFS starting with the given node.
     * 
     * @param node a node
     * @return 1 if the node is part of a new connected component, 0 otherwise
     */
    private int dfs(final ElkNode node) {
        Boolean visited = visitedMap.get(node);
        if (visited == null) {
            visitedMap.put(node, true);
            for (ElkEdge edge : ElkGraphUtil.allOutgoingEdges(node)) {
                ElkNode target = ElkGraphUtil.getTargetNode(edge);
                if (node.getParent() == target.getParent()) {
                    dfs(target);
                }
            }
            for (ElkEdge edge : ElkGraphUtil.allIncomingEdges(node)) {
                ElkNode source = ElkGraphUtil.getSourceNode(edge);
                if (node.getParent() == source.getParent()) {
                    dfs(source);
                }
            }
            return 1;
        } else {
            return 0;
        }
    }

}
