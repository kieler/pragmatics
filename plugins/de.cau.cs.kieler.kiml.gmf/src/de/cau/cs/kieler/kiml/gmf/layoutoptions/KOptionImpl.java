/**
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.gmf.layoutoptions;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>KOption</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.kiml.gmf.layoutoptions.KOptionImpl#getKey <em>Key</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kiml.gmf.layoutoptions.KOptionImpl#getValue <em>Value</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kiml.gmf.layoutoptions.KOptionImpl#isDefault <em>Default</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class KOptionImpl extends EObjectImpl implements KOption {
    /**
     * The default value of the '{@link #getKey() <em>Key</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getKey()
     * @generated
     * @ordered
     */
    protected static final String KEY_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getKey() <em>Key</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getKey()
     * @generated
     * @ordered
     */
    protected String key = KEY_EDEFAULT;

    /**
     * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getValue()
     * @generated
     * @ordered
     */
    protected static final String VALUE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getValue()
     * @generated
     * @ordered
     */
    protected String value = VALUE_EDEFAULT;

    /**
     * The default value of the '{@link #isDefault() <em>Default</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isDefault()
     * @generated
     * @ordered
     */
    protected static final boolean DEFAULT_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isDefault() <em>Default</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isDefault()
     * @generated
     * @ordered
     */
    protected boolean default_ = DEFAULT_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected KOptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return LayoutOptionsPackage.Literals.KOPTION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getKey() {
        return key;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setKey(String newKey) {
        String oldKey = key;
        key = newKey;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, LayoutOptionsPackage.KOPTION__KEY, oldKey, key));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getValue() {
        return value;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setValue(String newValue) {
        String oldValue = value;
        value = newValue;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, LayoutOptionsPackage.KOPTION__VALUE, oldValue, value));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isDefault() {
        return default_;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDefault(boolean newDefault) {
        boolean oldDefault = default_;
        default_ = newDefault;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, LayoutOptionsPackage.KOPTION__DEFAULT, oldDefault, default_));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case LayoutOptionsPackage.KOPTION__KEY:
                return getKey();
            case LayoutOptionsPackage.KOPTION__VALUE:
                return getValue();
            case LayoutOptionsPackage.KOPTION__DEFAULT:
                return isDefault();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case LayoutOptionsPackage.KOPTION__KEY:
                setKey((String)newValue);
                return;
            case LayoutOptionsPackage.KOPTION__VALUE:
                setValue((String)newValue);
                return;
            case LayoutOptionsPackage.KOPTION__DEFAULT:
                setDefault((Boolean)newValue);
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
    public void eUnset(int featureID) {
        switch (featureID) {
            case LayoutOptionsPackage.KOPTION__KEY:
                setKey(KEY_EDEFAULT);
                return;
            case LayoutOptionsPackage.KOPTION__VALUE:
                setValue(VALUE_EDEFAULT);
                return;
            case LayoutOptionsPackage.KOPTION__DEFAULT:
                setDefault(DEFAULT_EDEFAULT);
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
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case LayoutOptionsPackage.KOPTION__KEY:
                return KEY_EDEFAULT == null ? key != null : !KEY_EDEFAULT.equals(key);
            case LayoutOptionsPackage.KOPTION__VALUE:
                return VALUE_EDEFAULT == null ? value != null : !VALUE_EDEFAULT.equals(value);
            case LayoutOptionsPackage.KOPTION__DEFAULT:
                return default_ != DEFAULT_EDEFAULT;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (key: ");
        result.append(key);
        result.append(", value: ");
        result.append(value);
        result.append(", default: ");
        result.append(default_);
        result.append(')');
        return result.toString();
    }

} //KOptionImpl
