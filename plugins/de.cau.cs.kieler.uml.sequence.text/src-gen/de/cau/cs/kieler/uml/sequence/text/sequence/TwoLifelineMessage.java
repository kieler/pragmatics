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
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#isSourceStartBlock <em>Source Start Block</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#isSourceEndBlock <em>Source End Block</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#getSourceEndBlockCount <em>Source End Block Count</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#isTargetStartBlock <em>Target Start Block</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#isTargetEndBlock <em>Target End Block</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#getTargetEndBlockCount <em>Target End Block Count</em>}</li>
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
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getTwoLifelineMessage_MessageType()
   * @model
   * @generated
   */
  MessageType getMessageType();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#getMessageType <em>Message Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Message Type</em>' attribute.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.MessageType
   * @see #getMessageType()
   * @generated
   */
  void setMessageType(MessageType value);

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
   * Returns the value of the '<em><b>Source Start Block</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Source Start Block</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Source Start Block</em>' attribute.
   * @see #setSourceStartBlock(boolean)
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getTwoLifelineMessage_SourceStartBlock()
   * @model
   * @generated
   */
  boolean isSourceStartBlock();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#isSourceStartBlock <em>Source Start Block</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Source Start Block</em>' attribute.
   * @see #isSourceStartBlock()
   * @generated
   */
  void setSourceStartBlock(boolean value);

  /**
   * Returns the value of the '<em><b>Source End Block</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Source End Block</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Source End Block</em>' attribute.
   * @see #setSourceEndBlock(boolean)
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getTwoLifelineMessage_SourceEndBlock()
   * @model
   * @generated
   */
  boolean isSourceEndBlock();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#isSourceEndBlock <em>Source End Block</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Source End Block</em>' attribute.
   * @see #isSourceEndBlock()
   * @generated
   */
  void setSourceEndBlock(boolean value);

  /**
   * Returns the value of the '<em><b>Source End Block Count</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Source End Block Count</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Source End Block Count</em>' attribute.
   * @see #setSourceEndBlockCount(int)
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getTwoLifelineMessage_SourceEndBlockCount()
   * @model
   * @generated
   */
  int getSourceEndBlockCount();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#getSourceEndBlockCount <em>Source End Block Count</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Source End Block Count</em>' attribute.
   * @see #getSourceEndBlockCount()
   * @generated
   */
  void setSourceEndBlockCount(int value);

  /**
   * Returns the value of the '<em><b>Target Start Block</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Target Start Block</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Target Start Block</em>' attribute.
   * @see #setTargetStartBlock(boolean)
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getTwoLifelineMessage_TargetStartBlock()
   * @model
   * @generated
   */
  boolean isTargetStartBlock();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#isTargetStartBlock <em>Target Start Block</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target Start Block</em>' attribute.
   * @see #isTargetStartBlock()
   * @generated
   */
  void setTargetStartBlock(boolean value);

  /**
   * Returns the value of the '<em><b>Target End Block</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Target End Block</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Target End Block</em>' attribute.
   * @see #setTargetEndBlock(boolean)
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getTwoLifelineMessage_TargetEndBlock()
   * @model
   * @generated
   */
  boolean isTargetEndBlock();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#isTargetEndBlock <em>Target End Block</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target End Block</em>' attribute.
   * @see #isTargetEndBlock()
   * @generated
   */
  void setTargetEndBlock(boolean value);

  /**
   * Returns the value of the '<em><b>Target End Block Count</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Target End Block Count</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Target End Block Count</em>' attribute.
   * @see #setTargetEndBlockCount(int)
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getTwoLifelineMessage_TargetEndBlockCount()
   * @model
   * @generated
   */
  int getTargetEndBlockCount();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#getTargetEndBlockCount <em>Target End Block Count</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target End Block Count</em>' attribute.
   * @see #getTargetEndBlockCount()
   * @generated
   */
  void setTargetEndBlockCount(int value);

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
