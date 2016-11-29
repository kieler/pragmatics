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

import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkNode;

import de.cau.cs.kieler.kiml.grana.AnalysisContext;
import de.cau.cs.kieler.kiml.grana.AnalysisOptions;
import de.cau.cs.kieler.kiml.grana.IAnalysis;

/**
 * A graph analysis that computes the number of edges in the given graph.
 * 
 * @author mri
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow 2012-07-10 msp
 */
public class EdgeCountAnalysis implements IAnalysis {
    
    /** the identifier of the edge count analysis. */
    public static final String ID = "de.cau.cs.kieler.kiml.grana.edgeCount";

    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final ElkNode parentNode, final AnalysisContext context,
            final IElkProgressMonitor progressMonitor) {
        progressMonitor.begin("Number of edges analysis", 1);
        
        boolean hierarchy = parentNode.getProperty(AnalysisOptions.ANALYZE_HIERARCHY);
        
        int numberOfEdges = 0;
        List<ElkNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(parentNode);
        while (nodeQueue.size() > 0) {
            // pop first element
            ElkNode node = nodeQueue.remove(0);
            
            if (hierarchy) {
                numberOfEdges += node.getContainedEdges().size();
                nodeQueue.addAll(node.getChildren());
            } else {
                for (ElkEdge edge : node.getContainedEdges()) {
                    if (!edge.isHierarchical()) {
                        numberOfEdges++;
                    }
                }
            }
        }

        progressMonitor.done();
        return numberOfEdges;
    }

}
