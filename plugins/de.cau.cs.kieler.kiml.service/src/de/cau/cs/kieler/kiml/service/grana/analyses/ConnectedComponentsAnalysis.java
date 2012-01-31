/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kiml.service.grana.analyses;

import java.util.HashMap;
import java.util.Map;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.service.grana.IAnalysis;

/**
 * A graph analysis that finds the number of connected components in a graph. Returns
 * a single-component result of type integer.
 * 
 * @author msp
 */
public class ConnectedComponentsAnalysis implements IAnalysis {

    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final KNode parentNode, final Map<String, Object> results,
            final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Connected Components Analysis", 1);
        
        int count = countComponents(parentNode);
        
        visitedMap.clear();
        progressMonitor.done();
        return count;
    }
    
    /**
     * Count the number of components in the given node.
     * 
     * @param parent a parent node
     * @return the number of components that are children of the node
     */
    private int countComponents(final KNode parent) {
        int count = 0;
        for (KNode child : parent.getChildren()) {
            count += dfs(child);
            count += countComponents(child);
        }
        return count;
    }
    
    /** map of nodes to visited states. */
    private Map<KNode, Boolean> visitedMap = new HashMap<KNode, Boolean>();
    
    /**
     * Perform a DFS starting with the given node.
     * 
     * @param node a node
     * @return 1 if the node is part of a new connected component, 0 otherwise
     */
    private int dfs(final KNode node) {
        Boolean visited = visitedMap.get(node);
        if (visited == null) {
            visitedMap.put(node, true);
            for (KEdge edge : node.getOutgoingEdges()) {
                KNode target = edge.getTarget();
                if (node.getParent() == target.getParent()) {
                    dfs(target);
                }
            }
            for (KEdge edge : node.getIncomingEdges()) {
                KNode source = edge.getSource();
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
