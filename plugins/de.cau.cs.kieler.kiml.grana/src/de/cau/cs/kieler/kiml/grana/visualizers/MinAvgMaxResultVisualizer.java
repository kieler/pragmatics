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

import de.cau.cs.kieler.core.math.KielerMath;
import de.cau.cs.kieler.kiml.grana.AbstractAnalysisResultVisualizer;
import de.cau.cs.kieler.kiml.grana.MinAvgMaxResult;

/**
 * A specialized visualizer for the {@code MinAvgMaxResult} result class.
 * 
 * @author mri
 */
public class MinAvgMaxResultVisualizer extends AbstractAnalysisResultVisualizer {

    private static final int MAX_DECIMAL_PLACE = 2;

    /**
     * {@inheritDoc}
     */
    public boolean canVisualize(final Object result) {
        return (result instanceof MinAvgMaxResult<?, ?>);
    }

    private static final float D = (float) KielerMath.pow(10, MAX_DECIMAL_PLACE);
    
    private Object anonymousRound(final Object o) {
        if (o instanceof Float) {
            Float f = (Float) o;
            f = (float) Math.round(f * D); 
            f /= D;
            return f;
        } else if (o instanceof Double) {
            Double f = (Double) o;
            f = (double) Math.round(f * D); 
            f /= D;
            return f;
        }
        return o;
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
            return "<b>Min:</b> " + min + "<br/><b>Avg:</b> "
                    + avg + "<br/><b>Max:</b> " + max;
        } else {
            return result.toString();
        }
    }

}
