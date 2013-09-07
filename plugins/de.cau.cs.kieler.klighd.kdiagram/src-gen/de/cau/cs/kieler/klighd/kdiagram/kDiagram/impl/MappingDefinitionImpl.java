/**
 */
package de.cau.cs.kieler.klighd.kdiagram.kDiagram.impl;

import de.cau.cs.kieler.klighd.kdiagram.kDiagram.EdgeMapping;
import de.cau.cs.kieler.klighd.kdiagram.kDiagram.KDiagramPackage;
import de.cau.cs.kieler.klighd.kdiagram.kDiagram.MappingDefinition;
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

import org.eclipse.xtext.common.types.JvmParameterizedTypeReference;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Mapping Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.impl.MappingDefinitionImpl#getType <em>Type</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.impl.MappingDefinitionImpl#getName <em>Name</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.impl.MappingDefinitionImpl#getNodeMappings <em>Node Mappings</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.impl.MappingDefinitionImpl#getPortMappings <em>Port Mappings</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.impl.MappingDefinitionImpl#getEdgeMappings <em>Edge Mappings</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MappingDefinitionImpl extends MinimalEObjectImpl.Container implements MappingDefinition
{
  /**
   * The cached value of the '{@link #getType() <em>Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getType()
   * @generated
   * @ordered
   */
  protected JvmParameterizedTypeReference type;

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
   * The cached value of the '{@link #getNodeMappings() <em>Node Mappings</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNodeMappings()
   * @generated
   * @ordered
   */
  protected EList<NodeMapping> nodeMappings;

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
   * The cached value of the '{@link #getEdgeMappings() <em>Edge Mappings</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEdgeMappings()
   * @generated
   * @ordered
   */
  protected EList<EdgeMapping> edgeMappings;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected MappingDefinitionImpl()
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
    return KDiagramPackage.Literals.MAPPING_DEFINITION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public JvmParameterizedTypeReference getType()
  {
    return type;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetType(JvmParameterizedTypeReference newType, NotificationChain msgs)
  {
    JvmParameterizedTypeReference oldType = type;
    type = newType;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, KDiagramPackage.MAPPING_DEFINITION__TYPE, oldType, newType);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setType(JvmParameterizedTypeReference newType)
  {
    if (newType != type)
    {
      NotificationChain msgs = null;
      if (type != null)
        msgs = ((InternalEObject)type).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - KDiagramPackage.MAPPING_DEFINITION__TYPE, null, msgs);
      if (newType != null)
        msgs = ((InternalEObject)newType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - KDiagramPackage.MAPPING_DEFINITION__TYPE, null, msgs);
      msgs = basicSetType(newType, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, KDiagramPackage.MAPPING_DEFINITION__TYPE, newType, newType));
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
      eNotify(new ENotificationImpl(this, Notification.SET, KDiagramPackage.MAPPING_DEFINITION__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<NodeMapping> getNodeMappings()
  {
    if (nodeMappings == null)
    {
      nodeMappings = new EObjectContainmentEList<NodeMapping>(NodeMapping.class, this, KDiagramPackage.MAPPING_DEFINITION__NODE_MAPPINGS);
    }
    return nodeMappings;
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
      portMappings = new EObjectContainmentEList<PortMapping>(PortMapping.class, this, KDiagramPackage.MAPPING_DEFINITION__PORT_MAPPINGS);
    }
    return portMappings;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<EdgeMapping> getEdgeMappings()
  {
    if (edgeMappings == null)
    {
      edgeMappings = new EObjectContainmentEList<EdgeMapping>(EdgeMapping.class, this, KDiagramPackage.MAPPING_DEFINITION__EDGE_MAPPINGS);
    }
    return edgeMappings;
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
      case KDiagramPackage.MAPPING_DEFINITION__TYPE:
        return basicSetType(null, msgs);
      case KDiagramPackage.MAPPING_DEFINITION__NODE_MAPPINGS:
        return ((InternalEList<?>)getNodeMappings()).basicRemove(otherEnd, msgs);
      case KDiagramPackage.MAPPING_DEFINITION__PORT_MAPPINGS:
        return ((InternalEList<?>)getPortMappings()).basicRemove(otherEnd, msgs);
      case KDiagramPackage.MAPPING_DEFINITION__EDGE_MAPPINGS:
        return ((InternalEList<?>)getEdgeMappings()).basicRemove(otherEnd, msgs);
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
      case KDiagramPackage.MAPPING_DEFINITION__TYPE:
        return getType();
      case KDiagramPackage.MAPPING_DEFINITION__NAME:
        return getName();
      case KDiagramPackage.MAPPING_DEFINITION__NODE_MAPPINGS:
        return getNodeMappings();
      case KDiagramPackage.MAPPING_DEFINITION__PORT_MAPPINGS:
        return getPortMappings();
      case KDiagramPackage.MAPPING_DEFINITION__EDGE_MAPPINGS:
        return getEdgeMappings();
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
      case KDiagramPackage.MAPPING_DEFINITION__TYPE:
        setType((JvmParameterizedTypeReference)newValue);
        return;
      case KDiagramPackage.MAPPING_DEFINITION__NAME:
        setName((String)newValue);
        return;
      case KDiagramPackage.MAPPING_DEFINITION__NODE_MAPPINGS:
        getNodeMappings().clear();
        getNodeMappings().addAll((Collection<? extends NodeMapping>)newValue);
        return;
      case KDiagramPackage.MAPPING_DEFINITION__PORT_MAPPINGS:
        getPortMappings().clear();
        getPortMappings().addAll((Collection<? extends PortMapping>)newValue);
        return;
      case KDiagramPackage.MAPPING_DEFINITION__EDGE_MAPPINGS:
        getEdgeMappings().clear();
        getEdgeMappings().addAll((Collection<? extends EdgeMapping>)newValue);
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
      case KDiagramPackage.MAPPING_DEFINITION__TYPE:
        setType((JvmParameterizedTypeReference)null);
        return;
      case KDiagramPackage.MAPPING_DEFINITION__NAME:
        setName(NAME_EDEFAULT);
        return;
      case KDiagramPackage.MAPPING_DEFINITION__NODE_MAPPINGS:
        getNodeMappings().clear();
        return;
      case KDiagramPackage.MAPPING_DEFINITION__PORT_MAPPINGS:
        getPortMappings().clear();
        return;
      case KDiagramPackage.MAPPING_DEFINITION__EDGE_MAPPINGS:
        getEdgeMappings().clear();
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
      case KDiagramPackage.MAPPING_DEFINITION__TYPE:
        return type != null;
      case KDiagramPackage.MAPPING_DEFINITION__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case KDiagramPackage.MAPPING_DEFINITION__NODE_MAPPINGS:
        return nodeMappings != null && !nodeMappings.isEmpty();
      case KDiagramPackage.MAPPING_DEFINITION__PORT_MAPPINGS:
        return portMappings != null && !portMappings.isEmpty();
      case KDiagramPackage.MAPPING_DEFINITION__EDGE_MAPPINGS:
        return edgeMappings != null && !edgeMappings.isEmpty();
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

} //MappingDefinitionImpl
