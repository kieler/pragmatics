/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.test.phases;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.options.EdgeRouting;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.p5edges.OrthogonalEdgeRouter;
import de.cau.cs.kieler.klay.layered.p5edges.PolylineEdgeRouter;
import de.cau.cs.kieler.klay.layered.p5edges.SplineEdgeRouter;
import de.cau.cs.kieler.klay.test.config.ILayoutConfigurator;
import de.cau.cs.kieler.klay.test.config.SimplePhaseLayoutConfigurator;
import de.cau.cs.kieler.klay.test.utils.GraphTestObject;

/**
 * Basic edge routing tests.
 * 
 * @author uru
 * 
 */
public class EdgeRoutingTest extends AbstractLayeredPhaseTest {

    /** Epsilon value for double comparisons (equal). */
    private static final double COMPARE_EPSILON = 0.0001d;

    /**
     * Instantiates a new layer assignment test and set the graphObject to the current graph to
     * test.
     * 
     * @param testObject
     *            the test object
     * @param config
     *            layout configurator
     */
    public EdgeRoutingTest(final GraphTestObject testObject, final ILayoutConfigurator config) {
        super(testObject, config);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<ILayoutConfigurator> getConfigurators() {
        List<ILayoutConfigurator> configs = Lists.newArrayList();

        configs.add(new SimplePhaseLayoutConfigurator(LayoutOptions.EDGE_ROUTING,
                EdgeRouting.ORTHOGONAL, OrthogonalEdgeRouter.class));
        configs.add(new SimplePhaseLayoutConfigurator(LayoutOptions.EDGE_ROUTING,
                EdgeRouting.POLYLINE, PolylineEdgeRouter.class));
        configs.add(new SimplePhaseLayoutConfigurator(LayoutOptions.EDGE_ROUTING,
                EdgeRouting.SPLINES, SplineEdgeRouter.class));

        return configs;
    }

    /**
     * Run the layout algorithm until the specified node placement strategy finished.
     */
    @Before
    public void runUntil() {
        if (configurator instanceof SimplePhaseLayoutConfigurator) {
            SimplePhaseLayoutConfigurator simple = (SimplePhaseLayoutConfigurator) configurator;
            lgraphs = layered.runLayoutTestUntil(simple.getStrategyImpl());
        } else {
            throw new IllegalArgumentException("Every edge routing configurator should be "
                    + "a subclass of SimplePhaseLayoutConfigurator");
        }
    }

    /**
     * Edges created by the orthogonal routing strategy have to consist of straight lines.
     */
    @Test
    public void testOrthogonalEdges() {
        // only check the orthogonal strategy
        if (configurator instanceof SimplePhaseLayoutConfigurator
                && ((SimplePhaseLayoutConfigurator) configurator).getStrategyImpl().equals(
                        OrthogonalEdgeRouter.class)) {

            // run through all edges
            for (LGraph g : lgraphs) {
                for (Layer layer : g.getLayers()) {
                    for (LNode n : layer.getNodes()) {

                        for (LEdge e : n.getOutgoingEdges()) {

                            // combine start point, end point, and bend points
                            // the position of a port is calculated as nodePos + portPos + anchor
                            List<KVector> points = Lists.newLinkedList();
                            points.add(KVector.sum(n.getPosition(), e.getSource().getPosition(), e
                                    .getSource().getAnchor()));
                            points.addAll(e.getBendPoints());
                            points.add(KVector.sum(e.getTarget().getNode().getPosition(), e
                                    .getTarget().getPosition(), e.getTarget().getAnchor()));

                            // the first segment has to be horizontal
                            boolean horizontal = true;
                            KVector last = null;
                            for (KVector v : points) {
                                if (last != null) {

                                    // for horizontal segments the y coordinates of the consecutive
                                    // points have to be equal, for vertical segments the x
                                    // coordinates
                                    if (horizontal) {
                                        assertTrue(isCloseEnough(last.y, v.y));
                                    } else {
                                        assertTrue(isCloseEnough(last.x, v.x));
                                    }
                                    horizontal = !horizontal;
                                }
                                last = v;
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean isCloseEnough(final double d1, final double d2) {
        return (Math.abs(d1 - d2) < COMPARE_EPSILON);
    }
}
