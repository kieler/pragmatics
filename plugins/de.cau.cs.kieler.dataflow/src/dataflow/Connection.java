/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dataflow;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Connection</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link dataflow.Connection#getSourcePort <em>Source Port</em>}</li>
 *   <li>{@link dataflow.Connection#getTargetPort <em>Target Port</em>}</li>
 * </ul>
 * </p>
 *
 * @see dataflow.DataflowPackage#getConnection()
 * @model
 * @generated
 */
public interface Connection extends EObject {
	/**
     * Returns the value of the '<em><b>Source Port</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source Port</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Source Port</em>' reference.
     * @see #setSourcePort(Port)
     * @see dataflow.DataflowPackage#getConnection_SourcePort()
     * @model required="true"
     * @generated
     */
	Port getSourcePort();

	/**
     * Sets the value of the '{@link dataflow.Connection#getSourcePort <em>Source Port</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Source Port</em>' reference.
     * @see #getSourcePort()
     * @generated
     */
	void setSourcePort(Port value);

	/**
     * Returns the value of the '<em><b>Target Port</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target Port</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Target Port</em>' reference.
     * @see #setTargetPort(Port)
     * @see dataflow.DataflowPackage#getConnection_TargetPort()
     * @model required="true"
     * @generated
     */
	Port getTargetPort();

	/**
     * Sets the value of the '{@link dataflow.Connection#getTargetPort <em>Target Port</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Target Port</em>' reference.
     * @see #getTargetPort()
     * @generated
     */
	void setTargetPort(Port value);

} // Connection
