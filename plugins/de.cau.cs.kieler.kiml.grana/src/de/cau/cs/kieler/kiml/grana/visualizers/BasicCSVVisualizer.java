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
package de.cau.cs.kieler.kiml.grana.visualizers;

import de.cau.cs.kieler.kiml.grana.visualization.AbstractSimpleVisualizer;
import de.cau.cs.kieler.kiml.grana.visualization.Visualization;
import de.cau.cs.kieler.kiml.grana.visualization.VisualizationServices;
import de.cau.cs.kieler.kiml.service.grana.AnalysisData;

/**
 * The basic visualizer for the CSV format.
 * 
 * @author mri
 */
public class BasicCSVVisualizer extends AbstractSimpleVisualizer<String> {

    /** the error message for an invalid result type. */
    private static final String ERROR_WRONG_RESULT_FORMAT = "Invalid type";

    /**
     * {@inheritDoc}
     */
    public boolean canVisualize(final Object result) {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String visualize(final AnalysisData analysis,
            final Object result) {
        String str = "";
        if (result instanceof Object[]) {
            Object[] results = (Object[]) result;
            if (results.length == analysis.getComponents().size()) {
                boolean first = true;
                for (int i = 0; i < analysis.getComponents().size(); ++i) {
                    if (first) {
                        first = false;
                    } else {
                        str += ";";
                    }
                    Visualization visualization =
                            VisualizationServices.getInstance()
                                    .getVisualization("text", results[i]);
                    if (visualization == null) {
                        str += results[i].toString();
                    } else {
                        str += visualization.get(analysis, results[i]);
                    }
                }
            } else {
                str += ERROR_WRONG_RESULT_FORMAT;
                for (int i = 1; i < analysis.getComponents().size(); ++i) {
                    str += "; " + ERROR_WRONG_RESULT_FORMAT;
                }
            }
        } else {
            if (analysis.getComponents().size() > 1) {
                str += ERROR_WRONG_RESULT_FORMAT;
                for (int i = 1; i < analysis.getComponents().size(); ++i) {
                    str += ";" + ERROR_WRONG_RESULT_FORMAT;
                }
            } else {
                Visualization visualization =
                        VisualizationServices.getInstance().getVisualization(
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
}
