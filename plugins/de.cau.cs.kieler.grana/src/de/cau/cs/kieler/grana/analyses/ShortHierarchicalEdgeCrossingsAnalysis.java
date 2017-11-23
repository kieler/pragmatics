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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.elk.core.math.ElkMath;
import org.eclipse.elk.core.math.KVector;
import org.eclipse.elk.core.math.KVectorChain;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.options.EdgeRouting;
import org.eclipse.elk.core.util.ElkUtil;
import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkEdgeSection;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.util.ElkGraphUtil;

import de.cau.cs.kieler.grana.AnalysisContext;
import de.cau.cs.kieler.grana.IAnalysis;
import de.cau.cs.kieler.grana.analyses.EdgeCrossingsAnalysis.EdgeCrossingsResult;

/**
 * Counts crossings between short hierarchical edges only.
 */
public class ShortHierarchicalEdgeCrossingsAnalysis implements IAnalysis {

    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final ElkNode parentNode, final AnalysisContext context,
            final IElkProgressMonitor progressMonitor) {
        progressMonitor.begin("Short hierarchical edge Crossings analysis", 1);

        // collect all edges and translate their coordinates to absolute
        LinkedList<ElkNode> nodeQueue = new LinkedList<>();
        List<ElkEdge> edges = new ArrayList<>();
        List<KVectorChain> chains = new ArrayList<>();
        nodeQueue.addAll(parentNode.getChildren());
        while (!nodeQueue.isEmpty()) {
            // poll the first element
            ElkNode node = nodeQueue.poll();

            // collect the outgoing edges
            for (ElkEdge edge : ElkGraphUtil.allOutgoingEdges(node)) {
                if (!isShortHierarchicalEdge(edge)) {
                    continue;
                }

                ElkEdgeSection section = ElkGraphUtil.firstEdgeSection(edge, false, false);
                KVectorChain chain = ElkUtil.createVectorChain(section);

                // translate the bend point coordinates to absolute
                ElkNode referenceNode = edge.getContainingNode();
                KVector referencePoint = new KVector();
                ElkUtil.toAbsolute(referencePoint, referenceNode);
                chain.offset(referencePoint);

                // transform spline control points to approximated bend points
                if (edge.getProperty(CoreOptions.EDGE_ROUTING) == EdgeRouting.SPLINES) {
                    chain = ElkMath.approximateBezierSpline(chain);
                }

                edges.add(edge);
                chains.add(chain);
            }

            // enqueue the child nodes
            nodeQueue.addAll(node.getChildren());
        }

        EdgeCrossingsResult results = EdgeCrossingsAnalysis.countCrossings(edges, chains);

        progressMonitor.done();
        return new Object[] { results.min, results.average, results.max, results.sum,
                results.onefold };
    }
    
    private boolean isShortHierarchicalEdge(final ElkEdge edge) {
        ElkNode src = ElkGraphUtil.getSourceNode(edge);
        ElkNode tgt = ElkGraphUtil.getTargetNode(edge);
        return (src.getParent() != null && src.getParent() == tgt)
                || (tgt.getParent() != null && tgt.getParent() == src);
    }
}
