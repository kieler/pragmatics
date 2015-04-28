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

import java.util.List;
import java.util.Queue;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LNode.NodeType;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.p4nodes.bk.BKAlignedLayout.HDirection;
import de.cau.cs.kieler.klay.layered.p4nodes.bk.BKAlignedLayout.VDirection;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * For documentation see {@link BKNodePlacer}.
 * 
 * As opposed to the default {@link BKCompacter} this version
 * trades maximal compactness with straight edges. In other words,
 * where possible it favours additional straight edges over compactness. 
 * 
 * @author jjc
 * @author uru
 */
public class BKCompacterStraight implements ICompacter {
    
    // TODO make this an option
    private static final double THRESHOLD = 2000; 
    
    /** The graph to process. */
    private LGraph layeredGraph;
    /** Basic spacing between nodes, determined by layout options. */
    private float normalSpacing;
    /** Spacing between dummy nodes, determined by layout options. */
    private float smallSpacing;
    /** Spacing between external ports, determined by layout options. */
    private float externalPortSpacing;
    
    
    private Set<LNode> blockFinished = Sets.newHashSet();
    private Queue<Pair<LNode, Boolean>> postProcessables = Lists.newLinkedList();
    
    /**
     * @param layeredGraph the graph to handle.
     */
    public BKCompacterStraight(final LGraph layeredGraph) {
        this.layeredGraph = layeredGraph;
        // Initialize spacing value from layout options.
        normalSpacing = layeredGraph.getProperty(Properties.OBJ_SPACING) 
                * layeredGraph.getProperty(Properties.OBJ_SPACING_IN_LAYER_FACTOR);
        smallSpacing = normalSpacing * layeredGraph.getProperty(Properties.EDGE_SPACING_FACTOR);
        externalPortSpacing = layeredGraph.getProperty(Properties.PORT_SPACING);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////
    // Block Placement
    
    /**
     * In this step, actual coordinates are calculated for blocks and its nodes.
     * 
     * <p>First, all blocks are placed, trying to avoid any crossing of the blocks. Then, the blocks are
     * shifted towards each other if there is any space for compaction.</p>
     * 
     * @param bal One of the four layouts which shall be used in this step
     */
    public void horizontalCompaction(final BKAlignedLayout bal) {
        // Initialize fields with basic values, partially depending on the direction
        for (Layer layer : layeredGraph.getLayers()) {
            for (LNode node : layer.getNodes()) {
                bal.sink.put(node, node);
                bal.shift.put(node, bal.vdir == VDirection.UP
                        ? Double.NEGATIVE_INFINITY
                        : Double.POSITIVE_INFINITY);
            }
        }

        // If the horizontal direction is LEFT, the layers are traversed from right to left, thus
        // a reverse iterator is needed (note that this does not change the original list of layers)
        List<Layer> layers = layeredGraph.getLayers();
        if (bal.hdir == HDirection.LEFT) {
            layers = Lists.reverse(layers);
        }

        blockFinished.clear();
        System.out.println("PLACING BLOCKS FOR " + bal);
        for (Layer layer : layers) {
            // As with layers, we need a reversed iterator for blocks for different directions
            List<LNode> nodes = layer.getNodes();
            if (bal.vdir == VDirection.UP) {
                nodes = Lists.reverse(nodes);
            }
            
            // Do an initial placement for all blocks
            for (LNode v : nodes) {
                if (bal.root.get(v).equals(v)) {
                    placeBlock(v, bal);
                }
            }
        }
        
        // Try to compact blocks by shifting them towards each other if there is space between them.
        // It's important to traverse top-bottom or bottom-top here too
        // This is where 'classes' are compacted?!
        for (Layer layer : layers) {
            for (LNode v : layer.getNodes()) {
                bal.y.put(v, bal.y.get(bal.root.get(v)));
                
                // If this is the root node of the block, check if the whole block can be shifted to
                // further compact the drawing (the block's non-root nodes will be processed later by
                // this loop and will thus use the updated y position calculated here)
                if (v.equals(bal.root.get(v))) {
                    double sinkShift = bal.shift.get(bal.sink.get(v));
                    
                    if ((bal.vdir == VDirection.UP && sinkShift > Double.NEGATIVE_INFINITY)
                     || (bal.vdir == VDirection.DOWN  && sinkShift < Double.POSITIVE_INFINITY)) {
                        
                        bal.y.put(v, bal.y.get(v) + sinkShift);
                    }
                }
            }
        }
        
        
        
        // all blocks were placed, shift latecomers
        while (!postProcessables.isEmpty()) {
            Pair<LNode, Boolean> pair = postProcessables.poll();
            System.out.println("PostProcesS: " + pair);
            
            // TODO !!! it is quite important to check both directions here! 
            // why ... elaborate
            Pair<LEdge, Boolean> pick =
                    pickEdge(bal, pair.getFirst(), pair.getSecond(), bal.y.get(pair.getFirst()),
                            true);

            if (pick.getFirst() == null) {
                pick =
                        pickEdge(bal, pair.getFirst(), pair.getSecond(),
                                bal.y.get(pair.getFirst()), false);
            }
            
            if (!pick.getSecond()) {
                continue;
            }
            
            if (pick.getFirst() == null) {
                continue;   
            }
            
            LEdge edge =  pick.getFirst();
            LPort left = edge.getSource();
            LPort right = edge.getTarget();
            LPort block = bal.hdir == HDirection.LEFT ? right : left;
            LPort fix = bal.hdir == HDirection.LEFT ? left : right;
            
            // t has to be the root node of a different block
            double delta = bal.calculateDelta(fix, block);

            if (delta > 0 && delta < THRESHOLD) {
                
                // target y larger than source y --> shift upwards?
                if (bal.checkSpaceAbove(fix.getNode(), block.getNode(), delta)) {
                    bal.shiftBlock(block.getNode(), -delta);
                }
            } else if (delta < 0 && -delta < THRESHOLD) {
                
                // direction is up, we possibly shifted some blocks too far upward 
                // for an edge to be straight, so check if we can shift down again
                
                // target y smaller than source y --> shift down?
                if (bal.checkSpaceBelow(fix.getNode(), block.getNode(), -delta)) {
                    bal.shiftBlock(block.getNode(), -delta);
                }
            }
            
        }
        
    }

    /**
     * Blocks are placed based on their root node. This is done by going through all layers the block
     * occupies and moving the whole block upwards / downwards if there are blocks that it overlaps
     * with.
     * 
     * @param root The root node of the block (usually called {@code v})
     * @param bal One of the four layouts which shall be used in this step
     */
    private void placeBlock(final LNode root, final BKAlignedLayout bal) {
        // Skip if the block was already placed
        if (bal.y.containsKey(root)) {
            return;
        }
        
        System.out.println("Placing Block " + root);
        
        // Initial placement
        // As opposed to the original algorithm we cannot rely on the fact that 
        //  0.0 as initial block position is always feasible. This is due to 
        //  the inside shift allowing for negative block positions in conjunction with
        //  a RIGHT (bottom-to-top) traversal direction. Computing the minimum with 
        //  an initial position of 0.0 thus leads to wrong results.
        // The wrong behavior is documented in KIPRA-1426
        boolean isInitialAssignment = true;
        bal.y.put(root, 0.0);
        
        // Iterate through block and determine, where the block can be placed (until we arrive at the
        // block's root node again)
        LNode currentNode = root;
        double thresh =
                bal.vdir == VDirection.DOWN ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
        do {
            int currentIndexInLayer = currentNode.getIndex();
            int currentLayerSize = currentNode.getLayer().getNodes().size();
            NodeType currentNodeType = currentNode.getNodeType();
            
            // If the node is the top or bottom node of its layer, it can be placed safely since it is
            // the first to be placed in its layer. If it's not, we'll have to check its neighbours
            if ((bal.vdir == VDirection.DOWN && currentIndexInLayer > 0)
                    || (bal.vdir == VDirection.UP && currentIndexInLayer < (currentLayerSize - 1))) {

                // Get the node which is above / below the current node as well as the root of its block
                LNode neighbor = null;
                LNode neighborRoot = null;
                if (bal.vdir == VDirection.UP) {
                    neighbor = currentNode.getLayer().getNodes().get(currentIndexInLayer + 1);
                } else {
                    neighbor = currentNode.getLayer().getNodes().get(currentIndexInLayer - 1);
                }
                neighborRoot = bal.root.get(neighbor);
                
                // The neighbour's node type is important for the spacing between the two later on
                NodeType neighborNodeType = neighbor.getNodeType();

                // Ensure the neighbor was already placed
                placeBlock(neighborRoot, bal);
                
                // Note that the two nodes and their blocks form a unit called class in the original
                // algorithm. These are combinations of blocks which play a role in the final compaction
                if (bal.sink.get(root).equals(root)) {
                    bal.sink.put(root, bal.sink.get(neighborRoot));
                }
                
                // Check if the blocks of the two nodes are members of the same class
                if (bal.sink.get(root).equals(bal.sink.get(neighborRoot))) {
                    // They are part of the same class
                    
                    // The minimal spacing between the two nodes depends on their node type
                    double spacing = getSpacing(currentNodeType, neighborNodeType);
                    
                    // Determine the block's final position
                    if (bal.vdir == VDirection.UP) {
                        
                        double currentBlockPosition = bal.y.get(root);
                        double newPosition = bal.y.get(neighborRoot)
                                + bal.innerShift.get(neighbor)
                                - neighbor.getMargin().top
                                - spacing
                                - currentNode.getMargin().bottom
                                - currentNode.getSize().y
                                - bal.innerShift.get(currentNode);

                        // calculate threshold value for additional straight edges
                        thresh = calculateThreshold(bal, thresh, newPosition, root, currentNode);
                        
                        if (isInitialAssignment) {
                            isInitialAssignment = false;
                            bal.y.put(root, Math.min(newPosition, thresh));
                        } else {
                            bal.y.put(root, Math.min(currentBlockPosition, 
                                                     Math.min(newPosition, thresh)));
                        }
                        
                    } else { // DOWN
                        
                        double currentBlockPosition = bal.y.get(root);
                        double newPosition = bal.y.get(neighborRoot)
                                + bal.innerShift.get(neighbor)
                                + neighbor.getSize().y
                                + neighbor.getMargin().bottom
                                + spacing
                                + currentNode.getMargin().top
                                - bal.innerShift.get(currentNode);

                        // calculate threshold value for additional straight edges
                        thresh = calculateThreshold(bal, thresh, newPosition, root, currentNode);
                        
                        if (isInitialAssignment) {
                            isInitialAssignment = false;
                            bal.y.put(root, Math.max(newPosition, thresh));
                        } else {
                            bal.y.put(root, Math.max(currentBlockPosition, 
                                                     Math.max(newPosition, thresh)));
                        }
                    }
                    
                } else { // CLASSES
                    // TODO Take a look at this code
                    
                    // They are not part of the same class. Compute how the two classes can be compacted
                    // later
                    double spacing = normalSpacing;
                    
                    if (bal.vdir == VDirection.UP) {
                        bal.shift.put(
                                bal.sink.get(neighborRoot),
                                Math.max(bal.shift.get(bal.sink.get(neighborRoot)), bal.y.get(root)
                                        - bal.y.get(neighborRoot)
                                        + bal.blockSize.get(root)
                                        + spacing));
                    } else {
                        bal.shift.put(
                                bal.sink.get(neighborRoot),
                                Math.min(bal.shift.get(bal.sink.get(neighborRoot)), bal.y.get(root)
                                        - bal.y.get(neighborRoot)
                                        - bal.blockSize.get(neighborRoot)
                                        - spacing));
                    }
                }
            } else {
                // it's the first or last node of a layer, we sill wanna update the threshold value
                // FIXME check if thresh is the right 'suggestion' here
                thresh = calculateThreshold(bal, thresh, thresh, root, currentNode);
            }
            
            // Get the next node in the block
            currentNode = bal.align.get(currentNode);
        } while (currentNode != root);
        
        System.out.println("\tDone with " + root);
        blockFinished.add(root);
    }

    private double getSpacing(final NodeType currentNodeType, final NodeType neighborNodeType) {
        double spacing = smallSpacing;
        if (currentNodeType == NodeType.EXTERNAL_PORT
                && neighborNodeType == NodeType.EXTERNAL_PORT) {
            
            spacing = externalPortSpacing;
        } else if (currentNodeType == NodeType.NORMAL
                || neighborNodeType == NodeType.NORMAL) {
            
            // as soon as either of the two involved nodes is a regular node, 
            // use normal spacing
            spacing = normalSpacing;
        }
        return spacing;
    }
    
    
    private double calculateThreshold(final BKAlignedLayout bal, final double currentThreshold,
            final double suggestion, final LNode root, final LNode currentNode) {
        
        double t = currentThreshold;
        
        // Remember that for blocks with a single node both flags can be true
        boolean isRoot = root.equals(currentNode);
        boolean isLast = bal.align.get(currentNode).equals(root);
        
        if (!(isRoot || isLast)) {
            return t;
        }
        
        if (bal.hdir == HDirection.RIGHT) {

            // Note that it is not guaranteed that adjacent nodes are already placed!
            
            if (isRoot) {
                t = getBound(bal, root, true, suggestion);
            }
            if (Double.isInfinite(t) && isLast) {
                t = getBound(bal, currentNode, false, suggestion);
            }
            
        } else { // LEFT
            
            if (isRoot) {
                t = getBound(bal, root, true, suggestion);
            } 
            if (Double.isInfinite(t) && isLast) {
                t = getBound(bal, currentNode, false, suggestion);
            }
        }
        
        return t;
    }
    
    /**
     * Picks an edge that we consider the best choice when 
     * seeking for additional alignments between nodes 
     * of distinct blocks.
     */
    private Pair<LEdge, Boolean> pickEdge(final BKAlignedLayout bal, final LNode root, 
            final boolean isRoot, final double suggestion, final boolean inverted) {
   
        Iterable<LEdge> edges;
        if (isRoot) {
            edges = bal.hdir == HDirection.RIGHT ? root.getIncomingEdges() : root.getOutgoingEdges();
        } else {
            edges = bal.hdir == HDirection.LEFT ? root.getIncomingEdges() : root.getOutgoingEdges();
        }
        
        // pick the edge to check, we want to use the edge that
        // connects to the closest node in iteration direction
        LEdge pick = null;
        double distance = Double.MAX_VALUE;
        boolean hasEdges = false;
        for (LEdge e : edges) {
            hasEdges = true;
            LPort left = e.getSource();
            LPort right = e.getTarget();
            LPort rootPort, otherPort;
            if (isRoot) {
                rootPort = bal.hdir == HDirection.RIGHT ? right : left;
                otherPort = bal.hdir == HDirection.RIGHT ? left : right;
            } else {
                rootPort = bal.hdir == HDirection.LEFT ? right : left;
                otherPort = bal.hdir == HDirection.LEFT ? left : right;
            }

            // if the other node does not have a position yet, ignore this edge
            if (!blockFinished.contains(bal.root.get(otherPort.getNode()))) {
                continue;
            }

            LNode otherRoot = bal.root.get(otherPort.getNode());

            double otherPos =
                    bal.y.get(otherRoot) + bal.innerShift.get(otherPort.getNode())
                            + otherPort.getPosition().y + otherPort.getAnchor().y;

            double rootPos =
                    suggestion + bal.innerShift.get(rootPort.getNode()) + rootPort.getPosition().y
                            + rootPort.getAnchor().y;

            double curDistance = Math.abs(otherPos - rootPos);
            if (!inverted) {
                if (bal.vdir == VDirection.DOWN) {
                    if (otherPos > rootPos && curDistance < distance) {
                        pick = e;
                        distance = curDistance;
                    }
                } else {
                    if (otherPos < rootPos && curDistance < distance) {
                        pick = e;
                        distance = curDistance;
                    }
                }
            } else {
                if (bal.vdir == VDirection.DOWN) {
                    if (otherPos < rootPos && curDistance < distance) {
                        pick = e;
                        distance = curDistance;
                    }
                } else {
                    if (otherPos > rootPos && curDistance < distance) {
                        pick = e;
                        distance = curDistance;
                    }
                }
            }
        }

        return Pair.of(pick, hasEdges);
    }
    
    /**
     * Get threshold value for the <b>root</b> node of the block to be aligned! (As opposed 
     * to the last node in the block. 
     */
    private double getBound(final BKAlignedLayout bal, final LNode blockNode, final boolean isRoot,
            final double suggestion) {

        double invalid = bal.vdir == VDirection.UP ? Double.POSITIVE_INFINITY : Double.NEGATIVE_INFINITY;

        final Pair<LEdge, Boolean> pick = pickEdge(bal, blockNode, isRoot, suggestion, false);
        
        // if edges exist but we couldn't find a good one
        if (pick.getFirst() == null && pick.getSecond()) {
             postProcessables.add(Pair.of(blockNode, isRoot));
             return invalid;
        } else if (pick.getFirst() != null) {

            double threshold;
            LPort left = pick.getFirst().getSource();
            LPort right = pick.getFirst().getTarget();

            if (isRoot) {
                // We handle the root (first) node of a block here
                LPort rootPort = bal.hdir == HDirection.RIGHT ? right : left;
                LPort otherPort = bal.hdir == HDirection.RIGHT ? left : right;
    
                LNode otherRoot = bal.root.get(otherPort.getNode());
                threshold = bal.y.get(otherRoot) 
                                  + bal.innerShift.get(otherPort.getNode())
                                  + otherPort.getPosition().y 
                                  + otherPort.getAnchor().y
                                  // root node
                                  - bal.innerShift.get(rootPort.getNode()) 
                                  - rootPort.getPosition().y
                                  - rootPort.getAnchor().y;
            } else {
                
                // ... and the last node of a block here 
                LPort rootPort = bal.hdir == HDirection.LEFT ? right : left;
                LPort otherPort = bal.hdir == HDirection.LEFT ? left : right;

                threshold = bal.y.get(bal.root.get(otherPort.getNode()))
                        + bal.innerShift.get(otherPort.getNode())
                        + otherPort.getPosition().y
                        + otherPort.getAnchor().y
                        // root node
                        - bal.innerShift.get(rootPort.getNode())
                        - rootPort.getPosition().y
                        - rootPort.getAnchor().y;
            }
            return threshold;
        }
        return invalid;
    }
    
}
