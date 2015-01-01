/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.intermediate;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.properties.InternalProperties;

/**
 * This class is an abstract superclass for three different variants of the greedy switch algorithm:
 * {@link GreedySwitchCounterProcessor} uses {@link CrossingCounter} to count the amount of
 * crossings that would result from a switch. {@link GreedySwitchCrossingMatrixProcessor} calculates
 * the crossing matrix for a pair of fixed and free layers beforehand. Entries i,j in a crossing
 * matrix show the amount of crossings between incident edges to nodes i and j when node i is above
 * node j. {@link GreedySwitchOnDemandCrossingMatrixProcessor} calculates the entries in the
 * crossing matrix only when needed. The last two both use {@link TwoNodeTwoLayerCrossingCounter}
 * which calculates two entries i,j and j,i in the crossing matrix. All variants can
 *
 * @author alan
 *
 */
public abstract class AbstractGreedySwitchProcessor implements ILayoutProcessor {

    private LNode[][] bestNodeOrder;

    private LNode[][] currentNodeOrder;

    private final boolean considerAllCrossings;

    private int[][] eastNodeDegrees;

    private int[][] nodePositions;

    private final Map<LPort, Integer> portIndices;

    private int[][] westNodeDegrees;

    private LGraph graph;

    /**
     * The greedy switch processor can consider both neighboring layers for each layer whose node
     * order should be switched (considerAllCrossings is true) or only one neighboring layer
     * (considerAllCrossings is false).
     *
     * @param considerAllCrossings
     *            true when greedy switch should consider all neighbouring layers of the layer whose
     *            node order should be switched.
     */
    public AbstractGreedySwitchProcessor(final boolean considerAllCrossings) {
        this.considerAllCrossings = considerAllCrossings;
        portIndices = new HashMap<LPort, Integer>();
    }

