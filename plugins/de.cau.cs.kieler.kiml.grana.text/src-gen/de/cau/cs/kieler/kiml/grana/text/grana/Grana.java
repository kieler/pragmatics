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
 *   <li>{@link de.cau.cs.kieler.kiml.grana.text.grana.Grana#getGlobalResources <em>Global Resources</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kiml.grana.text.grana.Grana#getGloobalOutputs <em>Gloobal Outputs</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kiml.grana.text.grana.Grana#isExecuteAll <em>Execute All</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kiml.grana.text.grana.Grana#getExecute <em>Execute</em>}</li>
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
   * Returns the value of the '<em><b>Global Resources</b></em>' containment reference list.
   * The list contents are of type {@link de.cau.cs.kieler.kiml.grana.text.grana.GlobalResourceRef}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Global Resources</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Global Resources</em>' containment reference list.
   * @see de.cau.cs.kieler.kiml.grana.text.grana.GranaPackage#getGrana_GlobalResources()
   * @model containment="true"
   * @generated
   */
  EList<GlobalResourceRef> getGlobalResources();

  /**
   * Returns the value of the '<em><b>Gloobal Outputs</b></em>' containment reference list.
   * The list contents are of type {@link de.cau.cs.kieler.kiml.grana.text.grana.GlobalOutputRef}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Gloobal Outputs</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Gloobal Outputs</em>' containment reference list.
   * @see de.cau.cs.kieler.kiml.grana.text.grana.GranaPackage#getGrana_GloobalOutputs()
   * @model containment="true"
   * @generated
   */
  EList<GlobalOutputRef> getGloobalOutputs();

  /**
   * Returns the value of the '<em><b>Execute All</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Execute All</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Execute All</em>' attribute.
   * @see #setExecuteAll(boolean)
   * @see de.cau.cs.kieler.kiml.grana.text.grana.GranaPackage#getGrana_ExecuteAll()
   * @model
   * @generated
   */
  boolean isExecuteAll();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.kiml.grana.text.grana.Grana#isExecuteAll <em>Execute All</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Execute All</em>' attribute.
   * @see #isExecuteAll()
   * @generated
   */
  void setExecuteAll(boolean value);

  /**
   * Returns the value of the '<em><b>Execute</b></em>' reference list.
   * The list contents are of type {@link de.cau.cs.kieler.kiml.grana.text.grana.Job}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Execute</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Execute</em>' reference list.
   * @see de.cau.cs.kieler.kiml.grana.text.grana.GranaPackage#getGrana_Execute()
   * @model
   * @generated
   */
  EList<Job> getExecute();

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
