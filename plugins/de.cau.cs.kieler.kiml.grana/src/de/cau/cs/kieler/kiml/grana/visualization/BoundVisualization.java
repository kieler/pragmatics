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
package de.cau.cs.kieler.kiml.grana.visualization;

import de.cau.cs.kieler.kiml.service.grana.AnalysisData;

/**
 * This class binds an analysis result to a visualization.
 * 
 * @author mri
 */
public class BoundVisualization {

    /** the analysis. */
    private AnalysisData analysis;
    /** the analysis result. */
    private Object result;
    /** the visualization. */
    private Visualization visualization;

    /**
     * Constructs a BoundVisualization.
     * 
     * @param theAnalysis
     *            the analysis
     * @param theResult
     *            the analysis result
     * @param theVisualization
     *            the visualization
     */
    public BoundVisualization(final AnalysisData theAnalysis,
            final Object theResult, final Visualization theVisualization) {
        analysis = theAnalysis;
        result = theResult;
        visualization = theVisualization;
    }

    /**
     * Performs the visualization and returns the result. If this method can be
     * called depends on the implementation of the visualizer.
     * 
     * @param <S>
     *            the visualization result type
     * @return the visualization result
     */
    public <S> S get() {
        return visualization.get(analysis, result);
    }

    /**
     * Performs the visualization by attaching it in some way to the given
     * parameter.
     * 
     * @param <S>
     *            the visualization result type
     * @param parameter
     *            the parameter
     * @return the visualization result
     */
    public <S> S apply(final Object parameter) {
        return visualization.apply(analysis, result, parameter);
    }

    /**
     * Returns the analysis which result is visualized.
     * 
     * @return the analysis
     */
    public AnalysisData getAnalysis() {
        return analysis;
    }
}
