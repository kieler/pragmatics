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
package de.cau.cs.kieler.klay.layered.compaction.components;

import com.google.common.collect.Multimap;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.options.Direction;
import de.cau.cs.kieler.klay.layered.compaction.oned.CGraph;
import de.cau.cs.kieler.klay.layered.compaction.oned.CGroup;
import de.cau.cs.kieler.klay.layered.compaction.oned.CNode;
import de.cau.cs.kieler.klay.layered.compaction.oned.OneDimensionalCompactor;

/**
 * Compacts connected components using a {@link OneDimensionalCompactor}. Consecutively applies
 * horizontal and vertical compaction until no improvement can be made anymore.
 * 
 * @author uru
 * @param <N>
 *            the type of the contained nodes
 * @param <E>
 *            the type of the contained edges
 */
public final class OneDimensionalComponentsCompaction<N, E> {

    private static final int MAX_ITERATION = 10;
    private static final double EPSILON = 0.0001;
    
    private CGraph compactionGraph;

    private ComponentsToCGraphTransformer<N, E> transformer;

    private OneDimensionalCompactor compactor;
    
    private OneDimensionalComponentsCompaction() { }

    /**
     * .
     * 
     * @param ccs
     *            .
     * @param spacing
     *            .
     * @return . 
     * @param <N> the type of the contained nodes
     * @param <E>
     *            the type of the contained edges
     */
    public static <N, E> OneDimensionalComponentsCompaction<N, E> init(
            final IConnectedComponents<N, E> ccs, final double spacing) {

        OneDimensionalComponentsCompaction<N, E> compaction =
                new OneDimensionalComponentsCompaction<N, E>();
        compaction.transformer = new ComponentsToCGraphTransformer<N, E>(spacing);
        compaction.compactionGraph = compaction.transformer.transform(ccs);

        return compaction;
    }
    
    /**
     * Executes the compaction.
     * 
     * @param monitor
     *            a progress monitor.
     */
    public void compact(final IKielerProgressMonitor monitor) {
        
        // create or favorite compactor instance
        compactor = new OneDimensionalCompactor(compactionGraph);

        // we "lock" nodes based on outgoing constraints, i.e. if there's a 
        // node in the "opposite" shadow we do not move it away
        // remember do execute an initial unlocked compaction 
        // in #compact() first, though
        compactor.setLockingStrategy((n, d) -> n.outDegree != 0);

        // now execute compaction until no improvement is made or we hit the maximum number of iterations
        int run = 0;
        double delta;
        do {
            delta = compact();
            run++;
        } while (delta > EPSILON && run < MAX_ITERATION);

        // "align" the compaction top left
        compactor.setLockingStrategy((n, d) -> n.lock.get(d) || (n.outDegree != 0 && n.lock.get(d)));
        compact();
        
        // finish it!
        // make sure that the external edge representations have been removed from the 
        // compaction graph at this point for correct graph size calculation
        compactor.finish();
        
        // finally apply the determined positions 
        transformer.applyLayout();
    }

