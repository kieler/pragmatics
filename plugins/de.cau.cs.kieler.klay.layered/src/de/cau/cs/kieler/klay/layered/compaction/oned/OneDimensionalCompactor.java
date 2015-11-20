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
package de.cau.cs.kieler.klay.layered.compaction.oned;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeSet;
import java.util.function.BiFunction;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.options.Direction;
import de.cau.cs.kieler.klay.layered.compaction.recthull.Scanline;
import de.cau.cs.kieler.klay.layered.compaction.recthull.Scanline.EventHandler;

/**
 * Implements the compaction of a {@link CGraph}.
 * 
 * @author dag
 */
public final class OneDimensionalCompactor {
    
    /** the {@link CGraph}. */
    private CGraph cGraph;
    /** compacting in this direction. */
    private Direction direction = Direction.UNDEFINED;
    /** a function that sets the {@link CNode#reposition} flag according to the direction. */
    private BiFunction<CNode, Direction, Boolean> lockingStrategy;
    /** flag indicating whether the {@link #finish()} method has been called. */
    private boolean finished = false;
    
    private ISpacingsHandler<? super CNode> spacingsHandler = ISpacingsHandler.DEFAULT_SPACING_HANDLER;
    
    /**
     * Initializes the fields of the {@link OneDimensionalCompactor}.
     * 
     * @param cGraph
     *          the graph to compact
     */
    public OneDimensionalCompactor(final CGraph cGraph) {
        
        this.cGraph = cGraph;
        // the default locking strategy locks CNodes if they are not constrained
        setLockingStrategy((n, d) -> !(n.outDegree == 0));
        
        // for any pre-specified groups, deduce the offset of the elements
        calculateGroupOffsets();
        
        // the compaction operates solely on CGroups, 
        // thus, we wrap any plain CNodes into a CGroup
        for (CNode n : cGraph.cNodes) {
            if (n.cGroup == null) {
                CGroup group = new CGroup(n);
                cGraph.cGroups.add(group);
            }
        }
    }
    
    /**
     * Sets the spacing handler to the passed handler. Not using this method results in the
     * {@link ISpacingsHandler#DEFAULT_SPACING_HANDLER} being used.
     * 
     * @param handler
     *            the {@link ISpacingsHandler} to use.
     * @return this instance of {@link OneDimensionalCompactor}
     */
    public OneDimensionalCompactor setSpacingsHandler(final ISpacingsHandler<? super CNode> handler) {
        this.spacingsHandler = handler;
        return this;
    }
    
    /**
     * Compacts the graph in the specified direction.
     * 
     * @return
     *          this instance of {@link OneDimensionalCompactor}
     */
    public OneDimensionalCompactor compact() {
        
        if (finished) {
            throw new IllegalStateException("The " + getClass().getSimpleName()
                    + " instance has been finished already.");
        }
        
        // if no direction was specified the direction defaults to LEFT
        if (direction == Direction.UNDEFINED) {
            changeDirection(Direction.LEFT);
        }
        
        // reset any previously calculated start positions
        for (CNode n : cGraph.cNodes) {
            n.startPos = Double.NEGATIVE_INFINITY;
        }
        
        // perform the actual compaction
        compactCGroups();
        
        // remove all locks
        cGraph.cNodes.forEach(n -> n.reposition = true); // SUPPRESS CHECKSTYLE InnerAssignment
        
        return this;
    }
    
    /**
     * Call this method to indicate to the compacter that the compaction is finished now. No further
     * compaction steps are allowed. As a result, the direction is changed to the compactors natural
     * LEFT direction. In other words, if coordinates have been mirrored or transposed, they are
     * back to the original orientation now.
     * 
     * @return this instance of {@link OneDimensionalCompactor}. Note that the only use case after
     *         this call is to print debug output.
     */
    public OneDimensionalCompactor finish() {
        changeDirection(Direction.LEFT);
        finished = true;
        
        return this;
    }
    
