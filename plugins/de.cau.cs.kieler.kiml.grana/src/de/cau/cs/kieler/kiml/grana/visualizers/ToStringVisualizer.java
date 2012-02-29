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

import de.cau.cs.kieler.kiml.grana.visualization.AbstractSimpleVisualizer;
import de.cau.cs.kieler.kiml.service.grana.AnalysisData;

/**
 * The simplest possible visualizer. Returns the result of the {@code toString}
 * method.
 * 
 * @author mri
 */
public class ToStringVisualizer extends AbstractSimpleVisualizer<String> {

    /** the message that is displayed on a null result. */
    private static final String MESSAGE_NULL_RESULT = "No result";

    /**
     * {@inheritDoc}
     */
    public boolean canVisualize(final Object result) {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public String visualize(final AnalysisData analysis,
            final Object result) {
        if (result == null) {
            return MESSAGE_NULL_RESULT;
        }
        return result.toString();
    }

}
