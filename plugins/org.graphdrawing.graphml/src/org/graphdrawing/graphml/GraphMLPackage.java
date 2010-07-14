/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.graphdrawing.graphml;

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
 * <!-- begin-model-doc -->
 * 
 *      The schema corresponding to this document defines the structural
 *      layer of the Graph Markup Language (GraphML).
 *      Although a DTD is provided, this schema is, together with its extensions
 *      http://graphml.graphdrawing.org/xmlns/1.1/graphml-attributes.xsd
 *      and
 *      http://graphml.graphdrawing.org/xmlns/1.1/graphml-parseinfo.xsd,
 *      the only normative reference.
 *     
 * 
 *       The attribute groups &lt;element_name&gt;.extra.attrib may be used
 *       for adding user defined attributes to the elements
 *       &lt;element_name&gt;.
 *       The attribute group common.extra.attrib may be used for adding
 *       user defined attributes to all elements.
 *     
 * 
 *       Complex type definitions for the GraphML structural layer elements:
 *       &lt;data&gt;, &lt;default&gt;, &lt;key&gt;, &lt;graphml&gt;, &lt;graph&gt;, 
 *       &lt;node&gt;, &lt;port&gt;, 
 *       &lt;edge&gt;, &lt;hyperedge&gt;, &lt;endpoint&gt; and &lt;locator&gt;.
 *       The names of the complex types are constructed corresponding
 *       to the pattern element_name.type.
 *       (The only remaining GraphML structural layer element
 *       &lt;desc&gt; is of simple type xs:string.)
 *     
 * <!-- end-model-doc -->
 * @see org.graphdrawing.graphml.GraphMLFactory
 * @model kind="package"
 * @generated
 */
public interface GraphMLPackage extends EPackage {
    /**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "graphml";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://graphml.graphdrawing.org/xmlns";

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "graphml";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    GraphMLPackage eINSTANCE = org.graphdrawing.graphml.impl.GraphMLPackageImpl.init();

    /**
     * The meta object id for the '{@link org.graphdrawing.graphml.impl.DataExtensionTypeImpl <em>Data Extension Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.graphdrawing.graphml.impl.DataExtensionTypeImpl
     * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getDataExtensionType()
     * @generated
     */
    int DATA_EXTENSION_TYPE = 0;

    /**
     * The feature id for the '<em><b>Mixed</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATA_EXTENSION_TYPE__MIXED = 0;

    /**
     * The number of structural features of the '<em>Data Extension Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATA_EXTENSION_TYPE_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '{@link org.graphdrawing.graphml.impl.DataTypeImpl <em>Data Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.graphdrawing.graphml.impl.DataTypeImpl
     * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getDataType()
     * @generated
     */
    int DATA_TYPE = 1;

    /**
     * The feature id for the '<em><b>Mixed</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATA_TYPE__MIXED = DATA_EXTENSION_TYPE__MIXED;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATA_TYPE__ID = DATA_EXTENSION_TYPE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Key</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATA_TYPE__KEY = DATA_EXTENSION_TYPE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Time</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATA_TYPE__TIME = DATA_EXTENSION_TYPE_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Data Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATA_TYPE_FEATURE_COUNT = DATA_EXTENSION_TYPE_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '{@link org.graphdrawing.graphml.impl.DefaultTypeImpl <em>Default Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.graphdrawing.graphml.impl.DefaultTypeImpl
     * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getDefaultType()
     * @generated
     */
    int DEFAULT_TYPE = 2;

    /**
     * The feature id for the '<em><b>Mixed</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DEFAULT_TYPE__MIXED = DATA_EXTENSION_TYPE__MIXED;

    /**
     * The number of structural features of the '<em>Default Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DEFAULT_TYPE_FEATURE_COUNT = DATA_EXTENSION_TYPE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link org.graphdrawing.graphml.impl.DocumentRootImpl <em>Document Root</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.graphdrawing.graphml.impl.DocumentRootImpl
     * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getDocumentRoot()
     * @generated
     */
    int DOCUMENT_ROOT = 3;

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
     * The feature id for the '<em><b>Data</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__DATA = 3;

    /**
     * The feature id for the '<em><b>Default</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__DEFAULT = 4;

    /**
     * The feature id for the '<em><b>Desc</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__DESC = 5;

    /**
     * The feature id for the '<em><b>Edge</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__EDGE = 6;

    /**
     * The feature id for the '<em><b>Endpoint</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__ENDPOINT = 7;

    /**
     * The feature id for the '<em><b>Graph</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__GRAPH = 8;

    /**
     * The feature id for the '<em><b>Graphml</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__GRAPHML = 9;

    /**
     * The feature id for the '<em><b>Hyperedge</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__HYPEREDGE = 10;

    /**
     * The feature id for the '<em><b>Key</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__KEY = 11;

    /**
     * The feature id for the '<em><b>Locator</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__LOCATOR = 12;

    /**
     * The feature id for the '<em><b>Node</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__NODE = 13;

    /**
     * The feature id for the '<em><b>Port</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__PORT = 14;

    /**
     * The number of structural features of the '<em>Document Root</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT_FEATURE_COUNT = 15;

    /**
     * The meta object id for the '{@link org.graphdrawing.graphml.impl.EdgeTypeImpl <em>Edge Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.graphdrawing.graphml.impl.EdgeTypeImpl
     * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getEdgeType()
     * @generated
     */
    int EDGE_TYPE = 4;

    /**
     * The feature id for the '<em><b>Desc</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE_TYPE__DESC = 0;

    /**
     * The feature id for the '<em><b>Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE_TYPE__DATA = 1;

    /**
     * The feature id for the '<em><b>Graph</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE_TYPE__GRAPH = 2;

    /**
     * The feature id for the '<em><b>Directed</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE_TYPE__DIRECTED = 3;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE_TYPE__ID = 4;

    /**
     * The feature id for the '<em><b>Source</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE_TYPE__SOURCE = 5;

    /**
     * The feature id for the '<em><b>Sourceport</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE_TYPE__SOURCEPORT = 6;

    /**
     * The feature id for the '<em><b>Target</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE_TYPE__TARGET = 7;

    /**
     * The feature id for the '<em><b>Targetport</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE_TYPE__TARGETPORT = 8;

    /**
     * The number of structural features of the '<em>Edge Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE_TYPE_FEATURE_COUNT = 9;

    /**
     * The meta object id for the '{@link org.graphdrawing.graphml.impl.EndpointTypeImpl <em>Endpoint Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.graphdrawing.graphml.impl.EndpointTypeImpl
     * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getEndpointType()
     * @generated
     */
    int ENDPOINT_TYPE = 5;

    /**
     * The feature id for the '<em><b>Desc</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENDPOINT_TYPE__DESC = 0;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENDPOINT_TYPE__ID = 1;

    /**
     * The feature id for the '<em><b>Node</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENDPOINT_TYPE__NODE = 2;

    /**
     * The feature id for the '<em><b>Port</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENDPOINT_TYPE__PORT = 3;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENDPOINT_TYPE__TYPE = 4;

    /**
     * The number of structural features of the '<em>Endpoint Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENDPOINT_TYPE_FEATURE_COUNT = 5;

    /**
     * The meta object id for the '{@link org.graphdrawing.graphml.impl.GraphmlTypeImpl <em>Graphml Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.graphdrawing.graphml.impl.GraphmlTypeImpl
     * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getGraphmlType()
     * @generated
     */
    int GRAPHML_TYPE = 6;

    /**
     * The feature id for the '<em><b>Desc</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GRAPHML_TYPE__DESC = 0;

    /**
     * The feature id for the '<em><b>Key</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GRAPHML_TYPE__KEY = 1;

    /**
     * The feature id for the '<em><b>Group</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GRAPHML_TYPE__GROUP = 2;

    /**
     * The feature id for the '<em><b>Graph</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GRAPHML_TYPE__GRAPH = 3;

    /**
     * The feature id for the '<em><b>Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GRAPHML_TYPE__DATA = 4;

    /**
     * The number of structural features of the '<em>Graphml Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GRAPHML_TYPE_FEATURE_COUNT = 5;

    /**
     * The meta object id for the '{@link org.graphdrawing.graphml.impl.GraphTypeImpl <em>Graph Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.graphdrawing.graphml.impl.GraphTypeImpl
     * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getGraphType()
     * @generated
     */
    int GRAPH_TYPE = 7;

    /**
     * The feature id for the '<em><b>Desc</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GRAPH_TYPE__DESC = 0;

    /**
     * The feature id for the '<em><b>Group</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GRAPH_TYPE__GROUP = 1;

    /**
     * The feature id for the '<em><b>Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GRAPH_TYPE__DATA = 2;

    /**
     * The feature id for the '<em><b>Node</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GRAPH_TYPE__NODE = 3;

    /**
     * The feature id for the '<em><b>Edge</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GRAPH_TYPE__EDGE = 4;

    /**
     * The feature id for the '<em><b>Hyperedge</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GRAPH_TYPE__HYPEREDGE = 5;

    /**
     * The feature id for the '<em><b>Locator</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GRAPH_TYPE__LOCATOR = 6;

    /**
     * The feature id for the '<em><b>Edgedefault</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GRAPH_TYPE__EDGEDEFAULT = 7;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GRAPH_TYPE__ID = 8;

    /**
     * The feature id for the '<em><b>Parse Edgeids</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GRAPH_TYPE__PARSE_EDGEIDS = 9;

    /**
     * The feature id for the '<em><b>Parse Edges</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GRAPH_TYPE__PARSE_EDGES = 10;

    /**
     * The feature id for the '<em><b>Parse Maxindegree</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GRAPH_TYPE__PARSE_MAXINDEGREE = 11;

    /**
     * The feature id for the '<em><b>Parse Maxoutdegree</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GRAPH_TYPE__PARSE_MAXOUTDEGREE = 12;

    /**
     * The feature id for the '<em><b>Parse Nodeids</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GRAPH_TYPE__PARSE_NODEIDS = 13;

    /**
     * The feature id for the '<em><b>Parse Nodes</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GRAPH_TYPE__PARSE_NODES = 14;

    /**
     * The feature id for the '<em><b>Parse Order</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GRAPH_TYPE__PARSE_ORDER = 15;

    /**
     * The number of structural features of the '<em>Graph Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GRAPH_TYPE_FEATURE_COUNT = 16;

    /**
     * The meta object id for the '{@link org.graphdrawing.graphml.impl.HyperedgeTypeImpl <em>Hyperedge Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.graphdrawing.graphml.impl.HyperedgeTypeImpl
     * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getHyperedgeType()
     * @generated
     */
    int HYPEREDGE_TYPE = 8;

