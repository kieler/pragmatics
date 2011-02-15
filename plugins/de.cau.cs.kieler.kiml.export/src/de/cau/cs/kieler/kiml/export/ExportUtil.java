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
package de.cau.cs.kieler.kiml.export;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.osgi.framework.Bundle;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.model.m2m.ITransformationContext;
import de.cau.cs.kieler.core.model.m2m.TransformationDescriptor;
import de.cau.cs.kieler.core.model.xtend.m2m.XtendTransformationContext;
import de.cau.cs.kieler.core.model.xtend.transformation.TransformationException;

/**
 * A utility class for graph export.
 * 
 * @author mri
 */
public final class ExportUtil {

    /** the error message for a wrong xtend transformation result. */
    private static final String ERROR_MESSAGE_WRONG_XTEND_RESULT =
            "The xtend transformation returned a result that wasn't of type 'EObject'.";
    /** a dummy file extension. */
    private static final String FILE_EXT_DUMMY = "dummyext";

    /**
     * A private constructor to make the class not instantiable.
     */
    private ExportUtil() {
        // do nothing
    }

    /**
     * Creates an output stream to a file that is located in the workspace or in
     * the file system.
     * 
     * @param path
     *            the file path
     * @param isWorkspacePath
     *            true if the file path is relative to the workspace
     * @return the output stream
     * @throws IOException
     *             thrown when the stream could not be opened
     */
    public static OutputStream createOutputStream(final String path,
            final boolean isWorkspacePath) throws IOException {
        if (isWorkspacePath) {
            // workspace path
            IPath filePath = new Path(path);
            URI fileURI =
                    URI.createPlatformResourceURI(filePath.toOSString(), true);
            URIConverter uriConverter = new ExtensibleURIConverterImpl();
            OutputStream outputStream =
                    uriConverter.createOutputStream(fileURI);
            return outputStream;
        } else {
            // file system path
            File file = new File(path);
            FileOutputStream outputStream = new FileOutputStream(file);
            return outputStream;
        }
    }

    private static final int MONITOR_TRANSFORMATION_WORK = 3;

    /**
     * Transforms a kgraph into another model using a given Xtend transformation
     * file, and writes the model to the output stream.
     * 
     * @param xtendFile
     *            the xtend file containing the transformation
     * @param extension
     *            the name of the extension that starts the transformation
     *            inside the xtend file
     * @param parameters
     *            a list of additional parameters for the transformation or null
     *            if no additional parameters are required
     * @param node
     *            the kgraph parent node
     * @param outputStream
     *            the output stream the target model instance will be serialized
     *            to
     * @param resourceFactory
     *            the resource factory used to serialize the model
     * @param monitor
     *            the progress monitor
     * @param involvedMetamodels
     *            the metamodels involved in the transformation
     * @throws IOException
     *             thrown when the the xtend file could not be found or opened
     * @throws TransformationException
     *             thrown when the execution of the xtend transformation failed
     */
    public static void transformKGraph2Model(final String xtendFile,
            final String extension, final List<Object> parameters,
            final KNode node, final OutputStream outputStream,
            final Resource.Factory resourceFactory,
            final IKielerProgressMonitor monitor,
            final String... involvedMetamodels) throws IOException,
            TransformationException {
        monitor.begin("KGraph Model2Model transformation",
                MONITOR_TRANSFORMATION_WORK);

        // find the xtend file
        Bundle bundle = ExportPlugin.getDefault().getBundle();
        IPath path = new Path(xtendFile);
        URL url = FileLocator.find(bundle, path, null);
        String xtendFilePath = FileLocator.resolve(url).getFile();
        // initialize the xtend framework
        Object[] params = null;
        if (parameters != null && parameters.size() > 0) {
            // additional parameters
            params = new Object[parameters.size() + 1];
            params[0] = node;
            int i = 1;
            for (Object parameter : parameters) {
                params[i++] = parameter;
            }
        } else {
            params = new Object[1];
            params[0] = node;
        }
        // assemble the list of required metamodels
        String[] metamodels = new String[involvedMetamodels.length + 1];
        metamodels[0] = "de.cau.cs.kieler.core.kgraph.KGraphPackage";
        int i = 1;
        for (String metamodel : involvedMetamodels) {
            metamodels[i++] = metamodel; 
        }
        // initialize the transformation
        ITransformationContext transformationContext = new XtendTransformationContext(
                xtendFilePath,
                metamodels,
                null,
                null
        );
        TransformationDescriptor transformationDescriptor = new TransformationDescriptor(
                extension,
                params
        );
        // execute the transformation
        transformationContext.execute(transformationDescriptor);
        Object resultModel = transformationDescriptor.getResult();
        monitor.worked(2);
        // serialize the model
        EObject model = null;
        if (resultModel instanceof EObject) {
            model = (EObject) resultModel;
        } else {
            throw new RuntimeException(ERROR_MESSAGE_WRONG_XTEND_RESULT);
        }
        ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
                .put(FILE_EXT_DUMMY, resourceFactory);
        Resource resource =
                resourceSet.createResource(URI.createURI("http:///My."
                        + FILE_EXT_DUMMY));
        resource.getContents().add(model);
        Map<String, Object> options = new HashMap<String, Object>();
        // options.put(XMLResource.OPTION_SCHEMA_LOCATION, Boolean.FALSE);
        // options.put(XMLResource.OPTION_ESCAPE_USING_CDATA, Boolean.TRUE);
        options.put(XMLResource.OPTION_ENCODING, "UTF-8");
        options.put(XMLResource.OPTION_FORMATTED, true);
        // write to the stream
        resource.save(outputStream, options);
        monitor.done();
    }
}
