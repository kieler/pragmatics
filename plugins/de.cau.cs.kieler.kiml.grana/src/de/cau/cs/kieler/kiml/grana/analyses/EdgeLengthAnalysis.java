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

import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.grana.AnalysisContext;
import de.cau.cs.kieler.kiml.grana.AnalysisOptions;
import de.cau.cs.kieler.kiml.grana.IAnalysis;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;

/**
 * A graph analysis that computes the minimum, average and maximum edge length.
 * It assumes that the edge bend points describe polylines (splines are not
 * supported).
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
    public static final String ID = "de.cau.cs.kieler.kiml.grana.edgeLength";

    /**
     * Computes the length of the given edge.
     * 
     * @param edge
     *            the edge
     * @return the length
     */
    public static float computeEdgeLength(final KEdge edge) {
        KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
        float edgeLength = 0;
        KPoint current = edgeLayout.getSourcePoint();
        for (KPoint point : edgeLayout.getBendPoints()) {
            float deltaX = current.getX() - point.getX();
            float deltaY = current.getY() - point.getY();
            edgeLength += Math.sqrt(deltaX * deltaX + deltaY * deltaY);
            current = point;
        }
        float deltaX = current.getX() - edgeLayout.getTargetPoint().getX();
        float deltaY = current.getY() - edgeLayout.getTargetPoint().getY();
        edgeLength += Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        return edgeLength;
    }

    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final KNode parentNode,
            final AnalysisContext context,
            final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Edge length analysis", 1);
        
        boolean hierarchy = parentNode.getData(KShapeLayout.class).getProperty(
                AnalysisOptions.ANALYZE_HIERARCHY);

        int numberOfEdges = 0;
        float overallEdgeLength = 0;
        float minEdgeLength = Float.MAX_VALUE;
        float maxEdgeLength = 0;
        List<Float> edgeLengths = Lists.newLinkedList();
        List<KNode> nodeQueue = new LinkedList<KNode>();
        nodeQueue.addAll(parentNode.getChildren());
        while (nodeQueue.size() > 0) { 
            // pop first element
            KNode node = nodeQueue.remove(0);
            
            // compute edge length for all outgoing edges
            for (KEdge edge : node.getOutgoingEdges()) {
                if (!hierarchy && edge.getTarget().getParent() != parentNode) {
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