    /**
     * The feature id for the '<em><b>Desc</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int HYPEREDGE_TYPE__DESC = 0;

    /**
     * The feature id for the '<em><b>Group</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int HYPEREDGE_TYPE__GROUP = 1;

    /**
     * The feature id for the '<em><b>Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int HYPEREDGE_TYPE__DATA = 2;

    /**
     * The feature id for the '<em><b>Endpoint</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int HYPEREDGE_TYPE__ENDPOINT = 3;

    /**
     * The feature id for the '<em><b>Graph</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int HYPEREDGE_TYPE__GRAPH = 4;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int HYPEREDGE_TYPE__ID = 5;

    /**
     * The number of structural features of the '<em>Hyperedge Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int HYPEREDGE_TYPE_FEATURE_COUNT = 6;

    /**
     * The meta object id for the '{@link org.graphdrawing.graphml.impl.KeyTypeImpl <em>Key Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.graphdrawing.graphml.impl.KeyTypeImpl
     * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getKeyType()
     * @generated
     */
    int KEY_TYPE = 9;

    /**
     * The feature id for the '<em><b>Desc</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KEY_TYPE__DESC = 0;

    /**
     * The feature id for the '<em><b>Default</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KEY_TYPE__DEFAULT = 1;

    /**
     * The feature id for the '<em><b>Attr Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KEY_TYPE__ATTR_NAME = 2;

    /**
     * The feature id for the '<em><b>Attr Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KEY_TYPE__ATTR_TYPE = 3;

    /**
     * The feature id for the '<em><b>Dynamic</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KEY_TYPE__DYNAMIC = 4;

    /**
     * The feature id for the '<em><b>For</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KEY_TYPE__FOR = 5;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KEY_TYPE__ID = 6;

    /**
     * The number of structural features of the '<em>Key Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KEY_TYPE_FEATURE_COUNT = 7;

    /**
     * The meta object id for the '{@link org.graphdrawing.graphml.impl.LocatorTypeImpl <em>Locator Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.graphdrawing.graphml.impl.LocatorTypeImpl
     * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getLocatorType()
     * @generated
     */
    int LOCATOR_TYPE = 10;

    /**
     * The feature id for the '<em><b>Href</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LOCATOR_TYPE__HREF = 0;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LOCATOR_TYPE__TYPE = 1;

    /**
     * The number of structural features of the '<em>Locator Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LOCATOR_TYPE_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '{@link org.graphdrawing.graphml.impl.NodeTypeImpl <em>Node Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.graphdrawing.graphml.impl.NodeTypeImpl
     * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getNodeType()
     * @generated
     */
    int NODE_TYPE = 11;

    /**
     * The feature id for the '<em><b>Desc</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE_TYPE__DESC = 0;

    /**
     * The feature id for the '<em><b>Group</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE_TYPE__GROUP = 1;

    /**
     * The feature id for the '<em><b>Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE_TYPE__DATA = 2;

    /**
     * The feature id for the '<em><b>Port</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE_TYPE__PORT = 3;

    /**
     * The feature id for the '<em><b>Graph</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE_TYPE__GRAPH = 4;

    /**
     * The feature id for the '<em><b>Locator</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE_TYPE__LOCATOR = 5;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE_TYPE__ID = 6;

    /**
     * The feature id for the '<em><b>Parse Indegree</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE_TYPE__PARSE_INDEGREE = 7;

    /**
     * The feature id for the '<em><b>Parse Outdegree</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE_TYPE__PARSE_OUTDEGREE = 8;

    /**
     * The number of structural features of the '<em>Node Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE_TYPE_FEATURE_COUNT = 9;

    /**
     * The meta object id for the '{@link org.graphdrawing.graphml.impl.PortTypeImpl <em>Port Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.graphdrawing.graphml.impl.PortTypeImpl
     * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getPortType()
     * @generated
     */
    int PORT_TYPE = 12;

    /**
     * The feature id for the '<em><b>Desc</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PORT_TYPE__DESC = 0;

    /**
     * The feature id for the '<em><b>Group</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PORT_TYPE__GROUP = 1;

    /**
     * The feature id for the '<em><b>Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PORT_TYPE__DATA = 2;

    /**
     * The feature id for the '<em><b>Port</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PORT_TYPE__PORT = 3;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PORT_TYPE__NAME = 4;

    /**
     * The number of structural features of the '<em>Port Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PORT_TYPE_FEATURE_COUNT = 5;

    /**
     * The meta object id for the '{@link org.graphdrawing.graphml.EndpointTypeType <em>Endpoint Type Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.graphdrawing.graphml.EndpointTypeType
     * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getEndpointTypeType()
     * @generated
     */
    int ENDPOINT_TYPE_TYPE = 13;

    /**
     * The meta object id for the '{@link org.graphdrawing.graphml.GraphEdgedefaultType <em>Graph Edgedefault Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.graphdrawing.graphml.GraphEdgedefaultType
     * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getGraphEdgedefaultType()
     * @generated
     */
    int GRAPH_EDGEDEFAULT_TYPE = 14;

    /**
     * The meta object id for the '{@link org.graphdrawing.graphml.GraphEdgeidsType <em>Graph Edgeids Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.graphdrawing.graphml.GraphEdgeidsType
     * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getGraphEdgeidsType()
     * @generated
     */
    int GRAPH_EDGEIDS_TYPE = 15;

    /**
     * The meta object id for the '{@link org.graphdrawing.graphml.GraphNodeidsType <em>Graph Nodeids Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.graphdrawing.graphml.GraphNodeidsType
     * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getGraphNodeidsType()
     * @generated
     */
    int GRAPH_NODEIDS_TYPE = 16;

    /**
     * The meta object id for the '{@link org.graphdrawing.graphml.GraphOrderType <em>Graph Order Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.graphdrawing.graphml.GraphOrderType
     * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getGraphOrderType()
     * @generated
     */
    int GRAPH_ORDER_TYPE = 17;

    /**
     * The meta object id for the '{@link org.graphdrawing.graphml.KeyForType <em>Key For Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.graphdrawing.graphml.KeyForType
     * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getKeyForType()
     * @generated
     */
    int KEY_FOR_TYPE = 18;

    /**
     * The meta object id for the '{@link org.graphdrawing.graphml.KeyTypeType <em>Key Type Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.graphdrawing.graphml.KeyTypeType
     * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getKeyTypeType()
     * @generated
     */
    int KEY_TYPE_TYPE = 19;

    /**
     * The meta object id for the '<em>Endpoint Type Type Object</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.graphdrawing.graphml.EndpointTypeType
     * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getEndpointTypeTypeObject()
     * @generated
     */
    int ENDPOINT_TYPE_TYPE_OBJECT = 20;

    /**
     * The meta object id for the '<em>Graph Edgedefault Type Object</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.graphdrawing.graphml.GraphEdgedefaultType
     * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getGraphEdgedefaultTypeObject()
     * @generated
     */
    int GRAPH_EDGEDEFAULT_TYPE_OBJECT = 21;

    /**
     * The meta object id for the '<em>Graph Edgeids Type Object</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.graphdrawing.graphml.GraphEdgeidsType
     * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getGraphEdgeidsTypeObject()
     * @generated
     */
    int GRAPH_EDGEIDS_TYPE_OBJECT = 22;

    /**
     * The meta object id for the '<em>Graph Edges Type</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see java.math.BigInteger
     * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getGraphEdgesType()
     * @generated
     */
    int GRAPH_EDGES_TYPE = 23;

    /**
     * The meta object id for the '<em>Graph Maxindegree Type</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see java.math.BigInteger
     * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getGraphMaxindegreeType()
     * @generated
     */
    int GRAPH_MAXINDEGREE_TYPE = 24;

    /**
     * The meta object id for the '<em>Graph Maxoutdegree Type</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see java.math.BigInteger
     * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getGraphMaxoutdegreeType()
     * @generated
     */
    int GRAPH_MAXOUTDEGREE_TYPE = 25;

    /**
     * The meta object id for the '<em>Graph Nodeids Type Object</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.graphdrawing.graphml.GraphNodeidsType
     * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getGraphNodeidsTypeObject()
     * @generated
     */
    int GRAPH_NODEIDS_TYPE_OBJECT = 26;

    /**
     * The meta object id for the '<em>Graph Nodes Type</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see java.math.BigInteger
     * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getGraphNodesType()
     * @generated
     */
    int GRAPH_NODES_TYPE = 27;

    /**
     * The meta object id for the '<em>Graph Order Type Object</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.graphdrawing.graphml.GraphOrderType
     * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getGraphOrderTypeObject()
     * @generated
     */
    int GRAPH_ORDER_TYPE_OBJECT = 28;

