/**
 * generated by Xtext 2.10.0
 */
package de.cau.cs.kieler.kgraph.text.grandom;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Nodes</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.kgraph.text.grandom.Nodes#getNNodes <em>NNodes</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kgraph.text.grandom.Nodes#getPorts <em>Ports</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kgraph.text.grandom.Nodes#isLabels <em>Labels</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kgraph.text.grandom.Nodes#getSize <em>Size</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kgraph.text.grandom.Nodes#isRemoveIsolated <em>Remove Isolated</em>}</li>
 * </ul>
 *
 * @see de.cau.cs.kieler.kgraph.text.grandom.GrandomPackage#getNodes()
 * @model
 * @generated
 */
public interface Nodes extends EObject
{
  /**
   * Returns the value of the '<em><b>NNodes</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>NNodes</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>NNodes</em>' containment reference.
   * @see #setNNodes(DoubleQuantity)
   * @see de.cau.cs.kieler.kgraph.text.grandom.GrandomPackage#getNodes_NNodes()
   * @model containment="true"
   * @generated
   */
  DoubleQuantity getNNodes();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.kgraph.text.grandom.Nodes#getNNodes <em>NNodes</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>NNodes</em>' containment reference.
   * @see #getNNodes()
   * @generated
   */
  void setNNodes(DoubleQuantity value);

  /**
   * Returns the value of the '<em><b>Ports</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Ports</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Ports</em>' containment reference.
   * @see #setPorts(Ports)
   * @see de.cau.cs.kieler.kgraph.text.grandom.GrandomPackage#getNodes_Ports()
   * @model containment="true"
   * @generated
   */
  Ports getPorts();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.kgraph.text.grandom.Nodes#getPorts <em>Ports</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Ports</em>' containment reference.
   * @see #getPorts()
   * @generated
   */
  void setPorts(Ports value);

  /**
   * Returns the value of the '<em><b>Labels</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Labels</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Labels</em>' attribute.
   * @see #setLabels(boolean)
   * @see de.cau.cs.kieler.kgraph.text.grandom.GrandomPackage#getNodes_Labels()
   * @model
   * @generated
   */
  boolean isLabels();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.kgraph.text.grandom.Nodes#isLabels <em>Labels</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Labels</em>' attribute.
   * @see #isLabels()
   * @generated
   */
  void setLabels(boolean value);

  /**
   * Returns the value of the '<em><b>Size</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Size</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Size</em>' containment reference.
   * @see #setSize(Size)
   * @see de.cau.cs.kieler.kgraph.text.grandom.GrandomPackage#getNodes_Size()
   * @model containment="true"
   * @generated
   */
  Size getSize();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.kgraph.text.grandom.Nodes#getSize <em>Size</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Size</em>' containment reference.
   * @see #getSize()
   * @generated
   */
  void setSize(Size value);

  /**
   * Returns the value of the '<em><b>Remove Isolated</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Remove Isolated</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Remove Isolated</em>' attribute.
   * @see #setRemoveIsolated(boolean)
   * @see de.cau.cs.kieler.kgraph.text.grandom.GrandomPackage#getNodes_RemoveIsolated()
   * @model
   * @generated
   */
  boolean isRemoveIsolated();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.kgraph.text.grandom.Nodes#isRemoveIsolated <em>Remove Isolated</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Remove Isolated</em>' attribute.
   * @see #isRemoveIsolated()
   * @generated
   */
  void setRemoveIsolated(boolean value);

} // Nodes
