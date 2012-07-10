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
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.service.grana.AnalysisOptions;
import de.cau.cs.kieler.kiml.service.grana.IAnalysis;

/**
 * A graph analysis that computes the number of edges in the given graph.
 * 
 * @author mri
 * @kieler.rating 2012-07-10 proposed yellow msp
 */
public class EdgeCountAnalysis implements IAnalysis {
    
    /** the identifier of the edge count analysis. */
    public static final String ID = "de.cau.cs.kieler.kiml.grana.edgeCount";

    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final KNode parentNode, final Map<String, Object> results,
            final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Number of edges analysis", 1);
        
        boolean hierarchy = parentNode.getData(KShapeLayout.class).getProperty(
                AnalysisOptions.ANALYZE_HIERARCHY);
        
        int numberOfEdges = 0;
        List<KNode> nodeQueue = new LinkedList<KNode>();
        nodeQueue.addAll(parentNode.getChildren());
        while (nodeQueue.size() > 0) {
            // pop first element
            KNode node = nodeQueue.remove(0);
            
            if (hierarchy) {
                numberOfEdges += node.getOutgoingEdges().size();
                nodeQueue.addAll(node.getChildren());
            } else {
                for (KEdge edge : node.getOutgoingEdges()) {
                    if (edge.getTarget().getParent() == parentNode) {
                        numberOfEdges++;
                    }
                }
            }
        }

        progressMonitor.done();
        return numberOfEdges;
    }

}
