/**
 */
package de.cau.cs.kieler.klighd.kdiagram.kDiagram.impl;

import de.cau.cs.kieler.klighd.kdiagram.kDiagram.KDiagramPackage;
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

import org.eclipse.xtext.common.types.JvmParameterizedTypeReference;
import org.eclipse.xtext.common.types.JvmType;

import org.eclipse.xtext.xbase.XExpression;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Port Mapping</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.impl.PortMappingImpl#getName <em>Name</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.impl.PortMappingImpl#getNodeElementType <em>Node Element Type</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.impl.PortMappingImpl#getNodeElementName <em>Node Element Name</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.impl.PortMappingImpl#getElements <em>Elements</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.impl.PortMappingImpl#getIdentifiedBy <em>Identified By</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.impl.PortMappingImpl#getIndentifiedBy <em>Indentified By</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.impl.PortMappingImpl#getFigureType <em>Figure Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PortMappingImpl extends MinimalEObjectImpl.Container implements PortMapping
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
   * The cached value of the '{@link #getNodeElementType() <em>Node Element Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNodeElementType()
   * @generated
   * @ordered
   */
  protected JvmParameterizedTypeReference nodeElementType;

  /**
   * The default value of the '{@link #getNodeElementName() <em>Node Element Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNodeElementName()
   * @generated
   * @ordered
   */
  protected static final String NODE_ELEMENT_NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getNodeElementName() <em>Node Element Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNodeElementName()
   * @generated
   * @ordered
   */
  protected String nodeElementName = NODE_ELEMENT_NAME_EDEFAULT;

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
   * The cached value of the '{@link #getIdentifiedBy() <em>Identified By</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIdentifiedBy()
   * @generated
   * @ordered
   */
  protected EList<XExpression> identifiedBy;

  /**
   * The cached value of the '{@link #getIndentifiedBy() <em>Indentified By</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIndentifiedBy()
   * @generated
   * @ordered
   */
  protected EList<XExpression> indentifiedBy;

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
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected PortMappingImpl()
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
    return KDiagramPackage.Literals.PORT_MAPPING;
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
      eNotify(new ENotificationImpl(this, Notification.SET, KDiagramPackage.PORT_MAPPING__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public JvmParameterizedTypeReference getNodeElementType()
  {
    return nodeElementType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetNodeElementType(JvmParameterizedTypeReference newNodeElementType, NotificationChain msgs)
  {
    JvmParameterizedTypeReference oldNodeElementType = nodeElementType;
    nodeElementType = newNodeElementType;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, KDiagramPackage.PORT_MAPPING__NODE_ELEMENT_TYPE, oldNodeElementType, newNodeElementType);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setNodeElementType(JvmParameterizedTypeReference newNodeElementType)
  {
    if (newNodeElementType != nodeElementType)
    {
      NotificationChain msgs = null;
      if (nodeElementType != null)
        msgs = ((InternalEObject)nodeElementType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - KDiagramPackage.PORT_MAPPING__NODE_ELEMENT_TYPE, null, msgs);
      if (newNodeElementType != null)
        msgs = ((InternalEObject)newNodeElementType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - KDiagramPackage.PORT_MAPPING__NODE_ELEMENT_TYPE, null, msgs);
      msgs = basicSetNodeElementType(newNodeElementType, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, KDiagramPackage.PORT_MAPPING__NODE_ELEMENT_TYPE, newNodeElementType, newNodeElementType));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getNodeElementName()
  {
    return nodeElementName;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setNodeElementName(String newNodeElementName)
  {
    String oldNodeElementName = nodeElementName;
    nodeElementName = newNodeElementName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, KDiagramPackage.PORT_MAPPING__NODE_ELEMENT_NAME, oldNodeElementName, nodeElementName));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, KDiagramPackage.PORT_MAPPING__ELEMENTS, oldElements, newElements);
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
        msgs = ((InternalEObject)elements).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - KDiagramPackage.PORT_MAPPING__ELEMENTS, null, msgs);
      if (newElements != null)
        msgs = ((InternalEObject)newElements).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - KDiagramPackage.PORT_MAPPING__ELEMENTS, null, msgs);
      msgs = basicSetElements(newElements, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, KDiagramPackage.PORT_MAPPING__ELEMENTS, newElements, newElements));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<XExpression> getIdentifiedBy()
  {
    if (identifiedBy == null)
    {
      identifiedBy = new EObjectContainmentEList<XExpression>(XExpression.class, this, KDiagramPackage.PORT_MAPPING__IDENTIFIED_BY);
    }
    return identifiedBy;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<XExpression> getIndentifiedBy()
  {
    if (indentifiedBy == null)
    {
      indentifiedBy = new EObjectContainmentEList<XExpression>(XExpression.class, this, KDiagramPackage.PORT_MAPPING__INDENTIFIED_BY);
    }
    return indentifiedBy;
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
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, KDiagramPackage.PORT_MAPPING__FIGURE_TYPE, oldFigureType, figureType));
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
      eNotify(new ENotificationImpl(this, Notification.SET, KDiagramPackage.PORT_MAPPING__FIGURE_TYPE, oldFigureType, figureType));
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
      case KDiagramPackage.PORT_MAPPING__NODE_ELEMENT_TYPE:
        return basicSetNodeElementType(null, msgs);
      case KDiagramPackage.PORT_MAPPING__ELEMENTS:
        return basicSetElements(null, msgs);
      case KDiagramPackage.PORT_MAPPING__IDENTIFIED_BY:
        return ((InternalEList<?>)getIdentifiedBy()).basicRemove(otherEnd, msgs);
      case KDiagramPackage.PORT_MAPPING__INDENTIFIED_BY:
        return ((InternalEList<?>)getIndentifiedBy()).basicRemove(otherEnd, msgs);
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
      case KDiagramPackage.PORT_MAPPING__NAME:
        return getName();
      case KDiagramPackage.PORT_MAPPING__NODE_ELEMENT_TYPE:
        return getNodeElementType();
      case KDiagramPackage.PORT_MAPPING__NODE_ELEMENT_NAME:
        return getNodeElementName();
      case KDiagramPackage.PORT_MAPPING__ELEMENTS:
        return getElements();
      case KDiagramPackage.PORT_MAPPING__IDENTIFIED_BY:
        return getIdentifiedBy();
      case KDiagramPackage.PORT_MAPPING__INDENTIFIED_BY:
        return getIndentifiedBy();
      case KDiagramPackage.PORT_MAPPING__FIGURE_TYPE:
        if (resolve) return getFigureType();
        return basicGetFigureType();
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
      case KDiagramPackage.PORT_MAPPING__NAME:
        setName((String)newValue);
        return;
      case KDiagramPackage.PORT_MAPPING__NODE_ELEMENT_TYPE:
        setNodeElementType((JvmParameterizedTypeReference)newValue);
        return;
      case KDiagramPackage.PORT_MAPPING__NODE_ELEMENT_NAME:
        setNodeElementName((String)newValue);
        return;
      case KDiagramPackage.PORT_MAPPING__ELEMENTS:
        setElements((XExpression)newValue);
        return;
      case KDiagramPackage.PORT_MAPPING__IDENTIFIED_BY:
        getIdentifiedBy().clear();
        getIdentifiedBy().addAll((Collection<? extends XExpression>)newValue);
        return;
      case KDiagramPackage.PORT_MAPPING__INDENTIFIED_BY:
        getIndentifiedBy().clear();
        getIndentifiedBy().addAll((Collection<? extends XExpression>)newValue);
        return;
      case KDiagramPackage.PORT_MAPPING__FIGURE_TYPE:
        setFigureType((JvmType)newValue);
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
      case KDiagramPackage.PORT_MAPPING__NAME:
        setName(NAME_EDEFAULT);
        return;
      case KDiagramPackage.PORT_MAPPING__NODE_ELEMENT_TYPE:
        setNodeElementType((JvmParameterizedTypeReference)null);
        return;
      case KDiagramPackage.PORT_MAPPING__NODE_ELEMENT_NAME:
        setNodeElementName(NODE_ELEMENT_NAME_EDEFAULT);
        return;
      case KDiagramPackage.PORT_MAPPING__ELEMENTS:
        setElements((XExpression)null);
        return;
      case KDiagramPackage.PORT_MAPPING__IDENTIFIED_BY:
        getIdentifiedBy().clear();
        return;
      case KDiagramPackage.PORT_MAPPING__INDENTIFIED_BY:
        getIndentifiedBy().clear();
        return;
      case KDiagramPackage.PORT_MAPPING__FIGURE_TYPE:
        setFigureType((JvmType)null);
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
      case KDiagramPackage.PORT_MAPPING__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case KDiagramPackage.PORT_MAPPING__NODE_ELEMENT_TYPE:
        return nodeElementType != null;
      case KDiagramPackage.PORT_MAPPING__NODE_ELEMENT_NAME:
        return NODE_ELEMENT_NAME_EDEFAULT == null ? nodeElementName != null : !NODE_ELEMENT_NAME_EDEFAULT.equals(nodeElementName);
      case KDiagramPackage.PORT_MAPPING__ELEMENTS:
        return elements != null;
      case KDiagramPackage.PORT_MAPPING__IDENTIFIED_BY:
        return identifiedBy != null && !identifiedBy.isEmpty();
      case KDiagramPackage.PORT_MAPPING__INDENTIFIED_BY:
        return indentifiedBy != null && !indentifiedBy.isEmpty();
      case KDiagramPackage.PORT_MAPPING__FIGURE_TYPE:
        return figureType != null;
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
    result.append(", nodeElementName: ");
    result.append(nodeElementName);
    result.append(')');
    return result.toString();
  }

} //PortMappingImpl
