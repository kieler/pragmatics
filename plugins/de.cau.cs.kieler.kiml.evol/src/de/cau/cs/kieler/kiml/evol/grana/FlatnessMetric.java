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
package de.cau.cs.kieler.kiml.evol.grana;

import java.util.Map;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.grana.IAnalysis;

/**
 * A layout metric that computes the vertical compactness (flatness) of the
 * given graph layout.
 *
 * Does not care for hierarchy. The returned Object is a float value within the
 * range of 0.0 to 1.0, where a higher value means more vertical compactness.
 *
 * @author bdu
 *
 */
public class FlatnessMetric implements IAnalysis {
    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(
            final KNode parentNode, final Map<String, Object> results,
            final IKielerProgressMonitor progressMonitor)
            throws KielerException {
        progressMonitor.begin("Flatness metric analysis", 1);
        final Float result;
        final Object dimsResult = results.get("de.cau.cs.kieler.kiml.grana.dimensions");
        final Pair<Float, Float> dims;
        final float xdim;
        final float ydim;
        if (dimsResult instanceof Pair) {
            dims = (Pair<Float, Float>) dimsResult;
            xdim = dims.getFirst();
            ydim = dims.getSecond();
        } else {
            // this should not happen
            xdim = 0.0f;
            ydim = 0.0f;
        }
        final boolean isXdimZero = (xdim == 0.0f);
        final boolean isYdimZero = (ydim == 0.0f);
        if (isXdimZero && isYdimZero) {
            // XXX this should happen rarely, consider returning NaN?
            result = 0.5f;
        } else {
            final float heightToWidthRatio = (isXdimZero ? Float.POSITIVE_INFINITY : ydim / xdim);
            final float widthToHeightRatio = (isYdimZero ? Float.POSITIVE_INFINITY : xdim / ydim);
            if (widthToHeightRatio < 1.0) {
                // narrow
                result = widthToHeightRatio * .5f;
            } else {
                // wide
                result = 1.0f - (heightToWidthRatio * .5f);
            }
        }
        progressMonitor.done();
        return result;
    }
}
