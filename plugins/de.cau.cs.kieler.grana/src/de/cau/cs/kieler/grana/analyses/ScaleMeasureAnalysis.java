/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2020 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.grana.analyses;

import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.ElkNode;

import de.cau.cs.kieler.grana.AnalysisContext;
import de.cau.cs.kieler.grana.AnalysisFailed;
import de.cau.cs.kieler.grana.IAnalysis;

/**
 * A drawing analysis that computes the scale measure of a graph.
 * 
 * @author sdo
 */
public class ScaleMeasureAnalysis implements IAnalysis {

    /**
     * Identifier of the area analysis.
     */
    public static final String ID = "de.cau.cs.kieler.grana.scalemeasure";
    
    /**
     * Index of the area width in the result array.
     */
    public static final int INDEX_WIDTH = 0;

    /**
     * Index of the area height in the result array.
     */
    public static final int INDEX_HEIGHT = 1;
    
    
    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final ElkNode parentNode, final AnalysisContext context,
            final IElkProgressMonitor progressMonitor) {
        progressMonitor.begin("Scale measure analysis", 1);
        
        // Fetch the results of the area analysis
        Object o = context.getResult(AreaAnalysis.ID);
        if (o == null) {
            progressMonitor.done();
            
            return new AnalysisFailed(AnalysisFailed.Type.Dependency);
        }
        
        // Compute the aspect ratio
        Object[] areaResults = (Object[]) o;
        double width = (Double) areaResults[AreaAnalysis.INDEX_WIDTH];
        double height = (Double) areaResults[AreaAnalysis.INDEX_HEIGHT];
        double dar = parentNode.getProperty(CoreOptions.ASPECT_RATIO);
        
        
        return new Object[] {dar, Math.min(1d / width, (1d / dar) / height)};
    }
}
