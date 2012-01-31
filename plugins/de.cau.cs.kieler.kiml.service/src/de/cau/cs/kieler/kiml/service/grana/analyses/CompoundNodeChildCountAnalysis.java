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
 */
public class CompoundNodeChildCountAnalysis implements IAnalysis {

    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final KNode parentNode, final Map<String, Object> results,
            final IKielerProgressMonitor progressMonitor) {
        
        progressMonitor.begin("Compound Node Child Count Analysis", 1);
        
        int minimum = Integer.MAX_VALUE;
        int maximum = Integer.MIN_VALUE;
        int childNodes = 0;
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
                
                childNodes += children.size();
                minimum = Math.min(minimum, children.size());
                maximum = Math.max(maximum, children.size());
            }
        }
        
        // The graph root shouldn't count
        compoundNodes = Math.max(0, compoundNodes - 1);
        if (compoundNodes == 0) {
            minimum = 0;
            maximum = 0;
        }
        
        // Calculate the average
        float avg = compoundNodes == 0 ? 0.0f : ((float) childNodes) / compoundNodes;
        
        progressMonitor.done();
        return new Object[] {minimum, avg, maximum};
    }
}
