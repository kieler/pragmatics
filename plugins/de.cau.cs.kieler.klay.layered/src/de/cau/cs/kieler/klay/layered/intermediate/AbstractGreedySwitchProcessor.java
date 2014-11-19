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
     * Port position array used for counting the number of edge crossings.
     */
    private int[] portPos;
    /**
     * The number of in-layer edges for each layer, including virtual connections to north/south
     * dummies.
     */
    private int[] inLayerEdgeCount;
    /**
     * Whether the layers contain north / south port dummies or not.
     */
    private boolean[] northSouthPorts;
    /**
     * Layout units represented by a single node.
     */
    private final Multimap<LNode, LNode> layoutUnits = HashMultimap.create();

    /**
     * Complete node order of the current layer sweep.
     */
    private NodeGroup[][] curSweep;
    /**
     * Complete node order of the best layer sweep.
     */
    private NodeGroup[][] bestSweep;
    /**
     * Complete node order of the previous layer sweep.
     */
    private NodeGroup[][] prevSweep;

    /**
     * Returns the current sweep.
     * 
     * @return
     */
    protected NodeGroup[][] getCurSweep() {
        return curSweep;
    }

    /**
     * Returns the best sweep.
     * 
     * @return
     */
    protected NodeGroup[][] getBestSweep() {
        return bestSweep;
    }

    /**
     * returns the previous sweep.
     * 
     * @return
     */
    protected NodeGroup[][] getPrevSweep() {
        return prevSweep;
    }

    /**
     * {@inheritDoc}
     */
    public void process(final LGraph layeredGraph, final IKielerProgressMonitor progressMonitor) {
        int layerCount = layeredGraph.getLayers().size();
        if (layerCount < 2) {
            // progressMonitor.done();
            return;
        }

        initialize(layeredGraph);//TodoAlan Think about this
        // -- delme TODOAlan
        int totalCrossingsBefore = countAllCrossings(layerCount);
        // --delme

        boolean forward = true;
        int sweepCount = 0;
        while (sweepCount < 2) { // TODOAlan: Stop depending on improvement and take previous or current sweep.
            for (int layerIndex = forward ? 0 : layerCount - 1; forward ? layerIndex < layerCount - 1
                    : layerIndex > 0; layerIndex = forward ? layerIndex + 1 : layerIndex - 1) {
                switchInLayer(forward, layerIndex);
            }
            forward = !forward;
            sweepCount++;
        }

        // -- delme TODOAlan
        setNewGraph(layeredGraph);
        initialize(layeredGraph);
        int totalCrossingsAfter = countAllCrossings(layerCount);
        float ratioSaved =
                (float) (totalCrossingsBefore - totalCrossingsAfter) / (float) totalCrossingsBefore;
        int i = 0;
        // -- delme
    }

    /**
     * Performs switches in one layer depending on implementation.
     * 
     * @param forward
     * @param layerIndex
     */
    protected abstract void switchInLayer(final boolean forward, final int layerIndex);

    private void setNewGraph(final LGraph layeredGraph) {
        ListIterator<Layer> layerIter = layeredGraph.getLayers().listIterator();
        while (layerIter.hasNext()) {
            Layer layer = layerIter.next();
            NodeGroup[] nodes = curSweep[layerIter.previousIndex()];
            ListIterator<LNode> nodeIter = layer.getNodes().listIterator();
            while (nodeIter.hasNext()) {
                nodeIter.next();
                nodeIter.set(nodes[nodeIter.previousIndex()].getNode());
            }
        }
    }

    protected void exchangeNodes(final int indexOne, final int indexTwo, final NodeGroup[] layer) {
        NodeGroup temp = layer[indexTwo];
        layer[indexTwo] = layer[indexOne];
        layer[indexOne] = temp;
    }

    private int countAllCrossings(final int layerCount) {
        int totalCrossingsAfter = 0;
        for (int layerIndex = 0; layerIndex < layerCount - 1; layerIndex++) {
            NodeGroup[] fixedLayer = curSweep[layerIndex];
            NodeGroup[] freeLayer = curSweep[layerIndex + 1];
            totalCrossingsAfter += countCrossings(fixedLayer, freeLayer);
        }
        return totalCrossingsAfter;
    }

    /**
     * Initialize all data for the layer sweep crossing minimizer.
     * 
     * @param layeredGraph
     *            a layered graph
     */
    void initialize(final LGraph layeredGraph) {
        int layerCount = layeredGraph.getLayers().size();

        // Remember the best, current and previous sweep; they basically save the node oder
        // per layer for the different sweeps of the algorithm
        bestSweep = new NodeGroup[layerCount][];
        curSweep = new NodeGroup[layerCount][];
        prevSweep = new NodeGroup[layerCount][];

        inLayerEdgeCount = new int[layerCount];
        northSouthPorts = new boolean[layerCount];

        int nodeCount = 0;
        int portCount = 0;

        // Iterate through the layers, initializing port and node IDs, collecting
        // the nodes into the current sweep and building the layout unit map
        ListIterator<Layer> layerIter = layeredGraph.getLayers().listIterator();
        while (layerIter.hasNext()) {
            Layer layer = layerIter.next();

            int layerIndex = layerIter.previousIndex();
            int layerNodeCount = layer.getNodes().size();
            // Empty layers are not allowed!
            assert layerNodeCount > 0;

            // Initialize this layer's node arrays in the different sweeps
            bestSweep[layerIndex] = new NodeGroup[layerNodeCount];
            prevSweep[layerIndex] = new NodeGroup[layerNodeCount];
            curSweep[layerIndex] = new NodeGroup[layerNodeCount];
            inLayerEdgeCount[layerIndex] = 0;
            northSouthPorts[layerIndex] = false;

            ListIterator<LNode> nodeIter = layer.getNodes().listIterator();
            while (nodeIter.hasNext()) {
                LNode node = nodeIter.next();

                // Create node group and register layout unit
                NodeGroup nodeGroup = new NodeGroup(node);
                curSweep[layerIndex][nodeIter.previousIndex()] = nodeGroup;
                node.id = nodeCount++;
                node.setProperty(InternalProperties.NODE_GROUP, nodeGroup);
                LNode layoutUnit = node.getProperty(InternalProperties.IN_LAYER_LAYOUT_UNIT);
                if (layoutUnit != null) {
                    layoutUnits.put(layoutUnit, node);
                }

                // Count in-layer edges
                for (LPort port : node.getPorts()) {
                    port.id = portCount++;
                    for (LEdge edge : port.getOutgoingEdges()) {
                        if (edge.getTarget().getNode().getLayer() == layer) {
                            inLayerEdgeCount[layerIndex]++;
                        }
                    }
                }

                // Count north/south dummy nodes
                if (node.getProperty(InternalProperties.NODE_TYPE) == NodeType.NORTH_SOUTH_PORT) {
                    inLayerEdgeCount[layerIndex]++;
                    northSouthPorts[layerIndex] = true;
                }
            }
        }

        // Initialize the port positions and ranks arrays
        portPos = new int[portCount];
    }

    int countCrossings(final NodeGroup[] leftLayer, final NodeGroup[] rightLayer) {
        // Assign index values to the ports of the right layer
        int targetCount = 0, edgeCount = 0;
        Layer leftLayerRef = leftLayer[0].getNode().getLayer();
        Layer rightLayerRef = rightLayer[0].getNode().getLayer();
        for (NodeGroup nodeGroup : rightLayer) {
            LNode node = nodeGroup.getNode();
            assert node.getLayer() == rightLayerRef;
            if (node.getProperty(LayoutOptions.PORT_CONSTRAINTS).isOrderFixed()) {
                // Determine how many input ports there are on the north side
                // (note that the standard port order is north - east - south - west)
                int northInputPorts = 0;
                for (LPort port : node.getPorts()) {
                    if (port.getSide() == PortSide.NORTH) {
                        for (LEdge edge : port.getIncomingEdges()) {
                            if (edge.getSource().getNode().getLayer() == leftLayerRef) {
                                northInputPorts++;
                                break;
                            }
                        }
                    } else {
                        break;
                    }
                }
                // Assign index values in the order north - west - south - east
                int otherInputPorts = 0;
                ListIterator<LPort> portIter = node.getPorts().listIterator(node.getPorts().size());
                while (portIter.hasPrevious()) {
                    LPort port = portIter.previous();
                    int portEdges = 0;
                    for (LEdge edge : port.getIncomingEdges()) {
                        if (edge.getSource().getNode().getLayer() == leftLayerRef) {
                            portEdges++;
                        }
                    }
                    if (portEdges > 0) {
                        if (port.getSide() == PortSide.NORTH) {
                            portPos[port.id] = targetCount;
                            targetCount++;
                        } else {
                            portPos[port.id] = targetCount + northInputPorts + otherInputPorts;
                            otherInputPorts++;
                        }
                        edgeCount += portEdges;
                    }
                }
                targetCount += otherInputPorts;

            } else {
                // All ports are assigned the same index value, since their order does not matter
                int nodeEdges = 0;
                for (LPort port : node.getPorts()) {
                    for (LEdge edge : port.getIncomingEdges()) {
                        if (edge.getSource().getNode().getLayer() == leftLayerRef) {
                            nodeEdges++;
                        }
                    }
                    portPos[port.id] = targetCount;
                }
                if (nodeEdges > 0) {
                    targetCount++;
                    edgeCount += nodeEdges;
                }
            }
        }

        // Determine the sequence of edge target positions sorted by source and target index
        int[] southSequence = new int[edgeCount];
        int i = 0;
        for (NodeGroup nodeGroup : leftLayer) {
            LNode node = nodeGroup.getNode();
            assert node.getLayer() == leftLayerRef;
            if (node.getProperty(LayoutOptions.PORT_CONSTRAINTS).isOrderFixed()) {
                // Iterate output ports in their natural order, that is north - east - south - west
                for (LPort port : node.getPorts()) {
                    int start = i;
                    for (LEdge edge : port.getOutgoingEdges()) {
                        LPort target = edge.getTarget();
                        if (target.getNode().getLayer() == rightLayerRef) {
                            assert i < edgeCount;
                            // If the port has multiple output edges, sort them by target port index
                            insert(southSequence, start, i++, portPos[target.id]);
                        }
                    }
                }
            } else {
                // The order of output ports does not matter, so sort only by target port index
                int start = i;
                for (LPort port : node.getPorts()) {
                    for (LEdge edge : port.getOutgoingEdges()) {
                        LPort target = edge.getTarget();
                        if (target.getNode().getLayer() == rightLayerRef) {
                            assert i < edgeCount;
                            insert(southSequence, start, i++, portPos[target.id]);
                        }
                    }
                }
            }
        }

        // Build the accumulator tree
        int firstIndex = 1;
        while (firstIndex < targetCount) {
            firstIndex *= 2;
        }
        int treeSize = 2 * firstIndex - 1;
        firstIndex -= 1;
        int[] tree = new int[treeSize];

        // Count the crossings
        int crossCount = 0;
        for (int k = 0; k < edgeCount; k++) {
            int index = southSequence[k] + firstIndex;
            tree[index]++;
            while (index > 0) {
                if (index % 2 > 0) {
                    crossCount += tree[index + 1];
                }
                index = (index - 1) / 2;
                tree[index]++;
            }
        }

        return crossCount;
    }

    /**
     * Insert a number into a sorted range of an array.
     * 
     * @param array
     *            an integer array
     * @param start
     *            the start index of the search range (inclusive)
     * @param end
     *            the end index of the search range (exclusive)
     * @param n
     *            the number to insert
     */
    private static void insert(final int[] array, final int start, final int end, final int n) {
        int insx = binarySearch(array, start, end, n);
        if (insx < 0) {
            insx = -insx - 1;
        }
        for (int j = end - 1; j >= insx; j--) {
            array[j + 1] = array[j];
        }
        array[insx] = n;
    }

    /**
     * Searches a range of the specified array of ints for the specified value using the binary
     * search algorithm. The range must be sorted prior to making this call.
     * 
     * @param a
     *            the array to be searched
     * @param fromIndex
     *            the index of the first element (inclusive) to be searched
     * @param toIndex
     *            the index of the last element (exclusive) to be searched
     * @param key
     *            the value to be searched for
     * @return index of the search key
     */
    private static int binarySearch(final int[] a, final int fromIndex, final int toIndex,
            final int key) {
        int low = fromIndex;
        int high = toIndex - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            int midVal = a[mid];

            if (midVal < key) {
                low = mid + 1;
            } else if (midVal > key) {
                high = mid - 1;
            } else {
                return mid; // key found
            }
        }
        return -(low + 1); // key not found
    }

}