    /**
     * Changes the direction for compaction by transforming the hitboxes.
     * 
     * @param dir
     *          the new direction
     * @return
     *          this instance of {@link OneDimensionalCompactor}
     */
    public OneDimensionalCompactor changeDirection(final Direction dir) {
        
        if (finished) {
            throw new IllegalStateException("The " + getClass().getSimpleName()
                    + " instance has been finished already.");
        }
        
        // prohibits vertical compaction if the graph has edges, because vertical and horizontal
        // segments would interchange
        if (!cGraph.supports(dir)) {
            throw new RuntimeException(
                    "The direction " + dir + " is not supported by the CGraph instance.");
        }
        
        Direction oldDirection = direction;
        direction = dir;
        
        // executes required transformations and calculates constraints
        // if the graph is compacted in opposing directions the constraints are reversed instead of
        // being recalculated and CNodes are locked
        switch (oldDirection) {
        case UNDEFINED:
            switch (dir) {
            case LEFT:
                calculateConstraints();
                break;
                
            case RIGHT:
                mirrorHitboxes(); calculateConstraints();
                break;
                
            case UP:
                transposeHitboxes(); calculateConstraints();
                break;
                
            case DOWN:
                transposeHitboxes(); mirrorHitboxes(); calculateConstraints();
                break;

            default:
                break;
            }
            break;
            
        case LEFT:
            switch (dir) {
            case RIGHT:
                mirrorHitboxes(); reverseConstraints();
                break;
                
            case UP:
                transposeHitboxes(); calculateConstraints();
                break;
                
            case DOWN:
                transposeHitboxes(); mirrorHitboxes(); calculateConstraints();
                break;

            default:
                break;
            }
            break;
            
        case RIGHT:
            switch (dir) {
            case LEFT:
                mirrorHitboxes(); reverseConstraints();
                break;
                
            case UP:
                mirrorHitboxes(); transposeHitboxes(); calculateConstraints();
                break;
                
            case DOWN:
                mirrorHitboxes(); transposeHitboxes(); mirrorHitboxes(); calculateConstraints();
                break;

            default:
                break;
            }
            break;
            
        case UP:
            switch (dir) {
            case LEFT:
                transposeHitboxes(); calculateConstraints();
                break;
                
            case RIGHT:
                transposeHitboxes(); mirrorHitboxes(); calculateConstraints();
                break;
                
            case DOWN:
                mirrorHitboxes(); reverseConstraints();
                break;

            default:
                break;
            }
            break;
    
        case DOWN:
            switch (dir) {
            case LEFT:
                mirrorHitboxes(); transposeHitboxes(); calculateConstraints();
                break;
                
            case RIGHT:
                mirrorHitboxes(); transposeHitboxes(); mirrorHitboxes(); calculateConstraints();
                break;
                
            case UP:
                mirrorHitboxes(); reverseConstraints();
                break;

            default:
                break;
            }
            break;

        default:
            break;
        }
        
        return this;
    }
    
    /**
     * Sets the function to lock cGraph.cNodes. Does not apply the strategy yet, to do so, call
     * {@link #applyLockingStrategy(Direction)}.
     * 
     * @param strategy
     *            a function that returns the required state for the reposition flag of a
     *            {@link CNode} according to the direction
     * @return this instance of {@link OneDimensionalCompactor}
     */
    public OneDimensionalCompactor setLockingStrategy(
            final BiFunction<CNode, Direction, Boolean> strategy) {
        lockingStrategy = strategy;
        return this;
    }
    
    /**
     * Locks nodes according to the currently set locking strategy and the current direction. After
     * the next call to {@link #compact()}, all locks are removed again.
     * 
     * @return this instance of {@link OneDimensionalCompactor}
     * 
     * @see #setLockingStrategy(BiFunction)
     */
    public OneDimensionalCompactor applyLockingStrategy() {
        applyLockingStrategy(direction);
        return this;
    }

