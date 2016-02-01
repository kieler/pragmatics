/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kiml.grana.analyses;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.common.util.EList;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.grana.AnalysisContext;
import de.cau.cs.kieler.kiml.grana.IAnalysis;

/**
 * A graph analysis that counts the number of contained nodes per hierarchy level. The result
 * has three components for the minimum, average, and maximum value, respectively.
 * 
 * @author cds
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow 2012-07-10 msp
 */
public class CompoundNodeChildCountAnalysis implements IAnalysis {

    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final KNode parentNode, final AnalysisContext context,
            final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Compound node child count analysis", 1);
        
        int minimum = Integer.MAX_VALUE;
        int maximum = Integer.MIN_VALUE;
        int childNodes = 0;
        int compoundNodes = 0;
        
        // Iterate through the graph using a queue of nodes discovered
        List<KNode> nodeQueue = new LinkedList<KNode>();
        nodeQueue.addAll(parentNode.getChildren());
        
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
