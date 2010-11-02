/**
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 *
 * $Id$
 */
package de.cau.cs.kieler.keg.impl;

import de.cau.cs.kieler.core.kgraph.impl.KPortImpl;

import de.cau.cs.kieler.keg.GraphsPackage;
import de.cau.cs.kieler.keg.Port;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Port</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.keg.impl.PortImpl#getPortLabel <em>Port Label</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PortImpl extends KPortImpl implements Port {
    /**
     * The default value of the '{@link #getPortLabel() <em>Port Label</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPortLabel()
     * @generated
     * @ordered
     */
    protected static final String PORT_LABEL_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getPortLabel() <em>Port Label</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPortLabel()
     * @generated
     * @ordered
     */
    protected String portLabel = PORT_LABEL_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected PortImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return GraphsPackage.Literals.PORT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getPortLabel() {
        return portLabel;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPortLabel(String newPortLabel) {
        String oldPortLabel = portLabel;
        portLabel = newPortLabel;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GraphsPackage.PORT__PORT_LABEL, oldPortLabel, portLabel));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case GraphsPackage.PORT__PORT_LABEL:
                return getPortLabel();
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
            case GraphsPackage.PORT__PORT_LABEL:
                setPortLabel((String)newValue);
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
            case GraphsPackage.PORT__PORT_LABEL:
                setPortLabel(PORT_LABEL_EDEFAULT);
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
            case GraphsPackage.PORT__PORT_LABEL:
                return PORT_LABEL_EDEFAULT == null ? portLabel != null : !PORT_LABEL_EDEFAULT.equals(portLabel);
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
        result.append(" (portLabel: ");
        result.append(portLabel);
        result.append(')');
        return result.toString();
    }

} //PortImpl
