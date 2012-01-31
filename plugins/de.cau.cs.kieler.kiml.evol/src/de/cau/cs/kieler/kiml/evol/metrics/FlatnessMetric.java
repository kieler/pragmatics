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
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.service.grana.AnalysisFailed;
import de.cau.cs.kieler.kiml.service.grana.IAnalysis;

/**
 * A layout metric that computes the vertical compactness (flatness) of the
 * given graph layout.
 *
 * Does not care for hierarchy. The returned object is a float value within the
 * range of 0.0 to 1.0, where a higher value means more vertical compactness.
 *
 * @author bdu
 *
 */
public class FlatnessMetric implements IAnalysis {

    /** Identifier for "dimensions". */
    private static final String GRANA_DIMENSIONS = "de.cau.cs.kieler.kiml.grana.dimensions";

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public Object doAnalysis(
            final KNode parentNode, final Map<String, Object> results,
            final IKielerProgressMonitor progressMonitor) {

        progressMonitor.begin("Flatness metric analysis", 1);

        Float result;
        try {
            Object dimsResult = results.get(GRANA_DIMENSIONS);

            if (!(dimsResult instanceof Pair<?, ?>)) {
                // This should happen only when the dims analysis failed.

                // Flatness metric analysis failed.
                return new AnalysisFailed(AnalysisFailed.Type.Dependency);
            }

            Pair<Float, Float> dims = (Pair<Float, Float>) dimsResult;
            float xdim = dims.getFirst();
            float ydim = dims.getSecond();

            boolean isXdimZero = xdim == 0.0f;
            boolean isYdimZero = ydim == 0.0f;

            if (isXdimZero && isYdimZero) {
                return new AnalysisFailed(AnalysisFailed.Type.Failed, new IllegalStateException(
                        "Flatness metric analysis failed because of zero length or height."));
            }

            float heightToWidthRatio = isXdimZero ? Float.POSITIVE_INFINITY : ydim / xdim;
            float widthToHeightRatio = isYdimZero ? Float.POSITIVE_INFINITY : xdim / ydim;
            final float half = 0.5f;
            if (widthToHeightRatio < 1.0f) {
                // narrow
                result = widthToHeightRatio * half;
            } else {
                // wide
                result = 1.0f - (heightToWidthRatio * half);
            }

            assert (0.0f <= result) && (result <= 1.0f) : "Metric result out of bounds: "
                    + result;
        } finally {
            // We must close the monitor.
            progressMonitor.done();
        }

        return result;
    }
}
