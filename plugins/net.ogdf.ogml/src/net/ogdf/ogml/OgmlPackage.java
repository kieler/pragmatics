/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.ogdf.ogml;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
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
 * @see net.ogdf.ogml.OgmlFactory
 * @model kind="package"
 *        extendedMetaData="qualified='false'"
 * @generated
 */
public interface OgmlPackage extends EPackage {
    /**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "ogml";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "platform:/resource/net.ogdf.ogml/model/ogml.xsd";

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "ogml";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    OgmlPackage eINSTANCE = net.ogdf.ogml.impl.OgmlPackageImpl.init();

    /**
     * The meta object id for the '{@link net.ogdf.ogml.impl.BooleanTypeImpl <em>Boolean Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.impl.BooleanTypeImpl
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getBooleanType()
     * @generated
     */
    int BOOLEAN_TYPE = 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BOOLEAN_TYPE__NAME = 0;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BOOLEAN_TYPE__VALUE = 1;

    /**
     * The number of structural features of the '<em>Boolean Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BOOLEAN_TYPE_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '{@link net.ogdf.ogml.impl.BoolTypeImpl <em>Bool Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.impl.BoolTypeImpl
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getBoolType()
     * @generated
     */
    int BOOL_TYPE = 1;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BOOL_TYPE__NAME = 0;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BOOL_TYPE__VALUE = 1;

    /**
     * The number of structural features of the '<em>Bool Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BOOL_TYPE_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '{@link net.ogdf.ogml.impl.ComposedTypeImpl <em>Composed Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.impl.ComposedTypeImpl
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getComposedType()
     * @generated
     */
    int COMPOSED_TYPE = 2;

    /**
     * The feature id for the '<em><b>Group</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPOSED_TYPE__GROUP = 0;

    /**
     * The feature id for the '<em><b>Number</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPOSED_TYPE__NUMBER = 1;

    /**
     * The feature id for the '<em><b>Int</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPOSED_TYPE__INT = 2;

    /**
     * The feature id for the '<em><b>Boolean</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPOSED_TYPE__BOOLEAN = 3;

    /**
     * The feature id for the '<em><b>Node</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPOSED_TYPE__NODE = 4;

    /**
     * The feature id for the '<em><b>Edge</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPOSED_TYPE__EDGE = 5;

    /**
     * The feature id for the '<em><b>Label</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPOSED_TYPE__LABEL = 6;

    /**
     * The feature id for the '<em><b>Composed</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPOSED_TYPE__COMPOSED = 7;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPOSED_TYPE__NAME = 8;

    /**
     * The number of structural features of the '<em>Composed Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPOSED_TYPE_FEATURE_COUNT = 9;

    /**
     * The meta object id for the '{@link net.ogdf.ogml.impl.ConstraintsTypeImpl <em>Constraints Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.impl.ConstraintsTypeImpl
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getConstraintsType()
     * @generated
     */
    int CONSTRAINTS_TYPE = 3;

    /**
     * The feature id for the '<em><b>Group</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONSTRAINTS_TYPE__GROUP = 0;

    /**
     * The feature id for the '<em><b>Number</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONSTRAINTS_TYPE__NUMBER = 1;

    /**
     * The feature id for the '<em><b>Int</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONSTRAINTS_TYPE__INT = 2;

    /**
     * The feature id for the '<em><b>Boolean</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONSTRAINTS_TYPE__BOOLEAN = 3;

    /**
     * The feature id for the '<em><b>Node Ref</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONSTRAINTS_TYPE__NODE_REF = 4;

    /**
     * The feature id for the '<em><b>Edge Ref</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONSTRAINTS_TYPE__EDGE_REF = 5;

    /**
     * The feature id for the '<em><b>Label Ref</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONSTRAINTS_TYPE__LABEL_REF = 6;

    /**
     * The feature id for the '<em><b>Composed</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONSTRAINTS_TYPE__COMPOSED = 7;

    /**
     * The number of structural features of the '<em>Constraints Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONSTRAINTS_TYPE_FEATURE_COUNT = 8;

    /**
     * The meta object id for the '{@link net.ogdf.ogml.impl.DataTypeImpl <em>Data Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.impl.DataTypeImpl
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getDataType()
     * @generated
     */
    int DATA_TYPE = 4;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATA_TYPE__VALUE = 0;

    /**
     * The feature id for the '<em><b>Id Ref</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATA_TYPE__ID_REF = 1;

    /**
     * The number of structural features of the '<em>Data Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATA_TYPE_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '{@link net.ogdf.ogml.impl.DocumentRootImpl <em>Document Root</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.impl.DocumentRootImpl
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getDocumentRoot()
     * @generated
     */
    int DOCUMENT_ROOT = 5;

    /**
     * The feature id for the '<em><b>Mixed</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__MIXED = 0;

    /**
     * The feature id for the '<em><b>XMLNS Prefix Map</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__XMLNS_PREFIX_MAP = 1;

    /**
     * The feature id for the '<em><b>XSI Schema Location</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = 2;

    /**
     * The feature id for the '<em><b>Bool</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__BOOL = 3;

    /**
     * The feature id for the '<em><b>Boolean</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__BOOLEAN = 4;

    /**
     * The feature id for the '<em><b>Composed</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__COMPOSED = 5;

    /**
     * The feature id for the '<em><b>Constraints</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__CONSTRAINTS = 6;

    /**
     * The feature id for the '<em><b>Data</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__DATA = 7;

    /**
     * The feature id for the '<em><b>Edge</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__EDGE = 8;

    /**
     * The feature id for the '<em><b>Edge Constraint</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__EDGE_CONSTRAINT = 9;

    /**
     * The feature id for the '<em><b>Edge Layout</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__EDGE_LAYOUT = 10;

    /**
     * The feature id for the '<em><b>Edge Style Template</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__EDGE_STYLE_TEMPLATE = 11;

    /**
     * The feature id for the '<em><b>Endpoint</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__ENDPOINT = 12;

    /**
     * The feature id for the '<em><b>Fill</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__FILL = 13;

    /**
     * The feature id for the '<em><b>Font</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__FONT = 14;

    /**
     * The feature id for the '<em><b>Font Stretch</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__FONT_STRETCH = 15;

    /**
     * The feature id for the '<em><b>Font Style</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__FONT_STYLE = 16;

    /**
     * The feature id for the '<em><b>Font Variant</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__FONT_VARIANT = 17;

    /**
     * The feature id for the '<em><b>Font Weight</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__FONT_WEIGHT = 18;

    /**
     * The feature id for the '<em><b>Graph</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__GRAPH = 19;

    /**
     * The feature id for the '<em><b>Graph Style</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__GRAPH_STYLE = 20;

    /**
     * The feature id for the '<em><b>Int</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__INT = 21;

    /**
     * The feature id for the '<em><b>Key</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__KEY = 22;

    /**
     * The feature id for the '<em><b>Keys</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__KEYS = 23;

    /**
     * The feature id for the '<em><b>Key Value</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__KEY_VALUE = 24;

    /**
     * The feature id for the '<em><b>Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__LABEL = 25;

    /**
     * The feature id for the '<em><b>Label Constraint</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__LABEL_CONSTRAINT = 26;

    /**
     * The feature id for the '<em><b>Label Layout</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__LABEL_LAYOUT = 27;

    /**
     * The feature id for the '<em><b>Label Style Template</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__LABEL_STYLE_TEMPLATE = 28;

    /**
     * The feature id for the '<em><b>Layout</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__LAYOUT = 29;

    /**
     * The feature id for the '<em><b>Line</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__LINE = 30;

    /**
     * The feature id for the '<em><b>Line Style</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__LINE_STYLE = 31;

    /**
     * The feature id for the '<em><b>Line Style Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__LINE_STYLE_TYPE = 32;

    /**
     * The feature id for the '<em><b>Location</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__LOCATION = 33;

    /**
     * The feature id for the '<em><b>Node</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__NODE = 34;

    /**
     * The feature id for the '<em><b>Node Constraint</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__NODE_CONSTRAINT = 35;

    /**
     * The feature id for the '<em><b>Node Layout</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__NODE_LAYOUT = 36;

    /**
     * The feature id for the '<em><b>Node Style Template</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__NODE_STYLE_TEMPLATE = 37;

    /**
     * The feature id for the '<em><b>Number</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__NUMBER = 38;

    /**
     * The feature id for the '<em><b>Ogml</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__OGML = 39;

    /**
     * The feature id for the '<em><b>Pattern</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__PATTERN = 40;

    /**
     * The feature id for the '<em><b>Point</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__POINT = 41;

    /**
     * The feature id for the '<em><b>Segment</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__SEGMENT = 42;

    /**
     * The feature id for the '<em><b>Shape</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__SHAPE = 43;

    /**
     * The feature id for the '<em><b>Source Style</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__SOURCE_STYLE = 44;

    /**
     * The feature id for the '<em><b>Source Target</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__SOURCE_TARGET = 45;

    /**
     * The feature id for the '<em><b>Structure</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__STRUCTURE = 46;

    /**
     * The feature id for the '<em><b>Styles</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__STYLES = 47;

    /**
     * The feature id for the '<em><b>Style Templates</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__STYLE_TEMPLATES = 48;

    /**
     * The feature id for the '<em><b>Target Style</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__TARGET_STYLE = 49;

    /**
     * The feature id for the '<em><b>Template</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__TEMPLATE = 50;

    /**
     * The feature id for the '<em><b>Text</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__TEXT = 51;

    /**
     * The feature id for the '<em><b>Uri</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__URI = 52;

    /**
     * The number of structural features of the '<em>Document Root</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT_FEATURE_COUNT = 53;

    /**
     * The meta object id for the '{@link net.ogdf.ogml.impl.EdgeConstraintTypeImpl <em>Edge Constraint Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.impl.EdgeConstraintTypeImpl
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getEdgeConstraintType()
     * @generated
     */
    int EDGE_CONSTRAINT_TYPE = 6;

    /**
     * The feature id for the '<em><b>Id Ref</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE_CONSTRAINT_TYPE__ID_REF = 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE_CONSTRAINT_TYPE__NAME = 1;

    /**
     * The number of structural features of the '<em>Edge Constraint Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE_CONSTRAINT_TYPE_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '{@link net.ogdf.ogml.impl.EdgeLayoutTypeImpl <em>Edge Layout Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.impl.EdgeLayoutTypeImpl
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getEdgeLayoutType()
     * @generated
     */
    int EDGE_LAYOUT_TYPE = 7;

    /**
     * The feature id for the '<em><b>Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE_LAYOUT_TYPE__DATA = 0;

    /**
     * The feature id for the '<em><b>Template</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE_LAYOUT_TYPE__TEMPLATE = 1;

    /**
     * The feature id for the '<em><b>Line</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE_LAYOUT_TYPE__LINE = 2;

    /**
     * The feature id for the '<em><b>Source Style</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE_LAYOUT_TYPE__SOURCE_STYLE = 3;

    /**
     * The feature id for the '<em><b>Target Style</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE_LAYOUT_TYPE__TARGET_STYLE = 4;

    /**
     * The feature id for the '<em><b>Point</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE_LAYOUT_TYPE__POINT = 5;

    /**
     * The feature id for the '<em><b>Segment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE_LAYOUT_TYPE__SEGMENT = 6;

    /**
     * The feature id for the '<em><b>Id Ref</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE_LAYOUT_TYPE__ID_REF = 7;

    /**
     * The number of structural features of the '<em>Edge Layout Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE_LAYOUT_TYPE_FEATURE_COUNT = 8;

    /**
     * The meta object id for the '{@link net.ogdf.ogml.impl.EdgeStyleTemplateTypeImpl <em>Edge Style Template Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.impl.EdgeStyleTemplateTypeImpl
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getEdgeStyleTemplateType()
     * @generated
     */
    int EDGE_STYLE_TEMPLATE_TYPE = 8;

    /**
     * The feature id for the '<em><b>Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE_STYLE_TEMPLATE_TYPE__DATA = 0;

    /**
     * The feature id for the '<em><b>Template</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE_STYLE_TEMPLATE_TYPE__TEMPLATE = 1;

    /**
     * The feature id for the '<em><b>Line</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE_STYLE_TEMPLATE_TYPE__LINE = 2;

    /**
     * The feature id for the '<em><b>Source Style</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE_STYLE_TEMPLATE_TYPE__SOURCE_STYLE = 3;

    /**
     * The feature id for the '<em><b>Target Style</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE_STYLE_TEMPLATE_TYPE__TARGET_STYLE = 4;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE_STYLE_TEMPLATE_TYPE__ID = 5;

    /**
     * The number of structural features of the '<em>Edge Style Template Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE_STYLE_TEMPLATE_TYPE_FEATURE_COUNT = 6;

    /**
     * The meta object id for the '{@link net.ogdf.ogml.impl.EdgeTypeImpl <em>Edge Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.impl.EdgeTypeImpl
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getEdgeType()
     * @generated
     */
    int EDGE_TYPE = 9;

    /**
     * The feature id for the '<em><b>Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE_TYPE__DATA = 0;

    /**
     * The feature id for the '<em><b>Label</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE_TYPE__LABEL = 1;

    /**
     * The feature id for the '<em><b>Group</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE_TYPE__GROUP = 2;

    /**
     * The feature id for the '<em><b>Source</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE_TYPE__SOURCE = 3;

    /**
     * The feature id for the '<em><b>Target</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE_TYPE__TARGET = 4;

    /**
     * The feature id for the '<em><b>Label1</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE_TYPE__LABEL1 = 5;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE_TYPE__ID = 6;

    /**
     * The number of structural features of the '<em>Edge Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE_TYPE_FEATURE_COUNT = 7;

    /**
     * The meta object id for the '{@link net.ogdf.ogml.impl.EndpointTypeImpl <em>Endpoint Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.impl.EndpointTypeImpl
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getEndpointType()
     * @generated
     */
    int ENDPOINT_TYPE = 10;

    /**
     * The feature id for the '<em><b>Color</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENDPOINT_TYPE__COLOR = 0;

    /**
     * The feature id for the '<em><b>Id Ref</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENDPOINT_TYPE__ID_REF = 1;

    /**
     * The feature id for the '<em><b>Port</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENDPOINT_TYPE__PORT = 2;

    /**
     * The feature id for the '<em><b>Size</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENDPOINT_TYPE__SIZE = 3;

    /**
     * The feature id for the '<em><b>Style</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENDPOINT_TYPE__STYLE = 4;

    /**
     * The number of structural features of the '<em>Endpoint Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENDPOINT_TYPE_FEATURE_COUNT = 5;

    /**
     * The meta object id for the '{@link net.ogdf.ogml.impl.FillTypeImpl <em>Fill Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.impl.FillTypeImpl
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getFillType()
     * @generated
     */
    int FILL_TYPE = 11;

    /**
     * The feature id for the '<em><b>Color</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FILL_TYPE__COLOR = 0;

    /**
     * The feature id for the '<em><b>Pattern</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FILL_TYPE__PATTERN = 1;

    /**
     * The feature id for the '<em><b>Pattern Color</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FILL_TYPE__PATTERN_COLOR = 2;

    /**
     * The number of structural features of the '<em>Fill Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FILL_TYPE_FEATURE_COUNT = 3;

    /**
     * The meta object id for the '{@link net.ogdf.ogml.impl.FontTypeImpl <em>Font Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.impl.FontTypeImpl
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getFontType()
     * @generated
     */
    int FONT_TYPE = 12;

    /**
     * The feature id for the '<em><b>Color</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FONT_TYPE__COLOR = 0;

    /**
     * The feature id for the '<em><b>Family</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FONT_TYPE__FAMILY = 1;

    /**
     * The feature id for the '<em><b>Size</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FONT_TYPE__SIZE = 2;

    /**
     * The feature id for the '<em><b>Stretch</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FONT_TYPE__STRETCH = 3;

    /**
     * The feature id for the '<em><b>Style</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FONT_TYPE__STYLE = 4;

    /**
     * The feature id for the '<em><b>Variant</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FONT_TYPE__VARIANT = 5;

    /**
     * The feature id for the '<em><b>Weight</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FONT_TYPE__WEIGHT = 6;

    /**
     * The number of structural features of the '<em>Font Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FONT_TYPE_FEATURE_COUNT = 7;

    /**
     * The meta object id for the '{@link net.ogdf.ogml.impl.GraphStyleTypeImpl <em>Graph Style Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.impl.GraphStyleTypeImpl
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getGraphStyleType()
     * @generated
     */
    int GRAPH_STYLE_TYPE = 13;

    /**
     * The feature id for the '<em><b>Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GRAPH_STYLE_TYPE__DATA = 0;

    /**
     * The feature id for the '<em><b>Default Edge Template</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GRAPH_STYLE_TYPE__DEFAULT_EDGE_TEMPLATE = 1;

    /**
     * The feature id for the '<em><b>Default Label Template</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GRAPH_STYLE_TYPE__DEFAULT_LABEL_TEMPLATE = 2;

    /**
     * The feature id for the '<em><b>Default Node Template</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GRAPH_STYLE_TYPE__DEFAULT_NODE_TEMPLATE = 3;

    /**
     * The number of structural features of the '<em>Graph Style Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GRAPH_STYLE_TYPE_FEATURE_COUNT = 4;

    /**
     * The meta object id for the '{@link net.ogdf.ogml.impl.GraphTypeImpl <em>Graph Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.impl.GraphTypeImpl
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getGraphType()
     * @generated
     */
    int GRAPH_TYPE = 14;

    /**
     * The feature id for the '<em><b>Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GRAPH_TYPE__DATA = 0;

    /**
     * The feature id for the '<em><b>Structure</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GRAPH_TYPE__STRUCTURE = 1;

    /**
     * The feature id for the '<em><b>Layout</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GRAPH_TYPE__LAYOUT = 2;

    /**
     * The number of structural features of the '<em>Graph Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GRAPH_TYPE_FEATURE_COUNT = 3;

    /**
     * The meta object id for the '{@link net.ogdf.ogml.impl.IntTypeImpl <em>Int Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.impl.IntTypeImpl
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getIntType()
     * @generated
     */
    int INT_TYPE = 15;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INT_TYPE__NAME = 0;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INT_TYPE__VALUE = 1;

    /**
     * The number of structural features of the '<em>Int Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INT_TYPE_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '{@link net.ogdf.ogml.impl.KeysTypeImpl <em>Keys Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.impl.KeysTypeImpl
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getKeysType()
     * @generated
     */
    int KEYS_TYPE = 16;

    /**
     * The feature id for the '<em><b>Key</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KEYS_TYPE__KEY = 0;

    /**
     * The number of structural features of the '<em>Keys Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KEYS_TYPE_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '{@link net.ogdf.ogml.impl.KeyTypeImpl <em>Key Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.impl.KeyTypeImpl
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getKeyType()
     * @generated
     */
    int KEY_TYPE = 17;

    /**
     * The feature id for the '<em><b>Key Value</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KEY_TYPE__KEY_VALUE = 0;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KEY_TYPE__ID = 1;

    /**
     * The number of structural features of the '<em>Key Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KEY_TYPE_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '{@link net.ogdf.ogml.impl.KeyValueTypeImpl <em>Key Value Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.impl.KeyValueTypeImpl
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getKeyValueType()
     * @generated
     */
    int KEY_VALUE_TYPE = 18;

    /**
     * The feature id for the '<em><b>Dafault</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KEY_VALUE_TYPE__DAFAULT = 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KEY_VALUE_TYPE__NAME = 1;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KEY_VALUE_TYPE__TYPE = 2;

    /**
     * The number of structural features of the '<em>Key Value Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KEY_VALUE_TYPE_FEATURE_COUNT = 3;

    /**
     * The meta object id for the '{@link net.ogdf.ogml.impl.LabelConstraintTypeImpl <em>Label Constraint Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.impl.LabelConstraintTypeImpl
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getLabelConstraintType()
     * @generated
     */
    int LABEL_CONSTRAINT_TYPE = 19;

    /**
     * The feature id for the '<em><b>Id Ref</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_CONSTRAINT_TYPE__ID_REF = 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_CONSTRAINT_TYPE__NAME = 1;

    /**
     * The number of structural features of the '<em>Label Constraint Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_CONSTRAINT_TYPE_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '{@link net.ogdf.ogml.impl.LabelLayoutTypeImpl <em>Label Layout Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.impl.LabelLayoutTypeImpl
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getLabelLayoutType()
     * @generated
     */
    int LABEL_LAYOUT_TYPE = 20;

    /**
     * The feature id for the '<em><b>Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_LAYOUT_TYPE__DATA = 0;

    /**
     * The feature id for the '<em><b>Template</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_LAYOUT_TYPE__TEMPLATE = 1;

    /**
     * The feature id for the '<em><b>Location</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_LAYOUT_TYPE__LOCATION = 2;

    /**
     * The feature id for the '<em><b>Text</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_LAYOUT_TYPE__TEXT = 3;

    /**
     * The feature id for the '<em><b>Font</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_LAYOUT_TYPE__FONT = 4;

    /**
     * The feature id for the '<em><b>Id Ref</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_LAYOUT_TYPE__ID_REF = 5;

    /**
     * The number of structural features of the '<em>Label Layout Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_LAYOUT_TYPE_FEATURE_COUNT = 6;

    /**
     * The meta object id for the '{@link net.ogdf.ogml.impl.LabelStyleTemplateTypeImpl <em>Label Style Template Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.impl.LabelStyleTemplateTypeImpl
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getLabelStyleTemplateType()
     * @generated
     */
    int LABEL_STYLE_TEMPLATE_TYPE = 21;

