/**
 */
package de.cau.cs.kieler.uml.sequence.text.sequence.impl;

import de.cau.cs.kieler.uml.sequence.text.sequence.Destroy;
import de.cau.cs.kieler.uml.sequence.text.sequence.Lifeline;
import de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Destroy</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.DestroyImpl#getLifeline <em>Lifeline</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.DestroyImpl#isDestroy <em>Destroy</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DestroyImpl extends InteractionImpl implements Destroy
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
   * The default value of the '{@link #isDestroy() <em>Destroy</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isDestroy()
   * @generated
   * @ordered
   */
  protected static final boolean DESTROY_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isDestroy() <em>Destroy</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isDestroy()
   * @generated
   * @ordered
   */
  protected boolean destroy = DESTROY_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected DestroyImpl()
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
    return SequencePackage.Literals.DESTROY;
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
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, SequencePackage.DESTROY__LIFELINE, oldLifeline, lifeline));
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
      eNotify(new ENotificationImpl(this, Notification.SET, SequencePackage.DESTROY__LIFELINE, oldLifeline, lifeline));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isDestroy()
  {
    return destroy;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDestroy(boolean newDestroy)
  {
    boolean oldDestroy = destroy;
    destroy = newDestroy;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SequencePackage.DESTROY__DESTROY, oldDestroy, destroy));
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
      case SequencePackage.DESTROY__LIFELINE:
        if (resolve) return getLifeline();
        return basicGetLifeline();
      case SequencePackage.DESTROY__DESTROY:
        return isDestroy();
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
      case SequencePackage.DESTROY__LIFELINE:
        setLifeline((Lifeline)newValue);
        return;
      case SequencePackage.DESTROY__DESTROY:
        setDestroy((Boolean)newValue);
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
      case SequencePackage.DESTROY__LIFELINE:
        setLifeline((Lifeline)null);
        return;
      case SequencePackage.DESTROY__DESTROY:
        setDestroy(DESTROY_EDEFAULT);
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
      case SequencePackage.DESTROY__LIFELINE:
        return lifeline != null;
      case SequencePackage.DESTROY__DESTROY:
        return destroy != DESTROY_EDEFAULT;
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
    result.append(" (destroy: ");
    result.append(destroy);
    result.append(')');
    return result.toString();
  }

} //DestroyImpl
