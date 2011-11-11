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

import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.kiml.options.PortType;
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
        LNode current = child;
        LGraphElement currentParent = current.getProperty(Properties.PARENT);
        // Nodes that are directly contained by the layered graph carry it in their parent property.
        // So if the parent changes instance from LNode to LayeredGraph, the loop ends.
        while (currentParent instanceof LNode) {
            current = (LNode) currentParent;
            if (current == parent) {
                return true;
            }
            currentParent = current.getProperty(Properties.PARENT);
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
}
