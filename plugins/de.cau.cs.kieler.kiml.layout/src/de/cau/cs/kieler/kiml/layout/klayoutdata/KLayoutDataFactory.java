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

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutDataPackage
 * @generated
 */
public interface KLayoutDataFactory extends EFactory {
    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    KLayoutDataFactory eINSTANCE = de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KLayoutDataFactoryImpl.init();

    /**
     * Returns a new object of class '<em>KShape Layout</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KShape Layout</em>'.
     * @generated
     */
    KShapeLayout createKShapeLayout();

    /**
     * Returns a new object of class '<em>KEdge Layout</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KEdge Layout</em>'.
     * @generated
     */
    KEdgeLayout createKEdgeLayout();

    /**
     * Returns a new object of class '<em>KPoint</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KPoint</em>'.
     * @generated
     */
    KPoint createKPoint();

    /**
     * Returns a new object of class '<em>KString Option</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KString Option</em>'.
     * @generated
     */
    KStringOption createKStringOption();

    /**
     * Returns a new object of class '<em>KInt Option</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KInt Option</em>'.
     * @generated
     */
    KIntOption createKIntOption();

    /**
     * Returns a new object of class '<em>KBoolean Option</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KBoolean Option</em>'.
     * @generated
     */
    KBooleanOption createKBooleanOption();

    /**
     * Returns a new object of class '<em>KFloat Option</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KFloat Option</em>'.
     * @generated
     */
    KFloatOption createKFloatOption();

    /**
     * Returns a new object of class '<em>KObject Option</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KObject Option</em>'.
     * @generated
     */
    KObjectOption createKObjectOption();

    /**
     * Returns a new object of class '<em>KInsets</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KInsets</em>'.
     * @generated
     */
    KInsets createKInsets();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    KLayoutDataPackage getKLayoutDataPackage();

} //KLayoutDataFactory
