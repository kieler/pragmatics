/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.klay.layered.p3order;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.properties.PortType;

/**
 * Calculates port ranks and distributes ports in a node-relative way. This is in fact the original
 * way KLay Layered calculated port ranks.
 * 
 * <p>Port ranks are calculated by giving each node {@code i} a range of values {@code [i,i+1)} to
 * distribute their port values in. This effectively gives a node with many ports the same weight as
 * a node with few ports as opposed to a strategy that just numbers ports from top to bottom.</p>
 * 
 * @author cds
 * @author msp
 * @author ima
 */
public class NodeRelativePortDistributor implements IPortDistributor {

    /** barycenter values for ports. */
    private float[] portBarycenter;
    /** port position array. */
    private float[] portPos;

    /**
     * Constructs a NodeRelativePortDistributor with the given portPos-array.
     * 
     * @param portRanks
     *            The array of portRanks;
     * @param portBarycenters
     *            The barycenter value array for ports.
     */
    public NodeRelativePortDistributor(final float[] portRanks, final float[] portBarycenters) {
        portPos = portRanks;
        portBarycenter = portBarycenters;
    }

    // /////////////////////////////////////////////////////////////////////////////
    // Port Position Assignment

    /**
     * {@inheritDoc}
     */
    public void calculatePortRanks(final LNode[] layer) {
        int nodeIx = 0;
        for (LNode node : layer) {
            // count the input and output ports
            int inputPorts = 0, outputPorts = 0;
            for (LPort port : node.getPorts()) {
                if (port.getNetFlow() < 0) {
                    outputPorts++;
                } else {
                    inputPorts++;
                }
            }

            // set positions for the input ports
            if (inputPorts > 0) {
                calculatePortRanks(node, nodeIx, PortType.INPUT, inputPorts);
            }

            // set positions for the output ports
            if (outputPorts > 0) {
                calculatePortRanks(node, nodeIx, PortType.OUTPUT, outputPorts);
            }

            nodeIx++;
        }
    }

