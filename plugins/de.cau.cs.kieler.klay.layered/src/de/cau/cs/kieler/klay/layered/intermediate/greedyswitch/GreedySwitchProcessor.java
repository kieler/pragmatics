package de.cau.cs.kieler.klay.layered.intermediate;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.intermediate.SwitchDecider.SweepDirection;
import de.cau.cs.kieler.klay.layered.properties.GreedyType;

public class GreedySwitchProcessor implements ILayoutProcessor {

    private SwitchDeciderFactory switchDeciderFactory;
    private GraphDataInitializer graph;

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

        graph = new GraphDataInitializer(layeredGraph);
        graph.initialize();
        // TODO-alan missing method calls
        progressMonitor.done();
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
        SwitchDecider switchDecider;
        boolean improved;
        do {
            improved = false;
            switchDecider =
                    switchDeciderFactory.getNewOneSidedSwitchDecider(1, graph.getCurrentNodeOrder());
            improved |= continueSwitchingUntilNoImprovementInLayer(1, switchDecider);
            for (int freeLayerIndex = 0; freeLayerIndex < graph.getAmountOfLayers(); freeLayerIndex++) {
                switchDecider =
                        switchDeciderFactory.getNewOneSidedSwitchDecider(freeLayerIndex,
                                graph.getCurrentNodeOrder());
                improved |=
                        continueSwitchingUntilNoImprovementInLayer(freeLayerIndex, switchDecider);
            }
            switchDecider =
                    switchDeciderFactory.getNewOneSidedSwitchDecider(graph.getAmountOfLayers() - 2,
                            graph.getCurrentNodeOrder());
            improved |=
                    continueSwitchingUntilNoImprovementInLayer(graph.getAmountOfLayers() - 2,
                            switchDecider);
        } while (improved);

        graph.setBestOrderAsGraph();
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
        int crossingsInGraph = graph.getAmountOfCrossings();
        int tempCrossingsInGraph;
        do {
            tempCrossingsInGraph = crossingsInGraph;
            graph.setCurrentNodeOrderToBestNodeOrder();

            sweepBackwardAndForward();

            crossingsInGraph = graph.getAmountOfCrossings();
        } while (tempCrossingsInGraph > crossingsInGraph && crossingsInGraph > 0);
        graph.setBestOrderAsGraph();
    }

    private void sweepBackwardAndForward() {
        int layerCount = graph.getAmountOfLayers();
        for (int freeLayerIndex = 1; freeLayerIndex < layerCount; freeLayerIndex++) {
            SwitchDecider switchDecider =
                    switchDeciderFactory.getNewOneSidedSwitchDecider(freeLayerIndex,
                            graph.getCurrentNodeOrder());
            switchDecider.setSweepDirection(SweepDirection.FORWARD);
            continueSwitchingUntilNoImprovementInLayer(freeLayerIndex, switchDecider);
        }

        for (int freeLayerIndex = layerCount - 2; freeLayerIndex >= 0; freeLayerIndex--) {
            SwitchDecider switchDecider =
                    switchDeciderFactory.getNewOneSidedSwitchDecider(freeLayerIndex,
                            graph.getCurrentNodeOrder());
            switchDecider.setSweepDirection(SweepDirection.BACKWARD);
            continueSwitchingUntilNoImprovementInLayer(freeLayerIndex, switchDecider);
        }
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

        int lengthOfFreeLayer = graph.getLengthOfLayer(freeLayerIndex);
        for (int upperNodeIndex = 0; upperNodeIndex < lengthOfFreeLayer; upperNodeIndex++) {
            int lowerNodeIndex = upperNodeIndex + 1;

            if (switchDecider.doesSwitchReduceCrossings(upperNodeIndex, lowerNodeIndex)) {
                graph.exchangeNodes(upperNodeIndex, lowerNodeIndex, freeLayerIndex);
                continueSwitching = true;
            }
        }
        return continueSwitching;
    }
}
