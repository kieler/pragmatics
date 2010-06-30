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

import de.cau.cs.kieler.core.kgraph.KGraphPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

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
 * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutDataFactory
 * @model kind="package"
 * @generated
 */
public interface KLayoutDataPackage extends EPackage {
    /**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "klayoutdata";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://kieler.cs.cau.de/KLayoutData";

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "klayoutdata";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    KLayoutDataPackage eINSTANCE = de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KLayoutDataPackageImpl.init();

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KLayoutDataImpl <em>KLayout Data</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KLayoutDataImpl
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KLayoutDataPackageImpl#getKLayoutData()
     * @generated
     */
    int KLAYOUT_DATA = 0;

    /**
     * The feature id for the '<em><b>Options</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KLAYOUT_DATA__OPTIONS = KGraphPackage.KGRAPH_DATA_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>KLayout Data</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KLAYOUT_DATA_FEATURE_COUNT = KGraphPackage.KGRAPH_DATA_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KShapeLayoutImpl <em>KShape Layout</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KShapeLayoutImpl
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KLayoutDataPackageImpl#getKShapeLayout()
     * @generated
     */
    int KSHAPE_LAYOUT = 1;

    /**
     * The feature id for the '<em><b>Options</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSHAPE_LAYOUT__OPTIONS = KLAYOUT_DATA__OPTIONS;

    /**
     * The feature id for the '<em><b>Xpos</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSHAPE_LAYOUT__XPOS = KLAYOUT_DATA_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Ypos</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSHAPE_LAYOUT__YPOS = KLAYOUT_DATA_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSHAPE_LAYOUT__WIDTH = KLAYOUT_DATA_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Height</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSHAPE_LAYOUT__HEIGHT = KLAYOUT_DATA_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>KShape Layout</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSHAPE_LAYOUT_FEATURE_COUNT = KLAYOUT_DATA_FEATURE_COUNT + 4;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KEdgeLayoutImpl <em>KEdge Layout</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KEdgeLayoutImpl
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KLayoutDataPackageImpl#getKEdgeLayout()
     * @generated
     */
    int KEDGE_LAYOUT = 2;

    /**
     * The feature id for the '<em><b>Options</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KEDGE_LAYOUT__OPTIONS = KLAYOUT_DATA__OPTIONS;

    /**
     * The feature id for the '<em><b>Bend Points</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KEDGE_LAYOUT__BEND_POINTS = KLAYOUT_DATA_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Source Point</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KEDGE_LAYOUT__SOURCE_POINT = KLAYOUT_DATA_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Target Point</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KEDGE_LAYOUT__TARGET_POINT = KLAYOUT_DATA_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>KEdge Layout</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KEDGE_LAYOUT_FEATURE_COUNT = KLAYOUT_DATA_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KPointImpl <em>KPoint</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KPointImpl
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KLayoutDataPackageImpl#getKPoint()
     * @generated
     */
    int KPOINT = 3;

    /**
     * The feature id for the '<em><b>X</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KPOINT__X = 0;

    /**
     * The feature id for the '<em><b>Y</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KPOINT__Y = 1;

    /**
     * The number of structural features of the '<em>KPoint</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KPOINT_FEATURE_COUNT = 2;


    /**
     * The meta object id for the '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KOptionImpl <em>KOption</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KOptionImpl
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KLayoutDataPackageImpl#getKOption()
     * @generated
     */
    int KOPTION = 4;

    /**
     * The feature id for the '<em><b>Key</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KOPTION__KEY = 0;

    /**
     * The feature id for the '<em><b>Default</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KOPTION__DEFAULT = 1;

    /**
     * The number of structural features of the '<em>KOption</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KOPTION_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KStringOptionImpl <em>KString Option</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KStringOptionImpl
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KLayoutDataPackageImpl#getKStringOption()
     * @generated
     */
    int KSTRING_OPTION = 5;

    /**
     * The feature id for the '<em><b>Key</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSTRING_OPTION__KEY = KOPTION__KEY;

    /**
     * The feature id for the '<em><b>Default</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSTRING_OPTION__DEFAULT = KOPTION__DEFAULT;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSTRING_OPTION__VALUE = KOPTION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>KString Option</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSTRING_OPTION_FEATURE_COUNT = KOPTION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KIntOptionImpl <em>KInt Option</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KIntOptionImpl
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KLayoutDataPackageImpl#getKIntOption()
     * @generated
     */
    int KINT_OPTION = 6;

    /**
     * The feature id for the '<em><b>Key</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KINT_OPTION__KEY = KOPTION__KEY;

    /**
     * The feature id for the '<em><b>Default</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KINT_OPTION__DEFAULT = KOPTION__DEFAULT;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KINT_OPTION__VALUE = KOPTION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>KInt Option</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KINT_OPTION_FEATURE_COUNT = KOPTION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KBooleanOptionImpl <em>KBoolean Option</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KBooleanOptionImpl
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KLayoutDataPackageImpl#getKBooleanOption()
     * @generated
     */
    int KBOOLEAN_OPTION = 7;

