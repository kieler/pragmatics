/*
looks  * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
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
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.Util;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.properties.NodeType;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * TODO: Document.
 * 
 * @see HierarchicalPortConstraintProcessor
 * @see HierarchicalPortDummySizeProcessor
 * @see HierarchicalPortOrthogonalEdgeRouter
 * @author cds
 */
public class HierarchicalPortPositionProcessor extends AbstractAlgorithm implements ILayoutProcessor {
    
    /**
     * {@inheritDoc}
     */
    public void process(final LayeredGraph layeredGraph) {
        getMonitor().begin("Hierarchical port position processing", 1);
        
        List<Layer> layers = layeredGraph.getLayers();
        
        if (!layers.isEmpty()) {
            fixCoordinates(layers.get(0), layeredGraph);
            fixCoordinates(layers.get(layers.size() - 1), layeredGraph);
        }
        
        getMonitor().done();
    }
    
    /**
     * Fixes the y coordinates of external port dummies in the given layer.
     * 
     * @param layer the layer.
     * @param layeredGraph the layered graph.
     * @param portConstraints the port constraints that apply to external ports.
     * @param graphHeight height of the graph.
     */
    private void fixCoordinates(final Layer layer, final LayeredGraph layeredGraph) {
        PortConstraints portConstraints = layeredGraph.getProperty(LayoutOptions.PORT_CONSTRAINTS);
        if (!portConstraints.isRatioFixed()) {
            // If coordinates are free to be set, we're done
            return;
        }
        
        double graphHeight = layeredGraph.getSize().y;
        
        // Iterate over external port dummies
        for (LNode node : layer.getNodes()) {
            if (node.getProperty(Properties.NODE_TYPE) != NodeType.EXTERNAL_PORT) {
                continue;
            }
            
            double finalYCoordinate = node.getProperty(Properties.EXT_PORT_RATIO_OR_POSITION);
            
            // Depending on the port constraints applying to external ports, the coordinate
            // needs some fixing up
            if (portConstraints == PortConstraints.FIXED_RATIO) {
                finalYCoordinate *= graphHeight;
            }
            
            node.getPosition().y = finalYCoordinate;
            Util.borderToContentAreaCoordinates(node, layeredGraph, false, true);
        }
    }
    
}
