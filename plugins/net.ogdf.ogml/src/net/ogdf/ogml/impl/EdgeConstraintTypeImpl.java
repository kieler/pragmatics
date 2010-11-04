/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.ogdf.ogml.impl;

import net.ogdf.ogml.EdgeConstraintType;
import net.ogdf.ogml.OgmlPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Edge Constraint Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link net.ogdf.ogml.impl.EdgeConstraintTypeImpl#getIdRef <em>Id Ref</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.EdgeConstraintTypeImpl#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EdgeConstraintTypeImpl extends EObjectImpl implements EdgeConstraintType {
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
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String name = NAME_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EdgeConstraintTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return OgmlPackage.Literals.EDGE_CONSTRAINT_TYPE;
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
            eNotify(new ENotificationImpl(this, Notification.SET, OgmlPackage.EDGE_CONSTRAINT_TYPE__ID_REF, oldIdRef, idRef));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OgmlPackage.EDGE_CONSTRAINT_TYPE__NAME, oldName, name));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case OgmlPackage.EDGE_CONSTRAINT_TYPE__ID_REF:
                return getIdRef();
            case OgmlPackage.EDGE_CONSTRAINT_TYPE__NAME:
                return getName();
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
            case OgmlPackage.EDGE_CONSTRAINT_TYPE__ID_REF:
                setIdRef((String)newValue);
                return;
            case OgmlPackage.EDGE_CONSTRAINT_TYPE__NAME:
                setName((String)newValue);
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
            case OgmlPackage.EDGE_CONSTRAINT_TYPE__ID_REF:
                setIdRef(ID_REF_EDEFAULT);
                return;
            case OgmlPackage.EDGE_CONSTRAINT_TYPE__NAME:
                setName(NAME_EDEFAULT);
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
            case OgmlPackage.EDGE_CONSTRAINT_TYPE__ID_REF:
                return ID_REF_EDEFAULT == null ? idRef != null : !ID_REF_EDEFAULT.equals(idRef);
            case OgmlPackage.EDGE_CONSTRAINT_TYPE__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
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
        result.append(" (idRef: ");
        result.append(idRef);
        result.append(", name: ");
        result.append(name);
        result.append(')');
        return result.toString();
    }

} //EdgeConstraintTypeImpl
