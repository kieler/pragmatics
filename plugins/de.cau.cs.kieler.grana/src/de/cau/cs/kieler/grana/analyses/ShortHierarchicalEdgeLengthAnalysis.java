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

import java.util.List;

import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.util.ElkGraphUtil;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.grana.AnalysisContext;
import de.cau.cs.kieler.grana.IAnalysis;
import de.cau.cs.kieler.grana.analyses.EdgeLengthAnalysis.EdgeLengthResults;

/**
 * Counts the edge length of short hierarchical edges only.
 */
public class ShortHierarchicalEdgeLengthAnalysis implements IAnalysis {

    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final ElkNode parentNode, final AnalysisContext context,
            final IElkProgressMonitor progressMonitor) {
        progressMonitor.begin("Short hierarchical edge length analysis", 1);

        List<ElkEdge> edgesToAnalyze = Lists.newArrayList();
        List<ElkNode> nodeQueue = Lists.newLinkedList();
        nodeQueue.addAll(parentNode.getChildren());
        while (nodeQueue.size() > 0) {
            // pop first element
            ElkNode node = nodeQueue.remove(0);
            // collect all outgoing edges
            for (ElkEdge edge : ElkGraphUtil.allOutgoingEdges(node)) {
                if (isShortHierarchicalEdge(edge)) {
                    edgesToAnalyze.add(edge);
                }
            }

            nodeQueue.addAll(node.getChildren());

        }

        EdgeLengthResults res = EdgeLengthAnalysis.analyzeEdgeLengths(edgesToAnalyze);
        progressMonitor.done();
        return new Object[] { res.min, res.average, res.max, res.variance };
    }

    private boolean isShortHierarchicalEdge(final ElkEdge edge) {
        ElkNode src = ElkGraphUtil.getSourceNode(edge);
        ElkNode tgt = ElkGraphUtil.getTargetNode(edge);
        return (src.getParent() != null && src.getParent() == tgt)
                || (tgt.getParent() != null && tgt.getParent() == src);
    }
}
