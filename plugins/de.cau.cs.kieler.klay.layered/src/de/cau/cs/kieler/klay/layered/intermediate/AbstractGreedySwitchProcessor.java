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

import java.util.List;
import java.util.ListIterator;
import java.util.TreeMap;

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
 * crossing matrix only when needed. The last two both use {@link TwoNodeCrossingCounter} which
 * calculates two entries i,j and j,i in the crossing matrix.
 *
 * @author alan
 *
 */
public abstract class AbstractGreedySwitchProcessor implements ILayoutProcessor {

    private LNode[][] bestNodeOrder;

    private boolean considerAllCrossings;

    private int crossingsInGraph;

    private LNode[][] currentNodeOrder;

    private int[][] eastNodeDegrees;

    private int[][] nodePositions;

    private TreeMap<LPort, Integer> portIndices;

    private int[][] westNodeDegrees;

    public AbstractGreedySwitchProcessor(final boolean considerAllCrossings) {
        this.considerAllCrossings = considerAllCrossings;
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
     * @param layeredGraph
     */
    private void layerSweepConsiderThreeLayers(final int layerCount, final LGraph layeredGraph) {
        boolean improved = true;
        while (improved) {
            improved = false;
            improved |= switchInLayer(false, 1);
            for (int leftFixedLayerIndex = 0; leftFixedLayerIndex < layerCount - 1; leftFixedLayerIndex++) {
                improved |= switchInLayer(true, leftFixedLayerIndex);
            }
            improved |= switchInLayer(true, layerCount - 2);
        }
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
     * @param layeredGraph
     */
    private void layerSweepConsiderTwoLayers(final int layerCount, final LGraph layeredGraph) {
        crossingsInGraph = getAmountOfCrossings(currentNodeOrder, layeredGraph);

        boolean forward = true;
        int tempCrossingsInGraph = Integer.MAX_VALUE;

        while (tempCrossingsInGraph > crossingsInGraph && crossingsInGraph > 0) {
            tempCrossingsInGraph = crossingsInGraph;
            copySweep(currentNodeOrder, bestNodeOrder);

            for (int fixedLayerIndex = 0; fixedLayerIndex < layerCount - 1; fixedLayerIndex++) {
                switchInLayer(forward, fixedLayerIndex);
            }

            forward = !forward;

            for (int fixedLayerIndex = layerCount - 1; fixedLayerIndex > 0; fixedLayerIndex--) {
                switchInLayer(forward, fixedLayerIndex);
            }

            setNewGraph(currentNodeOrder, layeredGraph); // TODOALAN: BAD!!
            crossingsInGraph = getAmountOfCrossings(currentNodeOrder, layeredGraph);
            forward = !forward;
        }
        // setNewGraph(currentNodeOrder, layeredGraph);
        setNewGraph(bestNodeOrder, layeredGraph);
    }

    /**
     * Copy the content of the source node array to the target node array.
     *
     * @param source
     *            a layered graph
     * @param dest
     *            a node array to copy the graph into
     */
    private void copySweep(final LNode[][] source, final LNode[][] dest) {
        for (int i = 0; i < dest.length; i++) {
            for (int j = 0; j < dest[i].length; j++) {
                dest[i][j] = source[i][j];
            }
        }
    }

    private void initialize(final LGraph layeredGraph, final int layerCount) {
        bestNodeOrder = new LNode[layerCount][];
        currentNodeOrder = new LNode[layerCount][];
        eastNodeDegrees = new int[layerCount][];
        westNodeDegrees = new int[layerCount][];
        nodePositions = new int[layerCount][];

        ListIterator<Layer> layerIter = layeredGraph.getLayers().listIterator();
        while (layerIter.hasNext()) {
            Layer layer = layerIter.next();

            int layerIndex = layerIter.previousIndex();
            int layerNodeCount = layer.getNodes().size();
            // Empty layers are not allowed!
            assert layerNodeCount > 0;

            // Initialize this layer's node arrays in the different sweeps
            bestNodeOrder[layerIndex] = new LNode[layerNodeCount];
            currentNodeOrder[layerIndex] = new LNode[layerNodeCount];

            eastNodeDegrees[layerIndex] = new int[layerNodeCount];
            westNodeDegrees[layerIndex] = new int[layerNodeCount];

            getNodePositions()[layerIndex] = new int[layerNodeCount];

            int nodeId = 0;

            ListIterator<LNode> nodeIter = layer.getNodes().listIterator();
            while (nodeIter.hasNext()) {
                LNode node = nodeIter.next();

                // Create node group and register layout unit
                currentNodeOrder[layerIndex][nodeIter.previousIndex()] = node;
                bestNodeOrder[layerIndex][nodeIter.previousIndex()] = node;

                // Set node ids and positions
                getNodePositions()[layerIndex][nodeIter.previousIndex()] = nodeId;
                node.id = nodeId;
                nodeId++;

                // Set port Ids for each side of the node
                int easternPortId = 0;
                for (LPort lPort : node.getPorts(PortSide.EAST)) {
                    LPort port = lPort;
                    port.id = easternPortId;
                    easternPortId += port.getDegree();
                }
                eastNodeDegrees[layerIndex][node.id] = easternPortId;
                int westernPortId = 0;
                for (LPort lPort : node.getPorts(PortSide.WEST)) {
                    LPort port = lPort;
                    port.id = westernPortId;
                    westernPortId += port.getDegree();
                }
                westNodeDegrees[layerIndex][node.id] = westernPortId; // TODOALAN explain problem
            }
        }
    }

    private void setNewGraph(final LNode[][] sweep, final LGraph layeredGraph) {
        ListIterator<Layer> layerIter = layeredGraph.getLayers().listIterator();
        while (layerIter.hasNext()) {
            Layer layer = layerIter.next();
            LNode[] nodes = sweep[layerIter.previousIndex()];
            ListIterator<LNode> nodeIter = layer.getNodes().listIterator();
            while (nodeIter.hasNext()) {
                nodeIter.next();
                nodeIter.set(nodes[nodeIter.previousIndex()]);
            }
        }
    }

    private boolean switchInLayer(final boolean fixedLayerLeftOfFreeLayer, final int fixedLayerIndex) {
        int freeLayerIndex = fixedLayerLeftOfFreeLayer ? fixedLayerIndex + 1 : fixedLayerIndex - 1;
        LNode[] freeLayer = currentNodeOrder[freeLayerIndex];
    
        boolean improved = false;
        boolean stop = false;
        while (!stop) {
            stop = true;
            for (int upperNodeIndex = 0; upperNodeIndex < freeLayer.length - 1; upperNodeIndex++) {
                int lowerNodeIndex = upperNodeIndex + 1;
                LNode upperNode = freeLayer[upperNodeIndex];
                LNode lowerNode = freeLayer[lowerNodeIndex];
    
                List<LNode> constraints =
                        upperNode.getProperty(InternalProperties.IN_LAYER_SUCCESSOR_CONSTRAINTS);
                boolean noSuccessorConstraint =
                        constraints == null || constraints.size() == 0
                                || !constraints.contains(lowerNode);
    
                // If upperNode and lowerNode are part of a layout unit, then the layout units must
                // be equal for a switch to be allowed.
                LNode upperLayoutUnit =
                        upperNode.getProperty(InternalProperties.IN_LAYER_LAYOUT_UNIT);
                LNode lowerLayoutUnit =
                        lowerNode.getProperty(InternalProperties.IN_LAYER_LAYOUT_UNIT);
                if (upperLayoutUnit != null && lowerLayoutUnit != null) {
                    noSuccessorConstraint &= upperLayoutUnit == lowerLayoutUnit;
                }
    
                if (noSuccessorConstraint) {
                    boolean switchReducesCrossings =
                            switchReducesCrossings(currentNodeOrder, freeLayerIndex,
                                    fixedLayerIndex, upperNodeIndex, lowerNodeIndex,
                                    considerAllCrossings);
    
                    improved |= switchReducesCrossings;
    
                    if (switchReducesCrossings) {
                        exchangeNodes(upperNodeIndex, lowerNodeIndex, freeLayer, freeLayerIndex);
                        stop = false;
                    }
                }
            }
        }
        return improved;
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

        int positionNodeOne = nodePositions[freeLayerIndex][layer[indexOne].id];
        nodePositions[freeLayerIndex][layer[indexOne].id] =
                nodePositions[freeLayerIndex][layer[indexTwo].id];
        nodePositions[freeLayerIndex][layer[indexTwo].id] = positionNodeOne;

        LNode temp = layer[indexTwo];
        layer[indexTwo] = layer[indexOne];
        layer[indexOne] = temp;
    }

    /**
     * Calculates the amount of crossings in the graph.
     * 
     * @param currentOrder
     *            The current ordering of nodes in the graph.
     * @param layeredGraph
     *            The layered graph.
     * @return the amount of crossings in the graph.
     */
    protected abstract int getAmountOfCrossings(LNode[][] currentOrder, LGraph layeredGraph);

    /**
     * Returns whether or not a switch of upperNodeIndex and lowerNodeIndex would reduce the amount
     * of crossings.
     *
     * @param currentGraph
     *            Currently calculated node order.
     * @param freeLayerIndex
     *            Index of the layer which is currently being reordered.
     * @param fixedLayerIndex
     *            Index of the layer whose node order is fixed.
     * @param upperNodeIndex
     *            Upper node.
     * @param lowerNodeIndex
     *            Lower node.
     * @param calculateOnBothSides
     *            Defines if crossings caused on both sides of the free layer should be considered.
     * @return true if a switch would reduce crossings.
     */
    protected abstract boolean switchReducesCrossings(final LNode[][] currentGraph,
            final int freeLayerIndex, final int fixedLayerIndex, final int upperNodeIndex,
            final int lowerNodeIndex, boolean calculateOnBothSides);

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
    public TreeMap<LPort, Integer> getPortIndices() {
        return portIndices;
    }

    /**
     * @return the westNodeDegrees
     */
    public int[][] getWestNodeDegrees() {
        return westNodeDegrees;
    }

}
