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
package de.cau.klay.gwt.client.layout;

import java.util.EnumSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.math.KVectorChain;
import de.cau.cs.kieler.kiml.UnsupportedGraphException;
import de.cau.cs.kieler.kiml.options.Direction;
import de.cau.cs.kieler.kiml.options.EdgeLabelPlacement;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.KlayLayered;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LGraphElement;
import de.cau.cs.kieler.klay.layered.graph.LLabel;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.LShape;
import de.cau.cs.kieler.klay.layered.importexport.ImportUtil;
import de.cau.cs.kieler.klay.layered.p3order.CrossingMinimizationStrategy;
import de.cau.cs.kieler.klay.layered.properties.GraphProperties;
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
public class JsonGraphImporter {

    private Map<String, LNode> nodeIdMap = Maps.newHashMap();
    private Map<String, LEdge> edgeIdMap = Maps.newHashMap();
    private Map<String, LPort> portIdMap = Maps.newHashMap();

    private Map<LNode, JSONObject> nodeJsonMap = Maps.newHashMap();
    private Map<LEdge, JSONObject> edgeJsonMap = Maps.newHashMap();
    private Map<LPort, JSONObject> portJsonMap = Maps.newHashMap();
    private Map<LLabel, JSONObject> labelJsonMap = Maps.newHashMap();
    
    /** Holds for each compound node the {@link LGraph} created for a json node. */
    private Map<JSONObject, LGraph> jsonLGraphMap = Maps.newHashMap();
    
    /** Holds for an {@link LShape} the parent {@link LGraph}. */
    private Map<LShape, LGraph> shapeParentGraphMap = Maps.newHashMap();
    /** Holds for an {@link LEdge} the parent {@link LGraph}. */
    private Map<LEdge, LGraph> edgeParengGraphMap = Maps.newHashMap();

    public void layout(JSONObject json) {

        // transform JSON -> LGraph
        LGraph graph = transform(json);

        // perform layer-based layout
        KlayLayered klayLayered = new KlayLayered();
        LGraph result = klayLayered.doLayout(graph, new BasicProgressMonitor());

        // transfer the layout information back to the json objects
        transferLayout(result, json);
        transferLayout();

    }

    private void reset() {
        nodeIdMap.clear();
        edgeIdMap.clear();
        portIdMap.clear();

        nodeJsonMap.clear();
        edgeJsonMap.clear();
        portJsonMap.clear();
        labelJsonMap.clear();
        
        jsonLGraphMap.clear();
        shapeParentGraphMap.clear();
        edgeParengGraphMap.clear();
    }

    /*---------------------------------------------------------------------------------
     *                          Transform JSON to LGraph
     */

