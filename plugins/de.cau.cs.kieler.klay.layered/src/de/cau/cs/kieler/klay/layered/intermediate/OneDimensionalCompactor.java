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
package de.cau.cs.kieler.klay.layered.intermediate;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.options.Direction;
import de.cau.cs.kieler.klay.layered.graph.LGraph;

/**
 * @author dag
 */
public final class OneDimensionalCompactor {
    /** the layered graph. */
    private LGraph layeredGraph;
    /** the list of {@link CNode}s modeling the constraints in this graph. */
    private List<CNode> cNodes;
    /** groups of elements that are supposed to stay in the configuration they are. */
    private List<CGroup> cGroups;
    /** compacting in this direction. */
    private Direction direction = Direction.UNDEFINED;
    
    public OneDimensionalCompactor(final CGraph cGraph) {
        layeredGraph = cGraph.layeredGraph;
        cNodes = cGraph.cNodes;
        cGroups = cGraph.cGroups;
    }
    
    /**
     * Compacts the graph, reverses the transformation of hitboxes and writes the
     * new positions to the graph elements.
     * @return
     *          this instance of {@link HorizontalGraphCompactor}
     */
    public OneDimensionalCompactor compact() {
        // if no direction was specified readNodes() has to reload the CNodes
        if (direction == Direction.UNDEFINED) {
            changeDirection(Direction.LEFT);
        }
        
        compactCGroups();
        
        /* transform back
         * L: nop
         * R: mirror
         * U: transpose
         * D: mirror, transpose
         */
        switch (direction) {
        case RIGHT:
            mirrorHitboxes();
            break;
            
        case UP:
            transposeHitboxes();
            break;
            
        case DOWN:
            mirrorHitboxes(); transposeHitboxes();
            break;

        default:
            break;
        }
        
        applyNodePositions();
        
        return this;
    }
    
