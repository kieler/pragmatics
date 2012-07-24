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

import org.eclipse.emf.common.util.WrappedException;
import org.graphdrawing.graphml.util.GraphMLResourceFactoryImpl;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.model.m2m.TransformException;
import de.cau.cs.kieler.core.properties.IPropertyHolder;
import de.cau.cs.kieler.keg.Node;
import de.cau.cs.kieler.keg.importer.AbstractImporter;
import de.cau.cs.kieler.keg.importer.ImportUtil;

/**
 * A KEG importer for the GraphML file format.
 * 
 * @author mri
 * @kieler.ignore (excluded from review process)
 */
public class GraphMLImporter extends AbstractImporter {

    /** the supported file extensions. */
    private static final String[] SUPPORTED_FILE_EXTENSIONS = { "graphml" }; //$NON-NLS-1$
    /** the xtend transformation file. */
    private static final String XTEND_TRANSFORMATION_FILE = "graphml2keg.ext"; //$NON-NLS-1$
    /** the xtend extension which is performing the transformation. */
    private static final String XTEND_TRANSFORMATION = "transform"; //$NON-NLS-1$

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return Messages.GraphMLImporter_graphml_name;
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
    public Node doImport(final InputStream inputStream, final IPropertyHolder options,
            final IKielerProgressMonitor monitor) {
        Node node = null;
        try {
        node =
                ImportUtil.transformModel2KEGGraph(XTEND_TRANSFORMATION_FILE, XTEND_TRANSFORMATION,
                        null, inputStream, new GraphMLResourceFactoryImpl(), monitor,
                        "org.graphdrawing.graphml.GraphMLPackage"); //$NON-NLS-1$
        } catch (IOException e) {
            throw new WrappedException(Messages.GraphMLImporter_io_error, e);
        } catch (TransformException e) {
            throw new WrappedException(Messages.GraphMLImporter_transformation_error, e);
        }
        return node;
    }
}
