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
 * A representation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.dataflow.DataflowModel#getName <em>Name</em>}</li>
 *   <li>{@link de.cau.cs.kieler.dataflow.DataflowModel#getBoxes <em>Boxes</em>}</li>
 *   <li>{@link de.cau.cs.kieler.dataflow.DataflowModel#getConnections <em>Connections</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.dataflow.DataflowPackage#getDataflowModel()
 * @model
 * @generated
 */
public interface DataflowModel extends EObject {
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
     * @see de.cau.cs.kieler.dataflow.DataflowPackage#getDataflowModel_Name()
     * @model
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.dataflow.DataflowModel#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

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
     * @see de.cau.cs.kieler.dataflow.DataflowPackage#getDataflowModel_Boxes()
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
     * @see de.cau.cs.kieler.dataflow.DataflowPackage#getDataflowModel_Connections()
     * @model containment="true"
     * @generated
     */
    EList<Connection> getConnections();

} // DataflowModel
