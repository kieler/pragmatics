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

import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Function;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.ObjectArrays;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.p4nodes.InteractiveNodePlacer;
import de.cau.cs.kieler.klay.layered.p4nodes.LinearSegmentsNodePlacer;
import de.cau.cs.kieler.klay.layered.p4nodes.NodePlacementStrategy;
import de.cau.cs.kieler.klay.layered.p4nodes.SimpleNodePlacer;
import de.cau.cs.kieler.klay.layered.p4nodes.bk.BKNodePlacer;
import de.cau.cs.kieler.klay.layered.p4nodes.bk.CompactionStrategy;
import de.cau.cs.kieler.klay.layered.properties.InternalProperties;
import de.cau.cs.kieler.klay.layered.properties.Properties;
import de.cau.cs.kieler.klay.layered.properties.Spacings;
import de.cau.cs.kieler.klay.layered.test.AbstractLayeredProcessorTest;
import de.cau.cs.kieler.klay.test.config.ILayoutConfigurator;
import de.cau.cs.kieler.klay.test.utils.GraphTestObject;
import de.cau.cs.kieler.klay.test.utils.TestPath;

/**
 * Basic node placement phase tests.
 * 
 * @author uru
 * 
 */
public class NodePlacerTest extends AbstractLayeredProcessorTest {

    /**
     * Instantiates a new layer assignment test and set the graphObject to the current graph to
     * test.
     * 
     * @param testObject
     *            the test object
     * @param config
     *            layout configurator
     */
    public NodePlacerTest(final GraphTestObject testObject, final ILayoutConfigurator config) {
        super(testObject, config);
    }
    
    /**
     * {@inheritDoc}
     */
    protected TestPath[] getBundleTestPath() {
        return ObjectArrays.concat(super.getBundleTestPath(),
                new TestPath("klay_layered/node_placement/bk", true, false, TestPath.Type.KGRAPH));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<ILayoutConfigurator> getConfigurators() {
        List<ILayoutConfigurator> configs = Lists.newArrayList();

        configs.add(new SimplePhaseLayoutConfigurator(Properties.NODE_PLACER,
                NodePlacementStrategy.SIMPLE, SimpleNodePlacer.class));
        configs.add(new SimplePhaseLayoutConfigurator(Properties.NODE_PLACER,
                NodePlacementStrategy.INTERACTIVE, InteractiveNodePlacer.class));
        configs.add(new SimplePhaseLayoutConfigurator(Properties.NODE_PLACER,
                NodePlacementStrategy.LINEAR_SEGMENTS, LinearSegmentsNodePlacer.class));

        // BK with different compaction strategies
        configs.add(new SimplePhaseLayoutConfigurator(Properties.NODE_PLACER,
                NodePlacementStrategy.BRANDES_KOEPF, BKNodePlacer.class,
                CONFIG_COMPACTION_STRAT_CLASSIC));
        configs.add(new SimplePhaseLayoutConfigurator(Properties.NODE_PLACER,
                NodePlacementStrategy.BRANDES_KOEPF, BKNodePlacer.class,
                CONFIG_COMPACTION_STRAT_STRAIGHT));

        return configs;
    }

    /**
     * Run the layout algorithm until the specified node placement strategy finished.
     */
    @Before
    public void runUntil() {
        layered.runLayoutTestUntil(getAndCheckSimpleConfig().getStrategyImpl(), state);
    }

    /**
     * The y-coordinates of the nodes have to be strictly ordered.
     */
    @Test
    public void testStrictlyOrdered() {
        for (LGraph g : state.getGraphs()) {
            for (Layer layer : g.getLayers()) {
                Double lastY = null;
                for (LNode n : layer.getNodes()) {
                    if (lastY != null) {
                        assertTrue(lastY < n.getPosition().y);
                    }
                    lastY = n.getPosition().y;
                }
            }
        }
    }

    /**
     * Nodes are not allowed to overlap with their boundaries (including margins).
     */
    @Test
    public void testNonOverlapping() {
        for (LGraph g : state.getGraphs()) {
            for (Layer layer : g.getLayers()) {
                LNode last = null;
                for (LNode n : layer.getNodes()) {
                    // check
                    if (last != null) {
                        checkOverlapInHeight(g, last, n);
                    }
                    last = n;
                }
            }
        }
    }

    /**
     * @param fst
     *            the upper node
     * @param the
     *            lower node
     */
    private void checkOverlapInHeight(final LGraph g, final LNode fst, final LNode snd) {

        double spacing = getSpacing(g, fst, snd);
        // consider margin as well
        double fstLowerLeft = fst.getPosition().y + fst.getSize().y + fst.getMargin().bottom;
        double sndUpperLeft = snd.getPosition().y - snd.getMargin().top;
        assertTrue(
                fst + " does not overlap " + snd + ". (" 
                        + fstLowerLeft + ", " + sndUpperLeft
                        + ") " + spacing,
                doubleGreaterOrEqual(sndUpperLeft, fstLowerLeft + spacing));
    }
    
    private boolean doubleGreaterOrEqual(final double d1, final double d2) {
        if (d1 > d2) {
            return true;
        }
        return Math.abs(d1 - d2) < EPSILON;
    }
    
    private double getSpacing(final LGraph g, final LNode n1, final LNode n2) {
        Spacings spacings = g.getProperty(InternalProperties.SPACINGS);
        return spacings.getVerticalSpacing(n1, n2);
    }

    /**
     * A precision value for double comparisons. Note that the {@link LinearSegmentsNodePlacer} uses
     * a similar value for overlap detection, which can, if chosen too large, cause tests to fail.
     */
    private static final double EPSILON = 0.0001;

    private static final Function<KNode, KNode> CONFIG_COMPACTION_STRAT_CLASSIC =
            new Function<KNode, KNode>() {
                public KNode apply(final KNode input) {
                    input.getData(KShapeLayout.class).setProperty(Properties.COMPACTION_STRATEGY,
                            CompactionStrategy.CLASSIC);
                   Iterator<KNode> it = Iterators.filter(input.eAllContents(), KNode.class);
                   while (it.hasNext()) {
                       KNode n = it.next();
                       n.getData(KShapeLayout.class).setProperty(Properties.COMPACTION_STRATEGY,
                                CompactionStrategy.CLASSIC);
                    }
                    return input;
                };
            };
            
    private static final Function<KNode, KNode> CONFIG_COMPACTION_STRAT_STRAIGHT =
            new Function<KNode, KNode>() {
        public KNode apply(final KNode input) {
            input.getData(KShapeLayout.class).setProperty(Properties.COMPACTION_STRATEGY,
                    CompactionStrategy.IMPROVE_STRAIGHTNESS);
           Iterator<KNode> it = Iterators.filter(input.eAllContents(), KNode.class);
           while (it.hasNext()) {
               KNode n = it.next();
               n.getData(KShapeLayout.class).setProperty(Properties.COMPACTION_STRATEGY,
                        CompactionStrategy.IMPROVE_STRAIGHTNESS);
            }
            return input;
        };
    };
}
