/**
 */
package de.cau.cs.kieler.uml.sequence.text.sequence;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Refinement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.Refinement#getLifelines <em>Lifelines</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.Refinement#getLabel <em>Label</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getRefinement()
 * @model
 * @generated
 */
public interface Refinement extends Interaction
{
  /**
   * Returns the value of the '<em><b>Lifelines</b></em>' reference list.
   * The list contents are of type {@link de.cau.cs.kieler.uml.sequence.text.sequence.Lifeline}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Lifelines</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Lifelines</em>' reference list.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getRefinement_Lifelines()
   * @model
   * @generated
   */
  EList<Lifeline> getLifelines();

  /**
   * Returns the value of the '<em><b>Label</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Label</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Label</em>' attribute.
   * @see #setLabel(String)
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getRefinement_Label()
   * @model
   * @generated
   */
  String getLabel();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.Refinement#getLabel <em>Label</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Label</em>' attribute.
   * @see #getLabel()
   * @generated
   */
  void setLabel(String value);

} // Refinement
