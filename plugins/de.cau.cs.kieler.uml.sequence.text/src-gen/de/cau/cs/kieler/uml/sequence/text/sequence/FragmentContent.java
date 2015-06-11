/**
 */
package de.cau.cs.kieler.uml.sequence.text.sequence;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Fragment Content</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.FragmentContent#getLabel <em>Label</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.FragmentContent#getInteractions <em>Interactions</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getFragmentContent()
 * @model
 * @generated
 */
public interface FragmentContent extends EObject
{
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
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getFragmentContent_Label()
   * @model
   * @generated
   */
  String getLabel();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.FragmentContent#getLabel <em>Label</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Label</em>' attribute.
   * @see #getLabel()
   * @generated
   */
  void setLabel(String value);

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
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getFragmentContent_Interactions()
   * @model containment="true"
   * @generated
   */
  EList<Interaction> getInteractions();

} // FragmentContent
