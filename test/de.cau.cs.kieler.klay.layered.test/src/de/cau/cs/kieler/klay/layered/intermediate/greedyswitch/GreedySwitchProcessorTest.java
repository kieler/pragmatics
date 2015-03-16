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
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.properties.GreedySwitchType;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * Tests all variants of greedy switch. All three one-sided methods must behave the same and all
 * three two sided methods must behave the same.
 * 
 * @author alan
 *
 */
@RunWith(Parameterized.class)
public class GreedySwitchTest {
    private final TestGraphCreator creator = new TestGraphCreator();
    private LGraph graph;
    private final GreedySwitchProcessor greedySwitcher;
    private final IKielerProgressMonitor monitor;
    private final GreedySwitchType greedyType;

    /**
     * Constructor called by Parameterized.
     * 
     * @param gT
     *            greedyType
     */
    public GreedySwitchTest(final GreedySwitchType gT) {
        greedyType = gT;
        greedySwitcher = new GreedySwitchProcessor();
        monitor = new BasicProgressMonitor();
    }

    /**
     * Sets the Parameters to be tested as all elements of the GreedySwitchType enum.
     * 
     * @return parameters
     */
    @Parameters(name = "{0}")
    public static Iterable<Object[]> greedyTypes() {
        return Arrays.asList(new Object[][] { { GreedySwitchType.ONE_SIDED_COUNTER, },
                { GreedySwitchType.ONE_SIDED_CROSSING_MATRIX },
                { GreedySwitchType.ONE_SIDED_ON_DEMAND_CROSSING_MATRIX },
                { GreedySwitchType.TWO_SIDED_COUNTER }, { GreedySwitchType.TWO_SIDED_CROSSING_MATRIX },
                { GreedySwitchType.TWO_SIDED_ON_DEMAND_CROSSING_MATRIX } });
    }

    // CHECKSTYLEOFF javadoc
    // CHECKSTYLEOFF MagicNumber
    @Test
    public void shouldSwitchCross() {
        graph = creator.getCrossFormedGraph();

        List<LNode> expectedOrderLayerOne;
        List<LNode> expectedOrderLayerTwo;
        if (greedyType.isOneSided()) {
            expectedOrderLayerOne = getNodesInLayer(0);
            expectedOrderLayerTwo = switchOrderOfNodesInLayer(0, 1, 1);
        } else {
            expectedOrderLayerOne = switchOrderOfNodesInLayer(0, 1, 0);
            expectedOrderLayerTwo = getNodesInLayer(1);
        }

        startGreedySwitcherWithCurrentType();

        assertThat("Layer one", getNodesInLayer(0), is(expectedOrderLayerOne));
        assertThat("Layer two", getNodesInLayer(1), is(expectedOrderLayerTwo));
    }

    private void startGreedySwitcherWithCurrentType() {
        graph.setProperty(Properties.GREEDY_TYPE, greedyType);
        greedySwitcher.process(graph, monitor);
    }

    @Test
    public void constraintsPreventSwitchInSecondLayer() {
        graph = creator.getCrossFormedGraphWithConstraintsInSecondLayer();

        List<LNode> expectedOrderLayerOne = switchOrderOfNodesInLayer(0, 1, 0);
        List<LNode> expectedOrderLayerTwo = getNodesInLayer(1);

        startGreedySwitcherWithCurrentType();

        assertThat("Layer one", getNodesInLayer(0), is(expectedOrderLayerOne));
        assertThat("Layer two", getNodesInLayer(1), is(expectedOrderLayerTwo));
    }

    @Test
    public void constraintsPreventAnySwitch() {
        graph = creator.getCrossFormedGraphConstraintsPreventAnySwitch();

        List<LNode> expectedOrderLayerOne = getNodesInLayer(0);
        List<LNode> expectedOrderLayerTwo = getNodesInLayer(1);

        startGreedySwitcherWithCurrentType();

        assertThat("Layer one", getNodesInLayer(0), is(expectedOrderLayerOne));
        assertThat("Layer two", getNodesInLayer(1), is(expectedOrderLayerTwo));
    }

    @Test
    public void layoutUnitConstraintPreventsSwitch() {
        graph = creator.getNodesInDifferentLayoutUnitsPreventSwitch();

        List<LNode> expectedOrderLayerTwo = getNodesInLayer(1);

        startGreedySwitcherWithCurrentType();

        assertThat("Layer one", getNodesInLayer(1), is(expectedOrderLayerTwo));
    }

