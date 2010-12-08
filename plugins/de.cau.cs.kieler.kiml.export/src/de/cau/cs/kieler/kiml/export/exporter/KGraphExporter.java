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
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.export.AbstractExporter;
import de.cau.cs.kieler.kiml.export.ExportUtil;
import de.cau.cs.kieler.kiml.export.ExporterConfiguration;

/**
 * A graph exporter for the raw KGraph.
 * 
 * @author mri
 */
public class KGraphExporter extends AbstractExporter {

    /** the supported file extensions. */
    private static final String[] SUPPORTED_FILE_EXTENSIONS = { "kgraph" };
    /** a dummy file extension. */
    private static final String FILE_EXT_DUMMY = "dummyext";

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "KGraph";
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
    public void doExport(final KNode graph,
            final ExporterConfiguration configuration,
            final IKielerProgressMonitor monitor) throws KielerException {
        monitor.begin("Exporting KGraph", 1);
        try {
            OutputStream outputStream =
                    ExportUtil.createOutputStream(
                            configuration.getExportFilePath(),
                            configuration.isWorkspacePath());
            ResourceSet resourceSet = new ResourceSetImpl();
            Resource resource =
                    resourceSet.createResource(URI.createURI("http:///My."
                            + FILE_EXT_DUMMY));
            resource.getContents().add(graph);
            Map<String, Object> options = new HashMap<String, Object>();
            options.put(XMLResource.OPTION_ENCODING, "UTF-8");
            options.put(XMLResource.OPTION_FORMATTED, true);
            // write to the stream
            resource.save(outputStream, options);
            outputStream.close();
        } catch (IOException e) {
            throw new KielerException(ERROR_MESSAGE_EXPORT_FAILED, e);
        } finally {
            monitor.done();
        }
    }
}
