/**
 */
package de.cau.cs.kieler.kiml.grana.text.grana.impl;

import de.cau.cs.kieler.kiml.grana.text.grana.GlobalOutputRef;
import de.cau.cs.kieler.kiml.grana.text.grana.GranaPackage;
import de.cau.cs.kieler.kiml.grana.text.grana.OutputReference;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Output Reference</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.kiml.grana.text.grana.impl.OutputReferenceImpl#getOutputRef <em>Output Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OutputReferenceImpl extends OutputImpl implements OutputReference
{
  /**
   * The cached value of the '{@link #getOutputRef() <em>Output Ref</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOutputRef()
   * @generated
   * @ordered
   */
  protected GlobalOutputRef outputRef;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected OutputReferenceImpl()
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
    return GranaPackage.Literals.OUTPUT_REFERENCE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GlobalOutputRef getOutputRef()
  {
    if (outputRef != null && outputRef.eIsProxy())
    {
      InternalEObject oldOutputRef = (InternalEObject)outputRef;
      outputRef = (GlobalOutputRef)eResolveProxy(oldOutputRef);
      if (outputRef != oldOutputRef)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, GranaPackage.OUTPUT_REFERENCE__OUTPUT_REF, oldOutputRef, outputRef));
      }
    }
    return outputRef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GlobalOutputRef basicGetOutputRef()
  {
    return outputRef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setOutputRef(GlobalOutputRef newOutputRef)
  {
    GlobalOutputRef oldOutputRef = outputRef;
    outputRef = newOutputRef;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GranaPackage.OUTPUT_REFERENCE__OUTPUT_REF, oldOutputRef, outputRef));
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
      case GranaPackage.OUTPUT_REFERENCE__OUTPUT_REF:
        if (resolve) return getOutputRef();
        return basicGetOutputRef();
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
      case GranaPackage.OUTPUT_REFERENCE__OUTPUT_REF:
        setOutputRef((GlobalOutputRef)newValue);
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
      case GranaPackage.OUTPUT_REFERENCE__OUTPUT_REF:
        setOutputRef((GlobalOutputRef)null);
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
      case GranaPackage.OUTPUT_REFERENCE__OUTPUT_REF:
        return outputRef != null;
    }
    return super.eIsSet(featureID);
  }

} //OutputReferenceImpl
