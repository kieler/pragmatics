/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dataflow.impl;

import dataflow.Box;
import dataflow.DataflowPackage;
import dataflow.OutputPort;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Output Port</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link dataflow.impl.OutputPortImpl#getOutputParent <em>Output Parent</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OutputPortImpl extends PortImpl implements OutputPort {
	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected OutputPortImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return DataflowPackage.Literals.OUTPUT_PORT;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Box getOutputParent() {
        if (eContainerFeatureID != DataflowPackage.OUTPUT_PORT__OUTPUT_PARENT) return null;
        return (Box)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetOutputParent(Box newOutputParent, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newOutputParent, DataflowPackage.OUTPUT_PORT__OUTPUT_PARENT, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setOutputParent(Box newOutputParent) {
        if (newOutputParent != eInternalContainer() || (eContainerFeatureID != DataflowPackage.OUTPUT_PORT__OUTPUT_PARENT && newOutputParent != null)) {
            if (EcoreUtil.isAncestor(this, newOutputParent))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newOutputParent != null)
                msgs = ((InternalEObject)newOutputParent).eInverseAdd(this, DataflowPackage.BOX__OUTPUTS, Box.class, msgs);
            msgs = basicSetOutputParent(newOutputParent, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DataflowPackage.OUTPUT_PORT__OUTPUT_PARENT, newOutputParent, newOutputParent));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case DataflowPackage.OUTPUT_PORT__OUTPUT_PARENT:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetOutputParent((Box)otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case DataflowPackage.OUTPUT_PORT__OUTPUT_PARENT:
                return basicSetOutputParent(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
        switch (eContainerFeatureID) {
            case DataflowPackage.OUTPUT_PORT__OUTPUT_PARENT:
                return eInternalContainer().eInverseRemove(this, DataflowPackage.BOX__OUTPUTS, Box.class, msgs);
        }
        return super.eBasicRemoveFromContainerFeature(msgs);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case DataflowPackage.OUTPUT_PORT__OUTPUT_PARENT:
                return getOutputParent();
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
            case DataflowPackage.OUTPUT_PORT__OUTPUT_PARENT:
                setOutputParent((Box)newValue);
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
            case DataflowPackage.OUTPUT_PORT__OUTPUT_PARENT:
                setOutputParent((Box)null);
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
            case DataflowPackage.OUTPUT_PORT__OUTPUT_PARENT:
                return getOutputParent() != null;
        }
        return super.eIsSet(featureID);
    }

} //OutputPortImpl
