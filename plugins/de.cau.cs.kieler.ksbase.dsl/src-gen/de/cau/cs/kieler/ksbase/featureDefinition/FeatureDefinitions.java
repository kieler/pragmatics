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
 * A representation of the model object '<em><b>Feature Definitions</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.ksbase.featureDefinition.FeatureDefinitions#getModelName <em>Model Name</em>}</li>
 *   <li>{@link de.cau.cs.kieler.ksbase.featureDefinition.FeatureDefinitions#getModelPath <em>Model Path</em>}</li>
 *   <li>{@link de.cau.cs.kieler.ksbase.featureDefinition.FeatureDefinitions#getFeatureMenuTitle <em>Feature Menu Title</em>}</li>
 *   <li>{@link de.cau.cs.kieler.ksbase.featureDefinition.FeatureDefinitions#getFeatureFile <em>Feature File</em>}</li>
 *   <li>{@link de.cau.cs.kieler.ksbase.featureDefinition.FeatureDefinitions#getElements <em>Elements</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.ksbase.featureDefinition.FeatureDefinitionPackage#getFeatureDefinitions()
 * @model
 * @generated
 */
public interface FeatureDefinitions extends EObject
{
  /**
     * Returns the value of the '<em><b>Model Name</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Model Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
     * @return the value of the '<em>Model Name</em>' attribute.
     * @see #setModelName(String)
     * @see de.cau.cs.kieler.ksbase.featureDefinition.FeatureDefinitionPackage#getFeatureDefinitions_ModelName()
     * @model
     * @generated
     */
  String getModelName();

  /**
     * Sets the value of the '{@link de.cau.cs.kieler.ksbase.featureDefinition.FeatureDefinitions#getModelName <em>Model Name</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @param value the new value of the '<em>Model Name</em>' attribute.
     * @see #getModelName()
     * @generated
     */
  void setModelName(String value);

  /**
     * Returns the value of the '<em><b>Model Path</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Model Path</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
     * @return the value of the '<em>Model Path</em>' attribute.
     * @see #setModelPath(String)
     * @see de.cau.cs.kieler.ksbase.featureDefinition.FeatureDefinitionPackage#getFeatureDefinitions_ModelPath()
     * @model
     * @generated
     */
  String getModelPath();

  /**
     * Sets the value of the '{@link de.cau.cs.kieler.ksbase.featureDefinition.FeatureDefinitions#getModelPath <em>Model Path</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @param value the new value of the '<em>Model Path</em>' attribute.
     * @see #getModelPath()
     * @generated
     */
  void setModelPath(String value);

  /**
     * Returns the value of the '<em><b>Feature Menu Title</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Feature Menu Title</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
     * @return the value of the '<em>Feature Menu Title</em>' attribute.
     * @see #setFeatureMenuTitle(String)
     * @see de.cau.cs.kieler.ksbase.featureDefinition.FeatureDefinitionPackage#getFeatureDefinitions_FeatureMenuTitle()
     * @model
     * @generated
     */
  String getFeatureMenuTitle();

  /**
     * Sets the value of the '{@link de.cau.cs.kieler.ksbase.featureDefinition.FeatureDefinitions#getFeatureMenuTitle <em>Feature Menu Title</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @param value the new value of the '<em>Feature Menu Title</em>' attribute.
     * @see #getFeatureMenuTitle()
     * @generated
     */
  void setFeatureMenuTitle(String value);

  /**
     * Returns the value of the '<em><b>Feature File</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Feature File</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
     * @return the value of the '<em>Feature File</em>' attribute.
     * @see #setFeatureFile(String)
     * @see de.cau.cs.kieler.ksbase.featureDefinition.FeatureDefinitionPackage#getFeatureDefinitions_FeatureFile()
     * @model
     * @generated
     */
  String getFeatureFile();

  /**
     * Sets the value of the '{@link de.cau.cs.kieler.ksbase.featureDefinition.FeatureDefinitions#getFeatureFile <em>Feature File</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @param value the new value of the '<em>Feature File</em>' attribute.
     * @see #getFeatureFile()
     * @generated
     */
  void setFeatureFile(String value);

  /**
     * Returns the value of the '<em><b>Elements</b></em>' containment reference list.
     * The list contents are of type {@link de.cau.cs.kieler.ksbase.featureDefinition.FeatureType}.
     * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Elements</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
     * @return the value of the '<em>Elements</em>' containment reference list.
     * @see de.cau.cs.kieler.ksbase.featureDefinition.FeatureDefinitionPackage#getFeatureDefinitions_Elements()
     * @model containment="true"
     * @generated
     */
  EList<FeatureType> getElements();

} // FeatureDefinitions
