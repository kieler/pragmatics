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

import java.io.OutputStream;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.properties.MapPropertyHolder;

/**
 * The interface for all graph exporters.
 * 
 * @author mri
 */
public interface IExporter {

    /**
     * Performs the actual graph export by using the given options and writing
     * the result to the stream.
     * 
     * @param graph
     *            the graph
     * @param stream
     *            the output stream
     * @param options
     *            the export options
     * @param monitor
     *            the progress monitor
     */
    void doExport(final KNode graph, final OutputStream stream,
            final MapPropertyHolder options,
            final IKielerProgressMonitor monitor);
}
