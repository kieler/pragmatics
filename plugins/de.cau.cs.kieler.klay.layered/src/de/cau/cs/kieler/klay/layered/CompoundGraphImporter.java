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

//import java.util.HashMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KLabel;
//import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
//import de.cau.cs.kieler.core.math.KVector;
//import de.cau.cs.kieler.core.math.KVectorChain;
//import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
//import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
//import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
//import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
//import de.cau.cs.kieler.kiml.options.EdgeRouting;
//import de.cau.cs.kieler.kiml.options.LayoutOptions;
//import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.kiml.options.Direction;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.kiml.util.KimlUtil;
//import de.cau.cs.kieler.kiml.util.KimlUtil;
//import de.cau.cs.kieler.klay.layered.graph.Insets;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraphElement;
//import de.cau.cs.kieler.klay.layered.graph.LLabel;
import de.cau.cs.kieler.klay.layered.graph.LLabel;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
//import de.cau.cs.kieler.klay.layered.p5edges.EdgeRoutingStrategy;
import de.cau.cs.kieler.klay.layered.properties.GraphProperties;
import de.cau.cs.kieler.klay.layered.properties.NodeType;
//import de.cau.cs.kieler.klay.layered.properties.NodeType;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * Manages the transformation of Compound KGraphs to LayeredGraphs.
 * 
 * @author ima
 * @author cds
 */
public class CompoundGraphImporter {

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
                transformCompoundNodeWithEdges(currentNode, layeredNodes, layeredGraph, elemMap,
                        graphProperties);
                setCompoundDummyEdges(currentNode, elemMap, layeredNodes, parentChildMap);
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

        // // if we have a hypernode without ports, create a default input and output port
        // if (newNode.getProperty(LayoutOptions.HYPERNODE) && newNode.getPorts().isEmpty()) {
        // LPort inputPort = new LPort();
        // inputPort.setSide(PortSide.WEST);
        // inputPort.setNode(newNode);
        //
        // LPort outputPort = new LPort();
        // outputPort.setSide(PortSide.EAST);
        // outputPort.setNode(newNode);
        // }
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

