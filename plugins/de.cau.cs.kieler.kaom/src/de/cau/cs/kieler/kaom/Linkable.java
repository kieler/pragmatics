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
package de.cau.cs.kieler.kaom;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Linkable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.kaom.Linkable#getOutgoingLinks <em>Outgoing Links</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kaom.Linkable#getIncomingLinks <em>Incoming Links</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kaom.Linkable#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.kaom.KaomPackage#getLinkable()
 * @model abstract="true"
 * @generated
 */
public interface Linkable extends EObject {
    /**
     * Returns the value of the '<em><b>Outgoing Links</b></em>' reference list.
     * The list contents are of type {@link de.cau.cs.kieler.kaom.Link}.
     * It is bidirectional and its opposite is '{@link de.cau.cs.kieler.kaom.Link#getSource <em>Source</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Outgoing Links</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Outgoing Links</em>' reference list.
     * @see de.cau.cs.kieler.kaom.KaomPackage#getLinkable_OutgoingLinks()
     * @see de.cau.cs.kieler.kaom.Link#getSource
     * @model opposite="source"
     * @generated
     */
    EList<Link> getOutgoingLinks();

    /**
     * Returns the value of the '<em><b>Incoming Links</b></em>' reference list.
     * The list contents are of type {@link de.cau.cs.kieler.kaom.Link}.
     * It is bidirectional and its opposite is '{@link de.cau.cs.kieler.kaom.Link#getTarget <em>Target</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Incoming Links</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Incoming Links</em>' reference list.
     * @see de.cau.cs.kieler.kaom.KaomPackage#getLinkable_IncomingLinks()
     * @see de.cau.cs.kieler.kaom.Link#getTarget
     * @model opposite="target"
     * @generated
     */
    EList<Link> getIncomingLinks();

    /**
     * Returns the value of the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Id</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Id</em>' attribute.
     * @see #setId(String)
     * @see de.cau.cs.kieler.kaom.KaomPackage#getLinkable_Id()
     * @model
     * @generated
     */
    String getId();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.kaom.Linkable#getId <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Id</em>' attribute.
     * @see #getId()
     * @generated
     */
    void setId(String value);

} // Linkable