    /**
     * Changes the direction for compaction by transforming the hitboxes.
     * @param dir
     *          the new direction
     * @return
     *          this instance of {@link HorizontalGraphCompactor}
     */
    public OneDimensionalCompactor changeDirection(final Direction dir) {
        /*
         * *>UNDEFINED: nop
         * UNDEFINED>L: read, getcons, group
         * UNDEFINED>R: read, mirror, getcons, group
         * UNDEFINED>U: read, transpose, getcons, group
         * UNDEFINED>D: read, transpose, mirror, getcons, group
         * L>R|R>L|U>D|D>U: mirror, lock, revcons, group
         * L>D|[R>d]|[U>l]|D>L: transpose, mirror, getcons, group
         * [L>d]|R>D|U>L|[D>l]: mirror, transpose, mirror, getcons, group
         * L>U|[R>u]|[U>r]|D>R: transpose, getcons, group
         * [L>u]|R>U|U>R|[D>r]: mirror, transpose, getcons, group
         * *>*: nop
         * 
         * U,D is only permitted if !layeredGraph.hasEdges 
         * (but this cannot happen in an intermediate processor)
         */
        
        // prohibits vertical compaction if the graph has edges, because vertical and horizontal
        // segments would interchange
        if (dir == Direction.UP || dir == Direction.DOWN) {
            boolean hasEdges = layeredGraph.getLayers().stream()
                    .flatMap(l -> l.getNodes().stream())
                    .anyMatch(n -> !Iterables.isEmpty(n.getConnectedEdges()));

            if (hasEdges) {
                throw new RuntimeException(
                        "Vertical compaction is not permitted on graphs containing edges.");
            }
        }
        
        // executes required transformations
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
                mirrorHitboxes(); lockCNodes(dir); reverseConstraints();
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
                mirrorHitboxes(); lockCNodes(dir); reverseConstraints();
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
                mirrorHitboxes(); lockCNodes(dir); reverseConstraints();
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
                mirrorHitboxes(); lockCNodes(dir); reverseConstraints();
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
     * Updates graph properties offset and size and clears lists.
     */
    public void applyGraphSize() {
        KVector topLeft = new KVector(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        KVector bottomRight = new KVector(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
        for (CNode cNode : cNodes) {
            topLeft.x = Math.min(topLeft.x, cNode.hitbox.x);
            topLeft.y = Math.min(topLeft.y, cNode.hitbox.y);
            bottomRight.x = Math.max(bottomRight.x, cNode.hitbox.x + cNode.hitbox.width);
            bottomRight.y = Math.max(bottomRight.y, cNode.hitbox.y + cNode.hitbox.height);
        }
        layeredGraph.getOffset().reset().add(topLeft.clone().negate());
        layeredGraph.getSize().reset().add(bottomRight.clone().sub(topLeft));

        cGroups.clear();
        cNodes.clear();
        
        // resetting direction between calls of process()
        direction = Direction.UNDEFINED;
    }
    
    /**
     * Mirrors hitboxes horizontally.
     */
    private void mirrorHitboxes() {
        for (CNode cNode : cNodes) {
            cNode.hitbox.x = -cNode.hitbox.x - cNode.hitbox.width;
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
        for (CNode cNode : cNodes) {
            tmp = cNode.hitbox.x;
            cNode.hitbox.x = cNode.hitbox.y;
            cNode.hitbox.y = tmp;
            tmp = cNode.hitbox.width;
            cNode.hitbox.width = cNode.hitbox.height;
            cNode.hitbox.height = tmp;
        }
    }
    
    /**
     * For debugging. Writes hitboxes to svg.
     * @param name
     *          filename
     */
    @SuppressWarnings("unused")
    private void drawHitboxes(final String name) {
        // update graph properties to determine viewBox
        KVector topLeft = new KVector(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        KVector bottomRight = new KVector(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
        for (CNode cNode : cNodes) {
            topLeft.x = Math.min(topLeft.x, cNode.hitbox.x);
            topLeft.y = Math.min(topLeft.y, cNode.hitbox.y);
            bottomRight.x = Math.max(bottomRight.x, cNode.hitbox.x + cNode.hitbox.width);
            bottomRight.y = Math.max(bottomRight.y, cNode.hitbox.y + cNode.hitbox.height);
        }
        layeredGraph.getOffset().reset().add(topLeft.clone().negate());
        layeredGraph.getSize().reset().add(bottomRight.clone().sub(topLeft));

        // drawing hitboxes to svg
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(name));

            out.println("<svg viewBox=\"" + (-layeredGraph.getOffset().x) + " "
                    + (-layeredGraph.getOffset().y) + " " + layeredGraph.getSize().x + " "
                    + layeredGraph.getSize().y + "\">");

            for (CNode cNode : cNodes) {
                if (cNode.getClass().equals(CLNode.class)) {
                    out.println("<rect x=\"" + cNode.hitbox.x + "\" y=\"" + cNode.hitbox.y
                            + "\" width=\"" + cNode.hitbox.width + "\" height=\""
                            + cNode.hitbox.height + "\" fill=\"white\" stroke=\"black\"/>");
                } else {
                    out.println("<line x1=\"" + cNode.hitbox.x + "\" y1=\"" + cNode.hitbox.y
                            + "\" x2=\"" + (cNode.hitbox.x + cNode.hitbox.width) + "\" y2=\""
                            + (cNode.hitbox.y + cNode.hitbox.height) + "\" stroke=\"blue\"/>");
                }
            }

            out.println("</svg>");

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Sets the constraints of {@link CGroup}s according to the constraints of the included
     * {@link CNode}s.
     */
    private void calculateConstraintsForCGroups() {
        // adding constraints to each CGroup, that refer to CNodes outside of the CGroup
        for (CGroup group : cGroups) {
            group.outDegree = 0;
            group.incomingConstraints.clear();
            for (CNode cNode : group.cNodes) {
                for (CNode inc : cNode.constraints) {
                    if (!group.cNodes.contains(inc)) {
                        group.incomingConstraints.add(inc);
                    }
                }
            }
        }
        
        // setting the outDegree of CGroups (number of CNodes a group is constrained by)
        for (CGroup group : cGroups) {
            for (CNode cNode : group.cNodes) {
                for (CNode inc : cNode.constraints) {
                    if (group.incomingConstraints.contains(inc)) {
                        inc.cGroup.outDegree++;
                    }
                }
            }
        }
    }

    /**
     * Locks the position of CNodes before a second compaction in opposite direction
     * if the CNode has no edges connected to a node in that direction.
     * @param dir
     *          the direction
     */
    private void lockCNodes(final Direction dir) {
        for (CNode cNode : cNodes) {
            cNode.reposition = !cNode.lock.get(dir);
        }
    }

    /**
     * Creates a constraint between CNodes a and b if a could cast a shadow on b
     * considering margins and spacing.
     */
    private void calculateConstraints() {
        // resetting constraintsinitially
        for (CNode cNode : cNodes) {
            cNode.constraints.clear();
            cNode.outDegree = 0;
        }
        
        //  inferring constraints from hitbox intersections
        for (CNode cNode1 : cNodes) {
            for (CNode cNode2 : cNodes) {
                double spacing = cNode1.getSpacing(cNode2);

                // add constraint if node2 is to the right of node1 and could collide if 
                // movedhorizontally
                // exclude parentNodes because they don't constrain their north/south segments
                if (cNode1 != cNode2 && cNode1 != cNode2.parentNode
                        // shouldn't use >= nor Comp.gt to avoid simultaneous constraints a->b and b->a
                        && cNode2.hitbox.x > cNode1.hitbox.x
                        
                        && Comp.gt(cNode2.hitbox.y + cNode2.hitbox.height + spacing, cNode1.hitbox.y)
                        
                        && Comp.lt(cNode2.hitbox.y, cNode1.hitbox.y
                                   + cNode1.hitbox.height + spacing)) {

                    cNode1.constraints.add(cNode2);
                    cNode2.outDegree++;
                }
            }
        }

        // setting outDegree for CGroups
        calculateConstraintsForCGroups();
    }

    /**
     * If the graph is compacted twice in opposing directions, the constraints can be reversed to
     * avoid recalculating them. Also the {@link CNode}s' starting position is reset to be ready for
     * compaction.
     */
    private void reverseConstraints() {
        // maps CNodes to temporary lists of incoming constraints
        Map<CNode, List<CNode>> incMap = Maps.newHashMap();
        for (CNode cNode : cNodes) {
            incMap.put(cNode, Lists.newArrayList());
        }
        
        // resetting fields of CNodes and reversing constraints
        for (CNode cNode : cNodes) {
            cNode.startPos = Double.NEGATIVE_INFINITY;
            cNode.outDegree = 0;
            for (CNode inc : cNode.constraints) {
                cNode.outDegree++;
                incMap.get(inc).add(cNode);
            }
        }
        
        // write back
        for (CNode cNode : cNodes) {
            cNode.constraints.clear();
            cNode.constraints = incMap.get(cNode);
        }
        
        // setting outDegree for CGroups
        calculateConstraintsForCGroups();
    }

    /**
     * Compacting CGroups and the CNodes inside.
     */
    private void compactCGroups() {
        // queues CGroups whose outgoing constraints are processed
        Queue<CGroup> sinks = Lists.newLinkedList();
        
        // finding and compacting CGroups that are sinks
        for (CGroup group : cGroups) {
            if (group.isInnerCompactable()) {
                System.out.println("this group is initial sink"); //TODO
                group.compactInnerCNodes();
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
                System.out.println("this group became sink after propagation of: " + group.cNodes); //TODO
                g.compactInnerCNodes();
                sinks.add(g);
            }
            compactables.clear();
        }
        
        // setting hitbox positions to new starting positions
        for (CNode cNode : cNodes) {
            cNode.applyPosition();
        }
    }

    /**
     * Sets the positions of graph elements to the compacted position.
     */
    private void applyNodePositions() {
        for (CNode cNode : cNodes) {
            cNode.applyElementPosition();
        }
    }
}
