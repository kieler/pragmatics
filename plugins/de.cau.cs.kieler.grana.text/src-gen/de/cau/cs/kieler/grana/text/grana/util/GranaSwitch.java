/**
 */
package de.cau.cs.kieler.grana.text.grana.util;

import de.cau.cs.kieler.grana.text.grana.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see de.cau.cs.kieler.grana.text.grana.GranaPackage
 * @generated
 */
public class GranaSwitch<T> extends Switch<T>
{
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static GranaPackage modelPackage;

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GranaSwitch()
  {
    if (modelPackage == null)
    {
      modelPackage = GranaPackage.eINSTANCE;
    }
  }

  /**
   * Checks whether this is a switch for the given package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param ePackage the package in question.
   * @return whether this is a switch for the given package.
   * @generated
   */
  @Override
  protected boolean isSwitchFor(EPackage ePackage)
  {
    return ePackage == modelPackage;
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  @Override
  protected T doSwitch(int classifierID, EObject theEObject)
  {
    switch (classifierID)
    {
      case GranaPackage.GRANA:
      {
        Grana grana = (Grana)theEObject;
        T result = caseGrana(grana);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case GranaPackage.JOB:
      {
        Job job = (Job)theEObject;
        T result = caseJob(job);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case GranaPackage.REGULAR_JOB:
      {
        RegularJob regularJob = (RegularJob)theEObject;
        T result = caseRegularJob(regularJob);
        if (result == null) result = caseJob(regularJob);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case GranaPackage.COMPARE_JOB:
      {
        CompareJob compareJob = (CompareJob)theEObject;
        T result = caseCompareJob(compareJob);
        if (result == null) result = caseJob(compareJob);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case GranaPackage.RANGE_JOB:
      {
        RangeJob rangeJob = (RangeJob)theEObject;
        T result = caseRangeJob(rangeJob);
        if (result == null) result = caseJob(rangeJob);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case GranaPackage.RANGE:
      {
        Range range = (Range)theEObject;
        T result = caseRange(range);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case GranaPackage.FLOAT_RANGE:
      {
        FloatRange floatRange = (FloatRange)theEObject;
        T result = caseFloatRange(floatRange);
        if (result == null) result = caseRange(floatRange);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case GranaPackage.INT_RANGE:
      {
        IntRange intRange = (IntRange)theEObject;
        T result = caseIntRange(intRange);
        if (result == null) result = caseRange(intRange);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case GranaPackage.INT_RANGE_VALUES:
      {
        IntRangeValues intRangeValues = (IntRangeValues)theEObject;
        T result = caseIntRangeValues(intRangeValues);
        if (result == null) result = caseIntRange(intRangeValues);
        if (result == null) result = caseRange(intRangeValues);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case GranaPackage.INT_RANGE_RANGE:
      {
        IntRangeRange intRangeRange = (IntRangeRange)theEObject;
        T result = caseIntRangeRange(intRangeRange);
        if (result == null) result = caseIntRange(intRangeRange);
        if (result == null) result = caseRange(intRangeRange);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case GranaPackage.RESOURCE:
      {
        Resource resource = (Resource)theEObject;
        T result = caseResource(resource);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case GranaPackage.RESOURCE_REFERENCE:
      {
        ResourceReference resourceReference = (ResourceReference)theEObject;
        T result = caseResourceReference(resourceReference);
        if (result == null) result = caseResource(resourceReference);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case GranaPackage.GLOBAL_RESOURCE_REF:
      {
        GlobalResourceRef globalResourceRef = (GlobalResourceRef)theEObject;
        T result = caseGlobalResourceRef(globalResourceRef);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case GranaPackage.LOCAL_RESOURCE:
      {
        LocalResource localResource = (LocalResource)theEObject;
        T result = caseLocalResource(localResource);
        if (result == null) result = caseResource(localResource);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case GranaPackage.OUTPUT:
      {
        Output output = (Output)theEObject;
        T result = caseOutput(output);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case GranaPackage.GLOBAL_OUTPUT_REF:
      {
        GlobalOutputRef globalOutputRef = (GlobalOutputRef)theEObject;
        T result = caseGlobalOutputRef(globalOutputRef);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case GranaPackage.OUTPUT_REFERENCE:
      {
        OutputReference outputReference = (OutputReference)theEObject;
        T result = caseOutputReference(outputReference);
        if (result == null) result = caseOutput(outputReference);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case GranaPackage.LOCAL_OUTPUT:
      {
        LocalOutput localOutput = (LocalOutput)theEObject;
        T result = caseLocalOutput(localOutput);
        if (result == null) result = caseOutput(localOutput);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case GranaPackage.ANALYSIS:
      {
        Analysis analysis = (Analysis)theEObject;
        T result = caseAnalysis(analysis);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Grana</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Grana</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseGrana(Grana object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Job</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Job</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseJob(Job object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Regular Job</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Regular Job</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseRegularJob(RegularJob object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Compare Job</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Compare Job</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseCompareJob(CompareJob object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Range Job</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Range Job</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseRangeJob(RangeJob object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Range</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Range</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseRange(Range object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Float Range</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Float Range</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFloatRange(FloatRange object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Int Range</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Int Range</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseIntRange(IntRange object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Int Range Values</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Int Range Values</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseIntRangeValues(IntRangeValues object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Int Range Range</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Int Range Range</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseIntRangeRange(IntRangeRange object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Resource</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Resource</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseResource(Resource object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Resource Reference</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Resource Reference</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseResourceReference(ResourceReference object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Global Resource Ref</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Global Resource Ref</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseGlobalResourceRef(GlobalResourceRef object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Local Resource</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Local Resource</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseLocalResource(LocalResource object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Output</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Output</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOutput(Output object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Global Output Ref</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Global Output Ref</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseGlobalOutputRef(GlobalOutputRef object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Output Reference</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Output Reference</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOutputReference(OutputReference object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Local Output</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Local Output</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseLocalOutput(LocalOutput object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Analysis</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Analysis</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAnalysis(Analysis object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch, but this is the last case anyway.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject)
   * @generated
   */
  @Override
  public T defaultCase(EObject object)
  {
    return null;
  }

} //GranaSwitch
