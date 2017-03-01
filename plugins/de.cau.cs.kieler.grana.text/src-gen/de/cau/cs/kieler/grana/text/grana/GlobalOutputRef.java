/**
 */
package de.cau.cs.kieler.grana.text.grana;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Global Output Ref</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.grana.text.grana.GlobalOutputRef#getName <em>Name</em>}</li>
 *   <li>{@link de.cau.cs.kieler.grana.text.grana.GlobalOutputRef#getOutput <em>Output</em>}</li>
 * </ul>
 *
 * @see de.cau.cs.kieler.grana.text.grana.GranaPackage#getGlobalOutputRef()
 * @model
 * @generated
 */
public interface GlobalOutputRef extends EObject
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
   * @see de.cau.cs.kieler.grana.text.grana.GranaPackage#getGlobalOutputRef_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.grana.text.grana.GlobalOutputRef#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Output</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Output</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Output</em>' containment reference.
   * @see #setOutput(LocalOutput)
   * @see de.cau.cs.kieler.grana.text.grana.GranaPackage#getGlobalOutputRef_Output()
   * @model containment="true"
   * @generated
   */
  LocalOutput getOutput();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.grana.text.grana.GlobalOutputRef#getOutput <em>Output</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Output</em>' containment reference.
   * @see #getOutput()
   * @generated
   */
  void setOutput(LocalOutput value);

} // GlobalOutputRef
