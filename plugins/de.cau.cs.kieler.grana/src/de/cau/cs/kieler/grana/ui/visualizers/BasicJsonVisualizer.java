/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2016 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.grana.ui.visualizers;

import de.cau.cs.kieler.grana.AnalysisData;
import de.cau.cs.kieler.grana.ui.visualization.AbstractSimpleVisualizer;
import de.cau.cs.kieler.grana.ui.visualization.Visualization;
import de.cau.cs.kieler.grana.ui.visualization.VisualizationService;

/**
 * The basic visualizer for the Json format.
 * 
 * @author uru
 */
public class BasicJsonVisualizer extends AbstractSimpleVisualizer<String> {

    /** the error message for an invalid result type. */
    private static final String ERROR_WRONG_RESULT_FORMAT = "Invalid type";

    private static final String QUOTE = "\"";

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

        str.append(QUOTE + analysis.getName() + QUOTE + ": ");
        if (result instanceof Object[]) {
            Object[] results = (Object[]) result;
            if (results.length == analysis.getComponents().size()) {
                str.append("{ ");
                boolean first = true;
                for (int i = 0; i < analysis.getComponents().size(); ++i) {
                    if (first) {
                        first = false;
                    } else {
                        str.append(", ");
                    }
                    str.append(QUOTE + analysis.getComponents().get(i).getFirst() + QUOTE + ": "
                            + QUOTE);
                    Visualization visualization =
                            VisualizationService.getInstance().getVisualization("text", results[i]);
                    if (visualization == null) {
                        str.append(results[i].toString());
                    } else {
                        str.append(visualization.get(analysis, results[i]).toString());
                    }
                    str.append(QUOTE);
                }
                str.append(" }");
            } else {
                str.append(QUOTE + ERROR_WRONG_RESULT_FORMAT + QUOTE);
            }
        } else {
            if (analysis.getComponents().size() > 1) {
                str.append(QUOTE + ERROR_WRONG_RESULT_FORMAT + QUOTE);
            } else {
                str.append(QUOTE);
                Visualization visualization =
                        VisualizationService.getInstance().getVisualization("text", result);
                if (visualization == null) {
                    str.append(result.toString());
                } else {
                    str.append(visualization.get(analysis, result).toString());
                }
                str.append(QUOTE);
            }
        }

        return str.toString();
    }
}
