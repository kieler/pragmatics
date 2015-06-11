/**
 */
package de.cau.cs.kieler.uml.sequence.text.sequence;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Two Lifeline Message</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#getSourceLifeline <em>Source Lifeline</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#getTransitionType <em>Transition Type</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#getCaption <em>Caption</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#getTargetLifeline <em>Target Lifeline</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#isStartBlockLeft <em>Start Block Left</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#isEndBlockLeft <em>End Block Left</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#getEndBlockLeftCount <em>End Block Left Count</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#isStartBlockRight <em>Start Block Right</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#isEndBlockRight <em>End Block Right</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#getEndBlockRightCount <em>End Block Right Count</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#getSourceNote <em>Source Note</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#getTargetNote <em>Target Note</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getTwoLifelineMessage()
 * @model
 * @generated
 */
public interface TwoLifelineMessage extends Interaction
{
  /**
   * Returns the value of the '<em><b>Source Lifeline</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Source Lifeline</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Source Lifeline</em>' reference.
   * @see #setSourceLifeline(Lifeline)
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getTwoLifelineMessage_SourceLifeline()
   * @model
   * @generated
   */
  Lifeline getSourceLifeline();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#getSourceLifeline <em>Source Lifeline</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Source Lifeline</em>' reference.
   * @see #getSourceLifeline()
   * @generated
   */
  void setSourceLifeline(Lifeline value);

  /**
   * Returns the value of the '<em><b>Transition Type</b></em>' attribute.
   * The literals are from the enumeration {@link de.cau.cs.kieler.uml.sequence.text.sequence.TransitionType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Transition Type</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Transition Type</em>' attribute.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.TransitionType
   * @see #setTransitionType(TransitionType)
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getTwoLifelineMessage_TransitionType()
   * @model
   * @generated
   */
  TransitionType getTransitionType();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#getTransitionType <em>Transition Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Transition Type</em>' attribute.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.TransitionType
   * @see #getTransitionType()
   * @generated
   */
  void setTransitionType(TransitionType value);

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
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getTwoLifelineMessage_Caption()
   * @model
   * @generated
   */
  String getCaption();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#getCaption <em>Caption</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Caption</em>' attribute.
   * @see #getCaption()
   * @generated
   */
  void setCaption(String value);

  /**
   * Returns the value of the '<em><b>Target Lifeline</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Target Lifeline</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Target Lifeline</em>' reference.
   * @see #setTargetLifeline(Lifeline)
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getTwoLifelineMessage_TargetLifeline()
   * @model
   * @generated
   */
  Lifeline getTargetLifeline();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#getTargetLifeline <em>Target Lifeline</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target Lifeline</em>' reference.
   * @see #getTargetLifeline()
   * @generated
   */
  void setTargetLifeline(Lifeline value);

  /**
   * Returns the value of the '<em><b>Start Block Left</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Start Block Left</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Start Block Left</em>' attribute.
   * @see #setStartBlockLeft(boolean)
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getTwoLifelineMessage_StartBlockLeft()
   * @model
   * @generated
   */
  boolean isStartBlockLeft();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#isStartBlockLeft <em>Start Block Left</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Start Block Left</em>' attribute.
   * @see #isStartBlockLeft()
   * @generated
   */
  void setStartBlockLeft(boolean value);

  /**
   * Returns the value of the '<em><b>End Block Left</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>End Block Left</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>End Block Left</em>' attribute.
   * @see #setEndBlockLeft(boolean)
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getTwoLifelineMessage_EndBlockLeft()
   * @model
   * @generated
   */
  boolean isEndBlockLeft();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#isEndBlockLeft <em>End Block Left</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>End Block Left</em>' attribute.
   * @see #isEndBlockLeft()
   * @generated
   */
  void setEndBlockLeft(boolean value);

  /**
   * Returns the value of the '<em><b>End Block Left Count</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>End Block Left Count</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>End Block Left Count</em>' attribute.
   * @see #setEndBlockLeftCount(int)
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getTwoLifelineMessage_EndBlockLeftCount()
   * @model
   * @generated
   */
  int getEndBlockLeftCount();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#getEndBlockLeftCount <em>End Block Left Count</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>End Block Left Count</em>' attribute.
   * @see #getEndBlockLeftCount()
   * @generated
   */
  void setEndBlockLeftCount(int value);

  /**
   * Returns the value of the '<em><b>Start Block Right</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Start Block Right</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Start Block Right</em>' attribute.
   * @see #setStartBlockRight(boolean)
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getTwoLifelineMessage_StartBlockRight()
   * @model
   * @generated
   */
  boolean isStartBlockRight();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#isStartBlockRight <em>Start Block Right</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Start Block Right</em>' attribute.
   * @see #isStartBlockRight()
   * @generated
   */
  void setStartBlockRight(boolean value);

  /**
   * Returns the value of the '<em><b>End Block Right</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>End Block Right</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>End Block Right</em>' attribute.
   * @see #setEndBlockRight(boolean)
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getTwoLifelineMessage_EndBlockRight()
   * @model
   * @generated
   */
  boolean isEndBlockRight();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#isEndBlockRight <em>End Block Right</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>End Block Right</em>' attribute.
   * @see #isEndBlockRight()
   * @generated
   */
  void setEndBlockRight(boolean value);

  /**
   * Returns the value of the '<em><b>End Block Right Count</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>End Block Right Count</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>End Block Right Count</em>' attribute.
   * @see #setEndBlockRightCount(int)
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getTwoLifelineMessage_EndBlockRightCount()
   * @model
   * @generated
   */
  int getEndBlockRightCount();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#getEndBlockRightCount <em>End Block Right Count</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>End Block Right Count</em>' attribute.
   * @see #getEndBlockRightCount()
   * @generated
   */
  void setEndBlockRightCount(int value);

  /**
   * Returns the value of the '<em><b>Source Note</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Source Note</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Source Note</em>' attribute.
   * @see #setSourceNote(String)
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getTwoLifelineMessage_SourceNote()
   * @model
   * @generated
   */
  String getSourceNote();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#getSourceNote <em>Source Note</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Source Note</em>' attribute.
   * @see #getSourceNote()
   * @generated
   */
  void setSourceNote(String value);

  /**
   * Returns the value of the '<em><b>Target Note</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Target Note</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Target Note</em>' attribute.
   * @see #setTargetNote(String)
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getTwoLifelineMessage_TargetNote()
   * @model
   * @generated
   */
  String getTargetNote();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#getTargetNote <em>Target Note</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target Note</em>' attribute.
   * @see #getTargetNote()
   * @generated
   */
  void setTargetNote(String value);

} // TwoLifelineMessage
