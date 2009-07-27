/**
 * <copyright>
 * </copyright>
 *
 */
package de.cau.cs.kieler.ksbase.featureDefinition;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see de.cau.cs.kieler.ksbase.featureDefinition.FeatureDefinitionFactory
 * @model kind="package"
 * @generated
 */
public interface FeatureDefinitionPackage extends EPackage
{
  /**
     * The package name.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  String eNAME = "featureDefinition";

  /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  String eNS_URI = "http://www.cau.de/cs/kieler/ksbase/FeatureDefinition";

  /**
     * The package namespace name.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  String eNS_PREFIX = "featureDefinition";

  /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  FeatureDefinitionPackage eINSTANCE = de.cau.cs.kieler.ksbase.featureDefinition.impl.FeatureDefinitionPackageImpl.init();

  /**
     * The meta object id for the '{@link de.cau.cs.kieler.ksbase.featureDefinition.impl.FeatureDefinitionsImpl <em>Feature Definitions</em>}' class.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.ksbase.featureDefinition.impl.FeatureDefinitionsImpl
     * @see de.cau.cs.kieler.ksbase.featureDefinition.impl.FeatureDefinitionPackageImpl#getFeatureDefinitions()
     * @generated
     */
  int FEATURE_DEFINITIONS = 0;

  /**
     * The feature id for the '<em><b>Model Name</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int FEATURE_DEFINITIONS__MODEL_NAME = 0;

  /**
     * The feature id for the '<em><b>Model Path</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int FEATURE_DEFINITIONS__MODEL_PATH = 1;

  /**
     * The feature id for the '<em><b>Feature Menu Title</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int FEATURE_DEFINITIONS__FEATURE_MENU_TITLE = 2;

  /**
     * The feature id for the '<em><b>Feature File</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int FEATURE_DEFINITIONS__FEATURE_FILE = 3;

  /**
     * The feature id for the '<em><b>Elements</b></em>' containment reference list.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int FEATURE_DEFINITIONS__ELEMENTS = 4;

  /**
     * The number of structural features of the '<em>Feature Definitions</em>' class.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int FEATURE_DEFINITIONS_FEATURE_COUNT = 5;

  /**
     * The meta object id for the '{@link de.cau.cs.kieler.ksbase.featureDefinition.impl.FeatureTypeImpl <em>Feature Type</em>}' class.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.ksbase.featureDefinition.impl.FeatureTypeImpl
     * @see de.cau.cs.kieler.ksbase.featureDefinition.impl.FeatureDefinitionPackageImpl#getFeatureType()
     * @generated
     */
  int FEATURE_TYPE = 1;

  /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int FEATURE_TYPE__NAME = 0;

  /**
     * The feature id for the '<em><b>File Name</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int FEATURE_TYPE__FILE_NAME = 1;

  /**
     * The feature id for the '<em><b>Method Name</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int FEATURE_TYPE__METHOD_NAME = 2;

  /**
     * The feature id for the '<em><b>Num Parameter</b></em>' attribute list.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int FEATURE_TYPE__NUM_PARAMETER = 3;

  /**
     * The feature id for the '<em><b>Parameter</b></em>' attribute list.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int FEATURE_TYPE__PARAMETER = 4;

  /**
     * The feature id for the '<em><b>Menu Entry</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int FEATURE_TYPE__MENU_ENTRY = 5;

  /**
     * The number of structural features of the '<em>Feature Type</em>' class.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int FEATURE_TYPE_FEATURE_COUNT = 6;


  /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.ksbase.featureDefinition.FeatureDefinitions <em>Feature Definitions</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for class '<em>Feature Definitions</em>'.
     * @see de.cau.cs.kieler.ksbase.featureDefinition.FeatureDefinitions
     * @generated
     */
  EClass getFeatureDefinitions();

  /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.ksbase.featureDefinition.FeatureDefinitions#getModelName <em>Model Name</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Model Name</em>'.
     * @see de.cau.cs.kieler.ksbase.featureDefinition.FeatureDefinitions#getModelName()
     * @see #getFeatureDefinitions()
     * @generated
     */
  EAttribute getFeatureDefinitions_ModelName();

  /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.ksbase.featureDefinition.FeatureDefinitions#getModelPath <em>Model Path</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Model Path</em>'.
     * @see de.cau.cs.kieler.ksbase.featureDefinition.FeatureDefinitions#getModelPath()
     * @see #getFeatureDefinitions()
     * @generated
     */
  EAttribute getFeatureDefinitions_ModelPath();

  /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.ksbase.featureDefinition.FeatureDefinitions#getFeatureMenuTitle <em>Feature Menu Title</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Feature Menu Title</em>'.
     * @see de.cau.cs.kieler.ksbase.featureDefinition.FeatureDefinitions#getFeatureMenuTitle()
     * @see #getFeatureDefinitions()
     * @generated
     */
  EAttribute getFeatureDefinitions_FeatureMenuTitle();

  /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.ksbase.featureDefinition.FeatureDefinitions#getFeatureFile <em>Feature File</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Feature File</em>'.
     * @see de.cau.cs.kieler.ksbase.featureDefinition.FeatureDefinitions#getFeatureFile()
     * @see #getFeatureDefinitions()
     * @generated
     */
  EAttribute getFeatureDefinitions_FeatureFile();

  /**
     * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.ksbase.featureDefinition.FeatureDefinitions#getElements <em>Elements</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Elements</em>'.
     * @see de.cau.cs.kieler.ksbase.featureDefinition.FeatureDefinitions#getElements()
     * @see #getFeatureDefinitions()
     * @generated
     */
  EReference getFeatureDefinitions_Elements();

