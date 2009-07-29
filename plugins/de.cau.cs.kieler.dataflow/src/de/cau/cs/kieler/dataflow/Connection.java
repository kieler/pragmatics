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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Connection</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.dataflow.Connection#getSourcePort <em>Source Port</em>}</li>
 *   <li>{@link de.cau.cs.kieler.dataflow.Connection#getTargetPort <em>Target Port</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.dataflow.DataflowPackage#getConnection()
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
	 * @see de.cau.cs.kieler.dataflow.DataflowPackage#getConnection_SourcePort()
	 * @model required="true"
	 * @generated
	 */
    Port getSourcePort();

    /**
	 * Sets the value of the '{@link de.cau.cs.kieler.dataflow.Connection#getSourcePort <em>Source Port</em>}' reference.
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
	 * @see de.cau.cs.kieler.dataflow.DataflowPackage#getConnection_TargetPort()
	 * @model required="true"
	 * @generated
	 */
    Port getTargetPort();

    /**
	 * Sets the value of the '{@link de.cau.cs.kieler.dataflow.Connection#getTargetPort <em>Target Port</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Port</em>' reference.
	 * @see #getTargetPort()
	 * @generated
	 */
    void setTargetPort(Port value);

} // Connection