    /**
     * The feature id for the '<em><b>Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_STYLE_TEMPLATE_TYPE__DATA = 0;

    /**
     * The feature id for the '<em><b>Template</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_STYLE_TEMPLATE_TYPE__TEMPLATE = 1;

    /**
     * The feature id for the '<em><b>Text</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_STYLE_TEMPLATE_TYPE__TEXT = 2;

    /**
     * The feature id for the '<em><b>Font</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_STYLE_TEMPLATE_TYPE__FONT = 3;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_STYLE_TEMPLATE_TYPE__ID = 4;

    /**
     * The number of structural features of the '<em>Label Style Template Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_STYLE_TEMPLATE_TYPE_FEATURE_COUNT = 5;

    /**
     * The meta object id for the '{@link net.ogdf.ogml.impl.LabelTypeImpl <em>Label Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.impl.LabelTypeImpl
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getLabelType()
     * @generated
     */
    int LABEL_TYPE = 22;

    /**
     * The feature id for the '<em><b>Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_TYPE__DATA = 0;

    /**
     * The feature id for the '<em><b>Content</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_TYPE__CONTENT = 1;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_TYPE__ID = 2;

    /**
     * The number of structural features of the '<em>Label Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_TYPE_FEATURE_COUNT = 3;

    /**
     * The meta object id for the '{@link net.ogdf.ogml.impl.LayoutTypeImpl <em>Layout Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.impl.LayoutTypeImpl
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getLayoutType()
     * @generated
     */
    int LAYOUT_TYPE = 23;

    /**
     * The feature id for the '<em><b>Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LAYOUT_TYPE__DATA = 0;

    /**
     * The feature id for the '<em><b>Style Templates</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LAYOUT_TYPE__STYLE_TEMPLATES = 1;

    /**
     * The feature id for the '<em><b>Styles</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LAYOUT_TYPE__STYLES = 2;

    /**
     * The feature id for the '<em><b>Constraints</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LAYOUT_TYPE__CONSTRAINTS = 3;

    /**
     * The number of structural features of the '<em>Layout Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LAYOUT_TYPE_FEATURE_COUNT = 4;

    /**
     * The meta object id for the '{@link net.ogdf.ogml.impl.LineStyleTypeImpl <em>Line Style Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.impl.LineStyleTypeImpl
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getLineStyleType()
     * @generated
     */
    int LINE_STYLE_TYPE = 24;

    /**
     * The feature id for the '<em><b>Color</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LINE_STYLE_TYPE__COLOR = 0;

    /**
     * The feature id for the '<em><b>Style</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LINE_STYLE_TYPE__STYLE = 1;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LINE_STYLE_TYPE__WIDTH = 2;

    /**
     * The number of structural features of the '<em>Line Style Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LINE_STYLE_TYPE_FEATURE_COUNT = 3;

    /**
     * The meta object id for the '{@link net.ogdf.ogml.impl.LineTypeImpl <em>Line Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.impl.LineTypeImpl
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getLineType()
     * @generated
     */
    int LINE_TYPE = 25;

    /**
     * The feature id for the '<em><b>Color</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LINE_TYPE__COLOR = 0;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LINE_TYPE__TYPE = 1;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LINE_TYPE__WIDTH = 2;

    /**
     * The number of structural features of the '<em>Line Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LINE_TYPE_FEATURE_COUNT = 3;

    /**
     * The meta object id for the '{@link net.ogdf.ogml.impl.LocationTypeImpl <em>Location Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.impl.LocationTypeImpl
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getLocationType()
     * @generated
     */
    int LOCATION_TYPE = 26;

    /**
     * The feature id for the '<em><b>X</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LOCATION_TYPE__X = 0;

    /**
     * The feature id for the '<em><b>Y</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LOCATION_TYPE__Y = 1;

    /**
     * The feature id for the '<em><b>Z</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LOCATION_TYPE__Z = 2;

    /**
     * The number of structural features of the '<em>Location Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LOCATION_TYPE_FEATURE_COUNT = 3;

    /**
     * The meta object id for the '{@link net.ogdf.ogml.impl.NodeConstraintTypeImpl <em>Node Constraint Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.impl.NodeConstraintTypeImpl
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getNodeConstraintType()
     * @generated
     */
    int NODE_CONSTRAINT_TYPE = 27;

    /**
     * The feature id for the '<em><b>Id Ref</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE_CONSTRAINT_TYPE__ID_REF = 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE_CONSTRAINT_TYPE__NAME = 1;

    /**
     * The number of structural features of the '<em>Node Constraint Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE_CONSTRAINT_TYPE_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '{@link net.ogdf.ogml.impl.NodeLayoutTypeImpl <em>Node Layout Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.impl.NodeLayoutTypeImpl
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getNodeLayoutType()
     * @generated
     */
    int NODE_LAYOUT_TYPE = 28;

    /**
     * The feature id for the '<em><b>Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE_LAYOUT_TYPE__DATA = 0;

    /**
     * The feature id for the '<em><b>Template</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE_LAYOUT_TYPE__TEMPLATE = 1;

    /**
     * The feature id for the '<em><b>Location</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE_LAYOUT_TYPE__LOCATION = 2;

    /**
     * The feature id for the '<em><b>Shape</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE_LAYOUT_TYPE__SHAPE = 3;

    /**
     * The feature id for the '<em><b>Fill</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE_LAYOUT_TYPE__FILL = 4;

    /**
     * The feature id for the '<em><b>Line</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE_LAYOUT_TYPE__LINE = 5;

    /**
     * The feature id for the '<em><b>Id Ref</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE_LAYOUT_TYPE__ID_REF = 6;

    /**
     * The number of structural features of the '<em>Node Layout Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE_LAYOUT_TYPE_FEATURE_COUNT = 7;

    /**
     * The meta object id for the '{@link net.ogdf.ogml.impl.NodeStyleTemplateTypeImpl <em>Node Style Template Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.impl.NodeStyleTemplateTypeImpl
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getNodeStyleTemplateType()
     * @generated
     */
    int NODE_STYLE_TEMPLATE_TYPE = 29;

    /**
     * The feature id for the '<em><b>Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE_STYLE_TEMPLATE_TYPE__DATA = 0;

    /**
     * The feature id for the '<em><b>Template</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE_STYLE_TEMPLATE_TYPE__TEMPLATE = 1;

    /**
     * The feature id for the '<em><b>Shape</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE_STYLE_TEMPLATE_TYPE__SHAPE = 2;

    /**
     * The feature id for the '<em><b>Fill</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE_STYLE_TEMPLATE_TYPE__FILL = 3;

    /**
     * The feature id for the '<em><b>Line</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE_STYLE_TEMPLATE_TYPE__LINE = 4;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE_STYLE_TEMPLATE_TYPE__ID = 5;

    /**
     * The number of structural features of the '<em>Node Style Template Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE_STYLE_TEMPLATE_TYPE_FEATURE_COUNT = 6;

    /**
     * The meta object id for the '{@link net.ogdf.ogml.impl.NodeTypeImpl <em>Node Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.impl.NodeTypeImpl
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getNodeType()
     * @generated
     */
    int NODE_TYPE = 30;

    /**
     * The feature id for the '<em><b>Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE_TYPE__DATA = 0;

    /**
     * The feature id for the '<em><b>Group</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE_TYPE__GROUP = 1;

    /**
     * The feature id for the '<em><b>Label</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE_TYPE__LABEL = 2;

    /**
     * The feature id for the '<em><b>Node</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE_TYPE__NODE = 3;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE_TYPE__ID = 4;

    /**
     * The number of structural features of the '<em>Node Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE_TYPE_FEATURE_COUNT = 5;

    /**
     * The meta object id for the '{@link net.ogdf.ogml.impl.NumberTypeImpl <em>Number Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.impl.NumberTypeImpl
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getNumberType()
     * @generated
     */
    int NUMBER_TYPE = 31;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NUMBER_TYPE__NAME = 0;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NUMBER_TYPE__VALUE = 1;

    /**
     * The number of structural features of the '<em>Number Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NUMBER_TYPE_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '{@link net.ogdf.ogml.impl.OgmlTypeImpl <em>Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.impl.OgmlTypeImpl
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getOgmlType()
     * @generated
     */
    int OGML_TYPE = 32;

    /**
     * The feature id for the '<em><b>Keys</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OGML_TYPE__KEYS = 0;

    /**
     * The feature id for the '<em><b>Graph</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OGML_TYPE__GRAPH = 1;

    /**
     * The number of structural features of the '<em>Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OGML_TYPE_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '{@link net.ogdf.ogml.impl.PointTypeImpl <em>Point Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.impl.PointTypeImpl
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getPointType()
     * @generated
     */
    int POINT_TYPE = 33;

    /**
     * The feature id for the '<em><b>Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POINT_TYPE__DATA = 0;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POINT_TYPE__ID = 1;

    /**
     * The feature id for the '<em><b>X</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POINT_TYPE__X = 2;

    /**
     * The feature id for the '<em><b>Y</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POINT_TYPE__Y = 3;

    /**
     * The feature id for the '<em><b>Z</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POINT_TYPE__Z = 4;

    /**
     * The number of structural features of the '<em>Point Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POINT_TYPE_FEATURE_COUNT = 5;

    /**
     * The meta object id for the '{@link net.ogdf.ogml.impl.SegmentTypeImpl <em>Segment Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.impl.SegmentTypeImpl
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getSegmentType()
     * @generated
     */
    int SEGMENT_TYPE = 34;

    /**
     * The feature id for the '<em><b>Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SEGMENT_TYPE__DATA = 0;

    /**
     * The feature id for the '<em><b>Line</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SEGMENT_TYPE__LINE = 1;

    /**
     * The feature id for the '<em><b>Endpoint</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SEGMENT_TYPE__ENDPOINT = 2;

    /**
     * The number of structural features of the '<em>Segment Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SEGMENT_TYPE_FEATURE_COUNT = 3;

    /**
     * The meta object id for the '{@link net.ogdf.ogml.impl.ShapeType1Impl <em>Shape Type1</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.impl.ShapeType1Impl
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getShapeType1()
     * @generated
     */
    int SHAPE_TYPE1 = 35;

    /**
     * The feature id for the '<em><b>Height</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SHAPE_TYPE1__HEIGHT = 0;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SHAPE_TYPE1__TYPE = 1;

    /**
     * The feature id for the '<em><b>Uri</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SHAPE_TYPE1__URI = 2;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SHAPE_TYPE1__WIDTH = 3;

    /**
     * The number of structural features of the '<em>Shape Type1</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SHAPE_TYPE1_FEATURE_COUNT = 4;

    /**
     * The meta object id for the '{@link net.ogdf.ogml.impl.SourceStyleTypeImpl <em>Source Style Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.impl.SourceStyleTypeImpl
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getSourceStyleType()
     * @generated
     */
    int SOURCE_STYLE_TYPE = 36;

    /**
     * The feature id for the '<em><b>Color</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SOURCE_STYLE_TYPE__COLOR = 0;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SOURCE_STYLE_TYPE__TYPE = 1;

    /**
     * The feature id for the '<em><b>Uri</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SOURCE_STYLE_TYPE__URI = 2;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SOURCE_STYLE_TYPE__WIDTH = 3;

    /**
     * The number of structural features of the '<em>Source Style Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SOURCE_STYLE_TYPE_FEATURE_COUNT = 4;

    /**
     * The meta object id for the '{@link net.ogdf.ogml.impl.SourceTargetTypeImpl <em>Source Target Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.impl.SourceTargetTypeImpl
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getSourceTargetType()
     * @generated
     */
    int SOURCE_TARGET_TYPE = 37;

    /**
     * The feature id for the '<em><b>Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SOURCE_TARGET_TYPE__DATA = 0;

    /**
     * The feature id for the '<em><b>Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SOURCE_TARGET_TYPE__LABEL = 1;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SOURCE_TARGET_TYPE__ID = 2;

    /**
     * The feature id for the '<em><b>Id Ref</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SOURCE_TARGET_TYPE__ID_REF = 3;

    /**
     * The number of structural features of the '<em>Source Target Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SOURCE_TARGET_TYPE_FEATURE_COUNT = 4;

    /**
     * The meta object id for the '{@link net.ogdf.ogml.impl.StructureTypeImpl <em>Structure Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.impl.StructureTypeImpl
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getStructureType()
     * @generated
     */
    int STRUCTURE_TYPE = 38;

    /**
     * The feature id for the '<em><b>Group</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STRUCTURE_TYPE__GROUP = 0;

    /**
     * The feature id for the '<em><b>Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STRUCTURE_TYPE__DATA = 1;

    /**
     * The feature id for the '<em><b>Node</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STRUCTURE_TYPE__NODE = 2;

    /**
     * The feature id for the '<em><b>Edge</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STRUCTURE_TYPE__EDGE = 3;

    /**
     * The feature id for the '<em><b>Label</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STRUCTURE_TYPE__LABEL = 4;

    /**
     * The number of structural features of the '<em>Structure Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STRUCTURE_TYPE_FEATURE_COUNT = 5;

    /**
     * The meta object id for the '{@link net.ogdf.ogml.impl.StylesTypeImpl <em>Styles Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.impl.StylesTypeImpl
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getStylesType()
     * @generated
     */
    int STYLES_TYPE = 39;

    /**
     * The feature id for the '<em><b>Graph Style</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STYLES_TYPE__GRAPH_STYLE = 0;

    /**
     * The feature id for the '<em><b>Group</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STYLES_TYPE__GROUP = 1;

    /**
     * The feature id for the '<em><b>Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STYLES_TYPE__DATA = 2;

    /**
     * The feature id for the '<em><b>Node Style</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STYLES_TYPE__NODE_STYLE = 3;

    /**
     * The feature id for the '<em><b>Edge Style</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STYLES_TYPE__EDGE_STYLE = 4;

    /**
     * The feature id for the '<em><b>Label Style</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STYLES_TYPE__LABEL_STYLE = 5;

    /**
     * The number of structural features of the '<em>Styles Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STYLES_TYPE_FEATURE_COUNT = 6;

    /**
     * The meta object id for the '{@link net.ogdf.ogml.impl.StyleTemplatesTypeImpl <em>Style Templates Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.impl.StyleTemplatesTypeImpl
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getStyleTemplatesType()
     * @generated
     */
    int STYLE_TEMPLATES_TYPE = 40;

    /**
     * The feature id for the '<em><b>Group</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STYLE_TEMPLATES_TYPE__GROUP = 0;

    /**
     * The feature id for the '<em><b>Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STYLE_TEMPLATES_TYPE__DATA = 1;

    /**
     * The feature id for the '<em><b>Node Style Template</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STYLE_TEMPLATES_TYPE__NODE_STYLE_TEMPLATE = 2;

    /**
     * The feature id for the '<em><b>Edge Style Template</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STYLE_TEMPLATES_TYPE__EDGE_STYLE_TEMPLATE = 3;

    /**
     * The feature id for the '<em><b>Label Style Template</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STYLE_TEMPLATES_TYPE__LABEL_STYLE_TEMPLATE = 4;

    /**
     * The number of structural features of the '<em>Style Templates Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STYLE_TEMPLATES_TYPE_FEATURE_COUNT = 5;

    /**
     * The meta object id for the '{@link net.ogdf.ogml.impl.TargetStyleTypeImpl <em>Target Style Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.impl.TargetStyleTypeImpl
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getTargetStyleType()
     * @generated
     */
    int TARGET_STYLE_TYPE = 41;

    /**
     * The feature id for the '<em><b>Color</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TARGET_STYLE_TYPE__COLOR = 0;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TARGET_STYLE_TYPE__TYPE = 1;

    /**
     * The feature id for the '<em><b>Uri</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TARGET_STYLE_TYPE__URI = 2;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TARGET_STYLE_TYPE__WIDTH = 3;

    /**
     * The number of structural features of the '<em>Target Style Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TARGET_STYLE_TYPE_FEATURE_COUNT = 4;

    /**
     * The meta object id for the '{@link net.ogdf.ogml.impl.TemplateTypeImpl <em>Template Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.impl.TemplateTypeImpl
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getTemplateType()
     * @generated
     */
    int TEMPLATE_TYPE = 42;

    /**
     * The feature id for the '<em><b>Id Ref</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEMPLATE_TYPE__ID_REF = 0;

    /**
     * The number of structural features of the '<em>Template Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEMPLATE_TYPE_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '{@link net.ogdf.ogml.impl.TextTypeImpl <em>Text Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.impl.TextTypeImpl
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getTextType()
     * @generated
     */
    int TEXT_TYPE = 43;

    /**
     * The feature id for the '<em><b>Alignment</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEXT_TYPE__ALIGNMENT = 0;

    /**
     * The feature id for the '<em><b>Decoration</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEXT_TYPE__DECORATION = 1;

    /**
     * The feature id for the '<em><b>Rotation</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEXT_TYPE__ROTATION = 2;

    /**
     * The feature id for the '<em><b>Transform</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEXT_TYPE__TRANSFORM = 3;

    /**
     * The number of structural features of the '<em>Text Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEXT_TYPE_FEATURE_COUNT = 4;

    /**
     * The meta object id for the '{@link net.ogdf.ogml.impl.UriTypeImpl <em>Uri Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.impl.UriTypeImpl
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getUriType()
     * @generated
     */
    int URI_TYPE = 44;

    /**
     * The feature id for the '<em><b>Uri</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int URI_TYPE__URI = 0;

    /**
     * The number of structural features of the '<em>Uri Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int URI_TYPE_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '{@link net.ogdf.ogml.AlignmentType <em>Alignment Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.AlignmentType
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getAlignmentType()
     * @generated
     */
    int ALIGNMENT_TYPE = 45;

    /**
     * The meta object id for the '{@link net.ogdf.ogml.BoolIntType <em>Bool Int Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.BoolIntType
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getBoolIntType()
     * @generated
     */
    int BOOL_INT_TYPE = 46;

    /**
     * The meta object id for the '{@link net.ogdf.ogml.BoolTFType <em>Bool TF Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.BoolTFType
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getBoolTFType()
     * @generated
     */
    int BOOL_TF_TYPE = 47;

    /**
     * The meta object id for the '{@link net.ogdf.ogml.DecorationType <em>Decoration Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.DecorationType
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getDecorationType()
     * @generated
     */
    int DECORATION_TYPE = 48;

    /**
     * The meta object id for the '{@link net.ogdf.ogml.EndpointStylesType <em>Endpoint Styles Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.EndpointStylesType
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getEndpointStylesType()
     * @generated
     */
    int ENDPOINT_STYLES_TYPE = 49;

    /**
     * The meta object id for the '{@link net.ogdf.ogml.FontStretchType <em>Font Stretch Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.FontStretchType
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getFontStretchType()
     * @generated
     */
    int FONT_STRETCH_TYPE = 50;

    /**
     * The meta object id for the '{@link net.ogdf.ogml.FontStyleType <em>Font Style Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.FontStyleType
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getFontStyleType()
     * @generated
     */
    int FONT_STYLE_TYPE = 51;

    /**
     * The meta object id for the '{@link net.ogdf.ogml.FontVariantType <em>Font Variant Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.FontVariantType
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getFontVariantType()
     * @generated
     */
    int FONT_VARIANT_TYPE = 52;

    /**
     * The meta object id for the '{@link net.ogdf.ogml.FontWeightType <em>Font Weight Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.FontWeightType
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getFontWeightType()
     * @generated
     */
    int FONT_WEIGHT_TYPE = 53;

    /**
     * The meta object id for the '{@link net.ogdf.ogml.LineStyleTypeType <em>Line Style Type Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.LineStyleTypeType
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getLineStyleTypeType()
     * @generated
     */
    int LINE_STYLE_TYPE_TYPE = 54;

    /**
     * The meta object id for the '{@link net.ogdf.ogml.PatternType <em>Pattern Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.PatternType
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getPatternType()
     * @generated
     */
    int PATTERN_TYPE = 55;

    /**
     * The meta object id for the '{@link net.ogdf.ogml.ShapeType <em>Shape Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.ShapeType
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getShapeType()
     * @generated
     */
    int SHAPE_TYPE = 56;

    /**
     * The meta object id for the '{@link net.ogdf.ogml.TransformType <em>Transform Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.TransformType
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getTransformType()
     * @generated
     */
    int TRANSFORM_TYPE = 57;

    /**
     * The meta object id for the '<em>Alignment Type Object</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.AlignmentType
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getAlignmentTypeObject()
     * @generated
     */
    int ALIGNMENT_TYPE_OBJECT = 58;

    /**
     * The meta object id for the '<em>Bool Int Type Object</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.BoolIntType
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getBoolIntTypeObject()
     * @generated
     */
    int BOOL_INT_TYPE_OBJECT = 59;

    /**
     * The meta object id for the '<em>Bool TF Type Object</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.BoolTFType
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getBoolTFTypeObject()
     * @generated
     */
    int BOOL_TF_TYPE_OBJECT = 60;

    /**
     * The meta object id for the '<em>Decoration Type Object</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.DecorationType
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getDecorationTypeObject()
     * @generated
     */
    int DECORATION_TYPE_OBJECT = 61;

    /**
     * The meta object id for the '<em>Endpoint Styles Type Object</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.EndpointStylesType
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getEndpointStylesTypeObject()
     * @generated
     */
    int ENDPOINT_STYLES_TYPE_OBJECT = 62;

    /**
     * The meta object id for the '<em>Font Stretch Type Object</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.FontStretchType
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getFontStretchTypeObject()
     * @generated
     */
    int FONT_STRETCH_TYPE_OBJECT = 63;

    /**
     * The meta object id for the '<em>Font Style Type Object</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.FontStyleType
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getFontStyleTypeObject()
     * @generated
     */
    int FONT_STYLE_TYPE_OBJECT = 64;

    /**
     * The meta object id for the '<em>Font Variant Type Object</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.FontVariantType
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getFontVariantTypeObject()
     * @generated
     */
    int FONT_VARIANT_TYPE_OBJECT = 65;

    /**
     * The meta object id for the '<em>Font Weight Type Object</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.FontWeightType
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getFontWeightTypeObject()
     * @generated
     */
    int FONT_WEIGHT_TYPE_OBJECT = 66;

    /**
     * The meta object id for the '<em>Line Style Type Type Object</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.LineStyleTypeType
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getLineStyleTypeTypeObject()
     * @generated
     */
    int LINE_STYLE_TYPE_TYPE_OBJECT = 67;

