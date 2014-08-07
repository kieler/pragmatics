/**
 */
package de.cau.cs.kieler.kiml.grana.text.grana;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Grana</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.kiml.grana.text.grana.Grana#getJobs <em>Jobs</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.kiml.grana.text.grana.GranaPackage#getGrana()
 * @model
 * @generated
 */
public interface Grana extends EObject
{
  /**
   * Returns the value of the '<em><b>Jobs</b></em>' containment reference list.
   * The list contents are of type {@link de.cau.cs.kieler.kiml.grana.text.grana.Job}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Jobs</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Jobs</em>' containment reference list.
   * @see de.cau.cs.kieler.kiml.grana.text.grana.GranaPackage#getGrana_Jobs()
   * @model containment="true"
   * @generated
   */
  EList<Job> getJobs();

} // Grana
