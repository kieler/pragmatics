/**
 */
package de.cau.cs.kieler.uml.sequence.text.sequence.impl;

import de.cau.cs.kieler.uml.sequence.text.sequence.Lifeline;
import de.cau.cs.kieler.uml.sequence.text.sequence.MessageTypeTwo;
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
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.TwoLifelineMessageImpl#isSourceStartEndExec <em>Source Start End Exec</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.TwoLifelineMessageImpl#isSourceStartExec <em>Source Start Exec</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.TwoLifelineMessageImpl#isSourceEndExec <em>Source End Exec</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.TwoLifelineMessageImpl#getSourceEndExecCount <em>Source End Exec Count</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.TwoLifelineMessageImpl#isTargetStartEndExec <em>Target Start End Exec</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.TwoLifelineMessageImpl#isTargetStartExec <em>Target Start Exec</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.TwoLifelineMessageImpl#isTargetEndExec <em>Target End Exec</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.TwoLifelineMessageImpl#getTargetEndExecCount <em>Target End Exec Count</em>}</li>
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
  protected static final MessageTypeTwo MESSAGE_TYPE_EDEFAULT = MessageTypeTwo.ASYNC;

  /**
   * The cached value of the '{@link #getMessageType() <em>Message Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMessageType()
   * @generated
   * @ordered
   */
  protected MessageTypeTwo messageType = MESSAGE_TYPE_EDEFAULT;

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
   * The default value of the '{@link #isSourceStartEndExec() <em>Source Start End Exec</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSourceStartEndExec()
   * @generated
   * @ordered
   */
  protected static final boolean SOURCE_START_END_EXEC_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isSourceStartEndExec() <em>Source Start End Exec</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSourceStartEndExec()
   * @generated
   * @ordered
   */
  protected boolean sourceStartEndExec = SOURCE_START_END_EXEC_EDEFAULT;

  /**
   * The default value of the '{@link #isSourceStartExec() <em>Source Start Exec</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSourceStartExec()
   * @generated
   * @ordered
   */
  protected static final boolean SOURCE_START_EXEC_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isSourceStartExec() <em>Source Start Exec</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSourceStartExec()
   * @generated
   * @ordered
   */
  protected boolean sourceStartExec = SOURCE_START_EXEC_EDEFAULT;

  /**
   * The default value of the '{@link #isSourceEndExec() <em>Source End Exec</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSourceEndExec()
   * @generated
   * @ordered
   */
  protected static final boolean SOURCE_END_EXEC_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isSourceEndExec() <em>Source End Exec</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSourceEndExec()
   * @generated
   * @ordered
   */
  protected boolean sourceEndExec = SOURCE_END_EXEC_EDEFAULT;

  /**
   * The default value of the '{@link #getSourceEndExecCount() <em>Source End Exec Count</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSourceEndExecCount()
   * @generated
   * @ordered
   */
  protected static final int SOURCE_END_EXEC_COUNT_EDEFAULT = 0;

  /**
   * The cached value of the '{@link #getSourceEndExecCount() <em>Source End Exec Count</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSourceEndExecCount()
   * @generated
   * @ordered
   */
  protected int sourceEndExecCount = SOURCE_END_EXEC_COUNT_EDEFAULT;

  /**
   * The default value of the '{@link #isTargetStartEndExec() <em>Target Start End Exec</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isTargetStartEndExec()
   * @generated
   * @ordered
   */
  protected static final boolean TARGET_START_END_EXEC_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isTargetStartEndExec() <em>Target Start End Exec</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isTargetStartEndExec()
   * @generated
   * @ordered
   */
  protected boolean targetStartEndExec = TARGET_START_END_EXEC_EDEFAULT;

  /**
   * The default value of the '{@link #isTargetStartExec() <em>Target Start Exec</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isTargetStartExec()
   * @generated
   * @ordered
   */
  protected static final boolean TARGET_START_EXEC_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isTargetStartExec() <em>Target Start Exec</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isTargetStartExec()
   * @generated
   * @ordered
   */
  protected boolean targetStartExec = TARGET_START_EXEC_EDEFAULT;

  /**
   * The default value of the '{@link #isTargetEndExec() <em>Target End Exec</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isTargetEndExec()
   * @generated
   * @ordered
   */
  protected static final boolean TARGET_END_EXEC_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isTargetEndExec() <em>Target End Exec</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isTargetEndExec()
   * @generated
   * @ordered
   */
  protected boolean targetEndExec = TARGET_END_EXEC_EDEFAULT;

  /**
   * The default value of the '{@link #getTargetEndExecCount() <em>Target End Exec Count</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTargetEndExecCount()
   * @generated
   * @ordered
   */
  protected static final int TARGET_END_EXEC_COUNT_EDEFAULT = 0;

  /**
   * The cached value of the '{@link #getTargetEndExecCount() <em>Target End Exec Count</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTargetEndExecCount()
   * @generated
   * @ordered
   */
  protected int targetEndExecCount = TARGET_END_EXEC_COUNT_EDEFAULT;

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
  public MessageTypeTwo getMessageType()
  {
    return messageType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMessageType(MessageTypeTwo newMessageType)
  {
    MessageTypeTwo oldMessageType = messageType;
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
  public boolean isSourceStartEndExec()
  {
    return sourceStartEndExec;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSourceStartEndExec(boolean newSourceStartEndExec)
  {
    boolean oldSourceStartEndExec = sourceStartEndExec;
    sourceStartEndExec = newSourceStartEndExec;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SequencePackage.TWO_LIFELINE_MESSAGE__SOURCE_START_END_EXEC, oldSourceStartEndExec, sourceStartEndExec));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSourceStartExec()
  {
    return sourceStartExec;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSourceStartExec(boolean newSourceStartExec)
  {
    boolean oldSourceStartExec = sourceStartExec;
    sourceStartExec = newSourceStartExec;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SequencePackage.TWO_LIFELINE_MESSAGE__SOURCE_START_EXEC, oldSourceStartExec, sourceStartExec));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSourceEndExec()
  {
    return sourceEndExec;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSourceEndExec(boolean newSourceEndExec)
  {
    boolean oldSourceEndExec = sourceEndExec;
    sourceEndExec = newSourceEndExec;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SequencePackage.TWO_LIFELINE_MESSAGE__SOURCE_END_EXEC, oldSourceEndExec, sourceEndExec));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getSourceEndExecCount()
  {
    return sourceEndExecCount;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSourceEndExecCount(int newSourceEndExecCount)
  {
    int oldSourceEndExecCount = sourceEndExecCount;
    sourceEndExecCount = newSourceEndExecCount;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SequencePackage.TWO_LIFELINE_MESSAGE__SOURCE_END_EXEC_COUNT, oldSourceEndExecCount, sourceEndExecCount));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isTargetStartEndExec()
  {
    return targetStartEndExec;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTargetStartEndExec(boolean newTargetStartEndExec)
  {
    boolean oldTargetStartEndExec = targetStartEndExec;
    targetStartEndExec = newTargetStartEndExec;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SequencePackage.TWO_LIFELINE_MESSAGE__TARGET_START_END_EXEC, oldTargetStartEndExec, targetStartEndExec));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isTargetStartExec()
  {
    return targetStartExec;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTargetStartExec(boolean newTargetStartExec)
  {
    boolean oldTargetStartExec = targetStartExec;
    targetStartExec = newTargetStartExec;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SequencePackage.TWO_LIFELINE_MESSAGE__TARGET_START_EXEC, oldTargetStartExec, targetStartExec));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isTargetEndExec()
  {
    return targetEndExec;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTargetEndExec(boolean newTargetEndExec)
  {
    boolean oldTargetEndExec = targetEndExec;
    targetEndExec = newTargetEndExec;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SequencePackage.TWO_LIFELINE_MESSAGE__TARGET_END_EXEC, oldTargetEndExec, targetEndExec));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getTargetEndExecCount()
  {
    return targetEndExecCount;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTargetEndExecCount(int newTargetEndExecCount)
  {
    int oldTargetEndExecCount = targetEndExecCount;
    targetEndExecCount = newTargetEndExecCount;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SequencePackage.TWO_LIFELINE_MESSAGE__TARGET_END_EXEC_COUNT, oldTargetEndExecCount, targetEndExecCount));
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
      case SequencePackage.TWO_LIFELINE_MESSAGE__SOURCE_START_END_EXEC:
        return isSourceStartEndExec();
      case SequencePackage.TWO_LIFELINE_MESSAGE__SOURCE_START_EXEC:
        return isSourceStartExec();
      case SequencePackage.TWO_LIFELINE_MESSAGE__SOURCE_END_EXEC:
        return isSourceEndExec();
      case SequencePackage.TWO_LIFELINE_MESSAGE__SOURCE_END_EXEC_COUNT:
        return getSourceEndExecCount();
      case SequencePackage.TWO_LIFELINE_MESSAGE__TARGET_START_END_EXEC:
        return isTargetStartEndExec();
      case SequencePackage.TWO_LIFELINE_MESSAGE__TARGET_START_EXEC:
        return isTargetStartExec();
      case SequencePackage.TWO_LIFELINE_MESSAGE__TARGET_END_EXEC:
        return isTargetEndExec();
      case SequencePackage.TWO_LIFELINE_MESSAGE__TARGET_END_EXEC_COUNT:
        return getTargetEndExecCount();
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
        setMessageType((MessageTypeTwo)newValue);
        return;
      case SequencePackage.TWO_LIFELINE_MESSAGE__MESSAGE:
        setMessage((String)newValue);
        return;
      case SequencePackage.TWO_LIFELINE_MESSAGE__TARGET_LIFELINE:
        setTargetLifeline((Lifeline)newValue);
        return;
      case SequencePackage.TWO_LIFELINE_MESSAGE__SOURCE_START_END_EXEC:
        setSourceStartEndExec((Boolean)newValue);
        return;
      case SequencePackage.TWO_LIFELINE_MESSAGE__SOURCE_START_EXEC:
        setSourceStartExec((Boolean)newValue);
        return;
      case SequencePackage.TWO_LIFELINE_MESSAGE__SOURCE_END_EXEC:
        setSourceEndExec((Boolean)newValue);
        return;
      case SequencePackage.TWO_LIFELINE_MESSAGE__SOURCE_END_EXEC_COUNT:
        setSourceEndExecCount((Integer)newValue);
        return;
      case SequencePackage.TWO_LIFELINE_MESSAGE__TARGET_START_END_EXEC:
        setTargetStartEndExec((Boolean)newValue);
        return;
      case SequencePackage.TWO_LIFELINE_MESSAGE__TARGET_START_EXEC:
        setTargetStartExec((Boolean)newValue);
        return;
      case SequencePackage.TWO_LIFELINE_MESSAGE__TARGET_END_EXEC:
        setTargetEndExec((Boolean)newValue);
        return;
      case SequencePackage.TWO_LIFELINE_MESSAGE__TARGET_END_EXEC_COUNT:
        setTargetEndExecCount((Integer)newValue);
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
      case SequencePackage.TWO_LIFELINE_MESSAGE__SOURCE_START_END_EXEC:
        setSourceStartEndExec(SOURCE_START_END_EXEC_EDEFAULT);
        return;
      case SequencePackage.TWO_LIFELINE_MESSAGE__SOURCE_START_EXEC:
        setSourceStartExec(SOURCE_START_EXEC_EDEFAULT);
        return;
      case SequencePackage.TWO_LIFELINE_MESSAGE__SOURCE_END_EXEC:
        setSourceEndExec(SOURCE_END_EXEC_EDEFAULT);
        return;
      case SequencePackage.TWO_LIFELINE_MESSAGE__SOURCE_END_EXEC_COUNT:
        setSourceEndExecCount(SOURCE_END_EXEC_COUNT_EDEFAULT);
        return;
      case SequencePackage.TWO_LIFELINE_MESSAGE__TARGET_START_END_EXEC:
        setTargetStartEndExec(TARGET_START_END_EXEC_EDEFAULT);
        return;
      case SequencePackage.TWO_LIFELINE_MESSAGE__TARGET_START_EXEC:
        setTargetStartExec(TARGET_START_EXEC_EDEFAULT);
        return;
      case SequencePackage.TWO_LIFELINE_MESSAGE__TARGET_END_EXEC:
        setTargetEndExec(TARGET_END_EXEC_EDEFAULT);
        return;
      case SequencePackage.TWO_LIFELINE_MESSAGE__TARGET_END_EXEC_COUNT:
        setTargetEndExecCount(TARGET_END_EXEC_COUNT_EDEFAULT);
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
      case SequencePackage.TWO_LIFELINE_MESSAGE__SOURCE_START_END_EXEC:
        return sourceStartEndExec != SOURCE_START_END_EXEC_EDEFAULT;
      case SequencePackage.TWO_LIFELINE_MESSAGE__SOURCE_START_EXEC:
        return sourceStartExec != SOURCE_START_EXEC_EDEFAULT;
      case SequencePackage.TWO_LIFELINE_MESSAGE__SOURCE_END_EXEC:
        return sourceEndExec != SOURCE_END_EXEC_EDEFAULT;
      case SequencePackage.TWO_LIFELINE_MESSAGE__SOURCE_END_EXEC_COUNT:
        return sourceEndExecCount != SOURCE_END_EXEC_COUNT_EDEFAULT;
      case SequencePackage.TWO_LIFELINE_MESSAGE__TARGET_START_END_EXEC:
        return targetStartEndExec != TARGET_START_END_EXEC_EDEFAULT;
      case SequencePackage.TWO_LIFELINE_MESSAGE__TARGET_START_EXEC:
        return targetStartExec != TARGET_START_EXEC_EDEFAULT;
      case SequencePackage.TWO_LIFELINE_MESSAGE__TARGET_END_EXEC:
        return targetEndExec != TARGET_END_EXEC_EDEFAULT;
      case SequencePackage.TWO_LIFELINE_MESSAGE__TARGET_END_EXEC_COUNT:
        return targetEndExecCount != TARGET_END_EXEC_COUNT_EDEFAULT;
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
    result.append(", sourceStartEndExec: ");
    result.append(sourceStartEndExec);
    result.append(", sourceStartExec: ");
    result.append(sourceStartExec);
    result.append(", sourceEndExec: ");
    result.append(sourceEndExec);
    result.append(", sourceEndExecCount: ");
    result.append(sourceEndExecCount);
    result.append(", targetStartEndExec: ");
    result.append(targetStartEndExec);
    result.append(", targetStartExec: ");
    result.append(targetStartExec);
    result.append(", targetEndExec: ");
    result.append(targetEndExec);
    result.append(", targetEndExecCount: ");
    result.append(targetEndExecCount);
    result.append(", sourceNote: ");
    result.append(sourceNote);
    result.append(", targetNote: ");
    result.append(targetNote);
    result.append(')');
    return result.toString();
  }

} //TwoLifelineMessageImpl
