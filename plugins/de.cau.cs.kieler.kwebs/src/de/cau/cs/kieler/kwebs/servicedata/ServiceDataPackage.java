/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.cs.kieler.kwebs.servicedata;

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
 * @see de.cau.cs.kieler.kwebs.servicedata.ServiceDataFactory
 * @model kind="package"
 * @generated
 */
public interface ServiceDataPackage extends EPackage {
    /**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "servicedata";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://de.cau.cs.kieler.kwebs.service.data/2011-07-25/ServiceData/1.0";

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "servicedata";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    ServiceDataPackage eINSTANCE = de.cau.cs.kieler.kwebs.servicedata.impl.ServiceDataPackageImpl.init();

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.kwebs.servicedata.impl.ServiceDataImpl <em>Service Data</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.kwebs.servicedata.impl.ServiceDataImpl
     * @see de.cau.cs.kieler.kwebs.servicedata.impl.ServiceDataPackageImpl#getServiceData()
     * @generated
     */
    int SERVICE_DATA = 0;

    /**
     * The feature id for the '<em><b>Layout Algorithms</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SERVICE_DATA__LAYOUT_ALGORITHMS = 0;

    /**
     * The feature id for the '<em><b>Layout Types</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SERVICE_DATA__LAYOUT_TYPES = 1;

    /**
     * The feature id for the '<em><b>Layout Options</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SERVICE_DATA__LAYOUT_OPTIONS = 2;

    /**
     * The feature id for the '<em><b>Categories</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SERVICE_DATA__CATEGORIES = 3;

    /**
     * The feature id for the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SERVICE_DATA__VERSION = 4;

    /**
     * The number of structural features of the '<em>Service Data</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SERVICE_DATA_FEATURE_COUNT = 5;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.kwebs.servicedata.impl.LayoutAlgorithmImpl <em>Layout Algorithm</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.kwebs.servicedata.impl.LayoutAlgorithmImpl
     * @see de.cau.cs.kieler.kwebs.servicedata.impl.ServiceDataPackageImpl#getLayoutAlgorithm()
     * @generated
     */
    int LAYOUT_ALGORITHM = 1;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LAYOUT_ALGORITHM__ID = 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LAYOUT_ALGORITHM__NAME = 1;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LAYOUT_ALGORITHM__DESCRIPTION = 2;

    /**
     * The feature id for the '<em><b>Known Options</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LAYOUT_ALGORITHM__KNOWN_OPTIONS = 3;

    /**
     * The feature id for the '<em><b>Supported Diagrams</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LAYOUT_ALGORITHM__SUPPORTED_DIAGRAMS = 4;

    /**
     * The feature id for the '<em><b>Category</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LAYOUT_ALGORITHM__CATEGORY = 5;

    /**
     * The feature id for the '<em><b>Type</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LAYOUT_ALGORITHM__TYPE = 6;

    /**
     * The feature id for the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LAYOUT_ALGORITHM__VERSION = 7;

    /**
     * The feature id for the '<em><b>Preview Image Path</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LAYOUT_ALGORITHM__PREVIEW_IMAGE_PATH = 8;

    /**
     * The feature id for the '<em><b>Preview Image Port</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LAYOUT_ALGORITHM__PREVIEW_IMAGE_PORT = 9;

    /**
     * The number of structural features of the '<em>Layout Algorithm</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LAYOUT_ALGORITHM_FEATURE_COUNT = 10;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.kwebs.servicedata.impl.LayoutTypeImpl <em>Layout Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.kwebs.servicedata.impl.LayoutTypeImpl
     * @see de.cau.cs.kieler.kwebs.servicedata.impl.ServiceDataPackageImpl#getLayoutType()
     * @generated
     */
    int LAYOUT_TYPE = 2;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LAYOUT_TYPE__ID = 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LAYOUT_TYPE__NAME = 1;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LAYOUT_TYPE__DESCRIPTION = 2;

