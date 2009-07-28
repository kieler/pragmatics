/**
 * <copyright>
 * </copyright>
 *
 */
package de.cau.cs.kieler.kiml.graphviz.dot;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Edge Target</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.kiml.graphviz.dot.EdgeTarget#getOperator <em>Operator</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kiml.graphviz.dot.EdgeTarget#getTargetSubgraph <em>Target Subgraph</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kiml.graphviz.dot.EdgeTarget#getTargetnode <em>Targetnode</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.kiml.graphviz.dot.DotPackage#getEdgeTarget()
 * @model
 * @generated
 */
public interface EdgeTarget extends EObject
{
  /**
   * Returns the value of the '<em><b>Operator</b></em>' attribute.
   * The literals are from the enumeration {@link de.cau.cs.kieler.kiml.graphviz.dot.EdgeOperator}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Operator</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Operator</em>' attribute.
   * @see de.cau.cs.kieler.kiml.graphviz.dot.EdgeOperator
   * @see #setOperator(EdgeOperator)
   * @see de.cau.cs.kieler.kiml.graphviz.dot.DotPackage#getEdgeTarget_Operator()
   * @model
   * @generated
   */
  EdgeOperator getOperator();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.kiml.graphviz.dot.EdgeTarget#getOperator <em>Operator</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Operator</em>' attribute.
   * @see de.cau.cs.kieler.kiml.graphviz.dot.EdgeOperator
   * @see #getOperator()
   * @generated
   */
  void setOperator(EdgeOperator value);

  /**
   * Returns the value of the '<em><b>Target Subgraph</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Target Subgraph</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Target Subgraph</em>' containment reference.
   * @see #setTargetSubgraph(Subgraph)
   * @see de.cau.cs.kieler.kiml.graphviz.dot.DotPackage#getEdgeTarget_TargetSubgraph()
   * @model containment="true"
   * @generated
   */
  Subgraph getTargetSubgraph();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.kiml.graphviz.dot.EdgeTarget#getTargetSubgraph <em>Target Subgraph</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target Subgraph</em>' containment reference.
   * @see #getTargetSubgraph()
   * @generated
   */
  void setTargetSubgraph(Subgraph value);

  /**
   * Returns the value of the '<em><b>Targetnode</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Targetnode</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Targetnode</em>' containment reference.
   * @see #setTargetnode(Node)
   * @see de.cau.cs.kieler.kiml.graphviz.dot.DotPackage#getEdgeTarget_Targetnode()
   * @model containment="true"
   * @generated
   */
  Node getTargetnode();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.kiml.graphviz.dot.EdgeTarget#getTargetnode <em>Targetnode</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Targetnode</em>' containment reference.
   * @see #getTargetnode()
   * @generated
   */
  void setTargetnode(Node value);

} // EdgeTarget
