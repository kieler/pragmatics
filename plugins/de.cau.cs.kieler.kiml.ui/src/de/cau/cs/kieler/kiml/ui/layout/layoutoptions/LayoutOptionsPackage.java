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
package de.cau.cs.kieler.kiml.ui.layout.layoutoptions;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.gmf.runtime.notation.NotationPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see de.cau.cs.kieler.kiml.ui.layout.layoutoptions.LayoutOptionsFactory
 * @model kind="package"
 * @generated
 */
public interface LayoutOptionsPackage extends EPackage {
    /**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "layoutoptions";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://kieler.cs.cau.de/LayoutOptions";

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "layoutoptions";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    LayoutOptionsPackage eINSTANCE = de.cau.cs.kieler.kiml.ui.layout.layoutoptions.LayoutOptionsPackageImpl.init();

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.kiml.ui.layout.layoutoptions.LayoutOptionStyleImpl <em>Layout Option Style</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.kiml.ui.layout.layoutoptions.LayoutOptionStyleImpl
     * @see de.cau.cs.kieler.kiml.ui.layout.layoutoptions.LayoutOptionsPackageImpl#getLayoutOptionStyle()
     * @generated
     */
    int LAYOUT_OPTION_STYLE = 0;

    /**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LAYOUT_OPTION_STYLE__EANNOTATIONS = NotationPackage.STYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Source</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LAYOUT_OPTION_STYLE__SOURCE = NotationPackage.STYLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Details</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LAYOUT_OPTION_STYLE__DETAILS = NotationPackage.STYLE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>EModel Element</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LAYOUT_OPTION_STYLE__EMODEL_ELEMENT = NotationPackage.STYLE_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Contents</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LAYOUT_OPTION_STYLE__CONTENTS = NotationPackage.STYLE_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>References</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LAYOUT_OPTION_STYLE__REFERENCES = NotationPackage.STYLE_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Options</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LAYOUT_OPTION_STYLE__OPTIONS = NotationPackage.STYLE_FEATURE_COUNT + 6;

    /**
     * The number of structural features of the '<em>Layout Option Style</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LAYOUT_OPTION_STYLE_FEATURE_COUNT = NotationPackage.STYLE_FEATURE_COUNT + 7;


    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.kiml.ui.layout.layoutoptions.LayoutOptionStyle <em>Layout Option Style</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Layout Option Style</em>'.
     * @see de.cau.cs.kieler.kiml.ui.layout.layoutoptions.LayoutOptionStyle
     * @generated
     */
    EClass getLayoutOptionStyle();

    /**
     * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.kiml.ui.layout.layoutoptions.LayoutOptionStyle#getOptions <em>Options</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Options</em>'.
     * @see de.cau.cs.kieler.kiml.ui.layout.layoutoptions.LayoutOptionStyle#getOptions()
     * @see #getLayoutOptionStyle()
     * @generated
     */
    EReference getLayoutOptionStyle_Options();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    LayoutOptionsFactory getLayoutOptionsFactory();

    /**
     * <!-- begin-user-doc -->
     * Defines literals for the meta objects that represent
     * <ul>
     *   <li>each class,</li>
     *   <li>each feature of each class,</li>
     *   <li>each enum,</li>
     *   <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     * @generated
     */
    interface Literals {
        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.kiml.ui.layout.layoutoptions.LayoutOptionStyleImpl <em>Layout Option Style</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.kiml.ui.layout.layoutoptions.LayoutOptionStyleImpl
         * @see de.cau.cs.kieler.kiml.ui.layout.layoutoptions.LayoutOptionsPackageImpl#getLayoutOptionStyle()
         * @generated
         */
        EClass LAYOUT_OPTION_STYLE = eINSTANCE.getLayoutOptionStyle();

        /**
         * The meta object literal for the '<em><b>Options</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference LAYOUT_OPTION_STYLE__OPTIONS = eINSTANCE.getLayoutOptionStyle_Options();

    }

} //LayoutOptionsPackage
