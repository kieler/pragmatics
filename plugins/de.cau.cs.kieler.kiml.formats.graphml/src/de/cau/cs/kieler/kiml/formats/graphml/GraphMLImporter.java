/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2011 by
 * + Kiel University
 *     + Department of Computer Science
 *         + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.formats.graphml;

import java.util.List;
import java.util.Map;

import org.eclipse.elk.core.data.LayoutMetaDataService;
import org.eclipse.elk.core.data.LayoutOptionData;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.options.PortConstraints;
import org.eclipse.elk.core.util.Pair;
import org.eclipse.elk.graph.ElkBendPoint;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkEdgeSection;
import org.eclipse.elk.graph.ElkGraphElement;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.ElkPort;
import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.elk.graph.properties.Property;
import org.eclipse.elk.graph.util.ElkGraphUtil;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.graphdrawing.graphml.DataType;
import org.graphdrawing.graphml.DocumentRoot;
import org.graphdrawing.graphml.EdgeType;
import org.graphdrawing.graphml.EndpointType;
import org.graphdrawing.graphml.GraphMLFactory;
import org.graphdrawing.graphml.GraphType;
import org.graphdrawing.graphml.GraphmlType;
import org.graphdrawing.graphml.HyperedgeType;
import org.graphdrawing.graphml.KeyForType;
import org.graphdrawing.graphml.KeyType;
import org.graphdrawing.graphml.KeyTypeType;
import org.graphdrawing.graphml.NodeType;
import org.graphdrawing.graphml.PortType;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import de.cau.cs.kieler.kiml.formats.IGraphTransformer;
import de.cau.cs.kieler.kiml.formats.TransformationData;

