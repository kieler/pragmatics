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

import java.util.List;

import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;


/**
 * calculates port ranks and distributes ports.
 * 
 * @author msp
 * @author cds
 * @author ima
 */
public interface IPortDistributor {
    
    /**
     * Determine positions for all ports in the given layer. Input and output ports are processed
     * separately.
     * 
     * @param layer
     *            a layer
     */
    void calculatePortRanks(final LNode[] layer);
    
    /**
     * Returns a list of input ports, beginning at the top right port of the eastern side, going
     * clockwise.
     * 
     * @param node
     *            the node whose input ports to return.
     * @return list of input ports.
     */
    List<LPort> getSortedInputPorts(final LNode node);
    
    /**
     * Distribute the ports of the layered graph depending on the port constraints.
     * 
     * @param layeredGraph
     *            a layered graph
     */
    void distributePorts(final LNode[][] layeredGraph);
    
}