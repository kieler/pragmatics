/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.graphdrawing.graphml.impl;

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

import org.graphdrawing.graphml.DataType;
import org.graphdrawing.graphml.EdgeType;
import org.graphdrawing.graphml.GraphMLPackage;
import org.graphdrawing.graphml.GraphType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Edge Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.graphdrawing.graphml.impl.EdgeTypeImpl#getDesc <em>Desc</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.impl.EdgeTypeImpl#getData <em>Data</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.impl.EdgeTypeImpl#getGraph <em>Graph</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.impl.EdgeTypeImpl#isDirected <em>Directed</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.impl.EdgeTypeImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.impl.EdgeTypeImpl#getSource <em>Source</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.impl.EdgeTypeImpl#getSourceport <em>Sourceport</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.impl.EdgeTypeImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.impl.EdgeTypeImpl#getTargetport <em>Targetport</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EdgeTypeImpl extends EObjectImpl implements EdgeType {
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
     * The cached value of the '{@link #getData() <em>Data</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getData()
     * @generated
     * @ordered
     */
    protected EList<DataType> data;

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
     * The default value of the '{@link #isDirected() <em>Directed</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isDirected()
     * @generated
     * @ordered
     */
    protected static final boolean DIRECTED_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isDirected() <em>Directed</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isDirected()
     * @generated
     * @ordered
     */
    protected boolean directed = DIRECTED_EDEFAULT;

    /**
     * This is true if the Directed attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean directedESet;

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
     * The default value of the '{@link #getSource() <em>Source</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSource()
     * @generated
     * @ordered
     */
    protected static final String SOURCE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getSource() <em>Source</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSource()
     * @generated
     * @ordered
     */
    protected String source = SOURCE_EDEFAULT;

    /**
     * The default value of the '{@link #getSourceport() <em>Sourceport</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSourceport()
     * @generated
     * @ordered
     */
    protected static final String SOURCEPORT_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getSourceport() <em>Sourceport</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSourceport()
     * @generated
     * @ordered
     */
    protected String sourceport = SOURCEPORT_EDEFAULT;

    /**
     * The default value of the '{@link #getTarget() <em>Target</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTarget()
     * @generated
     * @ordered
     */
    protected static final String TARGET_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTarget() <em>Target</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTarget()
     * @generated
     * @ordered
     */
    protected String target = TARGET_EDEFAULT;

    /**
     * The default value of the '{@link #getTargetport() <em>Targetport</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTargetport()
     * @generated
     * @ordered
     */
    protected static final String TARGETPORT_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTargetport() <em>Targetport</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTargetport()
     * @generated
     * @ordered
     */
    protected String targetport = TARGETPORT_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EdgeTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return GraphMLPackage.Literals.EDGE_TYPE;
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
            eNotify(new ENotificationImpl(this, Notification.SET, GraphMLPackage.EDGE_TYPE__DESC, oldDesc, desc));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<DataType> getData() {
        if (data == null) {
            data = new EObjectContainmentEList<DataType>(DataType.class, this, GraphMLPackage.EDGE_TYPE__DATA);
        }
        return data;
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, GraphMLPackage.EDGE_TYPE__GRAPH, oldGraph, newGraph);
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
                msgs = ((InternalEObject)graph).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - GraphMLPackage.EDGE_TYPE__GRAPH, null, msgs);
            if (newGraph != null)
                msgs = ((InternalEObject)newGraph).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - GraphMLPackage.EDGE_TYPE__GRAPH, null, msgs);
            msgs = basicSetGraph(newGraph, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GraphMLPackage.EDGE_TYPE__GRAPH, newGraph, newGraph));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isDirected() {
        return directed;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDirected(boolean newDirected) {
        boolean oldDirected = directed;
        directed = newDirected;
        boolean oldDirectedESet = directedESet;
        directedESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GraphMLPackage.EDGE_TYPE__DIRECTED, oldDirected, directed, !oldDirectedESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetDirected() {
        boolean oldDirected = directed;
        boolean oldDirectedESet = directedESet;
        directed = DIRECTED_EDEFAULT;
        directedESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, GraphMLPackage.EDGE_TYPE__DIRECTED, oldDirected, DIRECTED_EDEFAULT, oldDirectedESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetDirected() {
        return directedESet;
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
            eNotify(new ENotificationImpl(this, Notification.SET, GraphMLPackage.EDGE_TYPE__ID, oldId, id));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getSource() {
        return source;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSource(String newSource) {
        String oldSource = source;
        source = newSource;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GraphMLPackage.EDGE_TYPE__SOURCE, oldSource, source));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getSourceport() {
        return sourceport;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSourceport(String newSourceport) {
        String oldSourceport = sourceport;
        sourceport = newSourceport;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GraphMLPackage.EDGE_TYPE__SOURCEPORT, oldSourceport, sourceport));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getTarget() {
        return target;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTarget(String newTarget) {
        String oldTarget = target;
        target = newTarget;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GraphMLPackage.EDGE_TYPE__TARGET, oldTarget, target));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getTargetport() {
        return targetport;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTargetport(String newTargetport) {
        String oldTargetport = targetport;
        targetport = newTargetport;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GraphMLPackage.EDGE_TYPE__TARGETPORT, oldTargetport, targetport));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case GraphMLPackage.EDGE_TYPE__DATA:
                return ((InternalEList<?>)getData()).basicRemove(otherEnd, msgs);
            case GraphMLPackage.EDGE_TYPE__GRAPH:
                return basicSetGraph(null, msgs);
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
            case GraphMLPackage.EDGE_TYPE__DESC:
                return getDesc();
            case GraphMLPackage.EDGE_TYPE__DATA:
                return getData();
            case GraphMLPackage.EDGE_TYPE__GRAPH:
                return getGraph();
            case GraphMLPackage.EDGE_TYPE__DIRECTED:
                return isDirected();
            case GraphMLPackage.EDGE_TYPE__ID:
                return getId();
            case GraphMLPackage.EDGE_TYPE__SOURCE:
                return getSource();
            case GraphMLPackage.EDGE_TYPE__SOURCEPORT:
                return getSourceport();
            case GraphMLPackage.EDGE_TYPE__TARGET:
                return getTarget();
            case GraphMLPackage.EDGE_TYPE__TARGETPORT:
                return getTargetport();
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
            case GraphMLPackage.EDGE_TYPE__DESC:
                setDesc((String)newValue);
                return;
            case GraphMLPackage.EDGE_TYPE__DATA:
                getData().clear();
                getData().addAll((Collection<? extends DataType>)newValue);
                return;
            case GraphMLPackage.EDGE_TYPE__GRAPH:
                setGraph((GraphType)newValue);
                return;
            case GraphMLPackage.EDGE_TYPE__DIRECTED:
                setDirected((Boolean)newValue);
                return;
            case GraphMLPackage.EDGE_TYPE__ID:
                setId((String)newValue);
                return;
            case GraphMLPackage.EDGE_TYPE__SOURCE:
                setSource((String)newValue);
                return;
            case GraphMLPackage.EDGE_TYPE__SOURCEPORT:
                setSourceport((String)newValue);
                return;
            case GraphMLPackage.EDGE_TYPE__TARGET:
                setTarget((String)newValue);
                return;
            case GraphMLPackage.EDGE_TYPE__TARGETPORT:
                setTargetport((String)newValue);
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
            case GraphMLPackage.EDGE_TYPE__DESC:
                setDesc(DESC_EDEFAULT);
                return;
            case GraphMLPackage.EDGE_TYPE__DATA:
                getData().clear();
                return;
            case GraphMLPackage.EDGE_TYPE__GRAPH:
                setGraph((GraphType)null);
                return;
            case GraphMLPackage.EDGE_TYPE__DIRECTED:
                unsetDirected();
                return;
            case GraphMLPackage.EDGE_TYPE__ID:
                setId(ID_EDEFAULT);
                return;
            case GraphMLPackage.EDGE_TYPE__SOURCE:
                setSource(SOURCE_EDEFAULT);
                return;
            case GraphMLPackage.EDGE_TYPE__SOURCEPORT:
                setSourceport(SOURCEPORT_EDEFAULT);
                return;
            case GraphMLPackage.EDGE_TYPE__TARGET:
                setTarget(TARGET_EDEFAULT);
                return;
            case GraphMLPackage.EDGE_TYPE__TARGETPORT:
                setTargetport(TARGETPORT_EDEFAULT);
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
            case GraphMLPackage.EDGE_TYPE__DESC:
                return DESC_EDEFAULT == null ? desc != null : !DESC_EDEFAULT.equals(desc);
            case GraphMLPackage.EDGE_TYPE__DATA:
                return data != null && !data.isEmpty();
            case GraphMLPackage.EDGE_TYPE__GRAPH:
                return graph != null;
            case GraphMLPackage.EDGE_TYPE__DIRECTED:
                return isSetDirected();
            case GraphMLPackage.EDGE_TYPE__ID:
                return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
            case GraphMLPackage.EDGE_TYPE__SOURCE:
                return SOURCE_EDEFAULT == null ? source != null : !SOURCE_EDEFAULT.equals(source);
            case GraphMLPackage.EDGE_TYPE__SOURCEPORT:
                return SOURCEPORT_EDEFAULT == null ? sourceport != null : !SOURCEPORT_EDEFAULT.equals(sourceport);
            case GraphMLPackage.EDGE_TYPE__TARGET:
                return TARGET_EDEFAULT == null ? target != null : !TARGET_EDEFAULT.equals(target);
            case GraphMLPackage.EDGE_TYPE__TARGETPORT:
                return TARGETPORT_EDEFAULT == null ? targetport != null : !TARGETPORT_EDEFAULT.equals(targetport);
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
        result.append(", directed: ");
        if (directedESet) result.append(directed); else result.append("<unset>");
        result.append(", id: ");
        result.append(id);
        result.append(", source: ");
        result.append(source);
        result.append(", sourceport: ");
        result.append(sourceport);
        result.append(", target: ");
        result.append(target);
        result.append(", targetport: ");
        result.append(targetport);
        result.append(')');
        return result.toString();
    }

} //EdgeTypeImpl
