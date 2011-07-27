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
 * A representation of the model object '<em><b>Supported Diagram</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.kwebs.servicedata.SupportedDiagram#getType <em>Type</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.servicedata.SupportedDiagram#getPriority <em>Priority</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.kwebs.servicedata.ServiceDataPackage#getSupportedDiagram()
 * @model
 * @generated
 */
public interface SupportedDiagram extends EObject {
    /**
     * Returns the value of the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Type</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Type</em>' attribute.
     * @see #setType(String)
     * @see de.cau.cs.kieler.kwebs.servicedata.ServiceDataPackage#getSupportedDiagram_Type()
     * @model required="true"
     * @generated
     */
    String getType();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.kwebs.servicedata.SupportedDiagram#getType <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Type</em>' attribute.
     * @see #getType()
     * @generated
     */
    void setType(String value);

    /**
     * Returns the value of the '<em><b>Priority</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Priority</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Priority</em>' attribute.
     * @see #setPriority(int)
     * @see de.cau.cs.kieler.kwebs.servicedata.ServiceDataPackage#getSupportedDiagram_Priority()
     * @model
     * @generated
     */
    int getPriority();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.kwebs.servicedata.SupportedDiagram#getPriority <em>Priority</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Priority</em>' attribute.
     * @see #getPriority()
     * @generated
     */
    void setPriority(int value);

} // SupportedDiagram
