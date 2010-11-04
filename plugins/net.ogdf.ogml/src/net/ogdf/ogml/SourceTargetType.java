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
 * A representation of the model object '<em><b>Source Target Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 	  the soruceTarget element is for edge-declaration
 *       id is the identificator for the source/target
 *       idref references a node or an edge - the source i.e. the target of the edge
 *       a sourceTarget can hold one label
 *     
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.ogdf.ogml.SourceTargetType#getData <em>Data</em>}</li>
 *   <li>{@link net.ogdf.ogml.SourceTargetType#getLabel <em>Label</em>}</li>
 *   <li>{@link net.ogdf.ogml.SourceTargetType#getId <em>Id</em>}</li>
 *   <li>{@link net.ogdf.ogml.SourceTargetType#getIdRef <em>Id Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.ogdf.ogml.OgmlPackage#getSourceTargetType()
 * @model extendedMetaData="name='sourceTarget.type' kind='elementOnly'"
 * @generated
 */
public interface SourceTargetType extends EObject {
    /**
     * Returns the value of the '<em><b>Data</b></em>' containment reference list.
     * The list contents are of type {@link net.ogdf.ogml.DataType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Data</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Data</em>' containment reference list.
     * @see net.ogdf.ogml.OgmlPackage#getSourceTargetType_Data()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='data' namespace='##targetNamespace'"
     * @generated
     */
    EList<DataType> getData();

    /**
     * Returns the value of the '<em><b>Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Label</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Label</em>' containment reference.
     * @see #setLabel(LabelType)
     * @see net.ogdf.ogml.OgmlPackage#getSourceTargetType_Label()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='label' namespace='##targetNamespace'"
     * @generated
     */
    LabelType getLabel();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.SourceTargetType#getLabel <em>Label</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Label</em>' containment reference.
     * @see #getLabel()
     * @generated
     */
    void setLabel(LabelType value);

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
     * @see net.ogdf.ogml.OgmlPackage#getSourceTargetType_Id()
     * @model id="true" dataType="org.eclipse.emf.ecore.xml.type.ID"
     *        extendedMetaData="kind='attribute' name='id' namespace='##targetNamespace'"
     * @generated
     */
    String getId();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.SourceTargetType#getId <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Id</em>' attribute.
     * @see #getId()
     * @generated
     */
    void setId(String value);

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
     * @see net.ogdf.ogml.OgmlPackage#getSourceTargetType_IdRef()
     * @model dataType="org.eclipse.emf.ecore.xml.type.IDREF" required="true"
     *        extendedMetaData="kind='attribute' name='idRef' namespace='##targetNamespace'"
     * @generated
     */
    String getIdRef();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.SourceTargetType#getIdRef <em>Id Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Id Ref</em>' attribute.
     * @see #getIdRef()
     * @generated
     */
    void setIdRef(String value);

} // SourceTargetType
