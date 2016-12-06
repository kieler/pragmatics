/**
 */
package de.cau.cs.kieler.kiml.grana.text.grana.impl;

import de.cau.cs.kieler.kiml.grana.text.grana.GlobalOutputRef;
import de.cau.cs.kieler.kiml.grana.text.grana.GlobalResourceRef;
import de.cau.cs.kieler.kiml.grana.text.grana.Grana;
import de.cau.cs.kieler.kiml.grana.text.grana.GranaPackage;
import de.cau.cs.kieler.kiml.grana.text.grana.Job;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Grana</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.kiml.grana.text.grana.impl.GranaImpl#getGlobalResources <em>Global Resources</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kiml.grana.text.grana.impl.GranaImpl#getGloobalOutputs <em>Gloobal Outputs</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kiml.grana.text.grana.impl.GranaImpl#isParallel <em>Parallel</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kiml.grana.text.grana.impl.GranaImpl#isExecuteAll <em>Execute All</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kiml.grana.text.grana.impl.GranaImpl#getExecute <em>Execute</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kiml.grana.text.grana.impl.GranaImpl#getJobs <em>Jobs</em>}</li>
 * </ul>
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
   * The cached value of the '{@link #getGloobalOutputs() <em>Gloobal Outputs</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGloobalOutputs()
   * @generated
   * @ordered
   */
  protected EList<GlobalOutputRef> gloobalOutputs;

  /**
   * The default value of the '{@link #isParallel() <em>Parallel</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isParallel()
   * @generated
   * @ordered
   */
  protected static final boolean PARALLEL_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isParallel() <em>Parallel</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isParallel()
   * @generated
   * @ordered
   */
  protected boolean parallel = PARALLEL_EDEFAULT;

  /**
   * The default value of the '{@link #isExecuteAll() <em>Execute All</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isExecuteAll()
   * @generated
   * @ordered
   */
  protected static final boolean EXECUTE_ALL_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isExecuteAll() <em>Execute All</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isExecuteAll()
   * @generated
   * @ordered
   */
  protected boolean executeAll = EXECUTE_ALL_EDEFAULT;

  /**
   * The cached value of the '{@link #getExecute() <em>Execute</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExecute()
   * @generated
   * @ordered
   */
  protected EList<Job> execute;

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
  public EList<GlobalOutputRef> getGloobalOutputs()
  {
    if (gloobalOutputs == null)
    {
      gloobalOutputs = new EObjectContainmentEList<GlobalOutputRef>(GlobalOutputRef.class, this, GranaPackage.GRANA__GLOOBAL_OUTPUTS);
    }
    return gloobalOutputs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isParallel()
  {
    return parallel;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setParallel(boolean newParallel)
  {
    boolean oldParallel = parallel;
    parallel = newParallel;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GranaPackage.GRANA__PARALLEL, oldParallel, parallel));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isExecuteAll()
  {
    return executeAll;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setExecuteAll(boolean newExecuteAll)
  {
    boolean oldExecuteAll = executeAll;
    executeAll = newExecuteAll;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GranaPackage.GRANA__EXECUTE_ALL, oldExecuteAll, executeAll));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Job> getExecute()
  {
    if (execute == null)
    {
      execute = new EObjectResolvingEList<Job>(Job.class, this, GranaPackage.GRANA__EXECUTE);
    }
    return execute;
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
      case GranaPackage.GRANA__GLOOBAL_OUTPUTS:
        return ((InternalEList<?>)getGloobalOutputs()).basicRemove(otherEnd, msgs);
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
      case GranaPackage.GRANA__GLOOBAL_OUTPUTS:
        return getGloobalOutputs();
      case GranaPackage.GRANA__PARALLEL:
        return isParallel();
      case GranaPackage.GRANA__EXECUTE_ALL:
        return isExecuteAll();
      case GranaPackage.GRANA__EXECUTE:
        return getExecute();
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
      case GranaPackage.GRANA__GLOOBAL_OUTPUTS:
        getGloobalOutputs().clear();
        getGloobalOutputs().addAll((Collection<? extends GlobalOutputRef>)newValue);
        return;
      case GranaPackage.GRANA__PARALLEL:
        setParallel((Boolean)newValue);
        return;
      case GranaPackage.GRANA__EXECUTE_ALL:
        setExecuteAll((Boolean)newValue);
        return;
      case GranaPackage.GRANA__EXECUTE:
        getExecute().clear();
        getExecute().addAll((Collection<? extends Job>)newValue);
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
      case GranaPackage.GRANA__GLOOBAL_OUTPUTS:
        getGloobalOutputs().clear();
        return;
      case GranaPackage.GRANA__PARALLEL:
        setParallel(PARALLEL_EDEFAULT);
        return;
      case GranaPackage.GRANA__EXECUTE_ALL:
        setExecuteAll(EXECUTE_ALL_EDEFAULT);
        return;
      case GranaPackage.GRANA__EXECUTE:
        getExecute().clear();
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
      case GranaPackage.GRANA__GLOOBAL_OUTPUTS:
        return gloobalOutputs != null && !gloobalOutputs.isEmpty();
      case GranaPackage.GRANA__PARALLEL:
        return parallel != PARALLEL_EDEFAULT;
      case GranaPackage.GRANA__EXECUTE_ALL:
        return executeAll != EXECUTE_ALL_EDEFAULT;
      case GranaPackage.GRANA__EXECUTE:
        return execute != null && !execute.isEmpty();
      case GranaPackage.GRANA__JOBS:
        return jobs != null && !jobs.isEmpty();
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
    result.append(" (parallel: ");
    result.append(parallel);
    result.append(", executeAll: ");
    result.append(executeAll);
    result.append(')');
    return result.toString();
  }

} //GranaImpl
