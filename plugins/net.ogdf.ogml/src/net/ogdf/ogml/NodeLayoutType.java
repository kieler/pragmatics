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
 * A representation of the model object '<em><b>Node Layout Type</b></em>'.
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
 *   <li>{@link net.ogdf.ogml.NodeLayoutType#getData <em>Data</em>}</li>
 *   <li>{@link net.ogdf.ogml.NodeLayoutType#getTemplate <em>Template</em>}</li>
 *   <li>{@link net.ogdf.ogml.NodeLayoutType#getLocation <em>Location</em>}</li>
 *   <li>{@link net.ogdf.ogml.NodeLayoutType#getShape <em>Shape</em>}</li>
 *   <li>{@link net.ogdf.ogml.NodeLayoutType#getFill <em>Fill</em>}</li>
 *   <li>{@link net.ogdf.ogml.NodeLayoutType#getLine <em>Line</em>}</li>
 *   <li>{@link net.ogdf.ogml.NodeLayoutType#getIdRef <em>Id Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.ogdf.ogml.OgmlPackage#getNodeLayoutType()
 * @model extendedMetaData="name='node_layout.type' kind='elementOnly'"
 * @generated
 */
public interface NodeLayoutType extends EObject {
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
     * @see net.ogdf.ogml.OgmlPackage#getNodeLayoutType_Data()
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
     * @see net.ogdf.ogml.OgmlPackage#getNodeLayoutType_Template()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='template' namespace='##targetNamespace'"
     * @generated
     */
    TemplateType getTemplate();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.NodeLayoutType#getTemplate <em>Template</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Template</em>' containment reference.
     * @see #getTemplate()
     * @generated
     */
    void setTemplate(TemplateType value);

    /**
     * Returns the value of the '<em><b>Location</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Location</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Location</em>' containment reference.
     * @see #setLocation(LocationType)
     * @see net.ogdf.ogml.OgmlPackage#getNodeLayoutType_Location()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='location' namespace='##targetNamespace'"
     * @generated
     */
    LocationType getLocation();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.NodeLayoutType#getLocation <em>Location</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Location</em>' containment reference.
     * @see #getLocation()
     * @generated
     */
    void setLocation(LocationType value);

    /**
     * Returns the value of the '<em><b>Shape</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * 
     * <!-- something missing -->
     *     
     * <!-- end-model-doc -->
     * @return the value of the '<em>Shape</em>' containment reference.
     * @see #setShape(ShapeType1)
     * @see net.ogdf.ogml.OgmlPackage#getNodeLayoutType_Shape()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='shape' namespace='##targetNamespace'"
     * @generated
     */
    ShapeType1 getShape();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.NodeLayoutType#getShape <em>Shape</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Shape</em>' containment reference.
     * @see #getShape()
     * @generated
     */
    void setShape(ShapeType1 value);

    /**
     * Returns the value of the '<em><b>Fill</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * 
     * <!-- something missing -->
     *     
     * <!-- end-model-doc -->
     * @return the value of the '<em>Fill</em>' containment reference.
     * @see #setFill(FillType)
     * @see net.ogdf.ogml.OgmlPackage#getNodeLayoutType_Fill()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='fill' namespace='##targetNamespace'"
     * @generated
     */
    FillType getFill();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.NodeLayoutType#getFill <em>Fill</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Fill</em>' containment reference.
     * @see #getFill()
     * @generated
     */
    void setFill(FillType value);

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
     * @see net.ogdf.ogml.OgmlPackage#getNodeLayoutType_Line()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='line' namespace='##targetNamespace'"
     * @generated
     */
    LineType getLine();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.NodeLayoutType#getLine <em>Line</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Line</em>' containment reference.
     * @see #getLine()
     * @generated
     */
    void setLine(LineType value);

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
     * @see net.ogdf.ogml.OgmlPackage#getNodeLayoutType_IdRef()
     * @model dataType="org.eclipse.emf.ecore.xml.type.IDREF"
     *        extendedMetaData="kind='attribute' name='idRef' namespace='##targetNamespace'"
     * @generated
     */
    String getIdRef();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.NodeLayoutType#getIdRef <em>Id Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Id Ref</em>' attribute.
     * @see #getIdRef()
     * @generated
     */
    void setIdRef(String value);

} // NodeLayoutType
