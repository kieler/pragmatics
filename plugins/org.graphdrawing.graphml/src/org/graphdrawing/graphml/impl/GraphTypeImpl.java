/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.graphdrawing.graphml.impl;

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

import org.graphdrawing.graphml.DataType;
import org.graphdrawing.graphml.EdgeType;
import org.graphdrawing.graphml.GraphEdgedefaultType;
import org.graphdrawing.graphml.GraphEdgeidsType;
import org.graphdrawing.graphml.GraphMLPackage;
import org.graphdrawing.graphml.GraphNodeidsType;
import org.graphdrawing.graphml.GraphOrderType;
import org.graphdrawing.graphml.GraphType;
import org.graphdrawing.graphml.HyperedgeType;
import org.graphdrawing.graphml.LocatorType;
import org.graphdrawing.graphml.NodeType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Graph Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.graphdrawing.graphml.impl.GraphTypeImpl#getDesc <em>Desc</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.impl.GraphTypeImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.impl.GraphTypeImpl#getData <em>Data</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.impl.GraphTypeImpl#getNode <em>Node</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.impl.GraphTypeImpl#getEdge <em>Edge</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.impl.GraphTypeImpl#getHyperedge <em>Hyperedge</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.impl.GraphTypeImpl#getLocator <em>Locator</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.impl.GraphTypeImpl#getEdgedefault <em>Edgedefault</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.impl.GraphTypeImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.impl.GraphTypeImpl#getParseEdgeids <em>Parse Edgeids</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.impl.GraphTypeImpl#getParseEdges <em>Parse Edges</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.impl.GraphTypeImpl#getParseMaxindegree <em>Parse Maxindegree</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.impl.GraphTypeImpl#getParseMaxoutdegree <em>Parse Maxoutdegree</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.impl.GraphTypeImpl#getParseNodeids <em>Parse Nodeids</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.impl.GraphTypeImpl#getParseNodes <em>Parse Nodes</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.impl.GraphTypeImpl#getParseOrder <em>Parse Order</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GraphTypeImpl extends EObjectImpl implements GraphType {
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
     * The cached value of the '{@link #getLocator() <em>Locator</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLocator()
     * @generated
     * @ordered
     */
    protected LocatorType locator;

    /**
     * The default value of the '{@link #getEdgedefault() <em>Edgedefault</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getEdgedefault()
     * @generated
     * @ordered
     */
    protected static final GraphEdgedefaultType EDGEDEFAULT_EDEFAULT = GraphEdgedefaultType.DIRECTED;

    /**
     * The cached value of the '{@link #getEdgedefault() <em>Edgedefault</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getEdgedefault()
     * @generated
     * @ordered
     */
    protected GraphEdgedefaultType edgedefault = EDGEDEFAULT_EDEFAULT;

    /**
     * This is true if the Edgedefault attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean edgedefaultESet;

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
     * The default value of the '{@link #getParseEdgeids() <em>Parse Edgeids</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getParseEdgeids()
     * @generated
     * @ordered
     */
    protected static final GraphEdgeidsType PARSE_EDGEIDS_EDEFAULT = GraphEdgeidsType.CANONICAL;

    /**
     * The cached value of the '{@link #getParseEdgeids() <em>Parse Edgeids</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getParseEdgeids()
     * @generated
     * @ordered
     */
    protected GraphEdgeidsType parseEdgeids = PARSE_EDGEIDS_EDEFAULT;

    /**
     * This is true if the Parse Edgeids attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean parseEdgeidsESet;

    /**
     * The default value of the '{@link #getParseEdges() <em>Parse Edges</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getParseEdges()
     * @generated
     * @ordered
     */
    protected static final BigInteger PARSE_EDGES_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getParseEdges() <em>Parse Edges</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getParseEdges()
     * @generated
     * @ordered
     */
    protected BigInteger parseEdges = PARSE_EDGES_EDEFAULT;

    /**
     * The default value of the '{@link #getParseMaxindegree() <em>Parse Maxindegree</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getParseMaxindegree()
     * @generated
     * @ordered
     */
    protected static final BigInteger PARSE_MAXINDEGREE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getParseMaxindegree() <em>Parse Maxindegree</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getParseMaxindegree()
     * @generated
     * @ordered
     */
    protected BigInteger parseMaxindegree = PARSE_MAXINDEGREE_EDEFAULT;

    /**
     * The default value of the '{@link #getParseMaxoutdegree() <em>Parse Maxoutdegree</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getParseMaxoutdegree()
     * @generated
     * @ordered
     */
    protected static final BigInteger PARSE_MAXOUTDEGREE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getParseMaxoutdegree() <em>Parse Maxoutdegree</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getParseMaxoutdegree()
     * @generated
     * @ordered
     */
    protected BigInteger parseMaxoutdegree = PARSE_MAXOUTDEGREE_EDEFAULT;

    /**
     * The default value of the '{@link #getParseNodeids() <em>Parse Nodeids</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getParseNodeids()
     * @generated
     * @ordered
     */
    protected static final GraphNodeidsType PARSE_NODEIDS_EDEFAULT = GraphNodeidsType.CANONICAL;

    /**
     * The cached value of the '{@link #getParseNodeids() <em>Parse Nodeids</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getParseNodeids()
     * @generated
     * @ordered
     */
    protected GraphNodeidsType parseNodeids = PARSE_NODEIDS_EDEFAULT;

    /**
     * This is true if the Parse Nodeids attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean parseNodeidsESet;

    /**
     * The default value of the '{@link #getParseNodes() <em>Parse Nodes</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getParseNodes()
     * @generated
     * @ordered
     */
    protected static final BigInteger PARSE_NODES_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getParseNodes() <em>Parse Nodes</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getParseNodes()
     * @generated
     * @ordered
     */
    protected BigInteger parseNodes = PARSE_NODES_EDEFAULT;

    /**
     * The default value of the '{@link #getParseOrder() <em>Parse Order</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getParseOrder()
     * @generated
     * @ordered
     */
    protected static final GraphOrderType PARSE_ORDER_EDEFAULT = GraphOrderType.FREE;

    /**
     * The cached value of the '{@link #getParseOrder() <em>Parse Order</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getParseOrder()
     * @generated
     * @ordered
     */
    protected GraphOrderType parseOrder = PARSE_ORDER_EDEFAULT;

    /**
     * This is true if the Parse Order attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean parseOrderESet;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected GraphTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return GraphMLPackage.Literals.GRAPH_TYPE;
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
            eNotify(new ENotificationImpl(this, Notification.SET, GraphMLPackage.GRAPH_TYPE__DESC, oldDesc, desc));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureMap getGroup() {
        if (group == null) {
            group = new BasicFeatureMap(this, GraphMLPackage.GRAPH_TYPE__GROUP);
        }
        return group;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<DataType> getData() {
        return getGroup().list(GraphMLPackage.Literals.GRAPH_TYPE__DATA);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<NodeType> getNode() {
        return getGroup().list(GraphMLPackage.Literals.GRAPH_TYPE__NODE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<EdgeType> getEdge() {
        return getGroup().list(GraphMLPackage.Literals.GRAPH_TYPE__EDGE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<HyperedgeType> getHyperedge() {
        return getGroup().list(GraphMLPackage.Literals.GRAPH_TYPE__HYPEREDGE);
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, GraphMLPackage.GRAPH_TYPE__LOCATOR, oldLocator, newLocator);
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
                msgs = ((InternalEObject)locator).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - GraphMLPackage.GRAPH_TYPE__LOCATOR, null, msgs);
            if (newLocator != null)
                msgs = ((InternalEObject)newLocator).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - GraphMLPackage.GRAPH_TYPE__LOCATOR, null, msgs);
            msgs = basicSetLocator(newLocator, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GraphMLPackage.GRAPH_TYPE__LOCATOR, newLocator, newLocator));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GraphEdgedefaultType getEdgedefault() {
        return edgedefault;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setEdgedefault(GraphEdgedefaultType newEdgedefault) {
        GraphEdgedefaultType oldEdgedefault = edgedefault;
        edgedefault = newEdgedefault == null ? EDGEDEFAULT_EDEFAULT : newEdgedefault;
        boolean oldEdgedefaultESet = edgedefaultESet;
        edgedefaultESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GraphMLPackage.GRAPH_TYPE__EDGEDEFAULT, oldEdgedefault, edgedefault, !oldEdgedefaultESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetEdgedefault() {
        GraphEdgedefaultType oldEdgedefault = edgedefault;
        boolean oldEdgedefaultESet = edgedefaultESet;
        edgedefault = EDGEDEFAULT_EDEFAULT;
        edgedefaultESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, GraphMLPackage.GRAPH_TYPE__EDGEDEFAULT, oldEdgedefault, EDGEDEFAULT_EDEFAULT, oldEdgedefaultESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetEdgedefault() {
        return edgedefaultESet;
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
            eNotify(new ENotificationImpl(this, Notification.SET, GraphMLPackage.GRAPH_TYPE__ID, oldId, id));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GraphEdgeidsType getParseEdgeids() {
        return parseEdgeids;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setParseEdgeids(GraphEdgeidsType newParseEdgeids) {
        GraphEdgeidsType oldParseEdgeids = parseEdgeids;
        parseEdgeids = newParseEdgeids == null ? PARSE_EDGEIDS_EDEFAULT : newParseEdgeids;
        boolean oldParseEdgeidsESet = parseEdgeidsESet;
        parseEdgeidsESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GraphMLPackage.GRAPH_TYPE__PARSE_EDGEIDS, oldParseEdgeids, parseEdgeids, !oldParseEdgeidsESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetParseEdgeids() {
        GraphEdgeidsType oldParseEdgeids = parseEdgeids;
        boolean oldParseEdgeidsESet = parseEdgeidsESet;
        parseEdgeids = PARSE_EDGEIDS_EDEFAULT;
        parseEdgeidsESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, GraphMLPackage.GRAPH_TYPE__PARSE_EDGEIDS, oldParseEdgeids, PARSE_EDGEIDS_EDEFAULT, oldParseEdgeidsESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetParseEdgeids() {
        return parseEdgeidsESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BigInteger getParseEdges() {
        return parseEdges;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setParseEdges(BigInteger newParseEdges) {
        BigInteger oldParseEdges = parseEdges;
        parseEdges = newParseEdges;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GraphMLPackage.GRAPH_TYPE__PARSE_EDGES, oldParseEdges, parseEdges));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BigInteger getParseMaxindegree() {
        return parseMaxindegree;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setParseMaxindegree(BigInteger newParseMaxindegree) {
        BigInteger oldParseMaxindegree = parseMaxindegree;
        parseMaxindegree = newParseMaxindegree;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GraphMLPackage.GRAPH_TYPE__PARSE_MAXINDEGREE, oldParseMaxindegree, parseMaxindegree));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BigInteger getParseMaxoutdegree() {
        return parseMaxoutdegree;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setParseMaxoutdegree(BigInteger newParseMaxoutdegree) {
        BigInteger oldParseMaxoutdegree = parseMaxoutdegree;
        parseMaxoutdegree = newParseMaxoutdegree;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GraphMLPackage.GRAPH_TYPE__PARSE_MAXOUTDEGREE, oldParseMaxoutdegree, parseMaxoutdegree));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GraphNodeidsType getParseNodeids() {
        return parseNodeids;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setParseNodeids(GraphNodeidsType newParseNodeids) {
        GraphNodeidsType oldParseNodeids = parseNodeids;
        parseNodeids = newParseNodeids == null ? PARSE_NODEIDS_EDEFAULT : newParseNodeids;
        boolean oldParseNodeidsESet = parseNodeidsESet;
        parseNodeidsESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GraphMLPackage.GRAPH_TYPE__PARSE_NODEIDS, oldParseNodeids, parseNodeids, !oldParseNodeidsESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetParseNodeids() {
        GraphNodeidsType oldParseNodeids = parseNodeids;
        boolean oldParseNodeidsESet = parseNodeidsESet;
        parseNodeids = PARSE_NODEIDS_EDEFAULT;
        parseNodeidsESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, GraphMLPackage.GRAPH_TYPE__PARSE_NODEIDS, oldParseNodeids, PARSE_NODEIDS_EDEFAULT, oldParseNodeidsESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetParseNodeids() {
        return parseNodeidsESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BigInteger getParseNodes() {
        return parseNodes;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setParseNodes(BigInteger newParseNodes) {
        BigInteger oldParseNodes = parseNodes;
        parseNodes = newParseNodes;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GraphMLPackage.GRAPH_TYPE__PARSE_NODES, oldParseNodes, parseNodes));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GraphOrderType getParseOrder() {
        return parseOrder;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setParseOrder(GraphOrderType newParseOrder) {
        GraphOrderType oldParseOrder = parseOrder;
        parseOrder = newParseOrder == null ? PARSE_ORDER_EDEFAULT : newParseOrder;
        boolean oldParseOrderESet = parseOrderESet;
        parseOrderESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GraphMLPackage.GRAPH_TYPE__PARSE_ORDER, oldParseOrder, parseOrder, !oldParseOrderESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetParseOrder() {
        GraphOrderType oldParseOrder = parseOrder;
        boolean oldParseOrderESet = parseOrderESet;
        parseOrder = PARSE_ORDER_EDEFAULT;
        parseOrderESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, GraphMLPackage.GRAPH_TYPE__PARSE_ORDER, oldParseOrder, PARSE_ORDER_EDEFAULT, oldParseOrderESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetParseOrder() {
        return parseOrderESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case GraphMLPackage.GRAPH_TYPE__GROUP:
                return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
            case GraphMLPackage.GRAPH_TYPE__DATA:
                return ((InternalEList<?>)getData()).basicRemove(otherEnd, msgs);
            case GraphMLPackage.GRAPH_TYPE__NODE:
                return ((InternalEList<?>)getNode()).basicRemove(otherEnd, msgs);
            case GraphMLPackage.GRAPH_TYPE__EDGE:
                return ((InternalEList<?>)getEdge()).basicRemove(otherEnd, msgs);
            case GraphMLPackage.GRAPH_TYPE__HYPEREDGE:
                return ((InternalEList<?>)getHyperedge()).basicRemove(otherEnd, msgs);
            case GraphMLPackage.GRAPH_TYPE__LOCATOR:
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
            case GraphMLPackage.GRAPH_TYPE__DESC:
                return getDesc();
            case GraphMLPackage.GRAPH_TYPE__GROUP:
                if (coreType) return getGroup();
                return ((FeatureMap.Internal)getGroup()).getWrapper();
            case GraphMLPackage.GRAPH_TYPE__DATA:
                return getData();
            case GraphMLPackage.GRAPH_TYPE__NODE:
                return getNode();
            case GraphMLPackage.GRAPH_TYPE__EDGE:
                return getEdge();
            case GraphMLPackage.GRAPH_TYPE__HYPEREDGE:
                return getHyperedge();
            case GraphMLPackage.GRAPH_TYPE__LOCATOR:
                return getLocator();
            case GraphMLPackage.GRAPH_TYPE__EDGEDEFAULT:
                return getEdgedefault();
            case GraphMLPackage.GRAPH_TYPE__ID:
                return getId();
            case GraphMLPackage.GRAPH_TYPE__PARSE_EDGEIDS:
                return getParseEdgeids();
            case GraphMLPackage.GRAPH_TYPE__PARSE_EDGES:
                return getParseEdges();
            case GraphMLPackage.GRAPH_TYPE__PARSE_MAXINDEGREE:
                return getParseMaxindegree();
            case GraphMLPackage.GRAPH_TYPE__PARSE_MAXOUTDEGREE:
                return getParseMaxoutdegree();
            case GraphMLPackage.GRAPH_TYPE__PARSE_NODEIDS:
                return getParseNodeids();
            case GraphMLPackage.GRAPH_TYPE__PARSE_NODES:
                return getParseNodes();
            case GraphMLPackage.GRAPH_TYPE__PARSE_ORDER:
                return getParseOrder();
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
            case GraphMLPackage.GRAPH_TYPE__DESC:
                setDesc((String)newValue);
                return;
            case GraphMLPackage.GRAPH_TYPE__GROUP:
                ((FeatureMap.Internal)getGroup()).set(newValue);
                return;
            case GraphMLPackage.GRAPH_TYPE__DATA:
                getData().clear();
                getData().addAll((Collection<? extends DataType>)newValue);
                return;
            case GraphMLPackage.GRAPH_TYPE__NODE:
                getNode().clear();
                getNode().addAll((Collection<? extends NodeType>)newValue);
                return;
            case GraphMLPackage.GRAPH_TYPE__EDGE:
                getEdge().clear();
                getEdge().addAll((Collection<? extends EdgeType>)newValue);
                return;
            case GraphMLPackage.GRAPH_TYPE__HYPEREDGE:
                getHyperedge().clear();
                getHyperedge().addAll((Collection<? extends HyperedgeType>)newValue);
                return;
            case GraphMLPackage.GRAPH_TYPE__LOCATOR:
                setLocator((LocatorType)newValue);
                return;
            case GraphMLPackage.GRAPH_TYPE__EDGEDEFAULT:
                setEdgedefault((GraphEdgedefaultType)newValue);
                return;
            case GraphMLPackage.GRAPH_TYPE__ID:
                setId((String)newValue);
                return;
            case GraphMLPackage.GRAPH_TYPE__PARSE_EDGEIDS:
                setParseEdgeids((GraphEdgeidsType)newValue);
                return;
            case GraphMLPackage.GRAPH_TYPE__PARSE_EDGES:
                setParseEdges((BigInteger)newValue);
                return;
            case GraphMLPackage.GRAPH_TYPE__PARSE_MAXINDEGREE:
                setParseMaxindegree((BigInteger)newValue);
                return;
            case GraphMLPackage.GRAPH_TYPE__PARSE_MAXOUTDEGREE:
                setParseMaxoutdegree((BigInteger)newValue);
                return;
            case GraphMLPackage.GRAPH_TYPE__PARSE_NODEIDS:
                setParseNodeids((GraphNodeidsType)newValue);
                return;
            case GraphMLPackage.GRAPH_TYPE__PARSE_NODES:
                setParseNodes((BigInteger)newValue);
                return;
            case GraphMLPackage.GRAPH_TYPE__PARSE_ORDER:
                setParseOrder((GraphOrderType)newValue);
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
            case GraphMLPackage.GRAPH_TYPE__DESC:
                setDesc(DESC_EDEFAULT);
                return;
            case GraphMLPackage.GRAPH_TYPE__GROUP:
                getGroup().clear();
                return;
            case GraphMLPackage.GRAPH_TYPE__DATA:
                getData().clear();
                return;
            case GraphMLPackage.GRAPH_TYPE__NODE:
                getNode().clear();
                return;
            case GraphMLPackage.GRAPH_TYPE__EDGE:
                getEdge().clear();
                return;
            case GraphMLPackage.GRAPH_TYPE__HYPEREDGE:
                getHyperedge().clear();
                return;
            case GraphMLPackage.GRAPH_TYPE__LOCATOR:
                setLocator((LocatorType)null);
                return;
            case GraphMLPackage.GRAPH_TYPE__EDGEDEFAULT:
                unsetEdgedefault();
                return;
            case GraphMLPackage.GRAPH_TYPE__ID:
                setId(ID_EDEFAULT);
                return;
            case GraphMLPackage.GRAPH_TYPE__PARSE_EDGEIDS:
                unsetParseEdgeids();
                return;
            case GraphMLPackage.GRAPH_TYPE__PARSE_EDGES:
                setParseEdges(PARSE_EDGES_EDEFAULT);
                return;
            case GraphMLPackage.GRAPH_TYPE__PARSE_MAXINDEGREE:
                setParseMaxindegree(PARSE_MAXINDEGREE_EDEFAULT);
                return;
            case GraphMLPackage.GRAPH_TYPE__PARSE_MAXOUTDEGREE:
                setParseMaxoutdegree(PARSE_MAXOUTDEGREE_EDEFAULT);
                return;
            case GraphMLPackage.GRAPH_TYPE__PARSE_NODEIDS:
                unsetParseNodeids();
                return;
            case GraphMLPackage.GRAPH_TYPE__PARSE_NODES:
                setParseNodes(PARSE_NODES_EDEFAULT);
                return;
            case GraphMLPackage.GRAPH_TYPE__PARSE_ORDER:
                unsetParseOrder();
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
            case GraphMLPackage.GRAPH_TYPE__DESC:
                return DESC_EDEFAULT == null ? desc != null : !DESC_EDEFAULT.equals(desc);
            case GraphMLPackage.GRAPH_TYPE__GROUP:
                return group != null && !group.isEmpty();
            case GraphMLPackage.GRAPH_TYPE__DATA:
                return !getData().isEmpty();
            case GraphMLPackage.GRAPH_TYPE__NODE:
                return !getNode().isEmpty();
            case GraphMLPackage.GRAPH_TYPE__EDGE:
                return !getEdge().isEmpty();
            case GraphMLPackage.GRAPH_TYPE__HYPEREDGE:
                return !getHyperedge().isEmpty();
            case GraphMLPackage.GRAPH_TYPE__LOCATOR:
                return locator != null;
            case GraphMLPackage.GRAPH_TYPE__EDGEDEFAULT:
                return isSetEdgedefault();
            case GraphMLPackage.GRAPH_TYPE__ID:
                return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
            case GraphMLPackage.GRAPH_TYPE__PARSE_EDGEIDS:
                return isSetParseEdgeids();
            case GraphMLPackage.GRAPH_TYPE__PARSE_EDGES:
                return PARSE_EDGES_EDEFAULT == null ? parseEdges != null : !PARSE_EDGES_EDEFAULT.equals(parseEdges);
            case GraphMLPackage.GRAPH_TYPE__PARSE_MAXINDEGREE:
                return PARSE_MAXINDEGREE_EDEFAULT == null ? parseMaxindegree != null : !PARSE_MAXINDEGREE_EDEFAULT.equals(parseMaxindegree);
            case GraphMLPackage.GRAPH_TYPE__PARSE_MAXOUTDEGREE:
                return PARSE_MAXOUTDEGREE_EDEFAULT == null ? parseMaxoutdegree != null : !PARSE_MAXOUTDEGREE_EDEFAULT.equals(parseMaxoutdegree);
            case GraphMLPackage.GRAPH_TYPE__PARSE_NODEIDS:
                return isSetParseNodeids();
            case GraphMLPackage.GRAPH_TYPE__PARSE_NODES:
                return PARSE_NODES_EDEFAULT == null ? parseNodes != null : !PARSE_NODES_EDEFAULT.equals(parseNodes);
            case GraphMLPackage.GRAPH_TYPE__PARSE_ORDER:
                return isSetParseOrder();
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
        result.append(", edgedefault: ");
        if (edgedefaultESet) result.append(edgedefault); else result.append("<unset>");
        result.append(", id: ");
        result.append(id);
        result.append(", parseEdgeids: ");
        if (parseEdgeidsESet) result.append(parseEdgeids); else result.append("<unset>");
        result.append(", parseEdges: ");
        result.append(parseEdges);
        result.append(", parseMaxindegree: ");
        result.append(parseMaxindegree);
        result.append(", parseMaxoutdegree: ");
        result.append(parseMaxoutdegree);
        result.append(", parseNodeids: ");
        if (parseNodeidsESet) result.append(parseNodeids); else result.append("<unset>");
        result.append(", parseNodes: ");
        result.append(parseNodes);
        result.append(", parseOrder: ");
        if (parseOrderESet) result.append(parseOrder); else result.append("<unset>");
        result.append(')');
        return result.toString();
    }

} //GraphTypeImpl
