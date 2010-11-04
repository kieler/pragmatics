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
 * A representation of the model object '<em><b>Template Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.ogdf.ogml.TemplateType#getIdRef <em>Id Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.ogdf.ogml.OgmlPackage#getTemplateType()
 * @model extendedMetaData="name='template.type' kind='empty'"
 * @generated
 */
public interface TemplateType extends EObject {
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
     * @see net.ogdf.ogml.OgmlPackage#getTemplateType_IdRef()
     * @model dataType="org.eclipse.emf.ecore.xml.type.IDREF"
     *        extendedMetaData="kind='attribute' name='idRef' namespace='##targetNamespace'"
     * @generated
     */
    String getIdRef();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.TemplateType#getIdRef <em>Id Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Id Ref</em>' attribute.
     * @see #getIdRef()
     * @generated
     */
    void setIdRef(String value);

} // TemplateType
