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

import java.awt.geom.Rectangle2D;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.math.DoubleMath;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;
import de.cau.cs.kieler.kiml.options.EdgeRouting;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.util.nodespacing.Spacing.Margins;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LShape;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.intermediate.compaction.GraphCompactionStrategy;
import de.cau.cs.kieler.klay.layered.intermediate.compaction.HorizontalGraphCompactor;
import de.cau.cs.kieler.klay.layered.properties.InternalProperties;
import de.cau.cs.kieler.klay.layered.properties.Properties;
import de.cau.cs.kieler.klay.layered.properties.Spacings;
import de.cau.cs.kieler.klay.layered.test.AbstractLayeredProcessorTest;
import de.cau.cs.kieler.klay.test.config.BasicLayoutConfigurator;
import de.cau.cs.kieler.klay.test.config.ILayoutConfigurator;
import de.cau.cs.kieler.klay.test.utils.GraphTestObject;

import static org.junit.Assert.*;

/**
 * Tests the {@link HorizontalGraphCompactor} for valid outputs. That is, after the processor has
 * been applied no pair of nodes may overlap and proper spacing values have to be preserved.
 * 
 * Since the processor only supports {@link EdgeRouting#ORTHOGONAL}, the test configures the passed
 * graphs such that orthogonal edge routing is applied.
 * 
 * @author uru
 */
public class HorizontalGraphCompactorTest extends AbstractLayeredProcessorTest {

    private static final Double TOLERANCE = 0.00001d;
    
