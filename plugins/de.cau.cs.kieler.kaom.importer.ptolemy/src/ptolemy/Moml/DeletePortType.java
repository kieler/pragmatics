/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ptolemy.Moml;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Delete Port Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ptolemy.Moml.DeletePortType#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see ptolemy.Moml.MomlPackage#getDeletePortType()
 * @model extendedMetaData="name='deletePort_._type' kind='empty'"
 * @generated
 */
public interface DeletePortType extends EObject {
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
     * @see ptolemy.Moml.MomlPackage#getDeletePortType_Name()
     * @model required="true"
     *        extendedMetaData="kind='attribute' name='name' namespace='##targetNamespace'"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link ptolemy.Moml.DeletePortType#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

} // DeletePortType