    /**
     * {@inheritDoc}
     */
    public void process(final LGraph layeredGraph, final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Begin Greedy Switch intermediate processor", 1);

        final int layerCount = layeredGraph.getLayers().size();
        if (layerCount < 2) {
            progressMonitor.done();
            return;
        }

        initialize(layeredGraph, layerCount);
        if (considerAllCrossings) {
            layerSweepConsiderThreeLayers(layerCount, layeredGraph);
        } else {
            layerSweepConsiderTwoLayers(layerCount, layeredGraph);
        }

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
    private void layerSweepConsiderThreeLayers(final int layerCount, final LGraph layeredGraph) {
        boolean improved;
        do {
            improved = false;
            improved |= continueSwitchingInLayerUntilNoImprovement(false, 1);
            for (int eastFixedLayIndex = 0; eastFixedLayIndex < layerCount - 1; eastFixedLayIndex++) {
                improved |= continueSwitchingInLayerUntilNoImprovement(true, eastFixedLayIndex);
            }
            improved |= continueSwitchingInLayerUntilNoImprovement(true, layerCount - 2);
        } while (improved);
        setNewGraph(currentNodeOrder, layeredGraph);
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
    private void layerSweepConsiderTwoLayers(final int layerCount, final LGraph layeredGraph) {
        int crossingsInGraph = getAmountOfCrossings(currentNodeOrder);
        int tempCrossingsInGraph;
        do {
            tempCrossingsInGraph = crossingsInGraph;
            copyMatrix(currentNodeOrder, bestNodeOrder);

            sweepBackwardAndForward(layerCount);

            crossingsInGraph = getAmountOfCrossings(currentNodeOrder);
        } while (tempCrossingsInGraph > crossingsInGraph && crossingsInGraph > 0);
        setNewGraph(bestNodeOrder, layeredGraph);
    }

    private void sweepBackwardAndForward(final int layerCount) {
        for (int fixedLayerIndex = 0; fixedLayerIndex < layerCount - 1; fixedLayerIndex++) {
            continueSwitchingInLayerUntilNoImprovement(true, fixedLayerIndex);
        }

        for (int fixedLayerIndex = layerCount - 1; fixedLayerIndex > 0; fixedLayerIndex--) {
            continueSwitchingInLayerUntilNoImprovement(false, fixedLayerIndex);
        }
    }

    private void initialize(final LGraph layeredGraph, final int layerCount) {
        graph = layeredGraph;
        bestNodeOrder = new LNode[layerCount][];
        currentNodeOrder = new LNode[layerCount][];
        eastNodeDegrees = new int[layerCount][];
        westNodeDegrees = new int[layerCount][];
        nodePositions = new int[layerCount][];

        final ListIterator<Layer> layerIter = layeredGraph.getLayers().listIterator();
        while (layerIter.hasNext()) {
            final Layer layer = layerIter.next();

            final int layerIndex = layerIter.previousIndex();
            initializeArrays(layer, layerIndex);

            int nodeId = 0;
            final ListIterator<LNode> nodeIter = layer.getNodes().listIterator();
            while (nodeIter.hasNext()) {
                final LNode node = nodeIter.next();

                setNodeOrderAndIds(layerIndex, nodeId, nodeIter.previousIndex(), node);
                nodeId++;

                setPortIndicesAndNodeDegrees(layerIndex, node);
            }
        }
    }

    private void initializeArrays(final Layer layer, final int layerIndex) {
        final int layerNodeCount = layer.getNodes().size();

        assert layerNodeCount > 0;

        bestNodeOrder[layerIndex] = new LNode[layerNodeCount];
        currentNodeOrder[layerIndex] = new LNode[layerNodeCount];

        eastNodeDegrees[layerIndex] = new int[layerNodeCount];
        westNodeDegrees[layerIndex] = new int[layerNodeCount];

        nodePositions[layerIndex] = new int[layerNodeCount];
    }

    /**
     * The lowest port on the western side of each node is numbered 0, i.e. the ports are numbered
     * bottom-up. eastNodeDegrees saves the amount of edges coming from the east into each node,
     * westNodeDegrees equivalently.
     *
     */
    private void setPortIndicesAndNodeDegrees(final int layerIndex, final LNode node) {
        int easternPortNo = 0;
        for (final LPort lPort : node.getPorts(PortSide.EAST)) {
            final LPort port = lPort;
            portIndices.put(port, easternPortNo);
            easternPortNo += port.getDegree();
        }
        eastNodeDegrees[layerIndex][node.id] = easternPortNo;

        int westernPortNo = 0;
        for (final LPort lPort : node.getPorts(PortSide.WEST)) {
            final LPort port = lPort;
            portIndices.put(port, westernPortNo);
            westernPortNo += port.getDegree();
        }
        westNodeDegrees[layerIndex][node.id] = westernPortNo;
    }

    private void setNodeOrderAndIds(final int layerIndex, final int nodeId, final int index,
            final LNode node) {
        currentNodeOrder[layerIndex][index] = node;
        bestNodeOrder[layerIndex][index] = node;
        nodePositions[layerIndex][index] = nodeId;
        node.id = nodeId;
    }

    private void setNewGraph(final LNode[][] sweep, final LGraph layeredGraph) {
        final ListIterator<Layer> layerIter = layeredGraph.getLayers().listIterator();
        while (layerIter.hasNext()) {
            final Layer layer = layerIter.next();
            final LNode[] nodes = sweep[layerIter.previousIndex()];
            final ListIterator<LNode> nodeIter = layer.getNodes().listIterator();
            while (nodeIter.hasNext()) {
                nodeIter.next();
                nodeIter.set(nodes[nodeIter.previousIndex()]);
            }
        }
    }

    private boolean continueSwitchingInLayerUntilNoImprovement(
            final boolean fixedLayerLeftOfFreeLayer, final int fixedLayerIndex) {
        final int freeLayerIndex =
                fixedLayerLeftOfFreeLayer ? fixedLayerIndex + 1 : fixedLayerIndex - 1;
        final LNode[] freeLayer = currentNodeOrder[freeLayerIndex];

        boolean improved = false;
        boolean continueSwitching;
        do {
            continueSwitching = inLayerSweep(fixedLayerIndex, freeLayerIndex, freeLayer);
            improved |= continueSwitching;
        } while (continueSwitching);
        return improved;
    }

    private boolean inLayerSweep(final int fixedLayerIndex, final int freeLayerIndex,
            final LNode[] freeLayer) {
        boolean continueSwitching = false;
        for (int upperNodeIndex = 0; upperNodeIndex < freeLayer.length - 1; upperNodeIndex++) {
            final int lowerNodeIndex = upperNodeIndex + 1;
            final boolean noSuccessorConstraint =
                    checkForConstraints(freeLayer, upperNodeIndex, lowerNodeIndex);

            if (noSuccessorConstraint) {
                final boolean switchReducesCrossings =
                        checkIfSwitchReducesCrossings(currentNodeOrder, freeLayerIndex,
                                fixedLayerIndex, upperNodeIndex, lowerNodeIndex);

                if (switchReducesCrossings) {
                    exchangeNodes(upperNodeIndex, lowerNodeIndex, freeLayer, freeLayerIndex);
                    continueSwitching = true;
                }
            }
        }
        return continueSwitching;
    }

    private boolean checkForConstraints(final LNode[] freeLayer, final int upperNodeIndex,
            final int lowerNodeIndex) {
        final LNode upperNode = freeLayer[upperNodeIndex];
        final LNode lowerNode = freeLayer[lowerNodeIndex];

        final List<LNode> constraints =
                upperNode.getProperty(InternalProperties.IN_LAYER_SUCCESSOR_CONSTRAINTS);
        boolean noSuccessorConstraint =
                constraints == null || constraints.size() == 0 || !constraints.contains(lowerNode);

        // If upperNode and lowerNode are part of a layout unit, then the layout units must
        // be equal for a switch to be allowed.
        final LNode upperLayoutUnit =
                upperNode.getProperty(InternalProperties.IN_LAYER_LAYOUT_UNIT);
        final LNode lowerLayoutUnit =
                lowerNode.getProperty(InternalProperties.IN_LAYER_LAYOUT_UNIT);
        if (upperLayoutUnit != null && lowerLayoutUnit != null) {
            noSuccessorConstraint &= upperLayoutUnit == lowerLayoutUnit;
        }
        return noSuccessorConstraint;
    }

    private static <T> void copyMatrix(final T[][] source, final T[][] dest) {
        for (int i = 0; i < dest.length; i++) {
            for (int j = 0; j < dest[i].length; j++) {
                dest[i][j] = source[i][j];
            }
        }
    }

    /**
     * Switches nodes with index indexOne and indexTwo in layer layer.
     *
     * @param indexOne
     *            The first nodes index
     * @param indexTwo
     *            The second nodes index
     * @param layer
     *            The layer as LNode array
     * @param freeLayerIndex
     *            The index of the free layer
     */
    protected void exchangeNodes(final int indexOne, final int indexTwo, final LNode[] layer,
            final int freeLayerIndex) {

        final int positionNodeOne = nodePositions[freeLayerIndex][layer[indexOne].id];
        nodePositions[freeLayerIndex][layer[indexOne].id] =
                nodePositions[freeLayerIndex][layer[indexTwo].id];
        nodePositions[freeLayerIndex][layer[indexTwo].id] = positionNodeOne;

        final LNode temp = layer[indexTwo];
        layer[indexTwo] = layer[indexOne];
        layer[indexOne] = temp;
    }

    /**
     * Returns whether or not a switch of upperNodeIndex and lowerNodeIndex would reduce the amount
     * of crossings.
     *
     * @param currentOrder
     *            TODO
     * @param freeLayerIndex
     *            Index of the layer which is currently being reordered.
     * @param fixedLayerIndex
     *            Index of the layer whose node order is fixed.
     * @param upperNodeIndex
     *            Upper node.
     * @param lowerNodeIndex
     *            Lower node.
     * @return true if a switch would reduce crossings.
     */
    protected abstract boolean checkIfSwitchReducesCrossings(LNode[][] currentOrder,
            final int freeLayerIndex, final int fixedLayerIndex, final int upperNodeIndex,
            final int lowerNodeIndex);

    /**
     * Calculates the amount of crossings in the graph.
     *
     * @param currentOrder
     *            The current ordering of nodes in the graph.
     * @return the amount of crossings in the graph.
     */
    protected abstract int getAmountOfCrossings(LNode[][] currentOrder);

    /**
     * @return the eastNodeDegrees
     */
    public int[][] getEastNodeDegrees() {
        return eastNodeDegrees;
    }

    /**
     * @return the nodePositions
     */
    public int[][] getNodePositions() {
        return nodePositions;
    }

    /**
     * @return the port indices
     */
    public Map<LPort, Integer> getPortIndices() {
        return portIndices;
    }

    /**
     * @return the westNodeDegrees
     */
    public int[][] getWestNodeDegrees() {
        return westNodeDegrees;
    }

    /**
     * @return the graph
     */
    public LGraph getGraph() {
        return graph;
    }

    /**
     * @return the considerAllCrossings
     */
    public boolean isConsiderAllCrossings() {
        return considerAllCrossings;
    }

}
