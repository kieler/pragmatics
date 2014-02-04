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

import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.user.client.Window;

import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KGraphData;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.options.Direction;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.klay.layered.KlayLayered;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LGraphElement;
import de.cau.cs.kieler.klay.layered.graph.LLabel;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.LShape;
import de.cau.cs.kieler.klay.layered.importexport.ImportUtil;
import de.cau.cs.kieler.klay.layered.properties.GraphProperties;
import de.cau.cs.kieler.klay.layered.properties.PortType;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * Transforms our JSON Graph format into an LGraph.
 * 
 * @author uru
 */
public class JsonGraphImporter {

    private Map<String, LNode> nodeIdMap = Maps.newHashMap();
    private Map<String, LEdge> edgeIdMap = Maps.newHashMap();
    private Map<String, LPort> portIdMap = Maps.newHashMap();
    private Map<String, LLabel> labelIdMap = Maps.newHashMap();

    private Map<LNode, JSONObject> nodeJsonMap = Maps.newHashMap();
    private Map<LEdge, JSONObject> edgeJsonMap = Maps.newHashMap();
    private Map<LPort, JSONObject> portJsonMap = Maps.newHashMap();
    private Map<LLabel, JSONObject> labelJsonMap = Maps.newHashMap();

    private EnumSet<GraphProperties> graphProperties;

    public void layout(JSONObject json) {

        LGraph graph = transform(json);

        System.out.println(graph.getLayerlessNodes());
        // Perform layer-based layout
        KlayLayered klayLayered = new KlayLayered();
        LGraph result = klayLayered.doLayout(graph, new BasicProgressMonitor());

        System.out.println(result.getLayers());
        transferLayout();

    }

    public LGraph transform(JSONObject json) {

        if (json == null) {
            Window.alert("JSON NULL");
        }
        reset();

        LGraph graph = new LGraph();
        // the graph properties discovered during the transformations
        graphProperties = EnumSet.noneOf(GraphProperties.class);
        graph.setProperty(Properties.GRAPH_PROPERTIES, graphProperties);

        // properties
        transformProperties(json, graph);

        transformNodes(json, null, graph);

        transformEdges(json, graph);

        return graph;
    }

    private void reset() {
        nodeIdMap.clear();
        edgeIdMap.clear();
        portIdMap.clear();
        labelIdMap.clear();

        nodeJsonMap.clear();
        edgeJsonMap.clear();
        portJsonMap.clear();
        labelJsonMap.clear();
    }

    public void transferLayout() {
        for (Entry<LNode, JSONObject> e : nodeJsonMap.entrySet()) {
            transferLayout(e.getKey(), e.getValue());
        }

        for (Entry<LEdge, JSONObject> e : edgeJsonMap.entrySet()) {
            transferLayout(e.getKey(), e.getValue());
        }

        for (Entry<LPort, JSONObject> e : portJsonMap.entrySet()) {
            transferLayout(e.getKey(), e.getValue());
        }

        for (Entry<LLabel, JSONObject> e : labelJsonMap.entrySet()) {
            transferLayout(e.getKey(), e.getValue());
        }

    }

    private void transferLayout(LShape shape, JSONObject json) {

        System.out.println("transfering layout " + shape.getPosition());
        JSONNumber x = new JSONNumber(shape.getPosition().x);
        json.put("x", x);

        JSONNumber y = new JSONNumber(shape.getPosition().y);
        json.put("y", y);

        JSONNumber width = new JSONNumber(shape.getSize().x);
        json.put("width", width);

        JSONNumber height = new JSONNumber(shape.getSize().y);
        json.put("height", height);
    }

    private void transferLayout(LEdge edge, JSONObject json) {
        // TODO
    }

