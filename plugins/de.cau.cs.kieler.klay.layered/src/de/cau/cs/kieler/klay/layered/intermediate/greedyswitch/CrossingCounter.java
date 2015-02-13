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
package de.cau.cs.kieler.klay.layered.intermediate.greedyswitch;

import java.util.ListIterator;

import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.properties.InternalProperties;
import de.cau.cs.kieler.klay.layered.properties.NodeType;

/**
 * Counts the amount of crossings between two given layers or all layers in a graph. Counts
 * crossings between layers, worst-case crossings caused by in-layer edges and crossings caused by
 * north-south ports.
 *
 * @author alan
 *
 */
public class CrossingCounter {

    /**
     * Port position array used for counting the number of edge crossings.
     */
    private int[] portPos;
    private final LNode[][] layeredGraph;
    private int[][] amountOfPortsPerNode;
    private int portCount;

    /**
     * Constructs and initializes a cross counter. Initialization iterates through all ports.
     * TODO-alan: consider asking for switch
     * 
     * @param layeredGraph
     *            The layered graph
     */
    public CrossingCounter(final LNode[][] layeredGraph) {
        this.layeredGraph = layeredGraph;
        initialize(layeredGraph);
    }

    /**
     * Counts all crossings in a graph.
     * 
     * @return the number of crossings for the node order passed to the constructor.
     */
    public int countAllCrossingsInGraph() {
        return countAllCrossingsInGraphWithOrder(layeredGraph);
    }

    /**
     * Counts all crossings in a graph in the originalOrder.
     *
     * @param currentOrder
     *            The current order of the nodes.
     * @return the amount of crossings
     */
    public int countAllCrossingsInGraphWithOrder(final LNode[][] currentOrder) {
        initialize(currentOrder);
        int totalCrossings = 0;
        for (int layerIndex = 0; layerIndex < currentOrder.length; layerIndex++) {
            LNode[] fixedLayer = currentOrder[layerIndex];
            if (layerIndex < currentOrder.length - 1) {
                LNode[] freeLayer = currentOrder[layerIndex + 1];
                totalCrossings += countCrossingsBetweenLayersInOrder(fixedLayer, freeLayer);
            }
            totalCrossings += countNorthSouthPortCrossings(fixedLayer);
            totalCrossings += countInLayerEdgeCrossingsWithOrder(fixedLayer);
            // totalCrossings += oldInLayerEdgeCrossingCounter(fixedLayer);
        }
        return totalCrossings;
    }

    /**
     * Count crossings between the fixedLayer and the freeLayer.
     *
     * @param easternLayer
     *            The layer with which to count crossings.
     * @param westernLayer
     *            Count in-layer and north-south-port crossings in this layer.
     * @return Amount of crossings.
     */
    public int countCrossingsBetweenLayersInOrder(final LNode[] easternLayer,
            final LNode[] westernLayer) {
        if (isALayerEmpty(easternLayer, westernLayer)) {
            return 0;
        }
        return countCrossings(easternLayer, westernLayer);

    }

    private boolean isALayerEmpty(final LNode[] fixedLayer, final LNode[] freeLayer) {
        return fixedLayer == null || fixedLayer.length == 0 || freeLayer == null
                || freeLayer.length == 0;
    }

    private void initialize(final LNode[][] graph) {
        portCount = 0;
        amountOfPortsPerNode = new int[graph.length][];
        for (int i = 0; i < graph.length; i++) {
            LNode[] layer = graph[i];
            amountOfPortsPerNode[i] = new int[layer.length];
            int nodeId = 0;
            for (int j = 0; j < layer.length; j++) {
                LNode node = layer[j];
                node.id = nodeId++;
                int amountOfPorts = 0;
                for (LPort port : node.getPorts()) {
                    port.id = portCount++;
                    amountOfPorts++;
                }
                amountOfPortsPerNode[i][j] = amountOfPorts;
            }
        }

        // Initialize the port positions and ranks arrays
        portPos = new int[portCount];
    }

