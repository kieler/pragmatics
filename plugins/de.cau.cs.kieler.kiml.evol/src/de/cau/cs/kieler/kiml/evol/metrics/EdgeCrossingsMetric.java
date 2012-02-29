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

/**
 * Calculates a normalized "crosslessness" metric, based upon the number of
 * crossings. This does not care for hierarchies.
 *
 * @author bdu
 *
 */
public class EdgeCrossingsMetric implements IAnalysis {

    /**
     * Identifier for "bend point count".
     */
    private static final String GRANA_BENDPOINT_COUNT =
            "de.cau.cs.kieler.kiml.grana.bendpointCount";
    /**
     * Identifier for "edge count".
     */
    private static final String GRANA_EDGE_COUNT = "de.cau.cs.kieler.kiml.grana.edgeCount";

    /** Identifier for edge crossings count. */
    private static final String GRANA_EDGE_CROSSINGS_COUNT =
            "de.cau.cs.kieler.kiml.grana.edgeCrossings";

    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(
            final KNode parentNode, final Map<String, Object> results,
            final IKielerProgressMonitor progressMonitor) {

        progressMonitor.begin("Edge crossings metric analysis", 1);

        Float result;

        try {
            // EdgeCrossingsAnalysis result is Object[] {min, avg, max, sum}
            Object[] crossingsResult = (Object[]) results.get(GRANA_EDGE_CROSSINGS_COUNT);
            Object edgesResult = results.get(GRANA_EDGE_COUNT);
            Object[] bendsResult = (Object[]) results.get(GRANA_BENDPOINT_COUNT);
            int edgesCount = (Integer) edgesResult;
            int bendsCount = (Integer) bendsResult[3];
            int crossingsCount = (Integer) crossingsResult[3];

            int edgesAuxCount = edgesCount + bendsCount;

            int sum = 0;
            for (final KNode node : parentNode.getChildren()) {
                int degree = node.getOutgoingEdges().size() + node.getIncomingEdges().size();
                sum += degree * (degree - 1);
                // TODO: consider bend points as pseudo nodes
            }

            // In straight-line drawings of connected graphs with at most one
            // edge between nodes, adjacent edges cannot cross.
            // [H. C. Purchase, "Metrics for Graph Drawing Aesthetics", 2002]

            // In general, this is not guaranteed, so we don't know how many
            // crossings are impossible.
            int impossibleCrossingsCount = 0;
            // TODO: if graph is connected and graph is not a multi graph, we
            // can use
            // impossibleCrossingsCount = sum / 2;

            int maxCrossingsCount =
                    (edgesAuxCount * (edgesAuxCount - 1)) / 2 - impossibleCrossingsCount;

            assert crossingsCount <= maxCrossingsCount;

            if (crossingsCount > maxCrossingsCount) {
                result = Float.valueOf(0.0f);
            } else if (maxCrossingsCount > 0) {
                result =
                        Float.valueOf(1.0f - (float) ((double) crossingsCount / maxCrossingsCount));
            } else {
                result = 1.0f;
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
