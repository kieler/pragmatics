/**
 */
package de.cau.cs.kieler.grana.text.grana;

import org.eclipse.elk.graph.ElkNode;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Job</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.grana.text.grana.Job#getName <em>Name</em>}</li>
 *   <li>{@link de.cau.cs.kieler.grana.text.grana.Job#getResources <em>Resources</em>}</li>
 *   <li>{@link de.cau.cs.kieler.grana.text.grana.Job#getLayoutOptions <em>Layout Options</em>}</li>
 *   <li>{@link de.cau.cs.kieler.grana.text.grana.Job#getAnalyses <em>Analyses</em>}</li>
 *   <li>{@link de.cau.cs.kieler.grana.text.grana.Job#getOutputType <em>Output Type</em>}</li>
 *   <li>{@link de.cau.cs.kieler.grana.text.grana.Job#getOutput <em>Output</em>}</li>
 * </ul>
 *
 * @see de.cau.cs.kieler.grana.text.grana.GranaPackage#getJob()
 * @model
 * @generated
 */
public interface Job extends EObject
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
   * @see de.cau.cs.kieler.grana.text.grana.GranaPackage#getJob_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.grana.text.grana.Job#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Resources</b></em>' containment reference list.
   * The list contents are of type {@link de.cau.cs.kieler.grana.text.grana.Resource}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Resources</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Resources</em>' containment reference list.
   * @see de.cau.cs.kieler.grana.text.grana.GranaPackage#getJob_Resources()
   * @model containment="true"
   * @generated
   */
  EList<Resource> getResources();

  /**
   * Returns the value of the '<em><b>Layout Options</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.elk.graph.ElkNode}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Layout Options</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Layout Options</em>' containment reference list.
   * @see de.cau.cs.kieler.grana.text.grana.GranaPackage#getJob_LayoutOptions()
   * @model containment="true"
   * @generated
   */
  EList<ElkNode> getLayoutOptions();

  /**
   * Returns the value of the '<em><b>Analyses</b></em>' containment reference list.
   * The list contents are of type {@link de.cau.cs.kieler.grana.text.grana.Analysis}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Analyses</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Analyses</em>' containment reference list.
   * @see de.cau.cs.kieler.grana.text.grana.GranaPackage#getJob_Analyses()
   * @model containment="true"
   * @generated
   */
  EList<Analysis> getAnalyses();

  /**
   * Returns the value of the '<em><b>Output Type</b></em>' attribute.
   * The literals are from the enumeration {@link de.cau.cs.kieler.grana.text.grana.OutputType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Output Type</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Output Type</em>' attribute.
   * @see de.cau.cs.kieler.grana.text.grana.OutputType
   * @see #setOutputType(OutputType)
   * @see de.cau.cs.kieler.grana.text.grana.GranaPackage#getJob_OutputType()
   * @model
   * @generated
   */
  OutputType getOutputType();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.grana.text.grana.Job#getOutputType <em>Output Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Output Type</em>' attribute.
   * @see de.cau.cs.kieler.grana.text.grana.OutputType
   * @see #getOutputType()
   * @generated
   */
  void setOutputType(OutputType value);

  /**
   * Returns the value of the '<em><b>Output</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Output</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Output</em>' containment reference.
   * @see #setOutput(Output)
   * @see de.cau.cs.kieler.grana.text.grana.GranaPackage#getJob_Output()
   * @model containment="true"
   * @generated
   */
  Output getOutput();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.grana.text.grana.Job#getOutput <em>Output</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Output</em>' containment reference.
   * @see #getOutput()
   * @generated
   */
  void setOutput(Output value);

} // Job
