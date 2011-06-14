/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.math.KVectorChain;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.EdgeRouting;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.klay.layered.graph.Insets;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraphElement;
import de.cau.cs.kieler.klay.layered.graph.LLabel;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.p5edges.EdgeRoutingStrategy;
import de.cau.cs.kieler.klay.layered.properties.GraphProperties;
import de.cau.cs.kieler.klay.layered.properties.NodeType;
//import de.cau.cs.kieler.klay.layered.properties.NodeType;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * Manages the transformation of KGraphs to LayeredGraphs. Sets the
 * {@link Properties#GRAPH_PROPERTIES} property on imported graphs.
 * 
 * @author msp
 * @author cds
 * @author ima
 */
public class KGraphImporter extends AbstractGraphImporter<KNode> {

    // /////////////////////////////////////////////////////////////////////////////
    // Transformation KGraph -> LGraph

    /**
     * {@inheritDoc}
     */
    public LayeredGraph importGraph(final KNode graph) {
        LayeredGraph layeredGraph = new LayeredGraph();
        layeredGraph.setProperty(Properties.ORIGIN, graph);

        KShapeLayout sourceShapeLayout = graph.getData(KShapeLayout.class);

        // copy the properties of the KGraph to the layered graph
        layeredGraph.copyProperties(sourceShapeLayout);
        layeredGraph.checkProperties(Properties.OBJ_SPACING, Properties.THOROUGHNESS,
                Properties.ASPECT_RATIO);

        float borderSpacing = layeredGraph.getProperty(LayoutOptions.BORDER_SPACING);
        if (borderSpacing < 0) {
            borderSpacing = Properties.DEF_SPACING;
        }
        layeredGraph.setProperty(LayoutOptions.BORDER_SPACING, borderSpacing);

        // copy the insets to the layered graph
        KInsets kinsets = sourceShapeLayout.getInsets();
        Insets.Double linsets = layeredGraph.getInsets();

        linsets.left = kinsets.getLeft();
        linsets.right = kinsets.getRight();
        linsets.top = kinsets.getTop();
        linsets.bottom = kinsets.getBottom();

        // keep a list of created nodes in the layered graph, as well as a map
        // between KGraph
        // nodes / ports and LGraph nodes / ports
        Map<KGraphElement, LGraphElement> elemMap = new HashMap<KGraphElement, LGraphElement>();

        // the graph properties discovered during the transformations
        EnumSet<GraphProperties> graphProperties = EnumSet.noneOf(GraphProperties.class);

        // Check if hierarchy handling for a compound graph is requested
        boolean isCompound = sourceShapeLayout.getProperty(LayoutOptions.LAYOUT_HIERARCHY);

        // Transform flat graph directly, import recursively for compound graph
        if (isCompound) {
            List<LNode> layeredNodes = layeredGraph.getLayerlessNodes();
            recursiveTransformCompoundGraph(graph, layeredNodes, layeredGraph, elemMap,
                    graphProperties);
            recursiveTransformEdges(graph, elemMap, layeredNodes);
        } else {
            // transform everything
            transformNodesAndPorts(graph, layeredGraph, elemMap, graphProperties);
            transformEdges(graph, elemMap, graphProperties);
        }

        // set the graph properties property
        layeredGraph.setProperty(Properties.GRAPH_PROPERTIES, graphProperties);

        return layeredGraph;
    }

    /**
     * Transforms the nodes and ports defined by the given layout node.
     * 
     * @param graph
     *            the original graph.
     * @param layeredGraph
     *            the layered graph.
     * @param elemMap
     *            the element map that maps the original {@code KGraph} elements to the transformed
     *            {@code LGraph} elements.
     * @param graphProperties
     *            graph properties updated during the transformation.
     */
    private void transformNodesAndPorts(final KNode graph, final LayeredGraph layeredGraph,
            final Map<KGraphElement, LGraphElement> elemMap,
            final EnumSet<GraphProperties> graphProperties) {

        List<LNode> layeredNodes = layeredGraph.getLayerlessNodes();

        KShapeLayout layoutNodeLayout = graph.getData(KShapeLayout.class);
        KVector layoutNodeSize = new KVector(layoutNodeLayout.getWidth(),
                layoutNodeLayout.getHeight());

        // Find out whether there are external ports that need to be considered
        List<KPort> ports = graph.getPorts();
        portLoop: for (KPort kport : ports) {
            for (KEdge kedge : kport.getEdges()) {
                if (graph.equals(kedge.getSource().getParent())
                        || graph.equals(kedge.getTarget().getParent())) {
                    graphProperties.add(GraphProperties.EXTERNAL_PORTS);
                    break portLoop;
                }
            }
        }

        // Transform the external ports
        if (graphProperties.contains(GraphProperties.EXTERNAL_PORTS)) {
            for (KPort kport : ports) {
                transformExternalPort(kport, layeredNodes, graph, layoutNodeSize, elemMap);
            }
        }

        // Now transform the node's children
        for (KNode child : graph.getChildren()) {
            transformNode(child, layeredNodes, elemMap, graphProperties);
        }

    }

    /**
     * Transforms the given external port into a dummy node.
     * 
     * @param kport
     *            the port.
     * @param layeredNodes
     *            the list of nodes to add the dummy node to.
     * @param graph
     *            the original graph.
     * @param layoutNodeSize
     *            the layout node's size.
     * @param elemMap
     *            the element map that maps the original {@code KGraph} elements to the transformed
     *            {@code LGraph} elements.
     */
    private void transformExternalPort(final KPort kport, final List<LNode> layeredNodes,
            final KNode graph, final KVector layoutNodeSize,
            final Map<KGraphElement, LGraphElement> elemMap) {

        KShapeLayout kportLayout = kport.getData(KShapeLayout.class);

        // Calculate the position of the port's center
        KVector kportPosition = new KVector(kportLayout.getXpos() + kportLayout.getWidth() / 2.0,
                kportLayout.getYpos() + kportLayout.getHeight() / 2.0);

        // Count the number of incoming and outgoing edges
        int inEdges = 0, outEdges = 0;
        for (KEdge edge : kport.getEdges()) {
            if (edge.getSourcePort() == kport && edge.getTarget().getParent() == graph) {
                outEdges++;
            }
            if (edge.getTargetPort() == kport && edge.getSource().getParent() == graph) {
                inEdges++;
            }
        }

        // Create dummy
        LNode dummy = createExternalPortDummy(kport,
                kportLayout.getProperty(LayoutOptions.PORT_CONSTRAINTS),
                KimlUtil.calcPortSide(kport), inEdges - outEdges, layoutNodeSize, kportPosition);
        layeredNodes.add(dummy);
        elemMap.put(kport, dummy);
    }

    /**
     * Transforms the given node.
     * 
     * @param node
     *            the node to transform.
     * @param layeredNodes
     *            the list of nodes to add the transformed node to.
     * @param elemMap
     *            the element map that maps the original {@code KGraph} elements to the transformed
     *            {@code LGraph} elements.
     * @param graphProperties
     *            graph properties updated during the transformation.
     */
    private void transformNode(final KNode node, final List<LNode> layeredNodes,
            final Map<KGraphElement, LGraphElement> elemMap,
            final EnumSet<GraphProperties> graphProperties) {

        // add a new node to the layered graph, copying its size
        KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
        if (!nodeLayout.getProperty(LayoutOptions.FIXED_SIZE)) {
            KimlUtil.resizeNode(node);
        }

        LNode newNode = new LNode();
        newNode.setProperty(Properties.ORIGIN, node);
        newNode.getPosition().x = nodeLayout.getXpos();
        newNode.getPosition().y = nodeLayout.getYpos();
        newNode.getSize().x = nodeLayout.getWidth();
        newNode.getSize().y = nodeLayout.getHeight();
        layeredNodes.add(newNode);

        elemMap.put(node, newNode);

        // port constraints cannot be undefined
        PortConstraints portConstraints = nodeLayout.getProperty(LayoutOptions.PORT_CONSTRAINTS);
        if (portConstraints == PortConstraints.UNDEFINED) {
            portConstraints = PortConstraints.FREE;
        }

        // get a sorted list of the node's ports; if there are any with non-free port
        // constraints, set the appropriate graph property
        KPort[] sortedPorts = KimlUtil.getSortedPorts(node);
        if (sortedPorts.length > 0 && portConstraints != PortConstraints.FREE) {
            graphProperties.add(GraphProperties.NON_FREE_PORTS);
        }

        // transform the ports
        for (KPort kport : sortedPorts) {
            KShapeLayout portLayout = kport.getData(KShapeLayout.class);

            // determine the port type
            int inEdges = 0, outEdges = 0;
            for (KEdge edge : kport.getEdges()) {
                if (edge.getSourcePort() == kport) {
                    outEdges++;
                }
                if (edge.getTargetPort() == kport) {
                    inEdges++;
                }
            }

            // create layered port, copying its position
            LPort newPort = new LPort();
            newPort.setProperty(Properties.ORIGIN, kport);
            newPort.getSize().x = portLayout.getWidth();
            newPort.getSize().y = portLayout.getHeight();
            newPort.getPosition().x = portLayout.getXpos() + portLayout.getWidth() / 2;
            newPort.getPosition().y = portLayout.getYpos() + portLayout.getHeight() / 2;
            newPort.setNode(newNode);

            elemMap.put(kport, newPort);

            // create layered label, if any
            KLabel klabel = kport.getLabel();
            if (klabel != null) {
                KShapeLayout labelLayout = klabel.getData(KShapeLayout.class);

                LLabel newLabel = new LLabel(klabel.getText());
                newLabel.setProperty(Properties.ORIGIN, klabel);
                newLabel.getSize().x = labelLayout.getWidth();
                newLabel.getSize().y = labelLayout.getHeight();
                newLabel.getPosition().x = labelLayout.getXpos() - portLayout.getWidth() / 2;
                newLabel.getPosition().y = labelLayout.getYpos() - portLayout.getHeight() / 2;
                newPort.setLabel(newLabel);
            }

            // calculate port side
            PortSide newPortSide = KimlUtil.calcPortSide(kport);
            newPort.setSide(newPortSide);

            if (newPortSide == PortSide.NORTH || newPortSide == PortSide.SOUTH) {
                graphProperties.add(GraphProperties.NORTH_SOUTH_PORTS);
            }
        }

        // add the node's label, if any
        KLabel klabel = node.getLabel();
        if (klabel != null) {
            KShapeLayout labelLayout = klabel.getData(KShapeLayout.class);

            LLabel newLabel = new LLabel(klabel.getText());
            newLabel.setProperty(Properties.ORIGIN, node);
            newLabel.getSize().x = labelLayout.getWidth();
            newLabel.getSize().y = labelLayout.getHeight();
            newLabel.getPosition().x = labelLayout.getXpos();
            newLabel.getPosition().y = labelLayout.getYpos();
            newNode.setLabel(newLabel);
        }

        // set properties of the new node
        newNode.copyProperties(nodeLayout);

        // if we have a hypernode without ports, create a default input and output port
        if (newNode.getProperty(LayoutOptions.HYPERNODE) && newNode.getPorts().isEmpty()) {
            LPort inputPort = new LPort();
            inputPort.setSide(PortSide.WEST);
            inputPort.setNode(newNode);

            LPort outputPort = new LPort();
            outputPort.setSide(PortSide.EAST);
            outputPort.setNode(newNode);
        }
    }

    /**
     * Transforms the edges defined by the given layout node.
     * 
     * @param graph
     *            the original graph.
     * @param elemMap
     *            the element map that maps the original {@code KGraph} elements to the transformed
     *            {@code LGraph} elements.
     * @param graphProperties
     *            graph properties updated during the transformation.
     */
    private void transformEdges(final KNode graph, final Map<KGraphElement, LGraphElement> elemMap,
            final EnumSet<GraphProperties> graphProperties) {

        // Transform external port edges
        transformExternalPortEdges(graph, graph.getIncomingEdges(), elemMap, graphProperties);
        transformExternalPortEdges(graph, graph.getOutgoingEdges(), elemMap, graphProperties);

        // Transform edges originating in the layout node's children
        for (KNode child : graph.getChildren()) {
            for (KEdge kedge : child.getOutgoingEdges()) {
                // exclude edges that pass hierarchy bounds (except for those
                // going into an
                // external port)
                if (kedge.getTarget().getParent() == child.getParent()) {
                    transformEdge(kedge, graph, elemMap, graphProperties);
                } else if (kedge.getTarget().getParent() != kedge.getSource()
                        && kedge.getTarget() != kedge.getSource().getParent()) {

                    // the edge is excluded from layout since it does not
                    // connect two adjacent
                    // hierarchy levels
                    KEdgeLayout edgeLayout = kedge.getData(KEdgeLayout.class);
                    edgeLayout.setProperty(LayoutOptions.NO_LAYOUT, true);
                }
            }
        }
    }

    /**
     * Transforms the given list of edges connected to the layout node's external ports.
     * 
     * @param graph
     *            the original graph.
     * @param edges
     *            the list of edges, each connected to an external port of the layout node.
     * @param elemMap
     *            the element map that maps the original {@code KGraph} elements to the transformed
     *            {@code LGraph} elements.
     * @param graphProperties
     *            graph properties updated during the transformation.
     */
    private void transformExternalPortEdges(final KNode graph, final List<KEdge> edges,
            final Map<KGraphElement, LGraphElement> elemMap,
            final EnumSet<GraphProperties> graphProperties) {

        for (KEdge kedge : edges) {
            // Only transform edges going into the layout node's direct children
            // (self-loops of the layout node will be processed on level higher)
            if (kedge.getSource().getParent() == graph || kedge.getTarget().getParent() == graph) {

                transformEdge(kedge, graph, elemMap, graphProperties);
            }
        }
    }

    /**
     * Transforms the given edge.
     * 
     * @param kedge
     *            the edge to transform.
     * @param graph
     *            the original graph.
     * @param elemMap
     *            the element map that maps the original {@code KGraph} elements to the transformed
     *            {@code LGraph} elements.
     * @param graphProperties
     *            graph properties updated during the transformation.
     */
    private void transformEdge(final KEdge kedge, final KNode graph,
            final Map<KGraphElement, LGraphElement> elemMap,
            final EnumSet<GraphProperties> graphProperties) {

        KEdgeLayout edgeLayout = kedge.getData(KEdgeLayout.class);

        // retrieve source and target
        LNode sourceNode = null;
        LPort sourcePort = null;
        LNode targetNode = null;
        LPort targetPort = null;

        // check if the edge source is an external port
        if (kedge.getSource() == graph) {
            sourceNode = (LNode) elemMap.get(kedge.getSourcePort());
            sourcePort = sourceNode.getPorts().get(0);
        } else {
            sourceNode = (LNode) elemMap.get(kedge.getSource());
            sourcePort = (LPort) elemMap.get(kedge.getSourcePort());
        }

        // check if the edge target is an external port
        if (kedge.getTarget() == graph) {
            targetNode = (LNode) elemMap.get(kedge.getTargetPort());
            targetPort = targetNode.getPorts().get(0);
        } else {
            targetNode = (LNode) elemMap.get(kedge.getTarget());
            targetPort = (LPort) elemMap.get(kedge.getTargetPort());
        }

        // if we have a self-loop, set the appropriate graph property
        if (sourceNode != null && sourceNode != graph && sourceNode == targetNode) {
            graphProperties.add(GraphProperties.SELF_LOOPS);
        }

        // create source port
        if (sourcePort == null) {
            if (sourceNode.getProperty(LayoutOptions.HYPERNODE)) {
                // Hypernodes have an eastern output port
                sourcePort = sourceNode.getPorts(PortSide.EAST).iterator().next();
            } else {
                sourcePort = new LPort();
                sourcePort.setNode(sourceNode);
                KPoint sourcePoint = edgeLayout.getSourcePoint();
                sourcePort.getPosition().x = sourcePoint.getX() - sourceNode.getPosition().x;
                sourcePort.getPosition().y = sourcePoint.getY() - sourceNode.getPosition().y;
                sourcePort.setSide(calcPortSide(sourceNode, sourcePort));
            }
        }

        // create target port
        if (targetPort == null) {
            if (targetNode.getProperty(LayoutOptions.HYPERNODE)) {
                // Hypernodes have a western input port
                targetPort = targetNode.getPorts(PortSide.WEST).iterator().next();
            } else {
                targetPort = new LPort();
                targetPort.setNode(targetNode);
                KPoint targetPoint = edgeLayout.getTargetPoint();
                targetPort.getPosition().x = targetPoint.getX() - targetNode.getPosition().x;
                targetPort.getPosition().y = targetPoint.getY() - targetNode.getPosition().y;
                targetPort.setSide(calcPortSide(targetNode, targetPort));
            }
        }

        // create a layered edge
        LEdge newEdge = new LEdge();
        newEdge.setProperty(Properties.ORIGIN, kedge);
        newEdge.setSource(sourcePort);
        newEdge.setTarget(targetPort);

        // transform the edge's labels
        for (KLabel klabel : kedge.getLabels()) {
            KShapeLayout labelLayout = klabel.getData(KShapeLayout.class);
            LLabel newLabel = new LLabel(klabel.getText());
            newLabel.getPosition().x = labelLayout.getXpos();
            newLabel.getPosition().y = labelLayout.getYpos();
            newLabel.getSize().x = labelLayout.getWidth();
            newLabel.getSize().y = labelLayout.getHeight();
            newLabel.setProperty(Properties.ORIGIN, klabel);
            newEdge.getLabels().add(newLabel);
        }

        // set properties of the new edge
        newEdge.copyProperties(edgeLayout);
    }

    /**
     * Calculate the port side from the relative position.
     * 
     * @param node
     *            a node
     * @param port
     *            a port of that node
     * @return the side of the node on which the port is situated
     */
    private static PortSide calcPortSide(final LNode node, final LPort port) {
        double widthPercent = port.getPosition().x / node.getSize().x;
        double heightPercent = port.getPosition().y / node.getSize().y;
        if (widthPercent + heightPercent <= 1 && widthPercent - heightPercent <= 0) {
            // port is on the left
            return PortSide.WEST;
        } else if (widthPercent + heightPercent >= 1 && widthPercent - heightPercent >= 0) {
            // port is on the right
            return PortSide.EAST;
        } else if (heightPercent < 1.0f / 2) {
            // port is on the top
            return PortSide.NORTH;
        } else {
            // port is on the bottom
            return PortSide.SOUTH;
        }
    }

    /**
     * Transforms a compound graph recursively.
     * 
     * @param currentNode
     *            current node.
     * @param layeredNodes
     *            the list of nodes to add dummy nodes to.
     * @param layeredGraph
     *            the layered graph.
     * @param elemMap
     *            the element map that maps the original {@code KGraph} elements to the transformed
     *            {@code LGraph} elements.
     * @param graphProperties
     *            graph properties updated during the transformation
     */
    private void recursiveTransformCompoundGraph(final KNode currentNode,
            final List<LNode> layeredNodes, final LayeredGraph layeredGraph,
            final Map<KGraphElement, LGraphElement> elemMap,
            final EnumSet<GraphProperties> graphProperties) {

        if (currentNode.getChildren().isEmpty()) {
            transformNode(currentNode, layeredNodes, elemMap, graphProperties);
        } else {
            transformCompoundNodeWithEdges(currentNode, layeredNodes, layeredGraph, elemMap,
                    graphProperties);
            for (KNode child : currentNode.getChildren()) {
                recursiveTransformCompoundGraph(child, layeredNodes, layeredGraph, elemMap,
                        graphProperties);
            }
        }

    }

    /**
     * Transforms all edges of a compound node and replaces the node and its ports by upper and
     * lower dummy nodes and their ports. Representing a compound node by upper and lower dummy
     * nodes is inspired by Georg Sander, Layout of Compound Directed Graphs, technical report
     * A/03/96, Universität des Saarlandes, 1996. Sander's approach is extended to the use of
     * multiple dummy nodes in the case of the presence of ports.
     * 
     * @param node
     *            node to be replaced by border dummies.
     * @param layeredNodes
     *            list the dummy nodes are to be added to.
     * @param layeredGraph
     *            the layered graph.
     * @param elemMap
     *            the element map that maps the original {@code KGraph} elements to the transformed
     *            {@code LGraph} elements.
     * @param graphProperties
     *            graph properties updated during the transformation.
     * 
     */
    private void transformCompoundNodeWithEdges(final KNode node, final List<LNode> layeredNodes,
            final LayeredGraph layeredGraph, final Map<KGraphElement, LGraphElement> elemMap,
            final EnumSet<GraphProperties> graphProperties) {
        // Iterate incoming and outgoing edges, transform them and create dummy nodes and ports
        // representing the node.
        transformIncomingEdges(node, layeredNodes, layeredGraph, elemMap);
        transformOutgoingEdges(node, layeredNodes, layeredGraph, elemMap);
    }

    private void transformOutgoingEdges(final KNode node, final List<LNode> layeredNodes,
            final LayeredGraph layeredGraph, final Map<KGraphElement, LGraphElement> elemMap) {
        EList<KEdge> outEdgesList = node.getOutgoingEdges();
        // Set variable representative = null, if there is no lower dummy node for this node yet.
        LNode representative = null;
        for (LNode lNode : layeredNodes) {
            KNode origin = (KNode) lNode.getProperty(Properties.ORIGIN);
            if (origin.equals(node)
                    && lNode.getProperty(Properties.NODE_TYPE).equals(
                            NodeType.LOWER_COMPOUND_BORDER)) {
                representative = lNode;
            }
        }
        // Iterate over the node's outgoing edges. Transform them, if they have not been transformed
        // before.
        // Create dummy nodes on right side of the Node (lower dummy nodes) and ports for incoming
        // edges, if none present, create one lower dummy node.
        if (!outEdgesList.isEmpty()) {
            for (KEdge kEdge : outEdgesList) {
                // Create a corresponding LEdge for each KEdge in the List that has no
                // representative yet.
                LEdge lEdge = null;
                if (!elemMap.containsKey(kEdge)) {
                    lEdge = createLEdgeFromKEdge(kEdge, elemMap);
                    elemMap.put(kEdge, lEdge);
                } else {
                    lEdge = (LEdge) elemMap.get(kEdge);
                }
                // Create a corresponding LEdge for each
                // Create a lower dummy node and its ports, if one of two
                // conditions holds:
                // 1. The edge has a TargetPort
                // 2. The edge has no TargetPort and the one dummy node and port needed to connect
                // all edges of this kind has not yet been created.
                // Set source for the corresponding LEdge accordingly.

                KPort sourcePort = kEdge.getSourcePort();
                if ((sourcePort != null) || (representative != null)) {
                    LNode dummyNode = createBorderDummyNode(node, NodeType.LOWER_COMPOUND_BORDER);
                    // If edge points to inside the Node, it is connected to the western port,
                    // else to the eastern
                    LPort dummyPort = null;
                    if (isDescendant(node, kEdge.getTarget())) {
                        Iterator<LPort> eastPortIterator = dummyNode.getPorts(PortSide.WEST)
                                .iterator();
                        dummyPort = eastPortIterator.next();
                    } else {
                        Iterator<LPort> westPortIterator = dummyNode.getPorts(PortSide.EAST)
                                .iterator();
                        dummyPort = westPortIterator.next();
                    }
                    dummyPort.setProperty(Properties.ORIGIN, sourcePort);
                    layeredNodes.add(dummyNode);
                    if (sourcePort != null) {
                        elemMap.put(sourcePort, dummyPort);
                    }
                    lEdge.setSource(dummyPort);
                    // In the case that there are no ports and the dummy node for incoming edges has
                    // already been created, set its western port or eastern port as source for the
                    // edge, depending on whether the edge is from a descendant of the node
                    // (eastern)
                    // or not.
                } else {
                    LNode lowerDummy = createBorderDummyNode(node, NodeType.LOWER_COMPOUND_BORDER);
                    LPort dummyPort = null;
                    if (isDescendant(node, kEdge.getSource())) {
                        Iterator<LPort> eastPortIterator = lowerDummy.getPorts(PortSide.WEST)
                                .iterator();
                        dummyPort = eastPortIterator.next();
                    } else {
                        Iterator<LPort> westPortIterator = lowerDummy.getPorts(PortSide.EAST)
                                .iterator();
                        dummyPort = westPortIterator.next();
                    }
                    lEdge.setSource(dummyPort);
                }
            }
        }
    }

    private void transformIncomingEdges(final KNode node, final List<LNode> layeredNodes,
            final LayeredGraph layeredGraph, final Map<KGraphElement, LGraphElement> elemMap) {
        EList<KEdge> inEdgesList = node.getIncomingEdges();
        // Iterate over the node's incoming edges. Transform them, if they have not been transformed
        // before
        // Create dummy nodes on left side of the Node (upper dummy nodes) and ports for incoming
        // edges, if none present, create one upper dummy node.
        if (!inEdgesList.isEmpty()) {
            for (KEdge kEdge : inEdgesList) {
                // Create a corresponding LEdge for each KEdge in the List that has no
                // representative yet.
                LEdge lEdge = null;
                if (!elemMap.containsKey(kEdge)) {
                    lEdge = createLEdgeFromKEdge(kEdge, elemMap);
                    elemMap.put(kEdge, lEdge);
                } else {
                    lEdge = (LEdge) elemMap.get(kEdge);
                }

                // Create a corresponding LEdge for each
                // Create an upper dummy node and its ports for the incoming edge, if one of two
                // conditions holds:
                // 1. The edge has a TargetPort
                // 2. The edge has no TargetPort and the one dummy node and port needed to connect
                // all edges of this kind has not yet been created.
                // Set source for the corresponding LEdge accordingly.
                KPort targetPort = kEdge.getTargetPort();
                if ((targetPort != null)
                        || (!((elemMap.containsKey(node)) && (elemMap.get(node) instanceof LNode)))) {
                    LNode dummyNode = createBorderDummyNode(node, NodeType.UPPER_COMPOUND_BORDER);
                    // If edge originates from inside the Node, it is connected to the eastern port,
                    // else to the western
                    LPort dummyPort = null;
                    if (isDescendant(node, kEdge.getSource())) {
                        Iterator<LPort> eastPortIterator = dummyNode.getPorts(PortSide.EAST)
                                .iterator();
                        dummyPort = eastPortIterator.next();
                    } else {
                        Iterator<LPort> westPortIterator = dummyNode.getPorts(PortSide.WEST)
                                .iterator();
                        dummyPort = westPortIterator.next();
                    }
                    dummyPort.setProperty(Properties.ORIGIN, targetPort);
                    layeredNodes.add(dummyNode);
                    if (targetPort != null) {
                        elemMap.put(targetPort, dummyPort);
                    } else {
                        elemMap.put(node, dummyNode);
                    }
                    lEdge.setSource(dummyPort);
                    // In the case that there are no ports and the dummy node for incoming edges has
                    // already been created, set its western port or eastern port as source for the
                    // edge, depending on whether the edge is from a descendant of the node
                    // (eastern)
                    // or not.
                } else {
                    LNode representative = (LNode) elemMap.get(node);
                    LPort dummyPort = null;
                    if (isDescendant(node, kEdge.getSource())) {
                        Iterator<LPort> eastPortIterator = representative.getPorts(PortSide.EAST)
                                .iterator();
                        dummyPort = eastPortIterator.next();
                    } else {
                        Iterator<LPort> westPortIterator = representative.getPorts(PortSide.WEST)
                                .iterator();
                        dummyPort = westPortIterator.next();
                    }
                    lEdge.setSource(dummyPort);
                }
            }

            // If there is no incoming edge, a single upper border dummy node is created.
        } else {
            LNode dummyNode = createBorderDummyNode(node, NodeType.UPPER_COMPOUND_BORDER);
            layeredNodes.add(dummyNode);
            elemMap.put(node, dummyNode);
        }
    }

    /**
     * Transforms the edges of the KGraph, associates them with the new Ports - possibly ports of
     * dummy nodes replacing the KNodes. Adds dummy edges between border dummy nodes and their
     * children.
     * 
     * @param currentNode
     * @param elemMap
     * @param layeredNodes
     */
    private void recursiveTransformEdges(final KNode currentNode,
            final Map<KGraphElement, LGraphElement> elemMap, final List<LNode> layeredNodes) {
        // TODO Fill in method body. Call setCompoundDummyEdges for each compound border dummy node.
        // call is to be changed to a conditional one
        setCompoundDummyEdges(currentNode, elemMap, layeredNodes);

    }

    /**
     * Creates dummy edges between a compound node border dummy node and its children.
     * 
     * @param layeredNodes
     * 
     * @param source
     *            the source node.
     * @param layeredNodes
     *            the list of LNodes with the created dummy nodes and the imported nodes.
     */
    private void setCompoundDummyEdges(final KNode layoutNode,
            final Map<KGraphElement, LGraphElement> elemMap, final List<LNode> layeredNodes) {
        // old code for recursive approach
        // if (!layoutNode.getChildren().isEmpty()) {
        // if (elemMap.get(layoutNode) != null) {
        // LNode correspondingNode = (LNode) elemMap.get(layoutNode);
        // for (KNode knode : layoutNode.getChildren()) {
        // if (elemMap.get(knode) != null) {
        // LEdge newEdge = new LEdge();

        // TODO complete method body

        // }
        // }
        // }
        // }
    }

    /**
     * Returns an LEdge as a representative for the given KEdge.
     * 
     * @param kEdge
     *            KEdge for which the LEdge is to be created.
     * @param elemMap
     *            the element map that maps the original {@code KGraph} elements to the transformed
     *            {@code LGraph} elements.
     * @return returns created LEdge that represents the given KEdge.
     */
    private LEdge createLEdgeFromKEdge(final KEdge kEdge,
            final Map<KGraphElement, LGraphElement> elemMap) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Creates a border dummy node in context of the representation of a compound node. Adds a
     * western and an eastern port.
     * 
     * @param node
     *            The node to be represented.
     * @param upperBorder
     *            Denotes, if an upper border node is to be created, if not, a lower border node
     *            will be created.
     */
    private LNode createBorderDummyNode(final KNode node, final NodeType nodetype) {
        KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
        LNode dummyNode = new LNode();
        dummyNode.setProperty(Properties.ORIGIN, node);
        dummyNode.setProperty(Properties.PARENT, node.getParent());
        dummyNode.getPosition().x = nodeLayout.getXpos();
        dummyNode.setProperty(Properties.NODE_TYPE, nodetype);
        createBorderDummyPort(dummyNode, PortSide.EAST);
        createBorderDummyPort(dummyNode, PortSide.WEST);
        return dummyNode;
    }

    /**
     * Creates a port for an LNode with the given PortSide.
     * 
     * @param dummyNode
     *            the node the port is to be created for.
     * @param side
     *            the side of the node, on which the port is to be located.
     */
    private void createBorderDummyPort(final LNode node, final PortSide side) {
        LPort dummyPort = new LPort();
        dummyPort.setSide(side);
        dummyPort.setNode(node);
        node.getPorts().add(dummyPort);
    }

    /**
     * Checks, if a KNode is descendant of another in the inclusion tree. attention: returns false,
     * if nodes are equal.
     * 
     * @param node
     *            node that is the possible ancestor.
     * @param candidate
     *            node that is the possible descendant.
     * @return returns a boolean value indicating, if candidate is descendant of node (false if
     *         nodes are equal).
     */
    private boolean isDescendant(final KNode node, final KNode candidate) {
        // TODO: complete method body
        return false;
    }

    // /////////////////////////////////////////////////////////////////////////////
    // Apply Layout Results

    /**
     * {@inheritDoc}
     */
    public void applyLayout(final LayeredGraph layeredGraph) {
        KNode target = (KNode) layeredGraph.getProperty(Properties.ORIGIN);

        // determine the border spacing, which influences the offset
        KShapeLayout parentLayout = target.getData(KShapeLayout.class);
        float borderSpacing = layeredGraph.getProperty(LayoutOptions.BORDER_SPACING);

        // calculate the offset
        KVector offset = new KVector(borderSpacing + layeredGraph.getOffset().x, borderSpacing
                + layeredGraph.getOffset().y);

        // along the way, we collect the list of edges to be processed later
        List<LEdge> edgeList = new LinkedList<LEdge>();

        // process the nodes
        for (LNode lnode : layeredGraph.getLayerlessNodes()) {
            Object origin = lnode.getProperty(Properties.ORIGIN);

            if (origin instanceof KNode) {
                // set the node position
                KShapeLayout nodeLayout = ((KNode) origin).getData(KShapeLayout.class);

                nodeLayout.setXpos((float) (lnode.getPosition().x + offset.x));
                nodeLayout.setYpos((float) (lnode.getPosition().y + offset.y));

                // set port positions
                if (!nodeLayout.getProperty(LayoutOptions.PORT_CONSTRAINTS).isPosFixed()) {
                    for (LPort lport : lnode.getPorts()) {
                        origin = lport.getProperty(Properties.ORIGIN);
                        if (origin instanceof KPort) {
                            KShapeLayout portLayout = ((KPort) origin).getData(KShapeLayout.class);
                            portLayout
                                    .setXpos((float) (lport.getPosition().x - lport.getSize().x / 2.0));
                            portLayout
                                    .setYpos((float) (lport.getPosition().y - lport.getSize().y / 2.0));
                        }
                    }
                }
            } else if (origin instanceof KPort) {
                // It's an external port. Set its position
                KShapeLayout portLayout = ((KPort) origin).getData(KShapeLayout.class);
                KVector portPosition = getExternalPortPosition(layeredGraph, lnode,
                        portLayout.getWidth(), portLayout.getHeight());

                portLayout.setXpos((float) portPosition.x);
                portLayout.setYpos((float) portPosition.y);
            }

            // collect edges
            for (LPort port : lnode.getPorts()) {
                edgeList.addAll(port.getOutgoingEdges());
            }
        }

        // check if the edge routing uses splines
        EdgeRoutingStrategy routing = parentLayout.getProperty(Properties.EDGE_ROUTING);
        boolean splinesActive = routing == EdgeRoutingStrategy.SIMPLE_SPLINES
                || routing == EdgeRoutingStrategy.COMPLEX_SPLINES;

        // iterate through all edges
        for (LEdge ledge : edgeList) {
            KEdge kedge = (KEdge) ledge.getProperty(Properties.ORIGIN);
            KEdgeLayout edgeLayout = kedge.getData(KEdgeLayout.class);
            KVectorChain bendPoints = ledge.getBendPoints();

            // add the source port and target port positions to the vector chain
            LPort sourcePort = ledge.getSource();
            bendPoints.addFirst(KVector.add(sourcePort.getPosition(), sourcePort.getNode()
                    .getPosition()));
            LPort targetPort = ledge.getTarget();
            bendPoints.addLast(KVector.add(targetPort.getPosition(), targetPort.getNode()
                    .getPosition()));

            // translate the bend points by the offset and apply the bend points
            bendPoints.translate(offset);
            KimlUtil.applyVectorChain(edgeLayout, bendPoints);

            // apply layout to labels
            for (LLabel label : ledge.getLabels()) {
                KLabel klabel = (KLabel) label.getProperty(Properties.ORIGIN);
                KShapeLayout klabelLayout = klabel.getData(KShapeLayout.class);

                KVector labelPos = new KVector(ledge.getSource().getPosition().x, ledge.getSource()
                        .getPosition().y);
                labelPos.add(ledge.getSource().getNode().getPosition());
                labelPos.add(label.getPosition());
                klabelLayout.setXpos((float) (labelPos.x + offset.x));
                klabelLayout.setYpos((float) (labelPos.y + offset.y));
            }

            // set spline option
            if (splinesActive) {
                edgeLayout.setProperty(LayoutOptions.EDGE_ROUTING, EdgeRouting.SPLINES);
            }
        }

        // set up the parent node
        KInsets insets = parentLayout.getInsets();
        float width = (float) layeredGraph.getSize().x + 2 * borderSpacing + insets.getLeft()
                + insets.getRight();
        float height = (float) layeredGraph.getSize().y + 2 * borderSpacing + insets.getTop()
                + insets.getBottom();
        if (layeredGraph.getProperty(Properties.GRAPH_PROPERTIES).contains(
                GraphProperties.EXTERNAL_PORTS)) {
            // ports have been positioned using dummy nodes
            parentLayout.setProperty(LayoutOptions.PORT_CONSTRAINTS, PortConstraints.FIXED_POS);
            KimlUtil.resizeNode(target, width, height, false);
        } else {
            // ports have not been positioned yet - leave this for next layouter
            KimlUtil.resizeNode(target, width, height, true);
        }
        parentLayout.setProperty(LayoutOptions.FIXED_SIZE, true);
    }

}
