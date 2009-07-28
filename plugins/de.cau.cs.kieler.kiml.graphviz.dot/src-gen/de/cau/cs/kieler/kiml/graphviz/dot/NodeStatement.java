/**
 * <copyright>
 * </copyright>
 *
 */
package de.cau.cs.kieler.kiml.graphviz.dot;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Node Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.kiml.graphviz.dot.NodeStatement#getNode <em>Node</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kiml.graphviz.dot.NodeStatement#getAttributes <em>Attributes</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.kiml.graphviz.dot.DotPackage#getNodeStatement()
 * @model
 * @generated
 */
public interface NodeStatement extends Statement
{
  /**
   * Returns the value of the '<em><b>Node</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Node</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Node</em>' containment reference.
   * @see #setNode(Node)
   * @see de.cau.cs.kieler.kiml.graphviz.dot.DotPackage#getNodeStatement_Node()
   * @model containment="true"
   * @generated
   */
  Node getNode();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.kiml.graphviz.dot.NodeStatement#getNode <em>Node</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Node</em>' containment reference.
   * @see #getNode()
   * @generated
   */
  void setNode(Node value);

  /**
   * Returns the value of the '<em><b>Attributes</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Attributes</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Attributes</em>' containment reference.
   * @see #setAttributes(AttributeList)
   * @see de.cau.cs.kieler.kiml.graphviz.dot.DotPackage#getNodeStatement_Attributes()
   * @model containment="true"
   * @generated
   */
  AttributeList getAttributes();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.kiml.graphviz.dot.NodeStatement#getAttributes <em>Attributes</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Attributes</em>' containment reference.
   * @see #getAttributes()
   * @generated
   */
  void setAttributes(AttributeList value);

} // NodeStatement
