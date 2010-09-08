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

import org.eclipse.core.runtime.Assert;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.grana.IAnalysis;
import de.cau.cs.kieler.kiml.grana.MinAvgMaxResult;

/**
 * A layout metric that computes the edge length uniformity of the graph layout.
 * The returned Object is a float value within the range of 0.0 to 1.0, where a
 * higher value means more edge length uniformity.
 * 
 * NOTE: This implementation is experimental. It depends only on the values
 * returned by the edge length analysis.
 *
 * @author bdu
 *
 */
public class EdgeUniformityMetric implements IAnalysis {

    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(
            final KNode parentNode, final Map<String, Object> results,
            final IKielerProgressMonitor progressMonitor) throws KielerException {

        progressMonitor.begin("Edge length uniformity analysis", 1);

        Float result = null;

        try {
            final Object edgeLengthResult = results.get("de.cau.cs.kieler.kiml.grana.edgeLength");

            if (edgeLengthResult instanceof MinAvgMaxResult<?, ?>) {
                final MinAvgMaxResult<Float, Float> mmr =
                        ((MinAvgMaxResult<Float, Float>) edgeLengthResult);
                final float min = mmr.getMin();
                final float max = mmr.getMax();
                final float avg = mmr.getAvg();

                final float range = max - min;

                final float rangeToAverageRatio = range / avg;

                final float half = .5f;
                // FIXME this correlates with the layout size?
                if (rangeToAverageRatio < 1.0f) {
                    // relatively small range
                    result = Float.valueOf(1.0f - rangeToAverageRatio * half);
                } else {
                    // relatively big range
                    result = Float.valueOf((1.0f / rangeToAverageRatio) * half);
                }

                Assert.isTrue((0.0f <= result.floatValue()) && (result.floatValue() <= 1.0f),
                        "Metric result out of bounds: " + result);
            } else {
                throw new KielerException("Edge length uniformity analysis failed.");
            }

        } finally {
            progressMonitor.done();
        }

        return result;
    }

}
