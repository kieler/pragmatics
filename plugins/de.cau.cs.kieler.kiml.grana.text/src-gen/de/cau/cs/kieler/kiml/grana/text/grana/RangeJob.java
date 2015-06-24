/**
 */
package de.cau.cs.kieler.kiml.grana.text.grana;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Range Job</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.kiml.grana.text.grana.RangeJob#getRangeOption <em>Range Option</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kiml.grana.text.grana.RangeJob#getRangeValues <em>Range Values</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kiml.grana.text.grana.RangeJob#getRangeAnalysis <em>Range Analysis</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kiml.grana.text.grana.RangeJob#getRangeAnalysisComponent <em>Range Analysis Component</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.kiml.grana.text.grana.GranaPackage#getRangeJob()
 * @model
 * @generated
 */
public interface RangeJob extends Job
{
  /**
   * Returns the value of the '<em><b>Range Option</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Range Option</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Range Option</em>' attribute.
   * @see #setRangeOption(String)
   * @see de.cau.cs.kieler.kiml.grana.text.grana.GranaPackage#getRangeJob_RangeOption()
   * @model
   * @generated
   */
  String getRangeOption();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.kiml.grana.text.grana.RangeJob#getRangeOption <em>Range Option</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Range Option</em>' attribute.
   * @see #getRangeOption()
   * @generated
   */
  void setRangeOption(String value);

  /**
   * Returns the value of the '<em><b>Range Values</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Range Values</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Range Values</em>' containment reference.
   * @see #setRangeValues(Range)
   * @see de.cau.cs.kieler.kiml.grana.text.grana.GranaPackage#getRangeJob_RangeValues()
   * @model containment="true"
   * @generated
   */
  Range getRangeValues();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.kiml.grana.text.grana.RangeJob#getRangeValues <em>Range Values</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Range Values</em>' containment reference.
   * @see #getRangeValues()
   * @generated
   */
  void setRangeValues(Range value);

  /**
   * Returns the value of the '<em><b>Range Analysis</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Range Analysis</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Range Analysis</em>' containment reference.
   * @see #setRangeAnalysis(Analysis)
   * @see de.cau.cs.kieler.kiml.grana.text.grana.GranaPackage#getRangeJob_RangeAnalysis()
   * @model containment="true"
   * @generated
   */
  Analysis getRangeAnalysis();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.kiml.grana.text.grana.RangeJob#getRangeAnalysis <em>Range Analysis</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Range Analysis</em>' containment reference.
   * @see #getRangeAnalysis()
   * @generated
   */
  void setRangeAnalysis(Analysis value);

  /**
   * Returns the value of the '<em><b>Range Analysis Component</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Range Analysis Component</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Range Analysis Component</em>' attribute.
   * @see #setRangeAnalysisComponent(int)
   * @see de.cau.cs.kieler.kiml.grana.text.grana.GranaPackage#getRangeJob_RangeAnalysisComponent()
   * @model
   * @generated
   */
  int getRangeAnalysisComponent();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.kiml.grana.text.grana.RangeJob#getRangeAnalysisComponent <em>Range Analysis Component</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Range Analysis Component</em>' attribute.
   * @see #getRangeAnalysisComponent()
   * @generated
   */
  void setRangeAnalysisComponent(int value);

} // RangeJob
