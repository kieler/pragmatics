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
 * The result for analyses which were canceled.
 * 
 * @author mri
 */
public class AnalysisCanceled extends AnalysisFailed {

    /** the message for a canceled analysis. */
    private static final String MESSAGE_ANALYSIS_CANCELED = "Analysis canceled";

    /**
     * {@inheritDoc}
     */
    public String toString() {
        return MESSAGE_ANALYSIS_CANCELED;
    }

}
