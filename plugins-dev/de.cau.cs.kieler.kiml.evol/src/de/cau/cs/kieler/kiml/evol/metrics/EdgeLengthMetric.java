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
import java.util.Map;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.grana.AnalysisOptions;
import de.cau.cs.kieler.kiml.grana.IAnalysis;
import de.cau.cs.kieler.kiml.grana.analyses.EdgeCountAnalysis;
import de.cau.cs.kieler.kiml.grana.analyses.EdgeLengthAnalysis;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;

/**
 * A layout metric that computes the edge lengths of the graph layout.
 * The returned Object is a float value within the range of 0.0 to 1.0, where a
 * higher value means better edge lengths (close to "optimal" values).
 *
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public class EdgeLengthMetric implements IAnalysis {
    
    /** the ideal length for which the metric will give 100%. */
    private static final float IDEAL_LENGTH = 60;
    
    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final KNode parentNode, final Map<String, Object> results,
            final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Edge length metric", 1);
        float result;

        int numberOfEdges = (Integer) results.get(EdgeCountAnalysis.ID);
        if (numberOfEdges > 0) {
            boolean hierarchy = parentNode.getData(KShapeLayout.class).getProperty(
                    AnalysisOptions.ANALYZE_HIERARCHY);

            // determine all individual edge lengths and their sum
            float lengthSum = 0;
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
                    float edgeLength = EdgeLengthAnalysis.computeEdgeLength(edge);
                    lengthSum += edgeLength;
                }
                
                if (hierarchy) {
                    nodeQueue.addAll(node.getChildren());
                }
            }
            float average = lengthSum / numberOfEdges;
            
            if (average <= IDEAL_LENGTH) {
                result = (float) Math.sqrt(average / IDEAL_LENGTH);
            } else {
                result = (float) IDEAL_LENGTH / average;
            }
            
            assert result >= 0 && result <= 1;
        } else {
            result = 1.0f;
        }

        progressMonitor.done();
        return result;
    }
    
}
