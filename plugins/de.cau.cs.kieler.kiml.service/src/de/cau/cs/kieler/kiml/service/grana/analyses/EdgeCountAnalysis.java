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

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.service.grana.IAnalysis;

/**
 * A graph analysis that computes the number of edges in the given graph.
 * 
 * @author mri
 */
public class EdgeCountAnalysis implements IAnalysis {

    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final KNode parentNode, final Map<String, Object> results,
            final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Number of edges analysis", 1);
        int numberOfEdges = 0;
        List<KNode> nodeQueue = new LinkedList<KNode>();
        nodeQueue.add(parentNode);
        while (nodeQueue.size() > 0) {
            // pop first element
            KNode node = nodeQueue.remove(0);
            numberOfEdges += node.getOutgoingEdges().size();
            
            nodeQueue.addAll(node.getChildren());
        }

        progressMonitor.done();
        return numberOfEdges;
    }

}
