/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dataflow;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Input Port</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link dataflow.InputPort#getInputParent <em>Input Parent</em>}</li>
 * </ul>
 * </p>
 *
 * @see dataflow.DataflowPackage#getInputPort()
 * @model
 * @generated
 */
public interface InputPort extends Port {
	/**
     * Returns the value of the '<em><b>Input Parent</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link dataflow.Box#getInputs <em>Inputs</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Input Parent</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Input Parent</em>' container reference.
     * @see #setInputParent(Box)
     * @see dataflow.DataflowPackage#getInputPort_InputParent()
     * @see dataflow.Box#getInputs
     * @model opposite="inputs" required="true" transient="false"
     * @generated
     */
	Box getInputParent();

	/**
     * Sets the value of the '{@link dataflow.InputPort#getInputParent <em>Input Parent</em>}' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Input Parent</em>' container reference.
     * @see #getInputParent()
     * @generated
     */
	void setInputParent(Box value);

} // InputPort
