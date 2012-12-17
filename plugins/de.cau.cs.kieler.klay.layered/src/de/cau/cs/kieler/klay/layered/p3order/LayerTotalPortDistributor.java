/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.p3order;

import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.properties.PortType;

/**
 * Calculates port ranks in a layer-total way.
 * 
 * <p>Port ranks are calculated by giving each node {@code i} a range of values {@code [i,i+x]},
 * where {@code x} is the number of input ports or output ports. Hence nodes with many ports are
 * assigned a broader range of ranks than nodes with few ports.</p>
 * 
 * <p>The port rank calculation done here depends on the fact that
 * {@link #calculatePortRanks(LNode, int, PortType)} is always called iteratively for all nodes
 * of a layer according to the current order. The rank of nodes is accumulated, since it does
 * not only depend on the node index, but also on the number of ports of preceding nodes.</p>
 *
 * @author msp
 */
class LayerTotalPortDistributor extends AbstractPortDistributor {
    
    /** the cumulative node rank of the currently processed layer. */
    private int cumulativeNodeRank;

    /**
     * Constructs a layer-total port distributor with the given array of ranks.
     * All ports are required to be assigned ids in the range of the given array.
     * 
     * @param portRanks
     *            The array of port ranks
     */
    public LayerTotalPortDistributor(final float[] portRanks) {
        super(portRanks);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void calculatePortRanks(final LNode node, final int nodeIx, final PortType type) {
        float[] portRanks = getPortRanks();
        
        // reset the cumulative node rank when a new calculation has started
        if (nodeIx == 0) {
            cumulativeNodeRank = 0;
        }

        if (node.getProperty(LayoutOptions.PORT_CONSTRAINTS).isOrderFixed()) {

            switch (type) {
            case INPUT: {
                // Count the number of input ports, and additionally the north-side input ports
                int inputCount = 0, northInputCount = 0;
                for (LPort port : node.getPorts()) {
                    if (!port.getIncomingEdges().isEmpty()) {
                        inputCount++;
                        if (port.getSide() == PortSide.NORTH) {
                            northInputCount++;
                        }
                    }
                }
                
                // Assign port ranks in the order north - west - south - east
                int northPos = cumulativeNodeRank + northInputCount;
                int restPos = cumulativeNodeRank + inputCount;
                for (LPort port : node.getPorts(PortType.INPUT)) {
                    if (port.getSide() == PortSide.NORTH) {
                        portRanks[port.id] = northPos;
                        northPos--;
                    } else {
                        portRanks[port.id] = restPos;
                        restPos--;
                    }
                }
                
                // update the cumulative node rank according to the number of input ports
                cumulativeNodeRank += inputCount;
                break;
            }
                
            case OUTPUT: {
                // Iterate output ports in their natural order, that is north - east - south - west
                for (LPort port : node.getPorts(PortType.OUTPUT)) {
                    // increase the cumulative node rank and assign the new value to the current port
                    portRanks[port.id] = ++cumulativeNodeRank;
                }
                break;
            }
            
            default:
                // this means illegal input to the method
                throw new IllegalArgumentException();
            }
            
        } else {
            // determine the minimal and maximal increment depending on port sides
            float minIncr = INCR_FOUR;
            float maxIncr = 0;
            for (LPort port : node.getPorts(type)) {
                float incr = getPortIncr(type, port.getSide());
                minIncr = Math.min(minIncr, incr - 1);
                maxIncr = Math.max(maxIncr, incr);
            }

            if (maxIncr > minIncr) {
                // make sure that ports on different sides get different ranks
                for (LPort port : node.getPorts(type)) {
                    portRanks[port.id] = cumulativeNodeRank
                            + getPortIncr(type, port.getSide()) - minIncr;
                }
            
                cumulativeNodeRank += maxIncr - minIncr;
            }
        }
    }

    private static final float INCR_ONE = 1;
    private static final float INCR_TWO = 2;
    private static final float INCR_THREE = 3;
    private static final float INCR_FOUR = 4;

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
            
        default:
            // this means illegal input to the method
            throw new IllegalArgumentException("Port type is undefined");
        }
        return 0;
    }

}