    /**
     * We expect the parentNode to be transformed already
     * 
     */
    private void transformNodes(JSONObject parent, LNode parentNode, LGraph layeredGraph) {

        // Set<GraphProperties> graphProperties =
        // layeredGraph.getProperty(Properties.GRAPH_PROPERTIES);
        // List<LNode> layeredNodes = layeredGraph.getLayerlessNodes();
        //
        // KVector layoutNodeSize = new KVector(parentNode.getSize().x,
        // parentNode.getSize().y);
        //
        // // Find out whether there are external ports or ports with multiple incident
        // // edges that need to be considered
        // List<LPort> ports = parentNode.getPorts();
        // for (LPort kport : ports) {
        // int hierarchicalEdges = 0;
        //
        // for (LEdge kedge : kport.getConnectedEdges()) {
        // if (parentNode.equals(kedge.getSource().getNode())
        // || parentNode.equals(kedge.getTarget().getNode())) {
        // hierarchicalEdges++;
        // }
        // }
        //
        // if (hierarchicalEdges > 0) {
        // graphProperties.add(GraphProperties.EXTERNAL_PORTS);
        // }
        //
        // if (hierarchicalEdges > 1) {
        // graphProperties.add(GraphProperties.HYPEREDGES);
        // }
        // }
        //
        // // Transform the external ports
        // Direction direction = layeredGraph.getProperty(LayoutOptions.DIRECTION);
        // if (direction == Direction.UNDEFINED) {
        // // The default direction is right
        // direction = Direction.RIGHT;
        // }
        // if (graphProperties.contains(GraphProperties.EXTERNAL_PORTS)) {
        // for (LPort kport : ports) {
        // transformExternalPort(kport, layeredNodes, graph, layoutNodeSize,
        // direction);
        // }
        // }

        // Now transform the node's children
        if (parent.containsKey("children")) {
            JSONArray nodes = (JSONArray) parent.get("children");
            for (int i = 0; i < nodes.size(); ++i) {
                if (nodes.get(i) instanceof JSONObject) {
                    transformNode((JSONObject) nodes.get(i), layeredGraph);
                }
            }
        }
        //
        // for (KNode child : graph.getChildren()) {
        // if (!child.getData(KShapeLayout.class).getProperty(LayoutOptions.NO_LAYOUT)) {
        // transformNode(child, layeredNodes, elemMap, graphProperties, direction);
        // }
        // }

    }

