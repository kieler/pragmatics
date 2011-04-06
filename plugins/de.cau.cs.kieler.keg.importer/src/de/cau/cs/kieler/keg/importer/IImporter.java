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
package de.cau.cs.kieler.keg.importer;

import java.io.IOException;

import org.eclipse.core.runtime.IPath;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.model.m2m.TransformException;
import de.cau.cs.kieler.core.properties.MapPropertyHolder;
import de.cau.cs.kieler.keg.Node;

/**
 * The interface for all KEG importers.
 * 
 * @author mri
 */
public interface IImporter {

    /**
     * Performs the actual KEG import using the given options and retrieving the
     * source from the stream.
     * 
     * @param path
     *            the file path
     * @param isWorkspacePath
     *            true if the file path is relative to the workspace
     * @param options
     *            the import options
     * @param monitor
     *            the progress monitor
     * @return the KEG graph
     * @throws TransformException if the transformation fails for some reason
     * @throws IOException if reading from the input stream fails for some reason
     */
    Node doImport(final String path, final boolean isWorkspacePath,
            final MapPropertyHolder options, final IKielerProgressMonitor monitor)
            throws IOException, TransformException;

    /**
     * Performs a post processing on the created KEG model instance file using
     * the given options.
     * 
     * @param modelPath
     *            the model file path
     * @param options
     *            the options
     */
    void doModelPostProcess(final IPath modelPath,
            final MapPropertyHolder options);

    /**
     * Performs a post process on the created KEG diagram file using the given
     * options; is only called when a diagram file is created automatically in
     * the import process.
     * 
     * @param diagramPath
     *            the diagram file path
     * @param options
     *            the options
     */
    void doDiagramPostProcess(final IPath diagramPath,
            final MapPropertyHolder options);
}
