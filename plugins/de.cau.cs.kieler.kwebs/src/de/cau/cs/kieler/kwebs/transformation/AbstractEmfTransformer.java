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
package de.cau.cs.kieler.kwebs.transformation;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;

/**
 * An abstract superclass for EMF-based transformers.
 *
 * @param <T> type of EMF object
 * @author msp
 */
public abstract class AbstractEmfTransformer<T extends EObject> implements IGraphTransformer<T> {
    
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public T deserialize(final String serializedGraph) {
        try {
            ByteArrayInputStream inStream = new ByteArrayInputStream(
                serializedGraph.getBytes("UTF-8")
            );
            URI uri = URI.createURI("inputstream://temp." + getFileExtension());
            ResourceSet resourceSet = createResourceSet();
            Resource resource = resourceSet.createResource(uri);
            Map<String, String> options = new HashMap<String, String>();
            options.put(XMLResource.OPTION_ENCODING, "UTF-8");
            resource.load(inStream, options);
            EObject eObject = resource.getContents().get(0);
            inStream.close();
            return (T) eObject;
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
        String xmi = null;
        try {
            EcoreUtil.resolveAll(graph);
            URI uri = URI.createURI("outputstream://temp." + getFileExtension());
            ResourceSet resourceSet = createResourceSet();
            Resource resource = resourceSet.createResource(uri);
            resource.getContents().add(graph);            
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            Map<String, String> options = new HashMap<String, String>();
            options.put(XMLResource.OPTION_ENCODING, "UTF-8");
            resource.save(outStream, options);
            outStream.flush();
            xmi = new String(outStream.toByteArray(), "UTF-8");
            outStream.close();
        } catch (IOException e) {
            throw new TransformationException(e);
        }
        return xmi;
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
