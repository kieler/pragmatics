/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.cs.kieler.kwebs.servicedata;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Category</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.kwebs.servicedata.Category#getId <em>Id</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.servicedata.Category#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.kwebs.servicedata.ServiceDataPackage#getCategory()
 * @model
 * @generated
 */
public interface Category extends EObject {
    /**
     * Returns the value of the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Id</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Id</em>' attribute.
     * @see #setId(String)
     * @see de.cau.cs.kieler.kwebs.servicedata.ServiceDataPackage#getCategory_Id()
     * @model required="true"
     * @generated
     */
    String getId();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.kwebs.servicedata.Category#getId <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Id</em>' attribute.
     * @see #getId()
     * @generated
     */
    void setId(String value);

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see de.cau.cs.kieler.kwebs.servicedata.ServiceDataPackage#getCategory_Name()
     * @model required="true"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.kwebs.servicedata.Category#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

} // Category
