/**
 */
package de.cau.cs.kieler.kiml.grana.text.grana;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Resource Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.kiml.grana.text.grana.ResourceReference#getResourceRefs <em>Resource Refs</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.kiml.grana.text.grana.GranaPackage#getResourceReference()
 * @model
 * @generated
 */
public interface ResourceReference extends Resource
{
  /**
   * Returns the value of the '<em><b>Resource Refs</b></em>' reference list.
   * The list contents are of type {@link de.cau.cs.kieler.kiml.grana.text.grana.GlobalResourceRef}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Resource Refs</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Resource Refs</em>' reference list.
   * @see de.cau.cs.kieler.kiml.grana.text.grana.GranaPackage#getResourceReference_ResourceRefs()
   * @model
   * @generated
   */
  EList<GlobalResourceRef> getResourceRefs();

} // ResourceReference
