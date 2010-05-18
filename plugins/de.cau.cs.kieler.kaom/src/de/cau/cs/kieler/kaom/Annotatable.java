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

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Annotatable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.kaom.Annotatable#getAnnotationMap <em>Annotation Map</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.kaom.KaomPackage#getAnnotatable()
 * @model abstract="true"
 * @generated
 */
public interface Annotatable extends EObject {
    /**
     * Returns the value of the '<em><b>Annotation Map</b></em>' map.
     * The key is of type {@link java.lang.String},
     * and the value is of type {@link de.cau.cs.kieler.kaom.Annotation},
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Annotation Map</em>' map isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Annotation Map</em>' map.
     * @see de.cau.cs.kieler.kaom.KaomPackage#getAnnotatable_AnnotationMap()
     * @model mapType="de.cau.cs.kieler.kaom.AnnotationMapEntry<org.eclipse.emf.ecore.EString, de.cau.cs.kieler.kaom.Annotation>"
     * @generated
     */
    EMap<String, Annotation> getAnnotationMap();

} // Annotatable
