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
package de.cau.cs.kieler.klay.layered.components;

import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LLabel;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;

/**
 * Provides a utility method to move graphs to a new destination. Since this functionality is required
 * by virtually all graph placers, they should extend this class instead of implementing
 * {@link IGraphPlacer}.
 * 
 * @author cds
 */
abstract class AbstractGraphPlacer implements IGraphPlacer {

    /**
     * Move the source graph into the destination graph using a specified offset.
     * 
     * @param destGraph the destination graph
     * @param sourceGraph the source graph
     * @param offsetx x coordinate offset
     * @param offsety y coordinate offset
     */
    protected void moveGraph(final LayeredGraph destGraph, final LayeredGraph sourceGraph,
            final double offsetx, final double offsety) {
        
        KVector graphOffset = sourceGraph.getOffset().translate(offsetx, offsety);
        for (Layer layer : sourceGraph) {
            for (LNode node : layer) {
                node.getPosition().add(graphOffset);
                for (LPort port : node.getPorts()) {
                    for (LEdge edge : port.getOutgoingEdges()) {
                        edge.getBendPoints().translate(graphOffset);
                        for (LLabel label : edge.getLabels()) {
                            label.getPosition().add(graphOffset);
                        }
                    }
                }
                destGraph.getLayerlessNodes().add(node);
            }
        }
    }

}
