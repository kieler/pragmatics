/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.ogdf.ogml;

import java.math.BigInteger;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Endpoint Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 *       idRef references a source-, target or point - id ( =&gt; node or an endpoint of a segment)
 * 	  the port is defined by an angle (ignorable if idRef references a point)
 * 	  style is the decoration of the endpoints
 *     
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.ogdf.ogml.EndpointType#getColor <em>Color</em>}</li>
 *   <li>{@link net.ogdf.ogml.EndpointType#getIdRef <em>Id Ref</em>}</li>
 *   <li>{@link net.ogdf.ogml.EndpointType#getPort <em>Port</em>}</li>
 *   <li>{@link net.ogdf.ogml.EndpointType#getSize <em>Size</em>}</li>
 *   <li>{@link net.ogdf.ogml.EndpointType#getStyle <em>Style</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.ogdf.ogml.OgmlPackage#getEndpointType()
 * @model extendedMetaData="name='endpoint.type' kind='empty'"
 * @generated
 */
public interface EndpointType extends EObject {
    /**
     * Returns the value of the '<em><b>Color</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Color</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Color</em>' attribute.
     * @see #setColor(String)
     * @see net.ogdf.ogml.OgmlPackage#getEndpointType_Color()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='color' namespace='##targetNamespace'"
     * @generated
     */
    String getColor();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.EndpointType#getColor <em>Color</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Color</em>' attribute.
     * @see #getColor()
     * @generated
     */
    void setColor(String value);

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
     * @see net.ogdf.ogml.OgmlPackage#getEndpointType_IdRef()
     * @model dataType="org.eclipse.emf.ecore.xml.type.IDREF" required="true"
     *        extendedMetaData="kind='attribute' name='idRef' namespace='##targetNamespace'"
     * @generated
     */
    String getIdRef();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.EndpointType#getIdRef <em>Id Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Id Ref</em>' attribute.
     * @see #getIdRef()
     * @generated
     */
    void setIdRef(String value);

    /**
     * Returns the value of the '<em><b>Port</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Port</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Port</em>' attribute.
     * @see #setPort(BigInteger)
     * @see net.ogdf.ogml.OgmlPackage#getEndpointType_Port()
     * @model dataType="org.eclipse.emf.ecore.xml.type.Integer"
     *        extendedMetaData="kind='attribute' name='port' namespace='##targetNamespace'"
     * @generated
     */
    BigInteger getPort();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.EndpointType#getPort <em>Port</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Port</em>' attribute.
     * @see #getPort()
     * @generated
     */
    void setPort(BigInteger value);

    /**
     * Returns the value of the '<em><b>Size</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Size</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Size</em>' attribute.
     * @see #isSetSize()
     * @see #unsetSize()
     * @see #setSize(double)
     * @see net.ogdf.ogml.OgmlPackage#getEndpointType_Size()
     * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Double"
     *        extendedMetaData="kind='attribute' name='size' namespace='##targetNamespace'"
     * @generated
     */
    double getSize();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.EndpointType#getSize <em>Size</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Size</em>' attribute.
     * @see #isSetSize()
     * @see #unsetSize()
     * @see #getSize()
     * @generated
     */
    void setSize(double value);

    /**
     * Unsets the value of the '{@link net.ogdf.ogml.EndpointType#getSize <em>Size</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetSize()
     * @see #getSize()
     * @see #setSize(double)
     * @generated
     */
    void unsetSize();

    /**
     * Returns whether the value of the '{@link net.ogdf.ogml.EndpointType#getSize <em>Size</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Size</em>' attribute is set.
     * @see #unsetSize()
     * @see #getSize()
     * @see #setSize(double)
     * @generated
     */
    boolean isSetSize();

    /**
     * Returns the value of the '<em><b>Style</b></em>' attribute.
     * The literals are from the enumeration {@link net.ogdf.ogml.EndpointStylesType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Style</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Style</em>' attribute.
     * @see net.ogdf.ogml.EndpointStylesType
     * @see #isSetStyle()
     * @see #unsetStyle()
     * @see #setStyle(EndpointStylesType)
     * @see net.ogdf.ogml.OgmlPackage#getEndpointType_Style()
     * @model unsettable="true"
     *        extendedMetaData="kind='attribute' name='style' namespace='##targetNamespace'"
     * @generated
     */
    EndpointStylesType getStyle();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.EndpointType#getStyle <em>Style</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Style</em>' attribute.
     * @see net.ogdf.ogml.EndpointStylesType
     * @see #isSetStyle()
     * @see #unsetStyle()
     * @see #getStyle()
     * @generated
     */
    void setStyle(EndpointStylesType value);

    /**
     * Unsets the value of the '{@link net.ogdf.ogml.EndpointType#getStyle <em>Style</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetStyle()
     * @see #getStyle()
     * @see #setStyle(EndpointStylesType)
     * @generated
     */
    void unsetStyle();

    /**
     * Returns whether the value of the '{@link net.ogdf.ogml.EndpointType#getStyle <em>Style</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Style</em>' attribute is set.
     * @see #unsetStyle()
     * @see #getStyle()
     * @see #setStyle(EndpointStylesType)
     * @generated
     */
    boolean isSetStyle();

} // EndpointType