    /**
     * The meta object id for the '<em>Pattern Type Object</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.PatternType
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getPatternTypeObject()
     * @generated
     */
    int PATTERN_TYPE_OBJECT = 68;

    /**
     * The meta object id for the '<em>Shape Type Object</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.ShapeType
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getShapeTypeObject()
     * @generated
     */
    int SHAPE_TYPE_OBJECT = 69;

    /**
     * The meta object id for the '<em>Transform Type Object</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.ogdf.ogml.TransformType
     * @see net.ogdf.ogml.impl.OgmlPackageImpl#getTransformTypeObject()
     * @generated
     */
    int TRANSFORM_TYPE_OBJECT = 70;


    /**
     * Returns the meta object for class '{@link net.ogdf.ogml.BooleanType <em>Boolean Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Boolean Type</em>'.
     * @see net.ogdf.ogml.BooleanType
     * @generated
     */
    EClass getBooleanType();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.BooleanType#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see net.ogdf.ogml.BooleanType#getName()
     * @see #getBooleanType()
     * @generated
     */
    EAttribute getBooleanType_Name();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.BooleanType#getValue <em>Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see net.ogdf.ogml.BooleanType#getValue()
     * @see #getBooleanType()
     * @generated
     */
    EAttribute getBooleanType_Value();

    /**
     * Returns the meta object for class '{@link net.ogdf.ogml.BoolType <em>Bool Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Bool Type</em>'.
     * @see net.ogdf.ogml.BoolType
     * @generated
     */
    EClass getBoolType();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.BoolType#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see net.ogdf.ogml.BoolType#getName()
     * @see #getBoolType()
     * @generated
     */
    EAttribute getBoolType_Name();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.BoolType#getValue <em>Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see net.ogdf.ogml.BoolType#getValue()
     * @see #getBoolType()
     * @generated
     */
    EAttribute getBoolType_Value();

    /**
     * Returns the meta object for class '{@link net.ogdf.ogml.ComposedType <em>Composed Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Composed Type</em>'.
     * @see net.ogdf.ogml.ComposedType
     * @generated
     */
    EClass getComposedType();

    /**
     * Returns the meta object for the attribute list '{@link net.ogdf.ogml.ComposedType#getGroup <em>Group</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Group</em>'.
     * @see net.ogdf.ogml.ComposedType#getGroup()
     * @see #getComposedType()
     * @generated
     */
    EAttribute getComposedType_Group();

    /**
     * Returns the meta object for the containment reference list '{@link net.ogdf.ogml.ComposedType#getNumber <em>Number</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Number</em>'.
     * @see net.ogdf.ogml.ComposedType#getNumber()
     * @see #getComposedType()
     * @generated
     */
    EReference getComposedType_Number();

    /**
     * Returns the meta object for the containment reference list '{@link net.ogdf.ogml.ComposedType#getInt <em>Int</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Int</em>'.
     * @see net.ogdf.ogml.ComposedType#getInt()
     * @see #getComposedType()
     * @generated
     */
    EReference getComposedType_Int();

    /**
     * Returns the meta object for the containment reference list '{@link net.ogdf.ogml.ComposedType#getBoolean <em>Boolean</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Boolean</em>'.
     * @see net.ogdf.ogml.ComposedType#getBoolean()
     * @see #getComposedType()
     * @generated
     */
    EReference getComposedType_Boolean();

    /**
     * Returns the meta object for the containment reference list '{@link net.ogdf.ogml.ComposedType#getNode <em>Node</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Node</em>'.
     * @see net.ogdf.ogml.ComposedType#getNode()
     * @see #getComposedType()
     * @generated
     */
    EReference getComposedType_Node();

    /**
     * Returns the meta object for the containment reference list '{@link net.ogdf.ogml.ComposedType#getEdge <em>Edge</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Edge</em>'.
     * @see net.ogdf.ogml.ComposedType#getEdge()
     * @see #getComposedType()
     * @generated
     */
    EReference getComposedType_Edge();

    /**
     * Returns the meta object for the containment reference list '{@link net.ogdf.ogml.ComposedType#getLabel <em>Label</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Label</em>'.
     * @see net.ogdf.ogml.ComposedType#getLabel()
     * @see #getComposedType()
     * @generated
     */
    EReference getComposedType_Label();

    /**
     * Returns the meta object for the containment reference list '{@link net.ogdf.ogml.ComposedType#getComposed <em>Composed</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Composed</em>'.
     * @see net.ogdf.ogml.ComposedType#getComposed()
     * @see #getComposedType()
     * @generated
     */
    EReference getComposedType_Composed();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.ComposedType#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see net.ogdf.ogml.ComposedType#getName()
     * @see #getComposedType()
     * @generated
     */
    EAttribute getComposedType_Name();

    /**
     * Returns the meta object for class '{@link net.ogdf.ogml.ConstraintsType <em>Constraints Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Constraints Type</em>'.
     * @see net.ogdf.ogml.ConstraintsType
     * @generated
     */
    EClass getConstraintsType();

    /**
     * Returns the meta object for the attribute list '{@link net.ogdf.ogml.ConstraintsType#getGroup <em>Group</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Group</em>'.
     * @see net.ogdf.ogml.ConstraintsType#getGroup()
     * @see #getConstraintsType()
     * @generated
     */
    EAttribute getConstraintsType_Group();

    /**
     * Returns the meta object for the containment reference list '{@link net.ogdf.ogml.ConstraintsType#getNumber <em>Number</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Number</em>'.
     * @see net.ogdf.ogml.ConstraintsType#getNumber()
     * @see #getConstraintsType()
     * @generated
     */
    EReference getConstraintsType_Number();

    /**
     * Returns the meta object for the containment reference list '{@link net.ogdf.ogml.ConstraintsType#getInt <em>Int</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Int</em>'.
     * @see net.ogdf.ogml.ConstraintsType#getInt()
     * @see #getConstraintsType()
     * @generated
     */
    EReference getConstraintsType_Int();

    /**
     * Returns the meta object for the containment reference list '{@link net.ogdf.ogml.ConstraintsType#getBoolean <em>Boolean</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Boolean</em>'.
     * @see net.ogdf.ogml.ConstraintsType#getBoolean()
     * @see #getConstraintsType()
     * @generated
     */
    EReference getConstraintsType_Boolean();

    /**
     * Returns the meta object for the containment reference list '{@link net.ogdf.ogml.ConstraintsType#getNodeRef <em>Node Ref</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Node Ref</em>'.
     * @see net.ogdf.ogml.ConstraintsType#getNodeRef()
     * @see #getConstraintsType()
     * @generated
     */
    EReference getConstraintsType_NodeRef();

    /**
     * Returns the meta object for the containment reference list '{@link net.ogdf.ogml.ConstraintsType#getEdgeRef <em>Edge Ref</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Edge Ref</em>'.
     * @see net.ogdf.ogml.ConstraintsType#getEdgeRef()
     * @see #getConstraintsType()
     * @generated
     */
    EReference getConstraintsType_EdgeRef();

    /**
     * Returns the meta object for the containment reference list '{@link net.ogdf.ogml.ConstraintsType#getLabelRef <em>Label Ref</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Label Ref</em>'.
     * @see net.ogdf.ogml.ConstraintsType#getLabelRef()
     * @see #getConstraintsType()
     * @generated
     */
    EReference getConstraintsType_LabelRef();

    /**
     * Returns the meta object for the containment reference list '{@link net.ogdf.ogml.ConstraintsType#getComposed <em>Composed</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Composed</em>'.
     * @see net.ogdf.ogml.ConstraintsType#getComposed()
     * @see #getConstraintsType()
     * @generated
     */
    EReference getConstraintsType_Composed();

    /**
     * Returns the meta object for class '{@link net.ogdf.ogml.DataType <em>Data Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Data Type</em>'.
     * @see net.ogdf.ogml.DataType
     * @generated
     */
    EClass getDataType();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.DataType#getValue <em>Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see net.ogdf.ogml.DataType#getValue()
     * @see #getDataType()
     * @generated
     */
    EAttribute getDataType_Value();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.DataType#getIdRef <em>Id Ref</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id Ref</em>'.
     * @see net.ogdf.ogml.DataType#getIdRef()
     * @see #getDataType()
     * @generated
     */
    EAttribute getDataType_IdRef();

    /**
     * Returns the meta object for class '{@link net.ogdf.ogml.DocumentRoot <em>Document Root</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Document Root</em>'.
     * @see net.ogdf.ogml.DocumentRoot
     * @generated
     */
    EClass getDocumentRoot();

    /**
     * Returns the meta object for the attribute list '{@link net.ogdf.ogml.DocumentRoot#getMixed <em>Mixed</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Mixed</em>'.
     * @see net.ogdf.ogml.DocumentRoot#getMixed()
     * @see #getDocumentRoot()
     * @generated
     */
    EAttribute getDocumentRoot_Mixed();

    /**
     * Returns the meta object for the map '{@link net.ogdf.ogml.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the map '<em>XMLNS Prefix Map</em>'.
     * @see net.ogdf.ogml.DocumentRoot#getXMLNSPrefixMap()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_XMLNSPrefixMap();

    /**
     * Returns the meta object for the map '{@link net.ogdf.ogml.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the map '<em>XSI Schema Location</em>'.
     * @see net.ogdf.ogml.DocumentRoot#getXSISchemaLocation()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_XSISchemaLocation();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.DocumentRoot#getBool <em>Bool</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Bool</em>'.
     * @see net.ogdf.ogml.DocumentRoot#getBool()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Bool();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.DocumentRoot#getBoolean <em>Boolean</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Boolean</em>'.
     * @see net.ogdf.ogml.DocumentRoot#getBoolean()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Boolean();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.DocumentRoot#getComposed <em>Composed</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Composed</em>'.
     * @see net.ogdf.ogml.DocumentRoot#getComposed()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Composed();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.DocumentRoot#getConstraints <em>Constraints</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Constraints</em>'.
     * @see net.ogdf.ogml.DocumentRoot#getConstraints()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Constraints();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.DocumentRoot#getData <em>Data</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Data</em>'.
     * @see net.ogdf.ogml.DocumentRoot#getData()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Data();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.DocumentRoot#getEdge <em>Edge</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Edge</em>'.
     * @see net.ogdf.ogml.DocumentRoot#getEdge()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Edge();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.DocumentRoot#getEdgeConstraint <em>Edge Constraint</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Edge Constraint</em>'.
     * @see net.ogdf.ogml.DocumentRoot#getEdgeConstraint()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_EdgeConstraint();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.DocumentRoot#getEdgeLayout <em>Edge Layout</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Edge Layout</em>'.
     * @see net.ogdf.ogml.DocumentRoot#getEdgeLayout()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_EdgeLayout();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.DocumentRoot#getEdgeStyleTemplate <em>Edge Style Template</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Edge Style Template</em>'.
     * @see net.ogdf.ogml.DocumentRoot#getEdgeStyleTemplate()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_EdgeStyleTemplate();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.DocumentRoot#getEndpoint <em>Endpoint</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Endpoint</em>'.
     * @see net.ogdf.ogml.DocumentRoot#getEndpoint()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Endpoint();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.DocumentRoot#getFill <em>Fill</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Fill</em>'.
     * @see net.ogdf.ogml.DocumentRoot#getFill()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Fill();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.DocumentRoot#getFont <em>Font</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Font</em>'.
     * @see net.ogdf.ogml.DocumentRoot#getFont()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Font();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.DocumentRoot#getFontStretch <em>Font Stretch</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Font Stretch</em>'.
     * @see net.ogdf.ogml.DocumentRoot#getFontStretch()
     * @see #getDocumentRoot()
     * @generated
     */
    EAttribute getDocumentRoot_FontStretch();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.DocumentRoot#getFontStyle <em>Font Style</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Font Style</em>'.
     * @see net.ogdf.ogml.DocumentRoot#getFontStyle()
     * @see #getDocumentRoot()
     * @generated
     */
    EAttribute getDocumentRoot_FontStyle();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.DocumentRoot#getFontVariant <em>Font Variant</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Font Variant</em>'.
     * @see net.ogdf.ogml.DocumentRoot#getFontVariant()
     * @see #getDocumentRoot()
     * @generated
     */
    EAttribute getDocumentRoot_FontVariant();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.DocumentRoot#getFontWeight <em>Font Weight</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Font Weight</em>'.
     * @see net.ogdf.ogml.DocumentRoot#getFontWeight()
     * @see #getDocumentRoot()
     * @generated
     */
    EAttribute getDocumentRoot_FontWeight();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.DocumentRoot#getGraph <em>Graph</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Graph</em>'.
     * @see net.ogdf.ogml.DocumentRoot#getGraph()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Graph();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.DocumentRoot#getGraphStyle <em>Graph Style</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Graph Style</em>'.
     * @see net.ogdf.ogml.DocumentRoot#getGraphStyle()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_GraphStyle();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.DocumentRoot#getInt <em>Int</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Int</em>'.
     * @see net.ogdf.ogml.DocumentRoot#getInt()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Int();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.DocumentRoot#getKey <em>Key</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Key</em>'.
     * @see net.ogdf.ogml.DocumentRoot#getKey()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Key();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.DocumentRoot#getKeys <em>Keys</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Keys</em>'.
     * @see net.ogdf.ogml.DocumentRoot#getKeys()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Keys();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.DocumentRoot#getKeyValue <em>Key Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Key Value</em>'.
     * @see net.ogdf.ogml.DocumentRoot#getKeyValue()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_KeyValue();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.DocumentRoot#getLabel <em>Label</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Label</em>'.
     * @see net.ogdf.ogml.DocumentRoot#getLabel()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Label();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.DocumentRoot#getLabelConstraint <em>Label Constraint</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Label Constraint</em>'.
     * @see net.ogdf.ogml.DocumentRoot#getLabelConstraint()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_LabelConstraint();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.DocumentRoot#getLabelLayout <em>Label Layout</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Label Layout</em>'.
     * @see net.ogdf.ogml.DocumentRoot#getLabelLayout()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_LabelLayout();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.DocumentRoot#getLabelStyleTemplate <em>Label Style Template</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Label Style Template</em>'.
     * @see net.ogdf.ogml.DocumentRoot#getLabelStyleTemplate()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_LabelStyleTemplate();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.DocumentRoot#getLayout <em>Layout</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Layout</em>'.
     * @see net.ogdf.ogml.DocumentRoot#getLayout()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Layout();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.DocumentRoot#getLine <em>Line</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Line</em>'.
     * @see net.ogdf.ogml.DocumentRoot#getLine()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Line();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.DocumentRoot#getLineStyle <em>Line Style</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Line Style</em>'.
     * @see net.ogdf.ogml.DocumentRoot#getLineStyle()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_LineStyle();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.DocumentRoot#getLineStyleType <em>Line Style Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Line Style Type</em>'.
     * @see net.ogdf.ogml.DocumentRoot#getLineStyleType()
     * @see #getDocumentRoot()
     * @generated
     */
    EAttribute getDocumentRoot_LineStyleType();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.DocumentRoot#getLocation <em>Location</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Location</em>'.
     * @see net.ogdf.ogml.DocumentRoot#getLocation()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Location();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.DocumentRoot#getNode <em>Node</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Node</em>'.
     * @see net.ogdf.ogml.DocumentRoot#getNode()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Node();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.DocumentRoot#getNodeConstraint <em>Node Constraint</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Node Constraint</em>'.
     * @see net.ogdf.ogml.DocumentRoot#getNodeConstraint()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_NodeConstraint();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.DocumentRoot#getNodeLayout <em>Node Layout</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Node Layout</em>'.
     * @see net.ogdf.ogml.DocumentRoot#getNodeLayout()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_NodeLayout();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.DocumentRoot#getNodeStyleTemplate <em>Node Style Template</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Node Style Template</em>'.
     * @see net.ogdf.ogml.DocumentRoot#getNodeStyleTemplate()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_NodeStyleTemplate();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.DocumentRoot#getNumber <em>Number</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Number</em>'.
     * @see net.ogdf.ogml.DocumentRoot#getNumber()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Number();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.DocumentRoot#getOgml <em>Ogml</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Ogml</em>'.
     * @see net.ogdf.ogml.DocumentRoot#getOgml()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Ogml();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.DocumentRoot#getPattern <em>Pattern</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Pattern</em>'.
     * @see net.ogdf.ogml.DocumentRoot#getPattern()
     * @see #getDocumentRoot()
     * @generated
     */
    EAttribute getDocumentRoot_Pattern();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.DocumentRoot#getPoint <em>Point</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Point</em>'.
     * @see net.ogdf.ogml.DocumentRoot#getPoint()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Point();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.DocumentRoot#getSegment <em>Segment</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Segment</em>'.
     * @see net.ogdf.ogml.DocumentRoot#getSegment()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Segment();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.DocumentRoot#getShape <em>Shape</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Shape</em>'.
     * @see net.ogdf.ogml.DocumentRoot#getShape()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Shape();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.DocumentRoot#getSourceStyle <em>Source Style</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Source Style</em>'.
     * @see net.ogdf.ogml.DocumentRoot#getSourceStyle()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_SourceStyle();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.DocumentRoot#getSourceTarget <em>Source Target</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Source Target</em>'.
     * @see net.ogdf.ogml.DocumentRoot#getSourceTarget()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_SourceTarget();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.DocumentRoot#getStructure <em>Structure</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Structure</em>'.
     * @see net.ogdf.ogml.DocumentRoot#getStructure()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Structure();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.DocumentRoot#getStyles <em>Styles</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Styles</em>'.
     * @see net.ogdf.ogml.DocumentRoot#getStyles()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Styles();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.DocumentRoot#getStyleTemplates <em>Style Templates</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Style Templates</em>'.
     * @see net.ogdf.ogml.DocumentRoot#getStyleTemplates()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_StyleTemplates();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.DocumentRoot#getTargetStyle <em>Target Style</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Target Style</em>'.
     * @see net.ogdf.ogml.DocumentRoot#getTargetStyle()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_TargetStyle();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.DocumentRoot#getTemplate <em>Template</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Template</em>'.
     * @see net.ogdf.ogml.DocumentRoot#getTemplate()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Template();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.DocumentRoot#getText <em>Text</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Text</em>'.
     * @see net.ogdf.ogml.DocumentRoot#getText()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Text();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.DocumentRoot#getUri <em>Uri</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Uri</em>'.
     * @see net.ogdf.ogml.DocumentRoot#getUri()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Uri();

    /**
     * Returns the meta object for class '{@link net.ogdf.ogml.EdgeConstraintType <em>Edge Constraint Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Edge Constraint Type</em>'.
     * @see net.ogdf.ogml.EdgeConstraintType
     * @generated
     */
    EClass getEdgeConstraintType();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.EdgeConstraintType#getIdRef <em>Id Ref</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id Ref</em>'.
     * @see net.ogdf.ogml.EdgeConstraintType#getIdRef()
     * @see #getEdgeConstraintType()
     * @generated
     */
    EAttribute getEdgeConstraintType_IdRef();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.EdgeConstraintType#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see net.ogdf.ogml.EdgeConstraintType#getName()
     * @see #getEdgeConstraintType()
     * @generated
     */
    EAttribute getEdgeConstraintType_Name();

    /**
     * Returns the meta object for class '{@link net.ogdf.ogml.EdgeLayoutType <em>Edge Layout Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Edge Layout Type</em>'.
     * @see net.ogdf.ogml.EdgeLayoutType
     * @generated
     */
    EClass getEdgeLayoutType();

    /**
     * Returns the meta object for the containment reference list '{@link net.ogdf.ogml.EdgeLayoutType#getData <em>Data</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Data</em>'.
     * @see net.ogdf.ogml.EdgeLayoutType#getData()
     * @see #getEdgeLayoutType()
     * @generated
     */
    EReference getEdgeLayoutType_Data();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.EdgeLayoutType#getTemplate <em>Template</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Template</em>'.
     * @see net.ogdf.ogml.EdgeLayoutType#getTemplate()
     * @see #getEdgeLayoutType()
     * @generated
     */
    EReference getEdgeLayoutType_Template();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.EdgeLayoutType#getLine <em>Line</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Line</em>'.
     * @see net.ogdf.ogml.EdgeLayoutType#getLine()
     * @see #getEdgeLayoutType()
     * @generated
     */
    EReference getEdgeLayoutType_Line();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.EdgeLayoutType#getSourceStyle <em>Source Style</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Source Style</em>'.
     * @see net.ogdf.ogml.EdgeLayoutType#getSourceStyle()
     * @see #getEdgeLayoutType()
     * @generated
     */
    EReference getEdgeLayoutType_SourceStyle();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.EdgeLayoutType#getTargetStyle <em>Target Style</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Target Style</em>'.
     * @see net.ogdf.ogml.EdgeLayoutType#getTargetStyle()
     * @see #getEdgeLayoutType()
     * @generated
     */
    EReference getEdgeLayoutType_TargetStyle();

    /**
     * Returns the meta object for the containment reference list '{@link net.ogdf.ogml.EdgeLayoutType#getPoint <em>Point</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Point</em>'.
     * @see net.ogdf.ogml.EdgeLayoutType#getPoint()
     * @see #getEdgeLayoutType()
     * @generated
     */
    EReference getEdgeLayoutType_Point();

    /**
     * Returns the meta object for the containment reference list '{@link net.ogdf.ogml.EdgeLayoutType#getSegment <em>Segment</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Segment</em>'.
     * @see net.ogdf.ogml.EdgeLayoutType#getSegment()
     * @see #getEdgeLayoutType()
     * @generated
     */
    EReference getEdgeLayoutType_Segment();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.EdgeLayoutType#getIdRef <em>Id Ref</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id Ref</em>'.
     * @see net.ogdf.ogml.EdgeLayoutType#getIdRef()
     * @see #getEdgeLayoutType()
     * @generated
     */
    EAttribute getEdgeLayoutType_IdRef();

