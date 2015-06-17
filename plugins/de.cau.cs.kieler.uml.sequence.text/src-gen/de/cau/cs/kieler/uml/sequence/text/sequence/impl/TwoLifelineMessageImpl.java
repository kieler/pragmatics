/**
 */
package de.cau.cs.kieler.uml.sequence.text.sequence.impl;

import de.cau.cs.kieler.uml.sequence.text.sequence.Lifeline;
import de.cau.cs.kieler.uml.sequence.text.sequence.MessageType;
import de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage;
import de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Two Lifeline Message</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.TwoLifelineMessageImpl#getSourceLifeline <em>Source Lifeline</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.TwoLifelineMessageImpl#getMessageType <em>Message Type</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.TwoLifelineMessageImpl#getMessage <em>Message</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.TwoLifelineMessageImpl#getTargetLifeline <em>Target Lifeline</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.TwoLifelineMessageImpl#isSourceStartBlock <em>Source Start Block</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.TwoLifelineMessageImpl#isSourceEndBlock <em>Source End Block</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.TwoLifelineMessageImpl#getSourceEndBlockCount <em>Source End Block Count</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.TwoLifelineMessageImpl#isTargetStartBlock <em>Target Start Block</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.TwoLifelineMessageImpl#isTargetEndBlock <em>Target End Block</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.TwoLifelineMessageImpl#getTargetEndBlockCount <em>Target End Block Count</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.TwoLifelineMessageImpl#getSourceNote <em>Source Note</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.TwoLifelineMessageImpl#getTargetNote <em>Target Note</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TwoLifelineMessageImpl extends InteractionImpl implements TwoLifelineMessage
{
  /**
   * The cached value of the '{@link #getSourceLifeline() <em>Source Lifeline</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSourceLifeline()
   * @generated
   * @ordered
   */
  protected Lifeline sourceLifeline;

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
   * The default value of the '{@link #getMessage() <em>Message</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMessage()
   * @generated
   * @ordered
   */
  protected static final String MESSAGE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getMessage() <em>Message</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMessage()
   * @generated
   * @ordered
   */
  protected String message = MESSAGE_EDEFAULT;

  /**
   * The cached value of the '{@link #getTargetLifeline() <em>Target Lifeline</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTargetLifeline()
   * @generated
   * @ordered
   */
  protected Lifeline targetLifeline;

  /**
   * The default value of the '{@link #isSourceStartBlock() <em>Source Start Block</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSourceStartBlock()
   * @generated
   * @ordered
   */
  protected static final boolean SOURCE_START_BLOCK_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isSourceStartBlock() <em>Source Start Block</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSourceStartBlock()
   * @generated
   * @ordered
   */
  protected boolean sourceStartBlock = SOURCE_START_BLOCK_EDEFAULT;

  /**
   * The default value of the '{@link #isSourceEndBlock() <em>Source End Block</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSourceEndBlock()
   * @generated
   * @ordered
   */
  protected static final boolean SOURCE_END_BLOCK_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isSourceEndBlock() <em>Source End Block</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSourceEndBlock()
   * @generated
   * @ordered
   */
  protected boolean sourceEndBlock = SOURCE_END_BLOCK_EDEFAULT;

  /**
   * The default value of the '{@link #getSourceEndBlockCount() <em>Source End Block Count</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSourceEndBlockCount()
   * @generated
   * @ordered
   */
  protected static final int SOURCE_END_BLOCK_COUNT_EDEFAULT = 0;

  /**
   * The cached value of the '{@link #getSourceEndBlockCount() <em>Source End Block Count</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSourceEndBlockCount()
   * @generated
   * @ordered
   */
  protected int sourceEndBlockCount = SOURCE_END_BLOCK_COUNT_EDEFAULT;

  /**
   * The default value of the '{@link #isTargetStartBlock() <em>Target Start Block</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isTargetStartBlock()
   * @generated
   * @ordered
   */
  protected static final boolean TARGET_START_BLOCK_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isTargetStartBlock() <em>Target Start Block</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isTargetStartBlock()
   * @generated
   * @ordered
   */
  protected boolean targetStartBlock = TARGET_START_BLOCK_EDEFAULT;

  /**
   * The default value of the '{@link #isTargetEndBlock() <em>Target End Block</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isTargetEndBlock()
   * @generated
   * @ordered
   */
  protected static final boolean TARGET_END_BLOCK_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isTargetEndBlock() <em>Target End Block</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isTargetEndBlock()
   * @generated
   * @ordered
   */
  protected boolean targetEndBlock = TARGET_END_BLOCK_EDEFAULT;

  /**
   * The default value of the '{@link #getTargetEndBlockCount() <em>Target End Block Count</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTargetEndBlockCount()
   * @generated
   * @ordered
   */
  protected static final int TARGET_END_BLOCK_COUNT_EDEFAULT = 0;

  /**
   * The cached value of the '{@link #getTargetEndBlockCount() <em>Target End Block Count</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTargetEndBlockCount()
   * @generated
   * @ordered
   */
  protected int targetEndBlockCount = TARGET_END_BLOCK_COUNT_EDEFAULT;

  /**
   * The default value of the '{@link #getSourceNote() <em>Source Note</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSourceNote()
   * @generated
   * @ordered
   */
  protected static final String SOURCE_NOTE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getSourceNote() <em>Source Note</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSourceNote()
   * @generated
   * @ordered
   */
  protected String sourceNote = SOURCE_NOTE_EDEFAULT;

  /**
   * The default value of the '{@link #getTargetNote() <em>Target Note</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTargetNote()
   * @generated
   * @ordered
   */
  protected static final String TARGET_NOTE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getTargetNote() <em>Target Note</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTargetNote()
   * @generated
   * @ordered
   */
  protected String targetNote = TARGET_NOTE_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected TwoLifelineMessageImpl()
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
    return SequencePackage.Literals.TWO_LIFELINE_MESSAGE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Lifeline getSourceLifeline()
  {
    if (sourceLifeline != null && sourceLifeline.eIsProxy())
    {
      InternalEObject oldSourceLifeline = (InternalEObject)sourceLifeline;
      sourceLifeline = (Lifeline)eResolveProxy(oldSourceLifeline);
      if (sourceLifeline != oldSourceLifeline)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, SequencePackage.TWO_LIFELINE_MESSAGE__SOURCE_LIFELINE, oldSourceLifeline, sourceLifeline));
      }
    }
    return sourceLifeline;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Lifeline basicGetSourceLifeline()
  {
    return sourceLifeline;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSourceLifeline(Lifeline newSourceLifeline)
  {
    Lifeline oldSourceLifeline = sourceLifeline;
    sourceLifeline = newSourceLifeline;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SequencePackage.TWO_LIFELINE_MESSAGE__SOURCE_LIFELINE, oldSourceLifeline, sourceLifeline));
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
      eNotify(new ENotificationImpl(this, Notification.SET, SequencePackage.TWO_LIFELINE_MESSAGE__MESSAGE_TYPE, oldMessageType, messageType));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getMessage()
  {
    return message;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMessage(String newMessage)
  {
    String oldMessage = message;
    message = newMessage;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SequencePackage.TWO_LIFELINE_MESSAGE__MESSAGE, oldMessage, message));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Lifeline getTargetLifeline()
  {
    if (targetLifeline != null && targetLifeline.eIsProxy())
    {
      InternalEObject oldTargetLifeline = (InternalEObject)targetLifeline;
      targetLifeline = (Lifeline)eResolveProxy(oldTargetLifeline);
      if (targetLifeline != oldTargetLifeline)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, SequencePackage.TWO_LIFELINE_MESSAGE__TARGET_LIFELINE, oldTargetLifeline, targetLifeline));
      }
    }
    return targetLifeline;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Lifeline basicGetTargetLifeline()
  {
    return targetLifeline;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTargetLifeline(Lifeline newTargetLifeline)
  {
    Lifeline oldTargetLifeline = targetLifeline;
    targetLifeline = newTargetLifeline;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SequencePackage.TWO_LIFELINE_MESSAGE__TARGET_LIFELINE, oldTargetLifeline, targetLifeline));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSourceStartBlock()
  {
    return sourceStartBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSourceStartBlock(boolean newSourceStartBlock)
  {
    boolean oldSourceStartBlock = sourceStartBlock;
    sourceStartBlock = newSourceStartBlock;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SequencePackage.TWO_LIFELINE_MESSAGE__SOURCE_START_BLOCK, oldSourceStartBlock, sourceStartBlock));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSourceEndBlock()
  {
    return sourceEndBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSourceEndBlock(boolean newSourceEndBlock)
  {
    boolean oldSourceEndBlock = sourceEndBlock;
    sourceEndBlock = newSourceEndBlock;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SequencePackage.TWO_LIFELINE_MESSAGE__SOURCE_END_BLOCK, oldSourceEndBlock, sourceEndBlock));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getSourceEndBlockCount()
  {
    return sourceEndBlockCount;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSourceEndBlockCount(int newSourceEndBlockCount)
  {
    int oldSourceEndBlockCount = sourceEndBlockCount;
    sourceEndBlockCount = newSourceEndBlockCount;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SequencePackage.TWO_LIFELINE_MESSAGE__SOURCE_END_BLOCK_COUNT, oldSourceEndBlockCount, sourceEndBlockCount));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isTargetStartBlock()
  {
    return targetStartBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTargetStartBlock(boolean newTargetStartBlock)
  {
    boolean oldTargetStartBlock = targetStartBlock;
    targetStartBlock = newTargetStartBlock;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SequencePackage.TWO_LIFELINE_MESSAGE__TARGET_START_BLOCK, oldTargetStartBlock, targetStartBlock));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isTargetEndBlock()
  {
    return targetEndBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTargetEndBlock(boolean newTargetEndBlock)
  {
    boolean oldTargetEndBlock = targetEndBlock;
    targetEndBlock = newTargetEndBlock;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SequencePackage.TWO_LIFELINE_MESSAGE__TARGET_END_BLOCK, oldTargetEndBlock, targetEndBlock));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getTargetEndBlockCount()
  {
    return targetEndBlockCount;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTargetEndBlockCount(int newTargetEndBlockCount)
  {
    int oldTargetEndBlockCount = targetEndBlockCount;
    targetEndBlockCount = newTargetEndBlockCount;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SequencePackage.TWO_LIFELINE_MESSAGE__TARGET_END_BLOCK_COUNT, oldTargetEndBlockCount, targetEndBlockCount));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getSourceNote()
  {
    return sourceNote;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSourceNote(String newSourceNote)
  {
    String oldSourceNote = sourceNote;
    sourceNote = newSourceNote;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SequencePackage.TWO_LIFELINE_MESSAGE__SOURCE_NOTE, oldSourceNote, sourceNote));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getTargetNote()
  {
    return targetNote;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTargetNote(String newTargetNote)
  {
    String oldTargetNote = targetNote;
    targetNote = newTargetNote;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SequencePackage.TWO_LIFELINE_MESSAGE__TARGET_NOTE, oldTargetNote, targetNote));
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
      case SequencePackage.TWO_LIFELINE_MESSAGE__SOURCE_LIFELINE:
        if (resolve) return getSourceLifeline();
        return basicGetSourceLifeline();
      case SequencePackage.TWO_LIFELINE_MESSAGE__MESSAGE_TYPE:
        return getMessageType();
      case SequencePackage.TWO_LIFELINE_MESSAGE__MESSAGE:
        return getMessage();
      case SequencePackage.TWO_LIFELINE_MESSAGE__TARGET_LIFELINE:
        if (resolve) return getTargetLifeline();
        return basicGetTargetLifeline();
      case SequencePackage.TWO_LIFELINE_MESSAGE__SOURCE_START_BLOCK:
        return isSourceStartBlock();
      case SequencePackage.TWO_LIFELINE_MESSAGE__SOURCE_END_BLOCK:
        return isSourceEndBlock();
      case SequencePackage.TWO_LIFELINE_MESSAGE__SOURCE_END_BLOCK_COUNT:
        return getSourceEndBlockCount();
      case SequencePackage.TWO_LIFELINE_MESSAGE__TARGET_START_BLOCK:
        return isTargetStartBlock();
      case SequencePackage.TWO_LIFELINE_MESSAGE__TARGET_END_BLOCK:
        return isTargetEndBlock();
      case SequencePackage.TWO_LIFELINE_MESSAGE__TARGET_END_BLOCK_COUNT:
        return getTargetEndBlockCount();
      case SequencePackage.TWO_LIFELINE_MESSAGE__SOURCE_NOTE:
        return getSourceNote();
      case SequencePackage.TWO_LIFELINE_MESSAGE__TARGET_NOTE:
        return getTargetNote();
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
      case SequencePackage.TWO_LIFELINE_MESSAGE__SOURCE_LIFELINE:
        setSourceLifeline((Lifeline)newValue);
        return;
      case SequencePackage.TWO_LIFELINE_MESSAGE__MESSAGE_TYPE:
        setMessageType((MessageType)newValue);
        return;
      case SequencePackage.TWO_LIFELINE_MESSAGE__MESSAGE:
        setMessage((String)newValue);
        return;
      case SequencePackage.TWO_LIFELINE_MESSAGE__TARGET_LIFELINE:
        setTargetLifeline((Lifeline)newValue);
        return;
      case SequencePackage.TWO_LIFELINE_MESSAGE__SOURCE_START_BLOCK:
        setSourceStartBlock((Boolean)newValue);
        return;
      case SequencePackage.TWO_LIFELINE_MESSAGE__SOURCE_END_BLOCK:
        setSourceEndBlock((Boolean)newValue);
        return;
      case SequencePackage.TWO_LIFELINE_MESSAGE__SOURCE_END_BLOCK_COUNT:
        setSourceEndBlockCount((Integer)newValue);
        return;
      case SequencePackage.TWO_LIFELINE_MESSAGE__TARGET_START_BLOCK:
        setTargetStartBlock((Boolean)newValue);
        return;
      case SequencePackage.TWO_LIFELINE_MESSAGE__TARGET_END_BLOCK:
        setTargetEndBlock((Boolean)newValue);
        return;
      case SequencePackage.TWO_LIFELINE_MESSAGE__TARGET_END_BLOCK_COUNT:
        setTargetEndBlockCount((Integer)newValue);
        return;
      case SequencePackage.TWO_LIFELINE_MESSAGE__SOURCE_NOTE:
        setSourceNote((String)newValue);
        return;
      case SequencePackage.TWO_LIFELINE_MESSAGE__TARGET_NOTE:
        setTargetNote((String)newValue);
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
      case SequencePackage.TWO_LIFELINE_MESSAGE__SOURCE_LIFELINE:
        setSourceLifeline((Lifeline)null);
        return;
      case SequencePackage.TWO_LIFELINE_MESSAGE__MESSAGE_TYPE:
        setMessageType(MESSAGE_TYPE_EDEFAULT);
        return;
      case SequencePackage.TWO_LIFELINE_MESSAGE__MESSAGE:
        setMessage(MESSAGE_EDEFAULT);
        return;
      case SequencePackage.TWO_LIFELINE_MESSAGE__TARGET_LIFELINE:
        setTargetLifeline((Lifeline)null);
        return;
      case SequencePackage.TWO_LIFELINE_MESSAGE__SOURCE_START_BLOCK:
        setSourceStartBlock(SOURCE_START_BLOCK_EDEFAULT);
        return;
      case SequencePackage.TWO_LIFELINE_MESSAGE__SOURCE_END_BLOCK:
        setSourceEndBlock(SOURCE_END_BLOCK_EDEFAULT);
        return;
      case SequencePackage.TWO_LIFELINE_MESSAGE__SOURCE_END_BLOCK_COUNT:
        setSourceEndBlockCount(SOURCE_END_BLOCK_COUNT_EDEFAULT);
        return;
      case SequencePackage.TWO_LIFELINE_MESSAGE__TARGET_START_BLOCK:
        setTargetStartBlock(TARGET_START_BLOCK_EDEFAULT);
        return;
      case SequencePackage.TWO_LIFELINE_MESSAGE__TARGET_END_BLOCK:
        setTargetEndBlock(TARGET_END_BLOCK_EDEFAULT);
        return;
      case SequencePackage.TWO_LIFELINE_MESSAGE__TARGET_END_BLOCK_COUNT:
        setTargetEndBlockCount(TARGET_END_BLOCK_COUNT_EDEFAULT);
        return;
      case SequencePackage.TWO_LIFELINE_MESSAGE__SOURCE_NOTE:
        setSourceNote(SOURCE_NOTE_EDEFAULT);
        return;
      case SequencePackage.TWO_LIFELINE_MESSAGE__TARGET_NOTE:
        setTargetNote(TARGET_NOTE_EDEFAULT);
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
      case SequencePackage.TWO_LIFELINE_MESSAGE__SOURCE_LIFELINE:
        return sourceLifeline != null;
      case SequencePackage.TWO_LIFELINE_MESSAGE__MESSAGE_TYPE:
        return messageType != MESSAGE_TYPE_EDEFAULT;
      case SequencePackage.TWO_LIFELINE_MESSAGE__MESSAGE:
        return MESSAGE_EDEFAULT == null ? message != null : !MESSAGE_EDEFAULT.equals(message);
      case SequencePackage.TWO_LIFELINE_MESSAGE__TARGET_LIFELINE:
        return targetLifeline != null;
      case SequencePackage.TWO_LIFELINE_MESSAGE__SOURCE_START_BLOCK:
        return sourceStartBlock != SOURCE_START_BLOCK_EDEFAULT;
      case SequencePackage.TWO_LIFELINE_MESSAGE__SOURCE_END_BLOCK:
        return sourceEndBlock != SOURCE_END_BLOCK_EDEFAULT;
      case SequencePackage.TWO_LIFELINE_MESSAGE__SOURCE_END_BLOCK_COUNT:
        return sourceEndBlockCount != SOURCE_END_BLOCK_COUNT_EDEFAULT;
      case SequencePackage.TWO_LIFELINE_MESSAGE__TARGET_START_BLOCK:
        return targetStartBlock != TARGET_START_BLOCK_EDEFAULT;
      case SequencePackage.TWO_LIFELINE_MESSAGE__TARGET_END_BLOCK:
        return targetEndBlock != TARGET_END_BLOCK_EDEFAULT;
      case SequencePackage.TWO_LIFELINE_MESSAGE__TARGET_END_BLOCK_COUNT:
        return targetEndBlockCount != TARGET_END_BLOCK_COUNT_EDEFAULT;
      case SequencePackage.TWO_LIFELINE_MESSAGE__SOURCE_NOTE:
        return SOURCE_NOTE_EDEFAULT == null ? sourceNote != null : !SOURCE_NOTE_EDEFAULT.equals(sourceNote);
      case SequencePackage.TWO_LIFELINE_MESSAGE__TARGET_NOTE:
        return TARGET_NOTE_EDEFAULT == null ? targetNote != null : !TARGET_NOTE_EDEFAULT.equals(targetNote);
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
    result.append(", message: ");
    result.append(message);
    result.append(", sourceStartBlock: ");
    result.append(sourceStartBlock);
    result.append(", sourceEndBlock: ");
    result.append(sourceEndBlock);
    result.append(", sourceEndBlockCount: ");
    result.append(sourceEndBlockCount);
    result.append(", targetStartBlock: ");
    result.append(targetStartBlock);
    result.append(", targetEndBlock: ");
    result.append(targetEndBlock);
    result.append(", targetEndBlockCount: ");
    result.append(targetEndBlockCount);
    result.append(", sourceNote: ");
    result.append(sourceNote);
    result.append(", targetNote: ");
    result.append(targetNote);
    result.append(')');
    return result.toString();
  }

} //TwoLifelineMessageImpl
