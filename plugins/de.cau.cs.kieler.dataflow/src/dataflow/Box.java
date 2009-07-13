/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dataflow;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Box</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link dataflow.Box#getName <em>Name</em>}</li>
 *   <li>{@link dataflow.Box#getInputs <em>Inputs</em>}</li>
 *   <li>{@link dataflow.Box#getOutputs <em>Outputs</em>}</li>
 *   <li>{@link dataflow.Box#getBoxes <em>Boxes</em>}</li>
 *   <li>{@link dataflow.Box#getConnections <em>Connections</em>}</li>
 * </ul>
 * </p>
 *
 * @see dataflow.DataflowPackage#getBox()
 * @model
 * @generated
 */
public interface Box extends EObject {
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
     * @see dataflow.DataflowPackage#getBox_Name()
     * @model
     * @generated
     */
	String getName();

	/**
     * Sets the value of the '{@link dataflow.Box#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
	void setName(String value);

	/**
     * Returns the value of the '<em><b>Inputs</b></em>' containment reference list.
     * The list contents are of type {@link dataflow.InputPort}.
     * It is bidirectional and its opposite is '{@link dataflow.InputPort#getInputParent <em>Input Parent</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inputs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Inputs</em>' containment reference list.
     * @see dataflow.DataflowPackage#getBox_Inputs()
     * @see dataflow.InputPort#getInputParent
     * @model opposite="inputParent" containment="true"
     * @generated
     */
	EList<InputPort> getInputs();

	/**
     * Returns the value of the '<em><b>Outputs</b></em>' containment reference list.
     * The list contents are of type {@link dataflow.OutputPort}.
     * It is bidirectional and its opposite is '{@link dataflow.OutputPort#getOutputParent <em>Output Parent</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Outputs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Outputs</em>' containment reference list.
     * @see dataflow.DataflowPackage#getBox_Outputs()
     * @see dataflow.OutputPort#getOutputParent
     * @model opposite="outputParent" containment="true"
     * @generated
     */
	EList<OutputPort> getOutputs();

	/**
     * Returns the value of the '<em><b>Boxes</b></em>' containment reference list.
     * The list contents are of type {@link dataflow.Box}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Boxes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Boxes</em>' containment reference list.
     * @see dataflow.DataflowPackage#getBox_Boxes()
     * @model containment="true"
     * @generated
     */
	EList<Box> getBoxes();

	/**
     * Returns the value of the '<em><b>Connections</b></em>' containment reference list.
     * The list contents are of type {@link dataflow.Connection}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Connections</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Connections</em>' containment reference list.
     * @see dataflow.DataflowPackage#getBox_Connections()
     * @model containment="true"
     * @generated
     */
	EList<Connection> getConnections();

} // Box
