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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Input Port</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.dataflow.InputPort#getInputParent <em>Input Parent</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.dataflow.DataflowPackage#getInputPort()
 * @model
 * @generated
 */
public interface InputPort extends Port {
    /**
	 * Returns the value of the '<em><b>Input Parent</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link de.cau.cs.kieler.dataflow.Box#getInputs <em>Inputs</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Input Parent</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Input Parent</em>' container reference.
	 * @see #setInputParent(Box)
	 * @see de.cau.cs.kieler.dataflow.DataflowPackage#getInputPort_InputParent()
	 * @see de.cau.cs.kieler.dataflow.Box#getInputs
	 * @model opposite="inputs" required="true" transient="false"
	 * @generated
	 */
    Box getInputParent();

    /**
	 * Sets the value of the '{@link de.cau.cs.kieler.dataflow.InputPort#getInputParent <em>Input Parent</em>}' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Input Parent</em>' container reference.
	 * @see #getInputParent()
	 * @generated
	 */
    void setInputParent(Box value);

} // InputPort
