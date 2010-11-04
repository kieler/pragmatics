/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.ogdf.ogml;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Keys Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.ogdf.ogml.KeysType#getKey <em>Key</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.ogdf.ogml.OgmlPackage#getKeysType()
 * @model extendedMetaData="name='keys.type' kind='elementOnly'"
 * @generated
 */
public interface KeysType extends EObject {
    /**
     * Returns the value of the '<em><b>Key</b></em>' containment reference list.
     * The list contents are of type {@link net.ogdf.ogml.KeyType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Key</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Key</em>' containment reference list.
     * @see net.ogdf.ogml.OgmlPackage#getKeysType_Key()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='key' namespace='##targetNamespace'"
     * @generated
     */
    EList<KeyType> getKey();

} // KeysType