    /**
     * The number of structural features of the '<em>Layout Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LAYOUT_TYPE_FEATURE_COUNT = 3;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.kwebs.servicedata.impl.LayoutOptionImpl <em>Layout Option</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.kwebs.servicedata.impl.LayoutOptionImpl
     * @see de.cau.cs.kieler.kwebs.servicedata.impl.ServiceDataPackageImpl#getLayoutOption()
     * @generated
     */
    int LAYOUT_OPTION = 3;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LAYOUT_OPTION__ID = 0;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LAYOUT_OPTION__TYPE = 1;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LAYOUT_OPTION__NAME = 2;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LAYOUT_OPTION__DESCRIPTION = 3;

    /**
     * The feature id for the '<em><b>Applies To</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LAYOUT_OPTION__APPLIES_TO = 4;

    /**
     * The feature id for the '<em><b>Default</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LAYOUT_OPTION__DEFAULT = 5;

    /**
     * The feature id for the '<em><b>Advanced</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LAYOUT_OPTION__ADVANCED = 6;

    /**
     * The feature id for the '<em><b>Remote Enum</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LAYOUT_OPTION__REMOTE_ENUM = 7;

    /**
     * The feature id for the '<em><b>Implementation</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LAYOUT_OPTION__IMPLEMENTATION = 8;

    /**
     * The number of structural features of the '<em>Layout Option</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LAYOUT_OPTION_FEATURE_COUNT = 9;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.kwebs.servicedata.impl.CategoryImpl <em>Category</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.kwebs.servicedata.impl.CategoryImpl
     * @see de.cau.cs.kieler.kwebs.servicedata.impl.ServiceDataPackageImpl#getCategory()
     * @generated
     */
    int CATEGORY = 4;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CATEGORY__ID = 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CATEGORY__NAME = 1;

    /**
     * The number of structural features of the '<em>Category</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CATEGORY_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.kwebs.servicedata.impl.KnownOptionImpl <em>Known Option</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.kwebs.servicedata.impl.KnownOptionImpl
     * @see de.cau.cs.kieler.kwebs.servicedata.impl.ServiceDataPackageImpl#getKnownOption()
     * @generated
     */
    int KNOWN_OPTION = 5;

    /**
     * The feature id for the '<em><b>Default</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KNOWN_OPTION__DEFAULT = 0;

    /**
     * The feature id for the '<em><b>Option</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KNOWN_OPTION__OPTION = 1;

    /**
     * The number of structural features of the '<em>Known Option</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KNOWN_OPTION_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.kwebs.servicedata.impl.SupportedDiagramImpl <em>Supported Diagram</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.kwebs.servicedata.impl.SupportedDiagramImpl
     * @see de.cau.cs.kieler.kwebs.servicedata.impl.ServiceDataPackageImpl#getSupportedDiagram()
     * @generated
     */
    int SUPPORTED_DIAGRAM = 6;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SUPPORTED_DIAGRAM__TYPE = 0;

    /**
     * The feature id for the '<em><b>Priority</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SUPPORTED_DIAGRAM__PRIORITY = 1;

    /**
     * The number of structural features of the '<em>Supported Diagram</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SUPPORTED_DIAGRAM_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.kwebs.servicedata.impl.RemoteEnumImpl <em>Remote Enum</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.kwebs.servicedata.impl.RemoteEnumImpl
     * @see de.cau.cs.kieler.kwebs.servicedata.impl.ServiceDataPackageImpl#getRemoteEnum()
     * @generated
     */
    int REMOTE_ENUM = 7;

    /**
     * The feature id for the '<em><b>Values</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REMOTE_ENUM__VALUES = 0;

    /**
     * The number of structural features of the '<em>Remote Enum</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REMOTE_ENUM_FEATURE_COUNT = 1;


    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.kwebs.servicedata.ServiceData <em>Service Data</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Service Data</em>'.
     * @see de.cau.cs.kieler.kwebs.servicedata.ServiceData
     * @generated
     */
    EClass getServiceData();

    /**
     * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.kwebs.servicedata.ServiceData#getLayoutAlgorithms <em>Layout Algorithms</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Layout Algorithms</em>'.
     * @see de.cau.cs.kieler.kwebs.servicedata.ServiceData#getLayoutAlgorithms()
     * @see #getServiceData()
     * @generated
     */
    EReference getServiceData_LayoutAlgorithms();

