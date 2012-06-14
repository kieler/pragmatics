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
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.service.grana.AnalysisOptions;
import de.cau.cs.kieler.kiml.service.grana.IAnalysis;
import de.cau.cs.kieler.kiml.service.grana.analyses.EdgeCountAnalysis;
import de.cau.cs.kieler.kiml.service.grana.analyses.EdgeLengthAnalysis;

/**
 * A layout metric that computes the edge length uniformity of the graph layout.
 * The returned Object is a float value within the range of 0.0 to 1.0, where a
 * higher value means more edge length uniformity.
 *
 * @author bdu
 * @author msp
 */
public class EdgeUniformityMetric implements IAnalysis {
    
    /** the result returned if the standard deviation equals the average. */
    private static final float RESULT_BASE = 0.5f;

    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final KNode parentNode, final Map<String, Object> results,
            final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Edge length uniformity analysis", 1);
        float result;

        int numberOfEdges = (Integer) results.get(EdgeCountAnalysis.ID);
        if (numberOfEdges > 0) {
            boolean hierarchy = parentNode.getData(KShapeLayout.class).getProperty(
                    AnalysisOptions.ANALYZE_HIERARCHY);

            // determine all individual edge lengths and their sum
            float[] individualLengths = new float[numberOfEdges];
            int index = 0;
            float average = 0;
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
                    average += edgeLength;
                    individualLengths[index++] = edgeLength;
                }
                
                if (hierarchy) {
                    nodeQueue.addAll(node.getChildren());
                }
            }
            average /= numberOfEdges;
            
            // compute standard deviation of edge length
            double deviation = 0;
            for (int i = 0; i < index; i++) {
                double diff = individualLengths[i] - average;
                deviation += diff * diff;
            }
            deviation = Math.sqrt(deviation / numberOfEdges);
            
            // the higher the standard deviation, the more the result goes to zero
            if (deviation >= average) {
                result = RESULT_BASE * (float) (average / deviation);
            } else {
                result = 1 - (float) (deviation / average) * (1 - RESULT_BASE);
            }

            assert result >= 0 && result <= 1;
        } else {
            result = 1.0f;
        }

        progressMonitor.done();
        return result;
    }
}
