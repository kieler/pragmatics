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

import java.util.List;

/**
 * The interface for all result visualization methods. A visualization method is
 * of a specific visualization type and receives appropriate visualizations.
 * 
 * @author mri
 */
public interface IVisualizationMethod {

    /**
     * Performs the visualization using the given type and visualizers.
     * 
     * @param type
     *            the visualization type
     * @param visualizations
     *            the encapsualted visualizer
     */
    void visualize(final String type,
            final List<BoundVisualization> visualizations);
}
