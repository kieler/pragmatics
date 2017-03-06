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
package de.cau.cs.kieler.grana.analyses;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.elk.core.math.KVector;
import org.eclipse.elk.core.math.KVectorChain;
import org.eclipse.elk.core.util.ElkUtil;
import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkEdgeSection;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.util.ElkGraphUtil;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.grana.AnalysisContext;
import de.cau.cs.kieler.grana.AnalysisOptions;
import de.cau.cs.kieler.grana.IAnalysis;

/**
 * A graph analysis that computes the minimum, average and maximum edge length.
 * It assumes that the edge bend points describe polylines (splines are not
 * supported).
 * 
 * <b>Warning:</b> Doesn't work for hyperedges.
 * 
 * @author mri
 * @author uru
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow 2012-07-10 msp
 */
public class EdgeLengthAnalysis implements IAnalysis {
    
    /**
     * Identifier of the edge length analysis.
     */
    public static final String ID = "de.cau.cs.kieler.grana.edgeLength";

    /**
     * Computes the length of the given edge.
     * 
     * @param edge
     *            the edge
     * @return the length
     */
    public static float computeEdgeLength(final ElkEdge edge) {
        float edgeLength = 0;
        ElkEdgeSection section = ElkGraphUtil.firstEdgeSection(edge, false, false);
        KVectorChain vs = ElkUtil.createVectorChain(section);
        Iterator<KVector> vsIt = vs.iterator();
        KVector last = vsIt.next(); // vs guaranteed to contain two points
        while (vsIt.hasNext()) {
            KVector current = vsIt.next();
            edgeLength += last.distance(current);
            last = current;
        }
        return edgeLength;
    }

    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final ElkNode parentNode,
            final AnalysisContext context,
            final IElkProgressMonitor progressMonitor) {
        progressMonitor.begin("Edge length analysis", 1);
        
        boolean hierarchy = parentNode.getProperty(AnalysisOptions.ANALYZE_HIERARCHY);

        int numberOfEdges = 0;
        float overallEdgeLength = 0;
        float minEdgeLength = Float.MAX_VALUE;
        float maxEdgeLength = 0;
        List<Float> edgeLengths = Lists.newLinkedList();
        List<ElkNode> nodeQueue = new LinkedList<ElkNode>();
        nodeQueue.addAll(parentNode.getChildren());
        while (nodeQueue.size() > 0) { 
            // pop first element
            ElkNode node = nodeQueue.remove(0);
            
            // compute edge length for all outgoing edges
            for (ElkEdge edge : ElkGraphUtil.allOutgoingEdges(node)) {
                if (!hierarchy && edge.isHierarchical()) {
                    continue;
                }
                
                numberOfEdges++;
                float edgeLength = computeEdgeLength(edge);
                overallEdgeLength += edgeLength;
                edgeLengths.add(edgeLength);
                // min edge length
                if (edgeLength < minEdgeLength) {
                    minEdgeLength = edgeLength;
                }
                // max edge length
                if (edgeLength > maxEdgeLength) {
                    maxEdgeLength = edgeLength;
                }
            }
            
            if (hierarchy) {
                nodeQueue.addAll(node.getChildren());
            }
        }
        
        // calculate variance
        double avg = overallEdgeLength / (double) numberOfEdges;
        double variance = 0;
        for (double l : edgeLengths) {
            variance += Math.pow(l - avg, 2);
        }
        variance *= 1 / (double) (numberOfEdges - 1);

        progressMonitor.done();

        if (numberOfEdges > 0) {
            return new Object[] { minEdgeLength,
                    avg, maxEdgeLength, variance };
        } else {
            return new Object[] { 0, 0.0f, 0, 0.0f };
        }
    }

}