    /**
     * The feature id for the '<em><b>Key</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KBOOLEAN_OPTION__KEY = KOPTION__KEY;

    /**
     * The feature id for the '<em><b>Default</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KBOOLEAN_OPTION__DEFAULT = KOPTION__DEFAULT;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KBOOLEAN_OPTION__VALUE = KOPTION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>KBoolean Option</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KBOOLEAN_OPTION_FEATURE_COUNT = KOPTION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KFloatOptionImpl <em>KFloat Option</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KFloatOptionImpl
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KLayoutDataPackageImpl#getKFloatOption()
     * @generated
     */
    int KFLOAT_OPTION = 8;

    /**
     * The feature id for the '<em><b>Key</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KFLOAT_OPTION__KEY = KOPTION__KEY;

    /**
     * The feature id for the '<em><b>Default</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KFLOAT_OPTION__DEFAULT = KOPTION__DEFAULT;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KFLOAT_OPTION__VALUE = KOPTION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>KFloat Option</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KFLOAT_OPTION_FEATURE_COUNT = KOPTION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KObjectOptionImpl <em>KObject Option</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KObjectOptionImpl
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KLayoutDataPackageImpl#getKObjectOption()
     * @generated
     */
    int KOBJECT_OPTION = 9;

    /**
     * The feature id for the '<em><b>Key</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KOBJECT_OPTION__KEY = KOPTION__KEY;

    /**
     * The feature id for the '<em><b>Default</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KOBJECT_OPTION__DEFAULT = KOPTION__DEFAULT;

    /**
     * The feature id for the '<em><b>Value</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KOBJECT_OPTION__VALUE = KOPTION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>KObject Option</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KOBJECT_OPTION_FEATURE_COUNT = KOPTION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KInsetsImpl <em>KInsets</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KInsetsImpl
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KLayoutDataPackageImpl#getKInsets()
     * @generated
     */
    int KINSETS = 10;

    /**
     * The feature id for the '<em><b>Top</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KINSETS__TOP = 0;

    /**
     * The feature id for the '<em><b>Bottom</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KINSETS__BOTTOM = 1;

    /**
     * The feature id for the '<em><b>Left</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KINSETS__LEFT = 2;

    /**
     * The feature id for the '<em><b>Right</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KINSETS__RIGHT = 3;

    /**
     * The number of structural features of the '<em>KInsets</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KINSETS_FEATURE_COUNT = 4;


    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutData <em>KLayout Data</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KLayout Data</em>'.
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutData
     * @generated
     */
    EClass getKLayoutData();

    /**
     * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutData#getOptions <em>Options</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Options</em>'.
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutData#getOptions()
     * @see #getKLayoutData()
     * @generated
     */
    EReference getKLayoutData_Options();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KShapeLayout <em>KShape Layout</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KShape Layout</em>'.
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KShapeLayout
     * @generated
     */
    EClass getKShapeLayout();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KShapeLayout#getXpos <em>Xpos</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Xpos</em>'.
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KShapeLayout#getXpos()
     * @see #getKShapeLayout()
     * @generated
     */
    EAttribute getKShapeLayout_Xpos();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KShapeLayout#getYpos <em>Ypos</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Ypos</em>'.
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KShapeLayout#getYpos()
     * @see #getKShapeLayout()
     * @generated
     */
    EAttribute getKShapeLayout_Ypos();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KShapeLayout#getWidth <em>Width</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Width</em>'.
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KShapeLayout#getWidth()
     * @see #getKShapeLayout()
     * @generated
     */
    EAttribute getKShapeLayout_Width();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KShapeLayout#getHeight <em>Height</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Height</em>'.
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KShapeLayout#getHeight()
     * @see #getKShapeLayout()
     * @generated
     */
    EAttribute getKShapeLayout_Height();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KEdgeLayout <em>KEdge Layout</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KEdge Layout</em>'.
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KEdgeLayout
     * @generated
     */
    EClass getKEdgeLayout();

    /**
     * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KEdgeLayout#getBendPoints <em>Bend Points</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Bend Points</em>'.
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KEdgeLayout#getBendPoints()
     * @see #getKEdgeLayout()
     * @generated
     */
    EReference getKEdgeLayout_BendPoints();

    /**
     * Returns the meta object for the containment reference '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KEdgeLayout#getSourcePoint <em>Source Point</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Source Point</em>'.
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KEdgeLayout#getSourcePoint()
     * @see #getKEdgeLayout()
     * @generated
     */
    EReference getKEdgeLayout_SourcePoint();

