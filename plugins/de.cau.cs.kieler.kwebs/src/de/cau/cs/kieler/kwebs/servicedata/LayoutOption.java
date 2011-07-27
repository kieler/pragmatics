/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.cs.kieler.kwebs.servicedata;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Layout Option</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.kwebs.servicedata.LayoutOption#getId <em>Id</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.servicedata.LayoutOption#getType <em>Type</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.servicedata.LayoutOption#getName <em>Name</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.servicedata.LayoutOption#getDescription <em>Description</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.servicedata.LayoutOption#getAppliesTo <em>Applies To</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.servicedata.LayoutOption#getDefault <em>Default</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.servicedata.LayoutOption#isAdvanced <em>Advanced</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.servicedata.LayoutOption#getRemoteEnum <em>Remote Enum</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.servicedata.LayoutOption#getImplementation <em>Implementation</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.kwebs.servicedata.ServiceDataPackage#getLayoutOption()
 * @model
 * @generated
 */
public interface LayoutOption extends EObject {
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
     * @see de.cau.cs.kieler.kwebs.servicedata.ServiceDataPackage#getLayoutOption_Id()
     * @model required="true"
     * @generated
     */
    String getId();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.kwebs.servicedata.LayoutOption#getId <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Id</em>' attribute.
     * @see #getId()
     * @generated
     */
    void setId(String value);

    /**
     * Returns the value of the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Type</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Type</em>' attribute.
     * @see #setType(String)
     * @see de.cau.cs.kieler.kwebs.servicedata.ServiceDataPackage#getLayoutOption_Type()
     * @model required="true"
     * @generated
     */
    String getType();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.kwebs.servicedata.LayoutOption#getType <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Type</em>' attribute.
     * @see #getType()
     * @generated
     */
    void setType(String value);

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
     * @see de.cau.cs.kieler.kwebs.servicedata.ServiceDataPackage#getLayoutOption_Name()
     * @model required="true"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.kwebs.servicedata.LayoutOption#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Description</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Description</em>' attribute.
     * @see #setDescription(String)
     * @see de.cau.cs.kieler.kwebs.servicedata.ServiceDataPackage#getLayoutOption_Description()
     * @model
     * @generated
     */
    String getDescription();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.kwebs.servicedata.LayoutOption#getDescription <em>Description</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Description</em>' attribute.
     * @see #getDescription()
     * @generated
     */
    void setDescription(String value);

    /**
     * Returns the value of the '<em><b>Applies To</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Applies To</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Applies To</em>' attribute.
     * @see #setAppliesTo(String)
     * @see de.cau.cs.kieler.kwebs.servicedata.ServiceDataPackage#getLayoutOption_AppliesTo()
     * @model
     * @generated
     */
    String getAppliesTo();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.kwebs.servicedata.LayoutOption#getAppliesTo <em>Applies To</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Applies To</em>' attribute.
     * @see #getAppliesTo()
     * @generated
     */
    void setAppliesTo(String value);

    /**
     * Returns the value of the '<em><b>Default</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Default</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Default</em>' attribute.
     * @see #setDefault(String)
     * @see de.cau.cs.kieler.kwebs.servicedata.ServiceDataPackage#getLayoutOption_Default()
     * @model
     * @generated
     */
    String getDefault();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.kwebs.servicedata.LayoutOption#getDefault <em>Default</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Default</em>' attribute.
     * @see #getDefault()
     * @generated
     */
    void setDefault(String value);

    /**
     * Returns the value of the '<em><b>Advanced</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Advanced</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Advanced</em>' attribute.
     * @see #setAdvanced(boolean)
     * @see de.cau.cs.kieler.kwebs.servicedata.ServiceDataPackage#getLayoutOption_Advanced()
     * @model
     * @generated
     */
    boolean isAdvanced();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.kwebs.servicedata.LayoutOption#isAdvanced <em>Advanced</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Advanced</em>' attribute.
     * @see #isAdvanced()
     * @generated
     */
    void setAdvanced(boolean value);

    /**
     * Returns the value of the '<em><b>Remote Enum</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Remote Enum</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Remote Enum</em>' containment reference.
     * @see #setRemoteEnum(RemoteEnum)
     * @see de.cau.cs.kieler.kwebs.servicedata.ServiceDataPackage#getLayoutOption_RemoteEnum()
     * @model containment="true"
     * @generated
     */
    RemoteEnum getRemoteEnum();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.kwebs.servicedata.LayoutOption#getRemoteEnum <em>Remote Enum</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Remote Enum</em>' containment reference.
     * @see #getRemoteEnum()
     * @generated
     */
    void setRemoteEnum(RemoteEnum value);

    /**
     * Returns the value of the '<em><b>Implementation</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Implementation</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Implementation</em>' attribute.
     * @see #setImplementation(String)
     * @see de.cau.cs.kieler.kwebs.servicedata.ServiceDataPackage#getLayoutOption_Implementation()
     * @model
     * @generated
     */
    String getImplementation();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.kwebs.servicedata.LayoutOption#getImplementation <em>Implementation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Implementation</em>' attribute.
     * @see #getImplementation()
     * @generated
     */
    void setImplementation(String value);

} // LayoutOption