    /**
     * Transforms the given external port into a dummy node.
     */
    private void transformExternalPort(final LPort lport, final LNode parentNode,
            final LGraph graph, final KVector layoutNodeSize) {

        // KShapeLayout graphLayout = graph.getData(KShapeLayout.class);
        // KShapeLayout knodeLayout = kport.getNode().getData(KShapeLayout.class);
        // KShapeLayout kportLayout = kport.getData(KShapeLayout.class);

        // TODO

        // Calculate the position of the port's center
        // KVector kportPosition = new KVector(lport.getPosition().x + lport.getSize().x / 2.0,
        // lport.getPosition().y + lport.getSize().y / 2.0);
        //
        // // Count the number of incoming and outgoing edges
        // int inEdges = 0, outEdges = 0;
        // for (LEdge edge : lport.getConnectedEdges()) {
        // if (edge.getSourcePort() == kport && edge.getTarget().getParent() == graph) {
        // outEdges++;
        // }
        // if (edge.getTargetPort() == kport && edge.getSource().getParent() == graph) {
        // inEdges++;
        // }
        // }
        //
        // // Create dummy
        // KShapeLayout portLayout = kport.getData(KShapeLayout.class);
        // PortSide portSide = portLayout.getProperty(LayoutOptions.PORT_SIDE);
        // Float offset = portLayout.getProperty(LayoutOptions.OFFSET);
        // PortConstraints portConstraints =
        // graphLayout.getProperty(LayoutOptions.PORT_CONSTRAINTS);
        //
        // if (portSide == PortSide.UNDEFINED) {
        // portSide = KimlUtil.calcPortSide(kport, direction);
        // portLayout.setProperty(LayoutOptions.PORT_SIDE, portSide);
        // }
        //
        // if (offset == null) {
        // // if port coordinates are (0,0), we default to port offset 0 to make the common case
        // // frustration-free
        // if (portLayout.getXpos() == 0.0f && portLayout.getYpos() == 0.0f) {
        // offset = 0.0f;
        // } else {
        // offset = KimlUtil.calcPortOffset(kport, portSide);
        // }
        // portLayout.setProperty(LayoutOptions.OFFSET, offset);
        // }
        //
        // LNode dummy = ImportUtil.createExternalPortDummy(
        // kportLayout, portConstraints, portSide, inEdges - outEdges, layoutNodeSize,
        // kportPosition, new KVector(kportLayout.getWidth(), kportLayout.getHeight()),
        // direction, layeredGraph);
        // dummy.setProperty(Properties.ORIGIN, kport);
        //
        // // If the compound node wants to have its port labels placed on the inside, we need to
        // leave
        // // enough space for them by creating an LLabel for the KLabels
        // if (knodeLayout.getProperty(LayoutOptions.PORT_LABEL_PLACEMENT) ==
        // PortLabelPlacement.INSIDE) {
        // LPort dummyPort = dummy.getPorts().get(0);
        // dummy.setProperty(LayoutOptions.PORT_LABEL_PLACEMENT, PortLabelPlacement.OUTSIDE);
        // for (KLabel klabel : kport.getLabels()) {
        // KShapeLayout labelLayout = klabel.getData(KShapeLayout.class);
        //
        // LLabel newLabel = new LLabel(layeredGraph, klabel.getText());
        // newLabel.setProperty(Properties.ORIGIN, klabel);
        // newLabel.getSize().x = labelLayout.getWidth();
        // newLabel.getSize().y = labelLayout.getHeight();
        // newLabel.getPosition().x = labelLayout.getXpos();
        // newLabel.getPosition().y = labelLayout.getYpos();
        // dummyPort.getLabels().add(newLabel);
        // }
        // }
        //
        // layeredNodes.add(dummy);
        // elemMap.put(kport, dummy);
    }

