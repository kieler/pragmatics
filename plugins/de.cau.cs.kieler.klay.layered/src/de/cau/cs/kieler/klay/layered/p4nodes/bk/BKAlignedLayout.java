/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.p4nodes.bk;

import java.util.Map;

import com.google.common.collect.Maps;

import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LNode.NodeType;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * Class which holds all information about a layout in one of the four direction
 * combinations.
 * 
 * @author jjc
 * @author uru
 */
public final class BKAlignedLayout {
    
    // Allow the fields of this container to be accessed from package siblings.
    // SUPPRESS CHECKSTYLE NEXT 24 VisibilityModifier
    // TODO one should use arrays indexed by LNode#id to improve performance
    /** The root node of each node in a block. */
    Map<LNode, LNode> root;
    /** The size of a block. */
    Map<LNode, Double> blockSize;
    /** The next node in a block, or the first if the current node is the last, forming a ring. */
    Map<LNode, LNode> align;
    /** The value by which a node must be shifted to stay straight inside a block. */
    Map<LNode, Double> innerShift;
    /** The root node of a class, mapped from block root nodes to class root nodes. */
    Map<LNode, LNode> sink;
    /** The value by which a block must be shifted for a more compact placement. */
    Map<LNode, Double> shift;
    /** The y-coordinate of every node, forming the final layout. */
    Map<LNode, Double> y;
    /** The vertical direction of the current layout. */
    VDirection vdir;
    /** The horizontal direction of the current layout. */
    HDirection hdir;

    /** The graph to process. */
    @SuppressWarnings("unused")
    private LGraph layeredGraph;
    /** Basic spacing between nodes, determined by layout options. */
    private float normalSpacing;
    /** Spacing between dummy nodes, determined by layout options. */
    private float smallSpacing;
    /** Spacing between external ports, determined by layout options. */
    private float externalPortSpacing;
    
    /**
     * Basic constructor for a layout.
     * 
     * @param layeredGraph
     *            the layered graph.
     * @param nodeCount
     *            number of nodes in this layout
     * @param vdir
     *            vertical traversal direction of the algorithm
     * @param hdir
     *            horizontal traversal direction of the algorithm
     */
    public BKAlignedLayout(final LGraph layeredGraph, final int nodeCount, 
            final VDirection vdir, final HDirection hdir) {

        this.layeredGraph = layeredGraph;
        // Initialize spacing value from layout options.
        normalSpacing = layeredGraph.getProperty(Properties.OBJ_SPACING) 
                * layeredGraph.getProperty(Properties.OBJ_SPACING_IN_LAYER_FACTOR);
        smallSpacing = normalSpacing * layeredGraph.getProperty(Properties.EDGE_SPACING_FACTOR);
        externalPortSpacing = layeredGraph.getProperty(Properties.PORT_SPACING);
        
        root = Maps.newLinkedHashMap();
        blockSize = Maps.newHashMapWithExpectedSize(nodeCount);
        align = Maps.newHashMapWithExpectedSize(nodeCount);
        innerShift = Maps.newHashMapWithExpectedSize(nodeCount);
        sink = Maps.newHashMapWithExpectedSize(nodeCount);
        shift = Maps.newHashMapWithExpectedSize(nodeCount);
        y = Maps.newHashMapWithExpectedSize(nodeCount);
        this.vdir = vdir;
        this.hdir = hdir;
    }

    /**
     * Calculate the layout size for comparison.
     * 
     * @return The size of the layout
     */
    public double layoutSize() {
        double min = Double.POSITIVE_INFINITY;
        double max = Double.NEGATIVE_INFINITY;
        // Prior to KIPRA-1426 the size of the layout was determined  
        // only based on y coordinates, neglecting any block sizes.
        // We now determine the maximal extend of the layout based on
        // the minimum y coordinate of any node and the maximum
        // y coordinate _plus_ the size of any block.
        for (LNode n : y.keySet()) {
            double yMin = y.get(n);
            double yMax = yMin + blockSize.get(root.get(n));
            min = Math.min(min, yMin);
            max = Math.max(max, yMax);
        }
        return max - min;
    }

    
    /**
     * @param src
     *            source port of the tested edge
     * @param tgt
     *            target port of the tested edge
     * @return A delta larger than 0 if the {@code tgt} port has a larger y coordinate than
     *         {@code src} and a delta smaller than zero if {@code src} has the larger y coordinate.
     *         This means that for {@code delta > 0} the target node has to be shifted upwards to
     *         straighten the edge.
     */
    public double calculateDelta(final LPort src, final LPort tgt) {
        double srcPos = y.get(src.getNode()) + innerShift.get(src.getNode()) 
                + src.getPosition().y + src.getAnchor().y;
        double tgtPos = y.get(tgt.getNode()) + innerShift.get(tgt.getNode()) 
                + tgt.getPosition().y + tgt.getAnchor().y;
        return tgtPos - srcPos;
    }

    /**
     * Shifts the y-coordinates of all nodes of the block represented by {@code root} by the
     * specified {@code delta}.
     * 
     * @param rootNode
     *            root node of the block.
     * @param delta
     *            the delta by which the node should be move. Can be either positive or negative.
     */
    public void shiftBlock(final LNode rootNode, final double delta) {
        LNode current = rootNode;
        do {
            double newPos = y.get(current) + delta;
            y.put(current, newPos);
            current = align.get(current);
        } while (current != rootNode);
    }
    
