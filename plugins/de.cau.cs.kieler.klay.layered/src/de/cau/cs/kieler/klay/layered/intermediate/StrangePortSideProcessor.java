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

import java.util.List;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;

/**
 * Inserts dummy nodes to cope with strange port sides.
 * 
 * <p>
 * The problem is with edges coming from the left of a node being connected to a
 * port that's on its right side, or the other way around. Let a node of that kind
 * be in layer {@code i}. This processor now takes the offending edge and connects
 * it to a new dummy node, also in layer {@code i}. That dummy node is then connected
 * to another new dummy node, in layer {@code i + 1}. Finally, a new edge connects
 * the offending port with the dummy node in layer {@code i + 1}.
 * 
 * <p>
 * The dummy nodes are decorated with a {@link de.cau.cs.kieler.klay.layered.Properties.NODE_TYPE}
 * property, its value being set to
 * {@link de.cau.cs.kieler.klay.layered.Properties.NodeType.STRANGE_PORT_SIDE}.
 * 
 * <dl>
 *   <dt>Precondition:</dt><dd>a layered graph.</dd>
 *   <dt>Postcondition:</dt><dd>dummy nodes have been insertes for edges connected to
 *     ports on strange sides.</dd>
 * </dl>
 * 
 * @author cds
 */
public class StrangePortSideProcessor extends AbstractAlgorithm implements ILayoutProcessor {

    /**
     * {@inheritDoc}
     */
    public void process(final LayeredGraph layeredGraph) {
        List<Layer> layers = layeredGraph.getLayers();
        int layerCount = layers.size();
        
        // Iterate through the layers
        for (int layerIndex = 0; layerIndex < layerCount; layerIndex++) {
            Layer layer = layers.get(layerIndex);
            
            // Iterate through the layer's nodes
            for (LNode node : layer.getNodes()) {
                // Look for ports on the right side connected to edges originating from lower layers
                for (LPort port : node.getPorts(PortSide.EAST)) {
                    
                }
                
                // Look for ports on the left side connected to edges going to higher layers
                for (LPort port : node.getPorts(PortSide.WEST)) {
                    
                }
            }
        }
    }

}
