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
package de.cau.cs.kieler.klay.layered.intermediate.greedyswitch;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.properties.GreedySwitchType;

/**
 * Checks all variants of SwitchDeciders. These are given as parameters with Parameterized.class to
 * create six different test cases.
 * 
 * @author alan
 *
 */
@RunWith(Parameterized.class)
public class SwitchDeciderTest {

    private final GreedySwitchType greedyType;

    /**
     * This method is needed by Parameterized.class. The parameters are all elements of the
     * GreedySwitchType enum.
     * 
     * @return parameters
     */
    @Parameters(name = "{0}")
    public static Iterable<Object[]> greedyTypes() {
        return Arrays.asList(new Object[][] { { GreedySwitchType.ONE_SIDED_COUNTER, },
                { GreedySwitchType.TWO_SIDED_COUNTER }, { GreedySwitchType.ONE_SIDED_CROSSING_MATRIX },
                { GreedySwitchType.TWO_SIDED_CROSSING_MATRIX },
                { GreedySwitchType.ONE_SIDED_ON_DEMAND_CROSSING_MATRIX },
                { GreedySwitchType.TWO_SIDED_ON_DEMAND_CROSSING_MATRIX } });
    }

    /**
     * Constructor is called and greedyType set by Parameterized.
     * 
     * @param gT
     *            the greedy type
     */
    public SwitchDeciderTest(final GreedySwitchType gT) {
        greedyType = gT;
        factory = new SwitchDeciderFactory(gT);
    }

    private final TestGraphCreator creator = new TestGraphCreator();
    private LGraph graph;
    private SwitchDecider decider;
    private final SwitchDeciderFactory factory;
    private LNode[][] currentNodeOrder;
    private int freeLayerIndex;

    // CHECKSTYLEOFF javadoc
    // CHECKSTYLEOFF MagicNumber
    @Test
    public void crossFormed() {
        graph = creator.getCrossFormedGraph();

        decider = givenDeciderForFreeLayer(1, CrossingCountSide.WEST);
        assertThat(decider.doesSwitchReduceCrossings(0, 1), is(true));

        decider = givenDeciderForFreeLayer(0, CrossingCountSide.EAST);
        assertThat(decider.doesSwitchReduceCrossings(0, 1), is(true));
    }

    @Test
    public void oneNode() {
        graph = creator.getOneNodeGraph();

        decider = givenDeciderForFreeLayer(0, CrossingCountSide.WEST);
        assertThat(decider.doesSwitchReduceCrossings(0, 0), is(false));

        decider = givenDeciderForFreeLayer(0, CrossingCountSide.EAST);
        assertThat(decider.doesSwitchReduceCrossings(0, 0), is(false));
    }

    /**
     * <pre>
     *   --* 
     *   |  
     * *-+-*-* 
     *   | 
     *   --*
     * </pre>
     * 
     * @ * *
     */
    @Test
    public void inLayerSwitchable() {
        graph = creator.getInLayerEdgesGraph();

        decider = givenDeciderForFreeLayer(1, CrossingCountSide.WEST);
        assertThat(decider.doesSwitchReduceCrossings(0, 1), is(true));

        decider = givenDeciderForFreeLayer(1, CrossingCountSide.EAST);
        assertThat(decider.doesSwitchReduceCrossings(0, 1), is(true));
    }

    @Test
    public void multipleEdgesBetweenSameNodes() {
        graph = creator.getMultipleEdgesBetweenSameNodesGraph();

        decider = givenDeciderForFreeLayer(1, CrossingCountSide.WEST);
        assertThat(decider.doesSwitchReduceCrossings(0, 1), is(true));

        decider = givenDeciderForFreeLayer(0, CrossingCountSide.EAST);
        assertThat(decider.doesSwitchReduceCrossings(0, 1), is(true));
    }

    @Test
    public void selfLoops() {
        graph = creator.getCrossWithManySelfLoopsGraph();

        decider = givenDeciderForFreeLayer(1, CrossingCountSide.WEST);
        assertThat(decider.doesSwitchReduceCrossings(0, 1), is(true));

        decider = givenDeciderForFreeLayer(0, CrossingCountSide.EAST);
        assertThat(decider.doesSwitchReduceCrossings(0, 1), is(true));
    }

