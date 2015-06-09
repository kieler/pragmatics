/**
 */
package de.cau.cs.kieler.kiml.grana.text.grana;

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
 * @see de.cau.cs.kieler.kiml.grana.text.grana.GranaFactory
 * @model kind="package"
 * @generated
 */
public interface GranaPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "grana";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.cau.de/cs/kieler/kiml/grana/text/Grana";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "grana";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  GranaPackage eINSTANCE = de.cau.cs.kieler.kiml.grana.text.grana.impl.GranaPackageImpl.init();

  /**
   * The meta object id for the '{@link de.cau.cs.kieler.kiml.grana.text.grana.impl.GranaImpl <em>Grana</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.cau.cs.kieler.kiml.grana.text.grana.impl.GranaImpl
   * @see de.cau.cs.kieler.kiml.grana.text.grana.impl.GranaPackageImpl#getGrana()
   * @generated
   */
  int GRANA = 0;

  /**
   * The feature id for the '<em><b>Global Resources</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GRANA__GLOBAL_RESOURCES = 0;

  /**
   * The feature id for the '<em><b>Gloobal Outputs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GRANA__GLOOBAL_OUTPUTS = 1;

  /**
   * The feature id for the '<em><b>Execute All</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GRANA__EXECUTE_ALL = 2;

  /**
   * The feature id for the '<em><b>Execute</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GRANA__EXECUTE = 3;

  /**
   * The feature id for the '<em><b>Jobs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GRANA__JOBS = 4;

  /**
   * The number of structural features of the '<em>Grana</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GRANA_FEATURE_COUNT = 5;

  /**
   * The meta object id for the '{@link de.cau.cs.kieler.kiml.grana.text.grana.impl.JobImpl <em>Job</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.cau.cs.kieler.kiml.grana.text.grana.impl.JobImpl
   * @see de.cau.cs.kieler.kiml.grana.text.grana.impl.GranaPackageImpl#getJob()
   * @generated
   */
  int JOB = 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JOB__NAME = 0;

  /**
   * The feature id for the '<em><b>Layout Before Analysis</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JOB__LAYOUT_BEFORE_ANALYSIS = 1;

  /**
   * The feature id for the '<em><b>Measure Execution Time</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JOB__MEASURE_EXECUTION_TIME = 2;

  /**
   * The feature id for the '<em><b>Resources</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JOB__RESOURCES = 3;

  /**
   * The feature id for the '<em><b>Layout Options</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JOB__LAYOUT_OPTIONS = 4;

  /**
   * The feature id for the '<em><b>Analyses</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JOB__ANALYSES = 5;

  /**
   * The feature id for the '<em><b>Output</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JOB__OUTPUT = 6;

  /**
   * The number of structural features of the '<em>Job</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JOB_FEATURE_COUNT = 7;

  /**
   * The meta object id for the '{@link de.cau.cs.kieler.kiml.grana.text.grana.impl.ResourceImpl <em>Resource</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.cau.cs.kieler.kiml.grana.text.grana.impl.ResourceImpl
   * @see de.cau.cs.kieler.kiml.grana.text.grana.impl.GranaPackageImpl#getResource()
   * @generated
   */
  int RESOURCE = 2;

  /**
   * The number of structural features of the '<em>Resource</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RESOURCE_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link de.cau.cs.kieler.kiml.grana.text.grana.impl.ResourceReferenceImpl <em>Resource Reference</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.cau.cs.kieler.kiml.grana.text.grana.impl.ResourceReferenceImpl
   * @see de.cau.cs.kieler.kiml.grana.text.grana.impl.GranaPackageImpl#getResourceReference()
   * @generated
   */
  int RESOURCE_REFERENCE = 3;

  /**
   * The feature id for the '<em><b>Resource Refs</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RESOURCE_REFERENCE__RESOURCE_REFS = RESOURCE_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Resource Reference</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RESOURCE_REFERENCE_FEATURE_COUNT = RESOURCE_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link de.cau.cs.kieler.kiml.grana.text.grana.impl.GlobalResourceRefImpl <em>Global Resource Ref</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.cau.cs.kieler.kiml.grana.text.grana.impl.GlobalResourceRefImpl
   * @see de.cau.cs.kieler.kiml.grana.text.grana.impl.GranaPackageImpl#getGlobalResourceRef()
   * @generated
   */
  int GLOBAL_RESOURCE_REF = 4;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GLOBAL_RESOURCE_REF__NAME = 0;

  /**
   * The feature id for the '<em><b>Resources</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GLOBAL_RESOURCE_REF__RESOURCES = 1;

  /**
   * The number of structural features of the '<em>Global Resource Ref</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GLOBAL_RESOURCE_REF_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link de.cau.cs.kieler.kiml.grana.text.grana.impl.LocalResourceImpl <em>Local Resource</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.cau.cs.kieler.kiml.grana.text.grana.impl.LocalResourceImpl
   * @see de.cau.cs.kieler.kiml.grana.text.grana.impl.GranaPackageImpl#getLocalResource()
   * @generated
   */
  int LOCAL_RESOURCE = 5;

  /**
   * The feature id for the '<em><b>Path</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LOCAL_RESOURCE__PATH = RESOURCE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Filter</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LOCAL_RESOURCE__FILTER = RESOURCE_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Local Resource</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LOCAL_RESOURCE_FEATURE_COUNT = RESOURCE_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link de.cau.cs.kieler.kiml.grana.text.grana.impl.OutputImpl <em>Output</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.cau.cs.kieler.kiml.grana.text.grana.impl.OutputImpl
   * @see de.cau.cs.kieler.kiml.grana.text.grana.impl.GranaPackageImpl#getOutput()
   * @generated
   */
  int OUTPUT = 6;

  /**
   * The number of structural features of the '<em>Output</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OUTPUT_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link de.cau.cs.kieler.kiml.grana.text.grana.impl.GlobalOutputRefImpl <em>Global Output Ref</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.cau.cs.kieler.kiml.grana.text.grana.impl.GlobalOutputRefImpl
   * @see de.cau.cs.kieler.kiml.grana.text.grana.impl.GranaPackageImpl#getGlobalOutputRef()
   * @generated
   */
  int GLOBAL_OUTPUT_REF = 7;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GLOBAL_OUTPUT_REF__NAME = 0;

  /**
   * The feature id for the '<em><b>Output</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GLOBAL_OUTPUT_REF__OUTPUT = 1;

  /**
   * The number of structural features of the '<em>Global Output Ref</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GLOBAL_OUTPUT_REF_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link de.cau.cs.kieler.kiml.grana.text.grana.impl.OutputReferenceImpl <em>Output Reference</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.cau.cs.kieler.kiml.grana.text.grana.impl.OutputReferenceImpl
   * @see de.cau.cs.kieler.kiml.grana.text.grana.impl.GranaPackageImpl#getOutputReference()
   * @generated
   */
  int OUTPUT_REFERENCE = 8;

  /**
   * The feature id for the '<em><b>Output Ref</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OUTPUT_REFERENCE__OUTPUT_REF = OUTPUT_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Output Reference</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OUTPUT_REFERENCE_FEATURE_COUNT = OUTPUT_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link de.cau.cs.kieler.kiml.grana.text.grana.impl.LocalOutputImpl <em>Local Output</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.cau.cs.kieler.kiml.grana.text.grana.impl.LocalOutputImpl
   * @see de.cau.cs.kieler.kiml.grana.text.grana.impl.GranaPackageImpl#getLocalOutput()
   * @generated
   */
  int LOCAL_OUTPUT = 9;

  /**
   * The feature id for the '<em><b>Path</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LOCAL_OUTPUT__PATH = OUTPUT_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Local Output</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LOCAL_OUTPUT_FEATURE_COUNT = OUTPUT_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link de.cau.cs.kieler.kiml.grana.text.grana.impl.AnalysisImpl <em>Analysis</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.cau.cs.kieler.kiml.grana.text.grana.impl.AnalysisImpl
   * @see de.cau.cs.kieler.kiml.grana.text.grana.impl.GranaPackageImpl#getAnalysis()
   * @generated
   */
  int ANALYSIS = 10;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANALYSIS__NAME = 0;

  /**
   * The number of structural features of the '<em>Analysis</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANALYSIS_FEATURE_COUNT = 1;


  /**
   * Returns the meta object for class '{@link de.cau.cs.kieler.kiml.grana.text.grana.Grana <em>Grana</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Grana</em>'.
   * @see de.cau.cs.kieler.kiml.grana.text.grana.Grana
   * @generated
   */
  EClass getGrana();

  /**
   * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.kiml.grana.text.grana.Grana#getGlobalResources <em>Global Resources</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Global Resources</em>'.
   * @see de.cau.cs.kieler.kiml.grana.text.grana.Grana#getGlobalResources()
   * @see #getGrana()
   * @generated
   */
  EReference getGrana_GlobalResources();

  /**
   * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.kiml.grana.text.grana.Grana#getGloobalOutputs <em>Gloobal Outputs</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Gloobal Outputs</em>'.
   * @see de.cau.cs.kieler.kiml.grana.text.grana.Grana#getGloobalOutputs()
   * @see #getGrana()
   * @generated
   */
  EReference getGrana_GloobalOutputs();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kiml.grana.text.grana.Grana#isExecuteAll <em>Execute All</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Execute All</em>'.
   * @see de.cau.cs.kieler.kiml.grana.text.grana.Grana#isExecuteAll()
   * @see #getGrana()
   * @generated
   */
  EAttribute getGrana_ExecuteAll();

  /**
   * Returns the meta object for the reference list '{@link de.cau.cs.kieler.kiml.grana.text.grana.Grana#getExecute <em>Execute</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Execute</em>'.
   * @see de.cau.cs.kieler.kiml.grana.text.grana.Grana#getExecute()
   * @see #getGrana()
   * @generated
   */
  EReference getGrana_Execute();

  /**
   * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.kiml.grana.text.grana.Grana#getJobs <em>Jobs</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Jobs</em>'.
   * @see de.cau.cs.kieler.kiml.grana.text.grana.Grana#getJobs()
   * @see #getGrana()
   * @generated
   */
  EReference getGrana_Jobs();

  /**
   * Returns the meta object for class '{@link de.cau.cs.kieler.kiml.grana.text.grana.Job <em>Job</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Job</em>'.
   * @see de.cau.cs.kieler.kiml.grana.text.grana.Job
   * @generated
   */
  EClass getJob();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kiml.grana.text.grana.Job#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see de.cau.cs.kieler.kiml.grana.text.grana.Job#getName()
   * @see #getJob()
   * @generated
   */
  EAttribute getJob_Name();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kiml.grana.text.grana.Job#isLayoutBeforeAnalysis <em>Layout Before Analysis</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Layout Before Analysis</em>'.
   * @see de.cau.cs.kieler.kiml.grana.text.grana.Job#isLayoutBeforeAnalysis()
   * @see #getJob()
   * @generated
   */
  EAttribute getJob_LayoutBeforeAnalysis();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kiml.grana.text.grana.Job#isMeasureExecutionTime <em>Measure Execution Time</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Measure Execution Time</em>'.
   * @see de.cau.cs.kieler.kiml.grana.text.grana.Job#isMeasureExecutionTime()
   * @see #getJob()
   * @generated
   */
  EAttribute getJob_MeasureExecutionTime();

  /**
   * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.kiml.grana.text.grana.Job#getResources <em>Resources</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Resources</em>'.
   * @see de.cau.cs.kieler.kiml.grana.text.grana.Job#getResources()
   * @see #getJob()
   * @generated
   */
  EReference getJob_Resources();

  /**
   * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.kiml.grana.text.grana.Job#getLayoutOptions <em>Layout Options</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Layout Options</em>'.
   * @see de.cau.cs.kieler.kiml.grana.text.grana.Job#getLayoutOptions()
   * @see #getJob()
   * @generated
   */
  EReference getJob_LayoutOptions();

  /**
   * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.kiml.grana.text.grana.Job#getAnalyses <em>Analyses</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Analyses</em>'.
   * @see de.cau.cs.kieler.kiml.grana.text.grana.Job#getAnalyses()
   * @see #getJob()
   * @generated
   */
  EReference getJob_Analyses();

  /**
   * Returns the meta object for the containment reference '{@link de.cau.cs.kieler.kiml.grana.text.grana.Job#getOutput <em>Output</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Output</em>'.
   * @see de.cau.cs.kieler.kiml.grana.text.grana.Job#getOutput()
   * @see #getJob()
   * @generated
   */
  EReference getJob_Output();

  /**
   * Returns the meta object for class '{@link de.cau.cs.kieler.kiml.grana.text.grana.Resource <em>Resource</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Resource</em>'.
   * @see de.cau.cs.kieler.kiml.grana.text.grana.Resource
   * @generated
   */
  EClass getResource();

  /**
   * Returns the meta object for class '{@link de.cau.cs.kieler.kiml.grana.text.grana.ResourceReference <em>Resource Reference</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Resource Reference</em>'.
   * @see de.cau.cs.kieler.kiml.grana.text.grana.ResourceReference
   * @generated
   */
  EClass getResourceReference();

  /**
   * Returns the meta object for the reference list '{@link de.cau.cs.kieler.kiml.grana.text.grana.ResourceReference#getResourceRefs <em>Resource Refs</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Resource Refs</em>'.
   * @see de.cau.cs.kieler.kiml.grana.text.grana.ResourceReference#getResourceRefs()
   * @see #getResourceReference()
   * @generated
   */
  EReference getResourceReference_ResourceRefs();

  /**
   * Returns the meta object for class '{@link de.cau.cs.kieler.kiml.grana.text.grana.GlobalResourceRef <em>Global Resource Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Global Resource Ref</em>'.
   * @see de.cau.cs.kieler.kiml.grana.text.grana.GlobalResourceRef
   * @generated
   */
  EClass getGlobalResourceRef();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kiml.grana.text.grana.GlobalResourceRef#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see de.cau.cs.kieler.kiml.grana.text.grana.GlobalResourceRef#getName()
   * @see #getGlobalResourceRef()
   * @generated
   */
  EAttribute getGlobalResourceRef_Name();

  /**
   * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.kiml.grana.text.grana.GlobalResourceRef#getResources <em>Resources</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Resources</em>'.
   * @see de.cau.cs.kieler.kiml.grana.text.grana.GlobalResourceRef#getResources()
   * @see #getGlobalResourceRef()
   * @generated
   */
  EReference getGlobalResourceRef_Resources();

  /**
   * Returns the meta object for class '{@link de.cau.cs.kieler.kiml.grana.text.grana.LocalResource <em>Local Resource</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Local Resource</em>'.
   * @see de.cau.cs.kieler.kiml.grana.text.grana.LocalResource
   * @generated
   */
  EClass getLocalResource();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kiml.grana.text.grana.LocalResource#getPath <em>Path</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Path</em>'.
   * @see de.cau.cs.kieler.kiml.grana.text.grana.LocalResource#getPath()
   * @see #getLocalResource()
   * @generated
   */
  EAttribute getLocalResource_Path();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kiml.grana.text.grana.LocalResource#getFilter <em>Filter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Filter</em>'.
   * @see de.cau.cs.kieler.kiml.grana.text.grana.LocalResource#getFilter()
   * @see #getLocalResource()
   * @generated
   */
  EAttribute getLocalResource_Filter();

  /**
   * Returns the meta object for class '{@link de.cau.cs.kieler.kiml.grana.text.grana.Output <em>Output</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Output</em>'.
   * @see de.cau.cs.kieler.kiml.grana.text.grana.Output
   * @generated
   */
  EClass getOutput();

  /**
   * Returns the meta object for class '{@link de.cau.cs.kieler.kiml.grana.text.grana.GlobalOutputRef <em>Global Output Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Global Output Ref</em>'.
   * @see de.cau.cs.kieler.kiml.grana.text.grana.GlobalOutputRef
   * @generated
   */
  EClass getGlobalOutputRef();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kiml.grana.text.grana.GlobalOutputRef#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see de.cau.cs.kieler.kiml.grana.text.grana.GlobalOutputRef#getName()
   * @see #getGlobalOutputRef()
   * @generated
   */
  EAttribute getGlobalOutputRef_Name();

  /**
   * Returns the meta object for the containment reference '{@link de.cau.cs.kieler.kiml.grana.text.grana.GlobalOutputRef#getOutput <em>Output</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Output</em>'.
   * @see de.cau.cs.kieler.kiml.grana.text.grana.GlobalOutputRef#getOutput()
   * @see #getGlobalOutputRef()
   * @generated
   */
  EReference getGlobalOutputRef_Output();

  /**
   * Returns the meta object for class '{@link de.cau.cs.kieler.kiml.grana.text.grana.OutputReference <em>Output Reference</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Output Reference</em>'.
   * @see de.cau.cs.kieler.kiml.grana.text.grana.OutputReference
   * @generated
   */
  EClass getOutputReference();

  /**
   * Returns the meta object for the reference '{@link de.cau.cs.kieler.kiml.grana.text.grana.OutputReference#getOutputRef <em>Output Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Output Ref</em>'.
   * @see de.cau.cs.kieler.kiml.grana.text.grana.OutputReference#getOutputRef()
   * @see #getOutputReference()
   * @generated
   */
  EReference getOutputReference_OutputRef();

  /**
   * Returns the meta object for class '{@link de.cau.cs.kieler.kiml.grana.text.grana.LocalOutput <em>Local Output</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Local Output</em>'.
   * @see de.cau.cs.kieler.kiml.grana.text.grana.LocalOutput
   * @generated
   */
  EClass getLocalOutput();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kiml.grana.text.grana.LocalOutput#getPath <em>Path</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Path</em>'.
   * @see de.cau.cs.kieler.kiml.grana.text.grana.LocalOutput#getPath()
   * @see #getLocalOutput()
   * @generated
   */
  EAttribute getLocalOutput_Path();

  /**
   * Returns the meta object for class '{@link de.cau.cs.kieler.kiml.grana.text.grana.Analysis <em>Analysis</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Analysis</em>'.
   * @see de.cau.cs.kieler.kiml.grana.text.grana.Analysis
   * @generated
   */
  EClass getAnalysis();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kiml.grana.text.grana.Analysis#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see de.cau.cs.kieler.kiml.grana.text.grana.Analysis#getName()
   * @see #getAnalysis()
   * @generated
   */
  EAttribute getAnalysis_Name();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  GranaFactory getGranaFactory();

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
     * The meta object literal for the '{@link de.cau.cs.kieler.kiml.grana.text.grana.impl.GranaImpl <em>Grana</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.kiml.grana.text.grana.impl.GranaImpl
     * @see de.cau.cs.kieler.kiml.grana.text.grana.impl.GranaPackageImpl#getGrana()
     * @generated
     */
    EClass GRANA = eINSTANCE.getGrana();

    /**
     * The meta object literal for the '<em><b>Global Resources</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GRANA__GLOBAL_RESOURCES = eINSTANCE.getGrana_GlobalResources();

    /**
     * The meta object literal for the '<em><b>Gloobal Outputs</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GRANA__GLOOBAL_OUTPUTS = eINSTANCE.getGrana_GloobalOutputs();

    /**
     * The meta object literal for the '<em><b>Execute All</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GRANA__EXECUTE_ALL = eINSTANCE.getGrana_ExecuteAll();

    /**
     * The meta object literal for the '<em><b>Execute</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GRANA__EXECUTE = eINSTANCE.getGrana_Execute();

    /**
     * The meta object literal for the '<em><b>Jobs</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GRANA__JOBS = eINSTANCE.getGrana_Jobs();

    /**
     * The meta object literal for the '{@link de.cau.cs.kieler.kiml.grana.text.grana.impl.JobImpl <em>Job</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.kiml.grana.text.grana.impl.JobImpl
     * @see de.cau.cs.kieler.kiml.grana.text.grana.impl.GranaPackageImpl#getJob()
     * @generated
     */
    EClass JOB = eINSTANCE.getJob();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute JOB__NAME = eINSTANCE.getJob_Name();

    /**
     * The meta object literal for the '<em><b>Layout Before Analysis</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute JOB__LAYOUT_BEFORE_ANALYSIS = eINSTANCE.getJob_LayoutBeforeAnalysis();

    /**
     * The meta object literal for the '<em><b>Measure Execution Time</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute JOB__MEASURE_EXECUTION_TIME = eINSTANCE.getJob_MeasureExecutionTime();

    /**
     * The meta object literal for the '<em><b>Resources</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference JOB__RESOURCES = eINSTANCE.getJob_Resources();

    /**
     * The meta object literal for the '<em><b>Layout Options</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference JOB__LAYOUT_OPTIONS = eINSTANCE.getJob_LayoutOptions();

    /**
     * The meta object literal for the '<em><b>Analyses</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference JOB__ANALYSES = eINSTANCE.getJob_Analyses();

    /**
     * The meta object literal for the '<em><b>Output</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference JOB__OUTPUT = eINSTANCE.getJob_Output();

    /**
     * The meta object literal for the '{@link de.cau.cs.kieler.kiml.grana.text.grana.impl.ResourceImpl <em>Resource</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.kiml.grana.text.grana.impl.ResourceImpl
     * @see de.cau.cs.kieler.kiml.grana.text.grana.impl.GranaPackageImpl#getResource()
     * @generated
     */
    EClass RESOURCE = eINSTANCE.getResource();

    /**
     * The meta object literal for the '{@link de.cau.cs.kieler.kiml.grana.text.grana.impl.ResourceReferenceImpl <em>Resource Reference</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.kiml.grana.text.grana.impl.ResourceReferenceImpl
     * @see de.cau.cs.kieler.kiml.grana.text.grana.impl.GranaPackageImpl#getResourceReference()
     * @generated
     */
    EClass RESOURCE_REFERENCE = eINSTANCE.getResourceReference();

    /**
     * The meta object literal for the '<em><b>Resource Refs</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference RESOURCE_REFERENCE__RESOURCE_REFS = eINSTANCE.getResourceReference_ResourceRefs();

    /**
     * The meta object literal for the '{@link de.cau.cs.kieler.kiml.grana.text.grana.impl.GlobalResourceRefImpl <em>Global Resource Ref</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.kiml.grana.text.grana.impl.GlobalResourceRefImpl
     * @see de.cau.cs.kieler.kiml.grana.text.grana.impl.GranaPackageImpl#getGlobalResourceRef()
     * @generated
     */
    EClass GLOBAL_RESOURCE_REF = eINSTANCE.getGlobalResourceRef();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GLOBAL_RESOURCE_REF__NAME = eINSTANCE.getGlobalResourceRef_Name();

    /**
     * The meta object literal for the '<em><b>Resources</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GLOBAL_RESOURCE_REF__RESOURCES = eINSTANCE.getGlobalResourceRef_Resources();

    /**
     * The meta object literal for the '{@link de.cau.cs.kieler.kiml.grana.text.grana.impl.LocalResourceImpl <em>Local Resource</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.kiml.grana.text.grana.impl.LocalResourceImpl
     * @see de.cau.cs.kieler.kiml.grana.text.grana.impl.GranaPackageImpl#getLocalResource()
     * @generated
     */
    EClass LOCAL_RESOURCE = eINSTANCE.getLocalResource();

    /**
     * The meta object literal for the '<em><b>Path</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute LOCAL_RESOURCE__PATH = eINSTANCE.getLocalResource_Path();

    /**
     * The meta object literal for the '<em><b>Filter</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute LOCAL_RESOURCE__FILTER = eINSTANCE.getLocalResource_Filter();

    /**
     * The meta object literal for the '{@link de.cau.cs.kieler.kiml.grana.text.grana.impl.OutputImpl <em>Output</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.kiml.grana.text.grana.impl.OutputImpl
     * @see de.cau.cs.kieler.kiml.grana.text.grana.impl.GranaPackageImpl#getOutput()
     * @generated
     */
    EClass OUTPUT = eINSTANCE.getOutput();

    /**
     * The meta object literal for the '{@link de.cau.cs.kieler.kiml.grana.text.grana.impl.GlobalOutputRefImpl <em>Global Output Ref</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.kiml.grana.text.grana.impl.GlobalOutputRefImpl
     * @see de.cau.cs.kieler.kiml.grana.text.grana.impl.GranaPackageImpl#getGlobalOutputRef()
     * @generated
     */
    EClass GLOBAL_OUTPUT_REF = eINSTANCE.getGlobalOutputRef();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GLOBAL_OUTPUT_REF__NAME = eINSTANCE.getGlobalOutputRef_Name();

    /**
     * The meta object literal for the '<em><b>Output</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GLOBAL_OUTPUT_REF__OUTPUT = eINSTANCE.getGlobalOutputRef_Output();

    /**
     * The meta object literal for the '{@link de.cau.cs.kieler.kiml.grana.text.grana.impl.OutputReferenceImpl <em>Output Reference</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.kiml.grana.text.grana.impl.OutputReferenceImpl
     * @see de.cau.cs.kieler.kiml.grana.text.grana.impl.GranaPackageImpl#getOutputReference()
     * @generated
     */
    EClass OUTPUT_REFERENCE = eINSTANCE.getOutputReference();

    /**
     * The meta object literal for the '<em><b>Output Ref</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference OUTPUT_REFERENCE__OUTPUT_REF = eINSTANCE.getOutputReference_OutputRef();

    /**
     * The meta object literal for the '{@link de.cau.cs.kieler.kiml.grana.text.grana.impl.LocalOutputImpl <em>Local Output</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.kiml.grana.text.grana.impl.LocalOutputImpl
     * @see de.cau.cs.kieler.kiml.grana.text.grana.impl.GranaPackageImpl#getLocalOutput()
     * @generated
     */
    EClass LOCAL_OUTPUT = eINSTANCE.getLocalOutput();

    /**
     * The meta object literal for the '<em><b>Path</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute LOCAL_OUTPUT__PATH = eINSTANCE.getLocalOutput_Path();

    /**
     * The meta object literal for the '{@link de.cau.cs.kieler.kiml.grana.text.grana.impl.AnalysisImpl <em>Analysis</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.kiml.grana.text.grana.impl.AnalysisImpl
     * @see de.cau.cs.kieler.kiml.grana.text.grana.impl.GranaPackageImpl#getAnalysis()
     * @generated
     */
    EClass ANALYSIS = eINSTANCE.getAnalysis();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ANALYSIS__NAME = eINSTANCE.getAnalysis_Name();

  }

} //GranaPackage
