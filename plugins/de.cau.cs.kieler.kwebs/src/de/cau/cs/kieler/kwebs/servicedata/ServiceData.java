/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.cs.kieler.kwebs.servicedata;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Service Data</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.kwebs.servicedata.ServiceData#getLayoutAlgorithms <em>Layout Algorithms</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.servicedata.ServiceData#getLayoutTypes <em>Layout Types</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.servicedata.ServiceData#getLayoutOptions <em>Layout Options</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.servicedata.ServiceData#getCategories <em>Categories</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.servicedata.ServiceData#getVersion <em>Version</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.servicedata.ServiceData#getSupportedFormats <em>Supported Formats</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.kwebs.servicedata.ServiceDataPackage#getServiceData()
 * @model
 * @generated
 */
public interface ServiceData extends EObject {
    /**
     * Returns the value of the '<em><b>Layout Algorithms</b></em>' containment reference list.
     * The list contents are of type {@link de.cau.cs.kieler.kwebs.servicedata.LayoutAlgorithm}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Layout Algorithms</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Layout Algorithms</em>' containment reference list.
     * @see de.cau.cs.kieler.kwebs.servicedata.ServiceDataPackage#getServiceData_LayoutAlgorithms()
     * @model containment="true"
     * @generated
     */
    EList<LayoutAlgorithm> getLayoutAlgorithms();

    /**
     * Returns the value of the '<em><b>Layout Types</b></em>' containment reference list.
     * The list contents are of type {@link de.cau.cs.kieler.kwebs.servicedata.LayoutType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Layout Types</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Layout Types</em>' containment reference list.
     * @see de.cau.cs.kieler.kwebs.servicedata.ServiceDataPackage#getServiceData_LayoutTypes()
     * @model containment="true"
     * @generated
     */
    EList<LayoutType> getLayoutTypes();

    /**
     * Returns the value of the '<em><b>Layout Options</b></em>' containment reference list.
     * The list contents are of type {@link de.cau.cs.kieler.kwebs.servicedata.LayoutOption}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Layout Options</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Layout Options</em>' containment reference list.
     * @see de.cau.cs.kieler.kwebs.servicedata.ServiceDataPackage#getServiceData_LayoutOptions()
     * @model containment="true"
     * @generated
     */
    EList<LayoutOption> getLayoutOptions();

    /**
     * Returns the value of the '<em><b>Categories</b></em>' containment reference list.
     * The list contents are of type {@link de.cau.cs.kieler.kwebs.servicedata.Category}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Categories</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Categories</em>' containment reference list.
     * @see de.cau.cs.kieler.kwebs.servicedata.ServiceDataPackage#getServiceData_Categories()
     * @model containment="true"
     * @generated
     */
    EList<Category> getCategories();

    /**
     * Returns the value of the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Version</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Version</em>' attribute.
     * @see #setVersion(String)
     * @see de.cau.cs.kieler.kwebs.servicedata.ServiceDataPackage#getServiceData_Version()
     * @model
     * @generated
     */
    String getVersion();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.kwebs.servicedata.ServiceData#getVersion <em>Version</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Version</em>' attribute.
     * @see #getVersion()
     * @generated
     */
    void setVersion(String value);

    /**
     * Returns the value of the '<em><b>Supported Formats</b></em>' containment reference list.
     * The list contents are of type {@link de.cau.cs.kieler.kwebs.servicedata.SupportedFormat}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Supported Formats</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Supported Formats</em>' containment reference list.
     * @see de.cau.cs.kieler.kwebs.servicedata.ServiceDataPackage#getServiceData_SupportedFormats()
     * @model containment="true"
     * @generated
     */
    EList<SupportedFormat> getSupportedFormats();

} // ServiceData