    /**
     * Returns the meta object for class '{@link net.ogdf.ogml.EdgeStyleTemplateType <em>Edge Style Template Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Edge Style Template Type</em>'.
     * @see net.ogdf.ogml.EdgeStyleTemplateType
     * @generated
     */
    EClass getEdgeStyleTemplateType();

    /**
     * Returns the meta object for the containment reference list '{@link net.ogdf.ogml.EdgeStyleTemplateType#getData <em>Data</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Data</em>'.
     * @see net.ogdf.ogml.EdgeStyleTemplateType#getData()
     * @see #getEdgeStyleTemplateType()
     * @generated
     */
    EReference getEdgeStyleTemplateType_Data();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.EdgeStyleTemplateType#getTemplate <em>Template</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Template</em>'.
     * @see net.ogdf.ogml.EdgeStyleTemplateType#getTemplate()
     * @see #getEdgeStyleTemplateType()
     * @generated
     */
    EReference getEdgeStyleTemplateType_Template();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.EdgeStyleTemplateType#getLine <em>Line</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Line</em>'.
     * @see net.ogdf.ogml.EdgeStyleTemplateType#getLine()
     * @see #getEdgeStyleTemplateType()
     * @generated
     */
    EReference getEdgeStyleTemplateType_Line();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.EdgeStyleTemplateType#getSourceStyle <em>Source Style</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Source Style</em>'.
     * @see net.ogdf.ogml.EdgeStyleTemplateType#getSourceStyle()
     * @see #getEdgeStyleTemplateType()
     * @generated
     */
    EReference getEdgeStyleTemplateType_SourceStyle();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.EdgeStyleTemplateType#getTargetStyle <em>Target Style</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Target Style</em>'.
     * @see net.ogdf.ogml.EdgeStyleTemplateType#getTargetStyle()
     * @see #getEdgeStyleTemplateType()
     * @generated
     */
    EReference getEdgeStyleTemplateType_TargetStyle();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.EdgeStyleTemplateType#getId <em>Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see net.ogdf.ogml.EdgeStyleTemplateType#getId()
     * @see #getEdgeStyleTemplateType()
     * @generated
     */
    EAttribute getEdgeStyleTemplateType_Id();

    /**
     * Returns the meta object for class '{@link net.ogdf.ogml.EdgeType <em>Edge Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Edge Type</em>'.
     * @see net.ogdf.ogml.EdgeType
     * @generated
     */
    EClass getEdgeType();

    /**
     * Returns the meta object for the containment reference list '{@link net.ogdf.ogml.EdgeType#getData <em>Data</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Data</em>'.
     * @see net.ogdf.ogml.EdgeType#getData()
     * @see #getEdgeType()
     * @generated
     */
    EReference getEdgeType_Data();

    /**
     * Returns the meta object for the containment reference list '{@link net.ogdf.ogml.EdgeType#getLabel <em>Label</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Label</em>'.
     * @see net.ogdf.ogml.EdgeType#getLabel()
     * @see #getEdgeType()
     * @generated
     */
    EReference getEdgeType_Label();

    /**
     * Returns the meta object for the attribute list '{@link net.ogdf.ogml.EdgeType#getGroup <em>Group</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Group</em>'.
     * @see net.ogdf.ogml.EdgeType#getGroup()
     * @see #getEdgeType()
     * @generated
     */
    EAttribute getEdgeType_Group();

    /**
     * Returns the meta object for the containment reference list '{@link net.ogdf.ogml.EdgeType#getSource <em>Source</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Source</em>'.
     * @see net.ogdf.ogml.EdgeType#getSource()
     * @see #getEdgeType()
     * @generated
     */
    EReference getEdgeType_Source();

    /**
     * Returns the meta object for the containment reference list '{@link net.ogdf.ogml.EdgeType#getTarget <em>Target</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Target</em>'.
     * @see net.ogdf.ogml.EdgeType#getTarget()
     * @see #getEdgeType()
     * @generated
     */
    EReference getEdgeType_Target();

    /**
     * Returns the meta object for the containment reference list '{@link net.ogdf.ogml.EdgeType#getLabel1 <em>Label1</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Label1</em>'.
     * @see net.ogdf.ogml.EdgeType#getLabel1()
     * @see #getEdgeType()
     * @generated
     */
    EReference getEdgeType_Label1();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.EdgeType#getId <em>Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see net.ogdf.ogml.EdgeType#getId()
     * @see #getEdgeType()
     * @generated
     */
    EAttribute getEdgeType_Id();

    /**
     * Returns the meta object for class '{@link net.ogdf.ogml.EndpointType <em>Endpoint Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Endpoint Type</em>'.
     * @see net.ogdf.ogml.EndpointType
     * @generated
     */
    EClass getEndpointType();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.EndpointType#getColor <em>Color</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Color</em>'.
     * @see net.ogdf.ogml.EndpointType#getColor()
     * @see #getEndpointType()
     * @generated
     */
    EAttribute getEndpointType_Color();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.EndpointType#getIdRef <em>Id Ref</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id Ref</em>'.
     * @see net.ogdf.ogml.EndpointType#getIdRef()
     * @see #getEndpointType()
     * @generated
     */
    EAttribute getEndpointType_IdRef();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.EndpointType#getPort <em>Port</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Port</em>'.
     * @see net.ogdf.ogml.EndpointType#getPort()
     * @see #getEndpointType()
     * @generated
     */
    EAttribute getEndpointType_Port();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.EndpointType#getSize <em>Size</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Size</em>'.
     * @see net.ogdf.ogml.EndpointType#getSize()
     * @see #getEndpointType()
     * @generated
     */
    EAttribute getEndpointType_Size();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.EndpointType#getStyle <em>Style</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Style</em>'.
     * @see net.ogdf.ogml.EndpointType#getStyle()
     * @see #getEndpointType()
     * @generated
     */
    EAttribute getEndpointType_Style();

    /**
     * Returns the meta object for class '{@link net.ogdf.ogml.FillType <em>Fill Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Fill Type</em>'.
     * @see net.ogdf.ogml.FillType
     * @generated
     */
    EClass getFillType();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.FillType#getColor <em>Color</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Color</em>'.
     * @see net.ogdf.ogml.FillType#getColor()
     * @see #getFillType()
     * @generated
     */
    EAttribute getFillType_Color();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.FillType#getPattern <em>Pattern</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Pattern</em>'.
     * @see net.ogdf.ogml.FillType#getPattern()
     * @see #getFillType()
     * @generated
     */
    EAttribute getFillType_Pattern();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.FillType#getPatternColor <em>Pattern Color</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Pattern Color</em>'.
     * @see net.ogdf.ogml.FillType#getPatternColor()
     * @see #getFillType()
     * @generated
     */
    EAttribute getFillType_PatternColor();

    /**
     * Returns the meta object for class '{@link net.ogdf.ogml.FontType <em>Font Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Font Type</em>'.
     * @see net.ogdf.ogml.FontType
     * @generated
     */
    EClass getFontType();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.FontType#getColor <em>Color</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Color</em>'.
     * @see net.ogdf.ogml.FontType#getColor()
     * @see #getFontType()
     * @generated
     */
    EAttribute getFontType_Color();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.FontType#getFamily <em>Family</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Family</em>'.
     * @see net.ogdf.ogml.FontType#getFamily()
     * @see #getFontType()
     * @generated
     */
    EAttribute getFontType_Family();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.FontType#getSize <em>Size</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Size</em>'.
     * @see net.ogdf.ogml.FontType#getSize()
     * @see #getFontType()
     * @generated
     */
    EAttribute getFontType_Size();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.FontType#getStretch <em>Stretch</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Stretch</em>'.
     * @see net.ogdf.ogml.FontType#getStretch()
     * @see #getFontType()
     * @generated
     */
    EAttribute getFontType_Stretch();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.FontType#getStyle <em>Style</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Style</em>'.
     * @see net.ogdf.ogml.FontType#getStyle()
     * @see #getFontType()
     * @generated
     */
    EAttribute getFontType_Style();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.FontType#getVariant <em>Variant</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Variant</em>'.
     * @see net.ogdf.ogml.FontType#getVariant()
     * @see #getFontType()
     * @generated
     */
    EAttribute getFontType_Variant();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.FontType#getWeight <em>Weight</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Weight</em>'.
     * @see net.ogdf.ogml.FontType#getWeight()
     * @see #getFontType()
     * @generated
     */
    EAttribute getFontType_Weight();

    /**
     * Returns the meta object for class '{@link net.ogdf.ogml.GraphStyleType <em>Graph Style Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Graph Style Type</em>'.
     * @see net.ogdf.ogml.GraphStyleType
     * @generated
     */
    EClass getGraphStyleType();

    /**
     * Returns the meta object for the containment reference list '{@link net.ogdf.ogml.GraphStyleType#getData <em>Data</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Data</em>'.
     * @see net.ogdf.ogml.GraphStyleType#getData()
     * @see #getGraphStyleType()
     * @generated
     */
    EReference getGraphStyleType_Data();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.GraphStyleType#getDefaultEdgeTemplate <em>Default Edge Template</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Default Edge Template</em>'.
     * @see net.ogdf.ogml.GraphStyleType#getDefaultEdgeTemplate()
     * @see #getGraphStyleType()
     * @generated
     */
    EAttribute getGraphStyleType_DefaultEdgeTemplate();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.GraphStyleType#getDefaultLabelTemplate <em>Default Label Template</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Default Label Template</em>'.
     * @see net.ogdf.ogml.GraphStyleType#getDefaultLabelTemplate()
     * @see #getGraphStyleType()
     * @generated
     */
    EAttribute getGraphStyleType_DefaultLabelTemplate();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.GraphStyleType#getDefaultNodeTemplate <em>Default Node Template</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Default Node Template</em>'.
     * @see net.ogdf.ogml.GraphStyleType#getDefaultNodeTemplate()
     * @see #getGraphStyleType()
     * @generated
     */
    EAttribute getGraphStyleType_DefaultNodeTemplate();

    /**
     * Returns the meta object for class '{@link net.ogdf.ogml.GraphType <em>Graph Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Graph Type</em>'.
     * @see net.ogdf.ogml.GraphType
     * @generated
     */
    EClass getGraphType();

    /**
     * Returns the meta object for the containment reference list '{@link net.ogdf.ogml.GraphType#getData <em>Data</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Data</em>'.
     * @see net.ogdf.ogml.GraphType#getData()
     * @see #getGraphType()
     * @generated
     */
    EReference getGraphType_Data();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.GraphType#getStructure <em>Structure</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Structure</em>'.
     * @see net.ogdf.ogml.GraphType#getStructure()
     * @see #getGraphType()
     * @generated
     */
    EReference getGraphType_Structure();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.GraphType#getLayout <em>Layout</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Layout</em>'.
     * @see net.ogdf.ogml.GraphType#getLayout()
     * @see #getGraphType()
     * @generated
     */
    EReference getGraphType_Layout();

    /**
     * Returns the meta object for class '{@link net.ogdf.ogml.IntType <em>Int Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Int Type</em>'.
     * @see net.ogdf.ogml.IntType
     * @generated
     */
    EClass getIntType();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.IntType#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see net.ogdf.ogml.IntType#getName()
     * @see #getIntType()
     * @generated
     */
    EAttribute getIntType_Name();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.IntType#getValue <em>Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see net.ogdf.ogml.IntType#getValue()
     * @see #getIntType()
     * @generated
     */
    EAttribute getIntType_Value();

    /**
     * Returns the meta object for class '{@link net.ogdf.ogml.KeysType <em>Keys Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Keys Type</em>'.
     * @see net.ogdf.ogml.KeysType
     * @generated
     */
    EClass getKeysType();

    /**
     * Returns the meta object for the containment reference list '{@link net.ogdf.ogml.KeysType#getKey <em>Key</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Key</em>'.
     * @see net.ogdf.ogml.KeysType#getKey()
     * @see #getKeysType()
     * @generated
     */
    EReference getKeysType_Key();

    /**
     * Returns the meta object for class '{@link net.ogdf.ogml.KeyType <em>Key Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Key Type</em>'.
     * @see net.ogdf.ogml.KeyType
     * @generated
     */
    EClass getKeyType();

    /**
     * Returns the meta object for the containment reference list '{@link net.ogdf.ogml.KeyType#getKeyValue <em>Key Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Key Value</em>'.
     * @see net.ogdf.ogml.KeyType#getKeyValue()
     * @see #getKeyType()
     * @generated
     */
    EReference getKeyType_KeyValue();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.KeyType#getId <em>Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see net.ogdf.ogml.KeyType#getId()
     * @see #getKeyType()
     * @generated
     */
    EAttribute getKeyType_Id();

    /**
     * Returns the meta object for class '{@link net.ogdf.ogml.KeyValueType <em>Key Value Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Key Value Type</em>'.
     * @see net.ogdf.ogml.KeyValueType
     * @generated
     */
    EClass getKeyValueType();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.KeyValueType#getDafault <em>Dafault</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Dafault</em>'.
     * @see net.ogdf.ogml.KeyValueType#getDafault()
     * @see #getKeyValueType()
     * @generated
     */
    EAttribute getKeyValueType_Dafault();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.KeyValueType#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see net.ogdf.ogml.KeyValueType#getName()
     * @see #getKeyValueType()
     * @generated
     */
    EAttribute getKeyValueType_Name();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.KeyValueType#getType <em>Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Type</em>'.
     * @see net.ogdf.ogml.KeyValueType#getType()
     * @see #getKeyValueType()
     * @generated
     */
    EAttribute getKeyValueType_Type();

    /**
     * Returns the meta object for class '{@link net.ogdf.ogml.LabelConstraintType <em>Label Constraint Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Label Constraint Type</em>'.
     * @see net.ogdf.ogml.LabelConstraintType
     * @generated
     */
    EClass getLabelConstraintType();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.LabelConstraintType#getIdRef <em>Id Ref</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id Ref</em>'.
     * @see net.ogdf.ogml.LabelConstraintType#getIdRef()
     * @see #getLabelConstraintType()
     * @generated
     */
    EAttribute getLabelConstraintType_IdRef();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.LabelConstraintType#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see net.ogdf.ogml.LabelConstraintType#getName()
     * @see #getLabelConstraintType()
     * @generated
     */
    EAttribute getLabelConstraintType_Name();

    /**
     * Returns the meta object for class '{@link net.ogdf.ogml.LabelLayoutType <em>Label Layout Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Label Layout Type</em>'.
     * @see net.ogdf.ogml.LabelLayoutType
     * @generated
     */
    EClass getLabelLayoutType();

    /**
     * Returns the meta object for the containment reference list '{@link net.ogdf.ogml.LabelLayoutType#getData <em>Data</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Data</em>'.
     * @see net.ogdf.ogml.LabelLayoutType#getData()
     * @see #getLabelLayoutType()
     * @generated
     */
    EReference getLabelLayoutType_Data();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.LabelLayoutType#getTemplate <em>Template</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Template</em>'.
     * @see net.ogdf.ogml.LabelLayoutType#getTemplate()
     * @see #getLabelLayoutType()
     * @generated
     */
    EReference getLabelLayoutType_Template();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.LabelLayoutType#getLocation <em>Location</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Location</em>'.
     * @see net.ogdf.ogml.LabelLayoutType#getLocation()
     * @see #getLabelLayoutType()
     * @generated
     */
    EReference getLabelLayoutType_Location();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.LabelLayoutType#getText <em>Text</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Text</em>'.
     * @see net.ogdf.ogml.LabelLayoutType#getText()
     * @see #getLabelLayoutType()
     * @generated
     */
    EReference getLabelLayoutType_Text();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.LabelLayoutType#getFont <em>Font</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Font</em>'.
     * @see net.ogdf.ogml.LabelLayoutType#getFont()
     * @see #getLabelLayoutType()
     * @generated
     */
    EReference getLabelLayoutType_Font();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.LabelLayoutType#getIdRef <em>Id Ref</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id Ref</em>'.
     * @see net.ogdf.ogml.LabelLayoutType#getIdRef()
     * @see #getLabelLayoutType()
     * @generated
     */
    EAttribute getLabelLayoutType_IdRef();

    /**
     * Returns the meta object for class '{@link net.ogdf.ogml.LabelStyleTemplateType <em>Label Style Template Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Label Style Template Type</em>'.
     * @see net.ogdf.ogml.LabelStyleTemplateType
     * @generated
     */
    EClass getLabelStyleTemplateType();

    /**
     * Returns the meta object for the containment reference list '{@link net.ogdf.ogml.LabelStyleTemplateType#getData <em>Data</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Data</em>'.
     * @see net.ogdf.ogml.LabelStyleTemplateType#getData()
     * @see #getLabelStyleTemplateType()
     * @generated
     */
    EReference getLabelStyleTemplateType_Data();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.LabelStyleTemplateType#getTemplate <em>Template</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Template</em>'.
     * @see net.ogdf.ogml.LabelStyleTemplateType#getTemplate()
     * @see #getLabelStyleTemplateType()
     * @generated
     */
    EReference getLabelStyleTemplateType_Template();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.LabelStyleTemplateType#getText <em>Text</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Text</em>'.
     * @see net.ogdf.ogml.LabelStyleTemplateType#getText()
     * @see #getLabelStyleTemplateType()
     * @generated
     */
    EReference getLabelStyleTemplateType_Text();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.LabelStyleTemplateType#getFont <em>Font</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Font</em>'.
     * @see net.ogdf.ogml.LabelStyleTemplateType#getFont()
     * @see #getLabelStyleTemplateType()
     * @generated
     */
    EReference getLabelStyleTemplateType_Font();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.LabelStyleTemplateType#getId <em>Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see net.ogdf.ogml.LabelStyleTemplateType#getId()
     * @see #getLabelStyleTemplateType()
     * @generated
     */
    EAttribute getLabelStyleTemplateType_Id();

    /**
     * Returns the meta object for class '{@link net.ogdf.ogml.LabelType <em>Label Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Label Type</em>'.
     * @see net.ogdf.ogml.LabelType
     * @generated
     */
    EClass getLabelType();

    /**
     * Returns the meta object for the containment reference list '{@link net.ogdf.ogml.LabelType#getData <em>Data</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Data</em>'.
     * @see net.ogdf.ogml.LabelType#getData()
     * @see #getLabelType()
     * @generated
     */
    EReference getLabelType_Data();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.LabelType#getContent <em>Content</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Content</em>'.
     * @see net.ogdf.ogml.LabelType#getContent()
     * @see #getLabelType()
     * @generated
     */
    EAttribute getLabelType_Content();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.LabelType#getId <em>Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see net.ogdf.ogml.LabelType#getId()
     * @see #getLabelType()
     * @generated
     */
    EAttribute getLabelType_Id();

    /**
     * Returns the meta object for class '{@link net.ogdf.ogml.LayoutType <em>Layout Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Layout Type</em>'.
     * @see net.ogdf.ogml.LayoutType
     * @generated
     */
    EClass getLayoutType();

    /**
     * Returns the meta object for the containment reference list '{@link net.ogdf.ogml.LayoutType#getData <em>Data</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Data</em>'.
     * @see net.ogdf.ogml.LayoutType#getData()
     * @see #getLayoutType()
     * @generated
     */
    EReference getLayoutType_Data();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.LayoutType#getStyleTemplates <em>Style Templates</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Style Templates</em>'.
     * @see net.ogdf.ogml.LayoutType#getStyleTemplates()
     * @see #getLayoutType()
     * @generated
     */
    EReference getLayoutType_StyleTemplates();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.LayoutType#getStyles <em>Styles</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Styles</em>'.
     * @see net.ogdf.ogml.LayoutType#getStyles()
     * @see #getLayoutType()
     * @generated
     */
    EReference getLayoutType_Styles();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.LayoutType#getConstraints <em>Constraints</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Constraints</em>'.
     * @see net.ogdf.ogml.LayoutType#getConstraints()
     * @see #getLayoutType()
     * @generated
     */
    EReference getLayoutType_Constraints();

    /**
     * Returns the meta object for class '{@link net.ogdf.ogml.LineStyleType <em>Line Style Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Line Style Type</em>'.
     * @see net.ogdf.ogml.LineStyleType
     * @generated
     */
    EClass getLineStyleType();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.LineStyleType#getColor <em>Color</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Color</em>'.
     * @see net.ogdf.ogml.LineStyleType#getColor()
     * @see #getLineStyleType()
     * @generated
     */
    EAttribute getLineStyleType_Color();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.LineStyleType#getStyle <em>Style</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Style</em>'.
     * @see net.ogdf.ogml.LineStyleType#getStyle()
     * @see #getLineStyleType()
     * @generated
     */
    EAttribute getLineStyleType_Style();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.LineStyleType#getWidth <em>Width</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Width</em>'.
     * @see net.ogdf.ogml.LineStyleType#getWidth()
     * @see #getLineStyleType()
     * @generated
     */
    EAttribute getLineStyleType_Width();

    /**
     * Returns the meta object for class '{@link net.ogdf.ogml.LineType <em>Line Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Line Type</em>'.
     * @see net.ogdf.ogml.LineType
     * @generated
     */
    EClass getLineType();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.LineType#getColor <em>Color</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Color</em>'.
     * @see net.ogdf.ogml.LineType#getColor()
     * @see #getLineType()
     * @generated
     */
    EAttribute getLineType_Color();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.LineType#getType <em>Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Type</em>'.
     * @see net.ogdf.ogml.LineType#getType()
     * @see #getLineType()
     * @generated
     */
    EAttribute getLineType_Type();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.LineType#getWidth <em>Width</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Width</em>'.
     * @see net.ogdf.ogml.LineType#getWidth()
     * @see #getLineType()
     * @generated
     */
    EAttribute getLineType_Width();

