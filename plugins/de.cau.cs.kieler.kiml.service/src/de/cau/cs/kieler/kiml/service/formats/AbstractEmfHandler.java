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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Diagnostic;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.service.formats.ITransformationHandler;

/**
 * An abstract superclass for EMF-based transformers.
 *
 * @param <T> type of EMF object
 * @author msp
 */
public abstract class AbstractEmfHandler<T extends EObject> implements ITransformationHandler<T> {
    
    /** the file extension for loading and saving resources. */
    private String fileExtension;
    
    /**
     * {@inheritDoc}
     */
    public void deserialize(final String serializedGraph,
            final TransformationData<T, KNode> transData) {
        try {
            ByteArrayInputStream source = new ByteArrayInputStream(serializedGraph.getBytes("UTF-8"));
            T result = deserializeBinary(source, null, transData);
            source.close();
            transData.setSourceGraph(result);
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
     * Set the file extension for this handler.
     * 
     * @param ext a file extension
     */
    public final void setFileExtension(final String ext) {
        this.fileExtension = ext;
    }
    
    /**
     * Returns the file extension of this handler.
     * 
     * @return the file extension
     */
    protected String getFileExtension() {
        return fileExtension;
    }

    /**
     * Deserializes an EMF model.
     * 
     * @param source
     *            the stream to parse
     * @param options
     *            optional map of settings. Can be {@code null}
     * @param transData
     *            transformation data, used only for logging
     * @return the EMF model
     * @throws IOException
     *            when an exception occurs while parsing the model   
     */
    @SuppressWarnings("unchecked")
    protected final T deserializeBinary(final InputStream source, final Map<Object, Object> options,
            final TransformationData<?, ?> transData) 
        throws IOException {
        URI uri = URI.createURI("temp." + getFileExtension());
        ResourceSet resourceSet = createResourceSet();
        Resource resource = resourceSet.createResource(uri);   
        Map<Object, Object> optMap = new HashMap<Object, Object>();
        optMap.put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, true);
        if (options != null) {
            optMap.putAll(options);
        }
        resource.load(source, optMap);
        for (Diagnostic diagnostic : resource.getErrors()) {
            if (diagnostic.getLine() > 0) {
                transData.log(diagnostic.getLine() + ": " + diagnostic.getMessage());
            } else {
                transData.log(diagnostic.getMessage());
            }
        }
        if (resource.getContents().isEmpty()) {
            throw new TransformationException("The given input could not be parsed.");
        }
        return (T) resource.getContents().get(0);
    }

    /**
     * Serializes a given EMF model to the given output stream. The serial representation
     * is stored using UTF-8 encoding.
     * 
     * @param graph
     *            the model to be serialized
     * @param target
     *            the output stream to write to
     * @param options
     *            optional map of settings. Can be {@code null}
     * @throws IOException
     *            when an exception occurs while writing the model
     */
    protected final void serializeBinary(final T graph, final OutputStream target, 
        final Map<Object, Object> options) throws IOException {
        EcoreUtil.resolveAll(graph);
        URI uri = URI.createURI("temp." + getFileExtension());
        ResourceSet resourceSet = createResourceSet();
        Resource resource = resourceSet.createResource(uri);
        resource.getContents().add(graph);
        Map<Object, Object> optMap = new HashMap<Object, Object>();
        optMap.put(XMLResource.OPTION_ENCODING, "UTF-8");
        if (options != null) {
            optMap.putAll(options);
        }
        resource.save(target, optMap);
    }
    
    /**
     * Create an appropriate resource set for the supported type of graphs.
     * 
     * @return a resource set
     */
    protected abstract ResourceSet createResourceSet();

}
