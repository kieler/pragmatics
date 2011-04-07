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
import de.cau.cs.kieler.core.math.KInsets;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
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
        
        // Iterate through the layers
        for (Layer layer : layeredGraph.getLayers()) {
            // Iterate through the layer's nodes
            for (LNode node : layer.getNodes()) {
                // Reset the margin
                KInsets.Double margin = node.getMargin();
                margin.top = 0.0;
                margin.bottom = 0.0;
                margin.left = 0.0;
                margin.right = 0.0;
                
                // Ports
                for (LPort port : node.getPorts()) {
                    switch (port.getSide()) {
                    case NORTH:
                        margin.top = Math.max(margin.top,
                                -(port.getPosition().y - port.getSize().y / 2.0));
                        break;
                    
                    case EAST:
                        margin.right = Math.max(margin.right,
                                port.getPosition().x + port.getSize().x / 2.0 - node.getSize().x);
                        break;
                    
                    case SOUTH:
                        margin.bottom = Math.max(margin.bottom,
                                port.getPosition().y + port.getSize().x / 2.0 - node.getSize().y);
                        break;
                    
                    case WEST:
                        margin.left = Math.max(margin.left,
                                -(port.getPosition().x - port.getSize().x / 2.0));
                        break;
                    }
                }
                
                // TODO Take labels into account as well.
            }
        }
        
        getMonitor().done();
    }

}
