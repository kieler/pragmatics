/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.graphdrawing.graphml.xmlns.impl;

import java.math.BigInteger;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.graphdrawing.graphml.xmlns.DataType;
import org.graphdrawing.graphml.xmlns.GraphType;
import org.graphdrawing.graphml.xmlns.LocatorType;
import org.graphdrawing.graphml.xmlns.NodeType;
import org.graphdrawing.graphml.xmlns.PortType;
import org.graphdrawing.graphml.xmlns.XmlnsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Node Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.graphdrawing.graphml.xmlns.impl.NodeTypeImpl#getDesc <em>Desc</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.impl.NodeTypeImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.impl.NodeTypeImpl#getData <em>Data</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.impl.NodeTypeImpl#getPort <em>Port</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.impl.NodeTypeImpl#getGraph <em>Graph</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.impl.NodeTypeImpl#getLocator <em>Locator</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.impl.NodeTypeImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.impl.NodeTypeImpl#getParseIndegree <em>Parse Indegree</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.impl.NodeTypeImpl#getParseOutdegree <em>Parse Outdegree</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NodeTypeImpl extends EObjectImpl implements NodeType {
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
	 * The cached value of the '{@link #getGroup() <em>Group</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroup()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap group;

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
	 * The cached value of the '{@link #getLocator() <em>Locator</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocator()
	 * @generated
	 * @ordered
	 */
	protected LocatorType locator;

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
	 * The default value of the '{@link #getParseIndegree() <em>Parse Indegree</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParseIndegree()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger PARSE_INDEGREE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getParseIndegree() <em>Parse Indegree</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParseIndegree()
	 * @generated
	 * @ordered
	 */
	protected BigInteger parseIndegree = PARSE_INDEGREE_EDEFAULT;

	/**
	 * The default value of the '{@link #getParseOutdegree() <em>Parse Outdegree</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParseOutdegree()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger PARSE_OUTDEGREE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getParseOutdegree() <em>Parse Outdegree</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParseOutdegree()
	 * @generated
	 * @ordered
	 */
	protected BigInteger parseOutdegree = PARSE_OUTDEGREE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NodeTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return XmlnsPackage.Literals.NODE_TYPE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, XmlnsPackage.NODE_TYPE__DESC, oldDesc, desc));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getGroup() {
		if (group == null) {
			group = new BasicFeatureMap(this, XmlnsPackage.NODE_TYPE__GROUP);
		}
		return group;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DataType> getData() {
		return getGroup().list(XmlnsPackage.Literals.NODE_TYPE__DATA);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PortType> getPort() {
		return getGroup().list(XmlnsPackage.Literals.NODE_TYPE__PORT);
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XmlnsPackage.NODE_TYPE__GRAPH, oldGraph, newGraph);
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
				msgs = ((InternalEObject)graph).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XmlnsPackage.NODE_TYPE__GRAPH, null, msgs);
			if (newGraph != null)
				msgs = ((InternalEObject)newGraph).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XmlnsPackage.NODE_TYPE__GRAPH, null, msgs);
			msgs = basicSetGraph(newGraph, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XmlnsPackage.NODE_TYPE__GRAPH, newGraph, newGraph));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LocatorType getLocator() {
		return locator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLocator(LocatorType newLocator, NotificationChain msgs) {
		LocatorType oldLocator = locator;
		locator = newLocator;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XmlnsPackage.NODE_TYPE__LOCATOR, oldLocator, newLocator);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLocator(LocatorType newLocator) {
		if (newLocator != locator) {
			NotificationChain msgs = null;
			if (locator != null)
				msgs = ((InternalEObject)locator).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XmlnsPackage.NODE_TYPE__LOCATOR, null, msgs);
			if (newLocator != null)
				msgs = ((InternalEObject)newLocator).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XmlnsPackage.NODE_TYPE__LOCATOR, null, msgs);
			msgs = basicSetLocator(newLocator, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XmlnsPackage.NODE_TYPE__LOCATOR, newLocator, newLocator));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XmlnsPackage.NODE_TYPE__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getParseIndegree() {
		return parseIndegree;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParseIndegree(BigInteger newParseIndegree) {
		BigInteger oldParseIndegree = parseIndegree;
		parseIndegree = newParseIndegree;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XmlnsPackage.NODE_TYPE__PARSE_INDEGREE, oldParseIndegree, parseIndegree));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getParseOutdegree() {
		return parseOutdegree;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParseOutdegree(BigInteger newParseOutdegree) {
		BigInteger oldParseOutdegree = parseOutdegree;
		parseOutdegree = newParseOutdegree;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XmlnsPackage.NODE_TYPE__PARSE_OUTDEGREE, oldParseOutdegree, parseOutdegree));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case XmlnsPackage.NODE_TYPE__GROUP:
				return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
			case XmlnsPackage.NODE_TYPE__DATA:
				return ((InternalEList<?>)getData()).basicRemove(otherEnd, msgs);
			case XmlnsPackage.NODE_TYPE__PORT:
				return ((InternalEList<?>)getPort()).basicRemove(otherEnd, msgs);
			case XmlnsPackage.NODE_TYPE__GRAPH:
				return basicSetGraph(null, msgs);
			case XmlnsPackage.NODE_TYPE__LOCATOR:
				return basicSetLocator(null, msgs);
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
			case XmlnsPackage.NODE_TYPE__DESC:
				return getDesc();
			case XmlnsPackage.NODE_TYPE__GROUP:
				if (coreType) return getGroup();
				return ((FeatureMap.Internal)getGroup()).getWrapper();
			case XmlnsPackage.NODE_TYPE__DATA:
				return getData();
			case XmlnsPackage.NODE_TYPE__PORT:
				return getPort();
			case XmlnsPackage.NODE_TYPE__GRAPH:
				return getGraph();
			case XmlnsPackage.NODE_TYPE__LOCATOR:
				return getLocator();
			case XmlnsPackage.NODE_TYPE__ID:
				return getId();
			case XmlnsPackage.NODE_TYPE__PARSE_INDEGREE:
				return getParseIndegree();
			case XmlnsPackage.NODE_TYPE__PARSE_OUTDEGREE:
				return getParseOutdegree();
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
			case XmlnsPackage.NODE_TYPE__DESC:
				setDesc((String)newValue);
				return;
			case XmlnsPackage.NODE_TYPE__GROUP:
				((FeatureMap.Internal)getGroup()).set(newValue);
				return;
			case XmlnsPackage.NODE_TYPE__DATA:
				getData().clear();
				getData().addAll((Collection<? extends DataType>)newValue);
				return;
			case XmlnsPackage.NODE_TYPE__PORT:
				getPort().clear();
				getPort().addAll((Collection<? extends PortType>)newValue);
				return;
			case XmlnsPackage.NODE_TYPE__GRAPH:
				setGraph((GraphType)newValue);
				return;
			case XmlnsPackage.NODE_TYPE__LOCATOR:
				setLocator((LocatorType)newValue);
				return;
			case XmlnsPackage.NODE_TYPE__ID:
				setId((String)newValue);
				return;
			case XmlnsPackage.NODE_TYPE__PARSE_INDEGREE:
				setParseIndegree((BigInteger)newValue);
				return;
			case XmlnsPackage.NODE_TYPE__PARSE_OUTDEGREE:
				setParseOutdegree((BigInteger)newValue);
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
			case XmlnsPackage.NODE_TYPE__DESC:
				setDesc(DESC_EDEFAULT);
				return;
			case XmlnsPackage.NODE_TYPE__GROUP:
				getGroup().clear();
				return;
			case XmlnsPackage.NODE_TYPE__DATA:
				getData().clear();
				return;
			case XmlnsPackage.NODE_TYPE__PORT:
				getPort().clear();
				return;
			case XmlnsPackage.NODE_TYPE__GRAPH:
				setGraph((GraphType)null);
				return;
			case XmlnsPackage.NODE_TYPE__LOCATOR:
				setLocator((LocatorType)null);
				return;
			case XmlnsPackage.NODE_TYPE__ID:
				setId(ID_EDEFAULT);
				return;
			case XmlnsPackage.NODE_TYPE__PARSE_INDEGREE:
				setParseIndegree(PARSE_INDEGREE_EDEFAULT);
				return;
			case XmlnsPackage.NODE_TYPE__PARSE_OUTDEGREE:
				setParseOutdegree(PARSE_OUTDEGREE_EDEFAULT);
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
			case XmlnsPackage.NODE_TYPE__DESC:
				return DESC_EDEFAULT == null ? desc != null : !DESC_EDEFAULT.equals(desc);
			case XmlnsPackage.NODE_TYPE__GROUP:
				return group != null && !group.isEmpty();
			case XmlnsPackage.NODE_TYPE__DATA:
				return !getData().isEmpty();
			case XmlnsPackage.NODE_TYPE__PORT:
				return !getPort().isEmpty();
			case XmlnsPackage.NODE_TYPE__GRAPH:
				return graph != null;
			case XmlnsPackage.NODE_TYPE__LOCATOR:
				return locator != null;
			case XmlnsPackage.NODE_TYPE__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case XmlnsPackage.NODE_TYPE__PARSE_INDEGREE:
				return PARSE_INDEGREE_EDEFAULT == null ? parseIndegree != null : !PARSE_INDEGREE_EDEFAULT.equals(parseIndegree);
			case XmlnsPackage.NODE_TYPE__PARSE_OUTDEGREE:
				return PARSE_OUTDEGREE_EDEFAULT == null ? parseOutdegree != null : !PARSE_OUTDEGREE_EDEFAULT.equals(parseOutdegree);
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
		result.append(", id: ");
		result.append(id);
		result.append(", parseIndegree: ");
		result.append(parseIndegree);
		result.append(", parseOutdegree: ");
		result.append(parseOutdegree);
		result.append(')');
		return result.toString();
	}

} //NodeTypeImpl
