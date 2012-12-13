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
import java.util.List;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.properties.PortType;

/**
 * Calculates port ranks and distributes ports.
 * The <em>rank</em> of a port is a floating point number that represents its position
 * inside the containing layer. This depends on the node order of that layer and on the
 * port constraints of the nodes. Port ranks are used by {@link ICrossingMinimizationHeuristic}s
 * for calculating barycenter or median values for nodes. Furthermore, they are used in this
 * class for distributing the ports of nodes where the order of ports is not fixed,
 * which has to be done as the last step of each crossing minimization processor.
 * There are different ways to determine port ranks, therefore that is done in concrete subclasses.
 * 
 * @author cds
 * @author ima
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
abstract class AbstractPortDistributor {

    /** port ranks array in which the results of ranks calculation are stored. */
    private float[] portRanks;

    /**
     * Constructs a port distributor for the given array of port ranks.
     * All ports are required to be assigned ids in the range of the given array.
     * 
     * @param portRanks
     *            The array of port ranks
     */
    public AbstractPortDistributor(final float[] portRanks) {
        this.portRanks = portRanks;
    }
    
    /**
     * Returns the array of port ranks.
     * 
     * @return the array of port ranks
     */
    public float[] getPortRanks() {
        return portRanks;
    }

    
    // /////////////////////////////////////////////////////////////////////////////
    // Port Rank Assignment
    
    /**
     * Assign port ranks for the input or output ports of the given node. If the node's port
     * constraints imply a fixed order, the ports are assumed to be pre-ordered in the usual way,
     * i.e. in clockwise order north - east - south - west.
     * The ranks are written to the {@link #getPortRanks()} array.
     * 
     * @param node
     *            a node
     * @param nodeIx
     *            the node's index in its layer
     * @param type
     *            the port type to consider
     * @see de.cau.cs.kieler.klay.layered.intermediate.PortListSorter
     */
    protected abstract void calculatePortRanks(final LNode node, final int nodeIx, final PortType type);
    
    /**
     * Determine ranks for all ports of specific type in the given layer.
     * The ranks are written to the {@link #getPortRanks()} array.
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
     * Determine ranks for all ports of specific type in the given layer. Entries that
     * contain multiple nodes are ignored. The ranks are written to the {@link #getPortRanks()} array.
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
     * Distribute the ports of each node in the layered graph depending on the port constraints.
     * 
     * @param layeredGraph
     *            a layered graph as node array
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
     * Distribute the ports of each node in the layered graph depending on the port constraints.
     * 
     * @param layeredGraph
     *            a layered graph as node group array
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
     * 
     * @param node node whose ports shall be sorted
     * @param nodeIndex the index of the given node
     */
    private void distributePorts(final LNode node, final int nodeIndex) {
        if (!node.getProperty(LayoutOptions.PORT_CONSTRAINTS).isOrderFixed()) {
            // the order of ports on each side is variable, so distribute the ports
            if (node.getPorts().size() > 1) {
                // array of port barycenter values calculated from ranks of connected ports
                Float[] portBarycenter = new Float[portRanks.length];
                
                // list that keeps track of ports connected to other ports in the same layer; these are
                // treated specially when calculating barycenters
                List<LPort> inLayerPorts = Lists.newLinkedList();
                
                // the maximum barycenter value assigned for ports of this node
                float maxBarycenter = 0.0f;
                
                // calculate barycenter values for the ports of the node
                PortIteration:
                for (LPort port : node.getPorts()) {
                    // add up all ranks of connected ports
                    float sum = 0;
                    for (LPort connectedPort : port.getConnectedPorts()) {
                        if (connectedPort.getNode().getLayer() == node.getLayer()) {
                            inLayerPorts.add(port);
                            continue PortIteration;
                        } else {
                            sum += portRanks[connectedPort.id];
                        }
                    }
                    
                    if (port.getDegree() == 0) {
                        // We don't have a barycenter value
                        portBarycenter[port.id] = null;
                    } else {
                        portBarycenter[port.id] = sum / port.getDegree();
                        maxBarycenter = Math.max(maxBarycenter, portBarycenter[port.id]);
                    }
                }
                
                // go through the list of in-layer ports and calculate their barycenter values
                int nodeIndexInLayer = node.getIndex();
                for (LPort inLayerPort : inLayerPorts) {
                    // add the indices of all connected in-layer ports
                    int sum = 0;
                    int inLayerConnections = 0;
                    
                    for (LPort connectedPort : inLayerPort.getConnectedPorts()) {
                        if (connectedPort.getNode().getLayer() == node.getLayer()) {
                            sum += connectedPort.getNode().getIndex();
                            inLayerConnections++;
                        }
                    }
                    
                    // the port's barycenter value is the mean index of connected ports. If that index
                    // is lower than the node's index, most in-layer edges will point upwards. We will
                    // give the port a negative barycenter then to have it show up top. If most edges
                    // point downwards, we add the maximum barycenter value encountered so far to the
                    // one calculated here to have it show up at the bottom.
                    float barycenter = ((float) sum) / inLayerConnections;
                    
                    if (barycenter < nodeIndexInLayer) {
                        portBarycenter[inLayerPort.id] = -1 * barycenter;
                    } else {
                        portBarycenter[inLayerPort.id] = maxBarycenter + barycenter;
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
    private static void sortPorts(final LNode node, final Float[] position) {
        Collections.sort(node.getPorts(), new Comparator<LPort>() {
            public int compare(final LPort port1, final LPort port2) {
                PortSide side1 = port1.getSide();
                PortSide side2 = port2.getSide();

                if (side1 != side2) {
                    // sort according to the node side
                    return side1.ordinal() - side2.ordinal();
                } else {
                    Float pos1 = position[port1.id];
                    Float pos2 = position[port2.id];
                    
                    if (pos1 == null && pos2 == null) {
                        // the ports are not connected to anything -- leave their order untouched
                        return 0;
                    } else if (pos1 == null) {
                        return -1;
                    } else if (pos2 == null) {
                        return 1;
                    } else {
                        // different port sides are sorted differently
                        if (side1 == PortSide.NORTH || side1 == PortSide.EAST) {
                            return Float.compare(pos1, pos2);
                        } else {
                            return Float.compare(pos2, pos1);
                        }
                    }
                }
            }
        });
    }
    
}