    /**
     * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.kwebs.servicedata.ServiceData#getLayoutTypes <em>Layout Types</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Layout Types</em>'.
     * @see de.cau.cs.kieler.kwebs.servicedata.ServiceData#getLayoutTypes()
     * @see #getServiceData()
     * @generated
     */
    EReference getServiceData_LayoutTypes();

    /**
     * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.kwebs.servicedata.ServiceData#getLayoutOptions <em>Layout Options</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Layout Options</em>'.
     * @see de.cau.cs.kieler.kwebs.servicedata.ServiceData#getLayoutOptions()
     * @see #getServiceData()
     * @generated
     */
    EReference getServiceData_LayoutOptions();

    /**
     * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.kwebs.servicedata.ServiceData#getCategories <em>Categories</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Categories</em>'.
     * @see de.cau.cs.kieler.kwebs.servicedata.ServiceData#getCategories()
     * @see #getServiceData()
     * @generated
     */
    EReference getServiceData_Categories();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kwebs.servicedata.ServiceData#getVersion <em>Version</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Version</em>'.
     * @see de.cau.cs.kieler.kwebs.servicedata.ServiceData#getVersion()
     * @see #getServiceData()
     * @generated
     */
    EAttribute getServiceData_Version();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.kwebs.servicedata.LayoutAlgorithm <em>Layout Algorithm</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Layout Algorithm</em>'.
     * @see de.cau.cs.kieler.kwebs.servicedata.LayoutAlgorithm
     * @generated
     */
    EClass getLayoutAlgorithm();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kwebs.servicedata.LayoutAlgorithm#getId <em>Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see de.cau.cs.kieler.kwebs.servicedata.LayoutAlgorithm#getId()
     * @see #getLayoutAlgorithm()
     * @generated
     */
    EAttribute getLayoutAlgorithm_Id();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kwebs.servicedata.LayoutAlgorithm#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see de.cau.cs.kieler.kwebs.servicedata.LayoutAlgorithm#getName()
     * @see #getLayoutAlgorithm()
     * @generated
     */
    EAttribute getLayoutAlgorithm_Name();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kwebs.servicedata.LayoutAlgorithm#getDescription <em>Description</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Description</em>'.
     * @see de.cau.cs.kieler.kwebs.servicedata.LayoutAlgorithm#getDescription()
     * @see #getLayoutAlgorithm()
     * @generated
     */
    EAttribute getLayoutAlgorithm_Description();

    /**
     * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.kwebs.servicedata.LayoutAlgorithm#getKnownOptions <em>Known Options</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Known Options</em>'.
     * @see de.cau.cs.kieler.kwebs.servicedata.LayoutAlgorithm#getKnownOptions()
     * @see #getLayoutAlgorithm()
     * @generated
     */
    EReference getLayoutAlgorithm_KnownOptions();

    /**
     * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.kwebs.servicedata.LayoutAlgorithm#getSupportedDiagrams <em>Supported Diagrams</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Supported Diagrams</em>'.
     * @see de.cau.cs.kieler.kwebs.servicedata.LayoutAlgorithm#getSupportedDiagrams()
     * @see #getLayoutAlgorithm()
     * @generated
     */
    EReference getLayoutAlgorithm_SupportedDiagrams();

    /**
     * Returns the meta object for the reference '{@link de.cau.cs.kieler.kwebs.servicedata.LayoutAlgorithm#getCategory <em>Category</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Category</em>'.
     * @see de.cau.cs.kieler.kwebs.servicedata.LayoutAlgorithm#getCategory()
     * @see #getLayoutAlgorithm()
     * @generated
     */
    EReference getLayoutAlgorithm_Category();

