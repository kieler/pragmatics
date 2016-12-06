/**
 */
package de.cau.cs.kieler.kiml.grana.text.grana;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Regular Job</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.kiml.grana.text.grana.RegularJob#isLayoutBeforeAnalysis <em>Layout Before Analysis</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kiml.grana.text.grana.RegularJob#isMeasureExecutionTime <em>Measure Execution Time</em>}</li>
 * </ul>
 *
 * @see de.cau.cs.kieler.kiml.grana.text.grana.GranaPackage#getRegularJob()
 * @model
 * @generated
 */
public interface RegularJob extends Job
{
  /**
   * Returns the value of the '<em><b>Layout Before Analysis</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Layout Before Analysis</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Layout Before Analysis</em>' attribute.
   * @see #setLayoutBeforeAnalysis(boolean)
   * @see de.cau.cs.kieler.kiml.grana.text.grana.GranaPackage#getRegularJob_LayoutBeforeAnalysis()
   * @model
   * @generated
   */
  boolean isLayoutBeforeAnalysis();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.kiml.grana.text.grana.RegularJob#isLayoutBeforeAnalysis <em>Layout Before Analysis</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Layout Before Analysis</em>' attribute.
   * @see #isLayoutBeforeAnalysis()
   * @generated
   */
  void setLayoutBeforeAnalysis(boolean value);

  /**
   * Returns the value of the '<em><b>Measure Execution Time</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Measure Execution Time</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Measure Execution Time</em>' attribute.
   * @see #setMeasureExecutionTime(boolean)
   * @see de.cau.cs.kieler.kiml.grana.text.grana.GranaPackage#getRegularJob_MeasureExecutionTime()
   * @model
   * @generated
   */
  boolean isMeasureExecutionTime();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.kiml.grana.text.grana.RegularJob#isMeasureExecutionTime <em>Measure Execution Time</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Measure Execution Time</em>' attribute.
   * @see #isMeasureExecutionTime()
   * @generated
   */
  void setMeasureExecutionTime(boolean value);

} // RegularJob
