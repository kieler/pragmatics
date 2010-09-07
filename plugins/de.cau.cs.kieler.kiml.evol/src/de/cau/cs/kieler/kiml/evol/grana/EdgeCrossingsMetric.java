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

/**
 * Calculates a normalized "crosslessness" metric, based upon the number of
 * crossings.
 *
 * @author bdu
 *
 */
public class EdgeCrossingsMetric implements IAnalysis {
    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(
            final KNode parentNode, final Map<String, Object> results,
            final IKielerProgressMonitor progressMonitor) throws KielerException {

        progressMonitor.begin("Edge crossings metric analysis", 1);

        final Float result;

        try {
            final Object crossingsResult =
                    results.get("de.cau.cs.kieler.kiml.grana.edgeCrossings");
            final Object edgesResult = results.get("de.cau.cs.kieler.kiml.grana.edgeCount");
            final Object bendsResult = results.get("de.cau.cs.kieler.kiml.grana.bendpointCount");
            final int edgesCount = ((Integer) edgesResult).intValue();
            final int bendsCount = ((Integer) bendsResult).intValue();
            final int crossingsCount = ((Integer) crossingsResult).intValue();

            final int edgesAuxCount = edgesCount + bendsCount;

            int sum = 0;
            for (final KNode node : parentNode.getChildren()) {
                final int degree =
                        node.getOutgoingEdges().size() + node.getIncomingEdges().size();
                sum += degree * (degree - 1);
                // FIXME: this only works for highest level, not for hierarchies
                // TODO: consider bend points as pseudo nodes
            }

            // In straight-line drawings of connected graphs with at most one
            // edge
            // between nodes, adjacent edges cannot cross.
            // [H. C. Purchase, "Metrics for Graph Drawing Aesthetics", 2002]

            // In general, this is not guaranteed, so we don't know how many
            // crossings are impossible.
            final int impossibleCrossingsCount = 0;
            // TODO: if graph is connected and graph is not a multi graph, we
            // can use
            // impossibleCrossingsCount = sum / 2;

            final int maxCrossingsCount =
                    (edgesAuxCount * (edgesAuxCount - 1)) / 2 - impossibleCrossingsCount;

            Assert.isTrue(crossingsCount <= maxCrossingsCount);

            if (crossingsCount > maxCrossingsCount) {
                result = Float.valueOf(0.0f);
            } else if (maxCrossingsCount > 0) {
                result =
                        Float.valueOf(1.0f - (float) ((double) crossingsCount / maxCrossingsCount));
            } else {
                result = Float.valueOf(1.0f);
            }

            Assert.isTrue((0.0f <= result.floatValue()) && (result.floatValue() <= 1.0f),
                    "Metric result out of bounds: " + result);

        } finally {
            progressMonitor.done();
        }

        return result;

    }
}