    /**
     * Locks nodes according to the currently set locking strategy and the current direction. After
     * the next call to {@link #compact()}, all locks are removed again.
     * 
     * @param dir
     *            the {@link Direction} for the locks to be set.
     * @return this instance of {@link OneDimensionalCompactor}
     * 
     * @see #setLockingStrategy(BiFunction)
     */
    public OneDimensionalCompactor applyLockingStrategy(final Direction dir) {

        for (CNode cNode : cGraph.cNodes) {
            cNode.reposition = lockingStrategy.apply(cNode, dir);
        }

        return this;
    }
    
    /**
     * Initiates a recalculation of all constraints. Usually this method does not have to be called
     * manually since {@link #changeDirection(Direction)} either recalculates or reversed
     * constraints as necessary.
     * 
     * @return this instance of a {@link OneDimensionalCompactor}.
     */
    public OneDimensionalCompactor forceConstraintsRecalculation() {
        calculateConstraints();
        return this;
    }

    /**
     * For each {@link CGroup} g of the {@link CGroup} to be compacted, calculates the offsets of
     * g's nodes. It shouldn't be necessary to call this method in the common case since its either
     * called by the constructor or for certain direction changes in
     * {@link #changeDirection(Direction)}.
     * 
     * @return this instance of a {@link OneDimensionalCompactor}.
     */
    public OneDimensionalCompactor calculateGroupOffsets() {
        // for any pre-specified groups, deduce the offset of the elements
        for (CGroup group : cGraph.cGroups) {
            group.reference = null;
            for (CNode n : group.cNodes) {
                if (group.reference == null) {
                    n.cGroupOffset.reset();
                    group.reference = n;
                } else {
                    n.cGroupOffset.x = n.hitbox.x - group.reference.hitbox.x;
                    n.cGroupOffset.y = n.hitbox.y - group.reference.hitbox.y;
                }
            }
        }
        
        return this;
    }

    //////////////////////////////////////////// PRIVATE API ////////////////////////////////////////////
    
    /**
     * Mirrors hitboxes horizontally.
     */
    private void mirrorHitboxes() {
        for (CNode cNode : cGraph.cNodes) {
            cNode.hitbox.x = -cNode.hitbox.x - cNode.hitbox.width;
            // mirroring the offsets inside CGroups
            // FIXME do we still require this?!
            if (cNode.parentNode != null) {
                cNode.cGroupOffset.x = -cNode.cGroupOffset.x + cNode.parentNode.hitbox.width;
            }
        }
        
        calculateGroupOffsets();
    }
    
    /**
     * Transposes hitboxes.
     */
    private void transposeHitboxes() {
        double tmp;
        for (CNode cNode : cGraph.cNodes) {
            tmp = cNode.hitbox.x;
            cNode.hitbox.x = cNode.hitbox.y;
            cNode.hitbox.y = tmp;
            
            tmp = cNode.hitbox.width;
            cNode.hitbox.width = cNode.hitbox.height;
            cNode.hitbox.height = tmp;

            tmp = cNode.cGroupOffset.x;
            cNode.cGroupOffset.x = cNode.cGroupOffset.y;
            cNode.cGroupOffset.y = tmp;
        }
        
        calculateGroupOffsets();
    }
    
    /**
     * Sets the constraints of {@link CGroup}s according to the constraints of the included
     * {@link CNode}s.
     */
    private void calculateConstraintsForCGroups() {
        
        for (CGroup group : cGraph.cGroups) {
            group.outDegree = 0;
            group.incomingConstraints.clear();
        }
        
        for (CGroup group : cGraph.cGroups) {
            for (CNode cNode : group.cNodes) {
                for (CNode inc : cNode.constraints) {

                    if (inc.cGroup != group) {
                        // adding constraints to each CGroup, that refer to CNodes outside of the
                        // CGroup
                        group.incomingConstraints.add(inc);
                        // setting the outDegree of CGroups
                        // (number of CNodes outside the group that the group is constrained by)
                        inc.cGroup.outDegree++;
                    }
                }
            }
        }
        
    }
    