    @Test
    public void oneNode() {
        graph = creator.getOneNodeGraph();
        int layerIndex = 0;
        switchOrderOfNodesInLayer(0, 0, layerIndex);
        // should cause no errors
    }

    @Test
    public void inLayerSwitchable() {
        graph = creator.getInLayerEdgesGraph();

        int layerIndex = 1;
        List<LNode> expectedOrder = switchOrderOfNodesInLayer(0, 1, layerIndex);

        startGreedySwitcherWithCurrentType();

        assertThat(getNodesInLayer(layerIndex), is(expectedOrder));
    }

    @Test(expected = AssertionError.class)
    public void emptyGraphShouldResultInException() {
        graph = creator.getEmptyGraph();

        startGreedySwitcherWithCurrentType();
        fail("Did not cause AssertionError");

    }

    @Test
    public void multipleEdgesBetweenSameNodes() {
        graph = creator.getMultipleEdgesBetweenSameNodesGraph();

        List<LNode> expectedOrderLayerOne;
        List<LNode> expectedOrderLayerTwo;
        if (greedyType.isOneSided()) {
            expectedOrderLayerOne = getNodesInLayer(0);
            expectedOrderLayerTwo = switchOrderOfNodesInLayer(0, 1, 1);
        } else {
            expectedOrderLayerOne = switchOrderOfNodesInLayer(0, 1, 0);
            expectedOrderLayerTwo = getNodesInLayer(1);
        }

        startGreedySwitcherWithCurrentType();

        assertThat("Layer one", getNodesInLayer(0), is(expectedOrderLayerOne));
        assertThat("Layer two", getNodesInLayer(1), is(expectedOrderLayerTwo));
    }

    @Test
    public void selfLoops() {
        graph = creator.getCrossWithManySelfLoopsGraph();

        List<LNode> expectedOrderLayerOne;
        List<LNode> expectedOrderLayerTwo;
        if (greedyType.isOneSided()) {
            expectedOrderLayerOne = getNodesInLayer(0);
            expectedOrderLayerTwo = switchOrderOfNodesInLayer(0, 1, 1);
        } else {
            expectedOrderLayerOne = switchOrderOfNodesInLayer(0, 1, 0);
            expectedOrderLayerTwo = getNodesInLayer(1);
        }

        startGreedySwitcherWithCurrentType();

        assertThat("Layer one", getNodesInLayer(0), is(expectedOrderLayerOne));
        assertThat("Layer two", getNodesInLayer(1), is(expectedOrderLayerTwo));
    }

    /**
     * <pre>
     *     ______
     * *---|____|
     *      |  |  
     *      *--+--*
     *         | 
     *         *--*
     * </pre>
     * 
     * The one-side decider should switch north-south port crossings in the center layer.
     */
    @Test
    public void northSouthPortCrossing() {
        graph = creator.getThreeLayerNorthSouthCrossingShouldSwitchGraph();

        int layerIndex = 1;
        List<LNode> expectedOrderTwoSided = new ArrayList<LNode>(getNodesInLayer(layerIndex));
        List<LNode> expectedOrderOneSided = switchOrderOfNodesInLayer(1, 2, layerIndex);

        startGreedySwitcherWithCurrentType();

        if (greedyType.isOneSided()) {
            assertThat(getNodesInLayer(layerIndex), is(expectedOrderOneSided));
        } else {
            assertThat(getNodesInLayer(layerIndex), is(expectedOrderTwoSided));
        }
    }

    /**
     * <pre>
     * *\  --*
     *   \/ /
     * *-*===*
     *  + /
     * * * --*
     * Port order not fixed.
     * </pre>
     */
    @Test
    public void moreComplex() {
        graph = creator.getMoreComplexThreeLayerGraph();

        List<LNode> expectedOrderLayerOne;
        List<LNode> expectedOrderLayerTwo;
        List<LNode> expectedOrderLayerThree;
        if (greedyType.isOneSided()) {
            expectedOrderLayerOne = switchOrderOfNodesInLayer(1, 2, 0);
            expectedOrderLayerTwo = getNodesInLayer(1);
            expectedOrderLayerThree = switchOrderOfNodesInLayer(0, 1, 2);
        } else {
            expectedOrderLayerOne = switchOrderOfNodesInLayer(1, 2, 0);
            expectedOrderLayerTwo = getNodesInLayer(1);
            expectedOrderLayerThree = switchOrderOfNodesInLayer(0, 1, 2);
        }
        startGreedySwitcherWithCurrentType();
        assertThat("Layer one", getNodesInLayer(0), is(expectedOrderLayerOne));
        assertThat("Layer two", getNodesInLayer(1), is(expectedOrderLayerTwo));
        assertThat("Layer three", getNodesInLayer(2), is(expectedOrderLayerThree));
    }

