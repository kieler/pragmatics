/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.cs.kieler.kwebs.kstatistics;

import de.cau.cs.kieler.core.kgraph.KGraphPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
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
 * @see de.cau.cs.kieler.kwebs.kstatistics.KStatisticsFactory
 * @model kind="package"
 * @generated
 */
public interface KStatisticsPackage extends EPackage {
    /**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "kstatistics";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://kieler.cs.cau.de/2011-08-30/KStatistics/1.0";

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "kstatistics";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    KStatisticsPackage eINSTANCE = de.cau.cs.kieler.kwebs.kstatistics.impl.KStatisticsPackageImpl.init();

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.kwebs.kstatistics.impl.KStatisticsImpl <em>KStatistics</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.kwebs.kstatistics.impl.KStatisticsImpl
     * @see de.cau.cs.kieler.kwebs.kstatistics.impl.KStatisticsPackageImpl#getKStatistics()
     * @generated
     */
    int KSTATISTICS = 0;

    /**
     * The feature id for the '<em><b>Properties</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSTATISTICS__PROPERTIES = KGraphPackage.KGRAPH_DATA__PROPERTIES;

    /**
     * The feature id for the '<em><b>Persistent Entries</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSTATISTICS__PERSISTENT_ENTRIES = KGraphPackage.KGRAPH_DATA__PERSISTENT_ENTRIES;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSTATISTICS__TYPE = KGraphPackage.KGRAPH_DATA_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Bytes</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSTATISTICS__BYTES = KGraphPackage.KGRAPH_DATA_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Compression</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSTATISTICS__COMPRESSION = KGraphPackage.KGRAPH_DATA_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Edges</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSTATISTICS__EDGES = KGraphPackage.KGRAPH_DATA_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Nodes</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSTATISTICS__NODES = KGraphPackage.KGRAPH_DATA_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Ports</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSTATISTICS__PORTS = KGraphPackage.KGRAPH_DATA_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Labels</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSTATISTICS__LABELS = KGraphPackage.KGRAPH_DATA_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Time Total</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSTATISTICS__TIME_TOTAL = KGraphPackage.KGRAPH_DATA_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Time Network</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSTATISTICS__TIME_NETWORK = KGraphPackage.KGRAPH_DATA_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Time Layout</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSTATISTICS__TIME_LAYOUT = KGraphPackage.KGRAPH_DATA_FEATURE_COUNT + 9;

    /**
     * The feature id for the '<em><b>Time Local Supplemental</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSTATISTICS__TIME_LOCAL_SUPPLEMENTAL = KGraphPackage.KGRAPH_DATA_FEATURE_COUNT + 10;

    /**
     * The feature id for the '<em><b>Time Remote Supplemental</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSTATISTICS__TIME_REMOTE_SUPPLEMENTAL = KGraphPackage.KGRAPH_DATA_FEATURE_COUNT + 11;

    /**
     * The number of structural features of the '<em>KStatistics</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSTATISTICS_FEATURE_COUNT = KGraphPackage.KGRAPH_DATA_FEATURE_COUNT + 12;


    /**
     * The meta object id for the '{@link de.cau.cs.kieler.kwebs.kstatistics.LayoutType <em>Layout Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.kwebs.kstatistics.LayoutType
     * @see de.cau.cs.kieler.kwebs.kstatistics.impl.KStatisticsPackageImpl#getLayoutType()
     * @generated
     */
    int LAYOUT_TYPE = 1;


    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.kwebs.kstatistics.KStatistics <em>KStatistics</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KStatistics</em>'.
     * @see de.cau.cs.kieler.kwebs.kstatistics.KStatistics
     * @generated
     */
    EClass getKStatistics();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kwebs.kstatistics.KStatistics#getType <em>Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Type</em>'.
     * @see de.cau.cs.kieler.kwebs.kstatistics.KStatistics#getType()
     * @see #getKStatistics()
     * @generated
     */
    EAttribute getKStatistics_Type();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kwebs.kstatistics.KStatistics#getBytes <em>Bytes</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Bytes</em>'.
     * @see de.cau.cs.kieler.kwebs.kstatistics.KStatistics#getBytes()
     * @see #getKStatistics()
     * @generated
     */
    EAttribute getKStatistics_Bytes();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kwebs.kstatistics.KStatistics#isCompression <em>Compression</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Compression</em>'.
     * @see de.cau.cs.kieler.kwebs.kstatistics.KStatistics#isCompression()
     * @see #getKStatistics()
     * @generated
     */
    EAttribute getKStatistics_Compression();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kwebs.kstatistics.KStatistics#getEdges <em>Edges</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Edges</em>'.
     * @see de.cau.cs.kieler.kwebs.kstatistics.KStatistics#getEdges()
     * @see #getKStatistics()
     * @generated
     */
    EAttribute getKStatistics_Edges();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kwebs.kstatistics.KStatistics#getNodes <em>Nodes</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Nodes</em>'.
     * @see de.cau.cs.kieler.kwebs.kstatistics.KStatistics#getNodes()
     * @see #getKStatistics()
     * @generated
     */
    EAttribute getKStatistics_Nodes();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kwebs.kstatistics.KStatistics#getPorts <em>Ports</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Ports</em>'.
     * @see de.cau.cs.kieler.kwebs.kstatistics.KStatistics#getPorts()
     * @see #getKStatistics()
     * @generated
     */
    EAttribute getKStatistics_Ports();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kwebs.kstatistics.KStatistics#getLabels <em>Labels</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Labels</em>'.
     * @see de.cau.cs.kieler.kwebs.kstatistics.KStatistics#getLabels()
     * @see #getKStatistics()
     * @generated
     */
    EAttribute getKStatistics_Labels();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kwebs.kstatistics.KStatistics#getTimeTotal <em>Time Total</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Time Total</em>'.
     * @see de.cau.cs.kieler.kwebs.kstatistics.KStatistics#getTimeTotal()
     * @see #getKStatistics()
     * @generated
     */
    EAttribute getKStatistics_TimeTotal();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kwebs.kstatistics.KStatistics#getTimeNetwork <em>Time Network</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Time Network</em>'.
     * @see de.cau.cs.kieler.kwebs.kstatistics.KStatistics#getTimeNetwork()
     * @see #getKStatistics()
     * @generated
     */
    EAttribute getKStatistics_TimeNetwork();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kwebs.kstatistics.KStatistics#getTimeLayout <em>Time Layout</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Time Layout</em>'.
     * @see de.cau.cs.kieler.kwebs.kstatistics.KStatistics#getTimeLayout()
     * @see #getKStatistics()
     * @generated
     */
    EAttribute getKStatistics_TimeLayout();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kwebs.kstatistics.KStatistics#getTimeLocalSupplemental <em>Time Local Supplemental</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Time Local Supplemental</em>'.
     * @see de.cau.cs.kieler.kwebs.kstatistics.KStatistics#getTimeLocalSupplemental()
     * @see #getKStatistics()
     * @generated
     */
    EAttribute getKStatistics_TimeLocalSupplemental();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kwebs.kstatistics.KStatistics#getTimeRemoteSupplemental <em>Time Remote Supplemental</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Time Remote Supplemental</em>'.
     * @see de.cau.cs.kieler.kwebs.kstatistics.KStatistics#getTimeRemoteSupplemental()
     * @see #getKStatistics()
     * @generated
     */
    EAttribute getKStatistics_TimeRemoteSupplemental();

