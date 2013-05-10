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
package de.cau.cs.kieler.klay.layered.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.klay.layered.ILayoutPhase;
import de.cau.cs.kieler.klay.layered.LayeredLayoutProvider;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.p1cycles.CycleBreakingStrategy;
import de.cau.cs.kieler.klay.layered.p1cycles.GreedyCycleBreaker;
import de.cau.cs.kieler.klay.layered.p1cycles.InteractiveCycleBreaker;
import de.cau.cs.kieler.klay.layered.properties.Properties;
import de.cau.cs.kieler.klay.test.KlayAutomatedJUnitTest;
import de.cau.cs.kieler.klay.test.utils.GraphTestObject;
import de.cau.cs.kieler.klay.test.utils.TestPath;
// SUPPRESS CHECKSTYLE AvoidStarImport

// SUPPRESS CHECKSTYLE AvoidStarImport

/**
 * Basic Tests for the cycle removing phase.
 * 
 * @author uru
 */
public class CycleBreakerTest extends KlayAutomatedJUnitTest {

    // Object containing the current graphTestObject that contains both, a File and a KNode.
    private GraphTestObject graphObject;

    private List<LGraph> lgraphs;

    private LayeredLayoutProvider layered = new LayeredLayoutProvider();

    /**
     * Instantiates a new KlayTestExample test and set the graphObject to the current graph to test.
     * 
     * @param testObject
     *            the test object
     */
    public CycleBreakerTest(final GraphTestObject testObject) {
        graphObject = testObject;
    }

    /**
     * {@inheritDoc}
     */
    protected TestPath[] getBundleTestPath() {
        TestPath[] testPaths = { new TestPath("random", false, false, TestPath.Type.KGRAPH) };
        return testPaths;
    }

    /**
     * Run the layered algorithm until the cycle breaker finished.
     */
    @Before
    public void layout() {

    }

    /**
     * Tests whether the current graph is acyclic with the interactive strategy.
     */
    @Test
    public void testAcyclicInteractive() {
        testWithStrategy(CycleBreakingStrategy.INTERACTIVE, InteractiveCycleBreaker.class);
    }

    /**
     * Tests whether the current graph is acyclic with the greedy strategy.
     */
    @Test
    public void testAcyclicGreedy() {
        testWithStrategy(CycleBreakingStrategy.GREEDY, GreedyCycleBreaker.class);
    }

    public void testWithStrategy(final CycleBreakingStrategy strategy,
            final Class<? extends ILayoutPhase> processor) {
        // set the desired strategy
        graphObject.getKnode().getData(KShapeLayout.class)
                .setProperty(Properties.CYCLE_BREAKING, strategy);

        // perform the layout
        lgraphs = layered.doLayoutTest(graphObject.getKnode(), new BasicProgressMonitor(),
                processor);

        // check every component
        for (LGraph lg : lgraphs) {
            List<LNode> nodes = lg.getLayerlessNodes();
            System.out.println(nodes);
            assertTrue(recAcyclic(nodes));
        }
    }

    public boolean recAcyclic(final List<LNode> nodes) {

        // if graph is empty, it is acyclic
        if (nodes.isEmpty()) {
            return true;
        }

        // find a leaf
        LNode leaf = null;
        for (LNode node : nodes) {
            if (isLeaf(node)) {
                leaf = node;
                break;
            }
        }

        if (leaf != null) {
            // trim the leaf and start recursion
            return recAcyclic(trimLeaf(nodes, leaf));
        } else {
            // graph is cyclic
            return false;
        }
    }

    public static List<LNode> trimLeaf(final List<LNode> nodes, final LNode leaf) {
        // remove all incoming edges
        for (LEdge inc : leaf.getIncomingEdges()) {
            inc.getSource().getOutgoingEdges().remove(inc);
        }
        // remove the node
        List<LNode> cpy = Lists.newArrayList(nodes);
        cpy.remove(leaf);
        return cpy;
    }

    public static boolean isLeaf(final LNode node) {
        return Iterables.isEmpty(node.getOutgoingEdges());
    }

}