    /**
     * Checks whether a block with root node {@code blockRoot} can be shifted upwards by
     * {@code delta} without overlapping any of the block's nodes upper neighbors.
     * 
     * @param blockRoot
     *            root node of a block
     * @param delta
     *            a positive value
     * @return true if there is space.
     */
    public boolean checkSpaceAbove(final LNode blockRoot, final double delta) {
       
        final LNode rootNode = blockRoot;
        // iterate through the block
        LNode current = rootNode;
        do {
            current = align.get(current);
            // get minimum possible position of the current node
            double minYCurrent = getMinY(current);

            LNode neighbor = getUpperNeighbor(current, current.getIndex()); // FIXME getindex SLOW
            if (neighbor != null) {
                double maxYNeighbor = getMaxY(neighbor);

                // can we shift the current node by delta upwards?
                if (!(minYCurrent - delta - getSpacing(current, neighbor) >= maxYNeighbor)) {
                    return false;
                }
            }
            // until we wrap around
        } while (rootNode != current);

        return true;
    }
    
    /**
     * Checks whether a block with root node {@code blockRoot} can be shifted downwards by
     * {@code delta} without overlapping any of the block's nodes lower neighbors.
     * 
     * @param blockRoot
     *            root node of a block
     * @param delta
     *            a positive value
     * @return true if there is space.
     */
    public boolean checkSpaceBelow(final LNode blockRoot, final double delta) {

        final LNode rootNode = blockRoot;
        // iterate through the block
        LNode current = rootNode;
        do {
            current = align.get(current);
            // get maximum possible position of the current node
            double maxYCurrent = getMaxY(current);

            // get the lower neighbor and check its position allows shifting
            LNode neighbour = getLowerNeighbor(current, current.getIndex()); // FIXME getindex SLOW
            if (neighbour != null) {
                double minYNeighbor = getMinY(neighbour);

                // can we shift the current node by delta downwards?
                if (!(maxYCurrent + delta + getSpacing(current, neighbour) <= minYNeighbor)) {
                    return false;
                }
            }
            // until we wrap around
        } while (rootNode != current);

        return true;
    }
    
    /**
     * Determines the required spacing between the two nodes {@code n1} and {@code n2} based on the
     * two nodes' type.
     */
    private float getSpacing(final LNode n1, final LNode n2) {
        float spacing;
        if (n1.getNodeType() == NodeType.EXTERNAL_PORT || n2.getNodeType() == NodeType.EXTERNAL_PORT) {
            spacing = externalPortSpacing;
        } else if (n1.getNodeType() != NodeType.NORMAL || n2.getNodeType() != NodeType.NORMAL) {
           spacing = smallSpacing; 
        } else {
            spacing = normalSpacing;
        }
        return spacing;
    }
    
    /**
     * Returns the minimum position of node {@code n} and its margins, that is,
     * {@code node.y + node.innerShift - node.margin.top}. Note that no spacing is accounted for.
     * 
     * @param n
     *            a node
     * @return the minimum position.
     */
    public double getMinY(final LNode n) {

        // node size + margins + inside shift etc
        LNode rootNode = root.get(n);
        return y.get(rootNode)
            + innerShift.get(n)
            - n.getMargin().top;
    }
    
    /**
     * Returns the maximum position of node {@code n} and its margins, that is,
     * {@code node.y + node.innerShift + node.size + node.margin.bottom}. Note that no spacing is
     * accounted for.
     * 
     * @param n
     *            a node
     * @return the minimum position.
     */
    public double getMaxY(final LNode n) {
        
        // node size + margins + inside shift etc
        LNode rootNode = root.get(n);
        return y.get(rootNode)
            + innerShift.get(n)
            + n.getSize().y
            + n.getMargin().bottom;
    }
    
    
    /**
     * @param n
     *            the node for which the neighbor is requested.
     * @param layerIndex
     *            the index of {@code n} within its layer.
     * @return the node with a <b>larger</b> y than {@code n} within {@code n}'s layer if it exists,
     *         otherwise {@code null}.
     */
    private LNode getLowerNeighbor(final LNode n, final int layerIndex) {
        Layer l = n.getLayer();
        if (layerIndex < l.getNodes().size() - 1) {
            return l.getNodes().get(layerIndex + 1);
        }
        return null;
    }

    /**
     * @param n
     *            the node for which the neighbor is requested.
     * @param layerIndex
     *            the index of {@code n} within its layer.
     * @return the node with a <b>smaller</b> y than {@code n} within {@code n}'s layer if it
     *         exists, otherwise {@code null}.
     */
    private LNode getUpperNeighbor(final LNode n, final int layerIndex) {
        Layer l = n.getLayer();
        if (layerIndex > 0) {
            return l.getNodes().get(layerIndex - 1); 
        }
        return null;
    }
    
    @Override
    public String toString() {
        String result = "";
        if (hdir == HDirection.RIGHT) {
            result += "RIGHT";
        } else if (hdir == HDirection.LEFT) {
            result += "LEFT";
        }
        if (vdir == VDirection.DOWN) {
            result += "DOWN";
        } else if (vdir == VDirection.UP) {
            result += "UP";
        } else {
            result += "BALANCED";
        }
        return result;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////
    // Enumerations
    
    /**
     * Vertical direction enumeration.
     */
    public static enum VDirection {
        /** Iteration direction top-down. */
        DOWN, 
        /** Iteration direction bottom-up. */
        UP;
    }
    
    /**
     * Horizontal direction enumeration.
     */
    public static enum HDirection {
        /** Iterating from right to left. */
        RIGHT, 
        /** Iterating from left to right. */
        LEFT;
    }
}
