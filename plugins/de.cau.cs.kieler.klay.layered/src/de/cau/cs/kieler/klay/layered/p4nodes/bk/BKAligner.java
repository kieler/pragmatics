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

import static de.cau.cs.kieler.klay.layered.p4nodes.bk.BKNodePlacer.allRightNeighbors;
import static de.cau.cs.kieler.klay.layered.p4nodes.bk.BKNodePlacer.allLeftNeighbors;
import static de.cau.cs.kieler.klay.layered.p4nodes.bk.BKNodePlacer.getBlocks;
import static de.cau.cs.kieler.klay.layered.p4nodes.bk.BKNodePlacer.getEdge;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.p4nodes.bk.BKAlignedLayout.HDirection;
import de.cau.cs.kieler.klay.layered.p4nodes.bk.BKAlignedLayout.VDirection;

/**
 * For documentation see {@link BKNodePlacer}.
 * 
 * @author jjc
 * @author uru
 */
public class BKAligner {
    
    /** The graph to process. */
    private LGraph layeredGraph;
    
    /**
     * @param layeredGraph the graph to handle.
     */
    public BKAligner(final LGraph layeredGraph) {
        this.layeredGraph = layeredGraph;
    }
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////
    // Block Building and Inner Shifting

    /**
     * The graph is traversed in the given directions and nodes a grouped into blocks. The nodes in these
     * blocks will later be placed such that the edges connecting them will be straight lines.
     * 
     * <p>Type 1 conflicts are resolved, so that the dummy nodes of a long edge share the same block if
     * possible, such that the long edge is drawn straightly.</p>
     * 
     * @param bal One of the four layouts which shall be used in this step 
     * @param markedEdges List with all edges that were marked as type 1 conflicts
     */
    public void verticalAlignment(final BKAlignedLayout bal, final List<LEdge> markedEdges) {
        // Initialize root and align maps
        for (Layer layer : layeredGraph.getLayers()) {
            for (LNode v : layer.getNodes()) {
                bal.root.put(v, v);
                bal.align.put(v, v);
                bal.innerShift.put(v, 0.0);
            }
        }

        List<Layer> layers = layeredGraph.getLayers();
        
        // If the horizontal direction is LEFT, the layers are traversed from
        // right to left, thus a reverse iterator is needed
        if (bal.hdir == HDirection.LEFT) {
            layers = Lists.reverse(layers);
        }

        for (Layer layer : layers) {
            // r denotes the position in layer order where the last block was found
            // It is initialized with -1, since nothing is found and the ordering starts with 0
            int r = -1;
            List<LNode> nodes = layer.getNodes();
            
            if (bal.vdir == VDirection.UP) {
                // If the alignment direction is UP, the nodes in a layer are traversed
                // reversely, thus we start at INT_MAX and with the reversed list of nodes.
                r = Integer.MAX_VALUE;
                nodes = Lists.reverse(nodes);
            }
            
            // Variable names here are again taken from the paper mentioned above.
            // i denotes the index of the layer and k the position of the node within the layer.
            // m denotes the position of a neighbor in the neighbor list of a node.
            // CHECKSTYLEOFF Local Variable Names
            for (LNode v_i_k : nodes) {
                List<LNode> neighbors = null;
                if (bal.hdir == HDirection.LEFT) {
                    neighbors = allRightNeighbors(v_i_k);
                } else {
                    neighbors = allLeftNeighbors(v_i_k);
                }

                if (neighbors.size() > 0) {

                    // When a node has many upper neighbors, consider only the (two) nodes in the
                    // middle.
                    int d = neighbors.size();
                    int low = ((int) Math.floor(((d + 1.0) / 2.0))) - 1;
                    int high = ((int) Math.ceil(((d + 1.0) / 2.0))) - 1;

                    if (bal.vdir == VDirection.UP) {
                        // Check, whether v_i_k can be added to a block of its upper/lower neighbor(s)
                        for (int m = high; m >= low; m--) {
                            if (bal.align.get(v_i_k).equals(v_i_k)) {
                                LNode u_m = neighbors.get(m);
                                
                                // Again, getEdge won't return null because the neighbor relationship
                                // ensures that at least one edge exists
                                if (!markedEdges.contains(getEdge(u_m, v_i_k)) && r > u_m.getIndex()) {
                                    bal.align.put(u_m, v_i_k);
                                    bal.root.put(v_i_k, bal.root.get(u_m));
                                    bal.align.put(v_i_k, bal.root.get(v_i_k));
                                    
                                    r = u_m.getIndex();
                                }
                            }
                        }
                    } else {
                        // Check, whether vik can be added to a block of its upper/lower neighbor(s)
                        for (int m = low; m <= high; m++) {
                            if (bal.align.get(v_i_k).equals(v_i_k)) {
                                LNode um = neighbors.get(m);
                                if (!markedEdges.contains(getEdge(um, v_i_k)) && r < um.getIndex()) {
                                    bal.align.put(um, v_i_k);
                                    bal.root.put(v_i_k, bal.root.get(um));
                                    bal.align.put(v_i_k, bal.root.get(v_i_k));
                                    
                                    r = um.getIndex();
                                }
                            }
                        }
                    }
                }
            }
            // CHECKSTYLEOFF Local Variable Names
        }
    }

