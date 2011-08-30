/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.cs.kieler.kwebs.servicedata.impl;

import de.cau.cs.kieler.kwebs.servicedata.Category;
import de.cau.cs.kieler.kwebs.servicedata.LayoutAlgorithm;
import de.cau.cs.kieler.kwebs.servicedata.LayoutOption;
import de.cau.cs.kieler.kwebs.servicedata.LayoutType;
import de.cau.cs.kieler.kwebs.servicedata.ServiceData;
import de.cau.cs.kieler.kwebs.servicedata.SupportedFormat;
import de.cau.cs.kieler.kwebs.servicedata.ServiceDataPackage;

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
 * An implementation of the model object '<em><b>Service Data</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.kwebs.servicedata.impl.ServiceDataImpl#getLayoutAlgorithms <em>Layout Algorithms</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.servicedata.impl.ServiceDataImpl#getLayoutTypes <em>Layout Types</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.servicedata.impl.ServiceDataImpl#getLayoutOptions <em>Layout Options</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.servicedata.impl.ServiceDataImpl#getCategories <em>Categories</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.servicedata.impl.ServiceDataImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.servicedata.impl.ServiceDataImpl#getSupportedFormats <em>Supported Formats</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ServiceDataImpl extends EObjectImpl implements ServiceData {
    /**
     * The cached value of the '{@link #getLayoutAlgorithms() <em>Layout Algorithms</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLayoutAlgorithms()
     * @generated
     * @ordered
     */
    protected EList<LayoutAlgorithm> layoutAlgorithms;

    /**
     * The cached value of the '{@link #getLayoutTypes() <em>Layout Types</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLayoutTypes()
     * @generated
     * @ordered
     */
    protected EList<LayoutType> layoutTypes;

    /**
     * The cached value of the '{@link #getLayoutOptions() <em>Layout Options</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLayoutOptions()
     * @generated
     * @ordered
     */
    protected EList<LayoutOption> layoutOptions;

    /**
     * The cached value of the '{@link #getCategories() <em>Categories</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCategories()
     * @generated
     * @ordered
     */
    protected EList<Category> categories;

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
     * The cached value of the '{@link #getSupportedFormats() <em>Supported Formats</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSupportedFormats()
     * @generated
     * @ordered
     */
    protected EList<SupportedFormat> supportedFormats;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ServiceDataImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ServiceDataPackage.Literals.SERVICE_DATA;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<LayoutAlgorithm> getLayoutAlgorithms() {
        if (layoutAlgorithms == null) {
            layoutAlgorithms = new EObjectContainmentEList<LayoutAlgorithm>(LayoutAlgorithm.class, this, ServiceDataPackage.SERVICE_DATA__LAYOUT_ALGORITHMS);
        }
        return layoutAlgorithms;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<LayoutType> getLayoutTypes() {
        if (layoutTypes == null) {
            layoutTypes = new EObjectContainmentEList<LayoutType>(LayoutType.class, this, ServiceDataPackage.SERVICE_DATA__LAYOUT_TYPES);
        }
        return layoutTypes;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<LayoutOption> getLayoutOptions() {
        if (layoutOptions == null) {
            layoutOptions = new EObjectContainmentEList<LayoutOption>(LayoutOption.class, this, ServiceDataPackage.SERVICE_DATA__LAYOUT_OPTIONS);
        }
        return layoutOptions;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<Category> getCategories() {
        if (categories == null) {
            categories = new EObjectContainmentEList<Category>(Category.class, this, ServiceDataPackage.SERVICE_DATA__CATEGORIES);
        }
        return categories;
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
            eNotify(new ENotificationImpl(this, Notification.SET, ServiceDataPackage.SERVICE_DATA__VERSION, oldVersion, version));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<SupportedFormat> getSupportedFormats() {
        if (supportedFormats == null) {
            supportedFormats = new EObjectContainmentEList<SupportedFormat>(SupportedFormat.class, this, ServiceDataPackage.SERVICE_DATA__SUPPORTED_FORMATS);
        }
        return supportedFormats;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ServiceDataPackage.SERVICE_DATA__LAYOUT_ALGORITHMS:
                return ((InternalEList<?>)getLayoutAlgorithms()).basicRemove(otherEnd, msgs);
            case ServiceDataPackage.SERVICE_DATA__LAYOUT_TYPES:
                return ((InternalEList<?>)getLayoutTypes()).basicRemove(otherEnd, msgs);
            case ServiceDataPackage.SERVICE_DATA__LAYOUT_OPTIONS:
                return ((InternalEList<?>)getLayoutOptions()).basicRemove(otherEnd, msgs);
            case ServiceDataPackage.SERVICE_DATA__CATEGORIES:
                return ((InternalEList<?>)getCategories()).basicRemove(otherEnd, msgs);
            case ServiceDataPackage.SERVICE_DATA__SUPPORTED_FORMATS:
                return ((InternalEList<?>)getSupportedFormats()).basicRemove(otherEnd, msgs);
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
            case ServiceDataPackage.SERVICE_DATA__LAYOUT_ALGORITHMS:
                return getLayoutAlgorithms();
            case ServiceDataPackage.SERVICE_DATA__LAYOUT_TYPES:
                return getLayoutTypes();
            case ServiceDataPackage.SERVICE_DATA__LAYOUT_OPTIONS:
                return getLayoutOptions();
            case ServiceDataPackage.SERVICE_DATA__CATEGORIES:
                return getCategories();
            case ServiceDataPackage.SERVICE_DATA__VERSION:
                return getVersion();
            case ServiceDataPackage.SERVICE_DATA__SUPPORTED_FORMATS:
                return getSupportedFormats();
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
            case ServiceDataPackage.SERVICE_DATA__LAYOUT_ALGORITHMS:
                getLayoutAlgorithms().clear();
                getLayoutAlgorithms().addAll((Collection<? extends LayoutAlgorithm>)newValue);
                return;
            case ServiceDataPackage.SERVICE_DATA__LAYOUT_TYPES:
                getLayoutTypes().clear();
                getLayoutTypes().addAll((Collection<? extends LayoutType>)newValue);
                return;
            case ServiceDataPackage.SERVICE_DATA__LAYOUT_OPTIONS:
                getLayoutOptions().clear();
                getLayoutOptions().addAll((Collection<? extends LayoutOption>)newValue);
                return;
            case ServiceDataPackage.SERVICE_DATA__CATEGORIES:
                getCategories().clear();
                getCategories().addAll((Collection<? extends Category>)newValue);
                return;
            case ServiceDataPackage.SERVICE_DATA__VERSION:
                setVersion((String)newValue);
                return;
            case ServiceDataPackage.SERVICE_DATA__SUPPORTED_FORMATS:
                getSupportedFormats().clear();
                getSupportedFormats().addAll((Collection<? extends SupportedFormat>)newValue);
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
            case ServiceDataPackage.SERVICE_DATA__LAYOUT_ALGORITHMS:
                getLayoutAlgorithms().clear();
                return;
            case ServiceDataPackage.SERVICE_DATA__LAYOUT_TYPES:
                getLayoutTypes().clear();
                return;
            case ServiceDataPackage.SERVICE_DATA__LAYOUT_OPTIONS:
                getLayoutOptions().clear();
                return;
            case ServiceDataPackage.SERVICE_DATA__CATEGORIES:
                getCategories().clear();
                return;
            case ServiceDataPackage.SERVICE_DATA__VERSION:
                setVersion(VERSION_EDEFAULT);
                return;
            case ServiceDataPackage.SERVICE_DATA__SUPPORTED_FORMATS:
                getSupportedFormats().clear();
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
            case ServiceDataPackage.SERVICE_DATA__LAYOUT_ALGORITHMS:
                return layoutAlgorithms != null && !layoutAlgorithms.isEmpty();
            case ServiceDataPackage.SERVICE_DATA__LAYOUT_TYPES:
                return layoutTypes != null && !layoutTypes.isEmpty();
            case ServiceDataPackage.SERVICE_DATA__LAYOUT_OPTIONS:
                return layoutOptions != null && !layoutOptions.isEmpty();
            case ServiceDataPackage.SERVICE_DATA__CATEGORIES:
                return categories != null && !categories.isEmpty();
            case ServiceDataPackage.SERVICE_DATA__VERSION:
                return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
            case ServiceDataPackage.SERVICE_DATA__SUPPORTED_FORMATS:
                return supportedFormats != null && !supportedFormats.isEmpty();
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
        result.append(" (version: ");
        result.append(version);
        result.append(')');
        return result.toString();
    }

} //ServiceDataImpl
