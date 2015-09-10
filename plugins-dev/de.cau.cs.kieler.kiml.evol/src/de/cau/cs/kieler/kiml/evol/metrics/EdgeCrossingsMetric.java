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
package de.cau.cs.kieler.kiml.evol.metrics;

import java.util.Map;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.grana.AnalysisContext;
import de.cau.cs.kieler.kiml.grana.IAnalysis;
import de.cau.cs.kieler.kiml.grana.analyses.EdgeCountAnalysis;
import de.cau.cs.kieler.kiml.grana.analyses.EdgeCrossingsAnalysis;
import de.cau.cs.kieler.kiml.grana.analyses.NodeCountAnalysis;

/**
 * Calculates a normalized "crosslessness" metric, based upon the number of crossings.
 * See Helen C. Purchase, Metrics for Graph Drawing Aesthetics, 2002.
 *
 * @author bdu
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public class EdgeCrossingsMetric implements IAnalysis {

    /** split value for the two linear regions of the result metric. */
    private static final double SCALE_SPLIT = 0.1;

    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final KNode parentNode, final AnalysisContext context,
            final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Edge crossings metric", 1);
        
        Map<String, Object> results = context.getResults();
        // EdgeCrossingsAnalysis result is Object[] {min, avg, max, sum}
        Object[] crossingsResult = (Object[]) results.get(EdgeCrossingsAnalysis.ID);
        int crossingsCount = (Integer) crossingsResult[3]; // SUPPRESS CHECKSTYLE MagicNumber
        Object edgesResult = results.get(EdgeCountAnalysis.ID);
        int edgeCount = (Integer) edgesResult;
        Object nodesResult = results.get(NodeCountAnalysis.ID);
        int nodeCount = (Integer) nodesResult;

        int maxCrossingsCount = (edgeCount * (edgeCount - 1)) / 2;

        float result;
        if (crossingsCount >= maxCrossingsCount) {
            result = 0.0f;
        } else if (maxCrossingsCount > 0) {
            // the number of crossings is divided in regions [0, splitCnt] and [splitCnt, maxCrCnt]
            // where splitCnt = edgeCnt^3 / nodeCnt^2
            double splitCount = Math.min((double) edgeCount * edgeCount * edgeCount
                    / (nodeCount * nodeCount),
                    maxCrossingsCount * (1 - SCALE_SPLIT));
            if (crossingsCount <= splitCount) {
                result = 1.0f - (float) ((1 - SCALE_SPLIT) * crossingsCount / splitCount);
            } else {
                result = (float) (SCALE_SPLIT * (1 - (crossingsCount - splitCount)
                        / (maxCrossingsCount - splitCount)));
            }
        } else {
            result = 1.0f;
        }

        assert result >= 0 && result <= 1;
        progressMonitor.done();
        return result;

    }
}
