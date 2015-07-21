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
import de.cau.cs.kieler.kiml.grana.AnalysisFailed;
import de.cau.cs.kieler.kiml.grana.ui.visualization.AbstractSimpleVisualizer;

/**
 * The html visualizer for failed analyses.
 * 
 * @author mri
 * @kieler.ignore (excluded from review process)
 */
public class AnalysisFailedHtmlVisualizer extends
        AbstractSimpleVisualizer<String> {

    /**
     * {@inheritDoc}
     */
    public boolean canVisualize(final Object result) {
        return result instanceof AnalysisFailed;
    }

    /**
     * {@inheritDoc}
     */
    public String visualize(final AnalysisData analysis,
            final Object result) {
        if (result instanceof AnalysisFailed) {
            AnalysisFailed analysisFailed = (AnalysisFailed) result;
            switch (analysisFailed.getType()) {
            case Dependency:
                return "<font color='blue'>" + result.toString() + "</font>";
            case Canceled:
                return "<font color='orange'>" + result.toString() + "</font>";
            case Failed:
            default:
                return "<font color='red'>" + result.toString() + "</font>";
            }
        }
        return null;
    }
}
