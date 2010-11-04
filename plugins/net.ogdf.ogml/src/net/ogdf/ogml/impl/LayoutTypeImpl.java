/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.ogdf.ogml.impl;

import java.util.Collection;

import net.ogdf.ogml.ConstraintsType;
import net.ogdf.ogml.DataType;
import net.ogdf.ogml.LayoutType;
import net.ogdf.ogml.OgmlPackage;
import net.ogdf.ogml.StyleTemplatesType;
import net.ogdf.ogml.StylesType;

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
 * An implementation of the model object '<em><b>Layout Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link net.ogdf.ogml.impl.LayoutTypeImpl#getData <em>Data</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.LayoutTypeImpl#getStyleTemplates <em>Style Templates</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.LayoutTypeImpl#getStyles <em>Styles</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.LayoutTypeImpl#getConstraints <em>Constraints</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LayoutTypeImpl extends EObjectImpl implements LayoutType {
    /**
     * The cached value of the '{@link #getData() <em>Data</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getData()
     * @generated
     * @ordered
     */
    protected EList<DataType> data;

    /**
     * The cached value of the '{@link #getStyleTemplates() <em>Style Templates</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStyleTemplates()
     * @generated
     * @ordered
     */
    protected StyleTemplatesType styleTemplates;

    /**
     * The cached value of the '{@link #getStyles() <em>Styles</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStyles()
     * @generated
     * @ordered
     */
    protected StylesType styles;

    /**
     * The cached value of the '{@link #getConstraints() <em>Constraints</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getConstraints()
     * @generated
     * @ordered
     */
    protected ConstraintsType constraints;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected LayoutTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return OgmlPackage.Literals.LAYOUT_TYPE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<DataType> getData() {
        if (data == null) {
            data = new EObjectContainmentEList<DataType>(DataType.class, this, OgmlPackage.LAYOUT_TYPE__DATA);
        }
        return data;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public StyleTemplatesType getStyleTemplates() {
        return styleTemplates;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetStyleTemplates(StyleTemplatesType newStyleTemplates, NotificationChain msgs) {
        StyleTemplatesType oldStyleTemplates = styleTemplates;
        styleTemplates = newStyleTemplates;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OgmlPackage.LAYOUT_TYPE__STYLE_TEMPLATES, oldStyleTemplates, newStyleTemplates);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setStyleTemplates(StyleTemplatesType newStyleTemplates) {
        if (newStyleTemplates != styleTemplates) {
            NotificationChain msgs = null;
            if (styleTemplates != null)
                msgs = ((InternalEObject)styleTemplates).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OgmlPackage.LAYOUT_TYPE__STYLE_TEMPLATES, null, msgs);
            if (newStyleTemplates != null)
                msgs = ((InternalEObject)newStyleTemplates).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OgmlPackage.LAYOUT_TYPE__STYLE_TEMPLATES, null, msgs);
            msgs = basicSetStyleTemplates(newStyleTemplates, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OgmlPackage.LAYOUT_TYPE__STYLE_TEMPLATES, newStyleTemplates, newStyleTemplates));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public StylesType getStyles() {
        return styles;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetStyles(StylesType newStyles, NotificationChain msgs) {
        StylesType oldStyles = styles;
        styles = newStyles;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OgmlPackage.LAYOUT_TYPE__STYLES, oldStyles, newStyles);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setStyles(StylesType newStyles) {
        if (newStyles != styles) {
            NotificationChain msgs = null;
            if (styles != null)
                msgs = ((InternalEObject)styles).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OgmlPackage.LAYOUT_TYPE__STYLES, null, msgs);
            if (newStyles != null)
                msgs = ((InternalEObject)newStyles).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OgmlPackage.LAYOUT_TYPE__STYLES, null, msgs);
            msgs = basicSetStyles(newStyles, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OgmlPackage.LAYOUT_TYPE__STYLES, newStyles, newStyles));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ConstraintsType getConstraints() {
        return constraints;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetConstraints(ConstraintsType newConstraints, NotificationChain msgs) {
        ConstraintsType oldConstraints = constraints;
        constraints = newConstraints;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OgmlPackage.LAYOUT_TYPE__CONSTRAINTS, oldConstraints, newConstraints);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setConstraints(ConstraintsType newConstraints) {
        if (newConstraints != constraints) {
            NotificationChain msgs = null;
            if (constraints != null)
                msgs = ((InternalEObject)constraints).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OgmlPackage.LAYOUT_TYPE__CONSTRAINTS, null, msgs);
            if (newConstraints != null)
                msgs = ((InternalEObject)newConstraints).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OgmlPackage.LAYOUT_TYPE__CONSTRAINTS, null, msgs);
            msgs = basicSetConstraints(newConstraints, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OgmlPackage.LAYOUT_TYPE__CONSTRAINTS, newConstraints, newConstraints));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case OgmlPackage.LAYOUT_TYPE__DATA:
                return ((InternalEList<?>)getData()).basicRemove(otherEnd, msgs);
            case OgmlPackage.LAYOUT_TYPE__STYLE_TEMPLATES:
                return basicSetStyleTemplates(null, msgs);
            case OgmlPackage.LAYOUT_TYPE__STYLES:
                return basicSetStyles(null, msgs);
            case OgmlPackage.LAYOUT_TYPE__CONSTRAINTS:
                return basicSetConstraints(null, msgs);
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
            case OgmlPackage.LAYOUT_TYPE__DATA:
                return getData();
            case OgmlPackage.LAYOUT_TYPE__STYLE_TEMPLATES:
                return getStyleTemplates();
            case OgmlPackage.LAYOUT_TYPE__STYLES:
                return getStyles();
            case OgmlPackage.LAYOUT_TYPE__CONSTRAINTS:
                return getConstraints();
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
            case OgmlPackage.LAYOUT_TYPE__DATA:
                getData().clear();
                getData().addAll((Collection<? extends DataType>)newValue);
                return;
            case OgmlPackage.LAYOUT_TYPE__STYLE_TEMPLATES:
                setStyleTemplates((StyleTemplatesType)newValue);
                return;
            case OgmlPackage.LAYOUT_TYPE__STYLES:
                setStyles((StylesType)newValue);
                return;
            case OgmlPackage.LAYOUT_TYPE__CONSTRAINTS:
                setConstraints((ConstraintsType)newValue);
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
            case OgmlPackage.LAYOUT_TYPE__DATA:
                getData().clear();
                return;
            case OgmlPackage.LAYOUT_TYPE__STYLE_TEMPLATES:
                setStyleTemplates((StyleTemplatesType)null);
                return;
            case OgmlPackage.LAYOUT_TYPE__STYLES:
                setStyles((StylesType)null);
                return;
            case OgmlPackage.LAYOUT_TYPE__CONSTRAINTS:
                setConstraints((ConstraintsType)null);
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
            case OgmlPackage.LAYOUT_TYPE__DATA:
                return data != null && !data.isEmpty();
            case OgmlPackage.LAYOUT_TYPE__STYLE_TEMPLATES:
                return styleTemplates != null;
            case OgmlPackage.LAYOUT_TYPE__STYLES:
                return styles != null;
            case OgmlPackage.LAYOUT_TYPE__CONSTRAINTS:
                return constraints != null;
        }
        return super.eIsSet(featureID);
    }

} //LayoutTypeImpl
