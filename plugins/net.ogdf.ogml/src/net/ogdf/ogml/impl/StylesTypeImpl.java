/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.ogdf.ogml.impl;

import java.util.Collection;

import net.ogdf.ogml.DataType;
import net.ogdf.ogml.EdgeLayoutType;
import net.ogdf.ogml.GraphStyleType;
import net.ogdf.ogml.LabelLayoutType;
import net.ogdf.ogml.NodeLayoutType;
import net.ogdf.ogml.OgmlPackage;
import net.ogdf.ogml.StylesType;

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

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Styles Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link net.ogdf.ogml.impl.StylesTypeImpl#getGraphStyle <em>Graph Style</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.StylesTypeImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.StylesTypeImpl#getData <em>Data</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.StylesTypeImpl#getNodeStyle <em>Node Style</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.StylesTypeImpl#getEdgeStyle <em>Edge Style</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.StylesTypeImpl#getLabelStyle <em>Label Style</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StylesTypeImpl extends EObjectImpl implements StylesType {
    /**
     * The cached value of the '{@link #getGraphStyle() <em>Graph Style</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getGraphStyle()
     * @generated
     * @ordered
     */
    protected GraphStyleType graphStyle;

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
    protected StylesTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return OgmlPackage.Literals.STYLES_TYPE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GraphStyleType getGraphStyle() {
        return graphStyle;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetGraphStyle(GraphStyleType newGraphStyle, NotificationChain msgs) {
        GraphStyleType oldGraphStyle = graphStyle;
        graphStyle = newGraphStyle;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OgmlPackage.STYLES_TYPE__GRAPH_STYLE, oldGraphStyle, newGraphStyle);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setGraphStyle(GraphStyleType newGraphStyle) {
        if (newGraphStyle != graphStyle) {
            NotificationChain msgs = null;
            if (graphStyle != null)
                msgs = ((InternalEObject)graphStyle).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OgmlPackage.STYLES_TYPE__GRAPH_STYLE, null, msgs);
            if (newGraphStyle != null)
                msgs = ((InternalEObject)newGraphStyle).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OgmlPackage.STYLES_TYPE__GRAPH_STYLE, null, msgs);
            msgs = basicSetGraphStyle(newGraphStyle, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OgmlPackage.STYLES_TYPE__GRAPH_STYLE, newGraphStyle, newGraphStyle));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureMap getGroup() {
        if (group == null) {
            group = new BasicFeatureMap(this, OgmlPackage.STYLES_TYPE__GROUP);
        }
        return group;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<DataType> getData() {
        return getGroup().list(OgmlPackage.Literals.STYLES_TYPE__DATA);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<NodeLayoutType> getNodeStyle() {
        return getGroup().list(OgmlPackage.Literals.STYLES_TYPE__NODE_STYLE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<EdgeLayoutType> getEdgeStyle() {
        return getGroup().list(OgmlPackage.Literals.STYLES_TYPE__EDGE_STYLE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<LabelLayoutType> getLabelStyle() {
        return getGroup().list(OgmlPackage.Literals.STYLES_TYPE__LABEL_STYLE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case OgmlPackage.STYLES_TYPE__GRAPH_STYLE:
                return basicSetGraphStyle(null, msgs);
            case OgmlPackage.STYLES_TYPE__GROUP:
                return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
            case OgmlPackage.STYLES_TYPE__DATA:
                return ((InternalEList<?>)getData()).basicRemove(otherEnd, msgs);
            case OgmlPackage.STYLES_TYPE__NODE_STYLE:
                return ((InternalEList<?>)getNodeStyle()).basicRemove(otherEnd, msgs);
            case OgmlPackage.STYLES_TYPE__EDGE_STYLE:
                return ((InternalEList<?>)getEdgeStyle()).basicRemove(otherEnd, msgs);
            case OgmlPackage.STYLES_TYPE__LABEL_STYLE:
                return ((InternalEList<?>)getLabelStyle()).basicRemove(otherEnd, msgs);
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
            case OgmlPackage.STYLES_TYPE__GRAPH_STYLE:
                return getGraphStyle();
            case OgmlPackage.STYLES_TYPE__GROUP:
                if (coreType) return getGroup();
                return ((FeatureMap.Internal)getGroup()).getWrapper();
            case OgmlPackage.STYLES_TYPE__DATA:
                return getData();
            case OgmlPackage.STYLES_TYPE__NODE_STYLE:
                return getNodeStyle();
            case OgmlPackage.STYLES_TYPE__EDGE_STYLE:
                return getEdgeStyle();
            case OgmlPackage.STYLES_TYPE__LABEL_STYLE:
                return getLabelStyle();
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
            case OgmlPackage.STYLES_TYPE__GRAPH_STYLE:
                setGraphStyle((GraphStyleType)newValue);
                return;
            case OgmlPackage.STYLES_TYPE__GROUP:
                ((FeatureMap.Internal)getGroup()).set(newValue);
                return;
            case OgmlPackage.STYLES_TYPE__DATA:
                getData().clear();
                getData().addAll((Collection<? extends DataType>)newValue);
                return;
            case OgmlPackage.STYLES_TYPE__NODE_STYLE:
                getNodeStyle().clear();
                getNodeStyle().addAll((Collection<? extends NodeLayoutType>)newValue);
                return;
            case OgmlPackage.STYLES_TYPE__EDGE_STYLE:
                getEdgeStyle().clear();
                getEdgeStyle().addAll((Collection<? extends EdgeLayoutType>)newValue);
                return;
            case OgmlPackage.STYLES_TYPE__LABEL_STYLE:
                getLabelStyle().clear();
                getLabelStyle().addAll((Collection<? extends LabelLayoutType>)newValue);
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
            case OgmlPackage.STYLES_TYPE__GRAPH_STYLE:
                setGraphStyle((GraphStyleType)null);
                return;
            case OgmlPackage.STYLES_TYPE__GROUP:
                getGroup().clear();
                return;
            case OgmlPackage.STYLES_TYPE__DATA:
                getData().clear();
                return;
            case OgmlPackage.STYLES_TYPE__NODE_STYLE:
                getNodeStyle().clear();
                return;
            case OgmlPackage.STYLES_TYPE__EDGE_STYLE:
                getEdgeStyle().clear();
                return;
            case OgmlPackage.STYLES_TYPE__LABEL_STYLE:
                getLabelStyle().clear();
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
            case OgmlPackage.STYLES_TYPE__GRAPH_STYLE:
                return graphStyle != null;
            case OgmlPackage.STYLES_TYPE__GROUP:
                return group != null && !group.isEmpty();
            case OgmlPackage.STYLES_TYPE__DATA:
                return !getData().isEmpty();
            case OgmlPackage.STYLES_TYPE__NODE_STYLE:
                return !getNodeStyle().isEmpty();
            case OgmlPackage.STYLES_TYPE__EDGE_STYLE:
                return !getEdgeStyle().isEmpty();
            case OgmlPackage.STYLES_TYPE__LABEL_STYLE:
                return !getLabelStyle().isEmpty();
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
        result.append(" (group: ");
        result.append(group);
        result.append(')');
        return result.toString();
    }

} //StylesTypeImpl
