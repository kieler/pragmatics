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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
//import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
//import de.cau.cs.kieler.core.math.KVector;
//import de.cau.cs.kieler.core.math.KVectorChain;
//import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
//import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
//import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
//import de.cau.cs.kieler.kiml.options.EdgeRouting;
//import de.cau.cs.kieler.kiml.options.LayoutOptions;
//import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.kiml.options.PortSide;
//import de.cau.cs.kieler.kiml.util.KimlUtil;
//import de.cau.cs.kieler.klay.layered.graph.Insets;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraphElement;
//import de.cau.cs.kieler.klay.layered.graph.LLabel;
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
 */
public class CompoundGraphImporter {
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
    void recursiveTransformCompoundGraph(final KNode currentNode, final List<LNode> layeredNodes,
            final LayeredGraph layeredGraph, final Map<KGraphElement, LGraphElement> elemMap,
            final EnumSet<GraphProperties> graphProperties) {

        if (currentNode.getChildren().isEmpty()) {
            transformNode(currentNode, layeredNodes, elemMap, graphProperties);
            // TODO: Write transformNode-Method for compound Importer (see method stub below) or use
            // it from KGraphImporter. Check in latter case, if there is no conflict resulting from
            // the use of transformNode (which is not designed for recursive import). Take care of
            // the incoming and outgoing edges of leave nodes additionally.
        } else {
            for (KNode child : currentNode.getChildren()) {
                recursiveTransformCompoundGraph(child, layeredNodes, layeredGraph, elemMap,
                        graphProperties);
            }
            transformCompoundNodeWithEdges(currentNode, layeredNodes, layeredGraph, elemMap,
                    graphProperties);
            setCompoundDummyEdges(currentNode, elemMap, layeredNodes);
        }
    }

    /**
     * Transform leave node.
     * 
     * @param currentNode
     *            Node to be transformed.
     * @param layeredNodes
     *            List to add representative to.
     * @param elemMap
     *            the element map that maps the original {@code KGraph} elements to the transformed
     *            {@code LGraph} elements.
     * @param graphProperties
     *            graph properties updated during the transformation.
     */
    private void transformNode(final KNode currentNode, final List<LNode> layeredNodes,
            final Map<KGraphElement, LGraphElement> elemMap,
            final EnumSet<GraphProperties> graphProperties) {
        // TODO Auto-generated method stub

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
                    elemMap.put(kEdge, lEdge);
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
                lEdge.setSource(dummyPort);
                if (targetPort != null) {
                    elemMap.put(targetPort, dummyPort);
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
                // variable denoting wether the edge points towards a descendant of node or not
                boolean isInnerEdge = isDescendantNotSelf(node, kEdge.getSource());
                // Create a corresponding LEdge for each KEdge in the List that has no
                // representative yet.
                LEdge lEdge = null;
                if (!elemMap.containsKey(kEdge)) {
                    lEdge = createLEdgeFromKEdge(kEdge, elemMap);
                    elemMap.put(kEdge, lEdge);
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
            final Map<KGraphElement, LGraphElement> elemMap, final List<LNode> layeredNodes) {
        for (LNode lNode : layeredNodes) {
            NodeType nodeType = lNode.getProperty(Properties.NODE_TYPE);
            switch (nodeType) {
            // If the node is a compound dummy node at the upper line of the compound node, add edge
            // to every child node
            case UPPER_COMPOUND_BORDER:
            case UPPER_COMPOUND_PORT:
                for (LNode childCandidate : layeredNodes) {
                    if (childCandidate.getProperty(Properties.PARENT).equals(
                            lNode.getProperty(Properties.ORIGIN))) {
                        LEdge dummyEdge = new LEdge();
                        dummyEdge.setSource(childCandidate.getPorts(PortSide.WEST).iterator().next());
                        
                    }
                }
                // If the node is a compound dummy node at the lower line of the compound node, add
                // edge from every child node to this node
            case LOWER_COMPOUND_BORDER:
            case LOWER_COMPOUND_PORT:
                // If the node is no compound dummy node, nothing is to be done.
            default:
                break;
            }
        }
        // TODO complete method body
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

}
