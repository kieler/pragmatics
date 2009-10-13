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
package de.cau.cs.kieler.dataflow;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Box</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.dataflow.Box#getInputs <em>Inputs</em>}</li>
 *   <li>{@link de.cau.cs.kieler.dataflow.Box#getOutputs <em>Outputs</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.dataflow.DataflowPackage#getBox()
 * @model
 * @generated
 */
public interface Box extends DataflowModel {
    /**
     * Returns the value of the '<em><b>Inputs</b></em>' containment reference list.
     * The list contents are of type {@link de.cau.cs.kieler.dataflow.InputPort}.
     * It is bidirectional and its opposite is '{@link de.cau.cs.kieler.dataflow.InputPort#getParentBox <em>Parent Box</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Inputs</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Inputs</em>' containment reference list.
     * @see de.cau.cs.kieler.dataflow.DataflowPackage#getBox_Inputs()
     * @see de.cau.cs.kieler.dataflow.InputPort#getParentBox
     * @model opposite="parentBox" containment="true"
     * @generated
     */
    EList<InputPort> getInputs();

    /**
     * Returns the value of the '<em><b>Outputs</b></em>' containment reference list.
     * The list contents are of type {@link de.cau.cs.kieler.dataflow.OutputPort}.
     * It is bidirectional and its opposite is '{@link de.cau.cs.kieler.dataflow.OutputPort#getParentBox <em>Parent Box</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Outputs</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Outputs</em>' containment reference list.
     * @see de.cau.cs.kieler.dataflow.DataflowPackage#getBox_Outputs()
     * @see de.cau.cs.kieler.dataflow.OutputPort#getParentBox
     * @model opposite="parentBox" containment="true"
     * @generated
     */
    EList<OutputPort> getOutputs();

} // Box
