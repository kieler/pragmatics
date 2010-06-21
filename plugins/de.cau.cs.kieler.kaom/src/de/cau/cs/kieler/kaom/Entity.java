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

import de.cau.cs.kieler.core.annotations.NamedObject;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Entity</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.kaom.Entity#getChildEntities <em>Child Entities</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kaom.Entity#getChildLinks <em>Child Links</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kaom.Entity#getChildPorts <em>Child Ports</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kaom.Entity#getChildRelations <em>Child Relations</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.kaom.KaomPackage#getEntity()
 * @model
 * @generated
 */
public interface Entity extends NamedObject, Linkable {
    /**
     * Returns the value of the '<em><b>Child Entities</b></em>' containment reference list.
     * The list contents are of type {@link de.cau.cs.kieler.kaom.Entity}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Child Entities</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Child Entities</em>' containment reference list.
     * @see de.cau.cs.kieler.kaom.KaomPackage#getEntity_ChildEntities()
     * @model containment="true"
     * @generated
     */
    EList<Entity> getChildEntities();

    /**
     * Returns the value of the '<em><b>Child Links</b></em>' containment reference list.
     * The list contents are of type {@link de.cau.cs.kieler.kaom.Link}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Child Links</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Child Links</em>' containment reference list.
     * @see de.cau.cs.kieler.kaom.KaomPackage#getEntity_ChildLinks()
     * @model containment="true"
     * @generated
     */
    EList<Link> getChildLinks();

    /**
     * Returns the value of the '<em><b>Child Ports</b></em>' containment reference list.
     * The list contents are of type {@link de.cau.cs.kieler.kaom.Port}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Child Ports</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Child Ports</em>' containment reference list.
     * @see de.cau.cs.kieler.kaom.KaomPackage#getEntity_ChildPorts()
     * @model containment="true"
     * @generated
     */
    EList<Port> getChildPorts();

    /**
     * Returns the value of the '<em><b>Child Relations</b></em>' containment reference list.
     * The list contents are of type {@link de.cau.cs.kieler.kaom.Relation}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Child Relations</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Child Relations</em>' containment reference list.
     * @see de.cau.cs.kieler.kaom.KaomPackage#getEntity_ChildRelations()
     * @model containment="true"
     * @generated
     */
    EList<Relation> getChildRelations();

} // Entity
