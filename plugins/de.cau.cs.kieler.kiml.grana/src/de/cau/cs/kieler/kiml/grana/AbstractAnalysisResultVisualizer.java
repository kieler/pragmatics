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
package de.cau.cs.kieler.kiml.grana;

/**
 * The abstract base class all analysis result visualizer have to extend.
 * 
 * @author mri
 */
public abstract class AbstractAnalysisResultVisualizer {

    /**
     * Returns whether this class can visualize the given analysis result.
     * 
     * @param result
     *            the result of an analysis
     * @return true if this class can visualize the result
     */
    public abstract boolean canVisualize(Object result);

    /**
     * Returns whether this class uses the main result dialog.
     * 
     * @return true if this class uses the result dialog
     */
    boolean usesResultDialog() {
        return true;
    }

    /**
     * Visualizes the given result object by returning html if {@code
     * canVisualize} returns true for the given result. Returns null if
     * {@code usesResultDialog} returns true.
     * 
     * @param result
     *            the result to visualize
     * @return the html to display in the result dialog or null if this
     *         visualizer displays the results in another way
     */
    public abstract String visualize(final Object result);
}