    /**
     * The meta object id for the '<em>Key For Type Object</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.graphdrawing.graphml.KeyForType
     * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getKeyForTypeObject()
     * @generated
     */
    int KEY_FOR_TYPE_OBJECT = 29;

    /**
     * The meta object id for the '<em>Key Name Type</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see java.lang.String
     * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getKeyNameType()
     * @generated
     */
    int KEY_NAME_TYPE = 30;

    /**
     * The meta object id for the '<em>Key Type Type Object</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.graphdrawing.graphml.KeyTypeType
     * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getKeyTypeTypeObject()
     * @generated
     */
    int KEY_TYPE_TYPE_OBJECT = 31;

    /**
     * The meta object id for the '<em>Node Indegree Type</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see java.math.BigInteger
     * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getNodeIndegreeType()
     * @generated
     */
    int NODE_INDEGREE_TYPE = 32;

    /**
     * The meta object id for the '<em>Node Outdegree Type</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see java.math.BigInteger
     * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getNodeOutdegreeType()
     * @generated
     */
    int NODE_OUTDEGREE_TYPE = 33;


    /**
     * Returns the meta object for class '{@link org.graphdrawing.graphml.DataExtensionType <em>Data Extension Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Data Extension Type</em>'.
     * @see org.graphdrawing.graphml.DataExtensionType
     * @generated
     */
    EClass getDataExtensionType();

    /**
     * Returns the meta object for the attribute list '{@link org.graphdrawing.graphml.DataExtensionType#getMixed <em>Mixed</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Mixed</em>'.
     * @see org.graphdrawing.graphml.DataExtensionType#getMixed()
     * @see #getDataExtensionType()
     * @generated
     */
    EAttribute getDataExtensionType_Mixed();

    /**
     * Returns the meta object for class '{@link org.graphdrawing.graphml.DataType <em>Data Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Data Type</em>'.
     * @see org.graphdrawing.graphml.DataType
     * @generated
     */
    EClass getDataType();

    /**
     * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.DataType#getId <em>Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see org.graphdrawing.graphml.DataType#getId()
     * @see #getDataType()
     * @generated
     */
    EAttribute getDataType_Id();

    /**
     * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.DataType#getKey <em>Key</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Key</em>'.
     * @see org.graphdrawing.graphml.DataType#getKey()
     * @see #getDataType()
     * @generated
     */
    EAttribute getDataType_Key();

    /**
     * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.DataType#getTime <em>Time</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Time</em>'.
     * @see org.graphdrawing.graphml.DataType#getTime()
     * @see #getDataType()
     * @generated
     */
    EAttribute getDataType_Time();

    /**
     * Returns the meta object for class '{@link org.graphdrawing.graphml.DefaultType <em>Default Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Default Type</em>'.
     * @see org.graphdrawing.graphml.DefaultType
     * @generated
     */
    EClass getDefaultType();

    /**
     * Returns the meta object for class '{@link org.graphdrawing.graphml.DocumentRoot <em>Document Root</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Document Root</em>'.
     * @see org.graphdrawing.graphml.DocumentRoot
     * @generated
     */
    EClass getDocumentRoot();

    /**
     * Returns the meta object for the attribute list '{@link org.graphdrawing.graphml.DocumentRoot#getMixed <em>Mixed</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Mixed</em>'.
     * @see org.graphdrawing.graphml.DocumentRoot#getMixed()
     * @see #getDocumentRoot()
     * @generated
     */
    EAttribute getDocumentRoot_Mixed();

    /**
     * Returns the meta object for the map '{@link org.graphdrawing.graphml.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the map '<em>XMLNS Prefix Map</em>'.
     * @see org.graphdrawing.graphml.DocumentRoot#getXMLNSPrefixMap()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_XMLNSPrefixMap();

    /**
     * Returns the meta object for the map '{@link org.graphdrawing.graphml.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the map '<em>XSI Schema Location</em>'.
     * @see org.graphdrawing.graphml.DocumentRoot#getXSISchemaLocation()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_XSISchemaLocation();

    /**
     * Returns the meta object for the containment reference '{@link org.graphdrawing.graphml.DocumentRoot#getData <em>Data</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Data</em>'.
     * @see org.graphdrawing.graphml.DocumentRoot#getData()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Data();

    /**
     * Returns the meta object for the containment reference '{@link org.graphdrawing.graphml.DocumentRoot#getDefault <em>Default</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Default</em>'.
     * @see org.graphdrawing.graphml.DocumentRoot#getDefault()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Default();

    /**
     * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.DocumentRoot#getDesc <em>Desc</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Desc</em>'.
     * @see org.graphdrawing.graphml.DocumentRoot#getDesc()
     * @see #getDocumentRoot()
     * @generated
     */
    EAttribute getDocumentRoot_Desc();

    /**
     * Returns the meta object for the containment reference '{@link org.graphdrawing.graphml.DocumentRoot#getEdge <em>Edge</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Edge</em>'.
     * @see org.graphdrawing.graphml.DocumentRoot#getEdge()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Edge();

    /**
     * Returns the meta object for the containment reference '{@link org.graphdrawing.graphml.DocumentRoot#getEndpoint <em>Endpoint</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Endpoint</em>'.
     * @see org.graphdrawing.graphml.DocumentRoot#getEndpoint()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Endpoint();

    /**
     * Returns the meta object for the containment reference '{@link org.graphdrawing.graphml.DocumentRoot#getGraph <em>Graph</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Graph</em>'.
     * @see org.graphdrawing.graphml.DocumentRoot#getGraph()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Graph();

    /**
     * Returns the meta object for the containment reference '{@link org.graphdrawing.graphml.DocumentRoot#getGraphml <em>Graphml</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Graphml</em>'.
     * @see org.graphdrawing.graphml.DocumentRoot#getGraphml()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Graphml();

    /**
     * Returns the meta object for the containment reference '{@link org.graphdrawing.graphml.DocumentRoot#getHyperedge <em>Hyperedge</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Hyperedge</em>'.
     * @see org.graphdrawing.graphml.DocumentRoot#getHyperedge()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Hyperedge();

    /**
     * Returns the meta object for the containment reference '{@link org.graphdrawing.graphml.DocumentRoot#getKey <em>Key</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Key</em>'.
     * @see org.graphdrawing.graphml.DocumentRoot#getKey()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Key();

    /**
     * Returns the meta object for the containment reference '{@link org.graphdrawing.graphml.DocumentRoot#getLocator <em>Locator</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Locator</em>'.
     * @see org.graphdrawing.graphml.DocumentRoot#getLocator()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Locator();

    /**
     * Returns the meta object for the containment reference '{@link org.graphdrawing.graphml.DocumentRoot#getNode <em>Node</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Node</em>'.
     * @see org.graphdrawing.graphml.DocumentRoot#getNode()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Node();

    /**
     * Returns the meta object for the containment reference '{@link org.graphdrawing.graphml.DocumentRoot#getPort <em>Port</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Port</em>'.
     * @see org.graphdrawing.graphml.DocumentRoot#getPort()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Port();

    /**
     * Returns the meta object for class '{@link org.graphdrawing.graphml.EdgeType <em>Edge Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Edge Type</em>'.
     * @see org.graphdrawing.graphml.EdgeType
     * @generated
     */
    EClass getEdgeType();

    /**
     * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.EdgeType#getDesc <em>Desc</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Desc</em>'.
     * @see org.graphdrawing.graphml.EdgeType#getDesc()
     * @see #getEdgeType()
     * @generated
     */
    EAttribute getEdgeType_Desc();

    /**
     * Returns the meta object for the containment reference list '{@link org.graphdrawing.graphml.EdgeType#getData <em>Data</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Data</em>'.
     * @see org.graphdrawing.graphml.EdgeType#getData()
     * @see #getEdgeType()
     * @generated
     */
    EReference getEdgeType_Data();

    /**
     * Returns the meta object for the containment reference '{@link org.graphdrawing.graphml.EdgeType#getGraph <em>Graph</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Graph</em>'.
     * @see org.graphdrawing.graphml.EdgeType#getGraph()
     * @see #getEdgeType()
     * @generated
     */
    EReference getEdgeType_Graph();

    /**
     * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.EdgeType#isDirected <em>Directed</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Directed</em>'.
     * @see org.graphdrawing.graphml.EdgeType#isDirected()
     * @see #getEdgeType()
     * @generated
     */
    EAttribute getEdgeType_Directed();

    /**
     * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.EdgeType#getId <em>Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see org.graphdrawing.graphml.EdgeType#getId()
     * @see #getEdgeType()
     * @generated
     */
    EAttribute getEdgeType_Id();

    /**
     * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.EdgeType#getSource <em>Source</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Source</em>'.
     * @see org.graphdrawing.graphml.EdgeType#getSource()
     * @see #getEdgeType()
     * @generated
     */
    EAttribute getEdgeType_Source();

    /**
     * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.EdgeType#getSourceport <em>Sourceport</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Sourceport</em>'.
     * @see org.graphdrawing.graphml.EdgeType#getSourceport()
     * @see #getEdgeType()
     * @generated
     */
    EAttribute getEdgeType_Sourceport();

    /**
     * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.EdgeType#getTarget <em>Target</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Target</em>'.
     * @see org.graphdrawing.graphml.EdgeType#getTarget()
     * @see #getEdgeType()
     * @generated
     */
    EAttribute getEdgeType_Target();

    /**
     * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.EdgeType#getTargetport <em>Targetport</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Targetport</em>'.
     * @see org.graphdrawing.graphml.EdgeType#getTargetport()
     * @see #getEdgeType()
     * @generated
     */
    EAttribute getEdgeType_Targetport();