    /**
     * This phase moves the nodes inside a block, ensuring that all edges inside a block can be drawn
     * as straight lines. It is not included in the original algorithm and adds port and node size
     * handling.
     * 
     * @param bal One of the four layouts which shall be used in this step
     */
    public void insideBlockShift(final BKAlignedLayout bal) {
        Map<LNode, List<LNode>> blocks = getBlocks(bal);
        for (LNode root : blocks.keySet()) {
            // For each block, we place the top left corner of the root node at coordinate (0,0). We
            // then calculate the space required above the top left corner (due to other nodes placed
            // above and to top margins of nodes, including the root node) and the space required below
            // the top left corner. The sum of both becomes the block size, and the y coordinate of each
            // node relative to the block's top border becomes the inner shift of that node.
            
            double spaceAbove = 0.0;
            double spaceBelow = 0.0;
            
            // Reserve space for the root node
            spaceAbove = root.getMargin().top;
            spaceBelow = root.getSize().y + root.getMargin().bottom;
            bal.innerShift.put(root, 0.0);
            
            // Iterate over all other nodes of the block
            LNode current = root;
            LNode next;
            while ((next = bal.align.get(current)) != root) {
                // Find the edge between the current and the next node
                LEdge edge = getEdge(current, next);
                
                // Calculate the y coordinate difference between the two nodes required to straighten
                // the edge
                double portPosDiff = 0.0;
                if (bal.hdir == HDirection.LEFT) {
                    portPosDiff = edge.getTarget().getPosition().y + edge.getTarget().getAnchor().y
                            - edge.getSource().getPosition().y - edge.getSource().getAnchor().y;
                } else {
                    portPosDiff = edge.getSource().getPosition().y + edge.getSource().getAnchor().y
                            - edge.getTarget().getPosition().y - edge.getTarget().getAnchor().y;
                }
                
                // The current node already has an inner shift value that we need to use as the basis
                // to calculate the next node's inner shift
                double nextInnerShift = bal.innerShift.get(current) + portPosDiff;
                bal.innerShift.put(next, nextInnerShift);
                
                // Update the space required above and below the root node's top left corner
                spaceAbove = Math.max(spaceAbove,
                        next.getMargin().top - nextInnerShift);
                spaceBelow = Math.max(spaceBelow,
                        nextInnerShift + next.getSize().y + next.getMargin().bottom);
                                
                // The next node is the current node in the next iteration
                current = next;
            }
            
            // Adjust each node's inner shift by the space required above the root node's top left
            // corner (which the inner shifts are relative to at the moment)
            current = root;
            do {
                bal.innerShift.put(current, bal.innerShift.get(current) + spaceAbove);
                current = bal.align.get(current);
            } while (current  != root);
            
            // Remember the block size
            bal.blockSize.put(root, spaceAbove + spaceBelow);
        }
    }

}