    private LGraph transform(JSONObject json) {

        reset();

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
    private LGraph transformNodes(JSONObject jparent, LNode parentNode) {

        // create a new graph instance
        LGraph graph = new LGraph();
        jsonLGraphMap.put(jparent, graph);
        
        if(parentNode == null) {
            // properties -> on root level the layout options
            // if we are not on root level, the properties are already transformed
            // for the node itself
            transformProperties(jparent, graph);
        } else {
            // set this LGraph as child of the parent LNode
            parentNode.setProperty(Properties.CHILD_LGRAPH, graph);
        }

        // the graph properties discovered during the transformations
        EnumSet<GraphProperties>  graphProperties = EnumSet.noneOf(GraphProperties.class);
        graph.setProperty(Properties.GRAPH_PROPERTIES, graphProperties);

        // try to get the 'children' array from the json node
        
        if (jparent.containsKey("children")) {
            JSONValue val = jparent.get("children");
            if (val.isArray() == null) {
                throw new UnsupportedGraphException(
                        "The 'children' property of nodes must be an array.");
            }
            JSONArray children = val.isArray();
            
            // transform all child nodes
            LNode[] childNodes = new LNode[children.size()];
            for (int i = 0; i < children.size(); ++i) {
                JSONValue childVal = children.get(i);
                if (childVal.isObject() == null) {
                    throw new UnsupportedGraphException(
                            "A 'children' array contains a non-object node element.");
                }
                LNode child = transformNode(childVal.isObject(), graph);
                childNodes[i] = child;
            }
            
            // after this graph layer has been processed, transform the children of this layer's children
            for(int i = 0; i < children.size(); ++i) {
                // we don't have to check the json here, was checked during previous for loop
                JSONObject jChild = children.get(i).isObject();
                LNode childNode = childNodes[i];
                // ignore the child's contents if NO_LAYOUT option is set
                if (!childNode.getProperty(LayoutOptions.NO_LAYOUT)) {
                    transformNodes(jChild, childNode);
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
    private LNode transformNode(JSONObject jNode, LGraph graph) {

        checkForId(jNode);
        Set<GraphProperties> graphProperties = 
                graph.getProperty(Properties.GRAPH_PROPERTIES);

        LNode node = new LNode(graph);
        graph.getLayerlessNodes().add(node);

        // id and register
        JSONString id = (JSONString) jNode.get("id");
        nodeIdMap.put(id.stringValue(), node);
        nodeJsonMap.put(node, jNode);
        shapeParentGraphMap.put(node, graph);

        // dimensions
        transformDimensions(jNode, node);

        // properties
        transformProperties(jNode, node);

        // ports
        if (jNode.containsKey("ports")) {
            JSONValue val = jNode.get("ports");
            if (val.isArray() == null) {
                throw new UnsupportedGraphException(
                        "The 'ports' property of the node must be an array.");
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
            node.setProperty(Properties.COMPOUND_NODE, true);
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
                graph.getProperty(Properties.GRAPH_PROPERTIES);

        // should we include this port into the layout?
        String noLayoutId = LayoutOptions.NO_LAYOUT.getId();
        if (jPort.containsKey(noLayoutId)
                && jPort.get(noLayoutId).isBoolean().booleanValue() == true) {
            return;
        }

        LPort port = new LPort(graph);
        port.setNode(node);

        // id and register
        JSONString id = (JSONString) jPort.get("id");
        portIdMap.put(id.stringValue(), port);
        portJsonMap.put(port, jPort);
        shapeParentGraphMap.put(port, graph);

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
        ImportUtil.initializePort(port, portConstraints, direction,
                port.getProperty(Properties.PORT_ANCHOR));

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
                throw new UnsupportedGraphException(
                        "The 'labels' property of a node must be an array.");
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
                && jLabel.get(noLayoutId).isBoolean().booleanValue() == true) {
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
            throw new UnsupportedGraphException("Labels must have a property 'text'.");
        } else if (val.isString() == null) {
            throw new UnsupportedGraphException("A label's 'text' property must be a string.");
        }

        // create a new label
        String text = val.isString().stringValue();
        LLabel label = new LLabel(graph, text);
        labelJsonMap.put(label, jLabel);
        shapeParentGraphMap.put(label, graph);

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
                        graph.getProperty(Properties.GRAPH_PROPERTIES);
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
                throw new UnsupportedGraphException(
                        "The 'edges' property of a node has to be an array.");
            }
            JSONArray edges = val.isArray();

            for (int i = 0; i < edges.size(); ++i) {
                JSONValue edgeVal = edges.get(i);
                if(edgeVal.isObject() == null) {
                    throw new UnsupportedGraphException("All elements of the 'edge' property must be objects.");
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
                && jEdge.get(noLayoutId).isBoolean().booleanValue() == true) {
            return;
        }
        
        JSONValue jSourceNode = jEdge.get("source");
        JSONValue jSourcePort = jEdge.get("sourcePort");
        JSONValue jTargetNode = jEdge.get("target");
        JSONValue jTargetPort = jEdge.get("targetPort");

        // check for valid source
        if (jSourceNode == null) {
            throw new UnsupportedGraphException("Edges must contain a 'source' property.");
        } else if (jSourceNode.isString() == null) {
            throw new UnsupportedGraphException(
                    "Invalid format of an edge's 'source' property. It must be a string.");
        }

        // check for valid target
        if (jTargetNode == null) {
            throw new UnsupportedGraphException("Edges must contain a 'target' property.");
        } else if (jTargetNode.isString() == null) {
            throw new UnsupportedGraphException(
                    "Invalid format of an edge's 'target' property. It must be a string.");
        }

        // create a layered edge
        LEdge edge = new LEdge(graph);

        // id and register
        JSONString id = (JSONString) jEdge.get("id");
        edgeIdMap.put(id.stringValue(), edge);
        edgeJsonMap.put(edge, jEdge);
        edgeParengGraphMap.put(edge, graph);
        
        // properties
        transformProperties(jEdge, edge);

        // labels
        transformLabels(jEdge, edge, graph);

        // retrieve source and target
        LNode sourceNode = null;
        LPort sourcePort = null;
        LNode targetNode = null;
        LPort targetPort = null;

        // get the source node
        try {
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
            throw new UnsupportedGraphException("An edge's 'source', 'target', 'sourcePort', "
                    + "and 'targetPort' properties have to be strings.");
        }
        

        Set<GraphProperties> graphProperties = graph.getProperty(Properties.GRAPH_PROPERTIES);

        if (sourceNode != null && targetNode != null) {
            // if we have a self-loop, set the appropriate graph property
            if (sourceNode == targetNode) {
                graphProperties.add(GraphProperties.SELF_LOOPS);
            }

            // create source and target ports if they do not exist yet
            if (sourcePort == null) {
                sourcePort =
                        ImportUtil.createPort(sourceNode, new KVector(), PortType.OUTPUT, graph);
            } else if (sourcePort.getNode() != sourceNode) {
                throw new UnsupportedGraphException("Inconsistent source port reference found.");
            }

            if (targetPort == null) {
                targetPort =
                        ImportUtil.createPort(targetNode, new KVector(), PortType.INPUT, graph);
            } else if (targetPort.getNode() != targetNode) {
                throw new UnsupportedGraphException("Inconsistent target port reference found.");
            }

            edge.setSource(sourcePort);
            edge.setTarget(targetPort);
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
                edge.setProperty(Properties.ORIGINAL_BENDPOINTS, bendpoints);
            } catch (Exception e) {
                throw new UnsupportedGraphException(
                        "Invalid format of an edges 'bendPoints' property.");
            }
        }

        // clear junction points, since they are recomputed from scratch
        edge.setProperty(LayoutOptions.JUNCTION_POINTS, null);

    }

    /**
     * Transforms the dimensions of a json element, this being x,y coordinates and width and height.
     */
    private void transformDimensions(JSONObject jsonEle, LShape ele) {
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
    private void transformProperties(JSONObject jsonEle, LGraphElement ele) {
        if (jsonEle.containsKey("properties")) {
            JSONValue val = jsonEle.get("properties");
            if (val.isObject() == null) {
                throw new UnsupportedGraphException(
                        "The 'properties' property of a graph element must be an object.");
            }
            JSONObject properties = val.isObject();
            for (String key : properties.keySet()) {
                JSONValue theVal = properties.get(key);
                // try to find the specified layout option and parse its value
                LayoutOptionResolver.setOption(ele, key, theVal);
            }
        }
    }


    /*---------------------------------------------------------------------------------
     *                          Transfer the Layout back
     */

    public void transferLayout() {
        // nodes
        for (Entry<LNode, JSONObject> e : nodeJsonMap.entrySet()) {
            transferLayout(e.getKey(), e.getValue());
        }

        // edges
        for (Entry<LEdge, JSONObject> e : edgeJsonMap.entrySet()) {
            transferLayout(e.getKey(), e.getValue());
        }

        // ports
        for (Entry<LPort, JSONObject> e : portJsonMap.entrySet()) {
            transferLayout(e.getKey(), e.getValue());
        }

        // labels
        for (Entry<LLabel, JSONObject> e : labelJsonMap.entrySet()) {
            transferLayout(e.getKey(), e.getValue());
        }

    }

    private void transferLayout(LGraph graph, JSONObject json) {
        JSONNumber width = new JSONNumber(graph.getSize().x);
        json.put("width", width);

        JSONNumber height = new JSONNumber(graph.getSize().y);
        json.put("height", height);
    }

    private void transferLayout(LShape shape, JSONObject json) {
        KVector offset = shapeParentGraphMap.get(shape).getOffset();

        JSONNumber x = new JSONNumber(shape.getPosition().x + offset.x);
        json.put("x", x);

        JSONNumber y = new JSONNumber(shape.getPosition().y + offset.y);
        json.put("y", y);

        JSONNumber width = new JSONNumber(shape.getSize().x);
        json.put("width", width);

        JSONNumber height = new JSONNumber(shape.getSize().y);
        json.put("height", height);
    }

    private void transferLayout(final LEdge edge, final JSONObject json) {

        KVector offset = edgeParengGraphMap.get(edge).getOffset();

        // Source Point
        KVector src = edge.getSource().getAbsoluteAnchor().translate(offset.x, offset.y);
        JSONObject srcPnt = new JSONObject();
        srcPnt.put("x", new JSONNumber(src.x));
        srcPnt.put("y", new JSONNumber(src.y));
        json.put("sourcePoint", srcPnt);

        // Target Point
        KVector tgt = edge.getTarget().getAbsoluteAnchor().translate(offset.x, offset.y);
        JSONObject tgtPnt = new JSONObject();
        tgtPnt.put("x", new JSONNumber(tgt.x));
        tgtPnt.put("y", new JSONNumber(tgt.y));
        json.put("targetPoint", tgtPnt);

        // Bend Points
        JSONArray bends = new JSONArray();
        KVectorChain vc = edge.getBendPoints().translate(offset);
        int index = 0;
        for (KVector v : vc) {
            JSONObject jv = new JSONObject();
            jv.put("x", new JSONNumber(v.x));
            jv.put("y", new JSONNumber(v.y));
            bends.set(index++, jv);
        }
        json.put("bendPoints", bends);
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
            throw new UnsupportedGraphException(
                    "Every graph element must specify an 'id' property.");
        }
        if (obj.get("id").isString() == null) {
            throw new UnsupportedGraphException("Invalid format for 'id'. Must be a string, was "
                    + obj.get("id").getClass());
        }
    }

}
