/**
 */
package de.cau.cs.kieler.grana.text.grana.impl;

import de.cau.cs.kieler.grana.text.grana.GlobalResourceRef;
import de.cau.cs.kieler.grana.text.grana.GranaPackage;
import de.cau.cs.kieler.grana.text.grana.ResourceReference;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Resource Reference</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.grana.text.grana.impl.ResourceReferenceImpl#getResourceRefs <em>Resource Refs</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ResourceReferenceImpl extends ResourceImpl implements ResourceReference
{
  /**
   * The cached value of the '{@link #getResourceRefs() <em>Resource Refs</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getResourceRefs()
   * @generated
   * @ordered
   */
  protected EList<GlobalResourceRef> resourceRefs;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ResourceReferenceImpl()
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
    return GranaPackage.Literals.RESOURCE_REFERENCE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<GlobalResourceRef> getResourceRefs()
  {
    if (resourceRefs == null)
    {
      resourceRefs = new EObjectResolvingEList<GlobalResourceRef>(GlobalResourceRef.class, this, GranaPackage.RESOURCE_REFERENCE__RESOURCE_REFS);
    }
    return resourceRefs;
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
      case GranaPackage.RESOURCE_REFERENCE__RESOURCE_REFS:
        return getResourceRefs();
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
      case GranaPackage.RESOURCE_REFERENCE__RESOURCE_REFS:
        getResourceRefs().clear();
        getResourceRefs().addAll((Collection<? extends GlobalResourceRef>)newValue);
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
      case GranaPackage.RESOURCE_REFERENCE__RESOURCE_REFS:
        getResourceRefs().clear();
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
      case GranaPackage.RESOURCE_REFERENCE__RESOURCE_REFS:
        return resourceRefs != null && !resourceRefs.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //ResourceReferenceImpl
