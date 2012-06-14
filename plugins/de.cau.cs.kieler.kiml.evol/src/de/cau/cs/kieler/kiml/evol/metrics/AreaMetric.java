/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2010 by + Christian-Albrechts-University of Kiel + Department of
 * Computer Science + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.evol.metrics;

import java.util.Map;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.service.grana.IAnalysis;
import de.cau.cs.kieler.kiml.service.grana.analyses.AreaAnalysis;
import de.cau.cs.kieler.kiml.service.grana.analyses.EdgeCountAnalysis;
import de.cau.cs.kieler.kiml.service.grana.analyses.NodeCountAnalysis;

/**
 * Measures the area extent of the given graph layout.
 *
 * Does not care for hierarchy. The returned object is a float value within the
 * range of 0.0 to 1.0, where a higher value means less area.
 *
 * @author bdu
 * @author msp
 */
public class AreaMetric implements IAnalysis {
    
    /** exponent for the computed area. */
    private static final double AREA_EXP = 0.05;

    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final KNode parentNode, final Map<String, Object> results,
            final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Area metric analysis", 1);
        Object[] dimsResult = (Object[]) results.get(AreaAnalysis.ID);
        int nodeCount = (Integer) results.get(NodeCountAnalysis.ID);
        int edgeCount = (Integer) results.get(EdgeCountAnalysis.ID);
        int elementCount = nodeCount + edgeCount;

        float xdim = (Float) dimsResult[0];
        float ydim = (Float) dimsResult[1];

        float result = 1.0f;
        double area = xdim * ydim;
        if (elementCount > 0) {
            // normalize considering the number of nodes and edges
            area /= elementCount * elementCount;
        }
        if (area > 1.0) {
            result = 1.0f / (float) Math.pow(area, AREA_EXP);
        }
        
        assert result >= 0 && result <= 1;
        progressMonitor.done();
        return result;
    }
    
}
