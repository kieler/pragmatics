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
 * TODO Write description.
 * 
 * <dl>
 *   <dt>Precondition:</dt><dd>a layered graph.</dd>
 *   <dt>Postcondition:</dt><dd>dummy nodes have been inserted for edges connected to
 *     ports on odd sides.</dd>
 * </dl>
 * 
 * @author cds
 */
public class PortSideAndOrderProcessor extends AbstractAlgorithm implements ILayoutProcessor {

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
        
    }

}
