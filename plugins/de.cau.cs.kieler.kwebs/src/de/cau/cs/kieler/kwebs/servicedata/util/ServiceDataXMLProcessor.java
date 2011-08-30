/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.cs.kieler.kwebs.servicedata.util;

import de.cau.cs.kieler.kwebs.servicedata.ServiceDataPackage;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ServiceDataXMLProcessor extends XMLProcessor {

    /**
     * Public constructor to instantiate the helper.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ServiceDataXMLProcessor() {
        super((EPackage.Registry.INSTANCE));
        ServiceDataPackage.eINSTANCE.eClass();
    }
    
    /**
     * Register for "*" and "xml" file extensions the ServiceDataResourceFactoryImpl factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected Map<String, Resource.Factory> getRegistrations() {
        if (registrations == null) {
            super.getRegistrations();
            registrations.put(XML_EXTENSION, new ServiceDataResourceFactoryImpl());
            registrations.put(STAR_EXTENSION, new ServiceDataResourceFactoryImpl());
        }
        return registrations;
    }

} //ServiceDataXMLProcessor