    /**
     * Graph:
     * 
     * <pre>
     *     ______
     * *---|____|
     *      |  |  ____
     *      *--+--|  |
     *         |  |  |
     *         *--|__|
     * </pre>
     * 
     * The one-side decider should switch north-south port crossings in the center layer.
     * 
     * 
     */
    @Test
    public void northSouthPortCrossing() {
        graph = creator.getThreeLayerNorthSouthCrossingGraph();

        decider = givenDeciderForFreeLayer(1, CrossingCountSide.WEST);
        if (greedyType.isOneSided()) {
            assertThat(decider.doesSwitchReduceCrossings(1, 2), is(true));
        } else {
            assertThat(decider.doesSwitchReduceCrossings(1, 2), is(false));
        }
    }

    @Test
    public void moreComplex() {
        graph = creator.getMoreComplexThreeLayerGraph();

        decider = givenDeciderForFreeLayer(1, CrossingCountSide.WEST);
        assertThat(decider.doesSwitchReduceCrossings(0, 1), is(false));

        decider = givenDeciderForFreeLayer(2, CrossingCountSide.WEST);
        assertThat(decider.doesSwitchReduceCrossings(0, 1), is(true));

        assertThat(decider.doesSwitchReduceCrossings(1, 2), is(false));

        decider = givenDeciderForFreeLayer(1, CrossingCountSide.EAST);
        assertThat(decider.doesSwitchReduceCrossings(0, 1), is(false));

        decider = givenDeciderForFreeLayer(0, CrossingCountSide.EAST);
        assertThat(decider.doesSwitchReduceCrossings(0, 1), is(false));

        assertThat(decider.doesSwitchReduceCrossings(1, 2), is(true));
    }

    @Test
    public void switchOnlyTrueForOneSided() {
        graph = creator.getSwitchOnlyOneSided();

        decider = givenDeciderForFreeLayer(1, CrossingCountSide.WEST);
        if (greedyType.isOneSided()) {
            assertThat(decider.doesSwitchReduceCrossings(0, 1), is(true));
        } else {
            assertThat(decider.doesSwitchReduceCrossings(0, 1), is(false));
        }
    }

    @Test
    public void switchOnlyTrueForOneSidedEasternSide() {
        graph = creator.getSwitchOnlyEastOneSided();

        decider = givenDeciderForFreeLayer(1, CrossingCountSide.EAST);
        if (greedyType.isOneSided()) {
            assertThat(decider.doesSwitchReduceCrossings(0, 1), is(true));
        } else {
            assertThat(decider.doesSwitchReduceCrossings(0, 1), is(false));
        }
    }

    @Test
    public void constraintsPreventSwitch() {
        graph = creator.getCrossFormedGraphWithConstraintsInSecondLayer();

        decider = givenDeciderForFreeLayer(1, CrossingCountSide.WEST);
        assertThat(decider.doesSwitchReduceCrossings(0, 1), is(false));
    }

    @Test
    public void inLayerUnitConstraintsPreventSwitch() {
        graph = creator.getGraphWhereLayoutUnitPreventsSwitch();
        decider = givenDeciderForFreeLayer(0, CrossingCountSide.WEST);
        assertThat(decider.doesSwitchReduceCrossings(1, 2), is(false));
    }

    @Test
    public void switchAndRecount() {
        graph = creator.getCrossFormedGraph();

        decider = givenDeciderForFreeLayer(1, CrossingCountSide.WEST);
        assertThat(decider.doesSwitchReduceCrossings(0, 1), is(true));

        decider = givenDeciderForFreeLayer(0, CrossingCountSide.EAST);
        assertThat(decider.doesSwitchReduceCrossings(0, 1), is(true));

        switchNodes(0, 1);
        decider.notifyOfSwitch(getNodesInLayer(0).get(0), getNodesInLayer(0).get(1));
        assertThat(decider.doesSwitchReduceCrossings(0, 1), is(false));

    }

