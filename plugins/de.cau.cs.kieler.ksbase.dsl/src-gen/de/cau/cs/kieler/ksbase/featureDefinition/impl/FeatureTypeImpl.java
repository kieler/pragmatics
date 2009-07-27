/**
 * <copyright>
 * </copyright>
 *
 */
package de.cau.cs.kieler.ksbase.featureDefinition.impl;

import de.cau.cs.kieler.ksbase.featureDefinition.FeatureDefinitionPackage;
import de.cau.cs.kieler.ksbase.featureDefinition.FeatureType;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Feature Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.ksbase.featureDefinition.impl.FeatureTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link de.cau.cs.kieler.ksbase.featureDefinition.impl.FeatureTypeImpl#getFileName <em>File Name</em>}</li>
 *   <li>{@link de.cau.cs.kieler.ksbase.featureDefinition.impl.FeatureTypeImpl#getMethodName <em>Method Name</em>}</li>
 *   <li>{@link de.cau.cs.kieler.ksbase.featureDefinition.impl.FeatureTypeImpl#getNumParameter <em>Num Parameter</em>}</li>
 *   <li>{@link de.cau.cs.kieler.ksbase.featureDefinition.impl.FeatureTypeImpl#getParameter <em>Parameter</em>}</li>
 *   <li>{@link de.cau.cs.kieler.ksbase.featureDefinition.impl.FeatureTypeImpl#getMenuEntry <em>Menu Entry</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FeatureTypeImpl extends MinimalEObjectImpl.Container implements FeatureType
{
  /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
  protected static final String NAME_EDEFAULT = null;

  /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
  protected String name = NAME_EDEFAULT;

  /**
     * The default value of the '{@link #getFileName() <em>File Name</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getFileName()
     * @generated
     * @ordered
     */
  protected static final String FILE_NAME_EDEFAULT = null;

  /**
     * The cached value of the '{@link #getFileName() <em>File Name</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getFileName()
     * @generated
     * @ordered
     */
  protected String fileName = FILE_NAME_EDEFAULT;

  /**
     * The default value of the '{@link #getMethodName() <em>Method Name</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getMethodName()
     * @generated
     * @ordered
     */
  protected static final String METHOD_NAME_EDEFAULT = null;

  /**
     * The cached value of the '{@link #getMethodName() <em>Method Name</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getMethodName()
     * @generated
     * @ordered
     */
  protected String methodName = METHOD_NAME_EDEFAULT;

  /**
     * The cached value of the '{@link #getNumParameter() <em>Num Parameter</em>}' attribute list.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getNumParameter()
     * @generated
     * @ordered
     */
  protected EList<Integer> numParameter;

  /**
     * The cached value of the '{@link #getParameter() <em>Parameter</em>}' attribute list.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getParameter()
     * @generated
     * @ordered
     */
  protected EList<String> parameter;

  /**
     * The default value of the '{@link #getMenuEntry() <em>Menu Entry</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getMenuEntry()
     * @generated
     * @ordered
     */
  protected static final String MENU_ENTRY_EDEFAULT = null;

  /**
     * The cached value of the '{@link #getMenuEntry() <em>Menu Entry</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getMenuEntry()
     * @generated
     * @ordered
     */
  protected String menuEntry = MENU_ENTRY_EDEFAULT;

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  protected FeatureTypeImpl()
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
        return FeatureDefinitionPackage.Literals.FEATURE_TYPE;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public String getName()
  {
        return name;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void setName(String newName)
  {
        String oldName = name;
        name = newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FeatureDefinitionPackage.FEATURE_TYPE__NAME, oldName, name));
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public String getFileName()
  {
        return fileName;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void setFileName(String newFileName)
  {
        String oldFileName = fileName;
        fileName = newFileName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FeatureDefinitionPackage.FEATURE_TYPE__FILE_NAME, oldFileName, fileName));
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public String getMethodName()
  {
        return methodName;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void setMethodName(String newMethodName)
  {
        String oldMethodName = methodName;
        methodName = newMethodName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FeatureDefinitionPackage.FEATURE_TYPE__METHOD_NAME, oldMethodName, methodName));
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EList<Integer> getNumParameter()
  {
        if (numParameter == null) {
            numParameter = new EDataTypeEList<Integer>(Integer.class, this, FeatureDefinitionPackage.FEATURE_TYPE__NUM_PARAMETER);
        }
        return numParameter;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EList<String> getParameter()
  {
        if (parameter == null) {
            parameter = new EDataTypeEList<String>(String.class, this, FeatureDefinitionPackage.FEATURE_TYPE__PARAMETER);
        }
        return parameter;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public String getMenuEntry()
  {
        return menuEntry;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void setMenuEntry(String newMenuEntry)
  {
        String oldMenuEntry = menuEntry;
        menuEntry = newMenuEntry;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FeatureDefinitionPackage.FEATURE_TYPE__MENU_ENTRY, oldMenuEntry, menuEntry));
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
        switch (featureID) {
            case FeatureDefinitionPackage.FEATURE_TYPE__NAME:
                return getName();
            case FeatureDefinitionPackage.FEATURE_TYPE__FILE_NAME:
                return getFileName();
            case FeatureDefinitionPackage.FEATURE_TYPE__METHOD_NAME:
                return getMethodName();
            case FeatureDefinitionPackage.FEATURE_TYPE__NUM_PARAMETER:
                return getNumParameter();
            case FeatureDefinitionPackage.FEATURE_TYPE__PARAMETER:
                return getParameter();
            case FeatureDefinitionPackage.FEATURE_TYPE__MENU_ENTRY:
                return getMenuEntry();
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
        switch (featureID) {
            case FeatureDefinitionPackage.FEATURE_TYPE__NAME:
                setName((String)newValue);
                return;
            case FeatureDefinitionPackage.FEATURE_TYPE__FILE_NAME:
                setFileName((String)newValue);
                return;
            case FeatureDefinitionPackage.FEATURE_TYPE__METHOD_NAME:
                setMethodName((String)newValue);
                return;
            case FeatureDefinitionPackage.FEATURE_TYPE__NUM_PARAMETER:
                getNumParameter().clear();
                getNumParameter().addAll((Collection<? extends Integer>)newValue);
                return;
            case FeatureDefinitionPackage.FEATURE_TYPE__PARAMETER:
                getParameter().clear();
                getParameter().addAll((Collection<? extends String>)newValue);
                return;
            case FeatureDefinitionPackage.FEATURE_TYPE__MENU_ENTRY:
                setMenuEntry((String)newValue);
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
        switch (featureID) {
            case FeatureDefinitionPackage.FEATURE_TYPE__NAME:
                setName(NAME_EDEFAULT);
                return;
            case FeatureDefinitionPackage.FEATURE_TYPE__FILE_NAME:
                setFileName(FILE_NAME_EDEFAULT);
                return;
            case FeatureDefinitionPackage.FEATURE_TYPE__METHOD_NAME:
                setMethodName(METHOD_NAME_EDEFAULT);
                return;
            case FeatureDefinitionPackage.FEATURE_TYPE__NUM_PARAMETER:
                getNumParameter().clear();
                return;
            case FeatureDefinitionPackage.FEATURE_TYPE__PARAMETER:
                getParameter().clear();
                return;
            case FeatureDefinitionPackage.FEATURE_TYPE__MENU_ENTRY:
                setMenuEntry(MENU_ENTRY_EDEFAULT);
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
        switch (featureID) {
            case FeatureDefinitionPackage.FEATURE_TYPE__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case FeatureDefinitionPackage.FEATURE_TYPE__FILE_NAME:
                return FILE_NAME_EDEFAULT == null ? fileName != null : !FILE_NAME_EDEFAULT.equals(fileName);
            case FeatureDefinitionPackage.FEATURE_TYPE__METHOD_NAME:
                return METHOD_NAME_EDEFAULT == null ? methodName != null : !METHOD_NAME_EDEFAULT.equals(methodName);
            case FeatureDefinitionPackage.FEATURE_TYPE__NUM_PARAMETER:
                return numParameter != null && !numParameter.isEmpty();
            case FeatureDefinitionPackage.FEATURE_TYPE__PARAMETER:
                return parameter != null && !parameter.isEmpty();
            case FeatureDefinitionPackage.FEATURE_TYPE__MENU_ENTRY:
                return MENU_ENTRY_EDEFAULT == null ? menuEntry != null : !MENU_ENTRY_EDEFAULT.equals(menuEntry);
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
        result.append(" (name: ");
        result.append(name);
        result.append(", fileName: ");
        result.append(fileName);
        result.append(", methodName: ");
        result.append(methodName);
        result.append(", numParameter: ");
        result.append(numParameter);
        result.append(", parameter: ");
        result.append(parameter);
        result.append(", menuEntry: ");
        result.append(menuEntry);
        result.append(')');
        return result.toString();
    }

} //FeatureTypeImpl