    private void calculateConstraints() {

        calculateConstraints2();

    }
    
    /* -----------------------------------------------------------------------------------------------
     * Scanline-procedure to calculate the constraint graph in O(nlogn) time, n being the number of
     * rectangles.
     * 
     * The implementation is based on the section 10.2.2.1.1.1 of the following book:
     * Lengauer, T. (1990). Combinatorial algorithms for integrated circuit layout. John Wiley & Sons.
     * -----------------------------------------------------------------------------------------------
     */
    
    /**
     * A timestamp in the sense of the scanline algorithm to determine the constraints of the
     * compaction graph. The scanline traverses the plane from top (negative infinity) to bottom
     * (positive infinity). Every timestamp (or point) it encounters either represents the upper (y)
     * or lower (y+height) border of a rectangle. Note that y is also denoted as "low" and y+height
     * as "high".
     */
    private class Timestamp {
        private boolean low;
        private CNode node;

        public Timestamp(final CNode node, final boolean low) {
            this.node = node;
            this.low = low;
        }
    }
  
    /**
     * Can be used to sort the passed {@link Timestamp}s either based on their 
     * {@link CNode}'s y-coordinate (low) or y-coordinate plus height (high).
     * If two positions are equal, the "low" value is sorted before the "high" value.
     */
    private static final Comparator<Timestamp> CONSTRAINTS_SCANLINE_COMPARATOR = (p1, p2) ->  {
        
        // chose the right coordinate
        double y1 = p1.node.hitbox.y;
        if (!p1.low) {
            y1 += p1.node.hitbox.height;
        }
        double y2 = p2.node.hitbox.y;
        if (!p2.low) {
            y2 += p2.node.hitbox.height;
        }

        // compare them ...
        int cmp = Double.compare(y1 , y2);
        
        // ... and if they are equal, sort low->high
        if (cmp == 0) {
            if (!p1.low && p2.low) {
                return 1;
             } else if (!p2.low && p1.low) {
                 return -1;
             }    
        } 
        return cmp;
    };
    
    /**
     * Implements the scanline procedure as discussed by Lengauer.
     */
    private class ConstraintsScanlineHandler implements EventHandler<OneDimensionalCompactor.Timestamp> {

        /**
         * Sorted set of intervals that allows to query for the left and right neighbor of
         * an interval. Sorted by the x coordinate of a {@link CNode}'s hitbox.
         */
        private TreeSet<CNode> intervals = Sets.newTreeSet((c1, c2) -> Double.compare(
                c1.hitbox.x, c2.hitbox.x));
        /** Candidate array with possible constraints. */
        private CNode[] cand;

        /** 
         * Resets the internal data structures.
         */
        public void reset() {
            intervals.clear();
            cand = new CNode[cGraph.cNodes.size()];
            int index = 0;
            for (CNode n : cGraph.cNodes) {
                n.id = index++;
            }
        }

        @Override
        public void handle(final Timestamp p) {
            // System.out.println((p.low ? "insert " : "delete ") + " " + p.node.hitbox);
            if (p.low) {
                insert(p);
            } else {
                delete(p);
            }
        }

        private void insert(final Timestamp p) {
            intervals.add(p.node);

            cand[p.node.id] = intervals.lower(p.node);

            CNode right = intervals.higher(p.node);
            if (right != null) {
                cand[right.id] = p.node; 
            }
        }

        private void delete(final Timestamp p) {

            CNode left = intervals.lower(p.node);
            if (left != null && left == cand[p.node.id]) {
                // different groups?
                if (left.cGroup != null && left.cGroup != p.node.cGroup) {
                    left.constraints.add(p.node);
                    p.node.outDegree++;
                }
            }

            CNode right = intervals.higher(p.node);
            if (right != null && cand[right.id] == p.node) {
                // different groups?
                if (right.cGroup != null && right.cGroup != p.node.cGroup) {
                    p.node.constraints.add(right);
                    right.outDegree++;
                }
            }

            // we are done with you!
            intervals.remove(p.node);
        }
    }
    
