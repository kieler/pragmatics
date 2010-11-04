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
import net.ogdf.ogml.ConstraintsType;
import net.ogdf.ogml.EdgeConstraintType;
import net.ogdf.ogml.IntType;
import net.ogdf.ogml.LabelConstraintType;
import net.ogdf.ogml.NodeConstraintType;
import net.ogdf.ogml.NumberType;
import net.ogdf.ogml.OgmlPackage;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Constraints Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link net.ogdf.ogml.impl.ConstraintsTypeImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.ConstraintsTypeImpl#getNumber <em>Number</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.ConstraintsTypeImpl#getInt <em>Int</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.ConstraintsTypeImpl#getBoolean <em>Boolean</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.ConstraintsTypeImpl#getNodeRef <em>Node Ref</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.ConstraintsTypeImpl#getEdgeRef <em>Edge Ref</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.ConstraintsTypeImpl#getLabelRef <em>Label Ref</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.ConstraintsTypeImpl#getComposed <em>Composed</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConstraintsTypeImpl extends EObjectImpl implements ConstraintsType {
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
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ConstraintsTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return OgmlPackage.Literals.CONSTRAINTS_TYPE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureMap getGroup() {
        if (group == null) {
            group = new BasicFeatureMap(this, OgmlPackage.CONSTRAINTS_TYPE__GROUP);
        }
        return group;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<NumberType> getNumber() {
        return getGroup().list(OgmlPackage.Literals.CONSTRAINTS_TYPE__NUMBER);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<IntType> getInt() {
        return getGroup().list(OgmlPackage.Literals.CONSTRAINTS_TYPE__INT);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<BooleanType> getBoolean() {
        return getGroup().list(OgmlPackage.Literals.CONSTRAINTS_TYPE__BOOLEAN);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<NodeConstraintType> getNodeRef() {
        return getGroup().list(OgmlPackage.Literals.CONSTRAINTS_TYPE__NODE_REF);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<EdgeConstraintType> getEdgeRef() {
        return getGroup().list(OgmlPackage.Literals.CONSTRAINTS_TYPE__EDGE_REF);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<LabelConstraintType> getLabelRef() {
        return getGroup().list(OgmlPackage.Literals.CONSTRAINTS_TYPE__LABEL_REF);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<ComposedType> getComposed() {
        return getGroup().list(OgmlPackage.Literals.CONSTRAINTS_TYPE__COMPOSED);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case OgmlPackage.CONSTRAINTS_TYPE__GROUP:
                return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
            case OgmlPackage.CONSTRAINTS_TYPE__NUMBER:
                return ((InternalEList<?>)getNumber()).basicRemove(otherEnd, msgs);
            case OgmlPackage.CONSTRAINTS_TYPE__INT:
                return ((InternalEList<?>)getInt()).basicRemove(otherEnd, msgs);
            case OgmlPackage.CONSTRAINTS_TYPE__BOOLEAN:
                return ((InternalEList<?>)getBoolean()).basicRemove(otherEnd, msgs);
            case OgmlPackage.CONSTRAINTS_TYPE__NODE_REF:
                return ((InternalEList<?>)getNodeRef()).basicRemove(otherEnd, msgs);
            case OgmlPackage.CONSTRAINTS_TYPE__EDGE_REF:
                return ((InternalEList<?>)getEdgeRef()).basicRemove(otherEnd, msgs);
            case OgmlPackage.CONSTRAINTS_TYPE__LABEL_REF:
                return ((InternalEList<?>)getLabelRef()).basicRemove(otherEnd, msgs);
            case OgmlPackage.CONSTRAINTS_TYPE__COMPOSED:
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
            case OgmlPackage.CONSTRAINTS_TYPE__GROUP:
                if (coreType) return getGroup();
                return ((FeatureMap.Internal)getGroup()).getWrapper();
            case OgmlPackage.CONSTRAINTS_TYPE__NUMBER:
                return getNumber();
            case OgmlPackage.CONSTRAINTS_TYPE__INT:
                return getInt();
            case OgmlPackage.CONSTRAINTS_TYPE__BOOLEAN:
                return getBoolean();
            case OgmlPackage.CONSTRAINTS_TYPE__NODE_REF:
                return getNodeRef();
            case OgmlPackage.CONSTRAINTS_TYPE__EDGE_REF:
                return getEdgeRef();
            case OgmlPackage.CONSTRAINTS_TYPE__LABEL_REF:
                return getLabelRef();
            case OgmlPackage.CONSTRAINTS_TYPE__COMPOSED:
                return getComposed();
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
            case OgmlPackage.CONSTRAINTS_TYPE__GROUP:
                ((FeatureMap.Internal)getGroup()).set(newValue);
                return;
            case OgmlPackage.CONSTRAINTS_TYPE__NUMBER:
                getNumber().clear();
                getNumber().addAll((Collection<? extends NumberType>)newValue);
                return;
            case OgmlPackage.CONSTRAINTS_TYPE__INT:
                getInt().clear();
                getInt().addAll((Collection<? extends IntType>)newValue);
                return;
            case OgmlPackage.CONSTRAINTS_TYPE__BOOLEAN:
                getBoolean().clear();
                getBoolean().addAll((Collection<? extends BooleanType>)newValue);
                return;
            case OgmlPackage.CONSTRAINTS_TYPE__NODE_REF:
                getNodeRef().clear();
                getNodeRef().addAll((Collection<? extends NodeConstraintType>)newValue);
                return;
            case OgmlPackage.CONSTRAINTS_TYPE__EDGE_REF:
                getEdgeRef().clear();
                getEdgeRef().addAll((Collection<? extends EdgeConstraintType>)newValue);
                return;
            case OgmlPackage.CONSTRAINTS_TYPE__LABEL_REF:
                getLabelRef().clear();
                getLabelRef().addAll((Collection<? extends LabelConstraintType>)newValue);
                return;
            case OgmlPackage.CONSTRAINTS_TYPE__COMPOSED:
                getComposed().clear();
                getComposed().addAll((Collection<? extends ComposedType>)newValue);
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
            case OgmlPackage.CONSTRAINTS_TYPE__GROUP:
                getGroup().clear();
                return;
            case OgmlPackage.CONSTRAINTS_TYPE__NUMBER:
                getNumber().clear();
                return;
            case OgmlPackage.CONSTRAINTS_TYPE__INT:
                getInt().clear();
                return;
            case OgmlPackage.CONSTRAINTS_TYPE__BOOLEAN:
                getBoolean().clear();
                return;
            case OgmlPackage.CONSTRAINTS_TYPE__NODE_REF:
                getNodeRef().clear();
                return;
            case OgmlPackage.CONSTRAINTS_TYPE__EDGE_REF:
                getEdgeRef().clear();
                return;
            case OgmlPackage.CONSTRAINTS_TYPE__LABEL_REF:
                getLabelRef().clear();
                return;
            case OgmlPackage.CONSTRAINTS_TYPE__COMPOSED:
                getComposed().clear();
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
            case OgmlPackage.CONSTRAINTS_TYPE__GROUP:
                return group != null && !group.isEmpty();
            case OgmlPackage.CONSTRAINTS_TYPE__NUMBER:
                return !getNumber().isEmpty();
            case OgmlPackage.CONSTRAINTS_TYPE__INT:
                return !getInt().isEmpty();
            case OgmlPackage.CONSTRAINTS_TYPE__BOOLEAN:
                return !getBoolean().isEmpty();
            case OgmlPackage.CONSTRAINTS_TYPE__NODE_REF:
                return !getNodeRef().isEmpty();
            case OgmlPackage.CONSTRAINTS_TYPE__EDGE_REF:
                return !getEdgeRef().isEmpty();
            case OgmlPackage.CONSTRAINTS_TYPE__LABEL_REF:
                return !getLabelRef().isEmpty();
            case OgmlPackage.CONSTRAINTS_TYPE__COMPOSED:
                return !getComposed().isEmpty();
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
        result.append(')');
        return result.toString();
    }

} //ConstraintsTypeImpl
