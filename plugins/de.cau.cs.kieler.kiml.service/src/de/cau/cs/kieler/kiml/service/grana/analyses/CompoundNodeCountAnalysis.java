/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kiml.service.grana.analyses;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.service.grana.IAnalysis;

/**
 * A graph analysis that counts the number of compound nodes. Returns a single-component
 * result {@code (int compoundNodes)}.
 * 
 * @author cds
 * @kieler.rating proposed yellow 2012-07-10 msp
 */
public class CompoundNodeCountAnalysis implements IAnalysis {
    
    /**
     * Identifier of the compound node count analysis.
     */
    public static final String ID = "de.cau.cs.kieler.kiml.grana.compoundNodeCount";

    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final KNode parentNode, final Map<String, Object> results,
            final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Compound Nodes Analysis", 1);
        
        int compoundNodes = 0;
        
        // Iterate through the graph using a queue of nodes discovered
        List<KNode> nodeQueue = new LinkedList<KNode>();
        nodeQueue.add(parentNode);
        
        while (nodeQueue.size() > 0) {
            KNode node = nodeQueue.remove(0);
            
            // Increase the compound node count if this node has children
            EList<KNode> children = node.getChildren();
            if (!children.isEmpty()) {
                compoundNodes++;
                nodeQueue.addAll(children);
            }
        }
        
        // The graph root shouldn't count
        compoundNodes = Math.max(0, compoundNodes - 1);
        
        progressMonitor.done();
        return compoundNodes;
    }
}
