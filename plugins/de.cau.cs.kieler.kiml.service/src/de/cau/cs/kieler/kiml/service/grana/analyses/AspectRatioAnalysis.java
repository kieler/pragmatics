/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kiml.service.grana.analyses;

import java.util.Map;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.service.grana.AnalysisFailed;
import de.cau.cs.kieler.kiml.service.grana.IAnalysis;


/**
 * A drawing analysis that computes the aspect ratio of the area a graph drawing occupies.
 * This analysis depends on the {@link AreaAnalysis} results. Returns a single-component
 * result {@code (double ratio)}.
 * 
 * @author cds
 */
public class AspectRatioAnalysis implements IAnalysis {

    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final KNode parentNode, final Map<String, Object> results,
            final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Aspect ratio analysis", 1);
        
        // Fetch the results of the area analysis
        Object o = results.get(AreaAnalysis.ID);
        if (o == null) {
            progressMonitor.done();
            
            return new AnalysisFailed(AnalysisFailed.Type.Dependency);
        }
        
        // Compute the aspect ratio
        Object[] areaResults = (Object[]) o;
        int width = (Integer) areaResults[AreaAnalysis.INDEX_WIDTH];
        int height = (Integer) areaResults[AreaAnalysis.INDEX_HEIGHT];
        
        double aspect = (double) width / (double) height;
        
        // Finish off..
        progressMonitor.done();
        return aspect;
    }

}
