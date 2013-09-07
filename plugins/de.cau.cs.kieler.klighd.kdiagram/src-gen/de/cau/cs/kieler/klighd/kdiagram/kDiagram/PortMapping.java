/**
 */
package de.cau.cs.kieler.klighd.kdiagram.kDiagram;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.xtext.common.types.JvmParameterizedTypeReference;
import org.eclipse.xtext.common.types.JvmType;

import org.eclipse.xtext.xbase.XExpression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Port Mapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.PortMapping#getName <em>Name</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.PortMapping#getNodeElementType <em>Node Element Type</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.PortMapping#getNodeElementName <em>Node Element Name</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.PortMapping#getElements <em>Elements</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.PortMapping#getIdentifiedBy <em>Identified By</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.PortMapping#getIndentifiedBy <em>Indentified By</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.PortMapping#getFigureType <em>Figure Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.KDiagramPackage#getPortMapping()
 * @model
 * @generated
 */
public interface PortMapping extends EObject
{
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
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.KDiagramPackage#getPortMapping_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.PortMapping#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Node Element Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Node Element Type</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Node Element Type</em>' containment reference.
   * @see #setNodeElementType(JvmParameterizedTypeReference)
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.KDiagramPackage#getPortMapping_NodeElementType()
   * @model containment="true"
   * @generated
   */
  JvmParameterizedTypeReference getNodeElementType();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.PortMapping#getNodeElementType <em>Node Element Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Node Element Type</em>' containment reference.
   * @see #getNodeElementType()
   * @generated
   */
  void setNodeElementType(JvmParameterizedTypeReference value);

  /**
   * Returns the value of the '<em><b>Node Element Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Node Element Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Node Element Name</em>' attribute.
   * @see #setNodeElementName(String)
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.KDiagramPackage#getPortMapping_NodeElementName()
   * @model
   * @generated
   */
  String getNodeElementName();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.PortMapping#getNodeElementName <em>Node Element Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Node Element Name</em>' attribute.
   * @see #getNodeElementName()
   * @generated
   */
  void setNodeElementName(String value);

  /**
   * Returns the value of the '<em><b>Elements</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Elements</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Elements</em>' containment reference.
   * @see #setElements(XExpression)
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.KDiagramPackage#getPortMapping_Elements()
   * @model containment="true"
   * @generated
   */
  XExpression getElements();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.PortMapping#getElements <em>Elements</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Elements</em>' containment reference.
   * @see #getElements()
   * @generated
   */
  void setElements(XExpression value);

  /**
   * Returns the value of the '<em><b>Identified By</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.xtext.xbase.XExpression}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Identified By</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Identified By</em>' containment reference list.
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.KDiagramPackage#getPortMapping_IdentifiedBy()
   * @model containment="true"
   * @generated
   */
  EList<XExpression> getIdentifiedBy();

  /**
   * Returns the value of the '<em><b>Indentified By</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.xtext.xbase.XExpression}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Indentified By</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Indentified By</em>' containment reference list.
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.KDiagramPackage#getPortMapping_IndentifiedBy()
   * @model containment="true"
   * @generated
   */
  EList<XExpression> getIndentifiedBy();

  /**
   * Returns the value of the '<em><b>Figure Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Figure Type</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Figure Type</em>' reference.
   * @see #setFigureType(JvmType)
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.KDiagramPackage#getPortMapping_FigureType()
   * @model
   * @generated
   */
  JvmType getFigureType();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.PortMapping#getFigureType <em>Figure Type</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Figure Type</em>' reference.
   * @see #getFigureType()
   * @generated
   */
  void setFigureType(JvmType value);

} // PortMapping