    /**
     * Returns the meta object for the reference '{@link de.cau.cs.kieler.kwebs.servicedata.LayoutAlgorithm#getType <em>Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Type</em>'.
     * @see de.cau.cs.kieler.kwebs.servicedata.LayoutAlgorithm#getType()
     * @see #getLayoutAlgorithm()
     * @generated
     */
    EReference getLayoutAlgorithm_Type();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kwebs.servicedata.LayoutAlgorithm#getVersion <em>Version</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Version</em>'.
     * @see de.cau.cs.kieler.kwebs.servicedata.LayoutAlgorithm#getVersion()
     * @see #getLayoutAlgorithm()
     * @generated
     */
    EAttribute getLayoutAlgorithm_Version();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kwebs.servicedata.LayoutAlgorithm#getPreviewImagePath <em>Preview Image Path</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Preview Image Path</em>'.
     * @see de.cau.cs.kieler.kwebs.servicedata.LayoutAlgorithm#getPreviewImagePath()
     * @see #getLayoutAlgorithm()
     * @generated
     */
    EAttribute getLayoutAlgorithm_PreviewImagePath();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kwebs.servicedata.LayoutAlgorithm#getPreviewImagePort <em>Preview Image Port</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Preview Image Port</em>'.
     * @see de.cau.cs.kieler.kwebs.servicedata.LayoutAlgorithm#getPreviewImagePort()
     * @see #getLayoutAlgorithm()
     * @generated
     */
    EAttribute getLayoutAlgorithm_PreviewImagePort();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.kwebs.servicedata.LayoutType <em>Layout Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Layout Type</em>'.
     * @see de.cau.cs.kieler.kwebs.servicedata.LayoutType
     * @generated
     */
    EClass getLayoutType();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kwebs.servicedata.LayoutType#getId <em>Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see de.cau.cs.kieler.kwebs.servicedata.LayoutType#getId()
     * @see #getLayoutType()
     * @generated
     */
    EAttribute getLayoutType_Id();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kwebs.servicedata.LayoutType#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see de.cau.cs.kieler.kwebs.servicedata.LayoutType#getName()
     * @see #getLayoutType()
     * @generated
     */
    EAttribute getLayoutType_Name();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kwebs.servicedata.LayoutType#getDescription <em>Description</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Description</em>'.
     * @see de.cau.cs.kieler.kwebs.servicedata.LayoutType#getDescription()
     * @see #getLayoutType()
     * @generated
     */
    EAttribute getLayoutType_Description();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.kwebs.servicedata.LayoutOption <em>Layout Option</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Layout Option</em>'.
     * @see de.cau.cs.kieler.kwebs.servicedata.LayoutOption
     * @generated
     */
    EClass getLayoutOption();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kwebs.servicedata.LayoutOption#getId <em>Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see de.cau.cs.kieler.kwebs.servicedata.LayoutOption#getId()
     * @see #getLayoutOption()
     * @generated
     */
    EAttribute getLayoutOption_Id();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kwebs.servicedata.LayoutOption#getType <em>Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Type</em>'.
     * @see de.cau.cs.kieler.kwebs.servicedata.LayoutOption#getType()
     * @see #getLayoutOption()
     * @generated
     */
    EAttribute getLayoutOption_Type();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kwebs.servicedata.LayoutOption#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see de.cau.cs.kieler.kwebs.servicedata.LayoutOption#getName()
     * @see #getLayoutOption()
     * @generated
     */
    EAttribute getLayoutOption_Name();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kwebs.servicedata.LayoutOption#getDescription <em>Description</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Description</em>'.
     * @see de.cau.cs.kieler.kwebs.servicedata.LayoutOption#getDescription()
     * @see #getLayoutOption()
     * @generated
     */
    EAttribute getLayoutOption_Description();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kwebs.servicedata.LayoutOption#getAppliesTo <em>Applies To</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Applies To</em>'.
     * @see de.cau.cs.kieler.kwebs.servicedata.LayoutOption#getAppliesTo()
     * @see #getLayoutOption()
     * @generated
     */
    EAttribute getLayoutOption_AppliesTo();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kwebs.servicedata.LayoutOption#getDefault <em>Default</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Default</em>'.
     * @see de.cau.cs.kieler.kwebs.servicedata.LayoutOption#getDefault()
     * @see #getLayoutOption()
     * @generated
     */
    EAttribute getLayoutOption_Default();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kwebs.servicedata.LayoutOption#isAdvanced <em>Advanced</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Advanced</em>'.
     * @see de.cau.cs.kieler.kwebs.servicedata.LayoutOption#isAdvanced()
     * @see #getLayoutOption()
     * @generated
     */
    EAttribute getLayoutOption_Advanced();

