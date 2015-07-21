/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LNode.NodeType;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.p4nodes.InteractiveNodePlacer;
import de.cau.cs.kieler.klay.layered.p4nodes.LinearSegmentsNodePlacer;
import de.cau.cs.kieler.klay.layered.p4nodes.NodePlacementStrategy;
import de.cau.cs.kieler.klay.layered.p4nodes.SimpleNodePlacer;
import de.cau.cs.kieler.klay.layered.p4nodes.bk.BKNodePlacer;
import de.cau.cs.kieler.klay.layered.p5edges.OrthogonalEdgeRouter;
import de.cau.cs.kieler.klay.layered.properties.InternalProperties;
import de.cau.cs.kieler.klay.layered.properties.Properties;
import de.cau.cs.kieler.klay.layered.properties.Spacings;
import de.cau.cs.kieler.klay.layered.test.phases.SimplePhaseLayoutConfigurator;
import de.cau.cs.kieler.klay.test.config.ILayoutConfigurator;
import de.cau.cs.kieler.klay.test.utils.GraphTestObject;
import de.cau.cs.kieler.klay.test.utils.TestPath;

/**
 * Tests that spacing values as specified by several layout options in {@link LayoutOptions} and
 * {@link Properties} are respected correctly. Spacings are applied during several layout processors
 * such as node placement and edge routing. Remember not to use any of the convenience methods of
 * the {@link Spacings} class as these should be tested and verified in this test as well.
 * 
 * @author uru
 */
public class SpacingTest extends AbstractLayeredProcessorTest {

    public SpacingTest(final GraphTestObject testObject, final ILayoutConfigurator config) {
        super(testObject, config);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<ILayoutConfigurator> getConfigurators() {
        List<ILayoutConfigurator> configs = Lists.newArrayList();

        configs.add(new SimplePhaseLayoutConfigurator(Properties.NODE_PLACER,
                NodePlacementStrategy.INTERACTIVE, InteractiveNodePlacer.class));
        configs.add(new SimplePhaseLayoutConfigurator(Properties.NODE_PLACER,
                NodePlacementStrategy.BRANDES_KOEPF, BKNodePlacer.class));
        configs.add(new SimplePhaseLayoutConfigurator(Properties.NODE_PLACER,
                NodePlacementStrategy.SIMPLE, SimpleNodePlacer.class));
        configs.add(new SimplePhaseLayoutConfigurator(Properties.NODE_PLACER,
                NodePlacementStrategy.LINEAR_SEGMENTS, LinearSegmentsNodePlacer.class));

        return configs;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected TestPath[] getBundleTestPath() {
        TestPath[] testPaths =
                { new TestPath("klay_layered/spacing/", false, false, TestPath.Type.KGRAPH) };
        return testPaths;
    }

    /**
     * Run the layout algorithm up to the specified phase.
     */
    @Before
    public void runUntil() {
        layered.runLayoutTestUntil(OrthogonalEdgeRouter.class, true, state);

    }

    @Test
    public void testNodesDontOverlap() {

        // for convenience, collect all nodes
        for (LGraph g : state.getGraphs()) {

            double horizontalNodeSpacing = g.getProperty(InternalProperties.SPACING).doubleValue();
            double verticalNodeSpacing =
                    g.getProperty(InternalProperties.SPACING).doubleValue()
                            * g.getProperty(Properties.OBJ_SPACING_IN_LAYER_FACTOR);
            double verticalEdgeNodeSpacing =
                    g.getProperty(InternalProperties.SPACING).doubleValue()
                            * g.getProperty(Properties.EDGE_NODE_SPACING_FACTOR)
                            * g.getProperty(Properties.OBJ_SPACING_IN_LAYER_FACTOR);
            double verticalEdgeEdgeSpacing =
                    g.getProperty(InternalProperties.SPACING).doubleValue()
                            * g.getProperty(Properties.EDGE_SPACING_FACTOR)
                            * g.getProperty(Properties.OBJ_SPACING_IN_LAYER_FACTOR);

            List<LNode> allNodes = Lists.newArrayList();
            for (Layer l : g) {
                allNodes.addAll(l.getNodes());
            }

            for (LNode u : allNodes) {
                for (LNode v : allNodes) {
                    if (u != v) {
                        
                        double dist = -1;
                        
                        double spacingToCheck = horizontalNodeSpacing;
                        // if they overlap vertically ... 
                        if (
                                (u.getPosition().y >= v.getPosition().y && u.getPosition().y <= v.getPosition().y + v.getSize().y)
                                || (u.getPosition().y <= v.getPosition().y && v.getPosition().y <= u.getPosition().y + u.getSize().y)
                           ) {
                            // check horizontal spacing
                            if (u.getPosition().x < v.getPosition().x) {
                                // u left of v
                                dist = v.getPosition().x - v.getMargin().left
                                        - (u.getPosition().x + u.getSize().x + u.getMargin().right);
                                Assert.assertTrue(dist + " >= " + spacingToCheck, doubleGtEq(dist, spacingToCheck));
                            } else {
                                // v left of u
                                dist = u.getPosition().x - u.getMargin().left
                                        - (v.getPosition().x + v.getSize().x + v.getMargin().right);
                                Assert.assertTrue(dist + " >= " + spacingToCheck, doubleGtEq(dist, spacingToCheck));
                            }
                        }
                        
                        spacingToCheck = -1;
                        if (u.getType() == NodeType.NORMAL && v.getType() == NodeType.NORMAL) {
                            spacingToCheck = verticalNodeSpacing;
                        } else if (u.getType() == NodeType.LONG_EDGE && v.getType() == NodeType.LONG_EDGE) {
                            spacingToCheck = verticalEdgeEdgeSpacing;
                        } else if ((u.getType() == NodeType.LONG_EDGE && v.getType() == NodeType.NORMAL)
                                || (u.getType() == NodeType.NORMAL && v.getType() == NodeType.LONG_EDGE)) {
                            spacingToCheck = verticalEdgeNodeSpacing;
                        } else {
                            throw new IllegalStateException(
                                    "Spacing test does not support to validate input model "
                                            + graphObject.getFile() + "\n Node types were: " + u.getType() + " " + v.getType() );
                        }
                        
                        // if the overlap horizontally ...
                        if ( 
                                (u.getPosition().x >= v.getPosition().x && u.getPosition().x <= v.getPosition().x + v.getSize().x)
                                || (u.getPosition().x <= v.getPosition().x && v.getPosition().x <= u.getPosition().x + u.getSize().x)
                            ) {
                            // check vertical spacing
                            if (u.getPosition().y < v.getPosition().y) {
                                // u above v
                                dist = v.getPosition().y - v.getMargin().top
                                                - (u.getPosition().y + u.getSize().y + u.getMargin().bottom);
                                Assert.assertTrue(dist + " >= " + spacingToCheck, doubleGtEq(dist, spacingToCheck));
                            } else {
                                // v above u
                                dist = u.getPosition().y - u.getMargin().top
                                        - (v.getPosition().y + v.getSize().y + v.getMargin().bottom);
                                Assert.assertTrue(dist + " >= " + spacingToCheck, doubleGtEq(dist, spacingToCheck));
                            }
                        }
                        
                    }
                }
            }
        }
    }
    
    private static final double EPSILON = 0.0001;
    
    private static boolean doubleGtEq(final double d1, final double d2) {
        if (d1 >= d2) {
            return true;
        } else if (Math.abs(d1 - d2) < EPSILON) {
            return true;
        }
        return false;
    }
    
}
