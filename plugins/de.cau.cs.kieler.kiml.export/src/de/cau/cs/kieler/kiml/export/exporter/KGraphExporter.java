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

import de.cau.cs.kieler.core.WrappedException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.properties.MapPropertyHolder;
import de.cau.cs.kieler.kiml.export.AbstractExporter;

/**
 * A graph exporter for the raw KGraph.
 * 
 * @author mri
 */
public class KGraphExporter extends AbstractExporter {

    /** the supported file extensions. */
    private static final String[] SUPPORTED_FILE_EXTENSIONS = { "kgraph" }; //$NON-NLS-1$
    /** a dummy file extension. */
    private static final String FILE_EXT_DUMMY = "dummyext"; //$NON-NLS-1$

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return Messages.KGraphExporter_kgraph_name;
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
        monitor.begin(Messages.KGraphExporter_export_kgraph_task, 1);
        try {
            ResourceSet resourceSet = new ResourceSetImpl();
            Resource resource =
                    resourceSet.createResource(URI.createURI("http:///My." //$NON-NLS-1$
                            + FILE_EXT_DUMMY));
            resource.getContents().add(graph);
            Map<String, Object> resourceOptions = new HashMap<String, Object>();
            resourceOptions.put(XMLResource.OPTION_ENCODING, "UTF-8"); //$NON-NLS-1$
            resourceOptions.put(XMLResource.OPTION_FORMATTED, true);
            // write to the stream
            resource.save(stream, resourceOptions);
        } catch (IOException e) {
            throw new WrappedException(e, ERROR_MESSAGE_EXPORT_FAILED);
        } finally {
            monitor.done();
        }
    }
}
