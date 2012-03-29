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
import de.cau.cs.kieler.kiml.service.grana.AnalysisFailed;
import de.cau.cs.kieler.kiml.service.grana.IAnalysis;
import de.cau.cs.kieler.kiml.service.grana.AnalysisFailed.Type;

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

    /** Identifier for "edge length". */
    private static final String GRANA_EDGE_LENGTH = "de.cau.cs.kieler.kiml.grana.edgeLength";

    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(
            final KNode parentNode, final Map<String, Object> results,
            final IKielerProgressMonitor progressMonitor) {

        progressMonitor.begin("Edge length uniformity analysis", 1);

        Float result = null;

        try {
            Object edgeLengthResult = results.get(GRANA_EDGE_LENGTH);

            if (!(edgeLengthResult instanceof Object[])) {
                return new AnalysisFailed(Type.Dependency);
            }

            Object[] mmr = (Object[]) edgeLengthResult;
            Float min = (Float) mmr[0];
            Float avg = (Float) mmr[1];
            Float max = (Float) mmr[2];

            float range = max - min;

            float rangeToAverageRatio = range / avg;

            final float half = 0.5f;
            // FIXME this correlates with the layout size?
            if (rangeToAverageRatio < 1.0f) {
                // relatively small range
                result = 1.0f - (rangeToAverageRatio * half);
            } else {
                // relatively big range
                result = (1.0f / rangeToAverageRatio) * half;
            }

            assert (0.0f <= result.floatValue()) && (result.floatValue() <= 1.0f) : "Metric result out of bounds: "
                    + result;

        } finally {
            // We must close the monitor.
            progressMonitor.done();
        }

        return result;
    }
}
