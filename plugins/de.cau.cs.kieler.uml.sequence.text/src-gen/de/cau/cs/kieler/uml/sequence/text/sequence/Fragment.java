/**
 */
package de.cau.cs.kieler.uml.sequence.text.sequence;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Fragment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.Fragment#getName <em>Name</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.Fragment#getFragmentContents <em>Fragment Contents</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getFragment()
 * @model
 * @generated
 */
public interface Fragment extends Interaction
{
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
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getFragment_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.Fragment#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Fragment Contents</b></em>' containment reference list.
   * The list contents are of type {@link de.cau.cs.kieler.uml.sequence.text.sequence.FragmentContent}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Fragment Contents</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Fragment Contents</em>' containment reference list.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getFragment_FragmentContents()
   * @model containment="true"
   * @generated
   */
  EList<FragmentContent> getFragmentContents();

} // Fragment