    /** This compactor's instance of the scanline handler. */
    private final ConstraintsScanlineHandler constraintsScanlineHandler =
            new ConstraintsScanlineHandler();

    private void calculateConstraints2() {
        
        // resetting constraints
        for (CNode cNode : cGraph.cNodes) {
            cNode.constraints.clear();
            cNode.outDegree = 0;
        }
        
        // add all nodes twice (once for the lower, once for the upper border)
        List<Timestamp> points = Lists.newArrayList();
        for (CNode n : cGraph.cNodes) {
            points.add(new Timestamp(n, true));
            points.add(new Timestamp(n, false));
        }
        
        // reset internal state
        constraintsScanlineHandler.reset();
        
        // execute the scanline
        Scanline.execute(points, CONSTRAINTS_SCANLINE_COMPARATOR, constraintsScanlineHandler);
        
        // update the "external" constraints of the groups
        calculateConstraintsForCGroups();
    }

    /**
     * Creates a constraint between CNodes A and B if B collides with the right shadow of A
     * considering vertical spacing.
     */
    private void calculateConstraints3() {
        // resetting constraints
        for (CNode cNode : cGraph.cNodes) {
            cNode.constraints.clear();
            cNode.outDegree = 0;
        }
        
        // inferring constraints from hitbox intersections
        for (CNode cNode1 : cGraph.cNodes) {
            for (CNode cNode2 : cGraph.cNodes) {
                // no self constraints
                if (cNode1 == cNode2) {
                    continue;
                }
                // no constraints between nodes of the same group
                if (cNode1.cGroup != null && cNode1.cGroup == cNode2.cGroup) {
                    continue;
                }
                
                double spacing;
                if (direction.isHorizontal()) {
                    //spacing = Math.min(cNode1.getVerticalSpacing(), cNode2.getVerticalSpacing());
                    spacing = spacingsHandler.getVerticalSpacing(cNode1, cNode2);
                } else {
                    //spacing = Math.min(cNode1.getHorizontalSpacing(), cNode2.getHorizontalSpacing());
                    spacing = spacingsHandler.getHorizontalSpacing(cNode1, cNode2);
                }
                
                // add constraint if cNode2 is to the right of cNode1 and could collide if moved
                // horizontally
                // exclude parentNodes because they don't constrain their north/south segments
                if (cNode1 != cNode2.parentNode
                        // '>' avoids simultaneous constraints A->B and B->A
                        && (cNode2.hitbox.x > cNode1.hitbox.x 
                                // 
                                || (cNode1.hitbox.x == cNode2.hitbox.x 
                                    && cNode1.hitbox.width < cNode2.hitbox.width))

                        && CompareFuzzy.gt(cNode2.hitbox.y + cNode2.hitbox.height + spacing,
                                cNode1.hitbox.y)

                        && CompareFuzzy.lt(cNode2.hitbox.y, cNode1.hitbox.y + cNode1.hitbox.height
                                + spacing)) {

                    cNode1.constraints.add(cNode2);
                    cNode2.outDegree++;
                }
            }
        }

        // resetting constraints for CGroups
        calculateConstraintsForCGroups();
    }

