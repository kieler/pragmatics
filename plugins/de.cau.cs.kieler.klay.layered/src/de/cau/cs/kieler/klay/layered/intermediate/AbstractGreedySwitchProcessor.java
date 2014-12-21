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

import java.util.Iterator;
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
import de.cau.cs.kieler.klay.layered.p3order.NodeGroup;
import de.cau.cs.kieler.klay.layered.properties.InternalProperties;

public abstract class AbstractGreedySwitchProcessor implements ILayoutProcessor {

    /**
     * Complete node order of the current layer sweep.
     */
    private NodeGroup[][] curSweep;
    /**
     * Complete node order of the best layer sweep.
     */
    private NodeGroup[][] bestSweep;

    private int crossingsInGraph;

    private LGraph layeredGraph;

    private int[][] eastNodeDegrees;

    private int[][] westNodeDegrees;

    private int[][] nodePositions;

    private TreeMap<LPort, Integer> portIndices;

    /**
     * @return the layeredGraph
     */
    public LGraph getLayeredGraph() {
        return layeredGraph;
    }

    /**
     * {@inheritDoc}
     */
    public void process(final LGraph layeredGraphIn, final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Begin Greedy Switch intermediate processor", 1);
        this.layeredGraph = layeredGraphIn;
        int layerCount = layeredGraph.getLayers().size();
        if (layerCount < 2) {
            progressMonitor.done();
            return;
        }

        initialize(layeredGraph, layerCount);
        crossingsInGraph = getAmountOfCrossings(curSweep); // TODOALAN depending on Method

        boolean forward = true;
        int tempCrossingsInGraph = Integer.MAX_VALUE;

        while (tempCrossingsInGraph > crossingsInGraph && crossingsInGraph > 0) {
            tempCrossingsInGraph = crossingsInGraph;
            copySweep(curSweep, bestSweep);

            // Always sweep forwards and backwards TODOALAN depending on METHOD
            for (int fixedLayerIndex = 0; fixedLayerIndex < layerCount - 1; fixedLayerIndex++) {
                switchInLayer(forward, fixedLayerIndex);
            }

            forward = !forward;

            for (int fixedLayerIndex = layerCount - 1; fixedLayerIndex > 0; fixedLayerIndex--) {
                switchInLayer(forward, fixedLayerIndex);
            }

            setNewGraph(curSweep); // TODOALAN: BAD!!
            crossingsInGraph = getAmountOfCrossings(curSweep); // TODOALAN depending on Method
            forward = !forward;
        }
        // setNewGraph(curSweep);
        setNewGraph(bestSweep);
        progressMonitor.done();
    }

    /**
     * @param layeredGraph
     * @param layerCount
     */
    public void initialize(final LGraph layeredGraph, final int layerCount) {
        bestSweep = new NodeGroup[layerCount][];
        curSweep = new NodeGroup[layerCount][];
        eastNodeDegrees = new int[layerCount][];
        westNodeDegrees = new int[layerCount][];
        setNodePositions(new int[layerCount][]);

        ListIterator<Layer> layerIter = layeredGraph.getLayers().listIterator();
        while (layerIter.hasNext()) {
            Layer layer = layerIter.next();

            int layerIndex = layerIter.previousIndex();
            int layerNodeCount = layer.getNodes().size();
            // Empty layers are not allowed!
            assert layerNodeCount > 0;

            // Initialize this layer's node arrays in the different sweeps
            bestSweep[layerIndex] = new NodeGroup[layerNodeCount];
            curSweep[layerIndex] = new NodeGroup[layerNodeCount];

            eastNodeDegrees[layerIndex] = new int[layerNodeCount];
            westNodeDegrees[layerIndex] = new int[layerNodeCount];

            getNodePositions()[layerIndex] = new int[layerNodeCount];

            int nodeId = 0;

            ListIterator<LNode> nodeIter = layer.getNodes().listIterator();
            while (nodeIter.hasNext()) {
                LNode node = nodeIter.next();

                // Create node group and register layout unit
                NodeGroup nodeGroup = new NodeGroup(node);
                curSweep[layerIndex][nodeIter.previousIndex()] = nodeGroup;
                bestSweep[layerIndex][nodeIter.previousIndex()] = nodeGroup;
                node.setProperty(InternalProperties.NODE_GROUP, nodeGroup);

                // Set node ids and positions
                getNodePositions()[layerIndex][nodeIter.previousIndex()] = nodeId;
                node.id = nodeId;
                nodeId++;

                // Set port Ids for each side of the node
                int easternPortId = 0;
                for (Iterator<LPort> iterator = node.getPorts(PortSide.EAST).iterator(); iterator
                        .hasNext();) {
                    LPort port = (LPort) iterator.next();
                    port.id = easternPortId;
                    easternPortId += port.getDegree();
                }
                eastNodeDegrees[layerIndex][node.id] = easternPortId;
                int westernPortId = 0;
                for (Iterator<LPort> iterator = node.getPorts(PortSide.WEST).iterator(); iterator
                        .hasNext();) {
                    LPort port = (LPort) iterator.next();
                    port.id = westernPortId;
                    westernPortId += port.getDegree();
                }
                westNodeDegrees[layerIndex][node.id] = westernPortId; // TODOALAN explain problem
            }
        }
    }

