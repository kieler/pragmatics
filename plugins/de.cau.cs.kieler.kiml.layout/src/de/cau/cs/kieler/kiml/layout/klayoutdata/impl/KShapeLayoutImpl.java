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
package de.cau.cs.kieler.kiml.layout.klayoutdata.impl;

import de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutDataPackage;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KShapeLayout;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>KShape Layout</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KShapeLayoutImpl#getXpos <em>Xpos</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KShapeLayoutImpl#getYpos <em>Ypos</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KShapeLayoutImpl#getWidth <em>Width</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KShapeLayoutImpl#getHeight <em>Height</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class KShapeLayoutImpl extends KLayoutDataImpl implements KShapeLayout {
    /**
     * The default value of the '{@link #getXpos() <em>Xpos</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getXpos()
     * @generated
     * @ordered
     */
    protected static final float XPOS_EDEFAULT = 0.0F;

    /**
     * The cached value of the '{@link #getXpos() <em>Xpos</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getXpos()
     * @generated
     * @ordered
     */
    protected float xpos = XPOS_EDEFAULT;

    /**
     * The default value of the '{@link #getYpos() <em>Ypos</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getYpos()
     * @generated
     * @ordered
     */
    protected static final float YPOS_EDEFAULT = 0.0F;

    /**
     * The cached value of the '{@link #getYpos() <em>Ypos</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getYpos()
     * @generated
     * @ordered
     */
    protected float ypos = YPOS_EDEFAULT;

    /**
     * The default value of the '{@link #getWidth() <em>Width</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getWidth()
     * @generated
     * @ordered
     */
    protected static final float WIDTH_EDEFAULT = 0.0F;

    /**
     * The cached value of the '{@link #getWidth() <em>Width</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getWidth()
     * @generated
     * @ordered
     */
    protected float width = WIDTH_EDEFAULT;

    /**
     * The default value of the '{@link #getHeight() <em>Height</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getHeight()
     * @generated
     * @ordered
     */
    protected static final float HEIGHT_EDEFAULT = 0.0F;

    /**
     * The cached value of the '{@link #getHeight() <em>Height</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getHeight()
     * @generated
     * @ordered
     */
    protected float height = HEIGHT_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected KShapeLayoutImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return KLayoutDataPackage.Literals.KSHAPE_LAYOUT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public float getXpos() {
        return xpos;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setXpos(float newXpos) {
        float oldXpos = xpos;
        xpos = newXpos;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KLayoutDataPackage.KSHAPE_LAYOUT__XPOS, oldXpos, xpos));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public float getYpos() {
        return ypos;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setYpos(float newYpos) {
        float oldYpos = ypos;
        ypos = newYpos;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KLayoutDataPackage.KSHAPE_LAYOUT__YPOS, oldYpos, ypos));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public float getWidth() {
        return width;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setWidth(float newWidth) {
        float oldWidth = width;
        width = newWidth;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KLayoutDataPackage.KSHAPE_LAYOUT__WIDTH, oldWidth, width));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public float getHeight() {
        return height;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setHeight(float newHeight) {
        float oldHeight = height;
        height = newHeight;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KLayoutDataPackage.KSHAPE_LAYOUT__HEIGHT, oldHeight, height));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case KLayoutDataPackage.KSHAPE_LAYOUT__XPOS:
                return new Float(getXpos());
            case KLayoutDataPackage.KSHAPE_LAYOUT__YPOS:
                return new Float(getYpos());
            case KLayoutDataPackage.KSHAPE_LAYOUT__WIDTH:
                return new Float(getWidth());
            case KLayoutDataPackage.KSHAPE_LAYOUT__HEIGHT:
                return new Float(getHeight());
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
            case KLayoutDataPackage.KSHAPE_LAYOUT__XPOS:
                setXpos(((Float)newValue).floatValue());
                return;
            case KLayoutDataPackage.KSHAPE_LAYOUT__YPOS:
                setYpos(((Float)newValue).floatValue());
                return;
            case KLayoutDataPackage.KSHAPE_LAYOUT__WIDTH:
                setWidth(((Float)newValue).floatValue());
                return;
            case KLayoutDataPackage.KSHAPE_LAYOUT__HEIGHT:
                setHeight(((Float)newValue).floatValue());
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
            case KLayoutDataPackage.KSHAPE_LAYOUT__XPOS:
                setXpos(XPOS_EDEFAULT);
                return;
            case KLayoutDataPackage.KSHAPE_LAYOUT__YPOS:
                setYpos(YPOS_EDEFAULT);
                return;
            case KLayoutDataPackage.KSHAPE_LAYOUT__WIDTH:
                setWidth(WIDTH_EDEFAULT);
                return;
            case KLayoutDataPackage.KSHAPE_LAYOUT__HEIGHT:
                setHeight(HEIGHT_EDEFAULT);
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
            case KLayoutDataPackage.KSHAPE_LAYOUT__XPOS:
                return xpos != XPOS_EDEFAULT;
            case KLayoutDataPackage.KSHAPE_LAYOUT__YPOS:
                return ypos != YPOS_EDEFAULT;
            case KLayoutDataPackage.KSHAPE_LAYOUT__WIDTH:
                return width != WIDTH_EDEFAULT;
            case KLayoutDataPackage.KSHAPE_LAYOUT__HEIGHT:
                return height != HEIGHT_EDEFAULT;
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
        result.append(" (xpos: ");
        result.append(xpos);
        result.append(", ypos: ");
        result.append(ypos);
        result.append(", width: ");
        result.append(width);
        result.append(", height: ");
        result.append(height);
        result.append(')');
        return result.toString();
    }

} //KShapeLayoutImpl
