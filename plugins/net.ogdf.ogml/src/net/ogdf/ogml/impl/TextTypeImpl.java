/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.ogdf.ogml.impl;

import java.math.BigInteger;

import net.ogdf.ogml.AlignmentType;
import net.ogdf.ogml.DecorationType;
import net.ogdf.ogml.OgmlPackage;
import net.ogdf.ogml.TextType;
import net.ogdf.ogml.TransformType;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Text Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link net.ogdf.ogml.impl.TextTypeImpl#getAlignment <em>Alignment</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.TextTypeImpl#getDecoration <em>Decoration</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.TextTypeImpl#getRotation <em>Rotation</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.TextTypeImpl#getTransform <em>Transform</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TextTypeImpl extends EObjectImpl implements TextType {
    /**
     * The default value of the '{@link #getAlignment() <em>Alignment</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAlignment()
     * @generated
     * @ordered
     */
    protected static final AlignmentType ALIGNMENT_EDEFAULT = AlignmentType.LEFT;

    /**
     * The cached value of the '{@link #getAlignment() <em>Alignment</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAlignment()
     * @generated
     * @ordered
     */
    protected AlignmentType alignment = ALIGNMENT_EDEFAULT;

    /**
     * This is true if the Alignment attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean alignmentESet;

    /**
     * The default value of the '{@link #getDecoration() <em>Decoration</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDecoration()
     * @generated
     * @ordered
     */
    protected static final DecorationType DECORATION_EDEFAULT = DecorationType.UNDERLINE;

    /**
     * The cached value of the '{@link #getDecoration() <em>Decoration</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDecoration()
     * @generated
     * @ordered
     */
    protected DecorationType decoration = DECORATION_EDEFAULT;

    /**
     * This is true if the Decoration attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean decorationESet;

    /**
     * The default value of the '{@link #getRotation() <em>Rotation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRotation()
     * @generated
     * @ordered
     */
    protected static final BigInteger ROTATION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getRotation() <em>Rotation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRotation()
     * @generated
     * @ordered
     */
    protected BigInteger rotation = ROTATION_EDEFAULT;

    /**
     * The default value of the '{@link #getTransform() <em>Transform</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTransform()
     * @generated
     * @ordered
     */
    protected static final TransformType TRANSFORM_EDEFAULT = TransformType.CAPITALIZE;

    /**
     * The cached value of the '{@link #getTransform() <em>Transform</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTransform()
     * @generated
     * @ordered
     */
    protected TransformType transform = TRANSFORM_EDEFAULT;

    /**
     * This is true if the Transform attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean transformESet;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TextTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return OgmlPackage.Literals.TEXT_TYPE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AlignmentType getAlignment() {
        return alignment;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setAlignment(AlignmentType newAlignment) {
        AlignmentType oldAlignment = alignment;
        alignment = newAlignment == null ? ALIGNMENT_EDEFAULT : newAlignment;
        boolean oldAlignmentESet = alignmentESet;
        alignmentESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OgmlPackage.TEXT_TYPE__ALIGNMENT, oldAlignment, alignment, !oldAlignmentESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetAlignment() {
        AlignmentType oldAlignment = alignment;
        boolean oldAlignmentESet = alignmentESet;
        alignment = ALIGNMENT_EDEFAULT;
        alignmentESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, OgmlPackage.TEXT_TYPE__ALIGNMENT, oldAlignment, ALIGNMENT_EDEFAULT, oldAlignmentESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetAlignment() {
        return alignmentESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DecorationType getDecoration() {
        return decoration;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDecoration(DecorationType newDecoration) {
        DecorationType oldDecoration = decoration;
        decoration = newDecoration == null ? DECORATION_EDEFAULT : newDecoration;
        boolean oldDecorationESet = decorationESet;
        decorationESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OgmlPackage.TEXT_TYPE__DECORATION, oldDecoration, decoration, !oldDecorationESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetDecoration() {
        DecorationType oldDecoration = decoration;
        boolean oldDecorationESet = decorationESet;
        decoration = DECORATION_EDEFAULT;
        decorationESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, OgmlPackage.TEXT_TYPE__DECORATION, oldDecoration, DECORATION_EDEFAULT, oldDecorationESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetDecoration() {
        return decorationESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BigInteger getRotation() {
        return rotation;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setRotation(BigInteger newRotation) {
        BigInteger oldRotation = rotation;
        rotation = newRotation;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OgmlPackage.TEXT_TYPE__ROTATION, oldRotation, rotation));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TransformType getTransform() {
        return transform;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTransform(TransformType newTransform) {
        TransformType oldTransform = transform;
        transform = newTransform == null ? TRANSFORM_EDEFAULT : newTransform;
        boolean oldTransformESet = transformESet;
        transformESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OgmlPackage.TEXT_TYPE__TRANSFORM, oldTransform, transform, !oldTransformESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetTransform() {
        TransformType oldTransform = transform;
        boolean oldTransformESet = transformESet;
        transform = TRANSFORM_EDEFAULT;
        transformESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, OgmlPackage.TEXT_TYPE__TRANSFORM, oldTransform, TRANSFORM_EDEFAULT, oldTransformESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetTransform() {
        return transformESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case OgmlPackage.TEXT_TYPE__ALIGNMENT:
                return getAlignment();
            case OgmlPackage.TEXT_TYPE__DECORATION:
                return getDecoration();
            case OgmlPackage.TEXT_TYPE__ROTATION:
                return getRotation();
            case OgmlPackage.TEXT_TYPE__TRANSFORM:
                return getTransform();
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
            case OgmlPackage.TEXT_TYPE__ALIGNMENT:
                setAlignment((AlignmentType)newValue);
                return;
            case OgmlPackage.TEXT_TYPE__DECORATION:
                setDecoration((DecorationType)newValue);
                return;
            case OgmlPackage.TEXT_TYPE__ROTATION:
                setRotation((BigInteger)newValue);
                return;
            case OgmlPackage.TEXT_TYPE__TRANSFORM:
                setTransform((TransformType)newValue);
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
            case OgmlPackage.TEXT_TYPE__ALIGNMENT:
                unsetAlignment();
                return;
            case OgmlPackage.TEXT_TYPE__DECORATION:
                unsetDecoration();
                return;
            case OgmlPackage.TEXT_TYPE__ROTATION:
                setRotation(ROTATION_EDEFAULT);
                return;
            case OgmlPackage.TEXT_TYPE__TRANSFORM:
                unsetTransform();
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
            case OgmlPackage.TEXT_TYPE__ALIGNMENT:
                return isSetAlignment();
            case OgmlPackage.TEXT_TYPE__DECORATION:
                return isSetDecoration();
            case OgmlPackage.TEXT_TYPE__ROTATION:
                return ROTATION_EDEFAULT == null ? rotation != null : !ROTATION_EDEFAULT.equals(rotation);
            case OgmlPackage.TEXT_TYPE__TRANSFORM:
                return isSetTransform();
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
        result.append(" (alignment: ");
        if (alignmentESet) result.append(alignment); else result.append("<unset>");
        result.append(", decoration: ");
        if (decorationESet) result.append(decoration); else result.append("<unset>");
        result.append(", rotation: ");
        result.append(rotation);
        result.append(", transform: ");
        if (transformESet) result.append(transform); else result.append("<unset>");
        result.append(')');
        return result.toString();
    }

} //TextTypeImpl
