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

import de.cau.cs.kieler.kiml.grana.MinAvgMaxResult;

/**
 * A specialized visualizer for the {@code MinAvgMaxResult} result class using
 * html.
 * 
 * @author mri
 */
public class MinAvgMaxHtmlVisualizer extends MinAvgMaxVisualizer {

    /**
     * {@inheritDoc}
     */
    public boolean canVisualize(final Object result) {
        return (result instanceof MinAvgMaxResult<?, ?>);
    }

    /**
     * {@inheritDoc}
     */
    public String visualize(final Object result) {
        if (result instanceof MinAvgMaxResult<?, ?>) {
            MinAvgMaxResult<?, ?> minAvgMax = (MinAvgMaxResult<?, ?>) result;
            Object min = anonymousRound(minAvgMax.getMin());
            Object avg = anonymousRound(minAvgMax.getAvg());
            Object max = anonymousRound(minAvgMax.getMax());
            return "<b>Min:</b> " + min + "<br/><b>Avg:</b> " + avg
                    + "<br/><b>Max:</b> " + max;
        } else {
            return result.toString();
        }
    }

}
