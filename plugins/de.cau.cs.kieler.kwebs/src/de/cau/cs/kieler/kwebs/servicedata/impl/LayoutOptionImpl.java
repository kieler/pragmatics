/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.cs.kieler.kwebs.servicedata.impl;

import de.cau.cs.kieler.kwebs.servicedata.LayoutOption;
import de.cau.cs.kieler.kwebs.servicedata.RemoteEnum;
import de.cau.cs.kieler.kwebs.servicedata.ServiceDataPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Layout Option</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.kwebs.servicedata.impl.LayoutOptionImpl#getId <em>Id</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.servicedata.impl.LayoutOptionImpl#getType <em>Type</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.servicedata.impl.LayoutOptionImpl#getName <em>Name</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.servicedata.impl.LayoutOptionImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.servicedata.impl.LayoutOptionImpl#getAppliesTo <em>Applies To</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.servicedata.impl.LayoutOptionImpl#getDefault <em>Default</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.servicedata.impl.LayoutOptionImpl#isAdvanced <em>Advanced</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.servicedata.impl.LayoutOptionImpl#getRemoteEnum <em>Remote Enum</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.servicedata.impl.LayoutOptionImpl#getImplementation <em>Implementation</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LayoutOptionImpl extends EObjectImpl implements LayoutOption {
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
     * The default value of the '{@link #getType() <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
    protected static final String TYPE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
    protected String type = TYPE_EDEFAULT;

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
     * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDescription()
     * @generated
     * @ordered
     */
    protected static final String DESCRIPTION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDescription()
     * @generated
     * @ordered
     */
    protected String description = DESCRIPTION_EDEFAULT;

    /**
     * The default value of the '{@link #getAppliesTo() <em>Applies To</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAppliesTo()
     * @generated
     * @ordered
     */
    protected static final String APPLIES_TO_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getAppliesTo() <em>Applies To</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAppliesTo()
     * @generated
     * @ordered
     */
    protected String appliesTo = APPLIES_TO_EDEFAULT;

    /**
     * The default value of the '{@link #getDefault() <em>Default</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDefault()
     * @generated
     * @ordered
     */
    protected static final String DEFAULT_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDefault() <em>Default</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDefault()
     * @generated
     * @ordered
     */
    protected String default_ = DEFAULT_EDEFAULT;

    /**
     * The default value of the '{@link #isAdvanced() <em>Advanced</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isAdvanced()
     * @generated
     * @ordered
     */
    protected static final boolean ADVANCED_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isAdvanced() <em>Advanced</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isAdvanced()
     * @generated
     * @ordered
     */
    protected boolean advanced = ADVANCED_EDEFAULT;

    /**
     * The cached value of the '{@link #getRemoteEnum() <em>Remote Enum</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRemoteEnum()
     * @generated
     * @ordered
     */
    protected RemoteEnum remoteEnum;

    /**
     * The default value of the '{@link #getImplementation() <em>Implementation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getImplementation()
     * @generated
     * @ordered
     */
    protected static final String IMPLEMENTATION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getImplementation() <em>Implementation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getImplementation()
     * @generated
     * @ordered
     */
    protected String implementation = IMPLEMENTATION_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected LayoutOptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ServiceDataPackage.Literals.LAYOUT_OPTION;
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
            eNotify(new ENotificationImpl(this, Notification.SET, ServiceDataPackage.LAYOUT_OPTION__ID, oldId, id));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getType() {
        return type;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setType(String newType) {
        String oldType = type;
        type = newType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ServiceDataPackage.LAYOUT_OPTION__TYPE, oldType, type));
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
            eNotify(new ENotificationImpl(this, Notification.SET, ServiceDataPackage.LAYOUT_OPTION__NAME, oldName, name));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getDescription() {
        return description;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDescription(String newDescription) {
        String oldDescription = description;
        description = newDescription;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ServiceDataPackage.LAYOUT_OPTION__DESCRIPTION, oldDescription, description));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getAppliesTo() {
        return appliesTo;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setAppliesTo(String newAppliesTo) {
        String oldAppliesTo = appliesTo;
        appliesTo = newAppliesTo;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ServiceDataPackage.LAYOUT_OPTION__APPLIES_TO, oldAppliesTo, appliesTo));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getDefault() {
        return default_;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDefault(String newDefault) {
        String oldDefault = default_;
        default_ = newDefault;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ServiceDataPackage.LAYOUT_OPTION__DEFAULT, oldDefault, default_));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isAdvanced() {
        return advanced;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setAdvanced(boolean newAdvanced) {
        boolean oldAdvanced = advanced;
        advanced = newAdvanced;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ServiceDataPackage.LAYOUT_OPTION__ADVANCED, oldAdvanced, advanced));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public RemoteEnum getRemoteEnum() {
        return remoteEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetRemoteEnum(RemoteEnum newRemoteEnum, NotificationChain msgs) {
        RemoteEnum oldRemoteEnum = remoteEnum;
        remoteEnum = newRemoteEnum;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ServiceDataPackage.LAYOUT_OPTION__REMOTE_ENUM, oldRemoteEnum, newRemoteEnum);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setRemoteEnum(RemoteEnum newRemoteEnum) {
        if (newRemoteEnum != remoteEnum) {
            NotificationChain msgs = null;
            if (remoteEnum != null)
                msgs = ((InternalEObject)remoteEnum).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ServiceDataPackage.LAYOUT_OPTION__REMOTE_ENUM, null, msgs);
            if (newRemoteEnum != null)
                msgs = ((InternalEObject)newRemoteEnum).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ServiceDataPackage.LAYOUT_OPTION__REMOTE_ENUM, null, msgs);
            msgs = basicSetRemoteEnum(newRemoteEnum, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ServiceDataPackage.LAYOUT_OPTION__REMOTE_ENUM, newRemoteEnum, newRemoteEnum));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getImplementation() {
        return implementation;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setImplementation(String newImplementation) {
        String oldImplementation = implementation;
        implementation = newImplementation;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ServiceDataPackage.LAYOUT_OPTION__IMPLEMENTATION, oldImplementation, implementation));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ServiceDataPackage.LAYOUT_OPTION__REMOTE_ENUM:
                return basicSetRemoteEnum(null, msgs);
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
            case ServiceDataPackage.LAYOUT_OPTION__ID:
                return getId();
            case ServiceDataPackage.LAYOUT_OPTION__TYPE:
                return getType();
            case ServiceDataPackage.LAYOUT_OPTION__NAME:
                return getName();
            case ServiceDataPackage.LAYOUT_OPTION__DESCRIPTION:
                return getDescription();
            case ServiceDataPackage.LAYOUT_OPTION__APPLIES_TO:
                return getAppliesTo();
            case ServiceDataPackage.LAYOUT_OPTION__DEFAULT:
                return getDefault();
            case ServiceDataPackage.LAYOUT_OPTION__ADVANCED:
                return isAdvanced();
            case ServiceDataPackage.LAYOUT_OPTION__REMOTE_ENUM:
                return getRemoteEnum();
            case ServiceDataPackage.LAYOUT_OPTION__IMPLEMENTATION:
                return getImplementation();
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
            case ServiceDataPackage.LAYOUT_OPTION__ID:
                setId((String)newValue);
                return;
            case ServiceDataPackage.LAYOUT_OPTION__TYPE:
                setType((String)newValue);
                return;
            case ServiceDataPackage.LAYOUT_OPTION__NAME:
                setName((String)newValue);
                return;
            case ServiceDataPackage.LAYOUT_OPTION__DESCRIPTION:
                setDescription((String)newValue);
                return;
            case ServiceDataPackage.LAYOUT_OPTION__APPLIES_TO:
                setAppliesTo((String)newValue);
                return;
            case ServiceDataPackage.LAYOUT_OPTION__DEFAULT:
                setDefault((String)newValue);
                return;
            case ServiceDataPackage.LAYOUT_OPTION__ADVANCED:
                setAdvanced((Boolean)newValue);
                return;
            case ServiceDataPackage.LAYOUT_OPTION__REMOTE_ENUM:
                setRemoteEnum((RemoteEnum)newValue);
                return;
            case ServiceDataPackage.LAYOUT_OPTION__IMPLEMENTATION:
                setImplementation((String)newValue);
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
            case ServiceDataPackage.LAYOUT_OPTION__ID:
                setId(ID_EDEFAULT);
                return;
            case ServiceDataPackage.LAYOUT_OPTION__TYPE:
                setType(TYPE_EDEFAULT);
                return;
            case ServiceDataPackage.LAYOUT_OPTION__NAME:
                setName(NAME_EDEFAULT);
                return;
            case ServiceDataPackage.LAYOUT_OPTION__DESCRIPTION:
                setDescription(DESCRIPTION_EDEFAULT);
                return;
            case ServiceDataPackage.LAYOUT_OPTION__APPLIES_TO:
                setAppliesTo(APPLIES_TO_EDEFAULT);
                return;
            case ServiceDataPackage.LAYOUT_OPTION__DEFAULT:
                setDefault(DEFAULT_EDEFAULT);
                return;
            case ServiceDataPackage.LAYOUT_OPTION__ADVANCED:
                setAdvanced(ADVANCED_EDEFAULT);
                return;
            case ServiceDataPackage.LAYOUT_OPTION__REMOTE_ENUM:
                setRemoteEnum((RemoteEnum)null);
                return;
            case ServiceDataPackage.LAYOUT_OPTION__IMPLEMENTATION:
                setImplementation(IMPLEMENTATION_EDEFAULT);
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
            case ServiceDataPackage.LAYOUT_OPTION__ID:
                return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
            case ServiceDataPackage.LAYOUT_OPTION__TYPE:
                return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
            case ServiceDataPackage.LAYOUT_OPTION__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case ServiceDataPackage.LAYOUT_OPTION__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
            case ServiceDataPackage.LAYOUT_OPTION__APPLIES_TO:
                return APPLIES_TO_EDEFAULT == null ? appliesTo != null : !APPLIES_TO_EDEFAULT.equals(appliesTo);
            case ServiceDataPackage.LAYOUT_OPTION__DEFAULT:
                return DEFAULT_EDEFAULT == null ? default_ != null : !DEFAULT_EDEFAULT.equals(default_);
            case ServiceDataPackage.LAYOUT_OPTION__ADVANCED:
                return advanced != ADVANCED_EDEFAULT;
            case ServiceDataPackage.LAYOUT_OPTION__REMOTE_ENUM:
                return remoteEnum != null;
            case ServiceDataPackage.LAYOUT_OPTION__IMPLEMENTATION:
                return IMPLEMENTATION_EDEFAULT == null ? implementation != null : !IMPLEMENTATION_EDEFAULT.equals(implementation);
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
        result.append(" (id: ");
        result.append(id);
        result.append(", type: ");
        result.append(type);
        result.append(", name: ");
        result.append(name);
        result.append(", description: ");
        result.append(description);
        result.append(", appliesTo: ");
        result.append(appliesTo);
        result.append(", default: ");
        result.append(default_);
        result.append(", advanced: ");
        result.append(advanced);
        result.append(", implementation: ");
        result.append(implementation);
        result.append(')');
        return result.toString();
    }

} //LayoutOptionImpl
