package de.cau.cs.kieler.klay.layered.intermediate.greedyswitch;

import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.properties.InternalProperties;
import de.cau.cs.kieler.klay.layered.properties.NodeType;

public class NorthSouthPortAllCrossingCounter {

    private final LNode[] layer;

    public NorthSouthPortAllCrossingCounter(final LNode[] layer) {
        this.layer = layer;
        initialize(layer);
    }

    private void initialize(final LNode[] layer) {
        int portCount = 0;
        for (LNode node : layer) {
            for (LPort port : node.getPorts()) {
                port.id = portCount++;
            }
        }
    }

    /**
     * Counts the number of edge crossings caused by the way north / south port dummies are ordered.
     *
     * @param layer
     *            the layer whose north / south port related crossings to count.
     * @return the number of crossings caused by edges connected to northern or southern ports.
     */
    public int countCrossings() {
        int crossings = 0;
        boolean northernSide = true;
        LNode recentNormalNode = null;
        int amountOfLongEdgeDummies = 0;
        int amountOfNorthSouthEdges = 0;

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
                amountOfNorthSouthEdges = 0;
            } else if (nodeType == NodeType.LONG_EDGE) {
                if (northernSide) {
                    crossings += amountOfNorthSouthEdges;
                } else {
                    amountOfLongEdgeDummies++;
                }
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
                    amountOfLongEdgeDummies = 0;
                    amountOfNorthSouthEdges = 0;
                }

                if (northernSide) { // TODO-alan
                    amountOfNorthSouthEdges++;
                } else {
                    crossings += amountOfLongEdgeDummies;
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
}
