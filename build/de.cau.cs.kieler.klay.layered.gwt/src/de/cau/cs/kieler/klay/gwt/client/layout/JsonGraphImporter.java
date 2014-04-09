/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.gwt.client.layout;

import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.math.KVectorChain;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.options.Direction;
import de.cau.cs.kieler.kiml.options.EdgeLabelPlacement;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.kiml.options.PortLabelPlacement;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.kiml.options.SizeOptions;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LGraphElement;
import de.cau.cs.kieler.klay.layered.graph.LGraphElement.HashCodeCounter;
import de.cau.cs.kieler.klay.layered.graph.LGraphUtil;
import de.cau.cs.kieler.klay.layered.graph.LInsets;
import de.cau.cs.kieler.klay.layered.graph.LLabel;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.LShape;
import de.cau.cs.kieler.klay.layered.graphimport.IGraphImporter;
import de.cau.cs.kieler.klay.layered.p3order.CrossingMinimizationStrategy;
import de.cau.cs.kieler.klay.layered.properties.GraphProperties;
import de.cau.cs.kieler.klay.layered.properties.InternalProperties;
import de.cau.cs.kieler.klay.layered.properties.PortType;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * Transforms our JSON Graph format into an LGraph. The LGraph will contain hierarchy, i.e
 * {@link Properties#CHILD_LGRAPH}s reference the child graphs of compound {@link LNode}s.
 * Furthermore the graph can contain cross hierarchy edges, where the source {@link LNode} of an
 * edge has a different parent than the target {@link LNode} of this edge.
 * 
 * @author uru
 */
public class JsonGraphImporter implements IGraphImporter<JSONObject> {

    /*
     * External Properties
     */
    /**
     * When writing coordinates back to the json object, int values are written
     * instead of double values. No rounding is involved, the decimal places 
     * are just cut off.
     */
    public static final IProperty<Boolean> INT_COORDINATES = new Property<Boolean>(
            "intCoordinates", false);

    /*
     * Internal Properties
     */
    private static final IProperty<JSONObject> JSON_OBJECT = new Property<JSONObject>("jsonObject");
    
    private Map<String, LNode> nodeIdMap = Maps.newHashMap();
    private Map<String, LEdge> edgeIdMap = Maps.newHashMap();
    private Map<String, LPort> portIdMap = Maps.newHashMap();

    private Map<LNode, JSONObject> nodeJsonMap = Maps.newHashMap();
    private Map<LEdge, JSONObject> edgeJsonMap = Maps.newHashMap();
    private Map<LPort, JSONObject> portJsonMap = Maps.newHashMap();
    private Map<LLabel, JSONObject> labelJsonMap = Maps.newHashMap();

    // Remark: the following maps can only be used during the transformation
    // process as new LGraphs may be created during the layout process,
    // hence, after layout the mappings are invalid.
    
    /** Holds for each compound node the {@link LGraph} created for a json node. */
    private Map<JSONObject, LGraph> jsonLGraphMap = Maps.newHashMap();

    /**
     * We will create multiple {@link LGraph} instances, hence we have to employ our own counter to
     * ensure unique hash codes.
     */
    private HashCodeCounter hashCodeCounter = new HashCodeCounter();
    
    /** Global options being applied to every compound graph. */
    private JSONObject globalOptions = null;
    
    /** Whether to export coordinates as integers. */
    private boolean exportIntegerCoordinates = false;
    
    private Boolean layoutHierarchy = null;

    private void reset() {
        nodeIdMap.clear();
        edgeIdMap.clear();
        portIdMap.clear();

        nodeJsonMap.clear();
        edgeJsonMap.clear();
        portJsonMap.clear();
        labelJsonMap.clear();
        
        jsonLGraphMap.clear();
        
        hashCodeCounter = new HashCodeCounter();
    }

    /*---------------------------------------------------------------------------------
     *                          Transform JSON to LGraph
     */

    /**
     * {@inheritDoc}
     */
    public LGraph importGraph(final JSONObject json) {
        
        reset();
        
        // retrieve some klay.js specific options
        if (globalOptions != null) {
            JSONValue val = globalOptions.get(INT_COORDINATES.getId());
            if (val != null && val.isBoolean() != null) {
                exportIntegerCoordinates = val.isBoolean().booleanValue();
            } 
        }

        // first we transform all nodes of all hierarchy levels
        // this includes existing ports and labels
        LGraph rootGraph = transformNodes(json, null);

        // then transform all edges
        // we have to do this after the node transformations
        // as cross hierarchy edges are possible and we have to assure that
        // all source and target nodes already exist
        // for port-less edges, new ports will be created
        transformEdges(json, rootGraph);

        return rootGraph;
    }

    /**
     * Sets the json object containing global layout options. The specified options will be added to
     * every {@link LGraph} in the hierarchy during the import.
     * 
     * @param globalOptions
     *            an {@link JSONObject} containing the desired global layout options.
     */
    public void setGlobalOptions(final JSONObject globalOptions) {
        this.globalOptions = globalOptions;
    }

    /**
     * Transforms the passed node in JSON format into an 'flat' {@link LGraph}. This is, for each
     * json node in the 'children' array, an {@link LNode} will be added to the newly created
     * {@link LGraph}. To support hierarchy, this method is called recursively after all json
     * children have been transformed to {@link LNode}s with the respective {@link LNode} as
     * {@code parentNode}. The {@link LGraph} that is created for the child node will be added to
     * the {@code parentNode} via the {@link Properties#CHILD_LGRAPH} property.
     * 
     * @param jparent
     *            a node in JSON format
     * @param parentNode
     *            either {@code null} for the root level, or the corresponding {@link LNode} for the
     *            {@code jparent}
     * @return the {@link LGraph} created for the current hierarchy level.
     */
    private LGraph transformNodes(final JSONObject jparent, final LNode parentNode) {

        // create a new graph instance
        LGraph graph = new LGraph(hashCodeCounter);
        graph.setProperty(JSON_OBJECT, jparent);
        jsonLGraphMap.put(jparent, graph);
        
        graph.setProperty(InternalProperties.PARENT_LNODE, parentNode);
        
        // global layout options are applied first, hence possibly overwritten
        if (globalOptions != null) {
            // do not override more specific options set on single graph elements
            transformPropertiesObj(globalOptions, graph, false);
        }
        
        // properties on a certain node serve as layout options for this graph
        transformProperties(jparent, graph);
        
        // copy the insets to the layered graph
        if (jparent.containsKey("padding")) {
            LInsets linsets = graph.getInsets();
            JSONObject padding = (JSONObject) jparent.get("padding");
            JSONNumber left = (JSONNumber) padding.get("left");
            if (left != null) {
                linsets.left = left.doubleValue();
            }
            JSONNumber top = (JSONNumber) padding.get("top");
            if (top != null) {
                linsets.top = top.doubleValue();
            }
            JSONNumber right = (JSONNumber) padding.get("right");
            if (right != null) {
                linsets.right = right.doubleValue();
            }
            JSONNumber bottom = (JSONNumber) padding.get("bottom");
            if (bottom != null) {
                linsets.bottom = bottom.doubleValue();
            }
            System.out.println("set insets " + parentNode + " " + linsets + " " + jparent);
        }

        // the graph properties discovered during the transformations
        EnumSet<GraphProperties>  graphProperties = EnumSet.noneOf(GraphProperties.class);
        graph.setProperty(InternalProperties.GRAPH_PROPERTIES, graphProperties);
        
        // for the top level node check if we wanna layout hierarchy
        if (layoutHierarchy == null) {
            layoutHierarchy = graph.getProperty(LayoutOptions.LAYOUT_HIERARCHY);
        }

        // try to get the 'children' array from the json node
        if (jparent.containsKey("children")) {
            JSONValue val = jparent.get("children");
            if (val.isArray() == null) {
                throw new UnsupportedJsonGraphException(
                        "The 'children' property of nodes must be an array.", val, jparent);
            }
            JSONArray children = val.isArray();

            // if there actually are children specified, transform them too
            if (children.size() > 0) {
                
                // as there are children, this node is a compound node and will have a nested graph
                if (parentNode != null) {
                    // set this LGraph as child of the parent LNode
                    parentNode.setProperty(InternalProperties.NESTED_LGRAPH, graph);
                }

                // transform all child nodes
                LNode[] childNodes = new LNode[children.size()];
                for (int i = 0; i < children.size(); ++i) {
                    JSONValue childVal = children.get(i);
                    if (childVal.isObject() == null) {
                        throw new UnsupportedJsonGraphException(
                                "A 'children' array contains a non-object node element.", childVal,
                                jparent);
                    }
                    LNode child = transformNode(childVal.isObject(), graph);
                    childNodes[i] = child;
                }

                // after this graph layer has been processed, transform the children of this layer's
                // children
                for (int i = 0; i < children.size(); ++i) {
                    // we don't have to check the json here, was checked during previous for loop
                    JSONObject jChild = children.get(i).isObject();
                    LNode childNode = childNodes[i];
                    // ignore the child's contents if NO_LAYOUT option is set
                    if (jChild.containsKey("children")
                            && !childNode.getProperty(LayoutOptions.NO_LAYOUT)) {
                        transformNodes(jChild, childNode);
                    }
                }
            }
        }

        return graph;
    }
    
    /**
     * Transforms a single node in JSON format to an {@link LNode} and adds it to 
     * the layerless nodes of the {@link LGraph}. 
     *
     * This includes: dimensions, properties, ports, labels 
     */
    private LNode transformNode(final JSONObject jNode, final LGraph graph) {

        checkForId(jNode);
        Set<GraphProperties> graphProperties = 
                graph.getProperty(InternalProperties.GRAPH_PROPERTIES);

        LNode node = new LNode(graph);
        node.setProperty(InternalProperties.ORIGIN, jNode);
        graph.getLayerlessNodes().add(node);

        // id and register
        JSONString id = (JSONString) jNode.get("id");
        nodeIdMap.put(id.stringValue(), node);
        nodeJsonMap.put(node, jNode);

        // dimensions
        transformDimensions(jNode, node);

        // properties
        transformProperties(jNode, node);

        // ports
        if (jNode.containsKey("ports")) {
            JSONValue val = jNode.get("ports");
            if (val.isArray() == null) {
                throw new UnsupportedJsonGraphException(
                        "The 'ports' property of the node must be an array.", val, jNode);
            }
            JSONArray ports = val.isArray();

            for (int i = 0; i < ports.size(); ++i) {
                if (ports.get(i) instanceof JSONObject) {
                    transformPort((JSONObject) ports.get(i), node, graph);
                }
            }
        }

        // labels
        transformLabels(jNode, node, graph);

        // check if the node is a compound node in the original graph
        if (jNode.containsKey("children") && jNode.get("children").isArray().size() > 0) {
            node.setProperty(InternalProperties.COMPOUND_NODE, true);
        }

        // port constraints and sides cannot be undefined
        PortConstraints portConstraints = node.getProperty(LayoutOptions.PORT_CONSTRAINTS);
        if (portConstraints == PortConstraints.UNDEFINED) {
            portConstraints = PortConstraints.FREE;
        } else if (portConstraints != PortConstraints.FREE) {
            // if the port constraints are not free, set the appropriate graph property
            graphProperties.add(GraphProperties.NON_FREE_PORTS);
        }

        if (node.getProperty(LayoutOptions.COMMENT_BOX)) {
            graphProperties.add(GraphProperties.COMMENTS);
        }

        // if we have a hypernode without ports, create a default input and output port
        if (node.getProperty(LayoutOptions.HYPERNODE)) {
            graphProperties.add(GraphProperties.HYPERNODES);
            graphProperties.add(GraphProperties.HYPEREDGES);
            node.setProperty(LayoutOptions.PORT_CONSTRAINTS, PortConstraints.FREE);
        }
        
        return node;
    }

    /**
     * Transforms a single port in JSON format to an {@link LPort} and adds it to the
     * passed {@link LNode} {@code node}.
     * 
     * This includes: dimensions, properties, labels
     */
    private void transformPort(final JSONObject jPort, final LNode node, final LGraph graph) {

        checkForId(jPort);
        Set<GraphProperties> graphProperties = 
                graph.getProperty(InternalProperties.GRAPH_PROPERTIES);

        // should we include this port into the layout?
        String noLayoutId = LayoutOptions.NO_LAYOUT.getId();
        if (jPort.containsKey(noLayoutId)
                && jPort.get(noLayoutId).isBoolean().booleanValue()) {
            return;
        }

        LPort port = new LPort(graph);
        port.setProperty(InternalProperties.ORIGIN, jPort);
        port.setNode(node);

        // id and register
        JSONString id = (JSONString) jPort.get("id");
        portIdMap.put(id.stringValue(), port);
        portJsonMap.put(port, jPort);

        // dimensions
        transformDimensions(jPort, port);

        // properties
        transformProperties(jPort, port);

        // labels
        transformLabels(jPort, port, graph);

        // collect information on the structure of the graph

        port.setSide(port.getProperty(LayoutOptions.PORT_SIDE));

        // port constraints and sides cannot be undefined
        PortConstraints portConstraints = node.getProperty(LayoutOptions.PORT_CONSTRAINTS);
        if (portConstraints == PortConstraints.UNDEFINED) {
            portConstraints = PortConstraints.FREE;
        }
        // Transform the external ports
        Direction direction = graph.getProperty(LayoutOptions.DIRECTION);
        if (direction == Direction.UNDEFINED) {
            // The default direction is right
            direction = Direction.RIGHT;
        }
        // initialize the port's side, offset, and anchor point
        LGraphUtil.initializePort(port, portConstraints, direction,
                port.getProperty(LayoutOptions.PORT_ANCHOR));

        switch (direction) {
        case LEFT:
        case RIGHT:
            if (port.getSide() == PortSide.NORTH || port.getSide() == PortSide.SOUTH) {
                graphProperties.add(GraphProperties.NORTH_SOUTH_PORTS);
            }
            break;
        case UP:
        case DOWN:
            if (port.getSide() == PortSide.EAST || port.getSide() == PortSide.WEST) {
                graphProperties.add(GraphProperties.NORTH_SOUTH_PORTS);
            }
            break;
        default:
            // shouldn't happen
            break;
        }

    }

    /**
     * Transforms the labels of the json element.     
     */
    private void transformLabels(final JSONObject jElement, final LGraphElement element,
            final LGraph graph) {
        if (jElement.containsKey("labels")) {
            JSONValue val = jElement.get("labels");
            if (val.isArray() == null) {
                throw new UnsupportedJsonGraphException(
                        "The 'labels' property of a node must be an array.", val, jElement);
            }
            JSONArray labels = val.isArray();

            for (int i = 0; i < labels.size(); ++i) {
                if (labels.get(i) instanceof JSONObject) {
                    transformLabel((JSONObject) labels.get(i), element, graph);
                }
            }
        }
    }

    /**
     * Transforms a single label in json format to an {@link LLabel} and adds it to the passed
     * {@link LGraphElement}. The transformation includes the dimensions of the label.
     */
    private void transformLabel(final JSONObject jLabel, final LGraphElement ele, final LGraph graph) {

        // should we include this label into the layout process?
        String noLayoutId = LayoutOptions.NO_LAYOUT.getId();
        if (jLabel.containsKey(noLayoutId)
                && jLabel.get(noLayoutId).isBoolean().booleanValue()) {
            return;
        }

        // for edges also check if the edge label placement is UNDEFINED
        if (ele instanceof LEdge) {
            String edgeLabPlace = LayoutOptions.EDGE_LABEL_PLACEMENT.getId();
            if (jLabel.containsKey(edgeLabPlace)
                    && jLabel.get(edgeLabPlace).isString().stringValue()
                            .equals(EdgeLabelPlacement.UNDEFINED.name())) {
                return;
            }
        }

        // check for text property
        JSONValue val = jLabel.get("text");
        if (val == null) {
            throw new UnsupportedJsonGraphException("Labels must have a property 'text'.", val,
                    jLabel);
        } else if (val.isString() == null) {
            throw new UnsupportedJsonGraphException("A label's 'text' property must be a string.",
                    val, jLabel);
        }

        // create a new label
        String text = val.isString().stringValue();
        LLabel label = new LLabel(graph, text);
        label.setProperty(InternalProperties.ORIGIN, jLabel);
        labelJsonMap.put(label, jLabel);

        // properties
        transformProperties(jLabel, label);

        // add label to the graph element
        if (ele instanceof LNode) {
            ((LNode) ele).getLabels().add(label);
        } else if (ele instanceof LEdge) {
            ((LEdge) ele).getLabels().add(label);
        } else if (ele instanceof LPort) {
            ((LPort) ele).getLabels().add(label);
        }

        // additional handling for edge labels
        if (ele instanceof LEdge) {
            EdgeLabelPlacement labelPlacement =
                    label.getProperty(LayoutOptions.EDGE_LABEL_PLACEMENT);

            if (labelPlacement != EdgeLabelPlacement.UNDEFINED) {
                // dimensions of the label
                transformDimensions(jLabel, label);
                label.setProperty(LayoutOptions.EDGE_LABEL_PLACEMENT, labelPlacement);

                Set<GraphProperties> graphProperties =
                        graph.getProperty(InternalProperties.GRAPH_PROPERTIES);
                switch (labelPlacement) {
                case CENTER:
                    // Add annotation if graph contains labels which shall be placed
                    // in the middle of an edge
                    graphProperties.add(GraphProperties.CENTER_LABELS);
                    break;
                case HEAD:
                case TAIL:
                    // Add annotation if graph contains labels which shall be placed
                    // in the middle of an edge
                    graphProperties.add(GraphProperties.END_LABELS);
                default:
                    break;
                }
            }
        }
    }
    

    /**
     * Transforms all edges specified on this hierarchy level.
     * 
     * Remark: make sure the WHOLE hierarchy of nodes is already transformed
     * 
     * @param parent
     *            a compound node in json format.
     * @param graph
     *            the {@link LGraph} created for this compound node.
     */
    private void transformEdges(final JSONObject parent, final LGraph graph) {

        
        // then transform the edges (important that the nodes and ports are already known)
        if (parent.containsKey("edges")) {
            JSONValue val = parent.get("edges");
            if (val.isArray() == null) {
                throw new UnsupportedJsonGraphException(
                        "The 'edges' property of a node has to be an array.", val, parent);
            }
            JSONArray edges = val.isArray();

            for (int i = 0; i < edges.size(); ++i) {
                JSONValue edgeVal = edges.get(i);
                if (edgeVal.isObject() == null) {
                    throw new UnsupportedJsonGraphException(
                            "All elements of the 'edges' property must be objects.", edgeVal,
                            parent);
                }
                transformEdge(edgeVal.isObject(), graph);
            }
        }
        
        
        // continue with edges of the children
        if (parent.containsKey("children")) {
           // the json should be proper, otherwise 'transformNodes' already threw an exception  
           JSONArray children = parent.get("children").isArray();
           for (int i = 0; i < children.size(); ++i) {
               JSONObject child = children.get(i).isObject();
               LGraph childGraph = jsonLGraphMap.get(child);
               transformEdges(child, childGraph);
           }
        }
    }

    /**
     * Transforms a single edge in json format to an {@link LEdge}.
     * 
     * Finds the source and target {@link LNode}s, as well as the {@link LPort}s and 
     * connects the edge to these. In case no ports are specified, new ones will be created.
     * 
     * @param jEdge
     * @param graph
     */
    private void transformEdge(final JSONObject jEdge, final LGraph graph) {

        checkForId(jEdge);

        // should we include this edge into the layout?
        String noLayoutId = LayoutOptions.NO_LAYOUT.getId();
        if (jEdge.containsKey(noLayoutId)
                && jEdge.get(noLayoutId).isBoolean().booleanValue()) {
            return;
        }
        
        JSONValue jSourceNode = jEdge.get("source");
        JSONValue jSourcePort = jEdge.get("sourcePort");
        JSONValue jTargetNode = jEdge.get("target");
        JSONValue jTargetPort = jEdge.get("targetPort");

        // check for valid source
        if (jSourceNode == null) {
            throw new UnsupportedJsonGraphException("Edges must contain a 'source' property.",
                    jSourceNode, jEdge);
        } else if (jSourceNode.isString() == null) {
            throw new UnsupportedJsonGraphException(
                    "Invalid format of an edge's 'source' property. It must be a string.",
                    jSourceNode, jEdge);
        }

        // check for valid target
        if (jTargetNode == null) {
            throw new UnsupportedJsonGraphException("Edges must contain a 'target' property.",
                    jTargetNode, jEdge);
        } else if (jTargetNode.isString() == null) {
            throw new UnsupportedJsonGraphException(
                    "Invalid format of an edge's 'target' property. It must be a string.",
                    jTargetNode, jEdge);
        }

        // retrieve source and target
        LNode sourceNode = null;
        LPort sourcePort = null;
        LNode targetNode = null;
        LPort targetPort = null;
        
        try {
            // get the source node
            sourceNode = nodeIdMap.get(jSourceNode.isString().stringValue());
            if (jSourcePort != null && jSourcePort.isString() != null) {
                sourcePort = portIdMap.get(jSourcePort.isString().stringValue());
            }
    
            // get the target node
            targetNode = nodeIdMap.get(jTargetNode.isString().stringValue());
            if (jTargetPort != null && jTargetPort.isString() != null) {
                targetPort = portIdMap.get(jTargetPort.isString().stringValue());
            }
        } catch (NullPointerException npe) {
            throw new UnsupportedJsonGraphException("An edge's 'source', 'target', 'sourcePort', "
                    + "and 'targetPort' properties have to be strings.", jEdge);
        }
        
        // exclude edges that pass hierarchy bounds if layoutHierarchy is switched off
        if (!layoutHierarchy) {
            if (sourceNode == null || targetNode == null) {
                // an endpoint node has not been transformed
                return;
            } else if (sourceNode.getGraph() != targetNode.getGraph()) {
                // the edge's endpoint nodes have a different parent
                return;
            }
        }
        
        // create a layered edge
        LEdge edge = new LEdge(graph);
        edge.setProperty(InternalProperties.ORIGIN, jEdge);
        
        // id and register
        JSONString id = (JSONString) jEdge.get("id");
        edgeIdMap.put(id.stringValue(), edge);
        edgeJsonMap.put(edge, jEdge);
        
        // properties
        transformProperties(jEdge, edge);

        // labels
        transformLabels(jEdge, edge, graph);

        Set<GraphProperties> graphProperties = graph.getProperty(InternalProperties.GRAPH_PROPERTIES);

        if (sourceNode != null && targetNode != null) {
            // if we have a self-loop, set the appropriate graph property
            if (sourceNode == targetNode) {
                graphProperties.add(GraphProperties.SELF_LOOPS);
            }

            // create source and target ports if they do not exist yet
            if (sourcePort == null) {
                sourcePort =
                        LGraphUtil.createPort(sourceNode, new KVector(), PortType.OUTPUT, graph);
            } else if (sourcePort.getNode() != sourceNode) {
                throw new UnsupportedJsonGraphException("Inconsistent source port reference found.");
            }

            if (targetPort == null) {
                targetPort =
                        LGraphUtil.createPort(targetNode, new KVector(), PortType.INPUT, graph);
            } else if (targetPort.getNode() != targetNode) {
                throw new UnsupportedJsonGraphException("Inconsistent target port reference found.");
            }

            edge.setSource(sourcePort);
            edge.setTarget(targetPort);
        } else {
            throw new UnsupportedJsonGraphException("An edge's source or target "
                    + "node could not be resolved.", jEdge);
        }

        // find out if there are hyperedges, that is a set of edges connected to the same port
        if (Iterables.size(sourcePort.getConnectedEdges()) > 1
                || Iterables.size(targetPort.getConnectedEdges()) > 1) {
            graphProperties.add(GraphProperties.HYPEREDGES);
        }

        // copy the bend points of the edge if they are needed by anyone
        if (graph.getProperty(Properties.CROSS_MIN) == CrossingMinimizationStrategy.INTERACTIVE
                && !jEdge.containsKey("bendPoints")) {
            KVectorChain bendpoints = new KVectorChain();

            try {
                JSONArray jBends = jEdge.get("bendPoints").isArray();
                for (int i = 0; i < jBends.size(); ++i) {
                    JSONObject jVector = jBends.get(i).isObject();
                    KVector v =
                            new KVector(jVector.get("x").isNumber().doubleValue(), jVector.get("y")
                                    .isNumber().doubleValue());
                    bendpoints.add(v);
                }
                edge.setProperty(InternalProperties.ORIGINAL_BENDPOINTS, bendpoints);
            } catch (Exception e) {
                throw new UnsupportedJsonGraphException(
                        "Invalid format of an edges 'bendPoints' property.", jEdge);
            }
        }

        // clear junction points, since they are recomputed from scratch
        edge.setProperty(LayoutOptions.JUNCTION_POINTS, null);

    }

    /**
     * Transforms the dimensions of a json element, this being x,y coordinates and width and height.
     */
    private void transformDimensions(final JSONObject jsonEle, final LShape ele) {
        if (jsonEle.containsKey("x")) {
            JSONNumber x = (JSONNumber) jsonEle.get("x");
            ele.getPosition().x = x.doubleValue();
        }
        if (jsonEle.containsKey("y")) {
            JSONNumber y = (JSONNumber) jsonEle.get("y");
            ele.getPosition().y = y.doubleValue();
        }
        if (jsonEle.containsKey("width")) {
            JSONNumber width = (JSONNumber) jsonEle.get("width");
            ele.getSize().x = width.doubleValue();
        }
        if (jsonEle.containsKey("height")) {
            JSONNumber height = (JSONNumber) jsonEle.get("height");
            ele.getSize().y = height.doubleValue();
        }
        
    }
    
    /**
     * Transforms the properties of an element, using the {@link LayoutOptionResolver} for the 
     * id->type mapping. 
     */
    private void transformProperties(final JSONObject jsonEle, final LGraphElement ele) {
        if (jsonEle.containsKey("properties")) {
            JSONValue val = jsonEle.get("properties");
            if (val.isObject() == null) {
                throw new UnsupportedJsonGraphException(
                        "The 'properties' property of a graph element must be an object.", val, jsonEle);
            }
            transformPropertiesObj(val.isObject(), ele);
        }
    }
    
    /**
     * Adds all properties of the passed json object to the graph element. This method overrides any
     * existing properties. For the opposite case use
     * {@link #transformPropertiesObj(JSONObject, LGraphElement, boolean)}
     */
    private void transformPropertiesObj(final JSONObject properties, final LGraphElement ele) {
        transformPropertiesObj(properties, ele, true);
    }
    
    private void transformPropertiesObj(final JSONObject properties, final LGraphElement ele, 
            final boolean override) {
        if (properties != null) {
            for (String key : properties.keySet()) {
                JSONValue theVal = properties.get(key);
                // try to find the specified layout option and parse its value
                // Note that we do not override user specified properties in the graph
                LayoutOptionResolver.setOption(ele, key, theVal, override);
            }
        }
    }

    /*---------------------------------------------------------------------------------
     *                          Transfer the Layout back
     */

    /**
     * {@inheritDoc}
     */
    public void applyLayout(final LGraph layeredGraph) {
        
        // transfer the layout information back to the json objects
        // and positions and dimension of all other elements
        transferLayout(layeredGraph);
    }

    private void transferLayout(final LGraph parentGraph) {
        
        // now the child nodes
        KVector offset = parentGraph.getOffset();
        for (LNode n : parentGraph.getLayerlessNodes()) {
            JSONObject jNode = nodeJsonMap.get(n);
            
            if (jNode != null) {
                // it's a usual node
                transferLayout(n, jNode, offset);
    
                // ports
                for (LPort p : n.getPorts()) {
                    JSONObject jPort = portJsonMap.get(p);
                    if (jPort != null) {
                        // dummy ports for port-less edges are not contained in the map
                        transferLayout(p, jPort, new KVector());
                        setJsProperty(jPort, LayoutOptions.PORT_SIDE, new JSONString(p
                                .getSide().name()));
                    }
    
                    // labels
                    if (n.getProperty(LayoutOptions.PORT_LABEL_PLACEMENT) != PortLabelPlacement.FIXED) {
                        for (LLabel l : p.getLabels()) {
                            JSONObject jLabel = labelJsonMap.get(l);
                            transferLayout(l, jLabel, offset);
                        }
                    }
                }
    
                // labels
                if (!n.getProperty(LayoutOptions.NODE_LABEL_PLACEMENT).isEmpty()) {
                    for (LLabel l : n.getLabels()) {
                        JSONObject jLabel = labelJsonMap.get(l);
                        transferLayout(l, jLabel, offset);
                    }
                }
    
                // edges
                for (LEdge e : n.getOutgoingEdges()) {
                    JSONObject jEdge = edgeJsonMap.get(e);
                    transferLayout(e, jEdge, offset);
    
                    // labels
                    for (LLabel l : e.getLabels()) {
                        JSONObject jLabel = labelJsonMap.get(l);
                        transferLayout(l, jLabel, offset);
                    }
    
                }
            
            } else {
                // it's an external port dummy
                // TODO
            }
        }
        
        KVector actualGraphSize = parentGraph.getActualSize();
        // transfer to origin LNode 
        // this is necessary for processing higher graph levels properly
        LNode graphNode = parentGraph.getProperty(InternalProperties.PARENT_LNODE);
        if (graphNode != null) {
            // the root has no parent
            graphNode.getSize().x = actualGraphSize.x;
            graphNode.getSize().y = actualGraphSize.y;
        }
        
        // transfer to json
        JSONObject graphJson = parentGraph.getProperty(JSON_OBJECT);
        setJsNumber(graphJson, "width", actualGraphSize.x);
        setJsNumber(graphJson, "height", actualGraphSize.y);
        

        // Process nested subgraphs
        for (LNode n : parentGraph.getLayerlessNodes()) {
            LGraph childGraph = n.getProperty(InternalProperties.NESTED_LGRAPH);
            if (childGraph != null) {
                transferLayout(childGraph);
            }
        }
    }

    private void transferLayout(final LShape shape, final JSONObject json, final KVector parentOffset) {
        KVector offset = new KVector();

        // offset is only used for nodes
        if (shape instanceof LNode) {
            offset = parentOffset;
        }

        setJsNumber(json, "x", shape.getPosition().x + offset.x);
        setJsNumber(json, "y", shape.getPosition().y + offset.y);
        setJsNumber(json, "width", shape.getSize().x);
        setJsNumber(json, "height", shape.getSize().y);
        
        // padding
        if (shape instanceof LNode) {
            // set node insets, if requested
            if (shape.getProperty(LayoutOptions.SIZE_OPTIONS).contains(SizeOptions.COMPUTE_INSETS)) {
                LInsets insets = ((LNode) shape).getInsets();

                JSONValue paddingVal = json.get("padding");
                if (paddingVal == null) {
                    paddingVal = new JSONObject();
                    json.put("padding", paddingVal);
                }
                JSONObject padding = paddingVal.isObject();
                setJsNumber(padding, "left", insets.left);
                setJsNumber(padding, "top", insets.top);
                setJsNumber(padding, "right", insets.right);
                setJsNumber(padding, "bottom", insets.bottom);
            }
        }
    }

    private void transferLayout(final LEdge edge, final JSONObject json, final KVector offset) {
        checkForNonNull(json, "The origin of an edge could not be determined, this might "
                + "be due to an inconsistency within the internal element mappings.");
        
        KVector edgeOffset = offset;
        
        // Source Point
        KVector src;
        if (LGraphUtil.isDescendant(edge.getTarget().getNode(), edge.getSource().getNode())) {
            LPort sourcePort = edge.getSource();
            src = KVector.sum(sourcePort.getPosition(), sourcePort.getAnchor());
            LInsets sourceInsets = sourcePort.getNode().getInsets();
            src.translate(-sourceInsets.left, -sourceInsets.top);
            LGraph nestedGraph = sourcePort.getNode().getProperty(InternalProperties.NESTED_LGRAPH);
            if (nestedGraph != null) {
                edgeOffset = nestedGraph.getOffset();
            }
            src.sub(edgeOffset);
        } else {
            src = edge.getSource().getAbsoluteAnchor();
        }
        
        src.translate(offset.x, offset.y);
        JSONObject srcPnt = new JSONObject();
        setJsNumber(srcPnt, "x", src.x);
        setJsNumber(srcPnt, "y", src.y);
        json.put("sourcePoint", srcPnt);

        // Target Point
        KVector tgt = edge.getTarget().getAbsoluteAnchor();
        if (edge.getProperty(InternalProperties.TARGET_OFFSET) != null) {
            tgt.add(edge.getProperty(InternalProperties.TARGET_OFFSET));
        }
        
        tgt.translate(offset.x, offset.y);
        JSONObject tgtPnt = new JSONObject();
        setJsNumber(tgtPnt, "x", tgt.x);
        setJsNumber(tgtPnt, "y", tgt.y);
        json.put("targetPoint", tgtPnt);

        // Bend Points
        JSONArray bends = new JSONArray();
        KVectorChain vc = edge.getBendPoints().translate(edgeOffset);
        int index = 0;
        for (KVector v : vc) {
            JSONObject jv = new JSONObject();
            setJsNumber(jv, "x", v.x);
            setJsNumber(jv, "y", v.y);
            bends.set(index++, jv);
        }
        if (!vc.isEmpty()) {
            json.put("bendPoints", bends);
        } else {
            json.put("bendPoints", null);
        }
        
        // Junction Points
        KVectorChain junctionPoints = edge.getProperty(LayoutOptions.JUNCTION_POINTS);
        index = 0;
        if (junctionPoints != null) {
            junctionPoints.translate(offset);
            JSONArray junctions = new JSONArray();
            for (KVector v : junctionPoints) {
                JSONObject jv = new JSONObject();
                setJsNumber(jv, "x", v.x);
                setJsNumber(jv, "y", v.y);
                junctions.set(index++, jv);
            }
            json.put("junctionPoints", junctions);
        } else {
            json.put("junctionPoints", null);
        }
    }

    /*---------------------------------------------------------------------------------
     *                          Internal convenience
     */

    /**
     * Tests if the object contains a valid 'id' property.
     * 
     * @throws UnsupportedGraphException
     *             in case the 'id' property is not existent or is invalid.
     */
    private void checkForId(final JSONObject obj) {
        if (!obj.containsKey("id")) {
            throw new UnsupportedJsonGraphException(
                    "Every graph element must specify an 'id' property.", obj);
        }
        if (obj.get("id").isString() == null) {
            throw new UnsupportedJsonGraphException("Invalid format for 'id'. Must be a string, was "
                    + obj.get("id").getClass(), obj);
        }
    }
    
    private void checkForNonNull(final Object obj, final String additionalMsg) {
        if (obj == null) {
           throw new UnsupportedJsonGraphException("An element is null. " + additionalMsg); 
        }
    }
    
    private void setJsProperty(final JSONObject obj, final IProperty<?> prop, final JSONValue value) {
        setJsProperty(obj, prop.getId(), value);
    }
    
    private void setJsProperty(final JSONObject obj, final String name, final JSONValue value) {
        JSONValue props = obj.get("properties");
        if (props == null) {
            props = new JSONObject();
            obj.put("properties", props);
        }
        props.isObject().put(name, value);
    }
    
    /**
     * Sets the value of the passed key to be a {@link JSONNumber} of {@code value}.
     * Obeys to the value of {@code exportIntegerCoordinates} and does a simple cast to
     * int if the flag is set to true.
     */
    private void setJsNumber(final JSONObject obj, final String key, final double value) {
        JSONNumber n;
        if (!exportIntegerCoordinates) {
            n = new JSONNumber(value);
        } else {
            n = new JSONNumber((int) value);
        }
        
        obj.put(key, n);
    }
}
