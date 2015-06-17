/**
 */
package de.cau.cs.kieler.uml.sequence.text.sequence;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>One Lifeline End Block</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineEndBlock#getLifeline <em>Lifeline</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineEndBlock#getEndBlockCount <em>End Block Count</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getOneLifelineEndBlock()
 * @model
 * @generated
 */
public interface OneLifelineEndBlock extends Interaction
{
  /**
   * Returns the value of the '<em><b>Lifeline</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Lifeline</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Lifeline</em>' reference.
   * @see #setLifeline(Lifeline)
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getOneLifelineEndBlock_Lifeline()
   * @model
   * @generated
   */
  Lifeline getLifeline();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineEndBlock#getLifeline <em>Lifeline</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Lifeline</em>' reference.
   * @see #getLifeline()
   * @generated
   */
  void setLifeline(Lifeline value);

  /**
   * Returns the value of the '<em><b>End Block Count</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>End Block Count</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>End Block Count</em>' attribute.
   * @see #setEndBlockCount(int)
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getOneLifelineEndBlock_EndBlockCount()
   * @model
   * @generated
   */
  int getEndBlockCount();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineEndBlock#getEndBlockCount <em>End Block Count</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>End Block Count</em>' attribute.
   * @see #getEndBlockCount()
   * @generated
   */
  void setEndBlockCount(int value);

} // OneLifelineEndBlock
