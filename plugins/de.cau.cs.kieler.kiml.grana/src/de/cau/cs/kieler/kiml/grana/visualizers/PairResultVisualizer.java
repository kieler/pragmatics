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

import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.grana.AbstractAnalysisResultVisualizer;

/**
 * A special visualizer for pairs.
 *
 * @author msp
 */
public class PairResultVisualizer extends AbstractAnalysisResultVisualizer {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canVisualize(final Object result) {
        return (result instanceof Pair<?, ?>);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String visualize(final Object result) {
        if (result instanceof Pair<?, ?>) {
            Pair<?, ?> pair = (Pair<?, ?>) result;
            return "(" + pair.getFirst() + ", " + pair.getSecond() + ")";
        } else {
            return result.toString();
        }
    }

}