    /**
     * Calculate the number of crossings between the two given layers. Taken from
     * <ul>
     * <li>W. Barth , M. Juenger, P. Mutzel, Simple and efficient bilayer cross counting, In
     * <i>Graph Drawing</i>, volume 2528 of LNCS, pp. 331-360. Springer, 2002.</li>
     * </ul>
     *
     * @param leftLayer
     *            the left layer
     * @param rightLayer
     *            the right layer
     * @return the number of edge crossings
     */
    private int countCrossings(final LNode[] leftLayer, final LNode[] rightLayer) {
        // Assign index values to the ports of the right layer
        int targetCount = 0, edgeCount = 0;
        Layer leftLayerRef = leftLayer[0].getLayer();
        Layer rightLayerRef = rightLayer[0].getLayer();
        for (LNode node : rightLayer) {
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
        for (LNode node : leftLayer) {
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
     * Calculates the amount of In-Layer edges for the given layer in the order, with which
     * CrossingCounter was initialized with.
     * 
     * @param layerIndex
     *            index of Layer to count in.
     * @return north South Port crossing number
     */
    public int countInLayerEdgeCrossings(final int layerIndex) {
        return countInLayerEdgeCrossingsWithOrder(layeredGraph[layerIndex]);
    }

    /**
     * Calculates the worst case for the number of crossings caused by in-layer edges in the given
     * layer and by north/south port dummies that are later connected to their corresponding regular
     * nodes. The actual number of crossings may be lower.
     *
     * @param layer
     *            the layer whose in-layer crossings and north/south dummy crossings to estimate.
     * @return the worst possible number of crossings
     */
    public int countInLayerEdgeCrossingsWithOrder(final LNode[] layer) {
        return new InLayerEdgeAllCrossingCounter(layer).countAllCrossings();
    }

    /**
     * Calculates the amount of North South Port crossings for the given layer in the order, with
     * which CrossingCounter was initialized with.
     * 
     * @param layerIndex
     *            index of Layer to count in.
     * @return north South Port crossing number
     */
    public int countNorthSouthPortCrossings(final int layerIndex) {
        return countNorthSouthPortCrossings(layeredGraph[layerIndex]);
    }

    /**
     * Counts the number of edge crossings caused by the way north / south port dummies are ordered.
     *
     * @param layer
     *            the layer whose north / south port related crossings to count.
     * @return the number of crossings caused by edges connected to northern or southern ports.
     *         TODO-alan consider moving to class TODO-alan This method is too long.
     */
    public int countNorthSouthPortCrossings(final LNode[] layer) {
        int crossings = 0;
        boolean northernSide = true;
        LNode recentNormalNode = null;

        int amountOfLongEdgeDummies = 0;
        // Iterate through the layer's nodes
        for (int i = 0; i < layer.length; i++) {
            LNode node = layer[i];
            NodeType nodeType = node.getProperty(InternalProperties.NODE_TYPE);
            if (nodeType == NodeType.NORMAL) {
                // We possibly have a new recentNormalNode; we definitely change the side to the
                // normal
                // node's south
                recentNormalNode = node;
                northernSide = false;
                amountOfLongEdgeDummies = 0;
            } else if (nodeType == NodeType.LONG_EDGE) {
                if (!northernSide) {
                    amountOfLongEdgeDummies++;
                }
            } else if (nodeType == NodeType.NORTH_SOUTH_PORT) {
                crossings += amountOfLongEdgeDummies; // TODO-alan comment and Test
                // If we have a dummy that represents a self-loop, continue with the next one
                // (self-loops have no influence on the number of crossings anyway, regardless of
                // where
                // they are placed)
                if (node.getProperty(InternalProperties.ORIGIN) instanceof LEdge) {
                    continue;
                }

                // Check if the dummy node belongs to a new normal node
                LNode currentNormalNode = (LNode) node.getProperty(InternalProperties.ORIGIN);
                if (recentNormalNode != currentNormalNode) {
                    // A have a new normal node and are on its northern side
                    recentNormalNode = currentNormalNode;
                    northernSide = true;
                    amountOfLongEdgeDummies = 0;
                }

                // Check if the current normal node has a fixed port order; if not, we can ignore it
                if (!currentNormalNode.getProperty(LayoutOptions.PORT_CONSTRAINTS).isOrderFixed()) {
                    continue;
                }

                // Find the up to two ports represented by this dummy node
                LPort nodeInputPort = null;
                LPort nodeOutputPort = null;
                for (LPort port : node.getPorts()) {
                    // We assume here that a port of a north / south dummy has either incoming or
                    // outgoing edges, but not both. So far, that's the case.
                    assert port.getIncomingEdges().isEmpty() ^ port.getOutgoingEdges().isEmpty() : port
                            .getIncomingEdges().size()
                            + " incoming edges, "
                            + port.getOutgoingEdges().size() + " outgoing edges";

                    if (!port.getIncomingEdges().isEmpty()) {
                        nodeInputPort = (LPort) port.getProperty(InternalProperties.ORIGIN);
                    } else if (!port.getOutgoingEdges().isEmpty()) {
                        nodeOutputPort = (LPort) port.getProperty(InternalProperties.ORIGIN);
                    }
                }
                // Iterate over the next nodes until we find a north / south port dummy belonging to
                // a
                // new normal node or until we find our current normal node
                for (int j = i + 1; j < layer.length; j++) {
                    LNode node2 = layer[j];
                    NodeType node2Type = node2.getProperty(InternalProperties.NODE_TYPE);

                    if (node2Type == NodeType.NORMAL) {
                        // We can stop
                        break;
                    } else if (node2Type == NodeType.NORTH_SOUTH_PORT) {
                        // Check if the north / south port dummy still belongs to the same normal
                        // node
                        if (node2.getProperty(InternalProperties.ORIGIN) != currentNormalNode) {
                            // New normal node, we can stop
                            break;
                        }

                        // Find the up to two ports represented by this dummy node
                        LPort node2InputPort = null;
                        LPort node2OutputPort = null;
                        for (LPort port2 : node2.getPorts()) {
                            // We assume here that a port of a north / south dummy has either
                            // incoming or
                            // outgoing edges, but not both. So far, that's the case.
                            if (!port2.getIncomingEdges().isEmpty()) {
                                node2InputPort =
                                        (LPort) port2.getProperty(InternalProperties.ORIGIN);
                            } else if (!port2.getOutgoingEdges().isEmpty()) {
                                node2OutputPort =
                                        (LPort) port2.getProperty(InternalProperties.ORIGIN);
                            }
                        }

                        // How crossings are determined depends on which side of the normal node
                        // we're on
                        if (northernSide) {
                            boolean nodeInputPortCollision = false;
                            boolean nodeOutputPortCollision = false;

                            if (nodeOutputPort != null && node2InputPort != null
                                    && nodeOutputPort.id < node2InputPort.id) {
                                crossings++;
                                nodeOutputPortCollision = true;
                            }
                            if (nodeInputPort != null && node2OutputPort != null
                                    && nodeInputPort.id > node2OutputPort.id) {
                                crossings++;
                                nodeInputPortCollision = true;
                            }
                            if (nodeOutputPort != null && node2OutputPort != null
                                    && nodeOutputPort.id > node2OutputPort.id) {
                                crossings++;
                                nodeOutputPortCollision = true;
                            }
                            if (nodeInputPort != null && node2InputPort != null
                                    && nodeInputPort.id < node2InputPort.id) {
                                crossings++;
                                nodeInputPortCollision = true;
                            }

                            if (nodeInputPortCollision && nodeOutputPortCollision
                                    && nodeInputPort == nodeOutputPort) {
                                crossings--;
                            }
                        } else {
                            boolean node2InputPortCollision = false;
                            boolean node2OutputPortCollision = false;

                            if (nodeInputPort != null && node2OutputPort != null
                                    && nodeInputPort.id < node2OutputPort.id) {
                                crossings++;
                                node2OutputPortCollision = true;
                            }
                            if (nodeOutputPort != null && node2InputPort != null
                                    && nodeOutputPort.id > node2InputPort.id) {
                                crossings++;
                                node2InputPortCollision = true;
                            }
                            if (nodeInputPort != null && node2InputPort != null
                                    && nodeInputPort.id < node2InputPort.id) {
                                crossings++;
                                node2InputPortCollision = true;
                            }
                            if (nodeOutputPort != null && node2OutputPort != null
                                    && nodeOutputPort.id > node2OutputPort.id) {
                                crossings++;
                                node2OutputPortCollision = true;
                            }

                            if (node2InputPortCollision && node2OutputPortCollision
                                    && node2InputPort == node2OutputPort) {
                                crossings--;
                            }
                        }
                    } else if (node2Type == NodeType.LONG_EDGE) {
                        if (northernSide) {
                            crossings++;
                        }
                    }
                }
            }
        }

        return crossings;
    }

    // /////////////////////////////////////////////////////////////////////////////
    // Utility Methods

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
            int mid = low + high >>> 1;
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
}