    /**
     * Returns the meta object for the containment reference '{@link de.cau.cs.kieler.kwebs.servicedata.LayoutOption#getRemoteEnum <em>Remote Enum</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Remote Enum</em>'.
     * @see de.cau.cs.kieler.kwebs.servicedata.LayoutOption#getRemoteEnum()
     * @see #getLayoutOption()
     * @generated
     */
    EReference getLayoutOption_RemoteEnum();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kwebs.servicedata.LayoutOption#getImplementation <em>Implementation</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Implementation</em>'.
     * @see de.cau.cs.kieler.kwebs.servicedata.LayoutOption#getImplementation()
     * @see #getLayoutOption()
     * @generated
     */
    EAttribute getLayoutOption_Implementation();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.kwebs.servicedata.Category <em>Category</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Category</em>'.
     * @see de.cau.cs.kieler.kwebs.servicedata.Category
     * @generated
     */
    EClass getCategory();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kwebs.servicedata.Category#getId <em>Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see de.cau.cs.kieler.kwebs.servicedata.Category#getId()
     * @see #getCategory()
     * @generated
     */
    EAttribute getCategory_Id();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kwebs.servicedata.Category#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see de.cau.cs.kieler.kwebs.servicedata.Category#getName()
     * @see #getCategory()
     * @generated
     */
    EAttribute getCategory_Name();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.kwebs.servicedata.KnownOption <em>Known Option</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Known Option</em>'.
     * @see de.cau.cs.kieler.kwebs.servicedata.KnownOption
     * @generated
     */
    EClass getKnownOption();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kwebs.servicedata.KnownOption#getDefault <em>Default</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Default</em>'.
     * @see de.cau.cs.kieler.kwebs.servicedata.KnownOption#getDefault()
     * @see #getKnownOption()
     * @generated
     */
    EAttribute getKnownOption_Default();

    /**
     * Returns the meta object for the reference '{@link de.cau.cs.kieler.kwebs.servicedata.KnownOption#getOption <em>Option</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Option</em>'.
     * @see de.cau.cs.kieler.kwebs.servicedata.KnownOption#getOption()
     * @see #getKnownOption()
     * @generated
     */
    EReference getKnownOption_Option();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.kwebs.servicedata.SupportedDiagram <em>Supported Diagram</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Supported Diagram</em>'.
     * @see de.cau.cs.kieler.kwebs.servicedata.SupportedDiagram
     * @generated
     */
    EClass getSupportedDiagram();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kwebs.servicedata.SupportedDiagram#getType <em>Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Type</em>'.
     * @see de.cau.cs.kieler.kwebs.servicedata.SupportedDiagram#getType()
     * @see #getSupportedDiagram()
     * @generated
     */
    EAttribute getSupportedDiagram_Type();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kwebs.servicedata.SupportedDiagram#getPriority <em>Priority</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Priority</em>'.
     * @see de.cau.cs.kieler.kwebs.servicedata.SupportedDiagram#getPriority()
     * @see #getSupportedDiagram()
     * @generated
     */
    EAttribute getSupportedDiagram_Priority();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.kwebs.servicedata.RemoteEnum <em>Remote Enum</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Remote Enum</em>'.
     * @see de.cau.cs.kieler.kwebs.servicedata.RemoteEnum
     * @generated
     */
    EClass getRemoteEnum();

    /**
     * Returns the meta object for the attribute list '{@link de.cau.cs.kieler.kwebs.servicedata.RemoteEnum#getValues <em>Values</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Values</em>'.
     * @see de.cau.cs.kieler.kwebs.servicedata.RemoteEnum#getValues()
     * @see #getRemoteEnum()
     * @generated
     */
    EAttribute getRemoteEnum_Values();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    ServiceDataFactory getServiceDataFactory();

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
         * The meta object literal for the '{@link de.cau.cs.kieler.kwebs.servicedata.impl.ServiceDataImpl <em>Service Data</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.kwebs.servicedata.impl.ServiceDataImpl
         * @see de.cau.cs.kieler.kwebs.servicedata.impl.ServiceDataPackageImpl#getServiceData()
         * @generated
         */
        EClass SERVICE_DATA = eINSTANCE.getServiceData();

