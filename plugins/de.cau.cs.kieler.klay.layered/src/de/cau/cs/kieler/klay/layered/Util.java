/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.kiml.options.PortType;
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.klay.layered.graph.LGraphElement;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.properties.NodeType;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * Contains utility methods used throughout KLay Layered.
 * 
 * @author cds
 * @author ima
 */
public final class Util {

    /**
     * Private constructor.
     */
    private Util() {
        // This space intentionally left blank
    }

    // /////////////////////////////////////////////////////////////////////////////
    // General Utility

    /**
     * Center the given point on one side of a boundary.
     * 
     * @param point
     *            a point to change
     * @param boundary
     *            the boundary to use for centering
     * @param side
     *            the side of the boundary
     */
    public static void centerPoint(final KVector point, final KVector boundary, final PortSide side) {
        switch (side) {
        case NORTH:
            point.x = boundary.x / 2;
            point.y = 0;
            break;
        case EAST:
            point.x = boundary.x;
            point.y = boundary.y / 2;
            break;
        case SOUTH:
            point.x = boundary.x / 2;
            point.y = boundary.y;
            break;
        case WEST:
            point.x = 0;
            point.y = boundary.y / 2;
            break;
        }
    }

    /**
     * Return a collector port of given type, creating it if necessary. A collector port is used to
     * merge all incident edges that originally had no ports.
     * 
     * @param node
     *            a node
     * @param type
     *            if {@code INPUT}, an input collector port is returned; if {@code OUTPUT}, an
     *            output collector port is returned
     * @param side
     *            the side to set for a newly created port
     * @return a collector port
     */
    public static LPort provideCollectorPort(final LNode node, final PortType type,
            final PortSide side) {
        LPort port = null;
        switch (type) {
        case INPUT:
            for (LPort inport : node.getPorts()) {
                if (inport.getProperty(Properties.INPUT_COLLECT)) {
                    return inport;
                }
            }
            port = new LPort();
            port.setProperty(Properties.INPUT_COLLECT, true);
            break;
        case OUTPUT:
            for (LPort outport : node.getPorts()) {
                if (outport.getProperty(Properties.OUTPUT_COLLECT)) {
                    return outport;
                }
            }
            port = new LPort();
            port.setProperty(Properties.OUTPUT_COLLECT, true);
            break;
        }
        if (port != null) {
            port.setNode(node);
            port.setSide(side);
            centerPoint(port.getPosition(), node.getSize(), side);
        }
        return port;
    }

    // /////////////////////////////////////////////////////////////////////////////
    // Debug Files

    /**
     * Returns the path for debug output graphs.
     * 
     * @return the path for debug output graphs, without trailing separator.
     */
    public static String getDebugOutputPath() {
        String path = System.getProperty("user.home");
        if (path.endsWith(File.separator)) {
            path += "tmp" + File.separator + "klay";
        } else {
            path += File.separator + "tmp" + File.separator + "klay";
        }

        return path;
    }

    /**
     * Returns the beginning of the file name used for debug output graphs while layouting the given
     * layered graph. This will look something like {@code "143293-"}.
     * 
     * @param graph
     *            the graph to return the base debug file name for.
     * @return the base debug file name for the given graph.
     */
    public static String getDebugOutputFileBaseName(final LayeredGraph graph) {
        return Integer.toString(graph.hashCode() & ((1 << (Integer.SIZE / 2)) - 1)) + "-";
    }

    // ///////////////////////////////////////////////////////////////////////////
    // Layout of Compound Graphs

    /**
     * Determines whether the given child node is a descendant of the parent node.
     * 
     * @param child
     *            a child node
     * @param parent
     *            a parent node
     * @return true if {@code child} is a direct or indirect child of {@code parent}
     */
    public static boolean isDescendant(final LNode child, final LNode parent) {
        LNode cParent = parent;
        LNode cChild = child;
        NodeType nodeTypeParent = parent.getProperty(Properties.NODE_TYPE);
        NodeType nodeTypeChild = child.getProperty(Properties.NODE_TYPE);
        if ((nodeTypeParent != NodeType.NORMAL) && (nodeTypeParent != NodeType.UPPER_COMPOUND_BORDER)) {
            cParent = parent.getProperty(Properties.COMPOUND_NODE);
        }
        if ((nodeTypeChild != NodeType.NORMAL) && (nodeTypeChild != NodeType.UPPER_COMPOUND_BORDER)) {
            cChild = child.getProperty(Properties.COMPOUND_NODE);
        }
       
        LNode current = cChild;
        LGraphElement currentParent = getParent(current);
        // Nodes that are directly contained by the layered graph carry it in their parent property.
        // So if the parent changes instance from LNode to LayeredGraph, the loop ends.
        while (currentParent instanceof LNode) {
            current = (LNode) currentParent;
            if (current == cParent) {
                return true;
            }
            currentParent = getParent(current);
        }
        return false;
    }