  /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.ksbase.featureDefinition.FeatureType <em>Feature Type</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for class '<em>Feature Type</em>'.
     * @see de.cau.cs.kieler.ksbase.featureDefinition.FeatureType
     * @generated
     */
  EClass getFeatureType();

  /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.ksbase.featureDefinition.FeatureType#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see de.cau.cs.kieler.ksbase.featureDefinition.FeatureType#getName()
     * @see #getFeatureType()
     * @generated
     */
  EAttribute getFeatureType_Name();

  /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.ksbase.featureDefinition.FeatureType#getFileName <em>File Name</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>File Name</em>'.
     * @see de.cau.cs.kieler.ksbase.featureDefinition.FeatureType#getFileName()
     * @see #getFeatureType()
     * @generated
     */
  EAttribute getFeatureType_FileName();

  /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.ksbase.featureDefinition.FeatureType#getMethodName <em>Method Name</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Method Name</em>'.
     * @see de.cau.cs.kieler.ksbase.featureDefinition.FeatureType#getMethodName()
     * @see #getFeatureType()
     * @generated
     */
  EAttribute getFeatureType_MethodName();

  /**
     * Returns the meta object for the attribute list '{@link de.cau.cs.kieler.ksbase.featureDefinition.FeatureType#getNumParameter <em>Num Parameter</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Num Parameter</em>'.
     * @see de.cau.cs.kieler.ksbase.featureDefinition.FeatureType#getNumParameter()
     * @see #getFeatureType()
     * @generated
     */
  EAttribute getFeatureType_NumParameter();

  /**
     * Returns the meta object for the attribute list '{@link de.cau.cs.kieler.ksbase.featureDefinition.FeatureType#getParameter <em>Parameter</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Parameter</em>'.
     * @see de.cau.cs.kieler.ksbase.featureDefinition.FeatureType#getParameter()
     * @see #getFeatureType()
     * @generated
     */
  EAttribute getFeatureType_Parameter();

  /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.ksbase.featureDefinition.FeatureType#getMenuEntry <em>Menu Entry</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Menu Entry</em>'.
     * @see de.cau.cs.kieler.ksbase.featureDefinition.FeatureType#getMenuEntry()
     * @see #getFeatureType()
     * @generated
     */
  EAttribute getFeatureType_MenuEntry();

  /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
  FeatureDefinitionFactory getFeatureDefinitionFactory();

  /**
     * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
     * @generated
     */
  interface Literals
  {
    /**
         * The meta object literal for the '{@link de.cau.cs.kieler.ksbase.featureDefinition.impl.FeatureDefinitionsImpl <em>Feature Definitions</em>}' class.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.ksbase.featureDefinition.impl.FeatureDefinitionsImpl
         * @see de.cau.cs.kieler.ksbase.featureDefinition.impl.FeatureDefinitionPackageImpl#getFeatureDefinitions()
         * @generated
         */
    EClass FEATURE_DEFINITIONS = eINSTANCE.getFeatureDefinitions();

    /**
         * The meta object literal for the '<em><b>Model Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @generated
         */
    EAttribute FEATURE_DEFINITIONS__MODEL_NAME = eINSTANCE.getFeatureDefinitions_ModelName();

    /**
         * The meta object literal for the '<em><b>Model Path</b></em>' attribute feature.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @generated
         */
    EAttribute FEATURE_DEFINITIONS__MODEL_PATH = eINSTANCE.getFeatureDefinitions_ModelPath();

    /**
         * The meta object literal for the '<em><b>Feature Menu Title</b></em>' attribute feature.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @generated
         */
    EAttribute FEATURE_DEFINITIONS__FEATURE_MENU_TITLE = eINSTANCE.getFeatureDefinitions_FeatureMenuTitle();

    /**
         * The meta object literal for the '<em><b>Feature File</b></em>' attribute feature.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @generated
         */
    EAttribute FEATURE_DEFINITIONS__FEATURE_FILE = eINSTANCE.getFeatureDefinitions_FeatureFile();

    /**
         * The meta object literal for the '<em><b>Elements</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @generated
         */
    EReference FEATURE_DEFINITIONS__ELEMENTS = eINSTANCE.getFeatureDefinitions_Elements();

    /**
         * The meta object literal for the '{@link de.cau.cs.kieler.ksbase.featureDefinition.impl.FeatureTypeImpl <em>Feature Type</em>}' class.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.ksbase.featureDefinition.impl.FeatureTypeImpl
         * @see de.cau.cs.kieler.ksbase.featureDefinition.impl.FeatureDefinitionPackageImpl#getFeatureType()
         * @generated
         */
    EClass FEATURE_TYPE = eINSTANCE.getFeatureType();

    /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @generated
         */
    EAttribute FEATURE_TYPE__NAME = eINSTANCE.getFeatureType_Name();

    /**
         * The meta object literal for the '<em><b>File Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @generated
         */
    EAttribute FEATURE_TYPE__FILE_NAME = eINSTANCE.getFeatureType_FileName();

    /**
         * The meta object literal for the '<em><b>Method Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @generated
         */
    EAttribute FEATURE_TYPE__METHOD_NAME = eINSTANCE.getFeatureType_MethodName();

    /**
         * The meta object literal for the '<em><b>Num Parameter</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @generated
         */
    EAttribute FEATURE_TYPE__NUM_PARAMETER = eINSTANCE.getFeatureType_NumParameter();

    /**
         * The meta object literal for the '<em><b>Parameter</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @generated
         */
    EAttribute FEATURE_TYPE__PARAMETER = eINSTANCE.getFeatureType_Parameter();

    /**
         * The meta object literal for the '<em><b>Menu Entry</b></em>' attribute feature.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @generated
         */
    EAttribute FEATURE_TYPE__MENU_ENTRY = eINSTANCE.getFeatureType_MenuEntry();

  }

} //FeatureDefinitionPackage
