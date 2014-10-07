/**
 */
package de.cau.cs.kieler.kiml.grana.text.grana.impl;

import de.cau.cs.kieler.kiml.grana.text.grana.Analysis;
import de.cau.cs.kieler.kiml.grana.text.grana.GlobalResourceRef;
import de.cau.cs.kieler.kiml.grana.text.grana.Grana;
import de.cau.cs.kieler.kiml.grana.text.grana.GranaFactory;
import de.cau.cs.kieler.kiml.grana.text.grana.GranaPackage;
import de.cau.cs.kieler.kiml.grana.text.grana.Job;
import de.cau.cs.kieler.kiml.grana.text.grana.LocalResource;
import de.cau.cs.kieler.kiml.grana.text.grana.Resource;
import de.cau.cs.kieler.kiml.grana.text.grana.ResourceReference;

import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataPackage;

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
public class GranaPackageImpl extends EPackageImpl implements GranaPackage
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass granaEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass jobEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass resourceEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass resourceReferenceEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass globalResourceRefEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass localResourceEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass analysisEClass = null;

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
   * @see de.cau.cs.kieler.kiml.grana.text.grana.GranaPackage#eNS_URI
   * @see #init()
   * @generated
   */
  private GranaPackageImpl()
  {
    super(eNS_URI, GranaFactory.eINSTANCE);
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
   * <p>This method is used to initialize {@link GranaPackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static GranaPackage init()
  {
    if (isInited) return (GranaPackage)EPackage.Registry.INSTANCE.getEPackage(GranaPackage.eNS_URI);

    // Obtain or create and register package
    GranaPackageImpl theGranaPackage = (GranaPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof GranaPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new GranaPackageImpl());

    isInited = true;

    // Initialize simple dependencies
    KLayoutDataPackage.eINSTANCE.eClass();

    // Create package meta-data objects
    theGranaPackage.createPackageContents();

    // Initialize created meta-data
    theGranaPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theGranaPackage.freeze();

  
    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(GranaPackage.eNS_URI, theGranaPackage);
    return theGranaPackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getGrana()
  {
    return granaEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getGrana_GlobalResources()
  {
    return (EReference)granaEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getGrana_Jobs()
  {
    return (EReference)granaEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getJob()
  {
    return jobEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getJob_Name()
  {
    return (EAttribute)jobEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getJob_LayoutBeforeAnalysis()
  {
    return (EAttribute)jobEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getJob_Resources()
  {
    return (EReference)jobEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getJob_LayoutOptions()
  {
    return (EReference)jobEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getJob_Analyses()
  {
    return (EReference)jobEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getJob_Output()
  {
    return (EAttribute)jobEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getResource()
  {
    return resourceEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getResourceReference()
  {
    return resourceReferenceEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getResourceReference_ResourceRefs()
  {
    return (EReference)resourceReferenceEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getGlobalResourceRef()
  {
    return globalResourceRefEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getGlobalResourceRef_Name()
  {
    return (EAttribute)globalResourceRefEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getGlobalResourceRef_Resources()
  {
    return (EReference)globalResourceRefEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getLocalResource()
  {
    return localResourceEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getLocalResource_Path()
  {
    return (EAttribute)localResourceEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getLocalResource_Filter()
  {
    return (EAttribute)localResourceEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAnalysis()
  {
    return analysisEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAnalysis_Name()
  {
    return (EAttribute)analysisEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GranaFactory getGranaFactory()
  {
    return (GranaFactory)getEFactoryInstance();
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
    granaEClass = createEClass(GRANA);
    createEReference(granaEClass, GRANA__GLOBAL_RESOURCES);
    createEReference(granaEClass, GRANA__JOBS);

    jobEClass = createEClass(JOB);
    createEAttribute(jobEClass, JOB__NAME);
    createEAttribute(jobEClass, JOB__LAYOUT_BEFORE_ANALYSIS);
    createEReference(jobEClass, JOB__RESOURCES);
    createEReference(jobEClass, JOB__LAYOUT_OPTIONS);
    createEReference(jobEClass, JOB__ANALYSES);
    createEAttribute(jobEClass, JOB__OUTPUT);

    resourceEClass = createEClass(RESOURCE);

    resourceReferenceEClass = createEClass(RESOURCE_REFERENCE);
    createEReference(resourceReferenceEClass, RESOURCE_REFERENCE__RESOURCE_REFS);

    globalResourceRefEClass = createEClass(GLOBAL_RESOURCE_REF);
    createEAttribute(globalResourceRefEClass, GLOBAL_RESOURCE_REF__NAME);
    createEReference(globalResourceRefEClass, GLOBAL_RESOURCE_REF__RESOURCES);

    localResourceEClass = createEClass(LOCAL_RESOURCE);
    createEAttribute(localResourceEClass, LOCAL_RESOURCE__PATH);
    createEAttribute(localResourceEClass, LOCAL_RESOURCE__FILTER);

    analysisEClass = createEClass(ANALYSIS);
    createEAttribute(analysisEClass, ANALYSIS__NAME);
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

    // Obtain other dependent packages
    KLayoutDataPackage theKLayoutDataPackage = (KLayoutDataPackage)EPackage.Registry.INSTANCE.getEPackage(KLayoutDataPackage.eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes
    resourceReferenceEClass.getESuperTypes().add(this.getResource());
    localResourceEClass.getESuperTypes().add(this.getResource());

    // Initialize classes and features; add operations and parameters
    initEClass(granaEClass, Grana.class, "Grana", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getGrana_GlobalResources(), this.getGlobalResourceRef(), null, "globalResources", null, 0, -1, Grana.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getGrana_Jobs(), this.getJob(), null, "jobs", null, 0, -1, Grana.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(jobEClass, Job.class, "Job", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getJob_Name(), ecorePackage.getEString(), "name", null, 0, 1, Job.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getJob_LayoutBeforeAnalysis(), ecorePackage.getEBoolean(), "layoutBeforeAnalysis", null, 0, 1, Job.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getJob_Resources(), this.getResource(), null, "resources", null, 0, -1, Job.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getJob_LayoutOptions(), theKLayoutDataPackage.getKIdentifier(), null, "layoutOptions", null, 0, -1, Job.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getJob_Analyses(), this.getAnalysis(), null, "analyses", null, 0, -1, Job.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getJob_Output(), ecorePackage.getEString(), "output", null, 0, 1, Job.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(resourceEClass, Resource.class, "Resource", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(resourceReferenceEClass, ResourceReference.class, "ResourceReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getResourceReference_ResourceRefs(), this.getGlobalResourceRef(), null, "resourceRefs", null, 0, -1, ResourceReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(globalResourceRefEClass, GlobalResourceRef.class, "GlobalResourceRef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getGlobalResourceRef_Name(), ecorePackage.getEString(), "name", null, 0, 1, GlobalResourceRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getGlobalResourceRef_Resources(), this.getLocalResource(), null, "resources", null, 0, -1, GlobalResourceRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(localResourceEClass, LocalResource.class, "LocalResource", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getLocalResource_Path(), ecorePackage.getEString(), "path", null, 0, 1, LocalResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getLocalResource_Filter(), ecorePackage.getEString(), "filter", null, 0, 1, LocalResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(analysisEClass, Analysis.class, "Analysis", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getAnalysis_Name(), ecorePackage.getEString(), "name", null, 0, 1, Analysis.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    // Create resource
    createResource(eNS_URI);
  }

} //GranaPackageImpl
