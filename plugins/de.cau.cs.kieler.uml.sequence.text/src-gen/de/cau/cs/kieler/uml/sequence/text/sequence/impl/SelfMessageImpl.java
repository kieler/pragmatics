/**
 */
package de.cau.cs.kieler.uml.sequence.text.sequence.impl;

import de.cau.cs.kieler.uml.sequence.text.sequence.Lifeline;
import de.cau.cs.kieler.uml.sequence.text.sequence.SelfMessage;
import de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Self Message</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.SelfMessageImpl#getLifeline <em>Lifeline</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.SelfMessageImpl#getMessageType <em>Message Type</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.SelfMessageImpl#getMessage <em>Message</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.SelfMessageImpl#isStartEndExec <em>Start End Exec</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.SelfMessageImpl#isStartExec <em>Start Exec</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.SelfMessageImpl#isEndExec <em>End Exec</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.SelfMessageImpl#getEndExecCount <em>End Exec Count</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.SelfMessageImpl#getNote <em>Note</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SelfMessageImpl extends InteractionImpl implements SelfMessage
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
  protected static final String MESSAGE_TYPE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getMessageType() <em>Message Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMessageType()
   * @generated
   * @ordered
   */
  protected String messageType = MESSAGE_TYPE_EDEFAULT;

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
   * The default value of the '{@link #isStartEndExec() <em>Start End Exec</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isStartEndExec()
   * @generated
   * @ordered
   */
  protected static final boolean START_END_EXEC_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isStartEndExec() <em>Start End Exec</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isStartEndExec()
   * @generated
   * @ordered
   */
  protected boolean startEndExec = START_END_EXEC_EDEFAULT;

  /**
   * The default value of the '{@link #isStartExec() <em>Start Exec</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isStartExec()
   * @generated
   * @ordered
   */
  protected static final boolean START_EXEC_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isStartExec() <em>Start Exec</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isStartExec()
   * @generated
   * @ordered
   */
  protected boolean startExec = START_EXEC_EDEFAULT;

  /**
   * The default value of the '{@link #isEndExec() <em>End Exec</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isEndExec()
   * @generated
   * @ordered
   */
  protected static final boolean END_EXEC_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isEndExec() <em>End Exec</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isEndExec()
   * @generated
   * @ordered
   */
  protected boolean endExec = END_EXEC_EDEFAULT;

  /**
   * The default value of the '{@link #getEndExecCount() <em>End Exec Count</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEndExecCount()
   * @generated
   * @ordered
   */
  protected static final int END_EXEC_COUNT_EDEFAULT = 0;

  /**
   * The cached value of the '{@link #getEndExecCount() <em>End Exec Count</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEndExecCount()
   * @generated
   * @ordered
   */
  protected int endExecCount = END_EXEC_COUNT_EDEFAULT;

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
  protected SelfMessageImpl()
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
    return SequencePackage.Literals.SELF_MESSAGE;
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
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, SequencePackage.SELF_MESSAGE__LIFELINE, oldLifeline, lifeline));
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
      eNotify(new ENotificationImpl(this, Notification.SET, SequencePackage.SELF_MESSAGE__LIFELINE, oldLifeline, lifeline));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getMessageType()
  {
    return messageType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMessageType(String newMessageType)
  {
    String oldMessageType = messageType;
    messageType = newMessageType;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SequencePackage.SELF_MESSAGE__MESSAGE_TYPE, oldMessageType, messageType));
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
      eNotify(new ENotificationImpl(this, Notification.SET, SequencePackage.SELF_MESSAGE__MESSAGE, oldMessage, message));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isStartEndExec()
  {
    return startEndExec;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setStartEndExec(boolean newStartEndExec)
  {
    boolean oldStartEndExec = startEndExec;
    startEndExec = newStartEndExec;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SequencePackage.SELF_MESSAGE__START_END_EXEC, oldStartEndExec, startEndExec));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isStartExec()
  {
    return startExec;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setStartExec(boolean newStartExec)
  {
    boolean oldStartExec = startExec;
    startExec = newStartExec;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SequencePackage.SELF_MESSAGE__START_EXEC, oldStartExec, startExec));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isEndExec()
  {
    return endExec;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEndExec(boolean newEndExec)
  {
    boolean oldEndExec = endExec;
    endExec = newEndExec;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SequencePackage.SELF_MESSAGE__END_EXEC, oldEndExec, endExec));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getEndExecCount()
  {
    return endExecCount;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEndExecCount(int newEndExecCount)
  {
    int oldEndExecCount = endExecCount;
    endExecCount = newEndExecCount;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SequencePackage.SELF_MESSAGE__END_EXEC_COUNT, oldEndExecCount, endExecCount));
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
      eNotify(new ENotificationImpl(this, Notification.SET, SequencePackage.SELF_MESSAGE__NOTE, oldNote, note));
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
      case SequencePackage.SELF_MESSAGE__LIFELINE:
        if (resolve) return getLifeline();
        return basicGetLifeline();
      case SequencePackage.SELF_MESSAGE__MESSAGE_TYPE:
        return getMessageType();
      case SequencePackage.SELF_MESSAGE__MESSAGE:
        return getMessage();
      case SequencePackage.SELF_MESSAGE__START_END_EXEC:
        return isStartEndExec();
      case SequencePackage.SELF_MESSAGE__START_EXEC:
        return isStartExec();
      case SequencePackage.SELF_MESSAGE__END_EXEC:
        return isEndExec();
      case SequencePackage.SELF_MESSAGE__END_EXEC_COUNT:
        return getEndExecCount();
      case SequencePackage.SELF_MESSAGE__NOTE:
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
      case SequencePackage.SELF_MESSAGE__LIFELINE:
        setLifeline((Lifeline)newValue);
        return;
      case SequencePackage.SELF_MESSAGE__MESSAGE_TYPE:
        setMessageType((String)newValue);
        return;
      case SequencePackage.SELF_MESSAGE__MESSAGE:
        setMessage((String)newValue);
        return;
      case SequencePackage.SELF_MESSAGE__START_END_EXEC:
        setStartEndExec((Boolean)newValue);
        return;
      case SequencePackage.SELF_MESSAGE__START_EXEC:
        setStartExec((Boolean)newValue);
        return;
      case SequencePackage.SELF_MESSAGE__END_EXEC:
        setEndExec((Boolean)newValue);
        return;
      case SequencePackage.SELF_MESSAGE__END_EXEC_COUNT:
        setEndExecCount((Integer)newValue);
        return;
      case SequencePackage.SELF_MESSAGE__NOTE:
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
      case SequencePackage.SELF_MESSAGE__LIFELINE:
        setLifeline((Lifeline)null);
        return;
      case SequencePackage.SELF_MESSAGE__MESSAGE_TYPE:
        setMessageType(MESSAGE_TYPE_EDEFAULT);
        return;
      case SequencePackage.SELF_MESSAGE__MESSAGE:
        setMessage(MESSAGE_EDEFAULT);
        return;
      case SequencePackage.SELF_MESSAGE__START_END_EXEC:
        setStartEndExec(START_END_EXEC_EDEFAULT);
        return;
      case SequencePackage.SELF_MESSAGE__START_EXEC:
        setStartExec(START_EXEC_EDEFAULT);
        return;
      case SequencePackage.SELF_MESSAGE__END_EXEC:
        setEndExec(END_EXEC_EDEFAULT);
        return;
      case SequencePackage.SELF_MESSAGE__END_EXEC_COUNT:
        setEndExecCount(END_EXEC_COUNT_EDEFAULT);
        return;
      case SequencePackage.SELF_MESSAGE__NOTE:
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
      case SequencePackage.SELF_MESSAGE__LIFELINE:
        return lifeline != null;
      case SequencePackage.SELF_MESSAGE__MESSAGE_TYPE:
        return MESSAGE_TYPE_EDEFAULT == null ? messageType != null : !MESSAGE_TYPE_EDEFAULT.equals(messageType);
      case SequencePackage.SELF_MESSAGE__MESSAGE:
        return MESSAGE_EDEFAULT == null ? message != null : !MESSAGE_EDEFAULT.equals(message);
      case SequencePackage.SELF_MESSAGE__START_END_EXEC:
        return startEndExec != START_END_EXEC_EDEFAULT;
      case SequencePackage.SELF_MESSAGE__START_EXEC:
        return startExec != START_EXEC_EDEFAULT;
      case SequencePackage.SELF_MESSAGE__END_EXEC:
        return endExec != END_EXEC_EDEFAULT;
      case SequencePackage.SELF_MESSAGE__END_EXEC_COUNT:
        return endExecCount != END_EXEC_COUNT_EDEFAULT;
      case SequencePackage.SELF_MESSAGE__NOTE:
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
    result.append(", message: ");
    result.append(message);
    result.append(", startEndExec: ");
    result.append(startEndExec);
    result.append(", startExec: ");
    result.append(startExec);
    result.append(", endExec: ");
    result.append(endExec);
    result.append(", endExecCount: ");
    result.append(endExecCount);
    result.append(", note: ");
    result.append(note);
    result.append(')');
    return result.toString();
  }

} //SelfMessageImpl
