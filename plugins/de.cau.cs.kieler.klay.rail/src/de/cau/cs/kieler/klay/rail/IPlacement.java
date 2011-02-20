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
package de.cau.cs.kieler.klay.rail;

import de.cau.cs.kieler.klay.layered.graph.LPort;

/**
 * Interface to allow generalization of node placement. This is needed for lookahead placement, so
 * the original placement code can be used for simulation without changes.
 * 
 * @author jjc
 * 
 */
public interface IPlacement {

    /**
     * Method which determines what should happen while placing a node.
     * 
     * @param targetPort The port of the node to place.
     * @param port The origin's node port.
     * @param offset The position offset got from e.g. lookahead. Leave 0 if not wanted.
     */
    void place(LPort targetPort, LPort port, int offset);

}
