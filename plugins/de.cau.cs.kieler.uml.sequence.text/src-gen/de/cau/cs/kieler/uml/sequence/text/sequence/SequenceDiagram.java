/**
 */
package de.cau.cs.kieler.uml.sequence.text.sequence;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Diagram</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.SequenceDiagram#getDiagramName <em>Diagram Name</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.SequenceDiagram#getLifelines <em>Lifelines</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.SequenceDiagram#getInteractions <em>Interactions</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getSequenceDiagram()
 * @model
 * @generated
 */
public interface SequenceDiagram extends EObject
{
  /**
   * Returns the value of the '<em><b>Diagram Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Diagram Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Diagram Name</em>' attribute.
   * @see #setDiagramName(String)
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getSequenceDiagram_DiagramName()
   * @model
   * @generated
   */
  String getDiagramName();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.SequenceDiagram#getDiagramName <em>Diagram Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Diagram Name</em>' attribute.
   * @see #getDiagramName()
   * @generated
   */
  void setDiagramName(String value);

  /**
   * Returns the value of the '<em><b>Lifelines</b></em>' containment reference list.
   * The list contents are of type {@link de.cau.cs.kieler.uml.sequence.text.sequence.Lifeline}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Lifelines</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Lifelines</em>' containment reference list.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getSequenceDiagram_Lifelines()
   * @model containment="true"
   * @generated
   */
  EList<Lifeline> getLifelines();

  /**
   * Returns the value of the '<em><b>Interactions</b></em>' containment reference list.
   * The list contents are of type {@link de.cau.cs.kieler.uml.sequence.text.sequence.Interaction}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Interactions</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Interactions</em>' containment reference list.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getSequenceDiagram_Interactions()
   * @model containment="true"
   * @generated
   */
  EList<Interaction> getInteractions();

} // SequenceDiagram
