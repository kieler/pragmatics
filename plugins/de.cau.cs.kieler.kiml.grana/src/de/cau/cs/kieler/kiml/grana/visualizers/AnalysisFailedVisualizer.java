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

import de.cau.cs.kieler.kiml.grana.AbstractAnalysisResultVisualizer;
import de.cau.cs.kieler.kiml.grana.AnalysisCanceled;
import de.cau.cs.kieler.kiml.grana.AnalysisFailed;

/**
 * The visualizer for failed analyses.
 * 
 * @author mri
 */
public class AnalysisFailedVisualizer extends AbstractAnalysisResultVisualizer {

    /**
     * {@inheritDoc}
     */
    public boolean canVisualize(final Object result) {
        return result instanceof AnalysisFailed;
    }

    /**
     * {@inheritDoc}
     */
    public String visualize(final Object result) {
        if (result instanceof AnalysisCanceled) {
            return "<font color='orange'>" + result.toString() + "</font>";
        } else if (result instanceof AnalysisFailed) {
            return "<font color='red'>" + result.toString() + "</font>";
        }
        return result.toString();
    }

}