    /**
     * Returns the meta object for the containment reference '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KEdgeLayout#getTargetPoint <em>Target Point</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Target Point</em>'.
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KEdgeLayout#getTargetPoint()
     * @see #getKEdgeLayout()
     * @generated
     */
    EReference getKEdgeLayout_TargetPoint();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KPoint <em>KPoint</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KPoint</em>'.
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KPoint
     * @generated
     */
    EClass getKPoint();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KPoint#getX <em>X</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>X</em>'.
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KPoint#getX()
     * @see #getKPoint()
     * @generated
     */
    EAttribute getKPoint_X();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KPoint#getY <em>Y</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Y</em>'.
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KPoint#getY()
     * @see #getKPoint()
     * @generated
     */
    EAttribute getKPoint_Y();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KOption <em>KOption</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KOption</em>'.
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KOption
     * @generated
     */
    EClass getKOption();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KOption#getKey <em>Key</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Key</em>'.
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KOption#getKey()
     * @see #getKOption()
     * @generated
     */
    EAttribute getKOption_Key();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KOption#isDefault <em>Default</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Default</em>'.
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KOption#isDefault()
     * @see #getKOption()
     * @generated
     */
    EAttribute getKOption_Default();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KStringOption <em>KString Option</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KString Option</em>'.
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KStringOption
     * @generated
     */
    EClass getKStringOption();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KStringOption#getValue <em>Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KStringOption#getValue()
     * @see #getKStringOption()
     * @generated
     */
    EAttribute getKStringOption_Value();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KIntOption <em>KInt Option</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KInt Option</em>'.
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KIntOption
     * @generated
     */
    EClass getKIntOption();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KIntOption#getValue <em>Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KIntOption#getValue()
     * @see #getKIntOption()
     * @generated
     */
    EAttribute getKIntOption_Value();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KBooleanOption <em>KBoolean Option</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KBoolean Option</em>'.
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KBooleanOption
     * @generated
     */
    EClass getKBooleanOption();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KBooleanOption#isValue <em>Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KBooleanOption#isValue()
     * @see #getKBooleanOption()
     * @generated
     */
    EAttribute getKBooleanOption_Value();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KFloatOption <em>KFloat Option</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KFloat Option</em>'.
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KFloatOption
     * @generated
     */
    EClass getKFloatOption();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KFloatOption#getValue <em>Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KFloatOption#getValue()
     * @see #getKFloatOption()
     * @generated
     */
    EAttribute getKFloatOption_Value();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KObjectOption <em>KObject Option</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KObject Option</em>'.
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KObjectOption
     * @generated
     */
    EClass getKObjectOption();

    /**
     * Returns the meta object for the containment reference '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KObjectOption#getValue <em>Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Value</em>'.
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KObjectOption#getValue()
     * @see #getKObjectOption()
     * @generated
     */
    EReference getKObjectOption_Value();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KInsets <em>KInsets</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KInsets</em>'.
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KInsets
     * @generated
     */
    EClass getKInsets();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KInsets#getTop <em>Top</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Top</em>'.
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KInsets#getTop()
     * @see #getKInsets()
     * @generated
     */
    EAttribute getKInsets_Top();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KInsets#getBottom <em>Bottom</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Bottom</em>'.
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KInsets#getBottom()
     * @see #getKInsets()
     * @generated
     */
    EAttribute getKInsets_Bottom();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KInsets#getLeft <em>Left</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Left</em>'.
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KInsets#getLeft()
     * @see #getKInsets()
     * @generated
     */
    EAttribute getKInsets_Left();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KInsets#getRight <em>Right</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Right</em>'.
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KInsets#getRight()
     * @see #getKInsets()
     * @generated
     */
    EAttribute getKInsets_Right();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    KLayoutDataFactory getKLayoutDataFactory();

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
         * The meta object literal for the '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KLayoutDataImpl <em>KLayout Data</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KLayoutDataImpl
         * @see de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KLayoutDataPackageImpl#getKLayoutData()
         * @generated
         */
        EClass KLAYOUT_DATA = eINSTANCE.getKLayoutData();

        /**
         * The meta object literal for the '<em><b>Options</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference KLAYOUT_DATA__OPTIONS = eINSTANCE.getKLayoutData_Options();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KShapeLayoutImpl <em>KShape Layout</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KShapeLayoutImpl
         * @see de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KLayoutDataPackageImpl#getKShapeLayout()
         * @generated
         */
        EClass KSHAPE_LAYOUT = eINSTANCE.getKShapeLayout();

