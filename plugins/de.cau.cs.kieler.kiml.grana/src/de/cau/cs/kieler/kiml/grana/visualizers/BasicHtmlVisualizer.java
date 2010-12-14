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

import de.cau.cs.kieler.kiml.grana.AbstractInfoAnalysis;
import de.cau.cs.kieler.kiml.grana.visualization.IVisualizer;
import de.cau.cs.kieler.kiml.grana.visualization.Visualization;
import de.cau.cs.kieler.kiml.grana.visualization.VisualizationServices;

/**
 * The html visualizer which simply uses the text visualization as it is.
 * 
 * @author mri
 */
public class BasicHtmlVisualizer implements IVisualizer<String, Object> {

    /**
     * {@inheritDoc}
     */
    public boolean canVisualize(final Object result) {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public String visualize(final AbstractInfoAnalysis analysis,
            final Object result, final Object parameter) {
        Visualization textVisualization =
                VisualizationServices.getInstance().getVisualization("text",
                        result);
        if (textVisualization == null) {
            return result.toString();
        }
        return textVisualization.get(analysis, result);
    }

}
