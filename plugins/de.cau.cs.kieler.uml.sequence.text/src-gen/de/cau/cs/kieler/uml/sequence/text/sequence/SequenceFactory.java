/**
 */
package de.cau.cs.kieler.uml.sequence.text.sequence;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage
 * @generated
 */
public interface SequenceFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  SequenceFactory eINSTANCE = de.cau.cs.kieler.uml.sequence.text.sequence.impl.SequenceFactoryImpl.init();

  /**
   * Returns a new object of class '<em>Diagram</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Diagram</em>'.
   * @generated
   */
  SequenceDiagram createSequenceDiagram();

  /**
   * Returns a new object of class '<em>Lifeline</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Lifeline</em>'.
   * @generated
   */
  Lifeline createLifeline();

  /**
   * Returns a new object of class '<em>Interaction</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Interaction</em>'.
   * @generated
   */
  Interaction createInteraction();

  /**
   * Returns a new object of class '<em>Two Lifeline Message</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Two Lifeline Message</em>'.
   * @generated
   */
  TwoLifelineMessage createTwoLifelineMessage();

  /**
   * Returns a new object of class '<em>One Lifeline Message</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>One Lifeline Message</em>'.
   * @generated
   */
  OneLifelineMessage createOneLifelineMessage();

  /**
   * Returns a new object of class '<em>Self Message</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Self Message</em>'.
   * @generated
   */
  SelfMessage createSelfMessage();

  /**
   * Returns a new object of class '<em>Destroy Lifeline Event</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Destroy Lifeline Event</em>'.
   * @generated
   */
  DestroyLifelineEvent createDestroyLifelineEvent();

  /**
   * Returns a new object of class '<em>Fragment</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Fragment</em>'.
   * @generated
   */
  Fragment createFragment();

  /**
   * Returns a new object of class '<em>Section</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Section</em>'.
   * @generated
   */
  Section createSection();

  /**
   * Returns a new object of class '<em>Refinement</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Refinement</em>'.
   * @generated
   */
  Refinement createRefinement();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  SequencePackage getSequencePackage();

} //SequenceFactory
