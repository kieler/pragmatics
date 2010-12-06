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
import java.util.LinkedList;
import java.util.List;

import net.ogdf.ogml.util.OgmlResourceFactoryImpl;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.model.transformation.TransformationException;
import de.cau.cs.kieler.kiml.export.AbstractExporter;
import de.cau.cs.kieler.kiml.export.ExportUtil;
import de.cau.cs.kieler.kiml.export.ExporterConfiguration;
import de.cau.cs.kieler.kiml.export.ExporterOption;
import de.cau.cs.kieler.kiml.export.util.XtendUtil;

/**
 * A graph exporter for the OGML file format.
 * 
 * @author mri
 */
public class OGMLExporter extends AbstractExporter {

    /** the supported file extensions. */
    private static final String[] SUPPORTED_FILE_EXTENSIONS = { "ogml" };
    /** the xtend transformation file. */
    private static final String XTEND_TRANSFORMATION_FILE =
            "/transformations/kgraph2ogml.ext";
    /** the xtend extension which is performing the transformation. */
    private static final String XTEND_TRANSFORMATION = "transform";

    /** the option for the include of layout information. */
    private static final ExporterOption<Boolean> OPTION_LAYOUT_INFORMATION =
            new ExporterOption<Boolean>("ogml.layoutInformation",
                    "Include layout information?", true);

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
        return "OGML";
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
    @Override
    public void doExport(final IKielerProgressMonitor monitor,
            final ExporterConfiguration configuration, final KNode graph)
            throws KielerException {
        monitor.begin("Exporting KGraph to OGML", 1);

        try {
            List<Object> parameters = new LinkedList<Object>();
            parameters
                    .add(configuration.getProperty(OPTION_LAYOUT_INFORMATION));
            XtendUtil.resetGenerators();
            ExportUtil.transformKGraph2Model(
                    monitor.subTask(1),
                    XTEND_TRANSFORMATION_FILE,
                    XTEND_TRANSFORMATION,
                    parameters,
                    graph,
                    ExportUtil.createOutputStream(
                            configuration.getExportFilePath(),
                            configuration.isWorkspacePath()),
                    new OgmlResourceFactoryImpl(),
                    "de.cau.cs.kieler.core.kgraph.KGraphPackage",
                    "net.ogdf.ogml.OgmlPackage",
                    "de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataPackage");

        } catch (IOException e) {
            throw new KielerException(ERROR_MESSAGE_EXPORT_FAILED, e);
        } catch (TransformationException e) {
            throw new KielerException(ERROR_MESSAGE_EXPORT_FAILED, e);
        } finally {
            monitor.done();
        }
    }

}
