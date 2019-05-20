/**
 */
package de.scheidtbachmann.osgimodel.impl;

import de.scheidtbachmann.osgimodel.OsgimodelPackage;
import de.scheidtbachmann.osgimodel.Reference;
import de.scheidtbachmann.osgimodel.ServiceComponent;
import de.scheidtbachmann.osgimodel.ServiceInterface;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Service Interface</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.scheidtbachmann.osgimodel.impl.ServiceInterfaceImpl#getServiceComponent <em>Service Component</em>}</li>
 *   <li>{@link de.scheidtbachmann.osgimodel.impl.ServiceInterfaceImpl#getName <em>Name</em>}</li>
 *   <li>{@link de.scheidtbachmann.osgimodel.impl.ServiceInterfaceImpl#getAbout <em>About</em>}</li>
 *   <li>{@link de.scheidtbachmann.osgimodel.impl.ServiceInterfaceImpl#getReferencedBy <em>Referenced By</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ServiceInterfaceImpl extends MinimalEObjectImpl.Container implements ServiceInterface {
	/**
	 * The cached value of the '{@link #getServiceComponent() <em>Service Component</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServiceComponent()
	 * @generated
	 * @ordered
	 */
	protected EList<ServiceComponent> serviceComponent;

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
	 * The default value of the '{@link #getAbout() <em>About</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAbout()
	 * @generated
	 * @ordered
	 */
	protected static final String ABOUT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAbout() <em>About</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAbout()
	 * @generated
	 * @ordered
	 */
	protected String about = ABOUT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getReferencedBy() <em>Referenced By</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferencedBy()
	 * @generated
	 * @ordered
	 */
	protected Reference referencedBy;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ServiceInterfaceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OsgimodelPackage.Literals.SERVICE_INTERFACE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<ServiceComponent> getServiceComponent() {
		if (serviceComponent == null) {
			serviceComponent = new EObjectWithInverseResolvingEList.ManyInverse<ServiceComponent>(ServiceComponent.class, this, OsgimodelPackage.SERVICE_INTERFACE__SERVICE_COMPONENT, OsgimodelPackage.SERVICE_COMPONENT__SERVICE_INTERFACES);
		}
		return serviceComponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OsgimodelPackage.SERVICE_INTERFACE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getAbout() {
		return about;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAbout(String newAbout) {
		String oldAbout = about;
		about = newAbout;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OsgimodelPackage.SERVICE_INTERFACE__ABOUT, oldAbout, about));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Reference getReferencedBy() {
		if (referencedBy != null && referencedBy.eIsProxy()) {
			InternalEObject oldReferencedBy = (InternalEObject)referencedBy;
			referencedBy = (Reference)eResolveProxy(oldReferencedBy);
			if (referencedBy != oldReferencedBy) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OsgimodelPackage.SERVICE_INTERFACE__REFERENCED_BY, oldReferencedBy, referencedBy));
			}
		}
		return referencedBy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Reference basicGetReferencedBy() {
		return referencedBy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetReferencedBy(Reference newReferencedBy, NotificationChain msgs) {
		Reference oldReferencedBy = referencedBy;
		referencedBy = newReferencedBy;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OsgimodelPackage.SERVICE_INTERFACE__REFERENCED_BY, oldReferencedBy, newReferencedBy);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setReferencedBy(Reference newReferencedBy) {
		if (newReferencedBy != referencedBy) {
			NotificationChain msgs = null;
			if (referencedBy != null)
				msgs = ((InternalEObject)referencedBy).eInverseRemove(this, OsgimodelPackage.REFERENCE__SERVICE_INTERFACE, Reference.class, msgs);
			if (newReferencedBy != null)
				msgs = ((InternalEObject)newReferencedBy).eInverseAdd(this, OsgimodelPackage.REFERENCE__SERVICE_INTERFACE, Reference.class, msgs);
			msgs = basicSetReferencedBy(newReferencedBy, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OsgimodelPackage.SERVICE_INTERFACE__REFERENCED_BY, newReferencedBy, newReferencedBy));
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
			case OsgimodelPackage.SERVICE_INTERFACE__SERVICE_COMPONENT:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getServiceComponent()).basicAdd(otherEnd, msgs);
			case OsgimodelPackage.SERVICE_INTERFACE__REFERENCED_BY:
				if (referencedBy != null)
					msgs = ((InternalEObject)referencedBy).eInverseRemove(this, OsgimodelPackage.REFERENCE__SERVICE_INTERFACE, Reference.class, msgs);
				return basicSetReferencedBy((Reference)otherEnd, msgs);
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
			case OsgimodelPackage.SERVICE_INTERFACE__SERVICE_COMPONENT:
				return ((InternalEList<?>)getServiceComponent()).basicRemove(otherEnd, msgs);
			case OsgimodelPackage.SERVICE_INTERFACE__REFERENCED_BY:
				return basicSetReferencedBy(null, msgs);
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
			case OsgimodelPackage.SERVICE_INTERFACE__SERVICE_COMPONENT:
				return getServiceComponent();
			case OsgimodelPackage.SERVICE_INTERFACE__NAME:
				return getName();
			case OsgimodelPackage.SERVICE_INTERFACE__ABOUT:
				return getAbout();
			case OsgimodelPackage.SERVICE_INTERFACE__REFERENCED_BY:
				if (resolve) return getReferencedBy();
				return basicGetReferencedBy();
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
			case OsgimodelPackage.SERVICE_INTERFACE__SERVICE_COMPONENT:
				getServiceComponent().clear();
				getServiceComponent().addAll((Collection<? extends ServiceComponent>)newValue);
				return;
			case OsgimodelPackage.SERVICE_INTERFACE__NAME:
				setName((String)newValue);
				return;
			case OsgimodelPackage.SERVICE_INTERFACE__ABOUT:
				setAbout((String)newValue);
				return;
			case OsgimodelPackage.SERVICE_INTERFACE__REFERENCED_BY:
				setReferencedBy((Reference)newValue);
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
			case OsgimodelPackage.SERVICE_INTERFACE__SERVICE_COMPONENT:
				getServiceComponent().clear();
				return;
			case OsgimodelPackage.SERVICE_INTERFACE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case OsgimodelPackage.SERVICE_INTERFACE__ABOUT:
				setAbout(ABOUT_EDEFAULT);
				return;
			case OsgimodelPackage.SERVICE_INTERFACE__REFERENCED_BY:
				setReferencedBy((Reference)null);
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
			case OsgimodelPackage.SERVICE_INTERFACE__SERVICE_COMPONENT:
				return serviceComponent != null && !serviceComponent.isEmpty();
			case OsgimodelPackage.SERVICE_INTERFACE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case OsgimodelPackage.SERVICE_INTERFACE__ABOUT:
				return ABOUT_EDEFAULT == null ? about != null : !ABOUT_EDEFAULT.equals(about);
			case OsgimodelPackage.SERVICE_INTERFACE__REFERENCED_BY:
				return referencedBy != null;
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

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(", about: ");
		result.append(about);
		result.append(')');
		return result.toString();
	}

} //ServiceInterfaceImpl
