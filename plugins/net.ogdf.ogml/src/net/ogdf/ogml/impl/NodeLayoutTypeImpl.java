/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.ogdf.ogml.impl;

import java.util.Collection;

import net.ogdf.ogml.DataType;
import net.ogdf.ogml.FillType;
import net.ogdf.ogml.LineType;
import net.ogdf.ogml.LocationType;
import net.ogdf.ogml.NodeLayoutType;
import net.ogdf.ogml.OgmlPackage;
import net.ogdf.ogml.ShapeType1;
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
 * An implementation of the model object '<em><b>Node Layout Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link net.ogdf.ogml.impl.NodeLayoutTypeImpl#getData <em>Data</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.NodeLayoutTypeImpl#getTemplate <em>Template</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.NodeLayoutTypeImpl#getLocation <em>Location</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.NodeLayoutTypeImpl#getShape <em>Shape</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.NodeLayoutTypeImpl#getFill <em>Fill</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.NodeLayoutTypeImpl#getLine <em>Line</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.NodeLayoutTypeImpl#getIdRef <em>Id Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NodeLayoutTypeImpl extends EObjectImpl implements NodeLayoutType {
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
     * The cached value of the '{@link #getLocation() <em>Location</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLocation()
     * @generated
     * @ordered
     */
    protected LocationType location;

    /**
     * The cached value of the '{@link #getShape() <em>Shape</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getShape()
     * @generated
     * @ordered
     */
    protected ShapeType1 shape;

    /**
     * The cached value of the '{@link #getFill() <em>Fill</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFill()
     * @generated
     * @ordered
     */
    protected FillType fill;

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
     * The default value of the '{@link #getIdRef() <em>Id Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getIdRef()
     * @generated
     * @ordered
     */
    protected static final String ID_REF_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getIdRef() <em>Id Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getIdRef()
     * @generated
     * @ordered
     */
    protected String idRef = ID_REF_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected NodeLayoutTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return OgmlPackage.Literals.NODE_LAYOUT_TYPE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<DataType> getData() {
        if (data == null) {
            data = new EObjectContainmentEList<DataType>(DataType.class, this, OgmlPackage.NODE_LAYOUT_TYPE__DATA);
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OgmlPackage.NODE_LAYOUT_TYPE__TEMPLATE, oldTemplate, newTemplate);
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
                msgs = ((InternalEObject)template).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OgmlPackage.NODE_LAYOUT_TYPE__TEMPLATE, null, msgs);
            if (newTemplate != null)
                msgs = ((InternalEObject)newTemplate).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OgmlPackage.NODE_LAYOUT_TYPE__TEMPLATE, null, msgs);
            msgs = basicSetTemplate(newTemplate, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OgmlPackage.NODE_LAYOUT_TYPE__TEMPLATE, newTemplate, newTemplate));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LocationType getLocation() {
        return location;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetLocation(LocationType newLocation, NotificationChain msgs) {
        LocationType oldLocation = location;
        location = newLocation;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OgmlPackage.NODE_LAYOUT_TYPE__LOCATION, oldLocation, newLocation);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLocation(LocationType newLocation) {
        if (newLocation != location) {
            NotificationChain msgs = null;
            if (location != null)
                msgs = ((InternalEObject)location).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OgmlPackage.NODE_LAYOUT_TYPE__LOCATION, null, msgs);
            if (newLocation != null)
                msgs = ((InternalEObject)newLocation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OgmlPackage.NODE_LAYOUT_TYPE__LOCATION, null, msgs);
            msgs = basicSetLocation(newLocation, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OgmlPackage.NODE_LAYOUT_TYPE__LOCATION, newLocation, newLocation));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ShapeType1 getShape() {
        return shape;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetShape(ShapeType1 newShape, NotificationChain msgs) {
        ShapeType1 oldShape = shape;
        shape = newShape;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OgmlPackage.NODE_LAYOUT_TYPE__SHAPE, oldShape, newShape);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setShape(ShapeType1 newShape) {
        if (newShape != shape) {
            NotificationChain msgs = null;
            if (shape != null)
                msgs = ((InternalEObject)shape).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OgmlPackage.NODE_LAYOUT_TYPE__SHAPE, null, msgs);
            if (newShape != null)
                msgs = ((InternalEObject)newShape).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OgmlPackage.NODE_LAYOUT_TYPE__SHAPE, null, msgs);
            msgs = basicSetShape(newShape, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OgmlPackage.NODE_LAYOUT_TYPE__SHAPE, newShape, newShape));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FillType getFill() {
        return fill;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetFill(FillType newFill, NotificationChain msgs) {
        FillType oldFill = fill;
        fill = newFill;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OgmlPackage.NODE_LAYOUT_TYPE__FILL, oldFill, newFill);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setFill(FillType newFill) {
        if (newFill != fill) {
            NotificationChain msgs = null;
            if (fill != null)
                msgs = ((InternalEObject)fill).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OgmlPackage.NODE_LAYOUT_TYPE__FILL, null, msgs);
            if (newFill != null)
                msgs = ((InternalEObject)newFill).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OgmlPackage.NODE_LAYOUT_TYPE__FILL, null, msgs);
            msgs = basicSetFill(newFill, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OgmlPackage.NODE_LAYOUT_TYPE__FILL, newFill, newFill));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OgmlPackage.NODE_LAYOUT_TYPE__LINE, oldLine, newLine);
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
                msgs = ((InternalEObject)line).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OgmlPackage.NODE_LAYOUT_TYPE__LINE, null, msgs);
            if (newLine != null)
                msgs = ((InternalEObject)newLine).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OgmlPackage.NODE_LAYOUT_TYPE__LINE, null, msgs);
            msgs = basicSetLine(newLine, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OgmlPackage.NODE_LAYOUT_TYPE__LINE, newLine, newLine));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getIdRef() {
        return idRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setIdRef(String newIdRef) {
        String oldIdRef = idRef;
        idRef = newIdRef;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OgmlPackage.NODE_LAYOUT_TYPE__ID_REF, oldIdRef, idRef));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case OgmlPackage.NODE_LAYOUT_TYPE__DATA:
                return ((InternalEList<?>)getData()).basicRemove(otherEnd, msgs);
            case OgmlPackage.NODE_LAYOUT_TYPE__TEMPLATE:
                return basicSetTemplate(null, msgs);
            case OgmlPackage.NODE_LAYOUT_TYPE__LOCATION:
                return basicSetLocation(null, msgs);
            case OgmlPackage.NODE_LAYOUT_TYPE__SHAPE:
                return basicSetShape(null, msgs);
            case OgmlPackage.NODE_LAYOUT_TYPE__FILL:
                return basicSetFill(null, msgs);
            case OgmlPackage.NODE_LAYOUT_TYPE__LINE:
                return basicSetLine(null, msgs);
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
            case OgmlPackage.NODE_LAYOUT_TYPE__DATA:
                return getData();
            case OgmlPackage.NODE_LAYOUT_TYPE__TEMPLATE:
                return getTemplate();
            case OgmlPackage.NODE_LAYOUT_TYPE__LOCATION:
                return getLocation();
            case OgmlPackage.NODE_LAYOUT_TYPE__SHAPE:
                return getShape();
            case OgmlPackage.NODE_LAYOUT_TYPE__FILL:
                return getFill();
            case OgmlPackage.NODE_LAYOUT_TYPE__LINE:
                return getLine();
            case OgmlPackage.NODE_LAYOUT_TYPE__ID_REF:
                return getIdRef();
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
            case OgmlPackage.NODE_LAYOUT_TYPE__DATA:
                getData().clear();
                getData().addAll((Collection<? extends DataType>)newValue);
                return;
            case OgmlPackage.NODE_LAYOUT_TYPE__TEMPLATE:
                setTemplate((TemplateType)newValue);
                return;
            case OgmlPackage.NODE_LAYOUT_TYPE__LOCATION:
                setLocation((LocationType)newValue);
                return;
            case OgmlPackage.NODE_LAYOUT_TYPE__SHAPE:
                setShape((ShapeType1)newValue);
                return;
            case OgmlPackage.NODE_LAYOUT_TYPE__FILL:
                setFill((FillType)newValue);
                return;
            case OgmlPackage.NODE_LAYOUT_TYPE__LINE:
                setLine((LineType)newValue);
                return;
            case OgmlPackage.NODE_LAYOUT_TYPE__ID_REF:
                setIdRef((String)newValue);
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
            case OgmlPackage.NODE_LAYOUT_TYPE__DATA:
                getData().clear();
                return;
            case OgmlPackage.NODE_LAYOUT_TYPE__TEMPLATE:
                setTemplate((TemplateType)null);
                return;
            case OgmlPackage.NODE_LAYOUT_TYPE__LOCATION:
                setLocation((LocationType)null);
                return;
            case OgmlPackage.NODE_LAYOUT_TYPE__SHAPE:
                setShape((ShapeType1)null);
                return;
            case OgmlPackage.NODE_LAYOUT_TYPE__FILL:
                setFill((FillType)null);
                return;
            case OgmlPackage.NODE_LAYOUT_TYPE__LINE:
                setLine((LineType)null);
                return;
            case OgmlPackage.NODE_LAYOUT_TYPE__ID_REF:
                setIdRef(ID_REF_EDEFAULT);
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
            case OgmlPackage.NODE_LAYOUT_TYPE__DATA:
                return data != null && !data.isEmpty();
            case OgmlPackage.NODE_LAYOUT_TYPE__TEMPLATE:
                return template != null;
            case OgmlPackage.NODE_LAYOUT_TYPE__LOCATION:
                return location != null;
            case OgmlPackage.NODE_LAYOUT_TYPE__SHAPE:
                return shape != null;
            case OgmlPackage.NODE_LAYOUT_TYPE__FILL:
                return fill != null;
            case OgmlPackage.NODE_LAYOUT_TYPE__LINE:
                return line != null;
            case OgmlPackage.NODE_LAYOUT_TYPE__ID_REF:
                return ID_REF_EDEFAULT == null ? idRef != null : !ID_REF_EDEFAULT.equals(idRef);
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
        result.append(" (idRef: ");
        result.append(idRef);
        result.append(')');
        return result.toString();
    }

} //NodeLayoutTypeImpl
