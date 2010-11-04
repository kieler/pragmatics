/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.ogdf.ogml.impl;

import java.util.Collection;

import net.ogdf.ogml.DataType;
import net.ogdf.ogml.EdgeStyleTemplateType;
import net.ogdf.ogml.LabelStyleTemplateType;
import net.ogdf.ogml.NodeStyleTemplateType;
import net.ogdf.ogml.OgmlPackage;
import net.ogdf.ogml.StyleTemplatesType;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Style Templates Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link net.ogdf.ogml.impl.StyleTemplatesTypeImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.StyleTemplatesTypeImpl#getData <em>Data</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.StyleTemplatesTypeImpl#getNodeStyleTemplate <em>Node Style Template</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.StyleTemplatesTypeImpl#getEdgeStyleTemplate <em>Edge Style Template</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.StyleTemplatesTypeImpl#getLabelStyleTemplate <em>Label Style Template</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StyleTemplatesTypeImpl extends EObjectImpl implements StyleTemplatesType {
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
    protected StyleTemplatesTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return OgmlPackage.Literals.STYLE_TEMPLATES_TYPE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureMap getGroup() {
        if (group == null) {
            group = new BasicFeatureMap(this, OgmlPackage.STYLE_TEMPLATES_TYPE__GROUP);
        }
        return group;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<DataType> getData() {
        return getGroup().list(OgmlPackage.Literals.STYLE_TEMPLATES_TYPE__DATA);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<NodeStyleTemplateType> getNodeStyleTemplate() {
        return getGroup().list(OgmlPackage.Literals.STYLE_TEMPLATES_TYPE__NODE_STYLE_TEMPLATE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<EdgeStyleTemplateType> getEdgeStyleTemplate() {
        return getGroup().list(OgmlPackage.Literals.STYLE_TEMPLATES_TYPE__EDGE_STYLE_TEMPLATE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<LabelStyleTemplateType> getLabelStyleTemplate() {
        return getGroup().list(OgmlPackage.Literals.STYLE_TEMPLATES_TYPE__LABEL_STYLE_TEMPLATE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case OgmlPackage.STYLE_TEMPLATES_TYPE__GROUP:
                return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
            case OgmlPackage.STYLE_TEMPLATES_TYPE__DATA:
                return ((InternalEList<?>)getData()).basicRemove(otherEnd, msgs);
            case OgmlPackage.STYLE_TEMPLATES_TYPE__NODE_STYLE_TEMPLATE:
                return ((InternalEList<?>)getNodeStyleTemplate()).basicRemove(otherEnd, msgs);
            case OgmlPackage.STYLE_TEMPLATES_TYPE__EDGE_STYLE_TEMPLATE:
                return ((InternalEList<?>)getEdgeStyleTemplate()).basicRemove(otherEnd, msgs);
            case OgmlPackage.STYLE_TEMPLATES_TYPE__LABEL_STYLE_TEMPLATE:
                return ((InternalEList<?>)getLabelStyleTemplate()).basicRemove(otherEnd, msgs);
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
            case OgmlPackage.STYLE_TEMPLATES_TYPE__GROUP:
                if (coreType) return getGroup();
                return ((FeatureMap.Internal)getGroup()).getWrapper();
            case OgmlPackage.STYLE_TEMPLATES_TYPE__DATA:
                return getData();
            case OgmlPackage.STYLE_TEMPLATES_TYPE__NODE_STYLE_TEMPLATE:
                return getNodeStyleTemplate();
            case OgmlPackage.STYLE_TEMPLATES_TYPE__EDGE_STYLE_TEMPLATE:
                return getEdgeStyleTemplate();
            case OgmlPackage.STYLE_TEMPLATES_TYPE__LABEL_STYLE_TEMPLATE:
                return getLabelStyleTemplate();
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
            case OgmlPackage.STYLE_TEMPLATES_TYPE__GROUP:
                ((FeatureMap.Internal)getGroup()).set(newValue);
                return;
            case OgmlPackage.STYLE_TEMPLATES_TYPE__DATA:
                getData().clear();
                getData().addAll((Collection<? extends DataType>)newValue);
                return;
            case OgmlPackage.STYLE_TEMPLATES_TYPE__NODE_STYLE_TEMPLATE:
                getNodeStyleTemplate().clear();
                getNodeStyleTemplate().addAll((Collection<? extends NodeStyleTemplateType>)newValue);
                return;
            case OgmlPackage.STYLE_TEMPLATES_TYPE__EDGE_STYLE_TEMPLATE:
                getEdgeStyleTemplate().clear();
                getEdgeStyleTemplate().addAll((Collection<? extends EdgeStyleTemplateType>)newValue);
                return;
            case OgmlPackage.STYLE_TEMPLATES_TYPE__LABEL_STYLE_TEMPLATE:
                getLabelStyleTemplate().clear();
                getLabelStyleTemplate().addAll((Collection<? extends LabelStyleTemplateType>)newValue);
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
            case OgmlPackage.STYLE_TEMPLATES_TYPE__GROUP:
                getGroup().clear();
                return;
            case OgmlPackage.STYLE_TEMPLATES_TYPE__DATA:
                getData().clear();
                return;
            case OgmlPackage.STYLE_TEMPLATES_TYPE__NODE_STYLE_TEMPLATE:
                getNodeStyleTemplate().clear();
                return;
            case OgmlPackage.STYLE_TEMPLATES_TYPE__EDGE_STYLE_TEMPLATE:
                getEdgeStyleTemplate().clear();
                return;
            case OgmlPackage.STYLE_TEMPLATES_TYPE__LABEL_STYLE_TEMPLATE:
                getLabelStyleTemplate().clear();
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
            case OgmlPackage.STYLE_TEMPLATES_TYPE__GROUP:
                return group != null && !group.isEmpty();
            case OgmlPackage.STYLE_TEMPLATES_TYPE__DATA:
                return !getData().isEmpty();
            case OgmlPackage.STYLE_TEMPLATES_TYPE__NODE_STYLE_TEMPLATE:
                return !getNodeStyleTemplate().isEmpty();
            case OgmlPackage.STYLE_TEMPLATES_TYPE__EDGE_STYLE_TEMPLATE:
                return !getEdgeStyleTemplate().isEmpty();
            case OgmlPackage.STYLE_TEMPLATES_TYPE__LABEL_STYLE_TEMPLATE:
                return !getLabelStyleTemplate().isEmpty();
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

} //StyleTemplatesTypeImpl
