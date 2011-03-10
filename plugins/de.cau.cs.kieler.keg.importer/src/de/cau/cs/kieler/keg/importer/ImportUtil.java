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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

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
import org.osgi.framework.Bundle;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.model.m2m.TransformException;
import de.cau.cs.kieler.core.model.xtend.transformation.ITransformationFramework;
import de.cau.cs.kieler.core.model.xtend.transformation.xtend.XtendTransformationFramework;
import de.cau.cs.kieler.keg.Node;

/**
 * A utility class for KEG import.
 * 
 * @author mri
 */
public final class ImportUtil {

    /** the error message for a wrong xtend transformation result. */
    private static final String ERROR_MESSAGE_WRONG_XTEND_RESULT =
            "The xtend transformation returned a result that wasn't of type 'Node'.";
    /** a dummy file extension. */
    private static final String FILE_EXT_DUMMY = "dummyext";

    /**
     * A private constructor to make the class not instantiable.
     */
    private ImportUtil() {
        // do nothing
    }

    /**
     * Creates an input stream to a file that is located in the workspace or in
     * the file system.
     * 
     * @param path
     *            the file path
     * @param isWorkspacePath
     *            true if the file path is relative to the workspace
     * @return the input stream
     * @throws IOException
     *             thrown when the stream could not be opened
     */
    public static InputStream createInputStream(final String path,
            final boolean isWorkspacePath) throws IOException {
        if (isWorkspacePath) {
            // workspace path
            IPath filePath = new Path(path);
            URI fileURI =
                    URI.createPlatformResourceURI(filePath.toOSString(), true);
            URIConverter uriConverter = new ExtensibleURIConverterImpl();
            InputStream inputStream = uriConverter.createInputStream(fileURI);
            return inputStream;
        } else {
            // file system path
            File file = new File(path);
            FileInputStream inputStream = new FileInputStream(file);
            return inputStream;
        }
    }

    private static final int MONITOR_TRANSFORMATION_WORK = 3;

    /**
     * Transforms a model to a KEG graph using a given Xtend transformation
     * file. The model instance is read from an input stream.
     * 
     * @param xtendFile
     *            the xtend file containing the transformation
     * @param extension
     *            the name of the extension that starts the transformation
     *            inside the xtend file
     * @param parameters
     *            a list of additional parameters for the transformation or null
     *            if no additional parameters are required
     * @param inputStream
     *            the input stream the source model instance is read from
     * @param resourceFactory
     *            the resource factory used to read the model or null for the
     *            standard factory
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
    public static Node transformModel2KEGGraph(final String xtendFile,
            final String extension, final List<Object> parameters,
            final InputStream inputStream,
            final Resource.Factory resourceFactory,
            final IKielerProgressMonitor monitor,
            final String... involvedMetamodels) throws IOException,
            TransformException {
        monitor.begin("KEG Model2Model transformation",
                MONITOR_TRANSFORMATION_WORK);

        // read the model
        ResourceSet resourceSet = new ResourceSetImpl();
        if (resourceFactory != null) {
            resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
                    .put(FILE_EXT_DUMMY, resourceFactory);
        }
        Resource resource =
                resourceSet.createResource(URI.createURI("http:///My."
                        + FILE_EXT_DUMMY));
        // Map<String, Object> options = new HashMap<String, Object>();
        // options.put(XMLResource.OPTION_ENCODING, "UTF-8");
        // read from the stream
        resource.load(inputStream, null);
        EObject model = resource.getContents().get(0);
        monitor.worked(1);
        // find the xtend file
        Bundle bundle = KEGImporterPlugin.getDefault().getBundle();
        IPath path = new Path(xtendFile);
        URL url = FileLocator.find(bundle, path, null);
        String xtendFilePath = FileLocator.resolve(url).getFile();
        // initialize the xtend framework
        ITransformationFramework transformationFramework =
                new XtendTransformationFramework();
        if (parameters != null && parameters.size() > 0) {
            // additional parameters
            Object[] params = new Object[parameters.size() + 1];
            params[0] = model;
            int i = 1;
            for (Object parameter : parameters) {
                params[i++] = parameter;
            }
            transformationFramework.setParameters(params);
        } else {
            Object[] params = new Object[1];
            params[0] = model;
            transformationFramework.setParameters(params);
        }
        // initialize the transformation
        String[] metamodels = new String[involvedMetamodels.length + 1];
        metamodels[0] = "de.cau.cs.kieler.keg.KEGPackage";
        int i = 1;
        for (String metamodel : involvedMetamodels) {
            metamodels[i++] = metamodel; 
        }
        transformationFramework.initializeTransformation(xtendFilePath,
                extension, metamodels);
        // execute the transformation
        Object resultModel = null;
        resultModel = transformationFramework.executeTransformation();
        // serialize the model
        Node node = null;
        if (resultModel instanceof Node) {
            node = (Node) resultModel;
        } else {
            throw new RuntimeException(ERROR_MESSAGE_WRONG_XTEND_RESULT);
        }
        monitor.done();
        return node;
    }
}
