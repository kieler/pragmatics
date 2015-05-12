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

import java.util.List;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LNode.NodeType;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.p4nodes.BKNodePlacer.BKAlignedLayout;
import de.cau.cs.kieler.klay.layered.p4nodes.BKNodePlacer.HDirection;
import de.cau.cs.kieler.klay.layered.p4nodes.BKNodePlacer.VDirection;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * For documentation see {@link BKNodePlacer}.
 * 
 * @author jjc
 * @author uru
 */
public class BKCompacter {
    
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
    public BKCompacter(final LGraph layeredGraph) {
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
    }
    
    /* Note: The following methods diverts from the convention of naming variables as they were named in
     *       the original paper for better code readability. (Since this is one of the most intricate
     *       pieces of code in the algorithm.) The original variable names are mentioned in comments.
     */

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

                        if (isInitialAssignment) {
                            isInitialAssignment = false;
                            bal.y.put(root, newPosition);
                        } else {
                            bal.y.put(root, Math.min(currentBlockPosition, newPosition));
                        }
                    } else {
                        double currentBlockPosition = bal.y.get(root);
                        double newPosition = bal.y.get(neighborRoot)
                                + bal.innerShift.get(neighbor)
                                + neighbor.getSize().y
                                + neighbor.getMargin().bottom
                                + spacing
                                + currentNode.getMargin().top
                                - bal.innerShift.get(currentNode);
                        
                        if (isInitialAssignment) {
                            isInitialAssignment = false;
                            bal.y.put(root, newPosition);
                        } else {
                            bal.y.put(root, Math.max(currentBlockPosition, newPosition));
                        }
                    }
                } else {

                    // They are not part of the same class. Compute how the two classes can be compacted
                    // later. Hence we determine a minimal required space between the two classes 
                    // relative two the two class sinks.
                    double spacing = normalSpacing;
                    
                    if (bal.vdir == VDirection.UP) {
                        //  possible setup:
                        //  root         --> currentNode  
                        //  neighborRoot --> neighbor
                        double requiredSpace = 
                                bal.y.get(root)
                                + bal.innerShift.get(currentNode)
                                + currentNode.getSize().y
                                + currentNode.getMargin().top
                                + spacing
                                - bal.y.get(neighborRoot)
                                - bal.innerShift.get(neighbor)
                                - neighbor.getMargin().top;
                        
                        bal.shift.put(bal.sink.get(neighborRoot),
                                Math.max(bal.shift.get(bal.sink.get(neighborRoot)), requiredSpace));
                    } else {
                        //  possible setup:
                        //  neighborRoot --> neighbor 
                        //  root         --> currentNode
                        double requiredSpace =
                                bal.y.get(root) 
                                + bal.innerShift.get(currentNode)
                                + currentNode.getMargin().top
                                - bal.y.get(neighborRoot)
                                - bal.innerShift.get(neighbor)
                                - neighbor.getSize().y
                                - neighbor.getMargin().bottom
                                - spacing;
                        
                        bal.shift.put(bal.sink.get(neighborRoot),
                                Math.min(bal.shift.get(bal.sink.get(neighborRoot)), requiredSpace));
                    }
                }
            }
            
            // Get the next node in the block
            currentNode = bal.align.get(currentNode);
        } while (currentNode != root);
    }
    
}
