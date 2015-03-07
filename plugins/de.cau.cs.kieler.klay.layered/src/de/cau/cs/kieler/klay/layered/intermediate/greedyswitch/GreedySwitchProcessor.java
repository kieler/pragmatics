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

import java.util.ListIterator;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.properties.GreedySwitchType;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * TODO-alan.
 * 
 * @author alan
 *
 */
public class GreedySwitchProcessor implements ILayoutProcessor {

    private SwitchDeciderFactory switchDeciderFactory;
    private LNode[][] currentNodeOrder;
    private LNode[][] bestNodeOrder;
    private LGraph layeredGraph;
    private AllCrossingCounter crossingCounter;

    /**
     * ¸ {@inheritDoc}
     */
    public void process(final LGraph graph, final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Greedy switch crossing reduction", 1);

        GreedySwitchType switchDeciderType = graph.getProperty(Properties.GREEDY_TYPE);

        int layerCount = graph.getLayers().size();
        if (layerCount < 2 || switchDeciderType == GreedySwitchType.OFF) {
            progressMonitor.done();
            return;
        }

        switchDeciderFactory = new SwitchDeciderFactory(switchDeciderType);

        initialize(graph);

        if (switchDeciderType.isOneSided()) {
            layerSweepConsiderTwoLayers();
        } else {
            layerSweepConsiderThreeLayers();
        }

        progressMonitor.done();
    }

    private void initialize(final LGraph graph) {

        layeredGraph = graph;
        int layerCount = graph.getLayers().size();
        bestNodeOrder = new LNode[layerCount][];
        currentNodeOrder = new LNode[layerCount][];

        ListIterator<Layer> layerIter = graph.getLayers().listIterator();
        while (layerIter.hasNext()) {
            Layer layer = layerIter.next();

            int layerNodeCount = layer.getNodes().size();
            assert layerNodeCount > 0;

            int layerIndex = layerIter.previousIndex();
            bestNodeOrder[layerIndex] = new LNode[layerNodeCount];
            currentNodeOrder[layerIndex] = new LNode[layerNodeCount];

            ListIterator<LNode> nodeIter = layer.getNodes().listIterator();
            while (nodeIter.hasNext()) {
                LNode node = nodeIter.next();

                currentNodeOrder[layerIndex][nodeIter.previousIndex()] = node;
                bestNodeOrder[layerIndex][nodeIter.previousIndex()] = node;
            }
        }
        crossingCounter = new AllCrossingCounter(currentNodeOrder);
    }

    /**
     * This version of the greedy switch processor repeatedly sweeps only forward across the graph.
     * For each layer it holds the neighboring layers in place and switches the node order iff it
     * would reduce the amount of crossings on both sides of the middle layer. In this manner, the
     * amount of crossings can not be increased and we do not need to calculate the total amount of
     * crossings.
     *
     */
    private void layerSweepConsiderThreeLayers() {
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
        setAsGraph(currentNodeOrder);
    }

    /**
     * This version of the greedy switch processor sweeps forward and backward over the graph.
     * Assuming a forward sweep, for each pair of layers it holds the nodes of the eastern layer in
     * place and changes the order of the western layer. A node is switched if it causes more
     * crossings between these two layers in the current order than in the switched order. Since the
     * layer following the western layer is not considered, this switch can cause more crossings in
     * the whole graph. In order to prevent this, the amount of crossings are counted at the
     * beginning and after each forward and backward sweep. The result with the fewest crossings
     * (possibly the original order) is taken.
     */
    private void layerSweepConsiderTwoLayers() {
        int crossingsInGraph = getCurrentAmountOfCrossings();
        int crossingsInGraphInLastSweep = Integer.MAX_VALUE;
        while (crossingsInGraphInLastSweep > crossingsInGraph) {
            setCurrentNodeOrderAsBestNodeOrder();

            if (crossingsInGraph == 0) {
                break;
            }

            sweepForward();
            sweepBackward();

            crossingsInGraphInLastSweep = crossingsInGraph;
            crossingsInGraph = getCurrentAmountOfCrossings();
        }
        setAsGraph(bestNodeOrder);
    }

    private boolean sweepForward() {
        boolean improved = false;
        for (int freeLayerIndex = 0; freeLayerIndex < currentNodeOrder.length; freeLayerIndex++) {
            SwitchDecider switchDecider =
                    switchDeciderFactory.getNewSwitchDecider(freeLayerIndex, currentNodeOrder,
                            CrossingCountSide.WEST);
            improved |= continueSwitchingUntilNoImprovementInLayer(freeLayerIndex, switchDecider);
        }
        return improved;
    }

    private boolean sweepBackward() {
        boolean improved = false;
        for (int freeLayerIndex = currentNodeOrder.length - 1; freeLayerIndex >= 0; freeLayerIndex--) {
            SwitchDecider switchDecider =
                    switchDeciderFactory.getNewSwitchDecider(freeLayerIndex, currentNodeOrder,
                            CrossingCountSide.EAST);
            improved |= continueSwitchingUntilNoImprovementInLayer(freeLayerIndex, switchDecider);
        }
        return improved;
    }

    private int getCurrentAmountOfCrossings() {
        return crossingCounter.countAllCrossingsInGraphWithOrder(currentNodeOrder);
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

    private void setCurrentNodeOrderAsBestNodeOrder() {
        for (int i = 0; i < bestNodeOrder.length; i++) {
            for (int j = 0; j < bestNodeOrder[i].length; j++) {
                bestNodeOrder[i][j] = currentNodeOrder[i][j];
            }
        }
    }

    private boolean continueSwitchingUntilNoImprovementInLayer(final int freeLayerIndex,
            final SwitchDecider switchDecider) {
        boolean improved = false;
        boolean continueSwitching;
        do {
            continueSwitching = sweepDownwardInLayer(freeLayerIndex, switchDecider);
            improved |= continueSwitching;
        } while (continueSwitching);
        return improved;
    }

    private boolean sweepDownwardInLayer(final int layerIndex, final SwitchDecider switchDecider) {
        boolean continueSwitching = false;
        int lengthOfFreeLayer = currentNodeOrder[layerIndex].length;
        for (int upperNodeIndex = 0; upperNodeIndex < lengthOfFreeLayer - 1; upperNodeIndex++) {
            int lowerNodeIndex = upperNodeIndex + 1;
            if (switchDecider.doesSwitchReduceCrossings(upperNodeIndex, lowerNodeIndex)) {
                exchangeNodes(upperNodeIndex, lowerNodeIndex, layerIndex, switchDecider);
                continueSwitching = true;
            }
        }
        return continueSwitching;
    }

    private void exchangeNodes(final int indexOne, final int indexTwo, final int layerIndex,
            final SwitchDecider switchDecider) {
        switchDecider.notifyOfSwitch(currentNodeOrder[layerIndex][indexOne],
                currentNodeOrder[layerIndex][indexTwo]);
        LNode[] layer = currentNodeOrder[layerIndex];
        LNode temp = layer[indexTwo];
        layer[indexTwo] = layer[indexOne];
        layer[indexOne] = temp;
    }
}
