/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.graphdrawing.graphml.xmlns.impl;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.graphdrawing.graphml.xmlns.DataType;
import org.graphdrawing.graphml.xmlns.DefaultType;
import org.graphdrawing.graphml.xmlns.DocumentRoot;
import org.graphdrawing.graphml.xmlns.EdgeType;
import org.graphdrawing.graphml.xmlns.EndpointType;
import org.graphdrawing.graphml.xmlns.GraphType;
import org.graphdrawing.graphml.xmlns.GraphmlType;
import org.graphdrawing.graphml.xmlns.HyperedgeType;
import org.graphdrawing.graphml.xmlns.KeyType;
import org.graphdrawing.graphml.xmlns.LocatorType;
import org.graphdrawing.graphml.xmlns.NodeType;
import org.graphdrawing.graphml.xmlns.PortType;
import org.graphdrawing.graphml.xmlns.XmlnsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Document Root</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.graphdrawing.graphml.xmlns.impl.DocumentRootImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.impl.DocumentRootImpl#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.impl.DocumentRootImpl#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.impl.DocumentRootImpl#getData <em>Data</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.impl.DocumentRootImpl#getDefault <em>Default</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.impl.DocumentRootImpl#getDesc <em>Desc</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.impl.DocumentRootImpl#getEdge <em>Edge</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.impl.DocumentRootImpl#getEndpoint <em>Endpoint</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.impl.DocumentRootImpl#getGraph <em>Graph</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.impl.DocumentRootImpl#getGraphml <em>Graphml</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.impl.DocumentRootImpl#getHyperedge <em>Hyperedge</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.impl.DocumentRootImpl#getKey <em>Key</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.impl.DocumentRootImpl#getLocator <em>Locator</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.impl.DocumentRootImpl#getNode <em>Node</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.impl.DocumentRootImpl#getPort <em>Port</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DocumentRootImpl extends EObjectImpl implements DocumentRoot {
	/**
	 * The cached value of the '{@link #getMixed() <em>Mixed</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMixed()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap mixed;

	/**
	 * The cached value of the '{@link #getXMLNSPrefixMap() <em>XMLNS Prefix Map</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXMLNSPrefixMap()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> xMLNSPrefixMap;

	/**
	 * The cached value of the '{@link #getXSISchemaLocation() <em>XSI Schema Location</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXSISchemaLocation()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> xSISchemaLocation;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DocumentRootImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return XmlnsPackage.Literals.DOCUMENT_ROOT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getMixed() {
		if (mixed == null) {
			mixed = new BasicFeatureMap(this, XmlnsPackage.DOCUMENT_ROOT__MIXED);
		}
		return mixed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, String> getXMLNSPrefixMap() {
		if (xMLNSPrefixMap == null) {
			xMLNSPrefixMap = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, XmlnsPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
		}
		return xMLNSPrefixMap;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, String> getXSISchemaLocation() {
		if (xSISchemaLocation == null) {
			xSISchemaLocation = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, XmlnsPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
		}
		return xSISchemaLocation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataType getData() {
		return (DataType)getMixed().get(XmlnsPackage.Literals.DOCUMENT_ROOT__DATA, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetData(DataType newData, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XmlnsPackage.Literals.DOCUMENT_ROOT__DATA, newData, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setData(DataType newData) {
		((FeatureMap.Internal)getMixed()).set(XmlnsPackage.Literals.DOCUMENT_ROOT__DATA, newData);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DefaultType getDefault() {
		return (DefaultType)getMixed().get(XmlnsPackage.Literals.DOCUMENT_ROOT__DEFAULT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDefault(DefaultType newDefault, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XmlnsPackage.Literals.DOCUMENT_ROOT__DEFAULT, newDefault, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefault(DefaultType newDefault) {
		((FeatureMap.Internal)getMixed()).set(XmlnsPackage.Literals.DOCUMENT_ROOT__DEFAULT, newDefault);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDesc() {
		return (String)getMixed().get(XmlnsPackage.Literals.DOCUMENT_ROOT__DESC, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDesc(String newDesc) {
		((FeatureMap.Internal)getMixed()).set(XmlnsPackage.Literals.DOCUMENT_ROOT__DESC, newDesc);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EdgeType getEdge() {
		return (EdgeType)getMixed().get(XmlnsPackage.Literals.DOCUMENT_ROOT__EDGE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEdge(EdgeType newEdge, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XmlnsPackage.Literals.DOCUMENT_ROOT__EDGE, newEdge, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEdge(EdgeType newEdge) {
		((FeatureMap.Internal)getMixed()).set(XmlnsPackage.Literals.DOCUMENT_ROOT__EDGE, newEdge);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EndpointType getEndpoint() {
		return (EndpointType)getMixed().get(XmlnsPackage.Literals.DOCUMENT_ROOT__ENDPOINT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEndpoint(EndpointType newEndpoint, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XmlnsPackage.Literals.DOCUMENT_ROOT__ENDPOINT, newEndpoint, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEndpoint(EndpointType newEndpoint) {
		((FeatureMap.Internal)getMixed()).set(XmlnsPackage.Literals.DOCUMENT_ROOT__ENDPOINT, newEndpoint);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GraphType getGraph() {
		return (GraphType)getMixed().get(XmlnsPackage.Literals.DOCUMENT_ROOT__GRAPH, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGraph(GraphType newGraph, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XmlnsPackage.Literals.DOCUMENT_ROOT__GRAPH, newGraph, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGraph(GraphType newGraph) {
		((FeatureMap.Internal)getMixed()).set(XmlnsPackage.Literals.DOCUMENT_ROOT__GRAPH, newGraph);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GraphmlType getGraphml() {
		return (GraphmlType)getMixed().get(XmlnsPackage.Literals.DOCUMENT_ROOT__GRAPHML, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGraphml(GraphmlType newGraphml, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XmlnsPackage.Literals.DOCUMENT_ROOT__GRAPHML, newGraphml, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGraphml(GraphmlType newGraphml) {
		((FeatureMap.Internal)getMixed()).set(XmlnsPackage.Literals.DOCUMENT_ROOT__GRAPHML, newGraphml);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HyperedgeType getHyperedge() {
		return (HyperedgeType)getMixed().get(XmlnsPackage.Literals.DOCUMENT_ROOT__HYPEREDGE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetHyperedge(HyperedgeType newHyperedge, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XmlnsPackage.Literals.DOCUMENT_ROOT__HYPEREDGE, newHyperedge, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHyperedge(HyperedgeType newHyperedge) {
		((FeatureMap.Internal)getMixed()).set(XmlnsPackage.Literals.DOCUMENT_ROOT__HYPEREDGE, newHyperedge);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public KeyType getKey() {
		return (KeyType)getMixed().get(XmlnsPackage.Literals.DOCUMENT_ROOT__KEY, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetKey(KeyType newKey, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XmlnsPackage.Literals.DOCUMENT_ROOT__KEY, newKey, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setKey(KeyType newKey) {
		((FeatureMap.Internal)getMixed()).set(XmlnsPackage.Literals.DOCUMENT_ROOT__KEY, newKey);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LocatorType getLocator() {
		return (LocatorType)getMixed().get(XmlnsPackage.Literals.DOCUMENT_ROOT__LOCATOR, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLocator(LocatorType newLocator, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XmlnsPackage.Literals.DOCUMENT_ROOT__LOCATOR, newLocator, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLocator(LocatorType newLocator) {
		((FeatureMap.Internal)getMixed()).set(XmlnsPackage.Literals.DOCUMENT_ROOT__LOCATOR, newLocator);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NodeType getNode() {
		return (NodeType)getMixed().get(XmlnsPackage.Literals.DOCUMENT_ROOT__NODE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetNode(NodeType newNode, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XmlnsPackage.Literals.DOCUMENT_ROOT__NODE, newNode, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNode(NodeType newNode) {
		((FeatureMap.Internal)getMixed()).set(XmlnsPackage.Literals.DOCUMENT_ROOT__NODE, newNode);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortType getPort() {
		return (PortType)getMixed().get(XmlnsPackage.Literals.DOCUMENT_ROOT__PORT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPort(PortType newPort, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XmlnsPackage.Literals.DOCUMENT_ROOT__PORT, newPort, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPort(PortType newPort) {
		((FeatureMap.Internal)getMixed()).set(XmlnsPackage.Literals.DOCUMENT_ROOT__PORT, newPort);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case XmlnsPackage.DOCUMENT_ROOT__MIXED:
				return ((InternalEList<?>)getMixed()).basicRemove(otherEnd, msgs);
			case XmlnsPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				return ((InternalEList<?>)getXMLNSPrefixMap()).basicRemove(otherEnd, msgs);
			case XmlnsPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				return ((InternalEList<?>)getXSISchemaLocation()).basicRemove(otherEnd, msgs);
			case XmlnsPackage.DOCUMENT_ROOT__DATA:
				return basicSetData(null, msgs);
			case XmlnsPackage.DOCUMENT_ROOT__DEFAULT:
				return basicSetDefault(null, msgs);
			case XmlnsPackage.DOCUMENT_ROOT__EDGE:
				return basicSetEdge(null, msgs);
			case XmlnsPackage.DOCUMENT_ROOT__ENDPOINT:
				return basicSetEndpoint(null, msgs);
			case XmlnsPackage.DOCUMENT_ROOT__GRAPH:
				return basicSetGraph(null, msgs);
			case XmlnsPackage.DOCUMENT_ROOT__GRAPHML:
				return basicSetGraphml(null, msgs);
			case XmlnsPackage.DOCUMENT_ROOT__HYPEREDGE:
				return basicSetHyperedge(null, msgs);
			case XmlnsPackage.DOCUMENT_ROOT__KEY:
				return basicSetKey(null, msgs);
			case XmlnsPackage.DOCUMENT_ROOT__LOCATOR:
				return basicSetLocator(null, msgs);
			case XmlnsPackage.DOCUMENT_ROOT__NODE:
				return basicSetNode(null, msgs);
			case XmlnsPackage.DOCUMENT_ROOT__PORT:
				return basicSetPort(null, msgs);
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
			case XmlnsPackage.DOCUMENT_ROOT__MIXED:
				if (coreType) return getMixed();
				return ((FeatureMap.Internal)getMixed()).getWrapper();
			case XmlnsPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				if (coreType) return getXMLNSPrefixMap();
				else return getXMLNSPrefixMap().map();
			case XmlnsPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				if (coreType) return getXSISchemaLocation();
				else return getXSISchemaLocation().map();
			case XmlnsPackage.DOCUMENT_ROOT__DATA:
				return getData();
			case XmlnsPackage.DOCUMENT_ROOT__DEFAULT:
				return getDefault();
			case XmlnsPackage.DOCUMENT_ROOT__DESC:
				return getDesc();
			case XmlnsPackage.DOCUMENT_ROOT__EDGE:
				return getEdge();
			case XmlnsPackage.DOCUMENT_ROOT__ENDPOINT:
				return getEndpoint();
			case XmlnsPackage.DOCUMENT_ROOT__GRAPH:
				return getGraph();
			case XmlnsPackage.DOCUMENT_ROOT__GRAPHML:
				return getGraphml();
			case XmlnsPackage.DOCUMENT_ROOT__HYPEREDGE:
				return getHyperedge();
			case XmlnsPackage.DOCUMENT_ROOT__KEY:
				return getKey();
			case XmlnsPackage.DOCUMENT_ROOT__LOCATOR:
				return getLocator();
			case XmlnsPackage.DOCUMENT_ROOT__NODE:
				return getNode();
			case XmlnsPackage.DOCUMENT_ROOT__PORT:
				return getPort();
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
			case XmlnsPackage.DOCUMENT_ROOT__MIXED:
				((FeatureMap.Internal)getMixed()).set(newValue);
				return;
			case XmlnsPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				((EStructuralFeature.Setting)getXMLNSPrefixMap()).set(newValue);
				return;
			case XmlnsPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				((EStructuralFeature.Setting)getXSISchemaLocation()).set(newValue);
				return;
			case XmlnsPackage.DOCUMENT_ROOT__DATA:
				setData((DataType)newValue);
				return;
			case XmlnsPackage.DOCUMENT_ROOT__DEFAULT:
				setDefault((DefaultType)newValue);
				return;
			case XmlnsPackage.DOCUMENT_ROOT__DESC:
				setDesc((String)newValue);
				return;
			case XmlnsPackage.DOCUMENT_ROOT__EDGE:
				setEdge((EdgeType)newValue);
				return;
			case XmlnsPackage.DOCUMENT_ROOT__ENDPOINT:
				setEndpoint((EndpointType)newValue);
				return;
			case XmlnsPackage.DOCUMENT_ROOT__GRAPH:
				setGraph((GraphType)newValue);
				return;
			case XmlnsPackage.DOCUMENT_ROOT__GRAPHML:
				setGraphml((GraphmlType)newValue);
				return;
			case XmlnsPackage.DOCUMENT_ROOT__HYPEREDGE:
				setHyperedge((HyperedgeType)newValue);
				return;
			case XmlnsPackage.DOCUMENT_ROOT__KEY:
				setKey((KeyType)newValue);
				return;
			case XmlnsPackage.DOCUMENT_ROOT__LOCATOR:
				setLocator((LocatorType)newValue);
				return;
			case XmlnsPackage.DOCUMENT_ROOT__NODE:
				setNode((NodeType)newValue);
				return;
			case XmlnsPackage.DOCUMENT_ROOT__PORT:
				setPort((PortType)newValue);
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
			case XmlnsPackage.DOCUMENT_ROOT__MIXED:
				getMixed().clear();
				return;
			case XmlnsPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				getXMLNSPrefixMap().clear();
				return;
			case XmlnsPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				getXSISchemaLocation().clear();
				return;
			case XmlnsPackage.DOCUMENT_ROOT__DATA:
				setData((DataType)null);
				return;
			case XmlnsPackage.DOCUMENT_ROOT__DEFAULT:
				setDefault((DefaultType)null);
				return;
			case XmlnsPackage.DOCUMENT_ROOT__DESC:
				setDesc(DESC_EDEFAULT);
				return;
			case XmlnsPackage.DOCUMENT_ROOT__EDGE:
				setEdge((EdgeType)null);
				return;
			case XmlnsPackage.DOCUMENT_ROOT__ENDPOINT:
				setEndpoint((EndpointType)null);
				return;
			case XmlnsPackage.DOCUMENT_ROOT__GRAPH:
				setGraph((GraphType)null);
				return;
			case XmlnsPackage.DOCUMENT_ROOT__GRAPHML:
				setGraphml((GraphmlType)null);
				return;
			case XmlnsPackage.DOCUMENT_ROOT__HYPEREDGE:
				setHyperedge((HyperedgeType)null);
				return;
			case XmlnsPackage.DOCUMENT_ROOT__KEY:
				setKey((KeyType)null);
				return;
			case XmlnsPackage.DOCUMENT_ROOT__LOCATOR:
				setLocator((LocatorType)null);
				return;
			case XmlnsPackage.DOCUMENT_ROOT__NODE:
				setNode((NodeType)null);
				return;
			case XmlnsPackage.DOCUMENT_ROOT__PORT:
				setPort((PortType)null);
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
			case XmlnsPackage.DOCUMENT_ROOT__MIXED:
				return mixed != null && !mixed.isEmpty();
			case XmlnsPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				return xMLNSPrefixMap != null && !xMLNSPrefixMap.isEmpty();
			case XmlnsPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				return xSISchemaLocation != null && !xSISchemaLocation.isEmpty();
			case XmlnsPackage.DOCUMENT_ROOT__DATA:
				return getData() != null;
			case XmlnsPackage.DOCUMENT_ROOT__DEFAULT:
				return getDefault() != null;
			case XmlnsPackage.DOCUMENT_ROOT__DESC:
				return DESC_EDEFAULT == null ? getDesc() != null : !DESC_EDEFAULT.equals(getDesc());
			case XmlnsPackage.DOCUMENT_ROOT__EDGE:
				return getEdge() != null;
			case XmlnsPackage.DOCUMENT_ROOT__ENDPOINT:
				return getEndpoint() != null;
			case XmlnsPackage.DOCUMENT_ROOT__GRAPH:
				return getGraph() != null;
			case XmlnsPackage.DOCUMENT_ROOT__GRAPHML:
				return getGraphml() != null;
			case XmlnsPackage.DOCUMENT_ROOT__HYPEREDGE:
				return getHyperedge() != null;
			case XmlnsPackage.DOCUMENT_ROOT__KEY:
				return getKey() != null;
			case XmlnsPackage.DOCUMENT_ROOT__LOCATOR:
				return getLocator() != null;
			case XmlnsPackage.DOCUMENT_ROOT__NODE:
				return getNode() != null;
			case XmlnsPackage.DOCUMENT_ROOT__PORT:
				return getPort() != null;
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
		result.append(" (mixed: ");
		result.append(mixed);
		result.append(')');
		return result.toString();
	}

} //DocumentRootImpl
