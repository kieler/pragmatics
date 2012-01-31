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
 * This class represents a concrete visualization.
 * 
 * @author mri
 */
public class Visualization {

    /** the visualizer in this visualization. */
    private IVisualizer<Object, Object> visualizer;

    /**
     * Constructs a Visualization.
     * 
     * @param theVisualizer
     *            the visualizer that handles the visualization
     */
    public Visualization(final IVisualizer<Object, Object> theVisualizer) {
        visualizer = theVisualizer;
    }

    /**
     * Performs the visualization and returns the result. If this method can be
     * called depends on the implementation of the visualizer.
     * 
     * @param <S>
     *            the result type
     * @param analysis
     *            the analysis
     * @param result
     *            the analysis result
     * @return the visualization result
     */
    @SuppressWarnings("unchecked")
    public <S> S get(final AnalysisData analysis, final Object result) {
        return (S) visualizer.visualize(analysis, result, null);
    }

    /**
     * Performs the visualization by attaching it in some way to the given
     * parameter.
     * 
     * @param <S>
     *            the result type
     * @param analysis
     *            the analysis
     * @param result
     *            the analysis result
     * @param parameter
     *            the parameter
     * @return the visualization result
     */
    @SuppressWarnings("unchecked")
    public <S> S apply(final AnalysisData analysis,
            final Object result, final Object parameter) {
        return (S) visualizer.visualize(analysis, result, parameter);
    }
}
