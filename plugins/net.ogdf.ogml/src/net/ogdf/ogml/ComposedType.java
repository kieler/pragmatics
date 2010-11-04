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
 * A representation of the model object '<em><b>Composed Type</b></em>'.
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
 *   <li>{@link net.ogdf.ogml.ComposedType#getGroup <em>Group</em>}</li>
 *   <li>{@link net.ogdf.ogml.ComposedType#getNumber <em>Number</em>}</li>
 *   <li>{@link net.ogdf.ogml.ComposedType#getInt <em>Int</em>}</li>
 *   <li>{@link net.ogdf.ogml.ComposedType#getBoolean <em>Boolean</em>}</li>
 *   <li>{@link net.ogdf.ogml.ComposedType#getNode <em>Node</em>}</li>
 *   <li>{@link net.ogdf.ogml.ComposedType#getEdge <em>Edge</em>}</li>
 *   <li>{@link net.ogdf.ogml.ComposedType#getLabel <em>Label</em>}</li>
 *   <li>{@link net.ogdf.ogml.ComposedType#getComposed <em>Composed</em>}</li>
 *   <li>{@link net.ogdf.ogml.ComposedType#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.ogdf.ogml.OgmlPackage#getComposedType()
 * @model extendedMetaData="name='composed.type' kind='elementOnly'"
 * @generated
 */
public interface ComposedType extends EObject {
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
     * @see net.ogdf.ogml.OgmlPackage#getComposedType_Group()
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
     * @see net.ogdf.ogml.OgmlPackage#getComposedType_Number()
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
     * @see net.ogdf.ogml.OgmlPackage#getComposedType_Int()
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
     * @see net.ogdf.ogml.OgmlPackage#getComposedType_Boolean()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='boolean' namespace='##targetNamespace' group='group:0'"
     * @generated
     */
    EList<BooleanType> getBoolean();

    /**
     * Returns the value of the '<em><b>Node</b></em>' containment reference list.
     * The list contents are of type {@link net.ogdf.ogml.NodeConstraintType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Node</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Node</em>' containment reference list.
     * @see net.ogdf.ogml.OgmlPackage#getComposedType_Node()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='node' namespace='##targetNamespace' group='group:0'"
     * @generated
     */
    EList<NodeConstraintType> getNode();

    /**
     * Returns the value of the '<em><b>Edge</b></em>' containment reference list.
     * The list contents are of type {@link net.ogdf.ogml.EdgeConstraintType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Edge</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Edge</em>' containment reference list.
     * @see net.ogdf.ogml.OgmlPackage#getComposedType_Edge()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='edge' namespace='##targetNamespace' group='group:0'"
     * @generated
     */
    EList<EdgeConstraintType> getEdge();

    /**
     * Returns the value of the '<em><b>Label</b></em>' containment reference list.
     * The list contents are of type {@link net.ogdf.ogml.LabelConstraintType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Label</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Label</em>' containment reference list.
     * @see net.ogdf.ogml.OgmlPackage#getComposedType_Label()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='label' namespace='##targetNamespace' group='group:0'"
     * @generated
     */
    EList<LabelConstraintType> getLabel();

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
     * @see net.ogdf.ogml.OgmlPackage#getComposedType_Composed()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='composed' namespace='##targetNamespace' group='group:0'"
     * @generated
     */
    EList<ComposedType> getComposed();

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
     * @see net.ogdf.ogml.OgmlPackage#getComposedType_Name()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='name' namespace='##targetNamespace'"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.ComposedType#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

} // ComposedType
