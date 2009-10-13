/**
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 *
 * $Id$
 */
package de.cau.cs.kieler.dataflow.impl;

import de.cau.cs.kieler.dataflow.Box;
import de.cau.cs.kieler.dataflow.DataflowPackage;
import de.cau.cs.kieler.dataflow.InputPort;
import de.cau.cs.kieler.dataflow.OutputPort;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Box</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.dataflow.impl.BoxImpl#getInputs <em>Inputs</em>}</li>
 *   <li>{@link de.cau.cs.kieler.dataflow.impl.BoxImpl#getOutputs <em>Outputs</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BoxImpl extends DataflowModelImpl implements Box {
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
    public EList<InputPort> getInputs() {
        if (inputs == null) {
            inputs = new EObjectContainmentWithInverseEList<InputPort>(InputPort.class, this, DataflowPackage.BOX__INPUTS, DataflowPackage.INPUT_PORT__PARENT_BOX);
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
            outputs = new EObjectContainmentWithInverseEList<OutputPort>(OutputPort.class, this, DataflowPackage.BOX__OUTPUTS, DataflowPackage.OUTPUT_PORT__PARENT_BOX);
        }
        return outputs;
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
            case DataflowPackage.BOX__INPUTS:
                return getInputs();
            case DataflowPackage.BOX__OUTPUTS:
                return getOutputs();
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
            case DataflowPackage.BOX__INPUTS:
                getInputs().clear();
                getInputs().addAll((Collection<? extends InputPort>)newValue);
                return;
            case DataflowPackage.BOX__OUTPUTS:
                getOutputs().clear();
                getOutputs().addAll((Collection<? extends OutputPort>)newValue);
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
            case DataflowPackage.BOX__INPUTS:
                getInputs().clear();
                return;
            case DataflowPackage.BOX__OUTPUTS:
                getOutputs().clear();
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
            case DataflowPackage.BOX__INPUTS:
                return inputs != null && !inputs.isEmpty();
            case DataflowPackage.BOX__OUTPUTS:
                return outputs != null && !outputs.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //BoxImpl
