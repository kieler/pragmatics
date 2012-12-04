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

import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.properties.PortType;

/**
 * Calculates port ranks and distributes ports.
 * 
 * @author cds
 * @author ima
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
abstract class AbstractPortDistributor {

    /** barycenter values for ports. */
    private float[] portBarycenter;
    /** port position array. */
    private float[] portRanks;

    /**
     * Constructs a port distributor with the given array of ranks.
     * 
     * @param portRanks
     *            The array of port ranks
     */
    public AbstractPortDistributor(final float[] portRanks) {
        this.portRanks = portRanks;
        portBarycenter = new float[portRanks.length];
    }
    
    /**
     * Returns the array of port ranks.
     * 
     * @return the array of port ranks
     */
    protected float[] getPortRanks() {
        return portRanks;
    }

    
    // /////////////////////////////////////////////////////////////////////////////
    // Port Rank Assignment
    
    /**
     * Assign port positions for the input or output ports of the given node.
     * 
     * @param node
     *            a node
     * @param nodeIx
     *            the node's index in its layer
     * @param type
     *            the port type to consider
     */
    protected abstract void calculatePortRanks(final LNode node, final int nodeIx, final PortType type);
    
    /**
     * Determine positions for all ports of specific type in the given layer. Input and output ports
     * are processed separately.
     * 
     * @param layer
     *            a layer as node array
     * @param portType
     *            the port type to consider
     */
    public final void calculatePortRanks(final LNode[] layer, final PortType portType) {
        for (int nodeIx = 0; nodeIx < layer.length; nodeIx++) {
            calculatePortRanks(layer[nodeIx], nodeIx, portType);
        }
    }
    
    /**
     * Determine positions for all ports of specific type in the given layer. Input and output ports
     * are processed separately. Entries that contain multiple nodes are ignored.
     * 
     * @param layer
     *            a layer as node group array
     * @param portType
     *            the port type to consider
     */
    public final void calculatePortRanks(final NodeGroup[] layer, final PortType portType) {
        for (int nodeIx = 0; nodeIx < layer.length; nodeIx++) {
            NodeGroup nodeGroup = layer[nodeIx];
            if (nodeGroup.getNodes().length == 1) {
                calculatePortRanks(nodeGroup.getNode(), nodeIx, portType);
            }
        }
    }
    
    
    // /////////////////////////////////////////////////////////////////////////////
    // Port Distribution

    /**
     * {@inheritDoc}
     */
    public final void distributePorts(final LNode[][] layeredGraph) {
        for (int l = 0; l < layeredGraph.length; l++) {
            if (l + 1 < layeredGraph.length) {
                calculatePortRanks(layeredGraph[l + 1], PortType.INPUT);
            }
            LNode[] layer = layeredGraph[l];
            for (int i = 0; i < layer.length; i++) {
                distributePorts(layer[i], i);
            }
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public final void distributePorts(final NodeGroup[][] layeredGraph) {
        for (int l = 0; l < layeredGraph.length; l++) {
            if (l + 1 < layeredGraph.length) {
                calculatePortRanks(layeredGraph[l + 1], PortType.INPUT);
            }
            NodeGroup[] layer = layeredGraph[l];
            for (int i = 0; i < layer.length; i++) {
                distributePorts(layer[i].getNode(), i);
            }
        }
    }

    /**
     * Distribute the ports of the given node by their sides, connected ports, and input or output
     * type.
     * FIXME this does not properly consider the FixedSides scenario
     * 
     * @param node node whose ports shall be sorted
     * @param nodeIndex the index of the given node
     */
    private void distributePorts(final LNode node, final int nodeIndex) {
        if (!node.getProperty(LayoutOptions.PORT_CONSTRAINTS).isOrderFixed()) {
            // the order of ports on each side is variable, so distribute the ports
            if (node.getPorts().size() > 1) {
                // calculate barycenter values for the ports of the node
                for (LPort port : node.getPorts()) {
                    float sum = 0;
                    for (LPort connectedPort : port.getConnectedPorts()) {
                        sum += portRanks[connectedPort.id];
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
            node.setProperty(LayoutOptions.PORT_CONSTRAINTS, PortConstraints.FIXED_ORDER);
        }
        
        // update the port ranks after reordering
        calculatePortRanks(node, nodeIndex, PortType.OUTPUT);
    }

    /**
     * Sort the ports of a node using the given relative position values.
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
