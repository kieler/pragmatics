/**
 */
package de.cau.cs.kieler.grana.text.grana.impl;

import de.cau.cs.kieler.grana.text.grana.Analysis;
import de.cau.cs.kieler.grana.text.grana.CompareJob;
import de.cau.cs.kieler.grana.text.grana.FloatRange;
import de.cau.cs.kieler.grana.text.grana.GlobalOutputRef;
import de.cau.cs.kieler.grana.text.grana.GlobalResourceRef;
import de.cau.cs.kieler.grana.text.grana.Grana;
import de.cau.cs.kieler.grana.text.grana.GranaFactory;
import de.cau.cs.kieler.grana.text.grana.GranaPackage;
import de.cau.cs.kieler.grana.text.grana.IntRange;
import de.cau.cs.kieler.grana.text.grana.IntRangeRange;
import de.cau.cs.kieler.grana.text.grana.IntRangeValues;
import de.cau.cs.kieler.grana.text.grana.Job;
import de.cau.cs.kieler.grana.text.grana.LocalOutput;
import de.cau.cs.kieler.grana.text.grana.LocalResource;
import de.cau.cs.kieler.grana.text.grana.Output;
import de.cau.cs.kieler.grana.text.grana.OutputReference;
import de.cau.cs.kieler.grana.text.grana.OutputType;
import de.cau.cs.kieler.grana.text.grana.Range;
import de.cau.cs.kieler.grana.text.grana.RangeJob;
import de.cau.cs.kieler.grana.text.grana.RegularJob;
import de.cau.cs.kieler.grana.text.grana.Resource;
import de.cau.cs.kieler.grana.text.grana.ResourceReference;