    /**
     * Returns the meta object for class '{@link net.ogdf.ogml.LocationType <em>Location Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Location Type</em>'.
     * @see net.ogdf.ogml.LocationType
     * @generated
     */
    EClass getLocationType();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.LocationType#getX <em>X</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>X</em>'.
     * @see net.ogdf.ogml.LocationType#getX()
     * @see #getLocationType()
     * @generated
     */
    EAttribute getLocationType_X();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.LocationType#getY <em>Y</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Y</em>'.
     * @see net.ogdf.ogml.LocationType#getY()
     * @see #getLocationType()
     * @generated
     */
    EAttribute getLocationType_Y();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.LocationType#getZ <em>Z</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Z</em>'.
     * @see net.ogdf.ogml.LocationType#getZ()
     * @see #getLocationType()
     * @generated
     */
    EAttribute getLocationType_Z();

    /**
     * Returns the meta object for class '{@link net.ogdf.ogml.NodeConstraintType <em>Node Constraint Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Node Constraint Type</em>'.
     * @see net.ogdf.ogml.NodeConstraintType
     * @generated
     */
    EClass getNodeConstraintType();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.NodeConstraintType#getIdRef <em>Id Ref</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id Ref</em>'.
     * @see net.ogdf.ogml.NodeConstraintType#getIdRef()
     * @see #getNodeConstraintType()
     * @generated
     */
    EAttribute getNodeConstraintType_IdRef();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.NodeConstraintType#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see net.ogdf.ogml.NodeConstraintType#getName()
     * @see #getNodeConstraintType()
     * @generated
     */
    EAttribute getNodeConstraintType_Name();

    /**
     * Returns the meta object for class '{@link net.ogdf.ogml.NodeLayoutType <em>Node Layout Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Node Layout Type</em>'.
     * @see net.ogdf.ogml.NodeLayoutType
     * @generated
     */
    EClass getNodeLayoutType();

    /**
     * Returns the meta object for the containment reference list '{@link net.ogdf.ogml.NodeLayoutType#getData <em>Data</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Data</em>'.
     * @see net.ogdf.ogml.NodeLayoutType#getData()
     * @see #getNodeLayoutType()
     * @generated
     */
    EReference getNodeLayoutType_Data();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.NodeLayoutType#getTemplate <em>Template</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Template</em>'.
     * @see net.ogdf.ogml.NodeLayoutType#getTemplate()
     * @see #getNodeLayoutType()
     * @generated
     */
    EReference getNodeLayoutType_Template();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.NodeLayoutType#getLocation <em>Location</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Location</em>'.
     * @see net.ogdf.ogml.NodeLayoutType#getLocation()
     * @see #getNodeLayoutType()
     * @generated
     */
    EReference getNodeLayoutType_Location();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.NodeLayoutType#getShape <em>Shape</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Shape</em>'.
     * @see net.ogdf.ogml.NodeLayoutType#getShape()
     * @see #getNodeLayoutType()
     * @generated
     */
    EReference getNodeLayoutType_Shape();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.NodeLayoutType#getFill <em>Fill</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Fill</em>'.
     * @see net.ogdf.ogml.NodeLayoutType#getFill()
     * @see #getNodeLayoutType()
     * @generated
     */
    EReference getNodeLayoutType_Fill();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.NodeLayoutType#getLine <em>Line</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Line</em>'.
     * @see net.ogdf.ogml.NodeLayoutType#getLine()
     * @see #getNodeLayoutType()
     * @generated
     */
    EReference getNodeLayoutType_Line();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.NodeLayoutType#getIdRef <em>Id Ref</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id Ref</em>'.
     * @see net.ogdf.ogml.NodeLayoutType#getIdRef()
     * @see #getNodeLayoutType()
     * @generated
     */
    EAttribute getNodeLayoutType_IdRef();

    /**
     * Returns the meta object for class '{@link net.ogdf.ogml.NodeStyleTemplateType <em>Node Style Template Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Node Style Template Type</em>'.
     * @see net.ogdf.ogml.NodeStyleTemplateType
     * @generated
     */
    EClass getNodeStyleTemplateType();

    /**
     * Returns the meta object for the containment reference list '{@link net.ogdf.ogml.NodeStyleTemplateType#getData <em>Data</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Data</em>'.
     * @see net.ogdf.ogml.NodeStyleTemplateType#getData()
     * @see #getNodeStyleTemplateType()
     * @generated
     */
    EReference getNodeStyleTemplateType_Data();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.NodeStyleTemplateType#getTemplate <em>Template</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Template</em>'.
     * @see net.ogdf.ogml.NodeStyleTemplateType#getTemplate()
     * @see #getNodeStyleTemplateType()
     * @generated
     */
    EReference getNodeStyleTemplateType_Template();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.NodeStyleTemplateType#getShape <em>Shape</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Shape</em>'.
     * @see net.ogdf.ogml.NodeStyleTemplateType#getShape()
     * @see #getNodeStyleTemplateType()
     * @generated
     */
    EReference getNodeStyleTemplateType_Shape();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.NodeStyleTemplateType#getFill <em>Fill</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Fill</em>'.
     * @see net.ogdf.ogml.NodeStyleTemplateType#getFill()
     * @see #getNodeStyleTemplateType()
     * @generated
     */
    EReference getNodeStyleTemplateType_Fill();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.NodeStyleTemplateType#getLine <em>Line</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Line</em>'.
     * @see net.ogdf.ogml.NodeStyleTemplateType#getLine()
     * @see #getNodeStyleTemplateType()
     * @generated
     */
    EReference getNodeStyleTemplateType_Line();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.NodeStyleTemplateType#getId <em>Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see net.ogdf.ogml.NodeStyleTemplateType#getId()
     * @see #getNodeStyleTemplateType()
     * @generated
     */
    EAttribute getNodeStyleTemplateType_Id();

    /**
     * Returns the meta object for class '{@link net.ogdf.ogml.NodeType <em>Node Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Node Type</em>'.
     * @see net.ogdf.ogml.NodeType
     * @generated
     */
    EClass getNodeType();

    /**
     * Returns the meta object for the containment reference list '{@link net.ogdf.ogml.NodeType#getData <em>Data</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Data</em>'.
     * @see net.ogdf.ogml.NodeType#getData()
     * @see #getNodeType()
     * @generated
     */
    EReference getNodeType_Data();

    /**
     * Returns the meta object for the attribute list '{@link net.ogdf.ogml.NodeType#getGroup <em>Group</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Group</em>'.
     * @see net.ogdf.ogml.NodeType#getGroup()
     * @see #getNodeType()
     * @generated
     */
    EAttribute getNodeType_Group();

    /**
     * Returns the meta object for the containment reference list '{@link net.ogdf.ogml.NodeType#getLabel <em>Label</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Label</em>'.
     * @see net.ogdf.ogml.NodeType#getLabel()
     * @see #getNodeType()
     * @generated
     */
    EReference getNodeType_Label();

    /**
     * Returns the meta object for the containment reference list '{@link net.ogdf.ogml.NodeType#getNode <em>Node</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Node</em>'.
     * @see net.ogdf.ogml.NodeType#getNode()
     * @see #getNodeType()
     * @generated
     */
    EReference getNodeType_Node();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.NodeType#getId <em>Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see net.ogdf.ogml.NodeType#getId()
     * @see #getNodeType()
     * @generated
     */
    EAttribute getNodeType_Id();

    /**
     * Returns the meta object for class '{@link net.ogdf.ogml.NumberType <em>Number Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Number Type</em>'.
     * @see net.ogdf.ogml.NumberType
     * @generated
     */
    EClass getNumberType();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.NumberType#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see net.ogdf.ogml.NumberType#getName()
     * @see #getNumberType()
     * @generated
     */
    EAttribute getNumberType_Name();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.NumberType#getValue <em>Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see net.ogdf.ogml.NumberType#getValue()
     * @see #getNumberType()
     * @generated
     */
    EAttribute getNumberType_Value();

    /**
     * Returns the meta object for class '{@link net.ogdf.ogml.OgmlType <em>Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Type</em>'.
     * @see net.ogdf.ogml.OgmlType
     * @generated
     */
    EClass getOgmlType();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.OgmlType#getKeys <em>Keys</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Keys</em>'.
     * @see net.ogdf.ogml.OgmlType#getKeys()
     * @see #getOgmlType()
     * @generated
     */
    EReference getOgmlType_Keys();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.OgmlType#getGraph <em>Graph</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Graph</em>'.
     * @see net.ogdf.ogml.OgmlType#getGraph()
     * @see #getOgmlType()
     * @generated
     */
    EReference getOgmlType_Graph();

    /**
     * Returns the meta object for class '{@link net.ogdf.ogml.PointType <em>Point Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Point Type</em>'.
     * @see net.ogdf.ogml.PointType
     * @generated
     */
    EClass getPointType();

    /**
     * Returns the meta object for the containment reference list '{@link net.ogdf.ogml.PointType#getData <em>Data</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Data</em>'.
     * @see net.ogdf.ogml.PointType#getData()
     * @see #getPointType()
     * @generated
     */
    EReference getPointType_Data();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.PointType#getId <em>Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see net.ogdf.ogml.PointType#getId()
     * @see #getPointType()
     * @generated
     */
    EAttribute getPointType_Id();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.PointType#getX <em>X</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>X</em>'.
     * @see net.ogdf.ogml.PointType#getX()
     * @see #getPointType()
     * @generated
     */
    EAttribute getPointType_X();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.PointType#getY <em>Y</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Y</em>'.
     * @see net.ogdf.ogml.PointType#getY()
     * @see #getPointType()
     * @generated
     */
    EAttribute getPointType_Y();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.PointType#getZ <em>Z</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Z</em>'.
     * @see net.ogdf.ogml.PointType#getZ()
     * @see #getPointType()
     * @generated
     */
    EAttribute getPointType_Z();

    /**
     * Returns the meta object for class '{@link net.ogdf.ogml.SegmentType <em>Segment Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Segment Type</em>'.
     * @see net.ogdf.ogml.SegmentType
     * @generated
     */
    EClass getSegmentType();

    /**
     * Returns the meta object for the containment reference list '{@link net.ogdf.ogml.SegmentType#getData <em>Data</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Data</em>'.
     * @see net.ogdf.ogml.SegmentType#getData()
     * @see #getSegmentType()
     * @generated
     */
    EReference getSegmentType_Data();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.SegmentType#getLine <em>Line</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Line</em>'.
     * @see net.ogdf.ogml.SegmentType#getLine()
     * @see #getSegmentType()
     * @generated
     */
    EReference getSegmentType_Line();

    /**
     * Returns the meta object for the containment reference list '{@link net.ogdf.ogml.SegmentType#getEndpoint <em>Endpoint</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Endpoint</em>'.
     * @see net.ogdf.ogml.SegmentType#getEndpoint()
     * @see #getSegmentType()
     * @generated
     */
    EReference getSegmentType_Endpoint();

    /**
     * Returns the meta object for class '{@link net.ogdf.ogml.ShapeType1 <em>Shape Type1</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Shape Type1</em>'.
     * @see net.ogdf.ogml.ShapeType1
     * @generated
     */
    EClass getShapeType1();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.ShapeType1#getHeight <em>Height</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Height</em>'.
     * @see net.ogdf.ogml.ShapeType1#getHeight()
     * @see #getShapeType1()
     * @generated
     */
    EAttribute getShapeType1_Height();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.ShapeType1#getType <em>Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Type</em>'.
     * @see net.ogdf.ogml.ShapeType1#getType()
     * @see #getShapeType1()
     * @generated
     */
    EAttribute getShapeType1_Type();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.ShapeType1#getUri <em>Uri</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Uri</em>'.
     * @see net.ogdf.ogml.ShapeType1#getUri()
     * @see #getShapeType1()
     * @generated
     */
    EAttribute getShapeType1_Uri();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.ShapeType1#getWidth <em>Width</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Width</em>'.
     * @see net.ogdf.ogml.ShapeType1#getWidth()
     * @see #getShapeType1()
     * @generated
     */
    EAttribute getShapeType1_Width();

    /**
     * Returns the meta object for class '{@link net.ogdf.ogml.SourceStyleType <em>Source Style Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Source Style Type</em>'.
     * @see net.ogdf.ogml.SourceStyleType
     * @generated
     */
    EClass getSourceStyleType();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.SourceStyleType#getColor <em>Color</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Color</em>'.
     * @see net.ogdf.ogml.SourceStyleType#getColor()
     * @see #getSourceStyleType()
     * @generated
     */
    EAttribute getSourceStyleType_Color();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.SourceStyleType#getType <em>Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Type</em>'.
     * @see net.ogdf.ogml.SourceStyleType#getType()
     * @see #getSourceStyleType()
     * @generated
     */
    EAttribute getSourceStyleType_Type();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.SourceStyleType#getUri <em>Uri</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Uri</em>'.
     * @see net.ogdf.ogml.SourceStyleType#getUri()
     * @see #getSourceStyleType()
     * @generated
     */
    EAttribute getSourceStyleType_Uri();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.SourceStyleType#getWidth <em>Width</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Width</em>'.
     * @see net.ogdf.ogml.SourceStyleType#getWidth()
     * @see #getSourceStyleType()
     * @generated
     */
    EAttribute getSourceStyleType_Width();

    /**
     * Returns the meta object for class '{@link net.ogdf.ogml.SourceTargetType <em>Source Target Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Source Target Type</em>'.
     * @see net.ogdf.ogml.SourceTargetType
     * @generated
     */
    EClass getSourceTargetType();

    /**
     * Returns the meta object for the containment reference list '{@link net.ogdf.ogml.SourceTargetType#getData <em>Data</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Data</em>'.
     * @see net.ogdf.ogml.SourceTargetType#getData()
     * @see #getSourceTargetType()
     * @generated
     */
    EReference getSourceTargetType_Data();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.SourceTargetType#getLabel <em>Label</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Label</em>'.
     * @see net.ogdf.ogml.SourceTargetType#getLabel()
     * @see #getSourceTargetType()
     * @generated
     */
    EReference getSourceTargetType_Label();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.SourceTargetType#getId <em>Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see net.ogdf.ogml.SourceTargetType#getId()
     * @see #getSourceTargetType()
     * @generated
     */
    EAttribute getSourceTargetType_Id();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.SourceTargetType#getIdRef <em>Id Ref</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id Ref</em>'.
     * @see net.ogdf.ogml.SourceTargetType#getIdRef()
     * @see #getSourceTargetType()
     * @generated
     */
    EAttribute getSourceTargetType_IdRef();

    /**
     * Returns the meta object for class '{@link net.ogdf.ogml.StructureType <em>Structure Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Structure Type</em>'.
     * @see net.ogdf.ogml.StructureType
     * @generated
     */
    EClass getStructureType();

    /**
     * Returns the meta object for the attribute list '{@link net.ogdf.ogml.StructureType#getGroup <em>Group</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Group</em>'.
     * @see net.ogdf.ogml.StructureType#getGroup()
     * @see #getStructureType()
     * @generated
     */
    EAttribute getStructureType_Group();

    /**
     * Returns the meta object for the containment reference list '{@link net.ogdf.ogml.StructureType#getData <em>Data</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Data</em>'.
     * @see net.ogdf.ogml.StructureType#getData()
     * @see #getStructureType()
     * @generated
     */
    EReference getStructureType_Data();

    /**
     * Returns the meta object for the containment reference list '{@link net.ogdf.ogml.StructureType#getNode <em>Node</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Node</em>'.
     * @see net.ogdf.ogml.StructureType#getNode()
     * @see #getStructureType()
     * @generated
     */
    EReference getStructureType_Node();

    /**
     * Returns the meta object for the containment reference list '{@link net.ogdf.ogml.StructureType#getEdge <em>Edge</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Edge</em>'.
     * @see net.ogdf.ogml.StructureType#getEdge()
     * @see #getStructureType()
     * @generated
     */
    EReference getStructureType_Edge();

    /**
     * Returns the meta object for the containment reference list '{@link net.ogdf.ogml.StructureType#getLabel <em>Label</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Label</em>'.
     * @see net.ogdf.ogml.StructureType#getLabel()
     * @see #getStructureType()
     * @generated
     */
    EReference getStructureType_Label();

    /**
     * Returns the meta object for class '{@link net.ogdf.ogml.StylesType <em>Styles Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Styles Type</em>'.
     * @see net.ogdf.ogml.StylesType
     * @generated
     */
    EClass getStylesType();

    /**
     * Returns the meta object for the containment reference '{@link net.ogdf.ogml.StylesType#getGraphStyle <em>Graph Style</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Graph Style</em>'.
     * @see net.ogdf.ogml.StylesType#getGraphStyle()
     * @see #getStylesType()
     * @generated
     */
    EReference getStylesType_GraphStyle();

    /**
     * Returns the meta object for the attribute list '{@link net.ogdf.ogml.StylesType#getGroup <em>Group</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Group</em>'.
     * @see net.ogdf.ogml.StylesType#getGroup()
     * @see #getStylesType()
     * @generated
     */
    EAttribute getStylesType_Group();

    /**
     * Returns the meta object for the containment reference list '{@link net.ogdf.ogml.StylesType#getData <em>Data</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Data</em>'.
     * @see net.ogdf.ogml.StylesType#getData()
     * @see #getStylesType()
     * @generated
     */
    EReference getStylesType_Data();

    /**
     * Returns the meta object for the containment reference list '{@link net.ogdf.ogml.StylesType#getNodeStyle <em>Node Style</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Node Style</em>'.
     * @see net.ogdf.ogml.StylesType#getNodeStyle()
     * @see #getStylesType()
     * @generated
     */
    EReference getStylesType_NodeStyle();

    /**
     * Returns the meta object for the containment reference list '{@link net.ogdf.ogml.StylesType#getEdgeStyle <em>Edge Style</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Edge Style</em>'.
     * @see net.ogdf.ogml.StylesType#getEdgeStyle()
     * @see #getStylesType()
     * @generated
     */
    EReference getStylesType_EdgeStyle();

    /**
     * Returns the meta object for the containment reference list '{@link net.ogdf.ogml.StylesType#getLabelStyle <em>Label Style</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Label Style</em>'.
     * @see net.ogdf.ogml.StylesType#getLabelStyle()
     * @see #getStylesType()
     * @generated
     */
    EReference getStylesType_LabelStyle();

    /**
     * Returns the meta object for class '{@link net.ogdf.ogml.StyleTemplatesType <em>Style Templates Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Style Templates Type</em>'.
     * @see net.ogdf.ogml.StyleTemplatesType
     * @generated
     */
    EClass getStyleTemplatesType();

    /**
     * Returns the meta object for the attribute list '{@link net.ogdf.ogml.StyleTemplatesType#getGroup <em>Group</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Group</em>'.
     * @see net.ogdf.ogml.StyleTemplatesType#getGroup()
     * @see #getStyleTemplatesType()
     * @generated
     */
    EAttribute getStyleTemplatesType_Group();

    /**
     * Returns the meta object for the containment reference list '{@link net.ogdf.ogml.StyleTemplatesType#getData <em>Data</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Data</em>'.
     * @see net.ogdf.ogml.StyleTemplatesType#getData()
     * @see #getStyleTemplatesType()
     * @generated
     */
    EReference getStyleTemplatesType_Data();

    /**
     * Returns the meta object for the containment reference list '{@link net.ogdf.ogml.StyleTemplatesType#getNodeStyleTemplate <em>Node Style Template</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Node Style Template</em>'.
     * @see net.ogdf.ogml.StyleTemplatesType#getNodeStyleTemplate()
     * @see #getStyleTemplatesType()
     * @generated
     */
    EReference getStyleTemplatesType_NodeStyleTemplate();

    /**
     * Returns the meta object for the containment reference list '{@link net.ogdf.ogml.StyleTemplatesType#getEdgeStyleTemplate <em>Edge Style Template</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Edge Style Template</em>'.
     * @see net.ogdf.ogml.StyleTemplatesType#getEdgeStyleTemplate()
     * @see #getStyleTemplatesType()
     * @generated
     */
    EReference getStyleTemplatesType_EdgeStyleTemplate();

    /**
     * Returns the meta object for the containment reference list '{@link net.ogdf.ogml.StyleTemplatesType#getLabelStyleTemplate <em>Label Style Template</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Label Style Template</em>'.
     * @see net.ogdf.ogml.StyleTemplatesType#getLabelStyleTemplate()
     * @see #getStyleTemplatesType()
     * @generated
     */
    EReference getStyleTemplatesType_LabelStyleTemplate();

    /**
     * Returns the meta object for class '{@link net.ogdf.ogml.TargetStyleType <em>Target Style Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Target Style Type</em>'.
     * @see net.ogdf.ogml.TargetStyleType
     * @generated
     */
    EClass getTargetStyleType();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.TargetStyleType#getColor <em>Color</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Color</em>'.
     * @see net.ogdf.ogml.TargetStyleType#getColor()
     * @see #getTargetStyleType()
     * @generated
     */
    EAttribute getTargetStyleType_Color();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.TargetStyleType#getType <em>Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Type</em>'.
     * @see net.ogdf.ogml.TargetStyleType#getType()
     * @see #getTargetStyleType()
     * @generated
     */
    EAttribute getTargetStyleType_Type();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.TargetStyleType#getUri <em>Uri</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Uri</em>'.
     * @see net.ogdf.ogml.TargetStyleType#getUri()
     * @see #getTargetStyleType()
     * @generated
     */
    EAttribute getTargetStyleType_Uri();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.TargetStyleType#getWidth <em>Width</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Width</em>'.
     * @see net.ogdf.ogml.TargetStyleType#getWidth()
     * @see #getTargetStyleType()
     * @generated
     */
    EAttribute getTargetStyleType_Width();

    /**
     * Returns the meta object for class '{@link net.ogdf.ogml.TemplateType <em>Template Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Template Type</em>'.
     * @see net.ogdf.ogml.TemplateType
     * @generated
     */
    EClass getTemplateType();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.TemplateType#getIdRef <em>Id Ref</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id Ref</em>'.
     * @see net.ogdf.ogml.TemplateType#getIdRef()
     * @see #getTemplateType()
     * @generated
     */
    EAttribute getTemplateType_IdRef();

