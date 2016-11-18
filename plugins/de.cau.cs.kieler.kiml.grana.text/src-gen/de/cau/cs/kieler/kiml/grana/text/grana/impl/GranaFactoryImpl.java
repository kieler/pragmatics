/**
 */
package de.cau.cs.kieler.kiml.grana.text.grana.impl;

import de.cau.cs.kieler.kiml.grana.text.grana.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class GranaFactoryImpl extends EFactoryImpl implements GranaFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static GranaFactory init()
  {
    try
    {
      GranaFactory theGranaFactory = (GranaFactory)EPackage.Registry.INSTANCE.getEFactory(GranaPackage.eNS_URI);
      if (theGranaFactory != null)
      {
        return theGranaFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new GranaFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GranaFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case GranaPackage.GRANA: return createGrana();
      case GranaPackage.JOB: return createJob();
      case GranaPackage.REGULAR_JOB: return createRegularJob();
      case GranaPackage.COMPARE_JOB: return createCompareJob();
      case GranaPackage.RANGE_JOB: return createRangeJob();
      case GranaPackage.RANGE: return createRange();
      case GranaPackage.FLOAT_RANGE: return createFloatRange();
      case GranaPackage.INT_RANGE: return createIntRange();
      case GranaPackage.INT_RANGE_VALUES: return createIntRangeValues();
      case GranaPackage.INT_RANGE_RANGE: return createIntRangeRange();
      case GranaPackage.RESOURCE: return createResource();
      case GranaPackage.RESOURCE_REFERENCE: return createResourceReference();
      case GranaPackage.GLOBAL_RESOURCE_REF: return createGlobalResourceRef();
      case GranaPackage.LOCAL_RESOURCE: return createLocalResource();
      case GranaPackage.OUTPUT: return createOutput();
      case GranaPackage.GLOBAL_OUTPUT_REF: return createGlobalOutputRef();
      case GranaPackage.OUTPUT_REFERENCE: return createOutputReference();
      case GranaPackage.LOCAL_OUTPUT: return createLocalOutput();
      case GranaPackage.ANALYSIS: return createAnalysis();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object createFromString(EDataType eDataType, String initialValue)
  {
    switch (eDataType.getClassifierID())
    {
      case GranaPackage.OUTPUT_TYPE:
        return createOutputTypeFromString(eDataType, initialValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String convertToString(EDataType eDataType, Object instanceValue)
  {
    switch (eDataType.getClassifierID())
    {
      case GranaPackage.OUTPUT_TYPE:
        return convertOutputTypeToString(eDataType, instanceValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Grana createGrana()
  {
    GranaImpl grana = new GranaImpl();
    return grana;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Job createJob()
  {
    JobImpl job = new JobImpl();
    return job;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public RegularJob createRegularJob()
  {
    RegularJobImpl regularJob = new RegularJobImpl();
    return regularJob;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CompareJob createCompareJob()
  {
    CompareJobImpl compareJob = new CompareJobImpl();
    return compareJob;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public RangeJob createRangeJob()
  {
    RangeJobImpl rangeJob = new RangeJobImpl();
    return rangeJob;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Range createRange()
  {
    RangeImpl range = new RangeImpl();
    return range;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FloatRange createFloatRange()
  {
    FloatRangeImpl floatRange = new FloatRangeImpl();
    return floatRange;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IntRange createIntRange()
  {
    IntRangeImpl intRange = new IntRangeImpl();
    return intRange;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IntRangeValues createIntRangeValues()
  {
    IntRangeValuesImpl intRangeValues = new IntRangeValuesImpl();
    return intRangeValues;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IntRangeRange createIntRangeRange()
  {
    IntRangeRangeImpl intRangeRange = new IntRangeRangeImpl();
    return intRangeRange;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Resource createResource()
  {
    ResourceImpl resource = new ResourceImpl();
    return resource;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ResourceReference createResourceReference()
  {
    ResourceReferenceImpl resourceReference = new ResourceReferenceImpl();
    return resourceReference;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GlobalResourceRef createGlobalResourceRef()
  {
    GlobalResourceRefImpl globalResourceRef = new GlobalResourceRefImpl();
    return globalResourceRef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public LocalResource createLocalResource()
  {
    LocalResourceImpl localResource = new LocalResourceImpl();
    return localResource;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Output createOutput()
  {
    OutputImpl output = new OutputImpl();
    return output;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GlobalOutputRef createGlobalOutputRef()
  {
    GlobalOutputRefImpl globalOutputRef = new GlobalOutputRefImpl();
    return globalOutputRef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OutputReference createOutputReference()
  {
    OutputReferenceImpl outputReference = new OutputReferenceImpl();
    return outputReference;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public LocalOutput createLocalOutput()
  {
    LocalOutputImpl localOutput = new LocalOutputImpl();
    return localOutput;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Analysis createAnalysis()
  {
    AnalysisImpl analysis = new AnalysisImpl();
    return analysis;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OutputType createOutputTypeFromString(EDataType eDataType, String initialValue)
  {
    OutputType result = OutputType.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertOutputTypeToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GranaPackage getGranaPackage()
  {
    return (GranaPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static GranaPackage getPackage()
  {
    return GranaPackage.eINSTANCE;
  }

} //GranaFactoryImpl
