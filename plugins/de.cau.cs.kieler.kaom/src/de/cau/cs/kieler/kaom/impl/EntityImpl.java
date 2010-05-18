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

import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.kaom.KaomPackage;
import de.cau.cs.kieler.kaom.Link;
import de.cau.cs.kieler.kaom.Port;
import de.cau.cs.kieler.kaom.Relation;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Entity</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.kaom.impl.EntityImpl#getChildEntities <em>Child Entities</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kaom.impl.EntityImpl#getChildLinks <em>Child Links</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kaom.impl.EntityImpl#getChildRelations <em>Child Relations</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kaom.impl.EntityImpl#getChildPorts <em>Child Ports</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class EntityImpl extends NamedObjectImpl implements Entity {
    /**
     * The cached value of the '{@link #getChildEntities() <em>Child Entities</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getChildEntities()
     * @generated
     * @ordered
     */
    protected EList<Entity> childEntities;

    /**
     * The cached value of the '{@link #getChildLinks() <em>Child Links</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getChildLinks()
     * @generated
     * @ordered
     */
    protected EList<Link> childLinks;

    /**
     * The cached value of the '{@link #getChildRelations() <em>Child Relations</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getChildRelations()
     * @generated
     * @ordered
     */
    protected EList<Relation> childRelations;

    /**
     * The cached value of the '{@link #getChildPorts() <em>Child Ports</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getChildPorts()
     * @generated
     * @ordered
     */
    protected EList<Port> childPorts;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EntityImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return KaomPackage.Literals.ENTITY;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<Entity> getChildEntities() {
        if (childEntities == null) {
            childEntities = new EObjectContainmentEList<Entity>(Entity.class, this, KaomPackage.ENTITY__CHILD_ENTITIES);
        }
        return childEntities;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<Link> getChildLinks() {
        if (childLinks == null) {
            childLinks = new EObjectContainmentEList<Link>(Link.class, this, KaomPackage.ENTITY__CHILD_LINKS);
        }
        return childLinks;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<Relation> getChildRelations() {
        if (childRelations == null) {
            childRelations = new EObjectContainmentEList<Relation>(Relation.class, this, KaomPackage.ENTITY__CHILD_RELATIONS);
        }
        return childRelations;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<Port> getChildPorts() {
        if (childPorts == null) {
            childPorts = new EObjectContainmentEList<Port>(Port.class, this, KaomPackage.ENTITY__CHILD_PORTS);
        }
        return childPorts;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case KaomPackage.ENTITY__CHILD_ENTITIES:
                return ((InternalEList<?>)getChildEntities()).basicRemove(otherEnd, msgs);
            case KaomPackage.ENTITY__CHILD_LINKS:
                return ((InternalEList<?>)getChildLinks()).basicRemove(otherEnd, msgs);
            case KaomPackage.ENTITY__CHILD_RELATIONS:
                return ((InternalEList<?>)getChildRelations()).basicRemove(otherEnd, msgs);
            case KaomPackage.ENTITY__CHILD_PORTS:
                return ((InternalEList<?>)getChildPorts()).basicRemove(otherEnd, msgs);
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
            case KaomPackage.ENTITY__CHILD_ENTITIES:
                return getChildEntities();
            case KaomPackage.ENTITY__CHILD_LINKS:
                return getChildLinks();
            case KaomPackage.ENTITY__CHILD_RELATIONS:
                return getChildRelations();
            case KaomPackage.ENTITY__CHILD_PORTS:
                return getChildPorts();
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
            case KaomPackage.ENTITY__CHILD_ENTITIES:
                getChildEntities().clear();
                getChildEntities().addAll((Collection<? extends Entity>)newValue);
                return;
            case KaomPackage.ENTITY__CHILD_LINKS:
                getChildLinks().clear();
                getChildLinks().addAll((Collection<? extends Link>)newValue);
                return;
            case KaomPackage.ENTITY__CHILD_RELATIONS:
                getChildRelations().clear();
                getChildRelations().addAll((Collection<? extends Relation>)newValue);
                return;
            case KaomPackage.ENTITY__CHILD_PORTS:
                getChildPorts().clear();
                getChildPorts().addAll((Collection<? extends Port>)newValue);
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
            case KaomPackage.ENTITY__CHILD_ENTITIES:
                getChildEntities().clear();
                return;
            case KaomPackage.ENTITY__CHILD_LINKS:
                getChildLinks().clear();
                return;
            case KaomPackage.ENTITY__CHILD_RELATIONS:
                getChildRelations().clear();
                return;
            case KaomPackage.ENTITY__CHILD_PORTS:
                getChildPorts().clear();
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
            case KaomPackage.ENTITY__CHILD_ENTITIES:
                return childEntities != null && !childEntities.isEmpty();
            case KaomPackage.ENTITY__CHILD_LINKS:
                return childLinks != null && !childLinks.isEmpty();
            case KaomPackage.ENTITY__CHILD_RELATIONS:
                return childRelations != null && !childRelations.isEmpty();
            case KaomPackage.ENTITY__CHILD_PORTS:
                return childPorts != null && !childPorts.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //EntityImpl
