/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.intermediate.greedyswitch;

import java.util.Arrays;
import java.util.ListIterator;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.properties.GreedySwitchType;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * Implements the greedy switch heuristic: For two neighboring nodes, check to see if by exchanging
 * their positions ("switching" them) the number of crossings is reduced. If it is, switch them, if
 * it is not, don't. This principle is continued throughout the graph for all nodes in each layer. <br>
 * Configuration depends on the {@link GreedySwitchType} set on the graph:
 * <ul>
 * <li>One-sided: fixes the order of one layer and changes the order in a neighboring layer using
 * the number of crossings to this neighboring layer. To prevent increasing the number of crosses,
 * after each forward and backward sweep the number of crossings in the graph are recounted.
 * <li>useHperedgeCounter: uses the hyperedge crossing approximation algorithm to count
 * between-layer edges.
 * <li>Two-sided: sets a layer as free and counts crossings to both neighboring layers.
 * <li>useBestOfUpOrDown: tries sweeping from top to bottom in a layer and other way around and
 * takes the better solution.
 * </ul>
 * 
 * @author alan
 *
 */
public class GreedySwitchProcessor implements ILayoutProcessor {
    private SwitchDeciderFactory switchDeciderFactory;
    private LNode[][] currentNodeOrder;
    private LNode[][] bestNodeOrder;
    private LGraph layeredGraph;
    private AllCrossingsCounter crossingCounter;
    private SwitchDecider switchDecider;
    private boolean sweepDownwardInLayer = true;
    private LNode[][] originalNodeOrder;
    private GreedySwitchType greedySwitchType;
    private int currentCrossings;

    /**
     * {@inheritDoc}
     */
    public void process(final LGraph graph, final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Greedy switch crossing reduction", 1);

        greedySwitchType = graph.getProperty(Properties.GREEDY_SWITCH_TYPE);

        int layerCount = graph.getLayers().size();
        if (layerCount < 2 || greedySwitchType == GreedySwitchType.OFF) {
            progressMonitor.done();
            return;
        }

        switchDeciderFactory = new SwitchDeciderFactory(greedySwitchType);

        initialize(graph);

        if (greedySwitchType.useBestOfUpOrDown()) {
            tryBothLayerSweepDirections();
        } else {
            sweepOneSidedOrTwoSided();
        }

        setAsGraph(bestNodeOrder);

        progressMonitor.done();
    }

    private void tryBothLayerSweepDirections() {
        sweepOneSidedOrTwoSided();

        LNode[][] downwardSweepOrder = copyNodeOrder();
        int downwardSweepCrossings = getCrossingCount();

        // try other direction
        sweepDownwardInLayer = !sweepDownwardInLayer;
        currentNodeOrder = originalNodeOrder;
        sweepOneSidedOrTwoSided();
        int upwardSweepCrossings = getCrossingCount();

        if (downwardSweepCrossings <= upwardSweepCrossings) {
            setAsBestNodeOrder(downwardSweepOrder);
        }
    }

    private void sweepOneSidedOrTwoSided() {
        if (greedySwitchType.isOneSided()) {
            oneSidedLayerSweep();
        } else {
            twoSidedlayerSweep();
        }
    }

    private LNode[][] copyNodeOrder() {
        LNode[][] order = new LNode[bestNodeOrder.length][];
        for (int i = 0; i < order.length; i++) {
            order[i] = Arrays.copyOf(bestNodeOrder[i], bestNodeOrder[i].length);
        }
        return order;
    }

    private int getCrossingCount() {
        return greedySwitchType.isOneSided() ? currentCrossings : countCurrentNumberOfCrossings();
    }


    /**
     * This version of the greedy switch processor sweeps forward and backward over the graph.
     * Assuming a forward sweep, for each pair of layers it holds the nodes of the western layer in
     * place and changes the order of the eastern layer. A node is switched if it causes more
     * crossings between these two layers in the current order than in the switched order. Since the
     * layer following the free layer is not considered, this switch can cause more crossings in the
     * whole graph. In order to prevent this, the number of crossings are counted at the beginning
     * and after each forward and backward sweep. The result with the fewest crossings (possibly the
     * original order) is taken.
     */
    private void oneSidedLayerSweep() {
        int crossingsInGraph = countCurrentNumberOfCrossings();
        int currentSweepCrossings = Integer.MAX_VALUE;
        while (currentSweepCrossings > crossingsInGraph) {
            setAsBestNodeOrder(currentNodeOrder);

            if (crossingsInGraph == 0) {
                currentSweepCrossings = crossingsInGraph;
                break;
            }

            sweepForward();
            sweepBackward();

            currentSweepCrossings = crossingsInGraph;
            crossingsInGraph = countCurrentNumberOfCrossings();
        }
        currentCrossings = currentSweepCrossings;
    }

    /**
     * This version of the greedy switch processor repeatedly sweeps only forward across the graph.
     * For each layer it holds the neighboring layers in place and switches the node order iff it
     * would reduce the number of crossings on both sides of the middle layer. In this manner, the
     * number of crossings can not be increased and we do not need to calculate the total number of
     * crossings.
     * 
     */
    private void twoSidedlayerSweep() {
        boolean forward = true;
        boolean improved;
        do {
            if (forward) {
                improved = sweepForward();
            } else {
                improved = sweepBackward();
            }
            forward = !forward;
        } while (improved);
        setAsBestNodeOrder(currentNodeOrder);
    }


