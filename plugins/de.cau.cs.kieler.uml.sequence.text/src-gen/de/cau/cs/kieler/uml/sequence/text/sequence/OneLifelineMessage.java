/**
 */
package de.cau.cs.kieler.uml.sequence.text.sequence;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>One Lifeline Message</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage#getLifeline <em>Lifeline</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage#getMessageType <em>Message Type</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage#getMessageTypeLostAndFound <em>Message Type Lost And Found</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage#getMessage <em>Message</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage#isStartEndExec <em>Start End Exec</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage#isStartExec <em>Start Exec</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage#isEndExec <em>End Exec</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage#getEndExecCount <em>End Exec Count</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage#getNote <em>Note</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getOneLifelineMessage()
 * @model
 * @generated
 */
public interface OneLifelineMessage extends Interaction
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
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getOneLifelineMessage_Lifeline()
   * @model
   * @generated
   */
  Lifeline getLifeline();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage#getLifeline <em>Lifeline</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Lifeline</em>' reference.
   * @see #getLifeline()
   * @generated
   */
  void setLifeline(Lifeline value);

  /**
   * Returns the value of the '<em><b>Message Type</b></em>' attribute.
   * The literals are from the enumeration {@link de.cau.cs.kieler.uml.sequence.text.sequence.MessageTypeOne}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Message Type</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Message Type</em>' attribute.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.MessageTypeOne
   * @see #setMessageType(MessageTypeOne)
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getOneLifelineMessage_MessageType()
   * @model
   * @generated
   */
  MessageTypeOne getMessageType();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage#getMessageType <em>Message Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Message Type</em>' attribute.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.MessageTypeOne
   * @see #getMessageType()
   * @generated
   */
  void setMessageType(MessageTypeOne value);

  /**
   * Returns the value of the '<em><b>Message Type Lost And Found</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Message Type Lost And Found</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Message Type Lost And Found</em>' attribute.
   * @see #setMessageTypeLostAndFound(String)
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getOneLifelineMessage_MessageTypeLostAndFound()
   * @model
   * @generated
   */
  String getMessageTypeLostAndFound();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage#getMessageTypeLostAndFound <em>Message Type Lost And Found</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Message Type Lost And Found</em>' attribute.
   * @see #getMessageTypeLostAndFound()
   * @generated
   */
  void setMessageTypeLostAndFound(String value);

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
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getOneLifelineMessage_Message()
   * @model
   * @generated
   */
  String getMessage();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage#getMessage <em>Message</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Message</em>' attribute.
   * @see #getMessage()
   * @generated
   */
  void setMessage(String value);

  /**
   * Returns the value of the '<em><b>Start End Exec</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Start End Exec</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Start End Exec</em>' attribute.
   * @see #setStartEndExec(boolean)
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getOneLifelineMessage_StartEndExec()
   * @model
   * @generated
   */
  boolean isStartEndExec();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage#isStartEndExec <em>Start End Exec</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Start End Exec</em>' attribute.
   * @see #isStartEndExec()
   * @generated
   */
  void setStartEndExec(boolean value);

  /**
   * Returns the value of the '<em><b>Start Exec</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Start Exec</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Start Exec</em>' attribute.
   * @see #setStartExec(boolean)
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getOneLifelineMessage_StartExec()
   * @model
   * @generated
   */
  boolean isStartExec();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage#isStartExec <em>Start Exec</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Start Exec</em>' attribute.
   * @see #isStartExec()
   * @generated
   */
  void setStartExec(boolean value);

  /**
   * Returns the value of the '<em><b>End Exec</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>End Exec</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>End Exec</em>' attribute.
   * @see #setEndExec(boolean)
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getOneLifelineMessage_EndExec()
   * @model
   * @generated
   */
  boolean isEndExec();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage#isEndExec <em>End Exec</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>End Exec</em>' attribute.
   * @see #isEndExec()
   * @generated
   */
  void setEndExec(boolean value);

  /**
   * Returns the value of the '<em><b>End Exec Count</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>End Exec Count</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>End Exec Count</em>' attribute.
   * @see #setEndExecCount(int)
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getOneLifelineMessage_EndExecCount()
   * @model
   * @generated
   */
  int getEndExecCount();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage#getEndExecCount <em>End Exec Count</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>End Exec Count</em>' attribute.
   * @see #getEndExecCount()
   * @generated
   */
  void setEndExecCount(int value);

  /**
   * Returns the value of the '<em><b>Note</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Note</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Note</em>' attribute.
   * @see #setNote(String)
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getOneLifelineMessage_Note()
   * @model
   * @generated
   */
  String getNote();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage#getNote <em>Note</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Note</em>' attribute.
   * @see #getNote()
   * @generated
   */
  void setNote(String value);

} // OneLifelineMessage