    /**
     * Returns the meta object for class '{@link org.graphdrawing.graphml.EndpointType <em>Endpoint Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Endpoint Type</em>'.
     * @see org.graphdrawing.graphml.EndpointType
     * @generated
     */
    EClass getEndpointType();

    /**
     * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.EndpointType#getDesc <em>Desc</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Desc</em>'.
     * @see org.graphdrawing.graphml.EndpointType#getDesc()
     * @see #getEndpointType()
     * @generated
     */
    EAttribute getEndpointType_Desc();

    /**
     * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.EndpointType#getId <em>Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see org.graphdrawing.graphml.EndpointType#getId()
     * @see #getEndpointType()
     * @generated
     */
    EAttribute getEndpointType_Id();

    /**
     * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.EndpointType#getNode <em>Node</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Node</em>'.
     * @see org.graphdrawing.graphml.EndpointType#getNode()
     * @see #getEndpointType()
     * @generated
     */
    EAttribute getEndpointType_Node();

    /**
     * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.EndpointType#getPort <em>Port</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Port</em>'.
     * @see org.graphdrawing.graphml.EndpointType#getPort()
     * @see #getEndpointType()
     * @generated
     */
    EAttribute getEndpointType_Port();

    /**
     * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.EndpointType#getType <em>Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Type</em>'.
     * @see org.graphdrawing.graphml.EndpointType#getType()
     * @see #getEndpointType()
     * @generated
     */
    EAttribute getEndpointType_Type();

    /**
     * Returns the meta object for class '{@link org.graphdrawing.graphml.GraphmlType <em>Graphml Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Graphml Type</em>'.
     * @see org.graphdrawing.graphml.GraphmlType
     * @generated
     */
    EClass getGraphmlType();

    /**
     * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.GraphmlType#getDesc <em>Desc</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Desc</em>'.
     * @see org.graphdrawing.graphml.GraphmlType#getDesc()
     * @see #getGraphmlType()
     * @generated
     */
    EAttribute getGraphmlType_Desc();

    /**
     * Returns the meta object for the containment reference list '{@link org.graphdrawing.graphml.GraphmlType#getKey <em>Key</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Key</em>'.
     * @see org.graphdrawing.graphml.GraphmlType#getKey()
     * @see #getGraphmlType()
     * @generated
     */
    EReference getGraphmlType_Key();

    /**
     * Returns the meta object for the attribute list '{@link org.graphdrawing.graphml.GraphmlType#getGroup <em>Group</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Group</em>'.
     * @see org.graphdrawing.graphml.GraphmlType#getGroup()
     * @see #getGraphmlType()
     * @generated
     */
    EAttribute getGraphmlType_Group();

    /**
     * Returns the meta object for the containment reference list '{@link org.graphdrawing.graphml.GraphmlType#getGraph <em>Graph</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Graph</em>'.
     * @see org.graphdrawing.graphml.GraphmlType#getGraph()
     * @see #getGraphmlType()
     * @generated
     */
    EReference getGraphmlType_Graph();

    /**
     * Returns the meta object for the containment reference list '{@link org.graphdrawing.graphml.GraphmlType#getData <em>Data</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Data</em>'.
     * @see org.graphdrawing.graphml.GraphmlType#getData()
     * @see #getGraphmlType()
     * @generated
     */
    EReference getGraphmlType_Data();

    /**
     * Returns the meta object for class '{@link org.graphdrawing.graphml.GraphType <em>Graph Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Graph Type</em>'.
     * @see org.graphdrawing.graphml.GraphType
     * @generated
     */
    EClass getGraphType();

    /**
     * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.GraphType#getDesc <em>Desc</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Desc</em>'.
     * @see org.graphdrawing.graphml.GraphType#getDesc()
     * @see #getGraphType()
     * @generated
     */
    EAttribute getGraphType_Desc();

    /**
     * Returns the meta object for the attribute list '{@link org.graphdrawing.graphml.GraphType#getGroup <em>Group</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Group</em>'.
     * @see org.graphdrawing.graphml.GraphType#getGroup()
     * @see #getGraphType()
     * @generated
     */
    EAttribute getGraphType_Group();

    /**
     * Returns the meta object for the containment reference list '{@link org.graphdrawing.graphml.GraphType#getData <em>Data</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Data</em>'.
     * @see org.graphdrawing.graphml.GraphType#getData()
     * @see #getGraphType()
     * @generated
     */
    EReference getGraphType_Data();

    /**
     * Returns the meta object for the containment reference list '{@link org.graphdrawing.graphml.GraphType#getNode <em>Node</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Node</em>'.
     * @see org.graphdrawing.graphml.GraphType#getNode()
     * @see #getGraphType()
     * @generated
     */
    EReference getGraphType_Node();

    /**
     * Returns the meta object for the containment reference list '{@link org.graphdrawing.graphml.GraphType#getEdge <em>Edge</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Edge</em>'.
     * @see org.graphdrawing.graphml.GraphType#getEdge()
     * @see #getGraphType()
     * @generated
     */
    EReference getGraphType_Edge();

    /**
     * Returns the meta object for the containment reference list '{@link org.graphdrawing.graphml.GraphType#getHyperedge <em>Hyperedge</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Hyperedge</em>'.
     * @see org.graphdrawing.graphml.GraphType#getHyperedge()
     * @see #getGraphType()
     * @generated
     */
    EReference getGraphType_Hyperedge();

    /**
     * Returns the meta object for the containment reference '{@link org.graphdrawing.graphml.GraphType#getLocator <em>Locator</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Locator</em>'.
     * @see org.graphdrawing.graphml.GraphType#getLocator()
     * @see #getGraphType()
     * @generated
     */
    EReference getGraphType_Locator();

    /**
     * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.GraphType#getEdgedefault <em>Edgedefault</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Edgedefault</em>'.
     * @see org.graphdrawing.graphml.GraphType#getEdgedefault()
     * @see #getGraphType()
     * @generated
     */
    EAttribute getGraphType_Edgedefault();

    /**
     * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.GraphType#getId <em>Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see org.graphdrawing.graphml.GraphType#getId()
     * @see #getGraphType()
     * @generated
     */
    EAttribute getGraphType_Id();

    /**
     * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.GraphType#getParseEdgeids <em>Parse Edgeids</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Parse Edgeids</em>'.
     * @see org.graphdrawing.graphml.GraphType#getParseEdgeids()
     * @see #getGraphType()
     * @generated
     */
    EAttribute getGraphType_ParseEdgeids();

    /**
     * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.GraphType#getParseEdges <em>Parse Edges</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Parse Edges</em>'.
     * @see org.graphdrawing.graphml.GraphType#getParseEdges()
     * @see #getGraphType()
     * @generated
     */
    EAttribute getGraphType_ParseEdges();

    /**
     * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.GraphType#getParseMaxindegree <em>Parse Maxindegree</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Parse Maxindegree</em>'.
     * @see org.graphdrawing.graphml.GraphType#getParseMaxindegree()
     * @see #getGraphType()
     * @generated
     */
    EAttribute getGraphType_ParseMaxindegree();

    /**
     * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.GraphType#getParseMaxoutdegree <em>Parse Maxoutdegree</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Parse Maxoutdegree</em>'.
     * @see org.graphdrawing.graphml.GraphType#getParseMaxoutdegree()
     * @see #getGraphType()
     * @generated
     */
    EAttribute getGraphType_ParseMaxoutdegree();

    /**
     * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.GraphType#getParseNodeids <em>Parse Nodeids</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Parse Nodeids</em>'.
     * @see org.graphdrawing.graphml.GraphType#getParseNodeids()
     * @see #getGraphType()
     * @generated
     */
    EAttribute getGraphType_ParseNodeids();

    /**
     * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.GraphType#getParseNodes <em>Parse Nodes</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Parse Nodes</em>'.
     * @see org.graphdrawing.graphml.GraphType#getParseNodes()
     * @see #getGraphType()
     * @generated
     */
    EAttribute getGraphType_ParseNodes();

    /**
     * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.GraphType#getParseOrder <em>Parse Order</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Parse Order</em>'.
     * @see org.graphdrawing.graphml.GraphType#getParseOrder()
     * @see #getGraphType()
     * @generated
     */
    EAttribute getGraphType_ParseOrder();

    /**
     * Returns the meta object for class '{@link org.graphdrawing.graphml.HyperedgeType <em>Hyperedge Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Hyperedge Type</em>'.
     * @see org.graphdrawing.graphml.HyperedgeType
     * @generated
     */
    EClass getHyperedgeType();

    /**
     * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.HyperedgeType#getDesc <em>Desc</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Desc</em>'.
     * @see org.graphdrawing.graphml.HyperedgeType#getDesc()
     * @see #getHyperedgeType()
     * @generated
     */
    EAttribute getHyperedgeType_Desc();

    /**
     * Returns the meta object for the attribute list '{@link org.graphdrawing.graphml.HyperedgeType#getGroup <em>Group</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Group</em>'.
     * @see org.graphdrawing.graphml.HyperedgeType#getGroup()
     * @see #getHyperedgeType()
     * @generated
     */
    EAttribute getHyperedgeType_Group();

    /**
     * Returns the meta object for the containment reference list '{@link org.graphdrawing.graphml.HyperedgeType#getData <em>Data</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Data</em>'.
     * @see org.graphdrawing.graphml.HyperedgeType#getData()
     * @see #getHyperedgeType()
     * @generated
     */
    EReference getHyperedgeType_Data();

    /**
     * Returns the meta object for the containment reference list '{@link org.graphdrawing.graphml.HyperedgeType#getEndpoint <em>Endpoint</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Endpoint</em>'.
     * @see org.graphdrawing.graphml.HyperedgeType#getEndpoint()
     * @see #getHyperedgeType()
     * @generated
     */
    EReference getHyperedgeType_Endpoint();

