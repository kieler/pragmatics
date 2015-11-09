/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2015 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klay.layered.intermediate.compaction;

import com.google.common.collect.Sets;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.kiml.options.Direction;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.compaction.oned.CNode;
import de.cau.cs.kieler.klay.layered.compaction.oned.ISpacingsHandler;
import de.cau.cs.kieler.klay.layered.compaction.oned.OneDimensionalCompactor;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * This processor applies additional compaction to an already routed graph and can be executed after
 * {@link de.cau.cs.kieler.klay.layered.p5edges.OrthogonalEdgeRouter OrthogonalEdgeRouter}.
 * Therefore nodes and vertical segments of edges are repositioned in the specified direction where
 * the position is minimal considering the desired spacing between elements.
 * 
 * <p>
 * Since the locking functionality in {@link CLNode} and {@link CLEdge} relies on the direction of
 * incoming and outgoing edges, this processor is required to be executed before the
 * {@link de.cau.cs.kieler.klay.layered.intermediate.ReversedEdgeRestorer ReversedEdgeRestorer}.
 * </p>
 * 
 * <dl>
 * <dt>Precondition:</dt>
 * <dd>The edges are routed orthogonally</dd>
 * <dt>Postcondition:</dt>
 * <dd>Nodes and edges are positioned compact without colliding.</dd>
 * <dt>Slots:</dt>
 * <dd>After phase 5.</dd>
 * <dt>Same-slot dependencies:</dt>
 * <dd>After {@link de.cau.cs.kieler.klay.layered.intermediate.LabelDummyRemover LabelDummyRemover}</dd>
 * <dd>Before {@link de.cau.cs.kieler.klay.layered.intermediate.ReversedEdgeRestorer
 * ReversedEdgeRestorer}</dd>
 * </dl>
 * 
 * @author dag
 */
public class HorizontalGraphCompactor implements ILayoutProcessor {

    /**
     * {@inheritDoc}
     */
    public void process(final LGraph layeredGraph, final IKielerProgressMonitor progressMonitor) {

        progressMonitor.begin("Horizontal Compaction", 1);
        
        // the layered graph is transformed into a CGraph that is passed to OneDimensionalCompactor
        LGraphToCGraphTransformer transformer = new LGraphToCGraphTransformer();
        OneDimensionalCompactor odc =
                new OneDimensionalCompactor(transformer.transform(layeredGraph));
        
        odc.setSpacingsHandler(SPACINGS_HANDLER);
        
        GraphCompactionStrategy strategy = layeredGraph.getProperty(Properties.POST_COMPACTION);
        switch (strategy) {
        case LEFT:
            odc.compact();
            break;
            
        case RIGHT:
            odc.changeDirection(Direction.RIGHT)
               .compact();
            break;
            
        case LEFT_RIGHT_CONSTRAINT_LOCKING:
            // the default locking strategy locks CNodes if they are not constrained
            odc.compact()
               .changeDirection(Direction.RIGHT)
               .applyLockingStrategy()
               .compact();
            break;
            
        case LEFT_RIGHT_CONNECTION_LOCKING:
            // compacting left, locking all CNodes that have fewer connections to the right,
            // then compacting right to shorten unnecessary long edges
            odc.compact()
               .changeDirection(Direction.RIGHT)
               .setLockingStrategy((n, d) -> !n.lock.get(d))
               .applyLockingStrategy()
               .compact();
         break;

        default:
            break;
        }
        
        // since changeDirection may transform hitboxes, the final direction has to be LEFT again
        odc.finish();
        
        // applying the compacted positions to the LGraph and updating its size and offset
        transformer.applyLayout();
        
        progressMonitor.done();
    }
    
    /**
     * An implementation of a {@link ISpacingsHandler} that is able to cope with the special
     * requirements of {@link LGraph}s. For instance, there are special cases for the spacing
     * between {@link CLEdge}s as opposed to {@link CLNode}s.
     */
    private static final ISpacingsHandler<CNode> SPACINGS_HANDLER = new ISpacingsHandler<CNode>() {
        
        /**
         * {@inheritDoc}
         */
        @Override
        public double getHorizontalSpacing(final CNode cNode1, final CNode cNode2) {
            return Math.max(cNode1.getHorizontalSpacing(), cNode2.getHorizontalSpacing());
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public double getVerticalSpacing(final CNode cNode1, final CNode cNode2) {

            // joining north/south segments that belong to the same edge 
            // by setting their spacing to 0
            if (cNode1.parentNode != null
                    && cNode2.parentNode != null
                    && (cNode1 instanceof CLEdge && cNode2 instanceof CLEdge)
                    // this might seem quite expensive but in most cases the sets contain only
                    // one element
                    && !Sets.intersection(((CLEdge) cNode2).originalLEdges,
                            ((CLEdge) cNode2).originalLEdges).isEmpty()) {
                return 0;
            }

            // TODO really min?
            return Math.min(cNode1.getVerticalSpacing(), cNode2.getVerticalSpacing());
        }
        
    };
}
