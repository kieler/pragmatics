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
 * Interface for listeners to an {@code IAnalysesBundle}.
 * 
 * @author mri
 */
public interface IBundleChangedListener {

    /**
     * Called when an analysis is added to a bundle.
     * 
     * @param analysis
     *            the analysis
     */
    void analysisAdded(AbstractInfoAnalysis analysis);

    /**
     * Called when an analysis is removed from a bundle.
     * 
     * @param analysis
     *            the analysis
     */
    void analysisRemoved(AbstractInfoAnalysis analysis);

}
