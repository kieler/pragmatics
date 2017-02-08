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

package de.cau.cs.kieler.kiml.grana.analyses;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.util.ElkGraphUtil;

import de.cau.cs.kieler.kiml.grana.AnalysisContext;
import de.cau.cs.kieler.kiml.grana.AnalysisOptions;
import de.cau.cs.kieler.kiml.grana.IAnalysis;

/**
 * A graph analysis that gives the number of self loops in the graph. Returns a
 * single-component result {@code (int selfLoops)}.
 * 
 * This analysis does not yet take into account indirect self loops, e.g. paths
 * from a node to itself that only contain hyper nodes. It is not clear if these
 * kinds of self loops should be included or not.
 * 
 * @author cds
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow 2012-07-10 msp
 */
public class SelfLoopAnalysis implements IAnalysis {
    
    /** identifier of the self-loops analysis. */
    public static final String ID = "de.cau.cs.kieler.kiml.grana.selfLoops";

    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final ElkNode parentNode, final AnalysisContext context,
            final IElkProgressMonitor progressMonitor) {
        progressMonitor.begin("Self-loop analysis", 1);
        
        boolean hierarchy = parentNode.getProperty(AnalysisOptions.ANALYZE_HIERARCHY);
        
        List<ElkNode> nodeQueue = new LinkedList<>();
        nodeQueue.addAll(parentNode.getChildren());
        int selfLoops = 0;
        while (!nodeQueue.isEmpty()) {
            ElkNode node = nodeQueue.remove(0);
            
            // Count this node's self loops
            for (ElkEdge edge : ElkGraphUtil.allOutgoingEdges(node)) {
                if (edge.isSelfloop()) {
                    selfLoops++;
                }
            }
            
            if (hierarchy) {
                nodeQueue.addAll(node.getChildren());
            }
        }
        
        progressMonitor.done();
        
        return selfLoops;
    }

}
