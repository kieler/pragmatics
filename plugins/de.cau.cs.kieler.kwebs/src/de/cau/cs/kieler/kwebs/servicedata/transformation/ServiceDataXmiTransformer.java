/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2008 by
 * + Christian-Albrechts-University of Kiel
 *     + Department of Computer Science
 *         + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kwebs.servicedata.transformation;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import de.cau.cs.kieler.kwebs.servicedata.ServiceData;
import de.cau.cs.kieler.kwebs.servicedata.ServiceDataPackage;

/**
 * This class is used for the serialization and deserialization of the service data model.
 *
 * @kieler.rating 2011-08-02 proposed yellow
 *     reviewed by ckru, mri, msp
 *
 * @author swe
 */
public class ServiceDataXmiTransformer {

    /**
     * Deserializes a given serialized service data model.
     *  
     * @param serializedData
     *            the serialized service data model
     * @return a service data model
     */
    public final ServiceData deserialize(final String serializedData) {
        ServiceData serviceMeta = null;
        try {
            ByteArrayInputStream inStream = new ByteArrayInputStream(serializedData.getBytes());
            URI uri = URI.createURI("inputstream://temp.serviceData");
            ResourceSet resourceSet = createResourceSet();
            Resource resource = resourceSet.createResource(uri);
            EObject eObject = null;
            try {
                Map<String, String> options = new HashMap<String, String>();
                options.put(XMLResource.OPTION_ENCODING, "UTF-8");
                resource.load(inStream, options);
                eObject = resource.getContents().get(0);
                if (eObject instanceof ServiceData) {
                    serviceMeta = (ServiceData) eObject;
                }
            } catch (Throwable e) {
                System.err.println(e.getMessage());
            }
            inStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serviceMeta;
    }

    /**
     * Serializes a given service data model.
     * 
     * @param serviceData
     *            a service data model
     * @return a serialized service data model
     */
    public final String serialize(final ServiceData serviceData) {
        String serializedMeta = null;
        try {
            URI uri = URI.createURI("outputstream://temp.serviceData");
            ResourceSet resourceSet = createResourceSet();
            Resource resource = resourceSet.createResource(uri);
            resource.unload();
            resource.getContents().add(serviceData);            
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            Map<String, String> options = new HashMap<String, String>();
            options.put(XMLResource.OPTION_ENCODING, "UTF-8");
            resource.save(outStream, options);
            outStream.flush();
            serializedMeta = new String(outStream.toByteArray());
            outStream.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return serializedMeta;
    }

    /**
     * Creates an EMF resource set used for serialization and deserialization.
     *  
     * @return a resource set
     */
    private ResourceSet createResourceSet() {
        ResourceSet resourceset = new ResourceSetImpl();
        resourceset.getResourceFactoryRegistry().getExtensionToFactoryMap().put(
            Resource.Factory.Registry.DEFAULT_EXTENSION,
            new XMIResourceFactoryImpl()
        );
        resourceset.getPackageRegistry().put(
            ServiceDataPackage.eNS_URI,
            ServiceDataPackage.eINSTANCE
        );
        return resourceset;
    }

}
