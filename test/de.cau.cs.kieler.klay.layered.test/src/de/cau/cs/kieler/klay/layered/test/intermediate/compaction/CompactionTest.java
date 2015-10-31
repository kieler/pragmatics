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
    
    public static final double SPACING = 5;
    
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
        assertEquals(25, right.hitbox.x, EPSILON);
    }
    
    @Test
    public void testLeftCompactionSpacingAware() {
        
        CGraph graph = new CGraph(EnumSet.allOf(Direction.class));
        
        CTestNode left = new CTestNode(new Rectangle(0, 0, 20, 20));
        graph.cNodes.add(left);
        CTestNode right = new CTestNode(new Rectangle(30, 20 + SPACING - 1, 20, 20));
        graph.cNodes.add(right);
        
        compacter(graph)
                 .changeDirection(Direction.LEFT)
                 .compact()
                 .finish();
        
        assertEquals(0, left.hitbox.x, EPSILON);
        assertEquals(25, right.hitbox.x, EPSILON);
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
        
        assertEquals(5, left.hitbox.x, EPSILON);
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
        assertEquals(25, lower.hitbox.y, EPSILON);
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
        
        assertEquals(5, upper.hitbox.y, EPSILON);
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
        assertEquals(25, upperRight.hitbox.x, EPSILON);
        assertEquals(15, lowerRight.hitbox.x, EPSILON);
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
        
        assertEquals(15, left.hitbox.x, EPSILON);
        assertEquals(40, upperRight.hitbox.x, EPSILON);
        assertEquals(25, lowerRight.hitbox.x, EPSILON);
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
        assertEquals(25, lowerLeft.hitbox.y, EPSILON);
        assertEquals(15, right.hitbox.y, EPSILON);
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
        CTestNode lowerLeft = new CTestNode(new Rectangle(5, 40, 10, 20));
        graph.cNodes.add(lowerLeft);
        CTestNode right = new CTestNode(new Rectangle(25, 10, 20, 20));
        graph.cNodes.add(right);
        
        CGroup group = new CGroup(upperLeft, right);
        graph.cGroups.add(group);
        
        compacter(graph)
                .changeDirection(Direction.DOWN)
                .compact()
                .finish();
        
        assertEquals(15, upperLeft.hitbox.y, EPSILON);
        assertEquals(40, lowerLeft.hitbox.y, EPSILON);
        assertEquals(25, right.hitbox.y, EPSILON);
    }
    
    /**
     * Two separate groups, three nodes each.
     * 
     * Care has to be taken when calculating the initial position of a {@link CGroup} not to include
     * any spacing between the group nodes. (Group nodes are rigidly fixed relative to each other
     * and thus no other spacing should be applied in between them). This test asserts that the
     * initial position of the groups is as expected.
     */
    @Test
    public void testNoSpacingAppliedWithinGroups() {
        
        CGraph graph = new CGraph(EnumSet.allOf(Direction.class));
        
        CTestNode one = new CTestNode(new Rectangle(0, 0, 20, 20));
        graph.cNodes.add(one);
        CTestNode two = new CTestNode(new Rectangle(20, 10, 20, 20));
        graph.cNodes.add(two);
        CTestNode three = new CTestNode(new Rectangle(40, 20, 20, 20));
        graph.cNodes.add(three);
        
        CGroup g1 = new CGroup(one, two, three);
        graph.cGroups.add(g1);
        
        CTestNode four = new CTestNode(new Rectangle(22, 80, 20, 20));
        graph.cNodes.add(four);
        CTestNode five = new CTestNode(new Rectangle(42, 90, 20, 20));
        graph.cNodes.add(five);
        CTestNode six = new CTestNode(new Rectangle(62, 100, 20, 20));
        graph.cNodes.add(six);
        
        CGroup g2 = new CGroup(four, five, six);
        graph.cGroups.add(g2);
        
        compacter(graph)
            .changeDirection(Direction.LEFT)
            .compact()
            .changeDirection(Direction.RIGHT)
            .compact()
            .changeDirection(Direction.UP)
            .compact()
            .changeDirection(Direction.DOWN)
            .compact()
            .finish();

        assertEquals(0, one.hitbox.x, EPSILON);
        assertEquals(20, two.hitbox.x, EPSILON);
        assertEquals(40, three.hitbox.x, EPSILON);
        assertEquals(0, four.hitbox.x, EPSILON);
        assertEquals(20, five.hitbox.x, EPSILON);
        assertEquals(40, six.hitbox.x, EPSILON);
        
        assertEquals(0, one.hitbox.y, EPSILON);
        assertEquals(10, two.hitbox.y, EPSILON);
        assertEquals(20, three.hitbox.y, EPSILON);
        assertEquals(35, four.hitbox.y, EPSILON);
        assertEquals(45, five.hitbox.y, EPSILON);
        assertEquals(55, six.hitbox.y, EPSILON);
    }
    
    /* --------------------------------------------------
     * Testing subsequent calls with different directions
     * -------------------------------------------------- */
    
    @Test 
    public void testSubsequentDirectionsCompaction() {
        
        CGraph graph = new CGraph(EnumSet.allOf(Direction.class));
        
        CTestNode one = new CTestNode(new Rectangle(0, 0, 20, 20));
        graph.cNodes.add(one);
        CTestNode two = new CTestNode(new Rectangle(25, 0, 20, 20));
        graph.cNodes.add(two);
        CTestNode three = new CTestNode(new Rectangle(0, 25, 20, 20));
        graph.cNodes.add(three);
        CTestNode four = new CTestNode(new Rectangle(25, 25, 20, 20));
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
                        
                        assertEquals(currentDirections, 25, two.hitbox.x, EPSILON);
                        assertEquals(currentDirections, 0, two.hitbox.y, EPSILON);
                        
                        assertEquals(currentDirections, 0, three.hitbox.x, EPSILON);
                        assertEquals(currentDirections, 25, three.hitbox.y, EPSILON);
                        
                        assertEquals(currentDirections, 25, four.hitbox.x, EPSILON);
                        assertEquals(currentDirections, 25, four.hitbox.y, EPSILON);
                    }
                }
            }
        }
        
    }
    
    /* --------------------------------------------------
     *        Testing different kinds of spacings.
     * --------------------------------------------------*/
    
    /*
     * We support different spacing values between nodes and edges in vertical and horizontal
     * direction. Thereby "vertical" and "horizontal" depends on the compaction direction.
     * Furthermore, we support individual spacings between any pair of nodes.
     */
    
    /**
     * Horizontal spacing should be preserved when compaction leftwards or rightwards.
     */
    @Test
    public void testHorizontalSpacings() {

        CGraph graph = new CGraph(EnumSet.allOf(Direction.class));
        
        // test horizontal direction
        CTestNodeSpacing one = new CTestNodeSpacing(new Rectangle(0, 0, 20, 20), 5d, 0d);
        graph.cNodes.add(one);
        CTestNodeSpacing two = new CTestNodeSpacing(new Rectangle(50, 0, 20, 20), 7d, 0d);
        graph.cNodes.add(two);
        CTestNodeSpacing three = new CTestNodeSpacing(new Rectangle(150, 0, 20, 20), 10d, 0d);
        graph.cNodes.add(three);
        
        compacter(graph)
            .changeDirection(Direction.LEFT)
            .compact()
            .finish();
        
        assertEquals(0, one.hitbox.x, EPSILON);
        assertEquals(27, two.hitbox.x, EPSILON);
        assertEquals(57, three.hitbox.x, EPSILON);
        
        compacter(graph)
            .changeDirection(Direction.RIGHT)
            .compact()
            .finish();
    
        assertEquals(0, one.hitbox.x, EPSILON);
        assertEquals(27, two.hitbox.x, EPSILON);
        assertEquals(57, three.hitbox.x, EPSILON);

        compacter(graph)
            .changeDirection(Direction.LEFT)
            .compact()
            .finish();
    
        assertEquals(0, one.hitbox.x, EPSILON);
        assertEquals(27, two.hitbox.x, EPSILON);
        assertEquals(57, three.hitbox.x, EPSILON);
    }
    
    /**
     * "Two" is "center node". Node "one" is supposed to be blocked by "two", while "three" can move freely.
     */
    @Test
    public void testVerticalSpacingDuringHorizontalCompaction() {
        
        CGraph graph = new CGraph(EnumSet.allOf(Direction.class));
        
        CTestNodeSpacing one = new CTestNodeSpacing(new Rectangle(150, 11, 20, 20), 0d, 10d);
        graph.cNodes.add(one);
        CTestNodeSpacing two = new CTestNodeSpacing(new Rectangle(0, 40, 20, 20), 0d, 5d);
        graph.cNodes.add(two);
        CTestNodeSpacing three = new CTestNodeSpacing(new Rectangle(150, 76, 20, 20), 0d, 15d);
        graph.cNodes.add(three);
        
        compacter(graph)
            .changeDirection(Direction.LEFT)
            .compact()
            .finish();
        
        assertEquals(20, one.hitbox.x, EPSILON);
        assertEquals(0, two.hitbox.x, EPSILON);
        assertEquals(0, three.hitbox.x, EPSILON);
    }
    
    /**
     * Vertical spacing should be preserved when compaction upwards or downwards.
     */
    @Test
    public void testVeticalSpacings() {

        CGraph graph = new CGraph(EnumSet.allOf(Direction.class));
        
        // test horizontal direction
        CTestNodeSpacing one = new CTestNodeSpacing(new Rectangle(0, 0, 20, 20), 0d, 5d);
        graph.cNodes.add(one);
        CTestNodeSpacing two = new CTestNodeSpacing(new Rectangle(0, 50, 20, 20), 0d, 7d);
        graph.cNodes.add(two);
        CTestNodeSpacing three = new CTestNodeSpacing(new Rectangle(0, 150, 20, 20), 0d, 10d);
        graph.cNodes.add(three);
        
        compacter(graph)
            .changeDirection(Direction.UP)
            .compact()
            .finish();
        
        assertEquals(0, one.hitbox.y, EPSILON);
        assertEquals(27, two.hitbox.y, EPSILON);
        assertEquals(57, three.hitbox.y, EPSILON);
        
        compacter(graph)
            .changeDirection(Direction.DOWN)
            .compact()
            .finish();
        
        assertEquals(0, one.hitbox.y, EPSILON);
        assertEquals(27, two.hitbox.y, EPSILON);
        assertEquals(57, three.hitbox.y, EPSILON);
        
        compacter(graph)
            .changeDirection(Direction.UP)
            .compact()
            .finish();
        
        assertEquals(0, one.hitbox.y, EPSILON);
        assertEquals(27, two.hitbox.y, EPSILON);
        assertEquals(57, three.hitbox.y, EPSILON);
    }
    
    /**
     * "Two" is "center node". Node "one" is supposed to be blocked by "two", while "three" can move freely.
     */
    @Test
    public void testHorizontalSpacingDuringVerticalCompaction() {
        
        CGraph graph = new CGraph(EnumSet.allOf(Direction.class));
        
        CTestNodeSpacing one = new CTestNodeSpacing(new Rectangle(16, 150, 20, 20), 5d, 0d);
        graph.cNodes.add(one);
        CTestNodeSpacing two = new CTestNodeSpacing(new Rectangle(40, 0, 20, 20), 10d, 0d);
        graph.cNodes.add(two);
        CTestNodeSpacing three = new CTestNodeSpacing(new Rectangle(76, 150, 20, 20), 15d, 0d);
        graph.cNodes.add(three);
        
        compacter(graph)
            .changeDirection(Direction.UP)
            .compact()
            .finish();
        
        assertEquals(20, one.hitbox.y, EPSILON);
        assertEquals(0, two.hitbox.y, EPSILON);
        assertEquals(0, three.hitbox.y, EPSILON);
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
        public double getHorizontalSpacing() {
            return SPACING;
        }

        @Override
        public double getVerticalSpacing() {
            return SPACING;
        }

        @Override
        public void applyElementPosition() {
            hitbox.x = hitbox.x;
        }

        @Override
        public double getElementPosition() {
            return hitbox.x;
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return ""; // dont spam the debug output
        }
    }
    
    public static class CTestNodeSpacing extends CTestNode {

        private double hSpacing;
        private double vSpacing;
        
        public CTestNodeSpacing(final Rectangle rect, final double hSpacing, final double vSpacing) {
            super(rect);
            this.hSpacing = hSpacing;
            this.vSpacing = vSpacing;
        }
        
        @Override
        public double getHorizontalSpacing() {
            return hSpacing;
        }
        
        @Override
        public double getVerticalSpacing() {
            return vSpacing;
        }
    }
}
