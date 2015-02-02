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

import java.util.Iterator;

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

    private final LNode[] layer;

    public NorthSouthPortNeighbouringNodeCounter(final LNode[] lNodes) {
        layer = lNodes;
        initializeLayer();
    }

    private void initializeLayer() {
        int nodeId = 0;
        for (LNode node : layer) {
            int portId = 0;
            for (LPort port : node.getPorts()) {
                if (port.getSide() == PortSide.NORTH || port.getSide() == PortSide.SOUTH) {
                    port.id = portId++;
                }
                if (port.getSide() == PortSide.WEST) {
                    break;
                }
            }
            node.id = nodeId++;
        }
    }

    public void countCrossings(final LNode upperNode, final LNode lowerNode) {
        upperLowerCrossings = 0;
        lowerUpperCrossings = 0;
        if (nodeIsNorthSouth(upperNode) && nodeIsNorthSouth(lowerNode)) {
            if (noFixedPortOrderOn(upperNode)) {
                return;
            }
            if (portAtNormalNodeFrom(upperNode).id > portAtNormalNodeFrom(lowerNode).id) {
                upperLowerCrossings = 1;
            } else {
                lowerUpperCrossings = 1;
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

    private boolean noFixedPortOrderOn(final LNode node) {
        LNode normalNode = portAtNormalNodeFrom(node).getNode();
        return normalNode.getProperty(LayoutOptions.PORT_CONSTRAINTS) != PortConstraints.FIXED_ORDER;
    }

    private boolean isSouthernNode(final LNode node) {
        return node.getPorts(PortSide.NORTH).iterator().hasNext();
    }

    private boolean nodeIsLongEdgeDummy(final LNode node) {
        return node.getProperty(InternalProperties.NODE_TYPE) == NodeType.LONG_EDGE;
    }

    private boolean nodeIsNorthSouth(final LNode node) {
        return node.getProperty(InternalProperties.NODE_TYPE) == NodeType.NORTH_SOUTH_PORT;
    }

    private LPort portAtNormalNodeFrom(final LNode node) {
        Iterator<LPort> northPortIterator = node.getPorts(PortSide.NORTH).iterator();
        boolean hasNorthPort = northPortIterator.hasNext();
        if (hasNorthPort) {
            return northPortIterator.next().getConnectedPorts().iterator().next();
        } else {
            LPort southPort = node.getPorts(PortSide.SOUTH).iterator().next();
            return southPort.getConnectedPorts().iterator().next();
        }
    }

    public int getUpperLowerCrossings() {
        return upperLowerCrossings;
    }

    public int getLowerUpperCrossings() {
        return lowerUpperCrossings;
    }

}
