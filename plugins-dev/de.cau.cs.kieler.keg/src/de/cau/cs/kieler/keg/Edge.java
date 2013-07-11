/**
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.keg;

import de.cau.cs.kieler.core.kgraph.KEdge;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Edge</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.keg.Edge#getHeadLabel <em>Head Label</em>}</li>
 *   <li>{@link de.cau.cs.kieler.keg.Edge#getMidLabel <em>Mid Label</em>}</li>
 *   <li>{@link de.cau.cs.kieler.keg.Edge#getTailLabel <em>Tail Label</em>}</li>
 *   <li>{@link de.cau.cs.kieler.keg.Edge#isDirected <em>Directed</em>}</li>
 *   <li>{@link de.cau.cs.kieler.keg.Edge#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.keg.KEGPackage#getEdge()
 * @model
 * @generated
 */
public interface Edge extends KEdge {
    /**
     * Returns the value of the '<em><b>Head Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Head Label</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Head Label</em>' attribute.
     * @see #setHeadLabel(String)
     * @see de.cau.cs.kieler.keg.KEGPackage#getEdge_HeadLabel()
     * @model
     * @generated
     */
    String getHeadLabel();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.keg.Edge#getHeadLabel <em>Head Label</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Head Label</em>' attribute.
     * @see #getHeadLabel()
     * @generated
     */
    void setHeadLabel(String value);

    /**
     * Returns the value of the '<em><b>Mid Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Mid Label</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Mid Label</em>' attribute.
     * @see #setMidLabel(String)
     * @see de.cau.cs.kieler.keg.KEGPackage#getEdge_MidLabel()
     * @model
     * @generated
     */
    String getMidLabel();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.keg.Edge#getMidLabel <em>Mid Label</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Mid Label</em>' attribute.
     * @see #getMidLabel()
     * @generated
     */
    void setMidLabel(String value);

    /**
     * Returns the value of the '<em><b>Tail Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Tail Label</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Tail Label</em>' attribute.
     * @see #setTailLabel(String)
     * @see de.cau.cs.kieler.keg.KEGPackage#getEdge_TailLabel()
     * @model
     * @generated
     */
    String getTailLabel();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.keg.Edge#getTailLabel <em>Tail Label</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Tail Label</em>' attribute.
     * @see #getTailLabel()
     * @generated
     */
    void setTailLabel(String value);

    /**
     * Returns the value of the '<em><b>Directed</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Directed</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Directed</em>' attribute.
     * @see #setDirected(boolean)
     * @see de.cau.cs.kieler.keg.KEGPackage#getEdge_Directed()
     * @model default="false" required="true"
     * @generated
     */
    boolean isDirected();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.keg.Edge#isDirected <em>Directed</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Directed</em>' attribute.
     * @see #isDirected()
     * @generated
     */
    void setDirected(boolean value);

    /**
     * Returns the value of the '<em><b>Type</b></em>' attribute.
     * The literals are from the enumeration {@link de.cau.cs.kieler.keg.EdgeType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Type</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Type</em>' attribute.
     * @see de.cau.cs.kieler.keg.EdgeType
     * @see #setType(EdgeType)
     * @see de.cau.cs.kieler.keg.KEGPackage#getEdge_Type()
     * @model required="true"
     * @generated
     */
    EdgeType getType();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.keg.Edge#getType <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Type</em>' attribute.
     * @see de.cau.cs.kieler.keg.EdgeType
     * @see #getType()
     * @generated
     */
    void setType(EdgeType value);

} // Edge