    /**
     * @param testObject
     * @param config
     */
    public HorizontalGraphCompactorTest(GraphTestObject testObject, ILayoutConfigurator config) {
        super(testObject, config);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<ILayoutConfigurator> getConfigurators() {
        return Lists.newArrayList(
                Config.of(GraphCompactionStrategy.LEFT), 
                Config.of(GraphCompactionStrategy.RIGHT),
                Config.of(GraphCompactionStrategy.LEFT_RIGHT_CONNECTION_LOCKING),
                Config.of(GraphCompactionStrategy.LEFT_RIGHT_CONSTRAINT_LOCKING));
    }
    
    /**
     * Run the layout algorithm until the specified strategy finished.
     */
    @Before
    public void runUntil() {
        layered.runLayoutTestUntil(HorizontalGraphCompactor.class, state);
    }
    
    /**
     * Test checks that no pair of nodes overlaps. 
     * Considers node {@link Margins}.
     */
    @Test
    public void testNodeOverlaps() {
        
        for (LGraph g : state.getGraphs()) {

            // the following returns an array list
            List<LNode> allNodes =
                    g.getLayers().stream()
                            .flatMap(l -> l.getNodes().stream())
                            .collect(Collectors.toList());

            for (int i = 0; i < allNodes.size(); ++i) {
                for (int j = i + 1; j < allNodes.size(); ++j) {
                    checkOverlap(allNodes.get(i), allNodes.get(j));
                }
            }
            
        }
    }
    
    /**
     * Test checks that all edges are still orthogonal paths. In other words for two subsequent
     * bendpoints b1 and b2 it either holds that b1.x == b2.x or that b1.y == b2.y;
     */
    @Test
    public void testEdgeSegmentsOrthogonal() {

        for (LGraph g : state.getGraphs()) {
            for (Layer l : g) {
                for (LNode n : l) {
                    
                    for (LEdge e : n.getOutgoingEdges()) {
                        List<KVector> bends = e.getBendPoints();
                        bends.add(0, e.getSource().getAbsoluteAnchor());
                        bends.add(e.getTarget().getAbsoluteAnchor());

                        Iterator<KVector> bendsIt = bends.iterator();
                        KVector lastBend = bendsIt.next();
                        
                        while (bendsIt.hasNext()) {
                            KVector bend = bendsIt.next();
                            
                            boolean orthogonal = DoubleMath.fuzzyEquals(bend.x, lastBend.x, TOLERANCE)
                                    || DoubleMath.fuzzyEquals(bend.y, lastBend.y, TOLERANCE);
    
                            Assert.assertTrue(bends + " describe an orthogonal path.", orthogonal);
    
                            lastBend = bend;
                        }
                    }

                }
            }
        }
    }
    
    /**
     * Tests that for any pair of nodes the specified minimum spacing between the nodes is
     * preserved.
     * 
     * Does <b>not</b> test this for edge segments, or an edge segment and a node.
     */
    @Test 
    public void testSpacings() {

        for (LGraph g : state.getGraphs()) {

            Spacings spacings = g.getProperty(InternalProperties.SPACINGS);

            List<LNode> allNodes =
                    g.getLayers().stream().flatMap(l -> l.getNodes().stream())
                            .collect(Collectors.toList());

            for (int i = 0; i < allNodes.size(); ++i) {
                for (int j = i + 1; j < allNodes.size(); ++j) {

                    LNode n1 = allNodes.get(i);
                    LNode n2 = allNodes.get(j);

                    if (overlapHorizontally(n1, n2)) {
                        double vSpacing = spacings.getVerticalSpacing(n1, n2);
                        checkVerticalSpacing(n1, n2, vSpacing);
                    }

                    if (overlapVertically(n1, n2)) {
                        double hSpacing = spacings.getHorizontalSpacing(n1, n2);
                        checkHorizontalSpacing(n1, n2, hSpacing);
                    }

                }
            }
        }
    }

    private boolean overlapHorizontally(LNode n1, LNode n2) {
        return n1.getPosition().x > n2.getPosition().x
                && n1.getPosition().x < n2.getPosition().x + n2.getSize().x
                || n2.getPosition().x > n1.getPosition().x
                && n2.getPosition().x < n1.getPosition().x + n1.getSize().x;
    }
    
    private boolean overlapVertically(LNode n1, LNode n2) {
        return n1.getPosition().y > n2.getPosition().y
                && n1.getPosition().y < n2.getPosition().y + n2.getSize().y
                || n2.getPosition().y > n1.getPosition().y
                && n2.getPosition().y < n1.getPosition().y + n1.getSize().y;
    }
    
    private void checkHorizontalSpacing(LNode n1, LNode n2, final double spacing) {
        // assure that n1 is the "left" node
        if (n1.getPosition().x > n2.getPosition().x) {
            LNode tmp = n1;
            n1 = n2;
            n2 = tmp;
        }

        Margins m1 = n1.getProperty(LayoutOptions.MARGINS);
        Margins m2 = n2.getProperty(LayoutOptions.MARGINS);

        double left = n1.getPosition().x + n1.getSize().x + m1.right;
        double right = n2.getPosition().x - m2.left;
        
        boolean ok = DoubleMath.fuzzyCompare(left + spacing, right, TOLERANCE) <= 0;

        assertTrue("Sufficient spacing of " + spacing + " between nodes " + n1 + " and " + n2
                + " (" + (right - left) + ")", ok);
    }
    
    private void checkVerticalSpacing(LNode n1, LNode n2, final double spacing) {
        // assure that n1 is the "top" node
        if (n1.getPosition().y > n2.getPosition().y) {
            LNode tmp = n1;
            n1 = n2;
            n2 = tmp;
        }

        Margins m1 = n1.getProperty(LayoutOptions.MARGINS);
        Margins m2 = n2.getProperty(LayoutOptions.MARGINS);

        double top = n1.getPosition().y + n1.getSize().y + m1.bottom;
        double bottom = n2.getPosition().y - m2.top; 
        
        boolean ok = DoubleMath.fuzzyCompare(top + spacing, bottom, TOLERANCE) <= 0;

        assertTrue("Sufficient spacing of " + spacing + " between nodes " + n1 + " and " + n2
                , ok);
    }
    
    /**
     * Checks that the two passed shapes do not overlap. Considers any specified {@link Margins}.
     */
    private void checkOverlap(final LShape s1, final LShape s2) {
        
        Margins m1 = s1.getProperty(LayoutOptions.MARGINS);
        Margins m2 = s2.getProperty(LayoutOptions.MARGINS);
        
        Rectangle2D r1 = new Rectangle2D.Double(
                s1.getPosition().x - m1.left, 
                s1.getPosition().y - m1.top, 
                s1.getSize().x + m1.right, 
                s1.getSize().y + m1.bottom);
        Rectangle2D r2 = new Rectangle2D.Double(
                s2.getPosition().x - m2.left, 
                s2.getPosition().y - m2.top, 
                s2.getSize().x + m2.right, 
                s2.getSize().y + m2.bottom);
        
        Assert.assertTrue(s1 + " does not overlap " + s2, !r1.intersects(r2));
    }
    
    /**
     * Helper class to conveniently construct test configurations using different
     * {@link GraphCompactionStrategy}s.
     */
    private static final class Config extends BasicLayoutConfigurator {
        private GraphCompactionStrategy s;

        public static Config of(GraphCompactionStrategy s) {
            return new Config(s);
        }

        private Config(GraphCompactionStrategy s) {
            super(s.toString());
            this.s = s;
        }

        @Override
        public void applyConfiguration(KNode root) {
            root.getData(KLayoutData.class).setProperty(Properties.POST_COMPACTION, s);
            root.getData(KLayoutData.class).setProperty(LayoutOptions.EDGE_ROUTING, EdgeRouting.ORTHOGONAL);
        }
    }
}
