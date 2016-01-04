/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
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
import de.cau.cs.kieler.kiml.grana.analyses.NodeEdgeOverlapsAnalysis;

/**
 * Metric for the number of node-edge overlaps.
 *
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public class EdgeOverlapsMetric implements IAnalysis {

    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final KNode parentNode, final AnalysisContext context,
            final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Edge overlaps metric", 1);

        Map<String, Object> results = context.getResults();
        int edgeCount = (Integer) results.get(EdgeCountAnalysis.ID);
        int overlaps = (Integer) results.get(NodeEdgeOverlapsAnalysis.ID);
        
        float result;
        if (overlaps < edgeCount) {
            result = 1 - (float) overlaps / edgeCount;
        } else if (overlaps == 0) {
            result = 1.0f;
        } else {
            result = 0.0f;
        }
        
        assert result >= 0 && result <= 1;
        progressMonitor.done();
        return result;
    }

}
