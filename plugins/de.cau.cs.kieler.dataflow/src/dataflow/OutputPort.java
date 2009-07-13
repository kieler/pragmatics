/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dataflow;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Output Port</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link dataflow.OutputPort#getOutputParent <em>Output Parent</em>}</li>
 * </ul>
 * </p>
 *
 * @see dataflow.DataflowPackage#getOutputPort()
 * @model
 * @generated
 */
public interface OutputPort extends Port {
	/**
     * Returns the value of the '<em><b>Output Parent</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link dataflow.Box#getOutputs <em>Outputs</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Output Parent</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Output Parent</em>' container reference.
     * @see #setOutputParent(Box)
     * @see dataflow.DataflowPackage#getOutputPort_OutputParent()
     * @see dataflow.Box#getOutputs
     * @model opposite="outputs" required="true" transient="false"
     * @generated
     */
	Box getOutputParent();

	/**
     * Sets the value of the '{@link dataflow.OutputPort#getOutputParent <em>Output Parent</em>}' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Output Parent</em>' container reference.
     * @see #getOutputParent()
     * @generated
     */
	void setOutputParent(Box value);

} // OutputPort