        /**
         * The meta object literal for the '<em><b>Layout Algorithms</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference SERVICE_DATA__LAYOUT_ALGORITHMS = eINSTANCE.getServiceData_LayoutAlgorithms();

        /**
         * The meta object literal for the '<em><b>Layout Types</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference SERVICE_DATA__LAYOUT_TYPES = eINSTANCE.getServiceData_LayoutTypes();

        /**
         * The meta object literal for the '<em><b>Layout Options</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference SERVICE_DATA__LAYOUT_OPTIONS = eINSTANCE.getServiceData_LayoutOptions();

        /**
         * The meta object literal for the '<em><b>Categories</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference SERVICE_DATA__CATEGORIES = eINSTANCE.getServiceData_Categories();

        /**
         * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SERVICE_DATA__VERSION = eINSTANCE.getServiceData_Version();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.kwebs.servicedata.impl.LayoutAlgorithmImpl <em>Layout Algorithm</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.kwebs.servicedata.impl.LayoutAlgorithmImpl
         * @see de.cau.cs.kieler.kwebs.servicedata.impl.ServiceDataPackageImpl#getLayoutAlgorithm()
         * @generated
         */
        EClass LAYOUT_ALGORITHM = eINSTANCE.getLayoutAlgorithm();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute LAYOUT_ALGORITHM__ID = eINSTANCE.getLayoutAlgorithm_Id();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute LAYOUT_ALGORITHM__NAME = eINSTANCE.getLayoutAlgorithm_Name();

        /**
         * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute LAYOUT_ALGORITHM__DESCRIPTION = eINSTANCE.getLayoutAlgorithm_Description();

        /**
         * The meta object literal for the '<em><b>Known Options</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference LAYOUT_ALGORITHM__KNOWN_OPTIONS = eINSTANCE.getLayoutAlgorithm_KnownOptions();

        /**
         * The meta object literal for the '<em><b>Supported Diagrams</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference LAYOUT_ALGORITHM__SUPPORTED_DIAGRAMS = eINSTANCE.getLayoutAlgorithm_SupportedDiagrams();

        /**
         * The meta object literal for the '<em><b>Category</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference LAYOUT_ALGORITHM__CATEGORY = eINSTANCE.getLayoutAlgorithm_Category();

        /**
         * The meta object literal for the '<em><b>Type</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference LAYOUT_ALGORITHM__TYPE = eINSTANCE.getLayoutAlgorithm_Type();

        /**
         * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute LAYOUT_ALGORITHM__VERSION = eINSTANCE.getLayoutAlgorithm_Version();

        /**
         * The meta object literal for the '<em><b>Preview Image Path</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute LAYOUT_ALGORITHM__PREVIEW_IMAGE_PATH = eINSTANCE.getLayoutAlgorithm_PreviewImagePath();

        /**
         * The meta object literal for the '<em><b>Preview Image Port</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute LAYOUT_ALGORITHM__PREVIEW_IMAGE_PORT = eINSTANCE.getLayoutAlgorithm_PreviewImagePort();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.kwebs.servicedata.impl.LayoutTypeImpl <em>Layout Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.kwebs.servicedata.impl.LayoutTypeImpl
         * @see de.cau.cs.kieler.kwebs.servicedata.impl.ServiceDataPackageImpl#getLayoutType()
         * @generated
         */
        EClass LAYOUT_TYPE = eINSTANCE.getLayoutType();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute LAYOUT_TYPE__ID = eINSTANCE.getLayoutType_Id();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute LAYOUT_TYPE__NAME = eINSTANCE.getLayoutType_Name();

