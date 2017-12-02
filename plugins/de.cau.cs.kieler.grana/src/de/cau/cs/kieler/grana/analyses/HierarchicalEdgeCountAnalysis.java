/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2017 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.grana.analyses;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.util.ElkGraphUtil;

import de.cau.cs.kieler.grana.AnalysisContext;
import de.cau.cs.kieler.grana.IAnalysis;

/**
 * Counts number of hierarchical edges.
 */
public class HierarchicalEdgeCountAnalysis implements IAnalysis {

    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final ElkNode parentNode, final AnalysisContext context,
            final IElkProgressMonitor progressMonitor) {
        progressMonitor.begin("Number of hierarchical edges analysis", 1);

        int numberShortHierarchicalEdges = 0;
        int numberLongHierarchicalEdges = 0;
        List<ElkNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(parentNode);
        while (nodeQueue.size() > 0) {
            // pop first element
            ElkNode node = nodeQueue.remove(0);

            for (ElkEdge edge : ElkGraphUtil.allOutgoingEdges(node)) {
                if (edge.isHierarchical()) {
                    if (isShortHierarchicalEdge(edge)) {
                        numberShortHierarchicalEdges++;
                    } else {
                        numberLongHierarchicalEdges++;
                    }
                }
            }
            nodeQueue.addAll(node.getChildren());
        }

        progressMonitor.done();
        return new Object[] { numberShortHierarchicalEdges, numberLongHierarchicalEdges,
                numberShortHierarchicalEdges + numberLongHierarchicalEdges };
    }

    private boolean isShortHierarchicalEdge(final ElkEdge edge) {
        ElkNode src = ElkGraphUtil.getSourceNode(edge);
        ElkNode tgt = ElkGraphUtil.getTargetNode(edge);
        return (src.getParent() != null && src.getParent() == tgt)
                || (tgt.getParent() != null && tgt.getParent() == src);
    }
}
