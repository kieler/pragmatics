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
package de.cau.cs.kieler.kiml.evol.grana;

import java.util.Map;

import org.eclipse.core.runtime.Assert;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.grana.IAnalysis;

/**
 * Measures the area extent of the given graph layout.
 *
 * Does not care for hierarchy. The returned Object is a float value within the
 * range of 0.0 to 1.0, where a higher value means more area.
 *
 * @author bdu
 *
 */
public class AreaMetric implements IAnalysis {
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public Object doAnalysis(
            final KNode parentNode, final Map<String, Object> results,
            final IKielerProgressMonitor progressMonitor)
            throws KielerException {
        progressMonitor.begin("Area metric analysis", 1);
        final Float result;

        try {
            final Object dimsResult = results.get("de.cau.cs.kieler.kiml.grana.dimensions");
            final Pair<Float, Float> dims;
            final float xdim;
            final float ydim;
            if (dimsResult instanceof Pair) {
                dims = (Pair<Float, Float>) dimsResult;
                xdim = dims.getFirst().floatValue();
                ydim = dims.getSecond().floatValue();
            } else {
                xdim = 0.0f;
                ydim = 0.0f;
            }

            final double area = xdim * ydim;

            // normalize
            if (area < 1.0) {
                result = Float.valueOf(0.0f);
            } else {
                final double exponent = 0.08;
                result = Float.valueOf((float) (1.0f - (1.0f / Math.pow(area, exponent))));
            }
            Assert.isTrue((0.0f <= result.floatValue()) && (result.floatValue() <= 1.0f),
                    "Metric result out of bounds: " + result);

        } finally {
            progressMonitor.done();
        }

        return result;
    }
}
