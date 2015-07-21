/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2010 by + Kiel University + Department of
 * Computer Science + Real-Time and Embedded Systems Group
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
import de.cau.cs.kieler.kiml.grana.analyses.AreaAnalysis;
import de.cau.cs.kieler.kiml.grana.analyses.EdgeCountAnalysis;
import de.cau.cs.kieler.kiml.grana.analyses.NodeCountAnalysis;

/**
 * Measures the area extent of the given graph layout.
 *
 * Does not care for hierarchy. The returned object is a float value within the
 * range of 0.0 to 1.0, where a higher value means less area.
 *
 * @author bdu
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public class AreaMetric implements IAnalysis {
    
    /** lower bound of normalized area for linear region. */
    private static final double AREA_LOW = 50;
    /** upper bound of normalized area for linear region. */
    private static final double AREA_HIGH = 1000;
    /** lower bound of metric value for linear region. */
    private static final double METRIC_LOW = 0.1;
    /** upper bound of metric value for linear region. */
    private static final double METRIC_HIGH = 0.95;

    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final KNode parentNode, final AnalysisContext context,
            final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Area metric", 1);
        Map<String, Object> results = context.getResults();
        Object[] dimsResult = (Object[]) results.get(AreaAnalysis.ID);
        int nodeCount = (Integer) results.get(NodeCountAnalysis.ID);
        int edgeCount = (Integer) results.get(EdgeCountAnalysis.ID);
        int elementCount = nodeCount + edgeCount;

        float xdim = (Float) dimsResult[0];
        float ydim = (Float) dimsResult[1];
        double area = xdim * ydim;
        if (elementCount > 0) {
            // normalize considering the number of nodes and edges
            area /= elementCount * elementCount;
        }
        
        float result = 1.0f;
        if (area > AREA_HIGH) {
            // assign a very low rating
            result = (float) (METRIC_LOW * AREA_HIGH / area);
        } else if (area < AREA_LOW) {
            // assign a very high rating
            result = (float) (1 - (1 - METRIC_HIGH) * area / AREA_LOW);
        } else {
            // assign a medium rating
            result = (float) ((1 - (area - AREA_LOW) / (AREA_HIGH - AREA_LOW))
                    * (METRIC_HIGH - METRIC_LOW) + METRIC_LOW);
        }
        
        assert result >= 0 && result <= 1;
        progressMonitor.done();
        return result;
    }
    
}
