/**
 */
package de.cau.cs.kieler.klighd.kdiagram.kDiagram.impl;

import de.cau.cs.kieler.klighd.kdiagram.kDiagram.KDiagramPackage;
import de.cau.cs.kieler.klighd.kdiagram.kDiagram.NodeMapping;
import de.cau.cs.kieler.klighd.kdiagram.kDiagram.PortMapping;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.xtext.common.types.JvmType;

import org.eclipse.xtext.xbase.XExpression;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Node Mapping</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.impl.NodeMappingImpl#getName <em>Name</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.impl.NodeMappingImpl#getElements <em>Elements</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.impl.NodeMappingImpl#getWithPorts <em>With Ports</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.impl.NodeMappingImpl#getFigureType <em>Figure Type</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.impl.NodeMappingImpl#getPortMappings <em>Port Mappings</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NodeMappingImpl extends MinimalEObjectImpl.Container implements NodeMapping
{
  /**
   * The default value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected static final String NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected String name = NAME_EDEFAULT;

  /**
   * The cached value of the '{@link #getElements() <em>Elements</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getElements()
   * @generated
   * @ordered
   */
  protected XExpression elements;

  /**
   * The cached value of the '{@link #getWithPorts() <em>With Ports</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getWithPorts()
   * @generated
   * @ordered
   */
  protected EList<XExpression> withPorts;

  /**
   * The cached value of the '{@link #getFigureType() <em>Figure Type</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFigureType()
   * @generated
   * @ordered
   */
  protected JvmType figureType;

  /**
   * The cached value of the '{@link #getPortMappings() <em>Port Mappings</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPortMappings()
   * @generated
   * @ordered
   */
  protected EList<PortMapping> portMappings;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected NodeMappingImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return KDiagramPackage.Literals.NODE_MAPPING;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName(String newName)
  {
    String oldName = name;
    name = newName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, KDiagramPackage.NODE_MAPPING__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XExpression getElements()
  {
    return elements;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetElements(XExpression newElements, NotificationChain msgs)
  {
    XExpression oldElements = elements;
    elements = newElements;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, KDiagramPackage.NODE_MAPPING__ELEMENTS, oldElements, newElements);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setElements(XExpression newElements)
  {
    if (newElements != elements)
    {
      NotificationChain msgs = null;
      if (elements != null)
        msgs = ((InternalEObject)elements).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - KDiagramPackage.NODE_MAPPING__ELEMENTS, null, msgs);
      if (newElements != null)
        msgs = ((InternalEObject)newElements).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - KDiagramPackage.NODE_MAPPING__ELEMENTS, null, msgs);
      msgs = basicSetElements(newElements, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, KDiagramPackage.NODE_MAPPING__ELEMENTS, newElements, newElements));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<XExpression> getWithPorts()
  {
    if (withPorts == null)
    {
      withPorts = new EObjectContainmentEList<XExpression>(XExpression.class, this, KDiagramPackage.NODE_MAPPING__WITH_PORTS);
    }
    return withPorts;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public JvmType getFigureType()
  {
    if (figureType != null && figureType.eIsProxy())
    {
      InternalEObject oldFigureType = (InternalEObject)figureType;
      figureType = (JvmType)eResolveProxy(oldFigureType);
      if (figureType != oldFigureType)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, KDiagramPackage.NODE_MAPPING__FIGURE_TYPE, oldFigureType, figureType));
      }
    }
    return figureType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public JvmType basicGetFigureType()
  {
    return figureType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFigureType(JvmType newFigureType)
  {
    JvmType oldFigureType = figureType;
    figureType = newFigureType;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, KDiagramPackage.NODE_MAPPING__FIGURE_TYPE, oldFigureType, figureType));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<PortMapping> getPortMappings()
  {
    if (portMappings == null)
    {
      portMappings = new EObjectContainmentEList<PortMapping>(PortMapping.class, this, KDiagramPackage.NODE_MAPPING__PORT_MAPPINGS);
    }
    return portMappings;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case KDiagramPackage.NODE_MAPPING__ELEMENTS:
        return basicSetElements(null, msgs);
      case KDiagramPackage.NODE_MAPPING__WITH_PORTS:
        return ((InternalEList<?>)getWithPorts()).basicRemove(otherEnd, msgs);
      case KDiagramPackage.NODE_MAPPING__PORT_MAPPINGS:
        return ((InternalEList<?>)getPortMappings()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case KDiagramPackage.NODE_MAPPING__NAME:
        return getName();
      case KDiagramPackage.NODE_MAPPING__ELEMENTS:
        return getElements();
      case KDiagramPackage.NODE_MAPPING__WITH_PORTS:
        return getWithPorts();
      case KDiagramPackage.NODE_MAPPING__FIGURE_TYPE:
        if (resolve) return getFigureType();
        return basicGetFigureType();
      case KDiagramPackage.NODE_MAPPING__PORT_MAPPINGS:
        return getPortMappings();
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
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case KDiagramPackage.NODE_MAPPING__NAME:
        setName((String)newValue);
        return;
      case KDiagramPackage.NODE_MAPPING__ELEMENTS:
        setElements((XExpression)newValue);
        return;
      case KDiagramPackage.NODE_MAPPING__WITH_PORTS:
        getWithPorts().clear();
        getWithPorts().addAll((Collection<? extends XExpression>)newValue);
        return;
      case KDiagramPackage.NODE_MAPPING__FIGURE_TYPE:
        setFigureType((JvmType)newValue);
        return;
      case KDiagramPackage.NODE_MAPPING__PORT_MAPPINGS:
        getPortMappings().clear();
        getPortMappings().addAll((Collection<? extends PortMapping>)newValue);
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
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case KDiagramPackage.NODE_MAPPING__NAME:
        setName(NAME_EDEFAULT);
        return;
      case KDiagramPackage.NODE_MAPPING__ELEMENTS:
        setElements((XExpression)null);
        return;
      case KDiagramPackage.NODE_MAPPING__WITH_PORTS:
        getWithPorts().clear();
        return;
      case KDiagramPackage.NODE_MAPPING__FIGURE_TYPE:
        setFigureType((JvmType)null);
        return;
      case KDiagramPackage.NODE_MAPPING__PORT_MAPPINGS:
        getPortMappings().clear();
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
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case KDiagramPackage.NODE_MAPPING__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case KDiagramPackage.NODE_MAPPING__ELEMENTS:
        return elements != null;
      case KDiagramPackage.NODE_MAPPING__WITH_PORTS:
        return withPorts != null && !withPorts.isEmpty();
      case KDiagramPackage.NODE_MAPPING__FIGURE_TYPE:
        return figureType != null;
      case KDiagramPackage.NODE_MAPPING__PORT_MAPPINGS:
        return portMappings != null && !portMappings.isEmpty();
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (name: ");
    result.append(name);
    result.append(')');
    return result.toString();
  }

} //NodeMappingImpl