        /**
         * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute LAYOUT_TYPE__DESCRIPTION = eINSTANCE.getLayoutType_Description();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.kwebs.servicedata.impl.LayoutOptionImpl <em>Layout Option</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.kwebs.servicedata.impl.LayoutOptionImpl
         * @see de.cau.cs.kieler.kwebs.servicedata.impl.ServiceDataPackageImpl#getLayoutOption()
         * @generated
         */
        EClass LAYOUT_OPTION = eINSTANCE.getLayoutOption();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute LAYOUT_OPTION__ID = eINSTANCE.getLayoutOption_Id();

        /**
         * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute LAYOUT_OPTION__TYPE = eINSTANCE.getLayoutOption_Type();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute LAYOUT_OPTION__NAME = eINSTANCE.getLayoutOption_Name();

        /**
         * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute LAYOUT_OPTION__DESCRIPTION = eINSTANCE.getLayoutOption_Description();

        /**
         * The meta object literal for the '<em><b>Applies To</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute LAYOUT_OPTION__APPLIES_TO = eINSTANCE.getLayoutOption_AppliesTo();

        /**
         * The meta object literal for the '<em><b>Default</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute LAYOUT_OPTION__DEFAULT = eINSTANCE.getLayoutOption_Default();

        /**
         * The meta object literal for the '<em><b>Advanced</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute LAYOUT_OPTION__ADVANCED = eINSTANCE.getLayoutOption_Advanced();

        /**
         * The meta object literal for the '<em><b>Remote Enum</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference LAYOUT_OPTION__REMOTE_ENUM = eINSTANCE.getLayoutOption_RemoteEnum();

        /**
         * The meta object literal for the '<em><b>Implementation</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute LAYOUT_OPTION__IMPLEMENTATION = eINSTANCE.getLayoutOption_Implementation();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.kwebs.servicedata.impl.CategoryImpl <em>Category</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.kwebs.servicedata.impl.CategoryImpl
         * @see de.cau.cs.kieler.kwebs.servicedata.impl.ServiceDataPackageImpl#getCategory()
         * @generated
         */
        EClass CATEGORY = eINSTANCE.getCategory();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute CATEGORY__ID = eINSTANCE.getCategory_Id();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute CATEGORY__NAME = eINSTANCE.getCategory_Name();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.kwebs.servicedata.impl.KnownOptionImpl <em>Known Option</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.kwebs.servicedata.impl.KnownOptionImpl
         * @see de.cau.cs.kieler.kwebs.servicedata.impl.ServiceDataPackageImpl#getKnownOption()
         * @generated
         */
        EClass KNOWN_OPTION = eINSTANCE.getKnownOption();

        /**
         * The meta object literal for the '<em><b>Default</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KNOWN_OPTION__DEFAULT = eINSTANCE.getKnownOption_Default();

        /**
         * The meta object literal for the '<em><b>Option</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference KNOWN_OPTION__OPTION = eINSTANCE.getKnownOption_Option();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.kwebs.servicedata.impl.SupportedDiagramImpl <em>Supported Diagram</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.kwebs.servicedata.impl.SupportedDiagramImpl
         * @see de.cau.cs.kieler.kwebs.servicedata.impl.ServiceDataPackageImpl#getSupportedDiagram()
         * @generated
         */
        EClass SUPPORTED_DIAGRAM = eINSTANCE.getSupportedDiagram();

        /**
         * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SUPPORTED_DIAGRAM__TYPE = eINSTANCE.getSupportedDiagram_Type();

        /**
         * The meta object literal for the '<em><b>Priority</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SUPPORTED_DIAGRAM__PRIORITY = eINSTANCE.getSupportedDiagram_Priority();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.kwebs.servicedata.impl.RemoteEnumImpl <em>Remote Enum</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.kwebs.servicedata.impl.RemoteEnumImpl
         * @see de.cau.cs.kieler.kwebs.servicedata.impl.ServiceDataPackageImpl#getRemoteEnum()
         * @generated
         */
        EClass REMOTE_ENUM = eINSTANCE.getRemoteEnum();

        /**
         * The meta object literal for the '<em><b>Values</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute REMOTE_ENUM__VALUES = eINSTANCE.getRemoteEnum_Values();

    }

} //ServiceDataPackage
