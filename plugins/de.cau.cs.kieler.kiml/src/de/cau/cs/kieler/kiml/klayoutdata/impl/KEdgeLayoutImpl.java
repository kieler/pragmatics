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
package de.cau.cs.kieler.kiml.klayoutdata.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import de.cau.cs.kieler.core.kgraph.impl.KGraphDataImpl;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataPackage;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>KEdge Layout</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link de.cau.cs.kieler.kiml.klayoutdata.impl.KEdgeLayoutImpl#getBendPoints
 * <em>Bend Points</em>}</li>
 * <li>
 * {@link de.cau.cs.kieler.kiml.klayoutdata.impl.KEdgeLayoutImpl#getSourcePoint
 * <em>Source Point</em>}</li>
 * <li>
 * {@link de.cau.cs.kieler.kiml.klayoutdata.impl.KEdgeLayoutImpl#getTargetPoint
 * <em>Target Point</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class KEdgeLayoutImpl extends KGraphDataImpl implements KEdgeLayout {
    /**
     * The cached value of the '{@link #getBendPoints() <em>Bend Points</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getBendPoints()
     * @generated
     * @ordered
     */
    protected EList<KPoint> bendPoints;

    /**
     * The cached value of the '{@link #getSourcePoint() <em>Source Point</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getSourcePoint()
     * @generated
     * @ordered
     */
    protected KPoint sourcePoint;

    /**
     * The cached value of the '{@link #getTargetPoint() <em>Target Point</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getTargetPoint()
     * @generated
     * @ordered
     */
    protected KPoint targetPoint;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected KEdgeLayoutImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return KLayoutDataPackage.Literals.KEDGE_LAYOUT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<KPoint> getBendPoints() {
        if (bendPoints == null) {
            bendPoints =
                    new EObjectContainmentEList<KPoint>(KPoint.class, this,
                            KLayoutDataPackage.KEDGE_LAYOUT__BEND_POINTS);
        }
        return bendPoints;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public KPoint getSourcePoint() {
        return sourcePoint;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetSourcePoint(final KPoint newSourcePoint,
            NotificationChain msgs) {
        KPoint oldSourcePoint = sourcePoint;
        sourcePoint = newSourcePoint;
        if (eNotificationRequired()) {
            ENotificationImpl notification =
                    new ENotificationImpl(this, Notification.SET,
                            KLayoutDataPackage.KEDGE_LAYOUT__SOURCE_POINT,
                            oldSourcePoint, newSourcePoint);
            if (msgs == null) {
                msgs = notification;
            } else {
                msgs.add(notification);
            }
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setSourcePoint(final KPoint newSourcePoint) {
        if (newSourcePoint != sourcePoint) {
            NotificationChain msgs = null;
            if (sourcePoint != null) {
                msgs =
                        ((InternalEObject) sourcePoint)
                                .eInverseRemove(
                                        this,
                                        EOPPOSITE_FEATURE_BASE
                                                - KLayoutDataPackage.KEDGE_LAYOUT__SOURCE_POINT,
                                        null, msgs);
            }
            if (newSourcePoint != null) {
                msgs =
                        ((InternalEObject) newSourcePoint)
                                .eInverseAdd(
                                        this,
                                        EOPPOSITE_FEATURE_BASE
                                                - KLayoutDataPackage.KEDGE_LAYOUT__SOURCE_POINT,
                                        null, msgs);
            }
            msgs = basicSetSourcePoint(newSourcePoint, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET,
                    KLayoutDataPackage.KEDGE_LAYOUT__SOURCE_POINT,
                    newSourcePoint, newSourcePoint));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public KPoint getTargetPoint() {
        return targetPoint;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetTargetPoint(final KPoint newTargetPoint,
            NotificationChain msgs) {
        KPoint oldTargetPoint = targetPoint;
        targetPoint = newTargetPoint;
        if (eNotificationRequired()) {
            ENotificationImpl notification =
                    new ENotificationImpl(this, Notification.SET,
                            KLayoutDataPackage.KEDGE_LAYOUT__TARGET_POINT,
                            oldTargetPoint, newTargetPoint);
            if (msgs == null) {
                msgs = notification;
            } else {
                msgs.add(notification);
            }
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setTargetPoint(final KPoint newTargetPoint) {
        if (newTargetPoint != targetPoint) {
            NotificationChain msgs = null;
            if (targetPoint != null) {
                msgs =
                        ((InternalEObject) targetPoint)
                                .eInverseRemove(
                                        this,
                                        EOPPOSITE_FEATURE_BASE
                                                - KLayoutDataPackage.KEDGE_LAYOUT__TARGET_POINT,
                                        null, msgs);
            }
            if (newTargetPoint != null) {
                msgs =
                        ((InternalEObject) newTargetPoint)
                                .eInverseAdd(
                                        this,
                                        EOPPOSITE_FEATURE_BASE
                                                - KLayoutDataPackage.KEDGE_LAYOUT__TARGET_POINT,
                                        null, msgs);
            }
            msgs = basicSetTargetPoint(newTargetPoint, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET,
                    KLayoutDataPackage.KEDGE_LAYOUT__TARGET_POINT,
                    newTargetPoint, newTargetPoint));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(final InternalEObject otherEnd,
            final int featureID, final NotificationChain msgs) {
        switch (featureID) {
        case KLayoutDataPackage.KEDGE_LAYOUT__BEND_POINTS:
            return ((InternalEList<?>) getBendPoints()).basicRemove(otherEnd,
                    msgs);
        case KLayoutDataPackage.KEDGE_LAYOUT__SOURCE_POINT:
            return basicSetSourcePoint(null, msgs);
        case KLayoutDataPackage.KEDGE_LAYOUT__TARGET_POINT:
            return basicSetTargetPoint(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(final int featureID, final boolean resolve,
            final boolean coreType) {
        switch (featureID) {
        case KLayoutDataPackage.KEDGE_LAYOUT__BEND_POINTS:
            return getBendPoints();
        case KLayoutDataPackage.KEDGE_LAYOUT__SOURCE_POINT:
            return getSourcePoint();
        case KLayoutDataPackage.KEDGE_LAYOUT__TARGET_POINT:
            return getTargetPoint();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(final int featureID, final Object newValue) {
        switch (featureID) {
        case KLayoutDataPackage.KEDGE_LAYOUT__BEND_POINTS:
            getBendPoints().clear();
            getBendPoints().addAll((Collection<? extends KPoint>) newValue);
            return;
        case KLayoutDataPackage.KEDGE_LAYOUT__SOURCE_POINT:
            setSourcePoint((KPoint) newValue);
            return;
        case KLayoutDataPackage.KEDGE_LAYOUT__TARGET_POINT:
            setTargetPoint((KPoint) newValue);
            return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eUnset(final int featureID) {
        switch (featureID) {
        case KLayoutDataPackage.KEDGE_LAYOUT__BEND_POINTS:
            getBendPoints().clear();
            return;
        case KLayoutDataPackage.KEDGE_LAYOUT__SOURCE_POINT:
            setSourcePoint((KPoint) null);
            return;
        case KLayoutDataPackage.KEDGE_LAYOUT__TARGET_POINT:
            setTargetPoint((KPoint) null);
            return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean eIsSet(final int featureID) {
        switch (featureID) {
        case KLayoutDataPackage.KEDGE_LAYOUT__BEND_POINTS:
            return bendPoints != null && !bendPoints.isEmpty();
        case KLayoutDataPackage.KEDGE_LAYOUT__SOURCE_POINT:
            return sourcePoint != null;
        case KLayoutDataPackage.KEDGE_LAYOUT__TARGET_POINT:
            return targetPoint != null;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    @Override
    public String toString() {
        String result = "( " + sourcePoint;
        if (bendPoints != null) {
            for (KPoint p : bendPoints) {
                result += " -> " + p;
            }
        }
        return result + " -> " + targetPoint + " )";
    }
} // KEdgeLayoutImpl
