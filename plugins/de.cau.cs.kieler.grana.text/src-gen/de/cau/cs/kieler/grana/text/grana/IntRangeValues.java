/**
 */
package de.cau.cs.kieler.grana.text.grana;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Int Range Values</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.grana.text.grana.IntRangeValues#getValues <em>Values</em>}</li>
 * </ul>
 *
 * @see de.cau.cs.kieler.grana.text.grana.GranaPackage#getIntRangeValues()
 * @model
 * @generated
 */
public interface IntRangeValues extends IntRange
{
  /**
   * Returns the value of the '<em><b>Values</b></em>' attribute list.
   * The list contents are of type {@link java.lang.Integer}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Values</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Values</em>' attribute list.
   * @see de.cau.cs.kieler.grana.text.grana.GranaPackage#getIntRangeValues_Values()
   * @model unique="false"
   * @generated
   */
  EList<Integer> getValues();

} // IntRangeValues
