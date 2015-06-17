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
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage#getCaption <em>Caption</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage#isStartBlock <em>Start Block</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage#isEndBlock <em>End Block</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage#getEndBlockCount <em>End Block Count</em>}</li>
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
   * The literals are from the enumeration {@link de.cau.cs.kieler.uml.sequence.text.sequence.MessageType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Message Type</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Message Type</em>' attribute.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.MessageType
   * @see #setMessageType(MessageType)
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getOneLifelineMessage_MessageType()
   * @model
   * @generated
   */
  MessageType getMessageType();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage#getMessageType <em>Message Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Message Type</em>' attribute.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.MessageType
   * @see #getMessageType()
   * @generated
   */
  void setMessageType(MessageType value);

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
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getOneLifelineMessage_Caption()
   * @model
   * @generated
   */
  String getCaption();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage#getCaption <em>Caption</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Caption</em>' attribute.
   * @see #getCaption()
   * @generated
   */
  void setCaption(String value);

  /**
   * Returns the value of the '<em><b>Start Block</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Start Block</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Start Block</em>' attribute.
   * @see #setStartBlock(boolean)
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getOneLifelineMessage_StartBlock()
   * @model
   * @generated
   */
  boolean isStartBlock();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage#isStartBlock <em>Start Block</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Start Block</em>' attribute.
   * @see #isStartBlock()
   * @generated
   */
  void setStartBlock(boolean value);

  /**
   * Returns the value of the '<em><b>End Block</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>End Block</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>End Block</em>' attribute.
   * @see #setEndBlock(boolean)
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getOneLifelineMessage_EndBlock()
   * @model
   * @generated
   */
  boolean isEndBlock();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage#isEndBlock <em>End Block</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>End Block</em>' attribute.
   * @see #isEndBlock()
   * @generated
   */
  void setEndBlock(boolean value);

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
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getOneLifelineMessage_EndBlockCount()
   * @model
   * @generated
   */
  int getEndBlockCount();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage#getEndBlockCount <em>End Block Count</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>End Block Count</em>' attribute.
   * @see #getEndBlockCount()
   * @generated
   */
  void setEndBlockCount(int value);

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