    private boolean sweepForward() {
        boolean improved = false;
        for (int freeLayerIndex = 0; freeLayerIndex < currentNodeOrder.length; freeLayerIndex++) {
            switchDecider =
                    switchDeciderFactory.getNewSwitchDecider(freeLayerIndex, currentNodeOrder,
                            CrossingCountSide.WEST);
            improved |= continueSwitchingUntilNoImprovementInLayer(freeLayerIndex);
        }
        return improved;
    }

    private boolean sweepBackward() {
        boolean improved = false;
        for (int freeLayerIndex = currentNodeOrder.length - 1; freeLayerIndex >= 0; freeLayerIndex--) {
            switchDecider =
                    switchDeciderFactory.getNewSwitchDecider(freeLayerIndex, currentNodeOrder,
                            CrossingCountSide.EAST);
            improved |= continueSwitchingUntilNoImprovementInLayer(freeLayerIndex);
        }
        return improved;
    }

    private boolean continueSwitchingUntilNoImprovementInLayer(final int freeLayerIndex) {
        boolean improved = false;
        boolean continueSwitching;
        do {

            if (sweepDownwardInLayer) {
                continueSwitching = sweepDownwardInLayer(freeLayerIndex);
            } else {
                continueSwitching = sweepUpwardInLayer(freeLayerIndex);
            }

            improved |= continueSwitching;
        } while (continueSwitching);
        return improved;
    }

    private boolean sweepDownwardInLayer(final int layerIndex) {
        boolean continueSwitching = false;
        int lengthOfFreeLayer = currentNodeOrder[layerIndex].length;
        for (int upperNodeIndex = 0; upperNodeIndex < lengthOfFreeLayer - 1; upperNodeIndex++) {
            int lowerNodeIndex = upperNodeIndex + 1;

            continueSwitching |= switchIfImproves(layerIndex, upperNodeIndex, lowerNodeIndex);

        }
        return continueSwitching;
    }


    private boolean sweepUpwardInLayer(final int layerIndex) {
        boolean continueSwitching = false;
        int lengthOfFreeLayer = currentNodeOrder[layerIndex].length;
        for (int lowerNodeIndex = lengthOfFreeLayer - 1; lowerNodeIndex > 0; lowerNodeIndex--) {
            int upperNodeIndex = lowerNodeIndex - 1;

            continueSwitching |= switchIfImproves(layerIndex, upperNodeIndex, lowerNodeIndex);

        }
        return continueSwitching;
    }

    private boolean switchIfImproves(final int layerIndex, final int upperNodeIndex,
            final int lowerNodeIndex) {
        boolean continueSwitching = false;

        if (switchDecider.doesSwitchReduceCrossings(upperNodeIndex, lowerNodeIndex)) {
            exchangeNodes(upperNodeIndex, lowerNodeIndex, layerIndex);

            continueSwitching = true;
        }
        return continueSwitching;
    }

    private int countCurrentNumberOfCrossings() {
        return crossingCounter.countAllCrossingsInGraphWithOrder(currentNodeOrder);
    }

    private void setAsBestNodeOrder(final LNode[][] nodeOrder) {
        for (int i = 0; i < bestNodeOrder.length; i++) {
            for (int j = 0; j < bestNodeOrder[i].length; j++) {
                bestNodeOrder[i][j] = nodeOrder[i][j];
            }
        }
    }

    private void exchangeNodes(final int indexOne, final int indexTwo, final int layerIndex) {
        switchDecider.notifyOfSwitch(currentNodeOrder[layerIndex][indexOne],
                currentNodeOrder[layerIndex][indexTwo]);
        LNode[] layer = currentNodeOrder[layerIndex];
        LNode temp = layer[indexTwo];
        layer[indexTwo] = layer[indexOne];
        layer[indexOne] = temp;
    }

    private void setAsGraph(final LNode[][] nodeOrder) {
        ListIterator<Layer> layerIter = layeredGraph.getLayers().listIterator();
        while (layerIter.hasNext()) {
            Layer layer = layerIter.next();
            LNode[] nodes = nodeOrder[layerIter.previousIndex()];
            ListIterator<LNode> nodeIter = layer.getNodes().listIterator();
            while (nodeIter.hasNext()) {
                nodeIter.next();
                nodeIter.set(nodes[nodeIter.previousIndex()]);
            }
        }
    }

    private void initialize(final LGraph graph) {

        layeredGraph = graph;
        int layerCount = graph.getLayers().size();
        bestNodeOrder = new LNode[layerCount][];
        currentNodeOrder = new LNode[layerCount][];
        originalNodeOrder = new LNode[layerCount][];

        ListIterator<Layer> layerIter = graph.getLayers().listIterator();
        while (layerIter.hasNext()) {
            Layer layer = layerIter.next();

            int layerNodeCount = layer.getNodes().size();
            assert layerNodeCount > 0;

            int layerIndex = layerIter.previousIndex();
            bestNodeOrder[layerIndex] = new LNode[layerNodeCount];
            currentNodeOrder[layerIndex] = new LNode[layerNodeCount];
            originalNodeOrder[layerIndex] = new LNode[layerNodeCount];

            ListIterator<LNode> nodeIter = layer.getNodes().listIterator();
            while (nodeIter.hasNext()) {
                LNode node = nodeIter.next();

                currentNodeOrder[layerIndex][nodeIter.previousIndex()] = node;
                bestNodeOrder[layerIndex][nodeIter.previousIndex()] = node;
                originalNodeOrder[layerIndex][nodeIter.previousIndex()] = node;
            }
        }
        crossingCounter = new AllCrossingsCounter(currentNodeOrder);
        if (greedySwitchType.useHyperedgeCounter()) {
            crossingCounter.useHyperedgeCounter();
        }
    }
}
