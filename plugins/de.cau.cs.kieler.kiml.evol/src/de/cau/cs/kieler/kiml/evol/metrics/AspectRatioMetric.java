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

import java.util.Map;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.service.grana.IAnalysis;
import de.cau.cs.kieler.kiml.service.grana.analyses.AreaAnalysis;

/**
 * A layout metric that computes the aspect ratio of the given graph layout.
 *
 * The returned object is a float value within the range of 0.0 to 1.0, where a higher
 * value means that the aspect ratio is closer to the optimal value (the golden ratio).
 *
 * @author msp
 */
public class AspectRatioMetric implements IAnalysis {
    
    /** the golden ratio: (1 + sqrt(5)) / 2 . */
    private static final float GOLDEN_RATIO = 1.61803398875f;

    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(
            final KNode parentNode, final Map<String, Object> results,
            final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Flatness metric analysis", 1);

        float result;
        Object[] dimsResult = (Object[]) results.get(AreaAnalysis.ID);
        float xdim = (Float) dimsResult[0];
        float ydim = (Float) dimsResult[1];

        float ratio = xdim / ydim;
        if (ratio >= GOLDEN_RATIO) {
            // the aspect ratio is wider than the optimal value
            result = GOLDEN_RATIO / ratio;
        } else {
            // the aspect ratio is narrower than the optimal value
            result = ratio / GOLDEN_RATIO;
        }
        // take the square root for lower consideration of deviation
        result = (float) Math.sqrt(result);

        assert result >= 0 && result <= 1;
        
        progressMonitor.done();
        return result;
    }
}
