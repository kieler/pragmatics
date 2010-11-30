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
package de.cau.cs.kieler.kiml.grana.batch;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;

/**
 * The interface for KGraph providers.
 * 
 * @author mri
 * 
 * @param <T>
 *            the parameter type
 */
public interface IKGraphProvider<T> {

    /**
     * Returns a KGraph instance from a specific source using the parameter.
     * 
     * @param parameter
     *            the parameter
     * @param monitor
     *            the progress monitor
     * @return the KGraph instance
     * @throws Exception
     *             any kind of exception
     */
    KNode getKGraph(final T parameter, final IKielerProgressMonitor monitor)
            throws Exception;
}