    /**
     * Returns the meta object for class '{@link net.ogdf.ogml.TextType <em>Text Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Text Type</em>'.
     * @see net.ogdf.ogml.TextType
     * @generated
     */
    EClass getTextType();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.TextType#getAlignment <em>Alignment</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Alignment</em>'.
     * @see net.ogdf.ogml.TextType#getAlignment()
     * @see #getTextType()
     * @generated
     */
    EAttribute getTextType_Alignment();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.TextType#getDecoration <em>Decoration</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Decoration</em>'.
     * @see net.ogdf.ogml.TextType#getDecoration()
     * @see #getTextType()
     * @generated
     */
    EAttribute getTextType_Decoration();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.TextType#getRotation <em>Rotation</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Rotation</em>'.
     * @see net.ogdf.ogml.TextType#getRotation()
     * @see #getTextType()
     * @generated
     */
    EAttribute getTextType_Rotation();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.TextType#getTransform <em>Transform</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Transform</em>'.
     * @see net.ogdf.ogml.TextType#getTransform()
     * @see #getTextType()
     * @generated
     */
    EAttribute getTextType_Transform();

    /**
     * Returns the meta object for class '{@link net.ogdf.ogml.UriType <em>Uri Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Uri Type</em>'.
     * @see net.ogdf.ogml.UriType
     * @generated
     */
    EClass getUriType();

    /**
     * Returns the meta object for the attribute '{@link net.ogdf.ogml.UriType#getUri <em>Uri</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Uri</em>'.
     * @see net.ogdf.ogml.UriType#getUri()
     * @see #getUriType()
     * @generated
     */
    EAttribute getUriType_Uri();

    /**
     * Returns the meta object for enum '{@link net.ogdf.ogml.AlignmentType <em>Alignment Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Alignment Type</em>'.
     * @see net.ogdf.ogml.AlignmentType
     * @generated
     */
    EEnum getAlignmentType();

    /**
     * Returns the meta object for enum '{@link net.ogdf.ogml.BoolIntType <em>Bool Int Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Bool Int Type</em>'.
     * @see net.ogdf.ogml.BoolIntType
     * @generated
     */
    EEnum getBoolIntType();

    /**
     * Returns the meta object for enum '{@link net.ogdf.ogml.BoolTFType <em>Bool TF Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Bool TF Type</em>'.
     * @see net.ogdf.ogml.BoolTFType
     * @generated
     */
    EEnum getBoolTFType();

    /**
     * Returns the meta object for enum '{@link net.ogdf.ogml.DecorationType <em>Decoration Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Decoration Type</em>'.
     * @see net.ogdf.ogml.DecorationType
     * @generated
     */
    EEnum getDecorationType();

    /**
     * Returns the meta object for enum '{@link net.ogdf.ogml.EndpointStylesType <em>Endpoint Styles Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Endpoint Styles Type</em>'.
     * @see net.ogdf.ogml.EndpointStylesType
     * @generated
     */
    EEnum getEndpointStylesType();

    /**
     * Returns the meta object for enum '{@link net.ogdf.ogml.FontStretchType <em>Font Stretch Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Font Stretch Type</em>'.
     * @see net.ogdf.ogml.FontStretchType
     * @generated
     */
    EEnum getFontStretchType();

    /**
     * Returns the meta object for enum '{@link net.ogdf.ogml.FontStyleType <em>Font Style Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Font Style Type</em>'.
     * @see net.ogdf.ogml.FontStyleType
     * @generated
     */
    EEnum getFontStyleType();

    /**
     * Returns the meta object for enum '{@link net.ogdf.ogml.FontVariantType <em>Font Variant Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Font Variant Type</em>'.
     * @see net.ogdf.ogml.FontVariantType
     * @generated
     */
    EEnum getFontVariantType();

    /**
     * Returns the meta object for enum '{@link net.ogdf.ogml.FontWeightType <em>Font Weight Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Font Weight Type</em>'.
     * @see net.ogdf.ogml.FontWeightType
     * @generated
     */
    EEnum getFontWeightType();

    /**
     * Returns the meta object for enum '{@link net.ogdf.ogml.LineStyleTypeType <em>Line Style Type Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Line Style Type Type</em>'.
     * @see net.ogdf.ogml.LineStyleTypeType
     * @generated
     */
    EEnum getLineStyleTypeType();

    /**
     * Returns the meta object for enum '{@link net.ogdf.ogml.PatternType <em>Pattern Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Pattern Type</em>'.
     * @see net.ogdf.ogml.PatternType
     * @generated
     */
    EEnum getPatternType();

    /**
     * Returns the meta object for enum '{@link net.ogdf.ogml.ShapeType <em>Shape Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Shape Type</em>'.
     * @see net.ogdf.ogml.ShapeType
     * @generated
     */
    EEnum getShapeType();

    /**
     * Returns the meta object for enum '{@link net.ogdf.ogml.TransformType <em>Transform Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Transform Type</em>'.
     * @see net.ogdf.ogml.TransformType
     * @generated
     */
    EEnum getTransformType();

    /**
     * Returns the meta object for data type '{@link net.ogdf.ogml.AlignmentType <em>Alignment Type Object</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for data type '<em>Alignment Type Object</em>'.
     * @see net.ogdf.ogml.AlignmentType
     * @model instanceClass="net.ogdf.ogml.AlignmentType"
     *        extendedMetaData="name='alignment.type:Object' baseType='alignment.type'"
     * @generated
     */
    EDataType getAlignmentTypeObject();

    /**
     * Returns the meta object for data type '{@link net.ogdf.ogml.BoolIntType <em>Bool Int Type Object</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for data type '<em>Bool Int Type Object</em>'.
     * @see net.ogdf.ogml.BoolIntType
     * @model instanceClass="net.ogdf.ogml.BoolIntType"
     *        extendedMetaData="name='boolInt.type:Object' baseType='boolInt.type'"
     * @generated
     */
    EDataType getBoolIntTypeObject();

    /**
     * Returns the meta object for data type '{@link net.ogdf.ogml.BoolTFType <em>Bool TF Type Object</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for data type '<em>Bool TF Type Object</em>'.
     * @see net.ogdf.ogml.BoolTFType
     * @model instanceClass="net.ogdf.ogml.BoolTFType"
     *        extendedMetaData="name='boolTF.type:Object' baseType='boolTF.type'"
     * @generated
     */
    EDataType getBoolTFTypeObject();

    /**
     * Returns the meta object for data type '{@link net.ogdf.ogml.DecorationType <em>Decoration Type Object</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for data type '<em>Decoration Type Object</em>'.
     * @see net.ogdf.ogml.DecorationType
     * @model instanceClass="net.ogdf.ogml.DecorationType"
     *        extendedMetaData="name='decoration.type:Object' baseType='decoration.type'"
     * @generated
     */
    EDataType getDecorationTypeObject();

    /**
     * Returns the meta object for data type '{@link net.ogdf.ogml.EndpointStylesType <em>Endpoint Styles Type Object</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for data type '<em>Endpoint Styles Type Object</em>'.
     * @see net.ogdf.ogml.EndpointStylesType
     * @model instanceClass="net.ogdf.ogml.EndpointStylesType"
     *        extendedMetaData="name='endpointStyles.type:Object' baseType='endpointStyles.type'"
     * @generated
     */
    EDataType getEndpointStylesTypeObject();

    /**
     * Returns the meta object for data type '{@link net.ogdf.ogml.FontStretchType <em>Font Stretch Type Object</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for data type '<em>Font Stretch Type Object</em>'.
     * @see net.ogdf.ogml.FontStretchType
     * @model instanceClass="net.ogdf.ogml.FontStretchType"
     *        extendedMetaData="name='fontStretch.type:Object' baseType='fontStretch.type'"
     * @generated
     */
    EDataType getFontStretchTypeObject();

    /**
     * Returns the meta object for data type '{@link net.ogdf.ogml.FontStyleType <em>Font Style Type Object</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for data type '<em>Font Style Type Object</em>'.
     * @see net.ogdf.ogml.FontStyleType
     * @model instanceClass="net.ogdf.ogml.FontStyleType"
     *        extendedMetaData="name='fontStyle.type:Object' baseType='fontStyle.type'"
     * @generated
     */
    EDataType getFontStyleTypeObject();

    /**
     * Returns the meta object for data type '{@link net.ogdf.ogml.FontVariantType <em>Font Variant Type Object</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for data type '<em>Font Variant Type Object</em>'.
     * @see net.ogdf.ogml.FontVariantType
     * @model instanceClass="net.ogdf.ogml.FontVariantType"
     *        extendedMetaData="name='fontVariant.type:Object' baseType='fontVariant.type'"
     * @generated
     */
    EDataType getFontVariantTypeObject();

    /**
     * Returns the meta object for data type '{@link net.ogdf.ogml.FontWeightType <em>Font Weight Type Object</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for data type '<em>Font Weight Type Object</em>'.
     * @see net.ogdf.ogml.FontWeightType
     * @model instanceClass="net.ogdf.ogml.FontWeightType"
     *        extendedMetaData="name='fontWeight.type:Object' baseType='fontWeight.type'"
     * @generated
     */
    EDataType getFontWeightTypeObject();

    /**
     * Returns the meta object for data type '{@link net.ogdf.ogml.LineStyleTypeType <em>Line Style Type Type Object</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for data type '<em>Line Style Type Type Object</em>'.
     * @see net.ogdf.ogml.LineStyleTypeType
     * @model instanceClass="net.ogdf.ogml.LineStyleTypeType"
     *        extendedMetaData="name='lineStyleType.type:Object' baseType='lineStyleType.type'"
     * @generated
     */
    EDataType getLineStyleTypeTypeObject();

    /**
     * Returns the meta object for data type '{@link net.ogdf.ogml.PatternType <em>Pattern Type Object</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for data type '<em>Pattern Type Object</em>'.
     * @see net.ogdf.ogml.PatternType
     * @model instanceClass="net.ogdf.ogml.PatternType"
     *        extendedMetaData="name='pattern.type:Object' baseType='pattern.type'"
     * @generated
     */
    EDataType getPatternTypeObject();

    /**
     * Returns the meta object for data type '{@link net.ogdf.ogml.ShapeType <em>Shape Type Object</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for data type '<em>Shape Type Object</em>'.
     * @see net.ogdf.ogml.ShapeType
     * @model instanceClass="net.ogdf.ogml.ShapeType"
     *        extendedMetaData="name='shape.type:Object' baseType='shape.type'"
     * @generated
     */
    EDataType getShapeTypeObject();

    /**
     * Returns the meta object for data type '{@link net.ogdf.ogml.TransformType <em>Transform Type Object</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for data type '<em>Transform Type Object</em>'.
     * @see net.ogdf.ogml.TransformType
     * @model instanceClass="net.ogdf.ogml.TransformType"
     *        extendedMetaData="name='transform.type:Object' baseType='transform.type'"
     * @generated
     */
    EDataType getTransformTypeObject();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    OgmlFactory getOgmlFactory();

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
         * The meta object literal for the '{@link net.ogdf.ogml.impl.BooleanTypeImpl <em>Boolean Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.impl.BooleanTypeImpl
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getBooleanType()
         * @generated
         */
        EClass BOOLEAN_TYPE = eINSTANCE.getBooleanType();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute BOOLEAN_TYPE__NAME = eINSTANCE.getBooleanType_Name();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute BOOLEAN_TYPE__VALUE = eINSTANCE.getBooleanType_Value();

        /**
         * The meta object literal for the '{@link net.ogdf.ogml.impl.BoolTypeImpl <em>Bool Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.impl.BoolTypeImpl
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getBoolType()
         * @generated
         */
        EClass BOOL_TYPE = eINSTANCE.getBoolType();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute BOOL_TYPE__NAME = eINSTANCE.getBoolType_Name();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute BOOL_TYPE__VALUE = eINSTANCE.getBoolType_Value();

        /**
         * The meta object literal for the '{@link net.ogdf.ogml.impl.ComposedTypeImpl <em>Composed Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.impl.ComposedTypeImpl
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getComposedType()
         * @generated
         */
        EClass COMPOSED_TYPE = eINSTANCE.getComposedType();

        /**
         * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute COMPOSED_TYPE__GROUP = eINSTANCE.getComposedType_Group();

        /**
         * The meta object literal for the '<em><b>Number</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference COMPOSED_TYPE__NUMBER = eINSTANCE.getComposedType_Number();

        /**
         * The meta object literal for the '<em><b>Int</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference COMPOSED_TYPE__INT = eINSTANCE.getComposedType_Int();

        /**
         * The meta object literal for the '<em><b>Boolean</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference COMPOSED_TYPE__BOOLEAN = eINSTANCE.getComposedType_Boolean();

        /**
         * The meta object literal for the '<em><b>Node</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference COMPOSED_TYPE__NODE = eINSTANCE.getComposedType_Node();

        /**
         * The meta object literal for the '<em><b>Edge</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference COMPOSED_TYPE__EDGE = eINSTANCE.getComposedType_Edge();

        /**
         * The meta object literal for the '<em><b>Label</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference COMPOSED_TYPE__LABEL = eINSTANCE.getComposedType_Label();

        /**
         * The meta object literal for the '<em><b>Composed</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference COMPOSED_TYPE__COMPOSED = eINSTANCE.getComposedType_Composed();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute COMPOSED_TYPE__NAME = eINSTANCE.getComposedType_Name();

        /**
         * The meta object literal for the '{@link net.ogdf.ogml.impl.ConstraintsTypeImpl <em>Constraints Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.impl.ConstraintsTypeImpl
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getConstraintsType()
         * @generated
         */
        EClass CONSTRAINTS_TYPE = eINSTANCE.getConstraintsType();

        /**
         * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute CONSTRAINTS_TYPE__GROUP = eINSTANCE.getConstraintsType_Group();

        /**
         * The meta object literal for the '<em><b>Number</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CONSTRAINTS_TYPE__NUMBER = eINSTANCE.getConstraintsType_Number();

        /**
         * The meta object literal for the '<em><b>Int</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CONSTRAINTS_TYPE__INT = eINSTANCE.getConstraintsType_Int();

        /**
         * The meta object literal for the '<em><b>Boolean</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CONSTRAINTS_TYPE__BOOLEAN = eINSTANCE.getConstraintsType_Boolean();

        /**
         * The meta object literal for the '<em><b>Node Ref</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CONSTRAINTS_TYPE__NODE_REF = eINSTANCE.getConstraintsType_NodeRef();

        /**
         * The meta object literal for the '<em><b>Edge Ref</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CONSTRAINTS_TYPE__EDGE_REF = eINSTANCE.getConstraintsType_EdgeRef();

        /**
         * The meta object literal for the '<em><b>Label Ref</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CONSTRAINTS_TYPE__LABEL_REF = eINSTANCE.getConstraintsType_LabelRef();

        /**
         * The meta object literal for the '<em><b>Composed</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CONSTRAINTS_TYPE__COMPOSED = eINSTANCE.getConstraintsType_Composed();

        /**
         * The meta object literal for the '{@link net.ogdf.ogml.impl.DataTypeImpl <em>Data Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.impl.DataTypeImpl
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getDataType()
         * @generated
         */
        EClass DATA_TYPE = eINSTANCE.getDataType();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DATA_TYPE__VALUE = eINSTANCE.getDataType_Value();

        /**
         * The meta object literal for the '<em><b>Id Ref</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DATA_TYPE__ID_REF = eINSTANCE.getDataType_IdRef();

        /**
         * The meta object literal for the '{@link net.ogdf.ogml.impl.DocumentRootImpl <em>Document Root</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.impl.DocumentRootImpl
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getDocumentRoot()
         * @generated
         */
        EClass DOCUMENT_ROOT = eINSTANCE.getDocumentRoot();

        /**
         * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DOCUMENT_ROOT__MIXED = eINSTANCE.getDocumentRoot_Mixed();

        /**
         * The meta object literal for the '<em><b>XMLNS Prefix Map</b></em>' map feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__XMLNS_PREFIX_MAP = eINSTANCE.getDocumentRoot_XMLNSPrefixMap();

        /**
         * The meta object literal for the '<em><b>XSI Schema Location</b></em>' map feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = eINSTANCE.getDocumentRoot_XSISchemaLocation();

        /**
         * The meta object literal for the '<em><b>Bool</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__BOOL = eINSTANCE.getDocumentRoot_Bool();

        /**
         * The meta object literal for the '<em><b>Boolean</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__BOOLEAN = eINSTANCE.getDocumentRoot_Boolean();

        /**
         * The meta object literal for the '<em><b>Composed</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__COMPOSED = eINSTANCE.getDocumentRoot_Composed();

        /**
         * The meta object literal for the '<em><b>Constraints</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__CONSTRAINTS = eINSTANCE.getDocumentRoot_Constraints();

        /**
         * The meta object literal for the '<em><b>Data</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__DATA = eINSTANCE.getDocumentRoot_Data();

        /**
         * The meta object literal for the '<em><b>Edge</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__EDGE = eINSTANCE.getDocumentRoot_Edge();

        /**
         * The meta object literal for the '<em><b>Edge Constraint</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__EDGE_CONSTRAINT = eINSTANCE.getDocumentRoot_EdgeConstraint();

        /**
         * The meta object literal for the '<em><b>Edge Layout</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__EDGE_LAYOUT = eINSTANCE.getDocumentRoot_EdgeLayout();

        /**
         * The meta object literal for the '<em><b>Edge Style Template</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__EDGE_STYLE_TEMPLATE = eINSTANCE.getDocumentRoot_EdgeStyleTemplate();

        /**
         * The meta object literal for the '<em><b>Endpoint</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__ENDPOINT = eINSTANCE.getDocumentRoot_Endpoint();

        /**
         * The meta object literal for the '<em><b>Fill</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__FILL = eINSTANCE.getDocumentRoot_Fill();

        /**
         * The meta object literal for the '<em><b>Font</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__FONT = eINSTANCE.getDocumentRoot_Font();

        /**
         * The meta object literal for the '<em><b>Font Stretch</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DOCUMENT_ROOT__FONT_STRETCH = eINSTANCE.getDocumentRoot_FontStretch();

        /**
         * The meta object literal for the '<em><b>Font Style</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DOCUMENT_ROOT__FONT_STYLE = eINSTANCE.getDocumentRoot_FontStyle();

        /**
         * The meta object literal for the '<em><b>Font Variant</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DOCUMENT_ROOT__FONT_VARIANT = eINSTANCE.getDocumentRoot_FontVariant();

        /**
         * The meta object literal for the '<em><b>Font Weight</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DOCUMENT_ROOT__FONT_WEIGHT = eINSTANCE.getDocumentRoot_FontWeight();

        /**
         * The meta object literal for the '<em><b>Graph</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__GRAPH = eINSTANCE.getDocumentRoot_Graph();

        /**
         * The meta object literal for the '<em><b>Graph Style</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__GRAPH_STYLE = eINSTANCE.getDocumentRoot_GraphStyle();

        /**
         * The meta object literal for the '<em><b>Int</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__INT = eINSTANCE.getDocumentRoot_Int();

        /**
         * The meta object literal for the '<em><b>Key</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__KEY = eINSTANCE.getDocumentRoot_Key();

        /**
         * The meta object literal for the '<em><b>Keys</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__KEYS = eINSTANCE.getDocumentRoot_Keys();

        /**
         * The meta object literal for the '<em><b>Key Value</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__KEY_VALUE = eINSTANCE.getDocumentRoot_KeyValue();

        /**
         * The meta object literal for the '<em><b>Label</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__LABEL = eINSTANCE.getDocumentRoot_Label();

        /**
         * The meta object literal for the '<em><b>Label Constraint</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__LABEL_CONSTRAINT = eINSTANCE.getDocumentRoot_LabelConstraint();

        /**
         * The meta object literal for the '<em><b>Label Layout</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__LABEL_LAYOUT = eINSTANCE.getDocumentRoot_LabelLayout();

        /**
         * The meta object literal for the '<em><b>Label Style Template</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__LABEL_STYLE_TEMPLATE = eINSTANCE.getDocumentRoot_LabelStyleTemplate();

        /**
         * The meta object literal for the '<em><b>Layout</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__LAYOUT = eINSTANCE.getDocumentRoot_Layout();

        /**
         * The meta object literal for the '<em><b>Line</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__LINE = eINSTANCE.getDocumentRoot_Line();

        /**
         * The meta object literal for the '<em><b>Line Style</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__LINE_STYLE = eINSTANCE.getDocumentRoot_LineStyle();

        /**
         * The meta object literal for the '<em><b>Line Style Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DOCUMENT_ROOT__LINE_STYLE_TYPE = eINSTANCE.getDocumentRoot_LineStyleType();

        /**
         * The meta object literal for the '<em><b>Location</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__LOCATION = eINSTANCE.getDocumentRoot_Location();

        /**
         * The meta object literal for the '<em><b>Node</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__NODE = eINSTANCE.getDocumentRoot_Node();

        /**
         * The meta object literal for the '<em><b>Node Constraint</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__NODE_CONSTRAINT = eINSTANCE.getDocumentRoot_NodeConstraint();

        /**
         * The meta object literal for the '<em><b>Node Layout</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__NODE_LAYOUT = eINSTANCE.getDocumentRoot_NodeLayout();

        /**
         * The meta object literal for the '<em><b>Node Style Template</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__NODE_STYLE_TEMPLATE = eINSTANCE.getDocumentRoot_NodeStyleTemplate();

        /**
         * The meta object literal for the '<em><b>Number</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__NUMBER = eINSTANCE.getDocumentRoot_Number();

        /**
         * The meta object literal for the '<em><b>Ogml</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__OGML = eINSTANCE.getDocumentRoot_Ogml();

        /**
         * The meta object literal for the '<em><b>Pattern</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DOCUMENT_ROOT__PATTERN = eINSTANCE.getDocumentRoot_Pattern();

        /**
         * The meta object literal for the '<em><b>Point</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__POINT = eINSTANCE.getDocumentRoot_Point();

        /**
         * The meta object literal for the '<em><b>Segment</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__SEGMENT = eINSTANCE.getDocumentRoot_Segment();

        /**
         * The meta object literal for the '<em><b>Shape</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__SHAPE = eINSTANCE.getDocumentRoot_Shape();

        /**
         * The meta object literal for the '<em><b>Source Style</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__SOURCE_STYLE = eINSTANCE.getDocumentRoot_SourceStyle();

        /**
         * The meta object literal for the '<em><b>Source Target</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__SOURCE_TARGET = eINSTANCE.getDocumentRoot_SourceTarget();

        /**
         * The meta object literal for the '<em><b>Structure</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__STRUCTURE = eINSTANCE.getDocumentRoot_Structure();

        /**
         * The meta object literal for the '<em><b>Styles</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__STYLES = eINSTANCE.getDocumentRoot_Styles();

        /**
         * The meta object literal for the '<em><b>Style Templates</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__STYLE_TEMPLATES = eINSTANCE.getDocumentRoot_StyleTemplates();

        /**
         * The meta object literal for the '<em><b>Target Style</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__TARGET_STYLE = eINSTANCE.getDocumentRoot_TargetStyle();

        /**
         * The meta object literal for the '<em><b>Template</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__TEMPLATE = eINSTANCE.getDocumentRoot_Template();

        /**
         * The meta object literal for the '<em><b>Text</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__TEXT = eINSTANCE.getDocumentRoot_Text();

        /**
         * The meta object literal for the '<em><b>Uri</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__URI = eINSTANCE.getDocumentRoot_Uri();

        /**
         * The meta object literal for the '{@link net.ogdf.ogml.impl.EdgeConstraintTypeImpl <em>Edge Constraint Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.impl.EdgeConstraintTypeImpl
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getEdgeConstraintType()
         * @generated
         */
        EClass EDGE_CONSTRAINT_TYPE = eINSTANCE.getEdgeConstraintType();

