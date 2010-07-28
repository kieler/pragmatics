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
import java.util.Map.Entry;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.grana.IAnalysis;

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
            final IKielerProgressMonitor progressMonitor)
            throws KielerException {

        progressMonitor.begin("Weighted aggregation", 1);
        double sum = 0.0;
        int count = 0;
        for (final Entry<String, Object> entry : results.entrySet()) {
            if (entry.getKey().toLowerCase().contains("metric")) {
                final Object val = entry.getValue();
                if (val instanceof Float) {
                    sum += (Float) val;
                    count++;
                    System.out.println(entry.getKey() + ": " + val + " ");
                }
            }
        }
        System.out.println();

        if (count == 0) {
            return Float.NaN;
        }
        return sum / count;
    }
}
