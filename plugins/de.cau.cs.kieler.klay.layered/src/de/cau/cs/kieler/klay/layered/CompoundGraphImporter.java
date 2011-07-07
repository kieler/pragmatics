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

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.math.KVectorChain;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.Direction;
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
import de.cau.cs.kieler.klay.layered.properties.EdgeType;
import de.cau.cs.kieler.klay.layered.properties.GraphProperties;
import de.cau.cs.kieler.klay.layered.properties.NodeType;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * Manages the transformation of Compound KGraphs to LayeredGraphs.
 * 
 * @author ima
 * @author cds
 */
public class CompoundGraphImporter extends AbstractGraphImporter<KNode> {

    /**
     * {@inheritDoc}
     */
    public LayeredGraph importGraph(final KNode kgraph) {
        LayeredGraph layeredGraph = new LayeredGraph();
        layeredGraph.setProperty(Properties.ORIGIN, kgraph);

        KShapeLayout sourceShapeLayout = kgraph.getData(KShapeLayout.class);

        // copy the properties of the KGraph to the layered graph and check values
        layeredGraph.copyProperties(sourceShapeLayout);
        layeredGraph.checkProperties(Properties.OBJ_SPACING, Properties.BORDER_SPACING,
                Properties.THOROUGHNESS, Properties.ASPECT_RATIO);
        if (layeredGraph.getProperty(LayoutOptions.DIRECTION) == Direction.UNDEFINED) {
            layeredGraph.setProperty(LayoutOptions.DIRECTION, Direction.RIGHT);
        }

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

        List<LNode> layeredNodes = layeredGraph.getLayerlessNodes();

        graphProperties.add(GraphProperties.FLAT_HIERARCHICAL);
        // Prepare a map to insert Parent Nodes and Child nodes for the documentation of dummy
        // edges.
        Map<LNode, List<LNode>> parentChildMap = new HashMap<LNode, List<LNode>>();
        recursiveTransformCompoundGraph(kgraph, kgraph, layeredNodes, layeredGraph, elemMap,
                graphProperties, parentChildMap);

        // set the graph properties property
        layeredGraph.setProperty(Properties.GRAPH_PROPERTIES, graphProperties);

        return layeredGraph;
    }

    /**
     * Transforms a compound graph.
     * 
     * @param graph
     *            the KGraph to be transformed.
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

    public void transformCompoundGraph(final KNode graph, final List<LNode> layeredNodes,
            final LayeredGraph layeredGraph, final Map<KGraphElement, LGraphElement> elemMap,
            final EnumSet<GraphProperties> graphProperties) {
        // Comment next line in to make the dummy edges be removed after Layering
        graphProperties.add(GraphProperties.FLAT_HIERARCHICAL);
        // Prepare a map to insert Parent Nodes and Child nodes for the documentation of dummy
        // edges.
        Map<LNode, List<LNode>> parentChildMap = new HashMap<LNode, List<LNode>>();
        recursiveTransformCompoundGraph(graph, graph, layeredNodes, layeredGraph, elemMap,
                graphProperties, parentChildMap);
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
     * @param parentChildMap
     *            Map to document set dummy edges
     */
    void recursiveTransformCompoundGraph(final KNode graph, final KNode currentNode,
            final List<LNode> layeredNodes, final LayeredGraph layeredGraph,
            final Map<KGraphElement, LGraphElement> elemMap,
            final EnumSet<GraphProperties> graphProperties,
            final Map<LNode, List<LNode>> parentChildMap) {
        if (currentNode.getChildren().isEmpty()) {
            transformLeaveNode(currentNode, layeredNodes, elemMap, graphProperties,
                    layeredGraph.getProperty(LayoutOptions.DIRECTION));
            transformLeaveEdges(currentNode, elemMap);
        } else {
            for (KNode child : currentNode.getChildren()) {
                recursiveTransformCompoundGraph(graph, child, layeredNodes, layeredGraph, elemMap,
                        graphProperties, parentChildMap);
            }
            if (currentNode != graph) {
                transformCompoundNodeWithEdges(currentNode, layeredNodes, layeredGraph, elemMap);
                setCompoundDummyEdges(layeredNodes, parentChildMap);
            } else {
                System.out.println("The layoutNode has been reached in recursion.");
            }
        }
    }

