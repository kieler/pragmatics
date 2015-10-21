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
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#getMessageType <em>Message Type</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#getMessage <em>Message</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#getTargetLifeline <em>Target Lifeline</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#isSourceStartEndExec <em>Source Start End Exec</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#isSourceStartExec <em>Source Start Exec</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#isSourceEndExec <em>Source End Exec</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#getSourceEndExecCount <em>Source End Exec Count</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#isTargetStartEndExec <em>Target Start End Exec</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#isTargetStartExec <em>Target Start Exec</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#isTargetEndExec <em>Target End Exec</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#getTargetEndExecCount <em>Target End Exec Count</em>}</li>
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
   * Returns the value of the '<em><b>Message Type</b></em>' attribute.
   * The literals are from the enumeration {@link de.cau.cs.kieler.uml.sequence.text.sequence.MessageTypeTwo}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Message Type</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Message Type</em>' attribute.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.MessageTypeTwo
   * @see #setMessageType(MessageTypeTwo)
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getTwoLifelineMessage_MessageType()
   * @model
   * @generated
   */
  MessageTypeTwo getMessageType();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#getMessageType <em>Message Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Message Type</em>' attribute.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.MessageTypeTwo
   * @see #getMessageType()
   * @generated
   */
  void setMessageType(MessageTypeTwo value);

  /**
   * Returns the value of the '<em><b>Message</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Message</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Message</em>' attribute.
   * @see #setMessage(String)
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getTwoLifelineMessage_Message()
   * @model
   * @generated
   */
  String getMessage();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#getMessage <em>Message</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Message</em>' attribute.
   * @see #getMessage()
   * @generated
   */
  void setMessage(String value);

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
   * Returns the value of the '<em><b>Source Start End Exec</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Source Start End Exec</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Source Start End Exec</em>' attribute.
   * @see #setSourceStartEndExec(boolean)
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getTwoLifelineMessage_SourceStartEndExec()
   * @model
   * @generated
   */
  boolean isSourceStartEndExec();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#isSourceStartEndExec <em>Source Start End Exec</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Source Start End Exec</em>' attribute.
   * @see #isSourceStartEndExec()
   * @generated
   */
  void setSourceStartEndExec(boolean value);

  /**
   * Returns the value of the '<em><b>Source Start Exec</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Source Start Exec</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Source Start Exec</em>' attribute.
   * @see #setSourceStartExec(boolean)
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getTwoLifelineMessage_SourceStartExec()
   * @model
   * @generated
   */
  boolean isSourceStartExec();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#isSourceStartExec <em>Source Start Exec</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Source Start Exec</em>' attribute.
   * @see #isSourceStartExec()
   * @generated
   */
  void setSourceStartExec(boolean value);

  /**
   * Returns the value of the '<em><b>Source End Exec</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Source End Exec</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Source End Exec</em>' attribute.
   * @see #setSourceEndExec(boolean)
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getTwoLifelineMessage_SourceEndExec()
   * @model
   * @generated
   */
  boolean isSourceEndExec();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#isSourceEndExec <em>Source End Exec</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Source End Exec</em>' attribute.
   * @see #isSourceEndExec()
   * @generated
   */
  void setSourceEndExec(boolean value);

  /**
   * Returns the value of the '<em><b>Source End Exec Count</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Source End Exec Count</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Source End Exec Count</em>' attribute.
   * @see #setSourceEndExecCount(int)
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getTwoLifelineMessage_SourceEndExecCount()
   * @model
   * @generated
   */
  int getSourceEndExecCount();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#getSourceEndExecCount <em>Source End Exec Count</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Source End Exec Count</em>' attribute.
   * @see #getSourceEndExecCount()
   * @generated
   */
  void setSourceEndExecCount(int value);

  /**
   * Returns the value of the '<em><b>Target Start End Exec</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Target Start End Exec</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Target Start End Exec</em>' attribute.
   * @see #setTargetStartEndExec(boolean)
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getTwoLifelineMessage_TargetStartEndExec()
   * @model
   * @generated
   */
  boolean isTargetStartEndExec();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#isTargetStartEndExec <em>Target Start End Exec</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target Start End Exec</em>' attribute.
   * @see #isTargetStartEndExec()
   * @generated
   */
  void setTargetStartEndExec(boolean value);

  /**
   * Returns the value of the '<em><b>Target Start Exec</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Target Start Exec</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Target Start Exec</em>' attribute.
   * @see #setTargetStartExec(boolean)
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getTwoLifelineMessage_TargetStartExec()
   * @model
   * @generated
   */
  boolean isTargetStartExec();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#isTargetStartExec <em>Target Start Exec</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target Start Exec</em>' attribute.
   * @see #isTargetStartExec()
   * @generated
   */
  void setTargetStartExec(boolean value);

  /**
   * Returns the value of the '<em><b>Target End Exec</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Target End Exec</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Target End Exec</em>' attribute.
   * @see #setTargetEndExec(boolean)
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getTwoLifelineMessage_TargetEndExec()
   * @model
   * @generated
   */
  boolean isTargetEndExec();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#isTargetEndExec <em>Target End Exec</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target End Exec</em>' attribute.
   * @see #isTargetEndExec()
   * @generated
   */
  void setTargetEndExec(boolean value);

  /**
   * Returns the value of the '<em><b>Target End Exec Count</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Target End Exec Count</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Target End Exec Count</em>' attribute.
   * @see #setTargetEndExecCount(int)
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getTwoLifelineMessage_TargetEndExecCount()
   * @model
   * @generated
   */
  int getTargetEndExecCount();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#getTargetEndExecCount <em>Target End Exec Count</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target End Exec Count</em>' attribute.
   * @see #getTargetEndExecCount()
   * @generated
   */
  void setTargetEndExecCount(int value);

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
