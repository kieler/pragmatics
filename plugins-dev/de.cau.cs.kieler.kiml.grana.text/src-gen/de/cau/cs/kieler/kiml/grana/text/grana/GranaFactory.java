/**
 */
package de.cau.cs.kieler.kiml.grana.text.grana;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see de.cau.cs.kieler.kiml.grana.text.grana.GranaPackage
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
  GranaFactory eINSTANCE = de.cau.cs.kieler.kiml.grana.text.grana.impl.GranaFactoryImpl.init();

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
