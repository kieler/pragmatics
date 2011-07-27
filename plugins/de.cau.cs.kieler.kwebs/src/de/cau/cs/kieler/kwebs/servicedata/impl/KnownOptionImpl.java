/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.cs.kieler.kwebs.servicedata.impl;

import de.cau.cs.kieler.kwebs.servicedata.KnownOption;
import de.cau.cs.kieler.kwebs.servicedata.LayoutOption;
import de.cau.cs.kieler.kwebs.servicedata.ServiceDataPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Known Option</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.kwebs.servicedata.impl.KnownOptionImpl#getDefault <em>Default</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.servicedata.impl.KnownOptionImpl#getOption <em>Option</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class KnownOptionImpl extends EObjectImpl implements KnownOption {
    /**
     * The default value of the '{@link #getDefault() <em>Default</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDefault()
     * @generated
     * @ordered
     */
    protected static final String DEFAULT_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDefault() <em>Default</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDefault()
     * @generated
     * @ordered
     */
    protected String default_ = DEFAULT_EDEFAULT;

    /**
     * The cached value of the '{@link #getOption() <em>Option</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOption()
     * @generated
     * @ordered
     */
    protected LayoutOption option;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected KnownOptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ServiceDataPackage.Literals.KNOWN_OPTION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getDefault() {
        return default_;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDefault(String newDefault) {
        String oldDefault = default_;
        default_ = newDefault;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ServiceDataPackage.KNOWN_OPTION__DEFAULT, oldDefault, default_));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LayoutOption getOption() {
        if (option != null && option.eIsProxy()) {
            InternalEObject oldOption = (InternalEObject)option;
            option = (LayoutOption)eResolveProxy(oldOption);
            if (option != oldOption) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ServiceDataPackage.KNOWN_OPTION__OPTION, oldOption, option));
            }
        }
        return option;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LayoutOption basicGetOption() {
        return option;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setOption(LayoutOption newOption) {
        LayoutOption oldOption = option;
        option = newOption;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ServiceDataPackage.KNOWN_OPTION__OPTION, oldOption, option));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ServiceDataPackage.KNOWN_OPTION__DEFAULT:
                return getDefault();
            case ServiceDataPackage.KNOWN_OPTION__OPTION:
                if (resolve) return getOption();
                return basicGetOption();
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
            case ServiceDataPackage.KNOWN_OPTION__DEFAULT:
                setDefault((String)newValue);
                return;
            case ServiceDataPackage.KNOWN_OPTION__OPTION:
                setOption((LayoutOption)newValue);
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
            case ServiceDataPackage.KNOWN_OPTION__DEFAULT:
                setDefault(DEFAULT_EDEFAULT);
                return;
            case ServiceDataPackage.KNOWN_OPTION__OPTION:
                setOption((LayoutOption)null);
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
            case ServiceDataPackage.KNOWN_OPTION__DEFAULT:
                return DEFAULT_EDEFAULT == null ? default_ != null : !DEFAULT_EDEFAULT.equals(default_);
            case ServiceDataPackage.KNOWN_OPTION__OPTION:
                return option != null;
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
        result.append(" (default: ");
        result.append(default_);
        result.append(')');
        return result.toString();
    }

} //KnownOptionImpl
