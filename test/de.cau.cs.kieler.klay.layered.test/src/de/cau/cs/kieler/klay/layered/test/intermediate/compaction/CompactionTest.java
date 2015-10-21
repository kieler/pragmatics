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
package de.cau.cs.kieler.klay.layered.test.intermediate.compaction;

import java.util.EnumSet;
import java.util.Set;

import org.junit.Test;

import de.cau.cs.kieler.kiml.options.Direction;
import de.cau.cs.kieler.kiml.util.nodespacing.Rectangle;
import de.cau.cs.kieler.klay.layered.compaction.CGraph;
import de.cau.cs.kieler.klay.layered.compaction.CGroup;
import de.cau.cs.kieler.klay.layered.compaction.CNode;
import de.cau.cs.kieler.klay.layered.compaction.OneDimensionalCompactor;
import static org.junit.Assert.*;

/**
 * @author uru
 */
public class CompactionTest {

    public static final double EPSILON = 0.0001d;
    
    @Test
    public void testLeftCompaction() {
        
        CGraph graph = new CGraph(EnumSet.allOf(Direction.class));
        
        CTestNode left = new CTestNode(new Rectangle(0, 0, 20, 20));
        graph.cNodes.add(left);
        CTestNode right = new CTestNode(new Rectangle(30, 0, 20, 20));
        graph.cNodes.add(right);
        
        compacter(graph)
                 .changeDirection(Direction.LEFT)
                 .compact()
                 .finish();
        
        assertEquals(0, left.hitbox.x, EPSILON);
        assertEquals(20, right.hitbox.x, EPSILON);
    }
    
    @Test
    public void testRightCompaction() {
        
        CGraph graph = new CGraph(EnumSet.allOf(Direction.class));
        
        CTestNode left = new CTestNode(new Rectangle(0, 0, 20, 20));
        graph.cNodes.add(left);
        CTestNode right = new CTestNode(new Rectangle(30, 0, 20, 20));
        graph.cNodes.add(right);
        
        compacter(graph)
                 .changeDirection(Direction.RIGHT)
                 .compact()
                 .finish();
        
        assertEquals(10, left.hitbox.x, EPSILON);
        assertEquals(30, right.hitbox.x, EPSILON);
    }
    
    @Test
    public void testUpCompaction() {
        
        CGraph graph = new CGraph(EnumSet.allOf(Direction.class));
        
        CTestNode upper = new CTestNode(new Rectangle(0, 0, 20, 20));
        graph.cNodes.add(upper);
        CTestNode lower = new CTestNode(new Rectangle(0, 30, 20, 20));
        graph.cNodes.add(lower);
        
        compacter(graph)
                 .changeDirection(Direction.UP)
                 .compact()
                 .finish();
        
        assertEquals(0, upper.hitbox.y, EPSILON);
        assertEquals(20, lower.hitbox.y, EPSILON);
    }
    
    @Test
    public void testDownCompaction() {
        
        CGraph graph = new CGraph(EnumSet.allOf(Direction.class));
        
        CTestNode upper = new CTestNode(new Rectangle(0, 0, 20, 20));
        graph.cNodes.add(upper);
        CTestNode lower = new CTestNode(new Rectangle(0, 30, 20, 20));
        graph.cNodes.add(lower);
        
        compacter(graph)
                 .changeDirection(Direction.DOWN)
                 .compact()
                 .finish();
        
        assertEquals(10, upper.hitbox.y, EPSILON);
        assertEquals(30, lower.hitbox.y, EPSILON);
    }
    
    /**
     * The connection indicates a grouping, not an edge.
     * 
     *   +--+         
     *   |  |     +--+
     *   +--+     |  |
     *            +--+
     *             |  
     *          +--+  
     *          |  |  
     *          +--+ 
     */
    @Test 
    public void testLeftGroupCompaction() {
        
        CGraph graph = new CGraph(EnumSet.allOf(Direction.class));
        
        CTestNode left = new CTestNode(new Rectangle(0, 0, 20, 20));
        graph.cNodes.add(left);
        CTestNode upperRight = new CTestNode(new Rectangle(40, 5, 20, 20));
        graph.cNodes.add(upperRight);
        CTestNode lowerRight = new CTestNode(new Rectangle(30, 25, 20, 20));
        graph.cNodes.add(lowerRight);
        
        CGroup group = new CGroup(upperRight, lowerRight);
        graph.cGroups.add(group);
        
        compacter(graph)
                .changeDirection(Direction.LEFT)
                .compact()
                .finish();
        
        assertEquals(0, left.hitbox.x, EPSILON);
        assertEquals(20, upperRight.hitbox.x, EPSILON);
        assertEquals(10, lowerRight.hitbox.x, EPSILON);
    }
    
    /**
     * The connection indicates a grouping, not an edge.
     * 
     *   +--+         
     *   |  |     +--+
     *   +--+     |  |
     *     |      +--+
     *     |          
     *     +--+  
     *     |  |  
     *     +--+ 
     */
    @Test 
    public void testRightGroupCompaction() {
        
        CGraph graph = new CGraph(EnumSet.allOf(Direction.class));
        
        CTestNode left = new CTestNode(new Rectangle(0, 5, 20, 20));
        graph.cNodes.add(left);
        CTestNode upperRight = new CTestNode(new Rectangle(40, 0, 20, 20));
        graph.cNodes.add(upperRight);
        CTestNode lowerRight = new CTestNode(new Rectangle(10, 25, 20, 20));
        graph.cNodes.add(lowerRight);
        
        CGroup group = new CGroup(left, lowerRight);
        graph.cGroups.add(group);
        
        compacter(graph)
                .changeDirection(Direction.RIGHT)
                .compact()
                .finish();
        
        assertEquals(20, left.hitbox.x, EPSILON);
        assertEquals(40, upperRight.hitbox.x, EPSILON);
        assertEquals(30, lowerRight.hitbox.x, EPSILON);
    }
    