    /**
     * Returns the meta object for the containment reference '{@link org.graphdrawing.graphml.HyperedgeType#getGraph <em>Graph</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Graph</em>'.
     * @see org.graphdrawing.graphml.HyperedgeType#getGraph()
     * @see #getHyperedgeType()
     * @generated
     */
    EReference getHyperedgeType_Graph();

    /**
     * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.HyperedgeType#getId <em>Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see org.graphdrawing.graphml.HyperedgeType#getId()
     * @see #getHyperedgeType()
     * @generated
     */
    EAttribute getHyperedgeType_Id();

    /**
     * Returns the meta object for class '{@link org.graphdrawing.graphml.KeyType <em>Key Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Key Type</em>'.
     * @see org.graphdrawing.graphml.KeyType
     * @generated
     */
    EClass getKeyType();

    /**
     * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.KeyType#getDesc <em>Desc</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Desc</em>'.
     * @see org.graphdrawing.graphml.KeyType#getDesc()
     * @see #getKeyType()
     * @generated
     */
    EAttribute getKeyType_Desc();

    /**
     * Returns the meta object for the containment reference '{@link org.graphdrawing.graphml.KeyType#getDefault <em>Default</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Default</em>'.
     * @see org.graphdrawing.graphml.KeyType#getDefault()
     * @see #getKeyType()
     * @generated
     */
    EReference getKeyType_Default();

    /**
     * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.KeyType#getAttrName <em>Attr Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Attr Name</em>'.
     * @see org.graphdrawing.graphml.KeyType#getAttrName()
     * @see #getKeyType()
     * @generated
     */
    EAttribute getKeyType_AttrName();

    /**
     * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.KeyType#getAttrType <em>Attr Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Attr Type</em>'.
     * @see org.graphdrawing.graphml.KeyType#getAttrType()
     * @see #getKeyType()
     * @generated
     */
    EAttribute getKeyType_AttrType();

    /**
     * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.KeyType#isDynamic <em>Dynamic</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Dynamic</em>'.
     * @see org.graphdrawing.graphml.KeyType#isDynamic()
     * @see #getKeyType()
     * @generated
     */
    EAttribute getKeyType_Dynamic();

    /**
     * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.KeyType#getFor <em>For</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>For</em>'.
     * @see org.graphdrawing.graphml.KeyType#getFor()
     * @see #getKeyType()
     * @generated
     */
    EAttribute getKeyType_For();

    /**
     * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.KeyType#getId <em>Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see org.graphdrawing.graphml.KeyType#getId()
     * @see #getKeyType()
     * @generated
     */
    EAttribute getKeyType_Id();

    /**
     * Returns the meta object for class '{@link org.graphdrawing.graphml.LocatorType <em>Locator Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Locator Type</em>'.
     * @see org.graphdrawing.graphml.LocatorType
     * @generated
     */
    EClass getLocatorType();

    /**
     * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.LocatorType#getHref <em>Href</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Href</em>'.
     * @see org.graphdrawing.graphml.LocatorType#getHref()
     * @see #getLocatorType()
     * @generated
     */
    EAttribute getLocatorType_Href();

    /**
     * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.LocatorType#getType <em>Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Type</em>'.
     * @see org.graphdrawing.graphml.LocatorType#getType()
     * @see #getLocatorType()
     * @generated
     */
    EAttribute getLocatorType_Type();

    /**
     * Returns the meta object for class '{@link org.graphdrawing.graphml.NodeType <em>Node Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Node Type</em>'.
     * @see org.graphdrawing.graphml.NodeType
     * @generated
     */
    EClass getNodeType();

    /**
     * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.NodeType#getDesc <em>Desc</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Desc</em>'.
     * @see org.graphdrawing.graphml.NodeType#getDesc()
     * @see #getNodeType()
     * @generated
     */
    EAttribute getNodeType_Desc();

    /**
     * Returns the meta object for the attribute list '{@link org.graphdrawing.graphml.NodeType#getGroup <em>Group</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Group</em>'.
     * @see org.graphdrawing.graphml.NodeType#getGroup()
     * @see #getNodeType()
     * @generated
     */
    EAttribute getNodeType_Group();

    /**
     * Returns the meta object for the containment reference list '{@link org.graphdrawing.graphml.NodeType#getData <em>Data</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Data</em>'.
     * @see org.graphdrawing.graphml.NodeType#getData()
     * @see #getNodeType()
     * @generated
     */
    EReference getNodeType_Data();

    /**
     * Returns the meta object for the containment reference list '{@link org.graphdrawing.graphml.NodeType#getPort <em>Port</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Port</em>'.
     * @see org.graphdrawing.graphml.NodeType#getPort()
     * @see #getNodeType()
     * @generated
     */
    EReference getNodeType_Port();

    /**
     * Returns the meta object for the containment reference '{@link org.graphdrawing.graphml.NodeType#getGraph <em>Graph</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Graph</em>'.
     * @see org.graphdrawing.graphml.NodeType#getGraph()
     * @see #getNodeType()
     * @generated
     */
    EReference getNodeType_Graph();

    /**
     * Returns the meta object for the containment reference '{@link org.graphdrawing.graphml.NodeType#getLocator <em>Locator</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Locator</em>'.
     * @see org.graphdrawing.graphml.NodeType#getLocator()
     * @see #getNodeType()
     * @generated
     */
    EReference getNodeType_Locator();

    /**
     * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.NodeType#getId <em>Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see org.graphdrawing.graphml.NodeType#getId()
     * @see #getNodeType()
     * @generated
     */
    EAttribute getNodeType_Id();

    /**
     * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.NodeType#getParseIndegree <em>Parse Indegree</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Parse Indegree</em>'.
     * @see org.graphdrawing.graphml.NodeType#getParseIndegree()
     * @see #getNodeType()
     * @generated
     */
    EAttribute getNodeType_ParseIndegree();

    /**
     * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.NodeType#getParseOutdegree <em>Parse Outdegree</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Parse Outdegree</em>'.
     * @see org.graphdrawing.graphml.NodeType#getParseOutdegree()
     * @see #getNodeType()
     * @generated
     */
    EAttribute getNodeType_ParseOutdegree();

    /**
     * Returns the meta object for class '{@link org.graphdrawing.graphml.PortType <em>Port Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Port Type</em>'.
     * @see org.graphdrawing.graphml.PortType
     * @generated
     */
    EClass getPortType();

    /**
     * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.PortType#getDesc <em>Desc</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Desc</em>'.
     * @see org.graphdrawing.graphml.PortType#getDesc()
     * @see #getPortType()
     * @generated
     */
    EAttribute getPortType_Desc();

    /**
     * Returns the meta object for the attribute list '{@link org.graphdrawing.graphml.PortType#getGroup <em>Group</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Group</em>'.
     * @see org.graphdrawing.graphml.PortType#getGroup()
     * @see #getPortType()
     * @generated
     */
    EAttribute getPortType_Group();

    /**
     * Returns the meta object for the containment reference list '{@link org.graphdrawing.graphml.PortType#getData <em>Data</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Data</em>'.
     * @see org.graphdrawing.graphml.PortType#getData()
     * @see #getPortType()
     * @generated
     */
    EReference getPortType_Data();

    /**
     * Returns the meta object for the containment reference list '{@link org.graphdrawing.graphml.PortType#getPort <em>Port</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Port</em>'.
     * @see org.graphdrawing.graphml.PortType#getPort()
     * @see #getPortType()
     * @generated
     */
    EReference getPortType_Port();

    /**
     * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.PortType#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.graphdrawing.graphml.PortType#getName()
     * @see #getPortType()
     * @generated
     */
    EAttribute getPortType_Name();

    /**
     * Returns the meta object for enum '{@link org.graphdrawing.graphml.EndpointTypeType <em>Endpoint Type Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Endpoint Type Type</em>'.
     * @see org.graphdrawing.graphml.EndpointTypeType
     * @generated
     */
    EEnum getEndpointTypeType();

    /**
     * Returns the meta object for enum '{@link org.graphdrawing.graphml.GraphEdgedefaultType <em>Graph Edgedefault Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Graph Edgedefault Type</em>'.
     * @see org.graphdrawing.graphml.GraphEdgedefaultType
     * @generated
     */
    EEnum getGraphEdgedefaultType();

    /**
     * Returns the meta object for enum '{@link org.graphdrawing.graphml.GraphEdgeidsType <em>Graph Edgeids Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Graph Edgeids Type</em>'.
     * @see org.graphdrawing.graphml.GraphEdgeidsType
     * @generated
     */
    EEnum getGraphEdgeidsType();

    /**
     * Returns the meta object for enum '{@link org.graphdrawing.graphml.GraphNodeidsType <em>Graph Nodeids Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Graph Nodeids Type</em>'.
     * @see org.graphdrawing.graphml.GraphNodeidsType
     * @generated
     */
    EEnum getGraphNodeidsType();

    /**
     * Returns the meta object for enum '{@link org.graphdrawing.graphml.GraphOrderType <em>Graph Order Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Graph Order Type</em>'.
     * @see org.graphdrawing.graphml.GraphOrderType
     * @generated
     */
    EEnum getGraphOrderType();

    /**
     * Returns the meta object for enum '{@link org.graphdrawing.graphml.KeyForType <em>Key For Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Key For Type</em>'.
     * @see org.graphdrawing.graphml.KeyForType
     * @generated
     */
    EEnum getKeyForType();

    /**
     * Returns the meta object for enum '{@link org.graphdrawing.graphml.KeyTypeType <em>Key Type Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Key Type Type</em>'.
     * @see org.graphdrawing.graphml.KeyTypeType
     * @generated
     */
    EEnum getKeyTypeType();

