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

import de.cau.cs.kieler.core.annotations.impl.NamedObjectImpl;

import de.cau.cs.kieler.kaom.KaomPackage;
import de.cau.cs.kieler.kaom.Link;
import de.cau.cs.kieler.kaom.Linkable;
import de.cau.cs.kieler.kaom.Port;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;

import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Port</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.kaom.impl.PortImpl#getOutgoingLinks <em>Outgoing Links</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kaom.impl.PortImpl#getIncomingLinks <em>Incoming Links</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kaom.impl.PortImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PortImpl extends NamedObjectImpl implements Port {
    /**
     * The cached value of the '{@link #getOutgoingLinks() <em>Outgoing Links</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOutgoingLinks()
     * @generated
     * @ordered
     */
    protected EList<Link> outgoingLinks;

    /**
     * The cached value of the '{@link #getIncomingLinks() <em>Incoming Links</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getIncomingLinks()
     * @generated
     * @ordered
     */
    protected EList<Link> incomingLinks;

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
    protected PortImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return KaomPackage.Literals.PORT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<Link> getOutgoingLinks() {
        if (outgoingLinks == null) {
            outgoingLinks = new EObjectWithInverseResolvingEList<Link>(Link.class, this, KaomPackage.PORT__OUTGOING_LINKS, KaomPackage.LINK__SOURCE);
        }
        return outgoingLinks;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<Link> getIncomingLinks() {
        if (incomingLinks == null) {
            incomingLinks = new EObjectWithInverseResolvingEList<Link>(Link.class, this, KaomPackage.PORT__INCOMING_LINKS, KaomPackage.LINK__TARGET);
        }
        return incomingLinks;
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
            eNotify(new ENotificationImpl(this, Notification.SET, KaomPackage.PORT__ID, oldId, id));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case KaomPackage.PORT__OUTGOING_LINKS:
                return ((InternalEList<InternalEObject>)(InternalEList<?>)getOutgoingLinks()).basicAdd(otherEnd, msgs);
            case KaomPackage.PORT__INCOMING_LINKS:
                return ((InternalEList<InternalEObject>)(InternalEList<?>)getIncomingLinks()).basicAdd(otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case KaomPackage.PORT__OUTGOING_LINKS:
                return ((InternalEList<?>)getOutgoingLinks()).basicRemove(otherEnd, msgs);
            case KaomPackage.PORT__INCOMING_LINKS:
                return ((InternalEList<?>)getIncomingLinks()).basicRemove(otherEnd, msgs);
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
            case KaomPackage.PORT__OUTGOING_LINKS:
                return getOutgoingLinks();
            case KaomPackage.PORT__INCOMING_LINKS:
                return getIncomingLinks();
            case KaomPackage.PORT__ID:
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
            case KaomPackage.PORT__OUTGOING_LINKS:
                getOutgoingLinks().clear();
                getOutgoingLinks().addAll((Collection<? extends Link>)newValue);
                return;
            case KaomPackage.PORT__INCOMING_LINKS:
                getIncomingLinks().clear();
                getIncomingLinks().addAll((Collection<? extends Link>)newValue);
                return;
            case KaomPackage.PORT__ID:
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
            case KaomPackage.PORT__OUTGOING_LINKS:
                getOutgoingLinks().clear();
                return;
            case KaomPackage.PORT__INCOMING_LINKS:
                getIncomingLinks().clear();
                return;
            case KaomPackage.PORT__ID:
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
            case KaomPackage.PORT__OUTGOING_LINKS:
                return outgoingLinks != null && !outgoingLinks.isEmpty();
            case KaomPackage.PORT__INCOMING_LINKS:
                return incomingLinks != null && !incomingLinks.isEmpty();
            case KaomPackage.PORT__ID:
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
    public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
        if (baseClass == Linkable.class) {
            switch (derivedFeatureID) {
                case KaomPackage.PORT__OUTGOING_LINKS: return KaomPackage.LINKABLE__OUTGOING_LINKS;
                case KaomPackage.PORT__INCOMING_LINKS: return KaomPackage.LINKABLE__INCOMING_LINKS;
                case KaomPackage.PORT__ID: return KaomPackage.LINKABLE__ID;
                default: return -1;
            }
        }
        return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
        if (baseClass == Linkable.class) {
            switch (baseFeatureID) {
                case KaomPackage.LINKABLE__OUTGOING_LINKS: return KaomPackage.PORT__OUTGOING_LINKS;
                case KaomPackage.LINKABLE__INCOMING_LINKS: return KaomPackage.PORT__INCOMING_LINKS;
                case KaomPackage.LINKABLE__ID: return KaomPackage.PORT__ID;
                default: return -1;
            }
        }
        return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
        result.append(" (id: ");
        result.append(id);
        result.append(')');
        return result.toString();
    }

} //PortImpl
