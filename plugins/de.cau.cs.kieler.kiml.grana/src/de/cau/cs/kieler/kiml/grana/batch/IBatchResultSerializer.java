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

import java.io.OutputStream;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;

/**
 * The interface for all classes which implement a serializer for batch results.
 * 
 * @author mri
 */
public interface IBatchResultSerializer {

    /**
     * Serializes a batch result to the given output stream.
     * 
     * @param outputStream
     *            the output stream
     * @param batchResult
     *            the batch result
     * @param monitor
     *            the monitor
     * @throws Exception
     *             any kind of exception
     */
    void serialize(final OutputStream outputStream,
            final BatchResult batchResult, final IKielerProgressMonitor monitor)
            throws Exception;
}