        /**
         * The meta object literal for the '<em><b>Id Ref</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EDGE_CONSTRAINT_TYPE__ID_REF = eINSTANCE.getEdgeConstraintType_IdRef();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EDGE_CONSTRAINT_TYPE__NAME = eINSTANCE.getEdgeConstraintType_Name();

        /**
         * The meta object literal for the '{@link net.ogdf.ogml.impl.EdgeLayoutTypeImpl <em>Edge Layout Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.impl.EdgeLayoutTypeImpl
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getEdgeLayoutType()
         * @generated
         */
        EClass EDGE_LAYOUT_TYPE = eINSTANCE.getEdgeLayoutType();

        /**
         * The meta object literal for the '<em><b>Data</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference EDGE_LAYOUT_TYPE__DATA = eINSTANCE.getEdgeLayoutType_Data();

        /**
         * The meta object literal for the '<em><b>Template</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference EDGE_LAYOUT_TYPE__TEMPLATE = eINSTANCE.getEdgeLayoutType_Template();

        /**
         * The meta object literal for the '<em><b>Line</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference EDGE_LAYOUT_TYPE__LINE = eINSTANCE.getEdgeLayoutType_Line();

        /**
         * The meta object literal for the '<em><b>Source Style</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference EDGE_LAYOUT_TYPE__SOURCE_STYLE = eINSTANCE.getEdgeLayoutType_SourceStyle();

        /**
         * The meta object literal for the '<em><b>Target Style</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference EDGE_LAYOUT_TYPE__TARGET_STYLE = eINSTANCE.getEdgeLayoutType_TargetStyle();

        /**
         * The meta object literal for the '<em><b>Point</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference EDGE_LAYOUT_TYPE__POINT = eINSTANCE.getEdgeLayoutType_Point();

        /**
         * The meta object literal for the '<em><b>Segment</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference EDGE_LAYOUT_TYPE__SEGMENT = eINSTANCE.getEdgeLayoutType_Segment();

        /**
         * The meta object literal for the '<em><b>Id Ref</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EDGE_LAYOUT_TYPE__ID_REF = eINSTANCE.getEdgeLayoutType_IdRef();

        /**
         * The meta object literal for the '{@link net.ogdf.ogml.impl.EdgeStyleTemplateTypeImpl <em>Edge Style Template Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.impl.EdgeStyleTemplateTypeImpl
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getEdgeStyleTemplateType()
         * @generated
         */
        EClass EDGE_STYLE_TEMPLATE_TYPE = eINSTANCE.getEdgeStyleTemplateType();

        /**
         * The meta object literal for the '<em><b>Data</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference EDGE_STYLE_TEMPLATE_TYPE__DATA = eINSTANCE.getEdgeStyleTemplateType_Data();

        /**
         * The meta object literal for the '<em><b>Template</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference EDGE_STYLE_TEMPLATE_TYPE__TEMPLATE = eINSTANCE.getEdgeStyleTemplateType_Template();

        /**
         * The meta object literal for the '<em><b>Line</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference EDGE_STYLE_TEMPLATE_TYPE__LINE = eINSTANCE.getEdgeStyleTemplateType_Line();

        /**
         * The meta object literal for the '<em><b>Source Style</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference EDGE_STYLE_TEMPLATE_TYPE__SOURCE_STYLE = eINSTANCE.getEdgeStyleTemplateType_SourceStyle();

        /**
         * The meta object literal for the '<em><b>Target Style</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference EDGE_STYLE_TEMPLATE_TYPE__TARGET_STYLE = eINSTANCE.getEdgeStyleTemplateType_TargetStyle();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EDGE_STYLE_TEMPLATE_TYPE__ID = eINSTANCE.getEdgeStyleTemplateType_Id();

        /**
         * The meta object literal for the '{@link net.ogdf.ogml.impl.EdgeTypeImpl <em>Edge Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.impl.EdgeTypeImpl
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getEdgeType()
         * @generated
         */
        EClass EDGE_TYPE = eINSTANCE.getEdgeType();

        /**
         * The meta object literal for the '<em><b>Data</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference EDGE_TYPE__DATA = eINSTANCE.getEdgeType_Data();

        /**
         * The meta object literal for the '<em><b>Label</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference EDGE_TYPE__LABEL = eINSTANCE.getEdgeType_Label();

        /**
         * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EDGE_TYPE__GROUP = eINSTANCE.getEdgeType_Group();

        /**
         * The meta object literal for the '<em><b>Source</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference EDGE_TYPE__SOURCE = eINSTANCE.getEdgeType_Source();

        /**
         * The meta object literal for the '<em><b>Target</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference EDGE_TYPE__TARGET = eINSTANCE.getEdgeType_Target();

        /**
         * The meta object literal for the '<em><b>Label1</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference EDGE_TYPE__LABEL1 = eINSTANCE.getEdgeType_Label1();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EDGE_TYPE__ID = eINSTANCE.getEdgeType_Id();

        /**
         * The meta object literal for the '{@link net.ogdf.ogml.impl.EndpointTypeImpl <em>Endpoint Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.impl.EndpointTypeImpl
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getEndpointType()
         * @generated
         */
        EClass ENDPOINT_TYPE = eINSTANCE.getEndpointType();

        /**
         * The meta object literal for the '<em><b>Color</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute ENDPOINT_TYPE__COLOR = eINSTANCE.getEndpointType_Color();

        /**
         * The meta object literal for the '<em><b>Id Ref</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute ENDPOINT_TYPE__ID_REF = eINSTANCE.getEndpointType_IdRef();

        /**
         * The meta object literal for the '<em><b>Port</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute ENDPOINT_TYPE__PORT = eINSTANCE.getEndpointType_Port();

        /**
         * The meta object literal for the '<em><b>Size</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute ENDPOINT_TYPE__SIZE = eINSTANCE.getEndpointType_Size();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute ENDPOINT_TYPE__STYLE = eINSTANCE.getEndpointType_Style();

        /**
         * The meta object literal for the '{@link net.ogdf.ogml.impl.FillTypeImpl <em>Fill Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.impl.FillTypeImpl
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getFillType()
         * @generated
         */
        EClass FILL_TYPE = eINSTANCE.getFillType();

        /**
         * The meta object literal for the '<em><b>Color</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute FILL_TYPE__COLOR = eINSTANCE.getFillType_Color();

        /**
         * The meta object literal for the '<em><b>Pattern</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute FILL_TYPE__PATTERN = eINSTANCE.getFillType_Pattern();

        /**
         * The meta object literal for the '<em><b>Pattern Color</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute FILL_TYPE__PATTERN_COLOR = eINSTANCE.getFillType_PatternColor();

        /**
         * The meta object literal for the '{@link net.ogdf.ogml.impl.FontTypeImpl <em>Font Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.impl.FontTypeImpl
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getFontType()
         * @generated
         */
        EClass FONT_TYPE = eINSTANCE.getFontType();

        /**
         * The meta object literal for the '<em><b>Color</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute FONT_TYPE__COLOR = eINSTANCE.getFontType_Color();

        /**
         * The meta object literal for the '<em><b>Family</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute FONT_TYPE__FAMILY = eINSTANCE.getFontType_Family();

        /**
         * The meta object literal for the '<em><b>Size</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute FONT_TYPE__SIZE = eINSTANCE.getFontType_Size();

        /**
         * The meta object literal for the '<em><b>Stretch</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute FONT_TYPE__STRETCH = eINSTANCE.getFontType_Stretch();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute FONT_TYPE__STYLE = eINSTANCE.getFontType_Style();

        /**
         * The meta object literal for the '<em><b>Variant</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute FONT_TYPE__VARIANT = eINSTANCE.getFontType_Variant();

        /**
         * The meta object literal for the '<em><b>Weight</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute FONT_TYPE__WEIGHT = eINSTANCE.getFontType_Weight();

        /**
         * The meta object literal for the '{@link net.ogdf.ogml.impl.GraphStyleTypeImpl <em>Graph Style Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.impl.GraphStyleTypeImpl
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getGraphStyleType()
         * @generated
         */
        EClass GRAPH_STYLE_TYPE = eINSTANCE.getGraphStyleType();

        /**
         * The meta object literal for the '<em><b>Data</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference GRAPH_STYLE_TYPE__DATA = eINSTANCE.getGraphStyleType_Data();

        /**
         * The meta object literal for the '<em><b>Default Edge Template</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute GRAPH_STYLE_TYPE__DEFAULT_EDGE_TEMPLATE = eINSTANCE.getGraphStyleType_DefaultEdgeTemplate();

        /**
         * The meta object literal for the '<em><b>Default Label Template</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute GRAPH_STYLE_TYPE__DEFAULT_LABEL_TEMPLATE = eINSTANCE.getGraphStyleType_DefaultLabelTemplate();

        /**
         * The meta object literal for the '<em><b>Default Node Template</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute GRAPH_STYLE_TYPE__DEFAULT_NODE_TEMPLATE = eINSTANCE.getGraphStyleType_DefaultNodeTemplate();

        /**
         * The meta object literal for the '{@link net.ogdf.ogml.impl.GraphTypeImpl <em>Graph Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.impl.GraphTypeImpl
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getGraphType()
         * @generated
         */
        EClass GRAPH_TYPE = eINSTANCE.getGraphType();

        /**
         * The meta object literal for the '<em><b>Data</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference GRAPH_TYPE__DATA = eINSTANCE.getGraphType_Data();

        /**
         * The meta object literal for the '<em><b>Structure</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference GRAPH_TYPE__STRUCTURE = eINSTANCE.getGraphType_Structure();

        /**
         * The meta object literal for the '<em><b>Layout</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference GRAPH_TYPE__LAYOUT = eINSTANCE.getGraphType_Layout();

        /**
         * The meta object literal for the '{@link net.ogdf.ogml.impl.IntTypeImpl <em>Int Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.impl.IntTypeImpl
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getIntType()
         * @generated
         */
        EClass INT_TYPE = eINSTANCE.getIntType();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute INT_TYPE__NAME = eINSTANCE.getIntType_Name();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute INT_TYPE__VALUE = eINSTANCE.getIntType_Value();

        /**
         * The meta object literal for the '{@link net.ogdf.ogml.impl.KeysTypeImpl <em>Keys Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.impl.KeysTypeImpl
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getKeysType()
         * @generated
         */
        EClass KEYS_TYPE = eINSTANCE.getKeysType();

        /**
         * The meta object literal for the '<em><b>Key</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference KEYS_TYPE__KEY = eINSTANCE.getKeysType_Key();

        /**
         * The meta object literal for the '{@link net.ogdf.ogml.impl.KeyTypeImpl <em>Key Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.impl.KeyTypeImpl
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getKeyType()
         * @generated
         */
        EClass KEY_TYPE = eINSTANCE.getKeyType();

        /**
         * The meta object literal for the '<em><b>Key Value</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference KEY_TYPE__KEY_VALUE = eINSTANCE.getKeyType_KeyValue();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KEY_TYPE__ID = eINSTANCE.getKeyType_Id();

        /**
         * The meta object literal for the '{@link net.ogdf.ogml.impl.KeyValueTypeImpl <em>Key Value Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.impl.KeyValueTypeImpl
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getKeyValueType()
         * @generated
         */
        EClass KEY_VALUE_TYPE = eINSTANCE.getKeyValueType();

        /**
         * The meta object literal for the '<em><b>Dafault</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KEY_VALUE_TYPE__DAFAULT = eINSTANCE.getKeyValueType_Dafault();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KEY_VALUE_TYPE__NAME = eINSTANCE.getKeyValueType_Name();

        /**
         * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KEY_VALUE_TYPE__TYPE = eINSTANCE.getKeyValueType_Type();

        /**
         * The meta object literal for the '{@link net.ogdf.ogml.impl.LabelConstraintTypeImpl <em>Label Constraint Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.impl.LabelConstraintTypeImpl
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getLabelConstraintType()
         * @generated
         */
        EClass LABEL_CONSTRAINT_TYPE = eINSTANCE.getLabelConstraintType();

        /**
         * The meta object literal for the '<em><b>Id Ref</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute LABEL_CONSTRAINT_TYPE__ID_REF = eINSTANCE.getLabelConstraintType_IdRef();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute LABEL_CONSTRAINT_TYPE__NAME = eINSTANCE.getLabelConstraintType_Name();

        /**
         * The meta object literal for the '{@link net.ogdf.ogml.impl.LabelLayoutTypeImpl <em>Label Layout Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.impl.LabelLayoutTypeImpl
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getLabelLayoutType()
         * @generated
         */
        EClass LABEL_LAYOUT_TYPE = eINSTANCE.getLabelLayoutType();

        /**
         * The meta object literal for the '<em><b>Data</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference LABEL_LAYOUT_TYPE__DATA = eINSTANCE.getLabelLayoutType_Data();

        /**
         * The meta object literal for the '<em><b>Template</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference LABEL_LAYOUT_TYPE__TEMPLATE = eINSTANCE.getLabelLayoutType_Template();

        /**
         * The meta object literal for the '<em><b>Location</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference LABEL_LAYOUT_TYPE__LOCATION = eINSTANCE.getLabelLayoutType_Location();

        /**
         * The meta object literal for the '<em><b>Text</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference LABEL_LAYOUT_TYPE__TEXT = eINSTANCE.getLabelLayoutType_Text();

        /**
         * The meta object literal for the '<em><b>Font</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference LABEL_LAYOUT_TYPE__FONT = eINSTANCE.getLabelLayoutType_Font();

        /**
         * The meta object literal for the '<em><b>Id Ref</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute LABEL_LAYOUT_TYPE__ID_REF = eINSTANCE.getLabelLayoutType_IdRef();

        /**
         * The meta object literal for the '{@link net.ogdf.ogml.impl.LabelStyleTemplateTypeImpl <em>Label Style Template Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.impl.LabelStyleTemplateTypeImpl
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getLabelStyleTemplateType()
         * @generated
         */
        EClass LABEL_STYLE_TEMPLATE_TYPE = eINSTANCE.getLabelStyleTemplateType();

        /**
         * The meta object literal for the '<em><b>Data</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference LABEL_STYLE_TEMPLATE_TYPE__DATA = eINSTANCE.getLabelStyleTemplateType_Data();

        /**
         * The meta object literal for the '<em><b>Template</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference LABEL_STYLE_TEMPLATE_TYPE__TEMPLATE = eINSTANCE.getLabelStyleTemplateType_Template();

        /**
         * The meta object literal for the '<em><b>Text</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference LABEL_STYLE_TEMPLATE_TYPE__TEXT = eINSTANCE.getLabelStyleTemplateType_Text();

        /**
         * The meta object literal for the '<em><b>Font</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference LABEL_STYLE_TEMPLATE_TYPE__FONT = eINSTANCE.getLabelStyleTemplateType_Font();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute LABEL_STYLE_TEMPLATE_TYPE__ID = eINSTANCE.getLabelStyleTemplateType_Id();

        /**
         * The meta object literal for the '{@link net.ogdf.ogml.impl.LabelTypeImpl <em>Label Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.impl.LabelTypeImpl
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getLabelType()
         * @generated
         */
        EClass LABEL_TYPE = eINSTANCE.getLabelType();

        /**
         * The meta object literal for the '<em><b>Data</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference LABEL_TYPE__DATA = eINSTANCE.getLabelType_Data();

        /**
         * The meta object literal for the '<em><b>Content</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute LABEL_TYPE__CONTENT = eINSTANCE.getLabelType_Content();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute LABEL_TYPE__ID = eINSTANCE.getLabelType_Id();

        /**
         * The meta object literal for the '{@link net.ogdf.ogml.impl.LayoutTypeImpl <em>Layout Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.impl.LayoutTypeImpl
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getLayoutType()
         * @generated
         */
        EClass LAYOUT_TYPE = eINSTANCE.getLayoutType();

        /**
         * The meta object literal for the '<em><b>Data</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference LAYOUT_TYPE__DATA = eINSTANCE.getLayoutType_Data();

        /**
         * The meta object literal for the '<em><b>Style Templates</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference LAYOUT_TYPE__STYLE_TEMPLATES = eINSTANCE.getLayoutType_StyleTemplates();

        /**
         * The meta object literal for the '<em><b>Styles</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference LAYOUT_TYPE__STYLES = eINSTANCE.getLayoutType_Styles();

        /**
         * The meta object literal for the '<em><b>Constraints</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference LAYOUT_TYPE__CONSTRAINTS = eINSTANCE.getLayoutType_Constraints();

        /**
         * The meta object literal for the '{@link net.ogdf.ogml.impl.LineStyleTypeImpl <em>Line Style Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.impl.LineStyleTypeImpl
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getLineStyleType()
         * @generated
         */
        EClass LINE_STYLE_TYPE = eINSTANCE.getLineStyleType();

        /**
         * The meta object literal for the '<em><b>Color</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute LINE_STYLE_TYPE__COLOR = eINSTANCE.getLineStyleType_Color();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute LINE_STYLE_TYPE__STYLE = eINSTANCE.getLineStyleType_Style();

        /**
         * The meta object literal for the '<em><b>Width</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute LINE_STYLE_TYPE__WIDTH = eINSTANCE.getLineStyleType_Width();

        /**
         * The meta object literal for the '{@link net.ogdf.ogml.impl.LineTypeImpl <em>Line Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.impl.LineTypeImpl
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getLineType()
         * @generated
         */
        EClass LINE_TYPE = eINSTANCE.getLineType();

        /**
         * The meta object literal for the '<em><b>Color</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute LINE_TYPE__COLOR = eINSTANCE.getLineType_Color();

        /**
         * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute LINE_TYPE__TYPE = eINSTANCE.getLineType_Type();

        /**
         * The meta object literal for the '<em><b>Width</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute LINE_TYPE__WIDTH = eINSTANCE.getLineType_Width();

        /**
         * The meta object literal for the '{@link net.ogdf.ogml.impl.LocationTypeImpl <em>Location Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.impl.LocationTypeImpl
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getLocationType()
         * @generated
         */
        EClass LOCATION_TYPE = eINSTANCE.getLocationType();

        /**
         * The meta object literal for the '<em><b>X</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute LOCATION_TYPE__X = eINSTANCE.getLocationType_X();

        /**
         * The meta object literal for the '<em><b>Y</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute LOCATION_TYPE__Y = eINSTANCE.getLocationType_Y();

        /**
         * The meta object literal for the '<em><b>Z</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute LOCATION_TYPE__Z = eINSTANCE.getLocationType_Z();

        /**
         * The meta object literal for the '{@link net.ogdf.ogml.impl.NodeConstraintTypeImpl <em>Node Constraint Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.impl.NodeConstraintTypeImpl
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getNodeConstraintType()
         * @generated
         */
        EClass NODE_CONSTRAINT_TYPE = eINSTANCE.getNodeConstraintType();

        /**
         * The meta object literal for the '<em><b>Id Ref</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute NODE_CONSTRAINT_TYPE__ID_REF = eINSTANCE.getNodeConstraintType_IdRef();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute NODE_CONSTRAINT_TYPE__NAME = eINSTANCE.getNodeConstraintType_Name();

        /**
         * The meta object literal for the '{@link net.ogdf.ogml.impl.NodeLayoutTypeImpl <em>Node Layout Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.impl.NodeLayoutTypeImpl
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getNodeLayoutType()
         * @generated
         */
        EClass NODE_LAYOUT_TYPE = eINSTANCE.getNodeLayoutType();

