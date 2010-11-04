/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.ogdf.ogml;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Data Type</b></em>'.
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
 *   <li>{@link net.ogdf.ogml.DataType#getValue <em>Value</em>}</li>
 *   <li>{@link net.ogdf.ogml.DataType#getIdRef <em>Id Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.ogdf.ogml.OgmlPackage#getDataType()
 * @model extendedMetaData="name='data.type' kind='simple'"
 * @generated
 */
public interface DataType extends EObject {
    /**
     * Returns the value of the '<em><b>Value</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Value</em>' attribute.
     * @see #setValue(String)
     * @see net.ogdf.ogml.OgmlPackage#getDataType_Value()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="name=':0' kind='simple'"
     * @generated
     */
    String getValue();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.DataType#getValue <em>Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value</em>' attribute.
     * @see #getValue()
     * @generated
     */
    void setValue(String value);

    /**
     * Returns the value of the '<em><b>Id Ref</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Id Ref</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Id Ref</em>' attribute.
     * @see #setIdRef(String)
     * @see net.ogdf.ogml.OgmlPackage#getDataType_IdRef()
     * @model dataType="org.eclipse.emf.ecore.xml.type.IDREF"
     *        extendedMetaData="kind='attribute' name='idRef' namespace='##targetNamespace'"
     * @generated
     */
    String getIdRef();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.DataType#getIdRef <em>Id Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Id Ref</em>' attribute.
     * @see #getIdRef()
     * @generated
     */
    void setIdRef(String value);

} // DataType