    /**
     * The connection indicates a grouping, not an edge.
     * 
     *   +--+         
     *   |  |     
     *   +--+     +--+
     *            |  |
     *     +--+---+--+ 
     *     |  |  
     *     +--+ 
     */
    @Test 
    public void testUpGroupCompaction() {
        
        CGraph graph = new CGraph(EnumSet.allOf(Direction.class));
        
        CTestNode upperLeft = new CTestNode(new Rectangle(0, 0, 20, 20));
        graph.cNodes.add(upperLeft);
        CTestNode lowerLeft = new CTestNode(new Rectangle(5, 40, 20, 20));
        graph.cNodes.add(lowerLeft);
        CTestNode right = new CTestNode(new Rectangle(25, 30, 20, 20));
        graph.cNodes.add(right);
        
        CGroup group = new CGroup(lowerLeft, right);
        graph.cGroups.add(group);
        
        compacter(graph)
                .changeDirection(Direction.UP)
                .compact()
                .finish();
        
        assertEquals(0, upperLeft.hitbox.y, EPSILON);
        assertEquals(20, lowerLeft.hitbox.y, EPSILON);
        assertEquals(10, right.hitbox.y, EPSILON);
    }
    
    /**
     * The connection indicates a grouping, not an edge.
     * 
     *   +--+         
     *   |  |     
     *   +--+-----+--+
     *            |  |
     *     +--+   +--+ 
     *     |  |  
     *     +--+ 
     */
    @Test 
    public void testDownGroupCompaction() {
        
        CGraph graph = new CGraph(EnumSet.allOf(Direction.class));
        
        CTestNode upperLeft = new CTestNode(new Rectangle(0, 0, 20, 20));
        graph.cNodes.add(upperLeft);
        CTestNode lowerLeft = new CTestNode(new Rectangle(5, 40, 20, 20));
        graph.cNodes.add(lowerLeft);
        CTestNode right = new CTestNode(new Rectangle(25, 10, 20, 20));
        graph.cNodes.add(right);
        
        CGroup group = new CGroup(upperLeft, right);
        graph.cGroups.add(group);
        
        compacter(graph)
                .changeDirection(Direction.DOWN)
                .compact()
                .finish();
        
        assertEquals(20, upperLeft.hitbox.y, EPSILON);
        assertEquals(40, lowerLeft.hitbox.y, EPSILON);
        assertEquals(30, right.hitbox.y, EPSILON);
    }
    
    
    /* --------------------------------------------------
     * Testing subsequent calls with different directions
     * -------------------------------------------------- */
    
    @Test 
    public void testSubsequentDirectionsCompaction() {
        
        CGraph graph = new CGraph(EnumSet.allOf(Direction.class));
        
        CTestNode one = new CTestNode(new Rectangle(0, 0, 20, 20));
        graph.cNodes.add(one);
        CTestNode two = new CTestNode(new Rectangle(20, 0, 20, 20));
        graph.cNodes.add(two);
        CTestNode three = new CTestNode(new Rectangle(0, 20, 20, 20));
        graph.cNodes.add(three);
        CTestNode four = new CTestNode(new Rectangle(20, 20, 20, 20));
        graph.cNodes.add(four);
        
        Set<Direction> directions = EnumSet.of(Direction.LEFT, Direction.RIGHT, Direction.UP, Direction.DOWN);
        
        // subsequently apply all combinations of four subsequent compaction steps
        for (Direction d1 : directions) {
            for (Direction d2 : directions) {
                for (Direction d3 : directions) {
                    for (Direction d4 : directions) {
                        
                        compacter(graph)
                            .changeDirection(d1)
                            .compact()
                            .changeDirection(d2)
                            .compact()
                            .changeDirection(d3)
                            .compact()
                            .changeDirection(d4)
                            .compact()
                            .finish();
                
                        // the way we modeled the graph, every node should stay where it is 
                        String currentDirections = d1 + " " + d2 + " " + d3 + " " + d4;
                        assertEquals(currentDirections, 0, one.hitbox.x, EPSILON);
                        assertEquals(currentDirections, 0, one.hitbox.y, EPSILON);
                        
                        assertEquals(currentDirections, 20, two.hitbox.x, EPSILON);
                        assertEquals(currentDirections, 0, two.hitbox.y, EPSILON);
                        
                        assertEquals(currentDirections, 0, three.hitbox.x, EPSILON);
                        assertEquals(currentDirections, 20, three.hitbox.y, EPSILON);
                        
                        assertEquals(currentDirections, 20, four.hitbox.x, EPSILON);
                        assertEquals(currentDirections, 20, four.hitbox.y, EPSILON);
                    }
                }
            }
        }
        

    }
    
    ////////////////////////////////// Internal Convenience API //////////////////////////////////
    
    private OneDimensionalCompactor compacter(final CGraph graph) {
        return new OneDimensionalCompactor(graph);
    }
    
    public static class CTestNode extends CNode {

        public CTestNode(Rectangle rect) {
            this.hitbox = rect;
        }
        
        @Override
        public double getHorizontalSpacing(CNode other) {
            return 0;
        }

        @Override
        public double getSingleHorizontalSpacing() {
            return 0;
        }

        @Override
        public double getSingleVerticalSpacing() {
            return 0;
        }

        @Override
        public void applyElementPosition() {
            hitbox.x = hitbox.x;
        }

        @Override
        public double getElementPosition() {
            return hitbox.x;
        }
    }
}
