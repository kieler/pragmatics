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
 * A representation of the model object '<em><b>Point Type</b></em>'.
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
 *   <li>{@link net.ogdf.ogml.PointType#getData <em>Data</em>}</li>
 *   <li>{@link net.ogdf.ogml.PointType#getId <em>Id</em>}</li>
 *   <li>{@link net.ogdf.ogml.PointType#getX <em>X</em>}</li>
 *   <li>{@link net.ogdf.ogml.PointType#getY <em>Y</em>}</li>
 *   <li>{@link net.ogdf.ogml.PointType#getZ <em>Z</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.ogdf.ogml.OgmlPackage#getPointType()
 * @model extendedMetaData="name='point.type' kind='elementOnly'"
 * @generated
 */
public interface PointType extends EObject {
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
     * @see net.ogdf.ogml.OgmlPackage#getPointType_Data()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='data' namespace='##targetNamespace'"
     * @generated
     */
    EList<DataType> getData();

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
     * @see net.ogdf.ogml.OgmlPackage#getPointType_Id()
     * @model id="true" dataType="org.eclipse.emf.ecore.xml.type.ID"
     *        extendedMetaData="kind='attribute' name='id' namespace='##targetNamespace'"
     * @generated
     */
    String getId();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.PointType#getId <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Id</em>' attribute.
     * @see #getId()
     * @generated
     */
    void setId(String value);

    /**
     * Returns the value of the '<em><b>X</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>X</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>X</em>' attribute.
     * @see #isSetX()
     * @see #unsetX()
     * @see #setX(double)
     * @see net.ogdf.ogml.OgmlPackage#getPointType_X()
     * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Double" required="true"
     *        extendedMetaData="kind='attribute' name='x' namespace='##targetNamespace'"
     * @generated
     */
    double getX();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.PointType#getX <em>X</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>X</em>' attribute.
     * @see #isSetX()
     * @see #unsetX()
     * @see #getX()
     * @generated
     */
    void setX(double value);

    /**
     * Unsets the value of the '{@link net.ogdf.ogml.PointType#getX <em>X</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetX()
     * @see #getX()
     * @see #setX(double)
     * @generated
     */
    void unsetX();

    /**
     * Returns whether the value of the '{@link net.ogdf.ogml.PointType#getX <em>X</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>X</em>' attribute is set.
     * @see #unsetX()
     * @see #getX()
     * @see #setX(double)
     * @generated
     */
    boolean isSetX();

    /**
     * Returns the value of the '<em><b>Y</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Y</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Y</em>' attribute.
     * @see #isSetY()
     * @see #unsetY()
     * @see #setY(double)
     * @see net.ogdf.ogml.OgmlPackage#getPointType_Y()
     * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Double" required="true"
     *        extendedMetaData="kind='attribute' name='y' namespace='##targetNamespace'"
     * @generated
     */
    double getY();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.PointType#getY <em>Y</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Y</em>' attribute.
     * @see #isSetY()
     * @see #unsetY()
     * @see #getY()
     * @generated
     */
    void setY(double value);

    /**
     * Unsets the value of the '{@link net.ogdf.ogml.PointType#getY <em>Y</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetY()
     * @see #getY()
     * @see #setY(double)
     * @generated
     */
    void unsetY();

    /**
     * Returns whether the value of the '{@link net.ogdf.ogml.PointType#getY <em>Y</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Y</em>' attribute is set.
     * @see #unsetY()
     * @see #getY()
     * @see #setY(double)
     * @generated
     */
    boolean isSetY();

    /**
     * Returns the value of the '<em><b>Z</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Z</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Z</em>' attribute.
     * @see #isSetZ()
     * @see #unsetZ()
     * @see #setZ(double)
     * @see net.ogdf.ogml.OgmlPackage#getPointType_Z()
     * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Double"
     *        extendedMetaData="kind='attribute' name='z' namespace='##targetNamespace'"
     * @generated
     */
    double getZ();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.PointType#getZ <em>Z</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Z</em>' attribute.
     * @see #isSetZ()
     * @see #unsetZ()
     * @see #getZ()
     * @generated
     */
    void setZ(double value);

    /**
     * Unsets the value of the '{@link net.ogdf.ogml.PointType#getZ <em>Z</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetZ()
     * @see #getZ()
     * @see #setZ(double)
     * @generated
     */
    void unsetZ();

    /**
     * Returns whether the value of the '{@link net.ogdf.ogml.PointType#getZ <em>Z</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Z</em>' attribute is set.
     * @see #unsetZ()
     * @see #getZ()
     * @see #setZ(double)
     * @generated
     */
    boolean isSetZ();

} // PointType
