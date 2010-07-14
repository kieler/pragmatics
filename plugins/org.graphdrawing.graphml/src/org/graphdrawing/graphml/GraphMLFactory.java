/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.graphdrawing.graphml;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.graphdrawing.graphml.GraphMLPackage
 * @generated
 */
public interface GraphMLFactory extends EFactory {
    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    GraphMLFactory eINSTANCE = org.graphdrawing.graphml.impl.GraphMLFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Data Extension Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Data Extension Type</em>'.
     * @generated
     */
    DataExtensionType createDataExtensionType();

    /**
     * Returns a new object of class '<em>Data Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Data Type</em>'.
     * @generated
     */
    DataType createDataType();

    /**
     * Returns a new object of class '<em>Default Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Default Type</em>'.
     * @generated
     */
    DefaultType createDefaultType();

    /**
     * Returns a new object of class '<em>Document Root</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Document Root</em>'.
     * @generated
     */
    DocumentRoot createDocumentRoot();

    /**
     * Returns a new object of class '<em>Edge Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Edge Type</em>'.
     * @generated
     */
    EdgeType createEdgeType();

    /**
     * Returns a new object of class '<em>Endpoint Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Endpoint Type</em>'.
     * @generated
     */
    EndpointType createEndpointType();

    /**
     * Returns a new object of class '<em>Graphml Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Graphml Type</em>'.
     * @generated
     */
    GraphmlType createGraphmlType();

    /**
     * Returns a new object of class '<em>Graph Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Graph Type</em>'.
     * @generated
     */
    GraphType createGraphType();

    /**
     * Returns a new object of class '<em>Hyperedge Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Hyperedge Type</em>'.
     * @generated
     */
    HyperedgeType createHyperedgeType();

    /**
     * Returns a new object of class '<em>Key Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Key Type</em>'.
     * @generated
     */
    KeyType createKeyType();

    /**
     * Returns a new object of class '<em>Locator Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Locator Type</em>'.
     * @generated
     */
    LocatorType createLocatorType();

    /**
     * Returns a new object of class '<em>Node Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Node Type</em>'.
     * @generated
     */
    NodeType createNodeType();

    /**
     * Returns a new object of class '<em>Port Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Port Type</em>'.
     * @generated
     */
    PortType createPortType();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    GraphMLPackage getGraphMLPackage();

} //GraphMLFactory
