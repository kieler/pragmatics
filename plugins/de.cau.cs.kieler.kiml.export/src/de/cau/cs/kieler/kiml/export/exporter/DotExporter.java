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
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;

import de.cau.cs.kieler.core.WrappedException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.properties.MapPropertyHolder;
import de.cau.cs.kieler.kiml.export.AbstractExporter;
import de.cau.cs.kieler.kiml.graphviz.dot.dot.GraphvizModel;
import de.cau.cs.kieler.kiml.graphviz.dot.transform.Attributes;
import de.cau.cs.kieler.kiml.graphviz.dot.transform.Command;
import de.cau.cs.kieler.kiml.graphviz.dot.transform.KGraphDotTransformation;

/**
 * A graph exporter for the Dot format.
 * 
 * @author mri
 */
public class DotExporter extends AbstractExporter {

    /** the supported file extensions. */
    private static final String[] SUPPORTED_FILE_EXTENSIONS = { "dot" }; //$NON-NLS-1$

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return Messages.DotExporter_dot_name;
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
            final MapPropertyHolder options, final IKielerProgressMonitor monitor) {
        monitor.begin(Messages.DotExporter_export_kgraph_to_dot_task, 2);
        try {
            // transform the graph
            KGraphDotTransformation transformation = new KGraphDotTransformation(graph);
            GraphvizModel dotGraph = transformation.transform(Command.DOT, monitor.subTask(1));
            // write to file
            XtextResourceSet resourceSet = transformation.createResourceSet();
            XtextResource resource = (XtextResource) resourceSet.createResource(URI
                    .createURI("http:///My.graphviz_dot")); //$NON-NLS-1$
            resource.getContents().add(dotGraph);
            Map<String, Object> resourceOptions = new HashMap<String, Object>();
            resourceOptions.put(XMLResource.OPTION_ENCODING, "UTF-8"); //$NON-NLS-1$
            // write to the stream
            resource.save(stream, resourceOptions);
        } catch (IOException e) {
            throw new WrappedException(e, ERROR_MESSAGE_EXPORT_FAILED);
        } finally {
            monitor.done();
        }
    }

}
