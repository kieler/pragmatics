/**
 */
package de.cau.cs.kieler.uml.sequence.text.sequence.impl;

import de.cau.cs.kieler.uml.sequence.text.sequence.Lifeline;
import de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineEndBlock;
import de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>One Lifeline End Block</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.OneLifelineEndBlockImpl#getLifeline <em>Lifeline</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.OneLifelineEndBlockImpl#getEndBlockCount <em>End Block Count</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OneLifelineEndBlockImpl extends InteractionImpl implements OneLifelineEndBlock
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
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected OneLifelineEndBlockImpl()
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
    return SequencePackage.Literals.ONE_LIFELINE_END_BLOCK;
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
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, SequencePackage.ONE_LIFELINE_END_BLOCK__LIFELINE, oldLifeline, lifeline));
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
      eNotify(new ENotificationImpl(this, Notification.SET, SequencePackage.ONE_LIFELINE_END_BLOCK__LIFELINE, oldLifeline, lifeline));
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
      eNotify(new ENotificationImpl(this, Notification.SET, SequencePackage.ONE_LIFELINE_END_BLOCK__END_BLOCK_COUNT, oldEndBlockCount, endBlockCount));
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
      case SequencePackage.ONE_LIFELINE_END_BLOCK__LIFELINE:
        if (resolve) return getLifeline();
        return basicGetLifeline();
      case SequencePackage.ONE_LIFELINE_END_BLOCK__END_BLOCK_COUNT:
        return getEndBlockCount();
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
      case SequencePackage.ONE_LIFELINE_END_BLOCK__LIFELINE:
        setLifeline((Lifeline)newValue);
        return;
      case SequencePackage.ONE_LIFELINE_END_BLOCK__END_BLOCK_COUNT:
        setEndBlockCount((Integer)newValue);
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
      case SequencePackage.ONE_LIFELINE_END_BLOCK__LIFELINE:
        setLifeline((Lifeline)null);
        return;
      case SequencePackage.ONE_LIFELINE_END_BLOCK__END_BLOCK_COUNT:
        setEndBlockCount(END_BLOCK_COUNT_EDEFAULT);
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
      case SequencePackage.ONE_LIFELINE_END_BLOCK__LIFELINE:
        return lifeline != null;
      case SequencePackage.ONE_LIFELINE_END_BLOCK__END_BLOCK_COUNT:
        return endBlockCount != END_BLOCK_COUNT_EDEFAULT;
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
    result.append(" (endBlockCount: ");
    result.append(endBlockCount);
    result.append(')');
    return result.toString();
  }

} //OneLifelineEndBlockImpl
