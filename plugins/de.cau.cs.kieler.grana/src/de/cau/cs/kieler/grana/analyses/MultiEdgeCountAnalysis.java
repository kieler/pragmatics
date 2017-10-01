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

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.ElkConnectableShape;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.ElkPort;

import de.cau.cs.kieler.grana.AnalysisContext;
import de.cau.cs.kieler.grana.AnalysisOptions;
import de.cau.cs.kieler.grana.IAnalysis;

/**
 * A graph analysis that counts the number of multi edges. (edges with the same source and target)
 * Returns a single-component result {@code (int multiEdgeCount)}.
 * 
 * <p>To be precise, the value returned is computed as follows. Starting with zero, for each pair
 * of ports the result is incremented by the number of edges connecting them if that number is
 * bigger than 1.</p>
 * 
 * <p>Note that this analysis currently only processes simple edges. Hyperedges are ignored.</p>
 * 
 * @author cds
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow 2012-07-10 msp
 */
public class MultiEdgeCountAnalysis implements IAnalysis {
    
    /** identifier of the multi-edge count analysis. */
    public static final String ID = "de.cau.cs.kieler.grana.multiEdgeCount";
    
    /**
     * A comparator that compares edges based on their target.
     * 
     * @author cds
     */
    private static class ElkEdgeComparator implements Comparator<ElkEdge> {
        /**
         * {@inheritDoc}
         */
        public int compare(final ElkEdge e1, final ElkEdge e2) {
            ElkConnectableShape targetShape1 = e1.getTargets().get(0);
            ElkConnectableShape targetShape2 = e2.getTargets().get(0);
            
            if (targetShape1.equals(targetShape2)) {
                return 0;
            } else {
                return targetShape1.hashCode() < targetShape2.hashCode() ? -1 : 1;
            }
        }
    }
    
    
    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final ElkNode parentNode, final AnalysisContext context,
            final IElkProgressMonitor progressMonitor) {
        
        progressMonitor.begin("Multi Edge Count Analysis", 1);
        
        boolean hierarchy = parentNode.getProperty(AnalysisOptions.ANALYZE_HIERARCHY);
        
        int multiEdgeCount = 0;
        for (ElkNode node : parentNode.getChildren()) {
            multiEdgeCount += countMultiEdges(node, hierarchy);
        }
        
        progressMonitor.done();
        return multiEdgeCount;
    }
    
    /**
     * Counts the number of multi edges in the graph rooted at the given node.
     * 
     * @param node root of the graph to analyze.
     * @param hierarchy whether to process hierarchy recursively
     * @return the number of multi edges.
     */
    public int countMultiEdges(final ElkNode node, final boolean hierarchy) {
        int multiEdges = 0;
        
        multiEdges += doCountMultiEdges(node);
        for (ElkPort port : node.getPorts()) {
            multiEdges += doCountMultiEdges(port);
        }
        
        // Recurse if requested
        if (hierarchy) {
            for (ElkNode child : node.getChildren()) {
                multiEdges += countMultiEdges(child, true);
            }
        }
        
        return multiEdges;
    }
    
    private int doCountMultiEdges(final ElkConnectableShape sourceShape) {
        // Get the list of outgoing edges. Sort them by port, then by target.
        List<ElkEdge> edges = sourceShape.getOutgoingEdges().stream()
                .filter(edge -> !edge.isHyperedge())
                .sorted(new ElkEdgeComparator())
                .collect(Collectors.toList());
        
        // Go through the list of edges, incrementing the counter when an edge has the same target
        // shape as the last one
        ElkConnectableShape lastTargetShape = null;
        int edgesConnectingTwoShapes = 0;
        int multiEdges = 0;
        
        for (ElkEdge edge : edges) {
            if (edge.getTargets().get(0) == lastTargetShape) {
                edgesConnectingTwoShapes++;
            } else {
                // This is another pair of ports. Check if the last pair of ports
                // was connected by more than one edge
                if (edgesConnectingTwoShapes > 1) {
                    multiEdges += edgesConnectingTwoShapes;
                }
                
                edgesConnectingTwoShapes = 1;
            }
            
            lastTargetShape = edge.getTargets().get(0);
        }
        
        // Do a last check for the last target shape
        if (edgesConnectingTwoShapes > 1) {
            multiEdges += edgesConnectingTwoShapes;
        }
        
        return multiEdges;
    }

}
