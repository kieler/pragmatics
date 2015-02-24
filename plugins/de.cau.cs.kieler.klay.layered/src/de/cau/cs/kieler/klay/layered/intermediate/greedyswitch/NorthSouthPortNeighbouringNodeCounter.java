/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.intermediate.greedyswitch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.properties.InternalProperties;
import de.cau.cs.kieler.klay.layered.properties.NodeType;

/**
 * Counts the amount of crossings caused by the order of north south port dummies when their
 * respective normal node in the same layer has a fixed port order. Also counts crossings between
 * north south edges and long edge dummies.
 * 
 * @author alan
 *
 */
public class NorthSouthPortNeighbouringNodeCounter {

    private int upperLowerCrossings;
    private int lowerUpperCrossings;
    private final Map<LPort, Integer> portPositions;
    private final LNode[] layer;

    /**
     * Creates a counter for north south port crossings.
     * 
     * @param nodes
     *            the order of nodes in the layer in question.
     */
    public NorthSouthPortNeighbouringNodeCounter(final LNode[] nodes) {
        layer = nodes;
        portPositions = new HashMap<LPort, Integer>();
        initializePortPositions();
    }

    /**
     * Since accessing the index of a port is linear, the positions are saved. To prevent dependency
     * problems, they are saved in a field of this Object. Ports are numbered as they are in the
     * list returned by getPorts(), so
     */
    private void initializePortPositions() {
        for (LNode node : layer) {
            int portId = 0;
            for (LPort port : node.getPorts()) {
                if (port.getSide() == PortSide.NORTH || port.getSide() == PortSide.SOUTH) {
                    portPositions.put(port, portId);
                    portId++;
                }
                if (port.getSide() == PortSide.WEST) {
                    break;
                }
            }
        }
    }

    /**
     * Counts north south port crossings and crossings between north south ports and dummy nodes,
     * for uppperNode and lowerNode.
     * 
     * @param upperNode
     *            The first node.
     * @param lowerNode
     *            The second node.
     */
    public void countCrossings(final LNode upperNode, final LNode lowerNode) {
        upperLowerCrossings = 0;
        lowerUpperCrossings = 0;

        processIfTwoNorthSouthNodes(upperNode, lowerNode);

        processIfNorthSouthLongEdgeDummyCrossing(upperNode, lowerNode);

        processIfNormalNodeWithNSPortsAndLongEdgeDummy(upperNode, lowerNode);
    }

    private void processIfNormalNodeWithNSPortsAndLongEdgeDummy(final LNode upperNode,
            final LNode lowerNode) {
        if (isNormalAndHasPortsOnSide(upperNode, PortSide.SOUTH) && isLongEdgeDummy(lowerNode)) {
            upperLowerCrossings =
                    amountOfDummyEdgeCrossingsWithNSEdgesOnSide(upperNode, PortSide.SOUTH);
            lowerUpperCrossings =
                    amountOfDummyEdgeCrossingsWithNSEdgesOnSide(upperNode, PortSide.NORTH);
        }
        if (isNormalAndHasPortsOnSide(lowerNode, PortSide.NORTH) && isLongEdgeDummy(upperNode)) {
            upperLowerCrossings =
                    amountOfDummyEdgeCrossingsWithNSEdgesOnSide(lowerNode, PortSide.NORTH);
            lowerUpperCrossings =
                    amountOfDummyEdgeCrossingsWithNSEdgesOnSide(lowerNode, PortSide.SOUTH);
        }
    }

    private int amountOfDummyEdgeCrossingsWithNSEdgesOnSide(final LNode node, final PortSide side) {
        int amountOfPorts = 0;
        for (LPort port : node.getPorts(side)) {
            List<LPort> nsPorts = port.getProperty(InternalProperties.CONNECTED_NORTH_SOUTH_PORTS);
            amountOfPorts += nsPorts.size();
        }
        return amountOfPorts;
    }

    private boolean isNormalAndHasPortsOnSide(final LNode node, final PortSide side) {
        boolean isNormal = node.getProperty(InternalProperties.NODE_TYPE) == NodeType.NORMAL;
        boolean hasPortsOnSide = node.getPorts(side).iterator().hasNext();
        return isNormal && hasPortsOnSide;
    }

    private void processIfTwoNorthSouthNodes(final LNode upperNode, final LNode lowerNode) {
        if (isNorthSouth(upperNode) && isNorthSouth(lowerNode)) {
            if (noFixedPortOrderOn(originOf(upperNode))
                    || haveDifferentOrigins(upperNode, lowerNode)) {
                return;
            }
            if (isNorthOfNormalNode(upperNode)) {
                processNodesNorthOfNormalNode(upperNode, lowerNode);
            } else {
                processNodesSouthOfNormalNode(upperNode, lowerNode);
            }
        }
    }

    private void processIfNorthSouthLongEdgeDummyCrossing(final LNode upperNode,
            final LNode lowerNode) {
        if (isNorthSouth(upperNode) && isLongEdgeDummy(lowerNode)) {
            if (isSouthOfConnectedNormalNode(upperNode)) {
                lowerUpperCrossings = 1;
            } else {
                upperLowerCrossings = 1;
            }
        } else if (isNorthSouth(lowerNode) && isLongEdgeDummy(upperNode)) {
            if (!isSouthOfConnectedNormalNode(lowerNode)) {
                lowerUpperCrossings = 1;
            } else {
                upperLowerCrossings = 1;
            }
        }
    }

