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
import java.util.Set;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.evol.EvolutionServices;
import de.cau.cs.kieler.kiml.service.grana.IAnalysis;

/**
 * This analysis returns the weighted sum of the metrics it depends on.
 *
 * @author bdu
 *
 */
public class WeightedAggregation implements IAnalysis {

    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(
            final KNode parentNode, final Map<String, Object> results,
            final IKielerProgressMonitor progressMonitor) {

        progressMonitor.begin("Weighted aggregation", 1);

        try {
            // TODO: read and use weights
            double sum = 0.0;
            int count = 0;

            Set<String> layoutMetricsIds =
                    EvolutionServices.getInstance().getLayoutMetricsIds();

            for (final String metricId : layoutMetricsIds) {
                Object val = results.get(metricId);
                if (val instanceof Float) {
                    sum += ((Float) val).doubleValue();
                    count++;
                    System.out.println(metricId + ": " + val + " ");
                }
            }

            System.out.println();
            if (count == 0) {
                return Float.valueOf(Float.NaN);
            }

            double result = sum / count;
            assert (0.0f <= result) && (result <= 1.0f) : "Metric result out of bounds: "
                    + result;
            return result;

        } finally {
            // We must close the monitor.
            progressMonitor.done();
        }
    }
}
