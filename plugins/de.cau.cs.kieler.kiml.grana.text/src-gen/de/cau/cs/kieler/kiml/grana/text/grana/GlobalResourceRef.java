/**
 */
package de.cau.cs.kieler.kiml.grana.text.grana;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Global Resource Ref</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.kiml.grana.text.grana.GlobalResourceRef#getName <em>Name</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kiml.grana.text.grana.GlobalResourceRef#getResources <em>Resources</em>}</li>
 * </ul>
 *
 * @see de.cau.cs.kieler.kiml.grana.text.grana.GranaPackage#getGlobalResourceRef()
 * @model
 * @generated
 */
public interface GlobalResourceRef extends EObject
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
   * @see de.cau.cs.kieler.kiml.grana.text.grana.GranaPackage#getGlobalResourceRef_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.kiml.grana.text.grana.GlobalResourceRef#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Resources</b></em>' containment reference list.
   * The list contents are of type {@link de.cau.cs.kieler.kiml.grana.text.grana.LocalResource}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Resources</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Resources</em>' containment reference list.
   * @see de.cau.cs.kieler.kiml.grana.text.grana.GranaPackage#getGlobalResourceRef_Resources()
   * @model containment="true"
   * @generated
   */
  EList<LocalResource> getResources();

} // GlobalResourceRef
