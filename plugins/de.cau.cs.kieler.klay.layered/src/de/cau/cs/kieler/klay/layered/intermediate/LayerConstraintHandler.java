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
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.p2layers.LayerConstraint;

/**
 * Moves nodes with layer constraints to the appropriate layers.
 * 
 * <dl>
 *   <dt>Precondition:</dt><dd>a layered graph.</dd>
 *   <dt>Postcondition:</dt><dd>.odes with layer constraints have been placed in the
 *     appropriate layers.</dd>
 *   <dt>Slots:</dt><dd>Before phase 3.</dd>
 *   <dt>Same-slot dependencies:</dt><dd>{@link LongEdgeSplitter}</dd>
 * </dl>
 *
 * @author cds
 */
public class LayerConstraintHandler extends AbstractAlgorithm implements ILayoutProcessor {

    /**
     * {@inheritDoc}
     */
    public void process(final LayeredGraph layeredGraph) {
        getMonitor().begin("Layer constraint handling", 1);
        
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
                    applyLayerConstraint(node, firstLayer, true);
                    break;
                
                case FIRST_SEPARATE:
                    applyLayerConstraint(node, newFirstLayer, true);
                    break;
                
                case LAST:
                    applyLayerConstraint(node, lastLayer, false);
                    break;
                
                case LAST_SEPARATE:
                    applyLayerConstraint(node, newLastLayer, false);
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
    
    /**
     * Places the node in the given layer, taking care to reverse the necessary edges.
     * 
     * @param node the node to place in the layer.
     * @param layer the layer to place the node in.
     * @param first {@code true} if the node is only allowed to have outgoing edges, {@code false}
     *              if it is only allowed to have incoming edges.
     */
    private void applyLayerConstraint(final LNode node, final Layer layer, final boolean first) {
        node.setLayer(layer);
        
        // Iterate through the node's edges and reverse them, if necessary
        for (LPort port : node.getPorts()) {
            // Iterate over an array of edges to avoid ConcurrentModificationExceptions
            LEdge[] edges = port.getEdges().toArray(new LEdge[0]);
            
            for (LEdge edge : edges) {
                if ((first && edge.getSource().getNode() != node)
                        || (!first && edge.getTarget().getNode() != node)) {
                    
                    edge.reverse();
                }
            }
        }
    }

}
