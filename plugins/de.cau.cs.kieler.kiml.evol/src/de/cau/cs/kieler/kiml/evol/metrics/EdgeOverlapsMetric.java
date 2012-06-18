/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
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
import de.cau.cs.kieler.kiml.service.grana.IAnalysis;
import de.cau.cs.kieler.kiml.service.grana.analyses.EdgeCountAnalysis;
import de.cau.cs.kieler.kiml.service.grana.analyses.NodeEdgeOverlapsAnalysis;

/**
 * Metric for the number of node-edge overlaps.
 *
 * @author msp
 */
public class EdgeOverlapsMetric implements IAnalysis {

    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final KNode parentNode, final Map<String, Object> results,
            final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Area metric analysis", 1);

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
