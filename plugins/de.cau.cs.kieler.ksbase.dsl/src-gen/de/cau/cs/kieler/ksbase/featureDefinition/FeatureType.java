/**
 * <copyright>
 * </copyright>
 *
 */
package de.cau.cs.kieler.ksbase.featureDefinition;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Feature Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.ksbase.featureDefinition.FeatureType#getName <em>Name</em>}</li>
 *   <li>{@link de.cau.cs.kieler.ksbase.featureDefinition.FeatureType#getFileName <em>File Name</em>}</li>
 *   <li>{@link de.cau.cs.kieler.ksbase.featureDefinition.FeatureType#getMethodName <em>Method Name</em>}</li>
 *   <li>{@link de.cau.cs.kieler.ksbase.featureDefinition.FeatureType#getNumParameter <em>Num Parameter</em>}</li>
 *   <li>{@link de.cau.cs.kieler.ksbase.featureDefinition.FeatureType#getParameter <em>Parameter</em>}</li>
 *   <li>{@link de.cau.cs.kieler.ksbase.featureDefinition.FeatureType#getMenuEntry <em>Menu Entry</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.ksbase.featureDefinition.FeatureDefinitionPackage#getFeatureType()
 * @model
 * @generated
 */
public interface FeatureType extends EObject
{
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
     * @see de.cau.cs.kieler.ksbase.featureDefinition.FeatureDefinitionPackage#getFeatureType_Name()
     * @model
     * @generated
     */
  String getName();

  /**
     * Sets the value of the '{@link de.cau.cs.kieler.ksbase.featureDefinition.FeatureType#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
  void setName(String value);

  /**
     * Returns the value of the '<em><b>File Name</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>File Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
     * @return the value of the '<em>File Name</em>' attribute.
     * @see #setFileName(String)
     * @see de.cau.cs.kieler.ksbase.featureDefinition.FeatureDefinitionPackage#getFeatureType_FileName()
     * @model
     * @generated
     */
  String getFileName();

  /**
     * Sets the value of the '{@link de.cau.cs.kieler.ksbase.featureDefinition.FeatureType#getFileName <em>File Name</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @param value the new value of the '<em>File Name</em>' attribute.
     * @see #getFileName()
     * @generated
     */
  void setFileName(String value);

  /**
     * Returns the value of the '<em><b>Method Name</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Method Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
     * @return the value of the '<em>Method Name</em>' attribute.
     * @see #setMethodName(String)
     * @see de.cau.cs.kieler.ksbase.featureDefinition.FeatureDefinitionPackage#getFeatureType_MethodName()
     * @model
     * @generated
     */
  String getMethodName();

  /**
     * Sets the value of the '{@link de.cau.cs.kieler.ksbase.featureDefinition.FeatureType#getMethodName <em>Method Name</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @param value the new value of the '<em>Method Name</em>' attribute.
     * @see #getMethodName()
     * @generated
     */
  void setMethodName(String value);

  /**
     * Returns the value of the '<em><b>Num Parameter</b></em>' attribute list.
     * The list contents are of type {@link java.lang.Integer}.
     * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Num Parameter</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
     * @return the value of the '<em>Num Parameter</em>' attribute list.
     * @see de.cau.cs.kieler.ksbase.featureDefinition.FeatureDefinitionPackage#getFeatureType_NumParameter()
     * @model unique="false"
     * @generated
     */
  EList<Integer> getNumParameter();

  /**
     * Returns the value of the '<em><b>Parameter</b></em>' attribute list.
     * The list contents are of type {@link java.lang.String}.
     * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Parameter</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
     * @return the value of the '<em>Parameter</em>' attribute list.
     * @see de.cau.cs.kieler.ksbase.featureDefinition.FeatureDefinitionPackage#getFeatureType_Parameter()
     * @model unique="false"
     * @generated
     */
  EList<String> getParameter();

  /**
     * Returns the value of the '<em><b>Menu Entry</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Menu Entry</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
     * @return the value of the '<em>Menu Entry</em>' attribute.
     * @see #setMenuEntry(String)
     * @see de.cau.cs.kieler.ksbase.featureDefinition.FeatureDefinitionPackage#getFeatureType_MenuEntry()
     * @model
     * @generated
     */
  String getMenuEntry();

  /**
     * Sets the value of the '{@link de.cau.cs.kieler.ksbase.featureDefinition.FeatureType#getMenuEntry <em>Menu Entry</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @param value the new value of the '<em>Menu Entry</em>' attribute.
     * @see #getMenuEntry()
     * @generated
     */
  void setMenuEntry(String value);

} // FeatureType
