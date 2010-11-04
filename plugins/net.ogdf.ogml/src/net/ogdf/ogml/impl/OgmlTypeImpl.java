/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.ogdf.ogml.impl;

import net.ogdf.ogml.GraphType;
import net.ogdf.ogml.KeysType;
import net.ogdf.ogml.OgmlPackage;
import net.ogdf.ogml.OgmlType;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link net.ogdf.ogml.impl.OgmlTypeImpl#getKeys <em>Keys</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.OgmlTypeImpl#getGraph <em>Graph</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OgmlTypeImpl extends EObjectImpl implements OgmlType {
    /**
     * The cached value of the '{@link #getKeys() <em>Keys</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getKeys()
     * @generated
     * @ordered
     */
    protected KeysType keys;

    /**
     * The cached value of the '{@link #getGraph() <em>Graph</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getGraph()
     * @generated
     * @ordered
     */
    protected GraphType graph;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected OgmlTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return OgmlPackage.Literals.OGML_TYPE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KeysType getKeys() {
        return keys;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetKeys(KeysType newKeys, NotificationChain msgs) {
        KeysType oldKeys = keys;
        keys = newKeys;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OgmlPackage.OGML_TYPE__KEYS, oldKeys, newKeys);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setKeys(KeysType newKeys) {
        if (newKeys != keys) {
            NotificationChain msgs = null;
            if (keys != null)
                msgs = ((InternalEObject)keys).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OgmlPackage.OGML_TYPE__KEYS, null, msgs);
            if (newKeys != null)
                msgs = ((InternalEObject)newKeys).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OgmlPackage.OGML_TYPE__KEYS, null, msgs);
            msgs = basicSetKeys(newKeys, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OgmlPackage.OGML_TYPE__KEYS, newKeys, newKeys));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GraphType getGraph() {
        return graph;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetGraph(GraphType newGraph, NotificationChain msgs) {
        GraphType oldGraph = graph;
        graph = newGraph;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OgmlPackage.OGML_TYPE__GRAPH, oldGraph, newGraph);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setGraph(GraphType newGraph) {
        if (newGraph != graph) {
            NotificationChain msgs = null;
            if (graph != null)
                msgs = ((InternalEObject)graph).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OgmlPackage.OGML_TYPE__GRAPH, null, msgs);
            if (newGraph != null)
                msgs = ((InternalEObject)newGraph).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OgmlPackage.OGML_TYPE__GRAPH, null, msgs);
            msgs = basicSetGraph(newGraph, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OgmlPackage.OGML_TYPE__GRAPH, newGraph, newGraph));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case OgmlPackage.OGML_TYPE__KEYS:
                return basicSetKeys(null, msgs);
            case OgmlPackage.OGML_TYPE__GRAPH:
                return basicSetGraph(null, msgs);
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
            case OgmlPackage.OGML_TYPE__KEYS:
                return getKeys();
            case OgmlPackage.OGML_TYPE__GRAPH:
                return getGraph();
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
            case OgmlPackage.OGML_TYPE__KEYS:
                setKeys((KeysType)newValue);
                return;
            case OgmlPackage.OGML_TYPE__GRAPH:
                setGraph((GraphType)newValue);
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
            case OgmlPackage.OGML_TYPE__KEYS:
                setKeys((KeysType)null);
                return;
            case OgmlPackage.OGML_TYPE__GRAPH:
                setGraph((GraphType)null);
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
            case OgmlPackage.OGML_TYPE__KEYS:
                return keys != null;
            case OgmlPackage.OGML_TYPE__GRAPH:
                return graph != null;
        }
        return super.eIsSet(featureID);
    }

} //OgmlTypeImpl
