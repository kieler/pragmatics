/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dataflow;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see dataflow.DataflowPackage
 * @generated
 */
public interface DataflowFactory extends EFactory {
	/**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	DataflowFactory eINSTANCE = dataflow.impl.DataflowFactoryImpl.init();

	/**
     * Returns a new object of class '<em>Box</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Box</em>'.
     * @generated
     */
	Box createBox();

	/**
     * Returns a new object of class '<em>Connection</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Connection</em>'.
     * @generated
     */
	Connection createConnection();

	/**
     * Returns a new object of class '<em>Model</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Model</em>'.
     * @generated
     */
	DataflowModel createDataflowModel();

	/**
     * Returns a new object of class '<em>Port</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Port</em>'.
     * @generated
     */
	Port createPort();

	/**
     * Returns a new object of class '<em>Input Port</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Input Port</em>'.
     * @generated
     */
	InputPort createInputPort();

	/**
     * Returns a new object of class '<em>Output Port</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Output Port</em>'.
     * @generated
     */
	OutputPort createOutputPort();

	/**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
	DataflowPackage getDataflowPackage();

} //DataflowFactory
