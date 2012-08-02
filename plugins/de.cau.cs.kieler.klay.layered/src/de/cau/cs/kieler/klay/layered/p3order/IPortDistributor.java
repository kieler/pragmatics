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

import de.cau.cs.kieler.klay.layered.graph.LNode;
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
public interface IPortDistributor {
    
    /**
     * Determine positions for all ports of specific type in the given layer. Input and output ports
     * are processed separately.
     * 
     * @param layer
     *            a layer as node array
     * @param portType
     *            the port type to consider
     */
    void calculatePortRanks(final LNode[] layer, final PortType portType);
    
    /**
     * Determine positions for all ports of specific type in the given layer. Input and output ports
     * are processed separately. Entries that contain multiple nodes are ignored.
     * 
     * @param layer
     *            a layer as node group array
     * @param portType
     *            the port type to consider
     */
    void calculatePortRanks(final NodeGroup[] layer, final PortType portType);
    
    /**
     * Distribute the ports of the layered graph depending on the port constraints.
     * 
     * @param layeredGraph
     *            a layered graph as node array
     */
    void distributePorts(final LNode[][] layeredGraph);
    
    /**
     * Distribute the ports of the layered graph depending on the port constraints.
     * 
     * @param layeredGraph
     *            a layered graph as node group array
     */
    void distributePorts(final NodeGroup[][] layeredGraph);
    
}