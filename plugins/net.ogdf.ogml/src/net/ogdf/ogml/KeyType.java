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
 * A representation of the model object '<em><b>Key Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * <!-- something missing -->
 *     
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.ogdf.ogml.KeyType#getKeyValue <em>Key Value</em>}</li>
 *   <li>{@link net.ogdf.ogml.KeyType#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.ogdf.ogml.OgmlPackage#getKeyType()
 * @model extendedMetaData="name='key.type' kind='elementOnly'"
 * @generated
 */
public interface KeyType extends EObject {
    /**
     * Returns the value of the '<em><b>Key Value</b></em>' containment reference list.
     * The list contents are of type {@link net.ogdf.ogml.KeyValueType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Key Value</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Key Value</em>' containment reference list.
     * @see net.ogdf.ogml.OgmlPackage#getKeyType_KeyValue()
     * @model containment="true" required="true"
     *        extendedMetaData="kind='element' name='keyValue' namespace='##targetNamespace'"
     * @generated
     */
    EList<KeyValueType> getKeyValue();

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
     * @see net.ogdf.ogml.OgmlPackage#getKeyType_Id()
     * @model id="true" dataType="org.eclipse.emf.ecore.xml.type.ID" required="true"
     *        extendedMetaData="kind='attribute' name='id' namespace='##targetNamespace'"
     * @generated
     */
    String getId();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.KeyType#getId <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Id</em>' attribute.
     * @see #getId()
     * @generated
     */
    void setId(String value);

} // KeyType
