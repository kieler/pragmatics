/**
 * <copyright>
 * </copyright>
 *
 */
package de.cau.cs.kieler.kiml.graphviz.dot;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Attribute List</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.kiml.graphviz.dot.AttributeList#getEntries <em>Entries</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.kiml.graphviz.dot.DotPackage#getAttributeList()
 * @model
 * @generated
 */
public interface AttributeList extends EObject
{
  /**
   * Returns the value of the '<em><b>Entries</b></em>' containment reference list.
   * The list contents are of type {@link de.cau.cs.kieler.kiml.graphviz.dot.Attribute}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Entries</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Entries</em>' containment reference list.
   * @see de.cau.cs.kieler.kiml.graphviz.dot.DotPackage#getAttributeList_Entries()
   * @model containment="true"
   * @generated
   */
  EList<Attribute> getEntries();

} // AttributeList
