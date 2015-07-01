/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.formats;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Diagnostic;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;

import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;

/**
 * An abstract superclass for EMF-based transformers.
 *
 * @param <T> type of EMF object
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow 2012-07-10 msp
 */
public abstract class AbstractEmfHandler<T extends EObject> implements IGraphFormatHandler<T> {
    
    /** the character set to use for serialization and deserialization. */
    public static final IProperty<String> CHARSET = new Property<String>("emfHandler.charset");
    /** the XML options for loading and saving files. */
    public static final IProperty<Map<Object, Object>> XML_OPTIONS = new Property<Map<Object, Object>>(
            "emfHandler.xmlOptions");
    
    /** the file extension for loading and saving resources. */
    private String fileExtension;
    
    /**
     * {@inheritDoc}
     */
    public void deserialize(final String serializedGraph,
            final TransformationData<T, KNode> transData) {
        try {
            String charset = transData.getProperty(CHARSET);
            byte[] bytes;
            if (charset != null) {
                bytes = serializedGraph.getBytes(charset);
            } else {
                bytes = serializedGraph.getBytes();
            }
            ByteArrayInputStream source = new ByteArrayInputStream(bytes);
            T result = deserializeBinary(source, transData.getProperty(XML_OPTIONS));
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
    public String serialize(final TransformationData<KNode, T> transData) {
        try {
            ByteArrayOutputStream target = new ByteArrayOutputStream();
            Map<Object, Object> xmlOptions = transData.getProperty(XML_OPTIONS);
            String charset = transData.getProperty(CHARSET);
            if (xmlOptions == null && charset != null) {
                xmlOptions = Maps.newHashMap();
                xmlOptions.put(XMLResource.OPTION_ENCODING, charset);
            }
            for (T graph : transData.getTargetGraphs()) {
                serializeBinary(graph, target, xmlOptions);
            }
            if (charset != null) {
                return target.toString(charset);
            } else {
                return target.toString();
            }
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
     * Deserialize an EMF model.
     * 
     * @param source
     *            the stream to parse
     * @param options
     *            optional map of settings. Can be {@code null}
     * @return the EMF model
     * @throws IOException
     *            when an exception occurs while parsing the model   
     */
    @SuppressWarnings("unchecked")
    protected final T deserializeBinary(final InputStream source, final Map<Object, Object> options) 
        throws IOException {
        URI uri = URI.createURI("temp." + getFileExtension());
        ResourceSet resourceSet = createResourceSet();
        Resource resource = resourceSet.createResource(uri);   
        resource.load(source, options == null ? Collections.emptyMap() : options);
        if (!resource.getErrors().isEmpty()) {
            StringBuilder message = new StringBuilder("The given input could not be parsed.");
            for (Diagnostic diagnostic : resource.getErrors()) {
                message.append("\n");
                if (diagnostic.getLine() > 0) {
                    message.append(diagnostic.getLine());
                    try {
                        if (diagnostic.getColumn() > 0) {
                            message.append(":");
                            message.append(diagnostic.getColumn());
                        }
                    } catch (UnsupportedOperationException exception) {
                        // columns may not be supported, so ignore the exception
                    }
                    message.append(" - ");
                }
                message.append(diagnostic.getMessage());
            }
            throw new TransformationException(message.toString());
        }
        if (resource.getContents().isEmpty()) {
            return null;
        }
        return (T) resource.getContents().get(0);
    }

    /**
     * Serialize a given EMF model to the given output stream. The serial representation
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
        resource.save(target, options == null ? Collections.emptyMap() : options);
    }
    
    /**
     * Create an appropriate resource set for the supported type of graphs.
     * 
     * @return a resource set
     */
    protected abstract ResourceSet createResourceSet();

}
