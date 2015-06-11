/**
 */
package de.cau.cs.kieler.uml.sequence.text.sequence.impl;

import de.cau.cs.kieler.uml.sequence.text.sequence.Lifeline;
import de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage;
import de.cau.cs.kieler.uml.sequence.text.sequence.TransitionType;
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
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.TwoLifelineMessageImpl#getTransitionType <em>Transition Type</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.TwoLifelineMessageImpl#getCaption <em>Caption</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.TwoLifelineMessageImpl#getTargetLifeline <em>Target Lifeline</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.TwoLifelineMessageImpl#isStartBlockLeft <em>Start Block Left</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.TwoLifelineMessageImpl#isEndBlockLeft <em>End Block Left</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.TwoLifelineMessageImpl#getEndBlockLeftCount <em>End Block Left Count</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.TwoLifelineMessageImpl#isStartBlockRight <em>Start Block Right</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.TwoLifelineMessageImpl#isEndBlockRight <em>End Block Right</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.TwoLifelineMessageImpl#getEndBlockRightCount <em>End Block Right Count</em>}</li>
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
   * The default value of the '{@link #getTransitionType() <em>Transition Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTransitionType()
   * @generated
   * @ordered
   */
  protected static final TransitionType TRANSITION_TYPE_EDEFAULT = TransitionType.ASYNC;

  /**
   * The cached value of the '{@link #getTransitionType() <em>Transition Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTransitionType()
   * @generated
   * @ordered
   */
  protected TransitionType transitionType = TRANSITION_TYPE_EDEFAULT;

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
   * The cached value of the '{@link #getTargetLifeline() <em>Target Lifeline</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTargetLifeline()
   * @generated
   * @ordered
   */
  protected Lifeline targetLifeline;

  /**
   * The default value of the '{@link #isStartBlockLeft() <em>Start Block Left</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isStartBlockLeft()
   * @generated
   * @ordered
   */
  protected static final boolean START_BLOCK_LEFT_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isStartBlockLeft() <em>Start Block Left</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isStartBlockLeft()
   * @generated
   * @ordered
   */
  protected boolean startBlockLeft = START_BLOCK_LEFT_EDEFAULT;

  /**
   * The default value of the '{@link #isEndBlockLeft() <em>End Block Left</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isEndBlockLeft()
   * @generated
   * @ordered
   */
  protected static final boolean END_BLOCK_LEFT_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isEndBlockLeft() <em>End Block Left</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isEndBlockLeft()
   * @generated
   * @ordered
   */
  protected boolean endBlockLeft = END_BLOCK_LEFT_EDEFAULT;

  /**
   * The default value of the '{@link #getEndBlockLeftCount() <em>End Block Left Count</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEndBlockLeftCount()
   * @generated
   * @ordered
   */
  protected static final int END_BLOCK_LEFT_COUNT_EDEFAULT = 0;

  /**
   * The cached value of the '{@link #getEndBlockLeftCount() <em>End Block Left Count</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEndBlockLeftCount()
   * @generated
   * @ordered
   */
  protected int endBlockLeftCount = END_BLOCK_LEFT_COUNT_EDEFAULT;

  /**
   * The default value of the '{@link #isStartBlockRight() <em>Start Block Right</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isStartBlockRight()
   * @generated
   * @ordered
   */
  protected static final boolean START_BLOCK_RIGHT_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isStartBlockRight() <em>Start Block Right</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isStartBlockRight()
   * @generated
   * @ordered
   */
  protected boolean startBlockRight = START_BLOCK_RIGHT_EDEFAULT;

  /**
   * The default value of the '{@link #isEndBlockRight() <em>End Block Right</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isEndBlockRight()
   * @generated
   * @ordered
   */
  protected static final boolean END_BLOCK_RIGHT_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isEndBlockRight() <em>End Block Right</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isEndBlockRight()
   * @generated
   * @ordered
   */
  protected boolean endBlockRight = END_BLOCK_RIGHT_EDEFAULT;

  /**
   * The default value of the '{@link #getEndBlockRightCount() <em>End Block Right Count</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEndBlockRightCount()
   * @generated
   * @ordered
   */
  protected static final int END_BLOCK_RIGHT_COUNT_EDEFAULT = 0;

  /**
   * The cached value of the '{@link #getEndBlockRightCount() <em>End Block Right Count</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEndBlockRightCount()
   * @generated
   * @ordered
   */
  protected int endBlockRightCount = END_BLOCK_RIGHT_COUNT_EDEFAULT;

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
  public TransitionType getTransitionType()
  {
    return transitionType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTransitionType(TransitionType newTransitionType)
  {
    TransitionType oldTransitionType = transitionType;
    transitionType = newTransitionType == null ? TRANSITION_TYPE_EDEFAULT : newTransitionType;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SequencePackage.TWO_LIFELINE_MESSAGE__TRANSITION_TYPE, oldTransitionType, transitionType));
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
      eNotify(new ENotificationImpl(this, Notification.SET, SequencePackage.TWO_LIFELINE_MESSAGE__CAPTION, oldCaption, caption));
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
  public boolean isStartBlockLeft()
  {
    return startBlockLeft;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setStartBlockLeft(boolean newStartBlockLeft)
  {
    boolean oldStartBlockLeft = startBlockLeft;
    startBlockLeft = newStartBlockLeft;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SequencePackage.TWO_LIFELINE_MESSAGE__START_BLOCK_LEFT, oldStartBlockLeft, startBlockLeft));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isEndBlockLeft()
  {
    return endBlockLeft;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEndBlockLeft(boolean newEndBlockLeft)
  {
    boolean oldEndBlockLeft = endBlockLeft;
    endBlockLeft = newEndBlockLeft;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SequencePackage.TWO_LIFELINE_MESSAGE__END_BLOCK_LEFT, oldEndBlockLeft, endBlockLeft));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getEndBlockLeftCount()
  {
    return endBlockLeftCount;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEndBlockLeftCount(int newEndBlockLeftCount)
  {
    int oldEndBlockLeftCount = endBlockLeftCount;
    endBlockLeftCount = newEndBlockLeftCount;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SequencePackage.TWO_LIFELINE_MESSAGE__END_BLOCK_LEFT_COUNT, oldEndBlockLeftCount, endBlockLeftCount));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isStartBlockRight()
  {
    return startBlockRight;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setStartBlockRight(boolean newStartBlockRight)
  {
    boolean oldStartBlockRight = startBlockRight;
    startBlockRight = newStartBlockRight;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SequencePackage.TWO_LIFELINE_MESSAGE__START_BLOCK_RIGHT, oldStartBlockRight, startBlockRight));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isEndBlockRight()
  {
    return endBlockRight;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEndBlockRight(boolean newEndBlockRight)
  {
    boolean oldEndBlockRight = endBlockRight;
    endBlockRight = newEndBlockRight;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SequencePackage.TWO_LIFELINE_MESSAGE__END_BLOCK_RIGHT, oldEndBlockRight, endBlockRight));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getEndBlockRightCount()
  {
    return endBlockRightCount;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEndBlockRightCount(int newEndBlockRightCount)
  {
    int oldEndBlockRightCount = endBlockRightCount;
    endBlockRightCount = newEndBlockRightCount;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SequencePackage.TWO_LIFELINE_MESSAGE__END_BLOCK_RIGHT_COUNT, oldEndBlockRightCount, endBlockRightCount));
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
      case SequencePackage.TWO_LIFELINE_MESSAGE__TRANSITION_TYPE:
        return getTransitionType();
      case SequencePackage.TWO_LIFELINE_MESSAGE__CAPTION:
        return getCaption();
      case SequencePackage.TWO_LIFELINE_MESSAGE__TARGET_LIFELINE:
        if (resolve) return getTargetLifeline();
        return basicGetTargetLifeline();
      case SequencePackage.TWO_LIFELINE_MESSAGE__START_BLOCK_LEFT:
        return isStartBlockLeft();
      case SequencePackage.TWO_LIFELINE_MESSAGE__END_BLOCK_LEFT:
        return isEndBlockLeft();
      case SequencePackage.TWO_LIFELINE_MESSAGE__END_BLOCK_LEFT_COUNT:
        return getEndBlockLeftCount();
      case SequencePackage.TWO_LIFELINE_MESSAGE__START_BLOCK_RIGHT:
        return isStartBlockRight();
      case SequencePackage.TWO_LIFELINE_MESSAGE__END_BLOCK_RIGHT:
        return isEndBlockRight();
      case SequencePackage.TWO_LIFELINE_MESSAGE__END_BLOCK_RIGHT_COUNT:
        return getEndBlockRightCount();
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
      case SequencePackage.TWO_LIFELINE_MESSAGE__TRANSITION_TYPE:
        setTransitionType((TransitionType)newValue);
        return;
      case SequencePackage.TWO_LIFELINE_MESSAGE__CAPTION:
        setCaption((String)newValue);
        return;
      case SequencePackage.TWO_LIFELINE_MESSAGE__TARGET_LIFELINE:
        setTargetLifeline((Lifeline)newValue);
        return;
      case SequencePackage.TWO_LIFELINE_MESSAGE__START_BLOCK_LEFT:
        setStartBlockLeft((Boolean)newValue);
        return;
      case SequencePackage.TWO_LIFELINE_MESSAGE__END_BLOCK_LEFT:
        setEndBlockLeft((Boolean)newValue);
        return;
      case SequencePackage.TWO_LIFELINE_MESSAGE__END_BLOCK_LEFT_COUNT:
        setEndBlockLeftCount((Integer)newValue);
        return;
      case SequencePackage.TWO_LIFELINE_MESSAGE__START_BLOCK_RIGHT:
        setStartBlockRight((Boolean)newValue);
        return;
      case SequencePackage.TWO_LIFELINE_MESSAGE__END_BLOCK_RIGHT:
        setEndBlockRight((Boolean)newValue);
        return;
      case SequencePackage.TWO_LIFELINE_MESSAGE__END_BLOCK_RIGHT_COUNT:
        setEndBlockRightCount((Integer)newValue);
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
      case SequencePackage.TWO_LIFELINE_MESSAGE__TRANSITION_TYPE:
        setTransitionType(TRANSITION_TYPE_EDEFAULT);
        return;
      case SequencePackage.TWO_LIFELINE_MESSAGE__CAPTION:
        setCaption(CAPTION_EDEFAULT);
        return;
      case SequencePackage.TWO_LIFELINE_MESSAGE__TARGET_LIFELINE:
        setTargetLifeline((Lifeline)null);
        return;
      case SequencePackage.TWO_LIFELINE_MESSAGE__START_BLOCK_LEFT:
        setStartBlockLeft(START_BLOCK_LEFT_EDEFAULT);
        return;
      case SequencePackage.TWO_LIFELINE_MESSAGE__END_BLOCK_LEFT:
        setEndBlockLeft(END_BLOCK_LEFT_EDEFAULT);
        return;
      case SequencePackage.TWO_LIFELINE_MESSAGE__END_BLOCK_LEFT_COUNT:
        setEndBlockLeftCount(END_BLOCK_LEFT_COUNT_EDEFAULT);
        return;
      case SequencePackage.TWO_LIFELINE_MESSAGE__START_BLOCK_RIGHT:
        setStartBlockRight(START_BLOCK_RIGHT_EDEFAULT);
        return;
      case SequencePackage.TWO_LIFELINE_MESSAGE__END_BLOCK_RIGHT:
        setEndBlockRight(END_BLOCK_RIGHT_EDEFAULT);
        return;
      case SequencePackage.TWO_LIFELINE_MESSAGE__END_BLOCK_RIGHT_COUNT:
        setEndBlockRightCount(END_BLOCK_RIGHT_COUNT_EDEFAULT);
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
      case SequencePackage.TWO_LIFELINE_MESSAGE__TRANSITION_TYPE:
        return transitionType != TRANSITION_TYPE_EDEFAULT;
      case SequencePackage.TWO_LIFELINE_MESSAGE__CAPTION:
        return CAPTION_EDEFAULT == null ? caption != null : !CAPTION_EDEFAULT.equals(caption);
      case SequencePackage.TWO_LIFELINE_MESSAGE__TARGET_LIFELINE:
        return targetLifeline != null;
      case SequencePackage.TWO_LIFELINE_MESSAGE__START_BLOCK_LEFT:
        return startBlockLeft != START_BLOCK_LEFT_EDEFAULT;
      case SequencePackage.TWO_LIFELINE_MESSAGE__END_BLOCK_LEFT:
        return endBlockLeft != END_BLOCK_LEFT_EDEFAULT;
      case SequencePackage.TWO_LIFELINE_MESSAGE__END_BLOCK_LEFT_COUNT:
        return endBlockLeftCount != END_BLOCK_LEFT_COUNT_EDEFAULT;
      case SequencePackage.TWO_LIFELINE_MESSAGE__START_BLOCK_RIGHT:
        return startBlockRight != START_BLOCK_RIGHT_EDEFAULT;
      case SequencePackage.TWO_LIFELINE_MESSAGE__END_BLOCK_RIGHT:
        return endBlockRight != END_BLOCK_RIGHT_EDEFAULT;
      case SequencePackage.TWO_LIFELINE_MESSAGE__END_BLOCK_RIGHT_COUNT:
        return endBlockRightCount != END_BLOCK_RIGHT_COUNT_EDEFAULT;
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
    result.append(" (transitionType: ");
    result.append(transitionType);
    result.append(", caption: ");
    result.append(caption);
    result.append(", startBlockLeft: ");
    result.append(startBlockLeft);
    result.append(", endBlockLeft: ");
    result.append(endBlockLeft);
    result.append(", endBlockLeftCount: ");
    result.append(endBlockLeftCount);
    result.append(", startBlockRight: ");
    result.append(startBlockRight);
    result.append(", endBlockRight: ");
    result.append(endBlockRight);
    result.append(", endBlockRightCount: ");
    result.append(endBlockRightCount);
    result.append(", sourceNote: ");
    result.append(sourceNote);
    result.append(", targetNote: ");
    result.append(targetNote);
    result.append(')');
    return result.toString();
  }

} //TwoLifelineMessageImpl
