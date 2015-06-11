/**
 */
package de.cau.cs.kieler.uml.sequence.text.sequence;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Lifeline</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.Lifeline#getCaption <em>Caption</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.Lifeline#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getLifeline()
 * @model
 * @generated
 */
public interface Lifeline extends EObject
{
  /**
   * Returns the value of the '<em><b>Caption</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Caption</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Caption</em>' attribute.
   * @see #setCaption(String)
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getLifeline_Caption()
   * @model
   * @generated
   */
  String getCaption();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.Lifeline#getCaption <em>Caption</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Caption</em>' attribute.
   * @see #getCaption()
   * @generated
   */
  void setCaption(String value);

  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getLifeline_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.Lifeline#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

} // Lifeline
