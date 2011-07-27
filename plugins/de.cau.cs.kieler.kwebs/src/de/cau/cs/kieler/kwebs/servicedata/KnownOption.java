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
 * A representation of the model object '<em><b>Known Option</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.kwebs.servicedata.KnownOption#getDefault <em>Default</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.servicedata.KnownOption#getOption <em>Option</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.kwebs.servicedata.ServiceDataPackage#getKnownOption()
 * @model
 * @generated
 */
public interface KnownOption extends EObject {
    /**
     * Returns the value of the '<em><b>Default</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Default</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Default</em>' attribute.
     * @see #setDefault(String)
     * @see de.cau.cs.kieler.kwebs.servicedata.ServiceDataPackage#getKnownOption_Default()
     * @model
     * @generated
     */
    String getDefault();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.kwebs.servicedata.KnownOption#getDefault <em>Default</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Default</em>' attribute.
     * @see #getDefault()
     * @generated
     */
    void setDefault(String value);

    /**
     * Returns the value of the '<em><b>Option</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Option</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Option</em>' reference.
     * @see #setOption(LayoutOption)
     * @see de.cau.cs.kieler.kwebs.servicedata.ServiceDataPackage#getKnownOption_Option()
     * @model required="true"
     * @generated
     */
    LayoutOption getOption();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.kwebs.servicedata.KnownOption#getOption <em>Option</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Option</em>' reference.
     * @see #getOption()
     * @generated
     */
    void setOption(LayoutOption value);

} // KnownOption
