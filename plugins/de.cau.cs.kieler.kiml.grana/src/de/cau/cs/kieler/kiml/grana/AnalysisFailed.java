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
 * An analysis should return a subclass of this class if it failed to analyse
 * the graph and provide meaningful information through the {@code toString} method.
 * 
 * @author mri
 */
public class AnalysisFailed {

    /** the default message for a failed analysis. */
    private static final String DEFAULT_MESSAGE_ANALYSIS_FAILED = "Analysis failed";

    /**
     * {@inheritDoc}
     */
    public String toString() {
        return DEFAULT_MESSAGE_ANALYSIS_FAILED;
    }
}
