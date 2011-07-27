/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.cs.kieler.kwebs.servicedata.impl;

import de.cau.cs.kieler.kwebs.servicedata.ServiceDataPackage;
import de.cau.cs.kieler.kwebs.servicedata.SupportedDiagram;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Supported Diagram</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.kwebs.servicedata.impl.SupportedDiagramImpl#getType <em>Type</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.servicedata.impl.SupportedDiagramImpl#getPriority <em>Priority</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SupportedDiagramImpl extends EObjectImpl implements SupportedDiagram {
    /**
     * The default value of the '{@link #getType() <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
    protected static final String TYPE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
    protected String type = TYPE_EDEFAULT;

    /**
     * The default value of the '{@link #getPriority() <em>Priority</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPriority()
     * @generated
     * @ordered
     */
    protected static final int PRIORITY_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getPriority() <em>Priority</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPriority()
     * @generated
     * @ordered
     */
    protected int priority = PRIORITY_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected SupportedDiagramImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ServiceDataPackage.Literals.SUPPORTED_DIAGRAM;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getType() {
        return type;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setType(String newType) {
        String oldType = type;
        type = newType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ServiceDataPackage.SUPPORTED_DIAGRAM__TYPE, oldType, type));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getPriority() {
        return priority;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPriority(int newPriority) {
        int oldPriority = priority;
        priority = newPriority;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ServiceDataPackage.SUPPORTED_DIAGRAM__PRIORITY, oldPriority, priority));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ServiceDataPackage.SUPPORTED_DIAGRAM__TYPE:
                return getType();
            case ServiceDataPackage.SUPPORTED_DIAGRAM__PRIORITY:
                return getPriority();
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
            case ServiceDataPackage.SUPPORTED_DIAGRAM__TYPE:
                setType((String)newValue);
                return;
            case ServiceDataPackage.SUPPORTED_DIAGRAM__PRIORITY:
                setPriority((Integer)newValue);
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
            case ServiceDataPackage.SUPPORTED_DIAGRAM__TYPE:
                setType(TYPE_EDEFAULT);
                return;
            case ServiceDataPackage.SUPPORTED_DIAGRAM__PRIORITY:
                setPriority(PRIORITY_EDEFAULT);
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
            case ServiceDataPackage.SUPPORTED_DIAGRAM__TYPE:
                return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
            case ServiceDataPackage.SUPPORTED_DIAGRAM__PRIORITY:
                return priority != PRIORITY_EDEFAULT;
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
        result.append(" (type: ");
        result.append(type);
        result.append(", priority: ");
        result.append(priority);
        result.append(')');
        return result.toString();
    }

} //SupportedDiagramImpl
