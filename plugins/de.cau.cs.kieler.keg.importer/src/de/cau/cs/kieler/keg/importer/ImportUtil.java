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
import java.io.InputStream;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.model.m2m.ITransformationContext;
import de.cau.cs.kieler.core.model.m2m.TransformException;
import de.cau.cs.kieler.core.model.m2m.TransformationDescriptor;
import de.cau.cs.kieler.core.model.xtend.m2m.XtendTransformationContext;
import de.cau.cs.kieler.keg.Node;

/**
 * A utility class for KEG import.
 * 
 * @author mri
 */
public final class ImportUtil {

    /** a dummy file extension. */
    private static final String FILE_EXT_DUMMY = "dummyext"; //$NON-NLS-1$

    /**
     * A private constructor to make the class not instantiable.
     */
    private ImportUtil() {
        // do nothing
    }

    /**
     * Transforms a model to a KEG graph using a given Xtend transformation file. The model instance
     * is read from an input stream.
     * 
     * @param xtendFile
     *            the xtend file containing the transformation
     * @param extension
     *            the name of the extension that starts the transformation inside the xtend file
     * @param parameters
     *            a list of additional parameters for the transformation or null if no additional
     *            parameters are required
     * @param inputStream
     *            the input stream the source model instance is read from
     * @param resourceFactory
     *            the resource factory used to read the model or null for the standard factory
     * @param monitor
     *            the progress monitor
     * @param involvedMetamodels
     *            the metamodels involved in the transformation
     * @return the parent node of the KEG graph
     * @throws IOException
     *             thrown when the the xtend file could not be found or opened
     * @throws TransformException
     *             thrown when the execution of the xtend transformation failed
     */
    public static Node transformModel2KEGGraph(final String xtendFile, final String extension,
            final List<Object> parameters, final InputStream inputStream,
            final Resource.Factory resourceFactory, final IKielerProgressMonitor monitor,
            final String... involvedMetamodels) throws IOException, TransformException {
        // read the model
        ResourceSet resourceSet = new ResourceSetImpl();
        if (resourceFactory != null) {
            resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
                    .put(FILE_EXT_DUMMY, resourceFactory);
        }
        Resource resource =
                resourceSet.createResource(URI.createURI("http:///My." + FILE_EXT_DUMMY)); //$NON-NLS-1$
        // Map<String, Object> options = new HashMap<String, Object>();
        // options.put(XMLResource.OPTION_ENCODING, "UTF-8");
        // read from the stream
        resource.load(inputStream, null);
        return transformModel2KEGGraph(xtendFile, extension, parameters, resource, monitor,
                involvedMetamodels);
    }
    
    /**
     * Transforms a model to a KEG graph using a given Xtend transformation file. The model instance
     * is read from a resource.
     * 
     * @param xtendFile
     *            the xtend file containing the transformation
     * @param extension
     *            the name of the extension that starts the transformation inside the xtend file
     * @param parameters
     *            a list of additional parameters for the transformation or null if no additional
     *            parameters are required
     * @param resource
     *            the resource from which to read the model
     * @param monitor
     *            the progress monitor
     * @param involvedMetamodels
     *            the metamodels involved in the transformation
     * @return the parent node of the KEG graph
     * @throws IOException
     *             thrown when the the xtend file could not be found or opened
     * @throws TransformException
     *             thrown when the execution of the xtend transformation failed
     */
    public static Node transformModel2KEGGraph(final String xtendFile, final String extension,
            final List<Object> parameters, final Resource resource,
            final IKielerProgressMonitor monitor, final String... involvedMetamodels)
            throws IOException, TransformException {
        monitor.begin(Messages.ImportUtil_m2m_task, 1);
        EObject model = resource.getContents().get(0);
        // initialize the xtend framework
        Object[] params = null;
        if (parameters != null && parameters.size() > 0) {
            // additional parameters
            params = new Object[parameters.size() + 1];
            params[0] = model;
            int i = 1;
            for (Object parameter : parameters) {
                params[i++] = parameter;
            }
        } else {
            params = new Object[1];
            params[0] = model;
        }
        // assemble the list of required metamodels
        String[] metamodels = new String[involvedMetamodels.length + 1];
        metamodels[0] = "de.cau.cs.kieler.keg.KEGPackage"; //$NON-NLS-1$
        int i = 1;
        for (String metamodel : involvedMetamodels) {
            metamodels[i++] = metamodel;
        }
        // initialize the transformation
        ITransformationContext transformationContext =
                new XtendTransformationContext(xtendFile, metamodels, null, null);
        TransformationDescriptor transformationDescriptor =
                new TransformationDescriptor(extension, params);
        // execute the transformation
        transformationContext.execute(transformationDescriptor);
        Object resultModel = transformationDescriptor.getResult();
        // serialize the model
        Node node = null;
        if (resultModel instanceof Node) {
            node = (Node) resultModel;
        } else {
            throw new RuntimeException(Messages.ImportUtil_no_node_error);
        }
        monitor.done();
        return node;
    }

}