    /**
     * Transform leave node. Almost the same as transformNode of the KGraphImporter with the
     * difference that ports for the connection of dummy edges for the layering are provided.
     * 
     * @param node
     *            Node to be transformed.
     * @param layeredNodes
     *            List to add representative to.
     * @param elemMap
     *            the element map that maps the original {@code KGraph} elements to the transformed
     *            {@code LGraph} elements.
     * @param graphProperties
     *            graph properties updated during the transformation.
     * @param direction
     *            overall layout direction
     */
    private void transformLeaveNode(final KNode node, final List<LNode> layeredNodes,
            final Map<KGraphElement, LGraphElement> elemMap,
            final EnumSet<GraphProperties> graphProperties, final Direction direction) {
        // add a new node to the layered graph, copying its size
        KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
        if (!nodeLayout.getProperty(LayoutOptions.FIXED_SIZE)) {
            KimlUtil.resizeNode(node);
        }

        LNode newNode = new LNode();
        newNode.setProperty(Properties.ORIGIN, node);
        newNode.setProperty(Properties.PARENT, node.getParent());
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
            PortSide newPortSide = KimlUtil.calcPortSide(kport, direction);
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

        // Add ports to connect dummy edges for the layering phase.
        LPort dummyPortWest = createDummyPort(newNode, PortSide.WEST);
        dummyPortWest.setProperty(Properties.LEAVE_DUMMY_PORT, true);
        LPort dummyPortEast = createDummyPort(newNode, PortSide.EAST);
        dummyPortEast.setProperty(Properties.LEAVE_DUMMY_PORT, true);
    }

