/**
 * <copyright>
 * </copyright>
 *
 */
package de.cau.cs.kieler.ksbase.featureDefinition.impl;

import de.cau.cs.kieler.ksbase.featureDefinition.FeatureDefinitionPackage;
import de.cau.cs.kieler.ksbase.featureDefinition.FeatureDefinitions;
import de.cau.cs.kieler.ksbase.featureDefinition.FeatureType;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Feature Definitions</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.ksbase.featureDefinition.impl.FeatureDefinitionsImpl#getModelName <em>Model Name</em>}</li>
 *   <li>{@link de.cau.cs.kieler.ksbase.featureDefinition.impl.FeatureDefinitionsImpl#getModelPath <em>Model Path</em>}</li>
 *   <li>{@link de.cau.cs.kieler.ksbase.featureDefinition.impl.FeatureDefinitionsImpl#getFeatureMenuTitle <em>Feature Menu Title</em>}</li>
 *   <li>{@link de.cau.cs.kieler.ksbase.featureDefinition.impl.FeatureDefinitionsImpl#getFeatureFile <em>Feature File</em>}</li>
 *   <li>{@link de.cau.cs.kieler.ksbase.featureDefinition.impl.FeatureDefinitionsImpl#getElements <em>Elements</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FeatureDefinitionsImpl extends MinimalEObjectImpl.Container implements FeatureDefinitions
{
  /**
     * The default value of the '{@link #getModelName() <em>Model Name</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getModelName()
     * @generated
     * @ordered
     */
  protected static final String MODEL_NAME_EDEFAULT = null;

  /**
     * The cached value of the '{@link #getModelName() <em>Model Name</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getModelName()
     * @generated
     * @ordered
     */
  protected String modelName = MODEL_NAME_EDEFAULT;

  /**
     * The default value of the '{@link #getModelPath() <em>Model Path</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getModelPath()
     * @generated
     * @ordered
     */
  protected static final String MODEL_PATH_EDEFAULT = null;

  /**
     * The cached value of the '{@link #getModelPath() <em>Model Path</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getModelPath()
     * @generated
     * @ordered
     */
  protected String modelPath = MODEL_PATH_EDEFAULT;

  /**
     * The default value of the '{@link #getFeatureMenuTitle() <em>Feature Menu Title</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getFeatureMenuTitle()
     * @generated
     * @ordered
     */
  protected static final String FEATURE_MENU_TITLE_EDEFAULT = null;

  /**
     * The cached value of the '{@link #getFeatureMenuTitle() <em>Feature Menu Title</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getFeatureMenuTitle()
     * @generated
     * @ordered
     */
  protected String featureMenuTitle = FEATURE_MENU_TITLE_EDEFAULT;

  /**
     * The default value of the '{@link #getFeatureFile() <em>Feature File</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getFeatureFile()
     * @generated
     * @ordered
     */
  protected static final String FEATURE_FILE_EDEFAULT = null;

  /**
     * The cached value of the '{@link #getFeatureFile() <em>Feature File</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getFeatureFile()
     * @generated
     * @ordered
     */
  protected String featureFile = FEATURE_FILE_EDEFAULT;

  /**
     * The cached value of the '{@link #getElements() <em>Elements</em>}' containment reference list.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getElements()
     * @generated
     * @ordered
     */
  protected EList<FeatureType> elements;

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  protected FeatureDefinitionsImpl()
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
        return FeatureDefinitionPackage.Literals.FEATURE_DEFINITIONS;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public String getModelName()
  {
        return modelName;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void setModelName(String newModelName)
  {
        String oldModelName = modelName;
        modelName = newModelName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FeatureDefinitionPackage.FEATURE_DEFINITIONS__MODEL_NAME, oldModelName, modelName));
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public String getModelPath()
  {
        return modelPath;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void setModelPath(String newModelPath)
  {
        String oldModelPath = modelPath;
        modelPath = newModelPath;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FeatureDefinitionPackage.FEATURE_DEFINITIONS__MODEL_PATH, oldModelPath, modelPath));
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public String getFeatureMenuTitle()
  {
        return featureMenuTitle;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void setFeatureMenuTitle(String newFeatureMenuTitle)
  {
        String oldFeatureMenuTitle = featureMenuTitle;
        featureMenuTitle = newFeatureMenuTitle;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FeatureDefinitionPackage.FEATURE_DEFINITIONS__FEATURE_MENU_TITLE, oldFeatureMenuTitle, featureMenuTitle));
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public String getFeatureFile()
  {
        return featureFile;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void setFeatureFile(String newFeatureFile)
  {
        String oldFeatureFile = featureFile;
        featureFile = newFeatureFile;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FeatureDefinitionPackage.FEATURE_DEFINITIONS__FEATURE_FILE, oldFeatureFile, featureFile));
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EList<FeatureType> getElements()
  {
        if (elements == null) {
            elements = new EObjectContainmentEList<FeatureType>(FeatureType.class, this, FeatureDefinitionPackage.FEATURE_DEFINITIONS__ELEMENTS);
        }
        return elements;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
        switch (featureID) {
            case FeatureDefinitionPackage.FEATURE_DEFINITIONS__ELEMENTS:
                return ((InternalEList<?>)getElements()).basicRemove(otherEnd, msgs);
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
        switch (featureID) {
            case FeatureDefinitionPackage.FEATURE_DEFINITIONS__MODEL_NAME:
                return getModelName();
            case FeatureDefinitionPackage.FEATURE_DEFINITIONS__MODEL_PATH:
                return getModelPath();
            case FeatureDefinitionPackage.FEATURE_DEFINITIONS__FEATURE_MENU_TITLE:
                return getFeatureMenuTitle();
            case FeatureDefinitionPackage.FEATURE_DEFINITIONS__FEATURE_FILE:
                return getFeatureFile();
            case FeatureDefinitionPackage.FEATURE_DEFINITIONS__ELEMENTS:
                return getElements();
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
            case FeatureDefinitionPackage.FEATURE_DEFINITIONS__MODEL_NAME:
                setModelName((String)newValue);
                return;
            case FeatureDefinitionPackage.FEATURE_DEFINITIONS__MODEL_PATH:
                setModelPath((String)newValue);
                return;
            case FeatureDefinitionPackage.FEATURE_DEFINITIONS__FEATURE_MENU_TITLE:
                setFeatureMenuTitle((String)newValue);
                return;
            case FeatureDefinitionPackage.FEATURE_DEFINITIONS__FEATURE_FILE:
                setFeatureFile((String)newValue);
                return;
            case FeatureDefinitionPackage.FEATURE_DEFINITIONS__ELEMENTS:
                getElements().clear();
                getElements().addAll((Collection<? extends FeatureType>)newValue);
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
            case FeatureDefinitionPackage.FEATURE_DEFINITIONS__MODEL_NAME:
                setModelName(MODEL_NAME_EDEFAULT);
                return;
            case FeatureDefinitionPackage.FEATURE_DEFINITIONS__MODEL_PATH:
                setModelPath(MODEL_PATH_EDEFAULT);
                return;
            case FeatureDefinitionPackage.FEATURE_DEFINITIONS__FEATURE_MENU_TITLE:
                setFeatureMenuTitle(FEATURE_MENU_TITLE_EDEFAULT);
                return;
            case FeatureDefinitionPackage.FEATURE_DEFINITIONS__FEATURE_FILE:
                setFeatureFile(FEATURE_FILE_EDEFAULT);
                return;
            case FeatureDefinitionPackage.FEATURE_DEFINITIONS__ELEMENTS:
                getElements().clear();
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
            case FeatureDefinitionPackage.FEATURE_DEFINITIONS__MODEL_NAME:
                return MODEL_NAME_EDEFAULT == null ? modelName != null : !MODEL_NAME_EDEFAULT.equals(modelName);
            case FeatureDefinitionPackage.FEATURE_DEFINITIONS__MODEL_PATH:
                return MODEL_PATH_EDEFAULT == null ? modelPath != null : !MODEL_PATH_EDEFAULT.equals(modelPath);
            case FeatureDefinitionPackage.FEATURE_DEFINITIONS__FEATURE_MENU_TITLE:
                return FEATURE_MENU_TITLE_EDEFAULT == null ? featureMenuTitle != null : !FEATURE_MENU_TITLE_EDEFAULT.equals(featureMenuTitle);
            case FeatureDefinitionPackage.FEATURE_DEFINITIONS__FEATURE_FILE:
                return FEATURE_FILE_EDEFAULT == null ? featureFile != null : !FEATURE_FILE_EDEFAULT.equals(featureFile);
            case FeatureDefinitionPackage.FEATURE_DEFINITIONS__ELEMENTS:
                return elements != null && !elements.isEmpty();
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
        result.append(" (modelName: ");
        result.append(modelName);
        result.append(", modelPath: ");
        result.append(modelPath);
        result.append(", featureMenuTitle: ");
        result.append(featureMenuTitle);
        result.append(", featureFile: ");
        result.append(featureFile);
        result.append(')');
        return result.toString();
    }

} //FeatureDefinitionsImpl