    /**
     * Finds and returns the given node's parent's representative in the LayeredGraph.
     * 
     * @param child
     *            the node for which the parent representative is to be found.
     * @return returns the LGraphElement representing the parent of the given node in the original
     *         graph. Returned element can be instance of LayeredGraph (in case the given Node is of
     *         depth level one, that is directly contained by the layered graph) or LNode (any other
     *         case).
     */
    public static LGraphElement getParent(final LNode child) {
        NodeType childNodeType = child.getProperty(Properties.NODE_TYPE);
        switch (childNodeType) {
        case NORMAL:
        case UPPER_COMPOUND_BORDER:
            return child.getProperty(Properties.PARENT);
        default:
            return child.getProperty(Properties.COMPOUND_NODE).getProperty(Properties.PARENT);
        }
    }

    /**
     * Get the given node's children list. If the node is not of NodeType NORMAL oder
     * UPPER_COMPOUND_BORDER and therefore directly representing a node of the original graph, get
     * the children-list of the related compound border dummy.
     * 
     * @param node
     *            the LNode, for which the children are to be returned
     * @return the list of the representatives of the represented node's children.
     */
    public static LinkedList<LNode> getChildren(final LNode node) {
        NodeType nodeType = node.getProperty(Properties.NODE_TYPE);
        switch (nodeType) {
        case NORMAL:
        case UPPER_COMPOUND_BORDER:
            return node.getProperty(Properties.CHILDREN);
        default:
            return node.getProperty(Properties.COMPOUND_NODE).getProperty(Properties.CHILDREN);
        }
    }
    
    /**
     * Get the compound node an LNode belongs to.
     * 
     * @param node
     *            The LNode for which the corresponding compound node is to be returned.
     * @param layeredGraph
     *            The layered Graph, which is to be laid out.
     * @return Returns: A. The parent node for a leave node, if it is not a node of the uppermost
     *         hierarchy level- in that case, null will be returned. B. The compound node of which's
     *         representation the node is part of for compound dummies. C. The node enclosing the
     *         represented LGraphElement for dummies of another kind. Null, if represented
     *         LGraphElement is of depth 1. D. null in default case.
     */
    public static LNode getRelatedCompoundNode(final LNode node, final LayeredGraph layeredGraph) {
        // method is to return null in the default case
        LNode retNode = null;
        HashMap<KGraphElement, LGraphElement> elemMap = layeredGraph
                .getProperty(Properties.ELEMENT_MAP);
        NodeType nodeType = node.getProperty(Properties.NODE_TYPE);
        LGraphElement parentRepresentative;
        switch (nodeType) {
        case LOWER_COMPOUND_BORDER:
        case LOWER_COMPOUND_PORT:
        case UPPER_COMPOUND_BORDER:
        case UPPER_COMPOUND_PORT:
            retNode = node.getProperty(Properties.COMPOUND_NODE);
            break;
        case NORMAL:
            KNode parent = node.getProperty(Properties.K_PARENT);
            parentRepresentative = elemMap.get(parent);
            if (!(parentRepresentative instanceof LayeredGraph)) {
                retNode = (LNode) parentRepresentative;
            }
            break;
        case LONG_EDGE:
            // In case of a to-descendant or from-descendant edge:
            // An edge is regarded contained by the compound node which contains both source and
            // target (directly or indirectly). If this is the layeredGraph, return null.
            LPort sourcePort = node.getProperty(Properties.LONG_EDGE_SOURCE);
            LPort targetPort = node.getProperty(Properties.LONG_EDGE_TARGET);
            LNode sourceNode = sourcePort.getNode();
            LNode targetNode = targetPort.getNode();
            KNode sourceNodeOrigin = (KNode) sourceNode.getProperty(Properties.ORIGIN);
            KNode targetNodeOrigin = (KNode) targetNode.getProperty(Properties.ORIGIN);
            if (KimlUtil.isDescendant(sourceNodeOrigin, targetNodeOrigin)
                    || KimlUtil.isDescendant(targetNodeOrigin, sourceNodeOrigin)) {
                LinkedList<LNode> sourceTargetList = new LinkedList<LNode>();
                sourceTargetList.add(sourceNode);
                sourceTargetList.add(targetNode);
                propagatePair(sourceTargetList, elemMap);
                LNode newSource = sourceTargetList.getFirst();
                KNode newSourceParent = newSource.getProperty(Properties.K_PARENT);
                LGraphElement container = elemMap.get(newSourceParent);
                if (!(container instanceof LayeredGraph)) {
                    retNode = (LNode) container;
                }
                // In other cases, determine, if the edge is hierarchy-crossing.
            } else {
                LNode sourceNodeCompound = getRelatedCompoundNode(sourceNode, layeredGraph);
                LNode targetNodeCompound = getRelatedCompoundNode(targetNode, layeredGraph);
                // if it is not hierarchy-crossing, return the compound node that is parent of both
                // source and target
                if (sourceNodeCompound == targetNodeCompound) {
                    retNode = sourceNodeCompound;
                // if the edge is hierarchy-crossing, choose the compound node of the target
                } else {
                    retNode = targetNodeCompound;
                }
            }
            break;
        case EXTERNAL_PORT:
            LGraphElement nodeParentRep = elemMap
                    .get(((KPort) (node.getProperty(Properties.ORIGIN))).getNode().getParent());
            if (!(nodeParentRep == layeredGraph)) {
                retNode = (LNode) nodeParentRep;
            }
            break;
        case NORTH_SOUTH_PORT:
            LNode portNode = node.getProperty(Properties.IN_LAYER_LAYOUT_UNIT);
            KNode portNodeParent = portNode.getProperty(Properties.K_PARENT);
            LGraphElement portNodeParentRepresentative = elemMap.get(portNodeParent);
            if (!(elemMap.get(portNodeParent) instanceof LayeredGraph)) {
                retNode = (LNode) portNodeParentRepresentative;
            }
            break;
        case COMPOUND_SIDE:
            retNode = node.getProperty(Properties.SIDE_OWNER);
            break;
        default:
            break;
        }

        return retNode;
    }
    
