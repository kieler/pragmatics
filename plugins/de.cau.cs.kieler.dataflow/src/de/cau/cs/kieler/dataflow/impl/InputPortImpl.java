/**
 * KIELER - Kiel Integrated Environment for Layout for the Eclipse RCP
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
 *
 * $Id$
 */
package de.cau.cs.kieler.dataflow.impl;

import de.cau.cs.kieler.dataflow.Box;
import de.cau.cs.kieler.dataflow.DataflowPackage;
import de.cau.cs.kieler.dataflow.InputPort;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Input Port</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.dataflow.impl.InputPortImpl#getInputParent <em>Input Parent</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InputPortImpl extends PortImpl implements InputPort {
    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected InputPortImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    protected EClass eStaticClass() {
		return DataflowPackage.Literals.INPUT_PORT;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Box getInputParent() {
		if (eContainerFeatureID() != DataflowPackage.INPUT_PORT__INPUT_PARENT) return null;
		return (Box)eContainer();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetInputParent(Box newInputParent, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newInputParent, DataflowPackage.INPUT_PORT__INPUT_PARENT, msgs);
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setInputParent(Box newInputParent) {
		if (newInputParent != eInternalContainer() || (eContainerFeatureID() != DataflowPackage.INPUT_PORT__INPUT_PARENT && newInputParent != null)) {
			if (EcoreUtil.isAncestor(this, newInputParent))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newInputParent != null)
				msgs = ((InternalEObject)newInputParent).eInverseAdd(this, DataflowPackage.BOX__INPUTS, Box.class, msgs);
			msgs = basicSetInputParent(newInputParent, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DataflowPackage.INPUT_PORT__INPUT_PARENT, newInputParent, newInputParent));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DataflowPackage.INPUT_PORT__INPUT_PARENT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetInputParent((Box)otherEnd, msgs);
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
			case DataflowPackage.INPUT_PORT__INPUT_PARENT:
				return basicSetInputParent(null, msgs);
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
		switch (eContainerFeatureID()) {
			case DataflowPackage.INPUT_PORT__INPUT_PARENT:
				return eInternalContainer().eInverseRemove(this, DataflowPackage.BOX__INPUTS, Box.class, msgs);
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
			case DataflowPackage.INPUT_PORT__INPUT_PARENT:
				return getInputParent();
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
			case DataflowPackage.INPUT_PORT__INPUT_PARENT:
				setInputParent((Box)newValue);
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
			case DataflowPackage.INPUT_PORT__INPUT_PARENT:
				setInputParent((Box)null);
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
			case DataflowPackage.INPUT_PORT__INPUT_PARENT:
				return getInputParent() != null;
		}
		return super.eIsSet(featureID);
	}

} //InputPortImpl