    private void transformNode(JSONObject jnode, LGraph graph) {

        LNode node = new LNode(graph);
        graph.getLayerlessNodes().add(node);

        // id and register
        JSONString id = (JSONString) jnode.get("id");
        nodeIdMap.put(id.stringValue(), node);
        nodeJsonMap.put(node, jnode);

        // dimensions
        transformDimensions(jnode, node);

        // ports
        if (jnode.containsKey("ports")) {
            JSONArray ports = (JSONArray) jnode.get("ports");

            for (int i = 0; i < ports.size(); ++i) {
                if (ports.get(i) instanceof JSONObject) {
                    transformPort((JSONObject) ports.get(i), node, graph);
                }
            }
        }

        // properties
        transformProperties(jnode, node);

        // KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
        //
        // // add a new node to the layered graph, copying its position
        // LNode newNode = new LNode(layeredGraph);
        // newNode.setProperty(Properties.ORIGIN, node);
        // newNode.getSize().x = nodeLayout.getWidth();
        // newNode.getSize().y = nodeLayout.getHeight();
        // newNode.getPosition().x = nodeLayout.getXpos();
        // newNode.getPosition().y = nodeLayout.getYpos();
        //
        // layeredNodes.add(newNode);
        // elemMap.put(node, newNode);
        //
        // check if the node is a compound node in the original graph
        if (jnode.containsKey("children") && jnode.get("children").isArray().size() > 0) {
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

        // transform the ports
        for (LPort lport : node.getPorts()) {

        }

        // add the node's labels
        // TODO
        // for (KLabel klabel : node.getLabels()) {
        // KShapeLayout labelLayout = klabel.getData(KShapeLayout.class);
        // if (!labelLayout.getProperty(LayoutOptions.NO_LAYOUT)) {
        // LLabel newLabel = new LLabel(layeredGraph, klabel.getText());
        // newLabel.setProperty(Properties.ORIGIN, klabel);
        // newLabel.getSize().x = labelLayout.getWidth();
        // newLabel.getSize().y = labelLayout.getHeight();
        // newLabel.getPosition().x = labelLayout.getXpos();
        // newLabel.getPosition().y = labelLayout.getYpos();
        // newNode.getLabels().add(newLabel);
        // }
        // }

        if (node.getProperty(LayoutOptions.COMMENT_BOX)) {
            graphProperties.add(GraphProperties.COMMENTS);
        }

        // if we have a hypernode without ports, create a default input and output port
        if (node.getProperty(LayoutOptions.HYPERNODE)) {
            graphProperties.add(GraphProperties.HYPERNODES);
            graphProperties.add(GraphProperties.HYPEREDGES);
            node.setProperty(LayoutOptions.PORT_CONSTRAINTS, PortConstraints.FREE);
        }
    }

    private void transformPort(final JSONObject jport, final LNode node, final LGraph graph) {

        // should we include this port into the layout?
        String noLayoutId = LayoutOptions.NO_LAYOUT.getId();
        if (jport.containsKey(noLayoutId)
                && jport.get(noLayoutId).isBoolean().booleanValue() == true) {
            return;
        }

        LPort port = new LPort(graph);
        port.setNode(node);

        // id and register
        JSONString id = (JSONString) jport.get("id");
        portIdMap.put(id.stringValue(), port);
        portJsonMap.put(port, jport);

        // dimensions
        transformDimensions(jport, port);

        // properties
        transformProperties(jport, port);

        // collect information on the structure of the graph

        // find out if there are hyperedges, that is a set of edges connected to the same port
        if (Iterables.size(port.getConnectedEdges()) > 1) {
            // TODO edges are not converted yet
            graphProperties.add(GraphProperties.HYPEREDGES);
        }

        port.setSide(port.getProperty(LayoutOptions.PORT_SIDE));

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
        }

        // create the port's labels
        // TODO labels
        // for (KLabel klabel : port.getLabels()) {
        // KShapeLayout labelLayout = klabel.getData(KShapeLayout.class);
        // if (!labelLayout.getProperty(LayoutOptions.NO_LAYOUT)) {
        // LLabel newLabel = new LLabel(layeredGraph, klabel.getText());
        // newLabel.setProperty(Properties.ORIGIN, klabel);
        // newLabel.getSize().x = labelLayout.getWidth();
        // newLabel.getSize().y = labelLayout.getHeight();
        // newLabel.getPosition().x = labelLayout.getXpos();
        // newLabel.getPosition().y = labelLayout.getYpos();
        // newPort.getLabels().add(newLabel);
        // }
        // }
    }

    private void transformEdges(final JSONObject parent, final LGraph graph) {

        // TODO when introducing hierarchy, make sure the WHOLE hierarchy of nodes is transformed
        // first
        // then transform the edges (important that the nodes and ports are already known)
        if (parent.containsKey("edges")) {
            JSONArray edges = (JSONArray) parent.get("edges");

            for (int i = 0; i < edges.size(); ++i) {
                if (edges.get(i) instanceof JSONObject) {
                    transformEdge((JSONObject) edges.get(i), graph);
                }
            }
        }

        // TODO
        // // Transform external port edges
        // transformExternalPortEdges(graph, graph.getIncomingEdges(), elemMap);
        // transformExternalPortEdges(graph, graph.getOutgoingEdges(), elemMap);
        //
        // // Transform edges originating in the layout node's children
        // for (KNode child : graph.getChildren()) {
        // for (KEdge kedge : child.getOutgoingEdges()) {
        // // exclude edges that pass hierarchy bounds (except for those
        // // going into an external port)
        // if (kedge.getTarget().getParent() == graph
        // && !kedge.getData(KEdgeLayout.class).getProperty(LayoutOptions.NO_LAYOUT)) {
        // transformEdge(kedge, graph, elemMap);
        // }
        // }
        // }
    }

