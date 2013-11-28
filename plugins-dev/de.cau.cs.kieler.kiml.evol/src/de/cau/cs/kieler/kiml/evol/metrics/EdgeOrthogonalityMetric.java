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
package de.cau.cs.kieler.kiml.evol.metrics;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.math.KVectorChain;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.service.grana.AnalysisOptions;
import de.cau.cs.kieler.kiml.service.grana.IAnalysis;
import de.cau.cs.kieler.kiml.service.grana.analyses.EdgeCountAnalysis;

/**
 * A layout metric that computes the edge orthogonality of the graph layout.
 * The returned Object is a float value within the range of 0.0 to 1.0, where a
 * higher value means more edge orthogonality.
 *
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public class EdgeOrthogonalityMetric implements IAnalysis {
    
    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final KNode parentNode, final Map<String, Object> results,
            final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Edge orthogonality analysis", 1);
        float result;

        int numberOfEdges = (Integer) results.get(EdgeCountAnalysis.ID);
        if (numberOfEdges > 0) {
            boolean hierarchy = parentNode.getData(KShapeLayout.class).getProperty(
                    AnalysisOptions.ANALYZE_HIERARCHY);

            // determine all individual edge orthogonalities and their sum
            float orthoSum = 0;
            List<KNode> nodeQueue = new LinkedList<KNode>();
            nodeQueue.addAll(parentNode.getChildren());
            while (nodeQueue.size() > 0) {
                // pop first element
                KNode node = nodeQueue.remove(0);
                
                // compute edge orthogonality for all outgoing edges
                for (KEdge edge : node.getOutgoingEdges()) {
                    if (!hierarchy && edge.getTarget().getParent() != parentNode) {
                        continue;
                    }
                    float edgeOrtho = computeOrthogonality(edge);
                    orthoSum += edgeOrtho;
                }
                
                if (hierarchy) {
                    nodeQueue.addAll(node.getChildren());
                }
            }
            float average = orthoSum / numberOfEdges;
            
            result = average * average;
            assert result >= 0 && result <= 1;
        } else {
            result = 1.0f;
        }

        progressMonitor.done();
        return result;
    }
    
    private static final double THRESHOLD = 0.01;
    
    /**
     * Compute the orthogonality of the given edge.
     * 
     * @param edge an edge
     * @return the orthogonality value
     */
    private static float computeOrthogonality(final KEdge edge) {
        KVectorChain chain = edge.getData(KEdgeLayout.class).createVectorChain();
        int orthoCount = 0;
        KVector point1 = chain.getFirst();
        ListIterator<KVector> iter = chain.listIterator(1);
        while (iter.hasNext()) {
            KVector point2 = iter.next();
            if (Math.abs(point2.x - point1.x) < THRESHOLD
                    || Math.abs(point2.y - point1.y) < THRESHOLD) {
                orthoCount++;
            }
            point1 = point2;
        }
        return (float) orthoCount / (chain.size() - 1);
    }
    
}
