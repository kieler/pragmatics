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

import de.cau.cs.kieler.core.kgraph.impl.KEdgeImpl;

import de.cau.cs.kieler.keg.Edge;
import de.cau.cs.kieler.keg.EdgeType;
import de.cau.cs.kieler.keg.KEGPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Edge</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.keg.impl.EdgeImpl#getHeadLabel <em>Head Label</em>}</li>
 *   <li>{@link de.cau.cs.kieler.keg.impl.EdgeImpl#getMidLabel <em>Mid Label</em>}</li>
 *   <li>{@link de.cau.cs.kieler.keg.impl.EdgeImpl#getTailLabel <em>Tail Label</em>}</li>
 *   <li>{@link de.cau.cs.kieler.keg.impl.EdgeImpl#isDirected <em>Directed</em>}</li>
 *   <li>{@link de.cau.cs.kieler.keg.impl.EdgeImpl#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EdgeImpl extends KEdgeImpl implements Edge {
    /**
     * The default value of the '{@link #getHeadLabel() <em>Head Label</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getHeadLabel()
     * @generated
     * @ordered
     */
    protected static final String HEAD_LABEL_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getHeadLabel() <em>Head Label</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getHeadLabel()
     * @generated
     * @ordered
     */
    protected String headLabel = HEAD_LABEL_EDEFAULT;

    /**
     * The default value of the '{@link #getMidLabel() <em>Mid Label</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMidLabel()
     * @generated
     * @ordered
     */
    protected static final String MID_LABEL_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getMidLabel() <em>Mid Label</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMidLabel()
     * @generated
     * @ordered
     */
    protected String midLabel = MID_LABEL_EDEFAULT;

    /**
     * The default value of the '{@link #getTailLabel() <em>Tail Label</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTailLabel()
     * @generated
     * @ordered
     */
    protected static final String TAIL_LABEL_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTailLabel() <em>Tail Label</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTailLabel()
     * @generated
     * @ordered
     */
    protected String tailLabel = TAIL_LABEL_EDEFAULT;

    /**
     * The default value of the '{@link #isDirected() <em>Directed</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isDirected()
     * @generated
     * @ordered
     */
    protected static final boolean DIRECTED_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isDirected() <em>Directed</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isDirected()
     * @generated
     * @ordered
     */
    protected boolean directed = DIRECTED_EDEFAULT;

    /**
     * The default value of the '{@link #getType() <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
    protected static final EdgeType TYPE_EDEFAULT = EdgeType.NODE2_NODE;

    /**
     * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
    protected EdgeType type = TYPE_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EdgeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return KEGPackage.Literals.EDGE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getHeadLabel() {
        return headLabel;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setHeadLabel(String newHeadLabel) {
        String oldHeadLabel = headLabel;
        headLabel = newHeadLabel;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KEGPackage.EDGE__HEAD_LABEL, oldHeadLabel, headLabel));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getMidLabel() {
        return midLabel;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setMidLabel(String newMidLabel) {
        String oldMidLabel = midLabel;
        midLabel = newMidLabel;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KEGPackage.EDGE__MID_LABEL, oldMidLabel, midLabel));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getTailLabel() {
        return tailLabel;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTailLabel(String newTailLabel) {
        String oldTailLabel = tailLabel;
        tailLabel = newTailLabel;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KEGPackage.EDGE__TAIL_LABEL, oldTailLabel, tailLabel));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isDirected() {
        return directed;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDirected(boolean newDirected) {
        boolean oldDirected = directed;
        directed = newDirected;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KEGPackage.EDGE__DIRECTED, oldDirected, directed));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EdgeType getType() {
        return type;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setType(EdgeType newType) {
        EdgeType oldType = type;
        type = newType == null ? TYPE_EDEFAULT : newType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KEGPackage.EDGE__TYPE, oldType, type));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case KEGPackage.EDGE__HEAD_LABEL:
                return getHeadLabel();
            case KEGPackage.EDGE__MID_LABEL:
                return getMidLabel();
            case KEGPackage.EDGE__TAIL_LABEL:
                return getTailLabel();
            case KEGPackage.EDGE__DIRECTED:
                return isDirected();
            case KEGPackage.EDGE__TYPE:
                return getType();
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
            case KEGPackage.EDGE__HEAD_LABEL:
                setHeadLabel((String)newValue);
                return;
            case KEGPackage.EDGE__MID_LABEL:
                setMidLabel((String)newValue);
                return;
            case KEGPackage.EDGE__TAIL_LABEL:
                setTailLabel((String)newValue);
                return;
            case KEGPackage.EDGE__DIRECTED:
                setDirected((Boolean)newValue);
                return;
            case KEGPackage.EDGE__TYPE:
                setType((EdgeType)newValue);
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
            case KEGPackage.EDGE__HEAD_LABEL:
                setHeadLabel(HEAD_LABEL_EDEFAULT);
                return;
            case KEGPackage.EDGE__MID_LABEL:
                setMidLabel(MID_LABEL_EDEFAULT);
                return;
            case KEGPackage.EDGE__TAIL_LABEL:
                setTailLabel(TAIL_LABEL_EDEFAULT);
                return;
            case KEGPackage.EDGE__DIRECTED:
                setDirected(DIRECTED_EDEFAULT);
                return;
            case KEGPackage.EDGE__TYPE:
                setType(TYPE_EDEFAULT);
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
            case KEGPackage.EDGE__HEAD_LABEL:
                return HEAD_LABEL_EDEFAULT == null ? headLabel != null : !HEAD_LABEL_EDEFAULT.equals(headLabel);
            case KEGPackage.EDGE__MID_LABEL:
                return MID_LABEL_EDEFAULT == null ? midLabel != null : !MID_LABEL_EDEFAULT.equals(midLabel);
            case KEGPackage.EDGE__TAIL_LABEL:
                return TAIL_LABEL_EDEFAULT == null ? tailLabel != null : !TAIL_LABEL_EDEFAULT.equals(tailLabel);
            case KEGPackage.EDGE__DIRECTED:
                return directed != DIRECTED_EDEFAULT;
            case KEGPackage.EDGE__TYPE:
                return type != TYPE_EDEFAULT;
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
        result.append(" (headLabel: ");
        result.append(headLabel);
        result.append(", midLabel: ");
        result.append(midLabel);
        result.append(", tailLabel: ");
        result.append(tailLabel);
        result.append(", directed: ");
        result.append(directed);
        result.append(", type: ");
        result.append(type);
        result.append(')');
        return result.toString();
    }

} //EdgeImpl
