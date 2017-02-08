/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kiml.grana.analyses;

import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.ElkNode;

import de.cau.cs.kieler.kiml.grana.AnalysisContext;
import de.cau.cs.kieler.kiml.grana.AnalysisFailed;
import de.cau.cs.kieler.kiml.grana.IAnalysis;

/**
 * A drawing analysis that computes the aspect ratio of the area a graph drawing occupies.
 * This analysis depends on the {@link AreaAnalysis} results. Returns a single-component
 * result {@code (double ratio)}.
 * 
 * @author cds
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow 2012-07-10 msp
 */
public class AspectRatioAnalysis implements IAnalysis {

    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final ElkNode parentNode, final AnalysisContext context,
            final IElkProgressMonitor progressMonitor) {
        progressMonitor.begin("Aspect ratio analysis", 1);
        
        // Fetch the results of the area analysis
        Object o = context.getResult(AreaAnalysis.ID);
        if (o == null) {
            progressMonitor.done();
            
            return new AnalysisFailed(AnalysisFailed.Type.Dependency);
        }
        
        // Compute the aspect ratio
        Object[] areaResults = (Object[]) o;
        double width = (Float) areaResults[AreaAnalysis.INDEX_WIDTH];
        double height = (Float) areaResults[AreaAnalysis.INDEX_HEIGHT];
        
        double aspect = width / height;
        
        // Finish off..
        progressMonitor.done();
        return aspect;
    }

}
