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
 * A representation of the model object '<em><b>Edge Layout Type</b></em>'.
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
 *   <li>{@link net.ogdf.ogml.EdgeLayoutType#getData <em>Data</em>}</li>
 *   <li>{@link net.ogdf.ogml.EdgeLayoutType#getTemplate <em>Template</em>}</li>
 *   <li>{@link net.ogdf.ogml.EdgeLayoutType#getLine <em>Line</em>}</li>
 *   <li>{@link net.ogdf.ogml.EdgeLayoutType#getSourceStyle <em>Source Style</em>}</li>
 *   <li>{@link net.ogdf.ogml.EdgeLayoutType#getTargetStyle <em>Target Style</em>}</li>
 *   <li>{@link net.ogdf.ogml.EdgeLayoutType#getPoint <em>Point</em>}</li>
 *   <li>{@link net.ogdf.ogml.EdgeLayoutType#getSegment <em>Segment</em>}</li>
 *   <li>{@link net.ogdf.ogml.EdgeLayoutType#getIdRef <em>Id Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.ogdf.ogml.OgmlPackage#getEdgeLayoutType()
 * @model extendedMetaData="name='edge_layout.type' kind='elementOnly'"
 * @generated
 */
public interface EdgeLayoutType extends EObject {
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
     * @see net.ogdf.ogml.OgmlPackage#getEdgeLayoutType_Data()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='data' namespace='##targetNamespace'"
     * @generated
     */
    EList<DataType> getData();

    /**
     * Returns the value of the '<em><b>Template</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Template</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Template</em>' containment reference.
     * @see #setTemplate(TemplateType)
     * @see net.ogdf.ogml.OgmlPackage#getEdgeLayoutType_Template()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='template' namespace='##targetNamespace'"
     * @generated
     */
    TemplateType getTemplate();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.EdgeLayoutType#getTemplate <em>Template</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Template</em>' containment reference.
     * @see #getTemplate()
     * @generated
     */
    void setTemplate(TemplateType value);

    /**
     * Returns the value of the '<em><b>Line</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * 
     * <!-- something missing -->
     *     
     * <!-- end-model-doc -->
     * @return the value of the '<em>Line</em>' containment reference.
     * @see #setLine(LineType)
     * @see net.ogdf.ogml.OgmlPackage#getEdgeLayoutType_Line()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='line' namespace='##targetNamespace'"
     * @generated
     */
    LineType getLine();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.EdgeLayoutType#getLine <em>Line</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Line</em>' containment reference.
     * @see #getLine()
     * @generated
     */
    void setLine(LineType value);

    /**
     * Returns the value of the '<em><b>Source Style</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * 
     * <!-- something missing -->
     *     
     * <!-- end-model-doc -->
     * @return the value of the '<em>Source Style</em>' containment reference.
     * @see #setSourceStyle(SourceStyleType)
     * @see net.ogdf.ogml.OgmlPackage#getEdgeLayoutType_SourceStyle()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='sourceStyle' namespace='##targetNamespace'"
     * @generated
     */
    SourceStyleType getSourceStyle();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.EdgeLayoutType#getSourceStyle <em>Source Style</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Source Style</em>' containment reference.
     * @see #getSourceStyle()
     * @generated
     */
    void setSourceStyle(SourceStyleType value);

    /**
     * Returns the value of the '<em><b>Target Style</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * 
     * <!-- something missing -->
     *     
     * <!-- end-model-doc -->
     * @return the value of the '<em>Target Style</em>' containment reference.
     * @see #setTargetStyle(TargetStyleType)
     * @see net.ogdf.ogml.OgmlPackage#getEdgeLayoutType_TargetStyle()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='targetStyle' namespace='##targetNamespace'"
     * @generated
     */
    TargetStyleType getTargetStyle();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.EdgeLayoutType#getTargetStyle <em>Target Style</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Target Style</em>' containment reference.
     * @see #getTargetStyle()
     * @generated
     */
    void setTargetStyle(TargetStyleType value);

    /**
     * Returns the value of the '<em><b>Point</b></em>' containment reference list.
     * The list contents are of type {@link net.ogdf.ogml.PointType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Point</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Point</em>' containment reference list.
     * @see net.ogdf.ogml.OgmlPackage#getEdgeLayoutType_Point()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='point' namespace='##targetNamespace'"
     * @generated
     */
    EList<PointType> getPoint();

    /**
     * Returns the value of the '<em><b>Segment</b></em>' containment reference list.
     * The list contents are of type {@link net.ogdf.ogml.SegmentType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Segment</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Segment</em>' containment reference list.
     * @see net.ogdf.ogml.OgmlPackage#getEdgeLayoutType_Segment()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='segment' namespace='##targetNamespace'"
     * @generated
     */
    EList<SegmentType> getSegment();

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
     * @see net.ogdf.ogml.OgmlPackage#getEdgeLayoutType_IdRef()
     * @model dataType="org.eclipse.emf.ecore.xml.type.IDREF"
     *        extendedMetaData="kind='attribute' name='idRef' namespace='##targetNamespace'"
     * @generated
     */
    String getIdRef();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.EdgeLayoutType#getIdRef <em>Id Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Id Ref</em>' attribute.
     * @see #getIdRef()
     * @generated
     */
    void setIdRef(String value);

} // EdgeLayoutType
