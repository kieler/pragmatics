/**
 */
package de.cau.cs.kieler.uml.sequence.text.sequence.impl;

import de.cau.cs.kieler.uml.sequence.text.sequence.Lifeline;
import de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineNote;
import de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>One Lifeline Note</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.OneLifelineNoteImpl#getLifeline <em>Lifeline</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.OneLifelineNoteImpl#getNote <em>Note</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OneLifelineNoteImpl extends InteractionImpl implements OneLifelineNote
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
  protected OneLifelineNoteImpl()
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
    return SequencePackage.Literals.ONE_LIFELINE_NOTE;
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
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, SequencePackage.ONE_LIFELINE_NOTE__LIFELINE, oldLifeline, lifeline));
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
      eNotify(new ENotificationImpl(this, Notification.SET, SequencePackage.ONE_LIFELINE_NOTE__LIFELINE, oldLifeline, lifeline));
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
      eNotify(new ENotificationImpl(this, Notification.SET, SequencePackage.ONE_LIFELINE_NOTE__NOTE, oldNote, note));
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
      case SequencePackage.ONE_LIFELINE_NOTE__LIFELINE:
        if (resolve) return getLifeline();
        return basicGetLifeline();
      case SequencePackage.ONE_LIFELINE_NOTE__NOTE:
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
      case SequencePackage.ONE_LIFELINE_NOTE__LIFELINE:
        setLifeline((Lifeline)newValue);
        return;
      case SequencePackage.ONE_LIFELINE_NOTE__NOTE:
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
      case SequencePackage.ONE_LIFELINE_NOTE__LIFELINE:
        setLifeline((Lifeline)null);
        return;
      case SequencePackage.ONE_LIFELINE_NOTE__NOTE:
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
      case SequencePackage.ONE_LIFELINE_NOTE__LIFELINE:
        return lifeline != null;
      case SequencePackage.ONE_LIFELINE_NOTE__NOTE:
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
    result.append(" (note: ");
    result.append(note);
    result.append(')');
    return result.toString();
  }

} //OneLifelineNoteImpl