    @Test
    public void switchAndRecountCounterBug() {
        graph = creator.shouldSwitchThreeTimesGraph();
        decider = givenDeciderForFreeLayer(1, CrossingCountSide.WEST);
        assertThat(decider.doesSwitchReduceCrossings(0, 1), is(true));
        assertThat(decider.doesSwitchReduceCrossings(1, 2), is(false));
        assertThat(decider.doesSwitchReduceCrossings(2, 3), is(true));

        decider.notifyOfSwitch(getNodesInLayer(1).get(0), getNodesInLayer(1).get(1));
        switchNodes(0, 1);
        assertThat(decider.doesSwitchReduceCrossings(0, 1), is(false));
        assertThat(decider.doesSwitchReduceCrossings(1, 2), is(false));
        assertThat(decider.doesSwitchReduceCrossings(2, 3), is(true));

        decider.notifyOfSwitch(getNodesInLayer(1).get(2), getNodesInLayer(1).get(3));
        switchNodes(2, 3);
        assertThat(decider.doesSwitchReduceCrossings(0, 1), is(false));
        assertThat(decider.doesSwitchReduceCrossings(1, 2), is(true));
        assertThat(decider.doesSwitchReduceCrossings(2, 3), is(false));

        decider.notifyOfSwitch(getNodesInLayer(1).get(1), getNodesInLayer(1).get(2));
        switchNodes(1, 2);
        assertThat(decider.doesSwitchReduceCrossings(0, 1), is(false));
        assertThat(decider.doesSwitchReduceCrossings(1, 2), is(false));
        assertThat(decider.doesSwitchReduceCrossings(2, 3), is(false));

    }

    @Test
    public void switchAndRecountReducedCounterBug() {
        graph = creator.getSwitchedProblemGraph();
        decider = givenDeciderForFreeLayer(1, CrossingCountSide.WEST);
        for (int i = 0; i < getNodesInLayer(1).size() - 1; i++) {
            assertThat("attempted switch " + i + " with " + (i + 1),
                    decider.doesSwitchReduceCrossings(i, i + 1), is(false));
        }
    }

    @Test
    public void ignoresNoFixedOrder() {
        graph = creator.getGraphNoCrossingsDueToPortOrderNotFixed();
        decider = givenDeciderForFreeLayer(1, CrossingCountSide.WEST);
        assertThat(decider.doesSwitchReduceCrossings(0, 1), is(false));
    }

    @Test
    public void shouldSwitchWithLongEdgeDummies() {
        graph = creator.getNorthernNorthSouthDummyEdgeCrossingGraph();
        decider = givenDeciderForFreeLayer(1, CrossingCountSide.WEST);

        assertThat(decider.doesSwitchReduceCrossings(1, 2), is(true));

        if (greedyType.isOneSided()) {
            assertThat(decider.doesSwitchReduceCrossings(0, 1), is(true));
        }

        graph = creator.getSouthernNorthSouthDummyEdgeCrossingGraph();
        decider = givenDeciderForFreeLayer(1, CrossingCountSide.WEST);

        assertThat(decider.doesSwitchReduceCrossings(0, 1), is(true));

        if (greedyType.isOneSided()) {
            assertThat(decider.doesSwitchReduceCrossings(1, 2), is(true));
        }
    }

    private void switchNodes(final int upperNodeIndex, final int lowerNodeIndex) {
        LNode upperNode = currentNodeOrder[freeLayerIndex][upperNodeIndex];
        currentNodeOrder[freeLayerIndex][upperNodeIndex] =
                currentNodeOrder[freeLayerIndex][lowerNodeIndex];
        currentNodeOrder[freeLayerIndex][lowerNodeIndex] = upperNode;
    }

    private List<LNode> getNodesInLayer(final int layerIndex) {
        return graph.getLayers().get(layerIndex).getNodes();
    }

    private SwitchDecider givenDeciderForFreeLayer(final int layerIndex,
            final CrossingCountSide direction) {
        freeLayerIndex = layerIndex;
        currentNodeOrder = getCurrentNodeOrder();
        return factory.getNewSwitchDecider(layerIndex, currentNodeOrder, direction);
    }

    private LNode[][] getCurrentNodeOrder() {
        LNode[][] nodeOrder = new LNode[graph.getLayers().size()][];
        List<Layer> layers = graph.getLayers();
        for (int i = 0; i < layers.size(); i++) {
            Layer layer = layers.get(i);
            List<LNode> nodes = layer.getNodes();
            nodeOrder[i] = new LNode[nodes.size()];
            for (int j = 0; j < nodes.size(); j++) {
                nodeOrder[i][j] = nodes.get(j);
            }
        }
        return nodeOrder;
    }
}
