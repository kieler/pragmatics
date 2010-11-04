/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.ogdf.ogml;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Constraints Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.ogdf.ogml.ConstraintsType#getGroup <em>Group</em>}</li>
 *   <li>{@link net.ogdf.ogml.ConstraintsType#getNumber <em>Number</em>}</li>
 *   <li>{@link net.ogdf.ogml.ConstraintsType#getInt <em>Int</em>}</li>
 *   <li>{@link net.ogdf.ogml.ConstraintsType#getBoolean <em>Boolean</em>}</li>
 *   <li>{@link net.ogdf.ogml.ConstraintsType#getNodeRef <em>Node Ref</em>}</li>
 *   <li>{@link net.ogdf.ogml.ConstraintsType#getEdgeRef <em>Edge Ref</em>}</li>
 *   <li>{@link net.ogdf.ogml.ConstraintsType#getLabelRef <em>Label Ref</em>}</li>
 *   <li>{@link net.ogdf.ogml.ConstraintsType#getComposed <em>Composed</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.ogdf.ogml.OgmlPackage#getConstraintsType()
 * @model extendedMetaData="name='constraints.type' kind='elementOnly'"
 * @generated
 */
public interface ConstraintsType extends EObject {
    /**
     * Returns the value of the '<em><b>Group</b></em>' attribute list.
     * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Group</em>' attribute list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Group</em>' attribute list.
     * @see net.ogdf.ogml.OgmlPackage#getConstraintsType_Group()
     * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
     *        extendedMetaData="kind='group' name='group:0'"
     * @generated
     */
    FeatureMap getGroup();

    /**
     * Returns the value of the '<em><b>Number</b></em>' containment reference list.
     * The list contents are of type {@link net.ogdf.ogml.NumberType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Number</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Number</em>' containment reference list.
     * @see net.ogdf.ogml.OgmlPackage#getConstraintsType_Number()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='number' namespace='##targetNamespace' group='group:0'"
     * @generated
     */
    EList<NumberType> getNumber();

    /**
     * Returns the value of the '<em><b>Int</b></em>' containment reference list.
     * The list contents are of type {@link net.ogdf.ogml.IntType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Int</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Int</em>' containment reference list.
     * @see net.ogdf.ogml.OgmlPackage#getConstraintsType_Int()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='int' namespace='##targetNamespace' group='group:0'"
     * @generated
     */
    EList<IntType> getInt();

    /**
     * Returns the value of the '<em><b>Boolean</b></em>' containment reference list.
     * The list contents are of type {@link net.ogdf.ogml.BooleanType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Boolean</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Boolean</em>' containment reference list.
     * @see net.ogdf.ogml.OgmlPackage#getConstraintsType_Boolean()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='boolean' namespace='##targetNamespace' group='group:0'"
     * @generated
     */
    EList<BooleanType> getBoolean();

    /**
     * Returns the value of the '<em><b>Node Ref</b></em>' containment reference list.
     * The list contents are of type {@link net.ogdf.ogml.NodeConstraintType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Node Ref</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Node Ref</em>' containment reference list.
     * @see net.ogdf.ogml.OgmlPackage#getConstraintsType_NodeRef()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='nodeRef' namespace='##targetNamespace' group='group:0'"
     * @generated
     */
    EList<NodeConstraintType> getNodeRef();

    /**
     * Returns the value of the '<em><b>Edge Ref</b></em>' containment reference list.
     * The list contents are of type {@link net.ogdf.ogml.EdgeConstraintType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Edge Ref</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Edge Ref</em>' containment reference list.
     * @see net.ogdf.ogml.OgmlPackage#getConstraintsType_EdgeRef()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='edgeRef' namespace='##targetNamespace' group='group:0'"
     * @generated
     */
    EList<EdgeConstraintType> getEdgeRef();

    /**
     * Returns the value of the '<em><b>Label Ref</b></em>' containment reference list.
     * The list contents are of type {@link net.ogdf.ogml.LabelConstraintType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Label Ref</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Label Ref</em>' containment reference list.
     * @see net.ogdf.ogml.OgmlPackage#getConstraintsType_LabelRef()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='labelRef' namespace='##targetNamespace' group='group:0'"
     * @generated
     */
    EList<LabelConstraintType> getLabelRef();

    /**
     * Returns the value of the '<em><b>Composed</b></em>' containment reference list.
     * The list contents are of type {@link net.ogdf.ogml.ComposedType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Composed</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Composed</em>' containment reference list.
     * @see net.ogdf.ogml.OgmlPackage#getConstraintsType_Composed()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='composed' namespace='##targetNamespace' group='group:0'"
     * @generated
     */
    EList<ComposedType> getComposed();

} // ConstraintsType
