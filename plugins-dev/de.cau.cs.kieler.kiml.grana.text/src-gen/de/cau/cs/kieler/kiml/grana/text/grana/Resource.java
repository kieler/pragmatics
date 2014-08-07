/**
 */
package de.cau.cs.kieler.kiml.grana.text.grana;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Resource</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.kiml.grana.text.grana.Resource#getPath <em>Path</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kiml.grana.text.grana.Resource#getFilter <em>Filter</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.kiml.grana.text.grana.GranaPackage#getResource()
 * @model
 * @generated
 */
public interface Resource extends EObject
{
  /**
   * Returns the value of the '<em><b>Path</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Path</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Path</em>' attribute.
   * @see #setPath(String)
   * @see de.cau.cs.kieler.kiml.grana.text.grana.GranaPackage#getResource_Path()
   * @model
   * @generated
   */
  String getPath();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.kiml.grana.text.grana.Resource#getPath <em>Path</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Path</em>' attribute.
   * @see #getPath()
   * @generated
   */
  void setPath(String value);

  /**
   * Returns the value of the '<em><b>Filter</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Filter</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Filter</em>' attribute.
   * @see #setFilter(String)
   * @see de.cau.cs.kieler.kiml.grana.text.grana.GranaPackage#getResource_Filter()
   * @model
   * @generated
   */
  String getFilter();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.kiml.grana.text.grana.Resource#getFilter <em>Filter</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Filter</em>' attribute.
   * @see #getFilter()
   * @generated
   */
  void setFilter(String value);

} // Resource
