/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.ogdf.ogml.impl;

import java.util.Collection;

import net.ogdf.ogml.DataType;
import net.ogdf.ogml.GraphStyleType;
import net.ogdf.ogml.OgmlPackage;

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
 * An implementation of the model object '<em><b>Graph Style Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link net.ogdf.ogml.impl.GraphStyleTypeImpl#getData <em>Data</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.GraphStyleTypeImpl#getDefaultEdgeTemplate <em>Default Edge Template</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.GraphStyleTypeImpl#getDefaultLabelTemplate <em>Default Label Template</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.GraphStyleTypeImpl#getDefaultNodeTemplate <em>Default Node Template</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GraphStyleTypeImpl extends EObjectImpl implements GraphStyleType {
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
     * The default value of the '{@link #getDefaultEdgeTemplate() <em>Default Edge Template</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDefaultEdgeTemplate()
     * @generated
     * @ordered
     */
    protected static final String DEFAULT_EDGE_TEMPLATE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDefaultEdgeTemplate() <em>Default Edge Template</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDefaultEdgeTemplate()
     * @generated
     * @ordered
     */
    protected String defaultEdgeTemplate = DEFAULT_EDGE_TEMPLATE_EDEFAULT;

    /**
     * The default value of the '{@link #getDefaultLabelTemplate() <em>Default Label Template</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDefaultLabelTemplate()
     * @generated
     * @ordered
     */
    protected static final String DEFAULT_LABEL_TEMPLATE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDefaultLabelTemplate() <em>Default Label Template</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDefaultLabelTemplate()
     * @generated
     * @ordered
     */
    protected String defaultLabelTemplate = DEFAULT_LABEL_TEMPLATE_EDEFAULT;

    /**
     * The default value of the '{@link #getDefaultNodeTemplate() <em>Default Node Template</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDefaultNodeTemplate()
     * @generated
     * @ordered
     */
    protected static final String DEFAULT_NODE_TEMPLATE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDefaultNodeTemplate() <em>Default Node Template</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDefaultNodeTemplate()
     * @generated
     * @ordered
     */
    protected String defaultNodeTemplate = DEFAULT_NODE_TEMPLATE_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected GraphStyleTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return OgmlPackage.Literals.GRAPH_STYLE_TYPE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<DataType> getData() {
        if (data == null) {
            data = new EObjectContainmentEList<DataType>(DataType.class, this, OgmlPackage.GRAPH_STYLE_TYPE__DATA);
        }
        return data;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getDefaultEdgeTemplate() {
        return defaultEdgeTemplate;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDefaultEdgeTemplate(String newDefaultEdgeTemplate) {
        String oldDefaultEdgeTemplate = defaultEdgeTemplate;
        defaultEdgeTemplate = newDefaultEdgeTemplate;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OgmlPackage.GRAPH_STYLE_TYPE__DEFAULT_EDGE_TEMPLATE, oldDefaultEdgeTemplate, defaultEdgeTemplate));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getDefaultLabelTemplate() {
        return defaultLabelTemplate;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDefaultLabelTemplate(String newDefaultLabelTemplate) {
        String oldDefaultLabelTemplate = defaultLabelTemplate;
        defaultLabelTemplate = newDefaultLabelTemplate;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OgmlPackage.GRAPH_STYLE_TYPE__DEFAULT_LABEL_TEMPLATE, oldDefaultLabelTemplate, defaultLabelTemplate));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getDefaultNodeTemplate() {
        return defaultNodeTemplate;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDefaultNodeTemplate(String newDefaultNodeTemplate) {
        String oldDefaultNodeTemplate = defaultNodeTemplate;
        defaultNodeTemplate = newDefaultNodeTemplate;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OgmlPackage.GRAPH_STYLE_TYPE__DEFAULT_NODE_TEMPLATE, oldDefaultNodeTemplate, defaultNodeTemplate));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case OgmlPackage.GRAPH_STYLE_TYPE__DATA:
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
            case OgmlPackage.GRAPH_STYLE_TYPE__DATA:
                return getData();
            case OgmlPackage.GRAPH_STYLE_TYPE__DEFAULT_EDGE_TEMPLATE:
                return getDefaultEdgeTemplate();
            case OgmlPackage.GRAPH_STYLE_TYPE__DEFAULT_LABEL_TEMPLATE:
                return getDefaultLabelTemplate();
            case OgmlPackage.GRAPH_STYLE_TYPE__DEFAULT_NODE_TEMPLATE:
                return getDefaultNodeTemplate();
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
            case OgmlPackage.GRAPH_STYLE_TYPE__DATA:
                getData().clear();
                getData().addAll((Collection<? extends DataType>)newValue);
                return;
            case OgmlPackage.GRAPH_STYLE_TYPE__DEFAULT_EDGE_TEMPLATE:
                setDefaultEdgeTemplate((String)newValue);
                return;
            case OgmlPackage.GRAPH_STYLE_TYPE__DEFAULT_LABEL_TEMPLATE:
                setDefaultLabelTemplate((String)newValue);
                return;
            case OgmlPackage.GRAPH_STYLE_TYPE__DEFAULT_NODE_TEMPLATE:
                setDefaultNodeTemplate((String)newValue);
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
            case OgmlPackage.GRAPH_STYLE_TYPE__DATA:
                getData().clear();
                return;
            case OgmlPackage.GRAPH_STYLE_TYPE__DEFAULT_EDGE_TEMPLATE:
                setDefaultEdgeTemplate(DEFAULT_EDGE_TEMPLATE_EDEFAULT);
                return;
            case OgmlPackage.GRAPH_STYLE_TYPE__DEFAULT_LABEL_TEMPLATE:
                setDefaultLabelTemplate(DEFAULT_LABEL_TEMPLATE_EDEFAULT);
                return;
            case OgmlPackage.GRAPH_STYLE_TYPE__DEFAULT_NODE_TEMPLATE:
                setDefaultNodeTemplate(DEFAULT_NODE_TEMPLATE_EDEFAULT);
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
            case OgmlPackage.GRAPH_STYLE_TYPE__DATA:
                return data != null && !data.isEmpty();
            case OgmlPackage.GRAPH_STYLE_TYPE__DEFAULT_EDGE_TEMPLATE:
                return DEFAULT_EDGE_TEMPLATE_EDEFAULT == null ? defaultEdgeTemplate != null : !DEFAULT_EDGE_TEMPLATE_EDEFAULT.equals(defaultEdgeTemplate);
            case OgmlPackage.GRAPH_STYLE_TYPE__DEFAULT_LABEL_TEMPLATE:
                return DEFAULT_LABEL_TEMPLATE_EDEFAULT == null ? defaultLabelTemplate != null : !DEFAULT_LABEL_TEMPLATE_EDEFAULT.equals(defaultLabelTemplate);
            case OgmlPackage.GRAPH_STYLE_TYPE__DEFAULT_NODE_TEMPLATE:
                return DEFAULT_NODE_TEMPLATE_EDEFAULT == null ? defaultNodeTemplate != null : !DEFAULT_NODE_TEMPLATE_EDEFAULT.equals(defaultNodeTemplate);
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
        result.append(" (defaultEdgeTemplate: ");
        result.append(defaultEdgeTemplate);
        result.append(", defaultLabelTemplate: ");
        result.append(defaultLabelTemplate);
        result.append(", defaultNodeTemplate: ");
        result.append(defaultNodeTemplate);
        result.append(')');
        return result.toString();
    }

} //GraphStyleTypeImpl
