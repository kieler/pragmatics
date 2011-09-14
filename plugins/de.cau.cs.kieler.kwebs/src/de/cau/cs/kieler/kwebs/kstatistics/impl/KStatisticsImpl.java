/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.cs.kieler.kwebs.kstatistics.impl;

import de.cau.cs.kieler.core.kgraph.impl.KGraphDataImpl;

import de.cau.cs.kieler.kwebs.kstatistics.KStatistics;
import de.cau.cs.kieler.kwebs.kstatistics.KStatisticsPackage;

import de.cau.cs.kieler.kwebs.kstatistics.LayoutType;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>KStatistics</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.kwebs.kstatistics.impl.KStatisticsImpl#getType <em>Type</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.kstatistics.impl.KStatisticsImpl#getBytes <em>Bytes</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.kstatistics.impl.KStatisticsImpl#isCompression <em>Compression</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.kstatistics.impl.KStatisticsImpl#getEdges <em>Edges</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.kstatistics.impl.KStatisticsImpl#getNodes <em>Nodes</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.kstatistics.impl.KStatisticsImpl#getPorts <em>Ports</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.kstatistics.impl.KStatisticsImpl#getLabels <em>Labels</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.kstatistics.impl.KStatisticsImpl#getTimeTotal <em>Time Total</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.kstatistics.impl.KStatisticsImpl#getTimeNetwork <em>Time Network</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.kstatistics.impl.KStatisticsImpl#getTimeLayout <em>Time Layout</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.kstatistics.impl.KStatisticsImpl#getTimeLocalSupplemental <em>Time Local Supplemental</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.kstatistics.impl.KStatisticsImpl#getTimeRemoteSupplemental <em>Time Remote Supplemental</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class KStatisticsImpl extends KGraphDataImpl implements KStatistics {
    /**
     * The default value of the '{@link #getType() <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
    protected static final LayoutType TYPE_EDEFAULT = LayoutType.LOCAL_LAYOUT;
    /**
     * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
    protected LayoutType type = TYPE_EDEFAULT;
    /**
     * The default value of the '{@link #getBytes() <em>Bytes</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBytes()
     * @generated
     * @ordered
     */
    protected static final int BYTES_EDEFAULT = 0;
    /**
     * The cached value of the '{@link #getBytes() <em>Bytes</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBytes()
     * @generated
     * @ordered
     */
    protected int bytes = BYTES_EDEFAULT;
    /**
     * The default value of the '{@link #isCompression() <em>Compression</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isCompression()
     * @generated
     * @ordered
     */
    protected static final boolean COMPRESSION_EDEFAULT = false;
    /**
     * The cached value of the '{@link #isCompression() <em>Compression</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isCompression()
     * @generated
     * @ordered
     */
    protected boolean compression = COMPRESSION_EDEFAULT;
    /**
     * The default value of the '{@link #getEdges() <em>Edges</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getEdges()
     * @generated
     * @ordered
     */
    protected static final int EDGES_EDEFAULT = 0;
    /**
     * The cached value of the '{@link #getEdges() <em>Edges</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getEdges()
     * @generated
     * @ordered
     */
    protected int edges = EDGES_EDEFAULT;
    /**
     * The default value of the '{@link #getNodes() <em>Nodes</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNodes()
     * @generated
     * @ordered
     */
    protected static final int NODES_EDEFAULT = 0;
    /**
     * The cached value of the '{@link #getNodes() <em>Nodes</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNodes()
     * @generated
     * @ordered
     */
    protected int nodes = NODES_EDEFAULT;
    /**
     * The default value of the '{@link #getPorts() <em>Ports</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPorts()
     * @generated
     * @ordered
     */
    protected static final int PORTS_EDEFAULT = 0;
    /**
     * The cached value of the '{@link #getPorts() <em>Ports</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPorts()
     * @generated
     * @ordered
     */
    protected int ports = PORTS_EDEFAULT;
    /**
     * The default value of the '{@link #getLabels() <em>Labels</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLabels()
     * @generated
     * @ordered
     */
    protected static final int LABELS_EDEFAULT = 0;
    /**
     * The cached value of the '{@link #getLabels() <em>Labels</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLabels()
     * @generated
     * @ordered
     */
    protected int labels = LABELS_EDEFAULT;
    /**
     * The default value of the '{@link #getTimeTotal() <em>Time Total</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTimeTotal()
     * @generated
     * @ordered
     */
    protected static final double TIME_TOTAL_EDEFAULT = 0.0;
    /**
     * The cached value of the '{@link #getTimeTotal() <em>Time Total</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTimeTotal()
     * @generated
     * @ordered
     */
    protected double timeTotal = TIME_TOTAL_EDEFAULT;
    /**
     * The default value of the '{@link #getTimeNetwork() <em>Time Network</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTimeNetwork()
     * @generated
     * @ordered
     */
    protected static final double TIME_NETWORK_EDEFAULT = 0.0;
    /**
     * The cached value of the '{@link #getTimeNetwork() <em>Time Network</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTimeNetwork()
     * @generated
     * @ordered
     */
    protected double timeNetwork = TIME_NETWORK_EDEFAULT;
    /**
     * The default value of the '{@link #getTimeLayout() <em>Time Layout</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTimeLayout()
     * @generated
     * @ordered
     */
    protected static final double TIME_LAYOUT_EDEFAULT = 0.0;
    /**
     * The cached value of the '{@link #getTimeLayout() <em>Time Layout</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTimeLayout()
     * @generated
     * @ordered
     */
    protected double timeLayout = TIME_LAYOUT_EDEFAULT;
    /**
     * The default value of the '{@link #getTimeLocalSupplemental() <em>Time Local Supplemental</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTimeLocalSupplemental()
     * @generated
     * @ordered
     */
    protected static final double TIME_LOCAL_SUPPLEMENTAL_EDEFAULT = 0.0;
    /**
     * The cached value of the '{@link #getTimeLocalSupplemental() <em>Time Local Supplemental</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTimeLocalSupplemental()
     * @generated
     * @ordered
     */
    protected double timeLocalSupplemental = TIME_LOCAL_SUPPLEMENTAL_EDEFAULT;
    /**
     * The default value of the '{@link #getTimeRemoteSupplemental() <em>Time Remote Supplemental</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTimeRemoteSupplemental()
     * @generated
     * @ordered
     */
    protected static final double TIME_REMOTE_SUPPLEMENTAL_EDEFAULT = 0.0;
    /**
     * The cached value of the '{@link #getTimeRemoteSupplemental() <em>Time Remote Supplemental</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTimeRemoteSupplemental()
     * @generated
     * @ordered
     */
    protected double timeRemoteSupplemental = TIME_REMOTE_SUPPLEMENTAL_EDEFAULT;
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected KStatisticsImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return KStatisticsPackage.Literals.KSTATISTICS;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LayoutType getType() {
        return type;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setType(LayoutType newType) {
        LayoutType oldType = type;
        type = newType == null ? TYPE_EDEFAULT : newType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KStatisticsPackage.KSTATISTICS__TYPE, oldType, type));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getBytes() {
        return bytes;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setBytes(int newBytes) {
        int oldBytes = bytes;
        bytes = newBytes;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KStatisticsPackage.KSTATISTICS__BYTES, oldBytes, bytes));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isCompression() {
        return compression;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCompression(boolean newCompression) {
        boolean oldCompression = compression;
        compression = newCompression;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KStatisticsPackage.KSTATISTICS__COMPRESSION, oldCompression, compression));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getEdges() {
        return edges;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setEdges(int newEdges) {
        int oldEdges = edges;
        edges = newEdges;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KStatisticsPackage.KSTATISTICS__EDGES, oldEdges, edges));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getNodes() {
        return nodes;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setNodes(int newNodes) {
        int oldNodes = nodes;
        nodes = newNodes;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KStatisticsPackage.KSTATISTICS__NODES, oldNodes, nodes));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getPorts() {
        return ports;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPorts(int newPorts) {
        int oldPorts = ports;
        ports = newPorts;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KStatisticsPackage.KSTATISTICS__PORTS, oldPorts, ports));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getLabels() {
        return labels;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLabels(int newLabels) {
        int oldLabels = labels;
        labels = newLabels;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KStatisticsPackage.KSTATISTICS__LABELS, oldLabels, labels));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public double getTimeTotal() {
        return timeTotal;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTimeTotal(double newTimeTotal) {
        double oldTimeTotal = timeTotal;
        timeTotal = newTimeTotal;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KStatisticsPackage.KSTATISTICS__TIME_TOTAL, oldTimeTotal, timeTotal));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public double getTimeNetwork() {
        return timeNetwork;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTimeNetwork(double newTimeNetwork) {
        double oldTimeNetwork = timeNetwork;
        timeNetwork = newTimeNetwork;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KStatisticsPackage.KSTATISTICS__TIME_NETWORK, oldTimeNetwork, timeNetwork));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public double getTimeLayout() {
        return timeLayout;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTimeLayout(double newTimeLayout) {
        double oldTimeLayout = timeLayout;
        timeLayout = newTimeLayout;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KStatisticsPackage.KSTATISTICS__TIME_LAYOUT, oldTimeLayout, timeLayout));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public double getTimeLocalSupplemental() {
        return timeLocalSupplemental;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTimeLocalSupplemental(double newTimeLocalSupplemental) {
        double oldTimeLocalSupplemental = timeLocalSupplemental;
        timeLocalSupplemental = newTimeLocalSupplemental;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KStatisticsPackage.KSTATISTICS__TIME_LOCAL_SUPPLEMENTAL, oldTimeLocalSupplemental, timeLocalSupplemental));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public double getTimeRemoteSupplemental() {
        return timeRemoteSupplemental;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTimeRemoteSupplemental(double newTimeRemoteSupplemental) {
        double oldTimeRemoteSupplemental = timeRemoteSupplemental;
        timeRemoteSupplemental = newTimeRemoteSupplemental;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KStatisticsPackage.KSTATISTICS__TIME_REMOTE_SUPPLEMENTAL, oldTimeRemoteSupplemental, timeRemoteSupplemental));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public int getElementCount() {
        return getNodes() + getPorts() + getLabels() + getEdges();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public double getTotalSpeed() {
        if (getTimeTotal() == 0) {
            return 0;
        }
        return getElementCount() / (getTimeTotal() * 1e-9);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public double getLayoutSpeed() {
        if (getTimeLayout() == 0) {
            return 0;
        }
        return getElementCount() / (getTimeLayout() * 1e-9);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public double getNetworkPart() {
        if (getTimeTotal() == 0) {
            return 0;
        }
        return ((getTimeNetwork() - getTimeLayout() - getTimeRemoteSupplemental()) / getTimeTotal()) * 100;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public double getLayoutPart() {
        if (getTimeTotal() == 0) {
            return 0;
        }
        return (getTimeLayout() / getTimeTotal()) * 100;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public double getSupplementalPart() {
        if (getTimeTotal() == 0) {
            return 0;
        }
        return ((getTimeLocalSupplemental() + getTimeRemoteSupplemental())/ getTimeTotal()) * 100;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public double getLocalSupplementalPart() {
        if (getTimeTotal() == 0) {
            return 0;
        }
        return (getTimeLocalSupplemental() / getTimeTotal()) * 100;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public double getRemoteSupplementalPart() {
        if (getTimeTotal() == 0) {
            return 0;
        }
        return (getTimeRemoteSupplemental() / getTimeTotal()) * 100;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case KStatisticsPackage.KSTATISTICS__TYPE:
                return getType();
            case KStatisticsPackage.KSTATISTICS__BYTES:
                return getBytes();
            case KStatisticsPackage.KSTATISTICS__COMPRESSION:
                return isCompression();
            case KStatisticsPackage.KSTATISTICS__EDGES:
                return getEdges();
            case KStatisticsPackage.KSTATISTICS__NODES:
                return getNodes();
            case KStatisticsPackage.KSTATISTICS__PORTS:
                return getPorts();
            case KStatisticsPackage.KSTATISTICS__LABELS:
                return getLabels();
            case KStatisticsPackage.KSTATISTICS__TIME_TOTAL:
                return getTimeTotal();
            case KStatisticsPackage.KSTATISTICS__TIME_NETWORK:
                return getTimeNetwork();
            case KStatisticsPackage.KSTATISTICS__TIME_LAYOUT:
                return getTimeLayout();
            case KStatisticsPackage.KSTATISTICS__TIME_LOCAL_SUPPLEMENTAL:
                return getTimeLocalSupplemental();
            case KStatisticsPackage.KSTATISTICS__TIME_REMOTE_SUPPLEMENTAL:
                return getTimeRemoteSupplemental();
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
            case KStatisticsPackage.KSTATISTICS__TYPE:
                setType((LayoutType)newValue);
                return;
            case KStatisticsPackage.KSTATISTICS__BYTES:
                setBytes((Integer)newValue);
                return;
            case KStatisticsPackage.KSTATISTICS__COMPRESSION:
                setCompression((Boolean)newValue);
                return;
            case KStatisticsPackage.KSTATISTICS__EDGES:
                setEdges((Integer)newValue);
                return;
            case KStatisticsPackage.KSTATISTICS__NODES:
                setNodes((Integer)newValue);
                return;
            case KStatisticsPackage.KSTATISTICS__PORTS:
                setPorts((Integer)newValue);
                return;
            case KStatisticsPackage.KSTATISTICS__LABELS:
                setLabels((Integer)newValue);
                return;
            case KStatisticsPackage.KSTATISTICS__TIME_TOTAL:
                setTimeTotal((Double)newValue);
                return;
            case KStatisticsPackage.KSTATISTICS__TIME_NETWORK:
                setTimeNetwork((Double)newValue);
                return;
            case KStatisticsPackage.KSTATISTICS__TIME_LAYOUT:
                setTimeLayout((Double)newValue);
                return;
            case KStatisticsPackage.KSTATISTICS__TIME_LOCAL_SUPPLEMENTAL:
                setTimeLocalSupplemental((Double)newValue);
                return;
            case KStatisticsPackage.KSTATISTICS__TIME_REMOTE_SUPPLEMENTAL:
                setTimeRemoteSupplemental((Double)newValue);
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
            case KStatisticsPackage.KSTATISTICS__TYPE:
                setType(TYPE_EDEFAULT);
                return;
            case KStatisticsPackage.KSTATISTICS__BYTES:
                setBytes(BYTES_EDEFAULT);
                return;
            case KStatisticsPackage.KSTATISTICS__COMPRESSION:
                setCompression(COMPRESSION_EDEFAULT);
                return;
            case KStatisticsPackage.KSTATISTICS__EDGES:
                setEdges(EDGES_EDEFAULT);
                return;
            case KStatisticsPackage.KSTATISTICS__NODES:
                setNodes(NODES_EDEFAULT);
                return;
            case KStatisticsPackage.KSTATISTICS__PORTS:
                setPorts(PORTS_EDEFAULT);
                return;
            case KStatisticsPackage.KSTATISTICS__LABELS:
                setLabels(LABELS_EDEFAULT);
                return;
            case KStatisticsPackage.KSTATISTICS__TIME_TOTAL:
                setTimeTotal(TIME_TOTAL_EDEFAULT);
                return;
            case KStatisticsPackage.KSTATISTICS__TIME_NETWORK:
                setTimeNetwork(TIME_NETWORK_EDEFAULT);
                return;
            case KStatisticsPackage.KSTATISTICS__TIME_LAYOUT:
                setTimeLayout(TIME_LAYOUT_EDEFAULT);
                return;
            case KStatisticsPackage.KSTATISTICS__TIME_LOCAL_SUPPLEMENTAL:
                setTimeLocalSupplemental(TIME_LOCAL_SUPPLEMENTAL_EDEFAULT);
                return;
            case KStatisticsPackage.KSTATISTICS__TIME_REMOTE_SUPPLEMENTAL:
                setTimeRemoteSupplemental(TIME_REMOTE_SUPPLEMENTAL_EDEFAULT);
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
            case KStatisticsPackage.KSTATISTICS__TYPE:
                return type != TYPE_EDEFAULT;
            case KStatisticsPackage.KSTATISTICS__BYTES:
                return bytes != BYTES_EDEFAULT;
            case KStatisticsPackage.KSTATISTICS__COMPRESSION:
                return compression != COMPRESSION_EDEFAULT;
            case KStatisticsPackage.KSTATISTICS__EDGES:
                return edges != EDGES_EDEFAULT;
            case KStatisticsPackage.KSTATISTICS__NODES:
                return nodes != NODES_EDEFAULT;
            case KStatisticsPackage.KSTATISTICS__PORTS:
                return ports != PORTS_EDEFAULT;
            case KStatisticsPackage.KSTATISTICS__LABELS:
                return labels != LABELS_EDEFAULT;
            case KStatisticsPackage.KSTATISTICS__TIME_TOTAL:
                return timeTotal != TIME_TOTAL_EDEFAULT;
            case KStatisticsPackage.KSTATISTICS__TIME_NETWORK:
                return timeNetwork != TIME_NETWORK_EDEFAULT;
            case KStatisticsPackage.KSTATISTICS__TIME_LAYOUT:
                return timeLayout != TIME_LAYOUT_EDEFAULT;
            case KStatisticsPackage.KSTATISTICS__TIME_LOCAL_SUPPLEMENTAL:
                return timeLocalSupplemental != TIME_LOCAL_SUPPLEMENTAL_EDEFAULT;
            case KStatisticsPackage.KSTATISTICS__TIME_REMOTE_SUPPLEMENTAL:
                return timeRemoteSupplemental != TIME_REMOTE_SUPPLEMENTAL_EDEFAULT;
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
        result.append(" (type: ");
        result.append(type);
        result.append(", bytes: ");
        result.append(bytes);
        result.append(", compression: ");
        result.append(compression);
        result.append(", edges: ");
        result.append(edges);
        result.append(", nodes: ");
        result.append(nodes);
        result.append(", ports: ");
        result.append(ports);
        result.append(", labels: ");
        result.append(labels);
        result.append(", timeTotal: ");
        result.append(timeTotal);
        result.append(", timeNetwork: ");
        result.append(timeNetwork);
        result.append(", timeLayout: ");
        result.append(timeLayout);
        result.append(", timeLocalSupplemental: ");
        result.append(timeLocalSupplemental);
        result.append(", timeRemoteSupplemental: ");
        result.append(timeRemoteSupplemental);
        result.append(')');
        return result.toString();
    }

} //KStatisticsImpl
