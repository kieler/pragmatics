/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.graphdrawing.graphml.xmlns;

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
 * @see org.graphdrawing.graphml.xmlns.XmlnsFactory
 * @model kind="package"
 * @generated
 */
public interface XmlnsPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "xmlns";

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
	String eNS_PREFIX = "_xmlns";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	XmlnsPackage eINSTANCE = org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.graphdrawing.graphml.xmlns.impl.DataExtensionTypeImpl <em>Data Extension Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.graphdrawing.graphml.xmlns.impl.DataExtensionTypeImpl
	 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getDataExtensionType()
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
	 * The meta object id for the '{@link org.graphdrawing.graphml.xmlns.impl.DataTypeImpl <em>Data Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.graphdrawing.graphml.xmlns.impl.DataTypeImpl
	 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getDataType()
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
	 * The meta object id for the '{@link org.graphdrawing.graphml.xmlns.impl.DefaultTypeImpl <em>Default Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.graphdrawing.graphml.xmlns.impl.DefaultTypeImpl
	 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getDefaultType()
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
	 * The meta object id for the '{@link org.graphdrawing.graphml.xmlns.impl.DocumentRootImpl <em>Document Root</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.graphdrawing.graphml.xmlns.impl.DocumentRootImpl
	 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getDocumentRoot()
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
	 * The meta object id for the '{@link org.graphdrawing.graphml.xmlns.impl.EdgeTypeImpl <em>Edge Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.graphdrawing.graphml.xmlns.impl.EdgeTypeImpl
	 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getEdgeType()
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
	 * The meta object id for the '{@link org.graphdrawing.graphml.xmlns.impl.EndpointTypeImpl <em>Endpoint Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.graphdrawing.graphml.xmlns.impl.EndpointTypeImpl
	 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getEndpointType()
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
	 * The meta object id for the '{@link org.graphdrawing.graphml.xmlns.impl.GraphmlTypeImpl <em>Graphml Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.graphdrawing.graphml.xmlns.impl.GraphmlTypeImpl
	 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getGraphmlType()
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
	 * The meta object id for the '{@link org.graphdrawing.graphml.xmlns.impl.GraphTypeImpl <em>Graph Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.graphdrawing.graphml.xmlns.impl.GraphTypeImpl
	 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getGraphType()
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
	 * The meta object id for the '{@link org.graphdrawing.graphml.xmlns.impl.HyperedgeTypeImpl <em>Hyperedge Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.graphdrawing.graphml.xmlns.impl.HyperedgeTypeImpl
	 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getHyperedgeType()
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
	 * The meta object id for the '{@link org.graphdrawing.graphml.xmlns.impl.KeyTypeImpl <em>Key Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.graphdrawing.graphml.xmlns.impl.KeyTypeImpl
	 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getKeyType()
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
	 * The meta object id for the '{@link org.graphdrawing.graphml.xmlns.impl.LocatorTypeImpl <em>Locator Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.graphdrawing.graphml.xmlns.impl.LocatorTypeImpl
	 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getLocatorType()
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
	 * The meta object id for the '{@link org.graphdrawing.graphml.xmlns.impl.NodeTypeImpl <em>Node Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.graphdrawing.graphml.xmlns.impl.NodeTypeImpl
	 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getNodeType()
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
	 * The meta object id for the '{@link org.graphdrawing.graphml.xmlns.impl.PortTypeImpl <em>Port Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.graphdrawing.graphml.xmlns.impl.PortTypeImpl
	 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getPortType()
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
	 * The meta object id for the '{@link org.graphdrawing.graphml.xmlns.EndpointTypeType <em>Endpoint Type Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.graphdrawing.graphml.xmlns.EndpointTypeType
	 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getEndpointTypeType()
	 * @generated
	 */
	int ENDPOINT_TYPE_TYPE = 13;

	/**
	 * The meta object id for the '{@link org.graphdrawing.graphml.xmlns.GraphEdgedefaultType <em>Graph Edgedefault Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.graphdrawing.graphml.xmlns.GraphEdgedefaultType
	 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getGraphEdgedefaultType()
	 * @generated
	 */
	int GRAPH_EDGEDEFAULT_TYPE = 14;

	/**
	 * The meta object id for the '{@link org.graphdrawing.graphml.xmlns.GraphEdgeidsType <em>Graph Edgeids Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.graphdrawing.graphml.xmlns.GraphEdgeidsType
	 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getGraphEdgeidsType()
	 * @generated
	 */
	int GRAPH_EDGEIDS_TYPE = 15;

	/**
	 * The meta object id for the '{@link org.graphdrawing.graphml.xmlns.GraphNodeidsType <em>Graph Nodeids Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.graphdrawing.graphml.xmlns.GraphNodeidsType
	 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getGraphNodeidsType()
	 * @generated
	 */
	int GRAPH_NODEIDS_TYPE = 16;

	/**
	 * The meta object id for the '{@link org.graphdrawing.graphml.xmlns.GraphOrderType <em>Graph Order Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.graphdrawing.graphml.xmlns.GraphOrderType
	 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getGraphOrderType()
	 * @generated
	 */
	int GRAPH_ORDER_TYPE = 17;

	/**
	 * The meta object id for the '{@link org.graphdrawing.graphml.xmlns.KeyForType <em>Key For Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.graphdrawing.graphml.xmlns.KeyForType
	 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getKeyForType()
	 * @generated
	 */
	int KEY_FOR_TYPE = 18;

	/**
	 * The meta object id for the '{@link org.graphdrawing.graphml.xmlns.KeyTypeType <em>Key Type Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.graphdrawing.graphml.xmlns.KeyTypeType
	 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getKeyTypeType()
	 * @generated
	 */
	int KEY_TYPE_TYPE = 19;

	/**
	 * The meta object id for the '<em>Endpoint Type Type Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.graphdrawing.graphml.xmlns.EndpointTypeType
	 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getEndpointTypeTypeObject()
	 * @generated
	 */
	int ENDPOINT_TYPE_TYPE_OBJECT = 20;

	/**
	 * The meta object id for the '<em>Graph Edgedefault Type Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.graphdrawing.graphml.xmlns.GraphEdgedefaultType
	 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getGraphEdgedefaultTypeObject()
	 * @generated
	 */
	int GRAPH_EDGEDEFAULT_TYPE_OBJECT = 21;

	/**
	 * The meta object id for the '<em>Graph Edgeids Type Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.graphdrawing.graphml.xmlns.GraphEdgeidsType
	 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getGraphEdgeidsTypeObject()
	 * @generated
	 */
	int GRAPH_EDGEIDS_TYPE_OBJECT = 22;

	/**
	 * The meta object id for the '<em>Graph Edges Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.math.BigInteger
	 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getGraphEdgesType()
	 * @generated
	 */
	int GRAPH_EDGES_TYPE = 23;

	/**
	 * The meta object id for the '<em>Graph Maxindegree Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.math.BigInteger
	 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getGraphMaxindegreeType()
	 * @generated
	 */
	int GRAPH_MAXINDEGREE_TYPE = 24;

	/**
	 * The meta object id for the '<em>Graph Maxoutdegree Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.math.BigInteger
	 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getGraphMaxoutdegreeType()
	 * @generated
	 */
	int GRAPH_MAXOUTDEGREE_TYPE = 25;

	/**
	 * The meta object id for the '<em>Graph Nodeids Type Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.graphdrawing.graphml.xmlns.GraphNodeidsType
	 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getGraphNodeidsTypeObject()
	 * @generated
	 */
	int GRAPH_NODEIDS_TYPE_OBJECT = 26;

	/**
	 * The meta object id for the '<em>Graph Nodes Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.math.BigInteger
	 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getGraphNodesType()
	 * @generated
	 */
	int GRAPH_NODES_TYPE = 27;

	/**
	 * The meta object id for the '<em>Graph Order Type Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.graphdrawing.graphml.xmlns.GraphOrderType
	 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getGraphOrderTypeObject()
	 * @generated
	 */
	int GRAPH_ORDER_TYPE_OBJECT = 28;

	/**
	 * The meta object id for the '<em>Key For Type Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.graphdrawing.graphml.xmlns.KeyForType
	 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getKeyForTypeObject()
	 * @generated
	 */
	int KEY_FOR_TYPE_OBJECT = 29;

	/**
	 * The meta object id for the '<em>Key Name Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.String
	 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getKeyNameType()
	 * @generated
	 */
	int KEY_NAME_TYPE = 30;

	/**
	 * The meta object id for the '<em>Key Type Type Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.graphdrawing.graphml.xmlns.KeyTypeType
	 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getKeyTypeTypeObject()
	 * @generated
	 */
	int KEY_TYPE_TYPE_OBJECT = 31;

	/**
	 * The meta object id for the '<em>Node Indegree Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.math.BigInteger
	 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getNodeIndegreeType()
	 * @generated
	 */
	int NODE_INDEGREE_TYPE = 32;

	/**
	 * The meta object id for the '<em>Node Outdegree Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.math.BigInteger
	 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getNodeOutdegreeType()
	 * @generated
	 */
	int NODE_OUTDEGREE_TYPE = 33;


	/**
	 * Returns the meta object for class '{@link org.graphdrawing.graphml.xmlns.DataExtensionType <em>Data Extension Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data Extension Type</em>'.
	 * @see org.graphdrawing.graphml.xmlns.DataExtensionType
	 * @generated
	 */
	EClass getDataExtensionType();

	/**
	 * Returns the meta object for the attribute list '{@link org.graphdrawing.graphml.xmlns.DataExtensionType#getMixed <em>Mixed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Mixed</em>'.
	 * @see org.graphdrawing.graphml.xmlns.DataExtensionType#getMixed()
	 * @see #getDataExtensionType()
	 * @generated
	 */
	EAttribute getDataExtensionType_Mixed();

	/**
	 * Returns the meta object for class '{@link org.graphdrawing.graphml.xmlns.DataType <em>Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data Type</em>'.
	 * @see org.graphdrawing.graphml.xmlns.DataType
	 * @generated
	 */
	EClass getDataType();

	/**
	 * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.xmlns.DataType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.graphdrawing.graphml.xmlns.DataType#getId()
	 * @see #getDataType()
	 * @generated
	 */
	EAttribute getDataType_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.xmlns.DataType#getKey <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see org.graphdrawing.graphml.xmlns.DataType#getKey()
	 * @see #getDataType()
	 * @generated
	 */
	EAttribute getDataType_Key();

	/**
	 * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.xmlns.DataType#getTime <em>Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Time</em>'.
	 * @see org.graphdrawing.graphml.xmlns.DataType#getTime()
	 * @see #getDataType()
	 * @generated
	 */
	EAttribute getDataType_Time();

	/**
	 * Returns the meta object for class '{@link org.graphdrawing.graphml.xmlns.DefaultType <em>Default Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Default Type</em>'.
	 * @see org.graphdrawing.graphml.xmlns.DefaultType
	 * @generated
	 */
	EClass getDefaultType();

	/**
	 * Returns the meta object for class '{@link org.graphdrawing.graphml.xmlns.DocumentRoot <em>Document Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Document Root</em>'.
	 * @see org.graphdrawing.graphml.xmlns.DocumentRoot
	 * @generated
	 */
	EClass getDocumentRoot();

	/**
	 * Returns the meta object for the attribute list '{@link org.graphdrawing.graphml.xmlns.DocumentRoot#getMixed <em>Mixed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Mixed</em>'.
	 * @see org.graphdrawing.graphml.xmlns.DocumentRoot#getMixed()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Mixed();

	/**
	 * Returns the meta object for the map '{@link org.graphdrawing.graphml.xmlns.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>XMLNS Prefix Map</em>'.
	 * @see org.graphdrawing.graphml.xmlns.DocumentRoot#getXMLNSPrefixMap()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XMLNSPrefixMap();

	/**
	 * Returns the meta object for the map '{@link org.graphdrawing.graphml.xmlns.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>XSI Schema Location</em>'.
	 * @see org.graphdrawing.graphml.xmlns.DocumentRoot#getXSISchemaLocation()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XSISchemaLocation();

	/**
	 * Returns the meta object for the containment reference '{@link org.graphdrawing.graphml.xmlns.DocumentRoot#getData <em>Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Data</em>'.
	 * @see org.graphdrawing.graphml.xmlns.DocumentRoot#getData()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Data();

	/**
	 * Returns the meta object for the containment reference '{@link org.graphdrawing.graphml.xmlns.DocumentRoot#getDefault <em>Default</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Default</em>'.
	 * @see org.graphdrawing.graphml.xmlns.DocumentRoot#getDefault()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Default();

	/**
	 * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.xmlns.DocumentRoot#getDesc <em>Desc</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Desc</em>'.
	 * @see org.graphdrawing.graphml.xmlns.DocumentRoot#getDesc()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Desc();

	/**
	 * Returns the meta object for the containment reference '{@link org.graphdrawing.graphml.xmlns.DocumentRoot#getEdge <em>Edge</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Edge</em>'.
	 * @see org.graphdrawing.graphml.xmlns.DocumentRoot#getEdge()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Edge();

	/**
	 * Returns the meta object for the containment reference '{@link org.graphdrawing.graphml.xmlns.DocumentRoot#getEndpoint <em>Endpoint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Endpoint</em>'.
	 * @see org.graphdrawing.graphml.xmlns.DocumentRoot#getEndpoint()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Endpoint();

	/**
	 * Returns the meta object for the containment reference '{@link org.graphdrawing.graphml.xmlns.DocumentRoot#getGraph <em>Graph</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Graph</em>'.
	 * @see org.graphdrawing.graphml.xmlns.DocumentRoot#getGraph()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Graph();

	/**
	 * Returns the meta object for the containment reference '{@link org.graphdrawing.graphml.xmlns.DocumentRoot#getGraphml <em>Graphml</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Graphml</em>'.
	 * @see org.graphdrawing.graphml.xmlns.DocumentRoot#getGraphml()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Graphml();

	/**
	 * Returns the meta object for the containment reference '{@link org.graphdrawing.graphml.xmlns.DocumentRoot#getHyperedge <em>Hyperedge</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Hyperedge</em>'.
	 * @see org.graphdrawing.graphml.xmlns.DocumentRoot#getHyperedge()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Hyperedge();

	/**
	 * Returns the meta object for the containment reference '{@link org.graphdrawing.graphml.xmlns.DocumentRoot#getKey <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Key</em>'.
	 * @see org.graphdrawing.graphml.xmlns.DocumentRoot#getKey()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Key();

	/**
	 * Returns the meta object for the containment reference '{@link org.graphdrawing.graphml.xmlns.DocumentRoot#getLocator <em>Locator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Locator</em>'.
	 * @see org.graphdrawing.graphml.xmlns.DocumentRoot#getLocator()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Locator();

	/**
	 * Returns the meta object for the containment reference '{@link org.graphdrawing.graphml.xmlns.DocumentRoot#getNode <em>Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Node</em>'.
	 * @see org.graphdrawing.graphml.xmlns.DocumentRoot#getNode()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Node();

	/**
	 * Returns the meta object for the containment reference '{@link org.graphdrawing.graphml.xmlns.DocumentRoot#getPort <em>Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Port</em>'.
	 * @see org.graphdrawing.graphml.xmlns.DocumentRoot#getPort()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Port();

	/**
	 * Returns the meta object for class '{@link org.graphdrawing.graphml.xmlns.EdgeType <em>Edge Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Edge Type</em>'.
	 * @see org.graphdrawing.graphml.xmlns.EdgeType
	 * @generated
	 */
	EClass getEdgeType();

	/**
	 * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.xmlns.EdgeType#getDesc <em>Desc</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Desc</em>'.
	 * @see org.graphdrawing.graphml.xmlns.EdgeType#getDesc()
	 * @see #getEdgeType()
	 * @generated
	 */
	EAttribute getEdgeType_Desc();

	/**
	 * Returns the meta object for the containment reference list '{@link org.graphdrawing.graphml.xmlns.EdgeType#getData <em>Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Data</em>'.
	 * @see org.graphdrawing.graphml.xmlns.EdgeType#getData()
	 * @see #getEdgeType()
	 * @generated
	 */
	EReference getEdgeType_Data();

	/**
	 * Returns the meta object for the containment reference '{@link org.graphdrawing.graphml.xmlns.EdgeType#getGraph <em>Graph</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Graph</em>'.
	 * @see org.graphdrawing.graphml.xmlns.EdgeType#getGraph()
	 * @see #getEdgeType()
	 * @generated
	 */
	EReference getEdgeType_Graph();

	/**
	 * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.xmlns.EdgeType#isDirected <em>Directed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Directed</em>'.
	 * @see org.graphdrawing.graphml.xmlns.EdgeType#isDirected()
	 * @see #getEdgeType()
	 * @generated
	 */
	EAttribute getEdgeType_Directed();

	/**
	 * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.xmlns.EdgeType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.graphdrawing.graphml.xmlns.EdgeType#getId()
	 * @see #getEdgeType()
	 * @generated
	 */
	EAttribute getEdgeType_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.xmlns.EdgeType#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Source</em>'.
	 * @see org.graphdrawing.graphml.xmlns.EdgeType#getSource()
	 * @see #getEdgeType()
	 * @generated
	 */
	EAttribute getEdgeType_Source();

	/**
	 * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.xmlns.EdgeType#getSourceport <em>Sourceport</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sourceport</em>'.
	 * @see org.graphdrawing.graphml.xmlns.EdgeType#getSourceport()
	 * @see #getEdgeType()
	 * @generated
	 */
	EAttribute getEdgeType_Sourceport();

	/**
	 * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.xmlns.EdgeType#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Target</em>'.
	 * @see org.graphdrawing.graphml.xmlns.EdgeType#getTarget()
	 * @see #getEdgeType()
	 * @generated
	 */
	EAttribute getEdgeType_Target();

	/**
	 * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.xmlns.EdgeType#getTargetport <em>Targetport</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Targetport</em>'.
	 * @see org.graphdrawing.graphml.xmlns.EdgeType#getTargetport()
	 * @see #getEdgeType()
	 * @generated
	 */
	EAttribute getEdgeType_Targetport();

	/**
	 * Returns the meta object for class '{@link org.graphdrawing.graphml.xmlns.EndpointType <em>Endpoint Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Endpoint Type</em>'.
	 * @see org.graphdrawing.graphml.xmlns.EndpointType
	 * @generated
	 */
	EClass getEndpointType();

	/**
	 * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.xmlns.EndpointType#getDesc <em>Desc</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Desc</em>'.
	 * @see org.graphdrawing.graphml.xmlns.EndpointType#getDesc()
	 * @see #getEndpointType()
	 * @generated
	 */
	EAttribute getEndpointType_Desc();

	/**
	 * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.xmlns.EndpointType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.graphdrawing.graphml.xmlns.EndpointType#getId()
	 * @see #getEndpointType()
	 * @generated
	 */
	EAttribute getEndpointType_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.xmlns.EndpointType#getNode <em>Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Node</em>'.
	 * @see org.graphdrawing.graphml.xmlns.EndpointType#getNode()
	 * @see #getEndpointType()
	 * @generated
	 */
	EAttribute getEndpointType_Node();

	/**
	 * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.xmlns.EndpointType#getPort <em>Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Port</em>'.
	 * @see org.graphdrawing.graphml.xmlns.EndpointType#getPort()
	 * @see #getEndpointType()
	 * @generated
	 */
	EAttribute getEndpointType_Port();

	/**
	 * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.xmlns.EndpointType#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.graphdrawing.graphml.xmlns.EndpointType#getType()
	 * @see #getEndpointType()
	 * @generated
	 */
	EAttribute getEndpointType_Type();

	/**
	 * Returns the meta object for class '{@link org.graphdrawing.graphml.xmlns.GraphmlType <em>Graphml Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Graphml Type</em>'.
	 * @see org.graphdrawing.graphml.xmlns.GraphmlType
	 * @generated
	 */
	EClass getGraphmlType();

	/**
	 * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.xmlns.GraphmlType#getDesc <em>Desc</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Desc</em>'.
	 * @see org.graphdrawing.graphml.xmlns.GraphmlType#getDesc()
	 * @see #getGraphmlType()
	 * @generated
	 */
	EAttribute getGraphmlType_Desc();

	/**
	 * Returns the meta object for the containment reference list '{@link org.graphdrawing.graphml.xmlns.GraphmlType#getKey <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Key</em>'.
	 * @see org.graphdrawing.graphml.xmlns.GraphmlType#getKey()
	 * @see #getGraphmlType()
	 * @generated
	 */
	EReference getGraphmlType_Key();

	/**
	 * Returns the meta object for the attribute list '{@link org.graphdrawing.graphml.xmlns.GraphmlType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.graphdrawing.graphml.xmlns.GraphmlType#getGroup()
	 * @see #getGraphmlType()
	 * @generated
	 */
	EAttribute getGraphmlType_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link org.graphdrawing.graphml.xmlns.GraphmlType#getGraph <em>Graph</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Graph</em>'.
	 * @see org.graphdrawing.graphml.xmlns.GraphmlType#getGraph()
	 * @see #getGraphmlType()
	 * @generated
	 */
	EReference getGraphmlType_Graph();

	/**
	 * Returns the meta object for the containment reference list '{@link org.graphdrawing.graphml.xmlns.GraphmlType#getData <em>Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Data</em>'.
	 * @see org.graphdrawing.graphml.xmlns.GraphmlType#getData()
	 * @see #getGraphmlType()
	 * @generated
	 */
	EReference getGraphmlType_Data();

	/**
	 * Returns the meta object for class '{@link org.graphdrawing.graphml.xmlns.GraphType <em>Graph Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Graph Type</em>'.
	 * @see org.graphdrawing.graphml.xmlns.GraphType
	 * @generated
	 */
	EClass getGraphType();

	/**
	 * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.xmlns.GraphType#getDesc <em>Desc</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Desc</em>'.
	 * @see org.graphdrawing.graphml.xmlns.GraphType#getDesc()
	 * @see #getGraphType()
	 * @generated
	 */
	EAttribute getGraphType_Desc();

	/**
	 * Returns the meta object for the attribute list '{@link org.graphdrawing.graphml.xmlns.GraphType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.graphdrawing.graphml.xmlns.GraphType#getGroup()
	 * @see #getGraphType()
	 * @generated
	 */
	EAttribute getGraphType_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link org.graphdrawing.graphml.xmlns.GraphType#getData <em>Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Data</em>'.
	 * @see org.graphdrawing.graphml.xmlns.GraphType#getData()
	 * @see #getGraphType()
	 * @generated
	 */
	EReference getGraphType_Data();

	/**
	 * Returns the meta object for the containment reference list '{@link org.graphdrawing.graphml.xmlns.GraphType#getNode <em>Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Node</em>'.
	 * @see org.graphdrawing.graphml.xmlns.GraphType#getNode()
	 * @see #getGraphType()
	 * @generated
	 */
	EReference getGraphType_Node();

	/**
	 * Returns the meta object for the containment reference list '{@link org.graphdrawing.graphml.xmlns.GraphType#getEdge <em>Edge</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Edge</em>'.
	 * @see org.graphdrawing.graphml.xmlns.GraphType#getEdge()
	 * @see #getGraphType()
	 * @generated
	 */
	EReference getGraphType_Edge();

	/**
	 * Returns the meta object for the containment reference list '{@link org.graphdrawing.graphml.xmlns.GraphType#getHyperedge <em>Hyperedge</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Hyperedge</em>'.
	 * @see org.graphdrawing.graphml.xmlns.GraphType#getHyperedge()
	 * @see #getGraphType()
	 * @generated
	 */
	EReference getGraphType_Hyperedge();

	/**
	 * Returns the meta object for the containment reference '{@link org.graphdrawing.graphml.xmlns.GraphType#getLocator <em>Locator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Locator</em>'.
	 * @see org.graphdrawing.graphml.xmlns.GraphType#getLocator()
	 * @see #getGraphType()
	 * @generated
	 */
	EReference getGraphType_Locator();

	/**
	 * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.xmlns.GraphType#getEdgedefault <em>Edgedefault</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Edgedefault</em>'.
	 * @see org.graphdrawing.graphml.xmlns.GraphType#getEdgedefault()
	 * @see #getGraphType()
	 * @generated
	 */
	EAttribute getGraphType_Edgedefault();

	/**
	 * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.xmlns.GraphType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.graphdrawing.graphml.xmlns.GraphType#getId()
	 * @see #getGraphType()
	 * @generated
	 */
	EAttribute getGraphType_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.xmlns.GraphType#getParseEdgeids <em>Parse Edgeids</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Parse Edgeids</em>'.
	 * @see org.graphdrawing.graphml.xmlns.GraphType#getParseEdgeids()
	 * @see #getGraphType()
	 * @generated
	 */
	EAttribute getGraphType_ParseEdgeids();

	/**
	 * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.xmlns.GraphType#getParseEdges <em>Parse Edges</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Parse Edges</em>'.
	 * @see org.graphdrawing.graphml.xmlns.GraphType#getParseEdges()
	 * @see #getGraphType()
	 * @generated
	 */
	EAttribute getGraphType_ParseEdges();

	/**
	 * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.xmlns.GraphType#getParseMaxindegree <em>Parse Maxindegree</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Parse Maxindegree</em>'.
	 * @see org.graphdrawing.graphml.xmlns.GraphType#getParseMaxindegree()
	 * @see #getGraphType()
	 * @generated
	 */
	EAttribute getGraphType_ParseMaxindegree();

	/**
	 * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.xmlns.GraphType#getParseMaxoutdegree <em>Parse Maxoutdegree</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Parse Maxoutdegree</em>'.
	 * @see org.graphdrawing.graphml.xmlns.GraphType#getParseMaxoutdegree()
	 * @see #getGraphType()
	 * @generated
	 */
	EAttribute getGraphType_ParseMaxoutdegree();

	/**
	 * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.xmlns.GraphType#getParseNodeids <em>Parse Nodeids</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Parse Nodeids</em>'.
	 * @see org.graphdrawing.graphml.xmlns.GraphType#getParseNodeids()
	 * @see #getGraphType()
	 * @generated
	 */
	EAttribute getGraphType_ParseNodeids();

	/**
	 * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.xmlns.GraphType#getParseNodes <em>Parse Nodes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Parse Nodes</em>'.
	 * @see org.graphdrawing.graphml.xmlns.GraphType#getParseNodes()
	 * @see #getGraphType()
	 * @generated
	 */
	EAttribute getGraphType_ParseNodes();

	/**
	 * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.xmlns.GraphType#getParseOrder <em>Parse Order</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Parse Order</em>'.
	 * @see org.graphdrawing.graphml.xmlns.GraphType#getParseOrder()
	 * @see #getGraphType()
	 * @generated
	 */
	EAttribute getGraphType_ParseOrder();

	/**
	 * Returns the meta object for class '{@link org.graphdrawing.graphml.xmlns.HyperedgeType <em>Hyperedge Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Hyperedge Type</em>'.
	 * @see org.graphdrawing.graphml.xmlns.HyperedgeType
	 * @generated
	 */
	EClass getHyperedgeType();

	/**
	 * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.xmlns.HyperedgeType#getDesc <em>Desc</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Desc</em>'.
	 * @see org.graphdrawing.graphml.xmlns.HyperedgeType#getDesc()
	 * @see #getHyperedgeType()
	 * @generated
	 */
	EAttribute getHyperedgeType_Desc();

	/**
	 * Returns the meta object for the attribute list '{@link org.graphdrawing.graphml.xmlns.HyperedgeType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.graphdrawing.graphml.xmlns.HyperedgeType#getGroup()
	 * @see #getHyperedgeType()
	 * @generated
	 */
	EAttribute getHyperedgeType_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link org.graphdrawing.graphml.xmlns.HyperedgeType#getData <em>Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Data</em>'.
	 * @see org.graphdrawing.graphml.xmlns.HyperedgeType#getData()
	 * @see #getHyperedgeType()
	 * @generated
	 */
	EReference getHyperedgeType_Data();

	/**
	 * Returns the meta object for the containment reference list '{@link org.graphdrawing.graphml.xmlns.HyperedgeType#getEndpoint <em>Endpoint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Endpoint</em>'.
	 * @see org.graphdrawing.graphml.xmlns.HyperedgeType#getEndpoint()
	 * @see #getHyperedgeType()
	 * @generated
	 */
	EReference getHyperedgeType_Endpoint();

	/**
	 * Returns the meta object for the containment reference '{@link org.graphdrawing.graphml.xmlns.HyperedgeType#getGraph <em>Graph</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Graph</em>'.
	 * @see org.graphdrawing.graphml.xmlns.HyperedgeType#getGraph()
	 * @see #getHyperedgeType()
	 * @generated
	 */
	EReference getHyperedgeType_Graph();

	/**
	 * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.xmlns.HyperedgeType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.graphdrawing.graphml.xmlns.HyperedgeType#getId()
	 * @see #getHyperedgeType()
	 * @generated
	 */
	EAttribute getHyperedgeType_Id();

	/**
	 * Returns the meta object for class '{@link org.graphdrawing.graphml.xmlns.KeyType <em>Key Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Key Type</em>'.
	 * @see org.graphdrawing.graphml.xmlns.KeyType
	 * @generated
	 */
	EClass getKeyType();

	/**
	 * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.xmlns.KeyType#getDesc <em>Desc</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Desc</em>'.
	 * @see org.graphdrawing.graphml.xmlns.KeyType#getDesc()
	 * @see #getKeyType()
	 * @generated
	 */
	EAttribute getKeyType_Desc();

	/**
	 * Returns the meta object for the containment reference '{@link org.graphdrawing.graphml.xmlns.KeyType#getDefault <em>Default</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Default</em>'.
	 * @see org.graphdrawing.graphml.xmlns.KeyType#getDefault()
	 * @see #getKeyType()
	 * @generated
	 */
	EReference getKeyType_Default();

	/**
	 * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.xmlns.KeyType#getAttrName <em>Attr Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr Name</em>'.
	 * @see org.graphdrawing.graphml.xmlns.KeyType#getAttrName()
	 * @see #getKeyType()
	 * @generated
	 */
	EAttribute getKeyType_AttrName();

	/**
	 * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.xmlns.KeyType#getAttrType <em>Attr Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr Type</em>'.
	 * @see org.graphdrawing.graphml.xmlns.KeyType#getAttrType()
	 * @see #getKeyType()
	 * @generated
	 */
	EAttribute getKeyType_AttrType();

	/**
	 * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.xmlns.KeyType#isDynamic <em>Dynamic</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Dynamic</em>'.
	 * @see org.graphdrawing.graphml.xmlns.KeyType#isDynamic()
	 * @see #getKeyType()
	 * @generated
	 */
	EAttribute getKeyType_Dynamic();

	/**
	 * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.xmlns.KeyType#getFor <em>For</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>For</em>'.
	 * @see org.graphdrawing.graphml.xmlns.KeyType#getFor()
	 * @see #getKeyType()
	 * @generated
	 */
	EAttribute getKeyType_For();

	/**
	 * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.xmlns.KeyType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.graphdrawing.graphml.xmlns.KeyType#getId()
	 * @see #getKeyType()
	 * @generated
	 */
	EAttribute getKeyType_Id();

	/**
	 * Returns the meta object for class '{@link org.graphdrawing.graphml.xmlns.LocatorType <em>Locator Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Locator Type</em>'.
	 * @see org.graphdrawing.graphml.xmlns.LocatorType
	 * @generated
	 */
	EClass getLocatorType();

	/**
	 * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.xmlns.LocatorType#getHref <em>Href</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Href</em>'.
	 * @see org.graphdrawing.graphml.xmlns.LocatorType#getHref()
	 * @see #getLocatorType()
	 * @generated
	 */
	EAttribute getLocatorType_Href();

	/**
	 * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.xmlns.LocatorType#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.graphdrawing.graphml.xmlns.LocatorType#getType()
	 * @see #getLocatorType()
	 * @generated
	 */
	EAttribute getLocatorType_Type();

	/**
	 * Returns the meta object for class '{@link org.graphdrawing.graphml.xmlns.NodeType <em>Node Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Node Type</em>'.
	 * @see org.graphdrawing.graphml.xmlns.NodeType
	 * @generated
	 */
	EClass getNodeType();

	/**
	 * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.xmlns.NodeType#getDesc <em>Desc</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Desc</em>'.
	 * @see org.graphdrawing.graphml.xmlns.NodeType#getDesc()
	 * @see #getNodeType()
	 * @generated
	 */
	EAttribute getNodeType_Desc();

	/**
	 * Returns the meta object for the attribute list '{@link org.graphdrawing.graphml.xmlns.NodeType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.graphdrawing.graphml.xmlns.NodeType#getGroup()
	 * @see #getNodeType()
	 * @generated
	 */
	EAttribute getNodeType_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link org.graphdrawing.graphml.xmlns.NodeType#getData <em>Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Data</em>'.
	 * @see org.graphdrawing.graphml.xmlns.NodeType#getData()
	 * @see #getNodeType()
	 * @generated
	 */
	EReference getNodeType_Data();

	/**
	 * Returns the meta object for the containment reference list '{@link org.graphdrawing.graphml.xmlns.NodeType#getPort <em>Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Port</em>'.
	 * @see org.graphdrawing.graphml.xmlns.NodeType#getPort()
	 * @see #getNodeType()
	 * @generated
	 */
	EReference getNodeType_Port();

	/**
	 * Returns the meta object for the containment reference '{@link org.graphdrawing.graphml.xmlns.NodeType#getGraph <em>Graph</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Graph</em>'.
	 * @see org.graphdrawing.graphml.xmlns.NodeType#getGraph()
	 * @see #getNodeType()
	 * @generated
	 */
	EReference getNodeType_Graph();

	/**
	 * Returns the meta object for the containment reference '{@link org.graphdrawing.graphml.xmlns.NodeType#getLocator <em>Locator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Locator</em>'.
	 * @see org.graphdrawing.graphml.xmlns.NodeType#getLocator()
	 * @see #getNodeType()
	 * @generated
	 */
	EReference getNodeType_Locator();

	/**
	 * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.xmlns.NodeType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.graphdrawing.graphml.xmlns.NodeType#getId()
	 * @see #getNodeType()
	 * @generated
	 */
	EAttribute getNodeType_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.xmlns.NodeType#getParseIndegree <em>Parse Indegree</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Parse Indegree</em>'.
	 * @see org.graphdrawing.graphml.xmlns.NodeType#getParseIndegree()
	 * @see #getNodeType()
	 * @generated
	 */
	EAttribute getNodeType_ParseIndegree();

	/**
	 * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.xmlns.NodeType#getParseOutdegree <em>Parse Outdegree</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Parse Outdegree</em>'.
	 * @see org.graphdrawing.graphml.xmlns.NodeType#getParseOutdegree()
	 * @see #getNodeType()
	 * @generated
	 */
	EAttribute getNodeType_ParseOutdegree();

	/**
	 * Returns the meta object for class '{@link org.graphdrawing.graphml.xmlns.PortType <em>Port Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Port Type</em>'.
	 * @see org.graphdrawing.graphml.xmlns.PortType
	 * @generated
	 */
	EClass getPortType();

	/**
	 * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.xmlns.PortType#getDesc <em>Desc</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Desc</em>'.
	 * @see org.graphdrawing.graphml.xmlns.PortType#getDesc()
	 * @see #getPortType()
	 * @generated
	 */
	EAttribute getPortType_Desc();

	/**
	 * Returns the meta object for the attribute list '{@link org.graphdrawing.graphml.xmlns.PortType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.graphdrawing.graphml.xmlns.PortType#getGroup()
	 * @see #getPortType()
	 * @generated
	 */
	EAttribute getPortType_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link org.graphdrawing.graphml.xmlns.PortType#getData <em>Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Data</em>'.
	 * @see org.graphdrawing.graphml.xmlns.PortType#getData()
	 * @see #getPortType()
	 * @generated
	 */
	EReference getPortType_Data();

	/**
	 * Returns the meta object for the containment reference list '{@link org.graphdrawing.graphml.xmlns.PortType#getPort <em>Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Port</em>'.
	 * @see org.graphdrawing.graphml.xmlns.PortType#getPort()
	 * @see #getPortType()
	 * @generated
	 */
	EReference getPortType_Port();

	/**
	 * Returns the meta object for the attribute '{@link org.graphdrawing.graphml.xmlns.PortType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.graphdrawing.graphml.xmlns.PortType#getName()
	 * @see #getPortType()
	 * @generated
	 */
	EAttribute getPortType_Name();

	/**
	 * Returns the meta object for enum '{@link org.graphdrawing.graphml.xmlns.EndpointTypeType <em>Endpoint Type Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Endpoint Type Type</em>'.
	 * @see org.graphdrawing.graphml.xmlns.EndpointTypeType
	 * @generated
	 */
	EEnum getEndpointTypeType();

	/**
	 * Returns the meta object for enum '{@link org.graphdrawing.graphml.xmlns.GraphEdgedefaultType <em>Graph Edgedefault Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Graph Edgedefault Type</em>'.
	 * @see org.graphdrawing.graphml.xmlns.GraphEdgedefaultType
	 * @generated
	 */
	EEnum getGraphEdgedefaultType();

	/**
	 * Returns the meta object for enum '{@link org.graphdrawing.graphml.xmlns.GraphEdgeidsType <em>Graph Edgeids Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Graph Edgeids Type</em>'.
	 * @see org.graphdrawing.graphml.xmlns.GraphEdgeidsType
	 * @generated
	 */
	EEnum getGraphEdgeidsType();

	/**
	 * Returns the meta object for enum '{@link org.graphdrawing.graphml.xmlns.GraphNodeidsType <em>Graph Nodeids Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Graph Nodeids Type</em>'.
	 * @see org.graphdrawing.graphml.xmlns.GraphNodeidsType
	 * @generated
	 */
	EEnum getGraphNodeidsType();

	/**
	 * Returns the meta object for enum '{@link org.graphdrawing.graphml.xmlns.GraphOrderType <em>Graph Order Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Graph Order Type</em>'.
	 * @see org.graphdrawing.graphml.xmlns.GraphOrderType
	 * @generated
	 */
	EEnum getGraphOrderType();

	/**
	 * Returns the meta object for enum '{@link org.graphdrawing.graphml.xmlns.KeyForType <em>Key For Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Key For Type</em>'.
	 * @see org.graphdrawing.graphml.xmlns.KeyForType
	 * @generated
	 */
	EEnum getKeyForType();

	/**
	 * Returns the meta object for enum '{@link org.graphdrawing.graphml.xmlns.KeyTypeType <em>Key Type Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Key Type Type</em>'.
	 * @see org.graphdrawing.graphml.xmlns.KeyTypeType
	 * @generated
	 */
	EEnum getKeyTypeType();

	/**
	 * Returns the meta object for data type '{@link org.graphdrawing.graphml.xmlns.EndpointTypeType <em>Endpoint Type Type Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Endpoint Type Type Object</em>'.
	 * @see org.graphdrawing.graphml.xmlns.EndpointTypeType
	 * @model instanceClass="org.graphdrawing.graphml.xmlns.EndpointTypeType"
	 *        extendedMetaData="name='endpoint.type.type:Object' baseType='endpoint.type.type'"
	 * @generated
	 */
	EDataType getEndpointTypeTypeObject();

	/**
	 * Returns the meta object for data type '{@link org.graphdrawing.graphml.xmlns.GraphEdgedefaultType <em>Graph Edgedefault Type Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Graph Edgedefault Type Object</em>'.
	 * @see org.graphdrawing.graphml.xmlns.GraphEdgedefaultType
	 * @model instanceClass="org.graphdrawing.graphml.xmlns.GraphEdgedefaultType"
	 *        extendedMetaData="name='graph.edgedefault.type:Object' baseType='graph.edgedefault.type'"
	 * @generated
	 */
	EDataType getGraphEdgedefaultTypeObject();

	/**
	 * Returns the meta object for data type '{@link org.graphdrawing.graphml.xmlns.GraphEdgeidsType <em>Graph Edgeids Type Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Graph Edgeids Type Object</em>'.
	 * @see org.graphdrawing.graphml.xmlns.GraphEdgeidsType
	 * @model instanceClass="org.graphdrawing.graphml.xmlns.GraphEdgeidsType"
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
	 * Returns the meta object for data type '{@link org.graphdrawing.graphml.xmlns.GraphNodeidsType <em>Graph Nodeids Type Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Graph Nodeids Type Object</em>'.
	 * @see org.graphdrawing.graphml.xmlns.GraphNodeidsType
	 * @model instanceClass="org.graphdrawing.graphml.xmlns.GraphNodeidsType"
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
	 * Returns the meta object for data type '{@link org.graphdrawing.graphml.xmlns.GraphOrderType <em>Graph Order Type Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Graph Order Type Object</em>'.
	 * @see org.graphdrawing.graphml.xmlns.GraphOrderType
	 * @model instanceClass="org.graphdrawing.graphml.xmlns.GraphOrderType"
	 *        extendedMetaData="name='graph.order.type:Object' baseType='graph.order.type'"
	 * @generated
	 */
	EDataType getGraphOrderTypeObject();

	/**
	 * Returns the meta object for data type '{@link org.graphdrawing.graphml.xmlns.KeyForType <em>Key For Type Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Key For Type Object</em>'.
	 * @see org.graphdrawing.graphml.xmlns.KeyForType
	 * @model instanceClass="org.graphdrawing.graphml.xmlns.KeyForType"
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
	 * Returns the meta object for data type '{@link org.graphdrawing.graphml.xmlns.KeyTypeType <em>Key Type Type Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Key Type Type Object</em>'.
	 * @see org.graphdrawing.graphml.xmlns.KeyTypeType
	 * @model instanceClass="org.graphdrawing.graphml.xmlns.KeyTypeType"
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
	XmlnsFactory getXmlnsFactory();

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
		 * The meta object literal for the '{@link org.graphdrawing.graphml.xmlns.impl.DataExtensionTypeImpl <em>Data Extension Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.graphdrawing.graphml.xmlns.impl.DataExtensionTypeImpl
		 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getDataExtensionType()
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
		 * The meta object literal for the '{@link org.graphdrawing.graphml.xmlns.impl.DataTypeImpl <em>Data Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.graphdrawing.graphml.xmlns.impl.DataTypeImpl
		 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getDataType()
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
		 * The meta object literal for the '{@link org.graphdrawing.graphml.xmlns.impl.DefaultTypeImpl <em>Default Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.graphdrawing.graphml.xmlns.impl.DefaultTypeImpl
		 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getDefaultType()
		 * @generated
		 */
		EClass DEFAULT_TYPE = eINSTANCE.getDefaultType();

		/**
		 * The meta object literal for the '{@link org.graphdrawing.graphml.xmlns.impl.DocumentRootImpl <em>Document Root</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.graphdrawing.graphml.xmlns.impl.DocumentRootImpl
		 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getDocumentRoot()
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
		 * The meta object literal for the '{@link org.graphdrawing.graphml.xmlns.impl.EdgeTypeImpl <em>Edge Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.graphdrawing.graphml.xmlns.impl.EdgeTypeImpl
		 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getEdgeType()
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
		 * The meta object literal for the '{@link org.graphdrawing.graphml.xmlns.impl.EndpointTypeImpl <em>Endpoint Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.graphdrawing.graphml.xmlns.impl.EndpointTypeImpl
		 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getEndpointType()
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
		 * The meta object literal for the '{@link org.graphdrawing.graphml.xmlns.impl.GraphmlTypeImpl <em>Graphml Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.graphdrawing.graphml.xmlns.impl.GraphmlTypeImpl
		 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getGraphmlType()
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
		 * The meta object literal for the '{@link org.graphdrawing.graphml.xmlns.impl.GraphTypeImpl <em>Graph Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.graphdrawing.graphml.xmlns.impl.GraphTypeImpl
		 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getGraphType()
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
		 * The meta object literal for the '{@link org.graphdrawing.graphml.xmlns.impl.HyperedgeTypeImpl <em>Hyperedge Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.graphdrawing.graphml.xmlns.impl.HyperedgeTypeImpl
		 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getHyperedgeType()
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
		 * The meta object literal for the '{@link org.graphdrawing.graphml.xmlns.impl.KeyTypeImpl <em>Key Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.graphdrawing.graphml.xmlns.impl.KeyTypeImpl
		 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getKeyType()
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
		 * The meta object literal for the '{@link org.graphdrawing.graphml.xmlns.impl.LocatorTypeImpl <em>Locator Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.graphdrawing.graphml.xmlns.impl.LocatorTypeImpl
		 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getLocatorType()
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
		 * The meta object literal for the '{@link org.graphdrawing.graphml.xmlns.impl.NodeTypeImpl <em>Node Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.graphdrawing.graphml.xmlns.impl.NodeTypeImpl
		 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getNodeType()
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
		 * The meta object literal for the '{@link org.graphdrawing.graphml.xmlns.impl.PortTypeImpl <em>Port Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.graphdrawing.graphml.xmlns.impl.PortTypeImpl
		 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getPortType()
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
		 * The meta object literal for the '{@link org.graphdrawing.graphml.xmlns.EndpointTypeType <em>Endpoint Type Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.graphdrawing.graphml.xmlns.EndpointTypeType
		 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getEndpointTypeType()
		 * @generated
		 */
		EEnum ENDPOINT_TYPE_TYPE = eINSTANCE.getEndpointTypeType();

		/**
		 * The meta object literal for the '{@link org.graphdrawing.graphml.xmlns.GraphEdgedefaultType <em>Graph Edgedefault Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.graphdrawing.graphml.xmlns.GraphEdgedefaultType
		 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getGraphEdgedefaultType()
		 * @generated
		 */
		EEnum GRAPH_EDGEDEFAULT_TYPE = eINSTANCE.getGraphEdgedefaultType();

		/**
		 * The meta object literal for the '{@link org.graphdrawing.graphml.xmlns.GraphEdgeidsType <em>Graph Edgeids Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.graphdrawing.graphml.xmlns.GraphEdgeidsType
		 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getGraphEdgeidsType()
		 * @generated
		 */
		EEnum GRAPH_EDGEIDS_TYPE = eINSTANCE.getGraphEdgeidsType();

		/**
		 * The meta object literal for the '{@link org.graphdrawing.graphml.xmlns.GraphNodeidsType <em>Graph Nodeids Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.graphdrawing.graphml.xmlns.GraphNodeidsType
		 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getGraphNodeidsType()
		 * @generated
		 */
		EEnum GRAPH_NODEIDS_TYPE = eINSTANCE.getGraphNodeidsType();

		/**
		 * The meta object literal for the '{@link org.graphdrawing.graphml.xmlns.GraphOrderType <em>Graph Order Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.graphdrawing.graphml.xmlns.GraphOrderType
		 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getGraphOrderType()
		 * @generated
		 */
		EEnum GRAPH_ORDER_TYPE = eINSTANCE.getGraphOrderType();

		/**
		 * The meta object literal for the '{@link org.graphdrawing.graphml.xmlns.KeyForType <em>Key For Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.graphdrawing.graphml.xmlns.KeyForType
		 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getKeyForType()
		 * @generated
		 */
		EEnum KEY_FOR_TYPE = eINSTANCE.getKeyForType();

		/**
		 * The meta object literal for the '{@link org.graphdrawing.graphml.xmlns.KeyTypeType <em>Key Type Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.graphdrawing.graphml.xmlns.KeyTypeType
		 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getKeyTypeType()
		 * @generated
		 */
		EEnum KEY_TYPE_TYPE = eINSTANCE.getKeyTypeType();

		/**
		 * The meta object literal for the '<em>Endpoint Type Type Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.graphdrawing.graphml.xmlns.EndpointTypeType
		 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getEndpointTypeTypeObject()
		 * @generated
		 */
		EDataType ENDPOINT_TYPE_TYPE_OBJECT = eINSTANCE.getEndpointTypeTypeObject();

		/**
		 * The meta object literal for the '<em>Graph Edgedefault Type Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.graphdrawing.graphml.xmlns.GraphEdgedefaultType
		 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getGraphEdgedefaultTypeObject()
		 * @generated
		 */
		EDataType GRAPH_EDGEDEFAULT_TYPE_OBJECT = eINSTANCE.getGraphEdgedefaultTypeObject();

		/**
		 * The meta object literal for the '<em>Graph Edgeids Type Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.graphdrawing.graphml.xmlns.GraphEdgeidsType
		 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getGraphEdgeidsTypeObject()
		 * @generated
		 */
		EDataType GRAPH_EDGEIDS_TYPE_OBJECT = eINSTANCE.getGraphEdgeidsTypeObject();

		/**
		 * The meta object literal for the '<em>Graph Edges Type</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.math.BigInteger
		 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getGraphEdgesType()
		 * @generated
		 */
		EDataType GRAPH_EDGES_TYPE = eINSTANCE.getGraphEdgesType();

		/**
		 * The meta object literal for the '<em>Graph Maxindegree Type</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.math.BigInteger
		 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getGraphMaxindegreeType()
		 * @generated
		 */
		EDataType GRAPH_MAXINDEGREE_TYPE = eINSTANCE.getGraphMaxindegreeType();

		/**
		 * The meta object literal for the '<em>Graph Maxoutdegree Type</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.math.BigInteger
		 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getGraphMaxoutdegreeType()
		 * @generated
		 */
		EDataType GRAPH_MAXOUTDEGREE_TYPE = eINSTANCE.getGraphMaxoutdegreeType();

		/**
		 * The meta object literal for the '<em>Graph Nodeids Type Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.graphdrawing.graphml.xmlns.GraphNodeidsType
		 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getGraphNodeidsTypeObject()
		 * @generated
		 */
		EDataType GRAPH_NODEIDS_TYPE_OBJECT = eINSTANCE.getGraphNodeidsTypeObject();

		/**
		 * The meta object literal for the '<em>Graph Nodes Type</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.math.BigInteger
		 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getGraphNodesType()
		 * @generated
		 */
		EDataType GRAPH_NODES_TYPE = eINSTANCE.getGraphNodesType();

		/**
		 * The meta object literal for the '<em>Graph Order Type Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.graphdrawing.graphml.xmlns.GraphOrderType
		 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getGraphOrderTypeObject()
		 * @generated
		 */
		EDataType GRAPH_ORDER_TYPE_OBJECT = eINSTANCE.getGraphOrderTypeObject();

		/**
		 * The meta object literal for the '<em>Key For Type Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.graphdrawing.graphml.xmlns.KeyForType
		 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getKeyForTypeObject()
		 * @generated
		 */
		EDataType KEY_FOR_TYPE_OBJECT = eINSTANCE.getKeyForTypeObject();

		/**
		 * The meta object literal for the '<em>Key Name Type</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.String
		 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getKeyNameType()
		 * @generated
		 */
		EDataType KEY_NAME_TYPE = eINSTANCE.getKeyNameType();

		/**
		 * The meta object literal for the '<em>Key Type Type Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.graphdrawing.graphml.xmlns.KeyTypeType
		 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getKeyTypeTypeObject()
		 * @generated
		 */
		EDataType KEY_TYPE_TYPE_OBJECT = eINSTANCE.getKeyTypeTypeObject();

		/**
		 * The meta object literal for the '<em>Node Indegree Type</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.math.BigInteger
		 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getNodeIndegreeType()
		 * @generated
		 */
		EDataType NODE_INDEGREE_TYPE = eINSTANCE.getNodeIndegreeType();

		/**
		 * The meta object literal for the '<em>Node Outdegree Type</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.math.BigInteger
		 * @see org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl#getNodeOutdegreeType()
		 * @generated
		 */
		EDataType NODE_OUTDEGREE_TYPE = eINSTANCE.getNodeOutdegreeType();

	}

} //XmlnsPackage
