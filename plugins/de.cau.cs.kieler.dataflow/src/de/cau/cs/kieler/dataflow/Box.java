/**
 * KIELER - Kiel Integrated Environment for Layout for the Eclipse RCP
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
package de.cau.cs.kieler.dataflow;

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
 *   <li>{@link de.cau.cs.kieler.dataflow.Box#getName <em>Name</em>}</li>
 *   <li>{@link de.cau.cs.kieler.dataflow.Box#getInputs <em>Inputs</em>}</li>
 *   <li>{@link de.cau.cs.kieler.dataflow.Box#getOutputs <em>Outputs</em>}</li>
 *   <li>{@link de.cau.cs.kieler.dataflow.Box#getBoxes <em>Boxes</em>}</li>
 *   <li>{@link de.cau.cs.kieler.dataflow.Box#getConnections <em>Connections</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.dataflow.DataflowPackage#getBox()
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
	 * @see de.cau.cs.kieler.dataflow.DataflowPackage#getBox_Name()
	 * @model
	 * @generated
	 */
    String getName();

    /**
	 * Sets the value of the '{@link de.cau.cs.kieler.dataflow.Box#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
    void setName(String value);

    /**
	 * Returns the value of the '<em><b>Inputs</b></em>' containment reference list.
	 * The list contents are of type {@link de.cau.cs.kieler.dataflow.InputPort}.
	 * It is bidirectional and its opposite is '{@link de.cau.cs.kieler.dataflow.InputPort#getInputParent <em>Input Parent</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Inputs</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Inputs</em>' containment reference list.
	 * @see de.cau.cs.kieler.dataflow.DataflowPackage#getBox_Inputs()
	 * @see de.cau.cs.kieler.dataflow.InputPort#getInputParent
	 * @model opposite="inputParent" containment="true"
	 * @generated
	 */
    EList<InputPort> getInputs();

    /**
	 * Returns the value of the '<em><b>Outputs</b></em>' containment reference list.
	 * The list contents are of type {@link de.cau.cs.kieler.dataflow.OutputPort}.
	 * It is bidirectional and its opposite is '{@link de.cau.cs.kieler.dataflow.OutputPort#getOutputParent <em>Output Parent</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Outputs</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Outputs</em>' containment reference list.
	 * @see de.cau.cs.kieler.dataflow.DataflowPackage#getBox_Outputs()
	 * @see de.cau.cs.kieler.dataflow.OutputPort#getOutputParent
	 * @model opposite="outputParent" containment="true"
	 * @generated
	 */
    EList<OutputPort> getOutputs();

    /**
	 * Returns the value of the '<em><b>Boxes</b></em>' containment reference list.
	 * The list contents are of type {@link de.cau.cs.kieler.dataflow.Box}.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Boxes</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Boxes</em>' containment reference list.
	 * @see de.cau.cs.kieler.dataflow.DataflowPackage#getBox_Boxes()
	 * @model containment="true"
	 * @generated
	 */
    EList<Box> getBoxes();

    /**
	 * Returns the value of the '<em><b>Connections</b></em>' containment reference list.
	 * The list contents are of type {@link de.cau.cs.kieler.dataflow.Connection}.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Connections</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Connections</em>' containment reference list.
	 * @see de.cau.cs.kieler.dataflow.DataflowPackage#getBox_Connections()
	 * @model containment="true"
	 * @generated
	 */
    EList<Connection> getConnections();

} // Box
