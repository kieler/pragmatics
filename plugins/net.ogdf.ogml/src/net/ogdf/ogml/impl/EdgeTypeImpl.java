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
import net.ogdf.ogml.OgmlPackage;
import net.ogdf.ogml.SourceTargetType;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Edge Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link net.ogdf.ogml.impl.EdgeTypeImpl#getData <em>Data</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.EdgeTypeImpl#getLabel <em>Label</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.EdgeTypeImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.EdgeTypeImpl#getSource <em>Source</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.EdgeTypeImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.EdgeTypeImpl#getLabel1 <em>Label1</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.EdgeTypeImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EdgeTypeImpl extends EObjectImpl implements EdgeType {
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
     * The cached value of the '{@link #getLabel() <em>Label</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLabel()
     * @generated
     * @ordered
     */
    protected EList<LabelType> label;

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
     * The cached value of the '{@link #getLabel1() <em>Label1</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLabel1()
     * @generated
     * @ordered
     */
    protected EList<LabelType> label1;

    /**
     * The default value of the '{@link #getId() <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getId()
     * @generated
     * @ordered
     */
    protected static final String ID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getId()
     * @generated
     * @ordered
     */
    protected String id = ID_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EdgeTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return OgmlPackage.Literals.EDGE_TYPE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<DataType> getData() {
        if (data == null) {
            data = new EObjectContainmentEList<DataType>(DataType.class, this, OgmlPackage.EDGE_TYPE__DATA);
        }
        return data;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<LabelType> getLabel() {
        if (label == null) {
            label = new EObjectContainmentEList<LabelType>(LabelType.class, this, OgmlPackage.EDGE_TYPE__LABEL);
        }
        return label;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureMap getGroup() {
        if (group == null) {
            group = new BasicFeatureMap(this, OgmlPackage.EDGE_TYPE__GROUP);
        }
        return group;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<SourceTargetType> getSource() {
        return getGroup().list(OgmlPackage.Literals.EDGE_TYPE__SOURCE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<SourceTargetType> getTarget() {
        return getGroup().list(OgmlPackage.Literals.EDGE_TYPE__TARGET);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<LabelType> getLabel1() {
        if (label1 == null) {
            label1 = new EObjectContainmentEList<LabelType>(LabelType.class, this, OgmlPackage.EDGE_TYPE__LABEL1);
        }
        return label1;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getId() {
        return id;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setId(String newId) {
        String oldId = id;
        id = newId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OgmlPackage.EDGE_TYPE__ID, oldId, id));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case OgmlPackage.EDGE_TYPE__DATA:
                return ((InternalEList<?>)getData()).basicRemove(otherEnd, msgs);
            case OgmlPackage.EDGE_TYPE__LABEL:
                return ((InternalEList<?>)getLabel()).basicRemove(otherEnd, msgs);
            case OgmlPackage.EDGE_TYPE__GROUP:
                return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
            case OgmlPackage.EDGE_TYPE__SOURCE:
                return ((InternalEList<?>)getSource()).basicRemove(otherEnd, msgs);
            case OgmlPackage.EDGE_TYPE__TARGET:
                return ((InternalEList<?>)getTarget()).basicRemove(otherEnd, msgs);
            case OgmlPackage.EDGE_TYPE__LABEL1:
                return ((InternalEList<?>)getLabel1()).basicRemove(otherEnd, msgs);
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
            case OgmlPackage.EDGE_TYPE__DATA:
                return getData();
            case OgmlPackage.EDGE_TYPE__LABEL:
                return getLabel();
            case OgmlPackage.EDGE_TYPE__GROUP:
                if (coreType) return getGroup();
                return ((FeatureMap.Internal)getGroup()).getWrapper();
            case OgmlPackage.EDGE_TYPE__SOURCE:
                return getSource();
            case OgmlPackage.EDGE_TYPE__TARGET:
                return getTarget();
            case OgmlPackage.EDGE_TYPE__LABEL1:
                return getLabel1();
            case OgmlPackage.EDGE_TYPE__ID:
                return getId();
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
            case OgmlPackage.EDGE_TYPE__DATA:
                getData().clear();
                getData().addAll((Collection<? extends DataType>)newValue);
                return;
            case OgmlPackage.EDGE_TYPE__LABEL:
                getLabel().clear();
                getLabel().addAll((Collection<? extends LabelType>)newValue);
                return;
            case OgmlPackage.EDGE_TYPE__GROUP:
                ((FeatureMap.Internal)getGroup()).set(newValue);
                return;
            case OgmlPackage.EDGE_TYPE__SOURCE:
                getSource().clear();
                getSource().addAll((Collection<? extends SourceTargetType>)newValue);
                return;
            case OgmlPackage.EDGE_TYPE__TARGET:
                getTarget().clear();
                getTarget().addAll((Collection<? extends SourceTargetType>)newValue);
                return;
            case OgmlPackage.EDGE_TYPE__LABEL1:
                getLabel1().clear();
                getLabel1().addAll((Collection<? extends LabelType>)newValue);
                return;
            case OgmlPackage.EDGE_TYPE__ID:
                setId((String)newValue);
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
            case OgmlPackage.EDGE_TYPE__DATA:
                getData().clear();
                return;
            case OgmlPackage.EDGE_TYPE__LABEL:
                getLabel().clear();
                return;
            case OgmlPackage.EDGE_TYPE__GROUP:
                getGroup().clear();
                return;
            case OgmlPackage.EDGE_TYPE__SOURCE:
                getSource().clear();
                return;
            case OgmlPackage.EDGE_TYPE__TARGET:
                getTarget().clear();
                return;
            case OgmlPackage.EDGE_TYPE__LABEL1:
                getLabel1().clear();
                return;
            case OgmlPackage.EDGE_TYPE__ID:
                setId(ID_EDEFAULT);
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
            case OgmlPackage.EDGE_TYPE__DATA:
                return data != null && !data.isEmpty();
            case OgmlPackage.EDGE_TYPE__LABEL:
                return label != null && !label.isEmpty();
            case OgmlPackage.EDGE_TYPE__GROUP:
                return group != null && !group.isEmpty();
            case OgmlPackage.EDGE_TYPE__SOURCE:
                return !getSource().isEmpty();
            case OgmlPackage.EDGE_TYPE__TARGET:
                return !getTarget().isEmpty();
            case OgmlPackage.EDGE_TYPE__LABEL1:
                return label1 != null && !label1.isEmpty();
            case OgmlPackage.EDGE_TYPE__ID:
                return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
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
        result.append(", id: ");
        result.append(id);
        result.append(')');
        return result.toString();
    }

} //EdgeTypeImpl
