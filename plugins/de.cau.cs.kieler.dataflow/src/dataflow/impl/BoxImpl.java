/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dataflow.impl;

import dataflow.Box;
import dataflow.Connection;
import dataflow.DataflowPackage;
import dataflow.InputPort;
import dataflow.OutputPort;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Box</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link dataflow.impl.BoxImpl#getName <em>Name</em>}</li>
 *   <li>{@link dataflow.impl.BoxImpl#getInputs <em>Inputs</em>}</li>
 *   <li>{@link dataflow.impl.BoxImpl#getOutputs <em>Outputs</em>}</li>
 *   <li>{@link dataflow.impl.BoxImpl#getBoxes <em>Boxes</em>}</li>
 *   <li>{@link dataflow.impl.BoxImpl#getConnections <em>Connections</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BoxImpl extends EObjectImpl implements Box {
	/**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
	protected static final String NAME_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
	protected String name = NAME_EDEFAULT;

	/**
     * The cached value of the '{@link #getInputs() <em>Inputs</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getInputs()
     * @generated
     * @ordered
     */
	protected EList<InputPort> inputs;

	/**
     * The cached value of the '{@link #getOutputs() <em>Outputs</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getOutputs()
     * @generated
     * @ordered
     */
	protected EList<OutputPort> outputs;

	/**
     * The cached value of the '{@link #getBoxes() <em>Boxes</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getBoxes()
     * @generated
     * @ordered
     */
	protected EList<Box> boxes;

	/**
     * The cached value of the '{@link #getConnections() <em>Connections</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getConnections()
     * @generated
     * @ordered
     */
	protected EList<Connection> connections;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected BoxImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return DataflowPackage.Literals.BOX;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getName() {
        return name;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DataflowPackage.BOX__NAME, oldName, name));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList<InputPort> getInputs() {
        if (inputs == null) {
            inputs = new EObjectContainmentWithInverseEList<InputPort>(InputPort.class, this, DataflowPackage.BOX__INPUTS, DataflowPackage.INPUT_PORT__INPUT_PARENT);
        }
        return inputs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList<OutputPort> getOutputs() {
        if (outputs == null) {
            outputs = new EObjectContainmentWithInverseEList<OutputPort>(OutputPort.class, this, DataflowPackage.BOX__OUTPUTS, DataflowPackage.OUTPUT_PORT__OUTPUT_PARENT);
        }
        return outputs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList<Box> getBoxes() {
        if (boxes == null) {
            boxes = new EObjectContainmentEList<Box>(Box.class, this, DataflowPackage.BOX__BOXES);
        }
        return boxes;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList<Connection> getConnections() {
        if (connections == null) {
            connections = new EObjectContainmentEList<Connection>(Connection.class, this, DataflowPackage.BOX__CONNECTIONS);
        }
        return connections;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case DataflowPackage.BOX__INPUTS:
                return ((InternalEList<InternalEObject>)(InternalEList<?>)getInputs()).basicAdd(otherEnd, msgs);
            case DataflowPackage.BOX__OUTPUTS:
                return ((InternalEList<InternalEObject>)(InternalEList<?>)getOutputs()).basicAdd(otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case DataflowPackage.BOX__INPUTS:
                return ((InternalEList<?>)getInputs()).basicRemove(otherEnd, msgs);
            case DataflowPackage.BOX__OUTPUTS:
                return ((InternalEList<?>)getOutputs()).basicRemove(otherEnd, msgs);
            case DataflowPackage.BOX__BOXES:
                return ((InternalEList<?>)getBoxes()).basicRemove(otherEnd, msgs);
            case DataflowPackage.BOX__CONNECTIONS:
                return ((InternalEList<?>)getConnections()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case DataflowPackage.BOX__NAME:
                return getName();
            case DataflowPackage.BOX__INPUTS:
                return getInputs();
            case DataflowPackage.BOX__OUTPUTS:
                return getOutputs();
            case DataflowPackage.BOX__BOXES:
                return getBoxes();
            case DataflowPackage.BOX__CONNECTIONS:
                return getConnections();
        }
        return super.eGet(featureID, resolve, coreType);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case DataflowPackage.BOX__NAME:
                setName((String)newValue);
                return;
            case DataflowPackage.BOX__INPUTS:
                getInputs().clear();
                getInputs().addAll((Collection<? extends InputPort>)newValue);
                return;
            case DataflowPackage.BOX__OUTPUTS:
                getOutputs().clear();
                getOutputs().addAll((Collection<? extends OutputPort>)newValue);
                return;
            case DataflowPackage.BOX__BOXES:
                getBoxes().clear();
                getBoxes().addAll((Collection<? extends Box>)newValue);
                return;
            case DataflowPackage.BOX__CONNECTIONS:
                getConnections().clear();
                getConnections().addAll((Collection<? extends Connection>)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void eUnset(int featureID) {
        switch (featureID) {
            case DataflowPackage.BOX__NAME:
                setName(NAME_EDEFAULT);
                return;
            case DataflowPackage.BOX__INPUTS:
                getInputs().clear();
                return;
            case DataflowPackage.BOX__OUTPUTS:
                getOutputs().clear();
                return;
            case DataflowPackage.BOX__BOXES:
                getBoxes().clear();
                return;
            case DataflowPackage.BOX__CONNECTIONS:
                getConnections().clear();
                return;
        }
        super.eUnset(featureID);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public boolean eIsSet(int featureID) {
        switch (featureID) {
            case DataflowPackage.BOX__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case DataflowPackage.BOX__INPUTS:
                return inputs != null && !inputs.isEmpty();
            case DataflowPackage.BOX__OUTPUTS:
                return outputs != null && !outputs.isEmpty();
            case DataflowPackage.BOX__BOXES:
                return boxes != null && !boxes.isEmpty();
            case DataflowPackage.BOX__CONNECTIONS:
                return connections != null && !connections.isEmpty();
        }
        return super.eIsSet(featureID);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (name: ");
        result.append(name);
        result.append(')');
        return result.toString();
    }

} //BoxImpl
