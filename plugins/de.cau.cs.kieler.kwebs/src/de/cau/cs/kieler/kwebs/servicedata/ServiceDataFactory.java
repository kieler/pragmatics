/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.cs.kieler.kwebs.servicedata;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see de.cau.cs.kieler.kwebs.servicedata.ServiceDataPackage
 * @generated
 */
public interface ServiceDataFactory extends EFactory {
    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    ServiceDataFactory eINSTANCE = de.cau.cs.kieler.kwebs.servicedata.impl.ServiceDataFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Service Data</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Service Data</em>'.
     * @generated
     */
    ServiceData createServiceData();

    /**
     * Returns a new object of class '<em>Layout Algorithm</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Layout Algorithm</em>'.
     * @generated
     */
    LayoutAlgorithm createLayoutAlgorithm();

    /**
     * Returns a new object of class '<em>Layout Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Layout Type</em>'.
     * @generated
     */
    LayoutType createLayoutType();

    /**
     * Returns a new object of class '<em>Layout Option</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Layout Option</em>'.
     * @generated
     */
    LayoutOption createLayoutOption();

    /**
     * Returns a new object of class '<em>Category</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Category</em>'.
     * @generated
     */
    Category createCategory();

    /**
     * Returns a new object of class '<em>Known Option</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Known Option</em>'.
     * @generated
     */
    KnownOption createKnownOption();

    /**
     * Returns a new object of class '<em>Supported Diagram</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Supported Diagram</em>'.
     * @generated
     */
    SupportedDiagram createSupportedDiagram();

    /**
     * Returns a new object of class '<em>Remote Enum</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Remote Enum</em>'.
     * @generated
     */
    RemoteEnum createRemoteEnum();

    /**
     * Returns a new object of class '<em>Supported Format</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Supported Format</em>'.
     * @generated
     */
    SupportedFormat createSupportedFormat();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    ServiceDataPackage getServiceDataPackage();

} //ServiceDataFactory
