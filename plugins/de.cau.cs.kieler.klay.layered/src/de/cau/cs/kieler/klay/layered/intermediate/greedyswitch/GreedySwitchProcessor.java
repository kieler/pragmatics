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
import de.cau.cs.kieler.klay.layered.intermediate.greedyswitch.SwitchDecider.CrossingCountSide;
import de.cau.cs.kieler.klay.layered.properties.GreedyType;

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
    private CrossingCounter crossingCounter;
    private final GreedyType switchDeciderType;

    /**
     * TODO-alan.
     * 
     * @param deciderType
     *            TODO-alan.
     */
    public GreedySwitchProcessor(final GreedyType deciderType) {
        switchDeciderType = deciderType;
        switchDeciderFactory = new SwitchDeciderFactory(deciderType);
    }

    /**
     * TODO-alan. Probably not necessary.
     * 
     * @param deciderType
     *            TODO-alan.
     */
    public void setConfiguration(final GreedyType deciderType) {
        switchDeciderFactory = new SwitchDeciderFactory(deciderType);
    }

    /**
     * {@inheritDoc}
     */
    public void process(final LGraph graph, final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Begin Greedy Switch intermediate processor", 1);

        int layerCount = graph.getLayers().size();
        if (layerCount < 2) {
            progressMonitor.done();
            return;
        }

        initialize(graph);

        if (switchDeciderType.isOneSided()) {
            layerSweepConsiderTwoLayers();
        } else {
            layerSweepConsiderThreeLayers();
        }

        progressMonitor.done();
    }

    // TODO-alan remove initialization of other classes, maybe add checks.
    private void initialize(final LGraph graph) {
        crossingCounter = new CrossingCounter(graph);

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

            int nodeId = 0;
            ListIterator<LNode> nodeIter = layer.getNodes().listIterator();
            while (nodeIter.hasNext()) {
                LNode node = nodeIter.next();

                currentNodeOrder[layerIndex][nodeIter.previousIndex()] = node;
                bestNodeOrder[layerIndex][nodeIter.previousIndex()] = node;
                node.id = nodeId;
                nodeId++; // TODO-alan It is not necessary to guarantee this at this level.
            }
        }
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
        sweepBackwardAndForwardInGraph();
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
        int tempCrossingsInGraph;
        do {
            setCurrentNodeOrderAsBestNodeOrder();
            tempCrossingsInGraph = crossingsInGraph;

            sweepBackwardAndForwardInGraph();

            crossingsInGraph = getCurrentAmountOfCrossings();
        } while (tempCrossingsInGraph > crossingsInGraph && crossingsInGraph > 0);
        setCurrentNodeOrderAsBestNodeOrder();

        setAsGraph(currentNodeOrder);
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

    private int getCurrentAmountOfCrossings() {
        return crossingCounter.countAllCrossingsInGraphWithOrder(currentNodeOrder);
    }

    private void setCurrentNodeOrderAsBestNodeOrder() {
        for (int i = 0; i < bestNodeOrder.length; i++) {
            for (int j = 0; j < bestNodeOrder[i].length; j++) {
                bestNodeOrder[i][j] = currentNodeOrder[i][j];
            }
        }
    }

    private void sweepBackwardAndForwardInGraph() {
        int layerCount = currentNodeOrder.length;
        for (int freeLayerIndex = 0; freeLayerIndex < layerCount; freeLayerIndex++) {
            SwitchDecider switchDecider =
                    switchDeciderFactory.getNewSwitchDecider(freeLayerIndex, currentNodeOrder,
                            CrossingCountSide.WEST);
            continueSwitchingUntilNoImprovementInLayer(freeLayerIndex, switchDecider);
        }

        for (int freeLayerIndex = layerCount - 1; freeLayerIndex >= 0; freeLayerIndex--) {
            SwitchDecider switchDecider =
                    switchDeciderFactory.getNewSwitchDecider(freeLayerIndex, currentNodeOrder,
                            CrossingCountSide.EAST);
            continueSwitchingUntilNoImprovementInLayer(freeLayerIndex, switchDecider);
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
                exchangeNodes(upperNodeIndex, lowerNodeIndex, layerIndex);
                continueSwitching = true;
            }
        }
        return continueSwitching;
    }

    private void exchangeNodes(final int indexOne, final int indexTwo, final int layerIndex) {
        LNode[] layer = currentNodeOrder[layerIndex];
        LNode temp = layer[indexTwo];
        layer[indexTwo] = layer[indexOne];
        layer[indexOne] = temp;
    }
}
