/**
 * <copyright>
 * </copyright>
 *
 */
package de.cau.cs.kieler.ksbase.featureDefinition.impl;

import de.cau.cs.kieler.ksbase.featureDefinition.FeatureDefinitionFactory;
import de.cau.cs.kieler.ksbase.featureDefinition.FeatureDefinitionPackage;
import de.cau.cs.kieler.ksbase.featureDefinition.FeatureDefinitions;
import de.cau.cs.kieler.ksbase.featureDefinition.FeatureType;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class FeatureDefinitionPackageImpl extends EPackageImpl implements FeatureDefinitionPackage
{
  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  private EClass featureDefinitionsEClass = null;

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  private EClass featureTypeEClass = null;

  /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
     * package URI value.
     * <p>Note: the correct way to create the package is via the static
     * factory method {@link #init init()}, which also performs
     * initialization of the package, or returns the registered package,
     * if one already exists.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see de.cau.cs.kieler.ksbase.featureDefinition.FeatureDefinitionPackage#eNS_URI
     * @see #init()
     * @generated
     */
  private FeatureDefinitionPackageImpl()
  {
        super(eNS_URI, FeatureDefinitionFactory.eINSTANCE);
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  private static boolean isInited = false;

  /**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
     * 
     * <p>This method is used to initialize {@link FeatureDefinitionPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
  public static FeatureDefinitionPackage init()
  {
        if (isInited) return (FeatureDefinitionPackage)EPackage.Registry.INSTANCE.getEPackage(FeatureDefinitionPackage.eNS_URI);

        // Obtain or create and register package
        FeatureDefinitionPackageImpl theFeatureDefinitionPackage = (FeatureDefinitionPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof FeatureDefinitionPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new FeatureDefinitionPackageImpl());

        isInited = true;

        // Create package meta-data objects
        theFeatureDefinitionPackage.createPackageContents();

        // Initialize created meta-data
        theFeatureDefinitionPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theFeatureDefinitionPackage.freeze();

  
        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(FeatureDefinitionPackage.eNS_URI, theFeatureDefinitionPackage);
        return theFeatureDefinitionPackage;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EClass getFeatureDefinitions()
  {
        return featureDefinitionsEClass;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EAttribute getFeatureDefinitions_ModelName()
  {
        return (EAttribute)featureDefinitionsEClass.getEStructuralFeatures().get(0);
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EAttribute getFeatureDefinitions_ModelPath()
  {
        return (EAttribute)featureDefinitionsEClass.getEStructuralFeatures().get(1);
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EAttribute getFeatureDefinitions_FeatureMenuTitle()
  {
        return (EAttribute)featureDefinitionsEClass.getEStructuralFeatures().get(2);
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EAttribute getFeatureDefinitions_FeatureFile()
  {
        return (EAttribute)featureDefinitionsEClass.getEStructuralFeatures().get(3);
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EReference getFeatureDefinitions_Elements()
  {
        return (EReference)featureDefinitionsEClass.getEStructuralFeatures().get(4);
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EClass getFeatureType()
  {
        return featureTypeEClass;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EAttribute getFeatureType_Name()
  {
        return (EAttribute)featureTypeEClass.getEStructuralFeatures().get(0);
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EAttribute getFeatureType_FileName()
  {
        return (EAttribute)featureTypeEClass.getEStructuralFeatures().get(1);
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EAttribute getFeatureType_MethodName()
  {
        return (EAttribute)featureTypeEClass.getEStructuralFeatures().get(2);
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EAttribute getFeatureType_NumParameter()
  {
        return (EAttribute)featureTypeEClass.getEStructuralFeatures().get(3);
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EAttribute getFeatureType_Parameter()
  {
        return (EAttribute)featureTypeEClass.getEStructuralFeatures().get(4);
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EAttribute getFeatureType_MenuEntry()
  {
        return (EAttribute)featureTypeEClass.getEStructuralFeatures().get(5);
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public FeatureDefinitionFactory getFeatureDefinitionFactory()
  {
        return (FeatureDefinitionFactory)getEFactoryInstance();
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  private boolean isCreated = false;

  /**
     * Creates the meta-model objects for the package.  This method is
     * guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void createPackageContents()
  {
        if (isCreated) return;
        isCreated = true;

        // Create classes and their features
        featureDefinitionsEClass = createEClass(FEATURE_DEFINITIONS);
        createEAttribute(featureDefinitionsEClass, FEATURE_DEFINITIONS__MODEL_NAME);
        createEAttribute(featureDefinitionsEClass, FEATURE_DEFINITIONS__MODEL_PATH);
        createEAttribute(featureDefinitionsEClass, FEATURE_DEFINITIONS__FEATURE_MENU_TITLE);
        createEAttribute(featureDefinitionsEClass, FEATURE_DEFINITIONS__FEATURE_FILE);
        createEReference(featureDefinitionsEClass, FEATURE_DEFINITIONS__ELEMENTS);

        featureTypeEClass = createEClass(FEATURE_TYPE);
        createEAttribute(featureTypeEClass, FEATURE_TYPE__NAME);
        createEAttribute(featureTypeEClass, FEATURE_TYPE__FILE_NAME);
        createEAttribute(featureTypeEClass, FEATURE_TYPE__METHOD_NAME);
        createEAttribute(featureTypeEClass, FEATURE_TYPE__NUM_PARAMETER);
        createEAttribute(featureTypeEClass, FEATURE_TYPE__PARAMETER);
        createEAttribute(featureTypeEClass, FEATURE_TYPE__MENU_ENTRY);
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  private boolean isInitialized = false;

  /**
     * Complete the initialization of the package and its meta-model.  This
     * method is guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void initializePackageContents()
  {
        if (isInitialized) return;
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes

        // Initialize classes and features; add operations and parameters
        initEClass(featureDefinitionsEClass, FeatureDefinitions.class, "FeatureDefinitions", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getFeatureDefinitions_ModelName(), ecorePackage.getEString(), "modelName", null, 0, 1, FeatureDefinitions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFeatureDefinitions_ModelPath(), ecorePackage.getEString(), "modelPath", null, 0, 1, FeatureDefinitions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFeatureDefinitions_FeatureMenuTitle(), ecorePackage.getEString(), "featureMenuTitle", null, 0, 1, FeatureDefinitions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFeatureDefinitions_FeatureFile(), ecorePackage.getEString(), "featureFile", null, 0, 1, FeatureDefinitions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getFeatureDefinitions_Elements(), this.getFeatureType(), null, "elements", null, 0, -1, FeatureDefinitions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(featureTypeEClass, FeatureType.class, "FeatureType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getFeatureType_Name(), ecorePackage.getEString(), "name", null, 0, 1, FeatureType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFeatureType_FileName(), ecorePackage.getEString(), "fileName", null, 0, 1, FeatureType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFeatureType_MethodName(), ecorePackage.getEString(), "methodName", null, 0, 1, FeatureType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFeatureType_NumParameter(), ecorePackage.getEInt(), "numParameter", null, 0, -1, FeatureType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFeatureType_Parameter(), ecorePackage.getEString(), "parameter", null, 0, -1, FeatureType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFeatureType_MenuEntry(), ecorePackage.getEString(), "menuEntry", null, 0, 1, FeatureType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Create resource
        createResource(eNS_URI);
    }

} //FeatureDefinitionPackageImpl
