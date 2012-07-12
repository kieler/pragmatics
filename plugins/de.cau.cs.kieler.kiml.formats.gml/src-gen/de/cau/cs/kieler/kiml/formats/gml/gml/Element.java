/**
 */
package de.cau.cs.kieler.kiml.formats.gml.gml;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.kiml.formats.gml.gml.Element#getKey <em>Key</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kiml.formats.gml.gml.Element#getValue <em>Value</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kiml.formats.gml.gml.Element#getElements <em>Elements</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.kiml.formats.gml.gml.GmlPackage#getElement()
 * @model
 * @generated
 */
public interface Element extends EObject
{
  /**
   * Returns the value of the '<em><b>Key</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Key</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Key</em>' attribute.
   * @see #setKey(String)
   * @see de.cau.cs.kieler.kiml.formats.gml.gml.GmlPackage#getElement_Key()
   * @model
   * @generated
   */
  String getKey();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.kiml.formats.gml.gml.Element#getKey <em>Key</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Key</em>' attribute.
   * @see #getKey()
   * @generated
   */
  void setKey(String value);

  /**
   * Returns the value of the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Value</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Value</em>' attribute.
   * @see #setValue(String)
   * @see de.cau.cs.kieler.kiml.formats.gml.gml.GmlPackage#getElement_Value()
   * @model
   * @generated
   */
  String getValue();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.kiml.formats.gml.gml.Element#getValue <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Value</em>' attribute.
   * @see #getValue()
   * @generated
   */
  void setValue(String value);

  /**
   * Returns the value of the '<em><b>Elements</b></em>' containment reference list.
   * The list contents are of type {@link de.cau.cs.kieler.kiml.formats.gml.gml.Element}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Elements</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Elements</em>' containment reference list.
   * @see de.cau.cs.kieler.kiml.formats.gml.gml.GmlPackage#getElement_Elements()
   * @model containment="true"
   * @generated
   */
  EList<Element> getElements();

} // Element
