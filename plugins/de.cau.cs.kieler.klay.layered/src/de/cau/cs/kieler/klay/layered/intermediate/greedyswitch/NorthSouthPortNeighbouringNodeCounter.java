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
import java.util.Map;

import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.properties.InternalProperties;
import de.cau.cs.kieler.klay.layered.properties.NodeType;

/**
 * @author alan
 *
 */
public class NorthSouthPortNeighbouringNodeCounter {

    private int upperLowerCrossings;
    private int lowerUpperCrossings;
    private final Map<LPort, Integer> portPositions;
    private final int[] nodeCardinalities;
    private final LNode[] layer;

    public NorthSouthPortNeighbouringNodeCounter(final LNode[] nodes) {
        layer = nodes;
        nodeCardinalities = new int[nodes.length];
        portPositions = new HashMap<LPort, Integer>();
        initializeLayer();
    }

    private void initializeLayer() {
        int nodeId = 0;
        for (LNode node : layer) {
            node.id = nodeId++;
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
            nodeCardinalities[node.id] = portId;
        }
    }

    private int positionOf(final LPort port) {
        return portPositions.get(port);
    }

    public void countCrossings(final LNode upperNode, final LNode lowerNode) {
        upperLowerCrossings = 0;
        lowerUpperCrossings = 0;
        if (nodeIsNorthSouth(upperNode) && nodeIsNorthSouth(lowerNode)) {
            if (noFixedPortOrderOn(getOrigin(upperNode))
                    || getOrigin(upperNode) != getOrigin(lowerNode)) {
                return;
            }
            if (isNorthOfNormalNode(upperNode)) {
                processNodesNorthOfNormalNode(upperNode, lowerNode);
            } else {
                processNodesSouthOfNormalNode(upperNode, lowerNode);
            }
        } else if (nodeIsNorthSouth(upperNode) && nodeIsLongEdgeDummy(lowerNode)) {
            if (isSouthernNode(upperNode)) {
                lowerUpperCrossings = 1;
            } else {
                upperLowerCrossings = 1;
            }
        } else if (nodeIsNorthSouth(lowerNode) && nodeIsLongEdgeDummy(upperNode)) {
            if (!isSouthernNode(lowerNode)) {
                lowerUpperCrossings = 1;
            } else {
                upperLowerCrossings = 1;
            }
        }
    }

    private void processNodesSouthOfNormalNode(final LNode upperNode, final LNode lowerNode) {
        PortSide upperNodePortSide = upperNode.getPorts().get(0).getSide();
        PortSide lowerNodePortSide = lowerNode.getPorts().get(0).getSide();
        if (upperNodePortSide == PortSide.EAST && lowerNodePortSide == PortSide.EAST) {
            if (originPortPositionOf(upperNode) > originPortPositionOf(lowerNode)) {
                upperLowerCrossings = 1;
            } else {
                lowerUpperCrossings = 1;
            }
        } else if (upperNodePortSide == PortSide.WEST && lowerNodePortSide == PortSide.WEST) {
            if (originPortPositionOf(upperNode) < originPortPositionOf(lowerNode)) {
                upperLowerCrossings = 1;
            } else {
                lowerUpperCrossings = 1;
            }
        } else if (upperNodePortSide == PortSide.WEST && lowerNodePortSide == PortSide.EAST) {
            if (originPortPositionOf(upperNode) < originPortPositionOf(lowerNode)) {
                upperLowerCrossings = 1;
                lowerUpperCrossings = 1;
            }
        } else {
            if (originPortPositionOf(upperNode) > originPortPositionOf(lowerNode)) {
                upperLowerCrossings = 1;
                lowerUpperCrossings = 1;
            }
        }
    }

    private boolean isNorthOfNormalNode(final LNode upperNode) {
        return originPortOf(upperNode).getSide() == PortSide.NORTH;
    }

    private void processNodesNorthOfNormalNode(final LNode upperNode, final LNode lowerNode) {
        PortSide upperNodePortSide = upperNode.getPorts().get(0).getSide();
        PortSide lowerNodePortSide = lowerNode.getPorts().get(0).getSide();
        if (upperNodePortSide == PortSide.EAST && lowerNodePortSide == PortSide.EAST) {
            if (originPortPositionOf(upperNode) > originPortPositionOf(lowerNode)) {
                upperLowerCrossings = 1;
            } else {
                lowerUpperCrossings = 1;
            }
        } else if (upperNodePortSide == PortSide.WEST && lowerNodePortSide == PortSide.WEST) {
            if (originPortPositionOf(upperNode) < originPortPositionOf(lowerNode)) {
                upperLowerCrossings = 1;
            } else {
                lowerUpperCrossings = 1;
            }
        } else if (upperNodePortSide == PortSide.WEST && lowerNodePortSide == PortSide.EAST) {
            if (originPortPositionOf(upperNode) > originPortPositionOf(lowerNode)) {
                upperLowerCrossings = 1;
                lowerUpperCrossings = 1;
            }
        } else {
            if (originPortPositionOf(upperNode) < originPortPositionOf(lowerNode)) {
                upperLowerCrossings = 1;
                lowerUpperCrossings = 1;
            }
        }
    }

    private LNode getOrigin(final LNode node) {
        return (LNode) node.getProperty(InternalProperties.ORIGIN);
    }

    private boolean noFixedPortOrderOn(final LNode node) {
        return node.getProperty(LayoutOptions.PORT_CONSTRAINTS) != PortConstraints.FIXED_ORDER;
    }

    private boolean isSouthernNode(final LNode node) {
        return ((LNode) node.getProperty(InternalProperties.ORIGIN)).id < node.id;
    }

    private boolean nodeIsLongEdgeDummy(final LNode node) {
        return node.getProperty(InternalProperties.NODE_TYPE) == NodeType.LONG_EDGE;
    }

    private boolean nodeIsNorthSouth(final LNode node) {
        return node.getProperty(InternalProperties.NODE_TYPE) == NodeType.NORTH_SOUTH_PORT;
    }

    private int originPortPositionOf(final LNode node) {
        LPort origin = originPortOf(node);
        return positionOf(origin);
    }

    private LPort originPortOf(final LNode node) {
        LPort port = node.getPorts().get(0);
        LPort origin = (LPort) port.getProperty(InternalProperties.ORIGIN);
        return origin;
    }

    public int getUpperLowerCrossings() {
        return upperLowerCrossings;
    }

    public int getLowerUpperCrossings() {
        return lowerUpperCrossings;
    }

    public void notifyNodeSwitch(final LNode upperNode, final LNode lowerNode) {
        for (LPort port : upperNode.getPorts()) {
            if (port.getSide() == PortSide.NORTH || port.getSide() == PortSide.SOUTH) {
                portPositions.put(port, positionOf(port) + nodeCardinalities[lowerNode.id]);
            }
        }
        for (LPort port : lowerNode.getPorts()) {
            if (port.getSide() == PortSide.NORTH || port.getSide() == PortSide.SOUTH) {
                portPositions.put(port, positionOf(port) - nodeCardinalities[upperNode.id]);
            }
        }
    }
}