    /**
     * Assign port positions for the input or output ports of the given node.
     * 
     * @param node
     *            a node
     * @param nodeIx
     *            the node's index in its layer
     * @param type
     *            the type of ports to process
     * @param count
     *            the number of ports of that type
     */
    private void calculatePortRanks(final LNode node, final int nodeIx, final PortType type,
            final int count) {

        if (node.getProperty(LayoutOptions.PORT_CONSTRAINTS).isOrderFixed()) {
            float incr = 1.0f / count;

            if (type == PortType.INPUT) {
                // Start at the top right port, going counter-clockwise
                List<LPort> portList = getSortedInputPorts(node);
                float pos = nodeIx + 1 - incr;

                for (LPort port : portList) {
                    portPos[port.id] = pos;
                    pos -= incr;
                }

            } else {
                // Start at the top left port, going clockwise
                float pos = nodeIx;

                for (LPort port : node.getPorts(type)) {
                    portPos[port.id] = pos;
                    pos += incr;
                }
            }
        } else {
            for (LPort port : node.getPorts(type)) {
                portPos[port.id] = nodeIx + getPortIncr(type, port.getSide());
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<LPort> getSortedInputPorts(final LNode node) {
        List<LPort> northPorts = new LinkedList<LPort>();
        List<LPort> portList = new LinkedList<LPort>();

        for (LPort port : node.getPorts(PortType.INPUT)) {
            if (port.getSide() == PortSide.NORTH) {
                northPorts.add(port);
            } else {
                portList.add(port);
            }
        }

        // Append the list of northern ports to the other ports
        portList.addAll(northPorts);
        return portList;
    }

    private static final float INCR_ONE = 0.3f;
    private static final float INCR_TWO = 0.5f;
    private static final float INCR_THREE = 0.7f;
    private static final float INCR_FOUR = 0.9f;

    /**
     * Return an increment value for the position of a port with given type and side.
     * 
     * @param type
     *            the port type
     * @param side
     *            the port side
     * @return a position increment for the port
     */
    private static float getPortIncr(final PortType type, final PortSide side) {
        switch (type) {
        case INPUT:
            switch (side) {
            case NORTH:
                return INCR_ONE;
            case WEST:
                return INCR_TWO;
            case SOUTH:
                return INCR_THREE;
            case EAST:
                return INCR_FOUR;
            }
            break;
        case OUTPUT:
            switch (side) {
            case NORTH:
                return INCR_ONE;
            case EAST:
                return INCR_TWO;
            case SOUTH:
                return INCR_THREE;
            case WEST:
                return INCR_FOUR;
            }
            break;
        }
        return 0;
    }

    // /////////////////////////////////////////////////////////////////////////////
    // Port Distribution

    /**
     * {@inheritDoc}
     */
    public void distributePorts(final LNode[][] layeredGraph) {
        for (int l = 0; l < layeredGraph.length; l++) {
            if (l + 1 < layeredGraph.length) {
                calculatePortRanks(layeredGraph[l + 1]);
            }
            LNode[] layer = layeredGraph[l];
            for (int i = 0; i < layer.length; i++) {
                LNode node = layer[i];
                if (!node.getProperty(LayoutOptions.PORT_CONSTRAINTS).isOrderFixed()) {
                    // the order of ports on each side is variable, so distribute the ports
                    distributePorts(node);
                    node.setProperty(LayoutOptions.PORT_CONSTRAINTS, PortConstraints.FIXED_ORDER);
                    int outputPortCount = 0;
                    for (LPort port : node.getPorts()) {
                        if (port.getNetFlow() < 0) {
                            outputPortCount++;
                        }
                    }
                    if (outputPortCount > 0) {
                        calculatePortRanks(node, i, PortType.OUTPUT, outputPortCount);
                    }
                }
            }
        }
    }

    /**
     * Distribute the ports of the given node by their sides, connected ports, and input or output
     * type.
     * 
     * @param node
     *            node whose ports shall be sorted
     */
    private void distributePorts(final LNode node) {
        // calculate barycenter values for the ports of the node
        for (LPort port : node.getPorts()) {
            float sum = 0;
            for (LPort connectedPort : port.getConnectedPorts()) {
                sum += portPos[connectedPort.id];
            }
            if (port.getDegree() == 0) {
                portBarycenter[port.id] = -1;
            } else {
                portBarycenter[port.id] = sum / port.getDegree();
            }
        }
        // sort the ports by considering the side, type, and barycenter values
        sortPorts(node, portBarycenter);
    }

    /**
     * Sort the ports of a node using relative position values.
     * 
     * @param node
     *            a node
     * @param position
     *            an array of position values; the identifier of each port of this node must be
     *            inside the range of the {@code position} array, since it is used as index
     */
    private static void sortPorts(final LNode node, final float[] position) {
        Collections.sort(node.getPorts(), new Comparator<LPort>() {
            public int compare(final LPort port1, final LPort port2) {
                PortSide side1 = port1.getSide();
                PortType type1 = port1.getNetFlow() >= 0 ? PortType.INPUT : PortType.OUTPUT;
                PortSide side2 = port2.getSide();
                PortType type2 = port2.getNetFlow() >= 0 ? PortType.INPUT : PortType.OUTPUT;

                if (side1 != side2) {
                    // sort according to the node side
                    return side1.ordinal() - side2.ordinal();
                } else if (type1 != type2) {
                    // north side: first inputs, then outputs; other sides: reverse
                    if (side1 == PortSide.NORTH) {
                        return type1.ordinal() - type2.ordinal();
                    } else {
                        return type2.ordinal() - type1.ordinal();
                    }
                } else {
                    float pos1 = position[port1.id], pos2 = position[port2.id];
                    // input ports are counter-clockwise, output ports are clockwise
                    if (type1 == PortType.INPUT) {
                        return Float.compare(pos2, pos1);
                    } else {
                        return Float.compare(pos1, pos2);
                    }
                }
            }
        });
    }

}
