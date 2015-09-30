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
package de.cau.cs.kieler.klay.layered.compaction;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.function.BiFunction;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.options.Direction;

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
    }
    
    /**
     * Compacts the graph in the specified direction.
     * 
     * @return
     *          this instance of {@link OneDimensionalCompactor}
     */
    public OneDimensionalCompactor compact() {
        // if no direction was specified the direction defaults to LEFT
        if (direction == Direction.UNDEFINED) {
            changeDirection(Direction.LEFT);
        }
        
        compactCGroups();
        
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
        
        // prohibits vertical compaction if the graph has edges, because vertical and horizontal
        // segments would interchange
        if (!cGraph.supports(dir)) {
            throw new RuntimeException(
                    "Vertical compaction is not permitted on graphs containing edges.");
        }
        
        // executes required transformations and calculates constraints
        // if the graph is compacted in opposing directions the constraints are reversed instead of
        // being recalculated and CNodes are locked
        switch (direction) {
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
                mirrorHitboxes(); reverseConstraints(); lockCNodes(dir);
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
                mirrorHitboxes(); reverseConstraints(); lockCNodes(dir);
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
                mirrorHitboxes(); transposeHitboxes(); mirrorHitboxes(); calculateConstraints();
                break;
                
            case RIGHT:
                mirrorHitboxes(); transposeHitboxes(); calculateConstraints();
                break;
                
            case DOWN:
                mirrorHitboxes(); reverseConstraints(); lockCNodes(dir);
                break;

            default:
                break;
            }
            break;
    
        case DOWN:
            switch (dir) {
            case LEFT:
                transposeHitboxes(); mirrorHitboxes(); calculateConstraints();
                break;
                
            case RIGHT:
                transposeHitboxes(); calculateConstraints();
                break;
                
            case UP:
                mirrorHitboxes(); reverseConstraints(); lockCNodes(dir);
                break;

            default:
                break;
            }
            break;

        default:
            break;
        }
        
        direction = dir;
        return this;
    }
    
    /**
     * Mirrors hitboxes horizontally.
     */
    private void mirrorHitboxes() {
        for (CNode cNode : cGraph.cNodes) {
            cNode.hitbox.x = -cNode.hitbox.x - cNode.hitbox.width;
            // mirroring the offsets inside CGroups
            if (cNode.parentNode != null) {
                cNode.cGroupOffset = -cNode.cGroupOffset + cNode.parentNode.hitbox.width;
            }
        }
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
        }
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
    
    /**
     * Sets the function to lock cGraph.cNodes.
     * 
     * @param strategy
     *          a function that returns the required state for the reposition flag of a {@link CNode}
     *          according to the direction
     * @return
     *          this instance of {@link OneDimensionalCompactor}
     */
    public OneDimensionalCompactor setLockingStrategy(
            final BiFunction<CNode, Direction, Boolean> strategy) {
        lockingStrategy = strategy;
        return this;
    }

    /**
     * Locks the position of CNodes before a second compaction in opposite direction
     * based on the {@link OneDimensionalCompactor#lockingStrategy locking strategy}.
     * 
     * @param dir
     *          the direction
     */
    private void lockCNodes(final Direction dir) {
        for (CNode cNode : cGraph.cNodes) {
            cNode.reposition = lockingStrategy.apply(cNode, dir);
        }
    }

    /**
     * Creates a constraint between CNodes A and B if B collides with the right shadow of A
     * considering vertical spacing.
     */
    private void calculateConstraints() {
        // resetting constraints
        for (CNode cNode : cGraph.cNodes) {
            cNode.constraints.clear();
            cNode.outDegree = 0;
        }
        
        // inferring constraints from hitbox intersections
        for (CNode cNode1 : cGraph.cNodes) {
            for (CNode cNode2 : cGraph.cNodes) {
                double spacing = cNode1.getVerticalSpacing(cNode2);

                // add constraint if cNode2 is to the right of cNode1 and could collide if moved
                // horizontally
                // exclude parentNodes because they don't constrain their north/south segments
                if (cNode1 != cNode2 && cNode1 != cNode2.parentNode
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
                group.compactInnerCNodes(minStartPos);
                sinks.add(group);
            }
        }
        
        // propagating the positions of sinks to set new starting positions for constrained CNodes
        // and CGroups
        while (!sinks.isEmpty()) {
            
            CGroup group = sinks.poll();
            List<CGroup> compactables = group.propagate();
            
            // compacting CGroups that became sinks in this iteration
            for (CGroup g : compactables) {
                g.compactInnerCNodes(minStartPos);
                sinks.add(g);
            }
            compactables.clear();
        }
        
        // setting hitbox positions to new starting positions
        for (CNode cNode : cGraph.cNodes) {
            cNode.applyPosition();
        }
    }
    
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

            out.println("<svg viewBox=\"" + (topLeft.x) + " "
                    + (topLeft.y) + " " + size.x + " "
                    + size.y + "\">");

            for (CNode cNode : cGraph.cNodes) {
                if (cNode instanceof CLNode) {
                    out.println("<rect x=\"" + cNode.hitbox.x + "\" y=\"" + cNode.hitbox.y
                            + "\" width=\"" + cNode.hitbox.width + "\" height=\""
                            + cNode.hitbox.height + "\" fill=\""
                            + (cNode.reposition ? "green" : "orange")
                            + "\" stroke=\"black\" opacity=\"0.5\"/>");
                    out.println("<text x=\"" + (cNode.hitbox.x + 2) 
                            + "\" y=\"" + (cNode.hitbox.y + 2 * 2 * 2 + 2 + 1) + "\">" 
                            + "(" + Math.round(cNode.hitbox.x) 
                            + ", " + Math.round(cNode.hitbox.y) + ")\n" 
                            + cNode + "</text>");
                } else {
                    out.println("<line x1=\"" + cNode.hitbox.x + "\" y1=\"" + cNode.hitbox.y
                            + "\" x2=\"" + (cNode.hitbox.x + cNode.hitbox.width) + "\" y2=\""
                            + (cNode.hitbox.y + cNode.hitbox.height) + "\" stroke=\""
                            + (cNode.reposition ? "blue" : "red") + "\" stroke-width=\"3px\"/>");
                }
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
