/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Kiel University
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

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.p1cycles.CycleBreakingStrategy;
import de.cau.cs.kieler.klay.layered.p1cycles.GreedyCycleBreaker;
import de.cau.cs.kieler.klay.layered.p1cycles.InteractiveCycleBreaker;
import de.cau.cs.kieler.klay.layered.properties.Properties;
import de.cau.cs.kieler.klay.layered.test.AbstractLayeredProcessorTest;
import de.cau.cs.kieler.klay.test.config.ILayoutConfigurator;
import de.cau.cs.kieler.klay.test.utils.GraphTestObject;

/**
 * Basic tests for the cycle removing phase.
 * 
 * @author uru
 */
public class CycleBreakerTest extends AbstractLayeredProcessorTest {

    // Configuring the interactive cycle breaker
    private static final int INTERACTIVE_MAX_POS = 100;

    /**
     * Instantiates a new cycle breaker test and set the graphObject to the current graph to test.
     * 
     * @param testObject
     *            the test object
     * @param config
     *            layout configurator
     */
    public CycleBreakerTest(final GraphTestObject testObject, final ILayoutConfigurator config) {
        super(testObject, config);
    }

    /**
     * Add a configurator for following strategies.
     * <ul>
     * <li> {@link GreedyCycleBreaker}</li>
     * <li> {@link InteractiveCycleBreaker}</li>
     * </ul>
     * 
     * @return the possible configurations for this test.
     */
    @Override
    protected List<ILayoutConfigurator> getConfigurators() {
        List<ILayoutConfigurator> configs = Lists.newArrayList();

        // INTERACTIVE
        // For the interactive strategy it is important that every node has an assigned position.
        configs.add(new SimplePhaseLayoutConfigurator(Properties.CYCLE_BREAKING,
                CycleBreakingStrategy.INTERACTIVE, InteractiveCycleBreaker.class) {

            public void applyConfiguration(final KNode root) {
                // assign random coordinates to the nodes
                for (KNode n : root.getChildren()) {
                    KShapeLayout shape = n.getData(KShapeLayout.class);
                    if (shape.getXpos() == 0) {
                        shape.setXpos(random.nextInt(INTERACTIVE_MAX_POS));
                    }
                    if (shape.getYpos() == 0) {
                        shape.setYpos(random.nextInt(INTERACTIVE_MAX_POS));
                    }
                }

                // call super to apply the original config
                super.applyConfiguration(root);
            }
        });

        // GREEDY
        configs.add(new SimplePhaseLayoutConfigurator(Properties.CYCLE_BREAKING,
                CycleBreakingStrategy.GREEDY, GreedyCycleBreaker.class));

        return configs;
    }

    /**
     * Run the layout algorithm until the specified cycle breaking strategy finished.
     */
    @Before
    public void runUntil() {
        layered.runLayoutTestUntil(getAndCheckSimpleConfig().getStrategyImpl(), state);
    }

    /**
     * Tests whether the current graph is acyclic.
     */
    @Test
    public void testIsAcyclic() {
        // check every component
        for (LGraph lg : state.getGraphs()) {
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
