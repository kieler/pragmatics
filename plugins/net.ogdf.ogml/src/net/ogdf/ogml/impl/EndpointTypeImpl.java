/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.ogdf.ogml.impl;

import java.math.BigInteger;

import net.ogdf.ogml.EndpointStylesType;
import net.ogdf.ogml.EndpointType;
import net.ogdf.ogml.OgmlPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Endpoint Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link net.ogdf.ogml.impl.EndpointTypeImpl#getColor <em>Color</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.EndpointTypeImpl#getIdRef <em>Id Ref</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.EndpointTypeImpl#getPort <em>Port</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.EndpointTypeImpl#getSize <em>Size</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.EndpointTypeImpl#getStyle <em>Style</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EndpointTypeImpl extends EObjectImpl implements EndpointType {
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
     * The default value of the '{@link #getIdRef() <em>Id Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getIdRef()
     * @generated
     * @ordered
     */
    protected static final String ID_REF_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getIdRef() <em>Id Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getIdRef()
     * @generated
     * @ordered
     */
    protected String idRef = ID_REF_EDEFAULT;

    /**
     * The default value of the '{@link #getPort() <em>Port</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPort()
     * @generated
     * @ordered
     */
    protected static final BigInteger PORT_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getPort() <em>Port</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPort()
     * @generated
     * @ordered
     */
    protected BigInteger port = PORT_EDEFAULT;

    /**
     * The default value of the '{@link #getSize() <em>Size</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSize()
     * @generated
     * @ordered
     */
    protected static final double SIZE_EDEFAULT = 0.0;

    /**
     * The cached value of the '{@link #getSize() <em>Size</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSize()
     * @generated
     * @ordered
     */
    protected double size = SIZE_EDEFAULT;

    /**
     * This is true if the Size attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean sizeESet;

    /**
     * The default value of the '{@link #getStyle() <em>Style</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStyle()
     * @generated
     * @ordered
     */
    protected static final EndpointStylesType STYLE_EDEFAULT = EndpointStylesType.ARROW;

    /**
     * The cached value of the '{@link #getStyle() <em>Style</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStyle()
     * @generated
     * @ordered
     */
    protected EndpointStylesType style = STYLE_EDEFAULT;

    /**
     * This is true if the Style attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean styleESet;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EndpointTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return OgmlPackage.Literals.ENDPOINT_TYPE;
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
            eNotify(new ENotificationImpl(this, Notification.SET, OgmlPackage.ENDPOINT_TYPE__COLOR, oldColor, color));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getIdRef() {
        return idRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setIdRef(String newIdRef) {
        String oldIdRef = idRef;
        idRef = newIdRef;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OgmlPackage.ENDPOINT_TYPE__ID_REF, oldIdRef, idRef));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BigInteger getPort() {
        return port;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPort(BigInteger newPort) {
        BigInteger oldPort = port;
        port = newPort;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OgmlPackage.ENDPOINT_TYPE__PORT, oldPort, port));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public double getSize() {
        return size;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSize(double newSize) {
        double oldSize = size;
        size = newSize;
        boolean oldSizeESet = sizeESet;
        sizeESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OgmlPackage.ENDPOINT_TYPE__SIZE, oldSize, size, !oldSizeESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetSize() {
        double oldSize = size;
        boolean oldSizeESet = sizeESet;
        size = SIZE_EDEFAULT;
        sizeESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, OgmlPackage.ENDPOINT_TYPE__SIZE, oldSize, SIZE_EDEFAULT, oldSizeESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetSize() {
        return sizeESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EndpointStylesType getStyle() {
        return style;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setStyle(EndpointStylesType newStyle) {
        EndpointStylesType oldStyle = style;
        style = newStyle == null ? STYLE_EDEFAULT : newStyle;
        boolean oldStyleESet = styleESet;
        styleESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OgmlPackage.ENDPOINT_TYPE__STYLE, oldStyle, style, !oldStyleESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetStyle() {
        EndpointStylesType oldStyle = style;
        boolean oldStyleESet = styleESet;
        style = STYLE_EDEFAULT;
        styleESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, OgmlPackage.ENDPOINT_TYPE__STYLE, oldStyle, STYLE_EDEFAULT, oldStyleESet));
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
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case OgmlPackage.ENDPOINT_TYPE__COLOR:
                return getColor();
            case OgmlPackage.ENDPOINT_TYPE__ID_REF:
                return getIdRef();
            case OgmlPackage.ENDPOINT_TYPE__PORT:
                return getPort();
            case OgmlPackage.ENDPOINT_TYPE__SIZE:
                return getSize();
            case OgmlPackage.ENDPOINT_TYPE__STYLE:
                return getStyle();
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
            case OgmlPackage.ENDPOINT_TYPE__COLOR:
                setColor((String)newValue);
                return;
            case OgmlPackage.ENDPOINT_TYPE__ID_REF:
                setIdRef((String)newValue);
                return;
            case OgmlPackage.ENDPOINT_TYPE__PORT:
                setPort((BigInteger)newValue);
                return;
            case OgmlPackage.ENDPOINT_TYPE__SIZE:
                setSize((Double)newValue);
                return;
            case OgmlPackage.ENDPOINT_TYPE__STYLE:
                setStyle((EndpointStylesType)newValue);
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
            case OgmlPackage.ENDPOINT_TYPE__COLOR:
                setColor(COLOR_EDEFAULT);
                return;
            case OgmlPackage.ENDPOINT_TYPE__ID_REF:
                setIdRef(ID_REF_EDEFAULT);
                return;
            case OgmlPackage.ENDPOINT_TYPE__PORT:
                setPort(PORT_EDEFAULT);
                return;
            case OgmlPackage.ENDPOINT_TYPE__SIZE:
                unsetSize();
                return;
            case OgmlPackage.ENDPOINT_TYPE__STYLE:
                unsetStyle();
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
            case OgmlPackage.ENDPOINT_TYPE__COLOR:
                return COLOR_EDEFAULT == null ? color != null : !COLOR_EDEFAULT.equals(color);
            case OgmlPackage.ENDPOINT_TYPE__ID_REF:
                return ID_REF_EDEFAULT == null ? idRef != null : !ID_REF_EDEFAULT.equals(idRef);
            case OgmlPackage.ENDPOINT_TYPE__PORT:
                return PORT_EDEFAULT == null ? port != null : !PORT_EDEFAULT.equals(port);
            case OgmlPackage.ENDPOINT_TYPE__SIZE:
                return isSetSize();
            case OgmlPackage.ENDPOINT_TYPE__STYLE:
                return isSetStyle();
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
        result.append(", idRef: ");
        result.append(idRef);
        result.append(", port: ");
        result.append(port);
        result.append(", size: ");
        if (sizeESet) result.append(size); else result.append("<unset>");
        result.append(", style: ");
        if (styleESet) result.append(style); else result.append("<unset>");
        result.append(')');
        return result.toString();
    }

} //EndpointTypeImpl