    /**
     * Finds for a pair of LNodes the pair of ancestors with a common parent that is highest in
     * depth in the inclusion tree. Each of the ancestors may be the given node itself.
     * 
     * @param sourceTargetList
     *            The pair of nodes is handed over as a List. The pair of ancestors will be stored
     *            in the same list.
     * @param elemMap
     *            The element map that maps the original KGraphElements to the LGraphElements.
     */
    public static void propagatePair(final LinkedList<LNode> sourceTargetList,
            final HashMap<KGraphElement, LGraphElement> elemMap) {
        LNode sourceNode = sourceTargetList.getFirst();
        LNode targetNode = sourceTargetList.getLast();

        KNode currentSource = getRelatedKNode(sourceNode);
        KNode currentTarget = getRelatedKNode(targetNode);

        int depthSource = elemMap.get(currentSource).getProperty(Properties.DEPTH);
        int depthTarget = elemMap.get(currentTarget).getProperty(Properties.DEPTH);
        assert (depthSource > 0);
        assert (depthTarget > 0);

        KNode currentSourceAncestor = currentSource.getParent();
        KNode currentTargetAncestor = currentTarget.getParent();

        // If source and target differ in depth in the nesting tree, crawl up the
        // nesting tree on the deep side to reach even depth level
        if (depthSource != depthTarget) {
            for (int i = depthSource; i > depthTarget; i--) {
                currentSource = currentSource.getParent();
            }
            for (int j = depthTarget; j > depthSource; j--) {
                currentTarget = currentTarget.getParent();
            }
        }

        if (currentSourceAncestor != currentTargetAncestor) {
            // Walk up the nesting tree from both sides, until nodes have the same
            // parent.
            currentSourceAncestor = currentSource.getParent();
            currentTargetAncestor = currentTarget.getParent();
            while (currentSourceAncestor != currentTargetAncestor) {
                currentSource = currentSource.getParent();
                currentTarget = currentTarget.getParent();
                currentSourceAncestor = currentSource.getParent();
                currentTargetAncestor = currentTarget.getParent();
            }
        }
        LNode newSource = (LNode) elemMap.get(currentSource);
        LNode newTarget = (LNode) elemMap.get(currentTarget);
        sourceTargetList.addFirst(newSource);
        sourceTargetList.addLast(newTarget);
    }
    
    /**
     * Returns the KNode the given node is representing (in case of normal or compound dummy nodes)
     * or directly related to - port node in case of Port dummies, target node origin in case of
     * long edge dummies.
     * 
     * @param node
     *            The node for which to find the related KNode.
     * @return The KNode represented by the given node or directly related to it.
     */
    private static KNode getRelatedKNode(final LNode node) {
        KNode retNode;
        Object origin = node.getProperty(Properties.ORIGIN);
        NodeType nodeType = node.getProperty(Properties.NODE_TYPE);
        switch (nodeType) {
        case EXTERNAL_PORT:
            KNode portNode = ((KPort) (node.getProperty(Properties.ORIGIN))).getNode();
            retNode = portNode;
            break;
        case LONG_EDGE:
            LNode edgeTarget = node.getProperty(Properties.LONG_EDGE_TARGET).getNode();
            Object edgeTargetOrigin = edgeTarget.getProperty(Properties.ORIGIN);
            assert (edgeTargetOrigin instanceof KNode);
            retNode = (KNode) edgeTargetOrigin;
            break;
        case NORTH_SOUTH_PORT:
            LNode lnode = node.getProperty(Properties.IN_LAYER_LAYOUT_UNIT);
            Object nodeOrigin = lnode.getProperty(Properties.ORIGIN);
            assert (nodeOrigin instanceof KNode);
            retNode = (KNode) nodeOrigin;
            break;
        case COMPOUND_SIDE:
            LNode compoundNode = node.getProperty(Properties.SIDE_OWNER);
            retNode = (KNode) compoundNode.getProperty(Properties.ORIGIN);
            break;
        default:
            assert (origin instanceof KNode);
            retNode = (KNode) origin;
            break;
        }
        return retNode;
    }
}