    /**
     * Returns the meta object for data type '{@link org.graphdrawing.graphml.EndpointTypeType <em>Endpoint Type Type Object</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for data type '<em>Endpoint Type Type Object</em>'.
     * @see org.graphdrawing.graphml.EndpointTypeType
     * @model instanceClass="org.graphdrawing.graphml.EndpointTypeType"
     *        extendedMetaData="name='endpoint.type.type:Object' baseType='endpoint.type.type'"
     * @generated
     */
    EDataType getEndpointTypeTypeObject();

    /**
     * Returns the meta object for data type '{@link org.graphdrawing.graphml.GraphEdgedefaultType <em>Graph Edgedefault Type Object</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for data type '<em>Graph Edgedefault Type Object</em>'.
     * @see org.graphdrawing.graphml.GraphEdgedefaultType
     * @model instanceClass="org.graphdrawing.graphml.GraphEdgedefaultType"
     *        extendedMetaData="name='graph.edgedefault.type:Object' baseType='graph.edgedefault.type'"
     * @generated
     */
    EDataType getGraphEdgedefaultTypeObject();

    /**
     * Returns the meta object for data type '{@link org.graphdrawing.graphml.GraphEdgeidsType <em>Graph Edgeids Type Object</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for data type '<em>Graph Edgeids Type Object</em>'.
     * @see org.graphdrawing.graphml.GraphEdgeidsType
     * @model instanceClass="org.graphdrawing.graphml.GraphEdgeidsType"
     *        extendedMetaData="name='graph.edgeids.type:Object' baseType='graph.edgeids.type'"
     * @generated
     */
    EDataType getGraphEdgeidsTypeObject();

    /**
     * Returns the meta object for data type '{@link java.math.BigInteger <em>Graph Edges Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for data type '<em>Graph Edges Type</em>'.
     * @see java.math.BigInteger
     * @model instanceClass="java.math.BigInteger"
     *        extendedMetaData="name='graph.edges.type' baseType='http://www.eclipse.org/emf/2003/XMLType#nonNegativeInteger'"
     * @generated
     */
    EDataType getGraphEdgesType();

    /**
     * Returns the meta object for data type '{@link java.math.BigInteger <em>Graph Maxindegree Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for data type '<em>Graph Maxindegree Type</em>'.
     * @see java.math.BigInteger
     * @model instanceClass="java.math.BigInteger"
     *        extendedMetaData="name='graph.maxindegree.type' baseType='http://www.eclipse.org/emf/2003/XMLType#nonNegativeInteger'"
     * @generated
     */
    EDataType getGraphMaxindegreeType();

    /**
     * Returns the meta object for data type '{@link java.math.BigInteger <em>Graph Maxoutdegree Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for data type '<em>Graph Maxoutdegree Type</em>'.
     * @see java.math.BigInteger
     * @model instanceClass="java.math.BigInteger"
     *        extendedMetaData="name='graph.maxoutdegree.type' baseType='http://www.eclipse.org/emf/2003/XMLType#nonNegativeInteger'"
     * @generated
     */
    EDataType getGraphMaxoutdegreeType();

    /**
     * Returns the meta object for data type '{@link org.graphdrawing.graphml.GraphNodeidsType <em>Graph Nodeids Type Object</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for data type '<em>Graph Nodeids Type Object</em>'.
     * @see org.graphdrawing.graphml.GraphNodeidsType
     * @model instanceClass="org.graphdrawing.graphml.GraphNodeidsType"
     *        extendedMetaData="name='graph.nodeids.type:Object' baseType='graph.nodeids.type'"
     * @generated
     */
    EDataType getGraphNodeidsTypeObject();

    /**
     * Returns the meta object for data type '{@link java.math.BigInteger <em>Graph Nodes Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for data type '<em>Graph Nodes Type</em>'.
     * @see java.math.BigInteger
     * @model instanceClass="java.math.BigInteger"
     *        extendedMetaData="name='graph.nodes.type' baseType='http://www.eclipse.org/emf/2003/XMLType#nonNegativeInteger'"
     * @generated
     */
    EDataType getGraphNodesType();

    /**
     * Returns the meta object for data type '{@link org.graphdrawing.graphml.GraphOrderType <em>Graph Order Type Object</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for data type '<em>Graph Order Type Object</em>'.
     * @see org.graphdrawing.graphml.GraphOrderType
     * @model instanceClass="org.graphdrawing.graphml.GraphOrderType"
     *        extendedMetaData="name='graph.order.type:Object' baseType='graph.order.type'"
     * @generated
     */
    EDataType getGraphOrderTypeObject();

    /**
     * Returns the meta object for data type '{@link org.graphdrawing.graphml.KeyForType <em>Key For Type Object</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for data type '<em>Key For Type Object</em>'.
     * @see org.graphdrawing.graphml.KeyForType
     * @model instanceClass="org.graphdrawing.graphml.KeyForType"
     *        extendedMetaData="name='key.for.type:Object' baseType='key.for.type'"
     * @generated
     */
    EDataType getKeyForTypeObject();

    /**
     * Returns the meta object for data type '{@link java.lang.String <em>Key Name Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for data type '<em>Key Name Type</em>'.
     * @see java.lang.String
     * @model instanceClass="java.lang.String"
     *        extendedMetaData="name='key.name.type' baseType='http://www.eclipse.org/emf/2003/XMLType#NMTOKEN'"
     * @generated
     */
    EDataType getKeyNameType();

    /**
     * Returns the meta object for data type '{@link org.graphdrawing.graphml.KeyTypeType <em>Key Type Type Object</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for data type '<em>Key Type Type Object</em>'.
     * @see org.graphdrawing.graphml.KeyTypeType
     * @model instanceClass="org.graphdrawing.graphml.KeyTypeType"
     *        extendedMetaData="name='key.type.type:Object' baseType='key.type.type'"
     * @generated
     */
    EDataType getKeyTypeTypeObject();

    /**
     * Returns the meta object for data type '{@link java.math.BigInteger <em>Node Indegree Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for data type '<em>Node Indegree Type</em>'.
     * @see java.math.BigInteger
     * @model instanceClass="java.math.BigInteger"
     *        extendedMetaData="name='node.indegree.type' baseType='http://www.eclipse.org/emf/2003/XMLType#nonNegativeInteger'"
     * @generated
     */
    EDataType getNodeIndegreeType();

    /**
     * Returns the meta object for data type '{@link java.math.BigInteger <em>Node Outdegree Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for data type '<em>Node Outdegree Type</em>'.
     * @see java.math.BigInteger
     * @model instanceClass="java.math.BigInteger"
     *        extendedMetaData="name='node.outdegree.type' baseType='http://www.eclipse.org/emf/2003/XMLType#nonNegativeInteger'"
     * @generated
     */
    EDataType getNodeOutdegreeType();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    GraphMLFactory getGraphMLFactory();

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
         * The meta object literal for the '{@link org.graphdrawing.graphml.impl.DataExtensionTypeImpl <em>Data Extension Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.graphdrawing.graphml.impl.DataExtensionTypeImpl
         * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getDataExtensionType()
         * @generated
         */
        EClass DATA_EXTENSION_TYPE = eINSTANCE.getDataExtensionType();

        /**
         * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DATA_EXTENSION_TYPE__MIXED = eINSTANCE.getDataExtensionType_Mixed();

        /**
         * The meta object literal for the '{@link org.graphdrawing.graphml.impl.DataTypeImpl <em>Data Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.graphdrawing.graphml.impl.DataTypeImpl
         * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getDataType()
         * @generated
         */
        EClass DATA_TYPE = eINSTANCE.getDataType();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DATA_TYPE__ID = eINSTANCE.getDataType_Id();

        /**
         * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DATA_TYPE__KEY = eINSTANCE.getDataType_Key();

        /**
         * The meta object literal for the '<em><b>Time</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DATA_TYPE__TIME = eINSTANCE.getDataType_Time();

        /**
         * The meta object literal for the '{@link org.graphdrawing.graphml.impl.DefaultTypeImpl <em>Default Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.graphdrawing.graphml.impl.DefaultTypeImpl
         * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getDefaultType()
         * @generated
         */
        EClass DEFAULT_TYPE = eINSTANCE.getDefaultType();

        /**
         * The meta object literal for the '{@link org.graphdrawing.graphml.impl.DocumentRootImpl <em>Document Root</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.graphdrawing.graphml.impl.DocumentRootImpl
         * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getDocumentRoot()
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
         * The meta object literal for the '<em><b>Data</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__DATA = eINSTANCE.getDocumentRoot_Data();

        /**
         * The meta object literal for the '<em><b>Default</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__DEFAULT = eINSTANCE.getDocumentRoot_Default();

        /**
         * The meta object literal for the '<em><b>Desc</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DOCUMENT_ROOT__DESC = eINSTANCE.getDocumentRoot_Desc();

        /**
         * The meta object literal for the '<em><b>Edge</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__EDGE = eINSTANCE.getDocumentRoot_Edge();

        /**
         * The meta object literal for the '<em><b>Endpoint</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__ENDPOINT = eINSTANCE.getDocumentRoot_Endpoint();

        /**
         * The meta object literal for the '<em><b>Graph</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__GRAPH = eINSTANCE.getDocumentRoot_Graph();

        /**
         * The meta object literal for the '<em><b>Graphml</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__GRAPHML = eINSTANCE.getDocumentRoot_Graphml();

        /**
         * The meta object literal for the '<em><b>Hyperedge</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__HYPEREDGE = eINSTANCE.getDocumentRoot_Hyperedge();

        /**
         * The meta object literal for the '<em><b>Key</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__KEY = eINSTANCE.getDocumentRoot_Key();

        /**
         * The meta object literal for the '<em><b>Locator</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__LOCATOR = eINSTANCE.getDocumentRoot_Locator();

        /**
         * The meta object literal for the '<em><b>Node</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__NODE = eINSTANCE.getDocumentRoot_Node();

        /**
         * The meta object literal for the '<em><b>Port</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__PORT = eINSTANCE.getDocumentRoot_Port();

        /**
         * The meta object literal for the '{@link org.graphdrawing.graphml.impl.EdgeTypeImpl <em>Edge Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.graphdrawing.graphml.impl.EdgeTypeImpl
         * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getEdgeType()
         * @generated
         */
        EClass EDGE_TYPE = eINSTANCE.getEdgeType();

