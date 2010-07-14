/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.graphdrawing.graphml.util;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

import org.graphdrawing.graphml.GraphMLPackage;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class GraphMLXMLProcessor extends XMLProcessor {

    /**
     * Public constructor to instantiate the helper.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GraphMLXMLProcessor() {
        super((EPackage.Registry.INSTANCE));
        GraphMLPackage.eINSTANCE.eClass();
    }
    
    /**
     * Register for "*" and "xml" file extensions the GraphMLResourceFactoryImpl factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected Map<String, Resource.Factory> getRegistrations() {
        if (registrations == null) {
            super.getRegistrations();
            registrations.put(XML_EXTENSION, new GraphMLResourceFactoryImpl());
            registrations.put(STAR_EXTENSION, new GraphMLResourceFactoryImpl());
        }
        return registrations;
    }

} //GraphMLXMLProcessor