    /**
     * If the graph is compacted twice in opposing directions, the constraints can be reversed to
     * avoid recalculating them. Also the {@link CNode}s' starting position is reset to be ready for
     * another compaction.
     */
    private void reverseConstraints() {
//        calculateConstraints();
        // maps CNodes to temporary lists of incoming constraints
        Map<CNode, List<CNode>> incMap = Maps.newHashMap();
        for (CNode cNode : cGraph.cNodes) {
            incMap.put(cNode, Lists.newArrayList());
        }
        
        // resetting fields of CNodes and reversing constraints
        for (CNode cNode : cGraph.cNodes) {
            cNode.startPos = Double.NEGATIVE_INFINITY;
            cNode.outDegree = 0;
            for (CNode inc : cNode.constraints) {
                cNode.outDegree++;
                incMap.get(inc).add(cNode);
            }
        }
        
        // write back
        for (CNode cNode : cGraph.cNodes) {
            cNode.constraints.clear();
            cNode.constraints = incMap.get(cNode);
        }
        
        // resetting constraints for CGroups
        calculateConstraintsForCGroups();
    }

    /**
     * Compacts CGroups and the CNodes inside.
     */
    private void compactCGroups() {
        // calculating the leftmost position to compact against
        double minStartPos = Double.POSITIVE_INFINITY;
        for (CNode cNode : cGraph.cNodes) {
            minStartPos = Math.min(minStartPos, cNode.getPosition());
        }
        
        // queues CGroups whose outgoing constraints are processed
        Queue<CGroup> sinks = Lists.newLinkedList();
        
        // finding and compacting CGroups that are sinks
        for (CGroup group : cGraph.cGroups) {
            if (group.isInnerCompactable()) {
                group.compactInnerCNodes(minStartPos, spacingsHandler, direction);
                sinks.add(group);
            }
        }
        
        // propagating the positions of sinks to set new starting positions for constrained CNodes
        // and CGroups
        while (!sinks.isEmpty()) {
            
            CGroup group = sinks.poll();
            List<CGroup> compactables = group.propagate(spacingsHandler, direction);
            
            // compacting CGroups that became sinks in this iteration
            for (CGroup g : compactables) {
                g.compactInnerCNodes(minStartPos, spacingsHandler, direction);
                sinks.add(g);
            }
            compactables.clear();
        }
        
        // setting hitbox positions to new starting positions
        for (CNode cNode : cGraph.cNodes) {
            cNode.applyPosition();
        }
    }
    
    
    //////////////////////////////////////////// DEBUGGING ////////////////////////////////////////////
    
    /**
     * For debugging. Writes hitboxes to svg.
     * 
     * @param name
     *          filename
     * @return
     *          this instance of {@link OneDimensionalCompactor}
     */
    public OneDimensionalCompactor drawHitboxes(final String name) {
        //determine viewBox
        KVector topLeft = new KVector(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        KVector bottomRight = new KVector(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
        for (CNode cNode : cGraph.cNodes) {
            topLeft.x = Math.min(topLeft.x, cNode.hitbox.x);
            topLeft.y = Math.min(topLeft.y, cNode.hitbox.y);
            bottomRight.x = Math.max(bottomRight.x, cNode.hitbox.x + cNode.hitbox.width);
            bottomRight.y = Math.max(bottomRight.y, cNode.hitbox.y + cNode.hitbox.height);
        }
        
        KVector size = bottomRight.clone().sub(topLeft);

        // drawing hitboxes to svg
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(name));

            out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            out.println("<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"100%\" height=\"100%\""
                    + "  viewBox=\"" + (topLeft.x) + " " + (topLeft.y) 
                    + " " + size.x + " " + size.y + "\">");

            for (CNode cNode : cGraph.cNodes) {
                
                // the node's representation
                out.println(cNode.getDebugSVG());

                // the constraints
                for (CNode inc : cNode.constraints) {
                    out.println("<line x1=\"" + (cNode.hitbox.x + cNode.hitbox.width / 2)
                            + "\" y1=\"" + (cNode.hitbox.y + cNode.hitbox.height / 2) + "\" x2=\""
                            + (inc.hitbox.x + inc.hitbox.width / 2) + "\" y2=\""
                            + (inc.hitbox.y + inc.hitbox.height / 2)
                            + "\" stroke=\"grey\" opacity=\"0.2\"/>");
                }
            }

            out.println("</svg>");

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }
}
