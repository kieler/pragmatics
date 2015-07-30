/**
 */
package de.cau.cs.kieler.uml.sequence.text.sequence.impl;

import de.cau.cs.kieler.uml.sequence.text.sequence.Lifeline;
import de.cau.cs.kieler.uml.sequence.text.sequence.MessageType;
import de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage;
import de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>One Lifeline Message</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.OneLifelineMessageImpl#getLifeline <em>Lifeline</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.OneLifelineMessageImpl#getMessageType <em>Message Type</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.OneLifelineMessageImpl#getMessageTypeLostAndFound <em>Message Type Lost And Found</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.OneLifelineMessageImpl#getCaption <em>Caption</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.OneLifelineMessageImpl#isStartBlock <em>Start Block</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.OneLifelineMessageImpl#isEndBlock <em>End Block</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.OneLifelineMessageImpl#getEndBlockCount <em>End Block Count</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.OneLifelineMessageImpl#getNote <em>Note</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OneLifelineMessageImpl extends InteractionImpl implements OneLifelineMessage
{
  /**
   * The cached value of the '{@link #getLifeline() <em>Lifeline</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLifeline()
   * @generated
   * @ordered
   */
  protected Lifeline lifeline;

  /**
   * The default value of the '{@link #getMessageType() <em>Message Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMessageType()
   * @generated
   * @ordered
   */
  protected static final MessageType MESSAGE_TYPE_EDEFAULT = MessageType.ASYNC;

  /**
   * The cached value of the '{@link #getMessageType() <em>Message Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMessageType()
   * @generated
   * @ordered
   */
  protected MessageType messageType = MESSAGE_TYPE_EDEFAULT;

  /**
   * The default value of the '{@link #getMessageTypeLostAndFound() <em>Message Type Lost And Found</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMessageTypeLostAndFound()
   * @generated
   * @ordered
   */
  protected static final String MESSAGE_TYPE_LOST_AND_FOUND_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getMessageTypeLostAndFound() <em>Message Type Lost And Found</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMessageTypeLostAndFound()
   * @generated
   * @ordered
   */
  protected String messageTypeLostAndFound = MESSAGE_TYPE_LOST_AND_FOUND_EDEFAULT;

  /**
   * The default value of the '{@link #getCaption() <em>Caption</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCaption()
   * @generated
   * @ordered
   */
  protected static final String CAPTION_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getCaption() <em>Caption</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCaption()
   * @generated
   * @ordered
   */
  protected String caption = CAPTION_EDEFAULT;

  /**
   * The default value of the '{@link #isStartBlock() <em>Start Block</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isStartBlock()
   * @generated
   * @ordered
   */
  protected static final boolean START_BLOCK_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isStartBlock() <em>Start Block</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isStartBlock()
   * @generated
   * @ordered
   */
  protected boolean startBlock = START_BLOCK_EDEFAULT;

  /**
   * The default value of the '{@link #isEndBlock() <em>End Block</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isEndBlock()
   * @generated
   * @ordered
   */
  protected static final boolean END_BLOCK_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isEndBlock() <em>End Block</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isEndBlock()
   * @generated
   * @ordered
   */
  protected boolean endBlock = END_BLOCK_EDEFAULT;

  /**
   * The default value of the '{@link #getEndBlockCount() <em>End Block Count</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEndBlockCount()
   * @generated
   * @ordered
   */
  protected static final int END_BLOCK_COUNT_EDEFAULT = 0;

  /**
   * The cached value of the '{@link #getEndBlockCount() <em>End Block Count</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEndBlockCount()
   * @generated
   * @ordered
   */
  protected int endBlockCount = END_BLOCK_COUNT_EDEFAULT;

  /**
   * The default value of the '{@link #getNote() <em>Note</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNote()
   * @generated
   * @ordered
   */
  protected static final String NOTE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getNote() <em>Note</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNote()
   * @generated
   * @ordered
   */
  protected String note = NOTE_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected OneLifelineMessageImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return SequencePackage.Literals.ONE_LIFELINE_MESSAGE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Lifeline getLifeline()
  {
    if (lifeline != null && lifeline.eIsProxy())
    {
      InternalEObject oldLifeline = (InternalEObject)lifeline;
      lifeline = (Lifeline)eResolveProxy(oldLifeline);
      if (lifeline != oldLifeline)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, SequencePackage.ONE_LIFELINE_MESSAGE__LIFELINE, oldLifeline, lifeline));
      }
    }
    return lifeline;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Lifeline basicGetLifeline()
  {
    return lifeline;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLifeline(Lifeline newLifeline)
  {
    Lifeline oldLifeline = lifeline;
    lifeline = newLifeline;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SequencePackage.ONE_LIFELINE_MESSAGE__LIFELINE, oldLifeline, lifeline));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MessageType getMessageType()
  {
    return messageType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMessageType(MessageType newMessageType)
  {
    MessageType oldMessageType = messageType;
    messageType = newMessageType == null ? MESSAGE_TYPE_EDEFAULT : newMessageType;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SequencePackage.ONE_LIFELINE_MESSAGE__MESSAGE_TYPE, oldMessageType, messageType));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getMessageTypeLostAndFound()
  {
    return messageTypeLostAndFound;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMessageTypeLostAndFound(String newMessageTypeLostAndFound)
  {
    String oldMessageTypeLostAndFound = messageTypeLostAndFound;
    messageTypeLostAndFound = newMessageTypeLostAndFound;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SequencePackage.ONE_LIFELINE_MESSAGE__MESSAGE_TYPE_LOST_AND_FOUND, oldMessageTypeLostAndFound, messageTypeLostAndFound));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getCaption()
  {
    return caption;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCaption(String newCaption)
  {
    String oldCaption = caption;
    caption = newCaption;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SequencePackage.ONE_LIFELINE_MESSAGE__CAPTION, oldCaption, caption));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isStartBlock()
  {
    return startBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setStartBlock(boolean newStartBlock)
  {
    boolean oldStartBlock = startBlock;
    startBlock = newStartBlock;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SequencePackage.ONE_LIFELINE_MESSAGE__START_BLOCK, oldStartBlock, startBlock));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isEndBlock()
  {
    return endBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEndBlock(boolean newEndBlock)
  {
    boolean oldEndBlock = endBlock;
    endBlock = newEndBlock;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SequencePackage.ONE_LIFELINE_MESSAGE__END_BLOCK, oldEndBlock, endBlock));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getEndBlockCount()
  {
    return endBlockCount;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEndBlockCount(int newEndBlockCount)
  {
    int oldEndBlockCount = endBlockCount;
    endBlockCount = newEndBlockCount;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SequencePackage.ONE_LIFELINE_MESSAGE__END_BLOCK_COUNT, oldEndBlockCount, endBlockCount));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getNote()
  {
    return note;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setNote(String newNote)
  {
    String oldNote = note;
    note = newNote;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SequencePackage.ONE_LIFELINE_MESSAGE__NOTE, oldNote, note));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case SequencePackage.ONE_LIFELINE_MESSAGE__LIFELINE:
        if (resolve) return getLifeline();
        return basicGetLifeline();
      case SequencePackage.ONE_LIFELINE_MESSAGE__MESSAGE_TYPE:
        return getMessageType();
      case SequencePackage.ONE_LIFELINE_MESSAGE__MESSAGE_TYPE_LOST_AND_FOUND:
        return getMessageTypeLostAndFound();
      case SequencePackage.ONE_LIFELINE_MESSAGE__CAPTION:
        return getCaption();
      case SequencePackage.ONE_LIFELINE_MESSAGE__START_BLOCK:
        return isStartBlock();
      case SequencePackage.ONE_LIFELINE_MESSAGE__END_BLOCK:
        return isEndBlock();
      case SequencePackage.ONE_LIFELINE_MESSAGE__END_BLOCK_COUNT:
        return getEndBlockCount();
      case SequencePackage.ONE_LIFELINE_MESSAGE__NOTE:
        return getNote();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case SequencePackage.ONE_LIFELINE_MESSAGE__LIFELINE:
        setLifeline((Lifeline)newValue);
        return;
      case SequencePackage.ONE_LIFELINE_MESSAGE__MESSAGE_TYPE:
        setMessageType((MessageType)newValue);
        return;
      case SequencePackage.ONE_LIFELINE_MESSAGE__MESSAGE_TYPE_LOST_AND_FOUND:
        setMessageTypeLostAndFound((String)newValue);
        return;
      case SequencePackage.ONE_LIFELINE_MESSAGE__CAPTION:
        setCaption((String)newValue);
        return;
      case SequencePackage.ONE_LIFELINE_MESSAGE__START_BLOCK:
        setStartBlock((Boolean)newValue);
        return;
      case SequencePackage.ONE_LIFELINE_MESSAGE__END_BLOCK:
        setEndBlock((Boolean)newValue);
        return;
      case SequencePackage.ONE_LIFELINE_MESSAGE__END_BLOCK_COUNT:
        setEndBlockCount((Integer)newValue);
        return;
      case SequencePackage.ONE_LIFELINE_MESSAGE__NOTE:
        setNote((String)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case SequencePackage.ONE_LIFELINE_MESSAGE__LIFELINE:
        setLifeline((Lifeline)null);
        return;
      case SequencePackage.ONE_LIFELINE_MESSAGE__MESSAGE_TYPE:
        setMessageType(MESSAGE_TYPE_EDEFAULT);
        return;
      case SequencePackage.ONE_LIFELINE_MESSAGE__MESSAGE_TYPE_LOST_AND_FOUND:
        setMessageTypeLostAndFound(MESSAGE_TYPE_LOST_AND_FOUND_EDEFAULT);
        return;
      case SequencePackage.ONE_LIFELINE_MESSAGE__CAPTION:
        setCaption(CAPTION_EDEFAULT);
        return;
      case SequencePackage.ONE_LIFELINE_MESSAGE__START_BLOCK:
        setStartBlock(START_BLOCK_EDEFAULT);
        return;
      case SequencePackage.ONE_LIFELINE_MESSAGE__END_BLOCK:
        setEndBlock(END_BLOCK_EDEFAULT);
        return;
      case SequencePackage.ONE_LIFELINE_MESSAGE__END_BLOCK_COUNT:
        setEndBlockCount(END_BLOCK_COUNT_EDEFAULT);
        return;
      case SequencePackage.ONE_LIFELINE_MESSAGE__NOTE:
        setNote(NOTE_EDEFAULT);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case SequencePackage.ONE_LIFELINE_MESSAGE__LIFELINE:
        return lifeline != null;
      case SequencePackage.ONE_LIFELINE_MESSAGE__MESSAGE_TYPE:
        return messageType != MESSAGE_TYPE_EDEFAULT;
      case SequencePackage.ONE_LIFELINE_MESSAGE__MESSAGE_TYPE_LOST_AND_FOUND:
        return MESSAGE_TYPE_LOST_AND_FOUND_EDEFAULT == null ? messageTypeLostAndFound != null : !MESSAGE_TYPE_LOST_AND_FOUND_EDEFAULT.equals(messageTypeLostAndFound);
      case SequencePackage.ONE_LIFELINE_MESSAGE__CAPTION:
        return CAPTION_EDEFAULT == null ? caption != null : !CAPTION_EDEFAULT.equals(caption);
      case SequencePackage.ONE_LIFELINE_MESSAGE__START_BLOCK:
        return startBlock != START_BLOCK_EDEFAULT;
      case SequencePackage.ONE_LIFELINE_MESSAGE__END_BLOCK:
        return endBlock != END_BLOCK_EDEFAULT;
      case SequencePackage.ONE_LIFELINE_MESSAGE__END_BLOCK_COUNT:
        return endBlockCount != END_BLOCK_COUNT_EDEFAULT;
      case SequencePackage.ONE_LIFELINE_MESSAGE__NOTE:
        return NOTE_EDEFAULT == null ? note != null : !NOTE_EDEFAULT.equals(note);
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (messageType: ");
    result.append(messageType);
    result.append(", messageTypeLostAndFound: ");
    result.append(messageTypeLostAndFound);
    result.append(", caption: ");
    result.append(caption);
    result.append(", startBlock: ");
    result.append(startBlock);
    result.append(", endBlock: ");
    result.append(endBlock);
    result.append(", endBlockCount: ");
    result.append(endBlockCount);
    result.append(", note: ");
    result.append(note);
    result.append(')');
    return result.toString();
  }

} //OneLifelineMessageImpl
