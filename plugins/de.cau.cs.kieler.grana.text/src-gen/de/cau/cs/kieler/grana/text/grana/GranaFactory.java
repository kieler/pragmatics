/**
 */
package de.cau.cs.kieler.grana.text.grana;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see de.cau.cs.kieler.grana.text.grana.GranaPackage
 * @generated
 */
public interface GranaFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  GranaFactory eINSTANCE = de.cau.cs.kieler.grana.text.grana.impl.GranaFactoryImpl.init();

  /**
   * Returns a new object of class '<em>Grana</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Grana</em>'.
   * @generated
   */
  Grana createGrana();

  /**
   * Returns a new object of class '<em>Job</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Job</em>'.
   * @generated
   */
  Job createJob();

  /**
   * Returns a new object of class '<em>Regular Job</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Regular Job</em>'.
   * @generated
   */
  RegularJob createRegularJob();

  /**
   * Returns a new object of class '<em>Compare Job</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Compare Job</em>'.
   * @generated
   */
  CompareJob createCompareJob();

  /**
   * Returns a new object of class '<em>Range Job</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Range Job</em>'.
   * @generated
   */
  RangeJob createRangeJob();

  /**
   * Returns a new object of class '<em>Range</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Range</em>'.
   * @generated
   */
  Range createRange();

  /**
   * Returns a new object of class '<em>Float Range</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Float Range</em>'.
   * @generated
   */
  FloatRange createFloatRange();

  /**
   * Returns a new object of class '<em>Int Range</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Int Range</em>'.
   * @generated
   */
  IntRange createIntRange();

  /**
   * Returns a new object of class '<em>Int Range Values</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Int Range Values</em>'.
   * @generated
   */
  IntRangeValues createIntRangeValues();

  /**
   * Returns a new object of class '<em>Int Range Range</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Int Range Range</em>'.
   * @generated
   */
  IntRangeRange createIntRangeRange();

  /**
   * Returns a new object of class '<em>Resource</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Resource</em>'.
   * @generated
   */
  Resource createResource();

  /**
   * Returns a new object of class '<em>Resource Reference</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Resource Reference</em>'.
   * @generated
   */
  ResourceReference createResourceReference();

  /**
   * Returns a new object of class '<em>Global Resource Ref</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Global Resource Ref</em>'.
   * @generated
   */
  GlobalResourceRef createGlobalResourceRef();

  /**
   * Returns a new object of class '<em>Local Resource</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Local Resource</em>'.
   * @generated
   */
  LocalResource createLocalResource();

  /**
   * Returns a new object of class '<em>Output</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Output</em>'.
   * @generated
   */
  Output createOutput();

  /**
   * Returns a new object of class '<em>Global Output Ref</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Global Output Ref</em>'.
   * @generated
   */
  GlobalOutputRef createGlobalOutputRef();

  /**
   * Returns a new object of class '<em>Output Reference</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Output Reference</em>'.
   * @generated
   */
  OutputReference createOutputReference();

  /**
   * Returns a new object of class '<em>Local Output</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Local Output</em>'.
   * @generated
   */
  LocalOutput createLocalOutput();

  /**
   * Returns a new object of class '<em>Analysis</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Analysis</em>'.
   * @generated
   */
  Analysis createAnalysis();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  GranaPackage getGranaPackage();

} //GranaFactory
