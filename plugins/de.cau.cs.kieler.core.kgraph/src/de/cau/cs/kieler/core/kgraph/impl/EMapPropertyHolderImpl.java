/**
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.core.kgraph.impl;

import java.util.Map.Entry;

import de.cau.cs.kieler.core.kgraph.EMapPropertyHolder;
import de.cau.cs.kieler.core.kgraph.KGraphPackage;

import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.IPropertyHolder;

import de.cau.cs.kieler.core.util.Pair;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EMap Property Holder</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.kgraph.impl.EMapPropertyHolderImpl#getProperties <em>Properties</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class EMapPropertyHolderImpl extends EObjectImpl implements EMapPropertyHolder {
    /**
     * The cached value of the '{@link #getProperties() <em>Properties</em>}' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getProperties()
     * @generated
     * @ordered
     */
    protected EMap<IProperty<?>, Object> properties;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EMapPropertyHolderImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return KGraphPackage.Literals.EMAP_PROPERTY_HOLDER;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EMap<IProperty<?>, Object> getProperties() {
        if (properties == null) {
            properties = new EcoreEMap<IProperty<?>,Object>(KGraphPackage.Literals.IPROPERTY_TO_OBJECT_MAP, IPropertyToObjectMapImpl.class, this, KGraphPackage.EMAP_PROPERTY_HOLDER__PROPERTIES);
        }
        return properties;
    }
    
    /**
     * <!-- begin-user-doc -->
     * {@inheritDoc}
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public void setProperty(IProperty<?> property, Object value) {
        getProperties().put(property, value);
    }

    /**
     * <!-- begin-user-doc -->
     * {@inheritDoc}
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public <T> T getProperty(IProperty<T> property) {
        @SuppressWarnings("unchecked")
        T value = (T) getProperties().get(property);
        if (value != null) {
            return value;
        }
        return property.getDefault();
    }

    /**
     * <!-- begin-user-doc -->
     * {@inheritDoc}
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public void copyProperties(IPropertyHolder holder) {
        if (holder instanceof EMapPropertyHolder) {
            this.getProperties().putAll(((EMapPropertyHolder) holder).getProperties());
        } else {
            for (Pair<IProperty<?>, Object> prop : holder.getAllProperties()) {
                setProperty(prop.getFirst(), prop.getSecond());
            }
        }
    }

    /**
     * <!-- begin-user-doc -->
     * {@inheritDoc}
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public EList<Pair<IProperty<?>, Object>> getAllProperties() {
        EMap<IProperty<?>, Object> propMap = getProperties();
        EList<Pair<IProperty<?>, Object>> list =
            new BasicEList<Pair<IProperty<?>, Object>>(propMap.size());
        for (Entry<IProperty<?>, Object> entry : propMap.entrySet()) {
            list.add(new Pair<IProperty<?>, Object>(entry.getKey(), entry.getValue()));
        }
        return list;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case KGraphPackage.EMAP_PROPERTY_HOLDER__PROPERTIES:
                return ((InternalEList<?>)getProperties()).basicRemove(otherEnd, msgs);
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
            case KGraphPackage.EMAP_PROPERTY_HOLDER__PROPERTIES:
                if (coreType) return getProperties();
                else return getProperties().map();
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
            case KGraphPackage.EMAP_PROPERTY_HOLDER__PROPERTIES:
                ((EStructuralFeature.Setting)getProperties()).set(newValue);
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
            case KGraphPackage.EMAP_PROPERTY_HOLDER__PROPERTIES:
                getProperties().clear();
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
            case KGraphPackage.EMAP_PROPERTY_HOLDER__PROPERTIES:
                return properties != null && !properties.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //EMapPropertyHolderImpl
