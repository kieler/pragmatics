/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.ogdf.ogml.impl;

import java.math.BigInteger;

import net.ogdf.ogml.FontStretchType;
import net.ogdf.ogml.FontStyleType;
import net.ogdf.ogml.FontType;
import net.ogdf.ogml.FontVariantType;
import net.ogdf.ogml.FontWeightType;
import net.ogdf.ogml.OgmlPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Font Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link net.ogdf.ogml.impl.FontTypeImpl#getColor <em>Color</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.FontTypeImpl#getFamily <em>Family</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.FontTypeImpl#getSize <em>Size</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.FontTypeImpl#getStretch <em>Stretch</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.FontTypeImpl#getStyle <em>Style</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.FontTypeImpl#getVariant <em>Variant</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.FontTypeImpl#getWeight <em>Weight</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FontTypeImpl extends EObjectImpl implements FontType {
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
     * The default value of the '{@link #getFamily() <em>Family</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFamily()
     * @generated
     * @ordered
     */
    protected static final String FAMILY_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFamily() <em>Family</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFamily()
     * @generated
     * @ordered
     */
    protected String family = FAMILY_EDEFAULT;

    /**
     * The default value of the '{@link #getSize() <em>Size</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSize()
     * @generated
     * @ordered
     */
    protected static final BigInteger SIZE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getSize() <em>Size</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSize()
     * @generated
     * @ordered
     */
    protected BigInteger size = SIZE_EDEFAULT;

    /**
     * The default value of the '{@link #getStretch() <em>Stretch</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStretch()
     * @generated
     * @ordered
     */
    protected static final FontStretchType STRETCH_EDEFAULT = FontStretchType.NORMAL;

    /**
     * The cached value of the '{@link #getStretch() <em>Stretch</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStretch()
     * @generated
     * @ordered
     */
    protected FontStretchType stretch = STRETCH_EDEFAULT;

    /**
     * This is true if the Stretch attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean stretchESet;

    /**
     * The default value of the '{@link #getStyle() <em>Style</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStyle()
     * @generated
     * @ordered
     */
    protected static final FontStyleType STYLE_EDEFAULT = FontStyleType.NORMAL;

    /**
     * The cached value of the '{@link #getStyle() <em>Style</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStyle()
     * @generated
     * @ordered
     */
    protected FontStyleType style = STYLE_EDEFAULT;

    /**
     * This is true if the Style attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean styleESet;

    /**
     * The default value of the '{@link #getVariant() <em>Variant</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getVariant()
     * @generated
     * @ordered
     */
    protected static final FontVariantType VARIANT_EDEFAULT = FontVariantType.NORMAL;

    /**
     * The cached value of the '{@link #getVariant() <em>Variant</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getVariant()
     * @generated
     * @ordered
     */
    protected FontVariantType variant = VARIANT_EDEFAULT;

    /**
     * This is true if the Variant attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean variantESet;

    /**
     * The default value of the '{@link #getWeight() <em>Weight</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getWeight()
     * @generated
     * @ordered
     */
    protected static final FontWeightType WEIGHT_EDEFAULT = FontWeightType.NORMAL;

    /**
     * The cached value of the '{@link #getWeight() <em>Weight</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getWeight()
     * @generated
     * @ordered
     */
    protected FontWeightType weight = WEIGHT_EDEFAULT;

    /**
     * This is true if the Weight attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean weightESet;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected FontTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return OgmlPackage.Literals.FONT_TYPE;
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
            eNotify(new ENotificationImpl(this, Notification.SET, OgmlPackage.FONT_TYPE__COLOR, oldColor, color));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getFamily() {
        return family;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setFamily(String newFamily) {
        String oldFamily = family;
        family = newFamily;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OgmlPackage.FONT_TYPE__FAMILY, oldFamily, family));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BigInteger getSize() {
        return size;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSize(BigInteger newSize) {
        BigInteger oldSize = size;
        size = newSize;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OgmlPackage.FONT_TYPE__SIZE, oldSize, size));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FontStretchType getStretch() {
        return stretch;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setStretch(FontStretchType newStretch) {
        FontStretchType oldStretch = stretch;
        stretch = newStretch == null ? STRETCH_EDEFAULT : newStretch;
        boolean oldStretchESet = stretchESet;
        stretchESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OgmlPackage.FONT_TYPE__STRETCH, oldStretch, stretch, !oldStretchESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetStretch() {
        FontStretchType oldStretch = stretch;
        boolean oldStretchESet = stretchESet;
        stretch = STRETCH_EDEFAULT;
        stretchESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, OgmlPackage.FONT_TYPE__STRETCH, oldStretch, STRETCH_EDEFAULT, oldStretchESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetStretch() {
        return stretchESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FontStyleType getStyle() {
        return style;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setStyle(FontStyleType newStyle) {
        FontStyleType oldStyle = style;
        style = newStyle == null ? STYLE_EDEFAULT : newStyle;
        boolean oldStyleESet = styleESet;
        styleESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OgmlPackage.FONT_TYPE__STYLE, oldStyle, style, !oldStyleESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetStyle() {
        FontStyleType oldStyle = style;
        boolean oldStyleESet = styleESet;
        style = STYLE_EDEFAULT;
        styleESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, OgmlPackage.FONT_TYPE__STYLE, oldStyle, STYLE_EDEFAULT, oldStyleESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetStyle() {
        return styleESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FontVariantType getVariant() {
        return variant;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setVariant(FontVariantType newVariant) {
        FontVariantType oldVariant = variant;
        variant = newVariant == null ? VARIANT_EDEFAULT : newVariant;
        boolean oldVariantESet = variantESet;
        variantESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OgmlPackage.FONT_TYPE__VARIANT, oldVariant, variant, !oldVariantESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetVariant() {
        FontVariantType oldVariant = variant;
        boolean oldVariantESet = variantESet;
        variant = VARIANT_EDEFAULT;
        variantESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, OgmlPackage.FONT_TYPE__VARIANT, oldVariant, VARIANT_EDEFAULT, oldVariantESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetVariant() {
        return variantESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FontWeightType getWeight() {
        return weight;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setWeight(FontWeightType newWeight) {
        FontWeightType oldWeight = weight;
        weight = newWeight == null ? WEIGHT_EDEFAULT : newWeight;
        boolean oldWeightESet = weightESet;
        weightESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OgmlPackage.FONT_TYPE__WEIGHT, oldWeight, weight, !oldWeightESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetWeight() {
        FontWeightType oldWeight = weight;
        boolean oldWeightESet = weightESet;
        weight = WEIGHT_EDEFAULT;
        weightESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, OgmlPackage.FONT_TYPE__WEIGHT, oldWeight, WEIGHT_EDEFAULT, oldWeightESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetWeight() {
        return weightESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case OgmlPackage.FONT_TYPE__COLOR:
                return getColor();
            case OgmlPackage.FONT_TYPE__FAMILY:
                return getFamily();
            case OgmlPackage.FONT_TYPE__SIZE:
                return getSize();
            case OgmlPackage.FONT_TYPE__STRETCH:
                return getStretch();
            case OgmlPackage.FONT_TYPE__STYLE:
                return getStyle();
            case OgmlPackage.FONT_TYPE__VARIANT:
                return getVariant();
            case OgmlPackage.FONT_TYPE__WEIGHT:
                return getWeight();
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
            case OgmlPackage.FONT_TYPE__COLOR:
                setColor((String)newValue);
                return;
            case OgmlPackage.FONT_TYPE__FAMILY:
                setFamily((String)newValue);
                return;
            case OgmlPackage.FONT_TYPE__SIZE:
                setSize((BigInteger)newValue);
                return;
            case OgmlPackage.FONT_TYPE__STRETCH:
                setStretch((FontStretchType)newValue);
                return;
            case OgmlPackage.FONT_TYPE__STYLE:
                setStyle((FontStyleType)newValue);
                return;
            case OgmlPackage.FONT_TYPE__VARIANT:
                setVariant((FontVariantType)newValue);
                return;
            case OgmlPackage.FONT_TYPE__WEIGHT:
                setWeight((FontWeightType)newValue);
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
            case OgmlPackage.FONT_TYPE__COLOR:
                setColor(COLOR_EDEFAULT);
                return;
            case OgmlPackage.FONT_TYPE__FAMILY:
                setFamily(FAMILY_EDEFAULT);
                return;
            case OgmlPackage.FONT_TYPE__SIZE:
                setSize(SIZE_EDEFAULT);
                return;
            case OgmlPackage.FONT_TYPE__STRETCH:
                unsetStretch();
                return;
            case OgmlPackage.FONT_TYPE__STYLE:
                unsetStyle();
                return;
            case OgmlPackage.FONT_TYPE__VARIANT:
                unsetVariant();
                return;
            case OgmlPackage.FONT_TYPE__WEIGHT:
                unsetWeight();
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
            case OgmlPackage.FONT_TYPE__COLOR:
                return COLOR_EDEFAULT == null ? color != null : !COLOR_EDEFAULT.equals(color);
            case OgmlPackage.FONT_TYPE__FAMILY:
                return FAMILY_EDEFAULT == null ? family != null : !FAMILY_EDEFAULT.equals(family);
            case OgmlPackage.FONT_TYPE__SIZE:
                return SIZE_EDEFAULT == null ? size != null : !SIZE_EDEFAULT.equals(size);
            case OgmlPackage.FONT_TYPE__STRETCH:
                return isSetStretch();
            case OgmlPackage.FONT_TYPE__STYLE:
                return isSetStyle();
            case OgmlPackage.FONT_TYPE__VARIANT:
                return isSetVariant();
            case OgmlPackage.FONT_TYPE__WEIGHT:
                return isSetWeight();
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
        result.append(", family: ");
        result.append(family);
        result.append(", size: ");
        result.append(size);
        result.append(", stretch: ");
        if (stretchESet) result.append(stretch); else result.append("<unset>");
        result.append(", style: ");
        if (styleESet) result.append(style); else result.append("<unset>");
        result.append(", variant: ");
        if (variantESet) result.append(variant); else result.append("<unset>");
        result.append(", weight: ");
        if (weightESet) result.append(weight); else result.append("<unset>");
        result.append(')');
        return result.toString();
    }

} //FontTypeImpl
