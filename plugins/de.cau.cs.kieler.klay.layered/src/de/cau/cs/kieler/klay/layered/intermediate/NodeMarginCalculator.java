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
package de.cau.cs.kieler.klay.layered.intermediate;


import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;

/**
 * TODO Document
 * 
 * <dl>
 *   <dt>Precondition:</dt><dd>a layered graph; the positions and sizes of ports and
 *     labels are fixed; nodes are not placed yet; edges are not routed yet.</dd>
 *   <dt>Postcondition:</dt><dd>the node margins are properly set to form a bounding box
 *     around the node and its ports and labels.</dd>
 *   <dt>Slots:</dt><dd>Before phase 4.</dd>
 *   <dt>Same-slot dependencies:</dt><dd>{@link PortArranger}</dd>
 * </dl>
 *
 * @see PortArranger
 * @author cds
 */
public class NodeMarginCalculator extends AbstractAlgorithm implements ILayoutProcessor {

    /**
     * {@inheritDoc}
     */
    public void process(final LayeredGraph layeredGraph) {
        getMonitor().begin("Node margin calculation", 1);
        
        // TODO Implement
        
        getMonitor().done();
    }

}
