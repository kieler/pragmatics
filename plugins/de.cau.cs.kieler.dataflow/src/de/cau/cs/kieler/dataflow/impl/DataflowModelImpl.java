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
 *
 * $Id$
 */
package de.cau.cs.kieler.dataflow.impl;

import de.cau.cs.kieler.dataflow.Box;
import de.cau.cs.kieler.dataflow.Connection;
import de.cau.cs.kieler.dataflow.DataflowModel;
import de.cau.cs.kieler.dataflow.DataflowPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.dataflow.impl.DataflowModelImpl#getName <em>Name</em>}</li>
 *   <li>{@link de.cau.cs.kieler.dataflow.impl.DataflowModelImpl#getBoxes <em>Boxes</em>}</li>
 *   <li>{@link de.cau.cs.kieler.dataflow.impl.DataflowModelImpl#getConnections <em>Connections</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DataflowModelImpl extends EObjectImpl implements DataflowModel {
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
     * The cached value of the '{@link #getBoxes() <em>Boxes</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBoxes()
     * @generated
     * @ordered
     */
    protected EList<Box> boxes;

    /**
     * The cached value of the '{@link #getConnections() <em>Connections</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getConnections()
     * @generated
     * @ordered
     */
    protected EList<Connection> connections;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected DataflowModelImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DataflowPackage.Literals.DATAFLOW_MODEL;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DataflowPackage.DATAFLOW_MODEL__NAME, oldName, name));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<Box> getBoxes() {
        if (boxes == null) {
            boxes = new EObjectContainmentEList<Box>(Box.class, this, DataflowPackage.DATAFLOW_MODEL__BOXES);
        }
        return boxes;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<Connection> getConnections() {
        if (connections == null) {
            connections = new EObjectContainmentEList<Connection>(Connection.class, this, DataflowPackage.DATAFLOW_MODEL__CONNECTIONS);
        }
        return connections;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case DataflowPackage.DATAFLOW_MODEL__BOXES:
                return ((InternalEList<?>)getBoxes()).basicRemove(otherEnd, msgs);
            case DataflowPackage.DATAFLOW_MODEL__CONNECTIONS:
                return ((InternalEList<?>)getConnections()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case DataflowPackage.DATAFLOW_MODEL__NAME:
                return getName();
            case DataflowPackage.DATAFLOW_MODEL__BOXES:
                return getBoxes();
            case DataflowPackage.DATAFLOW_MODEL__CONNECTIONS:
                return getConnections();
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
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case DataflowPackage.DATAFLOW_MODEL__NAME:
                setName((String)newValue);
                return;
            case DataflowPackage.DATAFLOW_MODEL__BOXES:
                getBoxes().clear();
                getBoxes().addAll((Collection<? extends Box>)newValue);
                return;
            case DataflowPackage.DATAFLOW_MODEL__CONNECTIONS:
                getConnections().clear();
                getConnections().addAll((Collection<? extends Connection>)newValue);
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
            case DataflowPackage.DATAFLOW_MODEL__NAME:
                setName(NAME_EDEFAULT);
                return;
            case DataflowPackage.DATAFLOW_MODEL__BOXES:
                getBoxes().clear();
                return;
            case DataflowPackage.DATAFLOW_MODEL__CONNECTIONS:
                getConnections().clear();
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
            case DataflowPackage.DATAFLOW_MODEL__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case DataflowPackage.DATAFLOW_MODEL__BOXES:
                return boxes != null && !boxes.isEmpty();
            case DataflowPackage.DATAFLOW_MODEL__CONNECTIONS:
                return connections != null && !connections.isEmpty();
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
        result.append(" (name: ");
        result.append(name);
        result.append(')');
        return result.toString();
    }

} //DataflowModelImpl
