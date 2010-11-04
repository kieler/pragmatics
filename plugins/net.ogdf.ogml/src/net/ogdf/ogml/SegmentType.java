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
 * A representation of the model object '<em><b>Segment Type</b></em>'.
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
 *   <li>{@link net.ogdf.ogml.SegmentType#getData <em>Data</em>}</li>
 *   <li>{@link net.ogdf.ogml.SegmentType#getLine <em>Line</em>}</li>
 *   <li>{@link net.ogdf.ogml.SegmentType#getEndpoint <em>Endpoint</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.ogdf.ogml.OgmlPackage#getSegmentType()
 * @model extendedMetaData="name='segment.type' kind='elementOnly'"
 * @generated
 */
public interface SegmentType extends EObject {
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
     * @see net.ogdf.ogml.OgmlPackage#getSegmentType_Data()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='data' namespace='##targetNamespace'"
     * @generated
     */
    EList<DataType> getData();

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
     * @see net.ogdf.ogml.OgmlPackage#getSegmentType_Line()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='line' namespace='##targetNamespace'"
     * @generated
     */
    LineType getLine();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.SegmentType#getLine <em>Line</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Line</em>' containment reference.
     * @see #getLine()
     * @generated
     */
    void setLine(LineType value);

    /**
     * Returns the value of the '<em><b>Endpoint</b></em>' containment reference list.
     * The list contents are of type {@link net.ogdf.ogml.EndpointType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Endpoint</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Endpoint</em>' containment reference list.
     * @see net.ogdf.ogml.OgmlPackage#getSegmentType_Endpoint()
     * @model containment="true" lower="2" upper="2"
     *        extendedMetaData="kind='element' name='endpoint' namespace='##targetNamespace'"
     * @generated
     */
    EList<EndpointType> getEndpoint();

} // SegmentType
