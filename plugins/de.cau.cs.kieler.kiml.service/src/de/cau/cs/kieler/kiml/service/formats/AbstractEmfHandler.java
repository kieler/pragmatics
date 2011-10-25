/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.service.formats;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Diagnostic;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;

import de.cau.cs.kieler.kiml.service.formats.ITransformationHandler;

/**
 * An abstract superclass for EMF-based transformers.
 *
 * @param <T> type of EMF object
 * @author msp
 */
public abstract class AbstractEmfHandler<T extends EObject> implements ITransformationHandler<T> {
    
    /**
     * {@inheritDoc}
     */
    public T deserialize(final String serializedGraph) {
        try {
            ByteArrayInputStream source = new ByteArrayInputStream(serializedGraph.getBytes("UTF-8"));
            T result = deserializeBinary(source, null);
            source.close();
            return result;
        } catch (UnsupportedEncodingException e) {
            throw new TransformationException(e);
        } catch (IOException e) {
            throw new TransformationException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    public String serialize(final T graph) {
        try {
            ByteArrayOutputStream target = new ByteArrayOutputStream();
            serializeBinary(graph, target, null);
            target.flush();
            String result = target.toString("UTF-8");
            target.close();
            return result;
        } catch (IOException e) {
            throw new TransformationException(e);
        }
    }

    /**
     * Deserializes an EMF model.
     * 
     * @param source
     *            the stream to parse
     * @param settings
     *            optional map of settings. Can be {@code null}
     * @return the EMF model
     * @throws IOException
     *            when an exception occurs while parsing the model   
     */
    @SuppressWarnings("unchecked")
    protected final T deserializeBinary(final InputStream source, final Map<Object, Object> settings) 
        throws IOException {
        URI uri = URI.createURI("temp." + getFileExtension());
        ResourceSet resourceSet = createResourceSet();
        Resource resource = resourceSet.createResource(uri);   
        Map<Object, Object> options = new HashMap<Object, Object>();
        if (settings != null) {
            options.putAll(settings);
        }
        resource.load(source, options);
        EList<EObject> eObjects = resource.getContents();
        if (eObjects.size() == 0) {
            throw new TransformationException("The given input is empty.");
        }
        if (!resource.getErrors().isEmpty()) {
            StringBuilder errorBuilder = new StringBuilder();
            for (Diagnostic diagnostic : resource.getErrors()) {
                if (diagnostic.getLine() > 0) {
                    errorBuilder.append(diagnostic.getLine());
                    errorBuilder.append(": ");
                }
                errorBuilder.append(diagnostic.getMessage());
                errorBuilder.append("\n");
            }
            throw new TransformationException("Errors while reading input:\n" + errorBuilder.toString());
        }
        return (T) eObjects.get(0);
    }

    /**
     * Serializes a given EMF model to the given output stream. The serial representation
     * is stored using UTF-8 encoding.
     * 
     * @param graph
     *            the model to be serialized
     * @param target
     *            the output stream to write to
     * @param settings
     *            optional map of settings. Can be {@code null}
     * @throws IOException
     *            when an exception occurs while writing the model
     */
    protected final void serializeBinary(final T graph, final OutputStream target, 
        final Map<Object, Object> settings) throws IOException {
        EcoreUtil.resolveAll(graph);
        URI uri = URI.createURI("temp." + getFileExtension());
        ResourceSet resourceSet = createResourceSet();
        Resource resource = resourceSet.createResource(uri);
        resource.getContents().add(graph);      
        Map<Object, Object> options = new HashMap<Object, Object>();
        options.put(XMLResource.OPTION_ENCODING, "UTF-8");
        if (settings != null) {
            options.putAll(settings);
        }
        resource.save(target, options);
    }
    
    /**
     * Return the file extension used for the supported EMF models.
     * 
     * @return the file extension
     */
    protected abstract String getFileExtension();
    
    /**
     * Create an appropriate resource set for the supported type of graphs.
     * 
     * @return a resource set
     */
    protected abstract ResourceSet createResourceSet();

}
