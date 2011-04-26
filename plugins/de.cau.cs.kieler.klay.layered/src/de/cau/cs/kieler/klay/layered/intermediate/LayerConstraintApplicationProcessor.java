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

import java.util.List;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.Properties;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.p2layers.LayerConstraint;

/**
 * Moves nodes with layer constraints to the appropriate layers. To meet the preconditions of
 * this processor, the {@link LayerConstraintEdgeReverser} can be used.
 * 
 * <dl>
 *   <dt>Precondition:</dt><dd>a layered graph; nodes to be placed in the first layer have only
 *     outgoing edges; nodes to be placed in the last layer have only incoming edges.</dd>
 *   <dt>Postcondition:</dt><dd>nodes with layer constraints have been placed in the
 *     appropriate layers.</dd>
 *   <dt>Slots:</dt><dd>Before phase 3.</dd>
 *   <dt>Same-slot dependencies:</dt><dd>{@link LongEdgeSplitter}</dd>
 * </dl>
 * 
 * @see LayerConstraintEdgeReverser
 * @author cds
 */
public class LayerConstraintApplicationProcessor extends AbstractAlgorithm implements ILayoutProcessor {

    /**
     * {@inheritDoc}
     */
    public void process(final LayeredGraph layeredGraph) {
        getMonitor().begin("Layer constraint application", 1);
        
        List<Layer> layers = layeredGraph.getLayers();
        
        // Retrieve the current first and last layers
        Layer firstLayer = layers.get(0);
        Layer lastLayer = layers.get(layers.size() - 1);
        
        // Create the new first and last layers, in case they will be needed
        Layer newFirstLayer = new Layer(layeredGraph);
        Layer newLastLayer = new Layer(layeredGraph);
        
        // Iterate through the current list of layers
        for (Layer layer : layers) {
            // Iterate through a node array to avoid ConcurrentModificationExceptions
            LNode [] nodes = layer.getNodes().toArray(new LNode[0]);
            
            for (LNode node : nodes) {
                LayerConstraint constraint = node.getProperty(Properties.LAYER_CONSTRAINT);
                
                // Check if there is a layer constraint
                switch (constraint) {
                case FIRST:
                    node.setLayer(firstLayer);
                    break;
                
                case FIRST_SEPARATE:
                    node.setLayer(newFirstLayer);
                    break;
                
                case LAST:
                    node.setLayer(lastLayer);
                    break;
                
                case LAST_SEPARATE:
                    node.setLayer(newLastLayer);
                    break;
                }
            }
        }
        
        // Add non-empty new first and last layers
        if (!newFirstLayer.getNodes().isEmpty()) {
            layers.add(0, newFirstLayer);
        }

        if (!newLastLayer.getNodes().isEmpty()) {
            layers.add(newLastLayer);
        }
        
        getMonitor().done();
    }

}