        /**
         * The meta object literal for the '<em><b>Xpos</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KSHAPE_LAYOUT__XPOS = eINSTANCE.getKShapeLayout_Xpos();

        /**
         * The meta object literal for the '<em><b>Ypos</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KSHAPE_LAYOUT__YPOS = eINSTANCE.getKShapeLayout_Ypos();

        /**
         * The meta object literal for the '<em><b>Width</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KSHAPE_LAYOUT__WIDTH = eINSTANCE.getKShapeLayout_Width();

        /**
         * The meta object literal for the '<em><b>Height</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KSHAPE_LAYOUT__HEIGHT = eINSTANCE.getKShapeLayout_Height();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KEdgeLayoutImpl <em>KEdge Layout</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KEdgeLayoutImpl
         * @see de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KLayoutDataPackageImpl#getKEdgeLayout()
         * @generated
         */
        EClass KEDGE_LAYOUT = eINSTANCE.getKEdgeLayout();

        /**
         * The meta object literal for the '<em><b>Bend Points</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference KEDGE_LAYOUT__BEND_POINTS = eINSTANCE.getKEdgeLayout_BendPoints();

        /**
         * The meta object literal for the '<em><b>Source Point</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference KEDGE_LAYOUT__SOURCE_POINT = eINSTANCE.getKEdgeLayout_SourcePoint();

        /**
         * The meta object literal for the '<em><b>Target Point</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference KEDGE_LAYOUT__TARGET_POINT = eINSTANCE.getKEdgeLayout_TargetPoint();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KPointImpl <em>KPoint</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KPointImpl
         * @see de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KLayoutDataPackageImpl#getKPoint()
         * @generated
         */
        EClass KPOINT = eINSTANCE.getKPoint();

        /**
         * The meta object literal for the '<em><b>X</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KPOINT__X = eINSTANCE.getKPoint_X();

        /**
         * The meta object literal for the '<em><b>Y</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KPOINT__Y = eINSTANCE.getKPoint_Y();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KOptionImpl <em>KOption</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KOptionImpl
         * @see de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KLayoutDataPackageImpl#getKOption()
         * @generated
         */
        EClass KOPTION = eINSTANCE.getKOption();

        /**
         * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KOPTION__KEY = eINSTANCE.getKOption_Key();

        /**
         * The meta object literal for the '<em><b>Default</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KOPTION__DEFAULT = eINSTANCE.getKOption_Default();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KStringOptionImpl <em>KString Option</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KStringOptionImpl
         * @see de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KLayoutDataPackageImpl#getKStringOption()
         * @generated
         */
        EClass KSTRING_OPTION = eINSTANCE.getKStringOption();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KSTRING_OPTION__VALUE = eINSTANCE.getKStringOption_Value();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KIntOptionImpl <em>KInt Option</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KIntOptionImpl
         * @see de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KLayoutDataPackageImpl#getKIntOption()
         * @generated
         */
        EClass KINT_OPTION = eINSTANCE.getKIntOption();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KINT_OPTION__VALUE = eINSTANCE.getKIntOption_Value();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KBooleanOptionImpl <em>KBoolean Option</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KBooleanOptionImpl
         * @see de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KLayoutDataPackageImpl#getKBooleanOption()
         * @generated
         */
        EClass KBOOLEAN_OPTION = eINSTANCE.getKBooleanOption();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KBOOLEAN_OPTION__VALUE = eINSTANCE.getKBooleanOption_Value();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KFloatOptionImpl <em>KFloat Option</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KFloatOptionImpl
         * @see de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KLayoutDataPackageImpl#getKFloatOption()
         * @generated
         */
        EClass KFLOAT_OPTION = eINSTANCE.getKFloatOption();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KFLOAT_OPTION__VALUE = eINSTANCE.getKFloatOption_Value();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KObjectOptionImpl <em>KObject Option</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KObjectOptionImpl
         * @see de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KLayoutDataPackageImpl#getKObjectOption()
         * @generated
         */
        EClass KOBJECT_OPTION = eINSTANCE.getKObjectOption();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference KOBJECT_OPTION__VALUE = eINSTANCE.getKObjectOption_Value();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KInsetsImpl <em>KInsets</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KInsetsImpl
         * @see de.cau.cs.kieler.kiml.layout.klayoutdata.impl.KLayoutDataPackageImpl#getKInsets()
         * @generated
         */
        EClass KINSETS = eINSTANCE.getKInsets();

        /**
         * The meta object literal for the '<em><b>Top</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KINSETS__TOP = eINSTANCE.getKInsets_Top();

        /**
         * The meta object literal for the '<em><b>Bottom</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KINSETS__BOTTOM = eINSTANCE.getKInsets_Bottom();

        /**
         * The meta object literal for the '<em><b>Left</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KINSETS__LEFT = eINSTANCE.getKInsets_Left();

        /**
         * The meta object literal for the '<em><b>Right</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KINSETS__RIGHT = eINSTANCE.getKInsets_Right();

    }

} //KLayoutDataPackage
