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

/**
 * Calculates port ranks and distributes ports.
 * 
 * @author cds
 * @author ima
 * @author msp
 * @kieler.rating 2012-07-10 proposed yellow msp
 */
public interface IPortDistributor {
    
    /**
     * Determine positions for all ports in the given layer. Input and output ports are processed
     * separately.
     * 
     * @param layer
     *            a layer as node array
     */
    void calculatePortRanks(final LNode[] layer);
    
    /**
     * Determine positions for all ports in the given layer. Input and output ports are processed
     * separately. Entries that contain multiple nodes are ignored.
     * 
     * @param layer
     *            a layer as node group array
     */
    void calculatePortRanks(final NodeGroup[] layer);
    
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