        /**
         * The meta object literal for the '<em><b>Desc</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EDGE_TYPE__DESC = eINSTANCE.getEdgeType_Desc();

        /**
         * The meta object literal for the '<em><b>Data</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference EDGE_TYPE__DATA = eINSTANCE.getEdgeType_Data();

        /**
         * The meta object literal for the '<em><b>Graph</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference EDGE_TYPE__GRAPH = eINSTANCE.getEdgeType_Graph();

        /**
         * The meta object literal for the '<em><b>Directed</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EDGE_TYPE__DIRECTED = eINSTANCE.getEdgeType_Directed();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EDGE_TYPE__ID = eINSTANCE.getEdgeType_Id();

        /**
         * The meta object literal for the '<em><b>Source</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EDGE_TYPE__SOURCE = eINSTANCE.getEdgeType_Source();

        /**
         * The meta object literal for the '<em><b>Sourceport</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EDGE_TYPE__SOURCEPORT = eINSTANCE.getEdgeType_Sourceport();

        /**
         * The meta object literal for the '<em><b>Target</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EDGE_TYPE__TARGET = eINSTANCE.getEdgeType_Target();

        /**
         * The meta object literal for the '<em><b>Targetport</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EDGE_TYPE__TARGETPORT = eINSTANCE.getEdgeType_Targetport();

        /**
         * The meta object literal for the '{@link org.graphdrawing.graphml.impl.EndpointTypeImpl <em>Endpoint Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.graphdrawing.graphml.impl.EndpointTypeImpl
         * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getEndpointType()
         * @generated
         */
        EClass ENDPOINT_TYPE = eINSTANCE.getEndpointType();

        /**
         * The meta object literal for the '<em><b>Desc</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute ENDPOINT_TYPE__DESC = eINSTANCE.getEndpointType_Desc();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute ENDPOINT_TYPE__ID = eINSTANCE.getEndpointType_Id();

        /**
         * The meta object literal for the '<em><b>Node</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute ENDPOINT_TYPE__NODE = eINSTANCE.getEndpointType_Node();

        /**
         * The meta object literal for the '<em><b>Port</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute ENDPOINT_TYPE__PORT = eINSTANCE.getEndpointType_Port();

        /**
         * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute ENDPOINT_TYPE__TYPE = eINSTANCE.getEndpointType_Type();

        /**
         * The meta object literal for the '{@link org.graphdrawing.graphml.impl.GraphmlTypeImpl <em>Graphml Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.graphdrawing.graphml.impl.GraphmlTypeImpl
         * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getGraphmlType()
         * @generated
         */
        EClass GRAPHML_TYPE = eINSTANCE.getGraphmlType();

        /**
         * The meta object literal for the '<em><b>Desc</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute GRAPHML_TYPE__DESC = eINSTANCE.getGraphmlType_Desc();

        /**
         * The meta object literal for the '<em><b>Key</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference GRAPHML_TYPE__KEY = eINSTANCE.getGraphmlType_Key();

        /**
         * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute GRAPHML_TYPE__GROUP = eINSTANCE.getGraphmlType_Group();

        /**
         * The meta object literal for the '<em><b>Graph</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference GRAPHML_TYPE__GRAPH = eINSTANCE.getGraphmlType_Graph();

        /**
         * The meta object literal for the '<em><b>Data</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference GRAPHML_TYPE__DATA = eINSTANCE.getGraphmlType_Data();

        /**
         * The meta object literal for the '{@link org.graphdrawing.graphml.impl.GraphTypeImpl <em>Graph Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.graphdrawing.graphml.impl.GraphTypeImpl
         * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getGraphType()
         * @generated
         */
        EClass GRAPH_TYPE = eINSTANCE.getGraphType();

        /**
         * The meta object literal for the '<em><b>Desc</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute GRAPH_TYPE__DESC = eINSTANCE.getGraphType_Desc();

        /**
         * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute GRAPH_TYPE__GROUP = eINSTANCE.getGraphType_Group();

        /**
         * The meta object literal for the '<em><b>Data</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference GRAPH_TYPE__DATA = eINSTANCE.getGraphType_Data();

        /**
         * The meta object literal for the '<em><b>Node</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference GRAPH_TYPE__NODE = eINSTANCE.getGraphType_Node();

        /**
         * The meta object literal for the '<em><b>Edge</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference GRAPH_TYPE__EDGE = eINSTANCE.getGraphType_Edge();

        /**
         * The meta object literal for the '<em><b>Hyperedge</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference GRAPH_TYPE__HYPEREDGE = eINSTANCE.getGraphType_Hyperedge();

        /**
         * The meta object literal for the '<em><b>Locator</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference GRAPH_TYPE__LOCATOR = eINSTANCE.getGraphType_Locator();

        /**
         * The meta object literal for the '<em><b>Edgedefault</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute GRAPH_TYPE__EDGEDEFAULT = eINSTANCE.getGraphType_Edgedefault();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute GRAPH_TYPE__ID = eINSTANCE.getGraphType_Id();

        /**
         * The meta object literal for the '<em><b>Parse Edgeids</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute GRAPH_TYPE__PARSE_EDGEIDS = eINSTANCE.getGraphType_ParseEdgeids();

        /**
         * The meta object literal for the '<em><b>Parse Edges</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute GRAPH_TYPE__PARSE_EDGES = eINSTANCE.getGraphType_ParseEdges();

        /**
         * The meta object literal for the '<em><b>Parse Maxindegree</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute GRAPH_TYPE__PARSE_MAXINDEGREE = eINSTANCE.getGraphType_ParseMaxindegree();

        /**
         * The meta object literal for the '<em><b>Parse Maxoutdegree</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute GRAPH_TYPE__PARSE_MAXOUTDEGREE = eINSTANCE.getGraphType_ParseMaxoutdegree();

        /**
         * The meta object literal for the '<em><b>Parse Nodeids</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute GRAPH_TYPE__PARSE_NODEIDS = eINSTANCE.getGraphType_ParseNodeids();

        /**
         * The meta object literal for the '<em><b>Parse Nodes</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute GRAPH_TYPE__PARSE_NODES = eINSTANCE.getGraphType_ParseNodes();

        /**
         * The meta object literal for the '<em><b>Parse Order</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute GRAPH_TYPE__PARSE_ORDER = eINSTANCE.getGraphType_ParseOrder();

        /**
         * The meta object literal for the '{@link org.graphdrawing.graphml.impl.HyperedgeTypeImpl <em>Hyperedge Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.graphdrawing.graphml.impl.HyperedgeTypeImpl
         * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getHyperedgeType()
         * @generated
         */
        EClass HYPEREDGE_TYPE = eINSTANCE.getHyperedgeType();

        /**
         * The meta object literal for the '<em><b>Desc</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute HYPEREDGE_TYPE__DESC = eINSTANCE.getHyperedgeType_Desc();

        /**
         * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute HYPEREDGE_TYPE__GROUP = eINSTANCE.getHyperedgeType_Group();

        /**
         * The meta object literal for the '<em><b>Data</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference HYPEREDGE_TYPE__DATA = eINSTANCE.getHyperedgeType_Data();

        /**
         * The meta object literal for the '<em><b>Endpoint</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference HYPEREDGE_TYPE__ENDPOINT = eINSTANCE.getHyperedgeType_Endpoint();

        /**
         * The meta object literal for the '<em><b>Graph</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference HYPEREDGE_TYPE__GRAPH = eINSTANCE.getHyperedgeType_Graph();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute HYPEREDGE_TYPE__ID = eINSTANCE.getHyperedgeType_Id();

        /**
         * The meta object literal for the '{@link org.graphdrawing.graphml.impl.KeyTypeImpl <em>Key Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.graphdrawing.graphml.impl.KeyTypeImpl
         * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getKeyType()
         * @generated
         */
        EClass KEY_TYPE = eINSTANCE.getKeyType();

        /**
         * The meta object literal for the '<em><b>Desc</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KEY_TYPE__DESC = eINSTANCE.getKeyType_Desc();

        /**
         * The meta object literal for the '<em><b>Default</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference KEY_TYPE__DEFAULT = eINSTANCE.getKeyType_Default();

        /**
         * The meta object literal for the '<em><b>Attr Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KEY_TYPE__ATTR_NAME = eINSTANCE.getKeyType_AttrName();

        /**
         * The meta object literal for the '<em><b>Attr Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KEY_TYPE__ATTR_TYPE = eINSTANCE.getKeyType_AttrType();

        /**
         * The meta object literal for the '<em><b>Dynamic</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KEY_TYPE__DYNAMIC = eINSTANCE.getKeyType_Dynamic();

        /**
         * The meta object literal for the '<em><b>For</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KEY_TYPE__FOR = eINSTANCE.getKeyType_For();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KEY_TYPE__ID = eINSTANCE.getKeyType_Id();

        /**
         * The meta object literal for the '{@link org.graphdrawing.graphml.impl.LocatorTypeImpl <em>Locator Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.graphdrawing.graphml.impl.LocatorTypeImpl
         * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getLocatorType()
         * @generated
         */
        EClass LOCATOR_TYPE = eINSTANCE.getLocatorType();

