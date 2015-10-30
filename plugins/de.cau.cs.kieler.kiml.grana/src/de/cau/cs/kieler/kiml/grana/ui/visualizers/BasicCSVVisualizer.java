/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.grana.ui.visualizers;

import de.cau.cs.kieler.kiml.grana.AnalysisData;
import de.cau.cs.kieler.kiml.grana.ui.visualization.AbstractSimpleVisualizer;
import de.cau.cs.kieler.kiml.grana.ui.visualization.Visualization;
import de.cau.cs.kieler.kiml.grana.ui.visualization.VisualizationService;

/**
 * The basic visualizer for the CSV format.
 * 
 * @author mri
 * @kieler.ignore (excluded from review process)
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
    public String visualize(final AnalysisData analysis, final Object result) {
        StringBuilder str = new StringBuilder();
        if (result instanceof Object[]) {
            Object[] results = (Object[]) result;
            if (results.length == analysis.getComponents().size()) {
                boolean first = true;
                for (int i = 0; i < analysis.getComponents().size(); ++i) {
                    if (first) {
                        first = false;
                    } else {
                        str.append(";");
                    }
                    Visualization visualization = VisualizationService.getInstance()
                            .getVisualization("text", results[i]);
                    if (visualization == null) {
                        str.append(results[i].toString());
                    } else {
                        str.append(visualization.get(analysis, results[i]));
                    }
                }
            } else {
                str.append(ERROR_WRONG_RESULT_FORMAT);
                for (int i = 1; i < analysis.getComponents().size(); ++i) {
                    str.append("; ").append(ERROR_WRONG_RESULT_FORMAT);
                }
            }
        } else {
            if (analysis.getComponents().size() > 1) {
                str.append(ERROR_WRONG_RESULT_FORMAT);
                for (int i = 1; i < analysis.getComponents().size(); ++i) {
                    str.append(";").append(ERROR_WRONG_RESULT_FORMAT);
                }
            } else {
                Visualization visualization = VisualizationService.getInstance().getVisualization(
                        "text", result);
                if (visualization == null) {
                    str.append(result.toString());
                } else {
                    str.append(visualization.get(analysis, result));
                }
            }
        }
        return str.toString();
    }
}
