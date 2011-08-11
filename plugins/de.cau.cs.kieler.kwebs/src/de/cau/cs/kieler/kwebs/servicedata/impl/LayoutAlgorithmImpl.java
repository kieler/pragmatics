/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.cs.kieler.kwebs.servicedata.impl;

import de.cau.cs.kieler.kwebs.servicedata.Category;
import de.cau.cs.kieler.kwebs.servicedata.KnownOption;
import de.cau.cs.kieler.kwebs.servicedata.LayoutAlgorithm;
import de.cau.cs.kieler.kwebs.servicedata.LayoutType;
import de.cau.cs.kieler.kwebs.servicedata.ServiceDataPackage;
import de.cau.cs.kieler.kwebs.servicedata.SupportedDiagram;

import java.util.Collection;

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
 * An implementation of the model object '<em><b>Layout Algorithm</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.kwebs.servicedata.impl.LayoutAlgorithmImpl#getId <em>Id</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.servicedata.impl.LayoutAlgorithmImpl#getName <em>Name</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.servicedata.impl.LayoutAlgorithmImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.servicedata.impl.LayoutAlgorithmImpl#getKnownOptions <em>Known Options</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.servicedata.impl.LayoutAlgorithmImpl#getSupportedDiagrams <em>Supported Diagrams</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.servicedata.impl.LayoutAlgorithmImpl#getCategory <em>Category</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.servicedata.impl.LayoutAlgorithmImpl#getType <em>Type</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.servicedata.impl.LayoutAlgorithmImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.servicedata.impl.LayoutAlgorithmImpl#getPreviewImagePath <em>Preview Image Path</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.servicedata.impl.LayoutAlgorithmImpl#getPreviewImagePort <em>Preview Image Port</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LayoutAlgorithmImpl extends EObjectImpl implements LayoutAlgorithm {
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
     * The cached value of the '{@link #getKnownOptions() <em>Known Options</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getKnownOptions()
     * @generated
     * @ordered
     */
    protected EList<KnownOption> knownOptions;

    /**
     * The cached value of the '{@link #getSupportedDiagrams() <em>Supported Diagrams</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSupportedDiagrams()
     * @generated
     * @ordered
     */
    protected EList<SupportedDiagram> supportedDiagrams;

    /**
     * The cached value of the '{@link #getCategory() <em>Category</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCategory()
     * @generated
     * @ordered
     */
    protected Category category;

    /**
     * The cached value of the '{@link #getType() <em>Type</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
    protected LayoutType type;

    /**
     * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getVersion()
     * @generated
     * @ordered
     */
    protected static final String VERSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getVersion()
     * @generated
     * @ordered
     */
    protected String version = VERSION_EDEFAULT;

    /**
     * The default value of the '{@link #getPreviewImagePath() <em>Preview Image Path</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPreviewImagePath()
     * @generated
     * @ordered
     */
    protected static final String PREVIEW_IMAGE_PATH_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getPreviewImagePath() <em>Preview Image Path</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPreviewImagePath()
     * @generated
     * @ordered
     */
    protected String previewImagePath = PREVIEW_IMAGE_PATH_EDEFAULT;

    /**
     * The default value of the '{@link #getPreviewImagePort() <em>Preview Image Port</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPreviewImagePort()
     * @generated
     * @ordered
     */
    protected static final int PREVIEW_IMAGE_PORT_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getPreviewImagePort() <em>Preview Image Port</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPreviewImagePort()
     * @generated
     * @ordered
     */
    protected int previewImagePort = PREVIEW_IMAGE_PORT_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected LayoutAlgorithmImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ServiceDataPackage.Literals.LAYOUT_ALGORITHM;
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
            eNotify(new ENotificationImpl(this, Notification.SET, ServiceDataPackage.LAYOUT_ALGORITHM__ID, oldId, id));
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
            eNotify(new ENotificationImpl(this, Notification.SET, ServiceDataPackage.LAYOUT_ALGORITHM__NAME, oldName, name));
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
            eNotify(new ENotificationImpl(this, Notification.SET, ServiceDataPackage.LAYOUT_ALGORITHM__DESCRIPTION, oldDescription, description));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<KnownOption> getKnownOptions() {
        if (knownOptions == null) {
            knownOptions = new EObjectContainmentEList<KnownOption>(KnownOption.class, this, ServiceDataPackage.LAYOUT_ALGORITHM__KNOWN_OPTIONS);
        }
        return knownOptions;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<SupportedDiagram> getSupportedDiagrams() {
        if (supportedDiagrams == null) {
            supportedDiagrams = new EObjectContainmentEList<SupportedDiagram>(SupportedDiagram.class, this, ServiceDataPackage.LAYOUT_ALGORITHM__SUPPORTED_DIAGRAMS);
        }
        return supportedDiagrams;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Category getCategory() {
        if (category != null && category.eIsProxy()) {
            InternalEObject oldCategory = (InternalEObject)category;
            category = (Category)eResolveProxy(oldCategory);
            if (category != oldCategory) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ServiceDataPackage.LAYOUT_ALGORITHM__CATEGORY, oldCategory, category));
            }
        }
        return category;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Category basicGetCategory() {
        return category;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCategory(Category newCategory) {
        Category oldCategory = category;
        category = newCategory;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ServiceDataPackage.LAYOUT_ALGORITHM__CATEGORY, oldCategory, category));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LayoutType getType() {
        if (type != null && type.eIsProxy()) {
            InternalEObject oldType = (InternalEObject)type;
            type = (LayoutType)eResolveProxy(oldType);
            if (type != oldType) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ServiceDataPackage.LAYOUT_ALGORITHM__TYPE, oldType, type));
            }
        }
        return type;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LayoutType basicGetType() {
        return type;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setType(LayoutType newType) {
        LayoutType oldType = type;
        type = newType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ServiceDataPackage.LAYOUT_ALGORITHM__TYPE, oldType, type));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getVersion() {
        return version;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setVersion(String newVersion) {
        String oldVersion = version;
        version = newVersion;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ServiceDataPackage.LAYOUT_ALGORITHM__VERSION, oldVersion, version));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getPreviewImagePath() {
        return previewImagePath;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPreviewImagePath(String newPreviewImagePath) {
        String oldPreviewImagePath = previewImagePath;
        previewImagePath = newPreviewImagePath;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ServiceDataPackage.LAYOUT_ALGORITHM__PREVIEW_IMAGE_PATH, oldPreviewImagePath, previewImagePath));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getPreviewImagePort() {
        return previewImagePort;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPreviewImagePort(int newPreviewImagePort) {
        int oldPreviewImagePort = previewImagePort;
        previewImagePort = newPreviewImagePort;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ServiceDataPackage.LAYOUT_ALGORITHM__PREVIEW_IMAGE_PORT, oldPreviewImagePort, previewImagePort));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ServiceDataPackage.LAYOUT_ALGORITHM__KNOWN_OPTIONS:
                return ((InternalEList<?>)getKnownOptions()).basicRemove(otherEnd, msgs);
            case ServiceDataPackage.LAYOUT_ALGORITHM__SUPPORTED_DIAGRAMS:
                return ((InternalEList<?>)getSupportedDiagrams()).basicRemove(otherEnd, msgs);
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
            case ServiceDataPackage.LAYOUT_ALGORITHM__ID:
                return getId();
            case ServiceDataPackage.LAYOUT_ALGORITHM__NAME:
                return getName();
            case ServiceDataPackage.LAYOUT_ALGORITHM__DESCRIPTION:
                return getDescription();
            case ServiceDataPackage.LAYOUT_ALGORITHM__KNOWN_OPTIONS:
                return getKnownOptions();
            case ServiceDataPackage.LAYOUT_ALGORITHM__SUPPORTED_DIAGRAMS:
                return getSupportedDiagrams();
            case ServiceDataPackage.LAYOUT_ALGORITHM__CATEGORY:
                if (resolve) return getCategory();
                return basicGetCategory();
            case ServiceDataPackage.LAYOUT_ALGORITHM__TYPE:
                if (resolve) return getType();
                return basicGetType();
            case ServiceDataPackage.LAYOUT_ALGORITHM__VERSION:
                return getVersion();
            case ServiceDataPackage.LAYOUT_ALGORITHM__PREVIEW_IMAGE_PATH:
                return getPreviewImagePath();
            case ServiceDataPackage.LAYOUT_ALGORITHM__PREVIEW_IMAGE_PORT:
                return getPreviewImagePort();
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
            case ServiceDataPackage.LAYOUT_ALGORITHM__ID:
                setId((String)newValue);
                return;
            case ServiceDataPackage.LAYOUT_ALGORITHM__NAME:
                setName((String)newValue);
                return;
            case ServiceDataPackage.LAYOUT_ALGORITHM__DESCRIPTION:
                setDescription((String)newValue);
                return;
            case ServiceDataPackage.LAYOUT_ALGORITHM__KNOWN_OPTIONS:
                getKnownOptions().clear();
                getKnownOptions().addAll((Collection<? extends KnownOption>)newValue);
                return;
            case ServiceDataPackage.LAYOUT_ALGORITHM__SUPPORTED_DIAGRAMS:
                getSupportedDiagrams().clear();
                getSupportedDiagrams().addAll((Collection<? extends SupportedDiagram>)newValue);
                return;
            case ServiceDataPackage.LAYOUT_ALGORITHM__CATEGORY:
                setCategory((Category)newValue);
                return;
            case ServiceDataPackage.LAYOUT_ALGORITHM__TYPE:
                setType((LayoutType)newValue);
                return;
            case ServiceDataPackage.LAYOUT_ALGORITHM__VERSION:
                setVersion((String)newValue);
                return;
            case ServiceDataPackage.LAYOUT_ALGORITHM__PREVIEW_IMAGE_PATH:
                setPreviewImagePath((String)newValue);
                return;
            case ServiceDataPackage.LAYOUT_ALGORITHM__PREVIEW_IMAGE_PORT:
                setPreviewImagePort((Integer)newValue);
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
            case ServiceDataPackage.LAYOUT_ALGORITHM__ID:
                setId(ID_EDEFAULT);
                return;
            case ServiceDataPackage.LAYOUT_ALGORITHM__NAME:
                setName(NAME_EDEFAULT);
                return;
            case ServiceDataPackage.LAYOUT_ALGORITHM__DESCRIPTION:
                setDescription(DESCRIPTION_EDEFAULT);
                return;
            case ServiceDataPackage.LAYOUT_ALGORITHM__KNOWN_OPTIONS:
                getKnownOptions().clear();
                return;
            case ServiceDataPackage.LAYOUT_ALGORITHM__SUPPORTED_DIAGRAMS:
                getSupportedDiagrams().clear();
                return;
            case ServiceDataPackage.LAYOUT_ALGORITHM__CATEGORY:
                setCategory((Category)null);
                return;
            case ServiceDataPackage.LAYOUT_ALGORITHM__TYPE:
                setType((LayoutType)null);
                return;
            case ServiceDataPackage.LAYOUT_ALGORITHM__VERSION:
                setVersion(VERSION_EDEFAULT);
                return;
            case ServiceDataPackage.LAYOUT_ALGORITHM__PREVIEW_IMAGE_PATH:
                setPreviewImagePath(PREVIEW_IMAGE_PATH_EDEFAULT);
                return;
            case ServiceDataPackage.LAYOUT_ALGORITHM__PREVIEW_IMAGE_PORT:
                setPreviewImagePort(PREVIEW_IMAGE_PORT_EDEFAULT);
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
            case ServiceDataPackage.LAYOUT_ALGORITHM__ID:
                return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
            case ServiceDataPackage.LAYOUT_ALGORITHM__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case ServiceDataPackage.LAYOUT_ALGORITHM__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
            case ServiceDataPackage.LAYOUT_ALGORITHM__KNOWN_OPTIONS:
                return knownOptions != null && !knownOptions.isEmpty();
            case ServiceDataPackage.LAYOUT_ALGORITHM__SUPPORTED_DIAGRAMS:
                return supportedDiagrams != null && !supportedDiagrams.isEmpty();
            case ServiceDataPackage.LAYOUT_ALGORITHM__CATEGORY:
                return category != null;
            case ServiceDataPackage.LAYOUT_ALGORITHM__TYPE:
                return type != null;
            case ServiceDataPackage.LAYOUT_ALGORITHM__VERSION:
                return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
            case ServiceDataPackage.LAYOUT_ALGORITHM__PREVIEW_IMAGE_PATH:
                return PREVIEW_IMAGE_PATH_EDEFAULT == null ? previewImagePath != null : !PREVIEW_IMAGE_PATH_EDEFAULT.equals(previewImagePath);
            case ServiceDataPackage.LAYOUT_ALGORITHM__PREVIEW_IMAGE_PORT:
                return previewImagePort != PREVIEW_IMAGE_PORT_EDEFAULT;
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
        result.append(", name: ");
        result.append(name);
        result.append(", description: ");
        result.append(description);
        result.append(", version: ");
        result.append(version);
        result.append(", previewImagePath: ");
        result.append(previewImagePath);
        result.append(", previewImagePort: ");
        result.append(previewImagePort);
        result.append(')');
        return result.toString();
    }

} //LayoutAlgorithmImpl