    private void transformEdge(final JSONObject jedge, final LGraph graph) {

        // TODO
        // // Only transform edges going into the layout node's direct children
        // // (self-loops of the layout node will be processed on level higher)
        // if (kedge.getSource().getParent() == graph || kedge.getTarget().getParent() == graph
        // && !kedge.getData(KEdgeLayout.class).getProperty(LayoutOptions.NO_LAYOUT)) {
        //
        // transformEdge(kedge, graph, elemMap);
        // }

        // create a layered edge
        LEdge edge = new LEdge(graph);

        // id and register
        JSONString id = (JSONString) jedge.get("id");
        edgeIdMap.put(id.stringValue(), edge);
        edgeJsonMap.put(edge, jedge);

        // TODO src and tgt node and port
        LNode srcNode = nodeIdMap.get(jedge.get("src").isNumber().doubleValue());

        // the following is not needed in case of compound graph handling, as source and target will
        // be set by calling function.

        JSONString jSourceNode = jedge.get("source").isString();
        JSONString jSourcePort = jedge.get("sourcePort").isString();
        JSONString jTargetNode = jedge.get("target").isString();
        JSONString jTargetPort = jedge.get("targetPort").isString();

        // retrieve source and target
        LNode sourceNode = null;
        LPort sourcePort = null;
        LNode targetNode = null;
        LPort targetPort = null;

        // check if the edge source is an external port
        // TODO
        // if (kedge.getSource() == graph && kedge.getSourcePort() != null) {
        // LGraphElement elem = elemMap.get(kedge.getSourcePort());
        // if (elem instanceof LNode) {
        // sourceNode = (LNode) elem;
        // // the port could be missing in the map if kedge is not in the port's edge list
        // if (sourceNode != null) {
        // sourcePort = sourceNode.getPorts().get(0);
        // }
        // } else if (elem != null) {
        // throw new UnsupportedGraphException("Inconsistent source port reference found.");
        // }
        // } else {
        sourceNode = nodeIdMap.get(jSourceNode.stringValue());
        if (jSourcePort.stringValue() != null) {
            sourcePort = portIdMap.get(jSourcePort);
        }
        // LGraphElement elem = elemMap.get(kedge.getSourcePort());
        // if (elem instanceof LPort) {
        // sourcePort = (LPort) elem;
        // } else if (elem != null) {
        // throw new UnsupportedGraphException("Inconsistent source port reference found.");
        // }
        // }

        // check if the edge target is an external port
        // TODO
        // if (kedge.getTarget() == graph && kedge.getTargetPort() != null) {
        // LGraphElement elem = elemMap.get(kedge.getTargetPort());
        // if (elem instanceof LNode) {
        // targetNode = (LNode) elem;
        // // the port could be missing in the map if kedge is not in the port's edge list
        // if (targetNode != null) {
        // targetPort = targetNode.getPorts().get(0);
        // }
        // } else if (elem != null) {
        // throw new UnsupportedGraphException("Inconsistent target port reference found.");
        // }
        // } else {
        targetNode = nodeIdMap.get(jTargetNode.stringValue());

        if (jTargetPort != null) {
            targetPort = portIdMap.get(jTargetPort.stringValue());
        }
        // LGraphElement elem = elemMap.get(kedge.getTargetPort());
        // if (elem instanceof LPort) {
        // targetPort = (LPort) elem;
        // } else if (elem != null) {
        // throw new UnsupportedGraphException("Inconsistent target port reference found.");
        // }
        // }

        if (sourceNode != null && targetNode != null) {
            // if we have a self-loop, set the appropriate graph property
            // TODO
            // if (sourceNode == targetNode) {
            // Set<GraphProperties> graphProperties = layeredGraph.getProperty(
            // Properties.GRAPH_PROPERTIES);
            // graphProperties.add(GraphProperties.SELF_LOOPS);
            // }

            // create source and target ports if they do not exist yet
            if (sourcePort == null) {
                sourcePort = ImportUtil.createPort(sourceNode,
                // TODO how to calculate the source point?
                // edge.getSourcePoint().createVector(),
                        new KVector(), PortType.OUTPUT, graph);
            }
            // else if (kedge.getSourcePort().getNode() != kedge.getSource()) {
            // throw new UnsupportedGraphException("Inconsistent source port reference found.");
            // }

            if (targetPort == null) {
                targetPort = ImportUtil.createPort(targetNode,
                // TODO how to calculate the source point=
                // edgeLayout.getTargetPoint().createVector(),
                        new KVector(), PortType.INPUT, graph);
            }
            // else if (kedge.getTargetPort().getNode() != kedge.getTarget()) {
            // throw new UnsupportedGraphException("Inconsistent target port reference found.");
            // }

            edge.setSource(sourcePort);
            edge.setTarget(targetPort);
        }

        // TODO
        // // transform the edge's labels
        // for (KLabel klabel : kedge.getLabels()) {
        // KShapeLayout labelLayout = klabel.getData(KShapeLayout.class);
        // EdgeLabelPlacement labelPlacement = labelLayout.getProperty(
        // LayoutOptions.EDGE_LABEL_PLACEMENT);
        // if (!labelLayout.getProperty(LayoutOptions.NO_LAYOUT)
        // && labelPlacement != EdgeLabelPlacement.UNDEFINED) {
        // LLabel newLabel = new LLabel(layeredGraph, klabel.getText());
        // newLabel.getPosition().x = labelLayout.getXpos();
        // newLabel.getPosition().y = labelLayout.getYpos();
        // newLabel.getSize().x = labelLayout.getWidth();
        // newLabel.getSize().y = labelLayout.getHeight();
        // newLabel.setProperty(Properties.ORIGIN, klabel);
        // newLabel.setProperty(LayoutOptions.EDGE_LABEL_PLACEMENT, labelPlacement);
        // newEdge.getLabels().add(newLabel);
        // Set<GraphProperties> graphProperties = layeredGraph.getProperty(
        // Properties.GRAPH_PROPERTIES);
        // switch (labelPlacement) {
        // case CENTER:
        // // Add annotation if graph contains labels which shall be placed
        // // in the middle of an edge
        // graphProperties.add(GraphProperties.CENTER_LABELS);
        // break;
        // case HEAD:
        // case TAIL:
        // // Add annotation if graph contains labels which shall be placed
        // // in the middle of an edge
        // graphProperties.add(GraphProperties.END_LABELS);
        // }
        // }
        // }
        //
        // // copy the bend points of the edge if they are needed by anyone
        // if (layeredGraph.getProperty(Properties.CROSS_MIN) ==
        // CrossingMinimizationStrategy.INTERACTIVE
        // && !edgeLayout.getBendPoints().isEmpty()) {
        // KVectorChain bendpoints = new KVectorChain();
        // for (KPoint point : edgeLayout.getBendPoints()) {
        // bendpoints.add(point.createVector());
        // }
        // newEdge.setProperty(Properties.ORIGINAL_BENDPOINTS, bendpoints);
        // }
        //
        // // set properties of the new edge
        // newEdge.copyProperties(edgeLayout);

        // clear junction points, since they are recomputed from scratch
        edge.setProperty(LayoutOptions.JUNCTION_POINTS, null);

    }

    private void transformLabel(final JSONObject jlabel, final LGraph graph) {
        // TODO
    }

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

    private void transformProperties(JSONObject jsonEle, LGraphElement ele) {
        if (jsonEle.containsKey("properties")) {
            JSONObject properties = (JSONObject) jsonEle.get("properties");
            for (String key : properties.keySet()) {
                setOption(ele, key, properties.get(key).toString());
            }
        }
    }

    /**
     * Copied and adapted from {@link KimlUtil#setOption(KGraphData, String, String)}
     */
    public void setOption(final LGraphElement graphData, final String id, final String value) {
        // LayoutDataService dataService = LayoutDataService.getInstance();
        // LayoutOptionData optionData = dataService.getOptionData(id);
        // if (optionData != null) {
        // Object obj = optionData.parseValue(value);
        // if (obj != null) {
        // graphData.setProperty(optionData, obj);
        // }
        // }
    }

}