        /**
         * The meta object literal for the '<em><b>Href</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute LOCATOR_TYPE__HREF = eINSTANCE.getLocatorType_Href();

        /**
         * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute LOCATOR_TYPE__TYPE = eINSTANCE.getLocatorType_Type();

        /**
         * The meta object literal for the '{@link org.graphdrawing.graphml.impl.NodeTypeImpl <em>Node Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.graphdrawing.graphml.impl.NodeTypeImpl
         * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getNodeType()
         * @generated
         */
        EClass NODE_TYPE = eINSTANCE.getNodeType();

        /**
         * The meta object literal for the '<em><b>Desc</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute NODE_TYPE__DESC = eINSTANCE.getNodeType_Desc();

        /**
         * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute NODE_TYPE__GROUP = eINSTANCE.getNodeType_Group();

        /**
         * The meta object literal for the '<em><b>Data</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference NODE_TYPE__DATA = eINSTANCE.getNodeType_Data();

        /**
         * The meta object literal for the '<em><b>Port</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference NODE_TYPE__PORT = eINSTANCE.getNodeType_Port();

        /**
         * The meta object literal for the '<em><b>Graph</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference NODE_TYPE__GRAPH = eINSTANCE.getNodeType_Graph();

        /**
         * The meta object literal for the '<em><b>Locator</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference NODE_TYPE__LOCATOR = eINSTANCE.getNodeType_Locator();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute NODE_TYPE__ID = eINSTANCE.getNodeType_Id();

        /**
         * The meta object literal for the '<em><b>Parse Indegree</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute NODE_TYPE__PARSE_INDEGREE = eINSTANCE.getNodeType_ParseIndegree();

        /**
         * The meta object literal for the '<em><b>Parse Outdegree</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute NODE_TYPE__PARSE_OUTDEGREE = eINSTANCE.getNodeType_ParseOutdegree();

        /**
         * The meta object literal for the '{@link org.graphdrawing.graphml.impl.PortTypeImpl <em>Port Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.graphdrawing.graphml.impl.PortTypeImpl
         * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getPortType()
         * @generated
         */
        EClass PORT_TYPE = eINSTANCE.getPortType();

        /**
         * The meta object literal for the '<em><b>Desc</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute PORT_TYPE__DESC = eINSTANCE.getPortType_Desc();

        /**
         * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute PORT_TYPE__GROUP = eINSTANCE.getPortType_Group();

        /**
         * The meta object literal for the '<em><b>Data</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference PORT_TYPE__DATA = eINSTANCE.getPortType_Data();

        /**
         * The meta object literal for the '<em><b>Port</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference PORT_TYPE__PORT = eINSTANCE.getPortType_Port();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute PORT_TYPE__NAME = eINSTANCE.getPortType_Name();

        /**
         * The meta object literal for the '{@link org.graphdrawing.graphml.EndpointTypeType <em>Endpoint Type Type</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.graphdrawing.graphml.EndpointTypeType
         * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getEndpointTypeType()
         * @generated
         */
        EEnum ENDPOINT_TYPE_TYPE = eINSTANCE.getEndpointTypeType();

        /**
         * The meta object literal for the '{@link org.graphdrawing.graphml.GraphEdgedefaultType <em>Graph Edgedefault Type</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.graphdrawing.graphml.GraphEdgedefaultType
         * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getGraphEdgedefaultType()
         * @generated
         */
        EEnum GRAPH_EDGEDEFAULT_TYPE = eINSTANCE.getGraphEdgedefaultType();

        /**
         * The meta object literal for the '{@link org.graphdrawing.graphml.GraphEdgeidsType <em>Graph Edgeids Type</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.graphdrawing.graphml.GraphEdgeidsType
         * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getGraphEdgeidsType()
         * @generated
         */
        EEnum GRAPH_EDGEIDS_TYPE = eINSTANCE.getGraphEdgeidsType();

        /**
         * The meta object literal for the '{@link org.graphdrawing.graphml.GraphNodeidsType <em>Graph Nodeids Type</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.graphdrawing.graphml.GraphNodeidsType
         * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getGraphNodeidsType()
         * @generated
         */
        EEnum GRAPH_NODEIDS_TYPE = eINSTANCE.getGraphNodeidsType();

        /**
         * The meta object literal for the '{@link org.graphdrawing.graphml.GraphOrderType <em>Graph Order Type</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.graphdrawing.graphml.GraphOrderType
         * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getGraphOrderType()
         * @generated
         */
        EEnum GRAPH_ORDER_TYPE = eINSTANCE.getGraphOrderType();

        /**
         * The meta object literal for the '{@link org.graphdrawing.graphml.KeyForType <em>Key For Type</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.graphdrawing.graphml.KeyForType
         * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getKeyForType()
         * @generated
         */
        EEnum KEY_FOR_TYPE = eINSTANCE.getKeyForType();

        /**
         * The meta object literal for the '{@link org.graphdrawing.graphml.KeyTypeType <em>Key Type Type</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.graphdrawing.graphml.KeyTypeType
         * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getKeyTypeType()
         * @generated
         */
        EEnum KEY_TYPE_TYPE = eINSTANCE.getKeyTypeType();

        /**
         * The meta object literal for the '<em>Endpoint Type Type Object</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.graphdrawing.graphml.EndpointTypeType
         * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getEndpointTypeTypeObject()
         * @generated
         */
        EDataType ENDPOINT_TYPE_TYPE_OBJECT = eINSTANCE.getEndpointTypeTypeObject();

        /**
         * The meta object literal for the '<em>Graph Edgedefault Type Object</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.graphdrawing.graphml.GraphEdgedefaultType
         * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getGraphEdgedefaultTypeObject()
         * @generated
         */
        EDataType GRAPH_EDGEDEFAULT_TYPE_OBJECT = eINSTANCE.getGraphEdgedefaultTypeObject();

        /**
         * The meta object literal for the '<em>Graph Edgeids Type Object</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.graphdrawing.graphml.GraphEdgeidsType
         * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getGraphEdgeidsTypeObject()
         * @generated
         */
        EDataType GRAPH_EDGEIDS_TYPE_OBJECT = eINSTANCE.getGraphEdgeidsTypeObject();

        /**
         * The meta object literal for the '<em>Graph Edges Type</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see java.math.BigInteger
         * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getGraphEdgesType()
         * @generated
         */
        EDataType GRAPH_EDGES_TYPE = eINSTANCE.getGraphEdgesType();

        /**
         * The meta object literal for the '<em>Graph Maxindegree Type</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see java.math.BigInteger
         * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getGraphMaxindegreeType()
         * @generated
         */
        EDataType GRAPH_MAXINDEGREE_TYPE = eINSTANCE.getGraphMaxindegreeType();

        /**
         * The meta object literal for the '<em>Graph Maxoutdegree Type</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see java.math.BigInteger
         * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getGraphMaxoutdegreeType()
         * @generated
         */
        EDataType GRAPH_MAXOUTDEGREE_TYPE = eINSTANCE.getGraphMaxoutdegreeType();

        /**
         * The meta object literal for the '<em>Graph Nodeids Type Object</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.graphdrawing.graphml.GraphNodeidsType
         * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getGraphNodeidsTypeObject()
         * @generated
         */
        EDataType GRAPH_NODEIDS_TYPE_OBJECT = eINSTANCE.getGraphNodeidsTypeObject();

        /**
         * The meta object literal for the '<em>Graph Nodes Type</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see java.math.BigInteger
         * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getGraphNodesType()
         * @generated
         */
        EDataType GRAPH_NODES_TYPE = eINSTANCE.getGraphNodesType();

        /**
         * The meta object literal for the '<em>Graph Order Type Object</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.graphdrawing.graphml.GraphOrderType
         * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getGraphOrderTypeObject()
         * @generated
         */
        EDataType GRAPH_ORDER_TYPE_OBJECT = eINSTANCE.getGraphOrderTypeObject();

        /**
         * The meta object literal for the '<em>Key For Type Object</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.graphdrawing.graphml.KeyForType
         * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getKeyForTypeObject()
         * @generated
         */
        EDataType KEY_FOR_TYPE_OBJECT = eINSTANCE.getKeyForTypeObject();

        /**
         * The meta object literal for the '<em>Key Name Type</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see java.lang.String
         * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getKeyNameType()
         * @generated
         */
        EDataType KEY_NAME_TYPE = eINSTANCE.getKeyNameType();

        /**
         * The meta object literal for the '<em>Key Type Type Object</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.graphdrawing.graphml.KeyTypeType
         * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getKeyTypeTypeObject()
         * @generated
         */
        EDataType KEY_TYPE_TYPE_OBJECT = eINSTANCE.getKeyTypeTypeObject();

        /**
         * The meta object literal for the '<em>Node Indegree Type</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see java.math.BigInteger
         * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getNodeIndegreeType()
         * @generated
         */
        EDataType NODE_INDEGREE_TYPE = eINSTANCE.getNodeIndegreeType();

        /**
         * The meta object literal for the '<em>Node Outdegree Type</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see java.math.BigInteger
         * @see org.graphdrawing.graphml.impl.GraphMLPackageImpl#getNodeOutdegreeType()
         * @generated
         */
        EDataType NODE_OUTDEGREE_TYPE = eINSTANCE.getNodeOutdegreeType();

    }

} //GraphMLPackage
