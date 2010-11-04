/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.ogdf.ogml.impl;

import net.ogdf.ogml.FillType;
import net.ogdf.ogml.OgmlPackage;
import net.ogdf.ogml.PatternType;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Fill Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link net.ogdf.ogml.impl.FillTypeImpl#getColor <em>Color</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.FillTypeImpl#getPattern <em>Pattern</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.FillTypeImpl#getPatternColor <em>Pattern Color</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FillTypeImpl extends EObjectImpl implements FillType {
    /**
     * The default value of the '{@link #getColor() <em>Color</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getColor()
     * @generated
     * @ordered
     */
    protected static final String COLOR_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getColor() <em>Color</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getColor()
     * @generated
     * @ordered
     */
    protected String color = COLOR_EDEFAULT;

    /**
     * The default value of the '{@link #getPattern() <em>Pattern</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPattern()
     * @generated
     * @ordered
     */
    protected static final PatternType PATTERN_EDEFAULT = PatternType.SOLID;

    /**
     * The cached value of the '{@link #getPattern() <em>Pattern</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPattern()
     * @generated
     * @ordered
     */
    protected PatternType pattern = PATTERN_EDEFAULT;

    /**
     * This is true if the Pattern attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean patternESet;

    /**
     * The default value of the '{@link #getPatternColor() <em>Pattern Color</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPatternColor()
     * @generated
     * @ordered
     */
    protected static final String PATTERN_COLOR_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getPatternColor() <em>Pattern Color</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPatternColor()
     * @generated
     * @ordered
     */
    protected String patternColor = PATTERN_COLOR_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected FillTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return OgmlPackage.Literals.FILL_TYPE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getColor() {
        return color;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setColor(String newColor) {
        String oldColor = color;
        color = newColor;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OgmlPackage.FILL_TYPE__COLOR, oldColor, color));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PatternType getPattern() {
        return pattern;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPattern(PatternType newPattern) {
        PatternType oldPattern = pattern;
        pattern = newPattern == null ? PATTERN_EDEFAULT : newPattern;
        boolean oldPatternESet = patternESet;
        patternESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OgmlPackage.FILL_TYPE__PATTERN, oldPattern, pattern, !oldPatternESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetPattern() {
        PatternType oldPattern = pattern;
        boolean oldPatternESet = patternESet;
        pattern = PATTERN_EDEFAULT;
        patternESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, OgmlPackage.FILL_TYPE__PATTERN, oldPattern, PATTERN_EDEFAULT, oldPatternESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetPattern() {
        return patternESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getPatternColor() {
        return patternColor;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPatternColor(String newPatternColor) {
        String oldPatternColor = patternColor;
        patternColor = newPatternColor;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OgmlPackage.FILL_TYPE__PATTERN_COLOR, oldPatternColor, patternColor));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case OgmlPackage.FILL_TYPE__COLOR:
                return getColor();
            case OgmlPackage.FILL_TYPE__PATTERN:
                return getPattern();
            case OgmlPackage.FILL_TYPE__PATTERN_COLOR:
                return getPatternColor();
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
            case OgmlPackage.FILL_TYPE__COLOR:
                setColor((String)newValue);
                return;
            case OgmlPackage.FILL_TYPE__PATTERN:
                setPattern((PatternType)newValue);
                return;
            case OgmlPackage.FILL_TYPE__PATTERN_COLOR:
                setPatternColor((String)newValue);
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
            case OgmlPackage.FILL_TYPE__COLOR:
                setColor(COLOR_EDEFAULT);
                return;
            case OgmlPackage.FILL_TYPE__PATTERN:
                unsetPattern();
                return;
            case OgmlPackage.FILL_TYPE__PATTERN_COLOR:
                setPatternColor(PATTERN_COLOR_EDEFAULT);
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
            case OgmlPackage.FILL_TYPE__COLOR:
                return COLOR_EDEFAULT == null ? color != null : !COLOR_EDEFAULT.equals(color);
            case OgmlPackage.FILL_TYPE__PATTERN:
                return isSetPattern();
            case OgmlPackage.FILL_TYPE__PATTERN_COLOR:
                return PATTERN_COLOR_EDEFAULT == null ? patternColor != null : !PATTERN_COLOR_EDEFAULT.equals(patternColor);
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
        result.append(" (color: ");
        result.append(color);
        result.append(", pattern: ");
        if (patternESet) result.append(pattern); else result.append("<unset>");
        result.append(", patternColor: ");
        result.append(patternColor);
        result.append(')');
        return result.toString();
    }

} //FillTypeImpl
