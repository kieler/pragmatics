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
 *
 * $Id$
 */
package de.cau.cs.kieler.kaom.impl;

import de.cau.cs.kieler.kaom.Annotatable;
import de.cau.cs.kieler.kaom.Annotation;
import de.cau.cs.kieler.kaom.KaomPackage;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Annotatable</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.kaom.impl.AnnotatableImpl#getAnnotationMap <em>Annotation Map</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class AnnotatableImpl extends EObjectImpl implements Annotatable {
    /**
     * The cached value of the '{@link #getAnnotationMap() <em>Annotation Map</em>}' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAnnotationMap()
     * @generated
     * @ordered
     */
    protected EMap<String, Annotation> annotationMap;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected AnnotatableImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return KaomPackage.Literals.ANNOTATABLE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EMap<String, Annotation> getAnnotationMap() {
        if (annotationMap == null) {
            annotationMap = new EcoreEMap<String,Annotation>(KaomPackage.Literals.ANNOTATION_MAP_ENTRY, AnnotationMapEntryImpl.class, this, KaomPackage.ANNOTATABLE__ANNOTATION_MAP);
        }
        return annotationMap;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case KaomPackage.ANNOTATABLE__ANNOTATION_MAP:
                return ((InternalEList<?>)getAnnotationMap()).basicRemove(otherEnd, msgs);
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
            case KaomPackage.ANNOTATABLE__ANNOTATION_MAP:
                if (coreType) return getAnnotationMap();
                else return getAnnotationMap().map();
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
            case KaomPackage.ANNOTATABLE__ANNOTATION_MAP:
                ((EStructuralFeature.Setting)getAnnotationMap()).set(newValue);
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
            case KaomPackage.ANNOTATABLE__ANNOTATION_MAP:
                getAnnotationMap().clear();
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
            case KaomPackage.ANNOTATABLE__ANNOTATION_MAP:
                return annotationMap != null && !annotationMap.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //AnnotatableImpl
