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
/**
 *
 */
package de.cau.cs.kieler.kiml.evol.metrics;

import java.util.Map;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.service.grana.IAnalysis;

/**
 * Measures the fraction of upward edges in the given KGraph. Returns a Float
 * object that indicates the degree of 'upwardness' within the range of (0.0,
 * 1.0), where 0.0 means no upwardness and 1.0 means maximum upwardness.
 *
 * This metric is based on the edge count and on the edge direction analysis.
 *
 * NOTE: A more sophisticated, fuzzy metric could take angles into account, so
 * that for example an edge that is pointing diagonally upward would be counted
 * as a "50% upward" edge.
 * 
 * @author bdu
 */
public class UpwardnessMetric implements IAnalysis {

    /**
     * Identifier for "edge count analysis".
     */
    private static final String GRANA_EDGE_COUNT = "de.cau.cs.kieler.kiml.grana.edgeCount";

    /**
     * Identifier for "edge direction analysis".
     */
    private static final String GRANA_EDGE_DIRECTION_COUNT =
            "de.cau.cs.kieler.kiml.grana.edgeDirections";

    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(
            final KNode parentNode, final Map<String, Object> results,
            final IKielerProgressMonitor progressMonitor) {

        progressMonitor.begin("Upwardness metric analysis", 1);

        Float result;

        try {
            // load numbers from analyses
            Object edgesResult = results.get(GRANA_EDGE_COUNT);
            Object[] edgeDirectionResult = (Object[]) results.get(GRANA_EDGE_DIRECTION_COUNT);

            int totalEdgesCount = (Integer) edgesResult;

            // edgeDirectionResult: TOP (0), LEFT (1), BOTTOM (2), RIGHT (3)
            int upwardEdgesCount = (Integer) edgeDirectionResult[0];

            result = (float) upwardEdgesCount / totalEdgesCount;

        } finally {
            // We must close the monitor.
            progressMonitor.done();
        }

        return result;
    }

}
