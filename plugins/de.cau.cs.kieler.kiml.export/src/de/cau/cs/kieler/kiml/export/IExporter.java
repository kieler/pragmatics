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
package de.cau.cs.kieler.kiml.export;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;

/**
 * The interface for all graph exporters.
 * 
 * @author mri
 */
public interface IExporter {

    /**
     * Performs the actual graph export using the given configuration.
     * 
     * @param monitor
     *            the progress monitor
     * @param configuration
     *            the export configuration
     * @param graph
     *            the graph
     * 
     * @throws KielerException
     *             thrown when the export failed
     */
    void doExport(final IKielerProgressMonitor monitor,
            final ExporterConfiguration configuration, final KNode graph)
            throws KielerException;
}
