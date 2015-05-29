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

import java.util.Set;

import com.google.common.collect.Sets;

import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.p4nodes.bk.BKAlignedLayout.HDirection;

/**
 * @author uru
 */
public class BKStraightener {

    /** The graph to process. */
    private LGraph layeredGraph;
   
    /**
     * @param layeredGraph the graph to handle.
     */
    public BKStraightener(final LGraph layeredGraph) {
        this.layeredGraph = layeredGraph;
    }
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////
    // Straightness improvement
    /**
     * Tries to draw further edges straigt using a post processing step.
     *         
     * @param bal the {@link BKAlignedLayout} onto which to apply the post processing.
     */
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
                        double delta = bal.calculateDelta(fix, block);

                        if (delta > 0) {
                            // target y larger than source y --> shift upwards?
                            if (bal.checkSpaceAbove(block.getNode(), delta) == delta) {
                                bal.shiftBlock(block.getNode(), -delta);
                                didSomething = true;
                            }
                        } else if (delta < 0) {
                            // target y smaller than source y --> shift down?
                            if (bal.checkSpaceBelow(block.getNode(), -delta) == delta) {
                                bal.shiftBlock(block.getNode(), -delta);
                                didSomething = true;
                            }
                        }
                    }
                }
            }
            i++;
            // SUPPRESS CHECKSTYLE NEXT 4 ber
        } while (didSomething && i < 100000);
        
        if (i == 100000) {
            throw new IllegalStateException("Iteration count overflew, shouldnt happen.");
        }
    }
    
    

    
}
