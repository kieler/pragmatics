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
package de.cau.cs.kieler.kiml.grana.ui.visualizers;

import de.cau.cs.kieler.kiml.grana.AnalysisData;
import de.cau.cs.kieler.kiml.grana.ui.visualization.IVisualizer;
import de.cau.cs.kieler.kiml.grana.ui.visualization.Visualization;
import de.cau.cs.kieler.kiml.grana.ui.visualization.VisualizationService;

/**
 * The html visualizer which simply uses the text visualization as it is.
 * 
 * @author mri
 * @author cds
 * @kieler.ignore (excluded from review process)
 */
public class BasicHtmlVisualizer implements IVisualizer<String, Object> {

    /** the error message for an invalid result type. */
    private static final String ERROR_WRONG_RESULT_FORMAT = "Invalid type";
    
    /**
     * Whether multi-component results should be visualized using horizontal tables
     * as opposed to vertically. (like a list)
     */
    private boolean horizontalTables = true;
    

    /**
     * {@inheritDoc}
     */
    public boolean canVisualize(final Object result) {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public String visualize(final AnalysisData analysis,
            final Object result, final Object parameter) {
        
        String str = null;
        
        // Check if we have an array of objects
        if (result instanceof Object[]) {
            Object[] results = (Object[]) result;
            
            // Check if there is a result per registered component
            if (results.length == analysis.getComponents().size()) {
                str = buildResultTable(analysis, results, horizontalTables);
            } else {
                str = "<font color='red'>" + ERROR_WRONG_RESULT_FORMAT + "</font>";
            }
        } else {
            // Check if the result should actually be an array
            if (analysis.getComponents().size() > 1) {
                str = "<font color='red'>" + ERROR_WRONG_RESULT_FORMAT + "</font>";
            } else {
                // Let the text visualizer visualize the result
                Visualization visualization =
                    VisualizationService.getInstance().getVisualization(
                            "text", result);
                
                if (visualization == null) {
                    str = result.toString();
                } else {
                    str = visualization.get(analysis, result);
                }
            }
        }
        
        return str;
    }
    
    /**
     * Builds a table visualizing the analysis components and the corresponding results.
     * 
     * @param analysis the analysis.
     * @param results the analysis results.
     * @param horizontal if {@code true}, the results will be visualized next to each other in
     *                   a table, with a dedicated table header row. Otherwise, the results
     *                   will be listet using one row per result.
     * @return a string with the result table.
     */
    private String buildResultTable(final AnalysisData analysis, final Object[] results,
            final boolean horizontal) {

        StringBuilder builder = new StringBuilder();
        
        for (int i = 0; i < analysis.getComponents().size(); i++) {
            // Add a line break if this is not the first result
            if (i > 0) {
                builder.append("<br/>");
            }
            
            // Visualization result
            Visualization visualization =
                VisualizationService.getInstance().getVisualization("text", results[i]);
            builder.append(visualization.get(analysis, results[i]));
            
            // Component name
            builder.append(" <em>(" + analysis.getComponents().get(i).getFirst() + ")</em>");
        }
        
        return builder.toString();
    }
}
