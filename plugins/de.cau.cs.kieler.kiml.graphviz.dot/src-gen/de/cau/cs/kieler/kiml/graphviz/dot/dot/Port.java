/**
 */
package de.cau.cs.kieler.kiml.graphviz.dot.dot;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Port</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.kiml.graphviz.dot.dot.Port#getName <em>Name</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kiml.graphviz.dot.dot.Port#getCompass_pt <em>Compass pt</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.kiml.graphviz.dot.dot.DotPackage#getPort()
 * @model
 * @generated
 */
public interface Port extends EObject
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
   * @see de.cau.cs.kieler.kiml.graphviz.dot.dot.DotPackage#getPort_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.kiml.graphviz.dot.dot.Port#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Compass pt</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Compass pt</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Compass pt</em>' attribute.
   * @see #setCompass_pt(String)
   * @see de.cau.cs.kieler.kiml.graphviz.dot.dot.DotPackage#getPort_Compass_pt()
   * @model
   * @generated
   */
  String getCompass_pt();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.kiml.graphviz.dot.dot.Port#getCompass_pt <em>Compass pt</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Compass pt</em>' attribute.
   * @see #getCompass_pt()
   * @generated
   */
  void setCompass_pt(String value);

} // Port
