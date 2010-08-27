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

import java.util.Collection;

/**
 * An analysis bundle can provide several analyses. It is required to notify
 * registered {@code IBundleChangedListener} about any added or removed
 * analyses.
 * 
 * @author mri
 */
public interface IAnalysisBundle {
    /**
     * Returns a collection of all analyses provided by this bundle.
     * 
     * @return the analyses
     */
    Collection<AbstractInfoAnalysis> getAnalyses();

    /**
     * Adds a listener to the bundle.
     * 
     * @param listener
     *            the listener
     */
    void addBundleChangedListener(final IBundleChangedListener listener);

    /**
     * Removes a listener from the bundle.
     * 
     * @param listener
     *            the listener
     */
    void removeBundleChangedListener(final IBundleChangedListener listener);
}
