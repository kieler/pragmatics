/**
 * <copyright>
 * </copyright>
 *
 */
package de.cau.cs.kieler.kiml.graphviz.dot;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Attribute Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.kiml.graphviz.dot.AttributeStatement#getType <em>Type</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kiml.graphviz.dot.AttributeStatement#getAttributes <em>Attributes</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.kiml.graphviz.dot.DotPackage#getAttributeStatement()
 * @model
 * @generated
 */
public interface AttributeStatement extends Statement
{
  /**
   * Returns the value of the '<em><b>Type</b></em>' attribute.
   * The literals are from the enumeration {@link de.cau.cs.kieler.kiml.graphviz.dot.AttributeType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Type</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type</em>' attribute.
   * @see de.cau.cs.kieler.kiml.graphviz.dot.AttributeType
   * @see #setType(AttributeType)
   * @see de.cau.cs.kieler.kiml.graphviz.dot.DotPackage#getAttributeStatement_Type()
   * @model
   * @generated
   */
  AttributeType getType();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.kiml.graphviz.dot.AttributeStatement#getType <em>Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type</em>' attribute.
   * @see de.cau.cs.kieler.kiml.graphviz.dot.AttributeType
   * @see #getType()
   * @generated
   */
  void setType(AttributeType value);

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
   * @see de.cau.cs.kieler.kiml.graphviz.dot.DotPackage#getAttributeStatement_Attributes()
   * @model containment="true"
   * @generated
   */
  AttributeList getAttributes();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.kiml.graphviz.dot.AttributeStatement#getAttributes <em>Attributes</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Attributes</em>' containment reference.
   * @see #getAttributes()
   * @generated
   */
  void setAttributes(AttributeList value);

} // AttributeStatement
