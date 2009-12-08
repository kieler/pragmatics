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
package de.cau.cs.kieler.kiml.layout.klayoutdata;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Edge Layout</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * This layout data contains special information for edges, such as bend points.
 * <p>
 * All layout coordinates for edges are defined to be relative to the position
 * of the parent of the source node, except when the target node is contained
 * in the source node, in which case all coordinates are relative to the
 * position of the source node.
 * </p>
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KEdgeLayout#getBendPoints <em>Bend Points</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KEdgeLayout#getSourcePoint <em>Source Point</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KEdgeLayout#getTargetPoint <em>Target Point</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutDataPackage#getKEdgeLayout()
 * @model
 * @generated
 */
public interface KEdgeLayout extends KLayoutData {
    /**
     * Returns the value of the '<em><b>Bend Points</b></em>' containment reference list.
     * The list contents are of type {@link de.cau.cs.kieler.kiml.layout.klayoutdata.KPoint}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The coordinates of bend points must obey the general rules for edge
     * coordinates defined above.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Bend Points</em>' containment reference list.
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutDataPackage#getKEdgeLayout_BendPoints()
     * @model containment="true"
     * @generated
     */
    EList<KPoint> getBendPoints();

    /**
     * Returns the value of the '<em><b>Source Point</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The source point is the point at which the edge touches the source node
     * or source port. The coordinates of source points must obey the general
     * rules for edge coordinates defined above.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Source Point</em>' containment reference.
     * @see #setSourcePoint(KPoint)
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutDataPackage#getKEdgeLayout_SourcePoint()
     * @model containment="true" required="true"
     * @generated
     */
    KPoint getSourcePoint();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KEdgeLayout#getSourcePoint <em>Source Point</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Source Point</em>' containment reference.
     * @see #getSourcePoint()
     * @generated
     */
    void setSourcePoint(KPoint value);

    /**
     * Returns the value of the '<em><b>Target Point</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The target point is the point at which the edge touches the target node
     * or target port. The coordinates of target points must obey the general
     * rules for edge coordinates defined above.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Target Point</em>' containment reference.
     * @see #setTargetPoint(KPoint)
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutDataPackage#getKEdgeLayout_TargetPoint()
     * @model containment="true" required="true"
     * @generated
     */
    KPoint getTargetPoint();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KEdgeLayout#getTargetPoint <em>Target Point</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Target Point</em>' containment reference.
     * @see #getTargetPoint()
     * @generated
     */
    void setTargetPoint(KPoint value);

} // KEdgeLayout
