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
package de.cau.cs.kieler.keg.importer.importer;

import java.io.IOException;
import java.io.InputStream;

import org.graphdrawing.graphml.util.GraphMLResourceFactoryImpl;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.model.m2m.TransformException;
import de.cau.cs.kieler.core.properties.MapPropertyHolder;
import de.cau.cs.kieler.keg.Node;
import de.cau.cs.kieler.keg.importer.AbstractImporter;
import de.cau.cs.kieler.keg.importer.ImportUtil;

/**
 * A KEG importer for the GraphML file format.
 * 
 * @author mri
 */
public class GraphMLImporter extends AbstractImporter {

    /** the supported file extensions. */
    private static final String[] SUPPORTED_FILE_EXTENSIONS = { "graphml" };
    /** the xtend transformation file. */
    private static final String XTEND_TRANSFORMATION_FILE =
            "/transformations/graphml2keg.ext";
    /** the xtend extension which is performing the transformation. */
    private static final String XTEND_TRANSFORMATION = "transform";
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "GraphML";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDefaultExtension() {
        return SUPPORTED_FILE_EXTENSIONS[0];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] getExtensions() {
        return SUPPORTED_FILE_EXTENSIONS;
    }

    /**
     * {@inheritDoc}
     */
    public Node doImport(final InputStream stream,
            final MapPropertyHolder options,
            final IKielerProgressMonitor monitor) {
        Node node = null;
        try {
            node =
                    ImportUtil.transformModel2KEGGraph(
                            XTEND_TRANSFORMATION_FILE, XTEND_TRANSFORMATION,
                            null, stream, new GraphMLResourceFactoryImpl(),
                            monitor, "org.graphdrawing.graphml.GraphMLPackage");
        } catch (IOException e) {
            throw new RuntimeException(ERROR_MESSAGE_IMPORT_FAILED, e);
        } catch (TransformException e) {
            throw new RuntimeException(ERROR_MESSAGE_IMPORT_FAILED, e);
        }
        return node;
    }
}
