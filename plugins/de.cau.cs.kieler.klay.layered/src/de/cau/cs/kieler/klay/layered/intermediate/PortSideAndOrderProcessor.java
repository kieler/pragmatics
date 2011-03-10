/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.intermediate;

import java.util.Arrays;
import java.util.Comparator;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.Properties;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;

/**
 * Makes sure ports have at least a fixed side. If they don't, input ports are assigned
 * to the left and output ports to the right side. If the port order is fixed, the node's
 * list of ports is sorted, beginning at the leftmost northern port, going clockwise.
 * 
 * <dl>
 *   <dt>Precondition:</dt><dd>a layered graph.</dd>
 *   <dt>Postcondition:</dt><dd>all nodes have their ports distributed, with port constraints
 *     set to fixed sides at the least.</dd>
 * </dl>
 * 
 * @author cds
 */
public class PortSideAndOrderProcessor extends AbstractAlgorithm implements ILayoutProcessor {
    
    /**
     * A comparer for ports. Ports are sorted by side (north, east, south, west) in
     * clockwise order, beginning at the top left corner.
     * 
     * @author cds
     */
    private static class PortComparator implements Comparator<LPort> {

        /**
         * {@inheritDoc}
         */
        public int compare(final LPort port1, final LPort port2) {
            int ordinalDifference = port1.getSide().ordinal() - port2.getSide().ordinal();
            
            // Sort by side first
            if (ordinalDifference != 0) {
                return ordinalDifference;
            }
            
            // In case of equal sides, sort by position next
            switch (port1.getSide()) {
            case NORTH:
                // Compare x coordinates
                return convertDifferenceToInt(port1.getPos().x - port2.getPos().x);
            
            case EAST:
                // Compare y coordinates
                return convertDifferenceToInt(port1.getPos().y - port2.getPos().y);
            
            case SOUTH:
                // Compare x coordinates
                return convertDifferenceToInt(port2.getPos().x - port1.getPos().x);
            
            case WEST:
                // Compare y coordinates
                return convertDifferenceToInt(port2.getPos().y - port1.getPos().y);
            }
            
            // Port side undefined; shouldn't happen. EVER!
            return 0;
        }
        
        /**
         * Converts the given difference to {@code -1} if it's negative, {@code 0} if
         * it's zero and {@code 1} if it's positive.
         * 
         * @param diff the value to be converted.
         * @return {@code -1}, {@code 0} or {@code 1}.
         */
        private int convertDifferenceToInt(final double diff) {
            if (diff < 0.0) {
                return -1;
            } else if (diff == 0) {
                return 0;
            } else {
                return 1;
            }
        }
        
    }
    

    /**
     * {@inheritDoc}
     */
    public void process(final LayeredGraph layeredGraph) {
        getMonitor().begin("Port side and order processing", 1);
        
        // Iterate through the nodes of all layers
        for (Layer layer : layeredGraph.getLayers()) {
            for (LNode node : layer.getNodes()) {
                PortConstraints portConstraints = node.getProperty(Properties.PORT_CONS);
                
                if (!portConstraints.isSideFixed()) {
                    // We need to distribute the ports
                    distributePorts(node);
                    node.setProperty(Properties.PORT_CONS, PortConstraints.FIXED_SIDE);
                } else if (portConstraints.isOrderFixed()) {
                    // We need to sort the port list accordingly
                    sortPorts(node);
                }
            }
        }
        
        getMonitor().done();
    }
    
    /**
     * Places input ports on the left side and output ports on the right side of nodes.
     * 
     * @param node node with free port placement.
     */
    private void distributePorts(final LNode node) {
        for (LPort port : node.getPorts()) {
            switch (port.getType()) {
            case INPUT:
                port.setSide(PortSide.WEST);
                break;
            case OUTPUT:
                port.setSide(PortSide.EAST);
                break;
            }
        }
    }
    
    /**
     * Sorts the list of nodes according to their placement, starting in the upper left
     * corner and going around the node in a clockwise direction.
     * 
     * @param node the node whose port list to sort.
     */
    private void sortPorts(final LNode node) {
        LPort[] portsArray = node.getPorts().toArray(new LPort[0]);
        Arrays.sort(portsArray, new PortComparator());
        node.getPorts().clear();
        node.getPorts().addAll(Arrays.asList(portsArray));
    }

}
