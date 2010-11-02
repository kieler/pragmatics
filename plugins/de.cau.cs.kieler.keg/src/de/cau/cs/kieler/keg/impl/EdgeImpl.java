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

import de.cau.cs.kieler.core.kgraph.impl.KEdgeImpl;

import de.cau.cs.kieler.keg.Edge;
import de.cau.cs.kieler.keg.EdgeType;
import de.cau.cs.kieler.keg.GraphsPackage;

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
 *   <li>{@link de.cau.cs.kieler.keg.impl.EdgeImpl#getHeadLabel1 <em>Head Label1</em>}</li>
 *   <li>{@link de.cau.cs.kieler.keg.impl.EdgeImpl#getHeadLabel2 <em>Head Label2</em>}</li>
 *   <li>{@link de.cau.cs.kieler.keg.impl.EdgeImpl#getMidLabel <em>Mid Label</em>}</li>
 *   <li>{@link de.cau.cs.kieler.keg.impl.EdgeImpl#getTailLabel1 <em>Tail Label1</em>}</li>
 *   <li>{@link de.cau.cs.kieler.keg.impl.EdgeImpl#getTailLabel2 <em>Tail Label2</em>}</li>
 *   <li>{@link de.cau.cs.kieler.keg.impl.EdgeImpl#isIsDirected <em>Is Directed</em>}</li>
 *   <li>{@link de.cau.cs.kieler.keg.impl.EdgeImpl#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EdgeImpl extends KEdgeImpl implements Edge {
    /**
     * The default value of the '{@link #getHeadLabel1() <em>Head Label1</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getHeadLabel1()
     * @generated
     * @ordered
     */
    protected static final String HEAD_LABEL1_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getHeadLabel1() <em>Head Label1</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getHeadLabel1()
     * @generated
     * @ordered
     */
    protected String headLabel1 = HEAD_LABEL1_EDEFAULT;

    /**
     * The default value of the '{@link #getHeadLabel2() <em>Head Label2</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getHeadLabel2()
     * @generated
     * @ordered
     */
    protected static final String HEAD_LABEL2_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getHeadLabel2() <em>Head Label2</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getHeadLabel2()
     * @generated
     * @ordered
     */
    protected String headLabel2 = HEAD_LABEL2_EDEFAULT;

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
     * The default value of the '{@link #getTailLabel1() <em>Tail Label1</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTailLabel1()
     * @generated
     * @ordered
     */
    protected static final String TAIL_LABEL1_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTailLabel1() <em>Tail Label1</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTailLabel1()
     * @generated
     * @ordered
     */
    protected String tailLabel1 = TAIL_LABEL1_EDEFAULT;

    /**
     * The default value of the '{@link #getTailLabel2() <em>Tail Label2</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTailLabel2()
     * @generated
     * @ordered
     */
    protected static final String TAIL_LABEL2_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTailLabel2() <em>Tail Label2</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTailLabel2()
     * @generated
     * @ordered
     */
    protected String tailLabel2 = TAIL_LABEL2_EDEFAULT;

    /**
     * The default value of the '{@link #isIsDirected() <em>Is Directed</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isIsDirected()
     * @generated
     * @ordered
     */
    protected static final boolean IS_DIRECTED_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isIsDirected() <em>Is Directed</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isIsDirected()
     * @generated
     * @ordered
     */
    protected boolean isDirected = IS_DIRECTED_EDEFAULT;

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
        return GraphsPackage.Literals.EDGE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getHeadLabel1() {
        return headLabel1;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setHeadLabel1(String newHeadLabel1) {
        String oldHeadLabel1 = headLabel1;
        headLabel1 = newHeadLabel1;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GraphsPackage.EDGE__HEAD_LABEL1, oldHeadLabel1, headLabel1));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getHeadLabel2() {
        return headLabel2;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setHeadLabel2(String newHeadLabel2) {
        String oldHeadLabel2 = headLabel2;
        headLabel2 = newHeadLabel2;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GraphsPackage.EDGE__HEAD_LABEL2, oldHeadLabel2, headLabel2));
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
            eNotify(new ENotificationImpl(this, Notification.SET, GraphsPackage.EDGE__MID_LABEL, oldMidLabel, midLabel));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getTailLabel1() {
        return tailLabel1;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTailLabel1(String newTailLabel1) {
        String oldTailLabel1 = tailLabel1;
        tailLabel1 = newTailLabel1;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GraphsPackage.EDGE__TAIL_LABEL1, oldTailLabel1, tailLabel1));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getTailLabel2() {
        return tailLabel2;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTailLabel2(String newTailLabel2) {
        String oldTailLabel2 = tailLabel2;
        tailLabel2 = newTailLabel2;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GraphsPackage.EDGE__TAIL_LABEL2, oldTailLabel2, tailLabel2));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isIsDirected() {
        return isDirected;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setIsDirected(boolean newIsDirected) {
        boolean oldIsDirected = isDirected;
        isDirected = newIsDirected;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GraphsPackage.EDGE__IS_DIRECTED, oldIsDirected, isDirected));
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
            eNotify(new ENotificationImpl(this, Notification.SET, GraphsPackage.EDGE__TYPE, oldType, type));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case GraphsPackage.EDGE__HEAD_LABEL1:
                return getHeadLabel1();
            case GraphsPackage.EDGE__HEAD_LABEL2:
                return getHeadLabel2();
            case GraphsPackage.EDGE__MID_LABEL:
                return getMidLabel();
            case GraphsPackage.EDGE__TAIL_LABEL1:
                return getTailLabel1();
            case GraphsPackage.EDGE__TAIL_LABEL2:
                return getTailLabel2();
            case GraphsPackage.EDGE__IS_DIRECTED:
                return isIsDirected();
            case GraphsPackage.EDGE__TYPE:
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
            case GraphsPackage.EDGE__HEAD_LABEL1:
                setHeadLabel1((String)newValue);
                return;
            case GraphsPackage.EDGE__HEAD_LABEL2:
                setHeadLabel2((String)newValue);
                return;
            case GraphsPackage.EDGE__MID_LABEL:
                setMidLabel((String)newValue);
                return;
            case GraphsPackage.EDGE__TAIL_LABEL1:
                setTailLabel1((String)newValue);
                return;
            case GraphsPackage.EDGE__TAIL_LABEL2:
                setTailLabel2((String)newValue);
                return;
            case GraphsPackage.EDGE__IS_DIRECTED:
                setIsDirected((Boolean)newValue);
                return;
            case GraphsPackage.EDGE__TYPE:
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
            case GraphsPackage.EDGE__HEAD_LABEL1:
                setHeadLabel1(HEAD_LABEL1_EDEFAULT);
                return;
            case GraphsPackage.EDGE__HEAD_LABEL2:
                setHeadLabel2(HEAD_LABEL2_EDEFAULT);
                return;
            case GraphsPackage.EDGE__MID_LABEL:
                setMidLabel(MID_LABEL_EDEFAULT);
                return;
            case GraphsPackage.EDGE__TAIL_LABEL1:
                setTailLabel1(TAIL_LABEL1_EDEFAULT);
                return;
            case GraphsPackage.EDGE__TAIL_LABEL2:
                setTailLabel2(TAIL_LABEL2_EDEFAULT);
                return;
            case GraphsPackage.EDGE__IS_DIRECTED:
                setIsDirected(IS_DIRECTED_EDEFAULT);
                return;
            case GraphsPackage.EDGE__TYPE:
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
            case GraphsPackage.EDGE__HEAD_LABEL1:
                return HEAD_LABEL1_EDEFAULT == null ? headLabel1 != null : !HEAD_LABEL1_EDEFAULT.equals(headLabel1);
            case GraphsPackage.EDGE__HEAD_LABEL2:
                return HEAD_LABEL2_EDEFAULT == null ? headLabel2 != null : !HEAD_LABEL2_EDEFAULT.equals(headLabel2);
            case GraphsPackage.EDGE__MID_LABEL:
                return MID_LABEL_EDEFAULT == null ? midLabel != null : !MID_LABEL_EDEFAULT.equals(midLabel);
            case GraphsPackage.EDGE__TAIL_LABEL1:
                return TAIL_LABEL1_EDEFAULT == null ? tailLabel1 != null : !TAIL_LABEL1_EDEFAULT.equals(tailLabel1);
            case GraphsPackage.EDGE__TAIL_LABEL2:
                return TAIL_LABEL2_EDEFAULT == null ? tailLabel2 != null : !TAIL_LABEL2_EDEFAULT.equals(tailLabel2);
            case GraphsPackage.EDGE__IS_DIRECTED:
                return isDirected != IS_DIRECTED_EDEFAULT;
            case GraphsPackage.EDGE__TYPE:
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
        result.append(" (headLabel1: ");
        result.append(headLabel1);
        result.append(", headLabel2: ");
        result.append(headLabel2);
        result.append(", midLabel: ");
        result.append(midLabel);
        result.append(", tailLabel1: ");
        result.append(tailLabel1);
        result.append(", tailLabel2: ");
        result.append(tailLabel2);
        result.append(", isDirected: ");
        result.append(isDirected);
        result.append(", type: ");
        result.append(type);
        result.append(')');
        return result.toString();
    }

} //EdgeImpl