    private boolean haveDifferentOrigins(final LNode upperNode, final LNode lowerNode) {
        return originOf(upperNode) != originOf(lowerNode);
    }

    private void processNodesNorthOfNormalNode(final LNode upperNode, final LNode lowerNode) {
        PortSide upperNodePortSide = getPortDirectionFromNorthSouthNode(upperNode);
        PortSide lowerNodePortSide = getPortDirectionFromNorthSouthNode(lowerNode);
        if (upperNodePortSide == PortSide.EAST && lowerNodePortSide == PortSide.EAST) {
            if (originPortsAreInClockwiseOrder(lowerNode, upperNode)) {
                upperLowerCrossings = 1;
            } else {
                lowerUpperCrossings = 1;
            }
        } else if (upperNodePortSide == PortSide.WEST && lowerNodePortSide == PortSide.WEST) {
            if (originPortsAreInClockwiseOrder(upperNode, lowerNode)) {
                upperLowerCrossings = 1;
            } else {
                lowerUpperCrossings = 1;
            }
        } else if (upperNodePortSide == PortSide.WEST && lowerNodePortSide == PortSide.EAST) {
            if (originPortsAreInClockwiseOrder(lowerNode, upperNode)) {
                upperLowerCrossings = 1;
                lowerUpperCrossings = 1;
            }
        } else {
            if (originPortsAreInClockwiseOrder(upperNode, lowerNode)) {
                upperLowerCrossings = 1;
                lowerUpperCrossings = 1;
            }
        }
    }

    private void processNodesSouthOfNormalNode(final LNode upperNode, final LNode lowerNode) {
        PortSide upperNodePortSide = getPortDirectionFromNorthSouthNode(upperNode);
        PortSide lowerNodePortSide = getPortDirectionFromNorthSouthNode(lowerNode);
        if (upperNodePortSide == PortSide.EAST && lowerNodePortSide == PortSide.EAST) {
            if (originPortsAreInClockwiseOrder(lowerNode, upperNode)) {
                upperLowerCrossings = 1;
            } else {
                lowerUpperCrossings = 1;
            }
        } else if (upperNodePortSide == PortSide.WEST && lowerNodePortSide == PortSide.WEST) {
            if (originPortsAreInClockwiseOrder(upperNode, lowerNode)) {
                upperLowerCrossings = 1;
            } else {
                lowerUpperCrossings = 1;
            }
        } else if (upperNodePortSide == PortSide.WEST && lowerNodePortSide == PortSide.EAST) {
            if (originPortsAreInClockwiseOrder(upperNode, lowerNode)) {
                upperLowerCrossings = 1;
                lowerUpperCrossings = 1;
            }
        } else {
            if (originPortsAreInClockwiseOrder(lowerNode, upperNode)) {
                upperLowerCrossings = 1;
                lowerUpperCrossings = 1;
            }
        }
    }

    private PortSide getPortDirectionFromNorthSouthNode(final LNode node) {
        assert isNorthSouth(node);
        boolean northSouthNodeOnlyHasOneInBetweenLayerEdge = node.getPorts().size() == 1;
        assert northSouthNodeOnlyHasOneInBetweenLayerEdge;
        return node.getPorts().get(0).getSide();
    }

    private boolean originPortsAreInClockwiseOrder(final LNode first, final LNode second) {
        return originPortPositionOf(first) < originPortPositionOf(second);
    }

    private boolean isNorthOfNormalNode(final LNode upperNode) {
        return originPortOf(upperNode).getSide() == PortSide.NORTH;
    }

    private LNode originOf(final LNode node) {
        return (LNode) node.getProperty(InternalProperties.ORIGIN);
    }

    private boolean noFixedPortOrderOn(final LNode node) {
        return !node.getProperty(LayoutOptions.PORT_CONSTRAINTS).isOrderFixed();
    }

    private boolean isSouthOfConnectedNormalNode(final LNode node) { // TODO-alan remove!
        return originPortOf(node).getSide() == PortSide.SOUTH;
    }

    private boolean isLongEdgeDummy(final LNode node) {
        return node.getProperty(InternalProperties.NODE_TYPE) == NodeType.LONG_EDGE;
    }

    private boolean isNorthSouth(final LNode node) {
        return node.getProperty(InternalProperties.NODE_TYPE) == NodeType.NORTH_SOUTH_PORT;
    }

    private int originPortPositionOf(final LNode node) {
        LPort origin = originPortOf(node);
        final LPort port = origin;
        return portPositions.get(port);
    }

    private LPort originPortOf(final LNode node) {
        LPort port = node.getPorts().get(0);
        LPort origin = (LPort) port.getProperty(InternalProperties.ORIGIN);
        return origin;
    }

    /**
     * Get crossing amounts.
     * 
     * @return the crossings between when ordered upper - lower.
     */
    public int getUpperLowerCrossings() {
        return upperLowerCrossings;
    }

    /**
     * 
     * Get crossing amounts.
     * 
     * @return the crossings between when ordered lower - upper.
     */
    public int getLowerUpperCrossings() {
        return lowerUpperCrossings;
    }

}
