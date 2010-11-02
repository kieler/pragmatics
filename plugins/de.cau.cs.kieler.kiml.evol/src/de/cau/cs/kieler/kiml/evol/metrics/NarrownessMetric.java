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

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.grana.IAnalysis;

/**
 * A layout metric that computes the horizontal compactness (narrowness) of the
 * given graph layout.
 *
 * Does not care for hierarchy. The returned Object is a float value within the
 * range of 0.0 to 1.0, where a higher value means more horizontal compactness.
 *
 * @author bdu
 *
 */
public class NarrownessMetric implements IAnalysis {

    /** Identifier for "dimensions". */
    private static final String GRANA_DIMENSIONS = "de.cau.cs.kieler.kiml.grana.dimensions";

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public Object doAnalysis(
            final KNode parentNode, final Map<String, Object> results,
            final IKielerProgressMonitor progressMonitor)
            throws KielerException {

        progressMonitor.begin("Narrowness metric analysis", 1);
        Float result;

        try {
            Object dimsResult = results.get(GRANA_DIMENSIONS);
            Pair<Float, Float> dims;
            float xdim;
            float ydim;
            if (dimsResult instanceof Pair) {
                dims = (Pair<Float, Float>) dimsResult;
                xdim = dims.getFirst();
                ydim = dims.getSecond();
            } else {
                // This should happen only when the dims analysis failed.
                xdim = 0.0f;
                ydim = 0.0f;
            }
            boolean isXdimZero = xdim == 0.0f;
            boolean isYdimZero = ydim == 0.0f;
            if (isXdimZero && isYdimZero) {
                throw new KielerException("Narrowness metric analysis failed.");
            }
            float heightToWidthRatio = isXdimZero ? Float.POSITIVE_INFINITY : ydim / xdim;
            float widthToHeightRatio = isYdimZero ? Float.POSITIVE_INFINITY : xdim / ydim;
            final float half = 0.5f;
            if (heightToWidthRatio < 1.0) {
                // wide
                result = heightToWidthRatio * half;
            } else {
                // narrow
                result = 1.0f - (widthToHeightRatio * half);
            }
            assert (0.0f <= result.floatValue()) && (result.floatValue() <= 1.0f) :
                    "Metric result out of bounds: " + result;

        } finally {
            // We must close the monitor.
            progressMonitor.done();
        }

        return result;
    }
}
