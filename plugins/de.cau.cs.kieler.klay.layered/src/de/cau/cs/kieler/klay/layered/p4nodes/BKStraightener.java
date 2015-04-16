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
package de.cau.cs.kieler.klay.layered.p4nodes;

import java.util.Set;

import com.google.common.collect.Sets;

import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.p4nodes.BKNodePlacer.BKAlignedLayout;
import de.cau.cs.kieler.klay.layered.p4nodes.BKNodePlacer.HDirection;
import de.cau.cs.kieler.klay.layered.properties.InternalProperties;
import de.cau.cs.kieler.klay.layered.properties.NodeType;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * @author uru
 */
public class BKStraightener {

    /** The graph to process. */
    private LGraph layeredGraph;
    /** Basic spacing between nodes, determined by layout options. */
    private float normalSpacing;
    /** Spacing between dummy nodes, determined by layout options. */
    private float smallSpacing;
    /** Spacing between external ports, determined by layout options. */
    private float externalPortSpacing;
   
    /**
     * @param layeredGraph the graph to handle.
     */
    public BKStraightener(final LGraph layeredGraph) {
        this.layeredGraph = layeredGraph;
        // Initialize spacing value from layout options.
        normalSpacing = layeredGraph.getProperty(Properties.OBJ_SPACING) 
                * layeredGraph.getProperty(Properties.OBJ_SPACING_IN_LAYER_FACTOR);
        smallSpacing = normalSpacing * layeredGraph.getProperty(Properties.EDGE_SPACING_FACTOR);
        externalPortSpacing = layeredGraph.getProperty(Properties.PORT_SPACING);
    }
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////
    // Straightness improvement