    @Test
    public void switchOnlyForOneSided() {
        graph = creator.getSwitchOnlyOneSided();

        int layerIndex = 1;
        List<LNode> expectedOrderOneSided = switchOrderOfNodesInLayer(0, 1, layerIndex);
        List<LNode> expectedOrderTwoSided = new ArrayList<LNode>(getNodesInLayer(layerIndex));

        startGreedySwitcherWithCurrentType();
        if (greedyType.isOneSided()) {
            assertThat(getNodesInLayer(layerIndex), is(expectedOrderOneSided));
        } else {
            assertThat(getNodesInLayer(layerIndex), is(expectedOrderTwoSided));
        }
    }

    @Test
    public void doesNotWorsenCrossAmount() {
        graph = creator.getGraphWhichCouldBeWorsenedBySwitch();
        List<LNode> expectedOrderFirstLayer = new ArrayList<LNode>(getNodesInLayer(0));
        List<LNode> expectedOrderSecondLayer = new ArrayList<LNode>(getNodesInLayer(1));

        startGreedySwitcherWithCurrentType();

        assertThat("Layer one", getNodesInLayer(0), is(expectedOrderFirstLayer));
        assertThat("Layer two", getNodesInLayer(1), is(expectedOrderSecondLayer));
    }

    @Test
    public void switchMoreThanOnce() {
        graph = creator.shouldSwitchThreeTimesGraph();
        List<LNode> oneSidedFirstLayer = new ArrayList<LNode>(getNodesInLayer(0));
        List<LNode> oneSidedFirstSwitchSecondLayer = switchOrderOfNodesInLayer(0, 1, 1);
        List<LNode> oneSidedsecondSwitchSecondLayer =
                getCopyWithSwitchedOrder(2, 3, oneSidedFirstSwitchSecondLayer);
        List<LNode> oneSidedThirdSwitchSecondLayer =
                getCopyWithSwitchedOrder(1, 2, oneSidedsecondSwitchSecondLayer);

        List<LNode> twoSidedFirstLayer = switchOrderOfNodesInLayer(0, 1, 0);
        List<LNode> twoSidedFirstSwitchSecondLayer = switchOrderOfNodesInLayer(1, 2, 1);
        List<LNode> twoSidedsecondSwitchSecondLayer =
                getCopyWithSwitchedOrder(0, 1, twoSidedFirstSwitchSecondLayer);
        startGreedySwitcherWithCurrentType();
        if (greedyType.isOneSided()) {
            assertThat("Layer one" + getNodesInLayer(0), getNodesInLayer(0), is(oneSidedFirstLayer));
            assertThat("Layer two " + getNodesInLayer(1), getNodesInLayer(1),
                    is(oneSidedThirdSwitchSecondLayer));
        } else {
            assertThat("Layer one " + getNodesInLayer(0), getNodesInLayer(0),
                    is(twoSidedFirstLayer));
            assertThat("Layer two " + getNodesInLayer(1), getNodesInLayer(1),
                    is(twoSidedsecondSwitchSecondLayer));
        }

    }

    private List<LNode> getNodesInLayer(final int layerIndex) {
        return new ArrayList<LNode>(graph.getLayers().get(layerIndex).getNodes());
    }

    private List<LNode> switchOrderOfNodesInLayer(final int nodeOne, final int nodeTwo,
            final int layerIndex) {
        List<LNode> layer = getNodesInLayer(layerIndex);
        return getCopyWithSwitchedOrder(nodeOne, nodeTwo, layer);
    }

    private List<LNode> getCopyWithSwitchedOrder(final int nodeOne, final int nodeTwo,
            final List<LNode> layer) {
        LNode firstNode = layer.get(nodeOne);
        LNode secondNode = layer.get(nodeTwo);
        List<LNode> switchedList = new ArrayList<LNode>(layer);
        switchedList.set(nodeOne, secondNode);
        switchedList.set(nodeTwo, firstNode);
        return switchedList;
    }

}
