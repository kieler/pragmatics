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
 * A representation of the model object '<em><b>Node Constraint Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.ogdf.ogml.NodeConstraintType#getIdRef <em>Id Ref</em>}</li>
 *   <li>{@link net.ogdf.ogml.NodeConstraintType#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.ogdf.ogml.OgmlPackage#getNodeConstraintType()
 * @model extendedMetaData="name='node_constraint.type' kind='empty'"
 * @generated
 */
public interface NodeConstraintType extends EObject {
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
     * @see net.ogdf.ogml.OgmlPackage#getNodeConstraintType_IdRef()
     * @model dataType="org.eclipse.emf.ecore.xml.type.IDREF" required="true"
     *        extendedMetaData="kind='attribute' name='idRef' namespace='##targetNamespace'"
     * @generated
     */
    String getIdRef();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.NodeConstraintType#getIdRef <em>Id Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Id Ref</em>' attribute.
     * @see #getIdRef()
     * @generated
     */
    void setIdRef(String value);

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
     * @see net.ogdf.ogml.OgmlPackage#getNodeConstraintType_Name()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='name' namespace='##targetNamespace'"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.NodeConstraintType#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

} // NodeConstraintType