    /**
     * Iterate the incoming edges of a node. Create an LEdge for each KEdge that has no
     * representative yet. Create dummy nodes with ports - one UPPER_COMPOUND_PORT for each edge
     * with a target port, one UPPER_COMPOUND_BORDER for all incoming edges without ports or in case
     * there are no incoming edges.
     * 
     * @param node
     *            The node to be replaced by upper dummy nodes.
     * @param layeredNodes
     *            The List the dummy nodes are to be added to.
     * @param layeredGraph
     *            The layered graph.
     * @param elemMap
     *            the element map that maps the original {@code KGraph} elements to the transformed
     *            {@code LGraph} elements.
     */
    private void transformIncomingEdges(final KNode node, final List<LNode> layeredNodes,
            final LayeredGraph layeredGraph, final Map<KGraphElement, LGraphElement> elemMap) {
        EList<KEdge> inEdgesList = node.getIncomingEdges();
        if (!inEdgesList.isEmpty()) {
            for (KEdge kEdge : inEdgesList) {
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
                // Check, whether there is a target port for this edge - decide on dummy node type
                // to use
                KPort targetPort = kEdge.getTargetPort();
                // If edge has no target port, check, if there is already an
                // UPPER_COMPOUND_BORDER
                if (targetPort == null) {
                    // If there is none, create one
                    if (!((elemMap.containsKey(node)) && (elemMap.get(node) instanceof LNode))) {
                        representative = createBorderDummyNode(node, NodeType.UPPER_COMPOUND_BORDER);
                        layeredNodes.add(representative);
                        elemMap.put(node, representative);
                        // If the upper border dummy node has already been created, reuse it.
                    } else {
                        representative = (LNode) elemMap.get(node);
                    }
                    // If edge has a target port, create UPPER_COMPOUND_PORT dummy node
                } else {
                    representative = createBorderDummyNode(node, NodeType.UPPER_COMPOUND_PORT);
                    layeredNodes.add(representative);
                }

                // Connect the edge to the upper border dummy node, to western port in general, to
                // eastern one, if edge originates in descendant of node.
                LPort dummyPort = null;
                if (isDescendantNotSelf(node, kEdge.getSource())) {
                    Iterator<LPort> eastPortIterator = representative.getPorts(PortSide.EAST)
                            .iterator();
                    dummyPort = eastPortIterator.next();
                } else {
                    Iterator<LPort> westPortIterator = representative.getPorts(PortSide.WEST)
                            .iterator();
                    dummyPort = westPortIterator.next();
                }
                lEdge.setTarget(dummyPort);
                if (targetPort != null) {
                    elemMap.put(targetPort, dummyPort);
                    dummyPort.setProperty(Properties.ORIGIN, targetPort);
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
     * Iterate the outgoing edges of a node. Create an LEdge for each KEdge that has no
     * representative yet. Create dummy nodes with ports. Handle two cases differently: 1. Edge has
     * a target node that is a descendant of node 2. Edge has a target node that is no descendant of
     * node In both cases, decide whether edge has a source port (create one compound port node per
     * edge - in case 1. UPPER_COMPOUND_PORT, in case 2. LOWER_COMPOUND_PORT) or not (create or use
     * UPPER_COMPOUND_BORDER in case 1., create ore use LOWER_COMPOUND_BORDER in case 2.)
     * 
     * @param node
     *            the node to be replaced by border dummy nodes.
     * @param layeredNodes
     *            The List the dummy nodes are to be added to.
     * @param layeredGraph
     *            The layered graph.
     * @param elemMap
     *            the element map that maps the original {@code KGraph} elements to the transformed
     *            {@code LGraph} elements.
     */
    private void transformOutgoingEdges(final KNode node, final List<LNode> layeredNodes,
            final LayeredGraph layeredGraph, final Map<KGraphElement, LGraphElement> elemMap) {
        EList<KEdge> outEdgesList = node.getOutgoingEdges();
        if (!outEdgesList.isEmpty()) {
            for (KEdge kEdge : outEdgesList) {
                // variable denoting whether the edge points towards a descendant of node or not
                boolean isInnerEdge = isDescendantNotSelf(node, kEdge.getSource());
                // Create a corresponding LEdge for each KEdge in the List that has no
                // representative yet.
                LEdge lEdge = null;
                if (!elemMap.containsKey(kEdge)) {
                    lEdge = createLEdgeFromKEdge(kEdge, elemMap);
                } else {
                    lEdge = (LEdge) elemMap.get(kEdge);
                }
                // LNode representative is to represent the node for this edge in the LGraph. If
                // edge points to a descendant of node, it is set to the UPPER_COMPOUND_BORDER, else
                // to LOWER_COMPOUND_BORDER, null, if the corresponding border node has not been
                // created yet.
                LNode representative = null;
                for (LNode lNode : layeredNodes) {
                    KNode origin = (KNode) lNode.getProperty(Properties.ORIGIN);
                    if (isInnerEdge) {
                        if ((origin.equals(node))
                                && (lNode.getProperty(Properties.NODE_TYPE)
                                        .equals(NodeType.UPPER_COMPOUND_BORDER))) {
                            representative = lNode;
                        }
                    } else {
                        if (origin.equals(node)
                                && lNode.getProperty(Properties.NODE_TYPE).equals(
                                        NodeType.LOWER_COMPOUND_BORDER)) {
                            representative = lNode;
                        }
                    }
                }
                // Check, whether there is a source port for this edge - decide on dummy node type
                // to use
                KPort sourcePort = kEdge.getSourcePort();
                // Check, if edge has source port
                if (sourcePort == null) {
                    // If edge has no source port, check, whether the corresponding COMPOUND_BORDER
                    // exists.
                    if (representative == null) {
                        // If there is none, create one. If there is one, it will be reused as
                        // representative is already set to it.
                        if (isInnerEdge) {
                            representative = createBorderDummyNode(node,
                                    NodeType.UPPER_COMPOUND_BORDER);
                        } else {
                            representative = createBorderDummyNode(node,
                                    NodeType.LOWER_COMPOUND_BORDER);
                        }
                        layeredNodes.add(representative);
                        elemMap.put(node, representative);
                    }
                    // If edge has a source port, create LOWER_COMPOUND_PORT dummy node
                } else {
                    if (isInnerEdge) {
                        representative = createBorderDummyNode(node, NodeType.UPPER_COMPOUND_PORT);
                    } else {
                        representative = createBorderDummyNode(node, NodeType.LOWER_COMPOUND_PORT);
                    }
                    layeredNodes.add(representative);
                }

                // Connect the edge to the representative node, to eastern port in case of an edge
                // to a descendant, to western port otherwise.
                LPort dummyPort = null;
                if (isInnerEdge) {
                    Iterator<LPort> eastPortIterator = representative.getPorts(PortSide.EAST)
                            .iterator();
                    dummyPort = eastPortIterator.next();
                } else {
                    Iterator<LPort> westPortIterator = representative.getPorts(PortSide.WEST)
                            .iterator();
                    dummyPort = westPortIterator.next();
                }
                lEdge.setSource(dummyPort);
                if (sourcePort != null) {
                    elemMap.put(sourcePort, dummyPort);
                    dummyPort.setProperty(Properties.ORIGIN, sourcePort);
                }
            }

            // If there is no outgoing edge, a single lower border dummy node is created.
        } else {
            LNode dummyNode = createBorderDummyNode(node, NodeType.LOWER_COMPOUND_BORDER);
            layeredNodes.add(dummyNode);
        }
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
            final Map<KGraphElement, LGraphElement> elemMap, final List<LNode> layeredNodes,
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
                            dummyEdge.setProperty(Properties.COMPOUND_DUMMY_EDGE, true);

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
                            dummyEdge.setProperty(Properties.COMPOUND_DUMMY_EDGE, true);

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

    // // version of the function that uses more dummy edges the higher the nodes are in depth
    //
    // /**
    // * Creates dummy edges between a compound node border dummy node and its children.
    // *
    // * @param layeredNodes
    // *
    // * @param source
    // * the source node.
    // * @param layeredNodes
    // * the list of LNodes with the created dummy nodes and the imported nodes.
    // */
    // private void setCompoundDummyEdges(final KNode layoutNode,
    // final Map<KGraphElement, LGraphElement> elemMap, final List<LNode> layeredNodes) {
    // for (LNode lNode : layeredNodes) {
    // NodeType nodeType = lNode.getProperty(Properties.NODE_TYPE);
    // switch (nodeType) {
    // // If the node is a compound dummy node at the upper line of the compound node, add
    // // edge
    // // to every child node
    // case UPPER_COMPOUND_BORDER:
    // case UPPER_COMPOUND_PORT:
    // for (LNode childCandidate : layeredNodes) {
    // if ((childCandidate.getProperty(Properties.PARENT) != null)
    // && (childCandidate.getProperty(Properties.PARENT).equals(lNode
    // .getProperty(Properties.ORIGIN)))) {
    // LEdge dummyEdge = new LEdge();
    // dummyEdge.setProperty(Properties.COMPOUND_DUMMY_EDGE, true);
    //
    // LPort sourcePort = lNode.getPorts(PortSide.EAST).iterator().next();
    // dummyEdge.setSource(sourcePort);
    //
    // LPort targetPort = findDummyEdgePort(childCandidate, PortSide.WEST);
    // dummyEdge.setTarget(targetPort);
    // }
    // }
    // break;
    // // If the node is a compound dummy node at the lower line of the compound node,
    // // add
    // // edge from every child node to this node
    // case LOWER_COMPOUND_BORDER:
    // case LOWER_COMPOUND_PORT:
    // for (LNode childCandidate : layeredNodes) {
    // if ((childCandidate.getProperty(Properties.PARENT) != null)
    // && (childCandidate.getProperty(Properties.PARENT).equals(lNode
    // .getProperty(Properties.ORIGIN)))) {
    // LEdge dummyEdge = new LEdge();
    // dummyEdge.setProperty(Properties.COMPOUND_DUMMY_EDGE, true);
    //
    // LPort sourcePort = findDummyEdgePort(childCandidate, PortSide.EAST);
    // dummyEdge.setSource(sourcePort);
    //
    // LPort targetPort = lNode.getPorts(PortSide.WEST).iterator().next();
    // dummyEdge.setTarget(targetPort);
    // }
    // }
    // break;
    // // If the node is no compound dummy node, nothing is to be done.
    // default:
    // break;
    //
    // }
    // }
    // }

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
        createDummyPort(dummyNode, PortSide.EAST);
        createDummyPort(dummyNode, PortSide.WEST);
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
     * Recursively adds descendants of given node to a given list. Adds the startNode as well.
     * 
     * @param currentNode
     *            actual root node of the inclusion tree, whose nodes are to be added to the list.
     * @param descendantsList
     */
    private void listDescendants(final KNode currentNode, final List<KNode> descendantsList) {
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
            }
        }
        // Do not return null, if there is no applicable port, create one.
        if (port == null) {
            port = createDummyPort(node, side);
        }
        return port;
    }
}
