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
import de.cau.cs.kieler.kiml.service.grana.analyses.BendsAnalysis;
import de.cau.cs.kieler.kiml.service.grana.analyses.EdgeCountAnalysis;
import de.cau.cs.kieler.kiml.service.grana.analyses.EdgeCrossingsAnalysis;

/**
 * Calculates a normalized "crosslessness" metric, based upon the number of
 * crossings. This does not care for hierarchies.
 *
 * @author bdu
 */
public class EdgeCrossingsMetric implements IAnalysis {

    /** exponent for the calculated crossings ratio. */
    private static final double CROSS_RATIO_EXP = 0.2;

    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(
            final KNode parentNode, final Map<String, Object> results,
            final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Edge crossings metric analysis", 1);
        
        // EdgeCrossingsAnalysis result is Object[] {min, avg, max, sum}
        Object[] crossingsResult = (Object[]) results.get(EdgeCrossingsAnalysis.ID);
        Object edgesResult = results.get(EdgeCountAnalysis.ID);
        Object[] bendsResult = (Object[]) results.get(BendsAnalysis.ID);
        int edgesCount = (Integer) edgesResult;
        int bendsCount = (Integer) bendsResult[3]; // SUPPRESS CHECKSTYLE MagicNumber
        int crossingsCount = (Integer) crossingsResult[3]; // SUPPRESS CHECKSTYLE MagicNumber

        int edgesAuxCount = edgesCount + bendsCount;
        int maxCrossingsCount = (edgesAuxCount * (edgesAuxCount - 1)) / 2;

        assert crossingsCount <= maxCrossingsCount;

        float result;
        if (maxCrossingsCount > 0) {
            result = 1.0f - (float) Math.pow((double) crossingsCount / maxCrossingsCount,
                    CROSS_RATIO_EXP);
        } else {
            result = 1.0f;
        }

        assert result >= 0 && result <= 1;

        progressMonitor.done();
        return result;

    }
}
