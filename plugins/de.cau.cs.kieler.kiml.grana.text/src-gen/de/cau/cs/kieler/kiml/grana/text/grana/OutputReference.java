/**
 */
package de.cau.cs.kieler.kiml.grana.text.grana;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Output Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.kiml.grana.text.grana.OutputReference#getOutputRef <em>Output Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.kiml.grana.text.grana.GranaPackage#getOutputReference()
 * @model
 * @generated
 */
public interface OutputReference extends Output
{
  /**
   * Returns the value of the '<em><b>Output Ref</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Output Ref</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Output Ref</em>' reference.
   * @see #setOutputRef(GlobalOutputRef)
   * @see de.cau.cs.kieler.kiml.grana.text.grana.GranaPackage#getOutputReference_OutputRef()
   * @model
   * @generated
   */
  GlobalOutputRef getOutputRef();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.kiml.grana.text.grana.OutputReference#getOutputRef <em>Output Ref</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Output Ref</em>' reference.
   * @see #getOutputRef()
   * @generated
   */
  void setOutputRef(GlobalOutputRef value);

} // OutputReference
