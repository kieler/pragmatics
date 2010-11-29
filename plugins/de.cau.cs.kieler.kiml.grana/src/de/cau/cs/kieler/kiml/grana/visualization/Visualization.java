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

import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a concrete visualization including the necessary
 * dependencies.
 * 
 * @author mri
 */
public class Visualization {

    /** the selected dependencies for the visualization. */
    private Map<String, Visualization> dependencies =
            new HashMap<String, Visualization>();
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
     * @param result
     *            the analysis result
     * @return the visualization result
     */
    @SuppressWarnings("unchecked")
    public <S> S get(final Object result) {
        return (S) visualizer.visualize(result, dependencies, null);
    }

    /**
     * Performs the visualization by attaching it in some way to the given
     * parameter.
     * 
     * @param <S>
     *            the result type
     * @param result
     *            the analysis result
     * @param parameter
     *            the parameter
     * @return the visualization result
     */
    @SuppressWarnings("unchecked")
    public <S> S apply(final Object result, final Object parameter) {
        return (S) visualizer.visualize(result, dependencies, parameter);
    }

    /**
     * Adds a dependency for the given type. There can only be one dependency
     * for a given type.
     * 
     * @param type
     *            the type
     * @param dependency
     *            the dependency
     */
    public void addDependency(final String type, final Visualization dependency) {
        dependencies.put(type, dependency);
    }
}
