/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.ogdf.ogml.impl;

import java.util.Collection;

import net.ogdf.ogml.DataType;
import net.ogdf.ogml.EdgeType;
import net.ogdf.ogml.LabelType;
import net.ogdf.ogml.NodeType;
import net.ogdf.ogml.OgmlPackage;
import net.ogdf.ogml.StructureType;

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
 * An implementation of the model object '<em><b>Structure Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link net.ogdf.ogml.impl.StructureTypeImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.StructureTypeImpl#getData <em>Data</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.StructureTypeImpl#getNode <em>Node</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.StructureTypeImpl#getEdge <em>Edge</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.StructureTypeImpl#getLabel <em>Label</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StructureTypeImpl extends EObjectImpl implements StructureType {
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
    protected StructureTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return OgmlPackage.Literals.STRUCTURE_TYPE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureMap getGroup() {
        if (group == null) {
            group = new BasicFeatureMap(this, OgmlPackage.STRUCTURE_TYPE__GROUP);
        }
        return group;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<DataType> getData() {
        return getGroup().list(OgmlPackage.Literals.STRUCTURE_TYPE__DATA);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<NodeType> getNode() {
        return getGroup().list(OgmlPackage.Literals.STRUCTURE_TYPE__NODE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<EdgeType> getEdge() {
        return getGroup().list(OgmlPackage.Literals.STRUCTURE_TYPE__EDGE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<LabelType> getLabel() {
        return getGroup().list(OgmlPackage.Literals.STRUCTURE_TYPE__LABEL);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case OgmlPackage.STRUCTURE_TYPE__GROUP:
                return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
            case OgmlPackage.STRUCTURE_TYPE__DATA:
                return ((InternalEList<?>)getData()).basicRemove(otherEnd, msgs);
            case OgmlPackage.STRUCTURE_TYPE__NODE:
                return ((InternalEList<?>)getNode()).basicRemove(otherEnd, msgs);
            case OgmlPackage.STRUCTURE_TYPE__EDGE:
                return ((InternalEList<?>)getEdge()).basicRemove(otherEnd, msgs);
            case OgmlPackage.STRUCTURE_TYPE__LABEL:
                return ((InternalEList<?>)getLabel()).basicRemove(otherEnd, msgs);
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
            case OgmlPackage.STRUCTURE_TYPE__GROUP:
                if (coreType) return getGroup();
                return ((FeatureMap.Internal)getGroup()).getWrapper();
            case OgmlPackage.STRUCTURE_TYPE__DATA:
                return getData();
            case OgmlPackage.STRUCTURE_TYPE__NODE:
                return getNode();
            case OgmlPackage.STRUCTURE_TYPE__EDGE:
                return getEdge();
            case OgmlPackage.STRUCTURE_TYPE__LABEL:
                return getLabel();
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
            case OgmlPackage.STRUCTURE_TYPE__GROUP:
                ((FeatureMap.Internal)getGroup()).set(newValue);
                return;
            case OgmlPackage.STRUCTURE_TYPE__DATA:
                getData().clear();
                getData().addAll((Collection<? extends DataType>)newValue);
                return;
            case OgmlPackage.STRUCTURE_TYPE__NODE:
                getNode().clear();
                getNode().addAll((Collection<? extends NodeType>)newValue);
                return;
            case OgmlPackage.STRUCTURE_TYPE__EDGE:
                getEdge().clear();
                getEdge().addAll((Collection<? extends EdgeType>)newValue);
                return;
            case OgmlPackage.STRUCTURE_TYPE__LABEL:
                getLabel().clear();
                getLabel().addAll((Collection<? extends LabelType>)newValue);
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
            case OgmlPackage.STRUCTURE_TYPE__GROUP:
                getGroup().clear();
                return;
            case OgmlPackage.STRUCTURE_TYPE__DATA:
                getData().clear();
                return;
            case OgmlPackage.STRUCTURE_TYPE__NODE:
                getNode().clear();
                return;
            case OgmlPackage.STRUCTURE_TYPE__EDGE:
                getEdge().clear();
                return;
            case OgmlPackage.STRUCTURE_TYPE__LABEL:
                getLabel().clear();
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
            case OgmlPackage.STRUCTURE_TYPE__GROUP:
                return group != null && !group.isEmpty();
            case OgmlPackage.STRUCTURE_TYPE__DATA:
                return !getData().isEmpty();
            case OgmlPackage.STRUCTURE_TYPE__NODE:
                return !getNode().isEmpty();
            case OgmlPackage.STRUCTURE_TYPE__EDGE:
                return !getEdge().isEmpty();
            case OgmlPackage.STRUCTURE_TYPE__LABEL:
                return !getLabel().isEmpty();
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

} //StructureTypeImpl
