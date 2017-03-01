/**
 */
package de.cau.cs.kieler.grana.text.grana.impl;

import de.cau.cs.kieler.grana.text.grana.GranaPackage;
import de.cau.cs.kieler.grana.text.grana.LocalResource;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Local Resource</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.grana.text.grana.impl.LocalResourceImpl#getPath <em>Path</em>}</li>
 *   <li>{@link de.cau.cs.kieler.grana.text.grana.impl.LocalResourceImpl#getFilter <em>Filter</em>}</li>
 *   <li>{@link de.cau.cs.kieler.grana.text.grana.impl.LocalResourceImpl#isRecurse <em>Recurse</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LocalResourceImpl extends ResourceImpl implements LocalResource
{
  /**
   * The default value of the '{@link #getPath() <em>Path</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPath()
   * @generated
   * @ordered
   */
  protected static final String PATH_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getPath() <em>Path</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPath()
   * @generated
   * @ordered
   */
  protected String path = PATH_EDEFAULT;

  /**
   * The default value of the '{@link #getFilter() <em>Filter</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFilter()
   * @generated
   * @ordered
   */
  protected static final String FILTER_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getFilter() <em>Filter</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFilter()
   * @generated
   * @ordered
   */
  protected String filter = FILTER_EDEFAULT;

  /**
   * The default value of the '{@link #isRecurse() <em>Recurse</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isRecurse()
   * @generated
   * @ordered
   */
  protected static final boolean RECURSE_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isRecurse() <em>Recurse</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isRecurse()
   * @generated
   * @ordered
   */
  protected boolean recurse = RECURSE_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected LocalResourceImpl()
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
    return GranaPackage.Literals.LOCAL_RESOURCE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getPath()
  {
    return path;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPath(String newPath)
  {
    String oldPath = path;
    path = newPath;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GranaPackage.LOCAL_RESOURCE__PATH, oldPath, path));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getFilter()
  {
    return filter;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFilter(String newFilter)
  {
    String oldFilter = filter;
    filter = newFilter;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GranaPackage.LOCAL_RESOURCE__FILTER, oldFilter, filter));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isRecurse()
  {
    return recurse;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRecurse(boolean newRecurse)
  {
    boolean oldRecurse = recurse;
    recurse = newRecurse;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GranaPackage.LOCAL_RESOURCE__RECURSE, oldRecurse, recurse));
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
      case GranaPackage.LOCAL_RESOURCE__PATH:
        return getPath();
      case GranaPackage.LOCAL_RESOURCE__FILTER:
        return getFilter();
      case GranaPackage.LOCAL_RESOURCE__RECURSE:
        return isRecurse();
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
      case GranaPackage.LOCAL_RESOURCE__PATH:
        setPath((String)newValue);
        return;
      case GranaPackage.LOCAL_RESOURCE__FILTER:
        setFilter((String)newValue);
        return;
      case GranaPackage.LOCAL_RESOURCE__RECURSE:
        setRecurse((Boolean)newValue);
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
      case GranaPackage.LOCAL_RESOURCE__PATH:
        setPath(PATH_EDEFAULT);
        return;
      case GranaPackage.LOCAL_RESOURCE__FILTER:
        setFilter(FILTER_EDEFAULT);
        return;
      case GranaPackage.LOCAL_RESOURCE__RECURSE:
        setRecurse(RECURSE_EDEFAULT);
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
      case GranaPackage.LOCAL_RESOURCE__PATH:
        return PATH_EDEFAULT == null ? path != null : !PATH_EDEFAULT.equals(path);
      case GranaPackage.LOCAL_RESOURCE__FILTER:
        return FILTER_EDEFAULT == null ? filter != null : !FILTER_EDEFAULT.equals(filter);
      case GranaPackage.LOCAL_RESOURCE__RECURSE:
        return recurse != RECURSE_EDEFAULT;
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
    result.append(" (path: ");
    result.append(path);
    result.append(", filter: ");
    result.append(filter);
    result.append(", recurse: ");
    result.append(recurse);
    result.append(')');
    return result.toString();
  }

} //LocalResourceImpl