    /**
     * Returns the meta object for enum '{@link de.cau.cs.kieler.kwebs.kstatistics.LayoutType <em>Layout Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Layout Type</em>'.
     * @see de.cau.cs.kieler.kwebs.kstatistics.LayoutType
     * @generated
     */
    EEnum getLayoutType();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    KStatisticsFactory getKStatisticsFactory();

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
         * The meta object literal for the '{@link de.cau.cs.kieler.kwebs.kstatistics.impl.KStatisticsImpl <em>KStatistics</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.kwebs.kstatistics.impl.KStatisticsImpl
         * @see de.cau.cs.kieler.kwebs.kstatistics.impl.KStatisticsPackageImpl#getKStatistics()
         * @generated
         */
        EClass KSTATISTICS = eINSTANCE.getKStatistics();
        /**
         * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KSTATISTICS__TYPE = eINSTANCE.getKStatistics_Type();
        /**
         * The meta object literal for the '<em><b>Bytes</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KSTATISTICS__BYTES = eINSTANCE.getKStatistics_Bytes();
        /**
         * The meta object literal for the '<em><b>Compression</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KSTATISTICS__COMPRESSION = eINSTANCE.getKStatistics_Compression();
        /**
         * The meta object literal for the '<em><b>Edges</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KSTATISTICS__EDGES = eINSTANCE.getKStatistics_Edges();
        /**
         * The meta object literal for the '<em><b>Nodes</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KSTATISTICS__NODES = eINSTANCE.getKStatistics_Nodes();
        /**
         * The meta object literal for the '<em><b>Ports</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KSTATISTICS__PORTS = eINSTANCE.getKStatistics_Ports();
        /**
         * The meta object literal for the '<em><b>Labels</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KSTATISTICS__LABELS = eINSTANCE.getKStatistics_Labels();
        /**
         * The meta object literal for the '<em><b>Time Total</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KSTATISTICS__TIME_TOTAL = eINSTANCE.getKStatistics_TimeTotal();
        /**
         * The meta object literal for the '<em><b>Time Network</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KSTATISTICS__TIME_NETWORK = eINSTANCE.getKStatistics_TimeNetwork();
        /**
         * The meta object literal for the '<em><b>Time Layout</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KSTATISTICS__TIME_LAYOUT = eINSTANCE.getKStatistics_TimeLayout();
        /**
         * The meta object literal for the '<em><b>Time Local Supplemental</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KSTATISTICS__TIME_LOCAL_SUPPLEMENTAL = eINSTANCE.getKStatistics_TimeLocalSupplemental();
        /**
         * The meta object literal for the '<em><b>Time Remote Supplemental</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KSTATISTICS__TIME_REMOTE_SUPPLEMENTAL = eINSTANCE.getKStatistics_TimeRemoteSupplemental();
        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.kwebs.kstatistics.LayoutType <em>Layout Type</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.kwebs.kstatistics.LayoutType
         * @see de.cau.cs.kieler.kwebs.kstatistics.impl.KStatisticsPackageImpl#getLayoutType()
         * @generated
         */
        EEnum LAYOUT_TYPE = eINSTANCE.getLayoutType();

    }

} //KStatisticsPackage
