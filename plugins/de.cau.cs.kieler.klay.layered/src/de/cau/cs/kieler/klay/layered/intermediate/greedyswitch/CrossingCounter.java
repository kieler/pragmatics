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

import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map;

import de.cau.cs.kieler.core.util.Pair;
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
class CrossingCounter {

    /**
     * Port position array used for counting the number of edge crossings.
     */
    private int[] portPos;
    private final LNode[][] layeredGraph;
    private final LNode[][] originalOrder;

    /**
     * Constructs and initializes a cross counter. Initialization iterates through all ports.
     * TODO-alan: consider asking for switch
     * 
     * @param layeredGraph
     *            The layered graph
     */
    public CrossingCounter(final LNode[][] layeredGraph) {
        this.layeredGraph = layeredGraph;
        originalOrder = new LNode[layeredGraph.length][];
        initialize();
    }

    /**
     * Counts all crossings in a graph.
     * 
     * @return the number of crossings for the node order passed to the constructor.
     */
    public int countAllCrossingsInGraph() {
        return countAllCrossingsInGraphWithOrder(originalOrder);
    }

    /**
     * Counts all crossings in a graph in the originalOrder.
     *
     * @param currentOrder
     *            The current order of the nodes.
     * @return the amount of crossings
     */
    public int countAllCrossingsInGraphWithOrder(final LNode[][] currentOrder) {
        int totalCrossings = 0;
        for (int layerIndex = 0; layerIndex < currentOrder.length; layerIndex++) {
            LNode[] fixedLayer = currentOrder[layerIndex];
            totalCrossings += countNorthSouthPortCrossings(fixedLayer);
            totalCrossings += countInLayerEdgeCrossingsWithOrder(fixedLayer);
            if (layerIndex < currentOrder.length - 1) {
                LNode[] freeLayer = currentOrder[layerIndex + 1];
                totalCrossings += countCrossingsBetweenLayersInOrder(fixedLayer, freeLayer);
            }
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

    private void initialize() {
        int portCount = 0;
        for (int i = 0; i < layeredGraph.length; i++) {
            LNode[] layer = layeredGraph[i];
            originalOrder[i] = new LNode[layer.length]; // TODO-alan WTF orginial order?
            for (int j = 0; j < layer.length; j++) {
                LNode node = layeredGraph[i][j];
                originalOrder[i][j] = node;
                for (LPort port : node.getPorts()) {
                    port.id = portCount++;
                }
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

    /*
     * The algorithm used to count crossings within a layer implemented in the following method has
     * two parts:
     * 
     * Part 1 A normal node cannot be connected to another normal node in the same layer due to how
     * layering is performed. It remains that at least one of the two nodes must be a dummy node.
     * Currently, that can only happen due to odd port side handling. Because of the way dummies are
     * created, there are only two cases:
     * 
     * - An eastern port can be connected to another eastern port. - A western port can be connected
     * to another western port.
     * 
     * The algorithm now works by assigning numbers to eastern ports top-down, and to western ports
     * bottom-up, all the time dependent on their number of incident edges. (the link direction is
     * not important) Then we traverse the ports. If we find an eastern port connected to another
     * eastern port, the difference of their indices tells us how many other ports with incident
     * edges lie between them and can cause crossings.
     * 
     * Part 2 Additional crossings can happen due to nodes being placed between a node and its north
     * / south dummies. The idea here is to use the first node sweep from part 1 to count the number
     * of northern and southern North/South dummies for each node. Each north dummy is assigned a
     * position in the list of northern dummies for its node. South dummies are treated accordingly.
     * 
     * In a second sweep, for each non-north/south dummy, the most recently encountered north/south
     * dummy or normal node is retrieved. Its index is subtracted from the number of northern or
     * southern dummies of its node. The result gives the number of crossings caused by the node
     * being placed between a node and its north/south dummies.
     * 
     * Note that part two relies on information about layer layout units. If we find that they have
     * not been set, we can skip this part.
     */

    /**
     * Calculates the amount of In-Layer edges for the given layer in the order, with which
     * CrossingCounter was initialized with.
     * 
     * @param layerIndex
     *            index of Layer to count in.
     * @return north South Port crossing number
     */
    public int countInLayerEdgeCrossings(final int layerIndex) {
        return countInLayerEdgeCrossingsWithOrder(originalOrder[layerIndex]);
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
        int eastWestCrossings = 0;
        int northSouthCrossings = 0;

        // Number of north/south dummies and indices
        Map<LNode, Pair<Integer, Integer>> northSouthCrossingHints =
                new HashMap<LNode, Pair<Integer, Integer>>();
        Map<LNode, Integer> dummyIndices = new HashMap<LNode, Integer>();

        // Assign numbers to the layer's eastern and western ports
        Map<LPort, Integer> easternPortNumbers = new HashMap<LPort, Integer>();
        Map<LPort, Integer> westernPortNumbers = new HashMap<LPort, Integer>();

        numberEastWestPorts(layer, easternPortNumbers, westernPortNumbers);

        // Iterate through the nodes
        LNode currentNormalNode = null;
        int northMaxCrossingHint = 0;
        int southMaxCrossingHint = 0;
        boolean northernSide = true;
        boolean layerLayoutUnitsSet = true;

        for (LNode node : layer) {

            // Part 1 of the crossing counting algorithm
            for (LPort port : node.getPorts()) {
                switch (port.getSide()) {
                case EAST:
                    eastWestCrossings += countInLayerCrossings(port, easternPortNumbers);
                    break;

                case WEST:
                    eastWestCrossings += countInLayerCrossings(port, westernPortNumbers);
                    break;
                }
            }

            // First sweep of part 2 of the crossing counting algorithm
            NodeType nodeType = node.getProperty(InternalProperties.NODE_TYPE);
            if (layerLayoutUnitsSet
                    && (nodeType == NodeType.NORMAL || nodeType == NodeType.NORTH_SOUTH_PORT)) {

                LNode newNormalNode = node.getProperty(InternalProperties.IN_LAYER_LAYOUT_UNIT);
                if (newNormalNode == null) {
                    // Layer layout units don't seem to have been set
                    layerLayoutUnitsSet = false;
                    continue;
                }

                // Check if this node belongs to a new normal node
                if (currentNormalNode != newNormalNode) {
                    // Save the old normal node's values
                    if (currentNormalNode != null) {
                        northSouthCrossingHints.put(currentNormalNode, new Pair<Integer, Integer>(
                                northMaxCrossingHint, southMaxCrossingHint));
                    }

                    // Reset the counters
                    currentNormalNode = newNormalNode;
                    northMaxCrossingHint = 0;
                    southMaxCrossingHint = 0;
                    northernSide = true;
                }

                // If the node is the normal node, we're entering its south side
                if (node == currentNormalNode) {
                    northernSide = false;
                }

                // Update and save crossing hints
                if (northernSide) {
                    northMaxCrossingHint += node.getProperty(InternalProperties.CROSSING_HINT);
                    dummyIndices.put(node, northMaxCrossingHint);
                } else {
                    southMaxCrossingHint += node.getProperty(InternalProperties.CROSSING_HINT);
                    dummyIndices.put(node, southMaxCrossingHint);
                }
            }
        }

        // Remember to save the values for the last normal node
        if (currentNormalNode != null) {
            northSouthCrossingHints.put(currentNormalNode, new Pair<Integer, Integer>(
                    northMaxCrossingHint, southMaxCrossingHint));
        }

        // Second sweep of Part 2 of the algorithm
        if (layerLayoutUnitsSet) {
            LNode lastDummyNormalNode = null;
            int lastDummyIndex = 0;
            int dummyCount = 0;
            northernSide = true;

            for (LNode node : layer) {
                NodeType nodeType = node.getProperty(InternalProperties.NODE_TYPE);

                switch (nodeType) {
                case NORMAL:
                    lastDummyIndex = dummyIndices.get(node);

                    dummyCount = northSouthCrossingHints.get(node).getSecond();
                    lastDummyNormalNode = node;
                    northernSide = false;

                    break;

                case NORTH_SOUTH_PORT:
                    lastDummyIndex = dummyIndices.get(node);

                    LNode newNormalNode = node.getProperty(InternalProperties.IN_LAYER_LAYOUT_UNIT);
                    if (newNormalNode != lastDummyNormalNode) {
                        dummyCount = northSouthCrossingHints.get(newNormalNode).getFirst();
                        lastDummyNormalNode = newNormalNode;
                        northernSide = true;
                    }
                    break;

                default:
                    northSouthCrossings +=
                            northernSide ? lastDummyIndex : dummyCount - lastDummyIndex;
                }
            }
        }

        return eastWestCrossings + northSouthCrossings;
    }

    /**
     * Counts the crossings caused by in-layer edges connected to the given port. An edge is only
     * counted once.
     *
     * @param port
     *            the port whose edge crossings to count.
     * @param portIndices
     *            map of ports to their respective indices as calculated by
     *            {@link #numberEastWestPorts(LNode[], Map, Map)}.
     * @return the maximum number of crossings‚ for this port.
     */
    private int countInLayerCrossings(final LPort port, final Map<LPort, Integer> portIndices) {
        int maxCrossings = 0;

        // Find this port's index
        Integer portIndex = portIndices.get(port);
        if (portIndex == null) {
            return 0;
        }

        // Find the maximum distance between two connected ports
        Integer connectedPortIndex = null;
        for (LEdge edge : port.getConnectedEdges()) {
            if (edge.getSource() == port) {
                connectedPortIndex = portIndices.get(edge.getTarget());
            } else {
                connectedPortIndex = portIndices.get(edge.getSource());
            }

            // Check if the edge is connected to another port in the same layer
            if (connectedPortIndex != null) {
                // Only count the edge once
                if (portIndex.intValue() > connectedPortIndex.intValue()) {
                    maxCrossings =
                            Math.max(maxCrossings,
                                    portIndex.intValue() - connectedPortIndex.intValue() - 1);
                }
            }
        }

        return maxCrossings;
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
        return countNorthSouthPortCrossings(originalOrder[layerIndex]);
    }

    /**
     * Counts the number of edge crossings caused by the way north / south port dummies are ordered.
     *
     * @param layer
     *            the layer whose north / south port related crossings to count.
     * @return the number of crossings caused by edges connected to northern or southern ports.
     */
    public int countNorthSouthPortCrossings(final LNode[] layer) {
        int crossings = 0;
        boolean northernSide = true;
        LNode recentNormalNode = null;

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
            } else if (nodeType == NodeType.NORTH_SOUTH_PORT) {
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
                    }
                }
            }
        }

        return crossings;
    }

    /**
     * Assigns numbers to the eastern ports of a layer, and to the western ports of a layer. A
     * number is assigned to a port if it has incident edges. Eastern ports are numbered top-down,
     * while western ports are numbered bottom-up.
     *
     * @param layer
     *            the layer whose ports to index.
     * @param easternMap
     *            map to put the eastern ports' indices in.
     * @param westernMap
     *            map to put the western ports' indices in.
     */
    private void numberEastWestPorts(final LNode[] layer, final Map<LPort, Integer> easternMap,
            final Map<LPort, Integer> westernMap) {

        int currentEasternNumber = 0;
        int currentWesternNumber = 0;

        // Assign numbers to eastern ports, top-down
        for (LNode node : layer) {
            if (node.getProperty(LayoutOptions.PORT_CONSTRAINTS).isOrderFixed()) {
                for (LPort easternPort : node.getPorts(PortSide.EAST)) {
                    if (easternPort.getDegree() > 0) {
                        currentEasternNumber += easternPort.getDegree();
                        easternMap.put(easternPort, currentEasternNumber);
                    }
                }
            } else {
                // Find the number of edges incident to eastern ports
                for (LPort easternPort : node.getPorts(PortSide.EAST)) {
                    currentEasternNumber += easternPort.getDegree();
                }

                // Assign the eastern number to all eastern ports
                for (LPort easternPort : node.getPorts(PortSide.EAST)) {
                    if (easternPort.getDegree() > 0) {
                        easternMap.put(easternPort, currentEasternNumber);
                    }
                }
            }
        }

        // Assign indices to western ports, bottom-up
        for (int nodeIndex = layer.length - 1; nodeIndex >= 0; nodeIndex--) {
            LNode node = layer[nodeIndex];

            if (node.getProperty(LayoutOptions.PORT_CONSTRAINTS).isOrderFixed()) {
                for (LPort westernPort : node.getPorts(PortSide.WEST)) {
                    if (westernPort.getDegree() > 0) {
                        currentWesternNumber += westernPort.getDegree();
                        westernMap.put(westernPort, currentWesternNumber);
                    }
                }
            } else {
                // Find the number of edges incident to western ports
                for (LPort westernPort : node.getPorts(PortSide.WEST)) {
                    currentWesternNumber += westernPort.getDegree();
                }

                // Assign the western number to all western ports
                for (LPort westernPort : node.getPorts(PortSide.WEST)) {
                    if (westernPort.getDegree() > 0) {
                        westernMap.put(westernPort, currentWesternNumber);
                    }
                }
            }
        }
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
