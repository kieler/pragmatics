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
 * An abstract base class for result visualizers. It provides a simpler
 * interface that has to be implemented at the loss of functionality.
 * 
 * @author mri
 * 
 * @param <S>
 *            the result type of the visualization
 */
public abstract class AbstractSimpleVisualizer<S> implements
        IVisualizer<S, Object> {

    /**
     * {@inheritDoc}
     */
    public S visualize(final AnalysisData analysis,
            final Object result, final Object parameter) {
        return visualize(analysis, result);
    }

    /**
     * Visualizes the given analysis result by returning an object that
     * represents the visualization, if {@code canVisualize} returns true for
     * the given result.
     * 
     * @param analysis
     *            the analysis
     * @param result
     *            the analysis result
     * @return the visualization result
     */
    public abstract S visualize(final AnalysisData analysis,
            final Object result);
}
