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
import java.util.Random;

import org.junit.Test;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
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

/**
 * Basic tests for the cycle removing phase.
 * 
 * @author uru
 */
public class CycleBreakerTest extends KlayAutomatedJUnitTest {

    // Object containing the current graphTestObject that contains both, a File and a KNode.
    private GraphTestObject graphObject;

    private List<LGraph> lgraphs;

    private LayeredLayoutProvider layered = new LayeredLayoutProvider();

    // Configuring the interactive cycle breaker
    private static final int INTERACTIVE_MAX_POS = 100;
    private static final int SEED = 1337;
    private Random random = new Random(SEED);

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
     * Tests whether the current graph is acyclic with the interactive strategy. For the interactive
     * strategy it is important that every node has an assigned position.
     */
    @Test
    public void testAcyclicInteractive() {

        // assign random coordinates to the nodes
        for (KNode n : graphObject.getKnode().getChildren()) {
            KShapeLayout shape = n.getData(KShapeLayout.class);
            if (shape.getXpos() == 0) {
                shape.setXpos(random.nextInt(INTERACTIVE_MAX_POS));
            }
            if (shape.getYpos() == 0) {
                shape.setYpos(random.nextInt(INTERACTIVE_MAX_POS));
            }
        }

        testWithStrategy(CycleBreakingStrategy.INTERACTIVE, InteractiveCycleBreaker.class);
    }

    /**
     * Tests whether the current graph is acyclic with the greedy strategy.
     */
    @Test
    public void testAcyclicGreedy() {
        testWithStrategy(CycleBreakingStrategy.GREEDY, GreedyCycleBreaker.class);
    }

    /**
     * Performs the actual test by applying the layout algorithm with the requested strategy until
     * the cycle breaking phase finished. Afterwards the acyclic test is performed.
     * 
     * @param strategy
     *            One of the available cycle breaking strategies.
     * @param processor
     *            The corresponding {{@link ILayoutPhase}
     */
    private void testWithStrategy(final CycleBreakingStrategy strategy,
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
            assertTrue(recAcyclicCheck(nodes));
        }
    }

    /**
     * Tests if the passed graph is acyclic in a recursive manner.
     * 
     * 1. If no nodes exist, the graph is acyclic. 2. Check if any leaf node exists, i.e., a node
     * with no outgoing edges. If not, the graph is cyclic. 3. Remove the identified leaf node and
     * all incoming edges. Continue with 1.
     * 
     * @param nodes
     *            the nodes composing the graph.
     * @return {@code true} if the graph is acyclic, false otherwise.
     */
    private boolean recAcyclicCheck(final List<LNode> nodes) {
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
            return recAcyclicCheck(removeLeaf(nodes, leaf));
        } else {
            // graph is cyclic
            return false;
        }
    }

    private static List<LNode> removeLeaf(final List<LNode> nodes, final LNode leaf) {
        // remove all incoming edges
        for (LEdge inc : leaf.getIncomingEdges()) {
            inc.getSource().getOutgoingEdges().remove(inc);
        }
        // remove the node
        List<LNode> cpy = Lists.newArrayList(nodes);
        cpy.remove(leaf);
        return cpy;
    }

    private static boolean isLeaf(final LNode node) {
        return Iterables.isEmpty(node.getOutgoingEdges());
    }

}