/**
 * A transformer for GraphML.
 *
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public class GraphMLImporter implements IGraphTransformer<DocumentRoot, ElkNode> {
    
    /** the data key used for GraphML positions. */
    public static final String POSITION_KEY = "position";
    /** the data key used for GraphML edge routing. */
    public static final String ROUTING_KEY = "points";
    
    /** map of GraphML node identifiers to KNodes. */
    private static final IProperty<Map<String, ElkNode>> NODE_ID_MAP
            = new Property<>("graphmlImporter.nodeIdMap");
    /** map of GraphML port identifiers to KPorts. */
    private static final IProperty<Map<Pair<ElkNode, String>, ElkPort>> PORT_ID_MAP
            = new Property<>("graphmlImporter.portIdMap");
    /** GraphML node attached to each new KNode. */
    private static final IProperty<NodeType> PROP_NODE
            = new Property<>("graphmlImporter.node");
    /** GraphML edge attached to each new KEdge. */
    private static final IProperty<EdgeType> PROP_EDGE
            = new Property<>("graphmlImporter.edge");
    
    /**
     * {@inheritDoc}
     */
    public void transform(final TransformationData<DocumentRoot, ElkNode> transData) {
        GraphmlType graphml = transData.getSourceGraph().getGraphml();
        for (GraphType graph : graphml.getGraph()) {
            ElkNode parent = ElkGraphUtil.createGraph();
            Map<String, ElkNode> nodeIdMap = Maps.newHashMap();
            transData.setProperty(NODE_ID_MAP, nodeIdMap);
            Map<Pair<ElkNode, String>, ElkPort> portIdMap = Maps.newHashMap();
            transData.setProperty(PORT_ID_MAP, portIdMap);
            transformGraph(graph, parent, transData);
            transData.getTargetGraphs().add(parent);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void transferLayout(final TransformationData<DocumentRoot, ElkNode> transData) {
        GraphmlType graphml = transData.getSourceGraph().getGraphml();
        // make sure there is a data key definition for position and routing
        KeyType positionKey = null, routingKey = null;
        for (KeyType key : graphml.getKey()) {
            if (POSITION_KEY.equals(key.getId())) {
                positionKey = key;
            } else if (ROUTING_KEY.equals(key.getId())) {
                routingKey = key;
            }
        }
        
        if (positionKey == null) {
            positionKey = GraphMLFactory.eINSTANCE.createKeyType();
            positionKey.setId(POSITION_KEY);
            graphml.getKey().add(positionKey);
        }
        positionKey.setAttrName(POSITION_KEY);
        positionKey.setAttrType(KeyTypeType.STRING);
        positionKey.setFor(KeyForType.NODE);
        
        if (routingKey == null) {
            routingKey = GraphMLFactory.eINSTANCE.createKeyType();
            routingKey.setId(ROUTING_KEY);
            graphml.getKey().add(routingKey);
        }
        routingKey.setAttrName(ROUTING_KEY);
        routingKey.setAttrType(KeyTypeType.STRING);
        routingKey.setFor(KeyForType.EDGE);
        
        for (ElkNode layoutNode : transData.getTargetGraphs()) {
            applyLayout(layoutNode);
        }
    }
    
    //---------- Transformation GraphML to KGraph ----------//  
        
    /**
     * Transform the contents of a GraphML graph or subgraph into a KNode.
     * 
     * @param graph a GraphML graph
     * @param parent the corresponding KNode
     */
    private void transformGraph(final GraphType graph, final ElkNode parent,
            final TransformationData<DocumentRoot, ElkNode> transData) {
        
        // transform layout options
        for (DataType data : graph.getData()) {
            setOption(parent, data.getKey(), getValue(data));
        }
        
        // transform nodes
        for (NodeType node : graph.getNode()) {
            ElkNode elknode = transformNode(node.getId(), parent, transData);
            elknode.setProperty(PROP_NODE, node);
            for (DataType data : node.getData()) {
                setOption(elknode, data.getKey(), getValue(data));
            }
            // transform ports
            for (PortType port : node.getPort()) {
                ElkPort elkport = transformPort(port.getName(), elknode, transData);
                for (DataType data : port.getData()) {
                    setOption(elkport, data.getKey(), getValue(data));
                }
            }
            // transform subgraph
            if (node.getGraph() != null) {
                transformGraph(node.getGraph(), elknode, transData);
            }
        }
        
        // transform edges
        for (EdgeType edge : graph.getEdge()) {
            ElkNode sourceNode = transformNode(edge.getSource(), parent, transData);
            ElkNode targetNode = transformNode(edge.getTarget(), parent, transData);
            
            ElkPort sourcePort = null;
            if (edge.getSourceport() != null) {
                sourcePort = transformPort(edge.getSourceport(), sourceNode, transData);
            }
            
            ElkPort targetPort = null;
            if (edge.getTargetport() != null) {
                targetPort = transformPort(edge.getTargetport(), targetNode, transData);
            }
            
            ElkEdge elkedge = ElkGraphUtil.createSimpleEdge(
                    sourcePort != null ? sourcePort : sourceNode,
                    targetPort != null ? targetPort : targetNode);
            elkedge.setProperty(PROP_EDGE, edge);
            
            for (DataType data : edge.getData()) {
                setOption(elkedge, data.getKey(), getValue(data));
            }
        }
        
        // transform hyperedges
        for (HyperedgeType hyperedge : graph.getHyperedge()) {
            // MIGRATE At some point we may want to actually create hyperedges here
            ElkNode hypernode = ElkGraphUtil.createNode(parent);
            hypernode.setProperty(CoreOptions.HYPERNODE, true);
            
            for (EndpointType endpoint : hyperedge.getEndpoint()) {
                ElkNode epnode = transformNode(endpoint.getNode(), parent, transData);
                ElkPort epport = null;
                if (endpoint.getPort() != null) {
                    epport = transformPort(endpoint.getPort(), epnode, transData);
                }
                
                ElkGraphUtil.createSimpleEdge(epport != null ? epport : epnode, hypernode);
            }
        }
    }
    
    /**
     * Transforms a single node, if not already done before.
     * 
     * @param nodeId a node identifier
     * @param parent the parent where the new KNode is stored
     * @return a KNode instance
     */
    private ElkNode transformNode(final String nodeId, final ElkNode parent,
            final TransformationData<DocumentRoot, ElkNode> transData) {
        
        Map<String, ElkNode> nodeIdMap = transData.getProperty(NODE_ID_MAP);
        ElkNode elknode = nodeIdMap.get(nodeId);
        if (elknode == null) {
            elknode = ElkGraphUtil.createNode(parent);
            elknode.setProperty(CoreOptions.PORT_CONSTRAINTS, PortConstraints.FREE);
            if (nodeId != null) {
                nodeIdMap.put(nodeId, elknode);
            }
        }
        return elknode;
    }
    
    /**
     * Transforms a single port, if not already done before.
     * 
     * @param portId a port identifier
     * @param node the node to which the new KPort belongs
     * @return a KPort instance
     */
    private ElkPort transformPort(final String portId, final ElkNode node,
            final TransformationData<DocumentRoot, ElkNode> transData) {
        
        Map<Pair<ElkNode, String>, ElkPort> portIdMap = transData.getProperty(PORT_ID_MAP);
        Pair<ElkNode, String> key = new Pair<>(node, portId);
        ElkPort elkport = portIdMap.get(key);
        if (elkport == null) {
            elkport = ElkGraphUtil.createPort(node);
            if (portId != null) {
                portIdMap.put(key, elkport);
            }
        }
        return elkport;
    }
    
    
    /*---------- Layout Transfer KGraph to GraphML ----------*/
    
    /**
     * Apply layout for the given parent node and all contained subgraphs.
     * 
     * @param parentNode a parent node
     */
    private void applyLayout(final ElkNode parentNode) {
        for (ElkNode elknode : parentNode.getChildren()) {
            NodeType graphmlNode = elknode.getProperty(PROP_NODE);
            if (graphmlNode != null) {
                // apply node layout
                DataType posData = null;
                for (DataType data : graphmlNode.getData()) {
                    if (POSITION_KEY.equals(data.getKey())) {
                        posData = data;
                        break;
                    }
                }
                if (posData == null) {
                    posData = GraphMLFactory.eINSTANCE.createDataType();
                    posData.setKey(POSITION_KEY);
                    graphmlNode.getData().add(posData);
                }
                setValue(posData, elknode.getX() + "," + elknode.getY());
            }
            
            for (ElkEdge elkedge : ElkGraphUtil.allOutgoingEdges(elknode)) {
                EdgeType graphmlEdge = elkedge.getProperty(PROP_EDGE);
                if (graphmlEdge != null && !elkedge.getSections().isEmpty()) {
                    // apply edge layout
                    DataType routeData = null;
                    for (DataType data : graphmlEdge.getData()) {
                        if (ROUTING_KEY.equals(data.getKey())) {
                            routeData = data;
                            break;
                        }
                    }
                    
                    if (routeData == null) {
                        routeData = GraphMLFactory.eINSTANCE.createDataType();
                        routeData.setKey(ROUTING_KEY);
                        graphmlEdge.getData().add(routeData);
                    }
                    
                    ElkEdgeSection edgeSection = elkedge.getSections().get(0);
                    
                    StringBuilder routeBuilder = new StringBuilder();
                    routeBuilder.append(edgeSection.getStartX()
                            + "," + edgeSection.getStartY());
                    for (ElkBendPoint point : edgeSection.getBendPoints()) {
                        routeBuilder.append(" " + point.getX() + "," + point.getY());
                    }
                    routeBuilder.append(" " + edgeSection.getEndX()
                            + "," + edgeSection.getEndY());
                    setValue(routeData, routeBuilder.toString());
                }
            }
            
            // apply layout for child nodes
            if (!elknode.getChildren().isEmpty()) {
                applyLayout(elknode);
            }
        }
    }
    
    /**
     * Retrieve the text value of a data instance.
     * 
     * @param data a data object
     * @return the contained text
     */
    private static String getValue(final DataType data) {
        List<?> list = (List<?>) data.getMixed().get(
                XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__TEXT, false);
        if (list.size() > 0) {
            return (String) list.get(0);
        }
        return null;
    }
    
    /**
     * Set the text value of a data instance.
     * 
     * @param data a data object
     * @param value the new text
     */
    private static void setValue(final DataType data, final String value) {
        data.getMixed().set(XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__TEXT,
                Lists.newArrayList(value));
    }

    /**
     * Set a layout option using a serialized key / value pair.
     * 
     * @param graphElement the graph data instance to modify
     * @param id the layout option identifier
     * @param value the value for the layout option
     */
    private static void setOption(final ElkGraphElement graphElement, final String id, final String value) {
        LayoutOptionData optionData = LayoutMetaDataService.getInstance().getOptionData(id);
        if (optionData != null) {
            Object obj = optionData.parseValue(value);
            if (obj != null) {
                graphElement.setProperty(optionData, obj);
            }
        }
    }
}
