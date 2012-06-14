/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2010 by + Christian-Albrechts-University of Kiel + Department of
 * Computer Science + Real-Time and Embedded Systems Group
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

/**
 * Calculates a metric for the number of bends in the graph. This depends on the
 * edge count analysis and the bend point count analysis that care for hierarchies.
 *
 * @author mri
 * @author bdu
 */
public class EdgeBendsMetric implements IAnalysis {

    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final KNode parentNode, final Map<String, Object> results,
            final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Bend metric analysis", 1);
        // load numbers from analyses
        Object edgesResult = results.get(EdgeCountAnalysis.ID);
        Object[] bendsResult = (Object[]) results.get(BendsAnalysis.ID);

        // bendsResult is Object[] {min, avg, max, sum}

        int edgesCount = (Integer) edgesResult;
        int bendsCount = (Integer) bendsResult[3]; // SUPPRESS CHECKSTYLE MagicNumber

        // normalize
        float result;
        if (edgesCount + bendsCount > 0) {
            result = 1.0f - (float) bendsCount / (float) (edgesCount + bendsCount);
        } else {
            result = 1.0f;
        }
        
        assert result >= 0 && result <= 1;
        progressMonitor.done();
        return result;
    }

}