    /**
     * Internal compaction method, performs 3 compactions into each direction (horizontal first,
     * then vertical). The first compaction is unconstrained, the latter two consider the node locks.
     */
    private double compact() {
        
        double delta = 0;
        
        // reset all groups' deltas
        compactionGraph.cGroups.forEach(g -> {
            g.delta = 0;
            g.deltaNormalized = 0;
        });
        
        // ----------------------------------------------------
        // #1 We want to perform horizontal compaction first.
        //    For this we have to add the vertical external 
        //    edges to the hulls to prevent edge node overlaps
        // ----------------------------------------------------
        
        addExternalEdgeRepresentations(transformer.getVerticalExternalEdges());
        
        // there are new nodes in the compaction graph, we have to update the constraints
        compactor.forceConstraintsRecalculation();

        // compact horizontally
        compactor
            .calculateGroupOffsets()
            .changeDirection(Direction.LEFT)
            .compact()
            .changeDirection(Direction.RIGHT)
            .applyLockingStrategy()
            .compact()
            .changeDirection(Direction.LEFT)
            .applyLockingStrategy()
            .compact();
        
        // very important to transform back to LEFT 
        // because the hitboxes representing external edges 
        // that will be added in a second have not been transformed yet
        compactor.changeDirection(Direction.LEFT);

        // remove the vertical external edges again
        removeExternalEdgeRepresentations(transformer.getVerticalExternalEdges());

        // .. and offset the horizontal external edges (that will be added next)
        // according to the just finished horizontal compaction
        transformer.getHorizontalExternalEdges().asMap().forEach((group, nodes) -> {
            nodes.forEach(n -> n.hitbox.x -= group.delta); // SUPPRESS CHECKSTYLE InnerAssignment
        });
        
        // ----------------------------------------------------
        // #2 We want to perform vertical compaction.
        // ----------------------------------------------------
        
        // now add them
        addExternalEdgeRepresentations(transformer.getHorizontalExternalEdges());
    
        // remember delta from horizontal compaction
        for (CGroup g : compactionGraph.cGroups) {
            delta += Math.abs(g.deltaNormalized);
        }
        // reset all groups' deltas
        compactionGraph.cGroups.forEach(g -> {
            g.delta = 0;
            g.deltaNormalized = 0;
        });

        // compact vertically
        compactor
            .calculateGroupOffsets()
            .changeDirection(Direction.UP)
            .compact()
            .changeDirection(Direction.DOWN)
            .applyLockingStrategy()
            .compact()
            .changeDirection(Direction.UP)
            .applyLockingStrategy()
            .compact();
        
        // transform back to LEFT
        compactor.changeDirection(Direction.LEFT);
        
        
        
        // remove the horizontal external edges
        removeExternalEdgeRepresentations(transformer.getHorizontalExternalEdges());
        // ... offset the vertical external edges 
        // (which have been excluded during the last compaction)
        transformer.getVerticalExternalEdges().asMap().forEach((group, nodes) -> {
            nodes.forEach(n -> n.hitbox.y -= group.delta); // SUPPRESS CHECKSTYLE InnerAssignment
         });
        
        compactor.forceConstraintsRecalculation();
        
        // ... and the delta from vertical compaction
        for (CGroup g : compactionGraph.cGroups) {
            delta += Math.abs(g.deltaNormalized);
        }
        
        return delta;
    }
    
    /**
     * @return After {@link #compact(IKielerProgressMonitor)} has been executed, returns the new
     *         size of the graph. That is, the size of a rectangle that represents the bounding box
     *         of all contained {@link IComponent}s.
     * 
     *         Note that this bounding box considers only real nodes and no contribution of external
     *         edges.
     */
    public KVector getGraphSize() {
        return transformer.getGraphSize();
    }

    /**
     * @param c
     *            the {@link IComponent} for which the offset is requested.
     * @return After {@link #compact(IKielerProgressMonitor)} has been executed returns the offset
     *         of this {@link IComponent} compared to the component's position prior to compaction.
     */
    public KVector getOffset(final IComponent<N, E> c) {
        KVector individual = transformer.getOffset(c);
        return individual.negate().add(transformer.getGlobalOffset());
    }

    /**
     * Adds the passed external edges to the {@link #compactionGraph}.
     */
    private void addExternalEdgeRepresentations(final Multimap<CGroup, CNode> ees) {
        ees.asMap().forEach((group, nodes) -> { 
            nodes.forEach(node -> {
                compactionGraph.cNodes.add(node); 
                group.addCNode(node);
            });
        });
    }
    
    /**
     * Remove the passed external edges from the {@link #compactionGraph}. 
     */
    private void removeExternalEdgeRepresentations(final Multimap<CGroup, CNode> ees) {
        ees.asMap().forEach((group, nodes) -> { 
            nodes.forEach(node -> {
                compactionGraph.cNodes.remove(node); 
                group.removeCNode(node);
            });
        });
    }
    
}
