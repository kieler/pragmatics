/**
 */
package de.cau.cs.kieler.klighd.kdiagram.kDiagram;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.xtext.common.types.JvmParameterizedTypeReference;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Mapping Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.MappingDefinition#getType <em>Type</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.MappingDefinition#getName <em>Name</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.MappingDefinition#getNodeMappings <em>Node Mappings</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.MappingDefinition#getPortMappings <em>Port Mappings</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.MappingDefinition#getEdgeMappings <em>Edge Mappings</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.KDiagramPackage#getMappingDefinition()
 * @model
 * @generated
 */
public interface MappingDefinition extends EObject
{
  /**
   * Returns the value of the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Type</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type</em>' containment reference.
   * @see #setType(JvmParameterizedTypeReference)
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.KDiagramPackage#getMappingDefinition_Type()
   * @model containment="true"
   * @generated
   */
  JvmParameterizedTypeReference getType();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.MappingDefinition#getType <em>Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type</em>' containment reference.
   * @see #getType()
   * @generated
   */
  void setType(JvmParameterizedTypeReference value);

  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.KDiagramPackage#getMappingDefinition_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.MappingDefinition#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Node Mappings</b></em>' containment reference list.
   * The list contents are of type {@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.NodeMapping}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Node Mappings</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Node Mappings</em>' containment reference list.
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.KDiagramPackage#getMappingDefinition_NodeMappings()
   * @model containment="true"
   * @generated
   */
  EList<NodeMapping> getNodeMappings();

  /**
   * Returns the value of the '<em><b>Port Mappings</b></em>' containment reference list.
   * The list contents are of type {@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.PortMapping}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Port Mappings</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Port Mappings</em>' containment reference list.
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.KDiagramPackage#getMappingDefinition_PortMappings()
   * @model containment="true"
   * @generated
   */
  EList<PortMapping> getPortMappings();

  /**
   * Returns the value of the '<em><b>Edge Mappings</b></em>' containment reference list.
   * The list contents are of type {@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.EdgeMapping}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Edge Mappings</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Edge Mappings</em>' containment reference list.
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.KDiagramPackage#getMappingDefinition_EdgeMappings()
   * @model containment="true"
   * @generated
   */
  EList<EdgeMapping> getEdgeMappings();

} // MappingDefinition
