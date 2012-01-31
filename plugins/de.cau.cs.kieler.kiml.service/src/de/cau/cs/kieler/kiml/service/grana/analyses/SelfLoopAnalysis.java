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

import java.util.Map;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.service.grana.IAnalysis;


/**
 * A graph analysis that gives the number of self loops in the graph. Returns a
 * single-component result {@code (int selfLoops)}.
 * 
 * This analysis does not yet take into account indirect self loops, e.g. paths
 * from a node to itself that only contain hyper nodes. It is not clear if these
 * kinds of self loops should be included or not.
 * 
 * @author cds
 */
public class SelfLoopAnalysis implements IAnalysis {

    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final KNode parentNode, final Map<String, Object> results,
            final IKielerProgressMonitor progressMonitor) {
        
        progressMonitor.begin("Self Loop Analysis", 1);
        int selfLoops = countSelfLoops(parentNode);
        progressMonitor.done();
        
        return selfLoops;
    }
    
    /**
     * Recursively goes through the graph, counting the number of self loops.
     * 
     * @param node the graph's root node.
     * @return the number of self loops found.
     */
    private int countSelfLoops(final KNode node) {
        int selfLoops = 0;
        
        // Count this node's self loops
        for (KEdge edge : node.getOutgoingEdges()) {
            if (edge.getTarget().equals(node)) {
                selfLoops++;
            }
        }
        
        // Recursively count the childrens' self loops
        for (KNode child : node.getChildren()) {
            selfLoops += countSelfLoops(child);
        }
        
        return selfLoops;
    }

}
