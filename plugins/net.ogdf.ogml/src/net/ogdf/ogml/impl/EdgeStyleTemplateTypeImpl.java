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
import net.ogdf.ogml.LineType;
import net.ogdf.ogml.OgmlPackage;
import net.ogdf.ogml.SourceStyleType;
import net.ogdf.ogml.TargetStyleType;
import net.ogdf.ogml.TemplateType;

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
 * An implementation of the model object '<em><b>Edge Style Template Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link net.ogdf.ogml.impl.EdgeStyleTemplateTypeImpl#getData <em>Data</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.EdgeStyleTemplateTypeImpl#getTemplate <em>Template</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.EdgeStyleTemplateTypeImpl#getLine <em>Line</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.EdgeStyleTemplateTypeImpl#getSourceStyle <em>Source Style</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.EdgeStyleTemplateTypeImpl#getTargetStyle <em>Target Style</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.EdgeStyleTemplateTypeImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EdgeStyleTemplateTypeImpl extends EObjectImpl implements EdgeStyleTemplateType {
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
     * The cached value of the '{@link #getTemplate() <em>Template</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTemplate()
     * @generated
     * @ordered
     */
    protected TemplateType template;

    /**
     * The cached value of the '{@link #getLine() <em>Line</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLine()
     * @generated
     * @ordered
     */
    protected LineType line;

    /**
     * The cached value of the '{@link #getSourceStyle() <em>Source Style</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSourceStyle()
     * @generated
     * @ordered
     */
    protected SourceStyleType sourceStyle;

    /**
     * The cached value of the '{@link #getTargetStyle() <em>Target Style</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTargetStyle()
     * @generated
     * @ordered
     */
    protected TargetStyleType targetStyle;

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
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EdgeStyleTemplateTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return OgmlPackage.Literals.EDGE_STYLE_TEMPLATE_TYPE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<DataType> getData() {
        if (data == null) {
            data = new EObjectContainmentEList<DataType>(DataType.class, this, OgmlPackage.EDGE_STYLE_TEMPLATE_TYPE__DATA);
        }
        return data;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TemplateType getTemplate() {
        return template;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetTemplate(TemplateType newTemplate, NotificationChain msgs) {
        TemplateType oldTemplate = template;
        template = newTemplate;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OgmlPackage.EDGE_STYLE_TEMPLATE_TYPE__TEMPLATE, oldTemplate, newTemplate);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTemplate(TemplateType newTemplate) {
        if (newTemplate != template) {
            NotificationChain msgs = null;
            if (template != null)
                msgs = ((InternalEObject)template).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OgmlPackage.EDGE_STYLE_TEMPLATE_TYPE__TEMPLATE, null, msgs);
            if (newTemplate != null)
                msgs = ((InternalEObject)newTemplate).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OgmlPackage.EDGE_STYLE_TEMPLATE_TYPE__TEMPLATE, null, msgs);
            msgs = basicSetTemplate(newTemplate, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OgmlPackage.EDGE_STYLE_TEMPLATE_TYPE__TEMPLATE, newTemplate, newTemplate));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LineType getLine() {
        return line;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetLine(LineType newLine, NotificationChain msgs) {
        LineType oldLine = line;
        line = newLine;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OgmlPackage.EDGE_STYLE_TEMPLATE_TYPE__LINE, oldLine, newLine);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLine(LineType newLine) {
        if (newLine != line) {
            NotificationChain msgs = null;
            if (line != null)
                msgs = ((InternalEObject)line).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OgmlPackage.EDGE_STYLE_TEMPLATE_TYPE__LINE, null, msgs);
            if (newLine != null)
                msgs = ((InternalEObject)newLine).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OgmlPackage.EDGE_STYLE_TEMPLATE_TYPE__LINE, null, msgs);
            msgs = basicSetLine(newLine, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OgmlPackage.EDGE_STYLE_TEMPLATE_TYPE__LINE, newLine, newLine));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SourceStyleType getSourceStyle() {
        return sourceStyle;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetSourceStyle(SourceStyleType newSourceStyle, NotificationChain msgs) {
        SourceStyleType oldSourceStyle = sourceStyle;
        sourceStyle = newSourceStyle;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OgmlPackage.EDGE_STYLE_TEMPLATE_TYPE__SOURCE_STYLE, oldSourceStyle, newSourceStyle);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSourceStyle(SourceStyleType newSourceStyle) {
        if (newSourceStyle != sourceStyle) {
            NotificationChain msgs = null;
            if (sourceStyle != null)
                msgs = ((InternalEObject)sourceStyle).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OgmlPackage.EDGE_STYLE_TEMPLATE_TYPE__SOURCE_STYLE, null, msgs);
            if (newSourceStyle != null)
                msgs = ((InternalEObject)newSourceStyle).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OgmlPackage.EDGE_STYLE_TEMPLATE_TYPE__SOURCE_STYLE, null, msgs);
            msgs = basicSetSourceStyle(newSourceStyle, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OgmlPackage.EDGE_STYLE_TEMPLATE_TYPE__SOURCE_STYLE, newSourceStyle, newSourceStyle));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TargetStyleType getTargetStyle() {
        return targetStyle;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetTargetStyle(TargetStyleType newTargetStyle, NotificationChain msgs) {
        TargetStyleType oldTargetStyle = targetStyle;
        targetStyle = newTargetStyle;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OgmlPackage.EDGE_STYLE_TEMPLATE_TYPE__TARGET_STYLE, oldTargetStyle, newTargetStyle);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTargetStyle(TargetStyleType newTargetStyle) {
        if (newTargetStyle != targetStyle) {
            NotificationChain msgs = null;
            if (targetStyle != null)
                msgs = ((InternalEObject)targetStyle).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OgmlPackage.EDGE_STYLE_TEMPLATE_TYPE__TARGET_STYLE, null, msgs);
            if (newTargetStyle != null)
                msgs = ((InternalEObject)newTargetStyle).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OgmlPackage.EDGE_STYLE_TEMPLATE_TYPE__TARGET_STYLE, null, msgs);
            msgs = basicSetTargetStyle(newTargetStyle, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OgmlPackage.EDGE_STYLE_TEMPLATE_TYPE__TARGET_STYLE, newTargetStyle, newTargetStyle));
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
            eNotify(new ENotificationImpl(this, Notification.SET, OgmlPackage.EDGE_STYLE_TEMPLATE_TYPE__ID, oldId, id));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case OgmlPackage.EDGE_STYLE_TEMPLATE_TYPE__DATA:
                return ((InternalEList<?>)getData()).basicRemove(otherEnd, msgs);
            case OgmlPackage.EDGE_STYLE_TEMPLATE_TYPE__TEMPLATE:
                return basicSetTemplate(null, msgs);
            case OgmlPackage.EDGE_STYLE_TEMPLATE_TYPE__LINE:
                return basicSetLine(null, msgs);
            case OgmlPackage.EDGE_STYLE_TEMPLATE_TYPE__SOURCE_STYLE:
                return basicSetSourceStyle(null, msgs);
            case OgmlPackage.EDGE_STYLE_TEMPLATE_TYPE__TARGET_STYLE:
                return basicSetTargetStyle(null, msgs);
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
            case OgmlPackage.EDGE_STYLE_TEMPLATE_TYPE__DATA:
                return getData();
            case OgmlPackage.EDGE_STYLE_TEMPLATE_TYPE__TEMPLATE:
                return getTemplate();
            case OgmlPackage.EDGE_STYLE_TEMPLATE_TYPE__LINE:
                return getLine();
            case OgmlPackage.EDGE_STYLE_TEMPLATE_TYPE__SOURCE_STYLE:
                return getSourceStyle();
            case OgmlPackage.EDGE_STYLE_TEMPLATE_TYPE__TARGET_STYLE:
                return getTargetStyle();
            case OgmlPackage.EDGE_STYLE_TEMPLATE_TYPE__ID:
                return getId();
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
            case OgmlPackage.EDGE_STYLE_TEMPLATE_TYPE__DATA:
                getData().clear();
                getData().addAll((Collection<? extends DataType>)newValue);
                return;
            case OgmlPackage.EDGE_STYLE_TEMPLATE_TYPE__TEMPLATE:
                setTemplate((TemplateType)newValue);
                return;
            case OgmlPackage.EDGE_STYLE_TEMPLATE_TYPE__LINE:
                setLine((LineType)newValue);
                return;
            case OgmlPackage.EDGE_STYLE_TEMPLATE_TYPE__SOURCE_STYLE:
                setSourceStyle((SourceStyleType)newValue);
                return;
            case OgmlPackage.EDGE_STYLE_TEMPLATE_TYPE__TARGET_STYLE:
                setTargetStyle((TargetStyleType)newValue);
                return;
            case OgmlPackage.EDGE_STYLE_TEMPLATE_TYPE__ID:
                setId((String)newValue);
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
            case OgmlPackage.EDGE_STYLE_TEMPLATE_TYPE__DATA:
                getData().clear();
                return;
            case OgmlPackage.EDGE_STYLE_TEMPLATE_TYPE__TEMPLATE:
                setTemplate((TemplateType)null);
                return;
            case OgmlPackage.EDGE_STYLE_TEMPLATE_TYPE__LINE:
                setLine((LineType)null);
                return;
            case OgmlPackage.EDGE_STYLE_TEMPLATE_TYPE__SOURCE_STYLE:
                setSourceStyle((SourceStyleType)null);
                return;
            case OgmlPackage.EDGE_STYLE_TEMPLATE_TYPE__TARGET_STYLE:
                setTargetStyle((TargetStyleType)null);
                return;
            case OgmlPackage.EDGE_STYLE_TEMPLATE_TYPE__ID:
                setId(ID_EDEFAULT);
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
            case OgmlPackage.EDGE_STYLE_TEMPLATE_TYPE__DATA:
                return data != null && !data.isEmpty();
            case OgmlPackage.EDGE_STYLE_TEMPLATE_TYPE__TEMPLATE:
                return template != null;
            case OgmlPackage.EDGE_STYLE_TEMPLATE_TYPE__LINE:
                return line != null;
            case OgmlPackage.EDGE_STYLE_TEMPLATE_TYPE__SOURCE_STYLE:
                return sourceStyle != null;
            case OgmlPackage.EDGE_STYLE_TEMPLATE_TYPE__TARGET_STYLE:
                return targetStyle != null;
            case OgmlPackage.EDGE_STYLE_TEMPLATE_TYPE__ID:
                return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
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
        result.append(" (id: ");
        result.append(id);
        result.append(')');
        return result.toString();
    }

} //EdgeStyleTemplateTypeImpl
