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
 */
package de.cau.cs.kieler.keg.impl;

import de.cau.cs.kieler.core.kgraph.impl.KNodeImpl;

import de.cau.cs.kieler.keg.KEGPackage;
import de.cau.cs.kieler.keg.Node;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.keg.impl.NodeImpl#getNodeLabel <em>Node Label</em>}</li>
 *   <li>{@link de.cau.cs.kieler.keg.impl.NodeImpl#isIsHypernode <em>Is Hypernode</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NodeImpl extends KNodeImpl implements Node {
    /**
     * The default value of the '{@link #getNodeLabel() <em>Node Label</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNodeLabel()
     * @generated
     * @ordered
     */
    protected static final String NODE_LABEL_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getNodeLabel() <em>Node Label</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNodeLabel()
     * @generated
     * @ordered
     */
    protected String nodeLabel = NODE_LABEL_EDEFAULT;

    /**
     * The default value of the '{@link #isIsHypernode() <em>Is Hypernode</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isIsHypernode()
     * @generated
     * @ordered
     */
    protected static final boolean IS_HYPERNODE_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isIsHypernode() <em>Is Hypernode</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isIsHypernode()
     * @generated
     * @ordered
     */
    protected boolean isHypernode = IS_HYPERNODE_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected NodeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return KEGPackage.Literals.NODE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getNodeLabel() {
        return nodeLabel;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setNodeLabel(String newNodeLabel) {
        String oldNodeLabel = nodeLabel;
        nodeLabel = newNodeLabel;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KEGPackage.NODE__NODE_LABEL, oldNodeLabel, nodeLabel));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isIsHypernode() {
        return isHypernode;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setIsHypernode(boolean newIsHypernode) {
        boolean oldIsHypernode = isHypernode;
        isHypernode = newIsHypernode;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KEGPackage.NODE__IS_HYPERNODE, oldIsHypernode, isHypernode));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case KEGPackage.NODE__NODE_LABEL:
                return getNodeLabel();
            case KEGPackage.NODE__IS_HYPERNODE:
                return isIsHypernode();
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
            case KEGPackage.NODE__NODE_LABEL:
                setNodeLabel((String)newValue);
                return;
            case KEGPackage.NODE__IS_HYPERNODE:
                setIsHypernode((Boolean)newValue);
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
            case KEGPackage.NODE__NODE_LABEL:
                setNodeLabel(NODE_LABEL_EDEFAULT);
                return;
            case KEGPackage.NODE__IS_HYPERNODE:
                setIsHypernode(IS_HYPERNODE_EDEFAULT);
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
            case KEGPackage.NODE__NODE_LABEL:
                return NODE_LABEL_EDEFAULT == null ? nodeLabel != null : !NODE_LABEL_EDEFAULT.equals(nodeLabel);
            case KEGPackage.NODE__IS_HYPERNODE:
                return isHypernode != IS_HYPERNODE_EDEFAULT;
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
        result.append(" (nodeLabel: ");
        result.append(nodeLabel);
        result.append(", isHypernode: ");
        result.append(isHypernode);
        result.append(')');
        return result.toString();
    }

} //NodeImpl
