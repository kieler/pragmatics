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
package de.cau.cs.kieler.kiml.export.exporter;

import java.io.IOException;
import java.io.OutputStream;

import org.graphdrawing.graphml.util.GraphMLResourceFactoryImpl;

import de.cau.cs.kieler.core.WrappedException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.model.m2m.TransformException;
import de.cau.cs.kieler.core.properties.MapPropertyHolder;
import de.cau.cs.kieler.kiml.export.AbstractExporter;
import de.cau.cs.kieler.kiml.export.ExportUtil;
import de.cau.cs.kieler.kiml.export.util.XtendUtil;

/**
 * A graph exporter for the GraphML file format.
 * 
 * @author mri
 */
public class GraphMLExporter extends AbstractExporter {

    /** the supported file extensions. */
    private static final String[] SUPPORTED_FILE_EXTENSIONS = { "graphml" }; //$NON-NLS-1$
    /** the xtend transformation file. */
    private static final String XTEND_TRANSFORMATION_FILE =
            "kgraph2graphml.ext"; //$NON-NLS-1$
    /** the xtend extension which is performing the transformation. */
    private static final String XTEND_TRANSFORMATION = "transform"; //$NON-NLS-1$

    /**
     * Constructs a GML exporter.
     */
    public GraphMLExporter() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return Messages.GraphMLExporter_graphml_name;
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
    public void doExport(final KNode graph, final OutputStream stream,
            final MapPropertyHolder options,
            final IKielerProgressMonitor monitor) {
        monitor.begin(Messages.GraphMLExporter_export_kgraph_to_graphml_task, 1);

        try {
            XtendUtil.resetGenerators();
            ExportUtil.transformKGraph2Model(
                    XTEND_TRANSFORMATION_FILE,
                    XTEND_TRANSFORMATION,
                    null,
                    graph,
                    stream,
                    new GraphMLResourceFactoryImpl(),
                    monitor.subTask(1),
                    "org.graphdrawing.graphml.GraphMLPackage"); //$NON-NLS-1$
        } catch (IOException e) {
            throw new WrappedException(e, ERROR_MESSAGE_EXPORT_FAILED);
        } catch (TransformException e) {
            throw new WrappedException(e, ERROR_MESSAGE_EXPORT_FAILED);
        } finally {
            monitor.done();
        }
    }
}