        /**
         * The meta object literal for the '<em><b>Data</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference NODE_LAYOUT_TYPE__DATA = eINSTANCE.getNodeLayoutType_Data();

        /**
         * The meta object literal for the '<em><b>Template</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference NODE_LAYOUT_TYPE__TEMPLATE = eINSTANCE.getNodeLayoutType_Template();

        /**
         * The meta object literal for the '<em><b>Location</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference NODE_LAYOUT_TYPE__LOCATION = eINSTANCE.getNodeLayoutType_Location();

        /**
         * The meta object literal for the '<em><b>Shape</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference NODE_LAYOUT_TYPE__SHAPE = eINSTANCE.getNodeLayoutType_Shape();

        /**
         * The meta object literal for the '<em><b>Fill</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference NODE_LAYOUT_TYPE__FILL = eINSTANCE.getNodeLayoutType_Fill();

        /**
         * The meta object literal for the '<em><b>Line</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference NODE_LAYOUT_TYPE__LINE = eINSTANCE.getNodeLayoutType_Line();

        /**
         * The meta object literal for the '<em><b>Id Ref</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute NODE_LAYOUT_TYPE__ID_REF = eINSTANCE.getNodeLayoutType_IdRef();

        /**
         * The meta object literal for the '{@link net.ogdf.ogml.impl.NodeStyleTemplateTypeImpl <em>Node Style Template Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.impl.NodeStyleTemplateTypeImpl
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getNodeStyleTemplateType()
         * @generated
         */
        EClass NODE_STYLE_TEMPLATE_TYPE = eINSTANCE.getNodeStyleTemplateType();

        /**
         * The meta object literal for the '<em><b>Data</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference NODE_STYLE_TEMPLATE_TYPE__DATA = eINSTANCE.getNodeStyleTemplateType_Data();

        /**
         * The meta object literal for the '<em><b>Template</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference NODE_STYLE_TEMPLATE_TYPE__TEMPLATE = eINSTANCE.getNodeStyleTemplateType_Template();

        /**
         * The meta object literal for the '<em><b>Shape</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference NODE_STYLE_TEMPLATE_TYPE__SHAPE = eINSTANCE.getNodeStyleTemplateType_Shape();

        /**
         * The meta object literal for the '<em><b>Fill</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference NODE_STYLE_TEMPLATE_TYPE__FILL = eINSTANCE.getNodeStyleTemplateType_Fill();

        /**
         * The meta object literal for the '<em><b>Line</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference NODE_STYLE_TEMPLATE_TYPE__LINE = eINSTANCE.getNodeStyleTemplateType_Line();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute NODE_STYLE_TEMPLATE_TYPE__ID = eINSTANCE.getNodeStyleTemplateType_Id();

        /**
         * The meta object literal for the '{@link net.ogdf.ogml.impl.NodeTypeImpl <em>Node Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.impl.NodeTypeImpl
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getNodeType()
         * @generated
         */
        EClass NODE_TYPE = eINSTANCE.getNodeType();

        /**
         * The meta object literal for the '<em><b>Data</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference NODE_TYPE__DATA = eINSTANCE.getNodeType_Data();

        /**
         * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute NODE_TYPE__GROUP = eINSTANCE.getNodeType_Group();

        /**
         * The meta object literal for the '<em><b>Label</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference NODE_TYPE__LABEL = eINSTANCE.getNodeType_Label();

        /**
         * The meta object literal for the '<em><b>Node</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference NODE_TYPE__NODE = eINSTANCE.getNodeType_Node();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute NODE_TYPE__ID = eINSTANCE.getNodeType_Id();

        /**
         * The meta object literal for the '{@link net.ogdf.ogml.impl.NumberTypeImpl <em>Number Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.impl.NumberTypeImpl
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getNumberType()
         * @generated
         */
        EClass NUMBER_TYPE = eINSTANCE.getNumberType();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute NUMBER_TYPE__NAME = eINSTANCE.getNumberType_Name();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute NUMBER_TYPE__VALUE = eINSTANCE.getNumberType_Value();

        /**
         * The meta object literal for the '{@link net.ogdf.ogml.impl.OgmlTypeImpl <em>Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.impl.OgmlTypeImpl
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getOgmlType()
         * @generated
         */
        EClass OGML_TYPE = eINSTANCE.getOgmlType();

        /**
         * The meta object literal for the '<em><b>Keys</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference OGML_TYPE__KEYS = eINSTANCE.getOgmlType_Keys();

        /**
         * The meta object literal for the '<em><b>Graph</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference OGML_TYPE__GRAPH = eINSTANCE.getOgmlType_Graph();

        /**
         * The meta object literal for the '{@link net.ogdf.ogml.impl.PointTypeImpl <em>Point Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.impl.PointTypeImpl
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getPointType()
         * @generated
         */
        EClass POINT_TYPE = eINSTANCE.getPointType();

        /**
         * The meta object literal for the '<em><b>Data</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference POINT_TYPE__DATA = eINSTANCE.getPointType_Data();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute POINT_TYPE__ID = eINSTANCE.getPointType_Id();

        /**
         * The meta object literal for the '<em><b>X</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute POINT_TYPE__X = eINSTANCE.getPointType_X();

        /**
         * The meta object literal for the '<em><b>Y</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute POINT_TYPE__Y = eINSTANCE.getPointType_Y();

        /**
         * The meta object literal for the '<em><b>Z</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute POINT_TYPE__Z = eINSTANCE.getPointType_Z();

        /**
         * The meta object literal for the '{@link net.ogdf.ogml.impl.SegmentTypeImpl <em>Segment Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.impl.SegmentTypeImpl
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getSegmentType()
         * @generated
         */
        EClass SEGMENT_TYPE = eINSTANCE.getSegmentType();

        /**
         * The meta object literal for the '<em><b>Data</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference SEGMENT_TYPE__DATA = eINSTANCE.getSegmentType_Data();

        /**
         * The meta object literal for the '<em><b>Line</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference SEGMENT_TYPE__LINE = eINSTANCE.getSegmentType_Line();

        /**
         * The meta object literal for the '<em><b>Endpoint</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference SEGMENT_TYPE__ENDPOINT = eINSTANCE.getSegmentType_Endpoint();

        /**
         * The meta object literal for the '{@link net.ogdf.ogml.impl.ShapeType1Impl <em>Shape Type1</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.impl.ShapeType1Impl
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getShapeType1()
         * @generated
         */
        EClass SHAPE_TYPE1 = eINSTANCE.getShapeType1();

        /**
         * The meta object literal for the '<em><b>Height</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SHAPE_TYPE1__HEIGHT = eINSTANCE.getShapeType1_Height();

        /**
         * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SHAPE_TYPE1__TYPE = eINSTANCE.getShapeType1_Type();

        /**
         * The meta object literal for the '<em><b>Uri</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SHAPE_TYPE1__URI = eINSTANCE.getShapeType1_Uri();

        /**
         * The meta object literal for the '<em><b>Width</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SHAPE_TYPE1__WIDTH = eINSTANCE.getShapeType1_Width();

        /**
         * The meta object literal for the '{@link net.ogdf.ogml.impl.SourceStyleTypeImpl <em>Source Style Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.impl.SourceStyleTypeImpl
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getSourceStyleType()
         * @generated
         */
        EClass SOURCE_STYLE_TYPE = eINSTANCE.getSourceStyleType();

        /**
         * The meta object literal for the '<em><b>Color</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SOURCE_STYLE_TYPE__COLOR = eINSTANCE.getSourceStyleType_Color();

        /**
         * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SOURCE_STYLE_TYPE__TYPE = eINSTANCE.getSourceStyleType_Type();

        /**
         * The meta object literal for the '<em><b>Uri</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SOURCE_STYLE_TYPE__URI = eINSTANCE.getSourceStyleType_Uri();

        /**
         * The meta object literal for the '<em><b>Width</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SOURCE_STYLE_TYPE__WIDTH = eINSTANCE.getSourceStyleType_Width();

        /**
         * The meta object literal for the '{@link net.ogdf.ogml.impl.SourceTargetTypeImpl <em>Source Target Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.impl.SourceTargetTypeImpl
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getSourceTargetType()
         * @generated
         */
        EClass SOURCE_TARGET_TYPE = eINSTANCE.getSourceTargetType();

        /**
         * The meta object literal for the '<em><b>Data</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference SOURCE_TARGET_TYPE__DATA = eINSTANCE.getSourceTargetType_Data();

        /**
         * The meta object literal for the '<em><b>Label</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference SOURCE_TARGET_TYPE__LABEL = eINSTANCE.getSourceTargetType_Label();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SOURCE_TARGET_TYPE__ID = eINSTANCE.getSourceTargetType_Id();

        /**
         * The meta object literal for the '<em><b>Id Ref</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SOURCE_TARGET_TYPE__ID_REF = eINSTANCE.getSourceTargetType_IdRef();

        /**
         * The meta object literal for the '{@link net.ogdf.ogml.impl.StructureTypeImpl <em>Structure Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.impl.StructureTypeImpl
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getStructureType()
         * @generated
         */
        EClass STRUCTURE_TYPE = eINSTANCE.getStructureType();

        /**
         * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute STRUCTURE_TYPE__GROUP = eINSTANCE.getStructureType_Group();

        /**
         * The meta object literal for the '<em><b>Data</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference STRUCTURE_TYPE__DATA = eINSTANCE.getStructureType_Data();

        /**
         * The meta object literal for the '<em><b>Node</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference STRUCTURE_TYPE__NODE = eINSTANCE.getStructureType_Node();

        /**
         * The meta object literal for the '<em><b>Edge</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference STRUCTURE_TYPE__EDGE = eINSTANCE.getStructureType_Edge();

        /**
         * The meta object literal for the '<em><b>Label</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference STRUCTURE_TYPE__LABEL = eINSTANCE.getStructureType_Label();

        /**
         * The meta object literal for the '{@link net.ogdf.ogml.impl.StylesTypeImpl <em>Styles Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.impl.StylesTypeImpl
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getStylesType()
         * @generated
         */
        EClass STYLES_TYPE = eINSTANCE.getStylesType();

        /**
         * The meta object literal for the '<em><b>Graph Style</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference STYLES_TYPE__GRAPH_STYLE = eINSTANCE.getStylesType_GraphStyle();

        /**
         * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute STYLES_TYPE__GROUP = eINSTANCE.getStylesType_Group();

        /**
         * The meta object literal for the '<em><b>Data</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference STYLES_TYPE__DATA = eINSTANCE.getStylesType_Data();

        /**
         * The meta object literal for the '<em><b>Node Style</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference STYLES_TYPE__NODE_STYLE = eINSTANCE.getStylesType_NodeStyle();

        /**
         * The meta object literal for the '<em><b>Edge Style</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference STYLES_TYPE__EDGE_STYLE = eINSTANCE.getStylesType_EdgeStyle();

        /**
         * The meta object literal for the '<em><b>Label Style</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference STYLES_TYPE__LABEL_STYLE = eINSTANCE.getStylesType_LabelStyle();

        /**
         * The meta object literal for the '{@link net.ogdf.ogml.impl.StyleTemplatesTypeImpl <em>Style Templates Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.impl.StyleTemplatesTypeImpl
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getStyleTemplatesType()
         * @generated
         */
        EClass STYLE_TEMPLATES_TYPE = eINSTANCE.getStyleTemplatesType();

        /**
         * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute STYLE_TEMPLATES_TYPE__GROUP = eINSTANCE.getStyleTemplatesType_Group();

        /**
         * The meta object literal for the '<em><b>Data</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference STYLE_TEMPLATES_TYPE__DATA = eINSTANCE.getStyleTemplatesType_Data();

        /**
         * The meta object literal for the '<em><b>Node Style Template</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference STYLE_TEMPLATES_TYPE__NODE_STYLE_TEMPLATE = eINSTANCE.getStyleTemplatesType_NodeStyleTemplate();

        /**
         * The meta object literal for the '<em><b>Edge Style Template</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference STYLE_TEMPLATES_TYPE__EDGE_STYLE_TEMPLATE = eINSTANCE.getStyleTemplatesType_EdgeStyleTemplate();

        /**
         * The meta object literal for the '<em><b>Label Style Template</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference STYLE_TEMPLATES_TYPE__LABEL_STYLE_TEMPLATE = eINSTANCE.getStyleTemplatesType_LabelStyleTemplate();

        /**
         * The meta object literal for the '{@link net.ogdf.ogml.impl.TargetStyleTypeImpl <em>Target Style Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.impl.TargetStyleTypeImpl
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getTargetStyleType()
         * @generated
         */
        EClass TARGET_STYLE_TYPE = eINSTANCE.getTargetStyleType();

        /**
         * The meta object literal for the '<em><b>Color</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TARGET_STYLE_TYPE__COLOR = eINSTANCE.getTargetStyleType_Color();

        /**
         * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TARGET_STYLE_TYPE__TYPE = eINSTANCE.getTargetStyleType_Type();

        /**
         * The meta object literal for the '<em><b>Uri</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TARGET_STYLE_TYPE__URI = eINSTANCE.getTargetStyleType_Uri();

        /**
         * The meta object literal for the '<em><b>Width</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TARGET_STYLE_TYPE__WIDTH = eINSTANCE.getTargetStyleType_Width();

        /**
         * The meta object literal for the '{@link net.ogdf.ogml.impl.TemplateTypeImpl <em>Template Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.impl.TemplateTypeImpl
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getTemplateType()
         * @generated
         */
        EClass TEMPLATE_TYPE = eINSTANCE.getTemplateType();

        /**
         * The meta object literal for the '<em><b>Id Ref</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TEMPLATE_TYPE__ID_REF = eINSTANCE.getTemplateType_IdRef();

        /**
         * The meta object literal for the '{@link net.ogdf.ogml.impl.TextTypeImpl <em>Text Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.impl.TextTypeImpl
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getTextType()
         * @generated
         */
        EClass TEXT_TYPE = eINSTANCE.getTextType();

        /**
         * The meta object literal for the '<em><b>Alignment</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TEXT_TYPE__ALIGNMENT = eINSTANCE.getTextType_Alignment();

        /**
         * The meta object literal for the '<em><b>Decoration</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TEXT_TYPE__DECORATION = eINSTANCE.getTextType_Decoration();

        /**
         * The meta object literal for the '<em><b>Rotation</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TEXT_TYPE__ROTATION = eINSTANCE.getTextType_Rotation();

        /**
         * The meta object literal for the '<em><b>Transform</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TEXT_TYPE__TRANSFORM = eINSTANCE.getTextType_Transform();

        /**
         * The meta object literal for the '{@link net.ogdf.ogml.impl.UriTypeImpl <em>Uri Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.impl.UriTypeImpl
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getUriType()
         * @generated
         */
        EClass URI_TYPE = eINSTANCE.getUriType();

        /**
         * The meta object literal for the '<em><b>Uri</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute URI_TYPE__URI = eINSTANCE.getUriType_Uri();

        /**
         * The meta object literal for the '{@link net.ogdf.ogml.AlignmentType <em>Alignment Type</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.AlignmentType
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getAlignmentType()
         * @generated
         */
        EEnum ALIGNMENT_TYPE = eINSTANCE.getAlignmentType();

        /**
         * The meta object literal for the '{@link net.ogdf.ogml.BoolIntType <em>Bool Int Type</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.BoolIntType
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getBoolIntType()
         * @generated
         */
        EEnum BOOL_INT_TYPE = eINSTANCE.getBoolIntType();

        /**
         * The meta object literal for the '{@link net.ogdf.ogml.BoolTFType <em>Bool TF Type</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.BoolTFType
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getBoolTFType()
         * @generated
         */
        EEnum BOOL_TF_TYPE = eINSTANCE.getBoolTFType();

        /**
         * The meta object literal for the '{@link net.ogdf.ogml.DecorationType <em>Decoration Type</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.DecorationType
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getDecorationType()
         * @generated
         */
        EEnum DECORATION_TYPE = eINSTANCE.getDecorationType();

        /**
         * The meta object literal for the '{@link net.ogdf.ogml.EndpointStylesType <em>Endpoint Styles Type</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.EndpointStylesType
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getEndpointStylesType()
         * @generated
         */
        EEnum ENDPOINT_STYLES_TYPE = eINSTANCE.getEndpointStylesType();

        /**
         * The meta object literal for the '{@link net.ogdf.ogml.FontStretchType <em>Font Stretch Type</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.FontStretchType
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getFontStretchType()
         * @generated
         */
        EEnum FONT_STRETCH_TYPE = eINSTANCE.getFontStretchType();

        /**
         * The meta object literal for the '{@link net.ogdf.ogml.FontStyleType <em>Font Style Type</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.FontStyleType
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getFontStyleType()
         * @generated
         */
        EEnum FONT_STYLE_TYPE = eINSTANCE.getFontStyleType();

        /**
         * The meta object literal for the '{@link net.ogdf.ogml.FontVariantType <em>Font Variant Type</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.FontVariantType
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getFontVariantType()
         * @generated
         */
        EEnum FONT_VARIANT_TYPE = eINSTANCE.getFontVariantType();

        /**
         * The meta object literal for the '{@link net.ogdf.ogml.FontWeightType <em>Font Weight Type</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.FontWeightType
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getFontWeightType()
         * @generated
         */
        EEnum FONT_WEIGHT_TYPE = eINSTANCE.getFontWeightType();

        /**
         * The meta object literal for the '{@link net.ogdf.ogml.LineStyleTypeType <em>Line Style Type Type</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.LineStyleTypeType
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getLineStyleTypeType()
         * @generated
         */
        EEnum LINE_STYLE_TYPE_TYPE = eINSTANCE.getLineStyleTypeType();

        /**
         * The meta object literal for the '{@link net.ogdf.ogml.PatternType <em>Pattern Type</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.PatternType
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getPatternType()
         * @generated
         */
        EEnum PATTERN_TYPE = eINSTANCE.getPatternType();

        /**
         * The meta object literal for the '{@link net.ogdf.ogml.ShapeType <em>Shape Type</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.ShapeType
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getShapeType()
         * @generated
         */
        EEnum SHAPE_TYPE = eINSTANCE.getShapeType();

        /**
         * The meta object literal for the '{@link net.ogdf.ogml.TransformType <em>Transform Type</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.TransformType
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getTransformType()
         * @generated
         */
        EEnum TRANSFORM_TYPE = eINSTANCE.getTransformType();

        /**
         * The meta object literal for the '<em>Alignment Type Object</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.AlignmentType
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getAlignmentTypeObject()
         * @generated
         */
        EDataType ALIGNMENT_TYPE_OBJECT = eINSTANCE.getAlignmentTypeObject();

        /**
         * The meta object literal for the '<em>Bool Int Type Object</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.BoolIntType
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getBoolIntTypeObject()
         * @generated
         */
        EDataType BOOL_INT_TYPE_OBJECT = eINSTANCE.getBoolIntTypeObject();

        /**
         * The meta object literal for the '<em>Bool TF Type Object</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.BoolTFType
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getBoolTFTypeObject()
         * @generated
         */
        EDataType BOOL_TF_TYPE_OBJECT = eINSTANCE.getBoolTFTypeObject();

        /**
         * The meta object literal for the '<em>Decoration Type Object</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.DecorationType
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getDecorationTypeObject()
         * @generated
         */
        EDataType DECORATION_TYPE_OBJECT = eINSTANCE.getDecorationTypeObject();

        /**
         * The meta object literal for the '<em>Endpoint Styles Type Object</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.EndpointStylesType
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getEndpointStylesTypeObject()
         * @generated
         */
        EDataType ENDPOINT_STYLES_TYPE_OBJECT = eINSTANCE.getEndpointStylesTypeObject();

        /**
         * The meta object literal for the '<em>Font Stretch Type Object</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.FontStretchType
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getFontStretchTypeObject()
         * @generated
         */
        EDataType FONT_STRETCH_TYPE_OBJECT = eINSTANCE.getFontStretchTypeObject();

        /**
         * The meta object literal for the '<em>Font Style Type Object</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.FontStyleType
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getFontStyleTypeObject()
         * @generated
         */
        EDataType FONT_STYLE_TYPE_OBJECT = eINSTANCE.getFontStyleTypeObject();

        /**
         * The meta object literal for the '<em>Font Variant Type Object</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.FontVariantType
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getFontVariantTypeObject()
         * @generated
         */
        EDataType FONT_VARIANT_TYPE_OBJECT = eINSTANCE.getFontVariantTypeObject();

        /**
         * The meta object literal for the '<em>Font Weight Type Object</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.FontWeightType
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getFontWeightTypeObject()
         * @generated
         */
        EDataType FONT_WEIGHT_TYPE_OBJECT = eINSTANCE.getFontWeightTypeObject();

        /**
         * The meta object literal for the '<em>Line Style Type Type Object</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.LineStyleTypeType
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getLineStyleTypeTypeObject()
         * @generated
         */
        EDataType LINE_STYLE_TYPE_TYPE_OBJECT = eINSTANCE.getLineStyleTypeTypeObject();

        /**
         * The meta object literal for the '<em>Pattern Type Object</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.PatternType
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getPatternTypeObject()
         * @generated
         */
        EDataType PATTERN_TYPE_OBJECT = eINSTANCE.getPatternTypeObject();

        /**
         * The meta object literal for the '<em>Shape Type Object</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.ShapeType
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getShapeTypeObject()
         * @generated
         */
        EDataType SHAPE_TYPE_OBJECT = eINSTANCE.getShapeTypeObject();

        /**
         * The meta object literal for the '<em>Transform Type Object</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see net.ogdf.ogml.TransformType
         * @see net.ogdf.ogml.impl.OgmlPackageImpl#getTransformTypeObject()
         * @generated
         */
        EDataType TRANSFORM_TYPE_OBJECT = eINSTANCE.getTransformTypeObject();

    }

} //OgmlPackage
