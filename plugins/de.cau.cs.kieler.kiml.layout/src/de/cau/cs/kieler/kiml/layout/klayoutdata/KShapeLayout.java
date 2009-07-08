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
package de.cau.cs.kieler.kiml.layout.klayoutdata;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Shape Layout</b></em>'. This
 * layout data contains information for graph elements for which rectangular
 * shape is assumed for layout, such as nodes, ports, and labels.
 * <p>
 * Layout coordinates for nodes, ports, and node labels are relative to the
 * position of the parent node. For edge labels the rules defined in
 * {@link KEdgeLayout} apply. Port labels are relative to their ports.  
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KShapeLayout#getXpos <em>Xpos</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KShapeLayout#getYpos <em>Ypos</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KShapeLayout#getWidth <em>Width</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KShapeLayout#getHeight <em>Height</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutDataPackage#getKShapeLayout()
 * @model
 * @generated
 */
public interface KShapeLayout extends KLayoutData {
    /**
     * Returns the value of the '<em><b>Xpos</b></em>' attribute.
     * The default value is <code>"0.0f"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Xpos</em>' attribute.
     * @see #setXpos(float)
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutDataPackage#getKShapeLayout_Xpos()
     * @model default="0.0f"
     * @generated
     */
    float getXpos();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KShapeLayout#getXpos <em>Xpos</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Xpos</em>' attribute.
     * @see #getXpos()
     * @generated
     */
    void setXpos(float value);

    /**
     * Returns the value of the '<em><b>Ypos</b></em>' attribute.
     * The default value is <code>"0.0f"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Ypos</em>' attribute.
     * @see #setYpos(float)
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutDataPackage#getKShapeLayout_Ypos()
     * @model default="0.0f"
     * @generated
     */
    float getYpos();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KShapeLayout#getYpos <em>Ypos</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Ypos</em>' attribute.
     * @see #getYpos()
     * @generated
     */
    void setYpos(float value);

    /**
     * Returns the value of the '<em><b>Width</b></em>' attribute.
     * The default value is <code>"0.0f"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Width</em>' attribute.
     * @see #setWidth(float)
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutDataPackage#getKShapeLayout_Width()
     * @model default="0.0f"
     * @generated
     */
    float getWidth();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KShapeLayout#getWidth <em>Width</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Width</em>' attribute.
     * @see #getWidth()
     * @generated
     */
    void setWidth(float value);

    /**
     * Returns the value of the '<em><b>Height</b></em>' attribute.
     * The default value is <code>"0.0f"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Height</em>' attribute.
     * @see #setHeight(float)
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutDataPackage#getKShapeLayout_Height()
     * @model default="0.0f"
     * @generated
     */
    float getHeight();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KShapeLayout#getHeight <em>Height</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Height</em>' attribute.
     * @see #getHeight()
     * @generated
     */
    void setHeight(float value);

} // KShapeLayout
