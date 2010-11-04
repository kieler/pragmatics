/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.ogdf.ogml.impl;

import java.util.Collection;

import net.ogdf.ogml.DataType;
import net.ogdf.ogml.GraphType;
import net.ogdf.ogml.LayoutType;
import net.ogdf.ogml.OgmlPackage;
import net.ogdf.ogml.StructureType;

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
 * An implementation of the model object '<em><b>Graph Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link net.ogdf.ogml.impl.GraphTypeImpl#getData <em>Data</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.GraphTypeImpl#getStructure <em>Structure</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.GraphTypeImpl#getLayout <em>Layout</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GraphTypeImpl extends EObjectImpl implements GraphType {
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
     * The cached value of the '{@link #getStructure() <em>Structure</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStructure()
     * @generated
     * @ordered
     */
    protected StructureType structure;

    /**
     * The cached value of the '{@link #getLayout() <em>Layout</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLayout()
     * @generated
     * @ordered
     */
    protected LayoutType layout;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected GraphTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return OgmlPackage.Literals.GRAPH_TYPE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<DataType> getData() {
        if (data == null) {
            data = new EObjectContainmentEList<DataType>(DataType.class, this, OgmlPackage.GRAPH_TYPE__DATA);
        }
        return data;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public StructureType getStructure() {
        return structure;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetStructure(StructureType newStructure, NotificationChain msgs) {
        StructureType oldStructure = structure;
        structure = newStructure;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OgmlPackage.GRAPH_TYPE__STRUCTURE, oldStructure, newStructure);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setStructure(StructureType newStructure) {
        if (newStructure != structure) {
            NotificationChain msgs = null;
            if (structure != null)
                msgs = ((InternalEObject)structure).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OgmlPackage.GRAPH_TYPE__STRUCTURE, null, msgs);
            if (newStructure != null)
                msgs = ((InternalEObject)newStructure).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OgmlPackage.GRAPH_TYPE__STRUCTURE, null, msgs);
            msgs = basicSetStructure(newStructure, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OgmlPackage.GRAPH_TYPE__STRUCTURE, newStructure, newStructure));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LayoutType getLayout() {
        return layout;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetLayout(LayoutType newLayout, NotificationChain msgs) {
        LayoutType oldLayout = layout;
        layout = newLayout;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OgmlPackage.GRAPH_TYPE__LAYOUT, oldLayout, newLayout);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLayout(LayoutType newLayout) {
        if (newLayout != layout) {
            NotificationChain msgs = null;
            if (layout != null)
                msgs = ((InternalEObject)layout).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OgmlPackage.GRAPH_TYPE__LAYOUT, null, msgs);
            if (newLayout != null)
                msgs = ((InternalEObject)newLayout).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OgmlPackage.GRAPH_TYPE__LAYOUT, null, msgs);
            msgs = basicSetLayout(newLayout, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OgmlPackage.GRAPH_TYPE__LAYOUT, newLayout, newLayout));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case OgmlPackage.GRAPH_TYPE__DATA:
                return ((InternalEList<?>)getData()).basicRemove(otherEnd, msgs);
            case OgmlPackage.GRAPH_TYPE__STRUCTURE:
                return basicSetStructure(null, msgs);
            case OgmlPackage.GRAPH_TYPE__LAYOUT:
                return basicSetLayout(null, msgs);
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
            case OgmlPackage.GRAPH_TYPE__DATA:
                return getData();
            case OgmlPackage.GRAPH_TYPE__STRUCTURE:
                return getStructure();
            case OgmlPackage.GRAPH_TYPE__LAYOUT:
                return getLayout();
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
            case OgmlPackage.GRAPH_TYPE__DATA:
                getData().clear();
                getData().addAll((Collection<? extends DataType>)newValue);
                return;
            case OgmlPackage.GRAPH_TYPE__STRUCTURE:
                setStructure((StructureType)newValue);
                return;
            case OgmlPackage.GRAPH_TYPE__LAYOUT:
                setLayout((LayoutType)newValue);
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
            case OgmlPackage.GRAPH_TYPE__DATA:
                getData().clear();
                return;
            case OgmlPackage.GRAPH_TYPE__STRUCTURE:
                setStructure((StructureType)null);
                return;
            case OgmlPackage.GRAPH_TYPE__LAYOUT:
                setLayout((LayoutType)null);
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
            case OgmlPackage.GRAPH_TYPE__DATA:
                return data != null && !data.isEmpty();
            case OgmlPackage.GRAPH_TYPE__STRUCTURE:
                return structure != null;
            case OgmlPackage.GRAPH_TYPE__LAYOUT:
                return layout != null;
        }
        return super.eIsSet(featureID);
    }

} //GraphTypeImpl
