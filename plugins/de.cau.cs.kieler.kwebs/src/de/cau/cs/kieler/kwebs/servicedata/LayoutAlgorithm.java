/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.cs.kieler.kwebs.servicedata;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Layout Algorithm</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.kwebs.servicedata.LayoutAlgorithm#getId <em>Id</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.servicedata.LayoutAlgorithm#getName <em>Name</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.servicedata.LayoutAlgorithm#getDescription <em>Description</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.servicedata.LayoutAlgorithm#getKnownOptions <em>Known Options</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.servicedata.LayoutAlgorithm#getSupportedDiagrams <em>Supported Diagrams</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.servicedata.LayoutAlgorithm#getCategory <em>Category</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.servicedata.LayoutAlgorithm#getType <em>Type</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.servicedata.LayoutAlgorithm#getVersion <em>Version</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.servicedata.LayoutAlgorithm#getPreviewImagePath <em>Preview Image Path</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.servicedata.LayoutAlgorithm#getPreviewImagePort <em>Preview Image Port</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.kwebs.servicedata.ServiceDataPackage#getLayoutAlgorithm()
 * @model
 * @generated
 */
public interface LayoutAlgorithm extends EObject {
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
     * @see de.cau.cs.kieler.kwebs.servicedata.ServiceDataPackage#getLayoutAlgorithm_Id()
     * @model required="true"
     * @generated
     */
    String getId();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.kwebs.servicedata.LayoutAlgorithm#getId <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Id</em>' attribute.
     * @see #getId()
     * @generated
     */
    void setId(String value);

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
     * @see de.cau.cs.kieler.kwebs.servicedata.ServiceDataPackage#getLayoutAlgorithm_Name()
     * @model required="true"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.kwebs.servicedata.LayoutAlgorithm#getName <em>Name</em>}' attribute.
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
     * @see de.cau.cs.kieler.kwebs.servicedata.ServiceDataPackage#getLayoutAlgorithm_Description()
     * @model
     * @generated
     */
    String getDescription();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.kwebs.servicedata.LayoutAlgorithm#getDescription <em>Description</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Description</em>' attribute.
     * @see #getDescription()
     * @generated
     */
    void setDescription(String value);

    /**
     * Returns the value of the '<em><b>Known Options</b></em>' containment reference list.
     * The list contents are of type {@link de.cau.cs.kieler.kwebs.servicedata.KnownOption}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Known Options</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Known Options</em>' containment reference list.
     * @see de.cau.cs.kieler.kwebs.servicedata.ServiceDataPackage#getLayoutAlgorithm_KnownOptions()
     * @model containment="true"
     * @generated
     */
    EList<KnownOption> getKnownOptions();

    /**
     * Returns the value of the '<em><b>Supported Diagrams</b></em>' containment reference list.
     * The list contents are of type {@link de.cau.cs.kieler.kwebs.servicedata.SupportedDiagram}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Supported Diagrams</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Supported Diagrams</em>' containment reference list.
     * @see de.cau.cs.kieler.kwebs.servicedata.ServiceDataPackage#getLayoutAlgorithm_SupportedDiagrams()
     * @model containment="true"
     * @generated
     */
    EList<SupportedDiagram> getSupportedDiagrams();

    /**
     * Returns the value of the '<em><b>Category</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Category</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Category</em>' reference.
     * @see #setCategory(Category)
     * @see de.cau.cs.kieler.kwebs.servicedata.ServiceDataPackage#getLayoutAlgorithm_Category()
     * @model
     * @generated
     */
    Category getCategory();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.kwebs.servicedata.LayoutAlgorithm#getCategory <em>Category</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Category</em>' reference.
     * @see #getCategory()
     * @generated
     */
    void setCategory(Category value);

    /**
     * Returns the value of the '<em><b>Type</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Type</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Type</em>' reference.
     * @see #setType(LayoutType)
     * @see de.cau.cs.kieler.kwebs.servicedata.ServiceDataPackage#getLayoutAlgorithm_Type()
     * @model
     * @generated
     */
    LayoutType getType();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.kwebs.servicedata.LayoutAlgorithm#getType <em>Type</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Type</em>' reference.
     * @see #getType()
     * @generated
     */
    void setType(LayoutType value);

    /**
     * Returns the value of the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Version</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Version</em>' attribute.
     * @see #setVersion(String)
     * @see de.cau.cs.kieler.kwebs.servicedata.ServiceDataPackage#getLayoutAlgorithm_Version()
     * @model
     * @generated
     */
    String getVersion();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.kwebs.servicedata.LayoutAlgorithm#getVersion <em>Version</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Version</em>' attribute.
     * @see #getVersion()
     * @generated
     */
    void setVersion(String value);

    /**
     * Returns the value of the '<em><b>Preview Image Path</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Preview Image Path</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Preview Image Path</em>' attribute.
     * @see #setPreviewImagePath(String)
     * @see de.cau.cs.kieler.kwebs.servicedata.ServiceDataPackage#getLayoutAlgorithm_PreviewImagePath()
     * @model
     * @generated
     */
    String getPreviewImagePath();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.kwebs.servicedata.LayoutAlgorithm#getPreviewImagePath <em>Preview Image Path</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Preview Image Path</em>' attribute.
     * @see #getPreviewImagePath()
     * @generated
     */
    void setPreviewImagePath(String value);

    /**
     * Returns the value of the '<em><b>Preview Image Port</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Preview Image Port</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Preview Image Port</em>' attribute.
     * @see #setPreviewImagePort(int)
     * @see de.cau.cs.kieler.kwebs.servicedata.ServiceDataPackage#getLayoutAlgorithm_PreviewImagePort()
     * @model
     * @generated
     */
    int getPreviewImagePort();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.kwebs.servicedata.LayoutAlgorithm#getPreviewImagePort <em>Preview Image Port</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Preview Image Port</em>' attribute.
     * @see #getPreviewImagePort()
     * @generated
     */
    void setPreviewImagePort(int value);

} // LayoutAlgorithm
