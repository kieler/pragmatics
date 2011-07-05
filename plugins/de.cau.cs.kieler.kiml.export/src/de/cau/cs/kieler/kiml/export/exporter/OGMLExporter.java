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
import java.util.LinkedList;
import java.util.List;

import net.ogdf.ogml.util.OgmlResourceFactoryImpl;

import de.cau.cs.kieler.core.WrappedException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.model.m2m.TransformException;
import de.cau.cs.kieler.core.properties.MapPropertyHolder;
import de.cau.cs.kieler.kiml.export.AbstractExporter;
import de.cau.cs.kieler.kiml.export.ExportUtil;
import de.cau.cs.kieler.kiml.export.ExporterOption;
import de.cau.cs.kieler.kiml.export.util.XtendUtil;

/**
 * A graph exporter for the OGML file format.
 * 
 * @author mri
 */
public class OGMLExporter extends AbstractExporter {

    /** the supported file extensions. */
    private static final String[] SUPPORTED_FILE_EXTENSIONS = { "ogml" }; //$NON-NLS-1$
    /** the xtend transformation file. */
    private static final String XTEND_TRANSFORMATION_FILE =
            "kgraph2ogml.ext"; //$NON-NLS-1$
    /** the xtend extension which is performing the transformation. */
    private static final String XTEND_TRANSFORMATION = "transform"; //$NON-NLS-1$

    /** the option for the include of layout information. */
    private static final ExporterOption<Boolean> OPTION_LAYOUT_INFORMATION =
            new ExporterOption<Boolean>("ogml.layoutInformation", //$NON-NLS-1$
                    Messages.OGMLExporter_include_layout_info_message, true);

    /**
     * Constructs an OGML exporter.
     */
    public OGMLExporter() {
        addOption(OPTION_LAYOUT_INFORMATION);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return Messages.OGMLExporter_ogml_name;
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
        monitor.begin(Messages.OGMLExporter_export_kgraph_to_ogml_task, 1);

        try {
            List<Object> parameters = new LinkedList<Object>();
            parameters
                    .add(options.getProperty(OPTION_LAYOUT_INFORMATION));
            XtendUtil.resetGenerators();
            ExportUtil.transformKGraph2Model(
                    XTEND_TRANSFORMATION_FILE,
                    XTEND_TRANSFORMATION,
                    parameters,
                    graph,
                    stream,
                    new OgmlResourceFactoryImpl(),
                    monitor.subTask(1),
                    "net.ogdf.ogml.OgmlPackage", //$NON-NLS-1$
                    "de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataPackage"); //$NON-NLS-1$

        } catch (IOException e) {
            throw new WrappedException(e, ERROR_MESSAGE_EXPORT_FAILED);
        } catch (TransformException e) {
            throw new WrappedException(e, ERROR_MESSAGE_EXPORT_FAILED);
        } finally {
            monitor.done();
        }
    }

}
