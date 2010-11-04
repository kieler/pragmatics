/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.ogdf.ogml.impl;

import java.util.Collection;

import net.ogdf.ogml.BooleanType;
import net.ogdf.ogml.ComposedType;
import net.ogdf.ogml.EdgeConstraintType;
import net.ogdf.ogml.IntType;
import net.ogdf.ogml.LabelConstraintType;
import net.ogdf.ogml.NodeConstraintType;
import net.ogdf.ogml.NumberType;
import net.ogdf.ogml.OgmlPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Composed Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link net.ogdf.ogml.impl.ComposedTypeImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.ComposedTypeImpl#getNumber <em>Number</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.ComposedTypeImpl#getInt <em>Int</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.ComposedTypeImpl#getBoolean <em>Boolean</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.ComposedTypeImpl#getNode <em>Node</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.ComposedTypeImpl#getEdge <em>Edge</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.ComposedTypeImpl#getLabel <em>Label</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.ComposedTypeImpl#getComposed <em>Composed</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.ComposedTypeImpl#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ComposedTypeImpl extends EObjectImpl implements ComposedType {
    /**
     * The cached value of the '{@link #getGroup() <em>Group</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getGroup()
     * @generated
     * @ordered
     */
    protected FeatureMap group;

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
    protected ComposedTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return OgmlPackage.Literals.COMPOSED_TYPE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureMap getGroup() {
        if (group == null) {
            group = new BasicFeatureMap(this, OgmlPackage.COMPOSED_TYPE__GROUP);
        }
        return group;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<NumberType> getNumber() {
        return getGroup().list(OgmlPackage.Literals.COMPOSED_TYPE__NUMBER);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<IntType> getInt() {
        return getGroup().list(OgmlPackage.Literals.COMPOSED_TYPE__INT);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<BooleanType> getBoolean() {
        return getGroup().list(OgmlPackage.Literals.COMPOSED_TYPE__BOOLEAN);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<NodeConstraintType> getNode() {
        return getGroup().list(OgmlPackage.Literals.COMPOSED_TYPE__NODE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<EdgeConstraintType> getEdge() {
        return getGroup().list(OgmlPackage.Literals.COMPOSED_TYPE__EDGE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<LabelConstraintType> getLabel() {
        return getGroup().list(OgmlPackage.Literals.COMPOSED_TYPE__LABEL);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<ComposedType> getComposed() {
        return getGroup().list(OgmlPackage.Literals.COMPOSED_TYPE__COMPOSED);
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
            eNotify(new ENotificationImpl(this, Notification.SET, OgmlPackage.COMPOSED_TYPE__NAME, oldName, name));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case OgmlPackage.COMPOSED_TYPE__GROUP:
                return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
            case OgmlPackage.COMPOSED_TYPE__NUMBER:
                return ((InternalEList<?>)getNumber()).basicRemove(otherEnd, msgs);
            case OgmlPackage.COMPOSED_TYPE__INT:
                return ((InternalEList<?>)getInt()).basicRemove(otherEnd, msgs);
            case OgmlPackage.COMPOSED_TYPE__BOOLEAN:
                return ((InternalEList<?>)getBoolean()).basicRemove(otherEnd, msgs);
            case OgmlPackage.COMPOSED_TYPE__NODE:
                return ((InternalEList<?>)getNode()).basicRemove(otherEnd, msgs);
            case OgmlPackage.COMPOSED_TYPE__EDGE:
                return ((InternalEList<?>)getEdge()).basicRemove(otherEnd, msgs);
            case OgmlPackage.COMPOSED_TYPE__LABEL:
                return ((InternalEList<?>)getLabel()).basicRemove(otherEnd, msgs);
            case OgmlPackage.COMPOSED_TYPE__COMPOSED:
                return ((InternalEList<?>)getComposed()).basicRemove(otherEnd, msgs);
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
            case OgmlPackage.COMPOSED_TYPE__GROUP:
                if (coreType) return getGroup();
                return ((FeatureMap.Internal)getGroup()).getWrapper();
            case OgmlPackage.COMPOSED_TYPE__NUMBER:
                return getNumber();
            case OgmlPackage.COMPOSED_TYPE__INT:
                return getInt();
            case OgmlPackage.COMPOSED_TYPE__BOOLEAN:
                return getBoolean();
            case OgmlPackage.COMPOSED_TYPE__NODE:
                return getNode();
            case OgmlPackage.COMPOSED_TYPE__EDGE:
                return getEdge();
            case OgmlPackage.COMPOSED_TYPE__LABEL:
                return getLabel();
            case OgmlPackage.COMPOSED_TYPE__COMPOSED:
                return getComposed();
            case OgmlPackage.COMPOSED_TYPE__NAME:
                return getName();
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
            case OgmlPackage.COMPOSED_TYPE__GROUP:
                ((FeatureMap.Internal)getGroup()).set(newValue);
                return;
            case OgmlPackage.COMPOSED_TYPE__NUMBER:
                getNumber().clear();
                getNumber().addAll((Collection<? extends NumberType>)newValue);
                return;
            case OgmlPackage.COMPOSED_TYPE__INT:
                getInt().clear();
                getInt().addAll((Collection<? extends IntType>)newValue);
                return;
            case OgmlPackage.COMPOSED_TYPE__BOOLEAN:
                getBoolean().clear();
                getBoolean().addAll((Collection<? extends BooleanType>)newValue);
                return;
            case OgmlPackage.COMPOSED_TYPE__NODE:
                getNode().clear();
                getNode().addAll((Collection<? extends NodeConstraintType>)newValue);
                return;
            case OgmlPackage.COMPOSED_TYPE__EDGE:
                getEdge().clear();
                getEdge().addAll((Collection<? extends EdgeConstraintType>)newValue);
                return;
            case OgmlPackage.COMPOSED_TYPE__LABEL:
                getLabel().clear();
                getLabel().addAll((Collection<? extends LabelConstraintType>)newValue);
                return;
            case OgmlPackage.COMPOSED_TYPE__COMPOSED:
                getComposed().clear();
                getComposed().addAll((Collection<? extends ComposedType>)newValue);
                return;
            case OgmlPackage.COMPOSED_TYPE__NAME:
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
            case OgmlPackage.COMPOSED_TYPE__GROUP:
                getGroup().clear();
                return;
            case OgmlPackage.COMPOSED_TYPE__NUMBER:
                getNumber().clear();
                return;
            case OgmlPackage.COMPOSED_TYPE__INT:
                getInt().clear();
                return;
            case OgmlPackage.COMPOSED_TYPE__BOOLEAN:
                getBoolean().clear();
                return;
            case OgmlPackage.COMPOSED_TYPE__NODE:
                getNode().clear();
                return;
            case OgmlPackage.COMPOSED_TYPE__EDGE:
                getEdge().clear();
                return;
            case OgmlPackage.COMPOSED_TYPE__LABEL:
                getLabel().clear();
                return;
            case OgmlPackage.COMPOSED_TYPE__COMPOSED:
                getComposed().clear();
                return;
            case OgmlPackage.COMPOSED_TYPE__NAME:
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
            case OgmlPackage.COMPOSED_TYPE__GROUP:
                return group != null && !group.isEmpty();
            case OgmlPackage.COMPOSED_TYPE__NUMBER:
                return !getNumber().isEmpty();
            case OgmlPackage.COMPOSED_TYPE__INT:
                return !getInt().isEmpty();
            case OgmlPackage.COMPOSED_TYPE__BOOLEAN:
                return !getBoolean().isEmpty();
            case OgmlPackage.COMPOSED_TYPE__NODE:
                return !getNode().isEmpty();
            case OgmlPackage.COMPOSED_TYPE__EDGE:
                return !getEdge().isEmpty();
            case OgmlPackage.COMPOSED_TYPE__LABEL:
                return !getLabel().isEmpty();
            case OgmlPackage.COMPOSED_TYPE__COMPOSED:
                return !getComposed().isEmpty();
            case OgmlPackage.COMPOSED_TYPE__NAME:
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
        result.append(" (group: ");
        result.append(group);
        result.append(", name: ");
        result.append(name);
        result.append(')');
        return result.toString();
    }

} //ComposedTypeImpl