import org.eclipse.elk.graph.ElkGraphPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
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
  private EClass regularJobEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass compareJobEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass rangeJobEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass rangeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass floatRangeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass intRangeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass intRangeValuesEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass intRangeRangeEClass = null;

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
  private EClass outputEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass globalOutputRefEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass outputReferenceEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass localOutputEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass analysisEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum outputTypeEEnum = null;

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
   * @see de.cau.cs.kieler.grana.text.grana.GranaPackage#eNS_URI
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
    ElkGraphPackage.eINSTANCE.eClass();

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
  public EReference getGrana_GloobalOutputs()
  {
    return (EReference)granaEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getGrana_Parallel()
  {
    return (EAttribute)granaEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getGrana_ExecuteAll()
  {
    return (EAttribute)granaEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getGrana_Execute()
  {
    return (EReference)granaEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getGrana_Jobs()
  {
    return (EReference)granaEClass.getEStructuralFeatures().get(5);
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
  public EReference getJob_Resources()
  {
    return (EReference)jobEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getJob_LayoutOptions()
  {
    return (EReference)jobEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getJob_Analyses()
  {
    return (EReference)jobEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getJob_OutputType()
  {
    return (EAttribute)jobEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getJob_Output()
  {
    return (EReference)jobEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getRegularJob()
  {
    return regularJobEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getRegularJob_LayoutBeforeAnalysis()
  {
    return (EAttribute)regularJobEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getRegularJob_MeasureExecutionTime()
  {
    return (EAttribute)regularJobEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getCompareJob()
  {
    return compareJobEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getRangeJob()
  {
    return rangeJobEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getRangeJob_MeasureExecutionTime()
  {
    return (EAttribute)rangeJobEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getRangeJob_RangeOption()
  {
    return (EAttribute)rangeJobEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getRangeJob_RangeValues()
  {
    return (EReference)rangeJobEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getRangeJob_RangeAnalysis()
  {
    return (EReference)rangeJobEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getRangeJob_RangeAnalysisComponent()
  {
    return (EAttribute)rangeJobEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getRangeJob_RangeAnalyses()
  {
    return (EReference)rangeJobEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getRange()
  {
    return rangeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getFloatRange()
  {
    return floatRangeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getFloatRange_Values()
  {
    return (EAttribute)floatRangeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getIntRange()
  {
    return intRangeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getIntRangeValues()
  {
    return intRangeValuesEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getIntRangeValues_Values()
  {
    return (EAttribute)intRangeValuesEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getIntRangeRange()
  {
    return intRangeRangeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getIntRangeRange_Start()
  {
    return (EAttribute)intRangeRangeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getIntRangeRange_End()
  {
    return (EAttribute)intRangeRangeEClass.getEStructuralFeatures().get(1);
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
  public EAttribute getLocalResource_Recurse()
  {
    return (EAttribute)localResourceEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getOutput()
  {
    return outputEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getGlobalOutputRef()
  {
    return globalOutputRefEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getGlobalOutputRef_Name()
  {
    return (EAttribute)globalOutputRefEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getGlobalOutputRef_Output()
  {
    return (EReference)globalOutputRefEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getOutputReference()
  {
    return outputReferenceEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getOutputReference_OutputRef()
  {
    return (EReference)outputReferenceEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getLocalOutput()
  {
    return localOutputEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getLocalOutput_Path()
  {
    return (EAttribute)localOutputEClass.getEStructuralFeatures().get(0);
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
  public EEnum getOutputType()
  {
    return outputTypeEEnum;
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
    createEReference(granaEClass, GRANA__GLOOBAL_OUTPUTS);
    createEAttribute(granaEClass, GRANA__PARALLEL);
    createEAttribute(granaEClass, GRANA__EXECUTE_ALL);
    createEReference(granaEClass, GRANA__EXECUTE);
    createEReference(granaEClass, GRANA__JOBS);

    jobEClass = createEClass(JOB);
    createEAttribute(jobEClass, JOB__NAME);
    createEReference(jobEClass, JOB__RESOURCES);
    createEReference(jobEClass, JOB__LAYOUT_OPTIONS);
    createEReference(jobEClass, JOB__ANALYSES);
    createEAttribute(jobEClass, JOB__OUTPUT_TYPE);
    createEReference(jobEClass, JOB__OUTPUT);

    regularJobEClass = createEClass(REGULAR_JOB);
    createEAttribute(regularJobEClass, REGULAR_JOB__LAYOUT_BEFORE_ANALYSIS);
    createEAttribute(regularJobEClass, REGULAR_JOB__MEASURE_EXECUTION_TIME);

    compareJobEClass = createEClass(COMPARE_JOB);

    rangeJobEClass = createEClass(RANGE_JOB);
    createEAttribute(rangeJobEClass, RANGE_JOB__MEASURE_EXECUTION_TIME);
    createEAttribute(rangeJobEClass, RANGE_JOB__RANGE_OPTION);
    createEReference(rangeJobEClass, RANGE_JOB__RANGE_VALUES);
    createEReference(rangeJobEClass, RANGE_JOB__RANGE_ANALYSIS);
    createEAttribute(rangeJobEClass, RANGE_JOB__RANGE_ANALYSIS_COMPONENT);
    createEReference(rangeJobEClass, RANGE_JOB__RANGE_ANALYSES);

    rangeEClass = createEClass(RANGE);

    floatRangeEClass = createEClass(FLOAT_RANGE);
    createEAttribute(floatRangeEClass, FLOAT_RANGE__VALUES);

    intRangeEClass = createEClass(INT_RANGE);

    intRangeValuesEClass = createEClass(INT_RANGE_VALUES);
    createEAttribute(intRangeValuesEClass, INT_RANGE_VALUES__VALUES);

    intRangeRangeEClass = createEClass(INT_RANGE_RANGE);
    createEAttribute(intRangeRangeEClass, INT_RANGE_RANGE__START);
    createEAttribute(intRangeRangeEClass, INT_RANGE_RANGE__END);

    resourceEClass = createEClass(RESOURCE);

    resourceReferenceEClass = createEClass(RESOURCE_REFERENCE);
    createEReference(resourceReferenceEClass, RESOURCE_REFERENCE__RESOURCE_REFS);

    globalResourceRefEClass = createEClass(GLOBAL_RESOURCE_REF);
    createEAttribute(globalResourceRefEClass, GLOBAL_RESOURCE_REF__NAME);
    createEReference(globalResourceRefEClass, GLOBAL_RESOURCE_REF__RESOURCES);

    localResourceEClass = createEClass(LOCAL_RESOURCE);
    createEAttribute(localResourceEClass, LOCAL_RESOURCE__PATH);
    createEAttribute(localResourceEClass, LOCAL_RESOURCE__FILTER);
    createEAttribute(localResourceEClass, LOCAL_RESOURCE__RECURSE);

    outputEClass = createEClass(OUTPUT);

    globalOutputRefEClass = createEClass(GLOBAL_OUTPUT_REF);
    createEAttribute(globalOutputRefEClass, GLOBAL_OUTPUT_REF__NAME);
    createEReference(globalOutputRefEClass, GLOBAL_OUTPUT_REF__OUTPUT);

    outputReferenceEClass = createEClass(OUTPUT_REFERENCE);
    createEReference(outputReferenceEClass, OUTPUT_REFERENCE__OUTPUT_REF);

    localOutputEClass = createEClass(LOCAL_OUTPUT);
    createEAttribute(localOutputEClass, LOCAL_OUTPUT__PATH);

    analysisEClass = createEClass(ANALYSIS);
    createEAttribute(analysisEClass, ANALYSIS__NAME);

    // Create enums
    outputTypeEEnum = createEEnum(OUTPUT_TYPE);
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
    ElkGraphPackage theElkGraphPackage = (ElkGraphPackage)EPackage.Registry.INSTANCE.getEPackage(ElkGraphPackage.eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes
    regularJobEClass.getESuperTypes().add(this.getJob());
    compareJobEClass.getESuperTypes().add(this.getJob());
    rangeJobEClass.getESuperTypes().add(this.getJob());
    floatRangeEClass.getESuperTypes().add(this.getRange());
    intRangeEClass.getESuperTypes().add(this.getRange());
    intRangeValuesEClass.getESuperTypes().add(this.getIntRange());
    intRangeRangeEClass.getESuperTypes().add(this.getIntRange());
    resourceReferenceEClass.getESuperTypes().add(this.getResource());
    localResourceEClass.getESuperTypes().add(this.getResource());
    outputReferenceEClass.getESuperTypes().add(this.getOutput());
    localOutputEClass.getESuperTypes().add(this.getOutput());

    // Initialize classes and features; add operations and parameters
    initEClass(granaEClass, Grana.class, "Grana", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getGrana_GlobalResources(), this.getGlobalResourceRef(), null, "globalResources", null, 0, -1, Grana.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getGrana_GloobalOutputs(), this.getGlobalOutputRef(), null, "gloobalOutputs", null, 0, -1, Grana.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGrana_Parallel(), ecorePackage.getEBoolean(), "parallel", null, 0, 1, Grana.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGrana_ExecuteAll(), ecorePackage.getEBoolean(), "executeAll", null, 0, 1, Grana.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getGrana_Execute(), this.getJob(), null, "execute", null, 0, -1, Grana.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getGrana_Jobs(), this.getJob(), null, "jobs", null, 0, -1, Grana.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(jobEClass, Job.class, "Job", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getJob_Name(), ecorePackage.getEString(), "name", null, 0, 1, Job.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getJob_Resources(), this.getResource(), null, "resources", null, 0, -1, Job.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getJob_LayoutOptions(), theElkGraphPackage.getElkNode(), null, "layoutOptions", null, 0, -1, Job.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getJob_Analyses(), this.getAnalysis(), null, "analyses", null, 0, -1, Job.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getJob_OutputType(), this.getOutputType(), "outputType", null, 0, 1, Job.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getJob_Output(), this.getOutput(), null, "output", null, 0, 1, Job.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(regularJobEClass, RegularJob.class, "RegularJob", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getRegularJob_LayoutBeforeAnalysis(), ecorePackage.getEBoolean(), "layoutBeforeAnalysis", null, 0, 1, RegularJob.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getRegularJob_MeasureExecutionTime(), ecorePackage.getEBoolean(), "measureExecutionTime", null, 0, 1, RegularJob.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(compareJobEClass, CompareJob.class, "CompareJob", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(rangeJobEClass, RangeJob.class, "RangeJob", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getRangeJob_MeasureExecutionTime(), ecorePackage.getEBoolean(), "measureExecutionTime", null, 0, 1, RangeJob.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getRangeJob_RangeOption(), ecorePackage.getEString(), "rangeOption", null, 0, 1, RangeJob.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getRangeJob_RangeValues(), this.getRange(), null, "rangeValues", null, 0, 1, RangeJob.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getRangeJob_RangeAnalysis(), this.getAnalysis(), null, "rangeAnalysis", null, 0, 1, RangeJob.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getRangeJob_RangeAnalysisComponent(), ecorePackage.getEInt(), "rangeAnalysisComponent", null, 0, 1, RangeJob.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getRangeJob_RangeAnalyses(), this.getAnalysis(), null, "rangeAnalyses", null, 0, -1, RangeJob.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(rangeEClass, Range.class, "Range", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(floatRangeEClass, FloatRange.class, "FloatRange", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getFloatRange_Values(), ecorePackage.getEDouble(), "values", null, 0, -1, FloatRange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(intRangeEClass, IntRange.class, "IntRange", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(intRangeValuesEClass, IntRangeValues.class, "IntRangeValues", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getIntRangeValues_Values(), ecorePackage.getEInt(), "values", null, 0, -1, IntRangeValues.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(intRangeRangeEClass, IntRangeRange.class, "IntRangeRange", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getIntRangeRange_Start(), ecorePackage.getEInt(), "start", null, 0, 1, IntRangeRange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getIntRangeRange_End(), ecorePackage.getEInt(), "end", null, 0, 1, IntRangeRange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(resourceEClass, Resource.class, "Resource", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(resourceReferenceEClass, ResourceReference.class, "ResourceReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getResourceReference_ResourceRefs(), this.getGlobalResourceRef(), null, "resourceRefs", null, 0, -1, ResourceReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(globalResourceRefEClass, GlobalResourceRef.class, "GlobalResourceRef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getGlobalResourceRef_Name(), ecorePackage.getEString(), "name", null, 0, 1, GlobalResourceRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getGlobalResourceRef_Resources(), this.getLocalResource(), null, "resources", null, 0, -1, GlobalResourceRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(localResourceEClass, LocalResource.class, "LocalResource", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getLocalResource_Path(), ecorePackage.getEString(), "path", null, 0, 1, LocalResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getLocalResource_Filter(), ecorePackage.getEString(), "filter", null, 0, 1, LocalResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getLocalResource_Recurse(), ecorePackage.getEBoolean(), "recurse", null, 0, 1, LocalResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(outputEClass, Output.class, "Output", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(globalOutputRefEClass, GlobalOutputRef.class, "GlobalOutputRef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getGlobalOutputRef_Name(), ecorePackage.getEString(), "name", null, 0, 1, GlobalOutputRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getGlobalOutputRef_Output(), this.getLocalOutput(), null, "output", null, 0, 1, GlobalOutputRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(outputReferenceEClass, OutputReference.class, "OutputReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getOutputReference_OutputRef(), this.getGlobalOutputRef(), null, "outputRef", null, 0, 1, OutputReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(localOutputEClass, LocalOutput.class, "LocalOutput", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getLocalOutput_Path(), ecorePackage.getEString(), "path", null, 0, 1, LocalOutput.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(analysisEClass, Analysis.class, "Analysis", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getAnalysis_Name(), ecorePackage.getEString(), "name", null, 0, 1, Analysis.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    // Initialize enums and add enum literals
    initEEnum(outputTypeEEnum, OutputType.class, "OutputType");
    addEEnumLiteral(outputTypeEEnum, OutputType.CSV);
    addEEnumLiteral(outputTypeEEnum, OutputType.JSON);

    // Create resource
    createResource(eNS_URI);
  }

} //GranaPackageImpl