    /**
     * Transforms the Edges of a leave node.
     * 
     * @param knode
     *            Node whose edges are to be transformed.
     * @param elemMap
     *            Map to store pairs of the original elements of the KGraph and their
     *            representatives in the LGraph.
     */
    private void transformLeaveEdges(final KNode knode,
            final Map<KGraphElement, LGraphElement> elemMap) {
        for (KEdge edge : knode.getIncomingEdges()) {
            KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
            LEdge newEdge = null;
            if (elemMap.containsKey(edge)) {
                newEdge = (LEdge) elemMap.get(edge);
            } else {
                newEdge = createLEdgeFromKEdge(edge, elemMap);
            }
            LNode representative = (LNode) elemMap.get(knode);
            if (edge.getTargetPort() == null) {
                LPort newPort = createDummyPort(representative, PortSide.WEST);
                KPoint targetPoint = edgeLayout.getTargetPoint();
                newPort.getPosition().x = targetPoint.getX() - representative.getPosition().x;
                newPort.getPosition().y = targetPoint.getY() - representative.getPosition().y;
                newEdge.setTarget(newPort);
            } else {
                LPort port = (LPort) elemMap.get(edge.getTargetPort());
                newEdge.setTarget(port);
            }
        }
        for (KEdge edge : knode.getOutgoingEdges()) {
            KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
            LEdge newEdge = null;
            if (elemMap.containsKey(edge)) {
                newEdge = (LEdge) elemMap.get(edge);
            } else {
                newEdge = createLEdgeFromKEdge(edge, elemMap);
            }
            LNode representative = (LNode) elemMap.get(knode);
            if (edge.getSourcePort() == null) {
                LPort newPort = createDummyPort(representative, PortSide.EAST);
                KPoint sourcePoint = edgeLayout.getSourcePoint();
                newPort.getPosition().x = sourcePoint.getX() - representative.getPosition().x;
                newPort.getPosition().y = sourcePoint.getY() - representative.getPosition().y;
                newEdge.setSource(newPort);
            } else {
                LPort port = (LPort) elemMap.get(edge.getSourcePort());
                newEdge.setSource(port);
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
     * 
     */
    private void transformCompoundNodeWithEdges(final KNode node, final List<LNode> layeredNodes,
            final LayeredGraph layeredGraph, final Map<KGraphElement, LGraphElement> elemMap) {
        // While transforming the edges and creating dummy nodes, keep a list of the dummies.
        List<LNode> dummyNodes = new LinkedList<LNode>();
        // Iterate incoming and outgoing edges, transform them and create dummy nodes and ports
        // representing the node.
        transformCompoundEdgeList(node, layeredNodes, elemMap, dummyNodes, node.getIncomingEdges(),
                true);
        transformCompoundEdgeList(node, layeredNodes, elemMap, dummyNodes, node.getOutgoingEdges(),
                false);
    }

    /**
     * Iterate the given list of edges of a node. Create an LEdge for each KEdge that has no
     * representative yet. Create dummy nodes with ports for each edge with a target port, a single
     * compound border for all incoming edges without ports or in case there are no incoming edges.
     * Edges from inside the node will be connected to lower dummy nodes, all others to upper dummy
     * nodes. Either to be invoked with a List of incoming or one of outgoing edges as a parameter,
     * indicate whether it is the one or the other by setting the incoming flag in the parameter
     * list.
     * 
     * @param node
     *            The node to be replaced by upper dummy nodes.
     * @param layeredNodes
     *            The List the dummy nodes are to be added to.
     * @param elemMap
     *            The element map that maps the original {@code KGraph} elements to the transformed
     *            {@code LGraph} elements.
     * @param dummyNodes
     *            List to document already created dummy nodes.
     * @param edgesList
     *            List of the edges to transform.
     * @param incoming
     *            True, if the List is a List of incoming edges, false if it is a List of outgoing
     *            edges.
     */
    private void transformCompoundEdgeList(final KNode node, final List<LNode> layeredNodes,
            final Map<KGraphElement, LGraphElement> elemMap, final List<LNode> dummyNodes,
            final List<KEdge> edgesList, final boolean incoming) {

        for (KEdge kEdge : edgesList) {
            KEdgeLayout edgeLayout = kEdge.getData(KEdgeLayout.class);

            // Create a corresponding LEdge for each KEdge in the List that has no
            // representative yet.
            LEdge lEdge = null;
            if (!elemMap.containsKey(kEdge)) {
                lEdge = createLEdgeFromKEdge(kEdge, elemMap);
            } else {
                lEdge = (LEdge) elemMap.get(kEdge);
            }

            // LNode to represent the node for this edge in the LGraph
            LNode representative = null;

            // Check, whether the edge is from inside the node or not
            boolean fromInside = false;
            if (incoming) {
                fromInside = isDescendantNotSelf(node, kEdge.getSource());
            } else {
                // The handling of outgoing edges is inverse to the handling of incoming edges.
                // So the reverse boolean value is needed.
                fromInside = !(isDescendantNotSelf(node, kEdge.getTarget()));
            }

            // Get the adequate port of current edge, null, if there is none
            KPort port = null;
            if (incoming) {
                port = kEdge.getTargetPort();
            } else {
                port = kEdge.getSourcePort();
            }

            // If edge has no target port, create a border dummy node resp. reuse the one
            // created
            // before
            if (port == null) {
                if (fromInside) {
                    representative = createBorderDummyNode(node, NodeType.LOWER_COMPOUND_BORDER,
                            dummyNodes);
                } else {
                    representative = createBorderDummyNode(node, NodeType.UPPER_COMPOUND_BORDER,
                            dummyNodes);
                }

                // If edge has a target port, create adequate compound port dummy node
            } else {
                if (fromInside) {
                    representative = createBorderDummyNode(node, NodeType.LOWER_COMPOUND_PORT,
                            dummyNodes);
                } else {
                    representative = createBorderDummyNode(node, NodeType.UPPER_COMPOUND_PORT,
                            dummyNodes);
                }
            }
            if (!layeredNodes.contains(representative)) {
                layeredNodes.add(representative);
            }

            // Connect the edge to the dummy node, to western port for incoming edges, to
            // eastern for outgoing ones
            LPort dummyPort = null;
            PortSide portSide = null;
            if (incoming) {
                portSide = PortSide.WEST;
            } else {
                portSide = PortSide.EAST;
            }
            Iterator<LPort> portIterator = representative.getPorts(portSide).iterator();
            dummyPort = portIterator.next();

            if (incoming) {
                KPoint targetPoint = edgeLayout.getTargetPoint();
                dummyPort.getPosition().x = targetPoint.getX() - representative.getPosition().x;
                // dummyPort.getPosition().y = targetPoint.getY() - representative.getPosition().y;
                lEdge.setTarget(dummyPort);
            } else {
                KPoint sourcePoint = edgeLayout.getSourcePoint();
                dummyPort.getPosition().x = sourcePoint.getX() - representative.getPosition().x;
                // dummyPort.getPosition().y = sourcePoint.getY() - representative.getPosition().y;
                lEdge.setSource(dummyPort);
            }

            if (port != null) {
                elemMap.put(port, dummyPort);
                dummyPort.setProperty(Properties.ORIGIN, port);
            }
        }

        // If not done before (if the edge list is empty or containing only edges to/from
        // descendants, a single border dummy node is created.

        NodeType nodeType = null;
        if (incoming) {
            nodeType = NodeType.UPPER_COMPOUND_BORDER;
        } else {
            nodeType = NodeType.LOWER_COMPOUND_BORDER;
        }
        LNode dummyNode = createBorderDummyNode(node, nodeType, dummyNodes);
        if (!(layeredNodes.contains(dummyNode))) {
            layeredNodes.add(dummyNode);
        }
    }

    /**
     * Creates dummy edges between a compound node border dummy node and its children.
     * 
     * @param layeredNodes
     * @param layeredNodes
     *            the list of LNodes with the created dummy nodes and the imported nodes.
     * @param source
     *            the source node.
     */
    private void setCompoundDummyEdges(final List<LNode> layeredNodes,
            final Map<LNode, List<LNode>> parentChildMap) {
        for (LNode lNode : layeredNodes) {
            NodeType nodeType = lNode.getProperty(Properties.NODE_TYPE);
            switch (nodeType) {
            // If the node is a compound dummy node at the upper line of the compound node, add
            // edge
            // to every child node
            case UPPER_COMPOUND_BORDER:
            case UPPER_COMPOUND_PORT:
                for (LNode childCandidate : layeredNodes) {
                    if ((childCandidate.getProperty(Properties.PARENT) != null)
                            && (childCandidate.getProperty(Properties.PARENT) == (lNode
                                    .getProperty(Properties.ORIGIN)))) {
                        if (!(parentChildMap.containsKey(lNode))
                                || !(parentChildMap.get(lNode).contains(childCandidate))) {
                            LEdge dummyEdge = new LEdge();
                            dummyEdge.setProperty(Properties.EDGE_TYPE, EdgeType.COMPOUND_DUMMY);

                            LPort sourcePort = lNode.getPorts(PortSide.EAST).iterator().next();
                            dummyEdge.setSource(sourcePort);

                            LPort targetPort = findDummyEdgePort(childCandidate, PortSide.WEST);
                            dummyEdge.setTarget(targetPort);
                            if (parentChildMap.containsKey(lNode)) {
                                parentChildMap.get(lNode).add(childCandidate);
                            } else {
                                List<LNode> list = new LinkedList<LNode>();
                                list.add(childCandidate);
                                parentChildMap.put(lNode, list);
                            }
                        }
                    }
                }
                break;
            // If the node is a compound dummy node at the lower line of the compound node,
            // add
            // edge from every child node to this node
            case LOWER_COMPOUND_BORDER:
            case LOWER_COMPOUND_PORT:
                for (LNode childCandidate : layeredNodes) {
                    if ((childCandidate.getProperty(Properties.PARENT) != null)
                            && (childCandidate.getProperty(Properties.PARENT) == (lNode
                                    .getProperty(Properties.ORIGIN)))) {
                        if (!(parentChildMap.containsKey(lNode))
                                || !(parentChildMap.get(lNode).contains(childCandidate))) {
                            LEdge dummyEdge = new LEdge();
                            dummyEdge.setProperty(Properties.EDGE_TYPE, EdgeType.COMPOUND_DUMMY);

                            LPort sourcePort = findDummyEdgePort(childCandidate, PortSide.EAST);
                            dummyEdge.setSource(sourcePort);

                            LPort targetPort = lNode.getPorts(PortSide.WEST).iterator().next();
                            dummyEdge.setTarget(targetPort);

                            if (parentChildMap.containsKey(lNode)) {
                                parentChildMap.get(lNode).add(childCandidate);
                            } else {
                                List<LNode> list = new LinkedList<LNode>();
                                list.add(childCandidate);
                                parentChildMap.put(lNode, list);
                            }
                        }
                    }
                }
                break;
            // If the node is no compound dummy node, nothing is to be done.
            default:
                break;

            }
        }
    }

    /**
     * Returns an LEdge as a representative for the given KEdge. Nodes and Ports for source and
     * target are not set. They are to be handled by calling methods.
     * 
     * @param kedge
     *            KEdge for which the LEdge is to be created.
     * @param elemMap
     *            the element map that maps the original {@code KGraph} elements to the transformed
     *            {@code LGraph} elements.
     * @return returns created LEdge that represents the given KEdge.
     */
    private LEdge createLEdgeFromKEdge(final KEdge kedge,
            final Map<KGraphElement, LGraphElement> elemMap) {
        KEdgeLayout edgeLayout = kedge.getData(KEdgeLayout.class);

        // create the representative edge
        LEdge newEdge = new LEdge();
        newEdge.setProperty(Properties.ORIGIN, kedge);

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

        // copy the edge properties
        newEdge.copyProperties(edgeLayout);
        elemMap.put(kedge, newEdge);

        return newEdge;
    }

    /**
     * Creates a border dummy node in context of the representation of a compound node. Adds a
     * western and an eastern port. In case a lower or upper compound dummy node is asked for,
     * checks whether there is one already in the given list. If yes, that one will be returned, if
     * not, a new dummy node is created.
     * 
     * @param node
     *            The node to be represented.
     * @param upperBorder
     *            Denotes, if an upper border node is to be created, if not, a lower border node
     *            will be created.
     */
    private LNode createBorderDummyNode(final KNode node, final NodeType nodeType,
            final List<LNode> dummyList) {
        LNode dummyNode = null;
        if ((nodeType == NodeType.LOWER_COMPOUND_BORDER)
                || (nodeType == NodeType.UPPER_COMPOUND_BORDER)) {
            for (LNode dummy : dummyList) {
                if (dummy.getProperty(Properties.NODE_TYPE) == nodeType) {
                    dummyNode = dummy;
                }
            }
        }
        if (dummyNode == null) {
            KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
            dummyNode = new LNode();
            dummyNode.setProperty(Properties.ORIGIN, node);
            dummyNode.setProperty(Properties.PARENT, node.getParent());
            dummyNode.getPosition().x = nodeLayout.getXpos();
            dummyNode.setProperty(Properties.NODE_TYPE, nodeType);
            createDummyPort(dummyNode, PortSide.EAST);
            createDummyPort(dummyNode, PortSide.WEST);
            dummyList.add(dummyNode);
        }
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
    private LPort createDummyPort(final LNode node, final PortSide side) {

        LPort dummyPort = new LPort();
        dummyPort.setSide(side);
        dummyPort.setNode(node);
        return dummyPort;
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
    private boolean isDescendantNotSelf(final KNode node, final KNode candidate) {
        List<KNode> descendantsList = new LinkedList<KNode>();
        listDescendants(node, descendantsList);
        if (node.equals(candidate)) {
            return false;
        } else {
            return descendantsList.contains(candidate);
        }
    }

    /**
     * Checks, if a KNode is descendant of another in the inclusion tree. attention: returns true,
     * if nodes are equal.
     * 
     * @param node
     *            node that is the possible ancestor.
     * @param candidate
     *            node that is the possible descendant.
     * @return returns a boolean value indicating, if candidate is descendant of node (false if
     *         nodes are equal).
     */
    public static boolean isDescendant(final KNode node, final KNode candidate) {
        List<KNode> descendantsList = new LinkedList<KNode>();
        listDescendants(node, descendantsList);
        return descendantsList.contains(candidate);
    }

    /**
     * Recursively adds descendants of given node to a given list. Adds the startNode as well.
     * 
     * @param currentNode
     *            actual root node of the inclusion tree, whose nodes are to be added to the list.
     * @param descendantsList
     */
    private static void listDescendants(final KNode currentNode, final List<KNode> descendantsList) {
        if (currentNode.getChildren().isEmpty()) {
            descendantsList.add(currentNode);
        } else {
            for (KNode child : currentNode.getChildren()) {
                listDescendants(child, descendantsList);
            }
            descendantsList.add(currentNode);
        }
    }

    /**
     * Finds among the ports of one side of the node the one prepared to connect dummy edges for
     * layering constraints.
     * 
     * @param node
     *            The node who's ports are to be probed.
     * @param side
     *            The side for which the ports are to be probed.
     */
    private LPort findDummyEdgePort(final LNode node, final PortSide side) {
        LPort port = null;
        Iterator<LPort> portIterator = node.getPorts(side).iterator();
        // Find the correct port to connect the edge to: do not use ports that are
        // transformed ports from the original graph or created to connect edges from the original
        // graph.
        while (portIterator.hasNext()) {
            LPort portCandidate = portIterator.next();
            NodeType nodeType = portCandidate.getNode().getProperty(Properties.NODE_TYPE);
            if ((nodeType == (NodeType.UPPER_COMPOUND_BORDER) || portCandidate
                    .getProperty(Properties.LEAVE_DUMMY_PORT))) {
                port = portCandidate;
                break;
            }
        }
        // Do not return null, if there is no applicable port, create one.
        if (port == null) {
            port = createDummyPort(node, side);
        }
        return port;
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
        float borderSpacing = layeredGraph.getProperty(Properties.BORDER_SPACING);

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
            edgeLayout.applyVectorChain(bendPoints);

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
    }

}