    public void improveStraightness(final BKAlignedLayout bal) {

        // TODO 
        // 1) simple check: next blocks root node has to be smaller (plus spacing) than
        // the node iterated through
        // 2) next block's size has to be smaller than ??? consider port anchor and inside shift of root
        // 3) starting at dummy node makes no sense, is straight anyway
        // 4) edge already straight
        
        
        // Gather root nodes and sink nodes of all blocks
        final Set<LNode> roots = Sets.newHashSet();
        final Set<LNode> sinks = Sets.newHashSet();

        roots.addAll(bal.blockSize.keySet());
        for (Layer l : layeredGraph) {
            for (LNode n : l) {
                if (roots.contains(bal.align.get(n))) {
                    sinks.add(n);
                }
            }
        }

        // start post-processing
        boolean didSomething = false;
        int i = 0;
        do {
            didSomething = false;
            for (Layer l : layeredGraph) {
                for (LNode n : l) {

                    // either use outgoing or incoming edges
                    // for TOP: blocks were assembled from left to right thus roots are
                    //  the left-most nodes in the blocks
                    // for BOTTOM: blocks were built from right to left
                    //  thus the block's right-most nodes are the roots
                    Iterable<LEdge> edges;
                    if (bal.hdir == HDirection.RIGHT) {
                        edges = n.getOutgoingEdges();
                    } else {
                        edges = n.getIncomingEdges();
                    }

                    for (LEdge out : edges) {
                        LNode t;
                        if (bal.hdir == HDirection.RIGHT) {
                            t = out.getTarget().getNode();
                        } else {
                            t = out.getSource().getNode();
                        }

                        // improvable edge?
                        if (!roots.contains(t) && !sinks.contains(n)) {
                            continue;
                        }

                        // decide which port's node belongs 
                        // to the block which possibly can be shifted
                        LPort fix;
                        LPort block;
                        if (bal.hdir == HDirection.RIGHT) {
                            if (roots.contains(t)) {
                                fix = out.getSource();
                                block = out.getTarget();
                            } else {
                                fix = out.getTarget();
                                block = out.getSource();
                            }
                        } else {
                            if (roots.contains(t)) {
                                fix = out.getTarget();
                                block = out.getSource();
                            } else {
                                fix = out.getSource();
                                block = out.getTarget();
                            }
                        }

                        // t has to be the root node of a different block
                        double delta = calculateDelta(bal, fix, block);

                        if (delta > 0) {
                            // target y larger than source y --> shift upwards?
                            if (checkSpaceAbove(bal, fix.getNode(), block.getNode(), delta)) {
                                shiftBlock(bal, block.getNode(), -delta);
                                didSomething = true;
                            }
                        } else if (delta < 0) {
                            // target y smaller than source y --> shift down?
                            if (checkSpaceBelow(bal, fix.getNode(), block.getNode(), -delta)) {
                                shiftBlock(bal, block.getNode(), -delta);
                                didSomething = true;
                            }
                        }
                    }
                }
            }
            i++;
            // SUPPRESS CHECKSTYLE NEXT 4 ber
        } while (didSomething && i < 1000);
        
        if (i == 1000) {
            throw new IllegalStateException("Iteration count overflew, shouldnt happen.");
        }
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
    private double calculateDelta(final BKAlignedLayout bal, final LPort src, final LPort tgt) {
        double srcPos = bal.y.get(src.getNode()) + bal.innerShift.get(src.getNode()) 
                + src.getPosition().y + src.getAnchor().y;
        double tgtPos = bal.y.get(tgt.getNode()) + bal.innerShift.get(tgt.getNode()) 
                + tgt.getPosition().y + tgt.getAnchor().y;
        return tgtPos - srcPos;
    }

    /**
     * Shifts the y-coordinates of all nodes of the block represented by {@code root} by the
     * specified {@code delta}.
     * 
     * @param bal
     *            corresponding layout.
     * @param root
     *            root node of the block.
     * @param delta
     *            the delta by which the node should be move. Can be either positive or negative.
     */
    private void shiftBlock(final BKAlignedLayout bal, final LNode root, final double delta) {
        LNode current = root;
        do {
            double newPos = bal.y.get(current) + delta;
            bal.y.put(current, newPos);
            current = bal.align.get(current);
        } while (current != root);
    }
    
    /**
     * .
     * @param bal .
     * @param node .
     * @param adjBlock .
     * @param delta
     *          has to be a positive value
     */
    private boolean checkSpaceAbove(final BKAlignedLayout bal, final LNode node,
            final LNode adjBlock, final double delta) {

        // adjBlock has to be a root node
        assert bal.blockSize.containsKey(adjBlock);
        final LNode root = adjBlock;

        // iterate through the block
        LNode current = root;
        do {
            current = bal.align.get(current);
            assert current != null;
            
            double minYCurrent = getMinY(bal, current);

            LNode neighbour = getUpperNeighbor(current, current.getIndex()); // FIXME getindex SLOW
            if (neighbour != null) {
                double maxYNeighbor = getMaxY(bal, neighbour);

                // can we shift the current node by delta upwards?
                if (!(minYCurrent - delta > maxYNeighbor)) {
                    return false;
                }
            }

            // until we wrap around
        } while (root != current);

        return true;
    }
    
    /**
     * .
     * @param bal .
     * @param node .
     * @param adjBlock .
     * @param delta
     *          has to be a positive value
     */
    private boolean checkSpaceBelow(final BKAlignedLayout bal, final LNode node,
            final LNode adjBlock, final double delta) {

        // adjBlock has to be a root node
        assert bal.blockSize.containsKey(adjBlock);
        final LNode root = adjBlock;

        // iterate through the block
        LNode current = root;
        do {
            current = bal.align.get(current);
            assert current != null;
            
            double maxYCurrent = getMaxY(bal, current);

            LNode neighbour = getLowerNeighbor(current, current.getIndex()); // FIXME getindex SLOW
            if (neighbour != null) {
                double minYNeighbor = getMinY(bal, neighbour);

                // can we shift the current node by delta downwards?
                if (!(maxYCurrent + delta < minYNeighbor)) {
                    return false;
                }
            }

            // until we wrap around
        } while (root != current);

        return true;
    }
    
    private double getMinY(final BKAlignedLayout bal, final LNode n) {

        // TODO consider external port spacing
        final double spacing;
        if (n.getProperty(InternalProperties.NODE_TYPE) != NodeType.NORMAL) {
           spacing = smallSpacing; 
        } else {
            spacing = normalSpacing;
        }
        
        // node size + margins + inside shift etc
        LNode root = bal.root.get(n);
        return bal.y.get(root)
            + bal.innerShift.get(n)
            - n.getMargin().top
            - spacing / 2d;
    }
    
    private double getMaxY(final BKAlignedLayout bal, final LNode n) {

        // TODO consider external port spacing
        final double spacing;
        if (n.getProperty(InternalProperties.NODE_TYPE) != NodeType.NORMAL) {
           spacing = smallSpacing; 
        } else {
            spacing = normalSpacing;
        }
        
        // node size + margins + inside shift etc
        LNode root = bal.root.get(n);
        return bal.y.get(root)
            + bal.innerShift.get(n)
            + n.getSize().y
            + n.getMargin().bottom
            + spacing / 2d;
    }
    
    /**
     * @param n
     *            the node of which the neigbhbor is requested.
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
     *            the node of which the neigbhbor is requested.
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
    
}
