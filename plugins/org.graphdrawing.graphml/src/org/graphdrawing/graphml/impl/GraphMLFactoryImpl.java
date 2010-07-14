/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.graphdrawing.graphml.impl;

import java.math.BigInteger;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.emf.ecore.xml.type.XMLTypeFactory;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

import org.graphdrawing.graphml.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class GraphMLFactoryImpl extends EFactoryImpl implements GraphMLFactory {
    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static GraphMLFactory init() {
        try {
            GraphMLFactory theGraphMLFactory = (GraphMLFactory)EPackage.Registry.INSTANCE.getEFactory("http://graphml.graphdrawing.org/xmlns"); 
            if (theGraphMLFactory != null) {
                return theGraphMLFactory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new GraphMLFactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GraphMLFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
            case GraphMLPackage.DATA_EXTENSION_TYPE: return createDataExtensionType();
            case GraphMLPackage.DATA_TYPE: return createDataType();
            case GraphMLPackage.DEFAULT_TYPE: return createDefaultType();
            case GraphMLPackage.DOCUMENT_ROOT: return createDocumentRoot();
            case GraphMLPackage.EDGE_TYPE: return createEdgeType();
            case GraphMLPackage.ENDPOINT_TYPE: return createEndpointType();
            case GraphMLPackage.GRAPHML_TYPE: return createGraphmlType();
            case GraphMLPackage.GRAPH_TYPE: return createGraphType();
            case GraphMLPackage.HYPEREDGE_TYPE: return createHyperedgeType();
            case GraphMLPackage.KEY_TYPE: return createKeyType();
            case GraphMLPackage.LOCATOR_TYPE: return createLocatorType();
            case GraphMLPackage.NODE_TYPE: return createNodeType();
            case GraphMLPackage.PORT_TYPE: return createPortType();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object createFromString(EDataType eDataType, String initialValue) {
        switch (eDataType.getClassifierID()) {
            case GraphMLPackage.ENDPOINT_TYPE_TYPE:
                return createEndpointTypeTypeFromString(eDataType, initialValue);
            case GraphMLPackage.GRAPH_EDGEDEFAULT_TYPE:
                return createGraphEdgedefaultTypeFromString(eDataType, initialValue);
            case GraphMLPackage.GRAPH_EDGEIDS_TYPE:
                return createGraphEdgeidsTypeFromString(eDataType, initialValue);
            case GraphMLPackage.GRAPH_NODEIDS_TYPE:
                return createGraphNodeidsTypeFromString(eDataType, initialValue);
            case GraphMLPackage.GRAPH_ORDER_TYPE:
                return createGraphOrderTypeFromString(eDataType, initialValue);
            case GraphMLPackage.KEY_FOR_TYPE:
                return createKeyForTypeFromString(eDataType, initialValue);
            case GraphMLPackage.KEY_TYPE_TYPE:
                return createKeyTypeTypeFromString(eDataType, initialValue);
            case GraphMLPackage.ENDPOINT_TYPE_TYPE_OBJECT:
                return createEndpointTypeTypeObjectFromString(eDataType, initialValue);
            case GraphMLPackage.GRAPH_EDGEDEFAULT_TYPE_OBJECT:
                return createGraphEdgedefaultTypeObjectFromString(eDataType, initialValue);
            case GraphMLPackage.GRAPH_EDGEIDS_TYPE_OBJECT:
                return createGraphEdgeidsTypeObjectFromString(eDataType, initialValue);
            case GraphMLPackage.GRAPH_EDGES_TYPE:
                return createGraphEdgesTypeFromString(eDataType, initialValue);
            case GraphMLPackage.GRAPH_MAXINDEGREE_TYPE:
                return createGraphMaxindegreeTypeFromString(eDataType, initialValue);
            case GraphMLPackage.GRAPH_MAXOUTDEGREE_TYPE:
                return createGraphMaxoutdegreeTypeFromString(eDataType, initialValue);
            case GraphMLPackage.GRAPH_NODEIDS_TYPE_OBJECT:
                return createGraphNodeidsTypeObjectFromString(eDataType, initialValue);
            case GraphMLPackage.GRAPH_NODES_TYPE:
                return createGraphNodesTypeFromString(eDataType, initialValue);
            case GraphMLPackage.GRAPH_ORDER_TYPE_OBJECT:
                return createGraphOrderTypeObjectFromString(eDataType, initialValue);
            case GraphMLPackage.KEY_FOR_TYPE_OBJECT:
                return createKeyForTypeObjectFromString(eDataType, initialValue);
            case GraphMLPackage.KEY_NAME_TYPE:
                return createKeyNameTypeFromString(eDataType, initialValue);
            case GraphMLPackage.KEY_TYPE_TYPE_OBJECT:
                return createKeyTypeTypeObjectFromString(eDataType, initialValue);
            case GraphMLPackage.NODE_INDEGREE_TYPE:
                return createNodeIndegreeTypeFromString(eDataType, initialValue);
            case GraphMLPackage.NODE_OUTDEGREE_TYPE:
                return createNodeOutdegreeTypeFromString(eDataType, initialValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String convertToString(EDataType eDataType, Object instanceValue) {
        switch (eDataType.getClassifierID()) {
            case GraphMLPackage.ENDPOINT_TYPE_TYPE:
                return convertEndpointTypeTypeToString(eDataType, instanceValue);
            case GraphMLPackage.GRAPH_EDGEDEFAULT_TYPE:
                return convertGraphEdgedefaultTypeToString(eDataType, instanceValue);
            case GraphMLPackage.GRAPH_EDGEIDS_TYPE:
                return convertGraphEdgeidsTypeToString(eDataType, instanceValue);
            case GraphMLPackage.GRAPH_NODEIDS_TYPE:
                return convertGraphNodeidsTypeToString(eDataType, instanceValue);
            case GraphMLPackage.GRAPH_ORDER_TYPE:
                return convertGraphOrderTypeToString(eDataType, instanceValue);
            case GraphMLPackage.KEY_FOR_TYPE:
                return convertKeyForTypeToString(eDataType, instanceValue);
            case GraphMLPackage.KEY_TYPE_TYPE:
                return convertKeyTypeTypeToString(eDataType, instanceValue);
            case GraphMLPackage.ENDPOINT_TYPE_TYPE_OBJECT:
                return convertEndpointTypeTypeObjectToString(eDataType, instanceValue);
            case GraphMLPackage.GRAPH_EDGEDEFAULT_TYPE_OBJECT:
                return convertGraphEdgedefaultTypeObjectToString(eDataType, instanceValue);
            case GraphMLPackage.GRAPH_EDGEIDS_TYPE_OBJECT:
                return convertGraphEdgeidsTypeObjectToString(eDataType, instanceValue);
            case GraphMLPackage.GRAPH_EDGES_TYPE:
                return convertGraphEdgesTypeToString(eDataType, instanceValue);
            case GraphMLPackage.GRAPH_MAXINDEGREE_TYPE:
                return convertGraphMaxindegreeTypeToString(eDataType, instanceValue);
            case GraphMLPackage.GRAPH_MAXOUTDEGREE_TYPE:
                return convertGraphMaxoutdegreeTypeToString(eDataType, instanceValue);
            case GraphMLPackage.GRAPH_NODEIDS_TYPE_OBJECT:
                return convertGraphNodeidsTypeObjectToString(eDataType, instanceValue);
            case GraphMLPackage.GRAPH_NODES_TYPE:
                return convertGraphNodesTypeToString(eDataType, instanceValue);
            case GraphMLPackage.GRAPH_ORDER_TYPE_OBJECT:
                return convertGraphOrderTypeObjectToString(eDataType, instanceValue);
            case GraphMLPackage.KEY_FOR_TYPE_OBJECT:
                return convertKeyForTypeObjectToString(eDataType, instanceValue);
            case GraphMLPackage.KEY_NAME_TYPE:
                return convertKeyNameTypeToString(eDataType, instanceValue);
            case GraphMLPackage.KEY_TYPE_TYPE_OBJECT:
                return convertKeyTypeTypeObjectToString(eDataType, instanceValue);
            case GraphMLPackage.NODE_INDEGREE_TYPE:
                return convertNodeIndegreeTypeToString(eDataType, instanceValue);
            case GraphMLPackage.NODE_OUTDEGREE_TYPE:
                return convertNodeOutdegreeTypeToString(eDataType, instanceValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DataExtensionType createDataExtensionType() {
        DataExtensionTypeImpl dataExtensionType = new DataExtensionTypeImpl();
        return dataExtensionType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DataType createDataType() {
        DataTypeImpl dataType = new DataTypeImpl();
        return dataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DefaultType createDefaultType() {
        DefaultTypeImpl defaultType = new DefaultTypeImpl();
        return defaultType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DocumentRoot createDocumentRoot() {
        DocumentRootImpl documentRoot = new DocumentRootImpl();
        return documentRoot;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EdgeType createEdgeType() {
        EdgeTypeImpl edgeType = new EdgeTypeImpl();
        return edgeType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EndpointType createEndpointType() {
        EndpointTypeImpl endpointType = new EndpointTypeImpl();
        return endpointType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GraphmlType createGraphmlType() {
        GraphmlTypeImpl graphmlType = new GraphmlTypeImpl();
        return graphmlType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GraphType createGraphType() {
        GraphTypeImpl graphType = new GraphTypeImpl();
        return graphType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public HyperedgeType createHyperedgeType() {
        HyperedgeTypeImpl hyperedgeType = new HyperedgeTypeImpl();
        return hyperedgeType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KeyType createKeyType() {
        KeyTypeImpl keyType = new KeyTypeImpl();
        return keyType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LocatorType createLocatorType() {
        LocatorTypeImpl locatorType = new LocatorTypeImpl();
        return locatorType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NodeType createNodeType() {
        NodeTypeImpl nodeType = new NodeTypeImpl();
        return nodeType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PortType createPortType() {
        PortTypeImpl portType = new PortTypeImpl();
        return portType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EndpointTypeType createEndpointTypeTypeFromString(EDataType eDataType, String initialValue) {
        EndpointTypeType result = EndpointTypeType.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertEndpointTypeTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GraphEdgedefaultType createGraphEdgedefaultTypeFromString(EDataType eDataType, String initialValue) {
        GraphEdgedefaultType result = GraphEdgedefaultType.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertGraphEdgedefaultTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GraphEdgeidsType createGraphEdgeidsTypeFromString(EDataType eDataType, String initialValue) {
        GraphEdgeidsType result = GraphEdgeidsType.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertGraphEdgeidsTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GraphNodeidsType createGraphNodeidsTypeFromString(EDataType eDataType, String initialValue) {
        GraphNodeidsType result = GraphNodeidsType.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertGraphNodeidsTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GraphOrderType createGraphOrderTypeFromString(EDataType eDataType, String initialValue) {
        GraphOrderType result = GraphOrderType.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertGraphOrderTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KeyForType createKeyForTypeFromString(EDataType eDataType, String initialValue) {
        KeyForType result = KeyForType.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertKeyForTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KeyTypeType createKeyTypeTypeFromString(EDataType eDataType, String initialValue) {
        KeyTypeType result = KeyTypeType.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertKeyTypeTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EndpointTypeType createEndpointTypeTypeObjectFromString(EDataType eDataType, String initialValue) {
        return createEndpointTypeTypeFromString(GraphMLPackage.Literals.ENDPOINT_TYPE_TYPE, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertEndpointTypeTypeObjectToString(EDataType eDataType, Object instanceValue) {
        return convertEndpointTypeTypeToString(GraphMLPackage.Literals.ENDPOINT_TYPE_TYPE, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GraphEdgedefaultType createGraphEdgedefaultTypeObjectFromString(EDataType eDataType, String initialValue) {
        return createGraphEdgedefaultTypeFromString(GraphMLPackage.Literals.GRAPH_EDGEDEFAULT_TYPE, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertGraphEdgedefaultTypeObjectToString(EDataType eDataType, Object instanceValue) {
        return convertGraphEdgedefaultTypeToString(GraphMLPackage.Literals.GRAPH_EDGEDEFAULT_TYPE, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GraphEdgeidsType createGraphEdgeidsTypeObjectFromString(EDataType eDataType, String initialValue) {
        return createGraphEdgeidsTypeFromString(GraphMLPackage.Literals.GRAPH_EDGEIDS_TYPE, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertGraphEdgeidsTypeObjectToString(EDataType eDataType, Object instanceValue) {
        return convertGraphEdgeidsTypeToString(GraphMLPackage.Literals.GRAPH_EDGEIDS_TYPE, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BigInteger createGraphEdgesTypeFromString(EDataType eDataType, String initialValue) {
        return (BigInteger)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.NON_NEGATIVE_INTEGER, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertGraphEdgesTypeToString(EDataType eDataType, Object instanceValue) {
        return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.NON_NEGATIVE_INTEGER, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BigInteger createGraphMaxindegreeTypeFromString(EDataType eDataType, String initialValue) {
        return (BigInteger)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.NON_NEGATIVE_INTEGER, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertGraphMaxindegreeTypeToString(EDataType eDataType, Object instanceValue) {
        return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.NON_NEGATIVE_INTEGER, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BigInteger createGraphMaxoutdegreeTypeFromString(EDataType eDataType, String initialValue) {
        return (BigInteger)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.NON_NEGATIVE_INTEGER, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertGraphMaxoutdegreeTypeToString(EDataType eDataType, Object instanceValue) {
        return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.NON_NEGATIVE_INTEGER, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GraphNodeidsType createGraphNodeidsTypeObjectFromString(EDataType eDataType, String initialValue) {
        return createGraphNodeidsTypeFromString(GraphMLPackage.Literals.GRAPH_NODEIDS_TYPE, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertGraphNodeidsTypeObjectToString(EDataType eDataType, Object instanceValue) {
        return convertGraphNodeidsTypeToString(GraphMLPackage.Literals.GRAPH_NODEIDS_TYPE, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BigInteger createGraphNodesTypeFromString(EDataType eDataType, String initialValue) {
        return (BigInteger)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.NON_NEGATIVE_INTEGER, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertGraphNodesTypeToString(EDataType eDataType, Object instanceValue) {
        return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.NON_NEGATIVE_INTEGER, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GraphOrderType createGraphOrderTypeObjectFromString(EDataType eDataType, String initialValue) {
        return createGraphOrderTypeFromString(GraphMLPackage.Literals.GRAPH_ORDER_TYPE, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertGraphOrderTypeObjectToString(EDataType eDataType, Object instanceValue) {
        return convertGraphOrderTypeToString(GraphMLPackage.Literals.GRAPH_ORDER_TYPE, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KeyForType createKeyForTypeObjectFromString(EDataType eDataType, String initialValue) {
        return createKeyForTypeFromString(GraphMLPackage.Literals.KEY_FOR_TYPE, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertKeyForTypeObjectToString(EDataType eDataType, Object instanceValue) {
        return convertKeyForTypeToString(GraphMLPackage.Literals.KEY_FOR_TYPE, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String createKeyNameTypeFromString(EDataType eDataType, String initialValue) {
        return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.NMTOKEN, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertKeyNameTypeToString(EDataType eDataType, Object instanceValue) {
        return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.NMTOKEN, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KeyTypeType createKeyTypeTypeObjectFromString(EDataType eDataType, String initialValue) {
        return createKeyTypeTypeFromString(GraphMLPackage.Literals.KEY_TYPE_TYPE, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertKeyTypeTypeObjectToString(EDataType eDataType, Object instanceValue) {
        return convertKeyTypeTypeToString(GraphMLPackage.Literals.KEY_TYPE_TYPE, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BigInteger createNodeIndegreeTypeFromString(EDataType eDataType, String initialValue) {
        return (BigInteger)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.NON_NEGATIVE_INTEGER, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertNodeIndegreeTypeToString(EDataType eDataType, Object instanceValue) {
        return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.NON_NEGATIVE_INTEGER, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BigInteger createNodeOutdegreeTypeFromString(EDataType eDataType, String initialValue) {
        return (BigInteger)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.NON_NEGATIVE_INTEGER, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertNodeOutdegreeTypeToString(EDataType eDataType, Object instanceValue) {
        return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.NON_NEGATIVE_INTEGER, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GraphMLPackage getGraphMLPackage() {
        return (GraphMLPackage)getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static GraphMLPackage getPackage() {
        return GraphMLPackage.eINSTANCE;
    }

} //GraphMLFactoryImpl