    private void switchInLayer(final boolean forward, final int layerIndex) {
        int freeLayerIndex = forward ? layerIndex + 1 : layerIndex - 1;
        NodeGroup[] fixedLayer = curSweep[layerIndex];
        NodeGroup[] freeLayer = curSweep[freeLayerIndex];

        boolean stop = false;
        boolean firstRun = true;
        while (!stop) {
            stop = true;
            for (int i = 0; i < freeLayer.length - 1; i++) {

                LNode currentNode = freeLayer[i].getNode();
                LNode nextNode = freeLayer[i + 1].getNode();

                List<LNode> constraints =
                        currentNode.getProperty(InternalProperties.IN_LAYER_SUCCESSOR_CONSTRAINTS);
                boolean noSuccessorConstraint =
                        constraints == null || constraints.size() == 0
                                || !constraints.contains(nextNode); // TODOALAN constraints can
                                                                    // be a list

                if (noSuccessorConstraint) {
                    boolean switchReducesCrossings =
                            checkIfSwitchReducesCrossings(i, i + 1, forward, fixedLayer, freeLayer,
                                    firstRun, freeLayerIndex);

                    if (switchReducesCrossings) {
                        exchangeNodes(i, i + 1, freeLayer, freeLayerIndex);
                        stop = false;
                    }
                }
                firstRun = false;
            }
        }
    }

    abstract boolean checkIfSwitchReducesCrossings(final int currentNodeIndex,
            final int nextNodeIndex, final boolean forward, final NodeGroup[] fixedLayer,
            final NodeGroup[] freeLayer, boolean firstRun, int freeLayerIndex);

    abstract int getAmountOfCrossings(NodeGroup[][] currentOrder);

    /**
     * Copy the content of the source node array to the target node array.
     * 
     * @param source
     *            a layered graph
     * @param dest
     *            a node array to copy the graph into
     */
    private static void copySweep(final NodeGroup[][] source, final NodeGroup[][] dest) {
        for (int i = 0; i < dest.length; i++) {
            for (int j = 0; j < dest[i].length; j++) {
                dest[i][j] = source[i][j];
            }
        }
    }

    private void setNewGraph(NodeGroup[][] sweep) {
        ListIterator<Layer> layerIter = layeredGraph.getLayers().listIterator();
        while (layerIter.hasNext()) {
            Layer layer = layerIter.next();
            NodeGroup[] nodes = sweep[layerIter.previousIndex()];
            ListIterator<LNode> nodeIter = layer.getNodes().listIterator();
            while (nodeIter.hasNext()) {
                nodeIter.next();
                nodeIter.set(nodes[nodeIter.previousIndex()].getNode());
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
     *            The layer as NodeGroup array
     */
    protected void exchangeNodes(final int indexOne, final int indexTwo, final NodeGroup[] layer,
            int freeLayerIndex) {
        System.out.println("Switched node " + layer[indexOne] + " index: " + indexOne
                + " and node: " + layer[indexTwo] + " index: " + indexTwo);

        int positionNodeOne = nodePositions[freeLayerIndex][layer[indexOne].getNode().id];
        nodePositions[freeLayerIndex][layer[indexOne].getNode().id] =
                nodePositions[freeLayerIndex][layer[indexTwo].getNode().id];
        nodePositions[freeLayerIndex][layer[indexTwo].getNode().id] = positionNodeOne;
        
        NodeGroup temp = layer[indexTwo];
        layer[indexTwo] = layer[indexOne];
        layer[indexOne] = temp;
    }

    /**
     * @return the eastNodeDegrees
     */
    public int[][] getEastNodeDegrees() {
        return eastNodeDegrees;
    }

    /**
     * @return the westNodeDegrees
     */
    public int[][] getWestNodeDegrees() {
        return westNodeDegrees;
    }

    /**
     * @return
     */
    public TreeMap<LPort, Integer> getPortIndices() {
        return portIndices;
    }

    /**
     * @return the nodePositions
     */
    public int[][] getNodePositions() {
        return nodePositions;
    }

    /**
     * @param nodePositions
     *            the nodePositions to set
     */
    public void setNodePositions(int[][] nodePositions) {
        this.nodePositions = nodePositions;
    }

}
