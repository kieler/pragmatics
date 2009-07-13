/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dataflow.impl;

import dataflow.Connection;
import dataflow.DataflowPackage;
import dataflow.Port;
import dataflow.InputPort;
import dataflow.OutputPort;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Connection</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link dataflow.impl.ConnectionImpl#getSourcePort <em>Source Port</em>}</li>
 *   <li>{@link dataflow.impl.ConnectionImpl#getTargetPort <em>Target Port</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConnectionImpl extends EObjectImpl implements Connection {
	/**
     * The cached value of the '{@link #getSourcePort() <em>Source Port</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getSourcePort()
     * @generated
     * @ordered
     */
	protected Port sourcePort;

	/**
     * The cached value of the '{@link #getTargetPort() <em>Target Port</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getTargetPort()
     * @generated
     * @ordered
     */
	protected Port targetPort;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected ConnectionImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return DataflowPackage.Literals.CONNECTION;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Port getSourcePort() {
        if (sourcePort != null && sourcePort.eIsProxy()) {
            InternalEObject oldSourcePort = (InternalEObject)sourcePort;
            sourcePort = (Port)eResolveProxy(oldSourcePort);
            if (sourcePort != oldSourcePort) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DataflowPackage.CONNECTION__SOURCE_PORT, oldSourcePort, sourcePort));
            }
        }
        return sourcePort;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Port basicGetSourcePort() {
        return sourcePort;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setSourcePort(Port newSourcePort) {
        Port oldSourcePort = sourcePort;
        sourcePort = newSourcePort;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DataflowPackage.CONNECTION__SOURCE_PORT, oldSourcePort, sourcePort));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Port getTargetPort() {
        if (targetPort != null && targetPort.eIsProxy()) {
            InternalEObject oldTargetPort = (InternalEObject)targetPort;
            targetPort = (Port)eResolveProxy(oldTargetPort);
            if (targetPort != oldTargetPort) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DataflowPackage.CONNECTION__TARGET_PORT, oldTargetPort, targetPort));
            }
        }
        return targetPort;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Port basicGetTargetPort() {
        return targetPort;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setTargetPort(Port newTargetPort) {
        Port oldTargetPort = targetPort;
        targetPort = newTargetPort;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DataflowPackage.CONNECTION__TARGET_PORT, oldTargetPort, targetPort));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case DataflowPackage.CONNECTION__SOURCE_PORT:
                if (resolve) return getSourcePort();
                return basicGetSourcePort();
            case DataflowPackage.CONNECTION__TARGET_PORT:
                if (resolve) return getTargetPort();
                return basicGetTargetPort();
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
            case DataflowPackage.CONNECTION__SOURCE_PORT:
                setSourcePort((Port)newValue);
                return;
            case DataflowPackage.CONNECTION__TARGET_PORT:
                setTargetPort((Port)newValue);
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
            case DataflowPackage.CONNECTION__SOURCE_PORT:
                setSourcePort((Port)null);
                return;
            case DataflowPackage.CONNECTION__TARGET_PORT:
                setTargetPort((Port)null);
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
            case DataflowPackage.CONNECTION__SOURCE_PORT:
                return sourcePort != null;
            case DataflowPackage.CONNECTION__TARGET_PORT:
                return targetPort != null;
        }
        return super.eIsSet(featureID);
    }

} //ConnectionImpl
