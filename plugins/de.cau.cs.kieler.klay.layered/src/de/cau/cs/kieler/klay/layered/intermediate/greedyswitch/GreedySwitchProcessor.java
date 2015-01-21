package de.cau.cs.kieler.klay.layered.intermediate.greedyswitch;

import java.util.ListIterator;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.intermediate.greedyswitch.SwitchDecider.SweepDirection;
import de.cau.cs.kieler.klay.layered.properties.GreedyType;

public class GreedySwitchProcessor implements ILayoutProcessor {

    private SwitchDeciderFactory switchDeciderFactory;
    private LNode[][] currentNodeOrder;
    private int amountOfLayers;
    private LNode[][] bestNodeOrder;
    private LGraph layeredGraph;
    private CrossingCounter crossingCounter;

    /**
     * The greedy switch processor can consider both neighboring layers for each layer whose node
     * order should be switched (considerAllCrossings is true) or only one neighboring layer
     * (considerAllCrossings is false).
     *
     * @param considerAllCrossings
     *            true when greedy switch should consider all neighbouring layers of the layer whose
     *            node order should be switched.
     */
    public GreedySwitchProcessor(final GreedyType switchDeciderType) {
        switchDeciderFactory = new SwitchDeciderFactory(switchDeciderType);
    }

    public void setConfiguration(final GreedyType switchDeciderType) {
        switchDeciderFactory = new SwitchDeciderFactory(switchDeciderType);
    }

    /**
     * {@inheritDoc}
     */
    public void process(final LGraph layeredGraph, final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Begin Greedy Switch intermediate processor", 1);

        int layerCount = layeredGraph.getLayers().size();
        if (layerCount < 2) {
            progressMonitor.done();
            return;
        }

        initialize(layeredGraph);
        progressMonitor.done();
    }

    private void initialize(final LGraph layeredGraph) {
        crossingCounter = new CrossingCounter(layeredGraph);

        this.layeredGraph = layeredGraph;
        int layerCount = layeredGraph.getLayers().size();
        bestNodeOrder = new LNode[layerCount][];
        currentNodeOrder = new LNode[layerCount][];

        ListIterator<Layer> layerIter = layeredGraph.getLayers().listIterator();
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

                currentNodeOrder[layerIndex][nodeIter.nextIndex()] = node;
                bestNodeOrder[layerIndex][nodeIter.nextIndex()] = node;
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
     * @param layerCount
     * @param graph
     */
    private void layerSweepConsiderThreeLayers() {
        // SwitchDecider switchDecider;
        // boolean improved;
        // do {
        // improved = false;
        // switchDecider =
        // switchDeciderFactory.getNewOneSidedSwitchDecider(amountOfLayers, currentNodeOrder, null);
        // improved |= continueSwitchingUntilNoImprovementInLayer(1, switchDecider);
        // for (int freeLayerIndex = 0; freeLayerIndex < amountOfLayers; freeLayerIndex++) {
        // switchDecider =
        // switchDeciderFactory.getNewOneSidedSwitchDecider(freeLayerIndex,
        // currentNodeOrder);
        // improved |=
        // continueSwitchingUntilNoImprovementInLayer(freeLayerIndex, switchDecider);
        // }
        // switchDecider =
        // switchDeciderFactory.getNewOneSidedSwitchDecider(graph.getAmountOfLayers() - 2,
        // currentNodeOrder);
        // improved |=
        // continueSwitchingUntilNoImprovementInLayer(graph.getAmountOfLayers() - 2,
        // switchDecider);
        // } while (improved);
        //
        // graph.setBestOrderAsGraph();
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
     *
     * and counts the number of crossings at the end of each sweep.
     *
     * @param layerCount
     *            The amount of layers.
     * @param graph
     */
    private void layerSweepConsiderTwoLayers() {
        int crossingsInGraph = amountOfLayers;
        int tempCrossingsInGraph;
        do {
            tempCrossingsInGraph = crossingsInGraph;
            setCurrentNodeOrderToBestNodeOrder();

            sweepBackwardAndForward();

            crossingsInGraph = getCurrentAmountOfCrossings();
        } while (tempCrossingsInGraph > crossingsInGraph && crossingsInGraph > 0);
        setBestOrderAsGraph();
    }

    private void setBestOrderAsGraph() {
        ListIterator<Layer> layerIter = layeredGraph.getLayers().listIterator();
        while (layerIter.hasNext()) {
            Layer layer = layerIter.next();
            LNode[] nodes = bestNodeOrder[layerIter.previousIndex()];
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

    private void setCurrentNodeOrderToBestNodeOrder() {
        for (int i = 0; i < bestNodeOrder.length; i++) {
            for (int j = 0; j < bestNodeOrder[i].length; j++) {
                bestNodeOrder[i][j] = currentNodeOrder[i][j];
            }
        }
    }

    private void sweepBackwardAndForward() {
        int layerCount = getAmountOfLayers();
        for (int freeLayerIndex = 1; freeLayerIndex < layerCount; freeLayerIndex++) {
            SwitchDecider switchDecider =
                    switchDeciderFactory.getNewOneSidedSwitchDecider(freeLayerIndex,
                            currentNodeOrder, SweepDirection.FORWARD);
            continueSwitchingUntilNoImprovementInLayer(freeLayerIndex, switchDecider);
        }

        for (int freeLayerIndex = layerCount - 2; freeLayerIndex >= 0; freeLayerIndex--) {
            SwitchDecider switchDecider =
                    switchDeciderFactory.getNewOneSidedSwitchDecider(freeLayerIndex,
                            currentNodeOrder, SweepDirection.BACKWARD);
            continueSwitchingUntilNoImprovementInLayer(freeLayerIndex, switchDecider);
        }
    }

    private int getAmountOfLayers() {
        return currentNodeOrder.length;
    }

    private boolean continueSwitchingUntilNoImprovementInLayer(final int freeLayerIndex,
            final SwitchDecider switchDecider) {
        boolean improved = false;
        boolean continueSwitching;
        do {
            continueSwitching = inLayerSweep(freeLayerIndex, switchDecider);
            improved |= continueSwitching;
        } while (continueSwitching);
        return improved;
    }

    private boolean inLayerSweep(final int freeLayerIndex, final SwitchDecider switchDecider) {
        boolean continueSwitching = false;

        int lengthOfFreeLayer = getLengthOfLayer(freeLayerIndex);
        for (int upperNodeIndex = 0; upperNodeIndex < lengthOfFreeLayer; upperNodeIndex++) {
            int lowerNodeIndex = upperNodeIndex + 1;

            if (switchDecider.doesSwitchReduceCrossings(upperNodeIndex, lowerNodeIndex)) {
                exchangeNodes(upperNodeIndex, lowerNodeIndex, freeLayerIndex);
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

    private int getLengthOfLayer(final int layerIndex) {
        return currentNodeOrder[layerIndex].length;
    }
}
