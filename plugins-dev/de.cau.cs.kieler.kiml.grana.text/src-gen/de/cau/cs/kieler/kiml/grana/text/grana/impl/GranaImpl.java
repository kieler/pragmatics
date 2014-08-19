/**
 */
package de.cau.cs.kieler.kiml.grana.text.grana.impl;

import de.cau.cs.kieler.kiml.grana.text.grana.GlobalResourceRef;
import de.cau.cs.kieler.kiml.grana.text.grana.Grana;
import de.cau.cs.kieler.kiml.grana.text.grana.GranaPackage;
import de.cau.cs.kieler.kiml.grana.text.grana.Job;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Grana</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.kiml.grana.text.grana.impl.GranaImpl#getGlobalResources <em>Global Resources</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kiml.grana.text.grana.impl.GranaImpl#getJobs <em>Jobs</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GranaImpl extends MinimalEObjectImpl.Container implements Grana
{
  /**
   * The cached value of the '{@link #getGlobalResources() <em>Global Resources</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGlobalResources()
   * @generated
   * @ordered
   */
  protected EList<GlobalResourceRef> globalResources;

  /**
   * The cached value of the '{@link #getJobs() <em>Jobs</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getJobs()
   * @generated
   * @ordered
   */
  protected EList<Job> jobs;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected GranaImpl()
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
    return GranaPackage.Literals.GRANA;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<GlobalResourceRef> getGlobalResources()
  {
    if (globalResources == null)
    {
      globalResources = new EObjectContainmentEList<GlobalResourceRef>(GlobalResourceRef.class, this, GranaPackage.GRANA__GLOBAL_RESOURCES);
    }
    return globalResources;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Job> getJobs()
  {
    if (jobs == null)
    {
      jobs = new EObjectContainmentEList<Job>(Job.class, this, GranaPackage.GRANA__JOBS);
    }
    return jobs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case GranaPackage.GRANA__GLOBAL_RESOURCES:
        return ((InternalEList<?>)getGlobalResources()).basicRemove(otherEnd, msgs);
      case GranaPackage.GRANA__JOBS:
        return ((InternalEList<?>)getJobs()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
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
      case GranaPackage.GRANA__GLOBAL_RESOURCES:
        return getGlobalResources();
      case GranaPackage.GRANA__JOBS:
        return getJobs();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case GranaPackage.GRANA__GLOBAL_RESOURCES:
        getGlobalResources().clear();
        getGlobalResources().addAll((Collection<? extends GlobalResourceRef>)newValue);
        return;
      case GranaPackage.GRANA__JOBS:
        getJobs().clear();
        getJobs().addAll((Collection<? extends Job>)newValue);
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
      case GranaPackage.GRANA__GLOBAL_RESOURCES:
        getGlobalResources().clear();
        return;
      case GranaPackage.GRANA__JOBS:
        getJobs().clear();
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
      case GranaPackage.GRANA__GLOBAL_RESOURCES:
        return globalResources != null && !globalResources.isEmpty();
      case GranaPackage.GRANA__JOBS:
        return jobs != null && !jobs.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //GranaImpl
