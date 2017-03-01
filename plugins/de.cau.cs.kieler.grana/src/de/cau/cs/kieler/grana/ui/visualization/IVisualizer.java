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
package de.cau.cs.kieler.grana.ui.visualization;

import de.cau.cs.kieler.grana.AnalysisData;

/**
 * The interface for all analysis result visualizer.
 * 
 * @author mri
 * @kieler.ignore (excluded from review process)
 * @param <S> the return type of the visualization
 * @param <T> the parameter type for the visualization
 */
public interface IVisualizer<S, T> {

    /**
     * Returns whether this class can visualize the given analysis result.
     * 
     * @param result
     *            the result of an analysis
     * @return true if this class can visualize the result
     */
    boolean canVisualize(Object result);

    /**
     * Visualizes the given analysis result by modifying the given parameter
     * and/or returning an object that represents the visualization, if
     * {@code canVisualize} returns true for the given result.
     * 
     * @param analysis
     *            the analysis
     * @param result
     *            the result to visualize
     * @param parameter
     *            the parameter
     * @return an object that represents the visualization or null if not
     *         applicable
     */
    S visualize(AnalysisData analysis, Object result, T parameter);
}
