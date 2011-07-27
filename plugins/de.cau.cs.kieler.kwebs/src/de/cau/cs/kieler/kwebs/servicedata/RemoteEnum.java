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
 * A representation of the model object '<em><b>Remote Enum</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.kwebs.servicedata.RemoteEnum#getValues <em>Values</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.kwebs.servicedata.ServiceDataPackage#getRemoteEnum()
 * @model
 * @generated
 */
public interface RemoteEnum extends EObject {
    /**
     * Returns the value of the '<em><b>Values</b></em>' attribute list.
     * The list contents are of type {@link java.lang.String}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Values</em>' attribute list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Values</em>' attribute list.
     * @see de.cau.cs.kieler.kwebs.servicedata.ServiceDataPackage#getRemoteEnum_Values()
     * @model
     * @generated
     */
    EList<String> getValues();

} // RemoteEnum
