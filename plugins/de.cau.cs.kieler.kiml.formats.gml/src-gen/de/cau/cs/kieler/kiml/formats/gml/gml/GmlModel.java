/**
 * <copyright>
 * </copyright>
 *
 */
package de.cau.cs.kieler.kiml.formats.gml.gml;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.kiml.formats.gml.gml.GmlModel#getElements <em>Elements</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.kiml.formats.gml.gml.GmlPackage#getGmlModel()
 * @model
 * @generated
 */
public interface GmlModel extends EObject
{
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
   * @see de.cau.cs.kieler.kiml.formats.gml.gml.GmlPackage#getGmlModel_Elements()
   * @model containment="true"
   * @generated
   */
  EList<Element> getElements();

} // GmlModel
