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

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.p3order.NodeGroup;
import de.cau.cs.kieler.klay.layered.properties.InternalProperties;
import de.cau.cs.kieler.klay.layered.properties.NodeType;

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
        this.layeredGraph = layeredGraphIn;
        int layerCount = layeredGraph.getLayers().size();
        if (layerCount < 2) {
            // progressMonitor.done();
            return;
        }

        initialize(layeredGraph, layerCount);
        CrossingCounter crossCounter = new CrossingCounter(layeredGraph);
        crossingsInGraph = getAmountOfCrossings(curSweep);
        int crossingsBeforeReduction = crossingsInGraph;// delme

        boolean forward = true;
        int tempCrossingsInGraph = Integer.MAX_VALUE;

//        for (int i = 0; i < 2; i++) {
//            // tempCrossingsInGraph = crossingsInGraph;
//            for (int layerIndex = forward ? 0 : layerCount - 1; forward ? layerIndex < layerCount - 1
//                    : layerIndex > 0; layerIndex = forward ? layerIndex + 1 : layerIndex - 1) {
//                switchInLayer(forward, layerIndex);
//            }
//            forward = !forward;
//            setNewGraph(curSweep);
//            crossingsInGraph = getAmountOfCrossings(curSweep);
//        }
//        copySweep(curSweep, bestSweep);
        long startTime = System.nanoTime();
        while (tempCrossingsInGraph > crossingsInGraph && crossingsInGraph > 0) {
            tempCrossingsInGraph = crossingsInGraph;
            copySweep(curSweep, bestSweep);
            //always sweep backwards and forwards
            for (int i = 0; i < 2; i++) {
                for (int layerIndex = forward ? 0 : layerCount - 1; forward ? layerIndex < layerCount - 1
                        : layerIndex > 0; layerIndex = forward ? layerIndex + 1 : layerIndex - 1) {
                    switchInLayer(forward, layerIndex);
                }
                forward = !forward;
            }
            setNewGraph(curSweep); // TODOALAN: BAD!!
            crossingsInGraph = getAmountOfCrossings(curSweep);
        }
        System.out.println("This operation took: " + (System.nanoTime() - startTime) + " ns.");
        setNewGraph(bestSweep);
        // -- delme TODOAlan
        int totalCrossingsAfter = crossCounter.countAllCrossingsInGraph(curSweep);
        float ratioSaved =
                (float) (crossingsBeforeReduction - totalCrossingsAfter)
                        / (float) crossingsBeforeReduction;
        int i = 0;
        // -- delme
    }

    /**
     * @param layeredGraph
     * @param layerCount
     */
    private void initialize(final LGraph layeredGraph, final int layerCount) {
        bestSweep = new NodeGroup[layerCount][];
        curSweep = new NodeGroup[layerCount][];

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

            ListIterator<LNode> nodeIter = layer.getNodes().listIterator();
            while (nodeIter.hasNext()) {
                LNode node = nodeIter.next();

                // Create node group and register layout unit
                NodeGroup nodeGroup = new NodeGroup(node);
                curSweep[layerIndex][nodeIter.previousIndex()] = nodeGroup;
                bestSweep[layerIndex][nodeIter.previousIndex()] = nodeGroup;
                node.setProperty(InternalProperties.NODE_GROUP, nodeGroup);

            }
        }
    }

    private void switchInLayer(final boolean forward, final int layerIndex) {
        int freeLayerIndex = forward ? layerIndex + 1 : layerIndex - 1;
        NodeGroup[] fixedLayer = curSweep[layerIndex];
        NodeGroup[] freeLayer = curSweep[freeLayerIndex];

        setIdsToIndicesInLayer(freeLayer);
        setIdsToIndicesInLayer(fixedLayer);

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
                                || !constraints.get(0).equals(nextNode);

                if (noSuccessorConstraint) {
                    boolean switchReducesCrossings =
                            checkIfSwitchReducesCrossings(i, i + 1, forward, fixedLayer, freeLayer,
                                    firstRun);

                    if (switchReducesCrossings) {
                        exchangeNodes(i, i + 1, freeLayer);
                        stop = false;
                    }
                }
                firstRun = false;
            }
        }
    }

    abstract boolean checkIfSwitchReducesCrossings(final int currentNodeIndex,
            final int nextNodeIndex, final boolean forward, final NodeGroup[] fixedLayer,
            final NodeGroup[] freeLayer, boolean firstRun);

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

    private void setIdsToIndicesInLayer(final NodeGroup[] freeLayer) {
        int id = 0;
        for (NodeGroup nodeGroup : freeLayer) {
            nodeGroup.getNode().id = id;
            id++;

            int portId = 0;
            for (LPort lPort : nodeGroup.getNode().getPorts()) {
                lPort.id = portId;
                portId++;
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
    protected void exchangeNodes(final int indexOne, final int indexTwo, final NodeGroup[] layer) {
        System.out.println("Switched node " + layer[indexOne] + " index: " + indexOne
                + " and node: " + layer[indexTwo] + " index: " + indexTwo);
        NodeGroup temp = layer[indexTwo];
        layer[indexTwo] = layer[indexOne];
        layer[indexOne] = temp;
    }

}
