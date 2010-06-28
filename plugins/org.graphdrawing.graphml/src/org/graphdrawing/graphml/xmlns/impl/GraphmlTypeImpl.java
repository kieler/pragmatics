/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.graphdrawing.graphml.xmlns.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.graphdrawing.graphml.xmlns.DataType;
import org.graphdrawing.graphml.xmlns.GraphType;
import org.graphdrawing.graphml.xmlns.GraphmlType;
import org.graphdrawing.graphml.xmlns.KeyType;
import org.graphdrawing.graphml.xmlns.XmlnsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Graphml Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.graphdrawing.graphml.xmlns.impl.GraphmlTypeImpl#getDesc <em>Desc</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.impl.GraphmlTypeImpl#getKey <em>Key</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.impl.GraphmlTypeImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.impl.GraphmlTypeImpl#getGraph <em>Graph</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.impl.GraphmlTypeImpl#getData <em>Data</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GraphmlTypeImpl extends EObjectImpl implements GraphmlType {
	/**
	 * The default value of the '{@link #getDesc() <em>Desc</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDesc()
	 * @generated
	 * @ordered
	 */
	protected static final String DESC_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDesc() <em>Desc</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDesc()
	 * @generated
	 * @ordered
	 */
	protected String desc = DESC_EDEFAULT;

	/**
	 * The cached value of the '{@link #getKey() <em>Key</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKey()
	 * @generated
	 * @ordered
	 */
	protected EList<KeyType> key;

	/**
	 * The cached value of the '{@link #getGroup() <em>Group</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroup()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap group;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GraphmlTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return XmlnsPackage.Literals.GRAPHML_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDesc(String newDesc) {
		String oldDesc = desc;
		desc = newDesc;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XmlnsPackage.GRAPHML_TYPE__DESC, oldDesc, desc));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<KeyType> getKey() {
		if (key == null) {
			key = new EObjectContainmentEList<KeyType>(KeyType.class, this, XmlnsPackage.GRAPHML_TYPE__KEY);
		}
		return key;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getGroup() {
		if (group == null) {
			group = new BasicFeatureMap(this, XmlnsPackage.GRAPHML_TYPE__GROUP);
		}
		return group;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<GraphType> getGraph() {
		return getGroup().list(XmlnsPackage.Literals.GRAPHML_TYPE__GRAPH);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DataType> getData() {
		return getGroup().list(XmlnsPackage.Literals.GRAPHML_TYPE__DATA);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case XmlnsPackage.GRAPHML_TYPE__KEY:
				return ((InternalEList<?>)getKey()).basicRemove(otherEnd, msgs);
			case XmlnsPackage.GRAPHML_TYPE__GROUP:
				return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
			case XmlnsPackage.GRAPHML_TYPE__GRAPH:
				return ((InternalEList<?>)getGraph()).basicRemove(otherEnd, msgs);
			case XmlnsPackage.GRAPHML_TYPE__DATA:
				return ((InternalEList<?>)getData()).basicRemove(otherEnd, msgs);
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
			case XmlnsPackage.GRAPHML_TYPE__DESC:
				return getDesc();
			case XmlnsPackage.GRAPHML_TYPE__KEY:
				return getKey();
			case XmlnsPackage.GRAPHML_TYPE__GROUP:
				if (coreType) return getGroup();
				return ((FeatureMap.Internal)getGroup()).getWrapper();
			case XmlnsPackage.GRAPHML_TYPE__GRAPH:
				return getGraph();
			case XmlnsPackage.GRAPHML_TYPE__DATA:
				return getData();
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
			case XmlnsPackage.GRAPHML_TYPE__DESC:
				setDesc((String)newValue);
				return;
			case XmlnsPackage.GRAPHML_TYPE__KEY:
				getKey().clear();
				getKey().addAll((Collection<? extends KeyType>)newValue);
				return;
			case XmlnsPackage.GRAPHML_TYPE__GROUP:
				((FeatureMap.Internal)getGroup()).set(newValue);
				return;
			case XmlnsPackage.GRAPHML_TYPE__GRAPH:
				getGraph().clear();
				getGraph().addAll((Collection<? extends GraphType>)newValue);
				return;
			case XmlnsPackage.GRAPHML_TYPE__DATA:
				getData().clear();
				getData().addAll((Collection<? extends DataType>)newValue);
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
			case XmlnsPackage.GRAPHML_TYPE__DESC:
				setDesc(DESC_EDEFAULT);
				return;
			case XmlnsPackage.GRAPHML_TYPE__KEY:
				getKey().clear();
				return;
			case XmlnsPackage.GRAPHML_TYPE__GROUP:
				getGroup().clear();
				return;
			case XmlnsPackage.GRAPHML_TYPE__GRAPH:
				getGraph().clear();
				return;
			case XmlnsPackage.GRAPHML_TYPE__DATA:
				getData().clear();
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
			case XmlnsPackage.GRAPHML_TYPE__DESC:
				return DESC_EDEFAULT == null ? desc != null : !DESC_EDEFAULT.equals(desc);
			case XmlnsPackage.GRAPHML_TYPE__KEY:
				return key != null && !key.isEmpty();
			case XmlnsPackage.GRAPHML_TYPE__GROUP:
				return group != null && !group.isEmpty();
			case XmlnsPackage.GRAPHML_TYPE__GRAPH:
				return !getGraph().isEmpty();
			case XmlnsPackage.GRAPHML_TYPE__DATA:
				return !getData().isEmpty();
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
		result.append(" (desc: ");
		result.append(desc);
		result.append(", group: ");
		result.append(group);
		result.append(')');
		return result.toString();
	}

} //GraphmlTypeImpl
