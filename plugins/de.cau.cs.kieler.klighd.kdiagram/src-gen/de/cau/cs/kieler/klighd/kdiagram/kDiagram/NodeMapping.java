/**
 */
package de.cau.cs.kieler.klighd.kdiagram.kDiagram;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.xtext.common.types.JvmType;

import org.eclipse.xtext.xbase.XExpression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Node Mapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.NodeMapping#getName <em>Name</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.NodeMapping#getElements <em>Elements</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.NodeMapping#getFigureType <em>Figure Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.KDiagramPackage#getNodeMapping()
 * @model
 * @generated
 */
public interface NodeMapping extends EObject
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
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.KDiagramPackage#getNodeMapping_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.NodeMapping#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

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
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.KDiagramPackage#getNodeMapping_Elements()
   * @model containment="true"
   * @generated
   */
  XExpression getElements();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.NodeMapping#getElements <em>Elements</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Elements</em>' containment reference.
   * @see #getElements()
   * @generated
   */
  void setElements(XExpression value);

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
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.KDiagramPackage#getNodeMapping_FigureType()
   * @model
   * @generated
   */
  JvmType getFigureType();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.NodeMapping#getFigureType <em>Figure Type</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Figure Type</em>' reference.
   * @see #getFigureType()
   * @generated
   */
  void setFigureType(JvmType value);

} // NodeMapping
