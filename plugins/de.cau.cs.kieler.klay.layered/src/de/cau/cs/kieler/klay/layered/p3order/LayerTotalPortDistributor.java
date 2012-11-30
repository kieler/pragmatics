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
 *
 * @author msp
 */
class LayerTotalPortDistributor extends AbstractPortDistributor {
    
    private int cumulativeNodeRank;

    /**
     * Constructs a layer-total port distributor with the given array of ranks.
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
            int increase = 0;
            for (LPort port : node.getPorts(type)) {
                portRanks[port.id] = cumulativeNodeRank + 1;
                increase = 1;
            }
            
            // increase the cumulative node rank only if at least one outgoing edge was found
            cumulativeNodeRank += increase;
        }
    }

}
