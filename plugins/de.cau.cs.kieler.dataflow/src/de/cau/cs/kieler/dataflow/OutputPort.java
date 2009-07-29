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
 * A representation of the model object '<em><b>Output Port</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.dataflow.OutputPort#getOutputParent <em>Output Parent</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.dataflow.DataflowPackage#getOutputPort()
 * @model
 * @generated
 */
public interface OutputPort extends Port {
    /**
	 * Returns the value of the '<em><b>Output Parent</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link de.cau.cs.kieler.dataflow.Box#getOutputs <em>Outputs</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Output Parent</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Output Parent</em>' container reference.
	 * @see #setOutputParent(Box)
	 * @see de.cau.cs.kieler.dataflow.DataflowPackage#getOutputPort_OutputParent()
	 * @see de.cau.cs.kieler.dataflow.Box#getOutputs
	 * @model opposite="outputs" required="true" transient="false"
	 * @generated
	 */
    Box getOutputParent();

    /**
	 * Sets the value of the '{@link de.cau.cs.kieler.dataflow.OutputPort#getOutputParent <em>Output Parent</em>}' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Output Parent</em>' container reference.
	 * @see #getOutputParent()
	 * @generated
	 */
    void setOutputParent(Box value);

} // OutputPort
