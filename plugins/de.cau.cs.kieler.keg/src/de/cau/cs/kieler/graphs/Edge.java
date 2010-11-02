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
 *
 * $Id$
 */
package de.cau.cs.kieler.graphs;

import de.cau.cs.kieler.core.kgraph.KEdge;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Edge</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.graphs.Edge#getHeadLabel1 <em>Head Label1</em>}</li>
 *   <li>{@link de.cau.cs.kieler.graphs.Edge#getHeadLabel2 <em>Head Label2</em>}</li>
 *   <li>{@link de.cau.cs.kieler.graphs.Edge#getMidLabel <em>Mid Label</em>}</li>
 *   <li>{@link de.cau.cs.kieler.graphs.Edge#getTailLabel1 <em>Tail Label1</em>}</li>
 *   <li>{@link de.cau.cs.kieler.graphs.Edge#getTailLabel2 <em>Tail Label2</em>}</li>
 *   <li>{@link de.cau.cs.kieler.graphs.Edge#isIsDirected <em>Is Directed</em>}</li>
 *   <li>{@link de.cau.cs.kieler.graphs.Edge#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.graphs.GraphsPackage#getEdge()
 * @model
 * @generated
 */
public interface Edge extends KEdge {
    /**
     * Returns the value of the '<em><b>Head Label1</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Head Label1</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Head Label1</em>' attribute.
     * @see #setHeadLabel1(String)
     * @see de.cau.cs.kieler.graphs.GraphsPackage#getEdge_HeadLabel1()
     * @model
     * @generated
     */
    String getHeadLabel1();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.graphs.Edge#getHeadLabel1 <em>Head Label1</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Head Label1</em>' attribute.
     * @see #getHeadLabel1()
     * @generated
     */
    void setHeadLabel1(String value);

    /**
     * Returns the value of the '<em><b>Head Label2</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Head Label2</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Head Label2</em>' attribute.
     * @see #setHeadLabel2(String)
     * @see de.cau.cs.kieler.graphs.GraphsPackage#getEdge_HeadLabel2()
     * @model
     * @generated
     */
    String getHeadLabel2();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.graphs.Edge#getHeadLabel2 <em>Head Label2</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Head Label2</em>' attribute.
     * @see #getHeadLabel2()
     * @generated
     */
    void setHeadLabel2(String value);

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
     * @see de.cau.cs.kieler.graphs.GraphsPackage#getEdge_MidLabel()
     * @model
     * @generated
     */
    String getMidLabel();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.graphs.Edge#getMidLabel <em>Mid Label</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Mid Label</em>' attribute.
     * @see #getMidLabel()
     * @generated
     */
    void setMidLabel(String value);

    /**
     * Returns the value of the '<em><b>Tail Label1</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Tail Label1</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Tail Label1</em>' attribute.
     * @see #setTailLabel1(String)
     * @see de.cau.cs.kieler.graphs.GraphsPackage#getEdge_TailLabel1()
     * @model
     * @generated
     */
    String getTailLabel1();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.graphs.Edge#getTailLabel1 <em>Tail Label1</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Tail Label1</em>' attribute.
     * @see #getTailLabel1()
     * @generated
     */
    void setTailLabel1(String value);

    /**
     * Returns the value of the '<em><b>Tail Label2</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Tail Label2</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Tail Label2</em>' attribute.
     * @see #setTailLabel2(String)
     * @see de.cau.cs.kieler.graphs.GraphsPackage#getEdge_TailLabel2()
     * @model
     * @generated
     */
    String getTailLabel2();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.graphs.Edge#getTailLabel2 <em>Tail Label2</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Tail Label2</em>' attribute.
     * @see #getTailLabel2()
     * @generated
     */
    void setTailLabel2(String value);

    /**
     * Returns the value of the '<em><b>Is Directed</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Is Directed</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Is Directed</em>' attribute.
     * @see #setIsDirected(boolean)
     * @see de.cau.cs.kieler.graphs.GraphsPackage#getEdge_IsDirected()
     * @model default="false" required="true"
     * @generated
     */
    boolean isIsDirected();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.graphs.Edge#isIsDirected <em>Is Directed</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Is Directed</em>' attribute.
     * @see #isIsDirected()
     * @generated
     */
    void setIsDirected(boolean value);

    /**
     * Returns the value of the '<em><b>Type</b></em>' attribute.
     * The literals are from the enumeration {@link de.cau.cs.kieler.graphs.EdgeType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Type</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Type</em>' attribute.
     * @see de.cau.cs.kieler.graphs.EdgeType
     * @see #setType(EdgeType)
     * @see de.cau.cs.kieler.graphs.GraphsPackage#getEdge_Type()
     * @model required="true"
     * @generated
     */
    EdgeType getType();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.graphs.Edge#getType <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Type</em>' attribute.
     * @see de.cau.cs.kieler.graphs.EdgeType
     * @see #getType()
     * @generated
     */
    void setType(EdgeType value);

} // Edge
