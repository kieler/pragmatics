/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.ogdf.ogml.impl;

import java.util.Collection;

import net.ogdf.ogml.DataType;
import net.ogdf.ogml.FontType;
import net.ogdf.ogml.LabelStyleTemplateType;
import net.ogdf.ogml.OgmlPackage;
import net.ogdf.ogml.TemplateType;
import net.ogdf.ogml.TextType;

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
 * An implementation of the model object '<em><b>Label Style Template Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link net.ogdf.ogml.impl.LabelStyleTemplateTypeImpl#getData <em>Data</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.LabelStyleTemplateTypeImpl#getTemplate <em>Template</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.LabelStyleTemplateTypeImpl#getText <em>Text</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.LabelStyleTemplateTypeImpl#getFont <em>Font</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.LabelStyleTemplateTypeImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LabelStyleTemplateTypeImpl extends EObjectImpl implements LabelStyleTemplateType {
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
     * The cached value of the '{@link #getText() <em>Text</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getText()
     * @generated
     * @ordered
     */
    protected TextType text;

    /**
     * The cached value of the '{@link #getFont() <em>Font</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFont()
     * @generated
     * @ordered
     */
    protected FontType font;

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
    protected LabelStyleTemplateTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return OgmlPackage.Literals.LABEL_STYLE_TEMPLATE_TYPE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<DataType> getData() {
        if (data == null) {
            data = new EObjectContainmentEList<DataType>(DataType.class, this, OgmlPackage.LABEL_STYLE_TEMPLATE_TYPE__DATA);
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OgmlPackage.LABEL_STYLE_TEMPLATE_TYPE__TEMPLATE, oldTemplate, newTemplate);
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
                msgs = ((InternalEObject)template).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OgmlPackage.LABEL_STYLE_TEMPLATE_TYPE__TEMPLATE, null, msgs);
            if (newTemplate != null)
                msgs = ((InternalEObject)newTemplate).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OgmlPackage.LABEL_STYLE_TEMPLATE_TYPE__TEMPLATE, null, msgs);
            msgs = basicSetTemplate(newTemplate, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OgmlPackage.LABEL_STYLE_TEMPLATE_TYPE__TEMPLATE, newTemplate, newTemplate));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TextType getText() {
        return text;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetText(TextType newText, NotificationChain msgs) {
        TextType oldText = text;
        text = newText;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OgmlPackage.LABEL_STYLE_TEMPLATE_TYPE__TEXT, oldText, newText);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setText(TextType newText) {
        if (newText != text) {
            NotificationChain msgs = null;
            if (text != null)
                msgs = ((InternalEObject)text).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OgmlPackage.LABEL_STYLE_TEMPLATE_TYPE__TEXT, null, msgs);
            if (newText != null)
                msgs = ((InternalEObject)newText).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OgmlPackage.LABEL_STYLE_TEMPLATE_TYPE__TEXT, null, msgs);
            msgs = basicSetText(newText, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OgmlPackage.LABEL_STYLE_TEMPLATE_TYPE__TEXT, newText, newText));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FontType getFont() {
        return font;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetFont(FontType newFont, NotificationChain msgs) {
        FontType oldFont = font;
        font = newFont;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OgmlPackage.LABEL_STYLE_TEMPLATE_TYPE__FONT, oldFont, newFont);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setFont(FontType newFont) {
        if (newFont != font) {
            NotificationChain msgs = null;
            if (font != null)
                msgs = ((InternalEObject)font).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OgmlPackage.LABEL_STYLE_TEMPLATE_TYPE__FONT, null, msgs);
            if (newFont != null)
                msgs = ((InternalEObject)newFont).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OgmlPackage.LABEL_STYLE_TEMPLATE_TYPE__FONT, null, msgs);
            msgs = basicSetFont(newFont, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OgmlPackage.LABEL_STYLE_TEMPLATE_TYPE__FONT, newFont, newFont));
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
            eNotify(new ENotificationImpl(this, Notification.SET, OgmlPackage.LABEL_STYLE_TEMPLATE_TYPE__ID, oldId, id));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case OgmlPackage.LABEL_STYLE_TEMPLATE_TYPE__DATA:
                return ((InternalEList<?>)getData()).basicRemove(otherEnd, msgs);
            case OgmlPackage.LABEL_STYLE_TEMPLATE_TYPE__TEMPLATE:
                return basicSetTemplate(null, msgs);
            case OgmlPackage.LABEL_STYLE_TEMPLATE_TYPE__TEXT:
                return basicSetText(null, msgs);
            case OgmlPackage.LABEL_STYLE_TEMPLATE_TYPE__FONT:
                return basicSetFont(null, msgs);
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
            case OgmlPackage.LABEL_STYLE_TEMPLATE_TYPE__DATA:
                return getData();
            case OgmlPackage.LABEL_STYLE_TEMPLATE_TYPE__TEMPLATE:
                return getTemplate();
            case OgmlPackage.LABEL_STYLE_TEMPLATE_TYPE__TEXT:
                return getText();
            case OgmlPackage.LABEL_STYLE_TEMPLATE_TYPE__FONT:
                return getFont();
            case OgmlPackage.LABEL_STYLE_TEMPLATE_TYPE__ID:
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
            case OgmlPackage.LABEL_STYLE_TEMPLATE_TYPE__DATA:
                getData().clear();
                getData().addAll((Collection<? extends DataType>)newValue);
                return;
            case OgmlPackage.LABEL_STYLE_TEMPLATE_TYPE__TEMPLATE:
                setTemplate((TemplateType)newValue);
                return;
            case OgmlPackage.LABEL_STYLE_TEMPLATE_TYPE__TEXT:
                setText((TextType)newValue);
                return;
            case OgmlPackage.LABEL_STYLE_TEMPLATE_TYPE__FONT:
                setFont((FontType)newValue);
                return;
            case OgmlPackage.LABEL_STYLE_TEMPLATE_TYPE__ID:
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
            case OgmlPackage.LABEL_STYLE_TEMPLATE_TYPE__DATA:
                getData().clear();
                return;
            case OgmlPackage.LABEL_STYLE_TEMPLATE_TYPE__TEMPLATE:
                setTemplate((TemplateType)null);
                return;
            case OgmlPackage.LABEL_STYLE_TEMPLATE_TYPE__TEXT:
                setText((TextType)null);
                return;
            case OgmlPackage.LABEL_STYLE_TEMPLATE_TYPE__FONT:
                setFont((FontType)null);
                return;
            case OgmlPackage.LABEL_STYLE_TEMPLATE_TYPE__ID:
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
            case OgmlPackage.LABEL_STYLE_TEMPLATE_TYPE__DATA:
                return data != null && !data.isEmpty();
            case OgmlPackage.LABEL_STYLE_TEMPLATE_TYPE__TEMPLATE:
                return template != null;
            case OgmlPackage.LABEL_STYLE_TEMPLATE_TYPE__TEXT:
                return text != null;
            case OgmlPackage.LABEL_STYLE_TEMPLATE_TYPE__FONT:
                return font != null;
            case OgmlPackage.LABEL_STYLE_TEMPLATE_TYPE__ID:
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

} //LabelStyleTemplateTypeImpl
